package Presentacion;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class New_game extends JFrame implements ChangeListener, ActionListener{
    private JPanel panel1;
    private JFrame principal;
    private JComboBox select_modality;
    private JSpinner select_size;
    private SpinnerModel sm;
    private JCheckBox select_vertical;
    private JCheckBox select_horizontal;
    private JCheckBox select_diagonal;
    private JComboBox select_handicap;
    private JComboBox select_handicap1;
    private JButton return_button;
    private JButton iniciar_button;
    private JButton titulo;
    private JLabel phrase_modality;
    private JLabel phrase_size;
    private JLabel phrase_rules;
    private JLabel phrase_handicap;
    private Font letra_boton;
    private Font letra_frase;
    private Color boton;
    private ImageIcon titulo_image;
    private ImageIcon iniciar_image;
    private boolean vertical = true;
    private boolean horizontal = false;
    private boolean diagonal = false;
    private int modalidad = 0;
    private int dimensiones = 8;
    private Game game_v;
    private int handicap_1;
    private JFrame alerta;



    /**
     * Creación de la pantalla para elegir los parametros de una partida nueva
     */
    public New_game() {
        load_images();
        principal = new JFrame("Nueva Partida");
        principal.add(panel1);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal.getContentPane().setLayout(null);
        principal.setResizable(false);
        principal.setVisible(true);

        set_screen();
        visual_parameters();
        display_interface();

        select_handicap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handicap_1=select_handicap.getSelectedIndex();
            }
        });

        return_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Screen_game img = new Screen_game();
                principal.dispose();
            }
        });
        iniciar_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!vertical && !horizontal && !diagonal) JOptionPane.showMessageDialog(alerta,"Selecciona al menos una regla");
                else{
                    dimensiones = (int) select_size.getValue();
                    Initial_tokens img = new Initial_tokens(modalidad,dimensiones,vertical,horizontal,diagonal,handicap_1);
                    principal.dispose();
                }
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
        letra_boton = new Font("Verdana",Font.BOLD,15);
        letra_frase = new Font("Verdana",Font.BOLD,15);
        boton = new Color(109,255,109);
    }

    /**
     * Function to set the standard parameters of the comboBox
     * @param combo Label to be changed
     */
    private void set_combo_predefined(JComboBox combo){
        combo.setSelectedIndex(0);
        combo.setFont(letra_boton);
        combo.setBackground(boton);
        combo.setBorder(new Initial.RoundedBorder(10));
        principal.getContentPane().add(combo);
    }

    /**
     * Function to set the standard parameters of the label
     * @param phrase Label to be changed
     */
    private void set_phrase_predefined(JLabel phrase){
        phrase.setFont(letra_frase);
        phrase.setForeground(Color.white);
        principal.getContentPane().add(phrase);
    }

    /**
     * Function to define the size of a standard button
     * @param button button to be changed
     */
    private void set_boton_predefined(JButton button){
        button.setFont(letra_boton);
        button.setBackground(boton);
        button.setBorder(new Initial.RoundedBorder(10));
        principal.getContentPane().add(button);
    }

    /**
     * Function with the images used in the screen to chose the parameters to start a new game
     */
    private void load_images(){
        iniciar_image = new ImageIcon("src/main/java/Imagenes/gameres.png");
        titulo_image = new ImageIcon("src/main/java/Imagenes/nueva_partidares.png");

    }

    /**
     * Function to add all the components used in the new game screen
     */
    private void display_interface(){
        //Combo Modalidad
        String[] Modalidad = { "Jugador vs Jugador", "Jugador vs Máquina", "Máquina vs Máquina" };
        select_modality = new JComboBox(Modalidad);
        select_modality.setBounds(600,150,230,50);
        select_modality.addActionListener(this);
        set_combo_predefined(select_modality);

        //Frase modalidad
        phrase_modality = new JLabel("Seleccione la modalidad de la partida");
        phrase_modality.setBounds(250,150,400,50);
        set_phrase_predefined(phrase_modality);

        //Caja de Texto Dimensiones
        sm = new SpinnerNumberModel(8,4,16,1);
        select_size = new JSpinner(sm);
        JFormattedTextField block_spinner = ((JSpinner.DefaultEditor)select_size.getEditor()).getTextField();
        block_spinner.setEditable(false);
        block_spinner.setBackground(boton);

        select_size.setBounds(600,240,200,50);
        select_size.setFont(letra_boton);
        select_size.setBackground(boton);
        principal.getContentPane().add(select_size);

        //Frase Dimensiones
        phrase_size = new JLabel("Introduzca las dimensiones del tablero: ");
        phrase_size.setBounds(250,240,400,50);
        set_phrase_predefined(phrase_size);

        select_vertical = new JCheckBox("Vertical");
        select_vertical.setFont(letra_boton);
        select_vertical.setBackground(Color.BLACK);
        select_vertical.setForeground(Color.white);
        select_vertical.setSelected(true);
        select_vertical.setBounds(600,330,200,50);
        select_vertical.addChangeListener(this);
        principal.getContentPane().add(select_vertical);

        select_horizontal = new JCheckBox("Horizontal");
        select_horizontal.setFont(letra_boton);
        select_horizontal.setBackground(Color.BLACK);
        select_horizontal.setSelected(false);
        select_horizontal.setBounds(600,370,200,50);
        select_horizontal.setForeground(Color.white);
        select_horizontal.addChangeListener(this);
        principal.getContentPane().add(select_horizontal);

        select_diagonal = new JCheckBox("Diagonal");
        select_diagonal.setFont(letra_boton);
        select_diagonal.setBackground(Color.BLACK);
        select_diagonal.setSelected(false);
        select_diagonal.setBounds(600,410,200,50);
        select_diagonal.setForeground(Color.white);
        select_diagonal.addChangeListener(this);
        principal.getContentPane().add(select_diagonal);

        //Frase Reglas
        phrase_rules = new JLabel("Selecciona las reglas de la partida: ");
        phrase_rules.setBounds(250,330,400,50);
        set_phrase_predefined(phrase_rules);

        //Combo Handicap
        String[] Handicap_jugador1 = { "0 skip", "1 skip", "2 skip","Recomendacion" };
        select_handicap = new JComboBox(Handicap_jugador1);
        select_handicap.setBounds(600,480,200,50);
        set_combo_predefined(select_handicap);

        //Frase Handicap
        phrase_handicap = new JLabel("¿Quieres dar Handicap al jugador1? ");
        phrase_handicap.setBounds(250,480,400,50);
        set_phrase_predefined(phrase_handicap);

        String[] Handicap_jugador2 = { "0 skip", "1 skip", "2 skip","Recomendacion" };
        select_handicap1 = new JComboBox(Handicap_jugador2);
        select_handicap1.setBounds(600,580,200,50);
        set_combo_predefined(select_handicap1);

        phrase_handicap = new JLabel("¿Quieres dar Handicap al jugador2? ");
        phrase_handicap.setBounds(250,580,400,50);
        set_phrase_predefined(phrase_handicap);

        //Boton Volver
        return_button = new JButton("Volver");
        return_button.setBounds(100,580,100,25);
        principal.getContentPane().add(return_button);

        //Boton Iniciar Partida
        iniciar_button = new JButton("   Iniciar",iniciar_image);
        iniciar_button.setBounds(850,580,150,25);
        set_boton_predefined(iniciar_button);

        //Label título
        titulo = new JButton(titulo_image);
        titulo.setBounds(225,-100,600,300);
        titulo.setBorderPainted(false);
        titulo.setContentAreaFilled(false);
        principal.getContentPane().add(titulo);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(select_vertical.isSelected()==true){
            vertical = true;
        }
        else if(select_vertical.isSelected()==false){
            vertical = false;
        }
        if(select_horizontal.isSelected()==true){
            horizontal = true;
        }
        else if(select_horizontal.isSelected()==false){
            horizontal = false;
        }
        if(select_diagonal.isSelected()==true){
            diagonal = true;
        }
        else if(select_diagonal.isSelected()==false){
            diagonal = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox result = (JComboBox) e.getSource();
        String string_result = (String) result.getSelectedItem();
        if(string_result == "Jugador vs Jugador")modalidad = 0;
        else if(string_result == "Jugador vs Máquina")modalidad = 1;
        else if(string_result == "Máquina vs Máquina")modalidad = 2;
    }

}
