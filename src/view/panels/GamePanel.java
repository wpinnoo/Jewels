

package view.panels;

import controllers.listeners.MouseListener;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import models.JewelModel;
import models.ScoreModel;
import pieces.Jewel;

/**
 *
 * @author Wouter Pinnoo
 */
public class GamePanel extends JPanel{

    private JewelModel jewelmodel;
    
    public GamePanel(final JewelModel jewelmodel, ScoreModel scoremodel){
        this.jewelmodel = jewelmodel;
        setPreferredSize(jewelmodel.getFieldDimension());
        addMouseListener(new MouseListener(jewelmodel, scoremodel));
        this.jewelmodel.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                repaint();
            }
        });
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Jewels
        for(Jewel jewel : jewelmodel.getJewellist()){
            jewel.paint(g.create());
        }
        
        // Lines
        g.setColor(Color.BLACK);
        for(int i=1; i < jewelmodel.getFieldsize(); i++){
            g.drawLine(i*jewelmodel.getJewelsize(), 0, i*jewelmodel.getJewelsize(), jewelmodel.getFieldsize()*jewelmodel.getJewelsize());
        }
        for(int i=1; i < jewelmodel.getFieldsize(); i++){
            g.drawLine(0, i*jewelmodel.getJewelsize(), jewelmodel.getFieldsize()*jewelmodel.getJewelsize(), i*jewelmodel.getJewelsize());
        }
    }
}
