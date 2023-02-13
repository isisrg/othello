package Dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * Class used to test all the functions in Class Game
 * @author Aidan
 * @version 1.0 27/04/2021
 * @since 1.8
 */

public class DriverRanking {

    public static void main(String[] ars) {
        Scanner input_user = new Scanner(System.in);
        System.out.println("Este es el driver para la clase Ranking: ");


        Player p1 = new Player("sara", 0);
        p1.set_final_score(20);
        Player p2 = new Player("isis", 0);
        p2.set_final_score(200);
        Player p3 = new Player("nico", 0);
        p3.set_final_score(220);

        //Ranking
        Ranking test_ranking = new Ranking();
        int difficulty = 0;
        test_ranking.set_score_player(p1, difficulty);
        ArrayList<Integer> score_player = new ArrayList<Integer>();
        score_player = test_ranking.get_player_stats(p1);
        test_ranking.set_score_player(p2, difficulty);
        test_ranking.set_score_player(p3, difficulty);

        difficulty = 1;
        p1.set_final_score(150);
        p2.set_final_score(100);
        p3.set_final_score(80);
        test_ranking.set_score_player(p1, difficulty);
        test_ranking.set_score_player(p2, difficulty);
        test_ranking.set_score_player(p3, difficulty);

        difficulty = 2;
        p1.set_final_score(100);
        p2.set_final_score(50);
        p3.set_final_score(45);
        test_ranking.set_score_player(p1, difficulty);
        test_ranking.set_score_player(p2, difficulty);
        test_ranking.set_score_player(p3, difficulty);

        difficulty = 2;
        p1.set_final_score(300);
        p2.set_final_score(250);
        p3.set_final_score(260);
        test_ranking.set_score_player(p1, difficulty);
        test_ranking.set_score_player(p2, difficulty);
        test_ranking.set_score_player(p3, difficulty);

        difficulty = 3;
        p1.set_final_score(100);
        p2.set_final_score(200);
        p3.set_final_score(1);
        test_ranking.set_score_player(p1, difficulty);
        test_ranking.set_score_player(p2, difficulty);
        test_ranking.set_score_player(p3, difficulty);



        String select_activity = "3";
        while(!(select_activity.equals("0")) && !(select_activity.equals("1")) && !(select_activity.equals("2"))){
            System.out.println("Elige que accion quieres realizar: ");
            System.out.println("0. Visualizar Ranking");
            System.out.println("1. Consultar jugador en el ranking");
            System.out.println("2. Eliminar Jugador");
            select_activity = input_user.next();
            if(!select_activity.equals("0")  && !select_activity.equals("1")  && !select_activity.equals("2")){
                System.out.println("Introduce un valor valido");
                System.out.println();
            }
        }

        if(select_activity.equals("0")){
            //ArrayList<Integer> score_player = new ArrayList<Integer>();
            score_player = test_ranking.get_player_stats(p1);
            System.out.println();
            System.out.println("RANKING");
            System.out.println("-------------------------------");
            System.out.print(p1.get_name()+ "  ");
            for(int i = 0; i < score_player.size(); i++){
                System.out.print(score_player.get(i));
                System.out.print("  ");
            }
            System.out.println();

            System.out.print(p2.get_name()+ "  ");
            score_player = test_ranking.get_player_stats(p2);
            for(int i = 0; i < score_player.size(); i++){
                System.out.print(score_player.get(i));
                System.out.print("  ");
            }
            System.out.println();

            System.out.print(p3.get_name()+ "  ");
            score_player = test_ranking.get_player_stats(p3);
            for(int i = 0; i < score_player.size(); i++){
                System.out.print(score_player.get(i));
                System.out.print("  ");
            }
            System.out.println();

            //sort_ranking
            boolean sort = false;
            String response = "";
            while(!response.equals("y") && !response.equals("n")) {
                System.out.println("¿Quieres ordenar el ranking? (y/n)");
                response = input_user.next();
                if(!response.equals("y") && !response.equals("n")){
                    System.out.println("Introduce un valor valido");
                    System.out.println();
                }
            }
            if (response.contains("y")) sort = true;
            response = "";
            if(sort){
                String select_diff = "8";
                while(!select_diff.equals("0")  && !select_diff.equals("1")  && !select_diff.equals("2")  && !select_diff.equals("3")
                        && !select_diff.equals("4")  && !select_diff.equals("5")  && !select_diff.equals("6")  && !select_diff.equals("7")){
                    System.out.println("Elige por que dificultad ordenarlo:");
                    System.out.println("0. Facil");
                    System.out.println("1. Normal");
                    System.out.println("2. Dificil");
                    System.out.println("3. Contra jugadores");
                    System.out.println("4. Promedio Facil");
                    System.out.println("5. Promedio Normal");
                    System.out.println("6. Promedio Dificil");
                    System.out.println("7. Promedio Contra jugadores");
                    select_diff = input_user.next();
                    if(!select_diff.equals("0")  && !select_diff.equals("1")  && !select_diff.equals("2")  && !select_diff.equals("3")
                            && !select_diff.equals("4")  && !select_diff.equals("5")  && !select_diff.equals("6")  && !select_diff.equals("7")){
                        System.out.println("Introduce un valor valido");
                        System.out.println();
                    }else{
                        List<Entry<String, Integer>> list_scores = test_ranking.sort_ranking(Integer.parseInt(select_diff));
                        System.out.println();
                        System.out.println("RANKING");
                        System.out.println("-------------------------------");
                        for(Entry<String, Integer> entry: list_scores) System.out.println(entry.getKey()+ "  " + entry.getValue());
                        System.out.println();
                    }
                }
            }
        }
        else if (select_activity.equals("1")) {
            String condition_exit = "";
            boolean continuar = true;
            while(continuar){
                System.out.println("Consulta si un jugador esta en el ranking poniendo su nombre: ");
                String test_player = input_user.next();
                Player paux = new Player(test_player, 0);
                //exists_player
                if(test_ranking.exists_player(paux)) System.out.println("El jugador " +paux.get_name()+ " esta en el ranking");
                else System.out.println("El jugador " +paux.get_name()+ " no esta en el ranking");


                String response = "";
                while(!response.equals("y") && !response.equals("n")) {
                    System.out.println("¿Quieres seguir consultando el ranking? (y/n)");
                    response = input_user.next();
                    if(!response.equals("y") && !response.equals("n")){
                        System.out.println("Introduce un valor valido");
                        System.out.println();
                    }
                }
                if (response.contains("y")) continuar = true;
                else if(response.contains("n"))continuar = false;
                response = "";
            }
        }
        else if(select_activity.equals("2")){
            boolean continuar = true;
            while(continuar){
                System.out.println("Elimina a un jugador del ranking poniendo su nombre: ");
                String test_player = input_user.next();
                Player paux = new Player(test_player, 0);
                //remove
                if(test_ranking.exists_player(paux)){
                    test_ranking.remove_player(paux);
                    System.out.println("El jugador " +paux.get_name()+ " ha sido eliminado");
                }
                else{
                    System.out.println("El jugador " +paux.get_name()+ " no esta en el ranking");
                    System.out.println("Prueba con otro jugador");
                }

                String response = "";
                while(!response.equals("y") && !response.equals("n")) {
                    System.out.println("¿Quieres seguir eliminando players del ranking? (y/n)");
                    response = input_user.next();
                    if(!response.equals("y") && !response.equals("n")){
                        System.out.println("Introduce un valor valido");
                        System.out.println();
                    }
                }
                if (response.contains("y")) continuar = true;
                else if(response.contains("n"))continuar = false;
                response = "";
            }

        }
    }
}

