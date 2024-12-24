package com.userB.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Path("")
public class UserAPI {


    @Path("subset2")
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

    @Path("tree")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response Btree(){
        return Response.ok().entity("").build();
    }
    class  Btree{

    }
    class Node{
        int val;
        Node left;
        Node right;
    }
}
