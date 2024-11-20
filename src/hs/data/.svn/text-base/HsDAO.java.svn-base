package hs.data;

import hs.to.HsTypeValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Iterator;
import java.util.List;


public abstract class HsDAO {
    private HsDataSource pool;

    protected HsDAO() {
        setPool(null);
    }

    protected HsDAO(HsDataSource newPool) {
        setPool(newPool);
    }

    protected HsDataSource getPool() {
        return pool;
    }

    protected void setPool(HsDataSource newPool) {
        this.pool = newPool;
    }

    public Connection getConnection() throws Exception {
        Connection conn = pool.getConnection();

        conn.setAutoCommit(true);
        return conn;
    }

    protected static int getId(Connection conn, String sql) throws Exception {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } finally {
            try {
                pstmt.close();
                rs.close();
            } catch (Exception e) {
            }
        }
        return 0;

    }

    public static String buildSql(String prefixSql, List filterList,
                                  String orderSql) {
        if (prefixSql == null)
            return null;

        StringBuffer sql = new StringBuffer(prefixSql);

        if (filterList == null) {
            return sql.toString();

        } else {

            Iterator i = filterList.iterator();
            while (i.hasNext()) {
                HsTypeValue typeValue = (HsTypeValue)i.next();
                if (typeValue.getType() != null) {
                    sql.append(typeValue.getType());
                }
            }


        }
        if (orderSql != null) {
            sql.append(orderSql);
        }

        return sql.toString();

    }

    public static void setStatementParameters(PreparedStatement pstmt,
                                              List filterList) throws Exception {
        if (pstmt == null)
            return;
        pstmt.clearParameters();
        if (filterList == null) {
            return;

        } else {

            Iterator i = filterList.iterator();
            int ii = 0;
            while (i.hasNext()) {
                HsTypeValue typeValue = (HsTypeValue)i.next();
                if (typeValue.getValue() != null) {
                    pstmt.setString(++ii, typeValue.getValue());
                }
            }
        }

    }
    
    public HsDatabaseConnection getHsDatabaseConnection() throws Exception {
        
        return pool.getHsDatabaseConnection();
        
    }


}
