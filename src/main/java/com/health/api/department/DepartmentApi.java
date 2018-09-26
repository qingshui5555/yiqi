package com.health.api.department;

import com.health.api.BaseApi;
import com.health.bo.DepartmentQueryBo;
import com.health.common.ResponseData;
import com.health.service.department.DepartmentService;
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
@Path("/department")
@Api(value = "/department")
public class DepartmentApi extends BaseApi {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 条件查询科室列表
     */
    @GET
    @Path("query")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response query(@Context final HttpHeaders httpHeaders, @QueryParam("clinicId") final Long clinicId,
                           @QueryParam("departmentName") final String departmentName,
                           @QueryParam("pageSize") final Integer pageSize, @QueryParam("pageNo") final Integer pageNo) {
        try {
            DepartmentQueryBo queryBo = new DepartmentQueryBo(pageSize==null?20:pageSize, pageNo==null?1:pageNo);
            if(clinicId != null){
                queryBo.setClinicId(clinicId);
            }
            if(StringUtils.isNotBlank(departmentName)){
                queryBo.setDepartmentName(departmentName);
            }
            return Response.status(Response.Status.OK).entity(new ResponseData(departmentService.query(httpHeaders, queryBo), queryBo.getPager())).build();
        } catch (final Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
        }
    }
}
