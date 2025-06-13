package org.firstinspires.ftc.teamcode.RobotTests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "intake", group = "RobotTests")
@Disabled
public class Intake extends LinearOpMode {
    public Servo intake_twist; // Control 0 (1.0, 0.32)
    public Servo intake_brazo; // Control 1 //(0.34, 1.0)
    public CRServo intake; // Control 2 [x, b]

    public void runOpMode(){
        initIntake();

        waitForStart();
        while (opModeIsActive()){
            if (gamepad1.right_bumper){
                intakeEnfrente();
            }//Intake Enfrente
            else if (gamepad1.left_bumper){
                intakeAtras();
            }// Intake atras
            else if (gamepad1.x){
                intake.setPower(1.0);
            }        // Consumir
            else if (gamepad1.b){
                intake.setPower(-1.0);
            }        // Escupir
            else if (gamepad1.dpad_right){
                intakeDejar();
            }// Pos Dejar a un lado
            else {
                intake.setPower(0.0);
            }
        }
    }

    public void intakeEnfrente(){
        intake_twist.setPosition(1.0);
        intake_brazo.setPosition(0.37);
    }
    public void intakeAtras(){
        intake_twist.setPosition(1.0);
        intake_brazo.setPosition(1.0);
    }
    public void intakeDejar(){
        intake_twist.setPosition(0.32);
        intake_brazo.setPosition(0.65);
        sleep(400);
        intake.setPower(-1.0);
    }

    public void initIntake(){
        intake_twist = hardwareMap.get(Servo.class, "intakeTwist");
        intake_brazo = hardwareMap.get(Servo.class, "intakeBrazo");
        intake = hardwareMap.get(CRServo.class, "intake");

        telemetry.addLine("intake Inizializado :D");
    }
}
