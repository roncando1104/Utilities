/*
 *  Util.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class Util {

  public static String bytesToStringHex(byte[] bytes, char[] hexArray) {
    char [] hexChars = new char[bytes.length * 2];
    for (int j = 0; j < bytes.length;j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2] = hexArray[v >>>4];
      hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }
    return new String(hexChars);
  }

  public static char[] getHexToCharArrayValue(String hexValue) {
    return hexValue.toCharArray();
  }

  public static Optional<String> getExtensionByStringHandling(String filename) {
    return Optional.ofNullable(filename)
        .filter(f -> f.contains("."))
        .map(f -> f.substring(filename.lastIndexOf(".") + 1));
  }

  public static String readFile(String filename) {
    String data = "";
    try {
      File myObj = new File(filename);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        data = myReader.nextLine();
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return data;
  }
}