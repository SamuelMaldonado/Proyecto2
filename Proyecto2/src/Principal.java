package proyectoprueba;

import java.io.File;
import javax.swing.JFrame;
import static proyectoprueba.SerialClass.writeData;

/**
   * @Principal.java  1.0 01/08/14
   *
   * Universidad del Valle de Guatemala.
   * Seccion: 30 Estructura de Datos
   * @author Brandon Mendez Carnet: 13087
   * @author Kuk ho carnet: 13279
   * @author Samuel Maldonado carnet: 13153
   * @author Javier Bucaro carnet: 13033
   * 
   * 
   * Principal: Llama a la interfaz grafica para registrar el correo del nuevo usuario del proyecto 1
   */


public class Principal {
	//Frame de controlx
	private static JFrame frame;	

	public static void main (String args[]){
            InterfazGrafica in = new InterfazGrafica(frame);
            Correo correo=new Correo();
            JFrame.setDefaultLookAndFeelDecorated(true);
            in.creandoInterfaz();

            
            
         
            
            SerialClass main = new SerialClass();
            main.initialize();
            Thread t=new Thread() {
            public void run() {
                //the following line will keep this app alive for 1000 seconds,
                //waiting for events to occur and responding to them (printing incoming messages to console).
                try {Thread.sleep(1500);
                    writeData("2");} catch (InterruptedException ie) {}
                }
             };
            t.start();

            

        }
}





