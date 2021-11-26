<%--
  Created by IntelliJ IDEA.
  User: arman
  Date: 03.10.2021
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>aar wallet | sign up</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>


<%

    HttpSession hs = request.getSession();
    String message  = (String) hs.getAttribute("message");
    if(message==null){
        message = "";
    }
%>


<%-------------------------------------------------------------------------------------------------%>
<div class="content">
    <span>
        <em class="user_name"><%=message%></em>
    </span>
    <div>
        <div>
            <form class="login100-form validate-form" action="Register" method="post">
					<span>
						Registration
					</span>

                <div data-validate = "Username is required">
                    <span class="label-input100">Username</span>
                    <input class="input100" type="text" name="uname" placeholder="Type your username">
                    <span class="focus-input100" data-symbol="&#xf206;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Password is required">
                    <span class="label-input100">Password</span>
                    <input class="input100" type="password" name="password" placeholder="Type your password">
                    <span class="focus-input100" data-symbol="&#xf190;"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-23" data-validate = "Email is required">
                    <span class="label-input100">Email:</span>
                    <input class="input100" type="email" name="email" placeholder="Type your email">
                    <span class="focus-input100" data-symbol="&#xf206;"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-23" data-validate = "amount is required">
                    <span class="label-input100">Amount</span>
                    <input class="input100" type="number" name="phone" placeholder="Amount">
                    <span class="focus-input100" data-symbol="&#xf206;"></span>
                </div>

                <div>
                    <a href="#">
                        Forgot password?
                    </a>
                </div>

                <div>
                    <div>

                        <button class="login100-form-btn" name="register" type="submit">
                            Sign Up
                        </button>
                    </div>
                </div>



                <div class="flex-col-c p-t-155">
						<span class="txt1 p-b-17">
							Or Login Using
						</span>

                    <a href="/tour_agency_war_exploded/login.jsp" class="txt2">
                        Login
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>




</body>
</html>
