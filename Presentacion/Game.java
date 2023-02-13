package Presentacion;


import Dominio.Cell;
import Dominio.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.border.*;

public class Game  extends JFrame implements ActionListener{
    private final JPanel panel1 = new JPanel(new BorderLayout(3, 3));

    private JPanel othello_board;
    private JFrame principal = new JFrame("Othello");;
    private JButton titulo;
    private JButton titulo_peque;
    private JButton saltar_turno;
    private JButton abandonar_button;
    private JButton salir_button;
    private JButton save_game;
    private JButton jugar_button;
    private JButton color_button;
    private Font letra;
    private Font letra2;
    private Color boton;
    private ImageIcon titulo_image;
    private ImageIcon titulo_image_peque;
    private ImageIcon jugar_image;
    private ImageIcon ficha_negra;
    private ImageIcon ficha_blanca;
    private JList list_cargar;
    private JButton[][] tablero_botones;
    private JPanel ui;
    private CtrlPresentacionGame controller_aux;
    int modalidad;
    int difficulty_1;
    int difficulty_2;
    private JFrame alerta;
    boolean acaba =false;
    private JLabel phrase_nombre1;
    private JLabel phrase_nombre2;
    private JTextField nombre1;
    private JTextField nombre2;
    private int handicap_1;


    Game(CtrlPresentacionGame cpres,int modalidad, int difficulty_1, int difficulty_2, int handicap_1) {
        controller_aux=cpres;
        this.handicap_1=handicap_1;
        this.modalidad=modalidad;
        this.difficulty_1=difficulty_1;
        this.difficulty_2=difficulty_2;
        int size_board=cpres.get_game_size(cpres.get_game_id());
        load_images();
        visual_parameters();
        display_buttons(size_board);
        set_screen();
        create_board(size_board);
        principal.add(panel1);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal.setVisible(true);
        if(modalidad==2){
            int i=0;
            while(i<=200) {
                move_machine(controller_aux.get_p1(controller_aux.get_game_id()));
                move_machine(controller_aux.get_p2(controller_aux.get_game_id()));
                //wait(1000);
                printar_tablero();
                i++;
            }
        }



        abandonar_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                New_game img = new New_game();
                principal.dispose();
            }
        });
        /*saltar_turno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller_aux.change_turn();
            }
        });*/
        salir_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(acaba) {
                    if (modalidad == 0) {
                        nombre1.setVisible(true);
                        nombre2.setVisible(true);
                        phrase_nombre1.setVisible(true);
                        phrase_nombre2.setVisible(true);
                        String name_player1 = nombre1.getText();
                        controller_aux.set_players_name(controller_aux.get_game_id(), name_player1, 1);
                        String name_player2 = nombre2.getText();
                        controller_aux.set_players_name(controller_aux.get_game_id(), name_player2, 2);
                        //int score1= controller_aux.get_score_player(controller_aux.get_game_id(),1);
                        //controller_aux.set_score(controller_aux.get_game_id(),score1,1);
                        //int score2= controller_aux.get_score_player(controller_aux.get_game_id(),2);
                        //controller_aux.set_score(controller_aux.get_game_id(),score2,2);
                        controller_aux.set_players_ranking(controller_aux.get_game_id());
                    }
                    if(modalidad == 1){
                        String name_player1 = nombre1.getText();
                        controller_aux.set_players_name(controller_aux.get_game_id(),name_player1, 1);
                        controller_aux.set_players_ranking(controller_aux.get_game_id());

                    }
                }

                Display_ranking img = new Display_ranking(controller_aux);
                principal.dispose();
            }
        });
    }

    public void move_machine(Player machine){
        Cell move=controller_aux.next_move(controller_aux.get_game_id(), difficulty_1);
        controller_aux.set_cell(controller_aux.get_game_id(), controller_aux.get_row(move), controller_aux.get_col(move), machine);
        controller_aux.change_turn();
    }


    private void create_board(int dimensiones){
        ui = new JPanel(new GridBagLayout());
        GridBagConstraints ui_constraints = new GridBagConstraints();
        tablero_botones = new JButton[dimensiones][dimensiones];

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
        printar_tablero();
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
        letra = new Font("Verdana",Font.BOLD,9);
        letra2 = new Font("Verdana",Font.BOLD,13);
        boton = new Color(109,255,109);
    }
    private void set_screen(){
        principal.setSize(1080,720);
        principal.setLocationRelativeTo(null);
        principal.getContentPane().setBackground(Color.BLACK);
    }
    private void load_images(){
        jugar_image = new ImageIcon("src/main/java/Imagenes/playres.png");
        ficha_negra = new ImageIcon("src/main/java/Imagenes/ficha_negra_res.png");
        ficha_blanca = new ImageIcon("src/main/java/Imagenes/ficha_blanca_res.png");

    }
    private void set_boton_predefined(JButton button){
        button.setFont(letra);
        button.setBackground(boton);
        button.setBorder(new Initial.RoundedBorder(10));
        principal.getContentPane().add(button);
    }

    private void set_phrase_predefined(JLabel phrase){
        phrase.setFont(letra);
        phrase.setForeground(Color.white);
        principal.getContentPane().add(phrase);
    }

    private void display_buttons(int dimensiones){

        //Boton Handicap
        /*saltar_turno = new JButton("Saltar turno");
        saltar_turno.setBounds(450,630,200,25);
        set_boton_predefined(saltar_turno);
        saltar_turno.addActionListener(this);
        principal.getContentPane().add(saltar_turno);*/
        save_game = new JButton("Guardar Partida");
        save_game.setBounds(900,400,150,50);
        save_game.setFont(letra2);
        save_game.setBackground(boton);
        save_game.setBorder(new Initial.RoundedBorder(10));
        principal.getContentPane().add(save_game);

        //Boton Abandonar
        abandonar_button = new JButton("Abandonar");
        abandonar_button.setBounds(100,600,100,25);
        principal.getContentPane().add(abandonar_button);

        //Boton Salir
        salir_button = new JButton("Salir");
        salir_button.setBounds(100,600,100,25);
        salir_button.setVisible(false);
        principal.getContentPane().add(salir_button);

        phrase_nombre1 = new JLabel("Nombre jugador 1");
        phrase_nombre1.setBounds(20,400,100,25);
        set_phrase_predefined(phrase_nombre1);

        nombre1 = new JTextField("");
        nombre1.setBounds(20,430,100,25);
        nombre1.setBackground(Color.green);
        principal.getContentPane().add(nombre1);

        nombre1.addActionListener(this);

        phrase_nombre2 = new JLabel("Nombre jugador 2");
        phrase_nombre2.setBounds(20,500,100,25);
        set_phrase_predefined(phrase_nombre2);

        nombre2 = new JTextField("");
        nombre2.setBounds(20,530,100,25);
        nombre2.setBackground(Color.green);
        nombre1.setVisible(false);
        nombre2.setVisible(false);
        nombre2.addActionListener(this);

        phrase_nombre1.setVisible(false);
        phrase_nombre2.setVisible(false);
        principal.getContentPane().add(nombre2);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        int columnas = (((JButton) e.getSource()).getX()) / 38;
        int filas= (((JButton) e.getSource()).getY()) / 38;
        HashMap<Cell, ArrayList<Integer>> valid_positions=new HashMap<>();
        Cell test=controller_aux.get_cell(controller_aux.get_game_id(), filas,columnas);

        if(modalidad==0) {
            if (controller_aux.is_black_turn()) {
                valid_positions = controller_aux.legal_moves(controller_aux.get_game_id(), controller_aux.get_p1(controller_aux.get_game_id()), controller_aux.get_p2(controller_aux.get_game_id()));
                if (valid_positions.containsKey(test)) {
                    controller_aux.set_cell(controller_aux.get_game_id(), filas, columnas, controller_aux.get_p1(controller_aux.get_game_id()));
                    ((JButton) e.getSource()).setIcon(ficha_negra);
                    controller_aux.change_turn();
                }else if(valid_positions.isEmpty()){
                    controller_aux.change_turn();
                }
            } else if (controller_aux.is_white_turn()) {
                valid_positions = controller_aux.legal_moves(controller_aux.get_game_id(), controller_aux.get_p2(controller_aux.get_game_id()), controller_aux.get_p1(controller_aux.get_game_id()));
                if (valid_positions.containsKey(test)) {
                    controller_aux.set_cell(controller_aux.get_game_id(), filas, columnas, controller_aux.get_p2(controller_aux.get_game_id()));
                    ((JButton) e.getSource()).setIcon(ficha_blanca);
                    controller_aux.change_turn();
                }else if(valid_positions.isEmpty()){
                    controller_aux.change_turn();
                }
            }
        }else if(modalidad==1){
            if (controller_aux.is_black_turn()) {
                valid_positions = controller_aux.legal_moves(controller_aux.get_game_id(), controller_aux.get_p1(controller_aux.get_game_id()), controller_aux.get_p2(controller_aux.get_game_id()));
                if (valid_positions.containsKey(test)) {
                    controller_aux.set_cell(controller_aux.get_game_id(), filas, columnas, controller_aux.get_p1(controller_aux.get_game_id()));
                    ((JButton) e.getSource()).setIcon(ficha_negra);
                    controller_aux.change_turn();
                    move_machine(controller_aux.get_p2(controller_aux.get_game_id()));
                }
            }
        }
        printar_tablero();
        if(controller_aux.is_finished(controller_aux.get_game_id())){
            JOptionPane.showMessageDialog(alerta,"Acabo la partida");
            abandonar_button.setVisible(false);
            salir_button.setVisible(true);
            acaba = true;
            if(acaba){
                if(modalidad==0){
                    nombre1.setVisible(true);
                    nombre2.setVisible(true);
                    phrase_nombre1.setVisible(true);
                    phrase_nombre2.setVisible(true);
                }
                if(modalidad==1){
                    nombre1.setVisible(true);
                    phrase_nombre1.setVisible(true);
                }
            }
        }

    }
}