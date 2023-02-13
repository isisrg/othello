package Dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Comparator;


/**
 * Class used to represent the actual ranking of the Players
 * @author Aidan
 * @version 3.0, 27/4/2021
 * @since 1.8
 */
public class Ranking {
    private HashMap<String, ArrayList<Integer>> player_stats;
    private HashMap<String, ArrayList<Integer>> total_points;
    private HashMap<String, ArrayList<Integer>> total_games;

    public Ranking(){
        this.player_stats = new HashMap<String, ArrayList<Integer>>();
        this.total_points = new HashMap<String, ArrayList<Integer>>();
        this.total_games = new HashMap<String, ArrayList<Integer>>();
    }

    /**
     * Initializes a player in the ranking with all the values equal to 0
     * @param px Player to be introduced in the ranking
     */
    protected void new_ranking_player(Player px){
        this.player_stats.putIfAbsent(px.get_name(), new ArrayList<Integer>());
        for(int counter = 0; counter < 8; ++counter){
            this.player_stats.get(px.get_name()).add(counter, 0);
        }
    }

    /**
     * Updates the score and the average of a player in the given difficulty
     * @param px Player to update its stats
     * @param difficulty Position to modify in the HashMap
     */
    protected void set_score_player(Player px, int difficulty){
        if(this.player_stats.containsKey(px.get_name())){
            update_total_points(px,difficulty);
            update_total_games(px,difficulty);
            player_stats.get(px.get_name()).set(difficulty+4,average(px, difficulty));
            if(player_stats.get(px.get_name()).get(difficulty) < px.get_final_score()){
                player_stats.get(px.get_name()).set(difficulty, px.get_final_score());
            }
        }else{
            new_ranking_player(px);
            this.player_stats.get(px.get_name()).set(difficulty, px.get_final_score());
            update_total_points(px,difficulty);
            update_total_games(px,difficulty);
            player_stats.get(px.get_name()).set(difficulty+4,average(px, difficulty));
        }
    }

    /**
     * Gets the Player name and the stats of the ranking
     * @return HashMap where the name of the player and the stats are saved
     */
    protected HashMap<String, ArrayList<Integer>> get_stats() {
        return this.player_stats;
    }

    /**
     * Gets the Player name and the total points of the Player
     * @return HashMap where the name of the player and the total points are saved
     */
    protected HashMap<String, ArrayList<Integer>> get_total_points() {
        return this.total_points;
    }

    /**
     * Gets the Player name and the total points of the Player
     * @return HashMap where the name of the player and the number of games are saved
     */
    protected HashMap<String, ArrayList<Integer>> get_total_games() {return this.total_games; }

    /**
     * Gets an array of the stats from a single Player
     * @param px Player to get the stats
     * @return Array where the stats of a player are saved
     */
    protected ArrayList<Integer> get_player_stats(Player px) {
        ArrayList<Integer> stats = new ArrayList<Integer>();

        if(this.player_stats.containsKey(px.get_name())){
            stats.addAll(this.player_stats.get(px.get_name()));
        }
        return stats;
    }
    /**
     * Gets an array of the stats from a single Player
     * @param px Player to get the total points
     * @return Array where the total points of a player are saved
     */
    protected ArrayList<Integer> get_player_points(Player px) {
        ArrayList<Integer> stats = new ArrayList<Integer>();

        if(this.total_points.containsKey(px.get_name())){
            stats.addAll(this.total_points.get(px.get_name()));
        }
        return stats;
    }
    /**
     * Gets an array of the number of games from a single Player
     * @param px Player to get the number of games
     * @return Array where the total games of a player are saved
     */
    protected ArrayList<Integer> get_player_games(Player px) {
        ArrayList<Integer> stats = new ArrayList<Integer>();

        if(this.total_games.containsKey(px.get_name())){
            stats.addAll(this.total_games.get(px.get_name()));
        }
        return stats;
    }

    /**
     * Restarts the complete Ranking
     */
    protected void restart_stats() {
        Set<String> keys = player_stats.keySet();
        for(String key: keys) {
            for(int i = 0; i < 8; ++i){
                this.player_stats.get(key).set(i,0);
            }
        }
        keys = total_points.keySet();
        for(String key: keys) {
            for(int i = 0; i < 4; ++i){
                this.total_points.get(key).set(i,0);
            }
        }
        keys = total_games.keySet();
        for(String key: keys) {
            for(int i = 0; i < 4; ++i){
                this.total_games.get(key).set(i,0);
            }
        }
    }

