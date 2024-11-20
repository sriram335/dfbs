package hs.data.system;

import hs.data.HsDAO;
import hs.data.HsDataSource;

import hs.to.HsCharNav;
import hs.to.HsCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;


public class HsUtilityDAO extends HsDAO {

    public HsUtilityDAO() {
        super();
    }

    public HsUtilityDAO(HsDataSource pool) {
        super(pool);
    }


    public List getOptions(String sql) throws SQLException, Exception {
        List list = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            getOptionsLoop(pstmt, list);
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public List getOptions(String sql, int id) throws SQLException, Exception {
        List list = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, id);
            getOptionsLoop(pstmt, list);
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public List getOptions(String sql, int id, String s) throws SQLException,
                                                                Exception {
        List list = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, id);
            pstmt.setString(2, s);
            getOptionsLoop(pstmt, list);
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public List getOptions(String sql, int id, int s) throws SQLException,
                                                             Exception {
        List list = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, id);
            pstmt.setInt(2, s);
            getOptionsLoop(pstmt, list);
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public List getOptions(String sql, String[] params) throws SQLException,
                                                               Exception {
        List list = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setString(i + 1, params[i]);
                }
            }
            getOptionsLoop(pstmt, list);
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public List getOptions(String sql, String param) throws SQLException,
                                                            Exception {
        List list = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setString(1, param);
            getOptionsLoop(pstmt, list);
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (Exception e) {
            }
        }
        return list;
    }


    public List getCodeMapOptions(String sql,
                                  String[] params) throws SQLException,
                                                          Exception {
        List list = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setString(i + 1, params[i]);
                }
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                HsCode code = new HsCode();
                code.setCode(rs.getString(1));
                code.setLabel(rs.getString(2));
                code.setCodeType(rs.getString(3));
                list.add(code);
            }
            return list;
        } finally {
            try {
                rs.close();
                conn.close();
                pstmt.close();
            } catch (Exception e) {
            }
        }
    }


    public static void execute(Connection conn,
                               String sql) throws SQLException, Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.execute();
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }
        }

    }


    public int getSequence(String sql) throws SQLException, Exception {
        int value = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            try {
                rs.close();
                conn.close();
                pstmt.close();
            } catch (Exception e) {
            }
        }
        return value;
    }

    public List getStringList(String sql) throws SQLException, Exception {
        List list = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } finally {
            try {
                rs.close();
                conn.close();
                pstmt.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public List getStringList(String sql, int id) throws SQLException,
                                                         Exception {
        List list = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } finally {
            try {
                rs.close();
                conn.close();
                pstmt.close();
            } catch (Exception e) {
            }
        }
        return list;
    }


    private void getOptionsLoop(PreparedStatement stmt,
                                List list) throws Exception {
        ResultSet rs = stmt.executeQuery();
        try {
            while (rs.next()) {

                list.add(new LabelValueBean(rs.getString(2), rs.getString(1)));
            }
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
        }
    }

    private boolean checkSelectQuery(String sql, List rows) {
        if (sql == null ||
            (sql.length() < 6 || sql.substring(0, 6).compareToIgnoreCase("SELECT") !=
             0)) {
            List cols = new ArrayList();
            cols.add("Error: Only SELECT query is allowed.");
            rows.add(cols);
            return false;
        } else {
            return true;
        }
    }


    public List getCharacterNavs(String sql) throws Exception {
        List list = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                HsCharNav nav = new HsCharNav();
                nav.setCharacter(rs.getString(1));
                nav.setCount(rs.getInt(2));
                list.add(nav);
            }

        } finally {
            try {
                conn.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public List getCharacterNavs(String sql, String param) throws Exception {
        List list = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, param);
            rs = stmt.executeQuery();
            while (rs.next()) {
                HsCharNav nav = new HsCharNav();
                nav.setCharacter(rs.getString(1));
                nav.setCount(rs.getInt(2));
                list.add(nav);
            }

        } finally {
            try {
                conn.close();
                stmt.close();
                rs.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public boolean testConnection() {
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT SYSDATE FROM DUAL");
            pstmt.clearParameters();
            pstmt.executeQuery();

            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                pstmt.close();
                conn.close();

            } catch (Exception e) {
            }
        }

    }


}


