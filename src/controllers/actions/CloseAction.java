

package controllers.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Wouter Pinnoo
 */
public class CloseAction extends AbstractAction{

    public CloseAction(){
        super("Afsluiten");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

}
