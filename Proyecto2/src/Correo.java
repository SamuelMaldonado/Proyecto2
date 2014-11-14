

package proyectoprueba;

import java.io.*;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Correo {

    private int horas=0;
    private String tiempoTotal, nombreUsuario,correoUsuario;
    public static String Username = "vitappinc@gmail.com";                      //se establece la cuenta de correo que enviará los mensajes
    public static String PassWord = "Javiersamuel_1";                           //se ingresa la contraseña propia del correo anterior
    String Mensage = "";                                                        //mensaje del cuerpo del correo
    String To = "";                                                             //destinatario del correo
    String Subject = "";                                                        //asunto del correo
    String edad="";                                                             //edad del cliente de la aplicación
    public ArrayList arrayTimer;                                                //arreglo que tiene los tiempos que el cliente durmió (datos recibidos de arduino)
    private int intervalo1Int=0,intervalo2int=0;                                //son los limites de rangos de edades que tiene el cliente para escoger de una lista
    private boolean flag;                                                       

    //constructor
    public Correo(){
        arrayTimer = new ArrayList();                                           //
        flag=false;

    }
    
      
       
    public String obtieneLinea(int numLinea, String archivotxt) {
    String auxString="";        
    try { 
        File f ;
        String sFichero = System.getProperty("user.dir");
        f = new File(sFichero+archivotxt);

        FileReader fr = new FileReader(f); 
        BufferedReader br = new BufferedReader(fr); 
        int contador = 0,cont=0; 

        String linea = ""; 

        while ((linea = br.readLine()) != null && cont==0) { 
        contador++; 
        /*La primera vez no imprime, sino el segundo intento correspondiente a la segunda linea */ 
        if(contador==numLinea) 
                auxString=linea;
                if(auxString!="")
                    contador=1;
        } 

        br.close(); 
        fr.close(); 

        } catch (Exception e) { 
        System.out.println(e); 

        } 
        return auxString;
    } 

    
    public String obtieneConsejoRandom(){
    
    int random=((int)(Math.random()*(10-0+1)+0));
    
    
    if(random==1 || random ==0)    
        return "Mantener un horario regular, "
                + "acostándonos y levantándonos a la misma hora cada día. "
                + "Durante el día realizar sólo una siesta corta para poder dormir bien por la noche.";
    if(random==2)    
        return "Si hacemos ejercicio, que sea a una hora regular todos los "
                + "días. El deporte estimula y el cuerpo tardará más tiempo"
                + " en relajarse. No penséis que ejercitarnos antes de dormir "
                + "hará que estemos más cansados para ayudarnos a conciliar el sueño.";
    if(random==3)    
        return "No comamos cenas pesadas antes de dormir, ni bebidas energéticas"
                + " o que contengan cafeína.";
    if(random==4)    
        return "No fumemos, ni bebamos alcohol. También son estimulantes que nos"
                + " mantendrán despiertos, además disminuyen el oxígeno que debe"
                + " llegar a nuestro cuerpo y nos impedirán descansar completamente.";
    if(random==5)    
        return "Nuestra cama debe ser cómoda, cerremos puertas y ventanas para "
                + "que el ruido no pueda molestarnos y así crear un ambiente propicio.";
    if(random==6)    
        return "Ventilemos el dormitorio antes de dormir, cambiemos las sabanas "
                + "regularmente y usemos aromas relajantes que estimulen el"
                + " sueño, como la lavanda.";
    if(random==7)    
        return "Ropa cómoda para ir a la cama, sin botones, con materiales "
                + "transpirables, que nos permitan movernos durante el sueño.";
    if(random==8)    
        return "Un baño caliente con manzanilla y algunas sales nos calman y "
                + "preparan al cuerpo para el descanso.";
    if(random==9)    
        return "Música relajante, al ponerla a un volumen bajo tranquiliza "
                + "la mente. Si tenemos suerte, antes de ir a la cama podemos"
                + " pedir que nos hagan un masaje (da placer y alivia el cuerpo).";
    if(random==10)    
        return "No trabajemos en la cama o en nuestro cuarto, esto le da a"
                + " nuestro cerebro un lenguaje equivocado de lo que "
                + "significa nuestro descanso.";
    
    
    
    return "";
    }
    
    
    public String obtieneConsejoEdad(){
    
    String edadObtenida=obtieneLinea(1,"\\datosUsuario.txt");
    
    String[] listaDosEdades=edadObtenida.split(",");
    String intervalo1="",intervalo2="";
    intervalo1=listaDosEdades[0];
    intervalo2=listaDosEdades[1];
    intervalo1Int=Integer.parseInt(intervalo1);
    intervalo2int=Integer.parseInt(intervalo2);
    
    
    if(intervalo1Int==3 && intervalo2int==6)    
      return "De acuerdo a su edad debe dormir entre 1  4 y 15 horas";
    if(intervalo1Int==7 && intervalo2int==11)    
      return "De acuerdo a su edad debe dormir entre 12 y 14 horas";
    if(intervalo1Int==12 && intervalo2int==19)    
      return "De acuerdo a su edad debe dormir entre 10 y 11 horas";
    if(intervalo1Int==20 && intervalo2int==39)    
      return "De acuerdo a su edad debe dormir entre 7 y 9 horas";
    if(intervalo1Int==40)    
      return "De acuerdo a su edad debe dormir 6 y 7 horas";
        
    
    
    return "";
    
    }

    private void verificaHorario(String edad) {
        String edadList[]=edad.split(",");
        int intEdad=Integer.parseInt(edadList[0]);
             
        if(horas<14 && intEdad==3 ){
          flag=true;            //quiere decir que no esta durmiendo bien
        }
        if(horas<12 && intEdad==7 ){
          flag=true;            //quiere decir que no esta durmiendo bien
        }
        if(horas<10 && intEdad==12){
            flag=true;            //quiere decir que no esta durmiendo bien
        }
        if(horas<7 && intEdad==20){
            flag=true;            //quiere decir que no esta durmiendo bien
        }
        if(horas<6 && intEdad==40){
           flag=true;            //quiere decir que no esta durmiendo bien
        }
    }
        
        
      public void SendMail(String seg) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Username, PassWord);
                    }
                });

        try {
            llenarArreglo();

            edad=obtieneLinea(1,"\\datosUsuario.txt");
            int segundos = Integer.parseInt((String) arrayTimer.get(arrayTimer.size()-1));

            calculaHoras(Integer.parseInt(seg));

            verificaHorario(edad);          
            String consejo="";
            if (flag==true){
                consejo="\nNo durmió lo suficiente. "+obtieneConsejoEdad();
            }else{
                consejo=obtieneConsejoEdad()+" . Su rutina es la correcta, siga así.";
            }
            
            
            HaceMatriz Obj2 = new HaceMatriz();
            Obj2.escribeEnTexto();
            Obj2.haceMatrizAleatorios();
            EmbeddedNeo4j hello = new EmbeddedNeo4j();
            String valoresDeNodos=hello.createDb();
            hello.removeData();
            hello.shutDown();
           
            
         
            String horasArray[] = valoresDeNodos.split(" ");
            int[] integerHorasArray = new int[horasArray.length];
           
            for (int i = 0; i < horasArray.length; i++) {
                try {
                    integerHorasArray[i] = Integer.parseInt(horasArray[i]);
                } catch (NumberFormatException nfe) {};
            }
                   
            int totalHorasLunesInt=integerHorasArray[5]+integerHorasArray[12]+integerHorasArray[18];            
            int totalHorasMartesInt=integerHorasArray[6]+integerHorasArray[13]+integerHorasArray[19];
            int totalHorasMiercolesInt=integerHorasArray[0]+integerHorasArray[7]+integerHorasArray[14]+integerHorasArray[21];
            int totalHorasJuevesInt=integerHorasArray[1]+integerHorasArray[8]+integerHorasArray[15]+integerHorasArray[22];
            int totalHorasViernesInt=integerHorasArray[2]+integerHorasArray[9]+integerHorasArray[16]+integerHorasArray[23];
            int totalHorasSabadosInt=integerHorasArray[3]+integerHorasArray[10]+integerHorasArray[17]+integerHorasArray[24];
            int totalHorasDomingosInt=integerHorasArray[4]+integerHorasArray[11]+integerHorasArray[18]+integerHorasArray[25];
            
            String totalHorasLunes="\nTotal de horas dormidas en lunes, los ultimos 25 dias: "+calculaHoras(totalHorasLunesInt);
            String totalHorasMartes="\nTotal de horas dormidas en martes, los ultimos 25 dias: "+calculaHoras(totalHorasMartesInt);
            String totalHorasMiercoles="\nTotal de horas dormidas en miercoles, los ultimos 25 dias: "+calculaHoras(totalHorasMiercolesInt);
            String totalHorasjueves="\nTotal de horas dormidas en jueves, los ultimos 25 dias: "+calculaHoras(totalHorasJuevesInt);
            String totalHorasViernes="\nTotal de horas dormidas en viernes, los ultimos 25 dias: "+calculaHoras(totalHorasViernesInt);
            String totalHorasSabado="\nTotal de horas dormidas en sabados, los ultimos 25 dias: "+calculaHoras(totalHorasSabadosInt);
            String totalHorasDomingo="\nTotal de horas dormidas en domingos, los ultimos 25 dias: "+calculaHoras(totalHorasDomingosInt);
            
            Mensage = "\n\n \n El dia de hoy usted durmió: "+calculaHoras(Integer.parseInt(seg))+"\n"+consejo+"\n\n\n"+"______________________________________________________\n"+"Consejo del día:\n"+obtieneConsejoRandom()+"\n______________________________________________________"+totalHorasLunes+totalHorasMartes+totalHorasMiercoles+totalHorasjueves+totalHorasViernes+totalHorasSabado+totalHorasDomingo;
            
            To=obtieneLinea(3,"\\datosUsuario.txt");
            Subject = "Vitapp Incorporated Informa: ";
        
            
            
            
            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setText(Mensage);

            // Se compone el adjunto con la imagen
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(
                new DataHandler(new FileDataSource("c:/sueno.jpg")));
            adjunto.setFileName("sueno.jpg");

            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
 
          
                
                        
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(Username));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            message.setSubject(Subject);
            //message.setText(Mensage);
            message.setContent(multiParte);
            Transport.send(message);
            JFrame frame = null;
            JOptionPane.showMessageDialog(frame, "Vitapp le ha enviado un mensaje a su bandeja de entrada.");
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
        
        public String calculaHoras(int tiempoTotal){
            int tiempoParcial = 0;  //esta variable solo la uso para guardar resultados temporales
            int minutos = 0;
            int segundos = 0;
            
            
            horas = tiempoTotal/3600;
            tiempoParcial = tiempoTotal-(horas * 3600);
            minutos = tiempoParcial/60;
            segundos = tiempoParcial-(minutos * 60);
            if(minutos>1)
                return horas + " horas " + minutos + " minutos " + segundos + " segundos";
            else
                return horas + " horas " + minutos + " minuto " + segundos + " segundos";   

        }
        
        
        
       

    public void llenarArreglo() {
            File f;
            String sFichero = System.getProperty("user.dir");
            f = new File(sFichero+"\\timer.txt");

            if(f.exists()){
                
                try{
                FileWriter w = new FileWriter(f,true);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true), "UTF8")); 
                PrintWriter wr = new PrintWriter(bw);  
                FileReader lectorArchivo = new FileReader(f);
                BufferedReader br = new BufferedReader(lectorArchivo);              
                
                
                String aux="";
                while((aux = br.readLine()) != null){
                
                
                    String[] items= aux.split(",");
                    for(int i=0;i<items.length;i++){
                       arrayTimer.add(items[i]);
                   }
                }
                wr.close();
                bw.close();

                }catch( Exception e4 ){};
            }else{
              
            }                              
                               
                               
    }  
        
        
        

}
