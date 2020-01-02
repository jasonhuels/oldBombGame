package a6;

/**
 * Created by jason_000 on 5/30/2016.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Demonstrate a simple Swing GUI
 *
 * @author Cara Tang
 * @version 2015.05.25
 */
public class SimpleWindow extends JFrame
{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 360;

    private JTextField nameTextField;

    /**
     * Create SimpleWindow
     */
    public SimpleWindow()
    {
        super("Simple Window");
        makeFrame();
    }

    /**
     * Set up SimpleWindow frame
     */
    private void makeFrame()
    {
        setSize(WIDTH, HEIGHT);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createContents(getContentPane());

        setVisible(true);
    }

    /**
     * Create contents for SimpleWindow
     */
    private void createContents(Container contentPane)
    {
        JLabel label = new JLabel("Hi! I'm a label!");
        JLabel nameLabel = new JLabel("What's your name?");
        nameTextField = new JTextField(10);
        JButton helloButton = new JButton("Say Hello");
        helloButton.addActionListener(new ButtonListener());

        contentPane.add(label);
        contentPane.add(nameLabel);
        contentPane.add(nameTextField);
        contentPane.add(helloButton);
    }

    /**
     * Listen for button click and display hello message
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(null, "Hello, " + nameTextField.getText() + "!");
        }
    }

    /**
     * Launch SimpleWindow GUI application
     */
    public static void main(String[] args)
    {
        new SimpleWindow();
    }
}
