/* 
 * Creation : 7 nov. 2015
 */
package gol;



/**
 * @date    7 nov. 2015
 * @author  Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class CelluleGriffeath extends Cellule{
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    
    public CelluleGriffeath(GriffeathState st){
        super(st);
    }
    
    //**************************************************************************
    // METHODS
    //**************************************************************************
    
    @Override
    public State nextState() {
        if((GriffeathState)this.state ==(GriffeathState)GriffeathState.FIRST){
            
        }
        else{
            
        }
        return this.state;
    }

    @Override
    public void kill() {
        state = LifeState.DEAD;
    }

    @Override
    public void born() {
        state = LifeState.ALIVE;
    }
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

    @Override
    public int getNbNeighborsAlive() {
        int sum=0;
        for(Enum direction : neighbors.keySet()){
            if(neighbors.get(direction) != null){
                if(neighbors.get(direction).getState() == LifeState.ALIVE){
                    sum++;
                }
            }
        }
        return sum;
    }

    @Override
    public boolean isAlive() {
        return state == LifeState.ALIVE;
    }
}
