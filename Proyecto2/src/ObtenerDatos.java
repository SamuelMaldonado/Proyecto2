package proyectoprueba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brandon Mendez - 13087
 * @author Javier Bucaro - 13033
 * @author Samuel Maldonado - 13153
 * @author Kuk Ho Chung - 13279
 */
public class ObtenerDatos {
    private String[] lista;
    
    public ObtenerDatos(){
        String[] lista = new String[25];
    }
    
    public String LecturaMatriz(int indice){
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      String direccion= System.getProperty("user.dir");
 
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (direccion+"\\prueba.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null){
            //System.out.println(linea);
            lista=linea.split(",");
         for (int i=0;i<25;i++){
             //System.out.println(lista[i]);
         }
      }
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
        return lista[indice];
    }
    
}
