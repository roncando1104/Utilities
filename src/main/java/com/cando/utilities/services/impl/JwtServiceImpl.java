/*
 *  PrivateKeyServiceImpl.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.services.impl;

import com.cando.utilities.model.CertJsonBean;
import com.cando.utilities.services.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtServiceImpl.class);
  private StringWriter sw = new StringWriter();
  private PrintWriter pw = new PrintWriter(sw);

  @Override
  public String generateToken(CertJsonBean certJsonBean) {
    String token;
    String privateKey;
    Map<String, Object> header;
    Map<String, Object> payload;

    try {
      privateKey = certJsonBean.getPrivateKey();
      payload = certJsonBean.jwtPayload;
      header = certJsonBean.jwtHeader;

      JSONObject jsonObject = new JSONObject(header);
      String algorithm = jsonObject.getString("alg");

      // get the private key from encoded key.
      PrivateKey pvtKey = getPrivateKey(privateKey);

      if (!certJsonBean.privateKey.isEmpty()) {
        token = Jwts.builder().setClaims(payload).setHeader(header)
            .signWith(pvtKey, SignatureAlgorithm.forName(algorithm)).compact();
      } else {
        token = "Something went wrong!!";
      }
    } catch (Exception e) {
      e.printStackTrace(pw);
      LOGGER.info("Invalid Token: {}", e.getMessage());
      LOGGER.error(sw.getBuffer().toString());

      return "Invalid Token";
    }
    return token;
  }

  private PrivateKey getPrivateKey(String privateKey) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

    String pkcs8Pem = getPkcs8PemKey(privateKey);

    if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
      Security.addProvider(new BouncyCastleProvider());
    }

    KeyFactory kf = KeyFactory.getInstance("RSA");
    byte[] pkcs8EncodedBytes = Base64.getDecoder().decode(pkcs8Pem);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);

    return kf.generatePrivate(keySpec);
  }

  private String getPkcs8PemKey(String privateKey) throws IOException {
    StringBuilder pkcs8Lines = new StringBuilder();
    BufferedReader rdr = new BufferedReader(new StringReader(privateKey));
    String line;
    while ((line = rdr.readLine()) != null) {
      pkcs8Lines.append(line);
    }
    // Remove the "BEGIN" and "END" lines, as well as any whitespace
    String pkcs8Pem = pkcs8Lines.toString();
    pkcs8Pem = pkcs8Pem
        .replaceAll("(?s)-----.+?-----", "")
        .replace("\\n", "")
        .replaceAll("\\s+", "")
        .trim();
    return pkcs8Pem;
  }

}