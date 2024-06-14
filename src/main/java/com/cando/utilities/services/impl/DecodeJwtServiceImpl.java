/*
 *  DecodeJwtService.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.services.impl;

import com.cando.utilities.services.DecodeJwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DecodeJwtServiceImpl implements DecodeJwtService {

  @Autowired
  private JsonPrettyPrinterServiceImpl jsonPrettyPrinterService;

  public String decodeJwt(String jwtToken) throws JsonProcessingException {

    String[] splitString = jwtToken.split("\\.");
    String base64EncodedHeader = splitString[0];
    String base64EncodedBody = splitString[1];
    String base64EncodedSignature = splitString[2];

    Base64 base64Url = new Base64(true);
    String header = new String(base64Url.decode(base64EncodedHeader));

    String body = new String(base64Url.decode(base64EncodedBody));
    var jwtBody = jsonPrettyPrinterService.printJsonObject(body);

    String signature = new String(base64Url.decode(base64EncodedSignature));

    //var decodedJwtToken = String.format("%s %n %s %n JWT Header: %s %n %s %n JWT Body: %s %n %s %n JWT Signature: %s", header, body, signature);

    return "------------ Decode JWT ------------" + "\n\n"
        + "~~~~~~~~~ JWT Header ~~~~~~~" + "\n\n"
        + "JWT Header: " + header + "\n\n"
        + "~~~~~~~~~ JWT Body ~~~~~~~" + "\n\n"
        + "JWT Body: " + jwtBody + "\n\n"
        + "~~~~~~~~~ JWT Signature ~~~~~~~" + "\n\n"
        + "JWT Signature: " + signature;
  }

}