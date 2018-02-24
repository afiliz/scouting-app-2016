package com.scouting2016.tko.eventCodes;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by rahul on 3/19/16.
 */
@WebServlet(name = "CodesUpdateServlet")
public class CodesUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String state = request.getParameter("action");
        if (state.equals("false")) {
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
                    "<form id=\"schedule-update\" action=\"/schedule\" method=\"POST\" hidden>\n" +
                    "    <input type=\"text\" id=\"schedule-action\" name=\"schedule-action\" value=\"false\"/>\n" +
                    "</form>\n" +
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
                            "                <li class=\"active\"><a onclick=\"document.getElementById('events-update').submit()\">Event Data Initializer</a></li>\n" +
                            "                <li><a onclick=\"document.getElementById('schedule-update').submit()\">Schedule Data Initializer</a></li>\n" +
                            "                <li><a href=\"/data?databaseName=SiliconValleyRegionalpresentedbyGoogleSchedule\">Silicon Valley Regional</a></li>\n" +
                            "                <li><a href=\"robotView.html\">Robot Data</a></li>\n" +
                    "            </ul>\n" +
                    "        </div><!--/.nav-collapse -->\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "<div class=\"container\">\n" +
                    "    <div class=\"row\">\n" +
                    "        <div class=\"col-md-12\">\n" +
                    "            <h1 style=\"text-align: center\">Update Event Codes</h1>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <br>\n" +
                    "    <br>\n" +
                    "    <form class=\"form-group-lg\" id=\"events-update\" action=\"/events\" method=\"POST\">\n" +
                    "        <input type=\"text\" id=\"action\" name=\"action\" value=\"true\" hidden/>\n" +
                    "        <input type=\"submit\" id=\"codeUpdate\" name=\"codeUpdate\" class=\"btn btn-lg btn-primary center-block\"/>\n" +
                    "    </form>\n" +
                    "</div>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>");
        }
        else {
            //  First Event Key + Base64 Encoding of it
            //final String credentials = "frcteam1351:5dd24ab4-d867-4bde-a518-12faa2596982";
            //byte[] encodedBytes = Base64.encodeBase64(credentials.getBytes());

            Client client = ClientBuilder.newClient();
            Response firstResponse = client.target("https://www.thebluealliance.com/api/v2/events/2016")
                    //v2.0/2016/events
                    //v2.0/2016/schedule/MNDU?tournamentLevel=Qualification
                    .request(MediaType.TEXT_PLAIN_TYPE)
                    .header("Accept", "application/json")
                    .header("X-TBA-App-Id", "frc1351:scouting-system-2016:v01")
                    .get();
            System.out.println("status: " + firstResponse.getStatus());
            System.out.println("headers: " + firstResponse.getHeaders());
            String jsonData = firstResponse.readEntity(String.class);
            System.out.println("body:" + jsonData);

            if(firstResponse.getStatus() == 200) {
                //  JDBC driver name and database URL
                final String DB_URL="jdbc:mysql://localhost:3306/tko1351_2016";

                //  Database credentials
                final String USER = "root";
                final String PASS = "babutastic";

                JSONArray eventArray = new JSONArray(jsonData);
                Connection conn = null;
                Statement stmt = null;
                try{
                    //STEP 2: Register JDBC driver
                    Class.forName("com.mysql.jdbc.Driver");

                    //STEP 3: Open a connection
                    System.out.println("Connecting to a selected database...");
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    System.out.println("Connected database successfully...");
                    //STEP 4: Execute a query
                    System.out.println("Inserting records into the table...");
                    stmt = conn.createStatement();
                    DatabaseMetaData dbm = conn.getMetaData();
                    // check if "Schedule" tables are there
                    ResultSet tables = dbm.getTables(null, null, "regionalCodes", null);
                    String sql = "";
                    //STEP 4: Execute a query
                    System.out.println("Creating statement...");
                    stmt = conn.createStatement();
                    if (tables.next()) {
                        // Table exists
                        sql = "DROP TABLE tko1351_2016.regionalCodes";
                        stmt.executeUpdate(sql);
                    }
                    else {
                        // Table does not exist
                    }
                    sql = "CREATE TABLE tko1351_2016.regionalCodes\n" +
                            "(\n" +
                            "    regionalName TEXT,\n" +
                            "    regionalCode TEXT\n" +
                            ")";
                    stmt.executeUpdate(sql);
                    for(int i = 0; i < eventArray.length(); i++) {
                        JSONObject tempObj = eventArray.getJSONObject(i);
                        sql = "INSERT INTO tko1351_2016.regionalCodes " +
                                "VALUES ("+"\""+tempObj.getString("name")+"\", \""+tempObj.getString("key")+"\""+")";
                        System.out.println(sql);
                        stmt.executeUpdate(sql);
                        System.out.println("Inserted records into the table...");
                    }

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
                        "<form id=\"schedule-update\" action=\"/schedule\" method=\"POST\" hidden>\n" +
                        "    <input type=\"text\" id=\"schedule-action\" name=\"schedule-action\" value=\"false\"/>\n" +
                        "</form>\n" +
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
                        "                <li class=\"active\"><a onclick=\"document.getElementById('events-update').submit()\">Event Data Initializer</a></li>\n" +
                        "                <li><a onclick=\"document.getElementById('schedule-update').submit()\">Schedule Data Initializer</a></li>\n" +
                        "                <li><a href=\"/data?databaseName=SiliconValleyRegionalpresentedbyGoogleSchedule\">Silicon Valley Regional</a></li>\n" +
                        "                <li><a href=\"robotView.html\">Robot Data</a></li>\n" +
                        "            </ul>\n" +
                        "        </div><!--/.nav-collapse -->\n" +
                        "    </div>\n" +
                        "</div>\n" +
                        "\n" +
                        "<div class=\"container\">\n" +
                        "    <div class=\"row\">\n" +
                        "        <div class=\"col-md-12\">\n" +
                        "            <h1 style=\"text-align: center; color: #3e8f3e\">Successfully Updated Event Codes</h1>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "    <br>\n" +
                        "    <br>\n" +
                        "    <form class=\"form-group-lg\" id=\"events-update\" action=\"/events\" method=\"POST\">\n" +
                        "        <input type=\"text\" id=\"action\" name=\"action\" value=\"true\" hidden/>\n" +
                        "        <input type=\"submit\" id=\"codeUpdate\" name=\"codeUpdate\" class=\"btn btn-lg btn-primary center-block\"/>\n" +
                        "    </form>\n" +
                        "</div>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>");

            }
            else {
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
                        "<form id=\"schedule-update\" action=\"/schedule\" method=\"POST\" hidden>\n" +
                        "    <input type=\"text\" id=\"schedule-action\" name=\"schedule-action\" value=\"false\"/>\n" +
                        "</form>\n" +
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
                        "                <li class=\"active\"><a onclick=\"document.getElementById('events-update').submit()\">Event Data Initializer</a></li>\n" +
                        "                <li><a onclick=\"document.getElementById('schedule-update').submit()\">Schedule Data Initializer</a></li>\n" +
                        "                <li><a href=\"/data?databaseName=SiliconValleyRegionalpresentedbyGoogleSchedule\">Silicon Valley Regional</a></li>\n" +
                        "                <li><a href=\"robotView.html\">Robot Data</a></li>\n" +
                        "            </ul>\n" +
                        "        </div><!--/.nav-collapse -->\n" +
                        "    </div>\n" +
                        "</div>\n" +
                        "\n" +
                        "<div class=\"container\">\n" +
                        "    <div class=\"row\">\n" +
                        "        <div class=\"col-md-12\">\n" +
                        "            <h1 style=\"text-align: center; color: #ac2925\">Failed To Updated Event Codes! Check Your Network Connection.</h1>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "    <br>\n" +
                        "    <br>\n" +
                        "    <form class=\"form-group-lg\" id=\"events-update\" action=\"/events\" method=\"POST\">\n" +
                        "        <input type=\"text\" id=\"action\" name=\"action\" value=\"true\" hidden/>\n" +
                        "        <input type=\"submit\" id=\"codeUpdate\" name=\"codeUpdate\" class=\"btn btn-lg btn-primary center-block\"/>\n" +
                        "    </form>\n" +
                        "</div>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
