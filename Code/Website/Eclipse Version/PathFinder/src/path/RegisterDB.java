package path;
/* 
Authors: Kevin Dunne, Jekaterina Pavlenko, Christopher Costelloe
Date: 11/12/2019
Program: PathFinder website application - Registration servlet
 */
import java.sql.*;
import javax.servlet.ServletException;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "RegisterDB", urlPatterns =
{
    "/RegisterDB"
})
public class RegisterDB extends HttpServlet
{

    String first_name;
    String last_name;
    String user_name;
    String password;
    String email;
    String organisation_name;
    Connection conn;
    PreparedStatement prepStat;
    Statement stat;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public void init() throws ServletException
    {

    	SQLConnection connect = new SQLConnection();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // setup the connection with the DB
            conn = DriverManager.getConnection(connect.URL, connect.USERNAME, connect.PASSWORD);
            
            System.out.println("Connected");
        } catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Error 1" + e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
    	
    	String whatAction = request.getParameter("whatAction");
    	
    	if("RegOrg".equals(whatAction))
    	{
    		System.out.println("Reg Org ");
            // get the values from the register.html form and store in these 
    		String organisation_name = request.getParameter("organisation_name");
    		String organisation_address = request.getParameter("organisation_address");
    		String organisation_email = request.getParameter("organisation_email");
    		String organisation_mobile = request.getParameter("organisation_mobile");
    		String organisation_building_name = request.getParameter("organisation_building_name");
    		
    		System.out.println("organisation_name: "+organisation_name);
    		System.out.println("organisation_address: "+organisation_address);
    		System.out.println("organisation_email: "+organisation_email);
    		System.out.println("organisation_mobile: "+organisation_mobile);
    		System.out.println("organisation_building_name: "+organisation_building_name);

            try
            {
                // create a query that inserts all form values into the users table
                String query = "insert into organisation values(?, ?, ?, ?, ?)";
                prepStat = conn.prepareStatement(query);
                prepStat.setString(1, organisation_name);
                prepStat.setString(2, organisation_address);
                prepStat.setString(3, organisation_email);
                prepStat.setString(4, organisation_mobile);
                prepStat.setString(5, organisation_building_name);
                prepStat.executeUpdate();
                
                response.sendRedirect("login.html");

            } catch (IOException | SQLException e)
            {

                System.err.println("Error registerOrg " + e);
                response.sendRedirect("registerOrg.html");
                
            }
    	}
    	else if(("RegUser").equals(whatAction))
    	{
    		System.out.println("Reg User");
	        // get the values from the register.html form and store in these
	        user_name = request.getParameter("user_name");
    	    first_name = request.getParameter("first_name");
	        last_name = request.getParameter("last_name");
	        password = request.getParameter("password");
	        email = request.getParameter("email");
	        organisation_name = request.getParameter("organisation_name");
	        
	        try
	        {
	            // create a query that inserts all form values into the users table
	            String query = "insert into users values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            prepStat = conn.prepareStatement(query);
	            prepStat.setInt(1, 0);
	            prepStat.setString(2, user_name);
	            prepStat.setString(3, first_name);
	            prepStat.setString(4, last_name);
	            prepStat.setString(5, password);
	            prepStat.setString(6, email);
	            prepStat.setTimestamp(7, timestamp);
	            prepStat.setString(8, organisation_name);
	            prepStat.setInt(9, 2);
	            prepStat.executeUpdate();
	            
	            response.sendRedirect("login.html");
	
	        } catch (IOException | SQLException e)
	        {
	
	        	response.sendRedirect("registerOrg.jsp");
	            System.err.println("Error RegUser " + e);
	        }
    	}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
