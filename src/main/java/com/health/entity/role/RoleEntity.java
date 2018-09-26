package com.health.entity.role;

import com.health.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Henry
 * @date 2018/7/8 20:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleEntity extends BaseEntity {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色英文名称
     */
    private String roleName;
    
    /**
     * 是否可编辑：0 不可编辑 1 可编辑
     */
    private Integer isEdit;
    
    public Long getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public Integer getIsEdit() {
        return isEdit;
    }
    
    public void setIsEdit(Integer isEdit) {
        this.isEdit = isEdit;
    }
}
