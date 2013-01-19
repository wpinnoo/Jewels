

package controllers.actions;

import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import models.JewelModel;
import models.ScoreModel;
import models.TimeModel;

/**
 *
 * @author Wouter Pinnoo
 */
public class RestartAction extends AbstractAction{

    private JewelModel jewelmodel;
    private ScoreModel scoremodel;
    private TimeModel timemodel;
    
    public RestartAction(JewelModel jewelmodel, ScoreModel scoremodel, TimeModel timemodel){
        super("Herstart");
        this.jewelmodel = jewelmodel;
        this.scoremodel = scoremodel;
        this.timemodel = timemodel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        resetAll();
    }
    
    public void resetAll(){
        jewelmodel.reset();
        scoremodel.reset();
        timemodel.reset();
    }
}
