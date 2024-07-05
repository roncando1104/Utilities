/*
 *  JsonReader.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.utils;

import com.cando.utilities.model.CertJsonBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JsonReader {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonReader.class);
  private StringWriter sw = new StringWriter();
  private PrintWriter pw = new PrintWriter(sw);

  public CertJsonBean readJsonFile(String json) {
    ObjectMapper objectMapper;
    try {
      objectMapper = new ObjectMapper();
      //return objectMapper.readValue(new File("/Users/JD81ME/workspace/Demos-and-BU/ING-Utilities/src/main/resources/sample-jwt-decoded.json"), CertJsonBean.class);
      return objectMapper.readValue(json, CertJsonBean.class);
    } catch (IOException ie) {
      ie.printStackTrace(pw);
      LOGGER.info("Invalid JSON: {}", ie.getMessage());
      LOGGER.error(sw.getBuffer().toString());

      return null;
    }
  }
}