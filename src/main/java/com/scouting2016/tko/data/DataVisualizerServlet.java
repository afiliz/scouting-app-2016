package com.scouting2016.tko.data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by rahul on 3/23/16.
 */
@WebServlet(name = "DataVisualizerServlet")
public class DataVisualizerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String databaseName = request.getParameter("databaseName");
        PrintWriter out = response.getWriter();
        // JDBC driver name and database
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL="jdbc:mysql://localhost:3306/tko1351_2016";

        //  Database credentials
        final String USER = "root";
        final String PASS = "babutastic";

        Connection conn = null;
        Statement stmt = null;
        ArrayList<Match> matches = new ArrayList<>();

        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();


            String sql = "SELECT * FROM "+databaseName;
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                matches.add(new Match(rs.getString("startTime"), rs.getString("matchLevel"), rs.getInt("matchNum"), rs.getInt("red1"),
                        rs.getInt("red2"), rs.getInt("red3"), rs.getInt("blue1"), rs.getInt("blue2"), rs.getInt("blue3")));
            }
            rs.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        out.print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <script type=\"text/javascript\" src=\"vendor/jquery-2.2.0.min.js\"></script>\n" +
                "    <script type=\"text/javascript\" src=\"vendor/bootstrap-3.3.6-dist/js/bootstrap.js\"></script>\n" +
                "    <link href=\"vendor/bootstrap-3.3.6-dist/css/bootstrap-theme.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"vendor/bootstrap-3.3.6-dist/css/bootstrap.css\" rel=\"stylesheet\">\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Scouting Admin Console</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"navbar navbar-default\" role=\"navigation\">\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"navbar-header\">\n" +
                "            <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n" +
                "                <span class=\"sr-only\">Toggle navigation</span>\n" +
                "                <span class=\"icon-bar\"></span>\n" +
                "                <span class=\"icon-bar\"></span>\n" +
                "                <span class=\"icon-bar\"></span>\n" +
                "            </button>\n" +
                "            <a class=\"navbar-brand\" href=\"admin.html\">Scouting Admin Console</a>\n" +
                "        </div>\n" +
                "        <div class=\"collapse navbar-collapse\">\n" +
                "            <ul class=\"nav navbar-nav\" id=\"menu-bar\">\n" +
                "                <li><a href=\"index.html\">Scouting App</a></li>\n" +
                "                <li><a onclick=\"document.getElementById('events-update').submit()\">Event Data Initializer</a></li>\n" +
                "                <li><a onclick=\"document.getElementById('schedule-update').submit()\">Schedule Data Initializer</a></li>\n" +
                "                <li class=\"active\"><a href=\"/data?databaseName=CalGamesSchedule\">CalGames Regional</a></li>\n" +
                "<li><a href=\"robotView.html\">Robot Data</a></li>\n" +
                "            </ul>\n" +
                "        </div><!--/.nav-collapse -->\n" +
                "    </div>\n" +
                "</div>\n" +
                "<form id=\"events-update\" action=\"/events\" method=\"POST\" hidden>\n" +
                "    <input type=\"text\" id=\"action\" name=\"action\" value=\"false\"/>\n" +
                "</form>\n" +
                "<form id=\"schedule-update\" action=\"/schedule\" method=\"POST\" hidden>\n" +
                "    <input type=\"text\" id=\"schedule-action\" name=\"schedule-action\" value=\"false\"/>\n" +
                "</form>\n" +
                "<div class=\"container\">\n" +
                "    <div class=\"row\">\n" +
                "        <div class=\"col-md-12\">\n" +
                "            <h1 style=\"text-align: center\">Silicon Valley Regional Match Selection</h1>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"row\">\n");
                for (Match m : matches) {
                    out.print("        <form class=\"form-group-lg\" id=\"team-update\" action=\"/view\" method=\"POST\">\n" +
                            "<input type=\"text\" id=\"red1\" name=\"red1\" value=\""+m.getRed1()+"\" hidden/>\n" +
                            "<input type=\"text\" id=\"red2\" name=\"red2\" value=\""+m.getRed2()+"\" hidden/>\n" +
                            "<input type=\"text\" id=\"red3\" name=\"red3\" value=\""+m.getRed3()+"\" hidden/>\n" +
                            "<input type=\"text\" id=\"blue1\" name=\"blue1\" value=\""+m.getBlue1()+"\" hidden/>\n" +
                            "<input type=\"text\" id=\"blue2\" name=\"blue2\" value=\""+m.getBlue2()+"\" hidden/>\n" +
                            "<input type=\"text\" id=\"blue3\" name=\"blue3\" value=\""+m.getBlue3()+"\" hidden/>\n" +
                            "            <button type=\"submit\" class=\"btn btn-lg\" style=\"width: 100%\"><h3>Match Number "+m.getMatchNum()+" - "+m.getMatchLevel()
                            +"<br>"+m.getStartTime()+"</h3><p style=\"float: left; color: #ac2925\">"+m.getRed1()+"<br>"+m.getRed2()+"<br>"+m.getRed3()+"</p>\n" +
                            "\n" +
                            "                <p style=\"float: right; ; color: #2b669a\">"+m.getBlue1()+"<br>"+m.getBlue2()+"<br>"+m.getBlue3()+"</p></button>\n" +
                            "        </form>\n" +
                            "        <br>\n");
                }
                 out.print("    </div>\n" +
                "\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>");
    }
}
