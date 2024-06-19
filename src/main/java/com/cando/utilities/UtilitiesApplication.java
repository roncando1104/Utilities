package com.cando.utilities;

import com.cando.utilities.services.FileStorageService;
import jakarta.annotation.Resource;
import java.io.IOException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UtilitiesApplication implements CommandLineRunner {

  @Resource
  private FileStorageService fileStorageService;

  public static void main(String[] args) {

    SpringApplication.run(UtilitiesApplication.class, args);

  }

  @Override
  public void run(String... arg) throws IOException {
    fileStorageService.deleteAll();
    fileStorageService.init();
  }

}

