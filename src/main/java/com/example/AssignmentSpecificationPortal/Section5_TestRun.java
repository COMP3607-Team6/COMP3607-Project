package com.example.AssignmentSpecificationPortal;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.security.auth.callback.Callback;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.AutomatedJudgeSystem;

public class Section5_TestRun extends JPanel {

    private CardLayout cardLayout;
    private ArrayList<ClassInformation> classes;
    private JButton runTestsButton;
    // private AutomatedJudgeSystem system;

    public Section5_TestRun(CardLayout layout, AutomatedJudgeSystem system) {
        this.cardLayout = layout;
        // this.system = system;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        runTestsButton = new JButton("Run Tests");
        add (runTestsButton);

        runTestsButton.addActionListener(e -> {
            AutomatedJudgeSystem.onGUIRunTestsButtonPressed();
            
        });

    }

    
    
}