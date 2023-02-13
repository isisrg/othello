package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delete_game {
    private JFrame principal;
    private JPanel panel1;
    private JButton titulo;
    private JButton volver_button;
    private JButton borrar_button;
    private Font letra;
    private Color boton;
    private ImageIcon titulo_image;
    private ImageIcon borrar_image;
    private JList list_borrar;


    /**
     * Creation of the screen to delete a previous game
     */
    public Delete_game() {
        load_images();
        principal = new JFrame("Borrar Partida");
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
     * Function with the images used in the delete game screen
     */
    private void load_images(){
        borrar_image = new ImageIcon("src/main/java/Imagenes/deleteres.png");
        titulo_image = new ImageIcon("src/main/java/Imagenes/borrar_partidares.png");

    }

    /**
     * Function to add all the buttons used in the delete game screen
     */
    private void display_buttons(){
        list_borrar = new JList();
        list_borrar.setBounds(330,200,400,300);
        principal.getContentPane().add(list_borrar);
        //Label título
        titulo = new JButton(titulo_image);
        titulo.setBounds(225,0,600,250);
        titulo.setBorderPainted(false);
        titulo.setContentAreaFilled(false);
        principal.getContentPane().add(titulo);

        //Boton Cargar Partida
        borrar_button = new JButton("   Borrar",borrar_image);
        borrar_button.setBounds(850,600,150,25);
        set_boton_predefined(borrar_button);

        //Boton Volver
        volver_button = new JButton("Volver");
        volver_button.setBounds(100,600,100,25);
        principal.getContentPane().add(volver_button);
    }

}