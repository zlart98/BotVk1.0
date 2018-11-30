package com.example;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;

public class DataBase  {


    final String URL = "jdbc:mysql://localhost:3306/new_schema";
    final String Name = "root";
    final String Pass = "ROOT";
    int idFilm = 0;
    int idGenre = 0;
    String C = "not found";
    String drama = "\"Драма\"";
    String comedy = "\"Комедия\"";
    String fantastic = "\"Фантастика\"";
    String inputLine;
    VkAPI vkAPI;

    void selectFilmFromDB(VkAPI vkAPI) throws SQLException, IOException  {
        this.vkAPI = vkAPI;

        Driver driver = new FabricMySQLDriver();
        DriverManager.registerDriver(driver);
        Connection connection2 = null;
        connection2 = DriverManager.getConnection(URL, Name,Pass);
        PreparedStatement statement = connection2.prepareStatement("SELECT Name FROM film WHERE idFilm=" + idFilm);
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT Name FROM film WHERE idFilm=" + idFilm);

        while (resultSet.next()) {
            C = resultSet.getString("Name");
            System.out.println(C);
        }

                    System.out.println(C);
    java.net.URL obj2 = new URL("https://api.vk.com/method/" + "messages.send?" +
            "&message=" + URLEncoder.encode(C, "UTF-8") +
            "&user_id=" + vkAPI.userID.get(vkAPI.iterator) +
            "&access_token=" + vkAPI.access_token + "&v=5.52");

    HttpURLConnection connection = (HttpURLConnection) obj2.openConnection();
                    connection.setRequestMethod("GET");
    BufferedReader in2 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    String inputLine2;
    StringBuffer response2 = new StringBuffer();
                    while ((inputLine = in2.readLine()) != null) {
        response2.append(inputLine);
                    }
                    in2.close();
    }

    void selectRandomFilmFromDB() throws SQLException, IOException{

        Driver driver = new FabricMySQLDriver();
        DriverManager.registerDriver(driver);
        Connection connection4 = null;
        System.out.println("\"" + vkAPI.Message.get(vkAPI.iterator) + "\"");
        connection4 = DriverManager.getConnection(URL, Name, Pass);
        PreparedStatement statement = connection4.prepareStatement("SELECT name_film FROM " + vkAPI.Message.get(vkAPI.iterator) + " WHERE id_genre=" + idGenre);
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT name_film FROM " + vkAPI.Message.get(vkAPI.iterator) + " WHERE id_genre=" + idGenre);

        while (resultSet.next()) {
            C = resultSet.getString("name_film");
            System.out.println(C);
        }

                    System.out.println(C);
    URL obj4 = new URL("https://api.vk.com/method/" + "messages.send?" +
            "&message=" + URLEncoder.encode(C, "UTF-8") +
            "&user_id=" + vkAPI.userID.get(vkAPI.iterator) +
            "&access_token=" + vkAPI.access_token + "&v=5.52");

    HttpURLConnection connection = (HttpURLConnection) obj4.openConnection();
                    connection.setRequestMethod("GET");
    BufferedReader in4 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    String inputLine4;
    StringBuffer response4 = new StringBuffer();
                    while ((inputLine = in4.readLine()) != null) {
        response4.append(inputLine);
    }
        in4.close();
    }
}
