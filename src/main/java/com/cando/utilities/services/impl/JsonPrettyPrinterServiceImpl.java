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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class JsonPrettyPrinterServiceImpl implements JsonPrettyPrinterService {

  public String printJsonObject(String json) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectMapper.readTree(json));
  }

  public String isValidJsonObject(String json) {
    try {
      new JSONObject(json);
    } catch (JSONException e) {
      return e.getLocalizedMessage();
    }
    return "valid";
  }

  public String isValidJsonArray(String json) {
    try {
      new JSONArray(json);
    } catch (JSONException e) {
      return e.getMessage();
    }
    return "valid";
  }

}