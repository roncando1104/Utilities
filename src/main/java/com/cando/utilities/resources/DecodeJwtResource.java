/*
 *  DecodeJwtResource.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.resources;

import com.cando.utilities.constants.Constants;
import com.cando.utilities.model.JwtPayload;
import com.cando.utilities.services.DecodeJwtService;
import com.cando.utilities.utils.JWTUtil;
import com.cando.utilities.utils.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DecodeJwtResource {

  private final Path root = Paths.get("./uploads");

  @Autowired
  private DecodeJwtService decodeJwtService;

  @GetMapping(value = "/decode-jwt/{jwtToken}", headers = {"content-type=*/*"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView getJwtContent(@PathVariable("jwtToken") String jwtToken) throws JsonProcessingException {
    ModelAndView mav = new ModelAndView("/jwt_file_content_viewer");
    String token = Util.readFile(root + "/" + jwtToken);

    var joseFile = Util.getExtensionByStringHandling(jwtToken);
    if (joseFile.isPresent()) {
      validateAndGetJWTValue(token, mav, "token", joseFile.get());
    }

    return mav;
  }

  @GetMapping(value = "/decode-jwt-string/", headers = {
      "content-type=*/*"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView getJwtStringContent(@RequestParam("jwtString") String jwtString, @RequestParam("tokenType") String tokenType)
      throws JsonProcessingException {
    ModelAndView mav = new ModelAndView("/jwt_string_content_viewer");
    validateAndGetJWTValue(jwtString, mav, "jwtString", tokenType);

    return mav;
  }

  @GetMapping("/get-jwt-file")
  public String getJWTFile() {
    return "decode_token";
  }

  @GetMapping("/get-jwt-string")
  public String getJWTString() {
    return "jwt_string_content_viewer";
  }

  private void validateAndGetJWTValue(String jwtToken, ModelAndView mav, String jwtStr, String tokenType) throws JsonProcessingException {

    if (tokenType.equalsIgnoreCase("JOSE")) {
      JwtPayload tokenList = decodeJwtService.decodeJwt(jwtToken);

      if (tokenList.getHeaderStr() == null || tokenList.getPayloadStr() == null || tokenList.getSignature() == null) {
        mav.addObject(jwtStr, jwtToken);
        mav.addObject(Constants.IS_VALID, String.format("%s is invalid", tokenType.toUpperCase()));
        mav.addObject(Constants.ERROR_NOTE, Constants.CHECK_JWT_VALID);
        mav.addObject(Constants.JWT_STRING, jwtToken);
      } else {
        mav.addObject(jwtStr, jwtToken);
        mav.addObject("header", tokenList.getHeaderStr());
        mav.addObject("payload", tokenList.getPayloadStr());
        mav.addObject("signature", tokenList.getSignature());
      }
    } else {
      if (jwtToken.isEmpty()) {
        mav.addObject(jwtStr, jwtToken);
        mav.addObject(Constants.IS_VALID, String.format("%s cannot be null", tokenType.toUpperCase()));
        mav.addObject(Constants.ERROR_NOTE, Constants.CHECK_JWT_VALID);
        mav.addObject(Constants.JWT_STRING, jwtToken);
      } else if (!JWTUtil.isJWTValid(jwtToken)) {
        mav.addObject(jwtStr, jwtToken);
        mav.addObject(Constants.IS_VALID, String.format("%s is invalid", tokenType.toUpperCase()));
        mav.addObject(Constants.ERROR_NOTE, Constants.CHECK_JWT_VALID);
        mav.addObject(Constants.JWT_STRING, jwtToken);
      } else {
        JwtPayload tokenList = decodeJwtService.decodeJwt(jwtToken);

        mav.addObject(jwtStr, jwtToken);
        mav.addObject("header", tokenList.getHeaderStr());
        mav.addObject("payload", tokenList.getPayloadStr());
        mav.addObject("signature", tokenList.getSignature());
      }
    }

  }
}