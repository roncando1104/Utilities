package com.cando.utilities.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EncryptPasswordServiceImplTest {

  private EncryptPasswordServiceImpl encryptPasswordService;

  @Test
  void testEncryptPassword_withSalt_passwordIsGeneratedSuccessfully() throws NoSuchAlgorithmException {
    encryptPasswordService = new EncryptPasswordServiceImpl();
    char[] hexArray = "0123456789ABCDEF".toCharArray();
    byte numOfBytes = 16;
    var encryptedPassword = encryptPasswordService.encryptPassword("pass", "SHA256", hexArray, numOfBytes, true);

    assertNotNull(encryptedPassword);
  }

  @Test
  void testEncryptPassword_withoutSalt_and_numOfBytesIsZero_passwordIsGeneratedSuccessfully() throws NoSuchAlgorithmException {
    encryptPasswordService = new EncryptPasswordServiceImpl();
    char[] hexArray = "0123456789ABCDEF".toCharArray();
    byte numOfBytes = 0;
    var encryptedPassword = encryptPasswordService.encryptPassword("pass", "SHA256", hexArray, numOfBytes, false);

    assertEquals("D74FF0EE8DA3B9806B18C877DBF29BBDE50B5BD8E4DAD7A3A725000FEB82E8F1", encryptedPassword);
  }

  @Test
  void testEncryptPassword_algorithmIsMD5_withoutSalt_and_numOfBytesIsZero_passwordIsGeneratedSuccessfully() throws NoSuchAlgorithmException {
    encryptPasswordService = new EncryptPasswordServiceImpl();
    char[] hexArray = "0123456789ABCDEF".toCharArray();
    byte numOfBytes = 0;
    var encryptedPassword = encryptPasswordService.encryptPassword("pass", "MD5", hexArray, numOfBytes, false);

    assertEquals("1a1dc91c907325c69271ddf0c944bc72", encryptedPassword);
  }

}