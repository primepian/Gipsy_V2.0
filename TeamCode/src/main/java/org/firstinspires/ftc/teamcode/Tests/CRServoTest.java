package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
@Disabled
public class CRServoTest extends LinearOpMode {
    public CRServo servo;

    public void runOpMode(){
        servo = hardwareMap.get(CRServo.class, "servo");

        waitForStart();
        while (opModeIsActive()){
            if (gamepad1.a){
                servo.setPower(1.0);
            }
            else if (gamepad1.b) {
                servo.setPower(-1.0);
            }
            else {
                servo.setPower(0.0);
            }
        }
    }
}
