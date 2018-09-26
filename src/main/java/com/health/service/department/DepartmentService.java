package com.health.service.department;

import com.health.bo.DepartmentQueryBo;
import com.health.dao.department.DepartmentDao;
import com.health.entity.department.DepartmentEntity;
import com.health.service.BaseService;
import com.health.transfer.department.DepartmentTransfer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

/**
 * @author henry
 */
public class DepartmentService extends BaseService {

    @Autowired
    private DepartmentDao departmentDao;
	
	/**
	 * 条件查询医院列表
     * @param httpHeaders
	 * @param queryBo
	 * @return
	 */
    public List<DepartmentTransfer> query(HttpHeaders httpHeaders, DepartmentQueryBo queryBo) {
        List<DepartmentEntity> departmentEntityList = departmentDao.queryListDepartment(queryBo);
        List<DepartmentTransfer> departmentTransferList = new ArrayList<>();
        for(DepartmentEntity entity : departmentEntityList){
			  DepartmentTransfer transfer = new DepartmentTransfer();
			  BeanUtils.copyProperties(entity, transfer);
			  departmentTransferList.add(transfer);
        }
        return departmentTransferList;
    }

}
