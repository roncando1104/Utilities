/*
 *  DecodeJwtResource.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.resources;

import com.cando.utilities.services.impl.DecodeJwtServiceImpl;
import com.cando.utilities.utils.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DecodeJwtResource {

  private final Path root = Paths.get("./uploads");

  @Autowired
  private DecodeJwtServiceImpl decodeJwtService;


  @GetMapping(value = "/decode-jwt/{jwtToken}", headers = {"content-type=*/*"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView getJwtContent(@PathVariable("jwtToken") String jwtToken) throws JsonProcessingException {
    ModelAndView mav = new ModelAndView("/content_viewer");
    String token = Util.readFile(root + "/" + jwtToken);
    mav.addObject("content", decodeJwtService.decodeJwt(token));

    return mav;
  }

  @GetMapping("/get-token")
  public String newFile() {
    return "decode_token";
  }

}