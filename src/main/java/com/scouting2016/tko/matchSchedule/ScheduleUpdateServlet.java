package com.scouting2016.tko.matchSchedule;

import org.apache.commons.codec.binary.Base64;
import org.apache.taglibs.standard.lang.jstl.IntegerDivideOperator;
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
import java.util.ArrayList;

/**
 * Created by rahul on 3/19/16.
 */
@WebServlet(name = "ScheduleUpdateServlet")
public class ScheduleUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String state = request.getParameter("schedule-action");
        ArrayList<Event> events = new ArrayList<>();
        boolean status = false;
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
                    "<form id=\"events-update\" action=\"/events\" method=\"POST\" hidden>\n" +
                    "    <input type=\"text\" id=\"action\" name=\"action\" value=\"false\"/>\n" +
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
                    "                <li><a onclick=\"document.getElementById('events-update').submit()\">Event Data Initializer</a></li>\n" +
                    "                <li class=\"active\"><a onclick=\"document.getElementById('schedule-action').value='false';document.getElementById('schedule-update').submit()\">Schedule Data Initializer</a></li>\n" +
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
                    "            <h1 style=\"text-align: center\">Update Schedule Data</h1>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <br>\n" +
                    "    <br>\n" +
                    "    <form class=\"form-group-lg\" id=\"schedule-update\" action=\"/schedule\" method=\"POST\">\n" +
                    "        <input type=\"text\" id=\"schedule-action\" name=\"schedule-action\" value=\"true\" hidden/>\n" +
                    "        <input type=\"submit\" id=\"codeUpdate\" name=\"codeUpdate\" class=\"btn btn-lg btn-primary center-block\"/>\n" +
                    "    </form>\n" +
                    "</div>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>");
        } else {
            // JDBC driver name and database URL
            final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            final String DB_URL = "jdbc:mysql://localhost:3306/tko1351_2016";

            //  Database credentials
            final String USER = "root";
            final String PASS = "babutastic";

            Connection conn = null;
            Statement stmt = null;
            try {
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to a selected database...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected database successfully...");

                //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();

                String sql = "SELECT * FROM regionalCodes";
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                while (rs.next()) {
                    //Retrieve by column name
                    events.add(new Event(rs.getString("regionalName"), rs.getString("regionalCode")));
                }
                rs.close();
            } catch (SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try {
                    if (stmt != null)
                        conn.close();
                } catch (SQLException se) {
                }// do nothing
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }//end finally try
            }//end try

            //final String credentials = "frcteam1351:5dd24ab4-d867-4bde-a518-12faa2596982";
            //byte[] encodedBytes = Base64.encodeBase64(credentials.getBytes());
            String jsonData = "";
            String jsonPlayoffData = "";
            for (Event event : events) {

                Client client = ClientBuilder.newClient();
                Response firstResponse = client.target("https://www.thebluealliance.com/api/v2/event/" +
                        event.getCode() + "/matches")
                        .request(MediaType.TEXT_PLAIN_TYPE)
                        .header("Accept", "application/json")
                        .header("X-TBA-App-Id", "frc1351:scouting-system-2016:v01")
                        .get();

                System.out.println("status: " + firstResponse.getStatus());
                System.out.println("headers: " + firstResponse.getHeaders());
                jsonData = firstResponse.readEntity(String.class);
                System.out.println("body:" + jsonData);
                JSONArray scheduleArray = new JSONArray(jsonData);

                /* Response firstPlayoffResponse = client.target("https://frc-api.firstinspires.org/v2.0/2016/schedule/" +
                        event.getCode() + "?tournamentLevel=Playoff")
                        .request(MediaType.TEXT_PLAIN_TYPE)
                        .header("Accept", "application/json")
                        .header("Authorization", "Basic " + new String(encodedBytes))
                        .get();

                System.out.println("status: " + firstPlayoffResponse.getStatus());
                System.out.println("headers: " + firstPlayoffResponse.getHeaders());
                jsonPlayoffData = firstPlayoffResponse.readEntity(String.class);
                System.out.println("body:" + jsonPlayoffData);
                JSONObject frcPlayoffObject = new JSONObject(jsonPlayoffData);
                JSONArray schedulePlayoffArray = frcPlayoffObject.getJSONArray("Schedule"); */

                status = false;
                if (firstResponse.getStatus() == 200) {
                    status = true;
                    System.out.print(event.getName());
                    try {
                        //STEP 2: Register JDBC driver
                        Class.forName("com.mysql.jdbc.Driver");

                        //STEP 3: Open a connection
                        System.out.println("Connecting to a selected database...");
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        System.out.println("Connected database successfully...");

                        DatabaseMetaData dbm = conn.getMetaData();
                        // check if "Schedule" tables are there
                        String table = event.getName().replaceAll("\\s+", "");
                        table = table.replaceAll("[/$&+,:;=?@#|'<>.^*()%!-]", "");
                        int MAX_CHAR = 56;
                        int maxLength = (table.length() < MAX_CHAR) ? table.length() : MAX_CHAR;
                        ResultSet tables = dbm.getTables(null, null, table.substring(0, maxLength) + "Schedule", null);
                        String sql = "";
                        //STEP 4: Execute a query
                        System.out.println("Creating statement...");
                        stmt = conn.createStatement();


                        /*if (table.contains(".")) {
                            table = table.substring(0, table.indexOf("."));
                        }*/

                        if (tables.next()) {
                            // Table exists
                            sql = "DROP TABLE tko1351_2016." + table.substring(0, maxLength) + "Schedule";
                            stmt.executeUpdate(sql);
                        } else {
                            // Table does not exist
                        }

                        sql = "CREATE TABLE tko1351_2016." + table.substring(0, maxLength) + "Schedule" + "\n" +
                                "(\n" +
                                "    startTime TEXT,\n" +
                                "    matchLevel TEXT,\n" +
                                "    matchNum INT DEFAULT 0,\n" +
                                "    red1 INT DEFAULT 0,\n" +
                                "    red2 INT DEFAULT 0,\n" +
                                "    red3 INT DEFAULT 0,\n" +
                                "    blue1 INT DEFAULT 0,\n" +
                                "    blue2 INT DEFAULT 0,\n" +
                                "    blue3 INT DEFAULT 0\n" +
                                ");";
                        stmt.executeUpdate(sql);
                        System.out.println(sql);
                        int matchNum = 0;
                        String startTime = "", compLevel = "";
                        JSONArray red_teams, blue_teams;
                        int[] teamNumbers;
                        String teamNumberTemp = "";
                        for (int i = 0; i < scheduleArray.length(); i++) {
                            teamNumbers = new int[6];
                            JSONObject obj = scheduleArray.getJSONObject(i);
                            switch (obj.getString("comp_level")) {
                                case "f":
                                    compLevel = "Final";
                                    matchNum = obj.getInt("set_number");
                                    break;
                                case "sf":
                                    compLevel = "Semi-Final";
                                    matchNum = obj.getInt("set_number");
                                    break;
                                case "qf":
                                    compLevel = "Quarter-Final";
                                    matchNum = obj.getInt("set_number");
                                    break;
                                case "ef":
                                    compLevel = "Eight-Final";
                                    matchNum = obj.getInt("set_number");
                                    break;
                                default:
                                    compLevel = "Qualification";
                                    matchNum = obj.getInt("match_number");
                            }
                            if(!obj.isNull("time_string"))
                                startTime = obj.getString("time_string");
                            else
                                startTime = "null";
                            blue_teams = obj.getJSONObject("alliances").getJSONObject("blue").getJSONArray("teams");
                            red_teams = obj.getJSONObject("alliances").getJSONObject("red").getJSONArray("teams");
                            for (int j = 0; j < red_teams.length(); j++) {
                                teamNumberTemp = red_teams.getString(j).substring(3);
                                if(teamNumberTemp.length()!=0)
                                    teamNumberTemp = teamNumberTemp.replaceAll("[A-Z]", Integer.toString((int)teamNumberTemp.charAt(teamNumberTemp.length()-1)-64));
                                teamNumbers[j] = (teamNumberTemp.equals("")) ? 0 : Integer.parseInt(teamNumberTemp);
                            }
                            for (int j = 0; j < blue_teams.length(); j++) {
                                teamNumberTemp = blue_teams.getString(j).substring(3);
                                if(teamNumberTemp.length()!=0)
                                    teamNumberTemp = teamNumberTemp.replaceAll("[A-Z]", Integer.toString((int)teamNumberTemp.charAt(teamNumberTemp.length()-1)-64));
                                teamNumbers[j+3] = (teamNumberTemp.equals("")) ? 0 : Integer.parseInt(teamNumberTemp);
                            }
                            if(!compLevel.equals("Qualification")) {
                                sql = " DELETE FROM tko1351_2016."+table.replace(" ", "").substring(0, maxLength) + "Schedule " +
                                        " WHERE matchLevel = \"" + compLevel + "\" AND matchNum = " + matchNum;
                                System.out.println(sql);
                                stmt.executeUpdate(sql);
                                System.out.println("Deleting any duplicate entry...");
                            }
                            sql = "INSERT INTO tko1351_2016." + table.replace(" ", "").substring(0, maxLength) + "Schedule " +
                                    "VALUES (" + "\"" + startTime + "\", \"" + compLevel + "\", " + matchNum + ", " + teamNumbers[0]
                                    + ", " + teamNumbers[1] + ", " + teamNumbers[2] + ", " + teamNumbers[3] + ", " + teamNumbers[4] + ", " + teamNumbers[5] + ")";
                            System.out.println(sql);
                            stmt.executeUpdate(sql);
                            System.out.println("Inserted records into the table...");
                        }

                    } catch (SQLException se) {
                        //Handle errors for JDBC
                        se.printStackTrace();
                    } catch (Exception e) {
                        //Handle errors for Class.forName
                        e.printStackTrace();
                    } finally {
                        //finally block used to close resources
                        try {
                            if (stmt != null)
                                conn.close();
                        } catch (SQLException se) {
                        }// do nothing
                        try {
                            if (conn != null)
                                conn.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                        }//end finally try
                    }//end try

                    System.out.println("Done updating schedules.");
                    //TODO Make this system compatible with Districts and the FIRST Championship

                }
            }
            if (status) {
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
                        "<form id=\"events-update\" action=\"/events\" method=\"POST\" hidden>\n" +
                        "    <input type=\"text\" id=\"action\" name=\"action\" value=\"false\"/>\n" +
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
                        "                <li><a onclick=\"document.getElementById('events-update').submit()\">Event Data Initializer</a></li>\n" +
                        "                <li class=\"active\"><a onclick=\"document.getElementById('schedule-action').value='false';document.getElementById('schedule-update').submit()\">Schedule Data Initializer</a></li>\n" +
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
                        "            <h1 style=\"text-align: center; color: #3e8f3e\">Successfully Updated Schedule Tables</h1>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "    <br>\n" +
                        "    <br>\n" +
                        "    <form class=\"form-group-lg\" id=\"schedule-update\" action=\"/schedule\" method=\"POST\">\n" +
                        "        <input type=\"text\" id=\"schedule-action\" name=\"schedule-action\" value=\"true\" hidden/>\n" +
                        "        <input type=\"submit\" id=\"codeUpdate\" name=\"codeUpdate\" class=\"btn btn-lg btn-primary center-block\"/>\n" +
                        "    </form>\n" +
                        "</div>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>");
            } else {
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
                        "<form id=\"events-update\" action=\"/events\" method=\"POST\" hidden>\n" +
                        "    <input type=\"text\" id=\"action\" name=\"action\" value=\"false\"/>\n" +
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
                        "                <li><a onclick=\"document.getElementById('events-update').submit()\">Event Data Initializer</a></li>\n" +
                        "                <li class=\"active\"><a onclick=\"document.getElementById('schedule-action').value='false';document.getElementById('schedule-update').submit()\">Schedule Data Initializer</a></li>\n" +
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
                        "            <h1 style=\"text-align: center; color: #ac2925\">Failed To Updated Schedule Data! Check Your Network Connection.</h1>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "    <br>\n" +
                        "    <br>\n" +
                        "    <form class=\"form-group-lg\" id=\"schedule-update\" action=\"/schedule\" method=\"POST\">\n" +
                        "        <input type=\"text\" id=\"schedule-action\" name=\"schedule-action\" value=\"true\" hidden/>\n" +
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
