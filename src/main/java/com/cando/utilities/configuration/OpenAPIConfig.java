/**
 * {@link com.jfcm.manda.bookingmanagerapi.config.OpenAPIConfig}.java
 * Copyright © 2023 JFCM. All rights reserved. This software is the confidential and
 * proprietary information of JFCM Mandaluyong
 *
 * @author Ronald Cando
 */
package com.cando.utilities.configuration;

import com.cando.utilities.resources.DecodeJwtResource;
import com.cando.utilities.resources.EncryptPasswordResource;
import com.cando.utilities.resources.FileResource;
import com.cando.utilities.resources.GenerateUUIDResource;
import com.cando.utilities.resources.IndexResource;
import com.cando.utilities.resources.JsonPrettyPrinterResource;
import com.cando.utilities.resources.ViewCertificateContentResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.configuration.SpringDocDataRestConfiguration;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@OpenAPIDefinition(
    info = @Info(
        title = "Utilities",
        version = "1.0.0",
        description = "Utility tools for development",
        termsOfService = "Terms of Services...",
        contact = @Contact(
            name = "Ronald Cando",
            url = "http://ronald-cando/dummy/url",
            email = "ron.cando04@gmail.com"
        ),
        license = @License(
            name = "Utilities API 1.0.0",
            url = "http://ronald-cando/dummy/url"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8082/",
            description = "LOCAL ENV")
    }
)
@Configuration
@Import(SpringDocDataRestConfiguration.class)
public class OpenAPIConfig {

  static {
    SpringDocUtils.getConfig()
        .addRestControllers(DecodeJwtResource.class, EncryptPasswordResource.class, FileResource.class, GenerateUUIDResource.class, IndexResource.class,
            JsonPrettyPrinterResource.class, ViewCertificateContentResource.class);
  }
}