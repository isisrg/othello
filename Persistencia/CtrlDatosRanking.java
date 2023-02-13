package Persistencia;

import Dominio.Ranking;
import Dominio.Game;

import java.io.*;

/**
        * Class intended to serve as a class to manage the Ranking save/delete actions
        * @author Nico
        * @version 1.0
        * @since 1.8
        */
public class CtrlDatosRanking extends CtrlDatosPersistencia{
    private static CtrlDatosRanking controlador = new CtrlDatosRanking();

    public static CtrlDatosRanking getInstance(){
        return controlador;
    }


}
