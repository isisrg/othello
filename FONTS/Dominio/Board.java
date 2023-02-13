package Dominio;

//import org.jetbrains.annotations.NotNull;

/**
 * Class used to represent an aggregation of Cells to play a Game
 * @author Sara
 * @version 2.0, 14/4/2021
 * @since 1.8
 */

public class Board{
    private int size;
    private Cell[][] board_game;

    /**
     * Creates a new Board with default size (8), this constructor will be used typically to clone another one
     */
    protected Board(){
        this.set_size(8);
        this.set_empty_board();
    }

    /**
     * Creates a new Board with a determined size
     * @param size_board size of the rows and columns of the Board
     */
    protected Board(int size_board){
        this.set_size(size_board);
        this.set_empty_board();
    }

    /**
     * Changes the colour of the Cell for the given Player
     * @param row row of the cell to be changed
     * @param col column of the cell to be changed
     * @param player_col colour of the Player who now gets the Cell
     */
    protected void change_colour_cells(int row, int col, int player_col){
        this.board_game[row][col].set_color(player_col);
    }

    /**
     * Gets the number of rows and columns of the Board
     * @return size of the rows and columns
     */
    protected int get_size() {
        return this.size;
    }

    /**
     * Gets the Cell for the given rows and columns within the Board
     * @param row row position of the Cell
     * @param column row position of the Cell
     * @return Cell type of the position row, column
     */
    protected Cell get_cell(int row, int column) {
        return this.board_game[row][column];
    }

    /**
     * Sets the size of the rows and columns of the Board
     * @param size_board number of rows and columns
     */
    protected void set_size(int size_board) {
        this.size=size_board;
    }

    /**
     * Initializes a Board with all its Cells empty
     */
    protected void set_empty_board() {
        this.board_game = new Cell[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                Cell aux = new Cell(2, i, j);
                this.board_game[i][j] = aux;
            }
        }
    }

    /**
     * Initializes a Board with the classic Position of two black Tokens and two white Tokens in the center of the Board
     */
    protected void initialize_default_board(){
        if(size>=2) {
            int half = size/2;
            this.board_game[half-1][half-1].set_color(1);
            this.board_game[half][half-1].set_color(0);
            this.board_game[half-1][half].set_color(0);
            this.board_game[half][half].set_color(1);
        }
    }

    /**
     * Creates an exact copy(new object) of the board we pass
     * @param clone_this_board board we need to copy
     */
    protected void clone(Board clone_this_board){
        this.size = clone_this_board.get_size();
        this.board_game = new Cell[clone_this_board.get_size()][clone_this_board.get_size()];
        for(int i = 0; i < clone_this_board.get_size(); i++){
            for(int j = 0; j < clone_this_board.get_size();j++) {
                Cell aux = new Cell(clone_this_board.get_cell(i,j).get_color(), clone_this_board.get_cell(i,j).get_row(), clone_this_board.get_cell(i,j).get_column());
                this.board_game[i][j] = aux;
            }
        }
    }


    /**
     * Creates a copy of chars of the given board
     * @param change_board board we need to change
     * @return returns a conversion from class Board to char[][]
     */
    protected char[][] board_to_char(Board change_board){
        this.size = change_board.get_size();
        char[][] charBoard = new char[size][size];
        int aux;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                final int RADIX = 10;
                aux = change_board.get_cell(i, j).get_color();
                charBoard[i][j] = Character.forDigit(aux, RADIX);
            }
        }
        return charBoard;
    }

    protected Board int_to_board(int[][] board_int, int size_board_int){
        this.size=size_board_int;
        this.set_empty_board();
        for (int i=0;i<size_board_int;i++){
            for(int j=0;j<size_board_int;j++){
                if(board_int[i][j]==0){
                    this.change_colour_cells(i,j,0);
                }else if(board_int[i][j]==1){
                    this.change_colour_cells(i,j,1);
                }else{
                    this.change_colour_cells(i,j,2);
                }
            }
        }
        return this;
    }

}