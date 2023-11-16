package com.example.AssignmentSpecificationPortal;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.example.AssignmentSpecificationPortal.Tests.*;

public class Section4_Tests extends JPanel{

    private JTabbedPane section4TabbedPane;
    private ArrayList<ClassInformation> classes;
    private CardLayout cardLayout;
    // private JPanel mainPanel;

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

            // need to do like an if to decide which test gui to add
            if (test == "Naming Convention Test") {
                section4TabbedPane.addTab(test, new NamingConventionTest(classes));
            } else if (test == "Hierarchy Test") {
                section4TabbedPane.addTab(test, new HierarchyTest(classes));
            } else if (test == "AccessorType Test") {
                section4TabbedPane.addTab(test, new AccessorTypeTest(classes));
            } else { // default for now
                section4TabbedPane.addTab(test, new NamingConventionTest(classes));
            }
        }

        add(section4TabbedPane);
    }
}