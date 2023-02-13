package Dominio;

import Persistencia.CtrlDatosPartida;

import Presentacion.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Class used to ......................
 * @author Isis
 * @version 2.0, 27/04/2021
 * @since 1.8
 */
public final class CtrlGame {
    /**
     * Class instance
     */
    private static CtrlGame controller = new CtrlGame();

    private CtrlDatosPartida ctrlGame2 = new CtrlDatosPartida();

    /**
     * Default constructor
     */
    public CtrlGame(){

    }

    /**
     * Gets the singleton instance of the domain controller
     * @return controller instance
     */
    public static CtrlGame get_ctrl_game() { return controller;}

    /**
     * new_game is the instance of Game used at the moment
     */
    private Game new_game;

    /**
     * j1 and j2 are the instances of Player used at this game
     */
    private Player j1, j2;

    private TreeMap<String, Game> games=new TreeMap<>();

    /**
     * Tells whether a game with the specified game id exists in the TreeMap or not
     * @param game_id id of a game
     * @return true if the game exists in the TreeMap, false otherwise
     */
    public boolean get_game_status(String game_id){
        return games.containsKey(game_id);
    }

    /**
     * Returns a Game given a game_id
     * @param game_id id of a game
     * @return Game if the Game exists
     */
    public Game get_game(String game_id){ return games.get(game_id); }

    public int get_game_size(String game_id){
        return games.get(game_id).get_game_board().get_size();
    }

    /**
     * Creates a new game given its parameters and inserts it into the TreeMap
     * @param game_board Board that this Game will be using
     * @param p1 Player that will be playing as the player 1
     * @param p2 Player that will be playing as the player 2
     * @param vertical boolean that indicates whether vertical moves are allowed or not
     * @param horizontal boolean that indicates whether horizontal moves are allowed or not
     * @param diagonal boolean that indicates whether diagonal moves are allowed or not
     * @param turn boolean that indicates which turn is it, being true white's turn, and false black's turn
     * @return a Sting with the game id associated with the game that has been inserted into the TreeMap
     */
    public String add_new_game(Board game_board, Player p1, Player p2, boolean vertical, boolean horizontal, boolean diagonal, boolean turn){
        Game new_game = new Game(game_board, p1, p2, vertical, horizontal, diagonal, turn);
        String game_id = new_game.get_game_id();
        games.put(game_id, new_game);
        return game_id;
    }

    /**
     * Deletes a game from the TreeMap given a game id
     * @param game_id id of the game the user wants to remove
     * @return an int specifying whether the game has been removed (0) or not (1)
     */
    public int delete_game(String game_id){
        if(!get_game_status(game_id)) return 1;
        else games.remove(game_id);
        return 0;
    }

    /**
     * Gets a player given a game id and the number of the desired player
     * @param game_id id of the game the user wants to get p1 from
     * @param num_player number of the desired player
     * @return if num_player is either 1 or 2 returns the desired Player in a specified game, it returns Player 1 otherwise
     */
    public Player get_player(String game_id, int num_player){
        if(num_player == 1) return games.get(game_id).get_j1();
        else if(num_player == 2) return games.get(game_id).get_j2();
        else return games.get(game_id).get_j1();
    }

    /**
     * Gets Player 1 given a game id
     * @param game_id id of the game the user wants to get p1 from
     * @return Player 1 in a specified game
     */
    public Player get_p1(String game_id){ return games.get(game_id).get_j1(); }

    /**
     * Gets Player 2 given a game id
     * @param game_id id of the game the user wants to get p2 from
     * @return Player 2 in a specified game
     */
    public Player get_p2(String game_id){ return games.get(game_id).get_j2(); }

    /**
     * Gets a player's name given a game id and the number of the player the user wants to get the name from
     * @param game_id id of the game the user wants to get player's name from
     * @param num_player number of the player the user wants to get player's name from
     * @return String with player's name in a specified game, returns an empty String if the game does not exist
     */
    public String get_name_player(String game_id, int num_player){
        String player_name = "";
        if(!get_game_status(game_id)) return player_name;
        else {
            if(num_player == 1) return games.get(game_id).get_j1().get_name();
            else if(num_player == 2) return games.get(game_id).get_j2().get_name();
            else return player_name;
        }
    }

    /**
     * Gets a player's game score given a game id and the number of the player the user wants to get the score from
     * @param game_id id of the game the user wants to get player's score from
     * @param num_player number of the player the user wants to get player's score from
     * @return int with player's score in a specified game, returns 0 if the game does not exist
     */
    public int get_score_player(String game_id, int num_player){
        if(!get_game_status(game_id)) return 0;
        else {
            if(num_player == 1) return games.get(game_id).get_score(j1.get_player_color());
            else if (num_player == 2) return games.get(game_id).get_score(j2.get_player_color());
            else return 0;
        }
    }

    /**
     * Sets a player's game score given a game id and the number of the player the user wants to set the score to
     * @param game_id id of the game the user wants to get player's score from
     * @param score score to set to a given player in a given game
     * @param num_player number of the player the user wants to get player's score from
     */
    protected void set_score(String game_id, int score, int num_player) {
        games.get(game_id).set_score(score,num_player);
    }

    /**
     * Sets a player's name given a game id and the number of the player the user wants to set the name
     * @param game_id id of the game the user wants to set player's name
     * @param player_name name of the player
     * @param num_player number of the player the user wants to set player's name
     */
    protected void set_players_name(String game_id, String player_name, int num_player){
        if(num_player==1 || num_player==2) games.get(game_id).set_players_name(player_name, num_player);
    }

