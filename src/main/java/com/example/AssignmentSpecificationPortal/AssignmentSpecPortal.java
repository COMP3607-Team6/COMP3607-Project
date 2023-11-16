package com.example.AssignmentSpecificationPortal;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class AssignmentSpecPortal {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Section1 section1;
    private Section2 section2;
    private Section3 section3;
    private Section4 section4;//

    public AssignmentSpecPortal() {
        frame = new JFrame("Assignment Specification Portal");
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        section1 = new Section1(cardLayout);
        section2 = new Section2(cardLayout);
        section3 = new Section3(cardLayout);
        section4 = new Section4(cardLayout);//

        cardPanel.add(section1, "Section 1");
        cardPanel.add(section2, "Section 2");
        cardPanel.add(section3, "Section 3");
        cardPanel.add(section4, "Section 4");//

        // Add padding around the contents of the frame
        cardPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        frame.add(cardPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setResizable(false);

        // center the frame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;

        frame.setLocation(x, y); 
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AssignmentSpecPortal();
            }
        });
    }
}
