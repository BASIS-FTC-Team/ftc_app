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

    double FOREARM_POWER = 0.30;

    double container_position = 0.20;
    HardwareMap hwMap = null;
    public ElapsedTime time = new ElapsedTime();

    public void init(HardwareMap Map, Config config) {
        hwMap = Map;
        motor1 = hwMap.get(DcMotor.class, "forearm1");
        motor2 = hwMap.get(DcMotor.class, "forearm2");
        //touchSensor = hwMap.get(DigitalChannel.class, "elevator_touch");
        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor2.setDirection(DcMotor.Direction.REVERSE);
        motor1.setPower(0);
        motor2.setPower(0);

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FOREARM_POWER = config.getDouble("forearm_power", 0.30);

    }


//    public int getLiftPosition() {
//        return motor1.getCurrentPosition();
//    }

    public void setForearmZeroPosition() {
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    public void moveUpDown(double power) {
        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor1.setPower(power);
        motor2.setDirection(DcMotor.Direction.REVERSE);
        motor2.setPower(power);
    }

    public void moveUp() {
        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor1.setPower(-1.0 * FOREARM_POWER);
        motor1.setDirection(DcMotor.Direction.REVERSE);
        motor1.setPower(-1.0 * FOREARM_POWER);

    }

    public void moveDown() {

        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor1.setPower(1.0 * FOREARM_POWER);
        motor1.setDirection(DcMotor.Direction.REVERSE);
        motor1.setPower(1.0 * FOREARM_POWER);

    }

    public void stop() {
        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor1.setPower(0);
        motor1.setDirection(DcMotor.Direction.REVERSE);
        motor1.setPower(0);

    }

}
