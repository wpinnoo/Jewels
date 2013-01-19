package models;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import pieces.Direction;
import pieces.Jewel;
import pieces.Position;

/**
 *
 * @author Wouter Pinnoo
 */
public class JewelModel extends Model {

    private List<Jewel> jewellist;
    private int fieldsize = 10;
    private int jewelsize = 30;
    private Image[] imglist;

    public JewelModel() {
        initiateImages();
        initiateJewellist();
    }

    private void initiateImages() {
        imglist = new Image[7];
        for (int i = 0; i < imglist.length; i++) {
            imglist[i] = Toolkit.getDefaultToolkit().getImage(JewelModel.class.getResource((i + 1) + ".png"));
        }
    }

    private void initiateJewellist() {
        jewellist = new ArrayList<>();
        for (int i = 0; i < getFieldsize() * getFieldsize(); i++) {
            int type;
            Jewel newjewel = null;
            boolean success = false;
            while (!success) {
                type = new Random().nextInt(getImgList().length);
                newjewel = new Jewel(
                        getImgList()[type],
                        (i % getFieldsize()),
                        (i / getFieldsize()), this, type);
                if (isValidTypeForPosition(newjewel)) {
                    success = true;
                }
            }
            jewellist.add(newjewel);
        }
        fireStateChanged();
    }

    private boolean isValidTypeForPosition(Jewel jewel) {
        boolean valid = true;
        for (Direction dir : Direction.ALL_DIRECTIONS) {
            Jewel temp = getJewelInRelDirection(jewel, dir);
            boolean validForDirection = true;
            if (temp == null) {
                valid = valid && validForDirection;
                break;
            }
            validForDirection = !temp.sameTypeAs(jewel);
            for (int i = 0; i < 2; i++) {
                temp = getJewelInRelDirection(temp, dir);
                if (temp == null) {
                    break;
                }
                validForDirection = validForDirection && !temp.sameTypeAs(jewel);
            }
            valid = valid && validForDirection;
        }
        return valid;
    }

    public void swap(Jewel j1, Jewel j2) {
        Jewel temp = j1.clone();
        j1.setPos(j2.getPos());
        j2.setPos(temp.getPos());
        fireStateChanged();
    }

    public Jewel getJewel(int x, int y) {
        for (Jewel j : getJewellist()) {
            if (j.getPosx() <= x
                    && (j.getPosx() + 1) * getJewelsize() > x
                    && j.getPosy() <= y
                    && (j.getPosy() + 1) * getJewelsize() > y) {
                return j;
            }
        }
        return null;
    }

    public Jewel getJewel(Position pos) {
        for (Jewel j : getJewellist()) {
            if (j.getPos().equals(pos)) {
                return j;
            }
        }
        return null;
    }

    public Jewel getJewelInRelDirection(Jewel jewel, Direction direction) {
        Jewel newjewel = null;
        for (Jewel j : jewellist) {
            if (j.getPos().equals(new Position(direction.getRelPosition().getX() + jewel.getPosx(), direction.getRelPosition().getY() + jewel.getPosy()))) {
                return j;
            }
        }
        return newjewel;
    }

    public Dimension getFieldDimension() {
        return new Dimension(jewelsize * fieldsize, jewelsize * fieldsize);
    }

    public void removeJewel(Jewel j) {
        jewellist.remove(j);
        fireStateChanged();
    }

    /**
     * @return the jewellist
     */
    public List<Jewel> getJewellist() {
        return jewellist;
    }

    /**
     * @return the fieldsize
     */
    public int getFieldsize() {
        return fieldsize;
    }

    /**
     * @return the jewelsize
     */
    public int getJewelsize() {
        return jewelsize;
    }

    /**
     * @return the IMGLIST
     */
    public Image[] getImgList() {
        return imglist;
    }

    public void reset() {
        initiateJewellist();
    }

    public void fillEmptyPlaces(List<Position> emptyPositionsList) {
        for (Position pos : renewPositionsForEmptyPosition(emptyPositionsList)) {
            int type = new Random().nextInt(getImgList().length);
            jewellist.add(new Jewel(getImgList()[type], pos, this, type));
        }
        fireStateChanged();
    }

    private List<Position> renewPositionsForEmptyPosition(List<Position> positions) {
        List<Position> newemptypositions = new ArrayList<>();
        Position.sortPositionsOnY(positions);
        for (Position pos : positions) {
            Position newposition = new Position(pos.getX(), pos.getY() - positions.size());
            while (newposition.isValidPosition(fieldsize)) {
                newemptypositions.add(newposition);
                Jewel foundjewel = getJewel(newposition);
                if (foundjewel != null) {
                    foundjewel.setPos(pos);
                }
                newposition = new Position(newposition.getX(), newposition.getY() - positions.size());
            }
        }
        return newemptypositions;
    }
}
