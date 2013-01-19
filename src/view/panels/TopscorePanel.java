

package view.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import models.ScoreModel;

/**
 *
 * @author Wouter Pinnoo
 */
public class TopscorePanel extends JPanel{
    
    private JLabel topscorelabel;
    
    public TopscorePanel(final ScoreModel scoremodel){
        topscorelabel = new JLabel("Topscore: "+scoremodel.getTopscore());
        scoremodel.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                topscorelabel.setText("Topscore: "+scoremodel.getTopscore());
            }
        });
        add(topscorelabel);
    }

}
