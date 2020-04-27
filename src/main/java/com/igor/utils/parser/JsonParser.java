package com.igor.utils.parser;
import com.igor.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonParser {
    private static final Logger LOGGER = LogManager.getLogger(JsonParser.class);
    private static JSONObject jsonObject;

    static {
        try {
            jsonObject = (JSONObject) (new JSONParser().parse(new FileReader("src/main/resources/data.json")));
        } catch (IOException | ParseException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static Object[][] getUsers(){
        int numberOfUsers = getNumberOfUsers();
        Object[][] objects = new Object[numberOfUsers][];
        for (int i = 0; i < numberOfUsers; i++) {
            objects[i] = new Object[]{getUserName(i), getPassword(i)};
        }
        return objects;
    }

    public static Object[][] getUsersForLoginTest(){
        int numberOfUsers = getNumberOfUsers();
        Object[][] objects = new Object[numberOfUsers][];
        objects[0] = new Object[]{"tralala@gmail.com", "password"};
        objects[0] = new Object[]{getUserName(0), "password"};
        objects[0] = new Object[]{getUserName(0), getPassword(0)};
        return objects;
    }

    public static User getValidUser(){
        return new User(getUserName(0), getPassword(0));
    }

    private static String getUserName(int index){
        return (String) ((JSONArray)((JSONObject)jsonObject.get("users")).get("email_address")).get(index);
    }

    private static int getNumberOfUsers(){
        return ((JSONArray)((JSONObject)jsonObject.get("users")).get("email_address")).size();
    }

    private static String getPassword(int index){
        return (String) ((JSONArray)((JSONObject)jsonObject.get("users")).get("passwords")).get(index);
    }

    public static String getIncorrectReceiver(){
        return (String) ((JSONObject)jsonObject.get("receivers")).get("incorrect_receiver");
    }

    public static String getReceiver(){
        return (String) ((JSONObject)jsonObject.get("receivers")).get("receiver");
    }

    public static String getMessage(){
        JSONArray jsonArray = (JSONArray) jsonObject.get("messages");
        return (String) jsonArray.get((int)(jsonArray.size()*Math.random()));
    }
}
