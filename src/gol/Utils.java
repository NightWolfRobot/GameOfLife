 /* 
 * Creation : 6 nov. 2015
 */
package gol;

import java.io.File;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * @date 6 nov. 2015
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class Utils {

    //**************************************************************************
    // FUNCTIONS
    //**************************************************************************
    /**
     * creates a new board according to the parameters in Param
     * @return the board generated
     */
    public static Board createNewBoard() {
        Board board;
        if (Param.GRID == 1) {
            board = new BoardSquare();
        } else if (Param.GRID == 2) {
            board = new BoardHexa();
        } else {
            board = new BoardSquare();
        }
        return board;
    }

    /**
     * creates a new cell according to the parameters in Param
     * @param st
     * @return 
     */
    public static Cellule createNewCell(State st) {
        Cellule cell;
        if (Param.IS_GRIFFEATH) {
            cell = new CelluleGriffeath((StateInt) st);
        } else if (Param.IS_IMMIGRATION) {
            cell = new CelluleImmigration((StateImmigration) st);
        } else if (Param.IS_ISOTROPE) {
            cell = new CelluleIsotrope((StateLife) st);
        } else if (Param.IS_HIGHLIFE) {
            cell = new CelluleHighLife((StateLife) st);
        } else if (Param.IS_DAY_AND_NIGHT) {
            cell = new CelluleDayAndNight((StateLife) st);
        } else if (Param.IS_FREDKIN) {
            cell = new CelluleFredkin((StateLife) st);
        } else if (Param.IS_MOYENNE) {
            cell = new CelluleMoyenne((StateDouble) st);
        } else if (Param.IS_GRIFFEATH_N) {
            cell = new CelluleGriffeathN((StateInt) st);
        } else if (Param.IS_MATHS) {
            cell = new CelluleMaths((StateDouble) st);
        } else {
            cell = new CelluleClassique((StateLife) st);
        }
        return cell;
    }
    
    //**************************************************************************
    // GRAPHICAL INTERFACE
    //**************************************************************************
    /**
     * attached all listeners needed on cell in the GUI
     * @param r chape of the cell (square or hexagonal)
     * @param c the cell
     * @param controller 
     */
    public static void attachListeners(Shape r, Cellule c, Controller controller) {
        r.setOnMousePressed(e -> {
            //r.setFill(Param.COLOR_BORN);
        });

        r.setOnMouseClicked(e -> {
            //Si la cellule est vivante on la tue autrement elle nait
            if (c.isAlive()) {
                c.kill();
                r.setFill(Utils.getColorCell(c));
                controller.incrementPopulation();
            } else {
                c.born();
                r.setFill(Utils.getColorCell(c));
                controller.incrementPopulation();
            }
        });
    }

    /**
     * 
     * @param i
     * @param j
     * @return le number of the element in the pane
     */
    public static int boardToPaneCoords(int i, int j) {
        return (i * Param.NB_COLUMNS) + j;
    }
    
    /**
     * used to set the color of the cellule according to the parameters in Param
     * @param cell to color
     * @return the color of the cell
     */
    public static Color getColorCell(Cellule cell) {
        Color c;
        if (Param.IS_IMMIGRATION) {
            if (cell.getState() == StateImmigration.ALIVE) {
                c = Color.web("#18bbff");
            } else if (cell.getState() == StateImmigration.ZOMBIE) {
                c = Color.web("#59EA9D");
            } else {
                c = Color.web("#757575");
            }
        } else if (Param.IS_GRIFFEATH) {
            if(((StateInt) cell.getState()).val == 0){
                c = Color.web("#ffff00");
            }
            else if(((StateInt) cell.getState()).val == 1){
                c = Color.web("#ffaa00");
            }
            else if(((StateInt) cell.getState()).val == 2){
                c = Color.web("#ff5500");
            }
            else {
                c = Color.web("#ff0000");
            }
        } else if(Param.IS_GRIFFEATH_N){
            int percentVal = (((StateInt) cell.getState()).val)*360/Param.ETAT_MAX_GRIFFEAT;
            c = Color.hsb(percentVal, 0.7, 0.94);
        } else if(Param.IS_MOYENNE || Param.IS_MATHS) {
            c = Color.color(0,0,(((StateDouble) cell.getState()).val));
            //Couleurs psychédélique
            //c = Color.hsb((((StateDouble) cell.getState()).val)*360, 0.7, 0.94);
        } else {
            if (cell.getState() == StateLife.ALIVE) {
                c = Color.web("#18bbff");
            } else {
                c = Color.web("#757575");
            }
        }
        return c;
    }
    
    /**
     * Used in the pattern choicebox in the GUI
     * @param directory from where to get all the pattern files
     * @return list of all the names of the files (without the extension)
     */
    public static ObservableList<String> listDirectory(File directory){
	String[] listefichiers;
	listefichiers = directory.list();
	ObservableList<String> res = FXCollections.observableArrayList();
        
        for (String listefichier : listefichiers) {
            if (listefichier.endsWith(".gol") == true) {
                res.add(listefichier.substring(0, listefichier.length() - 4));
                // on choisit la sous chaine - les 4 derniers caracteres ".gol"
            }
        }
        
	return res;
    }
}
