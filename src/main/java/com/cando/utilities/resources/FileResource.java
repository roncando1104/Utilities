/*
 *  FileResource.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.resources;

import com.cando.utilities.model.FileInfo;
import com.cando.utilities.services.FileStorageService;
import com.cando.utilities.utils.Util;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
public class FileResource {

  @Autowired
  private FileStorageService fileStorageService;

  @Value("${service.certificate.keystore-extension}")
  private String[] certificateKeystoreExtension;

  @Value("${service.certificate.token-extension}")
  private String[] certificateTokenExtension;

  @GetMapping("/files/new")
  public String newFile() {
    return "upload_form";
  }

  @PostMapping("/files/upload")
  public String uploadFile(Model model, @RequestParam("file") MultipartFile file) throws IOException {
    String message;
    String errorMessage;

    try {
      fileStorageService.save(file);

      message = String.format("%s uploaded successfully", file.getOriginalFilename());
      model.addAttribute("file", file.getOriginalFilename());
      model.addAttribute("message", message);
    } catch (Exception e) {
      message = String.format("%s could not upload. Error: %s", file.getOriginalFilename(), e.getMessage());
      model.addAttribute("message", message);
    }

    Optional<String> isFilePresent = Util.getExtensionByStringHandling(file.getOriginalFilename());

    if (isFilePresent.isPresent()) {
      var fileExtension = isFilePresent.get();

      boolean valueEqualsKeystoreExtension = Arrays.asList(certificateKeystoreExtension).contains(fileExtension);
      boolean valueEqualsTokenExtension = Arrays.asList(certificateTokenExtension).contains(fileExtension);

      if (valueEqualsKeystoreExtension) {
        return "upload_form";
      } else if (valueEqualsTokenExtension) {
        return "decode_token";
      } else {
        fileStorageService.deleteFilesInDirectory();
        errorMessage = String.format("Error Message: %s is not a certificate or JWT file.", file.getOriginalFilename());
        model.addAttribute("errorMessage", errorMessage);
        return "error_page";
      }
    } else {
      errorMessage = String.format("Error Message: %s", "No file selected!");
      model.addAttribute("errorMessage", errorMessage);
      return "error_page";
    }
  }

  @GetMapping("/files")
  public String getListFiles(Model model) {
    List<FileInfo> fileInfos = fileStorageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FileResource.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).toList();

    model.addAttribute("files", fileInfos);

    return "files";
  }

  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = fileStorageService.load(filename);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }

  @DeleteMapping("/delete")
  public String deleteFile() throws IOException {
    fileStorageService.deleteFilesInDirectory();
    return "redirect:/files";
  }

  @GetMapping("/cancel")
  public String cancel() throws IOException {
    fileStorageService.deleteFilesInDirectory();
    return "index";
  }
}