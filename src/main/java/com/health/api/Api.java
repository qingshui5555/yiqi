package com.health.api;

import com.health.common.Constant;
import com.health.common.ResponseData;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Henry
 * @date 2018/7/8 20:47
 */
@Component
@Path("/api")
@io.swagger.annotations.Api(value = "/api")
public class Api extends BaseApi {
    
    @GET
    @Path("version")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getVersion(@Context final HttpHeaders httpHeaders) {
        try{
            return Response.status(Response.Status.OK).entity(new ResponseData(true, Constant.STR_VERSION + new ResponseData(Constant.STR_EMPTY).getApiVersion())).build();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.serverError().entity(new ResponseData(true, e)).build();
        }
    }
}
