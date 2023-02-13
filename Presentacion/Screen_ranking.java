package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Screen_ranking {
    private JFrame principal;
    private JFrame alerta;
    private JPanel panel1;
    private JButton see_ranking;
    private JButton delete_ranking;
    private JButton return_button;
    private JButton titulo;
    private Font letra;
    private Color boton;
    private ImageIcon ver_ranking_image;
    private ImageIcon delete_image;
    private ImageIcon ranking_image;
    private CtrlPresentacionGame controller_aux;

    /**
     * Creation of the screen what to do with ranking
     */
    public Screen_ranking() {
        controller_aux = new CtrlPresentacionGame ();
        load_images();
        principal = new JFrame("Ranking");
        principal.add(panel1);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal.getContentPane().setLayout(null);
        principal.setResizable(false);
        principal.setVisible(true);

        set_screen();
        visual_parameters();
        display_buttons();


        return_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Initial img = new Initial();
                principal.dispose();
            }
        });
        delete_ranking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JOptionPane optionPane = new JOptionPane(
                        "¿Estás seguro de que quieres borrar el ranking?\n",
                        JOptionPane.WARNING_MESSAGE,
                        JOptionPane.YES_NO_OPTION);

                final JDialog dialog = new JDialog(alerta,
                        "Pulsa un botón",
                        true);
                dialog.setContentPane(optionPane);
                dialog.setLocation(770,450);
                dialog.setDefaultCloseOperation(
                        JDialog.DO_NOTHING_ON_CLOSE);
                optionPane.addPropertyChangeListener(
                        new PropertyChangeListener() {
                            public void propertyChange(PropertyChangeEvent e) {
                                String prop = e.getPropertyName();
                                if (dialog.isVisible()
                                        && (e.getSource() == optionPane)
                                        && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                                    dialog.setVisible(false);
                                }
                            }
                        });
                dialog.pack();
                dialog.setVisible(true);

                int value = ((Integer)optionPane.getValue()).intValue();
                if (value == JOptionPane.YES_OPTION) {
                    controller_aux.restart_stats();
                } else if (value == JOptionPane.NO_OPTION) {
                }
            }
        });

        see_ranking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display_ranking img = new Display_ranking(controller_aux);
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
     * Function with the images used in the ranking screen
     */
    private void load_images(){
        ver_ranking_image = new ImageIcon("src/main/java/Imagenes/verrankingres.png");
        delete_image = new ImageIcon("src/main/java/Imagenes/delete2res.png");
        ranking_image = new ImageIcon("src/main/java/Imagenes/ranking_greenres.png");

    }

    /**
     * Function to add all the buttons used in the ranking screen
     */
    private void display_buttons(){
        titulo = new JButton(ranking_image);
        titulo.setBounds(225,0,600,250);
        titulo.setBorderPainted(false);
        titulo.setContentAreaFilled(false);
        principal.getContentPane().add(titulo);

        //Boton Ver Ranking
        see_ranking = new JButton(" Consultar Ranking", ver_ranking_image);
        see_ranking.setBounds(420,300,220,50);
        set_boton_predefined(see_ranking);

        //Boton Borrar
        delete_ranking = new JButton(" Borrar Ranking",delete_image);
        delete_ranking.setBounds(420,450,220,50);
        set_boton_predefined(delete_ranking);

        //Borrar Volver
        return_button = new JButton("Volver");
        return_button.setBounds(100,600,100,25);
        principal.getContentPane().add(return_button);
    }

}
