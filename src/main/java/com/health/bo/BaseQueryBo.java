package com.health.bo;

import com.health.common.Pager;
import com.health.common.QueryBo;

/**
 * @author henry
 */
public abstract class BaseQueryBo implements QueryBo {
    /**
     * 主键ID
     */
    private Long primaryKey;
    /**
     * 分页对象
     */
    private Pager pager;
    /**
     * 是否要统计数据总数
     */
    private boolean needTotalCount = true;
    
    public BaseQueryBo(){
    
    }
    
    public BaseQueryBo(int pageSize, int currentPage){
        pager = new Pager(pageSize, currentPage);
    }
    
    public BaseQueryBo(int pageSize, int currentPage, boolean needTotalCount){
        pager = new Pager(pageSize, currentPage);
        this.needTotalCount = needTotalCount;
    }
    
    public Long getPrimaryKey() {
        return primaryKey;
    }
    
    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    @Override
    public boolean isNeedTotalCount(){
        return needTotalCount;
    };
    
    @Override
    public Pager getPager() {
        return pager;
    }
    
    public void setPager(Pager pager) {
        this.pager = pager;
    }
    
    public void setNeedTotalCount(boolean needTotalCount) {
        this.needTotalCount = needTotalCount;
    }
}
