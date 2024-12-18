package com.server.config;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.glassfish.jersey.servlet.ServletContainer;

public class AppServer {
    private static final String PACKAGE_SERVER = "com.user.api";
    private static final String APP_NAME = "test";
    public static void crateServer(int port,String connectorName){
        Server server = new Server();
        server.addConnector(createConnector(server,port,APP_NAME));

        server.setHandler(createCollectionHandler(PACKAGE_SERVER,APP_NAME,"/api"));
        try {
            server.start();
            server.join();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("closing server...");
            server.destroy();
        }
    }

    //1
    private static ServerConnector createConnector(Server server,int port,String name){
        ServerConnector connector=new ServerConnector(server);
        connector.setPort(port);
        connector.setName(name);
        return connector;
    }

    //4
    private static HandlerCollection createCollectionHandler(String pkgName,String appName,String contextPath){
        HandlerCollection collection=new HandlerCollection();
        collection.addHandler(createApiContextHandlers(pkgName,appName,contextPath));
        return collection;
    }

    //3
    private static ServletContextHandler createApiContextHandlers(String pkgName, String appName, String contextPath){

        //3.1 create container
        ServletContainer container=new ServletContainer(createResourceConfig(pkgName,appName));

        //3.2 create Holder
        ServletHolder servletHolder =new ServletHolder(container);

        //3.3 add holder to contextHandler
        ServletContextHandler apiContextHandler=new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        apiContextHandler.addServlet(servletHolder,"/v1/*"); // map servlet holder to specific path. see useful : api/v1/* in this pkg

        apiContextHandler.setContextPath(contextPath); // address= ip:port/contextPath

        apiContextHandler.setVirtualHosts(new String[]{"@"+APP_NAME}); //compulsory "@" //this ServletContextHandler host name== connector name

        return apiContextHandler;
    }

    //2
    private static ResourceConfig createResourceConfig(String apiPkgs, String appName){
        ResourceConfig config=new ResourceConfig();
        config.packages(true,"com.user.api");
//        config.setApplicationName(appName); //not important
        config.register(MultiParts.class);
        return config;
    }
}
