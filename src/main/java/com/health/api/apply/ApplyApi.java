package com.health.api.apply;

import com.health.api.BaseApi;
import com.health.bo.apply.ApplyQueryBo;
import com.health.common.ResponseData;
import com.health.dto.apply.ApplyDto;
import com.health.service.apply.ApplyService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;

/**
 * @author henry
 */
@Component
@Path("/apply")
@Api(value = "/apply")
public class ApplyApi extends BaseApi {

    @Autowired
    private ApplyService applyService;
    
    /**
     * 创建出诊申请单
     * @param httpHeaders
     * @param applyDto
     * @return
     */
    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(@Context final HttpHeaders httpHeaders, final ApplyDto applyDto){
        try {
            return Response.status(Response.Status.OK).entity(new ResponseData(applyService.create(httpHeaders, applyDto))).build();
        } catch (final Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
        }
    }
    
    /**
     * 条件查询出诊申请单列表
     * @param headers
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("query")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response query(@Context final HttpHeaders headers, @QueryParam("startDate") final String startDate,
                           @QueryParam("endDate") final String endDate, @QueryParam("auditStatus") final Integer auditStatus,
                           @QueryParam("scheduleId") final Long scheduleId,
                           @QueryParam("pageSize") final Integer pageSize, @QueryParam("pageNo") final Integer pageNo) {
        try {
            ApplyQueryBo queryBo = new ApplyQueryBo(pageSize==null?20:pageSize, pageNo==null?1:pageNo);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if(StringUtils.isNotBlank(startDate)){
                queryBo.setStartDate(format.parse(startDate));
            }
            if(StringUtils.isNotBlank(endDate)){
                queryBo.setEndDate(format.parse(endDate));
            }
            if(auditStatus != null){
                queryBo.setAuditStatus(auditStatus);
            }
            if(scheduleId != null){
                queryBo.setScheduleId(scheduleId);
            }
            return Response.status(Response.Status.OK).entity(new ResponseData(applyService.query(headers, queryBo), queryBo.getPager())).build();
        } catch (final Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
        }
    }
    
    @POST
    @Path("/audit")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response audit(@Context final HttpHeaders httpHeaders, final ApplyDto applyDto) {
        try {
            return Response.status(Response.Status.OK).entity(new ResponseData(applyService.audit(httpHeaders, applyDto))).build();
        } catch (final Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
        }
    }
    
    @POST
    @Path("/reject")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response reject(@Context final HttpHeaders httpHeaders, final ApplyDto applyDto) {
        try {
            return Response.status(Response.Status.OK).entity(new ResponseData(applyService.reject(httpHeaders, applyDto))).build();
        } catch (final Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
        }
    }
}
