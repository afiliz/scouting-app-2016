package com.scouting2016.tko.data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.regex.Pattern;

/**
 * Created by rahul on 3/24/16.
 */
@WebServlet(name = "RobotViewServlet")
public class RobotViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hi");
        int robot = Integer.parseInt(request.getParameter("robot"));
        PrintWriter out = response.getWriter();
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
                        "                <li><a href=\"/data?databaseName=CalGamesSchedule\">CalGames Regional</a></li>\n" +
                        "                <li class=\"active\"><a href=\"robotView.html\">Robot Data</a></li>\n"+
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
                "            <h1 style=\"text-align: center\">Team# "+robot+" Breakdown</h1>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <table class=\"table table-bordered\">\n" +
                "        <thead>\n" +
                "        <tr>\n" +
                "            <th>Team Number</th>\n" +
                "            <th>Match Number</th>\n" +
                "            <th>Autonomous High Goal Scores</th>\n" +
                "            <th>Autonomous Low Goal Scores</th>\n" +
                "            <th>Robot Type</th>\n" +
                "            <th>Robot Speed</th>\n" +
                "            <th>Intake Speed</th>\n" +
                "            <th>Shooting Speed</th>\n" +
                "            <th>Teleop High Goal Scores</th>\n" +
                "            <th>Teleop Low Goal Scores</th>\n" +
                "            <th>Scores From Multiple Locations</th>\n" +
                "            <th>Captured Tower</th>\n" +
                "            <th>Goes Up Ramp</th>\n" +
                "            <th>Climbs</th>\n" +
                "            <th>Broken</th>\n" +
                "            <th>No Show</th>\n" +
                "        </tr>\n" +
                "        </thead>\n" +
                "        <tbody>\n");

        // JDBC driver name and database URL
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL="jdbc:mysql://localhost:3306/tko1351_2016";

        //  Database credentials
        final String USER = "root";
        final String PASS = "babutastic";

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
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM tko1351_2016.robotData WHERE teamNum = "+robot;
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                out.print("        <tr>\n" +
                        "            <td>"+rs.getInt("teamNum")+"</td>\n" +
                        "            <td>"+rs.getString("matchNum")+"</td>\n" +
                        "            <td>"+rs.getInt("autonHighGoal")+"</td>\n" +
                        "            <td>"+rs.getInt("autonLowGoal")+"</td>\n" +
                        "            <td>"+rs.getString("robotType").replaceAll(Pattern.quote("="), ",")+"</td>\n" +
                        "            <td>"+rs.getString("robotSpeed")+"</td>\n" +
                        "            <td>"+rs.getString("intakeSpeed")+"</td>\n" +
                        "            <td>"+rs.getString("shootingSpeed")+"</td>\n" +
                        "            <td>"+rs.getInt("teleopHighGoal")+"</td>\n" +
                        "            <td>"+rs.getInt("teleopLowGoal")+"</td>\n" +
                        "            <td>"+(rs.getInt("scoresFromMultLoc")==1?"Yes":"No")+"</td>\n" +
                        "            <td>"+(rs.getInt("towerCaptured")==1?"Yes":"No")+"</td>\n" +
                        "            <td>"+(rs.getInt("goesUpRamp")==1?"Yes":"No")+"</td>\n" +
                        "            <td>"+(rs.getInt("climbs")==1?"Yes":"No")+"<br>"+(rs.getInt("climbsPoorly")==1?"Poorly Climbs":"")+
                        "<br>"+(rs.getInt("climbsFailed")==1?"Failed to Climb":"")+"</td>\n" +
                        "            <td>"+(rs.getInt("broken")==1?"Yes":"No")+"</td>\n" +
                        "            <td>"+(rs.getInt("noShow")==1?"Yes":"No")+"</td>\n" +
                        "        </tr>\n");
            }
            rs.close();

            out.print( "        </tbody>\n" +
                    "    </table>\n" +
                    "    <p>Autonomous</p>\n" +
                    "    <table class=\"table table-bordered\">\n" +
                    "        <thead>\n" +
                    "        <tr>\n" +
                    "            <th>Team Number</th>\n" +
                    "            <th>Match Number</th>\n" +
                    "            <th>Cheval De Frise</th>\n" +
                    "            <th>Drawbridge</th>\n" +
                    "            <th>Rock Wall</th>\n" +
                    "            <th>Moat</th>\n" +
                    "            <th>Portcullis</th>\n" +
                    "            <th>Sally Port</th>\n" +
                    "            <th>Rampart</th>\n" +
                    "            <th>Rough Terrain</th>\n" +
                    "            <th>Low Bar</th>\n" +
                    "        </tr>\n" +
                    "        </thead>\n" +
                    "        <tbody>\n");

            sql = "SELECT * FROM tko1351_2016.robotData WHERE teamNum = "+robot;
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                out.print("        <tr>\n" +
                        "            <td>"+rs.getInt("teamNum")+"</td>\n" +
                        "            <td>"+rs.getString("matchNum")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("autonCheval"))+"<br>"+(rs.getInt("chevalAutoPoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("chevalAutoFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("autonDrawbridge"))+"<br>"+(rs.getInt("drawbridgeAutoPoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("drawbridgeAutoFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("autonRockWall"))+"<br>"+(rs.getInt("rockWallAutoPoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("rockWallAutoFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("autonMoat"))+"<br>"+(rs.getInt("moatAutoPoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("moatAutoFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("autonPortcullis"))+"<br>"+(rs.getInt("portcullisAutoPoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("portcullisAutoFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("autonSallyPort"))+"<br>"+(rs.getInt("sallyPortAutoPoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("sallyPortAutoFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("autonRampart"))+"<br>"+(rs.getInt("rampartAutoPoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("rampartAutoFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("autonRoughTerrain"))+"<br>"+(rs.getInt("roughTerrainAutoPoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("roughTerrainAutoFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("autonLowBar"))+"<br>"+(rs.getInt("lowBarAutoPoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("lowBarAutoFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "        </tr>\n");
            }
            rs.close();

            out.print( "        </tbody>\n" +
                    "    </table>\n" +
                    "    <p>Teleop</p>\n" +
                    "    <table class=\"table table-bordered\">\n" +
                    "        <thead>\n" +
                    "        <tr>\n" +
                    "            <th>Team Number</th>\n" +
                    "            <th>Match Number</th>\n" +
                    "            <th>Cheval De Frise</th>\n" +
                    "            <th>Drawbridge</th>\n" +
                    "            <th>Rock Wall</th>\n" +
                    "            <th>Moat</th>\n" +
                    "            <th>Portcullis</th>\n" +
                    "            <th>Sally Port</th>\n" +
                    "            <th>Rampart</th>\n" +
                    "            <th>Rough Terrain</th>\n" +
                    "            <th>Low Bar</th>\n" +
                    "        </tr>\n" +
                    "        </thead>\n" +
                    "        <tbody>\n");

            sql = "SELECT * FROM tko1351_2016.robotData WHERE teamNum = "+robot;
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                out.print("        <tr>\n" +
                        "            <td>"+rs.getInt("teamNum")+"</td>\n" +
                        "            <td>"+rs.getString("matchNum")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("teleopCheval"))+"<br>"+(rs.getInt("chevalTelePoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("chevalTeleFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("teleopDrawbridge"))+"<br>"+(rs.getInt("drawbridgeTelePoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("drawbridgeTeleFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("teleopRockWall"))+"<br>"+(rs.getInt("rockWallTelePoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("rockWallTeleFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("teleopMoat"))+"<br>"+(rs.getInt("moatTelePoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("moatTeleFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("teleopPortcullis"))+"<br>"+(rs.getInt("portcullisTelePoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("portcullisTeleFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("teleopSallyPort"))+"<br>"+(rs.getInt("sallyPortTelePoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("sallyPortTeleFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("teleopRampart"))+"<br>"+(rs.getInt("rampartTelePoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("rampartTeleFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("teleopRoughTerrain"))+"<br>"+(rs.getInt("roughTerrainTelePoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("roughTerrainTeleFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "            <td>"+getState(rs.getInt("teleopLowBar"))+"<br>"+(rs.getInt("lowBarTelePoor")==1?"Struggled to Cross":"")+
                        "<br>"+(rs.getInt("lowBarTeleFail")==1?"Failed to Cross":"")+"</td>\n" +
                        "        </tr>\n");
            }
            rs.close();




            out.print("        </tbody>\n" +
                    "    </table>\n" +
                    "    <h2>Comments (Dear God)</h2>\n" +
                    "    <table class=\"table table-bordered\">\n" +
                    "        <thead>\n"+
                    "        <tr>\n" +
                    "            <th>Team Number</th>\n" +
                    "            <th>Match Number</th>\n" +
                    "            <th>Comments</th>\n" +
                    "        </tr>\n" +
                    "        </thead>\n" +
                    "        <tbody>\n");
            sql = "SELECT * FROM tko1351_2016.robotData WHERE teamNum = "+robot;
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                out.print("        <tr>\n" +
                        "            <td>"+rs.getInt("teamNum")+"</td>\n" +
                        "            <td>"+rs.getString("matchNum")+"</td>\n" +
                        "            <td>"+rs.getString("comments")+"</td>\n" +
                        "        </tr>\n");
            }
            rs.close();

            out.print("        </tbody>\n" +
                    "    </table>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>");

            System.out.println("Done.");
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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected String getState(int state) {
        switch (state) {
            case 2:
                return "Damaged";
            case 1:
                return "Crossed";
            default:
                return "N/a";
        }
    }
}
