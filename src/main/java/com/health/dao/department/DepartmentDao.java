package com.health.dao.department;

import com.health.bo.DepartmentQueryBo;
import com.health.entity.department.DepartmentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author henry
 */
public interface DepartmentDao {
    
    /**
     * 条件查询科室列表
     * @param queryBo
     * @return
     */
    List<DepartmentEntity> queryListDepartment(@Param("queryBo") DepartmentQueryBo queryBo);
    
    /**
     * 根据主键查询科室
     * @param departmentId
     * @return
     */
    DepartmentEntity selectDepartmentEntity(@Param("departmentId") Long departmentId);
}
