package com.health.application;

import io.swagger.jaxrs.listing.AcceptHeaderApiListingResource;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Henry
 * @date 2018/7/8 20:47
 */
public class RestApplication extends ResourceConfig {
    public RestApplication() {
        register(ApiListingResource.class);
        register(AcceptHeaderApiListingResource.class);
        register(SwaggerSerializers.class);
        register(LoggingFilter.class);

        packages("com.health.api");
    }
}