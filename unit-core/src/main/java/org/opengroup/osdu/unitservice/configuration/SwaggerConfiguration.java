package org.opengroup.osdu.unitservice.configuration;

import org.opengroup.osdu.core.common.model.http.DpsHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import static org.apache.commons.lang3.StringUtils.startsWithIgnoreCase;

@Configuration
@EnableOpenApi
public class SwaggerConfiguration {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String DEFAULT_INCLUDE_PATTERN = "/.*";

    @Bean
    public Docket apiV2() {
        RequestParameterBuilder builder = new RequestParameterBuilder();
        List<RequestParameter> parameters = new ArrayList<>();
        builder.name(DpsHeaders.DATA_PARTITION_ID)
                .description("tenant")
                .in(ParameterType.HEADER)
                .required(true)
                .build();
        parameters.add(builder.build());
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder().version("2.0").title("Unit API V2 - DEPRECATED").build())
                .groupName("~v2")
                .globalRequestParameters(parameters)
                .select()                
                .apis(RequestHandlerSelectors.basePackage("org.opengroup.osdu.unitservice.api"))
                .paths(s -> (startsWithIgnoreCase(s, "/api/unit/v2")|| startsWithIgnoreCase(s, "/api/unit/_ah")) && !startsWithIgnoreCase(s, "/api/unit/error"))
                .build()
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()));
    }

    @Bean
    public Docket apiV3() {
        RequestParameterBuilder builder = new RequestParameterBuilder();
        List<RequestParameter> parameters = new ArrayList<>();
        builder.name(DpsHeaders.DATA_PARTITION_ID)
                .description("tenant")
                .in(ParameterType.HEADER)
                .required(true)
                .build();
        parameters.add(builder.build());
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder().version("3.0").title("Unit API V3").build())
                .groupName("v3")
                .globalRequestParameters(parameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.opengroup.osdu.unitservice.api"))
                .paths(s -> (startsWithIgnoreCase(s, "/api/unit/v3") || startsWithIgnoreCase(s, "/api/unit/_ah") || startsWithIgnoreCase(s, "/api/unit/v3/info")) && !startsWithIgnoreCase(s, "/api/unit/error"))
                .build()
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()));
    }

    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION_HEADER, AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .operationSelector(o -> PathSelectors.regex(DEFAULT_INCLUDE_PATTERN).test(o.requestMappingPattern()))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes =
                new AuthorizationScope[]{authorizationScope};
        return Collections.singletonList(
                new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
    }
}
