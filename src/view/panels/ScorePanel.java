package view.panels;

import controllers.actions.RestartAction;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import models.JewelModel;
import models.ScoreModel;
import models.TimeModel;

/**
 *
 * @author Wouter Pinnoo
 */
public class ScorePanel extends JPanel {

    private JLabel scorelabel;
    private JLabel timelabel;

    public ScorePanel(JewelModel jewelmodel, final ScoreModel scoremodel, final TimeModel timemodel) {
        scorelabel = new JLabel("score: " + scoremodel.getScore());
        timelabel = new JLabel("tijd: " + timemodel.getFormattedTime());
        scoremodel.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (scoremodel.currentScoreIsTopscore()) {
                    scorelabel.setText("score: " + scoremodel.getScore() + "!!");
                } else {
                    scorelabel.setText("score: " + scoremodel.getScore());
                }

            }
        });
        timemodel.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                timelabel.setText("tijd: " + timemodel.getFormattedTime());
            }
        });
        add(scorelabel);
        add(new JButton(new RestartAction(jewelmodel, scoremodel, timemodel)));
        add(timelabel);
    }
}
