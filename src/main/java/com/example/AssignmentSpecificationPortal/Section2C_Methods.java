package com.example.AssignmentSpecificationPortal;

import java.awt.CardLayout;
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

public class Section2C_Methods extends JPanel {
    private CardLayout cardLayout;
    private JLabel prompt;
    private JPanel selectedClassPanel;
    private JLabel selectedClassLabel;
    private JButton loadClassesButton;
    private JComboBox<String> selectedClassComboBox;
    private ArrayList<ClassInformation> classes;

    public Section2C_Methods(CardLayout layout, ArrayList<ClassInformation> classes) {
        this.cardLayout = layout;
        this.classes = classes;
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        prompt = new JLabel("Methods");
        prompt.setFont(new Font("Arial", Font.ITALIC, 22));
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        

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

        add(prompt);
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

