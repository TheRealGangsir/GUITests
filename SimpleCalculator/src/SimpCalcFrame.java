
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Created on 12/5/2016, 2:16 PM
 *
 * @author Noah Morton
 * Tully 7th period
 * Part of project SimpleCalculator
 */
public class SimpCalcFrame extends JFrame {

    public SimpCalcFrame() {

        super("Simple Calculator");

        // Sets the close button to exit the program
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // makes the window not able to be re-sized
        setResizable(false);
        // creates the window
        pack();
        // creates the panel
        SimpCalcPanel p = new SimpCalcPanel();
        // gets the frames insets
        Insets frameInsets = getInsets();
        // calculates panel size
        int frameWidth = p.getWidth()
                + (frameInsets.left + frameInsets.right);
        int frameHeight = p.getHeight()
                + (frameInsets.top + frameInsets.bottom);
        // sets the frame's size
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        // turns off the layout options
        setLayout(null);
        // adds the panel to the frame
        add(p);
        // adjusts the window to meet its new preferred size
        pack();
        // shows the frame
        setVisible(true);
    }

}
