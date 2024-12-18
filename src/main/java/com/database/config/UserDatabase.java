package com.database.config;

import java.sql.*;

public class UserDatabase {

    Connection connection=null;
    Statement statement;
    ResultSet resultSet;

    private  final String DB_USER="root";
    private  final  String DB_USER_URL="jdbc:mysql://localhost:3306/user_login_db";
    private final String DB_USER_PASSWORD="root123";
    private  final  int DB_USER_PORT=3306;

    private  static  final  UserDatabase db=new UserDatabase();
    private  UserDatabase(){}
    public  static UserDatabase getDbInstance(){
        return db;
    }

    public Connection crateDatabaseConnection(){
        String url=DB_USER_URL; String user=DB_USER; String password=DB_USER_PASSWORD;
        if(this.connection!=null){
            return connection;
        }
        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("db connected successfully....");
            return connection;
        }catch (SQLException err) {
            System.out.println("DB connection failed...");
            err.printStackTrace();

        }finally {
            // don't close connection as it can be used again
//            try {
//                if (resultSet != null) resultSet.close();
//                if (statement != null) statement.close();
//                if (connection != null) connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            return null;
        }
    }

}
