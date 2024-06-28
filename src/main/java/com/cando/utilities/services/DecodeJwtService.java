package com.cando.utilities.services;

import com.cando.utilities.model.CertJsonBean;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DecodeJwtService {

  CertJsonBean decodeJwt(String jwtToken) throws JsonProcessingException;
}
