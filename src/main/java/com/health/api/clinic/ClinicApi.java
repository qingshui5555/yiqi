package com.health.api.clinic;

import com.health.api.BaseApi;
import com.health.bo.clinic.ClinicQueryBo;
import com.health.common.ResponseData;
import com.health.service.clinic.ClinicService;
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
@Path("/clinic")
@Api(value = "/clinic")
public class ClinicApi extends BaseApi {

    @Autowired
    private ClinicService clinicService;

    /**
     * 条件查询医院列表
     */
    @GET
    @Path("query")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response query(@Context final HttpHeaders httpHeaders, @QueryParam("departmentName") final String departmentName,
                          @QueryParam("clinicName") final String clinicName,
                          @QueryParam("pageSize") final Integer pageSize, @QueryParam("pageNo") final Integer pageNo) {
        try {
            ClinicQueryBo queryBo = new ClinicQueryBo(pageSize==null?20:pageSize, pageNo==null?1:pageNo);
            if(StringUtils.isNotBlank(departmentName)){
                queryBo.setDepartmentName(departmentName);
            }
            if(StringUtils.isNotBlank(clinicName)){
                queryBo.setClinicName(clinicName);
            }
            return Response.status(Response.Status.OK).entity(new ResponseData(clinicService.query(httpHeaders, queryBo), queryBo.getPager())).build();
        } catch (final Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
        }
    }
}
