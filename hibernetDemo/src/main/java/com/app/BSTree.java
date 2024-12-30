package com.app;

import java.util.Scanner;

public class BSTree {
    class Node{
        int val;
        Node left;
        Node right;
        int height;
        public Node(int val){
            this.val=val;
        }
    }
    Node root;
    public static void main(String[] args) {

        BSTree tree=new BSTree();
        tree.createTree();
    }

    void createTree(){

        System.out.println("insert root ");
        Scanner sc=new Scanner(System.in);
        int a= sc.nextInt();
        this.root=new Node(a);
        this.insert(sc,this.root);
        this.display(this.root,0);
        this.insertVal(sc);
        this.display(this.root,0);

    }
    void addRoot(int val){
        this.root=new Node(val);
    }
    public void insert(Scanner sc,Node root){
        System.out.println("do you want to insert after "+root.val);
        boolean b= sc.nextBoolean();
        if(b) {
            int val=sc.nextInt();
            if(val<root.val){
                System.out.println("inserting left");
                root.left = new Node(val);
                insert(sc,root.left);
            }else{
                System.out.println("inserting right");
                root.right = new Node(val);
                insert(sc,root.right);
            }
        }
    }
    void display(Node root,int level){
        if(root==null)return;
        //go to right
        display(root.right,level+1);
        if(level!=0){
            for(int i=0;i<level-1;i++){
                System.out.print("|\t\t");
            }
            System.out.println("|---->"+root.val);
        }else {
            System.out.println(root.val);
        }
        //go to left
        display(root.left,level+1);
    }
    public void insertVal(Scanner sc){
        //decide left or right
        System.out.println("Do you want to insert value?");
        boolean b=sc.nextBoolean();
        if(b){
            int a=sc.nextInt();
            insertNode(root,a);
            insertVal(sc);
        }
    }

    private Node insertNode(Node root,int val){
        if(root==null){
            root=new Node(val);
            return root;
        }
        if(val<root.val){
            //insert left in the end
            root.left=insertNode(root.left,val);
        }else{
            root.right=insertNode(root.right,val);
        }
        return root;
    }

    // no need to insert in between insert at last only
//    private void trevarese(Node root,int val,Node prev){
//        if( root==null){//at leaf node
//
//            if(val<=prev.val){
//                prev.left=new Node(val);
//            }else{
//                prev.right=new Node(val);
//            }
//            return ;
//        }
//        //in the middle ?
//        //insert add new node now
//        // if new node is added in the middle then?
//        if(val>root.val && val<=prev.val){
//             //what if left is null
//                //insert at left
//                Node ref=root;
//                prev.left=new Node(val);
//                prev.left.left=ref;
//                return;
//        } else if (val<prev.val && val>root.val) {
//            Node ref=root;
//            prev.right=new Node(val);
//            prev.right.right=ref;
//            return;
//        }
//        if(val<=root.val){
//            //go to left
//            trevarese(root.left,val,root);
//        }else{
//            //got to right
//            trevarese(root.right,val,root);
//        }
//        return ;
//
//    }
}
