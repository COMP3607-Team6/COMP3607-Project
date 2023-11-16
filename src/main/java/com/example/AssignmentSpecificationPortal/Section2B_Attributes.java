package com.example.AssignmentSpecificationPortal;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

// import org.w3c.dom.events.MouseEvent;

public class Section2B_Attributes extends JPanel {
    private DefaultListModel<String> attributeListModel;
    private JList<String> attributeList;
    private JTextField attributeNameField;
    private JTextField objNameField;
    private JComboBox<String> attributeTypeComboBox;
    private JComboBox<String> attAccessComboBox;
    private JButton saveButton;
    // private JButton closeButton;
    private CardLayout cardLayout;
    private JButton backButton;
    private JButton nextButton;
    private JPanel attributePanel;
    private JCheckBox instanceCheckBox;
    private JButton removeAttButton;
    private ArrayList<AttributeInformation> allAttributes;
    private JLabel prompt;
    private JComboBox selectedClassComboBox;
    private ArrayList<ClassInformation> classes;
    private JPanel selectedClassPanel;
    private JLabel selectedClassLabel;
    private int selectedClassIndex;
    private JButton loadClassesButton;

    public Section2B_Attributes(CardLayout layout, ArrayList<ClassInformation> classes) {
        this.cardLayout = layout;
        this.classes = classes;
        allAttributes = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        prompt = new JLabel("Attributes");
        prompt.setFont(new Font("Arial", Font.ITALIC, 22));
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);

        selectedClassPanel = new JPanel();
        selectedClassPanel.setLayout(new FlowLayout());
        selectedClassLabel = new JLabel("Class:");
        loadClassesButton = new JButton("Load classes");
        // selectedClassComboBox = new JComboBox<String>(new String[]{"choose class"});
        selectedClassComboBox = new JComboBox<String>();

        updateSelectedClassComboBox();

        selectedClassPanel.add(selectedClassLabel);
        selectedClassPanel.add(selectedClassComboBox);
        selectedClassPanel.add(loadClassesButton);

        if (selectedClassComboBox.getItemCount() == 0) {
            selectedClassComboBox.setVisible(false);
        }

        add(prompt);
        add(selectedClassPanel);
        add(createAttributePanel());
    }

    private JPanel createAttributePanel() {

        JPanel HoldPanel = new JPanel();
        HoldPanel.setLayout(new GridLayout(2, 1));

        attributePanel = new JPanel();
        attributePanel.setLayout(new FlowLayout());

        attributeListModel = new DefaultListModel<>();
        attributeList = new JList<>(attributeListModel);

        instanceCheckBox = new JCheckBox("Instance Variable");

        attributeNameField = new JTextField(20);
        objNameField = new JTextField(10);
        attributeNameField.setText("");
        objNameField.setText("");

        attributeTypeComboBox = new JComboBox<>(
                new String[] { "String", "int", "double", "boolean", "object", "char" });
        attAccessComboBox = new JComboBox<>(new String[] { "public", "private", "protected" });

        saveButton = new JButton("Save");
        removeAttButton = new JButton("Remove Selected Attribute");

        // attributePanel.add(selectedClassComboBox);
        attributePanel.add(attAccessComboBox);
        attributePanel.add(attributeTypeComboBox);
        attributePanel.add(objNameField);
        objNameField.setVisible(false);
        attributePanel.add(instanceCheckBox);
        attributePanel.add(attributeNameField);
        attributePanel.add(saveButton);
        // attributePanel.add(closeButton);
        attributePanel.add(removeAttButton);

        HoldPanel.add(attributePanel);
        HoldPanel.add(new JScrollPane(attributeList));

        // Listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String attributeName = attributeNameField.getText();
                String objName = objNameField.getText();
                String attributeType = (String) attributeTypeComboBox.getSelectedItem();
                String attAccessType = (String) attAccessComboBox.getSelectedItem();
                boolean isSelected = instanceCheckBox.isSelected();
                String attributeInfo = "";

                if (attributeName.isEmpty()) {
                    return;
                } else if ("object".equals(attributeType) && objName.isEmpty()) {
                    return;
                } else if ("object".equals(attributeType) && isSelected) {
                    attributeInfo = attAccessType + " static " + objName + " " + attributeName;
                } else if ("object".equals(attributeType)) {
                    attributeInfo = attAccessType + " " + objName + " " + attributeName;
                } else if (isSelected) {
                    attributeInfo = attAccessType + " static " + attributeType + " " + attributeName;
                } else {
                    attributeInfo = attAccessType + " " + attributeType + " " + attributeName;
                }

                attributeListModel.addElement(attributeInfo);

                // Create an instance of AttributeInformation and add it to the ArrayList
                AttributeInformation attribute = new AttributeInformation(attAccessType, isSelected ? "static" : "",
                        attributeType, attributeName, objName);
                allAttributes.add(attribute);
                classes.get(selectedClassIndex).addAttribute(attribute);

                attributeNameField.setText("");
                objNameField.setText("");
                instanceCheckBox.setSelected(false);

                // getAllClasses();
                // getAllAttributes();
            }
        });

        attributeTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("object".equals(attributeTypeComboBox.getSelectedItem())) {
                    objNameField.setVisible(true);
                } else {
                    objNameField.setVisible(false);
                }
            }
        });

        removeAttButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = attributeList.getSelectedIndex();
                if (selectedClassIndex >= 0 && selectedClassIndex < classes.size()) {
                    ClassInformation selectedClass = classes.get(selectedClassIndex);
                    if (index != -1) {
                        attributeListModel.remove(index);
                        allAttributes.remove(index);
                        selectedClass.getAttributes().remove(index);
                    }
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }

            }
        });

        loadClassesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedClassComboBox();
                selectedClassComboBox.setVisible(true);
            }
        });

        selectedClassComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassIndex = selectedClassComboBox.getSelectedIndex();

                if (selectedClassIndex >= 0 && selectedClassIndex < classes.size()) {
                    ClassInformation selectedClass = classes.get(selectedClassIndex);
                    attributeListModel.clear();

                    for (AttributeInformation attribute : selectedClass.getAttributes()) {
                        attributeListModel.addElement(attribute.toString());
                    }
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }
            }
        });

        return HoldPanel;
    }

    private void updateSelectedClassComboBox() {
        selectedClassComboBox.removeAllItems();
        for (ClassInformation c : classes) {
            String className = c.getClassName();
            selectedClassComboBox.addItem(className);
        }
    }

    private ArrayList<AttributeInformation> getAllAttributes() {
        for (ClassInformation classInfo : classes) {
            System.out.println(classInfo.toString());
            System.out.println("Attributes:");

            for (AttributeInformation attributeInfo : classInfo.getAttributes()) {
                System.out.println(attributeInfo.toString());
                System.out.println("-----------------------------------------");
            }
        }

        return allAttributes;
    }

    private ArrayList<ClassInformation> getAllClasses() {
        for (ClassInformation classInfo : classes) {
            System.out.println(classInfo.toString());
            System.out.println("-----------------------------------------");
        }

        return classes;
    }
}