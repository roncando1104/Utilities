/*
 *  EncryptPasswordResource.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.resources;

import com.cando.utilities.services.EncryptPasswordService;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EncryptPasswordResource {

  @Autowired
  private EncryptPasswordService encryptPasswordService;

  @GetMapping("/encrypt-password")
  public ModelAndView encryptPassword(@RequestParam("password") String password, @RequestParam("algorithm") String algorithm,
      @RequestParam("hex-value") char[] hex, @RequestParam("withSalt") boolean withSalt, @RequestParam("bytes") byte numOfBytes)
      throws NoSuchAlgorithmException {

    var encryptedPassword = encryptPasswordService.encryptPassword(password, algorithm, hex, numOfBytes, withSalt);
    ModelAndView mav = new ModelAndView("/encrypt_password");
    mav.addObject("result", encryptedPassword);
    return mav;
  }


  @GetMapping("/encryptor")
  public String encryptorPage() {
    return "encrypt_password";
  }
}