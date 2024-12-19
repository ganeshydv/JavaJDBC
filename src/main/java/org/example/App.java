package org.example;

import com.database.config.UserDatabase;
import com.server.config.AppServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("connecting to db....");
        UserDatabase database = UserDatabase.getDbInstance();
        database.crateDatabaseConnection();

        System.out.println("starting server...." );
        int port=3000;
        String handlerName="api";
        AppServer.crateServer(port,handlerName,null);
    }
}
