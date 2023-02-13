package Dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class used to represent a Game
 * @author Isis
 * @version 1.0
 * @since 1.8
 */

public class Game {

    private Board game_board;
    private Player j1, j2;
    private boolean vert, hrzn, diag;
    private boolean turn;
    private String game_id;
    private ArrayList<Cell> player_cells_p1;
    private ArrayList<Cell> player_cells_p2;
    private int score_p1, score_p2;
    int num_skips, machine_diff;

    /**
     * Creates a Game with a Board, two Players, a number of Cells for each player and the option to enable rules
     * @param game_board board for the Game
     * @param player1 first Player with all the information of color
     * @param player2 second Player with all the information of color
     * @param vertical check if the rules allow vertical moves
     * @param horizontal check if the rules allow horizontal moves
     * @param diagonal check if the rules allow diagonal moves
     */
    public Game(Board game_board, Player player1, Player player2, boolean vertical, boolean horizontal, boolean diagonal, boolean t) {
        set_board(game_board);
        set_players(player1, player2);
        rules(vertical, horizontal, diagonal);
        this.turn = false;      //black's turn
        set_game_id();
        this.player_cells_p1 = new ArrayList<>();
        this.player_cells_p2 = new ArrayList<>();
        this.initialize_game();
        score_p1 = 0;
        score_p2 = 0;
        num_skips = 0;
        machine_diff = 0;
    }


    protected void set_players_name(String name, int num_player){
        if(num_player==1)this.j1.set_name(name);
        else if(num_player==2)this.j2.set_name(name);
    }

    /**
     * Sets an id for the game
     */
    protected void set_game_id(){
        LocalDateTime id = LocalDateTime.now();
        int day, month, year, hour, minute, second;
        day = id.getDayOfMonth();
        month = id.getMonthValue();
        year = id.getYear();
        hour = id.getHour();
        minute = id.getMinute();
        second = id.getSecond();
        this.game_id = String.valueOf(day) + String.valueOf(month) + String.valueOf(year) + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
    }

    /**
     * @return Returns the game id
     */
    protected String get_game_id(){ return this.game_id; }

    /**
     * @return Returns the player 1
     */
    protected Player get_j1(){
        return this.j1;
    }

    /**
     * @return Returns the player 2
     */
    protected Player get_j2(){
        return this.j2;
    }

    /**
     * Sets a Cell with the Colour of the Player
     * @param new_cell Cell assigned to the Player
     * @param num_player identifies the player in order to set the cell in its vector
     */
    protected void set_player_cells(Cell new_cell, int num_player){
        if(num_player == 0) this.player_cells_p1.add(new_cell);
        else this.player_cells_p2.add(new_cell);
    }

    /**
     * Removes a Cell with the Colour of the Player
     * @param remove_cell Cell removed from Player's Cells
     * @param num_player identifies the player in order to delete the cell in its vector
     */
    protected void remove_from_player_cells(Cell remove_cell, int num_player){
        if(num_player == 0) this.player_cells_p1.remove(remove_cell);
        else this.player_cells_p2.remove(remove_cell);
    }

    /**
     * Gets all the Cells assigned to the Player
     * @param num_player identifies the player in order to get its vector
     * @return array of Cells assigned to the Player
     */
    protected ArrayList<Cell> get_player_cells(int num_player){
        if(num_player == 0) return this.player_cells_p1;
        else return this.player_cells_p2;
    }

    /**
     * Gets the score of the Player in the Game
     * @param num_player number of the Player to set score
     * @return score of the player
     */
    protected int get_score(int num_player) {
        if(num_player == 0) return this.score_p1 = this.player_cells_p1.size();
        else return this.score_p2 = this.player_cells_p2.size();
    }

    /**
     * Sets the score to a chosen number in the Game
     * @param score score to be set for the Player
     * @param num_player number of the Player to set score
     */
    protected void set_score(int score, int num_player) {
        if(num_player == 1) this.score_p1 = score;
        else if(num_player == 2) this.score_p2 = score;
    }

    /**
     * Returns if vertical rules are enabled
     * @return Returns true if vertical rules are enabled, returns false otherwise
     */
    protected boolean get_vert(){ return this.vert; }

