package controllers.listeners;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.MouseInputAdapter;
import models.JewelModel;
import models.ScoreModel;
import pieces.Direction;
import pieces.Jewel;
import pieces.Position;

/**
 *
 * @author Wouter Pinnoo
 */
public class MouseListener extends MouseInputAdapter {

    private JewelModel jewelmodel;
    private ScoreModel scoremodel;
    private Jewel previous;

    public MouseListener(JewelModel jewelmodel, ScoreModel scoremodel) {
        this.jewelmodel = jewelmodel;
        this.scoremodel = scoremodel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        checkAndSwapSameTypes(jewelmodel.getJewel(e.getX(), e.getY()));
    }

    protected void removeJewelsInList(List<Jewel> list) {
        List<Position> emptyPlaces = new ArrayList<>();
        for (Jewel j : list) {
            emptyPlaces.add(j.getPos());
            jewelmodel.removeJewel(j);
        }
        jewelmodel.fillEmptyPlaces(emptyPlaces);
    }

    protected Jewel giveJewelWithRelPositionAndSameType(Jewel jewel, Direction direction) {
        if (direction.isValidDirection() && jewel != null) {
            for (Jewel j : jewelmodel.getJewellist()) {
                if (jewel.relativePositionOf(j).equals(direction) && jewel.sameTypeAs(j)) {
                    return j;
                }
            }
        }
        return null;
    }

    public List<Jewel> listJewelsWithRelPosition(Jewel jewel, Direction direction) {
        List<Jewel> list = new ArrayList<>();
        list.add(jewel);
        Jewel j = jewel;
        boolean first = true;
        while ((j != null && j != jewel) || first) {
            first = false;
            j = giveJewelWithRelPositionAndSameType(j, direction);
            if (j != null) {
                list.add(j);
            }
        }
        j = jewel;
        first = true;
        while ((j != null && j != jewel) || first) {
            first = false;
            j = giveJewelWithRelPositionAndSameType(j, direction.giveOpposite());
            if (j != null) {
                list.add(j);
            }
        }
        return list;
    }

    public void checkAndSwapSameTypes(final Jewel current) {
        if (current.nextTo(previous)) {
            jewelmodel.swap(current, previous);
            boolean success = false;
            for (Jewel j : new Jewel[]{current, previous}) {
                for (Direction direction : Direction.DIRECTIONS_WITHOUT_OPPOSITES) {
                    List<Jewel> templist = listJewelsWithRelPosition(j, direction);
                    if (templist.size() >= 2) {
                        removeJewelsInList(templist);
                        scoremodel.scoreUp(templist.size());
                        success = true;
                    }
                }
            }
            if (!success) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                        } finally {
                            jewelmodel.swap(current, previous);
                            scoremodel.scoreDown();
                        }
                    }
                });

            }
            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    previous = null;
                }
            });
        } else {
            previous = current;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
