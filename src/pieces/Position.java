package pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Wouter Pinnoo
 */
public class Position {


    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        Position o2;
        try {
            o2 = (Position) o;
        } catch (Exception e) {
            return false;
        }
        return getX() == o2.getX() && getY() == o2.getY();
    }
    
    @Override
    public String toString(){
        return "Position ("+getX()+", "+getY()+")";
    }
    
    public boolean isValidPosition(int maxpos){
        return getX() >= 0 && getX() <= maxpos && getY() >= 0 && getY() <= maxpos;
    }
    
    public static void sortPositionsOnY(List<Position> positions) {
        Collections.sort(positions, new Comparator<Position>() {

            @Override
            public int compare(Position o1, Position o2) {
                if(o1.getY() > o2.getY()){
                    return 1;
                } else if (o1.getY() == o2.getY()){
                    if(o1.getX() >= o2.getX()){
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        });
    }
}
