<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@page import="path.getRankPower"%>
<%@page import="path.SQLConnection"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Register User</title>
<link rel="stylesheet" type="text/css" href="styles/mainstyle.css" />
<link rel="stylesheet" type="text/css" href="styles/forum.css" />
<script type="text/javascript">
            function validateAll()
            {
                var fname = document.getElementById("fname").value;
                var lname = document.getElementById("lname").value;
                var orgname = document.getElementById("orgname").value;
                var username = document.getElementById("username").value;
                var email = document.getElementById("email").value;
                var password1 = document.getElementById("password1").value;
                var password2 = document.getElementById("password2").value;

                if (validate_req(fname, lname, orgname, username, email, password1, password2) == true &&
                        pass_length(password1) == true &&
                        passwords_match(password1, password2) == true)
                    return true;
                else
                    return false;
            }
            function validate_req(fname, lname, orgname, username, email, password1, password2)
            {
                if (fname == null || fname == "")
                {
                    alert("Please, Enter your first name");
                    return false;
                }
                if (lname == null || lname == "")
                {
                    alert("Please, Enter your last name");
                    return false;
                }
                if (orgname == null || orgname == "")
                {
                    alert("Please, Enter Organisation name");
                    return false;
                }
                if (username == null || username == "")
                {
                    alert("Please, Enter your username");
                    return false;
                }
                if (email == null || email == "")
                {
                    alert("Please, Enter your email");
                    return false;
                }
                if (password1 == null || password1 == "")
                {
                    alert("Please, Enter your password");
                    return false;
                }
                if (password2 == null || password2 == "")
                {
                    alert("Please, Repeat password");
                    return false;
                }

                return true;
            }
            function pass_length(password1) {
                if (password1.length < 8) {
                    alert("Password must have at least 8 characters in it");
                    return false;
                }
                return true;
            }

            function passwords_match(password1, password2) {
                if (password2 != password1) {
                    alert("Passwords are not match");
                    return false;
                }
                return true;
            }
        </script>
</head>

<body>
	<%
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement prepStat = null;
		ArrayList<String> specialArray = new ArrayList<>();

		ResultSet result;
		getRankPower rp = new getRankPower();

		String org_name = "";
	        try
	        {
	        	SQLConnection connect = new SQLConnection();
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            // setup the connection with the DB
	            conn = DriverManager.getConnection(connect.URL(), connect.USERNAME(), connect.PASSWORD());
	            
	            System.out.println("Connected");
	        } catch (ClassNotFoundException | SQLException e)
	        {
	            System.err.println("Error 1" + e);
	        }
		rp.getStatusRank(request,response,stmt,conn);
		System.out.println("registerUser.jsp: "+ rp.getUserNameRights());

		try {
			prepStat = conn.prepareStatement("select organisation_name from organisation");
			result = prepStat.executeQuery();
			while (result.next()) {
				org_name = result.getString("organisation_name");
				specialArray.add(org_name);
			}

		} catch (SQLException ex) {
			System.err.println("Error Org: " + ex);
		}
	%>
	<div id="container">
		<header>
			<img src="images/bn_header.png" alt="PathFinder banner">
		</header>
		<nav id="menu">
			<ul>
				<li><a href="index.html">ABOUT US</a></li>
				<li><a href="register.html" class="current">REGISTER</a></li>
				<li><a href="login.html">LOGIN</a></li>
				<li><a href="contact.html">CONTACT US</a></li>
			</ul>
		</nav>
		<main>

			<section id="form">

				<h1>Register</h1>

				<form action="RegisterDB" method="post" name="form"
					onSubmit="return validateAll();">
					<fieldset>

						<p id="info">Please fill in this form to create an account:</p>

						<p>
							<label for="first_name" class="title">First Name: <span>*</span></label>
							<input type="text" name="first_name" id="first_name" />
						</p>

						<p>
							<label for="last_name" class="title">Last Name: <span>*</span></label>
							<input type="text" name="last_name" id="last_name" />
						</p>

						<!-- 	
                            <p><label for="organisation_name" class="title">Organisation name: <span>*</span></label>
                            <input type="text" name="organisation_name" id="organisation_name" /></p>
                             -->
						<p>
							<label for="organisation_name" class="title">Organisation
								name: <span>*</span>
							</label> <select name="organisation_name">
								<option value="">SELECT</option>
								<%
								for (int i = 0; i < specialArray.size(); i++) 
								{
									String organisation_name = (String) specialArray.get(i);
							%>
								<option value="<%=organisation_name%>">
									<%=organisation_name%>
								</option>
								<%
							}
							
							%>
							</select>
						</p>

						<p>
							<label for="user_name" class="title">Username: <span>*</span></label>
							<input type="text" name="user_name" id="user_name" />
						</p>

						<p>
							<label for="email" class="title">Email: <span>*</span></label> <input
								type="email" name="email" id="email" />
						</p>

						<p>
							<label for="password" class="title">Password: <span>*</span></label>
							<input type="password" name="password" id="password" />
						</p>

						<input type="hidden" name="whatAction" id="whatAction"
							value="RegUser" />

						<p>
							<label for="password2" class="title">Retype-Password <span>*</span></label>
							<input type="password" name="password" id="password2"
								onkeyup="passwords_match();" />
						</p>
						<p>
							<input type="submit" name="submit" id="submit"
								value="Submit Details" />
						</p>

					</fieldset>
				</form>
			</section>
			<br> <br>
		</main>
		<footer>
			<p>PathFinder project 2019</p>
			<p>Authors: Kevin Dunne, Jekaterina Pavlenko &amp; Christopher
				Costelloe</p>
			<p>
				<img src="images/maze_ic.png" alt="Maze icon">
			</p>
		</footer>
	</div>
</body>
</html>