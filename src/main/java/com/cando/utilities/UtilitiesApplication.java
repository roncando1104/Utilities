package com.cando.utilities;

import com.cando.utilities.services.FileStorageService;
import jakarta.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UtilitiesApplication implements CommandLineRunner {

  @Resource
  private FileStorageService fileStorageService;

  //private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

  public static void main(String[] args) throws NoSuchAlgorithmException {

    /**
     * @description Call for viewing keystores
     * change the path to keystore
     * sample password : "Sk2qH-f5ruq+s=v0LqXNpGabm"
     * @parameters keystore location and password of the keystore
     */
    //String keystoreLocation = "/Users/JD81ME/workspace/OMaaP/P03423-OMaaP-PaymentRequestManagerAPI/PaymentRequestManagerAPIWeb/config/local-dev/certs/prm_e2e.p12";
    //ViewCertificateContentService.getCertificateList(keystoreLocation, "5Pn_BiFUY4COH8jdw*Ln?fOwi");
    //ViewCertificateContentService.getPfxCertificate(keystoreLocation, "0k98HpCDKrEUyRLOSW54XFBopEijvw");

    /**
     * @description Call for JWT Decoder and Manifest
     * change the jwt token
     */
    //String jwtToken = "eyJhbGciOiJSUzI1NiIsIng1dCI6IkZCOkM2OkJFOkY0OkU5OkYyOjhEOjM2OjFFOjI2OkNGOkQ3OkQ4OjA5OkMyOjAyOkM5OkQ2OkFEOkM5Iiwia2lkIjoiRkJDNkJFRjRFOUYyOEQzNjFFMjZDRkQ3RDgwOUMyMDJDOUQ2QURDOSJ9.eyJzdWIiOiJQYXltZW50UmVxdWVzdE1hbmFnZXJBUEkiLCJzcGlmZmUiOiJzcGlmZmU6Ly9ub25wcm9kLmluZy5jb20vdjEvUDAzNDIzL0RFVi9QYXltZW50UmVxdWVzdE1hbmFnZXJBUEkiLCJlbmRwb2ludHMiOlt7Im1ldGhvZCI6IlBPU1QiLCJob3N0IjoiYXBpcy5pbmcuY29tIiwicGF0aFRlbXBsYXRlIjoiL3Y1L2FncmVlbWVudHMvcHJvZHVjdHMvbG9va3VwIiwidmVyc2lvbiI6IjExLjAuOSJ9LHsibWV0aG9kIjoiUE9TVCIsImhvc3QiOiJhcGkuZ2lvbS5vcmRlcnMuaW5nLm5ldCIsInBhdGhUZW1wbGF0ZSI6Ii9wYXltZW50cy9jcmVkaXQtdHJhbnNmZXJzIiwidmVyc2lvbiI6IjEuMC4yOSJ9LHsibWV0aG9kIjoiUE9TVCIsImhvc3QiOiJhcGlzLmluZy5jb20iLCJwYXRoVGVtcGxhdGUiOiIvdjYvYWdyZWVtZW50cy9wcm9kdWN0cy9sb29rdXAiLCJ2ZXJzaW9uIjoiMTQuMC41In0seyJtZXRob2QiOiJHRVQiLCJob3N0IjoiYXBpLXJlZ2lzdHJ5LmluZy5uZXQiLCJwYXRoVGVtcGxhdGUiOiIvYXBwbGljYXRpb25zL3thcHBJZH0iLCJ2ZXJzaW9uIjoiMTYuMC4wIn0seyJtZXRob2QiOiJQT1NUIiwiaG9zdCI6ImFwaS5pbmcuY29tIiwicGF0aFRlbXBsYXRlIjoiL2Fub255bW91cy1hcHByb3ZhbC1yZXF1ZXN0cyIsInZlcnNpb24iOiI0LjEuMCJ9LHsibWV0aG9kIjoiUE9TVCIsImhvc3QiOiJhcGkuaW5nLmNvbSIsInBhdGhUZW1wbGF0ZSI6Ii9wYXltZW50cy9vcmRlcnMvY3JlZGl0LXRyYW5zZmVycyIsInZlcnNpb24iOiIzLjAuMCJ9LHsibWV0aG9kIjoiUE9TVCIsImhvc3QiOiJvbWFhcC5pbmcuY29tIiwicGF0aFRlbXBsYXRlIjoiL3YxL29tYWFwL3BheW1lbnRzL2xpc3Qvb3JkZXJzIiwidmVyc2lvbiI6IjUuMC4wIn0seyJtZXRob2QiOiJHRVQiLCJob3N0IjoiYXBpLmluZy5jb20iLCJwYXRoVGVtcGxhdGUiOiIvc2VjdXJpdHktbWVhbnMvYXBwLWNlcnRpZmljYXRlL2NlcnRpZmljYXRlL3tjbGllbnRJZH0iLCJ2ZXJzaW9uIjoiNC40LjAifSx7Im1ldGhvZCI6IkRFTEVURSIsImhvc3QiOiJwYXltZW50LW9yZGVyLW9wZXJhdGlvbi5hcGkub3JkZXJzLmluZy5uZXQiLCJwYXRoVGVtcGxhdGUiOiIvcGF5bWVudHMve3BheW1lbnRJZH0iLCJ2ZXJzaW9uIjoiMy4wLjAifSx7Im1ldGhvZCI6IkdFVCIsImhvc3QiOiJwYXltZW50LW9yZGVyLW9wZXJhdGlvbi5hcGkub3JkZXJzLmluZy5uZXQiLCJwYXRoVGVtcGxhdGUiOiIvcGF5bWVudHMve3BheW1lbnRJZH0vc3RhdHVzIiwidmVyc2lvbiI6IjMuMC4wIn0seyJtZXRob2QiOiJQT1NUIiwiaG9zdCI6ImFwaS5vbWFhcC5pbmcuY29tIiwicGF0aFRlbXBsYXRlIjoiL3YxL29tYWFwL3BheW1lbnRzL2luaXRpYXRlLWNyZWRpdC10cmFuc2ZlcnMvaW50ZXJuYWwiLCJ2ZXJzaW9uIjoiMy4wLjAifSx7Im1ldGhvZCI6IkdFVCIsImhvc3QiOiJhcGkuaW5nLmNvbSIsInBhdGhUZW1wbGF0ZSI6Ii92NC9wZXJtaXNzaW9ucy9hZ3JlZW1lbnRzL3Byb2R1Y3RzL3tpZH0iLCJ2ZXJzaW9uIjoiMS4yLjIifSx7Im1ldGhvZCI6IlBPU1QiLCJob3N0IjoiYXBpcy5pbmcuY29tIiwicGF0aFRlbXBsYXRlIjoiL3Y0L2FncmVlbWVudHMvcHJvZHVjdHMvbG9va3VwIiwidmVyc2lvbiI6IjYuMC40In0seyJtZXRob2QiOiJHRVQiLCJob3N0IjoicGVyaW9kaWMtcGF5bWVudC1hZ3JlZW1lbnQtYXBpLW9yZGVycy5pbmcubmV0IiwicGF0aFRlbXBsYXRlIjoiL3BlcmlvZGljLXBheW1lbnRzL3twYXltZW50SWR9L3N0YXR1cyIsInZlcnNpb24iOiIyLjAuMCJ9LHsibWV0aG9kIjoiREVMRVRFIiwiaG9zdCI6InBlcmlvZGljLXBheW1lbnQtYWdyZWVtZW50LWFwaS1vcmRlcnMuaW5nLm5ldCIsInBhdGhUZW1wbGF0ZSI6Ii9wZXJpb2RpYy1wYXltZW50cy97cGF5bWVudElkfSIsInZlcnNpb24iOiIyLjAuMCJ9LHsibWV0aG9kIjoiR0VUIiwiaG9zdCI6ImFwaS5vcmRlcnMuaW5nLm5ldCIsInBhdGhUZW1wbGF0ZSI6Ii9wYXltZW50cy97cGF5bWVudElkfS9zdGF0dXMiLCJ2ZXJzaW9uIjoiMS4wLjAifSx7Im1ldGhvZCI6IkRFTEVURSIsImhvc3QiOiJhcGkuZ2lvbS5vcmRlcnMuaW5nLm5ldCIsInBhdGhUZW1wbGF0ZSI6Ii9wYXltZW50cy97cGF5bWVudElkfSIsInZlcnNpb24iOiIyLjAuMyJ9LHsibWV0aG9kIjoiUE9TVCIsImhvc3QiOiJhcGkuaW5nLmNvbSIsInBhdGhUZW1wbGF0ZSI6Ii9wYXltZW50cy9vcmRlcnMvY3JlZGl0LXRyYW5zZmVycy9wcmUtYXV0aG9yaXplZCIsInZlcnNpb24iOiIzLjEuMCJ9LHsibWV0aG9kIjoiUE9TVCIsImhvc3QiOiJhcGkuaW5nLmNvbSIsInBhdGhUZW1wbGF0ZSI6Ii92Mi9mdW5kcy1jb25maXJtYXRpb25zIiwidmVyc2lvbiI6IjIuMC42In0seyJtZXRob2QiOiJQT1NUIiwiaG9zdCI6ImFwaS5pbmcuY29tIiwicGF0aFRlbXBsYXRlIjoiL3YxL3BheW1lbnQvY3JlZGl0LXRyYW5zZmVyL3Byb2R1Y3QtdHlwZSIsInZlcnNpb24iOiIyLjAuMSJ9LHsibWV0aG9kIjoiR0VUIiwiaG9zdCI6ImFwaS5naW9tLm9yZGVycy5pbmcubmV0IiwicGF0aFRlbXBsYXRlIjoiL3BheW1lbnRzL3twYXltZW50SWR9L3N0YXR1cyIsInZlcnNpb24iOiIxLjAuMjkifV0sInZlciI6IjEuMCIsImp0aSI6ImRmYWEzZWQwLTA2YTQtNGNiNC1iNzNjLTA1MTkxNzUwMTYzZSIsInR5cCI6InBlZXIiLCJleHAiOjE4MDU3MDgxODIsIm5iZiI6MTcxMTAzNzQwNCwiaWF0IjoxNzExMDM3NDA0LCJpc3MiOiJBUElSZWdpc3RyeUFQSSJ9.sFRdh4GJGMIpbJj9RDUwrNvYtcysfuapyGg12gKfXhP69r3NV3Czd2jJghYMZhWdk4j0xd4yMJgz11FAQDp4g7HQs_uPZP1FQ6BJ2SuqTtPs8owVpndcsMupgmtWBlGkA-1EzhHKuvnmKvOCDGJUj2Og-HpbsnMAEnzJHzhPqmVy3tktyqJeLjjK3fCAVKlVzw7fO9V3xh1R_IqIylOWHvfhxPcT6NWtW3PkacsPxSUTFMnKdFDy4ROU1TZGofqqP4lHJfAKIYOAKb_rYvFhlQRnOU5amfswlpsOF_3TLbyccDXclEqVqN2f0JtfnUPIEU1brKN5PzwkETRmKx2Ypjjdt2C7C9A-w3m1L-Xn538JsH-ADYXpCFMXwm4P-BKEvPNuMZQYYbLpHi32skHUOq7bIGIQLJXqa0WLLsN60MM_nJMjuq1ivYX5zza38WcQOikPXrPte-c6gDbRBg2jmMMA3W9Hi7Kj_eLBloWtmNVEUP_yhiVfvcr_o5x87FuFq1GcUTRwJVOyuEI32EtOmpaoIM4VKIDS3pXpTH_FuBtMp9BcY5E2vGYll_Y1p8hwMfrMhR6WObKnYIPac983OLHZv2zkIM_f0UApTLUWciH8ZkCuA6ZlDD2GsJHgi7-BpakanrkZvbUn9UEklUpsgBVLfxO-kjKU_rb2KdjGbO8";
    //DecodeJwtService.decodeJwt(jwtToken);

    /**
     * Call for Json Pretty Printer
     */
    //JsonPrettryPrinterService.printJsonObject("");

    /**
     * Call for UUID Generator
     */
    //GenerateUUIDService.generateUUID();

    /**
     * @description Call for password encryptor
     * @method encryptPassword(String password, String algorithm, char[] hexArray)
     * @algorithm SHA-256, SHA-512, MD5
     */
    //EncryptPasswordService.encryptPassword("DaTawd2024!", "SHA-256", Util.getHexToCharArrayValue("0123456789ABCDEF"));

    /**
     * @description Call for password encryptor with salt
     * @method encryptPassword(String password, String algorithm, byte numOfBytes, char[] hexArray)
     * @algorithm SHA-256, SHA-512, MD5
     * @parameter byte bytes = 16;
     */
    //EncryptPasswordWithSaltService.encryptPassword("DaTawd2024!", "SHA-256", bytes, Util.getHexToCharArrayValue("0123456789ABCDEF"));



    SpringApplication.run(UtilitiesApplication.class, args);

  }

  @Override
  public void run(String... arg) {
//    storageService.deleteAll();
    fileStorageService.init();
  }

}