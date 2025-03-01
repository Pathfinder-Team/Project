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

@WebServlet(name = "LogOutDB", urlPatterns =
{
    "/LogOutDB"
})
public class LogOutDB extends HttpServlet
{

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Connection conn;
    Connection conn2;
    Statement stmt;
    PreparedStatement prepStat;

    String URL = "jdbc:mysql://localhost:3306/";
    String DB = "mydb";
    String USERNAME = "root";
    String PASSWORD = "";

    @Override
    public void init() throws ServletException
    {

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // setup the connection with the DB
            conn = DriverManager.getConnection(URL + DB, USERNAME, PASSWORD);
            System.out.println("Connected");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Error 1" + e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        // create a cookie array
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            stmt = conn.createStatement();
            // display a html page for the log out
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<!doctype html>\n"
                    + "<!-- Author: Jekaterina Pavlenko K00224431\n"
                    + "         Date: 09/03/2019\n"
                    + "         Project Forum Page\n"
                    + "-->\n"
                    + "<html lang=\"en\">\n"
                    + "    <head>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <title>Comments</title>\n"
                    + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"styles/mainstyle.css\" />\n"
                    + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"styles/forum.css\" />\n"
                    + "    </head>\n"
                    + "\n"
                    + "    <body>\n"
                    + "        <div id=\"container\">\n"
                    + "            <header>\n"
                    + "                <img src=\"images/img3.jpg\" >\n"
                    + "            </header>\n"
                    + "            <nav id=\"menu\">\n"
                    + "                <ul>\n"
                    + "                    <li><a href=\"index.html\" >About Us</a></li>\n"
                    + "                    <li><a href=\"register.html\">Register</a></li>\n"
                    + "                    <li><a href=\"login.html\" >LOGIN</a></li>\n"
                    + "                    <li><a href=\"forum.html\">FORUM</a></li>\n"
                    + "                    <li><a href=\"contact.html\" >CONTACT</a></li>\n"
                    + "                    <li><a href=\"ControlDB\" >CONTROL</a></li>\n"
                    + "                </ul>\n"
                    + "            </nav>"
                    + "            <main>\n"
                    + "                <section id=\"form\">\n"
                    + "                    <h2>You Have Now Loged Out</h2>\n");
            // wait 2 seconds then redirect to login page

            if (cookies != null)
            {
                // set the cookies age to zero so they expire
                for (int i = 0; i < cookies.length; i++)
                {
                    cookie = cookies[i];
                    if ((cookie.getName()).compareTo("cookUsername") == 0)
                    {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                    if ((cookie.getName()).compareTo("cookPassword") == 0)
                    {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
            else
            {
                out.println("<h2>Not Logged In</h2>");
            }
            response.setHeader("Refresh", "2; login.html");
            out.println("</section>\n"
                    + "<p style=\"text-align:center\"><a href =\"login.html\">Log In</a></p>"
                    + "                <br>\n"
                    + "                <br>\n"
                    + "            </main>\n"
                    + "            <footer>\n"
                    + "                <p>Dead By Daylight Fan Club\n"
                    + "                    <a href=\"https://www.facebook.com/DeadByDaylight/\" target=\"_blank\"><img src=\"images/facebook.png\"  alt=\"facebook\" title=\"Visit us on Facebook\"></a>\n"
                    + "                    <a href=\"https://www.instagram.com/deadbydaylight/?hl=en\" target=\"_blank\"><img src=\"images/instagram.png\" alt=\"instagram\" title=\"Visit us on Instagram\"></a>\n"
                    + "                    <a href=\"https://www.youtube.com/channel/UCaSgsFdGbwjfdawl3rOXiwQ\" target=\"_blank\"><img src=\"images/youtube.png\"  alt=\"youtube\" title=\"Visit us on Youtube\"></a>\n"
                    + "                    <br>\n"
                    + "                    <a style=\"text-decoration:none;color:#E7F3EF;\" href=\"mailto:K00224431@student.lit.ie\">Email: K00224431@student.lit.ie</a><br>\n"
                    + "                    Author: Jekaterina Pavlenko<br>\n"
                    + "                </p>\n"
                    + "            </footer>	\n"
                    + "\n"
                    + "        </div>\n"
                    + "\n"
                    + "    </body>\n"
                    + "</html>\n"
                    + "");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Special Warning Login " + e);
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
