package dev.suki;

import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Image {
    private String linkImageMemes;
    private String descriptionImageMemes;

    public static boolean insertImageLink(String url, String description){
        String sql = "INSERT INTO memes (link_image, description_image) VALUES (?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, url);
            pstmt.setString(2, description);

            int rowsAffected = pstmt.executeUpdate();
            //retorna verdadeiro caso bem sucedido
            return  rowsAffected > 0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Nullable
    public static String getRandomMeme(){
        String sql = "SELECT link_image FROM memes ORDER BY RAND() LIMIT 1";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){
            if (rs.next()){
                return rs.getString("link_image");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
