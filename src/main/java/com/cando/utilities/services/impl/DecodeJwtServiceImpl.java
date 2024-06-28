/*
 *  DecodeJwtService.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.services.impl;

import com.cando.utilities.model.CertJsonBean;
import com.cando.utilities.services.DecodeJwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DecodeJwtServiceImpl implements DecodeJwtService {

  @Autowired
  private JsonPrettyPrinterServiceImpl jsonPrettyPrinterService;

  private static final Logger LOGGER = LoggerFactory.getLogger(DecodeJwtServiceImpl.class);
  private StringWriter sw = new StringWriter();
  private PrintWriter pw = new PrintWriter(sw);

  public CertJsonBean decodeJwt(String jwtToken) {

    CertJsonBean certJsonBean = new CertJsonBean();

    try {
      String[] splitString = jwtToken.split("\\.");
      String base64EncodedHeader = splitString[0];
      String base64EncodedBody = splitString[1];
      String base64EncodedSignature = splitString[2];

      String header = new String(getDecodeJwt(base64EncodedHeader));
      var jwtHeader = jsonPrettyPrinterService.printJsonObject(header);

      String body = new String(getDecodeJwt(base64EncodedBody));
      var jwtBody = jsonPrettyPrinterService.printJsonObject(body);

      String signature = new String(getDecodeJwt(base64EncodedSignature));

      certJsonBean.setHeaderStr(jwtHeader);
      certJsonBean.setPayloadStr(jwtBody);
      certJsonBean.setSignature(signature);
    } catch (JsonProcessingException | IndexOutOfBoundsException e) {
      e.printStackTrace(pw);
      LOGGER.info("Invalid Token: {}", e.getMessage());
      LOGGER.error(sw.getBuffer().toString());

      return new CertJsonBean();
    }

    return certJsonBean;
  }

  private byte[] getDecodeJwt(String base64EncodedString) {
    Base64 base64Url = new Base64(true);
    return base64Url.decode(base64EncodedString);
  }

}