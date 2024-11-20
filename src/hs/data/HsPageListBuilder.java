package hs.data;

import hs.util.HsPageList;

import java.lang.reflect.Field;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;


public class HsPageListBuilder {
    //WHY NOT EDIT THIS AND ADD A STRING VERSION?

    public static HsPageList selectList(HsPageListDAO dao, Class sqlClass,
                                        String filterType, String param,
                                        String fromIndex) throws SQLException,
                                                                 Exception {

        if (param == null) {
            return selectList2(dao, sqlClass, filterType, null, fromIndex);
        } else {
            String[] p = { param };
            return selectList2(dao, sqlClass, filterType, p, fromIndex);
        }
    }


    public static HsPageList selectList2(HsPageListDAO dao, Class sqlClass,
                                         String filterType, String[] param,
                                         String fromIndex) throws SQLException,
                                                                  Exception {


        HsPageList pageList = new HsPageList();
        pageList.setMaxPageSize(dao.getMaxPageSize());

        //if(param == null || param.length == 0)
        //{
        //  return pageList;
        //}

        int toIndex = Integer.parseInt(fromIndex) + dao.getMaxPageSize() - 1;

        pageList.setIndex(Integer.parseInt(fromIndex));

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            String filterBySql = null;
            if (sqlClass != null && filterType != null) {
                Field filterByField = sqlClass.getDeclaredField(filterType);
                filterBySql = (String)filterByField.get(new String());
            } else {
                filterBySql = "";
            }

            conn = dao.getConnection();
            pstmt = conn.prepareStatement(dao.buildSqlCount(filterBySql));

            pstmt.clearParameters();
            int paramCount = 0;
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setString(++paramCount, param[i]);
                }
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pageList.setListSize(rs.getInt(1));
            }

            pstmt = conn.prepareStatement(dao.buildSqlList(filterBySql));
            pstmt.clearParameters();
            paramCount = 0;
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setString(++paramCount, param[i]);
                }
            }


            if (toIndex > pageList.getListSize()) {
                pstmt.setInt(++paramCount, pageList.getListSize());
            } else {
                pstmt.setInt(++paramCount, toIndex);
            }
            pstmt.setString(++paramCount, fromIndex);


            rs = pstmt.executeQuery();
            List list = dao.getList(rs);

            pageList.setPageList(list);

            return pageList;
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (Exception e) {
            }
        }

    }

    public static HsPageList selectList(HsPageListSortDAO dao, Class sqlClass,
                                        String filterType, String orderType,
                                        String param,
                                        String fromIndex) throws SQLException,
                                                                 Exception {

        if (param == null) {
            return selectList2(dao, sqlClass, filterType, orderType, null,
                               fromIndex);
        } else {
            String[] p = { param };
            return selectList2(dao, sqlClass, filterType, orderType, p,
                               fromIndex);
        }
    }


    public static HsPageList selectList2(HsPageListSortDAO dao, Class sqlClass,
                                         String filterType, String orderType,
                                         String[] param,
                                         String fromIndex) throws SQLException,
                                                                  Exception {

        HsPageList pageList = new HsPageList();
        pageList.setMaxPageSize(dao.getMaxPageSize());
        int toIndex = Integer.parseInt(fromIndex) + dao.getMaxPageSize() - 1;
        pageList.setIndex(Integer.parseInt(fromIndex));

        if (param == null || param.length == 0) {

            return pageList;
        }
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            String filterBySql = null;
            String orderBySql = null;
            if (sqlClass != null && filterType != null) {
                Field filterByField = sqlClass.getDeclaredField(filterType);
                filterBySql = (String)filterByField.get(new String());
            } else {
                filterBySql = "";
            }

            if (orderType != null) {
                Field orderByField = sqlClass.getDeclaredField(orderType);
                orderBySql = (String)orderByField.get(new String());
            }


            conn = dao.getConnection();
            pstmt = conn.prepareStatement(dao.buildSqlCount(filterBySql));

            pstmt.clearParameters();
            int paramCount = 0;
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setString(++paramCount, param[i]);
                }
            }

            rs = pstmt.executeQuery();

            if (rs.next()) {
                pageList.setListSize(rs.getInt(1));
            }

            pstmt =
conn.prepareStatement(dao.buildSqlList(filterBySql, orderBySql));
            pstmt.clearParameters();
            paramCount = 0;
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setString(++paramCount, param[i]);
                }
            }


            if (toIndex > pageList.getListSize()) {
                pstmt.setInt(++paramCount, pageList.getListSize());
            } else {
                pstmt.setInt(++paramCount, toIndex);
            }
            pstmt.setString(++paramCount, fromIndex);


            rs = pstmt.executeQuery();

            List list = dao.getList(rs);

            pageList.setPageList(list);

            return pageList;
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (Exception e) {
            }
        }

    }


    //ALL THE REST SHOULD BE DEPRECATED

    public static HsPageList getList(HsPageListDAO dao, String filterSql,
                                     String[] param,
                                     String fromIndex) throws SQLException,
                                                              Exception {


        HsPageList pageList = new HsPageList();
        pageList.setMaxPageSize(dao.getMaxPageSize());

        if (param == null || param.length == 0) {
            return pageList;
        }

        int toIndex = Integer.parseInt(fromIndex) + dao.getMaxPageSize() - 1;

        pageList.setIndex(Integer.parseInt(fromIndex));

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            conn = dao.getConnection();
            pstmt = conn.prepareStatement(dao.buildSqlCount(filterSql));

            pstmt.clearParameters();
            int paramCount = 0;
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setString(++paramCount, param[i]);
                }
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pageList.setListSize(rs.getInt(1));
            }

            pstmt = conn.prepareStatement(dao.buildSqlList(filterSql));
            pstmt.clearParameters();
            paramCount = 0;
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setString(++paramCount, param[i]);
                }
            }


            if (toIndex > pageList.getListSize()) {
                pstmt.setInt(++paramCount, pageList.getListSize());
            } else {
                pstmt.setInt(++paramCount, toIndex);
            }
            pstmt.setString(++paramCount, fromIndex);


            rs = pstmt.executeQuery();
            List list = dao.getList(rs);

            pageList.setPageList(list);

            return pageList;
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (Exception e) {
            }
        }

    }


}