    /**
     * Returns if horizontal rules are enabled
     * @return Returns true if horizontal rules are enabled, returns false otherwise
     */
    protected boolean get_hrzn(){
        return this.hrzn;
    }

    /**
     * Returns if diagonal rules are enabled
     * @return Returns true if diagonal rules are enabled, returns false otherwise
     */
    protected boolean get_diag(){
        return this.diag;
    }

    /**
     * Returns the board of the current game
     * @return Returns the board of the current game
     */
    protected Board get_game_board(){
        return this.game_board;
    }

    /**
     * @return Returns the turn of the current game
     */
    protected boolean get_turn(){
        return this.turn;
    }

    /**
     * Returns whether is black's turn or not
     * @return Returns true if is black's turn
     */
    protected boolean is_black_turn(){
        if(!this.turn) return true;
        else return false;
    }

    /**
     * Returns whether is white's turn or not
     * @return Returns true if is white's turn
     */
    protected boolean is_white_turn(){
        if(this.turn) return true;
        else return false;
    }

    /**
     * Sets how many times the player can skips its opponent's turn in this game
     * @skips times the player can skips its opponent's turn in this game
     */
    protected void set_num_skips(int skips){ this.num_skips = skips; }

    /**
     * Gets how many times the player can skips its opponent's turn in this game
     * @return times the player can skips its opponent's turn in this game
     */
    protected int get_num_skips(){ return this.num_skips; }

    /**
     * Changes the turn so when the player's turn is finished the game will change the turn again
     * so the opponent's turn will be skipped and decreases the number of times the player can skip
     * its opponent's turn
     */
    protected void skip_opponent_turn(){
        if(this.num_skips > 0){
            --this.num_skips;
            change_turn();
        }
    }

    /**
     * Sets the difficulty of the machine which is going to help the player
     * @diff difficulty of the machine helping the player
     */
    protected void set_machine_diff(int diff){ this.machine_diff = diff; }

    /**
     * Gets the difficulty of the machine which is going to help the player
     * @return difficulty of the machine helping the player
     */
    protected int get_machine_diff(){ return this.machine_diff; }

    /**
     * Creates a machine with a difficulty that recommends a Player the best move
     * @return best move that the Player can make based on the difficulty of the machine
     */
    protected Cell machine_help(){
        Minimax help_machine = new Minimax(this, this.machine_diff);
        Cell recommended_cell = help_machine.decide_move(this, this.machine_diff, this.turn);
        recommended_cell = this.game_board.get_cell(recommended_cell.get_row(), recommended_cell.get_column());
        return recommended_cell;
    }



    /**
     * Sets the Board Game with the parameters
     * @param board board with the size and established Cells
     */
    protected void set_board(Board board) {
        this.game_board = board;
    }

    /**
     * Changes the Player's turns in the current game
     */
    protected void change_turn(){
        if(this.turn) this.turn = false;
        else this.turn = true;
    }

    /**
     * Sets the Players of the Game with the parameters
     * @param player1 first Player of the Game
     * @param player2 second Player of the Game
     */
    protected void set_players(Player player1, Player player2) {
        this.j1 = player1;
        this.j2 = player2;
    }

    /**
     * Sets the rules of the available moves in the Game
     * @param vertical checks if vertical moves are allowed
     * @param horizontal checks if horizontal moves are allowed
     * @param diagonal checks if diagonal moves are allowed
     */
    protected void rules(boolean vertical, boolean horizontal, boolean diagonal) {
        this.vert = vertical;
        this.hrzn = horizontal;
        this.diag = diagonal;
    }

    /**
     * Sets the initial state of the board game and assigns the initial cells to the correspondent player
     */
    protected void initialize_game(){
        int size = this.game_board.get_size()/2;

        //Initial cells of Player 1
        this.set_player_cells(new Cell(0, size-1, size), j1.get_player_color());
        this.set_player_cells(new Cell(0, size, size-1), j1.get_player_color());

        //Initial cells of Player 2
        this.set_player_cells(new Cell(1, size-1, size-1), j2.get_player_color());
        this.set_player_cells(new Cell(1, size, size), j2.get_player_color());

    }

