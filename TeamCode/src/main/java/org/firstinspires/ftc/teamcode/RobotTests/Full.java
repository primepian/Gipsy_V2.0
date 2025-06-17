package org.firstinspires.ftc.teamcode.RobotTests;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@TeleOp
public class Full extends LinearOpMode {
    //TITLE: ELEVADOR SHIZE
    public Servo ART; // 3
    public Servo ARI; // 4
    public Servo ARA; // 5 [1.0 -> 0.34]

    //TITLE: INTAKE SHIZE
    public DcMotor extencion;
    public Servo intake_twist; // Control 0 (1.0, 0.32)
    public Servo intake_brazo; // Control 1 //(0.34, 1.0)
    public CRServo intake; // Control 2 [x, b]

    //TITLE: BRAZO SHIZE
    public Servo garra;
    public Servo brazo;
    public Servo brazoTwist;
    boolean RTrigger = false;
    boolean LTrigger = false;

    //TITLE: BARREDORA SHIZE

    @Override
    public void runOpMode(){
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        initIntake();
        initElevador();
        initBrazo();
        waitForStart();

        while (opModeIsActive()){
            //TITLE: ===CHASIS===
            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    -gamepad1.right_stick_x
            )
            );
//TITLE:                GPAD_1
            if (gamepad1.y){
                intakeEnfrente();
            }//T: Intake Enfrente
            else if (gamepad1.a){
                intakeAtras();
            }//T: Intake atras
            else if (gamepad1.x){
                intake.setPower(1.0);
            }//T: Intake Consumir
            else if (gamepad1.b){
                intake.setPower(-1.0);
            }//T: Tntake Escupir
            else if (gamepad1.dpad_left){
                intakeDejar();
            }//T:  Pos Dejar a un lado
            else if (gamepad1.right_bumper){
                extenderIntake();
            } //T: Intake Extender
            else if (gamepad1.left_bumper) {
                retraerIntake();
            }// T: Intake Retraer
            else {
                intake.setPower(0.0);
                extencion.setPower(0.0);
            }
//TITLE:                GPAD_2
            if (gamepad2.y){
                ARI.setPosition(0.0);
                ART.setPosition(1.0);
                ARA.setPosition(0.34);
                telemetry.addData("ART: ", ART.getPosition());
                telemetry.addData("ARI: ", ARI.getPosition());
            }//t: [BRAZO] Pos Enfrente / RECOGER
            else if (gamepad2.a){
                ARI.setPosition(0.7);
                ART.setPosition(0.3);
                ARA.setPosition(0.34);
                telemetry.addData("ART: ", ART.getPosition());
                telemetry.addData("ARI: ", ARI.getPosition());
            }//t: [BRAZO] Pos Atras / TIRAR
            else if (gamepad2.x){
                garra.setPosition(0.5);
            }//T: [BRAZO] Grarra abrir
            else if (gamepad2.b){
                garra.setPosition(0.0);
            }//T: [BRAZO] Grarra cerrar
            else if (gamepad2.right_trigger > 0.0 && !RTrigger) {
                brazo.setPosition(1.0);
                brazoTwist.setPosition(0.0);
            }
            else if (gamepad2.left_trigger > 0.0 && !LTrigger) {
                brazo.setPosition(0.0);
                brazoTwist.setPosition(0.68);
                garra.setPosition(0.5);
            }

            RTrigger = gamepad1.right_trigger > 0.0;

            LTrigger = gamepad1.left_trigger > 0.0;
        }
    }

    public void extenderIntake(){
        extencion.setPower(-0.5);
        intake_twist.setPosition(1.0);
        intake_brazo.setPosition(1.0);
    }
    public void retraerIntake(){
        extencion.setPower(0.5);
        intake_twist.setPosition(1.0);
        intake_brazo.setPosition(0.37);
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
    //TITLE: INIT
    public void initElevador(){
        ART = hardwareMap.get(Servo.class, "ART");
        ARI = hardwareMap.get(Servo.class, "ARI");
        ARA = hardwareMap.get(Servo.class, "ARA");
    }
    public void initIntake(){
        intake_twist = hardwareMap.get(Servo.class, "intakeTwist");
        intake_brazo = hardwareMap.get(Servo.class, "intakeBrazo");
        extencion = hardwareMap.get(DcMotor.class, "extencion");
        intake = hardwareMap.get(CRServo.class, "intake");

        telemetry.addLine("intake Inizializado :D");
    }
    public void initBrazo() {
        garra = hardwareMap.get(Servo.class, "garra");
        brazo = hardwareMap.get(Servo.class, "Brazo");
        brazoTwist = hardwareMap.get(Servo.class, "BrazoT");
    }
}