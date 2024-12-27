package com.users;

import javax.persistence.*;

//@Entity
//@Table(name="Users")
public class User {

//    @Id
//    @GeneratedValue
    private long id;
    private String username;
    private String email;

    public long getId(){
        return this.id;
    }

//    public void setId(long id){
//        this.id=id;
//    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


//    public static void main(String[] args) {
////        subsStrings("","abc");
////        System.out.println(printNum(10));
////        System.out.println(helper("abc",""));
//        System.out.println(fib(6));
//    }
////    static void subsStrings(String s,String sub){
////
////        if(sub.length()==0){
////            System.out.println(s);
////            return;
////        }
////        subsStrings(s+sub.charAt(0),sub.substring(1));
////        subsStrings(s+"",sub.substring(1));
////
////    }
//    static String helper(String up,String down){
//        if(up.length()==0){
//            return down;
//        }
////        String s1=helper(up.substring(1),down+up.charAt(0));
////        String s2= helper(up.substring(1),""+down);
////        System.out.println(s1);
////        System.out.println(s2);
//        return helper(up.substring(1),down+up.charAt(0)) +";"+ helper(up.substring(1),""+down);
////    return s1+";"+s2;
//    }
//
//    static String printNum(int n){
//        if(n==0)return n+"";
//        return  printNum(n-1)+"-"+n;
//    }
//
//    static int fib(int n){
//        if(n<=1)return n;
//        return fib(n-2)+fib(n-1);
//    }
}
