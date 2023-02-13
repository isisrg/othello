package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Load_game {
    private JFrame principal;
    private JPanel panel1;
    private JButton titulo;
    private JButton volver_button;
    private JButton cargar_button;
    private Font letra;
    private Color boton;
    private ImageIcon titulo_image;
    private ImageIcon cargar_image;
    private JList list_cargar;


    /**
     * Creación de la pantalla para elegir una partida a cargar
     */
    public Load_game() {
        load_images();
        principal = new JFrame("Cargar Partida");
        principal.add(panel1);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal.getContentPane().setLayout(null);
        principal.setResizable(false);
        principal.setVisible(true);

        set_screen();
        visual_parameters();
        display_buttons();

        volver_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Screen_game img = new Screen_game();
                principal.dispose();
            }
        });
    }

    /**
     * Function to define the screen size
     */
    private void set_screen(){
        principal.setSize(1080,720);
        principal.setLocationRelativeTo(null);
        principal.getContentPane().setBackground(Color.BLACK);
    }

    /**
     * Function to definie the most common parameters such as font, size and the green colour used
     */
    private void visual_parameters() {
        letra = new Font("Verdana",Font.BOLD,15);
        boton = new Color(109,255,109);
    }

    /**
     * Function to define the size of a standard button
     * @param button button to be changed
     */
    private void set_boton_predefined(JButton button){
        button.setFont(letra);
        button.setBackground(boton);
        button.setBorder(new Initial.RoundedBorder(10));
        principal.getContentPane().add(button);
    }

    /**
     * Function with the images used in the load game screen
     */
    private void load_images(){
        cargar_image = new ImageIcon("src/main/java/Imagenes/cargarres.png");
        titulo_image = new ImageIcon("src/main/java/Imagenes/cargar_partidares.png");

    }

    /**
     * Function to add all the buttons used in the load game screen
     */
    private void display_buttons(){
        //Label título
        titulo = new JButton(titulo_image);
        titulo.setBounds(225,0,600,150);
        titulo.setBorderPainted(false);
        titulo.setContentAreaFilled(false);
        principal.getContentPane().add(titulo);

        CtrlPresentacionGame ctrlPresentacion=new CtrlPresentacionGame();
        String [] data= ctrlPresentacion.get_game_list();
        //String [] data= {"ola","adios"};
        list_cargar = new JList(data);
        list_cargar.setBounds(330,200,400,300);
        principal.getContentPane().add(list_cargar);
        //Boton Cargar Partida
        cargar_button = new JButton("   Cargar",cargar_image);
        cargar_button.setBounds(850,600,150,25);
        set_boton_predefined(cargar_button);

        //Boton Volver
        volver_button = new JButton("Volver");
        volver_button.setBounds(100,600,100,25);
        principal.getContentPane().add(volver_button);
    }

}