    /**
     * @return Returns the winner of the game, 0 = Player 1 won, 1 = Player 2 won, 2 = tie
     */
    protected int return_winner(){
        if(player_cells_p1.size() > player_cells_p2.size()) return 0;
        else if(player_cells_p1.size() < player_cells_p2.size()) return 1;
        else return 2;
    }

    /**
     * Gets the horizontal valid moves for a Player in a Game
     * @param starter_cell first Cell for the move
     * @param actual_player Player attempting the move
     * @param horizontal HashMap to store the possible moves
     * @param opponent Opponent player
     * @param flip indicates weather we have intention to flip the rival's cells or not
     * @param direction indicates which side we need to look in order to find one of the Player's cell
     * @param mine_found when flipping, indicates weather we found one of the Player's Cell or not
     */
    protected void check_horizontal(Cell starter_cell, Player actual_player, Player opponent, HashMap<Cell, ArrayList<Integer>> horizontal, boolean flip, Integer direction, boolean mine_found) {
        boolean right_found = false;
        boolean left_found = false;

        Cell aux_cell;
        int move_right = starter_cell.get_column() + 1;
        int move_left = starter_cell.get_column() - 1;

        if (move_right < this.game_board.get_size()) {
            aux_cell = this.game_board.get_cell(starter_cell.get_row(), move_right);
            if (!aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                move_right++;
                if(flip && direction == 1){
                    remove_from_player_cells(aux_cell, opponent.get_player_color());
                    this.set_player_cells(aux_cell, actual_player.get_player_color());
                    game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                }
                while (move_right < this.game_board.get_size() && !right_found) {
                    aux_cell = this.game_board.get_cell(starter_cell.get_row(), move_right);
                    if (aux_cell.is_empty()) {
                        right_found = true;
                        horizontal.putIfAbsent(aux_cell, new ArrayList<>());
                        horizontal.get(aux_cell).add(0);
                    } else{
                        if(aux_cell.get_color() == actual_player.get_player_color()){
                            move_right++;
                            if(move_right < this.game_board.get_size()) {
                                aux_cell = this.game_board.get_cell(move_right, starter_cell.get_column());
                                if (aux_cell.is_empty()) right_found = true;
                                else {
                                    move_right--;
                                    aux_cell = this.game_board.get_cell(move_right, starter_cell.get_column());
                                }
                            }
                        }
                        move_right++;
                        if(flip && direction == 1){
                            if(aux_cell.get_color() == actual_player.get_player_color()) mine_found = true;
                            if(!mine_found && !aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                                remove_from_player_cells(aux_cell, opponent.get_player_color());
                                this.set_player_cells(aux_cell, actual_player.get_player_color());
                                game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                            }
                        }
                    }
                }
            }
        }

        if (move_left >= 0) {
            aux_cell = this.game_board.get_cell(starter_cell.get_row(), move_left);
            if (!aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                move_left--;
                if(flip && direction == 0){
                    remove_from_player_cells(aux_cell, opponent.get_player_color());
                    this.set_player_cells(aux_cell, actual_player.get_player_color());
                    game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                }
                while (move_left >= 0 && !left_found) {
                    aux_cell = this.game_board.get_cell(starter_cell.get_row(), move_left);
                    if (aux_cell.is_empty()) {
                        left_found = true;
                        horizontal.putIfAbsent(aux_cell, new ArrayList<>());
                        horizontal.get(aux_cell).add(1);
                    } else{
                        if(aux_cell.get_color() == actual_player.get_player_color()){
                            move_left--;
                            if(move_left >= 0) {
                                aux_cell = this.game_board.get_cell(move_left, starter_cell.get_column());
                                if (aux_cell.is_empty()) left_found = true;
                                else {
                                    move_left++;
                                    aux_cell = this.game_board.get_cell(move_left, starter_cell.get_column());
                                }
                            }
                        }
                        move_left--;
                        if(flip && direction == 0){
                            if(aux_cell.get_color() == actual_player.get_player_color()) mine_found = true;
                            if(!mine_found && !aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                                remove_from_player_cells(aux_cell, opponent.get_player_color());
                                this.set_player_cells(aux_cell, actual_player.get_player_color());
                                game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets the vertical valid moves for a Player in a Game
     * @param starter_cell first Cell for the move
     * @param actual_player Player attempting the move
     * @param opponent Opponent player
     * @param vertical HashMap to store the possible moves
     * @param flip indicates weather we have intention to flip the rival's cells or not
     * @param direction indicates which side we need to look in order to find one of the Player's cell
     * @param mine_found when flipping, indicates weather we found one of the Player's Cell or not
     */
    protected void check_vertical(Cell starter_cell, Player actual_player, Player opponent, HashMap<Cell, ArrayList<Integer>> vertical, boolean flip, Integer direction, boolean mine_found) {
        boolean bottom_found = false;
        boolean top_found = false;

        Cell aux_cell;
        int move_down = starter_cell.get_row() + 1;
        int move_up = starter_cell.get_row() - 1;

        if (move_down < this.game_board.get_size()) {
            aux_cell = this.game_board.get_cell(move_down, starter_cell.get_column());
            if (!aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                move_down++;
                if(flip && direction == 3){
                    remove_from_player_cells(aux_cell, opponent.get_player_color());
                    this.set_player_cells(aux_cell, actual_player.get_player_color());
                    System.out.println(this.get_game_board().get_cell(aux_cell.get_row(),aux_cell.get_column()) + "Dominio");
                    game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                }
                while (move_down < this.game_board.get_size() && !bottom_found) {
                    aux_cell = this.game_board.get_cell(move_down, starter_cell.get_column());
                    if (aux_cell.is_empty()) {
                        bottom_found = true;
                        vertical.putIfAbsent(aux_cell, new ArrayList<>());
                        vertical.get(aux_cell).add(2);
                    } else {
                        if(aux_cell.get_color() == actual_player.get_player_color()){
                            move_down++;
                            if(move_down < this.game_board.get_size()) {
                                aux_cell = this.game_board.get_cell(move_down, starter_cell.get_column());
                                if (aux_cell.is_empty()) bottom_found = true;
                                else {
                                    move_down--;
                                    aux_cell = this.game_board.get_cell(move_down, starter_cell.get_column());
                                }
                            }
                        }
                        move_down++;

                        if(flip && direction == 3){
                            if(aux_cell.get_color() == actual_player.get_player_color()) mine_found = true;
                            if(!mine_found && !aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                                remove_from_player_cells(aux_cell, opponent.get_player_color());
                                this.set_player_cells(aux_cell, actual_player.get_player_color());
                                game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                            }
                        }
                    }
                }
            }
        }

        if (move_up >= 0) {
            aux_cell = this.game_board.get_cell(move_up, starter_cell.get_column());
            if (!aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                move_up--;
                if(flip && direction == 2){
                    remove_from_player_cells(aux_cell, opponent.get_player_color());
                    this.set_player_cells(aux_cell, actual_player.get_player_color());
                    game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                }
                while (move_up >= 0 && !top_found) {
                    aux_cell = this.game_board.get_cell(move_up, starter_cell.get_column());
                    if (aux_cell.is_empty()) {
                        top_found = true;
                        vertical.putIfAbsent(aux_cell, new ArrayList<>());
                        vertical.get(aux_cell).add(3);
                    } else {
                        if(aux_cell.get_color() == actual_player.get_player_color()){
                            move_up--;
                            if(move_up >= 0){
                                aux_cell = this.game_board.get_cell(move_up, starter_cell.get_column());
                                if(aux_cell.is_empty()) top_found = true;
                                else{
                                    move_up++;
                                    aux_cell = this.game_board.get_cell(move_up, starter_cell.get_column());
                                }
                            }
                        }
                        move_up--;

                        if(flip && direction == 2){
                            if(aux_cell.get_color() == actual_player.get_player_color()) mine_found = true;
                            if(!mine_found && !aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                                remove_from_player_cells(aux_cell, opponent.get_player_color());
                                this.set_player_cells(aux_cell, actual_player.get_player_color());
                                game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets the diagonal valid moves for a Player in a Game
     * @param starter_cell first Cell for the move
     * @param actual_player Player attempting the move
     * @param opponent Opponent player
     * @param diagonal HashMap to store the possible moves
     * @param flip indicates weather we have intention to flip the rival's cells or not
     * @param direction indicates which side we need to look in order to find one of the Player's cell
     * @param mine_found when flipping, indicates weather we found one of the Player's Cell or not
     */
    protected void check_diagonal(Cell starter_cell, Player actual_player, Player opponent, HashMap<Cell, ArrayList<Integer>> diagonal, boolean flip, Integer direction, boolean mine_found) {
        boolean top_left_found = false;
        boolean bottom_left_found = false;
        boolean bottom_right_found = false;
        boolean top_right_found = false;

        Cell aux_cell;
        int move_right = starter_cell.get_column() + 1;
        int move_left = starter_cell.get_column() - 1;
        int move_down = starter_cell.get_row() + 1;
        int move_up = starter_cell.get_row() - 1;

        //TOP LEFT
        if (move_left >= 0 && move_up >= 0) {
            aux_cell = this.game_board.get_cell(move_up, move_left);
            if (!aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                move_left--;
                move_up--;
                if(flip && direction == 4){
                    remove_from_player_cells(aux_cell, opponent.get_player_color());
                    this.set_player_cells(aux_cell, actual_player.get_player_color());
                    game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                }
                while ((move_left >= 0) && (move_up >= 0) && (!top_left_found)) {        //skip to the next
                    aux_cell = this.game_board.get_cell(move_up, move_left);             //actual position
                    if (aux_cell.is_empty()) {                                           //if empty, add
                        top_left_found = true;
                        diagonal.putIfAbsent(aux_cell, new ArrayList<>());
                        diagonal.get(aux_cell).add(7);
                    } else {
                        if(aux_cell.get_color() == actual_player.get_player_color()){
                            move_left--;
                            move_up--;
                            if((move_left >= 0) && (move_up >= 0)) {
                                aux_cell = this.game_board.get_cell(move_up, move_left);
                                if (aux_cell.is_empty()) top_left_found = true;
                                else {
                                    move_left++;
                                    move_up++;
                                    aux_cell = this.game_board.get_cell(move_up, move_left);
                                }
                            }
                        }
                        move_left--;
                        move_up--;
                        if(flip && direction == 4){
                            if(aux_cell.get_color() == actual_player.get_player_color()) mine_found = true;
                            if(!mine_found && !aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                                remove_from_player_cells(aux_cell, opponent.get_player_color());
                                this.set_player_cells(aux_cell, actual_player.get_player_color());
                                game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                            }
                        }
                    }
                }
            }
        }
        move_left = starter_cell.get_column() - 1;
        move_up = starter_cell.get_row() - 1;
        //BOTTOM LEFT
        if (move_left >= 0 && move_down < this.game_board.get_size()) {
            aux_cell = this.game_board.get_cell(move_down, move_left);
            if (!aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                move_left--;
                move_down++;
                if(flip && direction == 6){
                    remove_from_player_cells(aux_cell, opponent.get_player_color());
                    this.set_player_cells(aux_cell, actual_player.get_player_color());
                    game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                }
                while ((move_left >= 0) && (move_down < this.game_board.get_size()) && (!bottom_left_found)) {
                    aux_cell = this.game_board.get_cell(move_down, move_left);
                    if (aux_cell.is_empty()) {
                        bottom_left_found = true;
                        diagonal.putIfAbsent(aux_cell, new ArrayList<>());
                        diagonal.get(aux_cell).add(5);
                    } else {
                        if(aux_cell.get_color() == actual_player.get_player_color()){
                            move_left--;
                            move_down++;
                            if((move_left >= 0) && (move_down < this.game_board.get_size())) {
                                aux_cell = this.game_board.get_cell(move_down, move_left);
                                if (aux_cell.is_empty()) bottom_left_found = true;
                                else {
                                    move_left++;
                                    move_down--;
                                    aux_cell = this.game_board.get_cell(move_down, move_left);
                                }
                            }
                        }
                        move_left--;
                        move_down++;
                        if(flip && direction == 6){
                            if(aux_cell.get_color() == actual_player.get_player_color()) mine_found = true;
                            if(!mine_found && !aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                                remove_from_player_cells(aux_cell, opponent.get_player_color());
                                this.set_player_cells(aux_cell, actual_player.get_player_color());
                                game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                            }
                        }
                    }
                }
            }
        }
        move_down = starter_cell.get_row() + 1;
        //BOTTOM RIGHT
        if (move_right < this.game_board.get_size() && move_down < this.game_board.get_size()) {
            aux_cell = this.game_board.get_cell(move_down, move_right);
            if (!aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                move_right++;
                move_down++;
                if(flip && direction == 7){
                    remove_from_player_cells(aux_cell, opponent.get_player_color());
                    this.set_player_cells(aux_cell, actual_player.get_player_color());
                    game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                }
                while ((move_right < this.game_board.get_size()) && (move_down < this.game_board.get_size()) && (!bottom_right_found)) {
                    aux_cell = this.game_board.get_cell(move_down, move_right);
                    if (aux_cell.is_empty()) {
                        bottom_right_found = true;
                        diagonal.putIfAbsent(aux_cell, new ArrayList<>());
                        diagonal.get(aux_cell).add(4);
                    } else {
                        if(aux_cell.get_color() == actual_player.get_player_color()) {
                            move_right++;
                            move_down++;
                            if((move_right < this.game_board.get_size()) && (move_down < this.game_board.get_size())){
                                aux_cell = this.game_board.get_cell(move_down, move_right);
                                if (aux_cell.is_empty()) bottom_right_found = true;
                                else {
                                    move_right--;
                                    move_down--;
                                    aux_cell = this.game_board.get_cell(move_down, move_right);
                                }
                            }
                        }
                        move_right++;
                        move_down++;
                        if(flip && direction == 7){
                            if(aux_cell.get_color() == actual_player.get_player_color()) mine_found = true;
                            if(!mine_found && !aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                                remove_from_player_cells(aux_cell, opponent.get_player_color());
                                this.set_player_cells(aux_cell, actual_player.get_player_color());
                                game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                            }
                        }
                    }
                }
            }
        }
        move_right = starter_cell.get_column() + 1;
        //TOP RIGHT
        if (move_right < this.game_board.get_size() && move_up >= 0) {
            aux_cell = this.game_board.get_cell(move_up, move_right);
            if (!aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                move_right++;
                move_up--;
                if(flip && direction == 5){
                    remove_from_player_cells(aux_cell, opponent.get_player_color());
                    this.set_player_cells(aux_cell, actual_player.get_player_color());
                    game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                }
                while ((move_right < this.game_board.get_size()) && (move_up >= 0) && (!top_right_found)) {
                    aux_cell = this.game_board.get_cell(move_up, move_right);
                    if (aux_cell.is_empty()) {
                        top_right_found = true;
                        diagonal.putIfAbsent(aux_cell, new ArrayList<>());
                        diagonal.get(aux_cell).add(6);
                    } else {
                        if(aux_cell.get_color() == actual_player.get_player_color()) {
                            move_right++;
                            move_up--;
                            if((move_right < this.game_board.get_size()) && (move_up >= 0)){
                                aux_cell = this.game_board.get_cell(move_up, move_right);
                                if (aux_cell.is_empty()) top_right_found = true;
                                else {
                                    move_right--;
                                    move_up++;
                                    aux_cell = this.game_board.get_cell(move_up, move_right);
                                }
                            }
                        }
                        move_right++;
                        move_up--;
                        if(flip && direction == 5){
                            if(aux_cell.get_color() == actual_player.get_player_color()) mine_found = true;
                            if(!mine_found && !aux_cell.is_empty() && aux_cell.get_color() != actual_player.get_player_color()){
                                remove_from_player_cells(aux_cell, opponent.get_player_color());
                                this.set_player_cells(aux_cell, actual_player.get_player_color());
                                game_board.change_colour_cells(aux_cell.get_row(), aux_cell.get_column(), actual_player.get_player_color());
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * Returns all the legal moves for the Player in the Game
     * @param actual_player Player attempting the next move
     * @return array of all the legal moves
     */
    protected HashMap<Cell, ArrayList<Integer>> legal_moves(Player actual_player, Player opponent) {
        HashMap <Cell, ArrayList<Integer>> moves = new HashMap<>();     //DIRECTION: L=0, R=1, U=2, D=3, DUL=4, DUR=5, DDL=6, DDR=7, NONE=8
        ArrayList<Cell> mines = new ArrayList<>();
        if(actual_player.is_player_black()) mines = this.player_cells_p1;
        else mines = this.player_cells_p2;
        for (int i = 0; i < mines.size(); i++) {
            Cell aux_cell = mines.get(i);
            if (hrzn) check_horizontal(aux_cell, actual_player, opponent, moves, false, 8, false);     //DIRECTION: L=0, R=1, U=2, D=3, DUL=4, DUR=5, DDL=6, DDR=7, NONE=8
            if (vert) check_vertical(aux_cell, actual_player, opponent, moves, false, 8, false);
            if (diag) check_diagonal(aux_cell, actual_player, opponent, moves, false, 8, false);
        }
        return moves;
    }

    /**
     * Given the cell of the last positioned token, flips the opponent's tokens that are in between the specified Cell
     * and others player_moving's cells achievable by the given rules
     * @param player_moving Player attempting the next move
     * @param opponent Player which is the opponent of the Player making the move
     * @param selected_cell Cell of the last move made by player_moving
     * @param moves HashMap with all the possible moves of player_moving which contains for each Cell the directions
     *              from where this possible move has been discovered
     */
    protected void flip_cell(Player player_moving, Player opponent, Cell selected_cell, HashMap<Cell, ArrayList<Integer>> moves){
        if(moves.containsKey(selected_cell)){
            this.set_player_cells(selected_cell, player_moving.get_player_color());
            game_board.change_colour_cells(selected_cell.get_row(), selected_cell.get_column(),player_moving.get_player_color());
            ArrayList<Integer> mines = moves.get(selected_cell);
            Integer direction;
            for(int i = 0 ; i < mines.size(); i++){
                direction = mines.get(i);           //DIRECTION: L=0, R=1, U=2, D=3, DUL=4, DUR=5, DDL=6, DDR=7, NONE=8
                if(direction==0) check_horizontal(selected_cell, player_moving, opponent, moves, true, 0, false);
                if(direction==1) check_horizontal(selected_cell, player_moving, opponent, moves, true, 1, false);
                if(direction==2) check_vertical(selected_cell, player_moving, opponent, moves, true, 2, false);
                if(direction==3) check_vertical(selected_cell, player_moving, opponent, moves, true, 3, false);
                if(direction==4) check_diagonal(selected_cell, player_moving, opponent, moves, true, 4, false);
                if(direction==5) check_diagonal(selected_cell, player_moving, opponent, moves, true, 5, false);
                if(direction==6) check_diagonal(selected_cell, player_moving, opponent, moves, true, 6, false);
                if(direction==7) check_diagonal(selected_cell, player_moving, opponent, moves, true, 7, false);
            }
        }
    }

    /**
     * Returns whether the game is finished or not
     * @return boolean that determines if the game is finished or not
     */
    protected boolean is_finished() {
        return (legal_moves(j1, j2).isEmpty() && legal_moves(j2, j1).isEmpty());
    }

    /**
     * Places a token in a given cell
     * @param chosen_cell specifies the cell in which the player wants to place a token
     * @param actual_player specifies which player is placing the token in the specified cell
     */
    protected void set_cell(Cell chosen_cell, Player actual_player){
        Player player_aux;
        if(actual_player.is_player_white()) player_aux = j1;
        else player_aux = j2;
        HashMap<Cell, ArrayList<Integer>> available_cells = legal_moves(actual_player, player_aux);
        if(available_cells.containsKey(chosen_cell)) flip_cell(actual_player, player_aux, chosen_cell, available_cells);
    }
}
