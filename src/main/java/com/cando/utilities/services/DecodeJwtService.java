package com.cando.utilities.services;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface DecodeJwtService {

  String decodeJwt(String jwtToken) throws JsonProcessingException;
}
