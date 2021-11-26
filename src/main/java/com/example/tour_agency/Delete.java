package com.example.tour_agency;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Delete", value = "/Delete")
public class Delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession hs = request.getSession();


        String uname = (String) hs.getAttribute("login");

        System.out.println("Deleting: first step done");
        try{
            RegisterDao rdao = new RegisterDao();

            rdao.delete(uname);

            System.out.println("function delete done");
            response.sendRedirect("/tour_agency_war_exploded/profile.jsp");
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
