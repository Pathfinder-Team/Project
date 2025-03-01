<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Register Organisation</title>
<link rel="stylesheet" type="text/css" href="styles/mainstyle.css" />
<link rel="stylesheet" type="text/css" href="styles/forum.css" />
<script type="text/javascript">
            function validateAll()
            {
                var orgname = document.getElementById("organisation_name").value;
                var orgaddress = document.getElementById("organisation_address").value;
                var orgemail = document.getElementById("organisation_email").value;
                var orgmobile = document.getElementById("organisation_mobile").value;
                var orgbuildname = document.getElementById("organisation_building_name").value;

                if (validate_req(orgname, orgaddress, orgemail, orgmobile, orgbuildname) == true)
                    return true;
                else
                    return false;
            }

            function validate_req(orgname, orgaddress, orgemail, orgmobile, orgbuildname)
            {
                if (orgname == null || orgname == "")
                {
                    alert("Please, Enter your Organisation name");
                    return false;
                }
                if (orgaddress == null || orgaddress == "")
                {
                    alert("Please, Enter Organisation Address");
                    return false;
                }
                if (orgemail == null || orgemail == "")
                {
                    alert("Please, Enter Organisation email");
                    return false;
                }
                if (orgmobile == null || orgmobile == "")
                {
                    alert("Please, Enter Organisation mobile");
                    return false;
                }
                if (orgbuildname == null || orgbuildname == "")
                {
                    alert("Please, Enter Building name");
                    return false;
                }

                return true;
            }

        </script>
</head>

<body>
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

						<p id="info">Please fill in this form to register
							Organisation:</p>

						<p>
							<label for="organisation_name" class="title">Organisation
								Name: <span>*</span>
							</label> <input type="text" name="organisation_name"
								id="organisation_name" />
						</p>

						<p>
							<label for="organisation_address" class="title">Organisation
								Address: <span>*</span>
							</label> <input type="text" name="organisation_address"
								id="organisation_address" />
						</p>

						<p>
							<label for="organisation_email" class="title">Organisation
								Email: <span>*</span>
							</label> <input type="email" name="organisation_email"
								id="organisation_email" />
						</p>

						<p>
							<label for="organisation_mobile" class="title">Organisation
								Phone: <span>*</span>
							</label> <input type="number" name="organisation_mobile"
								id="organisation_mobile" />
						</p>

						<p>
							<label for="organisation_building_name" class="title">Building
								Name: <span>*</span>
							</label> <input type="text" name="organisation_building_name"
								id="organisation_building_name" />
						</p>

						<input type="hidden" name="whatAction" id="whatAction"
							value="RegOrg" />

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