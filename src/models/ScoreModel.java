package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Wouter Pinnoo
 */
public class ScoreModel extends Model {

    private int currentScore;
    private int topscore;

    public ScoreModel() {
        currentScore = 0;
        topscore = parseTopscore();
        fireStateChanged();
    }

    public void scoreUp(int numberOfCorrectJewels) {
        if (numberOfCorrectJewels == 3) {
            currentScore += 100;
        } else if (numberOfCorrectJewels == 4) {
            currentScore += 150;
        } else if (numberOfCorrectJewels >= 5) {
            currentScore += 250;
        }
        fireStateChanged();
    }

    public void scoreDown() {
        currentScore -= 25;
        if (currentScore < 0) {
            currentScore = 0;
        }
        fireStateChanged();
    }

    public int getScore() {
        return currentScore;
    }
    
    public boolean currentScoreIsTopscore(){
        return currentScore > topscore;
    }

    public void reset() {
        if (currentScore > topscore) {
            System.out.println("Topscore verbroken!");
//            JOptionPane.showInternalMessageDialog(null, "Topscore verbroken!", "Topscore!", JOptionPane.INFORMATION_MESSAGE);
            topscore = currentScore;
            writeNewTopscore(topscore);
        }
        currentScore = 0;
        fireStateChanged();
    }

    private int parseTopscore() {
        int parsed = 0;
        try (Scanner sc = new Scanner(new File("topscore.txt"))) {
            parsed = sc.nextInt();
        } catch (FileNotFoundException ex) {
            System.err.println("IO Error: " + ex);
        } finally {
            return parsed;
        }
    }

    public int getTopscore() {
        return topscore;
    }

    private void writeNewTopscore(int topscore) {
        try (PrintWriter pw = new PrintWriter(new File("topscore.txt"))) {
            pw.print(topscore);
        } catch (FileNotFoundException ex) {
            System.err.println("IO Error: " + ex);
        }
    }
}