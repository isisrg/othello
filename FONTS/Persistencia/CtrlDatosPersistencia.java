package Persistencia;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
        * Class used to implement the algorithm the machine will be using known as MinMax
        * With an improvement of the efficiency with cutoffs and depth of the iterations
        * @author Nico
        * @version 1.0
        * @since 1.8
        */
public class CtrlDatosPersistencia {
    private File new_file;
    private FileReader fr;
    private BufferedReader br;
    private FileWriter fw;
    private BufferedWriter bw;
    private String path = "src/main/java/Games";

    public CtrlDatosPersistencia(){
        new_file=null;
        fr=null;
        br=null;
        open_file();
    }

    /**
     * Saves the content into a file
     * @return returns the path where we save our files
     */
    public String get_games_path(){
        return path;
    }

    /**
     * Saves the content into a file
     * @return return the list of files in a path
     */
    public String[] get_files(String path){
        File f = new File(path);
        return f.list();
    }

    /**
     * Saves the content into a file
     * Creates a new file
     */
    public void open_file(){
        new_file=new File(path);
        if(!new_file.exists()) try{
            new_file.createNewFile();
        }catch (IOException exception){
            System.out.println(exception);
        }
    }

    /**
     * Saves the content into a file
     * Deletes a file
     */
    public void delete_file(File delete_file){
        if(delete_file.exists()){
            delete_file.delete();
        }
    }

    /**
     * Saves the content into a file
     * @param path path to the files
     * @param content content to write
     * Writes the content into a file
     */
    public void write_file( String path, String content ) {
        try {
            if(Files.exists(Paths.get(path))){
                File f = new File(path);
                boolean bool=f.mkdir();
            }

            this.fw = new FileWriter(path);

            this.bw = new BufferedWriter(this.fw);
            this.bw.write(content);
            this.bw.close();

        } catch(IOException ex) {
            System.out.println(ex);
        }
    }
}
