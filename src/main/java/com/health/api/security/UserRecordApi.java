package com.health.api.security;

import com.health.api.BaseApi;
import com.health.bo.security.UserRecordQueryBo;
import com.health.common.ResponseData;
import com.health.dto.security.UserRecordDto;
import com.health.service.security.UserRecordService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author henry
 */
@Component
@Path("/user/record")
@Api(value = "/user/record")
public class UserRecordApi extends BaseApi {

    @Autowired
    private UserRecordService userRecordService;

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(@Context final HttpHeaders httpHeaders, final UserRecordDto userRecordDto ){
        try {
            return Response.status(Response.Status.OK).entity(new ResponseData(userRecordService.create(httpHeaders, userRecordDto))).build();
        } catch (final Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
        }
    }

    @GET
    @Path("query")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response query(@Context final HttpHeaders headers, @QueryParam("userId") final Long userId,
                           @QueryParam("mobile") final String mobile,
                           @QueryParam("pageSize") final Integer pageSize, @QueryParam("pageNo") final Integer pageNo) {
        try {
            UserRecordQueryBo queryBo = new UserRecordQueryBo(pageSize==null?20:pageSize, pageNo==null?1:pageNo);
            if (userId != null) {
                queryBo.setUserId(userId);
            }
            if(StringUtils.isNotBlank(mobile)){
                queryBo.setMobile(mobile);
            }
            return Response.status(Response.Status.OK).entity(new ResponseData(userRecordService.query(headers, queryBo), queryBo.getPager())).build();
        } catch (final Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
        }
    }
}
