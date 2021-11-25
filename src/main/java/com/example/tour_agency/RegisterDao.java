package com.example.tour_agency;

import java.sql.*;
import java.util.Date;
import java.util.Objects;

public class RegisterDao {
    private String dburl="jdbc:postgresql://localhost:5432/userdb";
    private String dbuname= "postgres";
    private String dbpass="Medina2018";
    public String dbdriver="org.postgresql.Driver";

    public void loadDriver(String dbdriver){
        try {
            Class.forName(dbdriver);
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL driver is not found!");
            e.printStackTrace();

        }
    }

    public Connection getConnection(){
        Connection con=null;
        try {
            con=DriverManager.getConnection(dburl, dbuname, dbpass);
        } catch (SQLException e ){
            System.out.println("connection failed");
            e.printStackTrace();
        }
        return con;
    }

    public String insert(Member member){
        loadDriver(dbdriver);
        Connection con=getConnection();
        String result="data entered successfully";
        String sql="insert into member values(?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, member.getUname());
            ps.setString(2, member.getPass());
            ps.setString(3, member.getEmail());
            ps.setInt(4, member.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            result = "Error: Data not entered!";
        }
        return result;
    }

    public Member getMember(String usname, String pass){

        Member member = new Member();
        loadDriver(dbdriver);
        Connection con = getConnection();
        try{
            Statement s = con.createStatement();
//            ps.setString(1, uname);
//            ps.setString(2, pass);




            ResultSet rs = s.executeQuery("select * from member where uname="+"'"+usname+"'");

            while(rs.next()){
                member.setEmail(rs.getString(3));
                member.setPhone(rs.getInt(4));
            }

            con.close();

        }catch(Exception e){
            System.out.println(e);
        }

        return member;
    }

    public void transaction(String uname, String touser, int amount){
        loadDriver(dbdriver);
        Connection con = getConnection();
//        String uname1 = null;
//        try {
//            String query3 = ("SELECT * from member WHERE uname=?");
//            Statement s = con.createStatement();
//            ResultSet rs = s.executeQuery(query3);
//
//
//            while(rs.next()){
//                uname1 = rs.getString(1);
//            }
//            System.out.println(uname1);
//
//
//        }catch(Exception e){
//            System.out.println(e);
//        }
//
//        if(Objects.equals(uname1, "null")){
//            System.out.println("Invalid username");
//
//        }



        String query = ("UPDATE member SET phone=phone+? WHERE uname=?");
        String query2 = ("UPDATE member SET phone=phone-? WHERE uname=?");
        try(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, amount);
            ps.setString(2,touser);
            ps.executeUpdate();

        } catch(Exception e){
            System.out.println(e);
        }

        try(PreparedStatement ps = con.prepareStatement(query2)){
            ps.setInt(1, amount);
            ps.setString(2, uname);
            ps.executeUpdate();
            con.close();
        } catch(Exception e){
            System.out.println(e);
        }

    }

    public String insert_history(String uname, String touser, int amount){
        loadDriver(dbdriver);
        Connection con=getConnection();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        System.out.println(date);
        System.out.println(sqlDate);
        String result="data entered successfully";
        String sql="insert into transaction values(?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setString(2, touser);
            ps.setInt(3, amount);
            ps.setDate(4, sqlDate);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            result = "Error: Data not entered!";
        }
        return result;
    }

    public boolean validate(LoginBean lb) throws  ClassNotFoundException{
        boolean status = false;
        loadDriver(dbdriver);
        Connection con= getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("select * from member where uname=? and password=?");
            ps.setString(1, lb.getUsername());
            ps.setString(2, lb.getPass());

            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            status = rs.next();



        }catch(Exception e){
            System.out.println(e);
        }
        return status;
    }
}
