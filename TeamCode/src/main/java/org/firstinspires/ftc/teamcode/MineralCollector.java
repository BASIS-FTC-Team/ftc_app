package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.util.Config;

public class MineralCollector {
    private CRServo servo1;  // Rotate to swipe in
    private Servo servo2;  // Turn over the holder

    double HOLDER_OPEN = 0.65;
    double HOLDER_CLOSED = 0.23;

    double SWIPE_SPEED = 0.99;
    boolean isRunning = false;
    boolean isSwipeOut = false;



    HardwareMap hwMap = null;
    public ElapsedTime time = new ElapsedTime();

    public void init(HardwareMap Map, Config config) {

        HOLDER_CLOSED = config.getDouble("holder_closed_pos", 0.23);
        HOLDER_OPEN = config.getDouble("holder_open_pos", 0.65);
        SWIPE_SPEED = config.getDouble("swipe_rotation_speed", 0.99);

        hwMap = Map;
        servo1 = hwMap.crservo.get("swipe_servo");
        servo2 = hwMap.get(Servo.class, "turn_servo");

        servo1.setPower(0);
        servo2.setPosition(HOLDER_CLOSED);

    }

    public void swipeIn() {
        servo1.setDirection(CRServo.Direction.FORWARD);
        servo1.setPower(SWIPE_SPEED);
        isSwipeOut = false;
        isRunning = true;
    }

    public void swipeOut() {
        servo1.setDirection(CRServo.Direction.REVERSE);
        servo1.setPower(SWIPE_SPEED);
        isSwipeOut = true;
        isRunning = true;
    }

    public void swipeStop() {
        servo1.setPower(0);
    }

    public void swipePause() {
        isRunning = isRunning?false:true;
        if(isRunning){
            if(isSwipeOut) swipeOut();
            else swipeIn();
        }
        else swipeStop();
    }

    public void swipeInOut(float power) {
        float speed = power;
        servo1.setPower(Range.clip(speed,-1.0,1.0));
    }

    public void openHolder(){
        servo2.setPosition(HOLDER_OPEN);
    }

    public void closeHolder() {
        servo2.setPosition(HOLDER_CLOSED);
    }

}
