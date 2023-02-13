package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen_game {
    private JFrame principal;
    private JPanel panel1;
    private JButton nueva_button;
    private JButton cargar_button;
    private JButton volver_button;
    private JButton borrar_button;
    private JButton titulo;
    private Font letra;
    private Color boton;
    private ImageIcon nueva_image;
    private ImageIcon cargar_image;
    private ImageIcon delete_image;
    private ImageIcon partida_image;

    /**
     * Creation of the screen game to chose what to do with game
     */
    public Screen_game() {
        load_images();
        principal = new JFrame("Partida");
        principal.add(panel1);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal.getContentPane().setLayout(null);
        principal.setResizable(false);
        principal.setVisible(true);

        set_screen();
        visual_parameters();
        display_buttons();

        nueva_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                New_game img = new New_game();
                principal.dispose();
            }
        });
        cargar_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Load_game img = new Load_game();
                principal.dispose();
            }
        });
        borrar_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete_game img = new Delete_game();
                principal.dispose();
            }
        });

        volver_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Initial img = new Initial();
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
     * Function with the images used in the game screen
     */
    private void load_images(){
        nueva_image = new ImageIcon("src/main/java/Imagenes/nuevares.png");
        cargar_image = new ImageIcon("src/main/java/Imagenes/cargarres.png");
        delete_image = new ImageIcon("src/main/java/Imagenes/deleteres.png");
        partida_image = new ImageIcon("src/main/java/Imagenes/partida_greenres.png");

    }

    /**
     * Function to add all the buttons used in the game screen
     */
    private void display_buttons(){
        titulo = new JButton(partida_image);
        titulo.setBounds(225,0,600,250);
        titulo.setBorderPainted(false);
        titulo.setContentAreaFilled(false);
        principal.getContentPane().add(titulo);

        //Boton Nueva Partida
        nueva_button = new JButton("   Nueva Partida",nueva_image);
        nueva_button.setBounds(420,230,200,50);
        set_boton_predefined(nueva_button);

        //Boton Cargar Partida
        cargar_button = new JButton("   Cargar Partida",cargar_image);
        cargar_button.setBounds(420,350,200,50);
        set_boton_predefined(cargar_button);

        //Boton Borrar Partida
        borrar_button = new JButton("   Borrar Partida",delete_image);
        borrar_button.setBounds(420,470,200,50);
        set_boton_predefined(borrar_button);

        //Boton Volver
        volver_button = new JButton("Volver");
        volver_button.setBounds(100,600,100,25);
        principal.getContentPane().add(volver_button);
    }

}