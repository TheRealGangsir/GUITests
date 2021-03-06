
import javax.swing.*;

/**
 * Created on 12/5/2016, 2:16 PM
 *
 * @author Noah Morton
 * Tully 7th period
 * Part of project FinalCalculator
 */
public class FinalCalcPanel extends JPanel {

    private final JLabel[] labels = new JLabel[9];
    private final JTextField[] textFields = new JTextField[8];
    private final JLabel answerLabel = new JLabel("");
    private JComboBox<String> termsAmount;

    public FinalCalcPanel() {
        setLayout(null);
        setSize(350, 650);

        //LABELS CODE ------------------
        String[] labelStrings = {"Total term weight: ", "Final Weight: ", "Number of terms: ", "Grade wanted: ", "Term 1 grade: ",
            "Term 2 grade: ", "Term 3 grade: ", "Term 4 grade: ", "Term 5 grade: "};
        for (int i = 0; i < labels.length; i++) { //init and set the label text for all labels
            labels[i] = new JLabel(labelStrings[i]); //defines them off of the array
            labels[i].setBounds(10, (i * 50) + 20, 150, 40);
            labels[i].setEnabled(true);
            add(labels[i]);
        }
        //code for answerLabel
        answerLabel.setEnabled(true);
        answerLabel.setBounds(20, 600, 300, 40);
        add(answerLabel);

        //TEXT FIELDS CODE ----------------
        for (int i = 0; i < textFields.length; i++) { //init and set the label text for all text fields
            if (i >= 2) { //this if/else is used so the for loop skips a space for the combo box.
                textFields[i] = new JTextField();
                textFields[i].setEditable(true);
                textFields[i].setBounds(150, (i * 50) + 70, 150, 40);
                textFields[i].setEnabled(false);
                add(textFields[i]);
            } else {
                textFields[i] = new JTextField();
                textFields[i].setEditable(true);
                textFields[i].setBounds(150, (i * 50) + 20, 150, 40);
                textFields[i].setEnabled(false);
                add(textFields[i]);
            }
            textFields[i].setText("0"); //set them all to 0 to start
        }
        for (int i = 0; i < 4; i++) {//the first box and other boxes should start enabled, because the dropdown starts on 1
            textFields[i].setEnabled(true);
        }

        //COMBO BOX CODE ------------------------
        String[] choices = new String[6];
        for (int i = 1; i < choices.length; i++) { //init list of choices
            choices[i] = "" + i;
        }

        termsAmount = new JComboBox<>(choices);
        termsAmount.setBounds(150, 115, 130, 40);
        termsAmount.setEnabled(true);
        termsAmount.setEditable(false);
        termsAmount.setSelectedItem("1"); //sets 1 term to be default

        termsAmount.addActionListener(e -> {
            if (termsAmount.getSelectedIndex() == -1) {
                termsAmount.setSelectedIndex(1); //sanity check
            }
            for (int i = 0; i < textFields.length; i++) { //changes the text fields enable status based on selected terms
                if (i > (2 + termsAmount.getSelectedIndex())) {
                    textFields[i].setEnabled(false);
                    textFields[i].setText("");
                } else {
                    textFields[i].setEnabled(true);
                }
            }
        });
        add(termsAmount);

        //BUTTONS CODE -----------------------------
        JButton calcButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear");
        calcButton.setEnabled(true);
        calcButton.setBounds(20, 500, 200, 30);
        add(calcButton);
        clearButton.setEnabled(true);
        clearButton.setBounds(20, 550, 200, 30);
        add(clearButton);

        calcButton.addActionListener(e -> answerLabel.setText("The score you need is: " + doCalc(textFields)));

        clearButton.addActionListener(e -> { //reset all editable fields
            for (JTextField textField : textFields) {
                textField.setText("0");
            }
            termsAmount.setSelectedItem("1");
            answerLabel.setText("");
        });

    }

    /**
     * Calculates the result based on the text of the textFields, then returns it.
     *
     * @param textFields An array of all the text fields.
     * @return double - The result of the calculation.
     */
    private double doCalc(JTextField[] textFields) {
        double termAverage = 0;
        for (int i = 3; i <= (termsAmount.getSelectedIndex()) + 2; i++) { //only calculate based on selected # of terms
            termAverage += Double.parseDouble(textFields[i].getText());
        }
        termAverage /= termsAmount.getSelectedIndex(); //divide the total by the amount of terms.
        //calculate the grade needed on the final to get the requested score
        double semester = Double.parseDouble(textFields[2].getText());
        double finalWeight = Double.parseDouble(textFields[1].getText()) / 100;
        double termWeight = Double.parseDouble(textFields[0].getText()) / 100;

        return (semester - (termAverage * termWeight)) / finalWeight;

    }

}
