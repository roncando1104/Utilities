package com.cando.utilities.services;

import com.cando.utilities.model.CertJsonBean;

public interface JwtService {

  String generateToken(CertJsonBean certJsonBean);
}
