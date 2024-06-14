package com.cando.utilities.services;

public interface ViewCertificateContentService {

  String getCertificateList(String keystoreFile, String password);

  String getPfxCertificate(String keystoreFile, String password);

}
