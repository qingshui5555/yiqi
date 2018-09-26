package com.health.service.clinic;

import com.health.bo.clinic.ClinicQueryBo;
import com.health.dao.clinic.ClinicDao;
import com.health.dto.clinic.ClinicDto;
import com.health.entity.clinic.ClinicEntity;
import com.health.service.BaseService;
import com.health.transfer.clinic.ClinicTransfer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

/**
 * @author henry
 */
public class ClinicService extends BaseService {

    @Autowired
    private ClinicDao clinicDao;
    
    /**
     * 条件查询医院列表
     * @param httpHeaders
     * @param queryBo
     * @return
     */
    public List<ClinicTransfer> query(HttpHeaders httpHeaders, ClinicQueryBo queryBo) {
        List<ClinicEntity> clinicEntityList = clinicDao.queryListClinic(queryBo);
        List<ClinicTransfer> clinicTransferList = new ArrayList<>();
        for(ClinicEntity entity : clinicEntityList){
            ClinicTransfer transfer = new ClinicTransfer();
            BeanUtils.copyProperties(entity, transfer);
            clinicTransferList.add(transfer);
        }
        return clinicTransferList;
    }

}
