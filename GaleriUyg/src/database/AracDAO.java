package database;

import models.Arac;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AracDAO {
    private Connection connection;

    public AracDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/galeri", "postgres", "aferinhuso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void aracEkle(String marka, String model, int yil, int km, String aciklama) {
        String sql = "INSERT INTO araclar (marka, model, yil, km, aciklama) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, marka);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, yil);
            preparedStatement.setInt(4, km);
            preparedStatement.setString(5, aciklama);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Arac> araclariGetir() {
        List<Arac> araclar = new ArrayList<>();
        String sql = "SELECT * FROM araclar";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                araclar.add(new Arac(
                        resultSet.getInt("id"),
                        resultSet.getString("marka"),
                        resultSet.getString("model"),
                        resultSet.getInt("yil"),
                        resultSet.getInt("km"),
                        resultSet.getString("aciklama")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return araclar;
    }

    public Arac aracGetirByMarkaModel(String marka, String model) {
        String sql = "SELECT * FROM araclar WHERE marka = ? AND model = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, marka);
            preparedStatement.setString(2, model);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Arac(
                        resultSet.getInt("id"),
                        resultSet.getString("marka"),
                        resultSet.getString("model"),
                        resultSet.getInt("yil"),
                        resultSet.getInt("km"),
                        resultSet.getString("aciklama")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void aracSil(int id) {
        String sql = "DELETE FROM araclar WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Arac araciGetir(int id) {
        String sql = "SELECT * FROM araclar WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Arac(
                        resultSet.getInt("id"),
                        resultSet.getString("marka"),
                        resultSet.getString("model"),
                        resultSet.getInt("yil"),
                        resultSet.getInt("km"),
                        resultSet.getString("aciklama")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void araciGuncelle(int id, String marka, String model, int yil, int km, String aciklama) {
        String sql = "UPDATE araclar SET marka = ?, model = ?, yil = ?, km = ?, aciklama = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, marka);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, yil);
            preparedStatement.setInt(4, km);
            preparedStatement.setString(5, aciklama);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
