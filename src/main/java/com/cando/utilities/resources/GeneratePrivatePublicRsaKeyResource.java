/*
 *  GeneratePrivatePublicRsaKeyResource.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.resources;

import com.cando.utilities.services.GeneratePrivateAndPublicKeyService;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeneratePrivatePublicRsaKeyResource {

  @Autowired
  private GeneratePrivateAndPublicKeyService generatePrivateAndPublicKeyService;

  @GetMapping("/generate-key-home")
  public String generateKeyHome() {
    return "generate_public_private_key";
  }

  @GetMapping("/generate-public-private-key")
  public ModelAndView getGeneratePublicAndPrivateKey(@RequestParam("algorithmType") String algorithmType, @RequestParam("keySize") int keySize)
      throws NoSuchAlgorithmException {

    ModelAndView mav = new ModelAndView("/generate_public_private_key");
    var privateKey = String.format("%s%s%s",
        "-----BEGIN PRIVATE KEY-----", generatePrivateAndPublicKeyService.generatePrivateKey(algorithmType, keySize), "-----END PRIVATE KEY-----");
    var publicKey = String.format("%s%s%s",
        "-----BEGIN PUBLIC KEY-----", generatePrivateAndPublicKeyService.generatePuplicKey(algorithmType, keySize), "-----END PUBLIC KEY-----");

    mav.addObject("privateKey", privateKey);
    mav.addObject("publicKey", publicKey);

    return mav;
  }
}