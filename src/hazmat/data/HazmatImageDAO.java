package hazmat.data;

import hazmat.to.HazmatImage;
import hs.data.HsDAO;

import java.sql.*;

public class HazmatImageDAO extends HsDAO {
    private Connection connection;

    public HazmatImageDAO(Connection connection) {
        this.connection = connection;
    }

    public int insertImage(HazmatImage image) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            int id = this.getId(conn, HazmatPermitSQL.SELECT_NEXT_IMG_ID);
            image.setImage_Id(id);

            pstmt.setString(2, image.getName());
            pstmt.setString(3, image.getImageType());
            pstmt.setBlob(4, image.getImage());
            pstmt.setDate(5, image.getEffectiveDate());
            pstmt.setString(6, image.getCreatedBy());
            pstmt.setDate(7, image.getCreatedDate());

            pstmt.execute();

            conn.commit();
            return id;
        } catch (Exception ex) {
            conn.rollback();
            throw new Exception(ex.getMessage());
        } finally {
            try {
                conn.close();
                pstmt.close();
            } catch (Exception e) {}
        }
    }

    public HazmatImage getImageById(int imageId) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT image_Id, name, imageType, image, effectiveDate, createdBy, createdDate FROM hazmat_images WHERE image_Id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, imageId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                HazmatImage image = new HazmatImage();
                image.setImage_Id(rs.getInt("image_Id"));
                image.setName(rs.getString("name"));
                image.setImageType(rs.getString("imageType"));
                image.setImage(rs.getBlob("image"));
                image.setEffectiveDate(rs.getDate("effectiveDate"));
                image.setCreatedBy(rs.getString("createdBy"));
                image.setCreatedDate(rs.getDate("createdDate"));
                return image;
            } else {
                throw new Exception("Image with ID " + imageId + " not found");
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception e) {}
            if (pstmt != null) try { pstmt.close(); } catch (Exception e) {}
            if (conn != null) try { conn.close(); } catch (Exception e) {}
        }
    }
}
