package hs.data;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.List;


public interface HsPageListSortDAO {
    public String buildSqlList(String filterSql,
                               String orderSql) throws Exception;

    public String buildSqlCount(String filterSql) throws Exception;

    public Connection getConnection() throws Exception;

    public List getList(ResultSet rs) throws Exception;

    public int getMaxPageSize();
}
