package org.opengroup.osdu.unitservice.swagger;

import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.opengroup.osdu.core.common.model.http.DpsHeaders;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import javax.servlet.ServletContext;
import java.util.Collections;
import org.springframework.context.annotation.Profile;



@Configuration
@Profile("!noswagger")
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi apiV2() {
        return GroupedOpenApi.builder()
                .group("~v2")
                .pathsToExclude("/api/unit/error")
                .pathsToMatch("/api/unit/v2", "/api/unit/_ah")
                .build();
    }

    @Bean
    public GroupedOpenApi apiV3() {
        return GroupedOpenApi.builder()
                .group("v3")
                .pathsToExclude("/api/unit/error")
                .pathsToMatch("/api/unit/v3", "/api/unit/_ah", "/api/unit/v3/info")
                .packagesToScan("src/main/java/org/opengroup/osdu/unitservice")
                .build();
    }

    @Bean
    public OpenAPI openApi(ServletContext servletContext) {
        Server server = new Server().url(servletContext.getContextPath());
        return new OpenAPI()
                .servers(Collections.singletonList(server))
                .info(new Info()
                        .description("Unit Service that provides a set of APIs ")
                        .title("Unit API V3 ")
                        .version("2.0"))
                .components(new Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("Authorization")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization")))
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("Authorization"));

    }

    @Bean
    public OperationCustomizer customize() {
        return (operation, handlerMethod) -> {
            operation.addParametersItem(
                    new Parameter()
                            .in("header")
                            .required(true)
                            .description("Tenant Id")
                            .name(DpsHeaders.DATA_PARTITION_ID));
            return operation;
        };
    }
}
