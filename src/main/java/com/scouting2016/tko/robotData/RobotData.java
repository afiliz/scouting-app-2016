package com.scouting2016.tko.robotData;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

/**
 * Created by rahul on 2/24/16.
 * This data serves as template for initially grabbing the Scouter data.
 */

@JsonAutoDetect
public class RobotData {
    private String match_num;
    private int team_num;
    private String alliance_color;
    private int auton_high_goal;
    private int auton_low_goal;
    private String robot_type;
    private String robot_speed;
    private String intake_speed;
    private String shooting_speed;
    private int teleop_high_goal;
    private int teleop_low_goal;
    private int scores_from_mult_loc;
    private int tower_captured;
    private int goes_up_ramp;
    private int climbs;
    private int climbs_failed;
    private int climbs_poorly;
    private int broken;
    private int no_show;
    private int cheval_selected;
    private int drawbridge_selected;
    private int rock_wall_selected;
    private int moat_selected;
    private int portcullis_selected;
    private int sally_port_selected;
    private int rampart_selected;
    private int rough_terrain_selected;
    private int auton_cheval;
    private int cheval_auto_poor;
    private int cheval_auto_fail;
    private int auton_drawbridge;
    private int drawbridge_auto_poor;
    private int drawbridge_auto_fail;
    private int auton_rock_wall;
    private int rock_wall_auto_poor;
    private int rock_wall_auto_fail;
    private int auton_moat;
    private int moat_auto_poor;
    private int moat_auto_fail;
    private int auton_portcullis;
    private int portcullis_auto_poor;
    private int portcullis_auto_fail;
    private int auton_sally_port;
    private int sally_port_auto_poor;
    private int sally_port_auto_fail;
    private int auton_rampart;
    private int rampart_auto_poor;
    private int rampart_auto_fail;
    private int auton_rough_terrain;
    private int rough_terrain_auto_poor;
    private int rough_terrain_auto_fail;
    private int auton_low_bar;
    private int low_bar_auto_poor;
    private int low_bar_auto_fail;
    private int teleop_cheval;
    private int cheval_tele_poor;
    private int cheval_tele_fail;
    private int teleop_drawbridge;
    private int drawbridge_tele_poor;
    private int drawbridge_tele_fail;
    private int teleop_rock_wall;
    private int rock_wall_tele_poor;
    private int rock_wall_tele_fail;
    private int teleop_moat;
    private int moat_tele_poor;
    private int moat_tele_fail;
    private int teleop_portcullis;
    private int portcullis_tele_poor;
    private int portcullis_tele_fail;
    private int teleop_sally_port;
    private int sally_port_tele_poor;
    private int sally_port_tele_fail;
    private int teleop_rampart;
    private int rampart_tele_poor;
    private int rampart_tele_fail;
    private int teleop_rough_terrain;
    private int rough_terrain_tele_poor;
    private int rough_terrain_tele_fail;
    private int teleop_low_bar;
    private int low_bar_tele_poor;
    private int low_bar_tele_fail;
    private int yellow_card;
    private int red_card;
    private int num_tech_fouls;
    private String comments;
    private String name;
    private int team_score;
    private String event_code;
    private String uuid;

    public String getMatch_num() {
        return match_num;
    }

    public void setMatch_num(String match_num) { this.match_num = match_num; }

    public int getTeam_num() {
        return team_num;
    }

    public void setTeam_num(int team_num) {
        this.team_num = team_num;
    }

    public String getAlliance_color() {
        return alliance_color;
    }

    public void setAlliance_color(String alliance_color) {
        this.alliance_color = alliance_color;
    }

    public int getAuton_high_goal() {
        return auton_high_goal;
    }

    public void setAuton_high_goal(int auton_high_goal) {
        this.auton_high_goal = auton_high_goal;
    }

    public int getAuton_low_goal() {
        return auton_low_goal;
    }

    public void setAuton_low_goal(int auton_low_goal) {
        this.auton_low_goal = auton_low_goal;
    }

    public String getRobot_type() {
        return robot_type;
    }

    public void setRobot_type(String robot_type) {
        this.robot_type = robot_type;
    }

