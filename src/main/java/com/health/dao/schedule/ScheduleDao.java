package com.health.dao.schedule;

import com.health.bo.schedule.ScheduleQueryBo;
import com.health.entity.schedule.ScheduleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author henry
 */
public interface ScheduleDao {
    /**
     * 设置出诊时间段
     * @param scheduleEntity
     * @return
     */
    int createScheduleEntity(ScheduleEntity scheduleEntity);
    
    /**
     * 条件查询出诊时间段列表
     * @param queryBo
     * @return
     */
    List<ScheduleEntity> queryListSchedule(@Param("queryBo") ScheduleQueryBo queryBo);
    
    /**
     * 根据主键查询排班
     * @param scheduleId
     * @return
     */
    ScheduleEntity selectScheduleEntity(@Param("scheduleId") Long scheduleId);
    
    /**
     * 删除排班
     * @param scheduleId
     * @param userId
     * @return
     */
	int deleteScheduleEntity(@Param("scheduleId") Long scheduleId, @Param("userId") Long userId);
    
    /**
     * 查询重复排班
     * @param scheduleDate
     * @param scheduleStartTime
     * @param userId
     * @return
     */
    ScheduleEntity queryScheduleEntity(@Param("scheduleDate") String scheduleDate, @Param("scheduleStartTime") String scheduleStartTime, @Param("userId") Long userId);
}
