/*
 *  GeneratePrivateAndPublicRsaKeyServiceImpl.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.services.impl;

import com.cando.utilities.services.GeneratePrivateAndPublicKeyService;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class GeneratePrivateAndPublicKeyServiceImpl implements GeneratePrivateAndPublicKeyService {

  private KeyPair getPrivateAndPublicRsaKey(String algorithm, int keySize) throws NoSuchAlgorithmException {

    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
    //1024, 2048, etc.
    keyPairGenerator.initialize(keySize);

    return keyPairGenerator.generateKeyPair();
  }

  @Override
  public String generatePrivateKey(String algorithm, int keySize) throws NoSuchAlgorithmException {
    KeyPair keyPair = getPrivateAndPublicRsaKey(algorithm, keySize);

    return Base64.getMimeEncoder().encodeToString(keyPair.getPrivate().getEncoded());
  }

  @Override
  public String generatePuplicKey(String algorithm, int keySize) throws NoSuchAlgorithmException {
    KeyPair keyPair = getPrivateAndPublicRsaKey(algorithm, keySize);

    return Base64.getMimeEncoder().encodeToString(keyPair.getPublic().getEncoded());
  }
}