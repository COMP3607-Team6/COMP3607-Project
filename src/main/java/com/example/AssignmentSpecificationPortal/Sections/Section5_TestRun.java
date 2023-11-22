package com.example.AssignmentSpecificationPortal.Sections;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.example.AutomatedJudgeSystem;

//
/* This class creates the Layout for the Test Run Panel(Section 5) of the GUI where the user runs desired tests
*/
public class Section5_TestRun extends JPanel {
    private JButton runTestsButton;
    private JButton folderButton;

    public Section5_TestRun(AutomatedJudgeSystem system) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        runTestsButton = new JButton("Run Tests");
        add (runTestsButton);

        folderButton = new JButton("Open Result Folder");
        add (folderButton);
        folderButton.setVisible(false);


        runTestsButton.addActionListener(e -> {
            AutomatedJudgeSystem.onGUIRunTestsButtonPressed();
            folderButton.setVisible(true);
            
        });

        
        folderButton.addActionListener(e -> {
            AutomatedJudgeSystem.onGUIFolderButtonPressed();
            
        });


    }

    
    
}
