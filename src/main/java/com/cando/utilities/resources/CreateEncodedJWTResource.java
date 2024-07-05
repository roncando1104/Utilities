/*
 *  CreateEncodedJWTResource.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.resources;

import com.cando.utilities.model.CertJsonBean;
import com.cando.utilities.services.JwtService;
import com.cando.utilities.utils.JsonReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateEncodedJWTResource {

  @Autowired
  private JwtService jwtService;

  @Autowired
  private JsonReader jsonReader;

  @GetMapping("/create-encoded-jwt")
  public String encodeJWT() {
    return "encode_token";
  }

  @GetMapping("/get-encoded-jwt")
  public ModelAndView getEncodedJWT(@RequestParam("jwtHeader") String jwtHeader, @RequestParam("jwtPayload") String jwtPayload, @RequestParam("privateKey") String privateKey)
      throws JsonProcessingException {
    ModelAndView mav = new ModelAndView("/encode_token");

    String certJsonStr = new JSONObject()
        .put("jwtHeader", new JSONObject(jwtHeader))
        .put("jwtPayload", new JSONObject(jwtPayload))
        .put("privateKey", privateKey)
        .toString();

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    Map<String, Object> map = objectMapper.readValue(certJsonStr, HashMap.class);
    String json = objectMapper.writeValueAsString(map);

    CertJsonBean certJsonBean = jsonReader.readJsonFile(json);
    String token = jwtService.generateToken(certJsonBean);

    mav.addObject("encodedJwt", token);
    mav.addObject("jwtHeader", jwtHeader);
    mav.addObject("jwtPayload", jwtPayload);
    mav.addObject("privateKey", privateKey);

    return mav;
  }
}