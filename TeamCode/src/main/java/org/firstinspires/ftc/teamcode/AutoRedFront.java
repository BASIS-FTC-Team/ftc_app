package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.Config;
import org.firstinspires.ftc.teamcode.util.telemetry.TelemetryWrapper;

import javax.microedition.khronos.opengles.GL;


@Autonomous(name = "AutoRedFront")
public class AutoRedFront extends LinearOpMode {
    //define the parts that will be moving

    private ElapsedTime runtime = new ElapsedTime();
    //color of the team
    String teamColor = "RED";
    String foundColor = null;
    //ColorSensor colorSensor;
    private Config config = new Config(Config.configFile);

    @Override
    public void runOpMode() {

        double kickColorTime1 = config.getDouble("red_front_kick_color_time1",1);
        double kickColorPower1 = config.getDouble("red_front_kick_color_power1",0.45);
        double kickColorTime2 = config.getDouble("red_front_kick_color_time2",1);
        double kickColorPower2 = config.getDouble("red_front_kick_color_power2",0.45);
        double timeToRunLeft = config.getDouble("red_front_time_to_run_left",4);
        double timeToRunCenter = config.getDouble("red_front_time_to_run_center",3.5);
        double timeToRunRight = config.getDouble("red_front_time_to_run_right",3);
        double timeToRunPower = config.getDouble("red_front_time_to_run_power",-0.2);
        double turnTime = config.getDouble("red_front_turn_time",3);
        double turnPower = config.getDouble("red_front_turn_power",-0.2);
        double approachTime = config.getDouble("red_front_approach_time",1);
        double approachPower = config.getDouble("red_front_approach_power",0.2);
        double adjustPosTime = config.getDouble("red_front_adjust_pos_time",1);
        double adjustPosPower = config.getDouble("red_front_adjust_pos_power",0.2);

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.UNKNOWN;

        //waiting for user to press start
        waitForStart();

        TelemetryWrapper.init(telemetry,7);


        runtime.reset();

//        while (opModeIsActive() && (runtime.seconds() < 10)) {
//            vuMark = vuMarkID.getVuMark();
//            TelemetryWrapper.setLine(0,"Elapsed "+runtime.seconds());
//            if(vuMark!=RelicRecoveryVuMark.UNKNOWN){
//                TelemetryWrapper.setLine(1,"vuMark ID is "+vuMark +""+ vuMarkID.format(vuMarkID.pose));
//                vuMarkID.deactivate();
//                break;
//            }
//            else
//                TelemetryWrapper.setLine(1,"vuMark ID is invisible, wait for recognizing ....");
//
//        }
        runtime.reset();

        //turn on color sensor light
        //colorSensor.enableLed(true);
        //drop jewel arm so color sensor can detect
        runtime.reset();
        TelemetryWrapper.setLine(2,"" + opModeIsActive() + runtime.seconds());
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            TelemetryWrapper.setLine(3,"Entered Arm Program");
            jewelArm.openJewelArm();
            //returns which color the jewel is
        }
        jewelArm.kickJewel();
        /////////////
        //move to crypto
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            glyphArm.closeContainer();
        }

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            TelemetryWrapper.setLine(6,"Sucking");
            glyphCatcher.sucking(true);
        }
        glyphCatcher.sucking(false);



        double timeToRun = timeToRunCenter;
        if(vuMark == RelicRecoveryVuMark.LEFT) timeToRun = timeToRunLeft;
        else  if(vuMark == RelicRecoveryVuMark.RIGHT) timeToRun = timeToRunRight;
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < timeToRun)) {
            driveTrain.move(0,timeToRunPower, 0);
        }
        //turn to crypto box
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < turnTime)) {
            driveTrain.move(0,0.0, turnPower);
        }
        driveTrain.stop();
        //move close to crypt box
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < approachTime)) {
            driveTrain.move(0,approachPower, 0);
        }
        driveTrain.stop();

        //open container
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            glyphArm.openContainer();
        }
        //glyphArm.closeContainer();
        //move in all the way
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < adjustPosTime)) {
            driveTrain.move(0, adjustPosPower, 0.0);
        }
        driveTrain.stop();
        //To stop the drive train
        
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            driveTrain.move(0,-approachPower, 0);
        }
        
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < adjustPosTime+0.7)) {
            driveTrain.move(0, adjustPosPower, 0.0);
        }
        driveTrain.stop();
        //To stop the drive train
        
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.2)) {
            driveTrain.move(0,-approachPower, 0);
        }
        
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            driveTrain.stop();
        }

        driveTrain.stop();
    }
}