/*
 *  GetResult.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.resources;

import com.cando.utilities.services.impl.FileStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/get-certificate-content")
public class ViewCertificateContentResource {

  @Autowired
  private FileStorageServiceImpl fileStorageService;

  @GetMapping("/view-keystore/{keystore}")
  public ModelAndView getFile(@PathVariable("keystore") String keystore, @RequestParam("password") String password) {

    ModelAndView mav = new ModelAndView("/content_viewer");
    mav.addObject("content", fileStorageService.loadKeystore(keystore, password));
    return mav;
  }
}