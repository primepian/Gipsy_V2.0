package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

@TeleOp(name = "servus", group = "Tests")
public class servoTest extends LinearOpMode {
    public Servo servo;
//    public CRServo crServo;

    @Override
    public void runOpMode() {
        double posActual = 0.5;
        boolean lastDpadLeft = false;
        boolean lastDpadRight = false;

        servo = hardwareMap.get(Servo.class, "servo");
//        crServo = hardwareMap.get(CRServo.class, "crservo");
        servo.setPosition(posActual);

        waitForStart();
        while (opModeIsActive()) {

            if (gamepad1.a){
                posActual = 0.5;
                servo.setPosition(posActual);
                telemetry.addLine("servo en 0.5");
            } else if (gamepad1.b) {
                posActual = 1.0;
                servo.setPosition(posActual);
                telemetry.addLine("servo en 1.0");
            } else if (gamepad1.x) {
                posActual = 0.0;
                servo.setPosition(posActual);
                telemetry.addLine("servo en 0.0");
            }
            /*
            if (gamepad1.dpad_up){
                crServo.setPower(1.0);
            } else if (gamepad1.dpad_down) {
                crServo.setPower(-1.0);
            } else {
                crServo.setPower(0.0);
            }
            */

            if (gamepad1.dpad_right && !lastDpadRight && posActual < 1.0) {
                posActual += 0.01;
                posActual = Math.min(1.0, posActual);
                servo.setPosition(posActual);
            }

            if (gamepad1.dpad_left && !lastDpadLeft && posActual > 0.0) {
                posActual -= 0.01;
                posActual = Math.max(0.0, posActual);
                servo.setPosition(posActual);
            }


            lastDpadRight = gamepad1.dpad_right;
            lastDpadLeft = gamepad1.dpad_left;

            telemetry.addData("servo pos:", posActual);
            telemetry.update();
        }
    }
}
