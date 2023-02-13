package Presentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeListener;
import Dominio.*;

public class Initial_tokens  extends JFrame implements ActionListener {
    private final JPanel panel1 = new JPanel(new BorderLayout(3, 3));

    private JFrame principal = new JFrame("Initial Tokens");;
    private JButton titulo;
    private JButton titulo_peque;
    private JButton volver_button;
    private JButton play_button;
    private JButton color_button;
    private Font letra;
    private Color boton;
    private ImageIcon titulo_image;
    private ImageIcon titulo_image_peque;
    private ImageIcon jugar_image;
    private ImageIcon ficha_negra;
    private ImageIcon ficha_blanca;
    private JList list_cargar;
    private JButton[][] tablero_botones;
    private int[][] tablero_enteros;
    private JPanel ui;
    private int modality_dif;
    private JComboBox select_diff1;
    private JComboBox select_diff2;
    private int dificulty = 0;
    private int dificulty_machine_1 = 0;
    private int dificulty_machine_2 = 0;
    private JFrame alerta;
    private CtrlPresentacionGame controller_aux;
    private int handicap1;
    private boolean color_ficha_negra = true;
    private JPanel othello_board;

    Initial_tokens(int modalidad,int dimensiones,boolean vertical,boolean horizontal,boolean diagonal,int handicap_1) {
        this.handicap1=handicap_1;
        load_images();
        visual_parameters();
        modality_dif = modalidad;
        display_buttons(dimensiones,modality_dif);
        set_screen();
        create_board(dimensiones);
        CtrlPresentacionGame cp = new CtrlPresentacionGame();
        Board test_board = cp.int_to_board(tablero_enteros, dimensiones);
        Player p1 = new Player("Jugador1",0);
        Player p2 = new Player("Jugador2",1);
        CtrlPresentacionGame cpres = new CtrlPresentacionGame(test_board,p1,p2,vertical,horizontal,diagonal,false);
        controller_aux=cpres;
        printar_tablero();
        principal.add(panel1);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal.setVisible(true);
        volver_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                New_game img = new New_game();
                principal.dispose();
            }
        });
        play_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dificulty_machine_1!=0 && dificulty_machine_1==dificulty_machine_2){
                    JOptionPane.showMessageDialog(alerta,"Las maquinas no pueden tener la misma dificultad");
                }else{
                    CtrlPresentacionGame cpres = new CtrlPresentacionGame(test_board,p1,p2,vertical,horizontal,diagonal,false);
                    Game img = new Game(cpres,modalidad,dificulty_machine_1,dificulty_machine_2,handicap_1);
                    // Game img = new Game(modalidad,dimensiones,vertical,horizontal,diagonal,tablero_enteros);
                    principal.dispose();
                }

            }
        });
        color_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color_ficha_negra = !color_ficha_negra;
            }
        });
    }


