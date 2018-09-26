package com.health.common;

/**
 * mybatis分页插件进入判断：需要满足以下条件才会有效
 * 1 dao方法要以queryList开始
 * 2 参数包含QueryBo实现类且getPager() != null
 * 3 如果isNeedTotalCount()返回true，会计算totalCount
 * 
 * @author 
 *
 */
public interface QueryBo {
	/**
	 * 获取分页对象
	 * @return
	 */
	Pager getPager();
	
	/**
	 * 是否需要统计TotalCount，应为有些查询是不需要统计总数的
	 * @return
	 */
	default boolean isNeedTotalCount(){
	    return true;
	};
	
}
