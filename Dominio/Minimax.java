package Dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class used to implement the algorithm the machine will be using known as MinMax
 * With an improvement of the efficiency with cutoffs and depth of the iterations
 * @author Nico
 * @version 1.0
 * @since 1.8
 */

public class Minimax {

    private Game current_game;
    private int depth;
    /**
     * Main function to choose the next move for the Machine Player
     * @param c_game current game playing
     * @param depth number of iterations of the algorithm
     */
    protected Minimax(Game c_game, int depth){
        this.current_game=c_game;
        this.depth=depth;
    }

    /**
     * Function to determinate which move would be the best for the player (MINimize IA move)
     * @param current current game playing
     * @param turn current turn
     * @param depth number of iterations of the algorithm
     * @return return the lowest value for a given level of recursion.
     */
    protected int min_evaluation(Game current, int depth, boolean turn, int alpha, int beta){
        if(depth == 0 || current.is_finished()){
            return evaluate_board(current,turn);
        }
        HashMap<Cell,ArrayList<Integer>> available_moves;
        if(turn) {
            available_moves = current.legal_moves(current.get_j2(), current.get_j1());
        }else{
            available_moves = current.legal_moves(current.get_j1(), current.get_j2());
        }
        int lowest=1000000;
        Iterator iterador_hm=available_moves.entrySet().iterator();
        while(iterador_hm.hasNext()){
            Map.Entry move=(Map.Entry)iterador_hm.next();
            Cell next_move= (Cell) move.getKey();
            Board clon= new Board();
            Player player_copia=new Player();
            Player player_copia2=new Player();
            player_copia.clonar(current.get_j1());
            player_copia2.clonar(current.get_j2());
            clon.clone(current.get_game_board());
            Game copia=new Game(clon, player_copia, player_copia2, current.get_vert(), current.get_hrzn(), current.get_diag(), current.get_turn());
            Cell next_move_set=copia.get_game_board().get_cell(next_move.get_row(),next_move.get_column());
            if(turn)copia.set_cell(next_move_set, player_copia);
            else copia.set_cell(next_move_set, player_copia2);

            int value=max_evaluation(copia,depth-1, !turn, alpha,beta);
            if(value<lowest)lowest=value;
            beta=Math.min(beta,value);
            if(beta<=alpha){
                return lowest; //Aquí básicamente lo que hacemos es la poda per sí.
            }
        }
        return lowest;
    }

    /**
     * Function to determinate which move would be the best for the IA (MAXimize IA move to gain more profit)
     * @param current current game playing
     * @param turn current turn
     * @param depth number of iterations of the algorithm
     * @return Returns the max value for a given leve of recursion.
     */
//
    protected int max_evaluation(Game current, int depth, boolean turn, int alpha, int beta){
        if(depth == 0 || current.is_finished()){
            return evaluate_board(current, turn);
        }
        HashMap<Cell,ArrayList<Integer>> available_moves;
        if(turn) {
            available_moves = current.legal_moves(current.get_j2(), current.get_j1());
        }else{
            available_moves = current.legal_moves(current.get_j1(), current.get_j2());
        }
        int highest=-1000000;
        Iterator iterador_hm=available_moves.entrySet().iterator();
        while(iterador_hm.hasNext()){
            Map.Entry move=(Map.Entry)iterador_hm.next();
            Cell next_move= (Cell) move.getKey();
            Board clon= new Board();
            Player player_copia=new Player();
            Player player_copia2=new Player();
            player_copia.clonar(current.get_j1());
            player_copia2.clonar(current.get_j2());
            clon.clone(current.get_game_board());
            Game copia=new Game(clon, player_copia, player_copia2, current.get_vert(), current.get_hrzn(), current.get_diag(), current.get_turn());
            Cell next_move_set=copia.get_game_board().get_cell(next_move.get_row(),next_move.get_column());
            if(turn)copia.set_cell(next_move_set, player_copia2);
            else copia.set_cell(next_move_set, player_copia);

            int value=min_evaluation(copia,depth-1, !turn, alpha, beta);
            if(value>highest)highest=value;
            alpha=Math.max(alpha,value);
            if(beta<=alpha){
                return highest;
            }
        }
        return highest;
    }

