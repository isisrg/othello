package Dominio;

import java.util.*;

/**
 * Class used to make a test game (pvp, pvm, mvm)
 * @author Isis
 * @version 1.0
 * @since 1.8
 */

public class TestGame {
    static void print_board(Board b){
        for(int i=0;i<b.get_size();i++){
            for(int j=0;j< b.get_size();j++){
                System.out.print(b.get_cell(i,j).get_color());
            }
            System.out.println();
        }
    }
    public static void main(String[] ars) {

        Scanner input_user = new Scanner(System.in);
        boolean play = true;
        while(play){
            String mode = "0";
            String machine_player = "0";
            String machine_diff = "0";
            String machine_diff2 = "0";

            while(!mode.equals("1") && !mode.equals("2") && !mode.equals("3")){
                System.out.println("Elige el modo de juego:");
                System.out.println("1. Jugador vs Jugador");
                System.out.println("2. Jugador vs Maquina");
                System.out.println("3. Maquina vs Maquina");
                mode = input_user.next();
                if(!mode.equals("1") && !mode.equals("2") && !mode.equals("3")){
                    System.out.println("Introduce un valor valido");
                    System.out.println();
                }
            }

            System.out.println("Elige el tamaño del tablero:");
            int size = input_user.nextInt();

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
            response = "";

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

            System.out.println("La partida ha comenzado");
            System.out.println("-----------------------");

            //Jugador vs Jugador
            if(mode.equals("1")){
                Board test_board = new Board(size);
                test_board.initialize_default_board();

                String player_name = " ";
                while(player_name.equals(" ") || player_name.length() > 20){
                    System.out.println("Introduce el nombre del Jugador 1:");
                    player_name = input_user.next();
                    if(player_name.equals(" ")) System.out.println("Este no es un nombre valido, intentalo de nuevo");
                    if(player_name.length() > 20) System.out.println("El nombre del jugador supera los 20 caracteres, intentalo de nuevo");
                }
                Player p1 = new Player(player_name, 0);
                System.out.println();

                player_name = " ";
                while(player_name.equals(" ") || player_name.length() > 20 || player_name.equals(p1.get_name())){
                    System.out.println("Introduce el nombre del Jugador 2:");
                    player_name = input_user.next();
                    if(player_name.equals(" ")) System.out.println("Este no es un nombre valido, intentalo de nuevo");
                    if(player_name.length() > 20) System.out.println("El nombre del jugador supera los 20 caracteres, intentalo de nuevo");
                    if(player_name.equals(p1.get_name())) System.out.println("Este jugador ya existe, por favor introduce otro nombre");
                }
                Player p2 = new Player(player_name, 1);
                System.out.println();
                System.out.println("Cada jugador iniciara la partida con 2 fichas.");

                Game test_game = new Game(test_board, p1, p2, vertical, horizontal, diagonal,false);
                int aux_turn = 0;
                if(handicap_turn.equals("1")) aux_turn = 1;
                else if(handicap_turn.equals("2")) aux_turn = 2;
                else if(handicap_turn.equals("3")) aux_turn = 3;
                test_game.set_num_skips(aux_turn);
                int aux_moves = 0;
                if(handicap_moves.equals("1")) aux_moves = 1;
                else if(handicap_moves.equals("2")) aux_moves = 2;
                else if(handicap_moves.equals("3")) aux_moves = 3;
                test_game.set_machine_diff(aux_moves);

                System.out.println("El Jugador 1: " + p1.get_name() + " tiene " + test_game.get_score(p1.get_player_color()) + " puntos");
                System.out.println("El Jugador 2: " + p2.get_name() + " tiene " + test_game.get_score(p2.get_player_color()) + " puntos");

                System.out.println("Tablero inicial:");
                print_board(test_game.get_game_board());

                Player actual_player = p1;
                Player opponent_player = p2;

                while(!test_game.is_finished()) {
                    if (!test_game.get_turn()) {
                        actual_player = p1;
                        opponent_player = p2;
                    } else {
                        actual_player = p2;
                        opponent_player = p1;
                    }

                    System.out.println("Es el turno del Jugador " + actual_player.get_name());
                    System.out.println("Las casillas actuales del Jugador " + actual_player.get_name() + " son:");
                    for (Cell prueba : test_game.get_player_cells(actual_player.get_player_color())) {
                        System.out.println("[" + prueba.get_row() + ", " + prueba.get_column() + "]");
                    }

                    HashMap<Cell, ArrayList<Integer>> available_moves = test_game.legal_moves(actual_player, opponent_player);
                    if(available_moves.isEmpty()) System.out.println("El Jugador " +actual_player.get_name()+ " no tiene movimientos posibles");
                    else{
                        System.out.println("Los movimientos posibles del Jugador " + actual_player.get_name() + " son:");
                        for (Cell prueba : available_moves.keySet()) {
                            System.out.println("[" + prueba.get_row() + ", " + prueba.get_column() + "]");
                        }
                    }

                    if(actual_player.is_player_black() && (handicap.equals("1") || handicap.equals("3"))) System.out.println("El turno del oponente se puede saltar " +test_game.get_num_skips()+ " veces");

                    if(!available_moves.isEmpty() && actual_player.is_player_black() && (handicap.equals("2") || handicap.equals("3"))){
                        if(test_game.get_machine_diff() != 0) {
                            Cell recommended_cell = test_game.machine_help();
                            if(available_moves.containsKey(recommended_cell)) System.out.println("Movimiento recomendado por la maquina: [" +recommended_cell.get_row()+ ", " +recommended_cell.get_column()+ "]");
                        }
                    }

                    print_board(test_game.get_game_board());

                    if(available_moves.isEmpty()) System.out.println("No hay movimientos posibles");
                    else{
                        int row = 0;
                        int col = 0;
                        Board test_get_board = test_game.get_game_board();
                        Cell check_cell = test_get_board.get_cell(row, col);
                        boolean first = true;

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

                        if(available_moves.containsKey(check_cell)) test_game.set_cell(check_cell, actual_player);
                    }
                    if(test_game.get_num_skips() > 0 && actual_player.is_player_black()){
                        response = "";
                        while(!response.equals("y") && !response.equals("n")) {
                            System.out.println("¿Quieres omitir el turno del oponente? (y/n)");
                            response = input_user.next();
                            if(!response.equals("y") && !response.equals("n")){
                                System.out.println("Introduce un valor valido");
                                System.out.println();
                            }
                        }
                        if(response.equals("y")) test_game.skip_opponent_turn();
                    }

                    test_game.change_turn();
                    System.out.println("-------------------------------------------------------------------------");
                }

                System.out.println("El juego se ha acabado");
                System.out.println("Tablero final:");
                print_board(test_game.get_game_board());
                int winner = test_game.return_winner();
                if (winner == 0) {
                    System.out.println("El ganador del juego ha sido " + p1.get_name() + " con una puntuacion de " + test_game.get_player_cells(p1.get_player_color()).size());
                    System.out.println("El jugador " + p2.get_name() + " ha conseguido puntuación de " + test_game.get_player_cells(p2.get_player_color()).size());
                } else if (winner == 1) {
                    System.out.println("El ganador del juego ha sido " + p2.get_name() + " con una puntuación de " + test_game.get_player_cells(p2.get_player_color()).size());
                    System.out.println("El jugador " + p1.get_name() + " ha conseguido una puntuacion de " + test_game.get_player_cells(p1.get_player_color()).size());
                } else {
                    System.out.println("El jugador " + p1.get_name() + " y el jugador " + p2.get_name() + " han empatado con una puntuacion de " + test_game.get_player_cells(p1.get_player_color()).size());
                }
            }

            //Juagdor vs Maquina
            else if(mode.equals("2")) {

                while (!machine_diff.equals("1") && !machine_diff.equals("2") && !machine_diff.equals("3")) {
                    System.out.println("Elige la dificultad de la máquina:");
                    if(!handicap_moves.equals("1")) System.out.println("1. Facil");
                    if(!handicap_moves.equals("2")) System.out.println("2. Medio");
                    if(!handicap_moves.equals("3")) System.out.println("3. Dificil");
                    machine_diff = input_user.next();
                    if (!machine_diff.equals("1") && !machine_diff.equals("2") && !machine_diff.equals("3")) {
                        System.out.println("Introduce un valor valido");
                        System.out.println();
                    }
                }

                Player p1 = new Player("player1", 0);
                Player p2 = new Player("player2", 1);

                while (!machine_player.equals("1") && !machine_player.equals("2")) {
                    System.out.println("Elige que jugador ocupara la maquina:");
                    System.out.println("1. Jugador 1");
                    System.out.println("2. Jugador 2");
                    machine_player = input_user.next();
                    String player_name = " ";
                    if (machine_player.equals("1")) {
                        while(player_name.equals(" ") || player_name.length() > 20 || player_name.equals("Maquina")){
                            System.out.println("Introduce el nombre del Jugador 2:");
                            player_name = input_user.next();
                            if(player_name.equals(" ")) System.out.println("Este no es un nombre valido, intentalo de nuevo");
                            if(player_name.length() > 20) System.out.println("El nombre del jugador supera los 20 caracteres, intentalo de nuevo");
                            if(player_name.equals("Maquina")) System.out.println("El nombre elegido es el nombre de la maquina, por favor introduce otro nombre");
                        }
                        p2 = new Player(player_name, 1);
                        p1 = new Player("Maquina", 0);
                    } else if (machine_player.equals("2")) {
                        while(player_name.equals(" ") || player_name.length() > 20 || player_name.equals("Maquina")){
                            System.out.println("Introduce el nombre del Jugador 1:");
                            player_name = input_user.next();
                            if(player_name.equals(" ")) System.out.println("Este no es un nombre valido, intentalo de nuevo");
                            if(player_name.length() > 20) System.out.println("El nombre del jugador supera los 20 caracteres, intentalo de nuevo");
                            if(player_name.equals("Maquina")) System.out.println("El nombre elegido es el nombre de la maquina, por favor introduce otro nombre");
                        }
                        p1 = new Player(player_name, 0);
                        p2 = new Player("Maquina",  1);
                    } else {
                        System.out.println("Introduce un valor valido");
                        System.out.println();
                    }
                }

                Board test_board = new Board(size);
                test_board.initialize_default_board();

                System.out.println();
                System.out.println("Cada jugador iniciara la partida con 2 fichas.");

                Game test_game = new Game(test_board, p1, p2, vertical, horizontal, diagonal, false);
                int aux_turn = 0;
                if(handicap_turn.equals("1")) aux_turn = 1;
                else if(handicap_turn.equals("2")) aux_turn = 2;
                else if(handicap_turn.equals("3")) aux_turn = 3;
                test_game.set_num_skips(aux_turn);
                int aux_moves = 0;
                if(handicap_moves.equals("1")) aux_moves = 1;
                else if(handicap_moves.equals("2")) aux_moves = 2;
                else if(handicap_moves.equals("3")) aux_moves = 3;
                test_game.set_machine_diff(aux_moves);
                int aux_diff = 0;
                if(machine_diff.equals("1")) aux_diff = 1;
                else if(machine_diff.equals("2")) aux_diff = 2;
                else if(machine_diff.equals("3")) aux_diff = 3;
                Minimax machine = new Minimax(test_game, aux_diff);

                System.out.println("El Jugador 1: " + p1.get_name() + " tiene " + test_game.get_score(p1.get_player_color()) + " puntos");
                System.out.println("El Jugador 2: " + p2.get_name() + " tiene " + test_game.get_score(p2.get_player_color()) + " puntos");

                System.out.println("Tablero inicial:");
                print_board(test_game.get_game_board());

                Player actual_player = p1;
                Player opponent_player = p2;

                while (!test_game.is_finished()) {
                    if (!test_game.get_turn()) {
                        actual_player = p1;
                        opponent_player = p2;
                    } else {
                        actual_player = p2;
                        opponent_player = p1;
                    }
                    System.out.println("Es el turno del Jugador " + actual_player.get_name());
                    System.out.println("Las casillas actuales del Jugador " + actual_player.get_name() + " son:");
                    for (Cell prueba : test_game.get_player_cells(actual_player.get_player_color())) {
                        System.out.println("[" + prueba.get_row() + ", " + prueba.get_column() + "]");
                    }

                    HashMap<Cell, ArrayList<Integer>> available_moves = test_game.legal_moves(actual_player, opponent_player);
                    if(available_moves.isEmpty()) System.out.println("El Jugador " +actual_player.get_name()+ " no tiene movimientos posibles");
                    else{
                        System.out.println("Los movimientos posibles del Jugador " + actual_player.get_name() + " son:");
                        for (Cell prueba : available_moves.keySet()) {
                            System.out.println("[" + prueba.get_row() + ", " + prueba.get_column() + "]");
                        }
                    }

                    if(actual_player.get_name() != "Maquina" && (handicap.equals("1") || handicap.equals("3"))) System.out.println("El turno del oponente se puede saltar " +test_game.get_num_skips()+ " veces");

                    if(!available_moves.isEmpty() && actual_player.get_name() != "Maquina" && (handicap.equals("2") || handicap.equals("3"))){
                        if(test_game.get_machine_diff() != 0) {
                            Cell recommended_cell = test_game.machine_help();
                            if(available_moves.containsKey(recommended_cell)) System.out.println("Movimiento recomendado por la maquina: [" +recommended_cell.get_row()+ ", " +recommended_cell.get_column()+ "]");
                        }
                    }

                    print_board(test_game.get_game_board());

                    if (actual_player.get_name() == "Maquina") {
                        if(available_moves.isEmpty()) System.out.println("No hay movimientos posibles");
                        else{
                            Cell move = machine.decide_move(test_game, aux_diff, test_game.get_turn());
                            Cell move_cell = test_game.get_game_board().get_cell(move.get_row(), move.get_column());
                            if(test_game.is_black_turn()) test_game.set_cell(move_cell, p1);
                            else if(test_game.is_white_turn()) test_game.set_cell(move_cell, p2);
                            System.out.println("Posicion de la ficha: [" + move.get_row() + ", " + move.get_column() + "]");
                        }

                    } else {
                        if(available_moves.isEmpty()) System.out.println("No hay movimientos posibles");
                        else {
                            int row = 0;
                            int col = 0;
                            Board test_get_board = test_game.get_game_board();
                            Cell check_cell = test_get_board.get_cell(row, col);
                            boolean first = true;

                            while (first || !available_moves.containsKey(check_cell) || (row >= test_game.get_game_board().get_size() || col >= test_game.get_game_board().get_size()) || (row < 0 || col < 0)) {
                                System.out.println("Introduce la posicion de la ficha:");
                                row = input_user.nextInt();
                                col = input_user.nextInt();
                                if ((row >= test_game.get_game_board().get_size() || col >= test_game.get_game_board().get_size()) || (row < 0 || col < 0)) System.out.println("Introduce una casilla dentro del rango del tablero");
                                else {
                                    check_cell = test_get_board.get_cell(row, col);
                                    if (first) first = false;
                                    if (!available_moves.containsKey(check_cell)) System.out.println("Introduce una de las casillas validas especificadas en movimientos posibles del jugador");
                                }
                            }

                            if (available_moves.containsKey(check_cell)) test_game.set_cell(check_cell, actual_player);
                        }
                    }

                    if(test_game.get_num_skips() > 0 && actual_player.get_name() != "Maquina"){
                        response = "";
                        while(!response.equals("y") && !response.equals("n")) {
                            System.out.println("¿Quieres omitir el turno del oponente? (y/n)");
                            response = input_user.next();
                            if(!response.equals("y") && !response.equals("n")){
                                System.out.println("Introduce un valor valido");
                                System.out.println();
                            }
                        }
                        if(response.equals("y")) test_game.skip_opponent_turn();
                    }

                    test_game.change_turn();
                    System.out.println("-------------------------------------------------------------------------");
                }

                System.out.println("El juego se ha acabado");
                System.out.println("Tablero final:");
                print_board(test_game.get_game_board());
                int winner = test_game.return_winner();
                if (winner == 0) {
                    if (p1.get_name().equals("Maquina"))
                        System.out.println("El ganador del juego ha sido " + p1.get_name() + " con una puntuacion de " + test_game.get_player_cells(p1.get_player_color()).size() + " con una dificultad de " + machine_diff);
                    else
                        System.out.println("El ganador del juego ha sido " + p1.get_name() + " con una puntuacion de " + test_game.get_player_cells(p1.get_player_color()).size());

                    if (p2.get_name().equals("Maquina"))
                        System.out.println("El jugador " + p2.get_name() + " ha conseguido puntuación de " + test_game.get_player_cells(p2.get_player_color()).size() + " con una dificultad de " + machine_diff);
                    else System.out.println("El jugador " + p2.get_name() + " ha conseguido puntuación de " + test_game.get_player_cells(p2.get_player_color()).size());

                } else if (winner == 1) {
                    if (p2.get_name().equals("Maquina"))
                        System.out.println("El ganador del juego ha sido " + p2.get_name() + " con una puntuacion de " + test_game.get_player_cells(p2.get_player_color()).size() + " con una dificultad de " + machine_diff);
                    else
                        System.out.println("El ganador del juego ha sido " + p2.get_name() + " con una puntuacion de " + test_game.get_player_cells(p2.get_player_color()).size());

                    if (p1.get_name().equals("Maquina"))
                        System.out.println("El jugador " + p1.get_name() + " ha conseguido puntuación de " + test_game.get_player_cells(p1.get_player_color()).size() + " con una dificultad de " + machine_diff);
                    else
                        System.out.println("El jugador " + p1.get_name() + " ha conseguido puntuación de " + test_game.get_player_cells(p1.get_player_color()).size());
                } else {
                    System.out.println("El jugador " + p1.get_name() + " y el jugador " + p2.get_name() + " han empatado con una puntuacion de " + test_game.get_player_cells(p1.get_player_color()).size());
                    if (p1.get_name().equals("Maquina"))
                        System.out.println("El jugador " + p1.get_name() + " tenia una dificultad de " + machine_diff);
                    else if (p2.get_name().equals("Maquina"))
                        System.out.println("El jugador " + p2.get_name() + " tenia una dificultad de " + machine_diff);
                }
            }

            //Maquina vs Maquina
            else if(mode.equals("3")) {

                System.out.println("Si escoges una dificultad para una maquina ya no estara disponible para la otra");

                while (!machine_diff.equals("1") && !machine_diff.equals("2") && !machine_diff.equals("3")) {
                    System.out.println("Elige la dificultad de la máquina 1:");
                    System.out.println("1. Facil");
                    System.out.println("2. Medio");
                    System.out.println("3. Dificil");
                    machine_diff = input_user.next();
                    if (!machine_diff.equals("1") && !machine_diff.equals("2") && !machine_diff.equals("3")) {
                        System.out.println("Introduce un valor valido");
                        System.out.println();
                    }
                }

                while(!machine_diff2.equals("1") && !machine_diff2.equals("2") && !machine_diff2.equals("3")) {
                    System.out.println("Elige la dificultad de la máquina 2:");
                    if(!machine_diff.equals("1")) System.out.println("1. Facil");
                    if(!machine_diff.equals("2")) System.out.println("2. Medio");
                    if(!machine_diff.equals("3")) System.out.println("3. Dificil");
                    machine_diff2 = input_user.next();
                    if(!machine_diff2.equals("1") && !machine_diff2.equals("2") && !machine_diff2.equals("3")) {
                        System.out.println("Introduce un valor valido");
                        System.out.println();
                    }
                }

                Player p1 = new Player("Maquina 1", 0);
                Player p2 = new Player("Maquina 2", 1);

                Board test_board = new Board(size);
                test_board.initialize_default_board();

                System.out.println("Cada jugador iniciara la partida con 2 fichas.");

                Game test_game = new Game(test_board, p1, p2, vertical, horizontal, diagonal, false);

                int aux_diff1 = 0;
                if(machine_diff.equals("1")) aux_diff1 = 1;
                else if(machine_diff.equals("2")) aux_diff1 = 2;
                else if(machine_diff.equals("3")) aux_diff1 = 3;
                Minimax machine = new Minimax(test_game, aux_diff1);
                int aux_diff2 = 0;
                if(machine_diff.equals("1")) aux_diff2 = 1;
                else if(machine_diff.equals("2")) aux_diff2 = 2;
                else if(machine_diff.equals("3")) aux_diff2 = 3;
                Minimax machine2 = new Minimax(test_game, aux_diff2);

                System.out.println("El Jugador 1: " + p1.get_name() + " tiene " + test_game.get_score(p1.get_player_color()) + " puntos");
                System.out.println("El Jugador 2:  " + p2.get_name() + " tiene " + test_game.get_score(p2.get_player_color()) + " puntos");

                System.out.println("Tablero inicial:");
                print_board(test_game.get_game_board());

                Player actual_player = p1;
                Player opponent_player = p2;

                while (!test_game.is_finished()) {
                    if (!test_game.get_turn()) {
                        actual_player = p1;
                        opponent_player = p2;
                    } else {
                        actual_player = p2;
                        opponent_player = p1;
                    }
                    System.out.println("Es el turno del Jugador " + actual_player.get_name());
                    System.out.println("Las casillas actuales del Jugador " + actual_player.get_name() + " son:");
                    for (Cell prueba : test_game.get_player_cells(actual_player.get_player_color())) {
                        System.out.println("[" + prueba.get_row() + ", " + prueba.get_column() + "]");
                    }

                    HashMap<Cell, ArrayList<Integer>> available_moves = test_game.legal_moves(actual_player, opponent_player);
                    if(available_moves.isEmpty()) System.out.println("El Jugador " +actual_player.get_name()+ " no tiene movimientos posibles");
                    else{
                        System.out.println("Los movimientos posibles del Jugador " + actual_player.get_name() + " son:");
                        for (Cell prueba : available_moves.keySet()) {
                            System.out.println("[" + prueba.get_row() + ", " + prueba.get_column() + "]");
                        }
                    }

                    print_board(test_game.get_game_board());

                    if(available_moves.isEmpty()) System.out.println("No hay movimientos posibles");
                    else{
                        Cell move = new Cell();
                        if(actual_player.get_name().equals("Maquina 1")) move = machine.decide_move(test_game, aux_diff1, test_game.get_turn());
                        else move = machine2.decide_move(test_game, aux_diff2, test_game.get_turn());
                        Cell move_cell = test_game.get_game_board().get_cell(move.get_row(), move.get_column());
                        if(test_game.is_black_turn()) test_game.set_cell(move_cell, p1);
                        else if(test_game.is_white_turn()) test_game.set_cell(move_cell, p2);
                        System.out.println("Posicion de la ficha: [" + move.get_row() + ", " + move.get_column() + "]");
                    }

                    test_game.change_turn();
                    System.out.println("-------------------------------------------------------------------------");
                }

                System.out.println("El juego se ha acabado");
                System.out.println("Tablero final:");
                print_board(test_game.get_game_board());
                int winner = test_game.return_winner();
                if (winner == 0) {
                    System.out.println("El ganador del juego ha sido " + p1.get_name() + " con una puntuacion de " + test_game.get_score(p1.get_player_color()) + " con una dificultad de " + machine_diff);
                    System.out.println("El jugador " + p2.get_name() + " ha conseguido puntuación de " + test_game.get_score(p2.get_player_color()) + " con una dificultad de " + machine_diff2);

                } else if (winner == 1) {
                    System.out.println("El ganador del juego ha sido " + p2.get_name() + " con una puntuacion de " + test_game.get_score(p2.get_player_color()) + " con una dificultad de " + machine_diff2);
                    System.out.println("El jugador " + p1.get_name() + " ha conseguido puntuación de " + test_game.get_score(p1.get_player_color()) + " con una dificultad de " + machine_diff);

                } else {
                    System.out.println("El jugador " + p1.get_name() + " y el jugador " + p2.get_name() + " han empatado con una puntuacion de " + test_game.get_score(p1.get_player_color()));
                    System.out.println("El jugador " + p1.get_name() + " tenia una dificultad de " + machine_diff);
                    System.out.println("El jugador " + p2.get_name() + " tenia una dificultad de " + machine_diff2);
                }
            }

            response = "";
            while(!response.equals("y") && !response.equals("n")) {
                System.out.println("¿Quieres jugar otra vez? (y/n)");
                response = input_user.next();
                if(!response.equals("y") && !response.equals("n")){
                    System.out.println("Introduce un valor valido");
                    System.out.println();
                }
            }
            if(response.equals("n")) play = false;
        }
    }
}
