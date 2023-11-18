package com.example.AssignmentSpecificationPortal;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPanel attributePanel;
    private JCheckBox instanceCheckBox;
    private JCheckBox finalCheckBox;
    private JButton removeAttButton;
    private JLabel prompt;
    private JComboBox selectedClassComboBox;
    private JPanel selectedClassPanel;
    private JLabel selectedClassLabel;
    private int selectedClassIndex;
    private JButton loadClassesButton;

    public Section2B_Attributes(CardLayout layout) {
        this.cardLayout = layout;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        prompt = new JLabel("Attributes");
        prompt.setFont(new Font("Arial", Font.ITALIC, 22));
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        finalCheckBox = new JCheckBox("final");

        attributeNameField = new JTextField(20);
        objNameField = new JTextField(10);
        attributeNameField.setText("");
        objNameField.setText("");

        attributeTypeComboBox = new JComboBox<>(
                new String[] { "String", "int", "double", "boolean", "object", "char" });
        attAccessComboBox = new JComboBox<>(new String[] { "public", "private", "protected" });
      //  isFinalComboBox = new JComboBox<>(new String[] { "final", "-"});

        saveButton = new JButton("Save");
        removeAttButton = new JButton("Remove Selected Attribute");

        // attributePanel.add(selectedClassComboBox);
        attributePanel.add(attAccessComboBox);
        attributePanel.add(finalCheckBox);
        attributePanel.add(attributeTypeComboBox);
        attributePanel.add(objNameField);
        objNameField.setVisible(false);
        attributePanel.add(instanceCheckBox);
        attributePanel.add(attributeNameField);
        attributePanel.add(saveButton);
        attributePanel.add(removeAttButton);

        HoldPanel.add(attributePanel);
        HoldPanel.add(new JScrollPane(attributeList));

        // Listeners
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

        return HoldPanel;
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
