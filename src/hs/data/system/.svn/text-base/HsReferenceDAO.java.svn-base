package hs.data.system;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class HsReferenceDAO {


    public static void insert(Connection conn, String sql, int parentId,
                              int childId, String type,
                              String by) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, parentId);
            pstmt.setInt(2, childId);
            pstmt.setString(3, type);
            pstmt.setString(4, by);
            pstmt.execute();
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }
        }
    }

    public static void delete(Connection conn, String sql, int parentId,
                              int childId, String type) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, parentId);
            pstmt.setInt(2, childId);
            pstmt.setString(3, type);
            pstmt.execute();
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }
        }
    }


    public static void delete(Connection conn, String sql, int parentId,
                              int childId) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, parentId);
            pstmt.setInt(2, childId);
            pstmt.execute();
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }
        }
    }

    public static void delete(Connection conn, String sql,
                              int parentId) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, parentId);
            pstmt.execute();
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }
        }
    }
}
