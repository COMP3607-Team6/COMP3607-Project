package com.example.AssignmentSpecificationPortal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

public class AssignmentSpecPortal {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Section1 section1;
    private Section2A_Classes section2a;
    private Section2B_Attributes section2b;
    private Section2C_Methods section2c;
    private JTabbedPane tabbedPane;
    private JButton backButton;
    private JButton nextButton;

public AssignmentSpecPortal(ArrayList<ClassInformation> classes) {
        frame = new JFrame("Assignment Specification Portal");
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        tabbedPane = new JTabbedPane();

        section1 = new Section1(cardLayout);
        section2a = new Section2A_Classes(cardLayout, classes);
        section2b = new Section2B_Attributes(cardLayout, classes);
        section2c = new Section2C_Methods(cardLayout, classes);
        
        tabbedPane.addTab("Classes", section2a);
        tabbedPane.addTab("Attributes", section2b);
        tabbedPane.addTab("Methods", section2c);

        cardPanel.add(section1, "Section 1");
        cardPanel.add(tabbedPane, "Section 2");
        // cardPanel.add(section2, "Section 2");
        // cardPanel.add(section3, "Section 3");

        // Add padding around the contents of the frame
        cardPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        frame.add(cardPanel);


        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");

        // ActionListener for the back button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(cardPanel);
            }
        });

        // ActionListener for the next button
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(cardPanel);
            }
        });

        // Panel to hold the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

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
                ArrayList<ClassInformation> classes = new ArrayList<ClassInformation>();
                AssignmentSpecPortal assignmentSpecPortal = new AssignmentSpecPortal(classes);
            }
        });
    }
}
