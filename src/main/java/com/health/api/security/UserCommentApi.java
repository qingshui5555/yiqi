package com.health.api.security;

import com.health.api.BaseApi;
import com.health.common.ResponseData;
import com.health.dto.security.UserCommentDto;
import com.health.service.security.UserCommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author henry
 */
@Component
@Path("/user/comment")
@Api(value = "/user/comment")
public class UserCommentApi extends BaseApi {

    @Autowired
    private UserCommentService userCommentService;

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(@Context final HttpHeaders httpHeaders, final UserCommentDto userCommentDto ){
        try {
            return Response.status(Response.Status.OK).entity(new ResponseData(userCommentService.create(httpHeaders, userCommentDto))).build();
        } catch (final Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
        }
    }
}
