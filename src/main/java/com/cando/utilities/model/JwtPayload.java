/*
 *  JwtPayload.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.model;

public class JwtPayload {

  public String headerStr;
  public String payloadStr;
  public String signature;

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

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }
}