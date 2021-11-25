package com.example.tour_agency;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Profile", value = "/Profile")
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession hs = request.getSession();
        String touser = request.getParameter("touser");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String uname = (String) hs.getAttribute("login");

        System.out.println("First step done");
        try{
            RegisterDao rdao = new RegisterDao();

            rdao.transaction(uname, touser, amount);
            rdao.insert_history(uname, touser, amount);
            System.out.println("function transaction done");
            response.sendRedirect("/tour_agency_war_exploded/profile.jsp");
        } catch(Exception e){
            System.out.println(e);
        }







    }
}
