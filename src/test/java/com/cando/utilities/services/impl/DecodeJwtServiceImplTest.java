package com.cando.utilities.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DecodeJwtServiceImplTest {

  private DecodeJwtServiceImpl decodeJwtService;

  private JsonPrettyPrinterServiceImpl jsonPrettyPrinterService;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    jsonPrettyPrinterService = new JsonPrettyPrinterServiceImpl(objectMapper);
    decodeJwtService = new DecodeJwtServiceImpl(jsonPrettyPrinterService);
  }

  @Test
  void testDecodeJwt_tokenIsCreatedSuccessfully() throws JsonProcessingException {

    String jwtStr = getJwtStr("eyJhbGciOiJSUzI1NiIsInR5cCI6Imp3dCJ9.eyJleHAiOjE4MDU3MDgxODIsImlhdCI6MTcxMTAzNzQwNCwiaXNzIjoiVGVzdEFQSSIsImp0aSI6ImRmYWEzZWQwLTA2YTQtNGNiNC1iNzNjLTA1MTkxNzUwMTYzZSIsIm5iZiI6MTcxMTAzNzQwNCwic3ViIjoiVGVzdFV0aWxpdGllc0FQSSIsInRlc3QiOiJ0ZXN0LWp3dCIsInR5cCI6InBlZXIiLCJ2ZXIiOiIxLjAifQ.O7JaBvv0nBtRD2c9y2f4RGLkRjRD8i6ilSunby9QJK-LMwULhwvqPBR7P_JvVjKo53Y5z0nWBR44xRKtyTicdPo0wIcSQOwXSl7VDPFOQvp-JpQdaE7RJQn4XpyO_EgoblIHjA0ybbt6axTMG42de2aSYP5VV3CeieVfYCuLquOCiaMy-mNnb5DcpRR_tAMXdgrfwzP6aYr2KbrY5z7jJvcsUvh3_OIK5fTuag6i_54Brgl_10fa9oEDZ8J7bkW60dV6_IGxiHEe4wpYxE3k8tX5U1poVN5qzHB-CCl6J94w-D1HHps4P5MHkvKeiciyf5gwzDsIQC_XeVpGjMCeKA");
    var decodedJwt = decodeJwtService.decodeJwt(jwtStr);

    assertNotNull(decodedJwt);
    assertEquals("""
        {
          "alg" : "RS256",
          "typ" : "jwt"
        }""", decodedJwt.headerStr);
    assertEquals("""
        {
          "exp" : 1805708182,
          "iat" : 1711037404,
          "iss" : "TestAPI",
          "jti" : "dfaa3ed0-06a4-4cb4-b73c-05191750163e",
          "nbf" : 1711037404,
          "sub" : "TestUtilitiesAPI",
          "test" : "test-jwt",
          "typ" : "peer",
          "ver" : "1.0"
        }""", decodedJwt.payloadStr);
    assertNotNull(decodedJwt.signature);
  }

  @Test
  void testDecodeJwt_throwsException() throws JsonProcessingException {
    String jwtStr = getJwtStr("eyJhbGciOiJSUzI1NiIsInR5cCI6Imp3dCJ9");

    assertThat(decodeJwtService.decodeJwt(jwtStr)).isNull();
  }

  private String getJwtStr(String jwtStr) throws JsonProcessingException {
    String[] splitString = jwtStr.split("\\.");
    String base64EncodedHeader = splitString[0];
    String header = new String(getDecodeJwt(base64EncodedHeader));
    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString("{}");
    jsonPrettyPrinterService.printJsonObject(header);
    return jwtStr;
  }

  private byte[] getDecodeJwt(String base64EncodedString) {
    Base64 base64Url = new Base64(true);
    return base64Url.decode(base64EncodedString);
  }
}