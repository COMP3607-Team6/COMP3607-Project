package com.example.AssignmentSpecificationPortal.Sections;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.example.AssignmentSpecificationPortal.TestInfo;
import com.example.AssignmentSpecificationPortal.Tests.AccessorTypeTest;
import com.example.AssignmentSpecificationPortal.Tests.FinalTest;
import com.example.AssignmentSpecificationPortal.Tests.HierarchyTest;
import com.example.AssignmentSpecificationPortal.Tests.NamingConventionTest;
import com.example.AssignmentSpecificationPortal.Tests.StaticTest;
import com.example.AssignmentSpecificationPortal.Tests.ValueTest;
import com.example.AssignmentSpecificationPortal.Tests.TypeTest;

//
/* This class prompts user to select tests. Creates the Panel that holds the relevant Test Tabbed Panes selected in Section (3)
*/
public class Section4_Tests extends JPanel{

    private JTabbedPane section4TabbedPane;

    public Section4_Tests() {
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    public void updateSelectedTests(TestInfo selectedTests) {
        System.out.println("Received Updated Selected Tests: " + selectedTests);

        if (section4TabbedPane != null) {
            remove(section4TabbedPane);
        }

        section4TabbedPane = new JTabbedPane();
        
        for (int i = 0; i < selectedTests.getSize(); i++) {
            String name = selectedTests.getTestName(i);
            String description = selectedTests.getTestDescription(i);

            if (name == "Naming Convention Test") {
                section4TabbedPane.addTab(name, new NamingConventionTest(description));
            } else if (name == "Hierarchy Test") {
                section4TabbedPane.addTab(name, new HierarchyTest(description));
            } else if (name == "AccessorType Test") {
                section4TabbedPane.addTab(name, new AccessorTypeTest(description));
            } else if (name == "Value Test") {
                section4TabbedPane.addTab(name, new ValueTest(description));
            } else if (name == "Type Test") {
                 section4TabbedPane.addTab(name, new TypeTest(description));
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
