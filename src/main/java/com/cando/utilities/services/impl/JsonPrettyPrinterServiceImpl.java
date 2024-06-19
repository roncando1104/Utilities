/*
 *  JsonPrettryPrinterService.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.services.impl;

import com.cando.utilities.services.JsonPrettyPrinterService;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JsonPrettyPrinterServiceImpl implements JsonPrettyPrinterService {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonPrettyPrinterServiceImpl.class);
  private StringWriter sw = new StringWriter();
  private PrintWriter pw = new PrintWriter(sw);
  private ObjectMapper objectMapper;

  public String printJsonObject(String json) throws JsonProcessingException {
    objectMapper = new ObjectMapper();
    return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectMapper.readTree(json));
  }

  public String isValidJson(String json) {
    objectMapper = new ObjectMapper()
        .enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
    try {
      objectMapper.readTree(json);
    } catch (JacksonException e) {
      e.printStackTrace(pw);
      LOGGER.info("Invalid JSON: {}", e.getMessage());
      LOGGER.error(sw.getBuffer().toString());

      return sw.getBuffer().toString();
    }
    return "valid";
  }
}