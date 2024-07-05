package com.cando.utilities.services;

import com.cando.utilities.model.CertJsonBean;
import com.cando.utilities.model.JwtPayload;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DecodeJwtService {

  JwtPayload decodeJwt(String jwtToken) throws JsonProcessingException;
}
