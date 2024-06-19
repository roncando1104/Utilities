package com.cando.utilities.services;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonPrettyPrinterService {

  String printJsonObject (String json) throws JsonProcessingException;

  String isValidJson(String json);
}
