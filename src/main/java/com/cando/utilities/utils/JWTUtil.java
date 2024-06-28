/*
 *  JWTUtil.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.DateTimeException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);
  private static StringWriter sw = new StringWriter();
  private static PrintWriter pw = new PrintWriter(sw);

  public static boolean isJWTValid(String token) {

    try {
      DecodedJWT decodedJWT = JWT.decode(token);

      if (decodedJWT.getHeader().isEmpty()) {
        return false;
      }
      if (decodedJWT.getPayload().isEmpty()) {
        return false;
      }
      if (decodedJWT.getSignature().isEmpty()) {
        return false;
      }
      if (isJWTExpired(decodedJWT)) {
        return false;
      }

    } catch (JWTVerificationException | DateTimeException e) {
      e.printStackTrace(pw);
      LOGGER.info("Invalid Token: {}", e.getMessage());
      LOGGER.error(sw.getBuffer().toString());

      return false;
    }
    return true;
  }

  private static boolean isJWTExpired(DecodedJWT decodedJWT) {
    Date expiresAt = decodedJWT.getExpiresAt();
    return expiresAt.before(new Date());
  }
}