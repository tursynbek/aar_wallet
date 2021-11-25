package com.example.tour_agency;



import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegisterDao rdao = new RegisterDao();
        String username = request.getParameter("login");
        String password = request.getParameter("password");

        Member member = rdao.getMember(username, password);
        int bank = member.getPhone();
        String email = member.getEmail();


        HttpSession hs = request.getSession();
        hs.setAttribute("login", username);
        hs.setAttribute("password", password);
        hs.setAttribute("bank", bank);
        hs.setAttribute("email", email);






        String pass = "";

        //int pass = (password + username).hashCode();

        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
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


        LoginBean lb = new LoginBean();
        lb.setUsername(username);
        lb.setPassword(pass);

        try {
            if(rdao.validate(lb)){
                response.sendRedirect("/tour_agency_war_exploded/profile.jsp");
            }else {
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Username or password is invalid!');");
                out.println("</script>");
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
