package com.health.bo.role;

import com.health.bo.BaseQueryBo;

/**
 * @author henry
 */
public class RoleQueryBo extends BaseQueryBo {
    
    /**
     * 角色名称
     */
    private String roleName;
    
    public RoleQueryBo(){
        super();
    }
    
    public RoleQueryBo(int pageSize, int pageNo){
        super(pageSize, pageNo);
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
}
