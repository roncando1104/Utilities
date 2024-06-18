/*
 *  GenerateUUIDResource.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.resources;

import com.cando.utilities.services.GenerateUUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/generate-uuid")
public class GenerateUUIDResource {

  @Autowired
  private GenerateUUIDService generateUUIDService;

  @GetMapping(value = "/random", headers = {"content-type=*/*"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView getGenerateUUID() {
    String uuid = generateUUIDService.generateRandomUUID();
    ModelAndView modelAndView = new ModelAndView("/generate_uuid");
    modelAndView.addObject("uuid", uuid);

    return modelAndView;
  }

  @GetMapping("/home")
  public String home() {
    return "generate_uuid";
  }
}