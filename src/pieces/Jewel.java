package pieces;

import java.awt.Graphics;
import java.awt.Image;
import models.JewelModel;

/**
 *
 * @author Wouter Pinnoo
 */
public class Jewel {

    private Image img;
    private Position pos;
    private JewelModel jewelmodel;
    private int type;

    public Jewel(Image img, Position pos, JewelModel jewelmodel, int type) {
        this.img = img;
        this.pos = pos;
        this.jewelmodel = jewelmodel;
        this.type = type;
    }
    
    public Jewel(Image img, int posx, int posy, JewelModel jewelmodel, int type){
        this(img, new Position(posx, posy), jewelmodel, type);
    }
    
    public Jewel(Position pos){
        this.img = null;
        this.pos = pos;
        this.jewelmodel = null;
        this.type = -1;
    }
    
    public Jewel(int posx, int posy){
        this(new Position(posx, posy));
    }
    
    
    
    public Jewel(){
        this.img = null;
        this.pos = null;
        this.jewelmodel = null;
        this.type = -1;
    }

    public void paint(Graphics g) {
        g.drawImage(getImg(), getPosx() * jewelmodel.getJewelsize(), getPosy() * jewelmodel.getJewelsize(), jewelmodel.getJewelsize(), jewelmodel.getJewelsize(), null);
    }

    @Override
    public Jewel clone() {
        return new Jewel(getImg(), getPos(), jewelmodel, getType());
    }

    @Override
    public String toString() {
        return "Jewel on position " + getPos();
    }

    public boolean nextTo(Jewel jewel) {
        if (jewel == null) {
            return false;
        }
        return !relativePositionOf(jewel).equals("none");
    }

    public Direction relativePositionOf(Jewel jewel) {
        if ((jewel.getPosx() - this.getPosx() == 1
                && jewel.getPosy() == this.getPosy())) {
            return new Direction("left");
        }
        if ((jewel.getPosx() - this.getPosx() == -1
                && jewel.getPosy() == this.getPosy())) {
            return new Direction("right");
        }
        if ((jewel.getPosy() - this.getPosy() == 1
                && jewel.getPosx() == this.getPosx())) {
            return new Direction("under");
        }
        if ((jewel.getPosy() - this.getPosy() == -1
                && jewel.getPosx() == this.getPosx())) {
            return new Direction("above");
        }
        return new Direction("none");
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Jewel j = (Jewel) obj;
            return img == j.getImg()
                    && pos == j.getPos()
                    && type == j.getType();
        } catch (Exception e) {
            return false;
        }

    }

    public boolean sameTypeAs(Jewel j) {
        return j.getType() == getType();

    }
    
    /**
     * @return the img
     */
    public Image getImg() {
        return img;
    }

    public Position getPos(){
        return pos;
    }
    
    public void setPos(Position newpos){
        pos = newpos;
    }
    
    /**
     * @return the posx
     */
    public int getPosx() {
        return pos.getX();
    }

    /**
     * @param posx the posx to set
     */
    public void setPosx(int posx) {
        this.pos = new Position(posx, this.pos.getY());
    }

    /**
     * @return the posy
     */
    public int getPosy() {
        return pos.getY();
    }

    /**
     * @param posy the posy to set
     */
    public void setPosy(int posy) {
        this.pos = new Position(this.pos.getX(), posy);
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }
}
