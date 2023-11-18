package com.example.AssignmentSpecificationPortal;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Section3_TestSelection extends JPanel {

    private CardLayout cardLayout;
    private JLabel prompt1;
    private JLabel prompt2;
    private Section4_Tests section4;
    private JButton addTestsButton;
    private static ArrayList<String> selectedTests;
    private static JPanel checkBoxPanel;

    public Section3_TestSelection(CardLayout layout, Section4_Tests section4) {
        this.cardLayout = layout;
        this.section4 = section4;
        selectedTests = new ArrayList<String>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel centerAlignPanel = new JPanel();
        centerAlignPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Vertical layout

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel promptPanel = new JPanel();
        promptPanel.setLayout(new BoxLayout(promptPanel, BoxLayout.Y_AXIS));
        promptPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        prompt1 = new JLabel("Tests");
        prompt1.setFont(new Font("Arial", Font.ITALIC, 20));
        prompt1.setAlignmentX(Component.CENTER_ALIGNMENT);

        prompt2 = new JLabel("Please select the categories of tests you want to run:");
        prompt2.setFont(new Font("Arial", Font.PLAIN, 15));
        prompt2.setAlignmentX(Component.CENTER_ALIGNMENT);

        promptPanel.add(prompt1);
        promptPanel.add(prompt2);

        String[] testNames = {
                "Naming Convention Test",
                "Hierarchy Test",
                "AccessorType Test",
                "Value Test",
                "Type Test",
                "Final Test",
                "Static Test"
        };

        String[] testDescriptions = {
                "insert Naming Convention Test description here",
                "insert Hierarchy Test description here",
                "insert AccessorType Test description here",
                "insert Value Test description here",
                "insert Type Test description here",
                "insert Final Test description here",
                "insert Static Test description here"
        };

        ArrayList<JPanel> panels = new ArrayList<>();

        mainPanel.add(promptPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 60)));

        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < testNames.length; i++) {
            JPanel panel = createPanelWithCheckboxAndLabel(testNames[i], testDescriptions[i]);
            panels.add(panel);
            checkBoxPanel.add(panel);
        }

        mainPanel.add(checkBoxPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 60)));

        addTestsButton = new JButton("Add Tests");
        addTestsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addTestsButton.addActionListener(e -> {
            ArrayList<String> testsSelected = getCheckedCheckboxes(checkBoxPanel.getComponents());
            // System.out.println("Checked Checkboxes:");
            selectedTests.clear();
            for (String test : testsSelected) {
                // System.out.println(test);
                selectedTests.add(test);
            }
            if (section4 != null) {
                section4.updateSelectedTests(selectedTests);
            }
        });

        mainPanel.add(addTestsButton);
        centerAlignPanel.add(mainPanel);
        add(centerAlignPanel);
    }

    private static JPanel createPanelWithCheckboxAndLabel(String test, String testDescription) {
        JCheckBox checkBox = new JCheckBox(test);
        checkBox.setPreferredSize(new Dimension(300, 20));
        checkBox.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel label = new JLabel(testDescription);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(checkBox);
        panel.add(label);

        return panel;
    }

    private static ArrayList<String> getCheckedCheckboxes(Component[] components) {
        ArrayList<String> checkedCheckboxes = new ArrayList<>();
        for (Component component : components) { // loop through panels
            if (component instanceof JPanel) {
                // System.out.println("cb panel reached");
                for (Component innerComponent : ((JPanel) component).getComponents()) { // loop through panel's kids -
                                                                                        // checkbox and label
                    if (innerComponent instanceof JCheckBox) {
                        // System.out.println("cb reached");
                        JCheckBox checkBox = (JCheckBox) innerComponent;
                        if (checkBox.isSelected()) {
                            checkedCheckboxes.add(checkBox.getText());
                        }
                    }
                }
            }
        }
        return checkedCheckboxes;
    }

    public static ArrayList<String> getSelectedTests() {
        return selectedTests;
    }
    
}