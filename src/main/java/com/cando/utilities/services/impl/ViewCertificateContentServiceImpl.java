/*
 *  ViewCertificateContentService.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.services.impl;

import com.cando.utilities.services.ViewCertificateContentService;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ViewCertificateContentServiceImpl implements ViewCertificateContentService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ViewCertificateContentServiceImpl.class);
  private StringWriter sw = new StringWriter();
  private PrintWriter pw = new PrintWriter(sw);

  public String getCertificateList(String keystoreFile, String password) {

    String alias = "";
    Certificate certificate = null;
    try (FileInputStream fis = new FileInputStream("./uploads/" + keystoreFile)) {
      KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
      keystore.load(fis, password.toCharArray());

      Enumeration<String> enumeration = keystore.aliases();

      while (enumeration.hasMoreElements()) {
        alias = enumeration.nextElement();
        certificate = keystore.getCertificate(alias);
      }
    } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | IOException e) {
      e.printStackTrace(pw);

      LOGGER.info("There is an error while loading the certificate from the keystore: {}", e.getMessage());
      LOGGER.error("Stacktrace: {}", sw.getBuffer());

      return String.format("There is an error while loading the certificate from the keystore: %s %n Stacktrace: %s", e.getMessage(),
          sw.getBuffer());
    }
//    finally {
//      if (null != fis) {
//        try {
//          fis.close();
//        } catch (IOException e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//        }
//      }
//    }\
    LOGGER.info("Certificate has been loaded successfully");

    return String.format("Alias: %s %n Certificate: %s", alias, certificate);
  }

  public String getPfxCertificate(String keystoreFile, String password) {
    String alias = "";
    X509Certificate certificate = null;
    try (FileInputStream stream = new FileInputStream("./uploads/" + keystoreFile)) {
      KeyStore store = KeyStore.getInstance("PKCS12", "SunJSSE");
      store.load(stream, password.toCharArray());

      Enumeration<String> aliases = store.aliases();

      while (aliases.hasMoreElements()) {
        alias = aliases.nextElement();
        certificate = (X509Certificate) store.getCertificate(alias);
      }
    } catch (CertificateException | KeyStoreException | NoSuchAlgorithmException | IOException | NoSuchProviderException e) {
      e.printStackTrace(pw);

      LOGGER.info("There is an error while loading the PFX certificate from the keystore: {}", e.getMessage());
      LOGGER.error("Stacktrace of PFX certificate: {}", sw.getBuffer());

      return String.format("There is an error while loading the PFX certificate from the keystore: %s %n Stacktrace: %s", e.getMessage(),
          sw.getBuffer());
    }

    if (certificate == null) {
      return "PFX Certificate is empty";
    }
    LOGGER.info("PFX Certificate has been loaded successfully");

    return String.format("Alias: %s %n Expiry: %s %n Validity: %s %n PFX Certificate: %s", alias, certificate.getNotBefore(), certificate.getNotAfter(),
        certificate);
  }
}