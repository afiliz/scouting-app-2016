/**
 * Created by Rahul Rameshbabu, Shreyas Vaidya, Adam Filiz
 */

/* Put variables and methods in here only if you need the,
 * or they can only work AFTER the document (a.k.a. the webpage) has been fully loaded.
 * Use the top of you need functions and variables to update at the moment the file is loaded by the webpage
 */

$(document).ready(function () {


    var currentScore;
    var currentCrossedScore;
    var teleop_hscore_textbox = document.getElementById('total-hscore');
    var teleop_lscore_textbox = document.getElementById('total-lscore');
    var crossed_poorly_textbox = document.getElementById('total-crosses');
    var auton_hscore_textbox = document.getElementById('auton-hscore');
    var auton_lscore_textbox = document.getElementById('auton-lscore');

    function plus5() {
        currentScore = parseInt(teleop_hscore_textbox.value) || 0;
        currentScore += 1;
        teleop_hscore_textbox.value = currentScore;
    }

    function plus2() {
        currentScore = parseInt(teleop_lscore_textbox.value) || 0;
        currentScore += 1;
        teleop_lscore_textbox.value = currentScore;
    }

    function plus1() {
        currentCrossedScore = parseInt(crossed_poorly_textbox.value) || 0;
        currentCrossedScore += 1;
        crossed_poorly_textbox.value = currentCrossedScore;
    }

    function minus5() {
        currentScore = parseInt(teleop_hscore_textbox.value) || 0;
        currentScore -= 1;
        currentScore < 0 ? currentScore=0 : currentScore;
        teleop_hscore_textbox.value = currentScore;
    }

    function minus2() {
        currentScore = parseInt(teleop_lscore_textbox.value) || 0;
        currentScore -= 1;
        currentScore < 0 ? currentScore=0 : currentScore;
        teleop_lscore_textbox.value = currentScore;
    }

    function minus1() {
        currentCrossedScore = parseInt(crossed_poorly_textbox.value) || 0;
        currentCrossedScore -= 1;
        currentCrossedScore < 0 ? currentCrossedScore=0 : currentCrossedScore;
        crossed_poorly_textbox.value = currentCrossedScore;
    }

    function autonHighPlus() {
        currentScore = parseInt(auton_hscore_textbox.value) || 0;
        currentScore += 1;
        auton_hscore_textbox.value = currentScore;
    }

    function autonHighMinus() {
        currentScore = parseInt(auton_hscore_textbox.value) || 0;
        currentScore -= 1;
        currentScore < 0 ? currentScore=0 : currentScore;
        auton_hscore_textbox.value = currentScore;
    }

    function autonLowPlus() {
        currentScore = parseInt(auton_lscore_textbox.value) || 0;
        currentScore += 1;
        auton_lscore_textbox.value = currentScore;
    }

    function autonLowMinus() {
        currentScore = parseInt(auton_lscore_textbox.value) || 0;
        currentScore -= 1;
        currentScore < 0 ? currentScore=0 : currentScore;
        auton_lscore_textbox.value = currentScore;
    }

    function autonPointCalc(autoElement) {
        if(autoElement.classList.contains('auton-state-one')) {
            return 0;
        }
        else if(autoElement.classList.contains('auton-state-two')) {
            return 1;
        }
        return 2;
    }

    function teleopPointCalc(teleopElement) {
        if(teleopElement.classList.contains('teleop-state-one')) {
            return 0;
        }
        else if(teleopElement.classList.contains('teleop-state-two')) {
            return 1;
        }
        return 2;
    }

    function calcTeamScore() {
        return (parseInt(document.getElementById('auton-lscore').value) || 0) * 5 +
            (parseInt(document.getElementById('auton-hscore').value) || 0) * 10 +
            (parseInt(document.getElementById('total-hscore').value) || 0) * 5 +
            (parseInt(document.getElementById('total-lscore').value) || 0) * 2 +
            ($('#gur').is(':checked') ? 1 : 0) * 5 +
            ($('#c').is(':checked') ? 1 : 0) * 15 +
            auton_def_score(autonPointCalc(document.getElementById('cheval-auton'))) +
            auton_def_score(autonPointCalc(document.getElementById('drawbridge-auton'))) +
            auton_def_score(autonPointCalc(document.getElementById('rockwall-auton'))) +
            auton_def_score(autonPointCalc(document.getElementById('moat-auton'))) +
            auton_def_score(autonPointCalc(document.getElementById('portcullis-auton'))) +
            auton_def_score(autonPointCalc(document.getElementById('sallyport-auton'))) +
            auton_def_score(autonPointCalc(document.getElementById('ramparts-auton'))) +
            auton_def_score(autonPointCalc(document.getElementById('roughterrain-auton'))) +
            auton_def_score(autonPointCalc(document.getElementById('lowbar-auton-button'))) +
            teleopPointCalc(document.getElementById('cheval-teleop')) * 5 +
            teleopPointCalc(document.getElementById('drawbridge-teleop')) * 5 +
            teleopPointCalc(document.getElementById('rockwall-teleop')) * 5 +
            teleopPointCalc(document.getElementById('moat-teleop')) * 5 +
            teleopPointCalc(document.getElementById('portcullis-teleop')) * 5 +
            teleopPointCalc(document.getElementById('sallyport-teleop')) * 5 +
            teleopPointCalc(document.getElementById('ramparts-teleop')) * 5 +
            teleopPointCalc(document.getElementById('roughterrain-teleop')) * 5 +
            teleopPointCalc(document.getElementById('lowbar-teleop-button')) * 5;
    }

    function auton_def_score(value) {
        if(value == 2) {
            return 10;
        }
        else if(value == 1) {
            return 2;
        }
        return 0;
    }

    $(document).on("click", '.defense-state-one',  function(e) {
        e.preventDefault();
        $(this).css('border-color', 'lime');
        $(this).addClass('defense-state-two');
        $(this).removeClass('defense-state-one');
    });

    $(document).on("click", '.defense-state-two',  function(e) {
        e.preventDefault();
        $(this).css('border-color', '');
        $(this).addClass('defense-state-one');
        $(this).removeClass('defense-state-two');
    });

    $(document).on("click", '.teleop-state-one',  function(e) {
        e.preventDefault();
        $(this).css('border-color', 'lime');
        $(this).addClass('teleop-state-two');
        $(this).removeClass('teleop-state-one');

    });

    $(document).on("click", '.teleop-state-two',  function(e) {
        e.preventDefault();
        $(this).css('border-color', 'red');
        $(this).addClass('teleop-state-three');
        $(this).removeClass('teleop-state-two');

    });

    $(document).on("click", '.teleop-state-three',  function(e) {
        e.preventDefault();
        $(this).css('border-color', '');
        $(this).addClass('teleop-state-one');
        $(this).removeClass('teleop-state-three');
    });

    $(document).on("click", '.auton-state-one',  function(e) {
        e.preventDefault();
        $(this).css('border-color', 'lime');
        $(this).addClass('auton-state-two');
        $(this).removeClass('auton-state-one');



    });

    $(document).on("click", '.auton-state-two',  function(e) {
        e.preventDefault();
        $(this).css('border-color', 'red');
        $(this).addClass('auton-state-three');
        $(this).removeClass('auton-state-two');

    });

    $(document).on("click", '.auton-state-three',  function(e) {
        e.preventDefault();
        $(this).css('border-color', '');
        $(this).addClass('auton-state-one');
        $(this).removeClass('auton-state-three');

    });

    $('#multipage').multipage({
        // your parameters here
        'stayLinkable': false,      // whether or not the id of the page will be appended to the url as a hashtag for permalinking purposes
        'submitLabel': 'Submit',    // default label for the last page's submit button, if not included in form
        'hideLegend': false,        // should the plugin hide the <legend> tags?  useful in concert with externally generated nav
        'hideSubmit': false,         // should the plugin hide the submit button?
        'generateNavigation': false, // should the plugin generate its own navigation buttons?
        'activeDot': '&nbsp;&#x25CF;',          // the dot used to represent an active page in the nav
        'inactiveDot': '&nbsp;&middot;',        // the dot used to represent an inactive page in the nav
    });

    $('#page1-button').click(function (e) {
       e.preventDefault();
        window.location.href = "index.jsp";
    });

    $('#page3-button').click(function(e) {
        e.preventDefault();
        $('#multipage').nextpage();
    });

    $('#page2-back-button').click(function(e) {
        e.preventDefault();
        $('#multipage').prevpage();
    });

    $('#submit-button').click(function(e) {

        e.preventDefault();

        // Variable to hold request
        var request;


            // Abort any pending request
            if (request) {
                request.abort();
            }
            // setup some local variables
            var $form = $(this);

            // Let's select and cache all the fields
            var $inputs = $form.find("input, select, button, textarea");

            // Serialize the data in the form
            var serializedData = {
                'match_num': document.getElementById('match-number').value,
                'team_num': parseInt(document.getElementById('team-number').value) || 0,
                'alliance_color': document.getElementById('alliance').value,
                'auton_high_goal': parseInt(document.getElementById('auton-hscore').value) || 0,
                'auton_low_goal': parseInt(document.getElementById('auton-lscore').value) || 0,
                'robot_type': $('#robot-type option:selected').map(
                    function(i){
                        return $(this).val();
                    }).get().join('='),
                'robot_speed': document.getElementById('robot-speed').options[document.getElementById('robot-speed').selectedIndex].text,
                'intake_speed': document.getElementById('intake-speed').options[document.getElementById('intake-speed').selectedIndex].text,
                'shooting_speed': document.getElementById('shooting-speed').options[document.getElementById('shooting-speed').selectedIndex].text,
                'teleop_high_goal': parseInt(document.getElementById('total-hscore').value) || 0,
                'teleop_low_goal': parseInt(document.getElementById('total-lscore').value) || 0,
                'scores_from_mult_loc': $('#sfml').is(':checked') ? 1 : 0,
                'tower_captured': $('#tc').is(':checked') ? 1 : 0,
                'goes_up_ramp': $('#gur').is(':checked') ? 1 : 0,
                'climbs': $('#c').is(':checked') ? 1 : 0,
                'climbs_failed': $('#cf').is(':checked') ? 1 : 0,
                'climbs_poorly': $('#cp').is(':checked') ? 1 : 0,
                'broken': $('#b').is(':checked') ? 1 : 0,
                'no_show': $('#ns').is(':checked') ? 1 : 0,
                'cheval_selected': document.getElementById('cheval-defense').classList.contains('defense-state-two') ? 1 : 0,
                'drawbridge_selected': document.getElementById('drawbridge-defense').classList.contains('defense-state-two') ? 1 : 0,
                'rock_wall_selected': document.getElementById('rockwall-defense').classList.contains('defense-state-two') ? 1 : 0,
                'moat_selected': document.getElementById('moat-defense').classList.contains('defense-state-two') ? 1 : 0,
                'portcullis_selected': document.getElementById('portcullis-defense').classList.contains('defense-state-two') ? 1 : 0,
                'sally_port_selected': document.getElementById('sallyport-defense').classList.contains('defense-state-two') ? 1 : 0,
                'rampart_selected': document.getElementById('ramparts-defense').classList.contains('defense-state-two') ? 1 : 0,
                'rough_terrain_selected': document.getElementById('roughterrain-defense').classList.contains('defense-state-two') ? 1 : 0,
                'auton_cheval': autonPointCalc(document.getElementById('cheval-auton')),
                'cheval_auto_poor': $('#cheval-auto-poor').is(':checked') ? 1 : 0,
                'cheval_auto_fail': $('#cheval-auto-fail').is(':checked') ? 1 : 0,
                'auton_drawbridge': autonPointCalc(document.getElementById('drawbridge-auton')),
                'drawbridge_auto_poor': $('#drawbridge-auto-poor').is(':checked') ? 1 : 0,
                'drawbridge_auto_fail': $('#drawbridge-auto-fail').is(':checked') ? 1 : 0,
                'auton_rock_wall': autonPointCalc(document.getElementById('rockwall-auton')),
                'rock_wall_auto_poor': $('#rockwall-auto-poor').is(':checked') ? 1 : 0,
                'rock_wall_auto_fail': $('#rockwall-auto-fail').is(':checked') ? 1 : 0,
                'auton_moat': autonPointCalc(document.getElementById('moat-auton')),
                'moat_auto_poor': $('#moat-auto-poor').is(':checked') ? 1 : 0,
                'moat_auto_fail': $('#moat-auto-fail').is(':checked') ? 1 : 0,
                'auton_portcullis': autonPointCalc(document.getElementById('portcullis-auton')),
                'portcullis_auto_poor': $('#portcullis-auto-poor').is(':checked') ? 1 : 0,
                'portcullis_auto_fail': $('#portcullis-auto-fail').is(':checked') ? 1 : 0,
                'auton_sally_port': autonPointCalc(document.getElementById('sallyport-auton')),
                'sally_port_auto_poor': $('#sallyport-auto-poor').is(':checked') ? 1 : 0,
                'sally_port_auto_fail': $('#sallyport-auto-fail').is(':checked') ? 1 : 0,
                'auton_rampart': autonPointCalc(document.getElementById('ramparts-auton')),
                'rampart_auto_poor': $('#ramparts-auto-poor').is(':checked') ? 1 : 0,
                'rampart_auto_fail': $('#ramparts-auto-fail').is(':checked') ? 1 : 0,
                'auton_rough_terrain': autonPointCalc(document.getElementById('roughterrain-auton')),
                'rough_terrain_auto_poor': $('#roughterrain-auto-poor').is(':checked') ? 1 : 0,
                'rough_terrain_auto_fail': $('#roughterrain-auto-fail').is(':checked') ? 1 : 0,
                'auton_low_bar': autonPointCalc(document.getElementById('lowbar-auton-button')),
                'low_bar_auto_poor': $('#lowbar-auto-poor').is(':checked') ? 1 : 0,
                'low_bar_auto_fail': $('#lowbar-auto-fail').is(':checked') ? 1 : 0,
                'teleop_cheval':  teleopPointCalc(document.getElementById('cheval-teleop')),
                'cheval_tele_poor': $('#cheval-tele-poor').is(':checked') ? 1 : 0,
                'cheval_tele_fail': $('#cheval-tele-fail').is(':checked') ? 1 : 0,
                'teleop_drawbridge': teleopPointCalc(document.getElementById('drawbridge-teleop')),
                'drawbridge_tele_poor': $('#drawbridge-tele-poor').is(':checked') ? 1 : 0,
                'drawbridge_tele_fail': $('#drawbridge-tele-fail').is(':checked') ? 1 : 0,
                'teleop_rock_wall': teleopPointCalc(document.getElementById('rockwall-teleop')),
                'rock_wall_tele_poor': $('#rockwall-tele-poor').is(':checked') ? 1 : 0,
                'rock_wall_tele_fail': $('#rockwall-tele-fail').is(':checked') ? 1 : 0,
                'teleop_moat': teleopPointCalc(document.getElementById('moat-teleop')),
                'moat_tele_poor': $('#moat-tele-poor').is(':checked') ? 1 : 0,
                'moat_tele_fail': $('#moat-tele-fail').is(':checked') ? 1 : 0,
                'teleop_portcullis': teleopPointCalc(document.getElementById('portcullis-teleop')),
                'portcullis_tele_poor': $('#portcullis-tele-poor').is(':checked') ? 1 : 0,
                'portcullis_tele_fail': $('#portcullis-tele-fail').is(':checked') ? 1 : 0,
                'teleop_sally_port': teleopPointCalc(document.getElementById('sallyport-teleop')),
                'sally_port_tele_poor': $('#sallyport-tele-poor').is(':checked') ? 1 : 0,
                'sally_port_tele_fail': $('#sallyport-tele-fail').is(':checked') ? 1 : 0,
                'teleop_rampart': teleopPointCalc(document.getElementById('ramparts-teleop')),
                'rampart_tele_poor': $('#ramparts-tele-poor').is(':checked') ? 1 : 0,
                'rampart_tele_fail': $('#ramparts-tele-fail').is(':checked') ? 1 : 0,
                'teleop_rough_terrain': teleopPointCalc(document.getElementById('roughterrain-teleop')),
                'rough_terrain_tele_poor': $('#roughterrain-tele-poor').is(':checked') ? 1 : 0,
                'rough_terrain_tele_fail': $('#roughterrain-tele-fail').is(':checked') ? 1 : 0,
                'teleop_low_bar': teleopPointCalc(document.getElementById('lowbar-teleop-button')),
                'low_bar_tele_poor': $('#lowbar-tele-poor').is(':checked') ? 1 : 0,
                'low_bar_tele_fail': $('#lowbar-tele-fail').is(':checked') ? 1 : 0,
                'yellow_card': $('#major-yellow').is(':checked') ? 1 : 0,
                'red_card': $('#major-red').is(':checked') ? 1 : 0,
                'num_tech_fouls': parseInt(document.getElementById('tech-foul').value) || 0,
                'comments': document.getElementById('comment-input').value,
                'name': document.getElementById('name').value,
                'team_score': calcTeamScore(),
                'event_code': document.getElementById('event-code').value,
                'uuid': document.getElementById('event-code').value + "-" + document.getElementById('match-number').value + "-" + parseInt(document.getElementById('team-number').value) || 0
            };

            // Let's disable the inputs for the duration of the Ajax request.
            // Note: we disable elements AFTER the form data has been serialized.
            // Disabled form elements will not be serialized.
            $inputs.prop("disabled", true);

            // Fire off the request to /form.php
        $.ajaxSetup({
            contentType: "application/json; charset=UTF-8"
        });
        request = $.ajax({
                url: "/collect",
                type: "post",
                contentType: "application/json",
                data: JSON.stringify(serializedData)
            });

            // Callback handler that will be called on success
            request.done(function (response, textStatus, jqXHR){
                // Log a message to the console
                console.log("Scouting data passed sucessfully!");
                //window.location.reload();
                window.location.href = "index.jsp";
            });

            // Callback handler that will be called on failure
            request.fail(function (jqXHR, textStatus, errorThrown){
                // Log the error to the console
                console.log(
                    "The following error occurred: "+
                    textStatus, errorThrown
                );
            });

            // Callback handler that will be called regardless
            // if the request failed or succeeded
            request.always(function () {
                // Reenable the inputs
                $inputs.prop("disabled", false);
            });

            // Prevent default posting of form
            event.preventDefault();
        });
    $('#plus-5').click(function(e) {
        plus5();
    });

    $('#plus-2').click(function(e) {
        plus2();
    });

    $('#minus-5').click(function(e) {
        minus5();
    });

    $('#minus-2').click(function(e) {
        minus2();
    });

    $('#minus-1').click(function(e) {
        minus1();
    });

    $('#plus-1').click(function(e) {
        plus1();
    });

    $('#auton-high-plus').click(function(e) {
        autonHighPlus();
    });

    $('#auton-high-minus').click(function(e) {
        autonHighMinus();
    });

    $('#auton-low-plus').click(function(e) {
        autonLowPlus();
    });

    $('#auton-low-minus').click(function(e) {
        autonLowMinus();
    });

    $(".btn").click(function(e) {
        // Removes focus of the button.
        $(this).blur();
    });
});

