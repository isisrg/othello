package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Display_ranking {
    private JFrame principal;
    private JPanel panel1;
    private JButton titulo;
    private JButton volver_button;
    private JButton cargar_button;
    private Font letra;
    private Color boton;
    private ImageIcon titulo_image;
    private ImageIcon cargar_image;
    private JList list_ver;

    private JTable display_rank;
    private CtrlPresentacionGame controller_aux;
    private HashMap<String, ArrayList<Integer>> registro;

    /**
     * Creation of the screen to display a ranking
     */
    public Display_ranking(CtrlPresentacionGame controller_aux) {
        registro = controller_aux.get_stats();

        display_rank = new JTable(registro.size(),2);
        viewAll();
        display_rank.setBounds(300,200,500,300);

        load_images();
        principal = new JFrame("Consultar Ranking");
        principal.add(panel1);
        principal.add(display_rank);
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
                Screen_ranking img = new Screen_ranking();
                principal.dispose();
            }
        });
    }
    private void viewAll() {
        int row = 0;
        for(Map.Entry<String, ArrayList<Integer>> entry : registro.entrySet()){
            display_rank.setValueAt(entry.getKey(), row, 0);
            display_rank.setValueAt(entry.getValue(), row, 1);
            row++;
        }
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
     * Function with the images used in the display ranking screen
     */
    private void load_images(){
        titulo_image = new ImageIcon("src/main/java/Imagenes/consultar_rankingres.png");

    }

    /**
     * Function to add all the buttons used in the display ranking screen
     */
    private void display_buttons(){
        //Label título
        titulo = new JButton(titulo_image);
        titulo.setBounds(225,0,600,250);
        titulo.setBorderPainted(false);
        titulo.setContentAreaFilled(false);
        principal.getContentPane().add(titulo);

        //Boton Volver
        volver_button = new JButton("Volver");
        volver_button.setBounds(100,600,100,25);
        principal.getContentPane().add(volver_button);
    }

}