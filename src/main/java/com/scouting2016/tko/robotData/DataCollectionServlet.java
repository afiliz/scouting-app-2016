package com.scouting2016.tko.robotData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by rahul on 1/21/16.
 */
@WebServlet(name = "DataCollectionServlet")
public class DataCollectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // JDBC driver name and database URL
        final String DB_URL="jdbc:mysql://localhost:3306/tko1351_2016";

        //  Database credentials
        final String USER = "root";
        final String PASS = "babutastic";

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RobotData scoutingData = mapper.readValue(json, RobotData.class);

        System.out.println("JSON Data\n"+json);
        System.out.println("Scouting Cheval Value Tele: "+scoutingData.getMoat_auto_poor());

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
            String sql = "";

            if(scoutingData.getMatch_num().contains("Qualification")) {
                sql = " DELETE FROM tko1351_2016.robotData WHERE uuid = \"" + scoutingData.getUuid() + "\"";
                System.out.println(sql);
                stmt.executeUpdate(sql);
                System.out.println("Deleting any duplicate entry...");
            }

            sql = "INSERT INTO tko1351_2016.robotData " +
                    "VALUES ("+"\""+scoutingData.getMatch_num()+"\", "+scoutingData.getTeam_num()+", \""+scoutingData.getAlliance_color()+"\", "
                    +scoutingData.getAuton_high_goal()+", "+scoutingData.getAuton_low_goal()+", \""+scoutingData.getRobot_type()+"\", \""
                    +scoutingData.getRobot_speed()+"\", \""+scoutingData.getIntake_speed()+"\", \""+scoutingData.getShooting_speed()+"\", "
                    +scoutingData.getTeleop_high_goal()+", "+scoutingData.getTeleop_low_goal()+", "+scoutingData.getScores_from_mult_loc()
                    +", "+scoutingData.getTower_captured()+", "+scoutingData.getGoes_up_ramp()+", "+scoutingData.getClimbs()
                    +", "+scoutingData.getClimbs_failed()+", "+scoutingData.getClimbs_poorly()+", "+scoutingData.getBroken()
                    +", "+scoutingData.getNo_show()+", "+scoutingData.getCheval_selected()+", "+scoutingData.getDrawbridge_selected()
                    +", "+scoutingData.getRock_wall_selected()+", "+scoutingData.getMoat_selected()+", "+scoutingData.getPortcullis_selected()
                    +", "+scoutingData.getSally_port_selected()+", "+scoutingData.getRampart_selected()+", " +scoutingData.getRough_terrain_selected()
                    +", "+scoutingData.getAuton_cheval()+", "+scoutingData.getCheval_auto_poor()+", "+scoutingData.getCheval_auto_fail()
                    +", "+scoutingData.getAuton_drawbridge()+", "+scoutingData.getDrawbridge_auto_poor()+", "+scoutingData.getDrawbridge_auto_fail()
                    +", "+scoutingData.getAuton_rock_wall()+", "+scoutingData.getRock_wall_auto_poor()+", "+scoutingData.getRock_wall_auto_fail()
                    +", "+scoutingData.getAuton_moat()+", "+scoutingData.getMoat_auto_poor()+", "+scoutingData.getMoat_auto_fail()
                    +", "+scoutingData.getAuton_portcullis()+", "+scoutingData.getPortcullis_auto_poor()+", "+scoutingData.getPortcullis_auto_fail()
                    +", "+scoutingData.getAuton_sally_port()+", "+scoutingData.getSally_port_auto_poor()+", "+scoutingData.getSally_port_auto_fail()
                    +", "+scoutingData.getAuton_rampart()+", "+scoutingData.getRampart_auto_poor()+", "+scoutingData.getRampart_auto_fail()
                    +", "+scoutingData.getAuton_rough_terrain()+", "+scoutingData.getRough_terrain_auto_poor()+", "
                    +scoutingData.getRough_terrain_auto_fail()+", "+scoutingData.getAuton_low_bar()+", "+scoutingData.getLow_bar_auto_poor()
                    +", "+scoutingData.getLow_bar_auto_fail()+", "+scoutingData.getTeleop_cheval()+", "+scoutingData.getCheval_tele_poor()
                    +", "+scoutingData.getCheval_tele_fail()+", "+scoutingData.getTeleop_drawbridge()+", "
                    +scoutingData.getDrawbridge_tele_poor()+", " +scoutingData.getDrawbridge_auto_fail()+", "+scoutingData.getTeleop_rock_wall()
                    +", "+scoutingData.getRock_wall_tele_poor()+", "+scoutingData.getRock_wall_tele_fail()+", "+scoutingData.getTeleop_moat()
                    +", "+scoutingData.getMoat_tele_poor() +", "+scoutingData.getMoat_tele_fail()+", "+scoutingData.getTeleop_portcullis()+", "
                    +scoutingData.getPortcullis_tele_poor()+", "+scoutingData.getPortcullis_tele_fail()+", "+scoutingData.getTeleop_sally_port()+", "
                    +scoutingData.getSally_port_tele_poor()+", "+scoutingData.getSally_port_tele_fail()+", "+scoutingData.getTeleop_rampart()
                    +", "+scoutingData.getRampart_tele_poor()+", "+scoutingData.getRampart_tele_fail()+", "+scoutingData.getTeleop_rough_terrain()
                    +", "+scoutingData.getRough_terrain_tele_poor()+", "+scoutingData.getRough_terrain_tele_fail()+", "
                    +scoutingData.getTeleop_low_bar()+", "+scoutingData.getLow_bar_tele_poor()+", "+scoutingData.getLow_bar_tele_fail()
                    +", "+scoutingData.getYellow_card()+", "+scoutingData.getRed_card()+", "+scoutingData.getNum_tech_fouls()+", \""
                    +scoutingData.getComments()+"\", \""+scoutingData.getName()+"\", "+scoutingData.getTeam_score()+", \""+
                    scoutingData.getEvent_code()+"\", \""+scoutingData.getUuid()+"\""+")";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");

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

        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
