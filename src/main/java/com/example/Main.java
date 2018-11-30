package com.example;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        BotVK botVK = new BotVK();
        VkAPI vkAPI = new VkAPI();
        DataBase dataBase = new DataBase();
        try {
            botVK.communication(vkAPI,dataBase);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
