package com.example.AssignmentSpecificationPortal.Tests;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

// import com.example.AssignmentSpecificationPortal.AttributeInformation;
import com.example.AssignmentSpecificationPortal.ClassInformation;

public class AccessorTypeTest extends JPanel {

    private ArrayList<ClassInformation> classes;
    private JLabel testDescription;
    private JPanel selectedClassPanel;
    private JLabel selectedClassLabel;
    private JButton loadClassesButton;
    private JComboBox<String> selectedClassComboBox;

    public AccessorTypeTest(ArrayList<ClassInformation> classes) {
        this.classes = classes;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        testDescription = new JLabel("accessor type test description. short one line description of test");
        testDescription.setFont(new Font("Arial", Font.ITALIC, 22));
        testDescription.setAlignmentX(Component.LEFT_ALIGNMENT);

        selectedClassPanel = new JPanel();
        selectedClassPanel.setLayout(new FlowLayout());
        selectedClassLabel = new JLabel("Class:");
        loadClassesButton = new JButton("Load classes");
        // selectedClassComboBox = new JComboBox<String>(new String[]{"choose class"});
        selectedClassComboBox = new JComboBox<String>();

        for (ClassInformation c : classes) {
            String className = c.getClassName();
            selectedClassComboBox.addItem(className);
        }

        selectedClassPanel.add(selectedClassLabel);
        selectedClassPanel.add(selectedClassComboBox);
        selectedClassPanel.add(loadClassesButton);

        if (selectedClassComboBox.getItemCount() == 0) {
            selectedClassComboBox.setVisible(false);
        }

        add(testDescription);
        add(selectedClassPanel);

        loadClassesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedClassComboBox();
                selectedClassComboBox.setVisible(true);
            }
        });

        selectedClassComboBox.addActionListener(new ActionListener() {
            private int selectedClassIndex;

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassIndex = selectedClassComboBox.getSelectedIndex();

                if (selectedClassIndex >= 0 && selectedClassIndex < classes.size()) {
                    ClassInformation selectedClass = classes.get(selectedClassIndex);
                    // attributeListModel.clear();

                    // for (AttributeInformation attribute : selectedClass.getAttributes()) {
                    //     attributeListModel.addElement(attribute.toString());
                    // }
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }
            }
        });
    }

    private void updateSelectedClassComboBox() {
        selectedClassComboBox.removeAllItems();
        for (ClassInformation c : classes) {
            String className = c.getClassName();
            selectedClassComboBox.addItem(className);
        }
    }
    
}
