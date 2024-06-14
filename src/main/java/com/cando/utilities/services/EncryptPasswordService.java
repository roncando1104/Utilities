package com.cando.utilities.services;

import java.security.NoSuchAlgorithmException;

public interface EncryptPasswordService {

   String encryptPassword(String password, String algorithm, char[] hexArray, byte numOfBytes, boolean withSalt)  throws NoSuchAlgorithmException;
}
