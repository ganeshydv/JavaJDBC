package com.user.api;
import com.database.config.UserDatabase;
import com.user.User;
import com.user.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class UserAPI {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        try{
            UserService user=UserService.getInstance();
            ResultSet resultSet= user.getAllUserData();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
            return "Hello, world! .....................";
        }catch (Exception e){
            System.out.println("error fetching data...");
            e.printStackTrace();
        }
        return "No Data";

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON) //request
    @Produces(MediaType.APPLICATION_JSON) //response
    public Response createUser(User user){
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        UserService service=UserService.getInstance();
        try{
            service.createUser(user);
        }catch (Error e){
            return Response.serverError().entity("{\"message\": \"Error While creating user...\"}")
                    .build();
        }

        return Response.ok().entity("{\"message\": \"User created successfully!\"}")
                .build();
    }

    @Path("subset")
    @GET()
    @Produces(MediaType.TEXT_PLAIN)
    public Response createSubset(){
        int[] nums=new int[]{1,2,3};
        List<List<Integer>> outer=new ArrayList<>();
        outer.add(new ArrayList<>());
        for(int num:nums){
            int n=outer.size();
            for(int i=0;i<n;i++){
                // i=0; outer:[{}] --> inner: {} : {1} --> outer [{},{1}]
                //i=0; outer [{},{1}] --> inner {} :{2} --> outer  [{},{1},{2}]
                //i=1; outer  [{},{1},{2}] --> inner {1}: {1,2} --> outer  [{},{1},{2},{1,2}]
                List<Integer> inner=new ArrayList<>(outer.get(i));
                inner.add(num);
                outer.add(inner);
            }
        }

        return Response.ok().entity("{\"message\":"+ outer.toString()+"}").build();
    }
}