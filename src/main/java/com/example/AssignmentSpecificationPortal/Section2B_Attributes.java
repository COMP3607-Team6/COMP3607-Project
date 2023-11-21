package com.example.AssignmentSpecificationPortal;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
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

public class Section2B_Attributes extends JPanel {
     //
    /* This class creates the Layout for Attribute Input TabbedPane of the GUI
     */
    private DefaultListModel<String> attributeListModel;
    private JList<String> attributeList;
    private JTextField attributeNameField;
    private JTextField objNameField;
    private JComboBox<String> attributeTypeComboBox;
    private JComboBox<String> attAccessComboBox;
   // private JComboBox<String> isFinalComboBox;
    private JButton saveButton;
    // private JButton closeButton;
    private CardLayout cardLayout;
    private JButton backButton;
    private JButton nextButton;
    private JPanel inputPanel;
    private JCheckBox instanceCheckBox;
    private JCheckBox finalCheckBox;
    private JButton removeAttButton;
    private JLabel prompt;
    private JComboBox selectedClassComboBox;
    private JPanel selectedClassPanel;
    private JLabel selectedClassLabel;
    private int selectedClassIndex;
    private JButton loadClassesButton;
    private JPanel attributePanel;
    private JPanel promptPanel;

    public Section2B_Attributes(CardLayout layout) {
        this.cardLayout = layout;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        prompt = new JLabel("  Add attributes (for inputted classes) to be tested here.");
        promptPanel = new JPanel();
        promptPanel.setLayout(new BoxLayout(promptPanel, BoxLayout.X_AXIS));
        promptPanel.add(prompt);
        promptPanel.add(Box.createHorizontalGlue());

        selectedClassPanel = new JPanel();
        selectedClassPanel.setLayout(new FlowLayout());
        selectedClassLabel = new JLabel("Class:");
        loadClassesButton = new JButton("Load classes");
        selectedClassComboBox = new JComboBox<String>();

        updateSelectedClassComboBox();

        selectedClassPanel.add(selectedClassLabel);
        selectedClassPanel.add(selectedClassComboBox);
        selectedClassPanel.add(loadClassesButton);

        if (selectedClassComboBox.getItemCount() == 0) {
            selectedClassComboBox.setVisible(false);
        }

        add(promptPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(selectedClassPanel);
        add(createAttributePanel());
    }

    private JPanel createAttributePanel() {

        attributePanel = new JPanel();
        attributePanel.setLayout(new BoxLayout(attributePanel, BoxLayout.Y_AXIS));

        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        // inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Aligns components to the left within inputPanel


        attributeListModel = new DefaultListModel<>();
        attributeList = new JList<>(attributeListModel);

        instanceCheckBox = new JCheckBox("static");
        finalCheckBox = new JCheckBox("final");

        attributeNameField = new JTextField(20);
        attributeNameField.setText("");
        objNameField = new JTextField(10);
        objNameField.setText("");
        objNameField.setVisible(false);

        attributeTypeComboBox = new JComboBox<>(
                new String[] { "String", "int", "double", "boolean", "object", "char" });
        attAccessComboBox = new JComboBox<>(new String[] { "public", "private", "protected" });
      //  isFinalComboBox = new JComboBox<>(new String[] { "final", "-"});

        saveButton = new JButton("Save");
        removeAttButton = new JButton("Remove Selected Attribute");

        inputPanel.add(attAccessComboBox);
        inputPanel.add(instanceCheckBox);
        inputPanel.add(finalCheckBox);
        inputPanel.add(attributeTypeComboBox);
        inputPanel.add(objNameField);
        inputPanel.add(attributeNameField);
        inputPanel.add(saveButton);
        inputPanel.add(removeAttButton);
        
        // attributePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        attributePanel.add(inputPanel);
        attributePanel.add(new JScrollPane(attributeList));
        
        
        
        // attributePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        attachListeners();

        return attributePanel;
    }

    private void attachListeners() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String attributeName = attributeNameField.getText();
                String objName = objNameField.getText();
                //String finalv = (String) isFinalComboBox.getSelectedItem();
                boolean finalv = finalCheckBox.isSelected();
                String attributeType = (String) attributeTypeComboBox.getSelectedItem();
                String attAccessType = (String) attAccessComboBox.getSelectedItem();
                boolean isSelected = instanceCheckBox.isSelected();
                String attributeInfo = "";

                if (attributeName.isEmpty()) {
                    return;
                } else if ("object".equals(attributeType) && objName.isEmpty()) {
                    return;
                } 
                
                if(finalv==false){
                    if(isSelected==true){
                        if ("object".equals(attributeType)) {
                            attributeInfo = attAccessType +" " +objName + " " + attributeName;
                        } else {
                            attributeInfo = attAccessType + " " + attributeType + " " + attributeName;
                        } 
                    }
                    else{
                        if ("object".equals(attributeType)) {
                            attributeInfo = attAccessType + " static " + objName + " " + attributeName;
                        }else {
                            attributeInfo = attAccessType + " static " + attributeType + " " + attributeName;  
                        }
                    }
                }

                else{
                    if(isSelected==true){
                        if ("object".equals(attributeType)) {
                            attributeInfo = attAccessType +" final " +objName + " " + attributeName;
                        } else {
                            attributeInfo = attAccessType + " final " + attributeType + " " + attributeName;
                        } 
                    }
                    else{
                        if ("object".equals(attributeType)) {
                            attributeInfo = attAccessType + " final static " + objName + " " + attributeName;
                        }else {
                            attributeInfo = attAccessType + "  final static " + attributeType + " " + attributeName;  
                        }
                    }
                }
             
                attributeListModel.addElement(attributeInfo);
                
                AttributeInformation attribute = new AttributeInformation(attAccessType, isSelected ? "static" : "",finalv ? "final" : "",attributeType, attributeName, objName);
                ClassesManager.getClass(selectedClassIndex).addAttribute(attribute);

                attributeNameField.setText("");
                objNameField.setText("");
                instanceCheckBox.setSelected(false);
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
                if (selectedClassIndex >= 0 && selectedClassIndex < ClassesManager.getClasses().size()) {
                    ClassInformation selectedClass = ClassesManager.getClass(selectedClassIndex);
                    if (index != -1) {
                        attributeListModel.remove(index);
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
                getAllClasses();
                getAllAttributes();
            }
        });

        selectedClassComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassIndex = selectedClassComboBox.getSelectedIndex();

                if (selectedClassIndex >= 0 && selectedClassIndex < ClassesManager.getClasses().size()) {
                    ClassInformation selectedClass = ClassesManager.getClass(selectedClassIndex);
                    attributeListModel.clear();

                    for (AttributeInformation attribute : selectedClass.getAttributes()) {
                        attributeListModel.addElement(attribute.toString());
                    }
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }
            }
        });

    }

    private void updateSelectedClassComboBox() {
        selectedClassComboBox.removeAllItems();
        for (ClassInformation c : ClassesManager.getClasses()) {
            String className = c.getClassName();
            selectedClassComboBox.addItem(className);
        }
    }

    private void getAllAttributes() {
        for (ClassInformation classInfo : ClassesManager.getClasses()) {
            System.out.println(classInfo.toString());
            System.out.println("Attributes:");

            for (AttributeInformation attributeInfo : classInfo.getAttributes()) {
                System.out.println(attributeInfo.toString());
                System.out.println("-----------------------------------------");
            }
        }
    }

    private void getAllClasses() {
        System.out.println(ClassesManager.getClasses());
    }

}
