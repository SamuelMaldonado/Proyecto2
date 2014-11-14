package proyectoprueba;

/**
   * @InterfazGrafica.java  1.0 14/08/2014
   *
   * Universidad del Valle de Guatemala.
   * Seccion: 30 Estructura de Datos
   * @author Brandon Mendez Carnet: 13087
   * @author Kuk ho carnet: 13279
   * @author Samuel Maldonado carnet: 13153
   * @author Javier Bucaro carnet: 13033
   * 
   * InterfazGrafica: GUI del proyecto 1
   * 
   *
   */
//Importando librerias utiles
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class InterfazGrafica extends JPanel implements ActionListener, ItemListener{
	/**ATRIBUTOS**/
	private static JFrame frame,frameControl;										//frame principal de la GUI y frame de control de errores
        private JComboBox<String> comboBox;
	private JButton btonEnviarDatos;
	private JLabel lblNombre;
	private JTextField jNombre;
	private JLabel lblCorreo;
	private JTextField jCorreo;
	private JButton btonCambiarCorreo;
	private static String comenviarDatos="comenviardatos",comCambiarCorreo="comcambiarcorreo";	
	private SerialClass arduino;
        private String edad,nombreUsuario,correoUsuario;


        
	/**
	* Constructor con un parametro que es un frame de control de errores
	* @param v El parametro v es un frame que sirve para mostrarle errores de ingreso al usuario
	*/
	public InterfazGrafica(JFrame v){              
            frameControl=v;
            //Construyendo paneles de la interfaz				
            JComponent panelBanner=panelBanner();											
            JComponent panelEnviarDatos=panelEnviarDatos(); 
            JComponent panelDatos=panelDatos();
            //Se agregan al panel principal
            add(panelBanner);
            add(panelDatos);
            add(panelEnviarDatos);
            verificacionDatos();                                                //Se verifica que los datos esten en los archivos.txt
	}	

	/**
	* Metodo que devuelve panel con boton para enviar datos de usuario y 
        * boton para cambiar correo electronico
	* @return JPanel con 1 boton
	*/	  
	protected JComponent panelEnviarDatos(){
            JPanel panel = new JPanel(new GridLayout(0,1));			//Creando panel con un Gridlayout con 0 filas 1 columna
            panel.setBorder(BorderFactory.
                    createTitledBorder(" Enviar Datos. ")); 		
            panel.setPreferredSize(new Dimension(180, 75));     		//Cambiandolo de tamanio
            btonEnviarDatos= new JButton("Enviar Datos.");			//Creando botones
            btonEnviarDatos.setActionCommand(comenviarDatos);			//Comando de boton
            btonEnviarDatos.addActionListener(this);				//Asignando listener

            btonCambiarCorreo= new JButton("Cambiar Datos Personales.");	//Creando botones
            btonCambiarCorreo.setActionCommand(comCambiarCorreo);		//Comando de boton
            btonCambiarCorreo.addActionListener(this);				//Asignando listener
            
            //Aniadindo botones al panel
            panel.add(btonEnviarDatos);	
            panel.add(btonCambiarCorreo);	

            return panel;
	}

	
	/**
	* Metodo que devuelve el panel para el ingreso de datos
	* @return JPanel con 2 Jtexfield
	*/	  									  							  
	protected JComponent panelDatos(){
		jNombre = new JTextField(15);
		lblNombre= new JLabel("Ingrese su nombre: ");
                lblNombre.setLabelFor(jNombre);							
        
                jCorreo = new JTextField(15);
		lblCorreo= new JLabel("  Ingrese su correo Electronico: ");
                lblCorreo.setLabelFor(jCorreo);							
	    
                JPanel panelTexto = new JPanel(new GridLayout(0,2));		//Creando panel de texto 
                
                Border bordejpanel = new TitledBorder(new EtchedBorder(), 
                        "Datos Personales");
                panelTexto.setBorder(bordejpanel); 
		//Agregando al panel de texto
                panelTexto.add(lblNombre);
                panelTexto.add(jNombre);
                panelTexto.add(lblCorreo);
                panelTexto.add(jCorreo); 
                
                comboBox= new JComboBox<String>();
		comboBox.addItem("Escoga una opcion");
		comboBox.addItem("Entre 3-6 anios de edad");
		comboBox.addItem("Entre 7-11 anios de edad");
                comboBox.addItem("Entre 12-19 anios de edad");
                comboBox.addItem("Entre 20-40 anios de edad");
                comboBox.addItem("Entre 50 en adelante");
	    
		comboBox.addItemListener(this); 
		panelTexto.add(comboBox);
              
                return panelTexto;
	    
	}	
	
			
	/**
	* Metodo que devuelve el panel con el banner que contiene una imagen 
        * jog
	* @return JPanel con una imagen
	*/
	protected JComponent panelBanner(){
            JPanel panel = new JPanel(new GridLayout(0,1));			//Creando panel con un Gridlayout con 0 filas 1 columna
            JLabel banner = new JLabel("");  					//Centrando banner              
            banner.setOpaque(true);        					//Haciendolo mas opaco
            banner.setPreferredSize(new Dimension(400, 552));         		//Cambiandolo de tamaño
            banner.setIcon(new ImageIcon("sueno.jpg")); 			//Creando Imaege icon con parametro de direccion	
            JPanel bannerPanel = new JPanel(new BorderLayout());      		//Creando panel para banner
            bannerPanel.add(banner);        					//Centrado y agregando el banner al panel
            panel.add(bannerPanel);						//Añadiendo al Frame
            return panel;
	}

	/**
	*GESTIONADOR DE EVENTOS: Permite gestionar todos los eventos de la clase.
	* @see ActionEvent
	* @see ActionListener
	*/						  
	public void actionPerformed(ActionEvent e){				//Empieza el control de eventos
            String comando = e.getActionCommand();				//String del comando de accion			

            String nombre=jNombre.getText();
            String correo=jCorreo.getText();

            if(comando.equals(comenviarDatos)){		
                if(nombre!="" && correo!=""){	
                    File f;
                    String sFichero = System.getProperty("user.dir");
                    f = new File(sFichero+"\\datosUsuario.txt");

                    //Se escriben los datos del usuario en un txt
                    try{
                        FileWriter w = new FileWriter(f);
                        BufferedWriter bw = new BufferedWriter(w);
                        PrintWriter wr = new PrintWriter(bw);  

                        wr.write(edad);											//escribimos en el archivo
                        bw.newLine();
                        wr.append(nombre); 
                        bw.newLine();

                        wr.append(correo); 

                        wr.close();
                        bw.close();

                    }catch(IOException e3){};


                    
                    desactivarTodo();
                    btonCambiarCorreo.setEnabled(true);
                    File timer;

                    String sFichero2 = System.getProperty("user.dir");
                    timer = new File(sFichero+"\\timer.txt");
                    try {
                        boolean createNewFile = timer.createNewFile();
                        } catch (IOException ex) {
                        Logger.getLogger(InterfazGrafica.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    
                }else{
                        JOptionPane.showMessageDialog(frameControl,
                                " Revise la entrada de datos");		
                }
                
            }

            if(comando.equals(comCambiarCorreo)){		
                File f ;

                String sFichero = System.getProperty("user.dir");
                f = new File(sFichero+"\\datosUsuario.txt");
                BufferedReader entrada; 

                try {
                    entrada = new BufferedReader( new FileReader( f ) );
                    FileWriter w = new FileWriter(f);
                    BufferedWriter bw = new BufferedWriter(w);
                    PrintWriter wr = new PrintWriter(bw);  

                    String linea;
                    int con=0;
                    while(entrada.ready()){
                        linea = entrada.readLine();
                        con++;
                        if (con==1)
                            nombreUsuario=linea;
                        if (con==2)
                            correoUsuario=linea;                            
                    }

                    String nuevoUsuario="";
                    String nuevoCorreo="";
                    String nuevaEdad=""; 

                
                    //Se escriben los datos del usuario en un txt
                    try{

                        try {
                            //do{ 
                                nuevaEdad = JOptionPane.showInputDialog("Ingrese el numero de la edad en la que se encuentra: \n"
                                + "1-   3 y 6 anios de edad. \n"
                                + "2-   7 y 11 anios de edad. \n"
                                + "3-   12 y 19 anios de edad. \n"
                                + "4-   20 y 39 anios de edad. \n"
                                + "5-   40 anios en adelante");

                                /*
                                if(!cont1.equals("1") || !cont1.equals("2") || !cont1.equals("3") || !cont1.equals("4") || !cont1.equals("5")){
                                    JOptionPane.showMessageDialog(frame, "Los datos ingresados son invalidos");
                                    cont2="";
                                    cont3="";
                                    break;
                                }
                                */
                                nuevoUsuario = JOptionPane.showInputDialog("Ingrese el nuevo nombre de usuario: ");
                                nuevoCorreo = JOptionPane.showInputDialog("Ingrese el nuevo correo de usuario: ");
                            //}while (cont2 != null || cont3 != null); 

                        }catch (Exception e9) {  
                            System.out.println("Los datos ingresados son invalidos");
                        }

                        if(nuevoUsuario!=""&& nuevoCorreo!=""){

                            if(nuevaEdad.equals("1"))
                                wr.write("3,6"); 

                            if(nuevaEdad.equals("2"))
                                wr.write("7,11"); 

                            if(nuevaEdad.equals("3"))
                                wr.write("12,19"); 

                            if(nuevaEdad.equals("4"))
                                wr.write("20,39"); 

                            if(nuevaEdad.equals("5"))
                                wr.write("40,0"); 

                            bw.newLine();
                            
                            wr.append(nuevoUsuario);											//escribimos en el archivo
                            bw.newLine();
                            wr.append(nuevoCorreo); 
                            wr.close();
                            bw.close();
                        }

                    }catch(IOException e3){};


                }catch (IOException e5) {}

            }           
    }
		
    /**
    * creandoInterfaz:Metodo que crea el Frame principal, el contenedor principal y muestra la interfaz grafica
    * @see windowActivated */
    public void creandoInterfaz() {
        frame = new JFrame(" VitApp. ");			        						//Creando la ventana principal
        frame.setPreferredSize(new Dimension(1000, 600)); 						//colocar caracteristicas al frame (tamaño)

        this.setOpaque(true); 													//Se vuelve opaco el panel de contenido para que no sea diferenciado
        frame.setContentPane(this);												//Estableciendo el panel principal.
        frame.setResizable(false);												//Bloqueando la maximización de la ventana

        frame.setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);	//Asegurandose de que el evento foco se valla al Panel principal
        frame.addWindowListener(new WindowAdapter() {							//El evento foco se centra en este panel no importa que
        //Cerrando con confirmacion
        public void windowClosing(WindowEvent we){
                int eleccion = JOptionPane.showConfirmDialog(null, "�Desea salir?");
                        if ( eleccion == 0) {
                                System.exit(0);
                        }  
                }
        });
        //Mostrando el frame
        frame.pack();
        frame.setLocationRelativeTo(null);;										//Frame en el centro
        frame.setVisible(true);	
    }

	
    /*
     * Metodo donde se controlan los indices del Combo Box
     * Ademas en cada indice se muestra el resultado deseado segun la implementacion escogida por le usuario
     * (non-Javadoc)
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    public void itemStateChanged(ItemEvent a){ 

        if(comboBox.getSelectedIndex() == 0){
        }

        if(comboBox.getSelectedIndex() == 1){
            verificaVacio();
            edad="3,6";
        }

        if(comboBox.getSelectedIndex() == 2){
            verificaVacio();
            edad="7,11";
        }

        if(comboBox.getSelectedIndex() == 3){
            verificaVacio();
            edad="12,19";

        } 	
        if(comboBox.getSelectedIndex() == 4){
            verificaVacio();
            edad="20,39";
        } 
        if(comboBox.getSelectedIndex() == 5){
            verificaVacio();
            edad="40";
        } 
	}
	
	public void verificaVacio(){
            if(jNombre.getText()!=""){
                    if(jCorreo.getText()!=""){
                            btonEnviarDatos.setEnabled(true);
                    }
            }
	}

	public void desactivarTodo(){
		btonEnviarDatos.setEnabled(false);
		jCorreo.setEnabled(false);
		jNombre.setEnabled(false);
		comboBox.setEnabled(false);

	}
   
 
    
    public void verificacionDatos(){
        String sFichero = System.getProperty("user.dir");
            File fichero = new File(sFichero+"\\datosUsuario.txt");

            if(fichero.exists()){
                btonCambiarCorreo.setEnabled(true); 
                desactivarTodo();
            }else{
                    btonCambiarCorreo.setEnabled(false); 
            }
            btonEnviarDatos.setEnabled(false);
    }

}
