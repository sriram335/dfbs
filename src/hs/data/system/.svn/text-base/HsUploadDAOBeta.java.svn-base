package hs.data.system;

import hs.data.HsDAO;
import hs.data.HsDataSource;




import hs.to.HsTransferObject;
import hs.to.HsUpload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


public class HsUploadDAOBeta extends HsDAO {
    public HsUploadDAOBeta() {
        super();
    }

    public HsUploadDAOBeta(HsDataSource pool) {
        super(pool);
    }


    public int insert(String sqlSequence, String sql,HsUpload upload,
                       String by) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            return insert(conn, sqlSequence,sql, upload, by);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }

    public static int  insert(Connection conn, String sequenceSql,String sql, HsUpload upload,
                              String by) throws Exception {

        PreparedStatement pstmt = null;
        int id = 0;
        try {
        
            id = getId(conn,sequenceSql);
        
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, id);
            pstmt.setInt(2, upload.getParentId());
            pstmt.setString(3, upload.getFilename());
            pstmt.setString(4, upload.getNotes());
            pstmt.setString(5, upload.getType());
            if(upload.getLinkId() == 0) {
                pstmt.setString(6, null);
            } else {
                pstmt.setInt(6, upload.getLinkId());                
            }
            pstmt.setString(7, by);
            pstmt.execute();
            
            return id;
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }
        }
        
    }

    public void update(String sql, HsUpload upload,
                       String by) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            update(conn, sql, upload, by);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

    }

    public static void update(Connection conn, String sql, HsUpload upload,
                              String by) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, upload.getParentId());
            pstmt.setString(2, upload.getFilename());
            pstmt.setString(3, upload.getNotes());
            pstmt.setString(4, upload.getType());
            if(upload.getLinkId() == 0) {
                pstmt.setString(5, null);
            } else {
                pstmt.setInt(5, upload.getLinkId());                
            }
            pstmt.setString(6, by);
            pstmt.setInt(7, upload.getId());
            pstmt.execute();
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }
        }

    }

    public static void delete(Connection conn, String sql,
                              int param) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, param);
            pstmt.execute();
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }
        }

    }

    public void delete(String sql, int param) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            delete(conn, sql, param);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

    }

    public HsUpload select(String sql, int id) throws Exception {
        HsUpload upload  = new HsUpload();
        upload.setId(id);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                upload.setParentId(rs.getInt(1));
                upload.setFilename(rs.getString(2));
                upload.setNotes(rs.getString(3));
                upload.setType(rs.getString(4));
                upload.setLinkId(rs.getInt(5));
                upload.setCreatedBy(rs.getString(6));
                upload.setCreatedDate(rs.getDate(7));                              
                upload.putDisplayMap("CREATED_DATE",HsTransferObject.getDateString(upload.getCreatedDate(),"MM/dd/yy"));
                
            }
        } finally {
            try {
                rs.close();
                conn.close();
                pstmt.close();
            } catch (Exception e) {
            }
        }

        return upload;
    }

    public List selectList(String sql, String[] param) throws Exception {
        List list = new ArrayList();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setString(i + 1, param[i]);
                }
            }
            rs = pstmt.executeQuery();

            while (rs.next()) {

                HsUpload upload = new HsUpload();
                upload.setId(rs.getInt(1));
                upload.setParentId(rs.getInt(2));
                upload.setFilename(rs.getString(3));
                upload.setNotes(rs.getString(4));
                upload.setType(rs.getString(5));
                upload.setLinkId(rs.getInt(6));
                upload.setCreatedBy(rs.getString(7));
                upload.setCreatedDate(rs.getDate(8));
                upload.putDisplayMap("CREATED_DATE",HsTransferObject.getDateString(upload.getCreatedDate(),"MM/dd/yy"));
                
                list.add(upload);
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


    public List selectList(String sql, int param) throws Exception {
        List list = new ArrayList();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, param);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                HsUpload upload = new HsUpload();
                upload.setId(rs.getInt(1));
                upload.setParentId(rs.getInt(2));
                upload.setFilename(rs.getString(3));
                upload.setNotes(rs.getString(4));
                upload.setType(rs.getString(5));
                upload.setLinkId(rs.getInt(6));
                upload.setCreatedBy(rs.getString(7));
                upload.setCreatedDate(rs.getDate(8));
                upload.putDisplayMap("CREATED_DATE",HsTransferObject.getDateString(upload.getCreatedDate(),"MM/dd/yy"));
                
                list.add(upload);
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

    public static List selectList(Connection conn, String sql,
                                  String[] param) throws Exception {
        List list = new ArrayList();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setString(i + 1, param[i]);
                }
            }
            rs = pstmt.executeQuery();

            while (rs.next()) {

                HsUpload upload = new HsUpload();
                upload.setId(rs.getInt(1));
                upload.setParentId(rs.getInt(2));
                upload.setFilename(rs.getString(3));
                upload.setNotes(rs.getString(4));
                upload.setType(rs.getString(5));
                upload.setLinkId(rs.getInt(6));
                upload.setCreatedBy(rs.getString(7));
                upload.setCreatedDate(rs.getDate(8));
                upload.putDisplayMap("CREATED_DATE",HsTransferObject.getDateString(upload.getCreatedDate(),"MM/dd/yy"));
                
                list.add(upload);
            }
        } finally {
            try {
                rs.close();
                pstmt.close();
            } catch (Exception e) {
            }
        }

        return list;
    }

    public static List selectList(Connection conn, String sql,
                                  int param) throws Exception {
        List list = new ArrayList();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, param);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                HsUpload upload = new HsUpload();
                upload.setId(rs.getInt(1));
                upload.setParentId(rs.getInt(2));
                upload.setFilename(rs.getString(3));
                upload.setNotes(rs.getString(4));
                upload.setType(rs.getString(5));
                upload.setLinkId(rs.getInt(6));
                upload.setCreatedBy(rs.getString(7));
                upload.setCreatedDate(rs.getDate(8));
                upload.putDisplayMap("CREATED_DATE",HsTransferObject.getDateString(upload.getCreatedDate(),"MM/dd/yy"));
                
                list.add(upload);
            }
        } finally {
            try {
                rs.close();
                pstmt.close();
            } catch (Exception e) {
            }
        }

        return list;
    }

    private void updateCount(Connection conn, String sql, int id,
                             int count) throws Exception {

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.clearParameters();
            pstmt.setInt(1, count);
            pstmt.setInt(2, id);
            pstmt.execute();

        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }
        }

    }

    public void insert(String sqlSequence,String sql, HsUpload upload, String sqlCount,
                       int count, String by) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            insert(conn,sqlSequence, sql, upload, by);

            updateCount(conn, sqlCount, upload.getParentId(), count);

            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }

}
