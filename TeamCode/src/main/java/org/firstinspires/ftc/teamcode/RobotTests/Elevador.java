package org.firstinspires.ftc.teamcode.RobotTests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Disabled
public class Elevador extends LinearOpMode {
    public Servo ART;
    public Servo ARI;
    //    public Servo ServoRecogedor;

    @Override
    public void runOpMode(){
        initElevador();
        waitForStart();
        while (opModeIsActive()){
            if (gamepad2.a){
                ARI.setPosition(0.0);
                ART.setPosition(1.0);
                telemetry.addData("ART: ", ART.getPosition());
                telemetry.addData("ARI: ", ARI.getPosition());
            }
            else if (gamepad2.x){
                ARI.setPosition(0.7);
                ART.setPosition(0.3);
                telemetry.addData("ART: ", ART.getPosition());
                telemetry.addData("ARI: ", ARI.getPosition());
            }

        }
    }
    public void initElevador(){
        ART = hardwareMap.get(Servo.class, "ART");
        ARI = hardwareMap.get(Servo.class, "ARI");
    }
}
