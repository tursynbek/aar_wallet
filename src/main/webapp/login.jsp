<%--
  Created by IntelliJ IDEA.
  User: arman
  Date: 03.10.2021
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>aar wallet | login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<%--<form action="<%=request.getContextPath()%>/Login" method="post">--%>
<%--<table>--%>
<%--    <tr>--%>
<%--        <td>Login: </td><td><input type="text" name="login"></td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <td>password: </td><td><input type="text" name="password"></td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <td>--%>

<%--        </td>--%>
<%--        <td>--%>
<%--            <input type="submit">--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--</table>--%>
<%--    <a href="/registration.jsp">Registration</a>--%>
<%--</form>--%>


<%------------------------------------------------------------%>

<div class="content">
    <div>
        <div>
            <form action="<%=request.getContextPath()%>/Login" method="post">
					<span>
						Login
					</span>

                <div  data-validate = "Username is required">
                    <span >Username</span>
                    <input  type="text" name="login" placeholder="Type your username">
                    <span data-symbol="&#xf206;"></span>
                </div>

                <div  data-validate="Password is required">
                    <span class="label-input100">Password</span>
                    <input class="input100" type="password" name="password" placeholder="Type your password">
                    <span class="focus-input100" data-symbol="&#xf190;"></span>
                </div>

                <div >
                    <a href="#">
                        Forgot password?
                    </a>
                </div>

                <div>
                    <div>
                        <div></div>
                        <button type="submit">
                            Login
                        </button>
                    </div>
                </div>


                <div>
						<span class="txt1 p-b-17">
							Or Sign Up Using
						</span>

                    <a href="/tour_agency_war_exploded/registration.jsp" class="txt2">
                        Sign Up
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>



</body>
</html>
