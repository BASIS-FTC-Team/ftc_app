package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.util.Config;
import java.util.Map;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Config;
import com.qualcomm.robotcore.util.Range;


public class ForeArm {
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;

    double FOREARM_UPDOWN_POWER = 0.30;
    double FOREARM_FORTHBACK_POWER = 0.30;

    HardwareMap hwMap = null;
    public ElapsedTime time = new ElapsedTime();

    public void init(HardwareMap Map, Config config) {
        hwMap = Map;
        motor1 = hwMap.get(DcMotor.class, "forearm1");
        motor2 = hwMap.get(DcMotor.class, "forearm2");
        motor3 = hwMap.get(DcMotor.class, "forearm3");

        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor2.setDirection(DcMotor.Direction.REVERSE);
        motor1.setPower(0);
        motor2.setPower(0);

        motor3.setDirection(DcMotor.Direction.FORWARD);
        motor3.setPower(0);

        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FOREARM_UPDOWN_POWER = config.getDouble("forearm_updown_power", 0.30);
        FOREARM_FORTHBACK_POWER = config.getDouble("orearm_forthback_power",0.30);

    }


//    public int getLiftPosition() {
//        return motor1.getCurrentPosition();
//    }

//    public void setForearmZeroPosition() {
//        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//    }


    public void moveUpDown(double power) {
        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor1.setPower(power);
        motor2.setDirection(DcMotor.Direction.REVERSE);
        motor2.setPower(power);
    }

    public void moveUp() {
        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor1.setPower(-1.0 * FOREARM_UPDOWN_POWER);
        motor2.setDirection(DcMotor.Direction.REVERSE);
        motor2.setPower(-1.0 * FOREARM_UPDOWN_POWER);

    }

    public void moveDown() {

        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor1.setPower(1.0 * FOREARM_UPDOWN_POWER);
        motor2.setDirection(DcMotor.Direction.REVERSE);
        motor2.setPower(1.0 * FOREARM_UPDOWN_POWER);

    }

    public void stopUpDown() {
        motor1.setPower(0);
        motor2.setPower(0);
    }
    public void stopForthBack() {
        motor3.setPower(0);
    }


    public void moveForward() {

        motor3.setDirection(DcMotor.Direction.FORWARD);
        motor3.setPower(1.0 * FOREARM_FORTHBACK_POWER);
    }

    public void moveBackward() {

        motor3.setDirection(DcMotor.Direction.REVERSE);
        motor3.setPower(1.0 * FOREARM_FORTHBACK_POWER);
    }

    public void moveForthBack(double power) {
        motor3.setDirection(DcMotor.Direction.FORWARD);
        motor3.setPower(power);

    }


}
