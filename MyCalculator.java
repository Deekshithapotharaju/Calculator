import java.awt.*;
import java.awt.event.*;
abstract class Calculator implements ActionListener {
    protected int c, n;
    protected String s1, s2, s3, s4, s5;
    protected Frame f;
    protected Button[] buttons;
    protected TextField tf;
    protected Panel p;
    protected GridLayout g;

    public Calculator() {
        f = new Frame("Calculator");
        p = new Panel();
        tf = new TextField(20);
        buttons = new Button[17];

        // Set styling for the text field
        tf.setFont(new Font("Arial", Font.BOLD, 24));
        tf.setBackground(Color.WHITE);
        tf.setForeground(Color.BLACK);

        f.setLayout(new BorderLayout());
        f.add(tf, BorderLayout.NORTH);
        
        g = new GridLayout(4, 4, 10, 10);
        p.setLayout(g);
        f.add(p, BorderLayout.CENTER);

        createButtons();
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.setSize(300, 300);
        f.setVisible(true);
    }

    protected void createButtons() {
        String[] labels = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "C", "=", "."};

        for (int i = 0; i < labels.length; i++) {
            buttons[i] = new Button(labels[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 18));
            buttons[i].setBackground(new Color(200, 200, 255)); 
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setPreferredSize(new Dimension(50, 50)); 
            buttons[i].addActionListener(this);
            p.add(buttons[i]);
        }
    }
    public abstract void actionPerformed(ActionEvent e);
}

public class MyCalculator extends Calculator {

    public MyCalculator() {
        super(); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button source = (Button) e.getSource();
        String label = source.getLabel();

        if ("0123456789".contains(label)) {
            s3 = tf.getText();
            s4 = label;
            s5 = s3 + s4;
            tf.setText(s5);
        } else if (label.equals("+")) {
            s1 = tf.getText();
            tf.setText("");
            c = 1;
        } else if (label.equals("-")) {
            s1 = tf.getText();
            tf.setText("");
            c = 2;
        } else if (label.equals("*")) {
            s1 = tf.getText();
            tf.setText("");
            c = 3;
        } else if (label.equals("/")) {
            s1 = tf.getText();
            tf.setText("");
            c = 4;
        } else if (label.equals("C")) {
            tf.setText("");
        } else if (label.equals("=")) {
            s2 = tf.getText();
            calculateResult();
        } else if (label.equals(".")) {
            s3 = tf.getText();
            s4 = label;
            s5 = s3 + s4;
            tf.setText(s5);
        }
    }
    private void calculateResult() {
        try {
            int num1 = Integer.parseInt(s1);
            int num2 = Integer.parseInt(s2);

            switch (c) {
                case 1:
                    n = num1 + num2;
                    break;
                case 2:
                    n = num1 - num2;
                    break;
                case 3:
                    n = num1 * num2;
                    break;
                case 4:
                    if (num2 != 0) {
                        n = num1 / num2;
                    } else {
                        tf.setText("Error");
                        return;
                    }
                    break;
                default:
                    n = 0;
            }
            tf.setText(String.valueOf(n));
        } catch (NumberFormatException ex) {
            tf.setText("Error");
        }
    }

    public static void main(String[] abc) {
        MyCalculator calc = new MyCalculator();
    }
}
