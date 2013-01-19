

package view.menubars;

import controllers.actions.CloseAction;
import controllers.actions.RestartAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import models.JewelModel;
import models.ScoreModel;

/**
 *
 * @author Wouter Pinnoo
 */
public class Menubar extends JMenuBar{
    
    public Menubar(RestartAction restartaction, CloseAction closeaction){
        JMenu game = new JMenu("Spel");
        game.add(new JMenuItem(restartaction));
        game.add(new JMenuItem(closeaction));
    }

}
