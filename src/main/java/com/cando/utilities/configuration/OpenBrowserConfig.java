/*
 *  OpenBrowserConfig.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.configuration;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenBrowserConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(OpenBrowserConfig.class);

  private static String HOME_PAGE_URL;

  @Value("${service.home-page}")
  public void setHomePage(String homePage) {
    OpenBrowserConfig.HOME_PAGE_URL = homePage;
  }

  public static void openPage() {

    String operatingSystem = System.getProperty("os.name").toLowerCase();
    Runtime rt = Runtime.getRuntime();

    if (Desktop.isDesktopSupported()) {
      System.setProperty("java.awt.headless", "false");
      Desktop desktop = Desktop.getDesktop();

      try {
        desktop.browse(new URI(HOME_PAGE_URL));
      } catch (IOException | URISyntaxException e) {
        LOGGER.error("Unable to open page in {} OS with error: {}", operatingSystem, e.getMessage());
      }

    } else {
      if (operatingSystem.contains("win")) {
        try {
          rt.exec(new String[]{"cmd", "/c", "chrome.exe", "start", HOME_PAGE_URL});
        } catch (IOException e) {
          LOGGER.error("Unable to open page in Windows with error: {}", e.getMessage());
        }
      } else if (operatingSystem.contains("mac")) {
        try {
          rt.exec(new String[]{"open", "-a", "Google Chrome", HOME_PAGE_URL});
        } catch (IOException e) {
          LOGGER.error("Unable to open page in Mac OS with error: {}", e.getMessage());
        }
      } else {
        LOGGER.error("Not Supported. Unable to open {} page in {} OS", HOME_PAGE_URL, operatingSystem);
        LOGGER.info("Open the page manually in your browser: {}", HOME_PAGE_URL);
      }
    }
  }

}