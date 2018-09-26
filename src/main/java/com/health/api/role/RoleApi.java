package com.health.api.role;

import com.health.api.BaseApi;
import com.health.bo.role.RoleQueryBo;
import com.health.common.ResponseData;
import com.health.dto.role.RoleDto;
import com.health.service.role.RoleService;
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
 * @author Henry
 * @date 2018/7/8 20:47
 */
@Component
@Path("/role")
@Api(value = "/role")
public class RoleApi extends BaseApi {

    @Autowired
    private RoleService roleService;
	
	/**
		 * 条件查询角色列表
		 * @param headers
		 * @param roleName
		 * @return
	 */
	 @GET
    @Path("query")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response query(@Context final HttpHeaders headers, @QueryParam("roleName") final String roleName,
								   @QueryParam("pageSize") final Integer pageSize, @QueryParam("pageNo") final Integer pageNo) {
        try {
			  RoleQueryBo queryBo = new RoleQueryBo(pageSize==null?20:pageSize, pageNo==null?1:pageNo);
			  if (StringUtils.isNotBlank(roleName)) {
			  		queryBo.setRoleName(roleName);
			  }
			  return Response.status(Response.Status.OK).entity(new ResponseData(roleService.query(headers, queryBo), queryBo.getPager())).build();
		  } catch (final Throwable e) {
            logger.error(e.getMessage(), e);
            return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
        }
    }
    
    @POST
	 @Path("create")
	 @Produces({MediaType.APPLICATION_JSON})
	 @Consumes({MediaType.APPLICATION_JSON})
	 public Response create(@Context final HttpHeaders headers, final RoleDto roleDto) {
		 try {
			 return Response.status(Response.Status.OK).entity(new ResponseData(roleService.create(headers, roleDto))).build();
		 } catch (final Throwable e) {
			 logger.error(e.getMessage(), e);
			 return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
		 }
	 }
	
	@POST
	@Path("update")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response update(@Context final HttpHeaders headers, final RoleDto roleDto) {
		try {
			return Response.status(Response.Status.OK).entity(new ResponseData(roleService.update(headers, roleDto))).build();
		} catch (final Throwable e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
		}
	}
	
	@POST
	@Path("delete")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response delete(@Context final HttpHeaders headers, final RoleDto roleDto) {
		try {
			return Response.status(Response.Status.OK).entity(new ResponseData(roleService.delete(headers, roleDto))).build();
		} catch (final Throwable e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.OK).entity(new ResponseData(true, e)).build();
		}
	}
}
