package com.health.dao.apply;

import com.health.bo.apply.ApplyQueryBo;
import com.health.entity.apply.ApplyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author henry
 */
public interface ApplyDao {
    
    /**
     * 创建申请单
     * @param applyEntity
     * @return
     */
    Long createApplyEntity(ApplyEntity applyEntity);
    
    /**
     * 条件查询出诊申请单列表
     * @param queryBo
     * @return
     */
    List<ApplyEntity> queryListApply(@Param("queryBo") ApplyQueryBo queryBo);
    
    /**
     * 条件查询出诊申请单列表
     * @param queryBo
     * @return
     */
    List<ApplyEntity> queryListApplyExpired(@Param("queryBo") ApplyQueryBo queryBo);
    
    /**
     * 根据主键查询出诊申请
     * @param applyId
     * @return
     */
    ApplyEntity selectApplyEntity(@Param("applyId") Long applyId);
    
    /**
     * 更新出诊申请
     * @param applyEntity
     * @return
     */
    int updateApplyEntity(ApplyEntity applyEntity);
    
    /**
     * 统计该排班已批准的数量
     * @param scheduleId
     * @param doctorUserId
     * @return
     */
    int countApplyEntity(@Param("doctorUserId") Long doctorUserId, @Param("scheduleId") Long scheduleId);
}
