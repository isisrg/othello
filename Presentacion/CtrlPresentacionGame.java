package Presentacion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Dominio.*;
import Dominio.Game;

public class CtrlPresentacionGame {
    private CtrlDominio ctrlGame1 = CtrlDominio.get_instance();
    private String game_id;


    //private CtrlPresentacionGame instance=new CtrlPresentacionGame();
    public CtrlPresentacionGame(){}

    /*public CtrlPresentacionGame getInstance(){
        return instance;
    }*/
    
    /**
     * Constructor with parameters to create a controller for an already existing Game
     * @param game_board Board to be player
     * @param p1 First player
     * @param p2 Second player
     * @param vertical Whether vertical moves are allowed or not
     * @param horizontal Whether horizontal moves are allowed or not
     * @param diagonal Whether diagonal moves are allowed or not
     * @param turn Whose turn it is for the next move, either p1 or p2
     */
    public CtrlPresentacionGame(Board game_board, Player p1, Player p2, boolean vertical, boolean horizontal, boolean diagonal, boolean turn){
        String game_id = ctrlGame1.add_new_game(game_board, p1, p2, vertical, horizontal, diagonal, turn);
        Game game_test = ctrlGame1.get_game(game_id);
        this.game_id=game_id;
    }

    public String get_game_id(){ return this.game_id; }

    public String[] get_game_list() {
        return ctrlGame1.get_game_list();
    }

    /**
     * Function used to change an integer matrix into an instance of Board
     * @param board Board in matrix form
     * @param size_board Size of columns and rows of the board
     * @return The board instance
     */
    public Board int_to_board(int[][] board, int size_board){  return ctrlGame1.int_to_board(board,size_board); }
    public int get_game_size(String game_id){
        return ctrlGame1.get_game_size(game_id);
    }

    /**
     * Returns wether a token in the game is white or not
     * @param game_id Game to check the player in
     * @param row Row of the token
     * @param col Column of the token
     * @return boolean condition of the token
     */
    public boolean is_white(String game_id,int row, int col){
        return ctrlGame1.is_white(game_id,row,col);
    }

    /**
     * Returns wether a token in the game is white or not
     * @param game_id Game to check the player in
     * @param row Row of the token
     * @param col Column of the token
     * @return boolean condition of the token
     */
    public boolean is_black(String game_id,int row, int col){
        return ctrlGame1.is_black(game_id,row,col);
    }

    /**
     * Returns whether is black's turn or not
     * @return Returns true if is white's turn
     */
    public boolean is_black_turn(){
        return ctrlGame1.is_black_turn(this.get_game_id());
    }

    /**
     * Returns whether is white's turn or not
     * @return Returns true if is white's turn
     */
    public boolean is_white_turn(){
        return ctrlGame1.is_white_turn(this.get_game_id());
    }


    /**
     * Changes the turn from one Player to the other
     */
    public void change_turn(){
        ctrlGame1.change_turn(this.get_game_id());
    }


    /**
     * Places a token in a Cell in a Game for a given Player
     * @param game_id Game being played
     * @param row Row for the token to be put
     * @param col Column for the token to be put
     * @param actual_player specifies which player is placing the token in the cell
     */
    public void set_cell(String game_id, int row, int col, Player actual_player){
        ctrlGame1.set_cell(game_id,row,col,actual_player);
    }

    /**
     * Returns all the legal moves for the Player in the Game
     * @param game_id Game being played
     * @param actual_player Player attempting the next move
     * @param opponent Next player to move
     * @return Array with all the legal moves
     */

    public HashMap<Cell, ArrayList<Integer>> legal_moves(String game_id, Player actual_player, Player opponent){
        return ctrlGame1.legal_moves(game_id,actual_player,opponent);
    }

    public Player get_p1(String game_id){ return ctrlGame1.get_p1(game_id); }
    public Player get_p2(String game_id){ return ctrlGame1.get_p2(game_id); }

    public Cell get_cell(String game_id,int row, int col){
        return ctrlGame1.get_cell(game_id,row,col);
    }

    public Cell next_move(String game_id, int depth){
        return ctrlGame1.next_move(game_id,depth);
    }

    public int get_row (Cell aux){
        return ctrlGame1.get_row(aux);
    }

    public int get_col (Cell aux){
        return ctrlGame1.get_col(aux);
    }

    public boolean is_finished(String game_id){
        return ctrlGame1.is_finished(game_id);
    }

    public void set_players_name(String game_id, String name, int num_player){
        ctrlGame1.set_players_name(game_id,name,num_player);
    }

    public void set_players_ranking(String game_id){ ctrlGame1.set_players_ranking(game_id); }

    public HashMap<String, ArrayList<Integer>> get_stats(){
        return ctrlGame1.get_stats();
    }

    public void set_score(String game_id, int score, int num_player) {
        ctrlGame1.set_score(game_id,score,num_player);
    }
    public int get_score_player(String game_id, int num_player){ return ctrlGame1.get_score_player(game_id, num_player); }
    public void restart_stats() { ctrlGame1.restart_stats(); }
}
