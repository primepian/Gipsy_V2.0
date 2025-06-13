package org.firstinspires.ftc.teamcode.OpMaster;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//TODO: PONER TOPE AL BRAZO
//TODO: HACER MÉTODOS PARA LOS DRIVERS

public class Mecanismos {

    //ELEVADOR
    public DcMotor elevador1; //0 expansion
    public DcMotor elevador2; //3 control
    public int eletopeSuperior= -3500;
    public int eletopeInferior= 0;


    //BARREDORA
    public DcMotor correderaBarredora; //2 control
    public DcMotor ingesta; //3 expansion
    //public Servo barrArtDer; //3 control DESCONECTADO
    public Servo servoAriculacionBarredora; //0 expansion (servo izquierda)

    //GARRA
    public Servo servoCorrederaGarra; //1 expansion
    public Servo servoCorrederaGarra2; //3 control
    public Servo servoBrazo1; //2 expansion
    public Servo servoBrazo2; //0 control
    public Servo servoArticulacionGarra; //1 control
    public Servo servoGarra; //3 control

    double servoPosicionHand = 0.5;
    double handIncremento =0.03;
    double brazoPos1 = 1;
    double brazoPos2 = 0.15;
    double brazoIncremento = 0.01;

    double topeAtrasBrazoIzq =1;
    double topeAtrasBrazoDer = 0.15;
    double topeFrontBrazoIzq =0.38;
    double topeFrontBrazoDer = 0.77;
    double brazoCanastaDerPos = 0.3;
    double brazoCanastaIzqPos= 0.85;

    double articulacionGarraCanastaPos = 0.66; //LO MISMO Q ERECTO

    //Subir la articulacion Garra en rango
    double atrasBrazoIzqPos1GarraUp = 0.7;
    double atrasBrazoIzqPos2GarraUp = 0.95;

    double posicionArtGarraErecto = 0.66;
    double garraArticPosMaxEnfrente = 0;
    double garraArticPosMinAtras = 1;


    public void init(HardwareMap hardwareMap){

        ///ELEVADOR
        elevador1 = hardwareMap.get(DcMotor.class,"elevador1");
        elevador2 = hardwareMap.get(DcMotor.class,"elevador2");

        ///BARREDORA
        correderaBarredora = hardwareMap.get(DcMotor.class, "correderaBarredora");
        ingesta =hardwareMap.get(DcMotor.class, "ingesta");
        //barrArtDer = hardwareMap.get(Servo.class, "bArtDer");
        servoAriculacionBarredora = hardwareMap.get(Servo.class, "bArtIzq");

        ///GARRA
//        servoCorrederaGarra = hardwareMap.get(Servo.class, "Corredera1");
//        servoCorrederaGarra2 = hardwareMap.get(Servo.class, "Corredera2");
        servoBrazo1 = hardwareMap.get(Servo.class, "brazo1");
        servoBrazo2 = hardwareMap.get(Servo.class, "brazo2");
        servoArticulacionGarra = hardwareMap.get(Servo.class, "hand");
        servoGarra = hardwareMap.get(Servo.class, "garra");

//        servoCorrederaGarra.setPosition(0.9); //1
//        servoCorrederaGarra2.setPosition(0.1); //0

        //moverBrazo(brazoPos1, brazoPos2);
        //moverArtGarra(0);
        cerrarGarra();

        stopResetEconder(elevador1, elevador2, correderaBarredora);
        runUsingEncoder(elevador1, elevador2, correderaBarredora);
        runWithoutEncoder(ingesta);

        elevador2.setDirection(DcMotorSimple.Direction.REVERSE);

        zeroPower(elevador1, elevador2);
        //zeroPowerBrake(elevador1, elevador2);

    }

    //COMBO
    ///TOMAR SAMPLE BARREDRORA
        public void autoTomarSample(){
            cerrarGarra();
            moverArtGarra(garraArticPosMaxEnfrente);
            moverBrazoMaxEnfrente();
            cerrarGarra();

        }

        public void autoEncestar(){
            cerrarGarra();
            moverArtGarra(posicionArtGarraErecto);
            moverBrazo(brazoCanastaIzqPos, brazoCanastaDerPos);
        }

        public void autoBarradoraAgarrarSample(){

        }

