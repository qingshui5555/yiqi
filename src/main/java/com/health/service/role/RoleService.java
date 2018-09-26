package com.health.service.role;

import com.health.bo.role.RoleQueryBo;
import com.health.common.Constant;
import com.health.dao.role.RoleDao;
import com.health.dto.role.RoleDto;
import com.health.entity.role.RoleEntity;
import com.health.exception.RequestConflictedException;
import com.health.service.BaseService;
import com.health.transfer.role.RoleTransfer;
import com.health.util.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.HttpHeaders;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author henry
 */
public class RoleService extends BaseService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 条件查询角色列表
     * @param httpHeaders
     * @param queryBo
     * @return
     */
    public List<RoleTransfer> query(HttpHeaders httpHeaders, RoleQueryBo queryBo) {
        List<RoleEntity> roleEntityList = roleDao.queryListRole(queryBo);
        List<RoleTransfer> roleTransferList = new ArrayList<>();
        for(RoleEntity entity : roleEntityList){
            RoleTransfer transfer = new RoleTransfer();
            BeanUtils.copyProperties(entity,transfer);
            roleTransferList.add(transfer);
        }
        return roleTransferList;
    }
    
    /**
     * 创建角色
     * @param httpHeaders
     * @param roleDto
     * @return
     */
    public Long create(HttpHeaders httpHeaders, RoleDto roleDto){
        Date createOn = new Date();
        Long createBy = TokenUtils.getUserIdFromToken(TokenUtils.getAuthToken(httpHeaders));
        String roleName = roleDto.getRoleName();
        if(StringUtils.isBlank(roleName)){
            throw new RequestConflictedException("E0000001", "roleName");
        }
        RoleQueryBo queryBo = new RoleQueryBo();
        queryBo.setRoleName(roleName);
        List<RoleEntity> roleEntityList = roleDao.queryListRole(queryBo);
        if(roleEntityList != null && roleEntityList.size() > 0){
            throw new RequestConflictedException("E0000003", "roleName");
        }
        
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(roleName);
        roleEntity.setCreateOn(createOn);
        roleEntity.setCreateBy(createBy);
        roleEntity.setStatus(Constant.VALID);
        roleEntity.setVersion(Constant.DEFAULT_VERSION);
        roleDao.createRoleEntity(roleEntity);
        
        return roleEntity.getRoleId();
    }
    
    /**
     * 更新角色
     * @param httpHeaders
     * @param roleDto
     * @return
     */
    public int update(HttpHeaders httpHeaders, RoleDto roleDto){
        Date modifyOn = new Date();
        Long modifyBy = TokenUtils.getUserIdFromToken(TokenUtils.getAuthToken(httpHeaders));
        int result = -1;
        Long roleId = roleDto.getRoleId();
        if(roleId == null){
            throw new RequestConflictedException("E0000001", "roleId");
        }
        String roleName = roleDto.getRoleName();
        if(StringUtils.isNotBlank(roleName)){
            RoleQueryBo queryBo = new RoleQueryBo();
            queryBo.setRoleName(roleName);
            List<RoleEntity> roleEntityList = roleDao.queryListRole(queryBo);
            if(roleEntityList != null && roleEntityList.size() > 0){
                throw new RequestConflictedException("E0000003", "roleName");
            }
        }
        
        RoleEntity roleEntity = roleDao.selectRoleEntity(roleId);
        if(roleEntity == null){
            throw new RequestConflictedException("E0000004", "role");
        }
        
        roleEntity.setRoleName(roleName);
        roleEntity.setModifyOn(modifyOn);
        roleEntity.setModifyBy(modifyBy);
        roleEntity.setStatus(Constant.VALID);
        result = roleDao.updateRoleEntity(roleEntity);
        
        return result;
    }
    
    /**
     * 更新角色
     * @param httpHeaders
     * @param roleDto
     * @return
     */
    public int delete(HttpHeaders httpHeaders, RoleDto roleDto){
        int result = -1;
        Long roleId = roleDto.getRoleId();
        if(roleId == null){
            throw new RequestConflictedException("E0000001", "roleId");
        }
        result = roleDao.deleteRoleEntity(roleId);
        
        return result;
    }
}
