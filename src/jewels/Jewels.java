package jewels;

import java.awt.EventQueue;
import java.awt.MenuBar;
import java.awt.Toolkit;
import javax.swing.JFrame;
import view.menubars.Menubar;
import view.panels.MainPanel;

/**
 *
 * @author Wouter Pinnoo
 */
public class Jewels {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Jewels().createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        JFrame window = new JFrame("Jewels \u00a9 Wouter Pinnoo");
        window.setContentPane(new MainPanel());
        window.setIconImage(Toolkit.getDefaultToolkit().getImage(Jewels.class.getResource("3.png")));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