    //ELEVADOR
    public void subirElevador(double POWER){

        elevador1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevador2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevador1.setPower(-POWER);
        elevador2.setPower(-POWER);
    }
    public void bajarElevador(double POWER){

        elevador1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevador2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevador1.setPower(POWER);
        elevador2.setPower(POWER);
    }

    //¿se podria cambiar por brake?
    public void mantenerElevador(){
        elevador1.setTargetPosition(elevador1.getCurrentPosition());
        elevador2.setTargetPosition(elevador1.getCurrentPosition());

        elevador1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevador2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elevador1.setPower(1);
        elevador2.setPower(1);


    }
    public void mantenerElevadorBrake(){
        zeroPower(elevador1, elevador2);
        zeroPowerBrake(elevador1, elevador2);
    }

    //BARREDORA
    public void extensionBarredora (double POWER){
        correderaBarredora.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        correderaBarredora.setPower(-POWER); //-1 ES PARA EXTENDERSE
    }
    public void retraccionBarredora (double POWER){
        correderaBarredora.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        correderaBarredora.setPower(POWER); //+1 es para retraerse
    }
    public void mantenerBarredora (){
        correderaBarredora.setTargetPosition(correderaBarredora.getCurrentPosition());
        correderaBarredora.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        correderaBarredora.setPower(1);
    }
    public void mantenerBarredoraBrake(){
        zeroPower(correderaBarredora);
        zeroPowerBrake(correderaBarredora);
    }

    public void subirArticulacionBarredora(){
        servoAriculacionBarredora.setPosition(0.63);
    }
    public void bajarArticulacionBarredora(){
        servoAriculacionBarredora.setPosition(0.85);

    }

    //GARRA

//    public void extenderCorrederaGarra(){
//        servoCorrederaGarra.setPosition(0.6);
//        servoCorrederaGarra2.setPosition(0.4);
//    }
//    public void retraerCorrederaGarra(){
//        servoCorrederaGarra.setPosition(0.9);
//        servoCorrederaGarra2.setPosition(0.1);
//    }
    public void moverArtGarra(double POS){
        servoArticulacionGarra.setPosition(POS);
    }
    public void moverBrazo(double POS1, double POS2) {
        servoBrazo1.setPosition(POS1);
        servoBrazo2.setPosition(POS2);
    }

    //MINIMO
    // 1: 1,
    // 2: 0.15
    // MAX
    // 1:0.37,
    // 2: 0.77
    public void moverBrazoMaxEnfrente(){
            moverBrazo(0.37, 0.77);
    }
    public void moverBrazoMinAtras(){
            moverBrazo(1, 0.15);
    }
    public void brazoEnfrente(){
        brazoPos1 = brazoPos1 - brazoIncremento;
        brazoPos2 = brazoPos2 + brazoIncremento;
        moverBrazo(brazoPos1, brazoPos2);

    }
    public void brazoAtrás(){
        brazoPos1 = brazoPos1 + brazoIncremento;
        brazoPos2 = brazoPos2 - brazoIncremento;
        moverBrazo(brazoPos1, brazoPos2);
    }
    public void mantenerBrazo(){
        servoBrazo1.setPosition(servoBrazo1.getPosition());
        servoBrazo2.setPosition(servoBrazo2.getPosition());

    }
    public void garraArticulacionFront(){
        servoPosicionHand = Math.min(servoPosicionHand +handIncremento, 1.0);
        moverArtGarra(servoPosicionHand);
    }
    public void garraArticulacionAtras(){
        servoPosicionHand = Math.max(servoPosicionHand - handIncremento, 0.0);
        moverArtGarra(servoPosicionHand);

    }
    public void abrirGarra(){
        servoGarra.setPosition(0.6);
    }
    public void cerrarGarra(){
        servoGarra.setPosition(1); //1
    }

    //MOTORES
    public void runUsingEncoder(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    public void runWithoutEncoder(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
    public void stopResetEconder(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }
    public void runToPosition(DcMotor... motores) {
        for (DcMotor motor : motores) {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
    public void zeroPowerBrake(DcMotor... motores){
        for(DcMotor motor : motores){
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }
    public void zeroPower(DcMotor... motores){
        for(DcMotor motor : motores){
            motor.setPower(0);
        }
    }

}

