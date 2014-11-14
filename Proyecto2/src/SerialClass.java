
package proyectoprueba;
/**
   * @SerialClass.java  1.0 14/08/2014
   *
   * Universidad del Valle de Guatemala.
   * Seccion: 30 Estructura de Datos
   * @author Brandon Mendez Carnet: 13087
   * @author Kuk ho carnet: 13279
   * @author Samuel Maldonado carnet: 13153
   * @author Javier Bucaro carnet: 13033
   * 
   * SerialClass: Interfaz Para el control de arduino
   * Basado en el programa de:
   * @author Atulmaharaj
   * @url {http://atulmaharaj.wordpress.com/2013/05/03/arduino-serial-communication-using-java-and-rxtx/}
   * 
   */

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JOptionPane;
public class SerialClass implements SerialPortEventListener {
    private static Component frame;
 
    public SerialPort serialPort;
    /** The port we're normally going to use. */
    private static final String PORT_NAMES[] = {
    "/dev/tty.usbserial-A9007UX1", // Mac OS X
    "/dev/ttyUSB0", // Linux
    "COM4", // Windows
    };

   public static BufferedReader input;
   public static OutputStream output;
    /** Milliseconds to block while waiting for port open */
    public static final int TIME_OUT = 2000;
    /** Default bits per second for COM port. */
    public static final int DATA_RATE = 9600;
    String inputLine;
    private ArrayList datosTimer ;
    private ArrayList caracter;
    private String aux;
    
    
   public void initialize() {            
       CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

       //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

       try {
        // open serial port, and use class name for the appName.
        serialPort = (SerialPort) portId.open(this.getClass().getName(),
        TIME_OUT);

        // set port parameters
        serialPort.setSerialPortParams(DATA_RATE,
        SerialPort.DATABITS_8,
        SerialPort.STOPBITS_1,
        SerialPort.PARITY_NONE);

        // open the streams
        input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        output = serialPort.getOutputStream();
        char ch = 1;
        output.write(ch);


        // add event listeners
        serialPort.addEventListener(this);
        serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

   public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    @SuppressWarnings("empty-statement")
   public synchronized void serialEvent(SerialPortEvent oEvent) {
    if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
        
        try {
            inputLine=input.readLine();
            

            File f;

            String sFichero = System.getProperty("user.dir");
            f = new File(sFichero+"\\timer.txt");


            if(f.exists()){
                datosTimer = new ArrayList();

                try{
                FileWriter w = new FileWriter(f,true);
                //BufferedWriter bw = new BufferedWriter(w);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true), "UTF8")); 
                PrintWriter wr = new PrintWriter(bw);  
                FileReader lectorArchivo = new FileReader(f);

                BufferedReader br = new BufferedReader(lectorArchivo);
                
                wr.write(inputLine+",");											//escribimos en el archivo
               
                
                while((aux = br.readLine()) != null){
                    String[] items= aux.split(",");
                    for(int i=0;i<items.length;i++){
                       datosTimer.add(items[i]);
                   
                   }
                }
                datosTimer.add(inputLine);
                wr.close();
                bw.close();

                }catch( Exception e4 ){};
            }else{
               try{
               FileWriter w = new FileWriter(f);
               BufferedWriter bw = new BufferedWriter(w);
               PrintWriter wr = new PrintWriter(bw);  

               wr.write(inputLine+",");											//escribimos en el archivo

               wr.close();
               bw.close();

               }catch(IOException e3){};
            }
          
            Correo correo = new Correo();
            System.out.println("Hoy usted durmio: "+inputLine+" segundos");
            
            correo.SendMail(inputLine);
            
            
            
        } catch (Exception e) {
            System.err.println(e.toString());
            
        }
    }

    }
   
   
   
public static synchronized void writeData(String data) {
        JOptionPane.showMessageDialog(frame,"Conectando con arduino...");
        System.out.println("Conectando con arduino...");
        try {
            output.write(data.getBytes());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,"No se pudo conectar con arduino..");
            System.out.println("No se pudo conectar con arduino..");
            
        }
    }

    
   public ArrayList getdatosTimer(){
       return datosTimer;
   
   }

}
