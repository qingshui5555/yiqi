package com.health.dao.role;

import com.health.bo.role.RoleQueryBo;
import com.health.entity.role.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Henry
 * @date 2018/7/8 20:47
 */
public interface RoleDao {
    
    /**
     * 查询角色列表
     * @param queryBo
     * @return
     */
    List<RoleEntity> queryListRole(@Param("queryBo") RoleQueryBo queryBo);
    
    /**
     * 查询角色列表
     * @param userId
     * @return
     */
    List<RoleEntity> queryListRoleByUserId(@Param("userId") Long userId);
    
    /**
     * 根据主键查询角色
     * @param roleId
     * @return
     */
    RoleEntity selectRoleEntity(@Param("roleId") Long roleId);
    
    /**
     * 创建角色
     * @param roleEntity
     * @return
     */
    Long createRoleEntity(RoleEntity roleEntity);
    
    /**
     * 修改角色
     * @param roleEntity
     * @return
     */
    int updateRoleEntity(RoleEntity roleEntity);
    
    /**
     * 删除角色
     * @param roleId
     * @return
     */
    int deleteRoleEntity(@Param("roleId") Long roleId);
}
