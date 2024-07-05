package com.cando.utilities.services;

import java.security.NoSuchAlgorithmException;

public interface GeneratePrivateAndPublicKeyService {

  String generatePrivateKey(String algorithm, int keySize) throws NoSuchAlgorithmException;

  String generatePuplicKey(String algorithm, int keySize) throws NoSuchAlgorithmException;
}
