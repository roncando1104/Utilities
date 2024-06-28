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

  public String privatekey;
  public String headerStr;
  public String payloadStr;
  public Map<String, Object> payload;
  public Map<String, Object> header;
  public String signature;

  public String getPrivatekey() {
    return privatekey;
  }

  public void setPrivatekey(String privatekey) {
    this.privatekey = privatekey;
  }

  public String getHeaderStr() {
    return headerStr;
  }

  public void setHeaderStr(String headerStr) {
    this.headerStr = headerStr;
  }

  public String getPayloadStr() {
    return payloadStr;
  }

  public void setPayloadStr(String payloadStr) {
    this.payloadStr = payloadStr;
  }

  public Map<String, Object> getPayload() {
    return payload;
  }
  public void setPayload(Map<String, Object> payload) {
    this.payload = payload;
  }

  public Map<String, Object> getHeader() {
    return header;
  }

  public void setHeader(Map<String, Object> header) {
    this.header = header;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  @Override
  public String toString() {
    return "CertJsonBean{" +
        "privatekey='" + privatekey + '\'' +
        ", payload=" + payload +
        ", header=" + header +
        '}';
  }
}