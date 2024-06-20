/**
 * {@link com.jfcm.manda.bookingmanagerapi.config.OpenAPIConfig}.java
 * Copyright © 2023 JFCM. All rights reserved. This software is the confidential and
 * proprietary information of JFCM Mandaluyong
 *
 * @author Ronald Cando
 */
package com.cando.utilities.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "Utilities",
        version = "1.0.0",
        description = "Utility tools for developement",
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
public class OpenAPIConfig {

}