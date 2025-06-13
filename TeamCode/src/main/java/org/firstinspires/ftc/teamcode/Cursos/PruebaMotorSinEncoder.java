package org.firstinspires.ftc.teamcode.Cursos;
//para que Android Studio agregue automáticamente las declaraciones de importación necesarias para las clases que desea utilizar

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;//se usa para definir un programa de operación autónoma en un robot FTC
import com.qualcomm.robotcore.eventloop.opmode.Disabled;// Este import corresponde a una anotación (@Disabled)
//
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;//el código del robot se ejecuta de manera lineal en lugar de seguir
// un bucle iterativo como OpMode, lo que facilita la escritura de secuencias de comandos paso a paso. Se usa comúnmente para
// controlar el flujo del programa en el robot, esperar entradas del controlador y operar motores y sensores de manera ordenada.

@Autonomous(name="PruebaMotorSinEncoder", group="Pushbot") //indica que el programa es un modo autónomo y puede ser seleccionado en
// la interfaz de control del robot.
@Disabled//ue se puede usar para desactivar una OpMode en un programa de robot.
//2. Es útil para desactivar código temporalmente sin eliminarlo.
public class PruebaMotorSinEncoder extends LinearOpMode {
    PruebaMotorSinEncoderConfig robot = new PruebaMotorSinEncoderConfig();//es un constructor

        @Override//Es para sobreescribir un codigo
    public void runOpMode() { //debe implementar este método. Este método se llama cuando un usuario selecciona y ejecuta el OpMode.

        robot.init(hardwareMap , telemetry);
        sleep(1000);
        telemetry.update();//

        waitForStart();//método para esperar hasta que el usuario presione el botón de inicio en la estación del conductor para
        // comenzar la ejecución de OpMode.
        while (opModeIsActive()) {//continúa iterando en este bucle hasta que ya no está activo (es decir, hasta que el usuario
            // presiona el botón de detención en la estación del conductor)

            robot.motor.setPower(1);//es una instrucción en programación de robots que se utiliza para establecer la potencia
            // del motor.
            telemetry.addData("Motor" , "100%");//Su propósito es enviar información a la telemetría del robot para
            // que aparezca en la pantalla de depuración durante la ejecución del código.
            // 2. para agregar un mensaje que se enviará a la estación del conductor. OpMode luego llama al método de actualización
            //  para enviar el mensaje a la estación del conductor.
            //3. es el valor asociado a esa categoría, lo que podría indicar que el motor está funcionando al 100% de su capacidad.
            telemetry.update();//Su propósito es proporcionar información en tiempo real sobre lo que está ocurriendo en la
            // aplicación, lo que facilita el monitoreo y la optimización.

        }
    }

}