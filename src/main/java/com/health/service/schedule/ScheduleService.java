package com.health.service.schedule;

import com.health.bo.apply.ApplyQueryBo;
import com.health.bo.schedule.ScheduleQueryBo;
import com.health.common.Constant;
import com.health.dao.apply.ApplyDao;
import com.health.dao.schedule.ScheduleDao;
import com.health.dto.role.RoleDto;
import com.health.dto.schedule.ScheduleDto;
import com.health.entity.apply.ApplyEntity;
import com.health.entity.schedule.ScheduleEntity;
import com.health.exception.RequestConflictedException;
import com.health.service.BaseService;
import com.health.transfer.schedule.ScheduleTransfer;
import com.health.util.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.HttpHeaders;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author henry
 */
public class ScheduleService extends BaseService {

    @Autowired
    private ScheduleDao scheduleDao;
    
    @Autowired
    private ApplyDao applyDao;
    
    /**
     * 条件查询医院列表
     * @param httpHeaders
     * @param scheduleDto
     * @return
     */
    public int create(HttpHeaders httpHeaders, ScheduleDto scheduleDto) {
        int result = -1;
        Date createOn = new Date();
        Long createBy = TokenUtils.getUserIdFromToken(TokenUtils.getAuthToken(httpHeaders));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Long userId = scheduleDto.getUserId();
        if(userId == null){
            throw new RequestConflictedException("E0000001", "userId");
        }
        Date scheduleStartDate = scheduleDto.getScheduleStartDate();
        if(scheduleStartDate == null){
            throw new RequestConflictedException("E0000001", "scheduleStartDate");
        }
        Date scheduleEndDate = scheduleDto.getScheduleEndDate();
        if(scheduleEndDate == null){
            throw new RequestConflictedException("E0000001", "scheduleEndDate");
        }
        String scheduleStartTime = scheduleDto.getScheduleStartTime();
        if(StringUtils.isBlank(scheduleStartTime )){
            throw new RequestConflictedException("E0000001", "scheduleStartTime");
        }
        String scheduleEndTime = scheduleDto.getScheduleEndTime();
        if(StringUtils.isBlank(scheduleEndTime)){
            throw new RequestConflictedException("E0000001", "scheduleEndTime");
        }
        Long max = scheduleDto.getMax();
        if(max == null){
            throw new RequestConflictedException("E0000001", "max");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(scheduleStartDate);
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(scheduleEndDate);
        calendarEnd.set(Calendar.DAY_OF_MONTH, calendarEnd.get(Calendar.DAY_OF_MONTH) + 1);
        while (calendar.before(calendarEnd)){
            ScheduleEntity schedule = new ScheduleEntity();
            schedule.setMax(max);
            schedule.setScheduleDate(calendar.getTime());
            schedule.setScheduleStart(scheduleStartTime);
            schedule.setScheduleEnd(scheduleEndTime);
            schedule.setUserId(userId);
            schedule.setCreateBy(createBy);
            schedule.setCreateOn(createOn);
            schedule.setStatus(Constant.VALID);
            schedule.setVersion(Constant.DEFAULT_VERSION);
            
            ScheduleEntity scheduleDb = scheduleDao.queryScheduleEntity(simpleDateFormat.format(calendar.getTime()), scheduleStartTime, userId);
            if(scheduleDb != null){
                throw new RequestConflictedException("E0000007","scheduleStartTime");
            }
            
            result += scheduleDao.createScheduleEntity(schedule);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        }
        return result;
    }
    
    /**
     * 查询出诊时间段列表
     * @param httpHeaders
     * @param queryBo
     * @return
     */
    public List<ScheduleTransfer> query(HttpHeaders httpHeaders, ScheduleQueryBo queryBo){
        List<ScheduleEntity> scheduleEntityList = scheduleDao.queryListSchedule(queryBo);
        List<ScheduleTransfer> scheduleTransferList = new ArrayList<>();
        for(ScheduleEntity entity : scheduleEntityList){
            ScheduleTransfer transfer = new ScheduleTransfer();
            BeanUtils.copyProperties(entity,transfer);
            transfer.setCurrent(applyDao.countApplyEntity(entity.getUserId(), entity.getScheduleId()));
            if(transfer.getCurrent() < transfer.getMax()){
                transfer.setFull(false);
            } else {
                transfer.setFull(true);
            }
            scheduleTransferList.add(transfer);
        }
        return scheduleTransferList;
    }
    
    /**
     * 删除排班
     * @param httpHeaders
     * @param scheduleDto
     * @return
     */
    public int delete(HttpHeaders httpHeaders, ScheduleDto scheduleDto){
        int result = -1;
        ApplyQueryBo queryBo = new ApplyQueryBo();
        Long userId = TokenUtils.getUserIdFromToken(TokenUtils.getAuthToken(httpHeaders));
        queryBo.setUserId(userId);
        Long scheduleId = scheduleDto.getScheduleId();
        if(scheduleId == null){
            throw new RequestConflictedException("E0000001", "scheduleId");
        }
        queryBo.setScheduleId(scheduleId);
        queryBo.setStartDate(new Date());
        List<ApplyEntity> applyEntityList = applyDao.queryListApply(queryBo);
        if(applyEntityList != null && applyEntityList.size() > 0){
            throw new RequestConflictedException("E2000004");
        }
        
        result = scheduleDao.deleteScheduleEntity(scheduleId, userId);
        
        return result;
    }
}