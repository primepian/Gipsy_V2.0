package org.firstinspires.ftc.teamcode.Cursos;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.time.Period;

public class PruebaMotorSinEncoderConfig {

    public DcMotor motor = null;
    HardwareMap hwMap = null;

    public void init(HardwareMap ahwMap, Telemetry telemetry) {

        hwMap = ahwMap;
        motor = hwMap.get(DcMotor.class, "motor");
        telemetry.addLine("Motores inicializados...");

        reversa(motor);
        telemetry.addLine("Cambio de giro de motores hecho...");

        motor.setPower(0);
        telemetry.addLine("Motores al 0%...");

        usarWithoutEncoder(motor);
        telemetry.addLine("Motores configurados...");

        telemetry.addLine("Robot inicilaizado con exito");

    }


    public void reversa(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setDirection(DcMotor.Direction.REVERSE);
        }
    }

    public void derecho(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setDirection(DcMotor.Direction.FORWARD);
        }
    }

    public void usarWithoutEncoder(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }


}