    /**
     * Gets the board of a game given the game id
     * @param game_id id of the game the user wants to get the board from
     * @return board of an existing game, if the game_id is not in the TreeMap returns an empty board
     */
    public Board get_game_board(String game_id){
        Board board_game = new Board();
        if(!get_game_status(game_id)) return board_game;
        else return games.get(game_id).get_game_board();
    }

    /**
     * Gets the rules set in a game
     * @param game_id id of the game the user wants to get the rules from
     * @return vector of booleans in which each position specifies whether the rule is set, specifying vertical in the
     * first position, horizontal in the second position, and diagonal in the third position of the vector
     */
    public Vector<Boolean> get_game_rules(String game_id){
        Vector<Boolean> rules = new Vector();
        if(!get_game_status(game_id)) return rules;
        else {
            rules.add(games.get(game_id).get_vert());
            rules.add(games.get(game_id).get_hrzn());
            rules.add(games.get(game_id).get_diag());
            return rules;
        }
    }

    /**
     * Gets which number of player is the machine in the game in case of having one player or two being a machine
     * @param game_id id of the game from which the user wants to get the number of player the machine has from
     * @return int indicating which player is the machine, -1 means the game specified does not exist, 0 means there
     * is no machine in this game, 1 means player 1 is a machine, 2 means player 2 is a machine, 3 means player 1 and
     * player 2 are both machines
     */
    public int get_nump_machine(String game_id){
        if(!get_game_status(game_id)) return -1;
        else{
            if(games.get(game_id).get_j1() instanceof Machine && !(games.get(game_id).get_j2() instanceof Machine)) return 1;
            else if(games.get(game_id).get_j2() instanceof Machine && !(games.get(game_id).get_j1() instanceof Machine)) return 2;
            else if(games.get(game_id).get_j1() instanceof Machine && games.get(game_id).get_j2() instanceof Machine) return 3;
            return 0;
        }
    }

    /**
     * Gets the machine difficulty in case of having one machine as a player
     * @param game_id id of the game from which the user wants to get the difficulty of the machine
     * @return -1 if the instance of the game does not exist, returns 0 if there is no machine as a player
     * in this game, returns {1, 2, 3} if there is a machine and its difficulty is easy (1), medium (2), or difficult (3)
     */
    public int get_machine_diff(String game_id){
        if(!get_game_status(game_id)) return -1;
        else {
            if(games.get(game_id).get_j1() instanceof Machine) return ((Machine) games.get(game_id).get_j1()).get_depth();
            else if(games.get(game_id).get_j2() instanceof Machine) return ((Machine) games.get(game_id).get_j2()).get_depth();
        }
        return 0;
    }

    /**
     * Gets the machines difficulties in an ArrayList in case of having two machines as players
     * @param game_id id of the game from which the user wants to get the difficulty of the machines
     * @return an Arraylist with -1 if the instance of the game does not exist, returns an Arraylist of 0 if there is no
     * machine as a player in this game, return an Arraylist with {1, 2, 3} in the first and second position indicating
     * in each position de difficulty of the machines in the game being easy (1), medium (2), or difficult (3)
     */
    public ArrayList get_machines_diffs(String game_id){
        ArrayList machines_diffs= new ArrayList<Integer>();
        machines_diffs.add(-1);
        machines_diffs.add(-1);
        if(!get_game_status(game_id)) return machines_diffs;
        else {
            if(!(games.get(game_id).get_j1() instanceof Machine) || !(games.get(game_id).get_j2() instanceof Machine)){
                machines_diffs.add(0, 0);
                machines_diffs.add(1, 0);
                return machines_diffs;
            }
            else{
                machines_diffs.add(0, ((Machine) games.get(game_id).get_j1()).get_depth());
                machines_diffs.add(1, ((Machine) games.get(game_id).get_j2()).get_depth());
            }
        }
        return machines_diffs;
    }

    /**
     * Returns whether is black's turn or not
     * @return Returns true if is black's turn
     */
    protected boolean is_black_turn(String game_id){
        if(games.get(game_id).is_black_turn()) return true;
        else return false;
    }

    /**
     * Returns whether is white's turn or not
     * @return Returns true if is white's turn
     */
    protected boolean is_white_turn(String game_id){
        if(games.get(game_id).is_white_turn()) return true;
        else return false;
    }

    /**
     * Changes player's turn
     */
    protected void change_turn(String game_id){
        games.get(game_id).change_turn();
    }

    /**
     * Places a token in a given cell
     * @param game_id id of the game the Player wants to set a token
     * @param chosen_cell Cell the Player wants to set as theirs
     * @param actual_player Player who wants to put a cell
     */
    protected void set_cell(String game_id, Cell chosen_cell, Player actual_player){
        games.get(game_id).set_cell(chosen_cell,actual_player);
    }

    /**
     * Gets a cell from a Game's board
     * @param game_id id from the game the user wants to get a cell from
     * @param row row of the desired cell
     * @param col column of the desired cell
     * @return Cell of a Game's board
     */
    protected Cell get_cell(String game_id, int row, int col){
        return games.get(game_id).get_game_board().get_cell(row,col);
    }

    /**
     * Returns all the legal moves for the Player in the Game
     * @param game_id id of the game the Player wants to check legal moves from
     * @param actual_player Player checking its legal moves
     * @param opponent Player which is the opponent to the actual player
     * @return array of all the legal moves
     */
    protected HashMap<Cell, ArrayList<Integer>> legal_moves(String game_id, Player actual_player, Player opponent){
        return games.get(game_id).legal_moves(actual_player,opponent);
    }

    /**
     * Tells whether a Game is finished or not
     * @param game_id id of a Game
     * @return
     */
    protected boolean is_finished(String game_id){
        return games.get(game_id).is_finished();
    }




}
