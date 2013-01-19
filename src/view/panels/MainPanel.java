

package view.panels;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import models.JewelModel;
import models.ScoreModel;
import models.TimeModel;

/**
 *
 * @author Wouter Pinnoo
 */
public class MainPanel extends JPanel{

    public MainPanel(){
        JewelModel jewelmodel = new JewelModel();
        ScoreModel scoremodel = new ScoreModel();
        TimeModel timemodel = new TimeModel(jewelmodel, scoremodel);
        TopscorePanel topscorepanel = new TopscorePanel(scoremodel);
        GamePanel gamepanel = new GamePanel(jewelmodel, scoremodel);
        ScorePanel scorepanel = new ScorePanel(jewelmodel, scoremodel, timemodel);
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().
            addComponent(topscorepanel).addComponent(gamepanel).addComponent(scorepanel)));
        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(topscorepanel).addComponent(gamepanel).addComponent(scorepanel));
    }
}
