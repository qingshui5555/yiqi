package com.health.api.security;

import com.health.api.BaseApi;
import com.health.bo.security.UserQueryBo;
import com.health.common.Constant;
import com.health.common.ResponseData;
import com.health.dto.security.LoginDto;
import com.health.dto.security.UserDto;
import com.health.service.security.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author henry
 */
@Component
@Path("/user")
@Api(value = "/user")

public class UserApi extends BaseApi {

    @Autowired
    private UserService userService;
   
    @POST
    @Path("/web/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response createWebUser(@Context final HttpHeaders httpHeaders, final UserDto userDto) {
       try {
          return Response.status(Response.Status.OK).entity(new ResponseData(userService.createWebUser(httpHeaders, userDto))).build();
       } catch (final Throwable e) {
          logger.error(e.getMessage(), e);
          return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
       }
    }
    
   @POST
   @Path("/web/update")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response updateWebUser(@Context final HttpHeaders httpHeaders, final UserDto userDto) {
      try {
         userDto.setType(Constant.TYPE_USER_WEB);
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.updateUser(httpHeaders, userDto))).build();
      } catch (final Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @POST
   @Path("/web/audit")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response audit(@Context final HttpHeaders httpHeaders, final UserDto userDto) {
      try {
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.audit(httpHeaders, userDto))).build();
      } catch (final Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @POST
   @Path("/web/reject")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response reject(@Context final HttpHeaders httpHeaders, final UserDto userDto) {
      try {
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.reject(httpHeaders, userDto))).build();
      } catch (final Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @GET
   @Path("/web/createCode")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response createWebCode(@Context final HttpHeaders httpHeaders, @QueryParam("mobile") final String mobile) {
      try {
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.createCode(httpHeaders, null, mobile))).build();
      } catch (final Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.serverError().entity(new ResponseData(true, e)).build();
      }
   }
   
   @POST
   @Path("/web/login")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response webLogin(@Context final HttpHeaders httpHeaders, final LoginDto loginDto) {
      try {
         return Response.status(200).entity(new ResponseData(userService.userLogin(httpHeaders, loginDto))).build();
      } catch (Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @POST
   @Path("/doctor/register")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response registerDoctor(@Context final HttpHeaders httpHeaders, final UserDto userDto) {
      try {
         return Response.status(200).entity(new ResponseData(userService.registerDoctor(httpHeaders, userDto))).build();
      } catch (Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @POST
   @Path("/doctor/update")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response updateDoctor(@Context final HttpHeaders httpHeaders, final UserDto userDto) {
      try {
         userDto.setType(Constant.TYPE_USER_DOC);
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.updateUser(httpHeaders, userDto))).build();
      } catch (final Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @GET
   @Path("/doctor/createCode")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response createDoctorCode(@Context final HttpHeaders httpHeaders, @QueryParam("mobile") final String mobile) {
      try {
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.createCode(httpHeaders, null, mobile))).build();
      } catch (final Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @POST
   @Path("/doctor/login")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response doctorLogin(@Context final HttpHeaders httpHeaders, final LoginDto loginDto){
      try {
         return Response.status(200).entity(new ResponseData(userService.userLogin(httpHeaders, loginDto))).build();
      } catch (Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @POST
   @Path("/app/register")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response registerAppUser(@Context final HttpHeaders httpHeaders, final UserDto userDto){
      try {
         return Response.status(200).entity(new ResponseData(userService.registerAppUser(httpHeaders, userDto))).build();
      } catch (Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @POST
   @Path("/app/update")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response updateAppUser(@Context final HttpHeaders httpHeaders, final UserDto userDto) {
      try {
         userDto.setType(Constant.TYPE_USER_APP);
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.updateUser(httpHeaders, userDto))).build();
      } catch (final Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @GET
   @Path("/app/createCode")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response createAppCode(@Context final HttpHeaders httpHeaders, @QueryParam("mobile") final String mobile) {
      try {
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.createCode(httpHeaders, Constant.TYPE_USER_APP, mobile))).build();
      } catch (final Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @POST
   @Path("/app/login")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response appLogin(@Context final HttpHeaders httpHeaders, final LoginDto loginDto){
      try {
         loginDto.setType(Constant.TYPE_USER_APP);
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.userLogin(httpHeaders, loginDto))).build();
      } catch (Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @POST
   @Path("/logout")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response appLogout(@Context final HttpHeaders httpHeaders){
      try {
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.userLogout(httpHeaders))).build();
      } catch (Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @GET
   @Path("/query")
   @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
   public Response query(@Context final HttpHeaders httpHeaders, @QueryParam("mobile") final String mobile,
                         @QueryParam("clinicId") Long clinicId, @QueryParam("departmentName") String departmentName,
                         @QueryParam("name") String name, @QueryParam("type") Integer type,
                         @QueryParam("registerStatus") Integer registerStatus,
                         @QueryParam("pageSize") final Integer pageSize, @QueryParam("pageNo") final Integer pageNo){
      try {
         UserQueryBo queryBo = new UserQueryBo(pageSize==null?20:pageSize, pageNo==null?1:pageNo);
         if(type != null) {
            queryBo.setType(type);
         }
         if(clinicId != null){
            queryBo.setClinicId(clinicId);
         }
         if(StringUtils.isNotBlank(departmentName)){
            queryBo.setDepartmentName(departmentName);
         }
         if(StringUtils.isNotBlank(name)){
            queryBo.setName(name);
         }
         if(StringUtils.isNotBlank(mobile)){
            queryBo.setMobile(mobile);
         }
         if(registerStatus != null){
            queryBo.setRegisterStatus(registerStatus);
         }
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.query(httpHeaders, queryBo), queryBo.getPager())).build();
      } catch (Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @GET
   @Path("/schedule/query")
   @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
   public Response querySchedule(@Context final HttpHeaders httpHeaders, @QueryParam("clinicId") Long clinicId,
                                  @QueryParam("departmentName") String departmentName, @QueryParam("name") String name,
                                  @QueryParam("startDate") final String startDate, @QueryParam("endDate") final String endDate,
                                  @QueryParam("pageSize") final Integer pageSize, @QueryParam("pageNo") final Integer pageNo){
      try {
         UserQueryBo queryBo = new UserQueryBo(pageSize==null?20:pageSize, pageNo==null?1:pageNo);
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         if(clinicId != null){
            queryBo.setClinicId(clinicId);
         }
         if(StringUtils.isNotBlank(departmentName)){
            queryBo.setDepartmentName(departmentName);
         }
         if (StringUtils.isBlank(startDate)) {
            queryBo.setStartDate(new Date());
         } else {
            queryBo.setStartDate(format.parse(startDate));
         }
         if(StringUtils.isNotBlank(endDate)){
            queryBo.setEndDate(format.parse(endDate));
         }
         if(StringUtils.isNotBlank(name)){
            queryBo.setName(name);
         }
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.querySchedule(httpHeaders, queryBo), queryBo.getPager())).build();
      } catch (Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @POST
   @Path("delete")
   @Produces({MediaType.APPLICATION_JSON})
   @Consumes({MediaType.APPLICATION_JSON})
   public Response delete(@Context final HttpHeaders headers, final UserDto userDto) {
      try {
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.delete(headers, userDto))).build();
      } catch (final Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
   
   @GET
   @Path("/account/create")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces(MediaType.APPLICATION_JSON)
   public Response createRandomAccount(@Context final HttpHeaders httpHeaders, @QueryParam("prefix") final String prefix, @QueryParam("size") final Integer size) {
      try {
         return Response.status(Response.Status.OK).entity(new ResponseData(userService.createRandomAccount(httpHeaders, prefix, size))).build();
      } catch (final Throwable e) {
         logger.error(e.getMessage(), e);
         return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
      }
   }
}
