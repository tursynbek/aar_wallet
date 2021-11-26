

<%@ page import="com.example.tour_agency.RegisterDao" %>

<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%--
  Created by IntelliJ IDEA.
  User: arman
  Date: 03.10.2021
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>aar wallet | my page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

      <%
          HttpSession hs = request.getSession();
          String uname = (String) hs.getAttribute("login");
          String password = (String) hs.getAttribute("password");


          RegisterDao rdao = new RegisterDao();



          com.example.tour_agency.Member member = rdao.getMember(uname, password);
          int bank = member.getPhone();
          String email = member.getEmail();


          if(uname == null){
              response.sendRedirect("/tour_agency_war_exploded/login.jsp");
          }





      %>
      <div class="content">
          <div class="header">
              <span >
                  Welcome <em class="user_name"> <%=uname%> </em>! Make life easier with aar wallet!
              </span><br>

              <span >
						Your email: <%=email%>
              </span>
              <br>

              <span >
						Bank amount: <%=bank%>
              </span>
              <br>
              <a href="/tour_agency_war_exploded/login.jsp">Logout</a>

              <br>
              <hr>
          </div>


              <%-- -------------------------------START FORM INPUT TRANSFER----------------------------          --%>
            <div class="form-popup" id="myForm">
                      <form action="Profile" method="post" class="form-container">
                      <span>
						Money transfer
                      </span>

                      <div  data-validate = "Username is required">
                          <span >AAR Username</span>
                          <input type="text" name="touser" placeholder="Type username of receiver" required>
                          <span data-symbol="&#xf206;"></span>
                      </div>

                      <div data-validate="Amount is required">
                          <span >Amount</span>
                          <input  type="number" name="amount" placeholder="Type amount" required>
                          <span  data-symbol="&#xf190;"></span>
                      </div>

                      <div>
                          <a href="#">
                              FAQ
                          </a>
                      </div>

                      <div>
                          <div>

                              <button type="submit" class="btn cancel">
                                  Send money
                              </button>

                              <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
                          </div>
                      </div>

                </form>
            </div>

            <button class="open-button" id="myButton" onclick="openForm()">
              Transfer Money
            </button>
                  <%-- ----------------------- END FORM INPUT TRANSFER-------------------     --%>

                  <hr>
                  <br>

                  <div class="transaction-history">
                      <h3>Sent</h3>
                      <table>

                          <tr>
                              <th>Sender</th>
                              <th>Receiver</th>
                              <th>Amount</th>
                              <th>Date</th>
                          </tr>

                          <%

                              rdao.loadDriver(rdao.dbdriver);
                              Connection con = rdao.getConnection();
                              try{
                                  Statement s = con.createStatement();
                                  ResultSet rs = s.executeQuery("select * from transaction where sender="+"'"+uname+"'");
                                  while(rs.next()){

                          %>

                          <tr>
                              <td><%=rs.getString(1)%></td>
                              <td><%=rs.getString(2)%></td>
                              <td><%=rs.getInt(3)%></td>
                              <td><%=rs.getDate(4)%></td>
                          </tr>

                          <%
                                  }
                              }catch(Exception e){
                                  System.out.println(e);
                              }


                          %>
                      </table>

                      <form action="<%=request.getContextPath()%>/Delete" method="post">
                          <button type="submit" class="delete-button">Delete history</button>
                      </form>


                  </div>

                  <div class="transaction-history">
                      <h3>Income</h3>
                      <table>

                          <tr>
                              <th>Sender</th>
                              <th>Receiver</th>
                              <th>Amount</th>
                              <th>Date</th>
                          </tr>

                          <%


                              try{
                                  Statement s = con.createStatement();
                                  ResultSet rs = s.executeQuery("select * from transaction where receiver="+"'"+uname+"'");
                                  while(rs.next()){

                          %>

                          <tr>
                              <td><%=rs.getString(1)%></td>
                              <td><%=rs.getString(2)%></td>
                              <td><%=rs.getInt(3)%></td>
                              <td><%=rs.getDate(4)%></td>
                          </tr>

                          <%
                                  }
                              }catch(Exception e){
                                  System.out.println(e);
                              }

                              con.close();
                          %>
                      </table>
                      <form action="<%=request.getContextPath()%>/DeleteIncome" method="post">
                          <button type="submit" class="delete-button">Delete history</button>
                      </form>

                  </div>

      </div>
</body>
</html>

<script>
    function openForm() {
        document.getElementById("myForm").style.display = "block";
        document.getElementById("myButton").style.display = "none";
    }

    function closeForm() {
        document.getElementById("myForm").style.display = "none";
        document.getElementById("myButton").style.display = "block";
    }

</script>