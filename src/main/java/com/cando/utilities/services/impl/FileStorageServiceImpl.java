/*
 *  FileStorageServiceImpl.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.services.impl;

import com.cando.utilities.services.CleanFolder;
import com.cando.utilities.services.FileStorageService;
import com.cando.utilities.utils.Util;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {

  private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageServiceImpl.class);

  private static final String COULD_NOT_READ_FILE = "Could not read file!";
  private static final String COULD_NOT_READ_KEYSTORE = "Could not read keystore file!";

  private final Path root = Paths.get("./uploads");

  @Autowired
  private ViewCertificateContentServiceImpl viewCertificateContentService;

  @Override
  public void init() {
    try {
      //deleteFolderIfNotEmpty(root);
      Files.createDirectories(root);
      LOGGER.info("DIR ENTRIES: {}", root.toAbsolutePath());
    } catch (IOException e) {
      LOGGER.error("Could not initialize folder for upload! Error: {}", e.getMessage());
    }
  }

  @Override
  public void save(MultipartFile file) {
    try {
      Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
    } catch (Exception e) {
      LOGGER.error("A file of that name already exists. Error: {}", e.getMessage());
    }
  }

  @Override
  public String loadKeystore(String filename, String password) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists()) {
        Optional<String> isFileExtPresent = Util.getExtensionByStringHandling(filename);

        if (isFileExtPresent.isPresent()) {
          var fileExt = isFileExtPresent.get();

          if (fileExt.equalsIgnoreCase("jks") || fileExt.equalsIgnoreCase("p12")) {
            return viewCertificateContentService.getCertificateList(filename, password);
          } else if (fileExt.equalsIgnoreCase("pfx")) {
            return viewCertificateContentService.getPfxCertificate(filename, password);
          } else {
            return "Not implemented yet";
          }
        } else {
          LOGGER.error("The filename {} is not supported.", filename);
          return String.format("The filename %s is not supported.", filename);
        }

      } else {
        LOGGER.error(COULD_NOT_READ_KEYSTORE);
        return COULD_NOT_READ_KEYSTORE;
      }
    } catch (MalformedURLException e) {
      LOGGER.error("Error loading keystore: {}", e.getMessage());
      return String.format("Malformed URL: %s", e.getMessage());
    }
  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        LOGGER.error(COULD_NOT_READ_FILE);
        return null;
      }
    } catch (MalformedURLException e) {
      LOGGER.error("Error loading {} file with error {}", filename, e.getMessage());
      return null;
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
    CleanFolder.clean(root);
  }

  @Override
  public void deleteFilesInDirectory() throws IOException {
    FileUtils.cleanDirectory(root.toFile());
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      LOGGER.error("Could not load the files!");
      return Stream.empty();
    }
  }

  private void deleteFolderIfNotEmpty(Path path) {
    var isFolderExist = Files.exists(path);
    LOGGER.info("Folder Exist?: {}", isFolderExist);

    try (Stream<Path> paths = Files.list(path)) {
      if (isFolderExist && paths.findAny().isPresent()) {
        FileUtils.deleteDirectory(new File(path.toString()));
      }
    } catch (IOException e) {
      LOGGER.error("Could not delete folder", e);
    }
  }
}

