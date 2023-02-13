package Dominio;

/**
 * Class used to represent a Cell of a Board
 * It can be occupied or not, if it is with a Color by a Player
 * @author Aidan
 * @version 2.0, 14/4/2021
 * @since 1.8
 */

public class Cell {

    private int color;  //WHITE - 0, BLACK - 1, EMPTY - 2
    //a token can either be black or white
    private int column;
    private int row;

    @Override
    public boolean equals(Object o){
        if(o instanceof Cell){
            Cell c= (Cell) o;
            return c.get_column()==get_column() && c.get_row()==get_row();
        }
        return false;
    }
    /**
     * Builds a default empty Cell
     */
    protected  Cell () {}


    /**
     * Builds a Cell with the parameters
     * @param color color of the Token in the Cell
     * @param row row position in the Board
     * @param col column position in the Board
     */
    protected Cell(int color, int row, int col) {
        if(color == 0) set_color(0);
        else if(color == 1) set_color(1);
        else set_color(2);
        this.set_coords(row, col);
    }


    protected Cell(Cell new_cell) {
        this.color = new_cell.get_color();
        this.row = new_cell.get_row();
        this.column = new_cell.get_column();
    }

    /**
     * Gets the colour (or empty) of the Cell
     * @return colour of the Token in the Cell
     *         WHITE - 0, BLACK - 1, EMPTY - 2
     */
    protected int get_color() {
        return color;
    }

    /**
     * Gets the row number of the Cell
     * @return row position within the size of the board
     */
    protected int get_row(){
        return this.row;
    }

    /**
     * Gets the column number of the Cell
     * @return column position within the size of the board
     */
    protected int get_column(){
        return this.column;
    }

    /**
     * Sets the colour of the Cell
     * @param color new color for the Cell
     */
    protected void set_color(int color) {
        this.color = color;
    }

    /**
     * Sets the coordinates of the Cell
     * @param new_row new value for the row of the Cell
     * @param new_col new value for the column of the Cell
     */
    protected void set_coords(int new_row, int new_col) {
        this.column = new_col;
        this.row = new_row;
    }
    /**
     * Returns whether a cell is occupied by a black token
     * @return if the cell is black
     */
    protected boolean is_black(){
        if(this.color == 0) return true;
        else return false;
    }
    /**
     * Returns whether a cell is occupied by a white token
     * @return if the cell is white
     */
    protected boolean is_white(){
        if(this.color == 1) return true;
        else return false;
    }
    /**
     * Returns whether a cell is not occupied
     * @return if the cell is empty
     */
    protected boolean is_empty(){
        if(this.color == 2) return true;
        else return false;
    }
}