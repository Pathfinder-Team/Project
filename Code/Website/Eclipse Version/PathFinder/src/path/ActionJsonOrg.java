package path;

/* 
 Authors: Kevin Dunne, Jekaterina Pavlenko
 Date: 7/4/19
 Program: Website for enterprise application development
 */
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



@MultipartConfig(maxFileSize = 16177216) // upto 16 MB
@WebServlet(name = "ActionJsonOrg", urlPatterns = { "/ActionJsonOrg" })
public class ActionJsonOrg extends HttpServlet {
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	Connection conn;
	Connection conn2;
	Statement stmt;
	PreparedStatement prepStat;
	PreparedStatement prepStat1;

	int powerID;
	String powerUsername;
	String powerFirstName;
	String powerLastName;
	String powerPassword;
	String powerEmail;
	int powerStatus;
	ResultSet result;
	ResultSet result1;

	int idRights;
	String userNameRights;
	String passwordRights;
	String emailRights;
	int AccountStatusRights;
	int special;
	String orgNameRights;

	String organisation_name;
	String organisation_address;
	String organisation_email;
	String organisation_mobile;
	String organisation_building_name;
	String user_org_name;

	String org_name;
	String org_building;
	String map_name;
	String map_comments;
	Blob map_image;
	
	getRankPower rp = new getRankPower();
	int counter = 0;
	// declaring 2 arraylists of type OrgNode
	ArrayList<OrgNode> OrgArray = new ArrayList();
	ArrayList<OrgNode> OrgMaps = new ArrayList();
	
	
	
    public void init() throws ServletException
    {

    	SQLConnection connect = new SQLConnection();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // setup the connection with the DB
            conn = DriverManager.getConnection(connect.URL, connect.USERNAME, connect.PASSWORD);
            
            System.out.println("Connected ActionJsonOrg");
        } catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Error 1" + e);
        }
    }

	@SuppressWarnings("unchecked")
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		///////////////////////////////////////////
		rp.getStatusRank(request,response,stmt,conn);
		System.out.println(" rp.getUserNameRights() ActionJsonOrg: "+ rp.getUserNameRights());
		

		try 
		{
			
		prepStat = conn.prepareStatement("select * from organisation");
		result = prepStat.executeQuery();
		// clearing the array so it is always empty when called
		OrgArray.clear();
	    while(result.next())
	    {
	    	// populating the first array
	    	OrgNode orgNode = new OrgNode(result.getString("organisation_name"),
	    			result.getString("organisation_address"),
	    			result.getString("organisation_email"),
	    			result.getString("organisation_mobile"),
	    			result.getString("organisation_building_name"));
	    	OrgArray.add(orgNode);
	    }
	    
		prepStat1 = conn.prepareStatement("select * from maps");
		result1 = prepStat1.executeQuery();
		
		// clearing it so the array is always clean
		OrgMaps.clear();
		// populating the second array
	    while(result1.next())
	    {
	    	System.out.println("map_image: "+result1.getBlob("map_image"));
	    	OrgNode orgNode = new OrgNode(result1.getInt("map_id"),
	    			result1.getString("org_name"),
	    			result1.getString("org_building"),
	    			result1.getString("map_name"),
	    			result1.getString("map_comments"),
	    			result1.getBlob("map_image"));
	    	OrgMaps.add(orgNode);
	    }
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		    System.out.println("Special Json error: "+e);
		}

		// declaring json object and array
	    JSONObject jsonObject = new JSONObject();
	    JSONArray array = new JSONArray();
	    
	    //JSONArray array2 = new JSONArray();
	   // JSONObject record = new JSONObject();
	    
	    int size = OrgArray.size();
	    try
	    {
	    	counter++;
	    	
		    for(int i = 0; i < size;i++)
		    {
			    JSONArray array2 = new JSONArray();
			    JSONObject record = new JSONObject();
			    
			    // adding the records into the json record object
			    record.put("organisation_name", OrgArray.get(i).organisation_name);
			    record.put("organisation_address", OrgArray.get(i).organisation_address);
			    record.put("organisation_email", OrgArray.get(i).organisation_email);
			    record.put("organisation_mobile", OrgArray.get(i).organisation_mobile);
			    record.put("organisation_building_name", OrgArray.get(i).organisation_building_name);
			  
			    for(int j = 0; j < OrgMaps.size();j++)
			    {
			    	// if the organisaiton name in the first array matchs the org name in the second array, this is basicaly 
			    	// like a primary and foreign key
			    	if(OrgArray.get(i).organisation_name.equals(OrgMaps.get(j).org_name))
			    	{
			    		
			    		// add the records to a second json object
			    		JSONObject record2 = new JSONObject();
			    		record2.put("map_id", OrgMaps.get(j).map_id);
					    record2.put("org_name", OrgMaps.get(j).org_name);
					    record2.put("org_building", OrgMaps.get(j).org_building);
					    record2.put("map_name", OrgMaps.get(j).map_name);
					    record2.put("map_comments", OrgMaps.get(j).map_comments);
					    Blob specialBlob = OrgMaps.get(j).map_image;
				        byte [] bytes = specialBlob.getBytes(1l, (int)specialBlob.length());
				        String encoded = Base64.getEncoder().encodeToString(bytes);
				        record2.put("map_image", encoded);
				        
				        
					    // add object record to second array
				    	array2.add(record2);
			    	}
			    	// add the second array to the record
			    	record.put("map_details",array2);
			    }
			    // add total record to array
			    array.add(record);
		    }
		    // each array is a set of values
		    jsonObject.put("org_details", array);  
		    response.setContentType("application/json");
		    PrintWriter out = response.getWriter();	      
		    out.println(jsonObject.toJSONString()+"\n");  
		    }
		
	    catch (Exception e) 
	    {
	    	e.printStackTrace();
	    	System.out.println("Special Json error: "+e);
		}
	    
	}
	//response.sendRedirect("Maps.jsp");

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
