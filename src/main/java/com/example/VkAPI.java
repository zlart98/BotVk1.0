package com.example;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class VkAPI {

     static final String access_token = "";

     static final String getMessage = "https://api.vk.com/method/" +
            "messages.getDialogs?" +
            "&unread=1" + "&access_token=" + access_token + "&v=5.52";

     static String line = "";

    List<String> Message = new ArrayList<String>();
    List<String> userID = new ArrayList<String>();
    int iterator = 0;

    BotVK botVK;
    DataBase dataBase ;

    void  getMessage(BotVK botVK, DataBase dataBase) throws IOException, SQLException, InterruptedException {
        this.botVK = botVK;
        this.dataBase = dataBase;
        for (; ; ) {
            URL url = new URL(getMessage);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            line = reader.readLine();
            reader.close();


            JSONObject object = new JSONObject(line);
            JSONObject responseObj = object.getJSONObject("response");

            JSONArray arr = responseObj.getJSONArray("items");
            for (int i = 0; i < arr.length(); i++) {
                Message.add(String.valueOf(arr.getJSONObject(i).getJSONObject("message").getString("body")));
                userID.add(String.valueOf(arr.getJSONObject(i).getJSONObject("message").getInt("user_id")));
            }

            sendMessage(botVK, dataBase);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(" ");

        }
    }




    void sendMessage(BotVK botVK , DataBase dataBase) throws IOException, InterruptedException, SQLException {



        for (iterator = 0; iterator < Message.size(); iterator++) {
            System.out.println(Message.get(iterator));
           dataBase.idGenre = (int) (Math.random() * 19);
            dataBase.idFilm = (int) (Math.random() * 222);
            int random_id  = (int) (Math.random() * 222);

            if ("Привет".equals(Message.get(iterator))) {
                URL obj = new URL("https://api.vk.com/method/" + "messages.send?" + "&random_id=" + random_id +
                        "&message=" + URLEncoder.encode(botVK.greeting, "UTF-8") +
                        "&user_id=" + userID.get(iterator) +
                        "&access_token=" + access_token + "&v=5.52");

                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

            } else if ("Посоветуй фильм".equals(Message.get(iterator))) {
                String inputLine;
                URL obj1 = new URL("https://api.vk.com/method/" + "messages.send?" +
                        "&message=" + URLEncoder.encode(botVK.message1, "UTF-8") +
                        "&user_id=" + userID.get(iterator) +
                        "&access_token=" + access_token + "&v=5.52");

                HttpURLConnection connection1 = (HttpURLConnection) obj1.openConnection();
                connection1.setRequestMethod("GET");
                BufferedReader in1 = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
                String inputLine1;
                StringBuffer response1 = new StringBuffer();
                while ((inputLine = in1.readLine()) != null) {
                    response1.append(inputLine);
                }
                in1.close();

            } else if ("Любой жанр".equals(Message.get(iterator)) || "еще".equals(Message.get(iterator))) {
                dataBase.selectFilmFromDB(this);
            } else if ("Драма".equals(Message.get(iterator)) || "Комедия".equals(Message.get(iterator)) || "Фантастика".equals(Message.get(iterator))) {
                dataBase.selectRandomFilmFromDB();
            } else {
                String inputLine1;
                URL obj3 = new URL("https://api.vk.com/method/" + "messages.send?" +
                        "&message=" + URLEncoder.encode(botVK.message2, "UTF-8") +
                        "&user_id=" + userID.get(iterator) +
                        "&access_token=" + access_token + "&v=5.52");

                HttpURLConnection connection3 = (HttpURLConnection) obj3.openConnection();
                connection3.setRequestMethod("GET");
                BufferedReader in3 = new BufferedReader(new InputStreamReader(connection3.getInputStream()));
                StringBuffer response3 = new StringBuffer();

                while ((inputLine1 = in3.readLine()) != null) {
                    response3.append(inputLine1);
                }
                in3.close();
            }
            }
        }


    }


