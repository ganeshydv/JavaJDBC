package com.user.service;

import com.database.config.UserDatabase;
import com.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserService {
    //get user data
    private static final UserService userSerViceInstance=new UserService();
    private UserService(){

    }
    public static UserService getInstance(){
        return userSerViceInstance;
    }
    public ResultSet getAllUserData(){
        try {
            UserDatabase userDbInstance =UserDatabase.getDbInstance();
            Connection dbConnection = userDbInstance.crateDatabaseConnection();
            Statement statement= dbConnection.createStatement();
            ResultSet users = statement.executeQuery("SELECT id,name FROM users");
            return users;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public User createUser(User user){
        try {
            UserDatabase userDbInstance =UserDatabase.getDbInstance();
            Connection connection = userDbInstance.crateDatabaseConnection();
            String sql = "INSERT INTO users (name, email,password,created_at, updated_at) VALUES (?, ?, ?,CURRENT_DATE(), CURRENT_DATE())";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Step 3: Set the values for the query parameters
//            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getEmail()); //change psd in POJO as well
//            preparedStatement.setString(4, user.getEmail()); //date

            // Step 4: Execute the query
            int rowsInserted = preparedStatement.executeUpdate();
            return user;
        }catch (Exception e){
            System.out.println("error while creating user....");
            e.printStackTrace();
            throw new Error("User not crated");
        }
//        return null;
    }
}
