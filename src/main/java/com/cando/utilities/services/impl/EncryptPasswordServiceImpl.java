/*
 *  EncryptPasswordService.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.services.impl;

import com.cando.utilities.services.EncryptPasswordService;
import com.cando.utilities.utils.Util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.springframework.stereotype.Service;

@Service
public class EncryptPasswordServiceImpl implements EncryptPasswordService {

  public String encryptPassword(String password, String algorithm, char[] hexArray, byte numOfBytes, boolean withSalt) throws NoSuchAlgorithmException {
    if (!withSalt && numOfBytes == 0) {
      return generateHash(password, algorithm, hexArray);
    } else {
      return generateHash(password, algorithm, createSalt(numOfBytes), hexArray);
    }
  }

  private String generateHash(String password, String algorithm, char[] hexArray) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance(algorithm);

    if (algorithm.equals("MD5")) {
      byte[] hash = getBytes(password, digest);
      BigInteger bigInteger = new BigInteger(1, hash);
      StringBuilder hashText = new StringBuilder(bigInteger.toString(16));

      while (hashText.length() < 32) {
        hashText.append("0")
            .append(hashText);
      }
      return hashText.toString();
    } else {
      digest.reset();
      byte[] hash = getBytes(password, digest);

      return Util.bytesToStringHex(hash, hexArray);
    }
  }

  private static byte[] getBytes(String password, MessageDigest digest) {
    return digest.digest(password.getBytes());
  }

  private static String generateHash(String password, String algorithm, byte[] salt, char[] hexArray) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance(algorithm);
    digest.reset();
    digest.update(salt);
    byte[] hash = getBytes(password, digest);

    return Util.bytesToStringHex(hash, hexArray);
  }

  private static byte[] createSalt(byte numOfBytes) {
    byte[] bytes = new byte[numOfBytes];
    SecureRandom random = new SecureRandom();

    random.nextBytes(bytes);
    return bytes;
  }

}