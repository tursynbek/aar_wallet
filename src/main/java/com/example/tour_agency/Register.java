package com.example.tour_agency;

import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "Register", value = "/Register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession hs = request.getSession();
        String message;
        String uname=request.getParameter("uname");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String pass = "";





        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            pass = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println(pass);



        Member member = new Member(uname, pass, email, phone);
        RegisterDao rdao = new RegisterDao();
        Boolean result = rdao.insert(member);


        if (result){
            response.sendRedirect("/tour_agency_war_exploded/login.jsp");
        } else {
            message = "Username is already chosen!";
            hs.setAttribute("message", message);
            response.sendRedirect("/tour_agency_war_exploded/registration.jsp");
        }
        response.getWriter().println(result);
    }
}
