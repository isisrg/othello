package Dominio;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class used to represent the communication between Domain layer and the Ranking class
 * @author Aidan
 * @version 1.0, 23/4/2021
 * @since 1.8
 */
public final class CtrlRanking {
    /**
     * Class instance
     */
    private static CtrlRanking controller = new CtrlRanking();
    private static CtrlDominio controlador = new CtrlDominio();
    Ranking ranking = new Ranking();


    /**
     * Default constructor
     */
    public CtrlRanking(){
        //controller = new CtrlRanking();
    }
    public static CtrlRanking get_instance() {
        return controller;
    }

    /**
     * Gets the singleton instance of the ranking controller
     * @return controller instance
     */
    public static CtrlRanking get_ctrl_ranking() { return controller;}

    /**
     * Introduces the players into the ranking
     * @param game_id The identifier of the game to set the players into the ranking
     */
    public void set_players_ranking(String game_id) {
        int nump_machine = controlador.get_nump_machine(game_id);
        if (nump_machine == 0) {
            ranking.set_score_player(controlador.get_p1(game_id), 3);
            ranking.set_score_player(controlador.get_p2(game_id), 3);
        } else if (nump_machine == 1) {
            int diff = controlador.get_machine_diff(game_id);
            ranking.set_score_player(controlador.get_p2(game_id), diff);
        } else if (nump_machine == 2) {
            int diff = controlador.get_machine_diff(game_id);
            ranking.set_score_player(controlador.get_p1(game_id), diff);
        }
    }

    /**
     * Removes Player px in the ranking
     * @param px Player to be removed in the ranking
     */
    public void delete_player_ranking(Player px){
        ranking.remove_player(px);
    }

    /**
     * Looks for a Player px in the ranking
     * @param px Player to be looked for in the ranking
     * @return Returns a boolean that shows if the Player px is in the ranking
     */
    protected boolean exists_player(Player px){
        return ranking.exists_player(px);
    }

    /**
     * Returns the Ranking
     * @return HashMap containing the Ranking
     */
    public HashMap<String, ArrayList<Integer>> get_stats(){
        return ranking.get_stats();
    }

    /**
     * Empties the Ranking
     */
    protected void restart_stats() {
        ranking.restart_stats();
    }
}
