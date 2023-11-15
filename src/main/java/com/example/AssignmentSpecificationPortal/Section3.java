package com.example.AssignmentSpecificationPortal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Section3 extends JPanel {
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

    public Section3(CardLayout layout) {
        cardLayout = layout;
        backButton = new JButton("Back");
        nextButton = new JButton("Next");
        allAttributes = new ArrayList<>();

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                layout.previous(getParent());
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(getParent());
                // getAllAttributes(); // testing
            }
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createAttributePanel());

        add(backButton);
        add(nextButton);
    }

    private JPanel createAttributePanel() {

        JPanel HoldPanel = new JPanel();
        HoldPanel.setLayout(new GridLayout(2,1));

        attributePanel = new JPanel();
        attributePanel.setLayout(new FlowLayout());

        DefaultListModel<String> attributeListModel = new DefaultListModel<>();
        JList<String> attributeList = new JList<>(attributeListModel);

        instanceCheckBox = new JCheckBox("Instance Variable");

        attributeNameField = new JTextField(20);
        objNameField = new JTextField(10);
        attributeNameField.setText("");
        objNameField.setText("");

        attributeTypeComboBox = new JComboBox<>(new String[]{"String","int","double","boolean","object","char"});
        attAccessComboBox = new JComboBox<>(new String[]{"public", "private", "protected"});
        
        saveButton = new JButton("Save");
        // closeButton = new JButton("X");
        removeAttButton = new JButton("Remove Selected Attribute");

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
    
        //Listeners
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
                AttributeInformation attribute = new AttributeInformation(attAccessType, isSelected ? "true" : "false", attributeType, attributeName, objName);
                allAttributes.add(attribute);
        
                attributeNameField.setText("");
                objNameField.setText("");
                instanceCheckBox.setSelected(false);
            }
        });
        

        //  closeButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e){
        //         HoldPanel.setVisible(false);  
        //         //remove(HoldPanel);
        //     }
        // }); 

        attributeTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("object".equals(attributeTypeComboBox.getSelectedItem())) {
                    objNameField.setVisible(true);
                }
                else{
                    objNameField.setVisible(false);     
                } 
            }
        });

         removeAttButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int index = attributeList.getSelectedIndex();
                if(index != -1){
                    attributeListModel.remove(index);
                    allAttributes.remove(index);
                }
            }
        });

    return HoldPanel;
   }

   private ArrayList<AttributeInformation> getAllAttributes() {
    for (AttributeInformation attributeInfo : allAttributes) {
        System.out.println(attributeInfo.toString());
        System.out.println("-----------------------------------------");
    }

    return allAttributes;
}
}
