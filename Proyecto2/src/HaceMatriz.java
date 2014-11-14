
package proyectoprueba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


/**
 *
 * SimulaDatosVitapp.java
 * 
 * @author Brandon Mendez - 13087
 * @author Javier Bucaro - 13033
 * @author Samuel Maldonado - 13153
 * @author Kuk Ho Chung - 13279
 * 
 * Descripcion: Programa que simula los datos recibidos por 1 anio en
 * el proyecto de vitapp
 */


public class HaceMatriz {
        //Se verifica si ya existe la matriz
        File matrizTxt;
        int numero = 0;
        int cont = 0;
        BufferedWriter bw = null; 
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
 
   public void escribeEnTexto(){
        String direccion= System.getProperty("user.dir");
        matrizTxt = new  File (direccion);
        if (matrizTxt.exists()){
            
            FileWriter fichero = null;
            PrintWriter pw = null;
            try{


                fichero = new FileWriter(direccion+"\\prueba.txt");
                pw = new PrintWriter(fichero);
                bw = new BufferedWriter(fichero);
                while(cont<25){ //este contador se puede modificar
                numero=(int)Math.floor(Math.random()*(36000-0)+18000);
                for (int i = 0; i < 1; i++)
                    bw.write(Integer.toString(numero)+",");
                cont++;
                } 
                bw.close();


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
               try {
               if (null != fichero)
                  fichero.close();
               } catch (Exception e2) {
                  e2.printStackTrace();
               }
            }
        }//Si existe no creo el archivo    
   }
   
   public void haceMatrizAleatorios(){
 
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
        
        String direccion= System.getProperty("user.dir");
        archivo = new File (direccion+"\\prueba.txt");
        fr = new FileReader (archivo);
        br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null){
            
         }
         
         
         
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
   
   }
   
}
