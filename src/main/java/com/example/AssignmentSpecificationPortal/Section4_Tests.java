package com.example.AssignmentSpecificationPortal;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.example.AssignmentSpecificationPortal.Tests.*;

public class Section4_Tests extends JPanel{

    private JTabbedPane section4TabbedPane;
    private CardLayout cardLayout;
    // private JPanel mainPanel;

    public Section4_Tests(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    public void updateSelectedTests(ArrayList<String> selectedTests) {
        System.out.println("Received Updated Selected Tests: " + selectedTests);

        if (section4TabbedPane != null) {
            remove(section4TabbedPane); // Remove the existing tabbed pane
        }

        section4TabbedPane = new JTabbedPane();

        /*private Section2A_Classes section2a;
        section2a = new Section2A_Classes(cardLayout);
        section2TabbedPane.addTab("Classes", section2a); */
        
        for (String test : selectedTests) {

            // need to do like an if to decide which test gui to add
            if (test == "Naming Convention Test") {
                BaseTest nameTest;
                nameTest = new NamingConventionTest();
                section4TabbedPane.addTab(test, nameTest);
            // } else if (test == "Hierarchy Test") {
            //     section4TabbedPane.addTab(test, new HierarchyTest());
             } else if (test == "AccessorType Test") {
                BaseTest ATest;
                ATest = new AccessorTypeTest();
                section4TabbedPane.addTab(test, ATest);
                //section4TabbedPane.addTab(test, new AccessorTypeTest());
            } else if (test == "Final Test") {
                 section4TabbedPane.addTab(test, new FinalTest());
            } else if (test == "Static Test") {
                section4TabbedPane.addTab(test, new StaticTest());  
            } else { // default for now
                section4TabbedPane.addTab(test, new BaseTest());
            }
        }

        add(section4TabbedPane);
    }
}
