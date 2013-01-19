package models;

import controllers.actions.RestartAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Wouter Pinnoo
 */
public class TimeModel extends Model {

    private int timeSeconds;
    private Timer timer;
    private JewelModel jewelmodel;
    private ScoreModel scoremodel;
    private TimeModel timemodel;
    private final int INITIAL_TIME = 120;

    public TimeModel(JewelModel jmodel, ScoreModel smodel) {
        this.jewelmodel= jmodel;
        this.scoremodel = smodel; 
        this.timemodel = this;
        timeSeconds = INITIAL_TIME;
        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                timeSeconds--;
                if(timeSeconds == 0){
                    new RestartAction(jewelmodel, scoremodel, timemodel).resetAll();
                }
                fireStateChanged();
            }
        });
        timer.start();
    }

    public void reset() {
        timeSeconds = INITIAL_TIME;
        fireStateChanged();
    }

    public int getTimeSeconds() {
        return timeSeconds;
    }

    private String formatTime(int timeSeconds) {
        int timeHoures = timeSeconds / 3600;
        int timeMinutes = (timeSeconds - (timeHoures * 3600)) / 60;
        int timeRestSeconds = timeSeconds - (timeHoures * 3600) - (timeMinutes * 60);
        String formattedtime = "";
        if (timeHoures > 0) {
            formattedtime += timeHoures + "u ";
        }
        if (timeMinutes > 0) {
            formattedtime += timeMinutes + "m ";
        }
        formattedtime += timeRestSeconds + "s";
        return formattedtime;
    }

    public String getFormattedTime() {
        return formatTime(timeSeconds);
    }
}