    /**
     * Function to determinate which move is the best for the IA.
     * @param current current game playing
     * @param turn current turn
     * @param depth number of iterations of the algorithm
     * @return returns best move for the machine.
     */
    protected Cell decide_move(Game current, int depth, boolean turn){
        int value=-1000000;

        Cell best_move=new Cell();
        HashMap<Cell, ArrayList<Integer>> available_moves;
        if(turn) {
            available_moves = current.legal_moves(current.get_j2(), current.get_j1());
        }else{
            available_moves = current.legal_moves(current.get_j1(), current.get_j2());
        }
        int cvalue;
        Iterator iterador_hm=available_moves.entrySet().iterator();
        while(iterador_hm.hasNext()){

            Map.Entry move=(Map.Entry)iterador_hm.next();
            Cell next_move= (Cell) move.getKey();
            next_move=current.get_game_board().get_cell(next_move.get_row(),next_move.get_column());
            Board clon= new Board();
            Player player_copia=new Player();
            Player player_copia2=new Player();
            player_copia.clonar(current.get_j1());
            player_copia2.clonar(current.get_j2());
            clon.clone(current.get_game_board());
            Game copia=new Game(clon, player_copia, player_copia2, current.get_vert(), current.get_hrzn(), current.get_diag(), current.get_turn());
            Cell next_move_set=copia.get_game_board().get_cell(next_move.get_row(),next_move.get_column());
            if(turn)copia.set_cell(next_move_set, player_copia2);
            else copia.set_cell(next_move_set, player_copia);

            cvalue=min_evaluation(copia,depth-1, !turn, -10000,10000);
            if(cvalue>value){
                value=cvalue;
                best_move= current.get_game_board().get_cell(next_move.get_row(),next_move.get_column());
            }
        }
        return best_move;
    }

    /**
     * Given a board in a exact situation, we give the heuristic for that board.
     * @param evaluate the board that we have to evaluate.
     * @param turn current turn
     * @return returns the score for a given board.
     */
    protected int evaluate_board(Game evaluate,  boolean turn){

        int black_pieces=evaluate.get_player_cells(0).size();
        int white_pieces=evaluate.get_player_cells(1).size();
        int res;
        int corner_bonus=evaluate.get_game_board().get_size()*5;
        int pre_corner_bonus=-200;
        HashMap<Cell, ArrayList<Integer>> black_moves= evaluate.legal_moves(evaluate.get_j1(), evaluate.get_j2());
        HashMap<Cell, ArrayList<Integer>> white_moves= evaluate.legal_moves(evaluate.get_j2(), evaluate.get_j1());
        int mobility;
        if(turn) mobility=100*((white_moves.size()-black_moves.size())/(white_moves.size()+black_moves.size()+1));
        else mobility=100*((black_moves.size()-white_moves.size())/(black_moves.size()+white_moves.size()+1));
        if((evaluate.get_game_board().get_cell(0,0).is_black())
                || evaluate.get_game_board().get_cell(this.current_game.get_game_board().get_size()-1,0).is_black()
                || evaluate.get_game_board().get_cell(0,this.current_game.get_game_board().get_size()-1).is_black()
                || evaluate.get_game_board().get_cell(this.current_game.get_game_board().get_size()-1,this.current_game.get_game_board().get_size()-1).is_black()){
            black_pieces+=corner_bonus;
        }

        if((evaluate.get_game_board().get_cell(0,0).is_white())
                || evaluate.get_game_board().get_cell(this.current_game.get_game_board().get_size()-1,0).is_white()
                || evaluate.get_game_board().get_cell(0,this.current_game.get_game_board().get_size()-1).is_white()
                || evaluate.get_game_board().get_cell(this.current_game.get_game_board().get_size()-1,this.current_game.get_game_board().get_size()-1).is_white()){
            white_pieces+=corner_bonus;
        }

        if((evaluate.get_game_board().get_cell(evaluate.get_game_board().get_size()-2,evaluate.get_game_board().get_size()-2).is_white())
                || evaluate.get_game_board().get_cell(1,1).is_white()
                || evaluate.get_game_board().get_cell(evaluate.get_game_board().get_size()-2,1).is_white()
                || evaluate.get_game_board().get_cell(1,evaluate.get_game_board().get_size()-2).is_white()){
            white_pieces+=pre_corner_bonus;
        }

        if((evaluate.get_game_board().get_cell(evaluate.get_game_board().get_size()-2,evaluate.get_game_board().get_size()-2).is_black())
                || evaluate.get_game_board().get_cell(1,1).is_black()
                || evaluate.get_game_board().get_cell(evaluate.get_game_board().get_size()-2,1).is_black()
                || evaluate.get_game_board().get_cell(1,evaluate.get_game_board().get_size()-2).is_black()){
            black_pieces+=pre_corner_bonus;
        }

        if (turn) res=2*mobility+white_pieces-black_pieces;
        else res=2*mobility+black_pieces-white_pieces;
        //res=white_pieces-black_pieces;
        //System.out.println(res);
        return res;
    }
}