package hazmat.data;

import com.lowagie.text.Image;
import hazmat.to.HazmatImage;
import hs.data.HsDAO;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.*;

public class HazmatImageDAO extends HsDAO {

    public Image getImageById(String imageName) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT image_id, name, image_type, image FROM hazmat_images WHERE name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, imageName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                HazmatImage image = new HazmatImage();
                image.setImageId(rs.getInt("image_id"));
                image.setName(rs.getString("name"));
                image.setImageType(rs.getString("image_type"));
                image.setImage(rs.getBlob("image"));

                Blob blob = image.getImage();
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();

                    Image finalImage = Image.getInstance(imageBytes);

                    System.out.println("Loaded Image " + image);
                    System.out.println("Image width: " + finalImage.getWidth());
                    System.out.println("Image height: " + finalImage.getHeight());

                    inputStream.close();
                    outputStream.close();
                }

            } else {
                throw new Exception("Image with ID " + imageName + " not found");
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ignored) {}
            if (pstmt != null) try { pstmt.close(); } catch (Exception ignored) {}
            if (conn != null) try { conn.close(); } catch (Exception ignored) {}
        }
    }
}
