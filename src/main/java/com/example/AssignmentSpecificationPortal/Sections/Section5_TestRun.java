package com.example.AssignmentSpecificationPortal.Sections;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.example.AutomatedJudgeSystem;

/**
* This class creates the Layout for the Test Run Panel(Section 5) of the GUI where the user runs desired tests
*/
public class Section5_TestRun extends JPanel {
    private JButton runTestsButton;
    private JButton folderButton;
    private JButton ungradedFolderButton;
    // private AutomatedJudgeSystem system;

    public Section5_TestRun(AutomatedJudgeSystem system) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        runTestsButton = new JButton("Run Tests");
        add (runTestsButton);

        folderButton = new JButton("Open Result Folder");
        add (folderButton);
        folderButton.setVisible(false);

        ungradedFolderButton = new JButton("Open Ungraded Folder");
        add (ungradedFolderButton);
        ungradedFolderButton.setVisible(false);

        runTestsButton.addActionListener(e -> {
            AutomatedJudgeSystem.onGUIRunTestsButtonPressed();
            folderButton.setVisible(true);
            ungradedFolderButton.setVisible(true);
        });
        
        folderButton.addActionListener(e -> {
            AutomatedJudgeSystem.onGUIFolderButtonPressed();
            
        });

        ungradedFolderButton.addActionListener(e -> {
            AutomatedJudgeSystem.onGUIUnGradedFolderButtonPressed();
            
        });


    }

    
    
}
