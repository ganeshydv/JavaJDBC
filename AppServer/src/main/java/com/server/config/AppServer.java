package com.server.config;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.glassfish.jersey.servlet.ServletContainer;

public class AppServer {
    private static String PACKAGE_SERVER = "com.user.api";
    private static String APP_NAME = "test";
    private AppServer(){}
    public static void crateServer(int port,String connectorName,String pkgs){
        APP_NAME=connectorName;
        PACKAGE_SERVER=PACKAGE_SERVER+(pkgs==null?"":";"+pkgs.trim());
        System.out.println("Packages : "+PACKAGE_SERVER);
        Server server = new Server();
        server.addConnector(createConnector(server,port,connectorName));

        server.setHandler(createCollectionHandler(PACKAGE_SERVER,connectorName,"/api"));
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

    public static void crateServer(int port,String connectorName,String pkgs, String ContextPath, String subPath){
        APP_NAME=connectorName;
        PACKAGE_SERVER=PACKAGE_SERVER+(pkgs==null?"":";"+pkgs.trim());

        System.out.println("App Name: "+APP_NAME);
        System.out.println("Packages : "+PACKAGE_SERVER);
        Server server = new Server();
        server.addConnector(createConnector(server,port,connectorName));

        server.setHandler(createCollectionHandler(PACKAGE_SERVER,connectorName,ContextPath,subPath));
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
        System.out.println("Connector Name: "+name);
        connector.setName(name);
        return connector;
    }

    //4
    private static HandlerCollection createCollectionHandler(String pkgName,String appName,String contextPath){
        HandlerCollection collection=new HandlerCollection();
        collection.addHandler(createApiContextHandlers(pkgName,appName,contextPath));
        return collection;
    }
    private static HandlerCollection createCollectionHandler(String pkgName,String appName,String contextPath, String subPath){
        HandlerCollection collection=new HandlerCollection();
        collection.addHandler(createApiContextHandlers(pkgName,appName,contextPath, subPath));
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

    private static ServletContextHandler createApiContextHandlers(String pkgName, String appName, String contextPath, String subPath){

        //3.1 create container
        ServletContainer container=new ServletContainer(createResourceConfig(pkgName,appName));

        //3.2 create Holder
        ServletHolder servletHolder =new ServletHolder(container);

        //3.3 add holder to contextHandler
        ServletContextHandler apiContextHandler=new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        apiContextHandler.addServlet(servletHolder,subPath+"/*"); // map servlet holder to specific path. see useful : api/v1/* in this pkg

        apiContextHandler.setContextPath(contextPath); // address= ip:port/contextPath
        System.out.println("path: "+contextPath+subPath);
        System.out.println("Context Handler Name: "+APP_NAME);
        apiContextHandler.setVirtualHosts(new String[]{"@"+APP_NAME}); //compulsory "@" //this ServletContextHandler host name== connector name

        return apiContextHandler;
    }

    //2
    private static ResourceConfig createResourceConfig(String apiPkgs, String appName){
        ResourceConfig config=new ResourceConfig();
        config.packages(true,apiPkgs);
//        config.setApplicationName(appName); //not important
        config.register(MultiParts.class);
        return config;
    }
}
