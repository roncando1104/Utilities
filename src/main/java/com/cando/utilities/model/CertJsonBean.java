/*
 *  CertJsonBean.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.model;

import java.util.Map;

public class CertJsonBean {

  public String privateKey;
  public Map<String, Object> jwtPayload;
  public Map<String, Object> jwtHeader;

  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }

  public Map<String, Object> getJwtPayload() {
    return jwtPayload;
  }
  public void setJwtPayload(Map<String, Object> jwtPayload) {
    this.jwtPayload = jwtPayload;
  }

  public Map<String, Object> getJwtHeader() {
    return jwtHeader;
  }

  public void setJwtHeader(Map<String, Object> jwtHeader) {
    this.jwtHeader = jwtHeader;
  }

  @Override
  public String toString() {
    return "CertJsonBean{" +
        "privatekey='" + privateKey + '\'' +
        ", payload=" + jwtPayload +
        ", header=" + jwtHeader +
        '}';
  }
}