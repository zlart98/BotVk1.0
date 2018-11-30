package com.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class BotVK {

      final String greeting ="Привет!Чем могу быть полезен?";
      final String message1 = "В каом жанре?";
      final String message2 = "Неверная команда";
      VkAPI vkAPI ;
      DataBase dataBase;

    void communication(VkAPI vkAPI, DataBase dataBase) throws InterruptedException, IOException, SQLException {
        this.vkAPI = vkAPI;
        this.dataBase = dataBase;

        vkAPI.getMessage(this,dataBase);






    }
}
