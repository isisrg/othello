package Dominio;

/**
 * Class used to test all the functions in Class Game
 * @author Isis
 * @version 1.0 27/04/2021
 * @since 1.8
 */

import java.util.*;

public class DriverGame {
    static void print_board(Board b){
        for(int i=0;i<b.get_size();i++){
            for(int j=0;j< b.get_size();j++){
                System.out.print(b.get_cell(i,j).get_color());
            }
            System.out.println();
        }
    }

    public static void main(String[] ars) {
        boolean test = true;
        boolean first = true;
        while(test){
            Scanner input_user = new Scanner(System.in);
            System.out.println("El juego de pruebas ha comenzado");
            System.out.println("--------------------------------");
            Board test_board = new Board(4);
            //initialize_default_board && set_player_cells
            test_board.initialize_default_board();

            String player_name = " ";
            while(player_name.equals(" ") || player_name.length() > 20){
                System.out.println("Introduce el nombre del Jugador 1:");
                player_name = input_user.next();
                if(player_name.equals(" ")) System.out.println("Este no es un nombre valido, intentalo de nuevo");
                if(player_name.length() > 20) System.out.println("El nombre del jugador supera los 20 caracteres, intentalo de nuevo");
                System.out.println();
            }
            Player p1 = new Player(player_name, 0);

            while(player_name.equals(" ") || player_name.length() > 20 || player_name.equals(p1.get_name())){
                System.out.println("Introduce el nombre del Jugador 2:");
                player_name = input_user.next();
                if(player_name.equals(" ")) System.out.println("Este no es un nombre valido, intentalo de nuevo");
                if(player_name.length() > 20) System.out.println("El nombre del jugador supera los 20 caracteres, intentalo de nuevo");
                if(player_name.equals(p1.get_name())) System.out.println("Este jugador ya existe, por favor introduce otro nombre");
                System.out.println();
            }
            Player p2 = new Player(player_name, 1);
            System.out.println("Cada jugador iniciara la partida con 2 fichas.");

            System.out.println("Elige las normas de movimiento de fichas:");
            boolean vertical, horizontal, diagonal;
            vertical = horizontal = diagonal = false;
            String response = "";
            while(!response.equals("y") && !response.equals("n")){
                System.out.println("¿Quieres que se permitan movimientos verticales? (y/n)");
                response = input_user.next();
                if(!response.equals("y") && !response.equals("n")){
                    System.out.println("Introduce un valor valido");
                    System.out.println();
                }
            }
            if (response.contains("y")) vertical = true;
            response = "";

            while(!response.equals("y") && !response.equals("n")) {
                System.out.println("¿Quieres que se permitan movimientos horizontales? (y/n)");
                response = input_user.next();
                if(!response.equals("y") && !response.equals("n")){
                    System.out.println("Introduce un valor valido");
                    System.out.println();
                }
            }
            if (response.contains("y")) horizontal = true;
            response = "";

            while(!response.equals("y") && !response.equals("n")) {
                System.out.println("¿Quieres que se permitan movimientos diagonales? (y/n)");
                response = input_user.next();
                if(!response.equals("y") && !response.equals("n")){
                    System.out.println("Introduce un valor valido");
                    System.out.println();
                }
            }
            if (response.contains("y")) diagonal = true;

            String handicap = " ";
            while(!handicap.equals("0") && !handicap.equals("1") && !handicap.equals("2") && !handicap.equals("3")) {
                System.out.println("Elige si poner un handicap e indica cual");
                System.out.println("0. Sin handicap");
                System.out.println("1. Omite el turno del oponente");
                System.out.println("2. Recomendacion de movimientos");
                System.out.println("3. Omite el turno del oponente y recomendacion de movimientos");
                handicap = input_user.next();
                if (!handicap.equals("0") && !handicap.equals("1") && !handicap.equals("2") && !handicap.equals("3")) {
                    System.out.println("Introduce un valor valido");
                    System.out.println();
                }
            }

            String handicap_turn = "0";
            String handicap_moves = "0";
            if(handicap.equals("1") || handicap.equals("3")){
                while(!handicap_turn.equals("1") && !handicap_turn.equals("2") && !handicap_turn.equals("3")) {
                    System.out.println("Elige cuantas veces omitir el turno del oponente");
                    System.out.println("1. Omite el turno del oponente 1 vez");
                    System.out.println("2. Omite el turno del oponente 2 veces");
                    System.out.println("3. Omite el turno del oponente 3 veces");
                    handicap_turn = input_user.next();
                    if(!handicap_turn.equals("1") && !handicap_turn.equals("2") && !handicap_turn.equals("3")) {
                        System.out.println("Introduce un valor valido");
                        System.out.println();
                    }
                }
            }

            if(handicap.equals("2") || handicap.equals("3")){
                while (!handicap_moves.equals("1") && !handicap_moves.equals("2") && !handicap_moves.equals("3")) {
                    System.out.println("Elige la dificultad de la maquina que recomendara movimientos");
                    System.out.println("1. Facil");
                    System.out.println("2. Media");
                    System.out.println("3. Dificil");
                    handicap_moves = input_user.next();
                    if (!handicap_moves.equals("1") && !handicap_moves.equals("2") && !handicap_moves.equals("3")) {
                        System.out.println("Introduce un valor valido");
                        System.out.println();
                    }
                }
            }

            //Game & set_game_id && initialize_game
            Game test_game = new Game(test_board, p1, p2, vertical, horizontal, diagonal,false);
            //get_game_id
            String test_id = test_game.get_game_id();
            System.out.println("El id del juego es: " +test_id);
            //set_num_skips
            int aux_turn = 0;
            if(handicap_turn.equals("1")) aux_turn = 1;
            else if(handicap_turn.equals("2")) aux_turn = 2;
            else if(handicap_turn.equals("3")) aux_turn = 3;
            test_game.set_num_skips(aux_turn);
            //get_num_skips
            int skips = test_game.get_num_skips();
            System.out.println("Se puede omitir el turno del oponente " +skips+ " veces");
            //set_machine_diff
            int aux_diff = 0;
            if(handicap_moves.equals("1")) aux_diff = 1;
            else if(handicap_moves.equals("2")) aux_diff = 2;
            else if(handicap_moves.equals("3")) aux_diff = 3;
            test_game.set_machine_diff(aux_diff);
            //get_machine_diff
            int mahcine_diff = test_game.get_machine_diff();
            if(mahcine_diff > 0) System.out.println("La dificultad de la maquina que recomienda los movimientos es de " +mahcine_diff);
            else System.out.println("No habran movimientos recomendados");

            //get_rules
            boolean rules;
            rules = test_game.get_vert();
            if(rules) System.out.println("Se permiten movimientos verticales");
            rules = test_game.get_hrzn();
            if(rules) System.out.println("Se permiten movimientos horizontales");
            rules = test_game.get_diag();
            if(rules) System.out.println("Se permiten movimientos diagonales");

            System.out.println("Cada jugador iniciara la partida con 2 fichas.");
            System.out.println();
            //get_j1
            Player aux_player;
            aux_player = test_game.get_j1();
            //get_score
            System.out.println("El Jugador 1: " + aux_player.get_name() + " tiene " + test_game.get_score(aux_player.get_player_color()) + " puntos");
            //get_j2
            aux_player = test_game.get_j2();
            System.out.println("El Jugador 2: " + aux_player.get_name() + " tiene " + test_game.get_score(aux_player.get_player_color()) + " puntos");

            //get_turn
            boolean test_turn;
            test_turn =test_game.get_turn();
            if(!test_turn) System.out.println("Turno negras");
            else System.out.println("Turno blancas");
            Player actual_player, opponent_player;
            actual_player = test_game.get_j1();
            opponent_player = test_game.get_j2();
            //black_turn
            if(test_game.is_black_turn()) System.out.println("Comenzara el Jugador 1: " +test_game.get_j1().get_name());
            //white_turn
            if(test_game.is_white_turn()){
                System.out.println("Comenzara el Jugador 2: " +test_game.get_j2().get_name());
                actual_player = test_game.get_j2();
                opponent_player = test_game.get_j1();
            }

            //get_player_cells
            System.out.println("Las casillas actuales de Jugador " +actual_player.get_name()+ " son:");
            for(Cell test_cell : test_game.get_player_cells(actual_player.get_player_color())){
                System.out.println("["+test_cell.get_row()+", "+test_cell.get_column()+"]");
            }

            System.out.println("Los movimientos posibles del Jugador " +actual_player.get_name()+ " son:");
            HashMap<Cell, ArrayList<Integer>> available_moves = test_game.legal_moves(actual_player, opponent_player);
            for(Cell test_cell : available_moves.keySet()){
                System.out.println("["+test_cell.get_row()+", "+test_cell.get_column()+"]");
            }

            if(!available_moves.isEmpty()){
                //get_machine_diff & machine_help
                if(test_game.get_machine_diff() != 0) {
                    Cell recommended_cell = test_game.machine_help();
                    if(available_moves.containsKey(recommended_cell)) System.out.println("Movimiento recomendado por la maquina: [" +recommended_cell.get_row()+ ", " +recommended_cell.get_column()+ "]");
                }
            }

            print_board(test_game.get_game_board());
            int row = 0;
            int col = 0;
            //get_game_board
            Board test_get_board = test_game.get_game_board();
            Cell check_cell = test_get_board.get_cell(row, col);
            if(available_moves.isEmpty()) System.out.println("No hay movimientos disponibles");
            else {
                while(first || !available_moves.containsKey(check_cell) || (row >= test_game.get_game_board().get_size() || col >= test_game.get_game_board().get_size()) || (row < 0 || col < 0)){
                    System.out.println("Introduce la posicion de la ficha:");
                    row = input_user.nextInt();
                    col = input_user.nextInt();
                    if((row >= test_game.get_game_board().get_size() || col >= test_game.get_game_board().get_size()) || (row < 0 || col < 0)) System.out.println("Introduce una casilla dentro del rango del tablero");
                    else{
                        check_cell = test_get_board.get_cell(row, col);
                        if(first) first = false;
                        if(!available_moves.containsKey(check_cell)) System.out.println("Introduce una de las casillas validas especificadas en movimientos posibles del jugador");

                    }
                }
            }
            System.out.println();

            //set_cell & flip_cell & check_vertical & check_horizontal & check_diagonal
            if(available_moves.containsKey(check_cell)) test_game.set_cell(check_cell, actual_player);
            print_board(test_game.get_game_board());
            System.out.println("Las casillas actuales de Jugador actual " +actual_player.get_name()+ " son:");
            for(Cell test_cell : test_game.get_player_cells(actual_player.get_player_color())){
                System.out.println("["+test_cell.get_row()+", "+test_cell.get_column()+"]");
            }

            System.out.println("Las casillas actuales de Jugador oponente " +opponent_player.get_name()+ " son:");
            for(Cell test_cell : test_game.get_player_cells(opponent_player.get_player_color())){
                System.out.println("["+test_cell.get_row()+", "+test_cell.get_column()+"]");
            }

            boolean finished;
            //is_finished
            finished = test_game.is_finished();
            if(finished) System.out.println("El juego se ha acabado");
            else System.out.println("El juego no se ha acabado");
            System.out.println();

            //skip_opponent_turn
            if(test_game.get_num_skips() > 0){
                response = "";
                while(!response.equals("y") && !response.equals("n")) {
                    System.out.println("¿Quieres omitir el turno del oponente? (y/n)");
                    response = input_user.next();
                    if(!response.equals("y") && !response.equals("n")){
                        System.out.println("Introduce un valor valido");
                        System.out.println();
                    }
                }
                if(response.equals("y")){
                    test_game.skip_opponent_turn();
                    test_turn =test_game.get_turn();
                    System.out.print("Se va a cambiar el turno del jugador por tal de saltar su turno. ");
                    if(!test_turn) System.out.println("Turno negras");
                    else System.out.println("Turno blancas");
                }
                else if(response.equals("n")) System.out.println("No se cambiara el turno");
                skips = test_game.get_num_skips();
                System.out.println("Se puede omitir el turno del oponente " +skips+ " veces");

            }

            //change_turn
            test_game.change_turn();
            //get_turn
            test_turn =test_game.get_turn();
            if(!test_turn) System.out.println("Turno resultante: negras");
            else System.out.println("Turno resultante: blancas");
            //black_turn
            if(test_game.is_black_turn()) System.out.println("El siguiente turno sera del Jugador 1: " +test_game.get_j1().get_name());
            //white_turn
            if(test_game.is_white_turn()) System.out.println("El siguiente turno sera del Jugador 2: " +test_game.get_j2().get_name());

            response = "";
            while(!response.equals("y") && !response.equals("n")) {
                System.out.println("El juego de pruebas ha acabado, ¿desea iniciar otro nuevo? (y/n)");
                response = input_user.next();
                if(!response.equals("y") && !response.equals("n")){
                    System.out.println("Introduce un valor valido");
                    System.out.println();
                }
            }
            if(response.equals("n")) test = false;
            System.out.println();
        }
    }
}

