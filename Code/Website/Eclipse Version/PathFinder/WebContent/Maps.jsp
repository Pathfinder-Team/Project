<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@page import="path.getRankPower"%>
<%@page import="path.SQLConnection"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Organization Page JSP</title>
<link rel="stylesheet" type="text/css" href="styles/mainstyle.css" />
<link rel="stylesheet" type="text/css" href="styles/forum.css" />
</head>
<body>

	<%
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Connection conn = null;
		Connection conn2 = null;
		Statement stmt = null;
		PreparedStatement prepStat = null;
		ArrayList<String> mapNames = new ArrayList<>();
		ArrayList<Integer> mapNamesId = new ArrayList<>();
		ArrayList<String> buildingNames = new ArrayList<>();

		ResultSet result;

		getRankPower rp = new getRankPower();

		String organisation_name = "";
		String organisation_address = "";
		String organisation_email = "";
		String organisation_mobile = "";
		String organisation_building_name = "";
		String user_org_name = "";
		int imageId = 0;
		String selected = "";
	        try
	        {
	        	SQLConnection connect = new SQLConnection();
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            // setup the connection with the DB
	            conn = DriverManager.getConnection(connect.URL(), connect.USERNAME(), connect.PASSWORD());
	            
	            System.out.println("Connected Maps.jsp");
	        } catch (ClassNotFoundException | SQLException e)
	        {
	            System.err.println("Error 1" + e);
	        }
	   

		///////////////////////////////////////////
		rp.getStatusRank(request,response,stmt,conn);
		//System.out.println(" Maps.jsp: "+ rp.getUserNameRights());

		try {
			//System.out.println("get org details");
			//orgNameRights = "Limerick Institute of Technology";
			prepStat = conn.prepareStatement("select * from organisation where organisation_name = ?");
			prepStat.setString(1, rp.getOrgRights());
			result = prepStat.executeQuery();
			while (result.next()) {
				organisation_name = result.getString("organisation_name");
				organisation_address = result.getString("organisation_address");
				organisation_email = result.getString("organisation_email");
				organisation_mobile = result.getString("organisation_mobile");
				organisation_building_name = result.getString("organisation_building_name");
				if(!buildingNames.contains(organisation_building_name))
				{
					buildingNames.add(organisation_building_name);
				}
			}

		} catch (SQLException ex) {
			System.err.println("Error Org: " + ex);
		}
		
		try {
			prepStat = conn.prepareStatement("select map_id,map_name from maps where org_name = ?");
			prepStat.setString(1, rp.getOrgRights());
			result = prepStat.executeQuery();
		
			while (result.next()) {
				mapNames.add(result.getString("map_name"));
				mapNamesId.add(result.getInt("map_id"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error 10 ae: " + e);
		}
		
	%>
	<div id="container">
		<header>
			<img src="images/bn_header.png" alt="">
		</header>
		<nav id="menu">
			<ul>
				<li><a href="index.html">ABOUT US</a></li>
				<li><a href="register.html">REGISTER</a></li>
				<li><a href="login.html">LOGIN</a></li>
				<li><a href="contact.html">CONTACT US</a></li>
			</ul>
		</nav>
		<main>
			<section id="form">
				<ul class="sign_login">
					<li><a href="DetailsDB">DETAILS</a></li>
					<li><a href="Maps.jsp" class="current">MAPS</a></li>
					<li><a href="LogOutDB">LOG OUT</a></li>
					<li><a href="ControlDB">Control</a></li>
				</ul>
				<br> <br>
				<p>Instructions:</p>
				<ol>
					<li><p>Upload a Map</p></li>
					<li><p>Add Map point</p></li>
					<li><p>Add the connections between Point A and Point B</p></li>
				</ol>

				<form action="UploadMapDB" method="post" class="">
					<button type="submit">Upload A New Map</button>
				</form>

				<br>

				<h5>Select an Image from the dropdown below and display it</h5>
				<form action="Maps.jsp">
					<select name="imageName">
						<option value="ground">SELECT</option>
						<%
							for (int i = 0; i < mapNames.size(); i++) 
							{
								String imageName = (String) mapNames.get(i);
						%>
						<option value="<%=imageName%>">
							<%=imageName%>
						</option>
						<%
						//System.out.println("value: "+imageName);
						}
						
						%>
					</select> <input type="Submit" value="Submit"> <br> <br>
				</form>
				<%
					selected = request.getParameter("imageName");
				%>
				<img
					src="GetImageAction?map_name=<%=selected%>&org_name=<%=organisation_name%>"
					alt="" height="350px" width="300px"> <br> <br>
				<p>
					Building Name:<%=organisation_building_name%>
					<br>

				</p>
				<form action="AddPointsDB" method="post">
					<input type="hidden" id="maps_map_id" name="maps_map_id"
						value="<%=selected%>"> <input type="hidden"
						id="organisation_name" name="organisation_name"
						value="<%=organisation_name%>"> <input type="hidden"
						id="organisation_building_name" name="organisation_building_name"
						value="<%=organisation_building_name%>">
					<button type="submit">Add Map Nodes and Points</button>
				</form>
				<br>
				<h5>Select a Building from the drop down below and view it's
					map points</h5>
				<form action="ViewPointsDB" method="post">
					<select name="organisation_building_name">
						<option value="">SELECT</option>
						<%
				for (int i = 0; i < buildingNames.size(); i++) 
				{
					String buildingName = (String) buildingNames.get(i);
				%>
						<option value="<%=buildingName%>">
							<%=buildingName%>
						</option>
						<%
				}	
				%>
					</select> <input type="hidden" id="organisation_name"
						name="organisation_name" value="<%=organisation_name%>">

					<button type="submit">View Map Detail's</button>
				</form>
				<br> <br>
			</section>
		</main>
	</div>
	<footer>
		<p>PathFinder project 2019</p>
		<p>Authors: Kevin Dunne, Jekaterina Pavlenko & Christopher
			Costelloe</p>
		<p>
			<img src="images/maze_ic.png" alt="">
		</p>
	</footer>
</body>
</html>