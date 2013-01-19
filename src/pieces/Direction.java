

package pieces;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Wouter Pinnoo
 */
public class Direction {
    
    private String dirstring;
    public static final Map<String, String> OPPOSITES;
    public static final Direction[] ALL_DIRECTIONS;
    public static final Direction[] DIRECTIONS_WITHOUT_OPPOSITES;
    static{
        OPPOSITES = new HashMap<>();
        OPPOSITES.put("left", "right");
        OPPOSITES.put("above", "under");
        OPPOSITES.put("right", "left");
        OPPOSITES.put("under", "above");
        OPPOSITES.put("none", "none");
        ALL_DIRECTIONS = new Direction[]{new Direction("left"), new Direction("right"), new Direction("above"), new Direction("under")};
        DIRECTIONS_WITHOUT_OPPOSITES = new Direction[]{ALL_DIRECTIONS[0], ALL_DIRECTIONS[2]};
    }
    
    public static final Map<String, Position> RELATIVE_POSITIONS;
    
    static{
        RELATIVE_POSITIONS = new HashMap<>();
        RELATIVE_POSITIONS.put("left", new Position(-1, 0));
        RELATIVE_POSITIONS.put("above", new Position(0, -1));
        RELATIVE_POSITIONS.put("right", new Position(1, 0));
        RELATIVE_POSITIONS.put("under", new Position(0, 1));
        RELATIVE_POSITIONS.put("none", new Position(0, 0));
    }
    
    
    public Direction(String dir){
        if(!OPPOSITES.containsKey(dir)){
        }
        dirstring = dir;
    }
    
    @Override
    public boolean equals(Object o){
        Direction o2;
        try {
            o2 = (Direction) o;
        } catch(Exception e){
            return false;
        }
        return getDirection().equals(o2.getDirection());
    }
    
    @Override
    public String toString(){
        return "Direction: "+dirstring;
    }
    
    public String getDirection(){
        return dirstring;
    }
    
    public Direction giveOpposite(){
        return new Direction(OPPOSITES.get(dirstring));
    }

    public static String giveOpposite(String dir){
        return OPPOSITES.get(dir);
    }
    
    public boolean isValidDirection(){
        return !getDirection().equals("none");
    }
    
    public Position getRelPosition(){
        return RELATIVE_POSITIONS.get(dirstring);
    }
}
