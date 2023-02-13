package Presentacion;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Initial {
    private JFrame principal;
    private JPanel panel1;
    private JPanel menu_panel;
    private JPanel center_panel;
    private JLabel foto;
    private JButton Salir;
    private JButton partida_button;
    private JButton ranking_button;
    private JButton salir_button;
    private JButton title_button;
    private JButton nombres_button;
    private Font letra;
    private Color boton;
    private ImageIcon game_image;
    private ImageIcon ranking_image;
    private ImageIcon close_image;
    private ImageIcon nombres_image;
    private ImageIcon Othello;

    /**
     * Creation of the initial game screen
     */
    public Initial() {
        load_images();
        principal = new JFrame("Othello Grupo 2.1");
        principal.add(panel1);
        principal.add(menu_panel);
        principal.add(center_panel);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal.getContentPane().setLayout(null);
        principal.setResizable(false);
        principal.setVisible(true);

        set_screen();
        visual_parameters();
        display_buttons();

        salir_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        partida_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Screen_game screen = new Screen_game();
                principal.dispose();
            }
        });
        ranking_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Screen_ranking screen = new Screen_ranking();
                principal.dispose();
            }
        });
        nombres_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombres_button.setVisible(false);
                nombres_image = new ImageIcon("src/main/java/Imagenes/bibires.png");
                nombres_button = new JButton(nombres_image);
                nombres_button.setBounds(605,200,600,630);
                nombres_button.setBorderPainted(false);
                nombres_button.setContentAreaFilled(false);
                principal.getContentPane().add(nombres_button);
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
     * Function with the images used in the initial screen
     */
    private void load_images(){
        Othello = new ImageIcon("src/main/java/Imagenes/othello_titulo_finalres.png");
        game_image = new ImageIcon("src/main/java/Imagenes/gameres.png");
        ranking_image = new ImageIcon("src/main/java/Imagenes/rankingres.png");
        close_image = new ImageIcon("src/main/java/Imagenes/Close.png");
        nombres_image = new ImageIcon("src/main/java/Imagenes/nombresres.png");
    }

    /**
     * Function to define the size of a standard button
     * @param button button to be changed
     */
    private void set_boton_predefined(JButton button){
        button.setFont(letra);
        button.setBackground(boton);
        button.setBorder(new RoundedBorder(12));
        principal.getContentPane().add(button);
    }

    /**
     * Function to add all the buttons used in the initial screen
     */
    private void display_buttons(){
        //Imagen Othello portada
        title_button = new JButton(Othello);
        title_button.setBounds(225,0,600,330);
        title_button.setBorderPainted(false);
        title_button.setContentAreaFilled(false);
        principal.getContentPane().add(title_button);

        //Imagen Othello portada
        nombres_button = new JButton(nombres_image);
        nombres_button.setBounds(605,420,600,330);
        nombres_button.setBorderPainted(false);
        nombres_button.setContentAreaFilled(false);
        principal.getContentPane().add(nombres_button);

        //Boton Partida
        partida_button = new JButton("      Partida",game_image);
        partida_button.setBounds(420,350,200,50);
        set_boton_predefined(partida_button);

        //Botón Ranking
        principal.getContentPane().add(partida_button);
        ranking_button = new JButton("      Ranking",ranking_image);
        set_boton_predefined(ranking_button);
        ranking_button.setBounds(420,450,200,50);

        //Botón Salir
        salir_button = new JButton("Salir",close_image);
        salir_button.setFont(letra);
        salir_button.setForeground(Color.white);
        salir_button.setBounds(50,600,150,25);
        salir_button.setBorderPainted(false);
        salir_button.setContentAreaFilled(false);
        principal.getContentPane().add(salir_button);
    }

    /**
     * Function to round the borders of a button
     */
    static class RoundedBorder implements Border {

        private int radius;
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }

}