    public String getRobot_speed() {
        return robot_speed;
    }

    public void setRobot_speed(String robot_speed) {
        this.robot_speed = robot_speed;
    }

    public String getIntake_speed() {
        return intake_speed;
    }

    public void setIntake_speed(String intake_speed) {
        this.intake_speed = intake_speed;
    }

    public String getShooting_speed() {
        return shooting_speed;
    }

    public void setShooting_speed(String shooting_speed) {
        this.shooting_speed = shooting_speed;
    }

    public int getTeleop_high_goal() {
        return teleop_high_goal;
    }

    public void setTeleop_high_goal(int teleop_high_goal) {
        this.teleop_high_goal = teleop_high_goal;
    }

    public int getTeleop_low_goal() {
        return teleop_low_goal;
    }

    public void setTeleop_low_goal(int teleop_low_goal) {
        this.teleop_low_goal = teleop_low_goal;
    }

    public int getScores_from_mult_loc() {
        return scores_from_mult_loc;
    }

    public void setScores_from_mult_loc(int scores_from_mult_loc) {
        this.scores_from_mult_loc = scores_from_mult_loc;
    }

    public int getTower_captured() {
        return tower_captured;
    }

    public void setTower_captured(int tower_captured) {
        this.tower_captured = tower_captured;
    }

    public int getGoes_up_ramp() {
        return goes_up_ramp;
    }

    public void setGoes_up_ramp(int goes_up_ramp) {
        this.goes_up_ramp = goes_up_ramp;
    }

    public int getClimbs() {
        return climbs;
    }

    public void setClimbs(int climbs) {
        this.climbs = climbs;
    }

    public int getClimbs_failed() {
        return climbs_failed;
    }

    public void setClimbs_failed(int climbs_failed) {
        this.climbs_failed = climbs_failed;
    }

    public int getClimbs_poorly() {
        return climbs_poorly;
    }

    public void setClimbs_poorly(int climbs_poorly) {
        this.climbs_poorly = climbs_poorly;
    }

    public int getBroken() {
        return broken;
    }

    public void setBroken(int broken) {
        this.broken = broken;
    }

    public int getNo_show() {
        return no_show;
    }

    public void setNo_show(int no_show) {
        this.no_show = no_show;
    }

    public int getCheval_selected() {
        return cheval_selected;
    }

    public void setCheval_selected(int cheval_selected) {
        this.cheval_selected = cheval_selected;
    }

    public int getDrawbridge_selected() {
        return drawbridge_selected;
    }

    public void setDrawbridge_selected(int drawbridge_selected) {
        this.drawbridge_selected = drawbridge_selected;
    }

    public int getRock_wall_selected() {
        return rock_wall_selected;
    }

    public void setRock_wall_selected(int rock_wall_selected) {
        this.rock_wall_selected = rock_wall_selected;
    }

    public int getMoat_selected() {
        return moat_selected;
    }

    public void setMoat_selected(int moat_selected) {
        this.moat_selected = moat_selected;
    }

    public int getPortcullis_selected() {
        return portcullis_selected;
    }

    public void setPortcullis_selected(int portcullis_selected) {
        this.portcullis_selected = portcullis_selected;
    }

    public int getSally_port_selected() {
        return sally_port_selected;
    }

    public void setSally_port_selected(int sally_port_selected) {
        this.sally_port_selected = sally_port_selected;
    }

    public int getRampart_selected() {
        return rampart_selected;
    }

    public void setRampart_selected(int rampart_selected) {
        this.rampart_selected = rampart_selected;
    }

    public int getRough_terrain_selected() {
        return rough_terrain_selected;
    }

    public void setRough_terrain_selected(int rough_terrain_selected) {
        this.rough_terrain_selected = rough_terrain_selected;
    }

    public int getAuton_cheval() {
        return auton_cheval;
    }

    public void setAuton_cheval(int auton_cheval) {
        this.auton_cheval = auton_cheval;
    }

    public int getCheval_auto_poor() {
        return cheval_auto_poor;
    }

    public void setCheval_auto_poor(int cheval_auto_poor) {
        this.cheval_auto_poor = cheval_auto_poor;
    }

