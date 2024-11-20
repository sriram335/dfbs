package hazmat.data;

import hazmat.to.HazmatImage;
import hs.data.HsDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HazmatImageDAO extends HsDAO {
    private Connection connection;

    public HazmatImageDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveImage(HazmatImage image) throws SQLException {
        String sql = "INSERT INTO DFBS_IMAGES (id, name, image) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, image.getId());
        statement.setString(2, image.getName());
        statement.setBlob(3, image.getImage());
        statement.executeUpdate();
        statement.close();
    }

    public HazmatImage getImageById(int id) throws SQLException {
        String sql = "SELECT * FROM DFBS_IMAGES WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        HazmatImage image = null;
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            Blob img = resultSet.getBlob("image");
            image = new HazmatImage(id, name, img);
        }
        resultSet.close();
        statement.close();
        return image;
    }

    public List<HazmatImage> getAllImages() throws SQLException {
        List<HazmatImage> images = new ArrayList<HazmatImage>();
        String sql = "SELECT * FROM DFBS_IMAGES";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Blob img = resultSet.getBlob("image");
            images.add(new HazmatImage(id, name, img));
        }
        resultSet.close();
        statement.close();
        return images;
    }
}
