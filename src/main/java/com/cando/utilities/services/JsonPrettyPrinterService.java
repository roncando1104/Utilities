package com.cando.utilities.services;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonPrettyPrinterService {

  String printJsonObject (String json) throws JsonProcessingException;
  //String printJsonArray (String jsonArray) throws JsonProcessingException;
  String isValidJsonObject(String json);
  String isValidJsonArray(String json);
}