    /**
     * Removes a Player from the Ranking
     * @param px Player to be removed
     */
    protected void remove_player(Player px){
        if(player_stats.containsKey(px.get_name())) player_stats.remove(px.get_name());
        if(total_points.containsKey(px.get_name())) total_points.remove(px.get_name());
        if(total_games.containsKey(px.get_name())) total_games.remove(px.get_name());
    }

    /**
     * Checks if a player already exists in the ranking
     * @param px Player to be checked
     * @return Whether a Player exists or not
     */
    protected boolean exists_player(Player px){
        return player_stats.containsKey(px.get_name());
    }


    /**
     * Gets a List with the given stats of the players sorted
     * @param value_to_sort Value used to sort the HashMap
     * @return Return a List with the selected value sorted.
     */
    protected List<Entry<String, Integer>> sort_ranking(int value_to_sort){
        Map<String, Integer> sort_ranking = new HashMap<>();
        Iterator iterator_hm = player_stats.entrySet().iterator();
        for(int i = 0; i < player_stats.size(); i++){
            Map.Entry next_entry = (Map.Entry) iterator_hm.next();
            ArrayList<Integer> auxiliar = player_stats.get(next_entry.getKey());
            sort_ranking.put((String)next_entry.getKey(),auxiliar.get(value_to_sort));
        }
        List<Entry<String, Integer>> list_scores = new ArrayList<Entry<String, Integer>>(sort_ranking.entrySet());
        Collections.sort(list_scores, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });
        return list_scores;
    }

    /**
     * Initializes the given Player in the total points HashMap. All the values are 0
     * @param px Player to initialize in the HashMap
     */
    protected void new_total_points(Player px){
        this.total_points.putIfAbsent(px.get_name(), new ArrayList<Integer>());
        for(int counter = 0; counter < 4; ++counter){
            this.total_points.get(px.get_name()).add(counter, 0);
        }
    }

    /**
     * Initializes the selected Player in the total games HashMap. All the values are 0
     * @param px Player to initialize in the HashMap
     */
    protected void new_total_games(Player px){
        this.total_games.putIfAbsent(px.get_name(), new ArrayList<Integer>());
        for(int counter = 0; counter < 4; ++counter){
            this.total_games.get(px.get_name()).add(counter, 0);
        }
    }

    /**
     * Updates in the HashMap for the given difficulty adding the new score to the total of points
     * @param px Player to update in the HashMap
     * @param difficulty Position in the HashMap total points to be updated
     */
    protected void update_total_points(Player px, int difficulty){
        if(this.total_points.containsKey(px.get_name())){
            Integer last_points = total_points.get(px.get_name()).get(difficulty);
            Integer current_points = last_points + px.get_final_score();
            total_points.get(px.get_name()).set(difficulty, current_points);
        }else{
            new_total_points(px);
            this.total_points.get(px.get_name()).set(difficulty, px.get_final_score());
        }
    }

    /**
     * Updates in the HashMap for the given difficulty adding one game to the total of games
     * @param px Player to update in the HashMap
     * @param difficulty Position in the HashMap total games to be updated
     */
    protected void update_total_games(Player px, int difficulty){
        if(this.total_games.containsKey(px.get_name())){
            Integer last_games = total_games.get(px.get_name()).get(difficulty);
            Integer current_games = last_games + 1;
            total_games.get(px.get_name()).set(difficulty, current_games);
        }else{
            new_total_games(px);
            this.total_games.get(px.get_name()).set(difficulty, 1);
        }
    }

    /**
     * Returns the average (division between the total points and the total games) of a player in the value_to_calculate HashMap position
     * @param px Player to calculate the average
     * @param value_to_calculate Position(difficulty) in the HashMap to calculate the average
     * @return The resulting average of the Player in the given difficulty
     */
    protected Integer average(Player px, int value_to_calculate){
        if(this.total_points.containsKey(px.get_name())){
            Integer num_points = total_points.get(px.get_name()).get(value_to_calculate);
            Integer num_games = total_games.get(px.get_name()).get(value_to_calculate);
            Integer average_calculated;
            if(num_games == 0) average_calculated = 0;
            else average_calculated = num_points / num_games;
            return average_calculated;
        }
        else return 0;
    }
}