private void create_board(int dimensiones){
    ui = new JPanel(new GridBagLayout());
    GridBagConstraints ui_constraints = new GridBagConstraints();
    tablero_botones = new JButton[dimensiones][dimensiones];
    tablero_enteros = new int[dimensiones][dimensiones];
    for(int i=0; i<tablero_enteros.length; i++){
        for(int j=0; j<tablero_enteros[i].length; j++){
            tablero_enteros[i][j]=2;
        }
    }
    int half = dimensiones/2;
    tablero_enteros[half-1][half-1]=1;
    tablero_enteros[half][half-1]=0;
    tablero_enteros[half-1][half]=0;
    tablero_enteros[half][half]=1;


    ui.setBounds(0,120,1080,500);
    ui.setLocation(0,120);
    ui.setBackground(Color.BLACK);
    panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
    othello_board = new JPanel(new GridLayout(tablero_botones.length, tablero_botones.length));
    ui.add(othello_board,ui_constraints);
    panel1.add(ui);
    panel1.setBackground(Color.BLACK);

    Insets buttonMargin = new Insets(0,0,0,0);
    for (int ii = 0; ii < tablero_botones.length; ii++) {
        for (int jj = 0; jj < tablero_botones[ii].length; jj++) {
            JButton b = new JButton();
            b.setMargin(buttonMargin);
            ImageIcon icon = new ImageIcon(
                    new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB));
            b.setIcon(icon);
            b.setBackground(boton);
            b.addActionListener(this);
            tablero_botones[jj][ii] = b;
        }
    }
    for (int ii = 0; ii < tablero_botones.length; ii++) {
        for (int jj = 0; jj < tablero_botones[ii].length; jj++) {
            switch (jj) {
                default:
                    othello_board.add(tablero_botones[jj][ii]);
            }
        }
    }
    //printar_tablero();
}
    private void printar_tablero(){
        for(int i=0; i< controller_aux.get_game_size(controller_aux.get_game_id()); i++){
            for(int j=0; j<controller_aux.get_game_size(controller_aux.get_game_id()); j++){
                if(controller_aux.is_black(controller_aux.get_game_id(), i,j)){
                    tablero_botones[j][i].setIcon(ficha_negra);
                }
                else if(controller_aux.is_white(controller_aux.get_game_id(), i,j))tablero_botones[j][i].setIcon(ficha_blanca);
            }
        }

    }
    private void visual_parameters() {
        letra = new Font("Verdana",Font.BOLD,15);
        boton = new Color(109,255,109);
    }
    private void set_screen(){
        principal.setSize(1080,720);
        principal.setLocationRelativeTo(null);
        principal.getContentPane().setBackground(Color.BLACK);
    }
    private void load_images(){
        jugar_image = new ImageIcon("src/main/java/Imagenes/playres.png");
        titulo_image = new ImageIcon("src/main/java/Imagenes/fichas_inicialesres.png");
        titulo_image_peque = new ImageIcon("src/main/java/Imagenes/fichas_inicialesres.png");
        ficha_negra = new ImageIcon("src/main/java/Imagenes/ficha_negra_res.png");
        ficha_blanca = new ImageIcon("src/main/java/Imagenes/ficha_blanca_res.png");

    }
    private void set_boton_predefined(JButton button){
        button.setFont(letra);
        button.setBackground(boton);
        button.setBorder(new Initial.RoundedBorder(10));
        principal.getContentPane().add(button);
    }

    private void set_combo_predefined(JComboBox combo){
        combo.setSelectedIndex(0);
        combo.setFont(letra);
        combo.setBackground(boton);
        combo.setBorder(new Initial.RoundedBorder(10));
        principal.getContentPane().add(combo);
    }
    private void display_buttons(int dimensiones, int modality_dif){
        //Label título
        titulo = new JButton(titulo_image);
        titulo_peque = new JButton(titulo_image_peque);
        if(dimensiones==14 ){
            titulo.setBounds(225,-40,600,130);
        } else if(dimensiones==15){
            titulo.setBounds(225,-45,600,120);
        }else if(dimensiones==16){
            titulo.setBounds(225,-45,600,100);
        }
        else titulo.setBounds(225,-70,600,250);
        titulo.setBorderPainted(false);
        titulo.setContentAreaFilled(false);
        principal.add(titulo);

        //Boton Jugar Partida
        play_button = new JButton("   Jugar",jugar_image);
        play_button.setBounds(850,600,150,50);
        set_boton_predefined(play_button);

        //Boton Cambiar Color ficha
        color_button = new JButton("Cambia Color Ficha");
        color_button.setBounds(850,400,200,50);
        set_boton_predefined(color_button);

        //Boton Volver
        volver_button = new JButton("Volver");
        volver_button.setBounds(100,600,100,25);
        principal.getContentPane().add(volver_button);

        if(modality_dif == 1){
            //Combo dif1
            String[] Modalidad = { "Maquina 1 Facil", "Maquina 1 Normal", "Maquina 1 Dificil" };
            select_diff1 = new JComboBox(Modalidad);
            select_diff1.setBounds(20,300,230,50);
            select_diff1.addActionListener(this);
            set_combo_predefined(select_diff1);
        }
        else if (modality_dif == 2){
            //Combo dif2
            String[] Modalidad = { "Maquina 1 Facil", "Maquina 1 Normal", "Maquina 1 Dificil" };
            select_diff1 = new JComboBox(Modalidad);
            select_diff1.setBounds(20,300,230,50);
            select_diff1.addActionListener(this);
            set_combo_predefined(select_diff1);

            String[] Modalidad2 = { "Maquina 2 Facil", "Maquina 2 Normal", "Maquina 2 Dificil" };
            select_diff2 = new JComboBox(Modalidad2);
            select_diff2.setBounds(20,400,230,50);
            select_diff2.setVisible(true);
            select_diff2.addActionListener(this);
            set_combo_predefined(select_diff2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(modality_dif == 1 && e.getSource()==select_diff1){
            JComboBox result = (JComboBox) e.getSource();
            String string_result = (String) result.getSelectedItem();
            if(string_result == "Maquina 1 Facil")dificulty_machine_1 = 1;
            else if(string_result == "Maquina 1 Normal")dificulty_machine_1 = 2;
            else if(string_result == "Maquina 1 Dificil")dificulty_machine_1 = 3;
        }
        else if(modality_dif == 2 && ((e.getSource()==select_diff1) || (e.getSource()==select_diff2))){
            if(e.getSource()==select_diff1){

                JComboBox result = (JComboBox) e.getSource();
                String string_result = (String) result.getSelectedItem();
                if(string_result == "Maquina 1 Facil") dificulty_machine_1=1;
                else if(string_result == "Maquina 1 Normal")dificulty_machine_1 = 2;
                else if(string_result == "Maquina 1 Dificil")dificulty_machine_1 = 3;
            }
            if(e.getSource()==select_diff2){
                JComboBox result = (JComboBox) e.getSource();
                String string_result = (String) result.getSelectedItem();
                if(string_result == "Maquina 2 Facil") dificulty_machine_2=1;
                else if(string_result == "Maquina 2 Normal")dificulty_machine_2 = 2;
                else if(string_result == "Maquina 2 Dificil")dificulty_machine_2 = 3;
            }
        }
        else if( (modality_dif==0 || modality_dif==1) || (modality_dif==2 && (e.getSource()!=select_diff1) && (e.getSource()!=select_diff2))){
            int columnas = (((JButton) e.getSource()).getX()) / 38;
            int filas= (((JButton) e.getSource()).getY()) / 38;
            HashMap<Cell, ArrayList<Integer>> valid_positions=new HashMap<>();
            Cell test=controller_aux.get_cell(controller_aux.get_game_id(), filas,columnas);

            if(color_ficha_negra){
                valid_positions=controller_aux.legal_moves(controller_aux.get_game_id(), controller_aux.get_p1(controller_aux.get_game_id()), controller_aux.get_p2(controller_aux.get_game_id()));
                if(valid_positions.containsKey(test)) {
                    controller_aux.set_cell(controller_aux.get_game_id(), filas,columnas, controller_aux.get_p1(controller_aux.get_game_id()));
                    ((JButton) e.getSource()).setIcon(ficha_negra);
                }
                printar_tablero();
            }else if(!color_ficha_negra){
                valid_positions=controller_aux.legal_moves(controller_aux.get_game_id(), controller_aux.get_p2(controller_aux.get_game_id()), controller_aux.get_p1(controller_aux.get_game_id()));
                if(valid_positions.containsKey(test)) {
                    controller_aux.set_cell(controller_aux.get_game_id(), filas,columnas, controller_aux.get_p2(controller_aux.get_game_id()));
                    ((JButton) e.getSource()).setIcon(ficha_blanca);
                }
                printar_tablero();
            }
            printar_tablero();
        }
        //printar_tablero();
    }
}