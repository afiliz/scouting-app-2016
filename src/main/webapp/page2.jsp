<%@ page import="java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: compilx
  Date: 3/30/16
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script type="text/javascript" src="vendor/jquery-2.2.0.min.js"></script>
    <script type="text/javascript" src="vendor/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
    <script type="text/javascript" src="js/scouter.js"></script>
    <script src="vendor/jquery-multipage/jquery.multipage.js"></script>
    <link rel="stylesheet" type="text/css" href="vendor/jquery-multipage/jquery.multipage.css" />
    <link href="vendor/bootstrap-3.3.6-dist/css/bootstrap-theme.css" rel="stylesheet">
    <link href="vendor/bootstrap-3.3.6-dist/css/bootstrap.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Scouting Sheet</title>
</head>

<body>
<%
    // JDBC driver name and database URL
    final String DB_URL="jdbc:mysql://localhost:3306/tko1351_2016";

    String matchNum = request.getParameter("match-number");
    String alliance = request.getParameter("alliance");

    String eventCode = "cada";

    int[] teamNums = new int[3];

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
        stmt = conn.createStatement();
        String level = matchNum.substring(0,matchNum.indexOf(" "));
        String sql = "SELECT * FROM tko1351_2016.CalGamesSchedule WHERE (matchNum = "+matchNum.substring(matchNum.indexOf(" ")+1)+" AND matchLevel = \""+
                level + "\")";
        ResultSet rs = stmt.executeQuery(sql);
        //STEP 5: Extract data from result set



        while(rs.next()){
            if(alliance.equals("Blue")) {
                teamNums[0] = rs.getInt("blue1");
                teamNums[1] = rs.getInt("blue2");
                teamNums[2] = rs.getInt("blue3");
            }
            else {
                teamNums[0] = rs.getInt("red1");
                teamNums[1] = rs.getInt("red2");
                teamNums[2] = rs.getInt("red3");
            }
            //Retrieve by column name
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
    request.setAttribute("teamNums", teamNums);
%>
<div id="header">Scouting Sheet: Stronghold</div>
<form id="multipage">
    <fieldset id="page1">
        <div class="row form-inline">
            <div style="text-align: center">
                Team Number<select style="width: 175px; display: block; margin: 0 auto;" class="form-control" id="team-number">
                <c:forEach var="num" items="${teamNums}">
                    <option><c:out value="${num}" /></option>
                </c:forEach>
            </select>
                <br>

                <button class="btn btn-primary pull-left" id="page1-button" style="margin-left: 20px">Previous</button>
                <button class="btn btn-primary pull-right" id="page3-button" style="margin-right: 20px">Next</button>

            </div>
        </div>

    </fieldset>
    <fieldset id="page2">
        <input type="text" id="match-number" value="<%= matchNum%>" hidden/>
        <input type="text" id="alliance" value="<%= alliance%>" hidden/>
        <input type="text" id="event-code" value="<%= eventCode%>" hidden/>
        <div class="row">
            <div class="col-xs-4" id="left-column">
                <div id="left-content" class="left-content-class">
                    <div id="game-info-div">
                        <h2 class="left-titles">Name </h2>
                        <h3 id="FIRST-LAST">(First Last)</h3>
                        <form class="form-inline" id="name-form">
                            <div class="form-group">
                                <input type="text" class="form-control" id="name" placeholder="Input your name...">
                            </div>
                        </form>
                        <br>
                    </div>

                    <!--<div class="checkbox">
                        <label>
                            <input type="checkbox" value="">
                            Scores High Goal
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="">
                            Scores Low Goal
                        </label>
                    </div>-->
                    <h3 id="reminder-title">Score = Number of goals scored, not the actual score.</h3>
                    <h2 class="left-titles">Auton High Goal</h2>

                    <!--<div id="outer-buttons">-->
                    <div class="inside-buttons"><input style="width: 100%; text-align: center" name="-5" id="auton-high-minus" class="btn btn-danger" type="button" value="-"/></div>
                    <div class="inside-buttons"><input style="width: 77px" type="number" id="auton-hscore" class="form-control" placeholder="Score"/></div>
                    <div class="inside-buttons"><input style="width: 100%; text-align: center" name="+5" id="auton-high-plus" class="btn btn-success" type="button" value="+"/></div>
                    <!-- </div>-->
                    <h2 class="left-titles">Auton Low Goal</h2>
                    <div class="inside-buttons"><input style="width: 100%; text-align: center" name="-2" id="auton-low-minus" class="btn btn-danger" type="button" value="-"/></div>
                    <div class="inside-buttons"><input style="width: 77px" type="number" id="auton-lscore" class="form-control" placeholder="Score"/></div>
                    <div class="inside-buttons"><input style="width: 100%; text-align: center" name="+2" id="auton-low-plus" class="btn btn-success" type="button" value="+"/></div>

                    <br>
                    <br>

                    <h2 class="left-titles">Robot Function</h2>
                    <div class="selectors">Robot Type<select style="width: 175px" multiple class="form-control" id="robot-type">
                        <option value="All-Around">All-Around</option>
                        <option value="Low-Robot">Low-Robot</option>
                        <option value="High-Robot">High-Robot</option>
                        <option value="Defense-Robot">Defense-Robot</option>
                        <option value="Boulder-Robot">Boulder-Robot</option>
                        <option value="Spy-Bot">Spy-Bot</option>
                    </select></div>

                    <div class="selectors">Robot Speed<select style="width: 175px" class="form-control" id="robot-speed">
                        <option>Choose an option...</option>
                        <option>Fast</option>
                        <option>Medium</option>
                        <option>Slow</option>
                        <option>N/A (A.K.A Non-Moving Robot)</option>
                    </select></div>

                    <div class="selectors">Intake Speed<select style="width: 175px" class="form-control" id="intake-speed">
                        <option>Choose an option...</option>
                        <option>Fast</option>
                        <option>Medium</option>
                        <option>Slow</option>
                        <option>N/A</option>
                    </select></div>

                    <div class="selectors">Shooting Speed<select style="width: 175px" class="form-control" id="shooting-speed">
                        <option>Choose an option...</option>
                        <option>Fast</option>
                        <option>Medium</option>
                        <option>Slow</option>
                        <option>N/A</option>
                    </select></div>



                    <br>
                    <h3 id="reminder-title">Score = Number of goals scored, not the actual score.</h3>
                    <h2 class="left-titles">Teleop High Goal</h2>
                    <!--<div id="outer-buttons">-->
                    <div class="inside-buttons"><input style="width: 100%; text-align: center" name="-5" id="minus-5" class="btn btn-danger" type="button" value="-"/></div>
                    <div class="inside-buttons"><input style="width: 77px" type="number" id="total-hscore" class="form-control" placeholder="Score"/></div>
                    <div class="inside-buttons"><input style="width: 100%; text-align: center" name="+5" id="plus-5" class="btn btn-success" type="button" value="+"/></div>
                    <!-- </div>-->
                    <h2 class="left-titles">Teleop Low Goal</h2>
                    <div class="inside-buttons"><input style="width: 100%; text-align: center" name="-2" id="minus-2" class="btn btn-danger" type="button" value="-"/></div>
                    <div class="inside-buttons"><input style="width: 77px" type="number" id="total-lscore" class="form-control" placeholder="Score"/></div>
                    <div class="inside-buttons"><input style="width: 100%; text-align: center" name="+2" id="plus-2" class="btn btn-success" type="button" value="+"/></div>

                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="" id="sfml">
                            Scores from Multiple Locations
                        </label>
                    </div>
                    <br>

                    <!--<h2 class="right-titles">End Game</h2>-->
                    <h2 class="left-titles">End Game / Misc.</h2>
                    <h3 id="end-game-title">Note: A Tower is captured if it is weakened and is surrounded by 3 robots on 3 SEPARATE faces of the tower. The robots can either be challenging or scaling the tower.</h3>
                    <br>
                    <div id="end-game-checkboxes">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" id="tc">
                                Tower Captured
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" id="gur">
                                Goes Up Ramp
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" id="c">
                                Climbs
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" id="cp">
                                Climbs poorly
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" id="cf">
                                Tried to climb, but failed
                            </label>
                        </div>
                        <hr id="hr">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" id="b">
                                Broken?
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" id="ns">
                                No Show?
                            </label>
                        </div>
                    </div>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <button class="btn btn-primary pull-left" id="page2-back-button">Previous Page</button>

                </div>
            </div>
            <div class="col-xs-8" id="right-column">
                <div id="defenses-selected">
                    <div id="defense-title">
                        <h2 class="right-titles">Defenses Selected</h2>
                        <h3 class="right-small-titles">Select all defenses that your alliance has to cross.</h3>
                    </div>

                    <row>
                        <div class="col-xs-3" id="defense-catA">
                            <button id="cheval-defense" class="btn btn-default image-button image-button-top defense-state-one"><img class="button-images" width="200" height="100" src="defense_resources/cheval.png"></button>
                            <button id="portcullis-defense" class="btn btn-default image-button image-button-bottom defense-state-one"><img class="button-images" width="200" height="100" src="defense_resources/portcullis.png"></button>
                        </div>

                        <div class="col-xs-3" id="defense-catB">
                            <button id="drawbridge-defense" class="btn btn-default image-button image-button-top defense-state-one"><img class="button-images" width="200" height="100" src="defense_resources/drawbridge.png"></button>
                            <button id="sallyport-defense" class="btn btn-default image-button image-button-bottom defense-state-one"><img class="button-images" width="200" height="100" src="defense_resources/sallyport.png"></button>
                        </div>
                    </row>

                    <row>
                        <div class="col-xs-3" id="defense-catC">
                            <button id="moat-defense" class="btn btn-default image-button image-button-top defense-state-one"><img class="button-images" width="200" height="100" src="defense_resources/moat.png"></button>
                            <button id="ramparts-defense" class="btn btn-default image-button image-button-bottom defense-state-one"><img class="button-images" width="200" height="100" src="defense_resources/ramparts.png"></button>
                        </div>
                        <div class="col-xs-3" id="defense-catD">
                            <button id="rockwall-defense" class="btn btn-default image-button image-button-top defense-state-one"><img class="button-images" width="200" height="100" src="defense_resources/rockwall.png"></button>
                            <button id="roughterrain-defense" class="btn btn-default image-button image-button-bottom defense-state-one"><img class="button-images" width="200" height="100" src="defense_resources/roughterrain.png"></button>
                        </div>
                    </row>
                </div>

                <div id="auton">
                    <div id="auton-title">
                        <h2 class="right-titles">Autonomous</h2>
                        <h3 class="right-small-titles">Select the defense the robot crosses. Gray = Did Nothing, <span class="green"> Green = Reached Defense</span>,  <span class="red"> Red = Crossed Defense</span>.</h3>
                    </div>
                    <row>
                        <div class="col-xs-3" id="auton-catA">
                            <button id="cheval-auton" class="btn btn-default image-button image-button-top auton-state-one"><img class="button-images" width="200" height="100" src="defense_resources/cheval.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="cheval-auto-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="cheval-auto-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                            <button id="portcullis-auton" class="btn btn-default image-button image-button-bottom auton-state-one"><img class="button-images" width="200" height="100" src="defense_resources/portcullis.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="portcullis-auto-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="portcullis-auto-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                        </div>
                        <div class="col-xs-3" id="auton-catB">
                            <button id="drawbridge-auton" class="btn btn-default image-button image-button-top auton-state-one"><img class="button-images" width="200" height="100" src="defense_resources/drawbridge.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="drawbridge-auto-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="drawbridge-auto-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                            <button id="sallyport-auton" class="btn btn-default image-button image-button-bottom auton-state-one"><img class="button-images" width="200" height="100" src="defense_resources/sallyport.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="sallyport-auto-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="sallyport-auto-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                        </div>
                    </row>

                    <row>
                        <div class="col-xs-3" id="auton-catC">
                            <button id="moat-auton" class="btn btn-default image-button image-button-top auton-state-one"><img class="button-images" width="200" height="100" src="defense_resources/moat.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="moat-auto-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="moat-auto-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                            <button id="ramparts-auton" class="btn btn-default image-button image-button-bottom auton-state-one"><img class="button-images" width="200" height="100" src="defense_resources/ramparts.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="ramparts-auto-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="ramparts-auto-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                        </div>
                        <div class="col-xs-3" id="auton-catD">
                            <button id="rockwall-auton" class="btn btn-default image-button image-button-top auton-state-one"><img class="button-images" width="200" height="100" src="defense_resources/rockwall.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="rockwall-auto-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="rockwall-auto-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                            <button id="roughterrain-auton" class="btn btn-default image-button image-button-bottom auton-state-one"><img class="button-images" width="200" height="100" src="defense_resources/roughterrain.png"></button>
                            <div class="checkbox">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value="" id="roughterrain-auto-poor">
                                        Crosses poorly
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value="" id="roughterrain-auto-fail">
                                        Attempted to Cross (failed)
                                    </label>
                                </div>
                            </div>
                        </div>
                    </row>
                    <div class="row" id="lowbar-top">
                        <div style="display: inline-block; margin-left: 15px">
                            <button type="button" style="width: 80px; height: 80px" class="btn btn-default image-button image-button-top auton-state-one" id="lowbar-auton-button">Low Bar</button>
                        </div>
                        <div style="display: inline-block; padding-left: 20px">
                            <div class="lowbar-checkboxes">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value="" id="lowbar-auto-poor">
                                        Crosses poorly
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value="" id="lowbar-auto-fail">
                                        Attempted to Cross (failed)
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <div id="teleop">
                    <div id="teleop-title">
                        <h2 class="right-titles">Teleop</h2>
                        <h3 class="right-small-titles">Select how many times the robot has crossed a defense. Gray = Did Nothing, <span class="green"> Green = Crossed Defense</span>,  <span class="red"> Red = Damaged Defense</span>.</h3>
                    </div>
                    <row>
                        <div class="col-xs-3" id="teleop-catA">
                            <button id="cheval-teleop" class="btn btn-default image-button image-button-top teleop-state-one"><img class="button-images" width="200" height="100" src="defense_resources/cheval.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="cheval-tele-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="cheval-tele-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                            <button id="portcullis-teleop" class="btn btn-default image-button image-button-bottom teleop-state-one"><img class="button-images" width="200" height="100" src="defense_resources/portcullis.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="portcullis-tele-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="portcullis-tele-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                        </div>
                        <div class="col-xs-3" id="teleop-catB">
                            <button id="drawbridge-teleop" class="btn btn-default image-button image-button-top teleop-state-one"><img class="button-images" width="200" height="100" src="defense_resources/drawbridge.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="drawbridge-tele-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="drawbridge-tele-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                            <button id="sallyport-teleop" class="btn btn-default image-button image-button-bottom teleop-state-one"><img class="button-images" width="200" height="100" src="defense_resources/sallyport.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="sallyport-tele-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="sallyport-tele-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                        </div>
                    </row>

                    <row>
                        <div class="col-xs-3" id="teleop-catC">
                            <button id="moat-teleop" class="btn btn-default image-button image-button-top teleop-state-one"><img class="button-images" width="200" height="100" src="defense_resources/moat.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="moat-tele-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="moat-tele-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                            <button id="ramparts-teleop" class="btn btn-default image-button image-button-bottom teleop-state-one"><img class="button-images" width="200" height="100" src="defense_resources/ramparts.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="ramparts-tele-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="ramparts-tele-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                        </div>
                        <div class="col-xs-3" id="teleop-catD">
                            <button id="rockwall-teleop" class="btn btn-default image-button image-button-top teleop-state-one"><img class="button-images" width="200" height="100" src="defense_resources/rockwall.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="rockwall-tele-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="rockwall-tele-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>
                            <button id="roughterrain-teleop" class="btn btn-default image-button image-button-bottom teleop-state-one"><img class="button-images" width="200" height="100" src="defense_resources/roughterrain.png"></button>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="roughterrain-tele-poor">
                                    Crosses poorly
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="roughterrain-tele-fail">
                                    Attempted to Cross (failed)
                                </label>
                            </div>

                        </div>
                    </row>
                    <div class="row" id="lowbar-bottom">
                        <div style="display: inline-block; margin-left: 15px">
                            <button type="button" style="width: 80px; height: 80px" class="btn btn-default image-button image-button-top teleop-state-one" id="lowbar-teleop-button">Low Bar</button>
                        </div>
                        <div style="display: inline-block; padding-left: 20px">
                            <div class="lowbar-checkboxes">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value="" id="lowbar-tele-poor">
                                        Crosses poorly
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value="" id="lowbar-tele-fail">
                                        Attempted to Cross (failed)
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>

                <row>
                    <div class="col-xs-2" id="right-bottom-column">
                        <h4 id="fouls-title">Major&nbsp;Fouls?</h4>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" id="major-yellow">
                                Yellow&nbsp;Card
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" id="major-red">
                                Red&nbsp;Card
                            </label>
                        </div>

                        <div id="number-fouls">Number&nbsp;of&nbsp;Technical&nbsp;Fouls</div>
                        <input class="form-control" type="number" id="tech-foul" placeholder="Number of Fouls"/>

                    </div>

                    <div class="col-xs-4" id="right-bottom-column-right" style="padding-left: 150px">
                        <h4 id="comment-title">Comments:</h4>
                        <textarea id="comment-input" class="form-control" rows="1"></textarea>
                    </div>

                </row>
                <row>
                    <div class="col-xs-12" id="right-bottom-column">
                        <h3 id="reminder-title">Remember to fill out the Robot Function category and any other categories you missed before submitting!</h3>
                    </div>
                    <input class="btn btn-primary btn-lg btn-block" id="submit-button" type="submit" value="Submit">
                </row>

            </div>
        </div>
    </fieldset>
</form>
</body>
</html>