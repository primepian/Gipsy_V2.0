package org.firstinspires.ftc.teamcode.RobotTests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
@Disabled
/*
*Elevador: right/left trigger
*Recogedor: right/left bumper
* Brazo: y/a
* Garra: x/b
* Twist: left/down dpad
*/
public class Brazo extends LinearOpMode {
    public Servo garra;
    public Servo brazo;
    public Servo brazoTwist;
    boolean RTrigger = false;
    boolean LTrigger = false;

    @Override
    public void runOpMode() throws InterruptedException {

        initBrazo();
        waitForStart();
        while (opModeIsActive()) {

            if (gamepad2.x){
                garra.setPosition(0.5);
            }
            else if (gamepad2.b){
                garra.setPosition(0.0);
            }
            else if (gamepad2.right_trigger > 0.0 && !RTrigger) {
                brazo.setPosition(1.0);
            }
            else if (gamepad2.left_trigger > 0.0 && !LTrigger) {
                brazo.setPosition(0.0);
            }

            RTrigger = gamepad1.right_trigger > 0.0;
            LTrigger = gamepad1.left_trigger > 0.0;
        }
    }

    public void initBrazo() {
        garra = hardwareMap.get(Servo.class, "garra");
        brazo = hardwareMap.get(Servo.class, "Brazo");
        brazoTwist = hardwareMap.get(Servo.class, "BrazoT");
    }

}
