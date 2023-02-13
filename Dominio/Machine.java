package Dominio;

/**
 * Class used to represent a Player of type Machine which will be using the implemented and chosen algorithm
 * @author Sara
 * @version 2.0, 14/4/2021
 * @since 1.8
 */

public class Machine extends Player{

    private int depth;

    /**
     * Sets the colour and depth of iterations for the algorithm of a Player Machine
     * @param choosen_depth number of iterations for the algorithm
     */
    protected Machine(int choosen_depth){
        super();
        depth=choosen_depth;
    }

    /**
     * Returns the depth
     * @return An integer with the depth
     */
    public int get_depth(){ return this.depth; }
}
