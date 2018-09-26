package com.health.common;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Henry
 * @date 2018/8/8 20:34
 */
public abstract class AbstractPageInterceptor implements Interceptor {

    private final Logger logger = LoggerFactory.getLogger(AbstractPageInterceptor.class);

    private static final Pattern PATTERN_SQL_BLANK = Pattern.compile("\\s+");

    private static final String FIELD_DELEGATE = "delegate";

    private static final String FIELD_BOUNDSQL = "boundSql";

    private static final String FIELD_MAPPEDSTATEMENT = "mappedStatement";

    private static final String FIELD_SQL = "sql";

    private static final String BLANK = " ";

    private static final String DOT = ".";

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    private final static String SELECT_ID = "querylist";

    @SuppressWarnings({ "resource" })
    @Override
    public Object intercept(final Invocation invocation) throws Throwable {
        final Connection connection = (Connection) invocation.getArgs()[0];
        final StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        final BoundSql boundSql = statementHandler.getBoundSql();

        final MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
                DEFAULT_OBJECT_WRAPPER_FACTORY);

        final MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
                .getValue(FIELD_DELEGATE + DOT + FIELD_MAPPEDSTATEMENT);

        final String selectId = mappedStatement.getId();
        logger.debug("selectId={}", selectId);

        final String currentSqlId = selectId.substring(selectId.lastIndexOf(".") + 1);
        if (currentSqlId.toLowerCase().lastIndexOf(SELECT_ID) != -1) {

            final Object obj = boundSql.getParameterObject();
            if (obj != null) {
                QueryBo baseQueryBo = null;
                
                if(obj instanceof QueryBo){
                	baseQueryBo = (QueryBo)obj;
                } else {
                    final MapperMethod.ParamMap<Object> paramMap = (MapperMethod.ParamMap<Object>) obj;
                    for (final Entry<String, Object> entry : paramMap.entrySet()) {
                        if (entry.getValue() instanceof QueryBo) {
                            baseQueryBo = (QueryBo) entry.getValue();
                            break;
                        }
                    }
                }

                if (baseQueryBo != null && baseQueryBo.getPager() != null) {
                    final String targetSql = replaceSqlBlank(boundSql.getSql());

                    int totalCount = 0;
                    if(baseQueryBo.isNeedTotalCount()){
                        totalCount = getTotal(connection, mappedStatement, boundSql, targetSql);
                        baseQueryBo.getPager().setTotalCount(totalCount);
                    }
                    
                    final String pagingSql = getSelectPagingSql(targetSql, baseQueryBo.getPager().getStartIndex(),
                            baseQueryBo.getPager().getPageSize());

                    logger.debug("生成分页SQL : {}", pagingSql);
                    metaStatementHandler.setValue(FIELD_DELEGATE + DOT + FIELD_BOUNDSQL + DOT + FIELD_SQL, pagingSql);
                }
            }

        }
        final long start = System.currentTimeMillis();
        final Object obj = invocation.proceed();
        final long executeTime = System.currentTimeMillis() - start;
        if (executeTime > 200) {
            logger.warn("slow sql, consumeTiem={},sql={}",executeTime, boundSql.getSql());
        }
        return obj;
    }

    // 查询总数
    private int getTotal(final Connection connection, final MappedStatement mappedStatement, final BoundSql boundSql,
            final String targetSql) {
        // 通过connection建立一个countSql对应的PreparedStatement对象。
        final String countSQL = getSelectTotalSql(targetSql);
        // 参数的映射集合
        final List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        // 我们查询传入的参数
        final Object parameterObject = boundSql.getParameterObject();
        // 构建新的BoundSql
        final BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSQL, parameterMappings,
                parameterObject);
        final ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject,
                countBoundSql);

        ResultSet rs = null;
        try (PreparedStatement pstmt = connection.prepareStatement(countSQL);) {

            parameterHandler.setParameters(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (final SQLException e) {
            logger.error("分页错误请检查sql:" + targetSql);
            throw new RuntimeException(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (final SQLException e) {
                    logger.error("rs关闭出错", e);
                }
            }
        }
        return 0;
    }

    private String replaceSqlBlank(final String originalSql) {
        final Matcher matcher = PATTERN_SQL_BLANK.matcher(originalSql);
        return matcher.replaceAll(BLANK);
    }

    @Override
    public Object plugin(final Object target) {
        if (target instanceof StatementHandler || target instanceof ResultSetHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    protected abstract String getSelectTotalSql(String targetSql);

    protected abstract String getSelectPagingSql(String targetSql, int offset, int limit);

    @Override
    public void setProperties(final Properties paramProperties) {
    }
}