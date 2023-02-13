package Dominio;

import java.util.Scanner;
/**
 * Class used to test the MiniMax
 * @author Nico
 * @version 1.0, 23/4/2021
 * @since 1.8
 */
public class DriverMinimax {

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
        boolean exit=false;
        while(!exit) {
            Player p1 = new Player("test1",0);
            Player p2 = new Player("test2",1);
            int size=8;
            Board test_board = new Board(size);
            test_board.initialize_default_board();
            Game game=new Game(test_board,p1,p2,true,true,true,false);
            game.initialize_game();
            System.out.println("Esto es el driver de la clase Minimax");
            System.out.println("1 - Simular partida completa Máquina vs Máquina");
            System.out.println("2 - Indicar siguiente movimiento para la máquina");
            String option_s = input_user.next();
            while(!option_s.equals("1") && !option_s.equals("2")){
                System.out.println("Introduce un valor valido!");
                option_s=input_user.next();
            }
            int option=Integer.parseInt(option_s);
            int depth=-1;
            int depth2=-1;
            String input1;
            String input2;
            Minimax test_minimax;
            //Minimax test_minimax2;
            Cell move;
            if(option==1){
                System.out.println("Tablero inicial: ");
                print_board(test_board);
                System.out.println("ADVERTENCIA: Valores muy altos en la dificultad llevará a un largo tiempo de cálculo por el siguiente movimiento (valores válidos 1, 2 y 3)");

                System.out.println("Introduce la profundidad del jugador 1");
                input1=input_user.next();
                while(!input1.equals("1") && !input1.equals("2") && !input1.equals("3")){
                    System.out.println("Introduce un valor valido!");
                    input1=input_user.next();
                }
                depth=Integer.parseInt(input1);

                System.out.println("Introduce la profundidad del jugador 2");
                input2=input_user.next();
                while(!input2.equals("1") && !input2.equals("2") && !input2.equals("3")){
                    System.out.println("Introduce un valor valido!");
                    input2=input_user.next();
                }
                depth2=Integer.parseInt(input2);

                test_minimax=new Minimax(game, depth);
                //test_minimax2=new Minimax(game, depth);
                while(!game.is_finished()){
                    if(game.is_black_turn()){
                        move=test_minimax.decide_move(game,depth,game.get_turn());
                        Cell move_cell=game.get_game_board().get_cell(move.get_row(),move.get_column());
                        game.set_cell(move_cell,p1);
                        System.out.println("Movimiento negro: "+move.get_row()+" "+move.get_column());
                    }else if(game.is_white_turn()){
                        move=test_minimax.decide_move(game,depth2,game.get_turn());
                        Cell move_cell=game.get_game_board().get_cell(move.get_row(),move.get_column());
                        game.set_cell(move_cell,p2);
                        System.out.println("Movimiento blanco: "+move.get_row()+" "+move.get_column());
                    }
                    game.change_turn();
                    System.out.println("-----------------------------");
                    print_board(game.get_game_board());
                }
                System.out.println("Tablero finalizado: ");
                print_board(test_board);
                if(game.get_score(p1.get_player_color()) > game.get_score(p2.get_player_color())) System.out.println("Ha ganado la maquina 1");
                else if(game.get_score(p2.get_player_color()) > game.get_score(p1.get_player_color())) System.out.println("Ha ganado la maquina 2");
                else System.out.println("Se ha producido un empate entre las dos maquinas");
                System.out.println("La maquina 1 ha conseguido una puntuacion de "+game.get_score(p1.get_player_color())+" con una dificultad de "+depth);
                System.out.println("La maquina 2 ha conseguido una puntuacion de "+game.get_score(p2.get_player_color())+" con una dificultad de "+depth2);
            }else if(option==2){
                System.out.println("Tablero inicial: ");
                print_board(test_board);
                System.out.println("ADVERTENCIA: Valores muy altos en la dificultad llevará a un largo tiempo de cálculo por el siguiente movimiento");

                System.out.println("Introduce la profundidad del jugador 1");
                input1=input_user.next();
                while(!input1.equals("1") && !input1.equals("2") && !input1.equals("3")){
                    System.out.println("Introduce un valor valido!");
                    input1=input_user.next();
                }
                depth=Integer.parseInt(input1);

                System.out.println("Introduce la profundidad del jugador 2");
                input2=input_user.next();
                while(!input2.equals("1") && !input2.equals("2") && !input2.equals("3")){
                    System.out.println("Introduce un valor valido!");
                    input2=input_user.next();
                }
                depth2=Integer.parseInt(input2);

                test_minimax=new Minimax(game, depth);
                //test_minimax2=new Minimax(game, depth2);
                System.out.println("¿De que jugador quieres saber el próximo movimiento?(1/2)");
                int jugador=input_user.nextInt();
                if(jugador==1) move=test_minimax.decide_move(game,depth,game.get_turn());
                else{
                    game.change_turn();
                    move=test_minimax.decide_move(game,depth2,game.get_turn());
                }
                System.out.println("El siguiente movimiento que hará la máquina será a la casilla: ");
                System.out.println("Fila "+move.get_row()+" Columna "+move.get_column());
            }else{
                System.out.println("Selecciona una opción correcta por favor!");
            }
            if(option==1 || option==2){
                System.out.println("Juego de pruebas finalizado, ¿Deseas iniciar otro?(S/N)");
                String opcion=input_user.next();
                while(!opcion.equals("S") && !opcion.equals("N")){
                    System.out.println("Introduce un valor valido");
                    opcion=input_user.next();
                }
                if(opcion.equals("N")) exit=true;
            }

        }
    }
}
