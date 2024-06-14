/*
 *  GenerateUUIDService.java
 *
 *  Copyright © 2024 ING Group. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  ING Group ("Confidential Information").
 */
package com.cando.utilities.services.impl;

import com.cando.utilities.services.GenerateUUIDService;
import java.util.UUID;

public class GenerateUUIDServiceImpl implements GenerateUUIDService {

  public String generateUUID() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }

}