    public int getCheval_auto_fail() {
        return cheval_auto_fail;
    }

    public void setCheval_auto_fail(int cheval_auto_fail) {
        this.cheval_auto_fail = cheval_auto_fail;
    }

    public int getAuton_drawbridge() {
        return auton_drawbridge;
    }

    public void setAuton_drawbridge(int auton_drawbridge) {
        this.auton_drawbridge = auton_drawbridge;
    }

    public int getDrawbridge_auto_poor() {
        return drawbridge_auto_poor;
    }

    public void setDrawbridge_auto_poor(int drawbridge_auto_poor) {
        this.drawbridge_auto_poor = drawbridge_auto_poor;
    }

    public int getDrawbridge_auto_fail() {
        return drawbridge_auto_fail;
    }

    public void setDrawbridge_auto_fail(int drawbridge_auto_fail) {
        this.drawbridge_auto_fail = drawbridge_auto_fail;
    }

    public int getAuton_rock_wall() {
        return auton_rock_wall;
    }

    public void setAuton_rock_wall(int auton_rock_wall) {
        this.auton_rock_wall = auton_rock_wall;
    }

    public int getRock_wall_auto_poor() {
        return rock_wall_auto_poor;
    }

    public void setRock_wall_auto_poor(int rock_wall_auto_poor) {
        this.rock_wall_auto_poor = rock_wall_auto_poor;
    }

    public int getRock_wall_auto_fail() {
        return rock_wall_auto_fail;
    }

    public void setRock_wall_auto_fail(int rock_wall_auto_fail) {
        this.rock_wall_auto_fail = rock_wall_auto_fail;
    }

    public int getAuton_moat() {
        return auton_moat;
    }

    public void setAuton_moat(int auton_moat) {
        this.auton_moat = auton_moat;
    }

    public int getMoat_auto_poor() {
        return moat_auto_poor;
    }

    public void setMoat_auto_poor(int moat_auto_poor) {
        this.moat_auto_poor = moat_auto_poor;
    }

    public int getMoat_auto_fail() {
        return moat_auto_fail;
    }

    public void setMoat_auto_fail(int moat_auto_fail) {
        this.moat_auto_fail = moat_auto_fail;
    }

    public int getAuton_portcullis() {
        return auton_portcullis;
    }

    public void setAuton_portcullis(int auton_portcullis) {
        this.auton_portcullis = auton_portcullis;
    }

    public int getPortcullis_auto_poor() {
        return portcullis_auto_poor;
    }

    public void setPortcullis_auto_poor(int portcullis_auto_poor) {
        this.portcullis_auto_poor = portcullis_auto_poor;
    }

    public int getPortcullis_auto_fail() {
        return portcullis_auto_fail;
    }

    public void setPortcullis_auto_fail(int portcullis_auto_fail) {
        this.portcullis_auto_fail = portcullis_auto_fail;
    }

    public int getAuton_sally_port() {
        return auton_sally_port;
    }

    public void setAuton_sally_port(int auton_sally_port) {
        this.auton_sally_port = auton_sally_port;
    }

    public int getSally_port_auto_poor() {
        return sally_port_auto_poor;
    }

    public void setSally_port_auto_poor(int sally_port_auto_poor) {
        this.sally_port_auto_poor = sally_port_auto_poor;
    }

    public int getSally_port_auto_fail() {
        return sally_port_auto_fail;
    }

    public void setSally_port_auto_fail(int sally_port_auto_fail) {
        this.sally_port_auto_fail = sally_port_auto_fail;
    }

    public int getAuton_rampart() {
        return auton_rampart;
    }

    public void setAuton_rampart(int auton_rampart) {
        this.auton_rampart = auton_rampart;
    }

    public int getRampart_auto_poor() {
        return rampart_auto_poor;
    }

    public void setRampart_auto_poor(int rampart_auto_poor) {
        this.rampart_auto_poor = rampart_auto_poor;
    }

    public int getRampart_auto_fail() {
        return rampart_auto_fail;
    }

    public void setRampart_auto_fail(int rampart_auto_fail) {
        this.rampart_auto_fail = rampart_auto_fail;
    }

