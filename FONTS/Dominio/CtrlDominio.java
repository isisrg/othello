package Dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import Persistencia.*;

/**
 * Class used to represent the communication between layers.
 * @author Nico
 * @version 2.0, 14/4/2021
 * @since 1.8
 */
public final class CtrlDominio {
    /**
     * Class instance
     */
    private static CtrlDominio controlador = new CtrlDominio();
    private static CtrlGame ctrlGame = CtrlGame.get_ctrl_game();
    private static CtrlRanking ctrlRanking = CtrlRanking.get_instance();
    private static CtrlDatosPersistencia ctrlDatos = new CtrlDatosPersistencia();
    private static CtrlDatosRanking ctrlDRanking = new CtrlDatosRanking();
    private static CtrlDatosPartida ctrlDGame = new CtrlDatosPartida();

    private Game game;

    /**
     * Default constructor
     */
    public CtrlDominio(){}

    /**
     * Gets the singleton instance of the domain controller
     * @return controller instance
     */
    public static CtrlDominio get_instance() {
        return controlador;
    }


    //ctrlGame
    public boolean get_game_status(String game_id){
        return ctrlGame.get_game_status(game_id);
    }
    public Game get_game(String game_id){ return ctrlGame.get_game(game_id); }
    public int get_game_size(String game_id){ return ctrlGame.get_game_size(game_id); }
    public String add_new_game(Board game_board, Player p1, Player p2, boolean vertical, boolean horizontal, boolean diagonal, boolean turn){
        return ctrlGame.add_new_game(game_board, p1, p2,vertical,horizontal,diagonal,turn);
    }
    public int delete_game(String game_id){ return ctrlGame.delete_game(game_id); }
    public Player get_player(String game_id, int num_player){return ctrlGame.get_player(game_id, num_player); }
    public Player get_p1(String game_id){ return ctrlGame.get_p1(game_id); }
    public Player get_p2(String game_id){ return ctrlGame.get_p2(game_id); }
    public String get_name_player(String game_id, int num_player){ return ctrlGame.get_name_player(game_id, num_player); }
    public int get_score_player(String game_id, int num_player){ return ctrlGame.get_score_player(game_id, num_player); }
    public void set_score(String game_id, int score, int num_player){ ctrlGame.set_score(game_id,score,num_player); }
    public void set_players_name(String game_id, String name, int num_player){ ctrlGame.set_players_name(game_id,name,num_player); }
    public Board get_game_board(String game_id){ return ctrlGame.get_game_board(game_id); }
    public Vector<Boolean> get_game_rules(String game_id){ return ctrlGame.get_game_rules(game_id); }
    public int get_nump_machine(String game_id){ return ctrlGame.get_nump_machine(game_id); }
    public int get_machine_diff(String game_id){ return ctrlGame.get_machine_diff(game_id); }
    public ArrayList get_machines_diffs(String game_id){ return ctrlGame.get_machines_diffs(game_id); }
    public boolean is_black_turn(String game_id){ return ctrlGame.is_black_turn(game_id); }
    public boolean is_white_turn(String game_id){ return ctrlGame.is_white_turn(game_id); }
    public void change_turn(String game_id){ ctrlGame.change_turn(game_id); }
    public void set_cell(String game_id, int row, int col, Player actual_player){
        Cell aux=ctrlGame.get_game(game_id).get_game_board().get_cell(row,col);
        ctrlGame.set_cell(game_id,aux,actual_player);
    }
    public Cell get_cell(String game_id,int row, int col){ return ctrlGame.get_cell(game_id,row,col); }
    public HashMap<Cell, ArrayList<Integer>> legal_moves(String game_id, Player actual_player, Player opponent){
        return ctrlGame.legal_moves(game_id,actual_player,opponent);
    }
    public boolean is_finished(String game_id){ return ctrlGame.is_finished(game_id); }


    //ctrlRanking
    public static CtrlRanking get_instance_ranking(){ return ctrlRanking.get_instance(); }
    public static CtrlRanking get_ctrl_ranking(){ return ctrlRanking.get_ctrl_ranking(); }
    public void set_players_ranking(String game_id){ ctrlRanking.set_players_ranking(game_id); }
    public void delete_player_ranking(Player px){ ctrlRanking.delete_player_ranking(px); }
    public boolean exists_player(Player px){ return ctrlRanking.exists_player(px); }
    public HashMap<String, ArrayList<Integer>> get_stats(){
        return ctrlRanking.get_stats();
    }
    public void restart_stats(){ ctrlRanking.restart_stats(); }



    public String [] get_game_list(){ return ctrlDGame.get_game_list(); }
    public Board int_to_board(int[][] board_int, int size_board){
        Board aux= new Board(size_board);
        return aux.int_to_board(board_int,size_board);
    }
    public boolean is_white(String game_id,int row, int col){ return ctrlGame.get_game_board(game_id).get_cell(row,col).is_white(); }
    public boolean is_black(String game_id,int row, int col){ return ctrlGame.get_game_board(game_id).get_cell(row,col).is_black(); }
    public Cell next_move(String game_id, int depth){

        //System.out.println("movs disponibles");
        Game aux=ctrlGame.get_game(game_id);
        /*HashMap<Cell, ArrayList<Integer>> prueba1=aux.legal_moves(aux.get_j2(), aux.get_j1());
        for(Cell pruebapoio : prueba1.keySet()){

            System.out.println(key);
        }*/
        Minimax move=new Minimax(aux,depth);
        Cell next=move.decide_move(aux,depth,aux.get_turn());
        System.out.println(next.get_row()+" "+next.get_column());
        return next;
    }
    public int get_row(Cell aux){
        return aux.get_row();
    }
    public int get_col(Cell aux){
        return aux.get_column();
    }

}
