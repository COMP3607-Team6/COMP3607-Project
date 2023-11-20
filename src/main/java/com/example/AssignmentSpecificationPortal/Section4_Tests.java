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
    
    public void updateSelectedTests(TestInfo selectedTests) {
        System.out.println("Received Updated Selected Tests: " + selectedTests);

        if (section4TabbedPane != null) {
            remove(section4TabbedPane); // Remove the existing tabbed pane
        }

        section4TabbedPane = new JTabbedPane();

        /*private Section2A_Classes section2a;
        section2a = new Section2A_Classes(cardLayout);
        section2TabbedPane.addTab("Classes", section2a); */
        
        for (int i = 0; i < selectedTests.getSize(); i++) {
            String name = selectedTests.getTestName(i);
            String description = selectedTests.getTestDescription(i);

            if (name == "Naming Convention Test") {
                section4TabbedPane.addTab(name, new NamingConventionTest(description));
            } else if (name == "Hierarchy Test") {
                section4TabbedPane.addTab(name, new HierarchyTest(description));
            } else if (name == "AccessorType Test") {
                section4TabbedPane.addTab(name, new AccessorTypeTest(description));
            } else if (name == "Final Test") {
                section4TabbedPane.addTab(name, new FinalTest(description));
            } else if (name == "Static Test") {
                section4TabbedPane.addTab(name, new StaticTest(description));
            } else { // default for now
                section4TabbedPane.addTab(name, new NamingConventionTest(description));
            }
        }

        add(section4TabbedPane);
    }
}