    public int getAuton_rough_terrain() {
        return auton_rough_terrain;
    }

    public void setAuton_rough_terrain(int auton_rough_terrain) {
        this.auton_rough_terrain = auton_rough_terrain;
    }

    public int getRough_terrain_auto_poor() {
        return rough_terrain_auto_poor;
    }

    public void setRough_terrain_auto_poor(int rough_terrain_auto_poor) {
        this.rough_terrain_auto_poor = rough_terrain_auto_poor;
    }

    public int getRough_terrain_auto_fail() {
        return rough_terrain_auto_fail;
    }

    public void setRough_terrain_auto_fail(int rough_terrain_auto_fail) {
        this.rough_terrain_auto_fail = rough_terrain_auto_fail;
    }

    public int getAuton_low_bar() {
        return auton_low_bar;
    }

    public void setAuton_low_bar(int auton_low_bar) {
        this.auton_low_bar = auton_low_bar;
    }

    public int getLow_bar_auto_poor() {
        return low_bar_auto_poor;
    }

    public void setLow_bar_auto_poor(int low_bar_auto_poor) {
        this.low_bar_auto_poor = low_bar_auto_poor;
    }

    public int getLow_bar_auto_fail() {
        return low_bar_auto_fail;
    }

    public void setLow_bar_auto_fail(int low_bar_auto_fail) {
        this.low_bar_auto_fail = low_bar_auto_fail;
    }

    public int getTeleop_cheval() {
        return teleop_cheval;
    }

    public void setTeleop_cheval(int teleop_cheval) {
        this.teleop_cheval = teleop_cheval;
    }

    public int getCheval_tele_poor() {
        return cheval_tele_poor;
    }

    public void setCheval_tele_poor(int cheval_tele_poor) {
        this.cheval_tele_poor = cheval_tele_poor;
    }

    public int getCheval_tele_fail() {
        return cheval_tele_fail;
    }

    public void setCheval_tele_fail(int cheval_tele_fail) {
        this.cheval_tele_fail = cheval_tele_fail;
    }

    public int getTeleop_drawbridge() {
        return teleop_drawbridge;
    }

    public void setTeleop_drawbridge(int teleop_drawbridge) {
        this.teleop_drawbridge = teleop_drawbridge;
    }

    public int getDrawbridge_tele_poor() {
        return drawbridge_tele_poor;
    }

    public void setDrawbridge_tele_poor(int drawbridge_tele_poor) {
        this.drawbridge_tele_poor = drawbridge_tele_poor;
    }

    public int getDrawbridge_tele_fail() {
        return drawbridge_tele_fail;
    }

    public void setDrawbridge_tele_fail(int drawbridge_tele_fail) {
        this.drawbridge_tele_fail = drawbridge_tele_fail;
    }

    public int getTeleop_rock_wall() {
        return teleop_rock_wall;
    }

    public void setTeleop_rock_wall(int teleop_rock_wall) {
        this.teleop_rock_wall = teleop_rock_wall;
    }

    public int getRock_wall_tele_poor() {
        return rock_wall_tele_poor;
    }

    public void setRock_wall_tele_poor(int rock_wall_tele_poor) {
        this.rock_wall_tele_poor = rock_wall_tele_poor;
    }

    public int getRock_wall_tele_fail() {
        return rock_wall_tele_fail;
    }

    public void setRock_wall_tele_fail(int rock_wall_tele_fail) {
        this.rock_wall_tele_fail = rock_wall_tele_fail;
    }

    public int getTeleop_moat() {
        return teleop_moat;
    }

    public void setTeleop_moat(int teleop_moat) {
        this.teleop_moat = teleop_moat;
    }

    public int getMoat_tele_poor() {
        return moat_tele_poor;
    }

    public void setMoat_tele_poor(int moat_tele_poor) {
        this.moat_tele_poor = moat_tele_poor;
    }

    public int getMoat_tele_fail() {
        return moat_tele_fail;
    }

    public void setMoat_tele_fail(int moat_tele_fail) {
        this.moat_tele_fail = moat_tele_fail;
    }

