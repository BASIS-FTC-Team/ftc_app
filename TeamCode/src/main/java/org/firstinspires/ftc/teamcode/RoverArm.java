package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.Map;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Config;
import com.qualcomm.robotcore.util.Range;


public class RoverArm {
    private DcMotor verticalMotor;
    //private DcMotor verticalMotor2;

    private DigitalChannel touchSensor;
    double LIFT_POWER = 0.30;

    double container_position = 0.20;
    HardwareMap hwMap = null;
    public ElapsedTime time = new ElapsedTime();

    public void init(HardwareMap Map, Config config) {
        hwMap = Map;
        verticalMotor = hwMap.get(DcMotor.class, "rover_elevator");
        //verticalMotor2 = hwMap.get(DcMotor.class, "rover_elevator2");
        touchSensor = hwMap.get(DigitalChannel.class, "elevatortouch");
        verticalMotor.setDirection(DcMotor.Direction.REVERSE);
        //verticalMotor2.setDirection(DcMotor.Direction.REVERSE);
//    verticalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//    verticalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        LIFT_POWER = config.getDouble("lift_power", 0.30);

        verticalMotor.setDirection(DcMotor.Direction.FORWARD);
        verticalMotor.setPower(0);
        //verticalMotor2.setDirection(DcMotor.Direction.FORWARD);
        //verticalMotor2.setPower(0);
    }


    public int getLiftPosition() {
        return verticalMotor.getCurrentPosition();
    }

    public void setLiftZeroPosition() {
        verticalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //verticalMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    public void moveUpOrDown(double power) {
        verticalMotor.setDirection(DcMotor.Direction.FORWARD);
        verticalMotor.setPower(power);
        //verticalMotor2.setDirection(DcMotor.Direction.FORWARD);
        //verticalMotor2.setPower(power);
    }

    public void moveUp() {
        verticalMotor.setDirection(DcMotor.Direction.FORWARD);
        verticalMotor.setPower(-1.0 * LIFT_POWER);
//        verticalMotor2.setDirection(DcMotor.Direction.FORWARD);
//        verticalMotor2.setPower(-1.0 * LIFT_POWER);
    }

    public void moveDown() {
        verticalMotor.setDirection(DcMotor.Direction.FORWARD);
        verticalMotor.setPower(LIFT_POWER);
//        verticalMotor2.setDirection(DcMotor.Direction.FORWARD);
//        verticalMotor2.setPower(LIFT_POWER);
    }

    public void stop() {
        verticalMotor.setDirection(DcMotor.Direction.FORWARD);
        verticalMotor.setPower(0);
//        verticalMotor2.setDirection(DcMotor.Direction.FORWARD);
//        verticalMotor2.setPower(0);
    }

    public boolean isTouched() {
        return !touchSensor.getState();
    }
}
