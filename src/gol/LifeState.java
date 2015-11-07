
package gol;

/**
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 * @date 30/10/2015
 */
public enum LifeState implements State{
    DEAD{
        @Override
        public char toChar() {
            return '.';
        }
    },
    ALIVE{
        @Override
        public char toChar() {
            return 'O';
        }
    }
}