    public int getTeleop_portcullis() {
        return teleop_portcullis;
    }

    public void setTeleop_portcullis(int teleop_portcullis) {
        this.teleop_portcullis = teleop_portcullis;
    }

    public int getPortcullis_tele_poor() {
        return portcullis_tele_poor;
    }

    public void setPortcullis_tele_poor(int portcullis_tele_poor) {
        this.portcullis_tele_poor = portcullis_tele_poor;
    }

    public int getPortcullis_tele_fail() {
        return portcullis_tele_fail;
    }

    public void setPortcullis_tele_fail(int portcullis_tele_fail) {
        this.portcullis_tele_fail = portcullis_tele_fail;
    }

    public int getTeleop_sally_port() {
        return teleop_sally_port;
    }

    public void setTeleop_sally_port(int teleop_sally_port) {
        this.teleop_sally_port = teleop_sally_port;
    }

    public int getSally_port_tele_poor() {
        return sally_port_tele_poor;
    }

    public void setSally_port_tele_poor(int sally_port_tele_poor) {
        this.sally_port_tele_poor = sally_port_tele_poor;
    }

    public int getSally_port_tele_fail() {
        return sally_port_tele_fail;
    }

    public void setSally_port_tele_fail(int sally_port_tele_fail) {
        this.sally_port_tele_fail = sally_port_tele_fail;
    }

    public int getTeleop_rampart() {
        return teleop_rampart;
    }

    public void setTeleop_rampart(int teleop_rampart) {
        this.teleop_rampart = teleop_rampart;
    }

    public int getRampart_tele_poor() {
        return rampart_tele_poor;
    }

    public void setRampart_tele_poor(int rampart_tele_poor) {
        this.rampart_tele_poor = rampart_tele_poor;
    }

    public int getRampart_tele_fail() {
        return rampart_tele_fail;
    }

    public void setRampart_tele_fail(int rampart_tele_fail) {
        this.rampart_tele_fail = rampart_tele_fail;
    }

    public int getTeleop_rough_terrain() {
        return teleop_rough_terrain;
    }

    public void setTeleop_rough_terrain(int teleop_rough_terrain) {
        this.teleop_rough_terrain = teleop_rough_terrain;
    }

    public int getRough_terrain_tele_poor() {
        return rough_terrain_tele_poor;
    }

    public void setRough_terrain_tele_poor(int rough_terrain_tele_poor) {
        this.rough_terrain_tele_poor = rough_terrain_tele_poor;
    }

    public int getRough_terrain_tele_fail() {
        return rough_terrain_tele_fail;
    }

    public void setRough_terrain_tele_fail(int rough_terrain_tele_fail) {
        this.rough_terrain_tele_fail = rough_terrain_tele_fail;
    }

    public int getTeleop_low_bar() {
        return teleop_low_bar;
    }

    public void setTeleop_low_bar(int teleop_low_bar) {
        this.teleop_low_bar = teleop_low_bar;
    }

    public int getLow_bar_tele_poor() {
        return low_bar_tele_poor;
    }

    public void setLow_bar_tele_poor(int low_bar_tele_poor) {
        this.low_bar_tele_poor = low_bar_tele_poor;
    }

    public int getLow_bar_tele_fail() {
        return low_bar_tele_fail;
    }

    public void setLow_bar_tele_fail(int low_bar_tele_fail) {
        this.low_bar_tele_fail = low_bar_tele_fail;
    }

    public int getYellow_card() {
        return yellow_card;
    }

    public void setYellow_card(int yellow_card) {
        this.yellow_card = yellow_card;
    }

    public int getRed_card() {
        return red_card;
    }

    public void setRed_card(int red_card) {
        this.red_card = red_card;
    }

    public int getNum_tech_fouls() {
        return num_tech_fouls;
    }

    public void setNum_tech_fouls(int num_tech_fouls) {
        this.num_tech_fouls = num_tech_fouls;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getTeam_score() { return team_score; }

    public void setTeam_score(int team_score) { this.team_score = team_score; }

    public String getEvent_code() { return event_code; }

    public void setEvent_code(String event_code) { this.event_code = event_code; }

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }
}
