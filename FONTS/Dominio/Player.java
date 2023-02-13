package Dominio;

import java.util.ArrayList;

/**
 * Class used to represent a Player which will be either a Machine or a Human
 * @author Sara
 * @version 2.0, 14/4/2021
 * @since 1.8
 */

public class Player {

    private String name;
    private int color;       //BLACK - 0, WHITE - 1
    private int final_score;

    /**
     * Creates an empty Player
     */
    protected Player () {
        this.name = " ";
        this.color = 0;
        this.final_score = 0;
    }

    //Una puntuacion ira asociada a un jugador.
    //Cuando se termine la partida el jugador escribira su nombre y se guardara su score asociado.

    //Pre: El nombre de un jugador sera un String de maximo 3 letras y maximo 9 letras

    /**
     * Sets a Player with a name, a score and its colour
     * @param player_name name to represent the colour of the Player
     * @param player_color colour of the Player in the Game
     */
    public Player(String player_name, int player_color) {
        set_name(player_name);
        this.color=player_color;
        this.final_score = 0;
    }

    /**
     * Gets the representing name of the Player
     * @return string name of the Player
     */
    protected String get_name() { return this.name; }

    /**
     * Sets the representing name of the Player
     * @param name string name of the Player
     */
    protected void set_name(String name) {
        this.name = name;
    }

    /**
     * Gets the final score of the Player in the Game
     * @return final score of the player
     */
    protected int get_final_score() { return this.final_score; }

    /**
     * Sets the final score to a chosen number in the Game
     * @param score final score to be set for the Player
     */
    protected void set_final_score(int score) { this.final_score = score; }

    //public void set_player_color(int color_token) { this.color = color_token; }

    /**
     * Gets the colour representing the Player
     * @return colour of the Player
     */
    protected int get_player_color() { return this.color; }


    /**
     * Returns whether a player is black
     * @return if a player is black
     */
    protected boolean is_player_black(){
        if(this.color == 0) return true;
        else return false;
    }

    /**
     * Returns whether a player is white
     * @return if a player is white
     */
    protected boolean is_player_white(){
        if(this.color == 1) return true;
        else return false;
    }

    /**
     * Creates a deep copy of player px
     * @param px player to clone
     */
    protected void clonar(Player px){
        this.name=px.get_name();
        this.color=px.get_player_color();
    }

}