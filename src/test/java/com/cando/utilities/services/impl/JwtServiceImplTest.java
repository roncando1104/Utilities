package com.cando.utilities.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.cando.utilities.model.CertJsonBean;
import com.cando.utilities.services.JwtService;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JwtServiceImplTest {

  private JwtService jwtService = new JwtServiceImpl();
  ;

  private CertJsonBean certJsonBean;

  @Test
  void testJwtService_tokenIsGenerated() {
    certJsonBean = new CertJsonBean();
    Map<String, Object> mapHeader = new HashMap<>();
    mapHeader.put("alg", "RS256");
    mapHeader.put("typ", "JWT");
    Map<String, Object> mapPayload = new HashMap<>();
    mapPayload.put("iat", "test");
    mapPayload.put("typ", "peer");
    certJsonBean.setJwtHeader(mapHeader);
    certJsonBean.setJwtPayload(mapPayload);
    certJsonBean.setPrivateKey(
        "-----BEGIN RSA PRIVATE KEY-----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-----END RSA PRIVATE KEY-----");

    var generatedToken = jwtService.generateToken(certJsonBean);

    String[] splitString = generatedToken.split("\\.");
    assertNotNull(generatedToken);
    assertEquals(3, splitString.length);
  }

  @Test
  void testJwtService_invalidSignatureAlgorithm_willReturnInvalidToken() {
    certJsonBean = new CertJsonBean();
    Map<String, Object> mapHeader = new HashMap<>();
    mapHeader.put("alg", "RSA");
    mapHeader.put("typ", "JWT");
    Map<String, Object> mapPayload = new HashMap<>();
    mapPayload.put("iat", "test");
    mapPayload.put("typ", "peer");
    certJsonBean.setJwtHeader(mapHeader);
    certJsonBean.setJwtPayload(mapPayload);
    certJsonBean.setPrivateKey(
        "-----BEGIN RSA PRIVATE KEY-----MIIJKAIBAAKCAgEApef+R+Us6CHECK5GzAEspU1gf3AoFa4rkQnkF4uS+R6289eGUfHRSqNcev1VtPNs5ov6JrduIb0JWIDukL4db0POBLCioxynuTDly0+XNl6b3gk3Qw1TzQ1RHZJhB1yHUqM7mm6P2hoXvlQXmiGjGUpEyNlMQBFtjnvWjJRaJXRhQfbXSgT9EmwnB4++hCRDQD3D7ChT+4/9lwHpIDBmqEc3T9qeibju2x7C5HmI5BSahR+O9HvnmvLr2tLgVIyFSW7RK2ErznRdukKB8v0BPW81MdXKslx91iF54SVm9uIe8mqksR6A7EeeBrkQ0dT5jIQyZkJGH3EQH+yfL69iNU1pqNnCDebW+kr+3S2V8tjjau4Ie3bOcwefj/hRTHwnppFocxhkpKulb42jgH8MFEVoumVJqbURME82F1adRIg1ZYfJOIWkAw05luYomW7M5OmvPt0X4fad6da7h5VOEQZ4HH5ksPtwyczTjU25fac9mTgRZ6ihZ0lPmzNSLlZYJInWFkH8Sbl9Gll5xGP3CqXfgqVgWWZ8/P2hwoZc/FsHn4ytw3fzCb5ICRzxeYFrbgIF2y0L0CN/hYBkvD9EfAhBpOsg9GkszpDrTztxd3lkKPMHDGQolvEtjZfUj/S67afNvDGO9mz7iHSZ1z+uZHgN+B77jSUUpk31ZgWgCckCAwEAAQKCAgEAgC6VdWDo6FUmS/qysm3526HD+9Jg9hdCi+YTd+mb3aRQgGe13Nwz8zHygwf2zz8AlcPyuzUf/ys57Ep5iBWLUmsV4uPCVLoQEHJqKUJON3CzhsKiEslaROSUFoABPZnM6Bk8NN5VgJKGY8Q/FBQHGsQaDwBs/Fc6yUp/AtCgMa4QctHPlt3x1ej/4sS50wjdeOX/2Rdzg7rFmIWeZANhXYTghG8J7KiYy22h8LrB/E668D3oypZs5HfpqIEjV8/WL11SeOyz1uhrfjxigKXf7mJcvCtNV69EfkwRCdZHDlq4xF2G7139CBFxVApqp2SghmOhOQbdfO9zIOdGOqaL/hnGW3gku23b7ilYgBKW4ZYoP2uvkxmHoBMjwPbJHVYnKsSWpIAJFDkX4WeMwI1spAALGCAGXdcBqJMVAQj79+6NDn5scYXMBOIknba6+NLAGRlaNf/lfkq4ec2ELUVMfq4op1TTY4207xHncLhpmlNeFA1AJN+5Km5RnQLglooMnhNOIsIdYzTnsvYAAoalH5+uYGcM70DRTUAJOJ3IUbD6T/pQC27E6eM29OjCCuVS3hz/E4e5pqyFEHpiAE/9xQioTtPjSKBx3TVwSgIKtOkxEOvi0W7/uoZQeHFudWiw+/F5/YWkCqwWLuTu0PpQ04EHKIi4Ur8jqLkYbiVhILECggEBANtHkG/duft30cbyuI7flQ++WlRu5Dm+TcIadJtlNR7TbWDXUFjBNuFUF6JVz0ejFmVwP2F6Fz1kH9tvaKZaS8pAcr53CrpHrRrfmt4eoTu+6rezIH4i07hDfXR083zMybLhw70EZeAX5K0Zo9U2g0e9aDz0yqbMIQjW4Il8YmT1pAi+Yy0ZIbR3SMJzwZK9OT0ysmpMrIw5QCg7OkZHNN72MN4mMWLAec1M5l3iHVerLMu+Zi257YpsFGcm3GBXt+ZXCM1Lwb/w5D0VMrmGn4dCMZh3wUnntLdpJZ4AUA4bZc/25nbZYu0J7vc25qYC7XNbxwSGVgKm9PuV3oP8As0CggEBAMGwVXHSy+aFedtnMhu+CPwXw+OJL5Gpu4AjPczglWtUvAO9fqtoQSwM/JLxT/PF1DyrB657VALrjQiHnFyAlkXWGk9moogI96PjXcISzJg59/FyMy4V4cbcWsUBWw3K/0nNekXZcs3uNXYWL+ilv35S6NfcnxxtJk1Fy6IbW3Au6DBXJPOwtUBtwutnoCUoqEQOUvN+ySGCDMd0SnF5talWbxu8C8WGzwRI6IkPMJsIQdHE35WkjjDOfvgspVmdBBYJ5c2/nf80jkzpmBg6PCB5Y/E5QJZh5oH4D/msQQmkhHqLhhjw+upUFNhwDMprMQlsyHbZHtlj9gGFKdVrOu0CggEAE3/1tDz/gQFSwmJrTylK1C+VUNTbrtbhi1VW8Uq6wpcCrw32sy3bd4ZG48TFvhUetL6apEx7LJZuztDFeaWl/8JjcdScO5yMJEumNgJ7TBjaPqPLS+xE76nwyh5sWLjNZv70rSZq+NanG02gaCGE4yhQ0LrkGpkkhui05wUMqvW9NJnZr/UALlA9RC2PFUGGiY6slw9ieMXOksVRlVZXuUwjZF35HlMHgd5IziiBe35ZaXTxGTfji9+4oMOv7VFfpTgwLDGEdVEyusvbyuvR2/Fi/7AGnE/injzXf4l/lQGSuD+4lvEV/wZXHdzf/ftcqlmLBTf9BhdaydS2CP6sMQKCAQBoKdm5zgDEJ3JCUTw8eTXBxVIe0w9XCe4BWnKI9RrGTxqR9F3j9rxd4VoP+GDkG7cpLV2DM2YZ7+238Z5gjPLD+gHKQ8sS16e+Zq9siMmo8DvsiFsH5NK4LJdQLYt3Xn1uhmfEms3H+9yadhIrzr+uQw5f0JFtKzIXCnsZ4haJvWA3ZxZDS/wd+ymBiggxE61ydDWeJ8gpEhSnS14hqq/MsuQsCTRXdUlt53vPAfFE3uru0i7f/lBN5S+ZbgE6sVuaMpZ40R06O1xv9oxKMz9Uw5PBDeYzOdFKgkqtHjsX4XcxpElormhyjudrAs+OKJmFlRA2Akw54y1Q4fl+gh19AoIBADm16NSjwW0ZVLTFHQP0OLRy3h0+aPtrhWahaVQ5fsNGJZRu2dgGZxTSyiCmt3KMERIl2puM1kCol4IdOfty6V6Km9PkpN7h6gqlL2CtkjOghravvcXX7/Z+QPi1I94QIrq+SFkwLz0CT8VVrMOCF/KwRxh+c2z22VdVaxKj07MDS6ScDtspO/IwXO4E7pui4a/6B58rRUoAKL//Fi93hFesjTr0jOgp+zMuofRDmFKbJj089j9knxYPWi2QPWWpYJ4R6djeZMgbohhIW/FrA5PZ08jJVswhIVkgnccnkIH9/PC/WvOKkhW/VvlkjZZuQz1wgIh5wxooE3nEXNVI+DA=-----END RSA PRIVATE KEY-----");

    assertThat(jwtService.generateToken(certJsonBean)).contains("Invalid Token");
  }

//  @Test
//  void testJwtService_privateKeyIsEmpty_willReturnErrorMessage() {
//    certJsonBean = new CertJsonBean();
//    Map<String, Object> mapHeader = new HashMap<>();
//    mapHeader.put("alg", "RSA");
//    mapHeader.put("typ", "JWT");
//    Map<String, Object> mapPayload = new HashMap<>();
//    mapPayload.put("iat", "test");
//    mapPayload.put("typ", "peer");
//    certJsonBean.setJwtHeader(mapHeader);
//    certJsonBean.setJwtPayload(mapPayload);
//    certJsonBean.setPrivateKey("");
//
//    var generatedToken = jwtService.generateToken(certJsonBean);
//
//    //assertThat(jwtService.generateToken(certJsonBean)).contains("Something went wrong!!");
//  }
}