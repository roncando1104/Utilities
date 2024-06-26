/*
 *  JsonPrettyPrinterResource.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.resources;

import com.cando.utilities.constants.Constants;
import com.cando.utilities.services.JsonPrettyPrinterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/json-formatter")
public class JsonPrettyPrinterResource {

  @Autowired
  private JsonPrettyPrinterService jsonPrettyPrinterService;


  @GetMapping(value="/", headers = {"content-type=*/*"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView formatJSON(@RequestParam("json") String json) throws JsonProcessingException {

    ModelAndView mav = new ModelAndView("/json_formatter");

    if (json.isEmpty()) {
      mav.addObject(Constants.IS_VALID, "JSON cannot be null");
      mav.addObject(Constants.ERROR_NOTE, Constants.CHECK_JSON_FORMAT);
      return mav.addObject(Constants.JSON, null);
    }

    if (jsonPrettyPrinterService.isValidJson(json).equalsIgnoreCase("valid")) {
      mav.addObject(Constants.IS_VALID, "JSON is valid");
      mav.addObject(Constants.JSON, json);
      return mav.addObject(Constants.CONTENT, jsonPrettyPrinterService.printJsonObject(json));
    } else {
      mav.addObject(Constants.IS_VALID, "JSON is invalid!!!");
      mav.addObject(Constants.ERROR_NOTE, Constants.CHECK_JSON_FORMAT);
      mav.addObject(Constants.CONTENT, jsonPrettyPrinterService.isValidJson(json));
      return mav.addObject(Constants.JSON, json);
    }
  }

  @GetMapping("/get-json")
  public String getJsonFormatter() {
    return "json_formatter";
  }
}