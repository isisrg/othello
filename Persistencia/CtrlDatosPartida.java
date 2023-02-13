package Persistencia;

import Dominio.Ranking;
import Dominio.Game;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
        * Class used to manage the game class to save/delete/write file
        * @author Nico
        * @version 1.0
        * @since 1.8
        */
public class CtrlDatosPartida extends CtrlDatosPersistencia{
    private static CtrlDatosPartida controlador = new CtrlDatosPartida();

    public static CtrlDatosPartida getInstance(){
        return controlador;
    }
    /**
     * Saves the content into a file
     * @param content Game being played
     */
    public void save_game(String content){
        String path=get_games_path();
        write_file(path, content);
    }
    /**
     * Saves the content into a file
     * @return returns the list of the games
     */
    public String[] get_game_list() {
        String path = get_games_path();
        if(Files.exists(Paths.get(path))){
            String[] files = get_files(path);
            return files;
        }else{
            String[] prueba={""};
            return prueba;
        }
    }

}
