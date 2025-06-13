package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Encoder Test", group = "Tests")
@Disabled
public class EncoderTest extends LinearOpMode {
    private DcMotor motor;
    private int pos = 0;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "motor");

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            if (!motor.isBusy()) {
                if (gamepad1.y) {
                    drive(1000, 0.5);
                } else if (gamepad1.a) {
                    drive(-1000, 0.5);
                }
            }
            telemetry.addData("Encoder Pos: ", motor.getCurrentPosition());
            telemetry.update();
        }
    }

    private void drive(int target, double speed) {
        pos += target;

        motor.setTargetPosition(pos);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(speed);
    }
}
