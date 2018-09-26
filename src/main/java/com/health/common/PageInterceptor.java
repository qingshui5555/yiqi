package com.health.common;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;

/**
 * @author Henry
 * @date 2018/8/8 20:34
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }))
public class PageInterceptor extends AbstractPageInterceptor  {
	
	@Override
	protected String getSelectTotalSql(final String targetSql) {
		
		StringBuilder sqlBuilder = new StringBuilder(targetSql.replaceAll("(?i)" + " FROM ", " from ").replaceAll("(?i)" + " order by ", " order by "));
		if (targetSql.lastIndexOf(" order by ") > targetSql.lastIndexOf(")")) {
			sqlBuilder = new StringBuilder(sqlBuilder.substring(sqlBuilder.indexOf(" from "), sqlBuilder.lastIndexOf(" order by ")));
		}
		
		final StringBuilder result = new StringBuilder(" select count(1) ");
		result.append(sqlBuilder.substring(sqlBuilder.indexOf(" from ")));
		return result.toString();
		
	}
	
	@Override
	protected String getSelectPagingSql(final String targetSql, final int offset, final int limit) {
		final String tmp = targetSql.replaceAll("(?i)" + " limit ", " limit ");
		if (tmp.lastIndexOf(" limit ") > tmp.lastIndexOf(")")) {
			return tmp;
		}
		final StringBuilder sqlBuilder = new StringBuilder(tmp);
		sqlBuilder.append(" LIMIT ").append(" " + offset).append(" , ").append(limit + " ");
		return sqlBuilder.toString();
	}
}
