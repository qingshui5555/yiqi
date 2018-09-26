package com.health.api.schedule;

import com.health.api.BaseApi;
import com.health.bo.schedule.ScheduleQueryBo;
import com.health.common.Pager;
import com.health.common.ResponseData;
import com.health.dto.schedule.ScheduleDto;
import com.health.service.schedule.ScheduleService;
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
@Path("/schedule")
@Api(value = "/schedule")
public class ScheduleApi extends BaseApi {

    @Autowired
    private ScheduleService scheduleService;
    
    /**
     * 设置出诊时间段
     * @param httpHeaders
     * @param scheduleDto
     * @return
     */
    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(@Context final HttpHeaders httpHeaders, final ScheduleDto scheduleDto ){
        try {
            return Response.status(Response.Status.OK).entity(new ResponseData(scheduleService.create(httpHeaders, scheduleDto))).build();
        } catch (final Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
        }
    }
    
    /**
     * 条件查询出诊列表
     * @param headers
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("query")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response query(@Context final HttpHeaders headers, @QueryParam("startDate") final String startDate,
                           @QueryParam("endDate") final String endDate,@QueryParam("userId") final Long userId,
                           @QueryParam("pageSize") final Integer pageSize,@QueryParam("pageNo") final Integer pageNo) {
        try {
            ScheduleQueryBo queryBo = new ScheduleQueryBo(pageSize==null?20:pageSize, pageNo==null?1:pageNo);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if(userId != null){
                queryBo.setUserId(userId);
            }
            if(StringUtils.isNotBlank(startDate)){
                queryBo.setStartDate(format.parse(startDate));
            }
            if(StringUtils.isNotBlank(endDate)){
                queryBo.setEndDate(format.parse(endDate));
            }
            return Response.status(Response.Status.OK).entity(new ResponseData(scheduleService.query(headers, queryBo), queryBo.getPager())).build();
        } catch (final Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
        }
    }
    
    @POST
    @Path("delete")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response delete(@Context final HttpHeaders httpHeaders, final ScheduleDto scheduleDto ){
        try {
            return Response.status(Response.Status.OK).entity(new ResponseData(scheduleService.delete(httpHeaders, scheduleDto))).build();
        } catch (final Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
        }
    }
}
