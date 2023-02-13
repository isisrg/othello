package Dominio;
/**
 * Class used to test board
 * @author Sara
 * @version 1.0, 23/4/2021
 * @since 1.8
 */
public class DriverBoard {

    static void print_board(Board b){
        for(int i=0;i<b.get_size();i++){
            for(int j=0;j< b.get_size();j++){
                System.out.print(b.get_cell(i,j).get_color());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Board board=new Board(4);
        board.initialize_default_board();
        System.out.println("Tablero inicializado");
        print_board(board);
        System.out.println("-----------------------------");
        Board clonar=new Board();
        System.out.println("Tablero Clonado");
        print_board(clonar);
        char [][] board_c;
        board_c=board.board_to_char(board);
        System.out.println("-----------------------------");
        System.out.println("Tablero con matriz de chars");
        for(int i=0;i< board_c.length;i++){
            for(int j=0;j< board_c.length;j++){
                System.out.print(board_c[i][j]);
            }
            System.out.println();
        }
    }
}
