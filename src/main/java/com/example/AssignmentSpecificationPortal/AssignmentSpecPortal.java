package com.example.AssignmentSpecificationPortal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

import com.example.AssignmentSpecification;
import com.example.AutomatedJudgeSystem;
import com.example.TestCaseManager;

public class AssignmentSpecPortal {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private String currentCard; 
    private Section1 section1;
     private Section2A_Classes section2a;
     private Section2B_Attributes section2b;
     private Section2C_Methods section2c;
    private JTabbedPane section2TabbedPane;
    private JButton backButton;
    private JButton nextButton;
    
    private Section3_TestSelection section3;
    private Section4_Tests section4;
    private AutomatedJudgeSystem system;
    private Section5_TestRun section5;
    private Section2A_ClassesNew section2new;
    private Section2B_AttributesNew section2bnew;
    private Section2C_MethodsNew section2cnew;

public AssignmentSpecPortal(AutomatedJudgeSystem system,AssignmentSpecification asSpec) {
    
        this.system = system;
        frame = new JFrame("Assignment Specification Portal");
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        section2TabbedPane = new JTabbedPane();

        
        
        //section2new = new Section2A_ClassesNew(cardLayout);
        //section2bnew = new Section2B_AttributesNew(cardLayout);
        //section2cnew = new Section2C_MethodsNew(cardLayout);
        section1 = new Section1(cardLayout,asSpec,system);
         section2a = new Section2A_Classes(cardLayout);
         section2b = new Section2B_Attributes(cardLayout);
         section2c = new Section2C_Methods(cardLayout);
        section4 = new Section4_Tests(cardLayout);
        section3 = new Section3_TestSelection(cardLayout, section4);
        section5 = new Section5_TestRun(cardLayout, system);

        section2TabbedPane.addTab("Classes", section2a);
        section2TabbedPane.addTab("Attributes", section2b);
        section2TabbedPane.addTab("Methods", section2c);
        // section2TabbedPane.addTab("Classes", section2a);
        // section2TabbedPane.addTab("Attributes", section2b);
        // section2TabbedPane.addTab("Methods", section2c);

        cardPanel.add(section1, "Section 1");

        cardPanel.add(section2TabbedPane, "Section 2");
        cardPanel.add(section3, "Section 3");
        cardPanel.add(section4, "Section 4");
        cardPanel.add(section5, "Section 5");
        // cardPanel.add(section3, "Section 3");
       

        // Add padding around the contents of the frame
        cardPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        frame.add(cardPanel);

        backButton = new JButton("Back");
        nextButton = new JButton("Next");
       
        backButton.setPreferredSize(new Dimension(100, 30));
        nextButton.setPreferredSize(new Dimension(100, 30));

        // Panel to hold the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        currentCard = "Section 1";
        updateButtonVisibility(buttonPanel);

        // ActionListener for the back button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(cardPanel);
                currentCard = getLastCardName(currentCard);
                updateButtonVisibility( buttonPanel);
                
            }
        });

        // ActionListener for the next button
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(cardPanel);
                currentCard = getNextCardName(currentCard);
                updateButtonVisibility( buttonPanel);
            }
        });

        frame.add(buttonPanel, BorderLayout.SOUTH);
        updateButtonVisibility( buttonPanel);

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

    private void updateButtonVisibility(JPanel buttonPanel) {
        Component[] components = buttonPanel.getComponents();
        JButton button1 = (JButton) components[0];
        JButton button2 = (JButton) components[1];

        button1.setVisible(!"Section 1".equals(currentCard));
        button2.setVisible(!"Section 5".equals(currentCard)); 
    }

    private String getNextCardName(String currentCard) {
        switch (currentCard) {
            case "Section 1":
                return "Section 2";
            case "Section 2":
                return "Section 3";
            case "Section 3":
                return "Section 4";
            case "Section 4":
                return "Section 5";
            case "Section 5":
                return "Section 1";
            default:
                return currentCard;
        }
    }

    private String getLastCardName(String currentCard) {
        switch (currentCard) {
            case "Section 1":
                return "Section 5";
            case "Section 5":
                return "Section 4";
            case "Section 4":
                return "Section 3";
            case "Section 3":
                return "Section 2";
            case "Section 2":
                return "Section 1";
            default:
                return currentCard;
        }
    }
    

}