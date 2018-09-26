package com.health.dao.clinic;

import com.health.bo.clinic.ClinicQueryBo;
import com.health.entity.clinic.ClinicEntity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author henry
 */
public interface ClinicDao {
    
    /**
     * 条件查询医院列表
     * @param queryBo
     * @return
     */
    List<ClinicEntity> queryListClinic(@Param("queryBo") ClinicQueryBo queryBo);
    
    /**
     * 根据主键查询医院
     * @param clinicId
     * @return
     */
    ClinicEntity selectClinicEntity(@Param("clinicId") Long clinicId);
}
