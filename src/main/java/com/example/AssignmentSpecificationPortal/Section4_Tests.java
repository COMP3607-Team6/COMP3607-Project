package com.example.AssignmentSpecificationPortal;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.example.AssignmentSpecificationPortal.Tests.NamingConventionTest;

public class Section4_Tests extends JPanel{

    private JTabbedPane section4TabbedPane;
    private ArrayList<ClassInformation> classes;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Section4_Tests(CardLayout cardLayout, ArrayList<ClassInformation> classes) {
        this.cardLayout = cardLayout;
        this.classes = classes;
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    public void updateSelectedTests(ArrayList<String> selectedTests) {
        System.out.println("Received Updated Selected Tests: " + selectedTests);

        if (section4TabbedPane != null) {
            remove(section4TabbedPane); // Remove the existing tabbed pane
        }

        section4TabbedPane = new JTabbedPane();
        
        for (String test : selectedTests) {
            NamingConventionTest nametest = new NamingConventionTest(classes);
            section4TabbedPane.addTab(test, nametest);

            // need to do like an if to decide which test gui to add
        }

        add(section4TabbedPane);
    }
}
