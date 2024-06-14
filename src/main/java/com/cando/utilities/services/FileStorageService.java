/*
 *  FileStorageService.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

  void init();

  void save(MultipartFile file);

  String loadKeystore(String filename, String password);

  Resource load(String filename);

  void deleteAll() throws IOException;

  Stream<Path> loadAll();
}