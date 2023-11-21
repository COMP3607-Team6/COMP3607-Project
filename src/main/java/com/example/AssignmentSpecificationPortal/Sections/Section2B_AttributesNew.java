package com.example.AssignmentSpecificationPortal.Sections;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.example.AssignmentSpecificationPortal.AttributeInformation;
import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;

public class Section2B_AttributesNew extends Section2_Input {
     //
    /* This class creates the Layout for Attribute Input TabbedPane of the GUI
     */

    private JPanel promptPanel;
    private JPanel selectedClassPanel;
    private int selectedClassIndex;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JPanel mainPanel;
    private DefaultListModel listModel;
    private JList list;
    protected int classCount;
    private JCheckBox staticCheckButton;
    private JCheckBox finalCheckButton;
    private JTextField attributeNameField;
    private JTextField objNameField;
    private JComboBox attributeTypeComboBox;
    private JComboBox attAccessComboBox;
    private JLabel selectedClassLabel;
    private JButton loadClassesButton;
    // private JRadioButton staticRadioButton;
    // private JRadioButton finalRadioButton;
    // private ButtonGroup buttonGroup;

    public Section2B_AttributesNew(CardLayout layout) {
        super(layout);

        promptPanel = createPromptPanel("Add attributes (for inputted classes) to be tested here.");
        selectedClassPanel = createSelectedClassPanel();
        mainPanel = createMainPanel();

        add(promptPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(selectedClassPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(mainPanel);

        attachListeners();
    }

    public JPanel createSelectedClassPanel() {
        selectedClassPanel = new JPanel();
        selectedClassPanel.setLayout(new FlowLayout());
        selectedClassLabel = new JLabel("Class:");
        loadClassesButton = new JButton("Load classes");
        selectedClassComboBox = new JComboBox<String>();

        selectedClassPanel.add(selectedClassLabel);
        selectedClassPanel.add(selectedClassComboBox);
        selectedClassPanel.add(loadClassesButton);

        updateSelectedClassComboBox();

        if (selectedClassComboBox.getItemCount() == 0) {
            selectedClassComboBox.setVisible(false);
        }

        return selectedClassPanel;
    }


    @Override
    public void setupInputPanel(JPanel inputPanel2) {
        inputPanel = inputPanel2;

        staticCheckButton = new JCheckBox("static");
        finalCheckButton = new JCheckBox("final");
        // staticRadioButton = new JRadioButton("Static");
        // finalRadioButton = new JRadioButton("Final");

        // Create a ButtonGroup and add radio buttons to it
        // buttonGroup = new ButtonGroup();
        // buttonGroup.add(staticRadioButton);
        // buttonGroup.add(finalRadioButton);

        attributeNameField = new JTextField(20);
        attributeNameField.setText("");
        objNameField = new JTextField(10);
        objNameField.setText("");
        objNameField.setVisible(false);

        attributeTypeComboBox = new JComboBox<>(
                new String[] { "String", "int", "double", "boolean", "object", "char" });
        attAccessComboBox = new JComboBox<>(new String[] { "public", "private", "protected" });
      //  isFinalComboBox = new JComboBox<>(new String[] { "final", "-"});


       staticCheckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (true == staticCheckButton.isSelected()) {
                    finalCheckButton.setVisible(false);
                    finalCheckButton.setSelected(false);
                }
                else {
                    finalCheckButton.setVisible(true);
                }
            }
        });

        finalCheckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (true == finalCheckButton.isSelected()) {
                    staticCheckButton.setVisible(false);
                    staticCheckButton.setSelected(false);
                }
                else {
                    staticCheckButton.setVisible(true);
                }
            }
        });
 
 

       

        buttonPanel = createButtonPanel("Add Attribute", "Remove Selected Attribute");
       
        inputPanel.add(attAccessComboBox);
        inputPanel.add(staticCheckButton);
        inputPanel.add(finalCheckButton);
        // inputPanel.add(staticRadioButton);
        // inputPanel.add(finalRadioButton);
        // // inputPanel.add(buttonGroup);
        inputPanel.add(attributeTypeComboBox);
        inputPanel.add(objNameField);
        inputPanel.add(attributeNameField);
        inputPanel.add(buttonPanel);
    }

    @Override
    public void setupOutputPanel(JPanel outputPanel2) {
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setFixedCellWidth(600);
        list.setFixedCellHeight(30);
        outputPanel2.add(new JScrollPane(list));
    }
     

    @Override
    public void attachSaveAndRemoveListeners(JButton saveBtn, JButton removeBtn) {
         saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String attributeName = attributeNameField.getText();
                String objName = objNameField.getText();
                //String finalv = (String) isFinalComboBox.getSelectedItem();
                //boolean finalv = finalCheckBox.isSelected();
                boolean finalv = finalCheckButton.isSelected();
                boolean isSelected = staticCheckButton.isSelected();
                String attributeType = (String) attributeTypeComboBox.getSelectedItem();
                String attAccessType = (String) attAccessComboBox.getSelectedItem();
                //boolean isSelected = instanceCheckBox.isSelected();

                if (attributeName.isEmpty()) {
                    return;
                } else if ("object".equals(attributeType) && objName.isEmpty()) {
                    return;
                } 
                
                AttributeInformation attribute = new AttributeInformation(attAccessType, isSelected ? "static" : "",finalv ? "final" : "",attributeType, attributeName, objName);
                ClassesManager.getClass(selectedClassIndex).addAttribute(attribute);

                listModel.addElement(attribute.toString());

                attributeNameField.setText("");
                objNameField.setText("");


                staticCheckButton.setVisible(true);
                finalCheckButton.setVisible(true);
                staticCheckButton.setSelected(false);
                finalCheckButton.setSelected(false);

                // buttonGroup.clearSelection();
                // finalRadioButton.setSelected(false);
                // staticRadioButton.setSelected(false);
            }
        });

        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (selectedClassIndex >= 0 && selectedClassIndex < ClassesManager.getClasses().size()) {
                    ClassInformation selectedClass = ClassesManager.getClass(selectedClassIndex);
                    if (index != -1) {
                        listModel.remove(index);
                        selectedClass.getAttributes().remove(index);
                    }
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }

            }
        });
    }

    
    
    public void attachListeners() {
        loadClassesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedClassComboBox();
                selectedClassComboBox.setVisible(true);
                getAllClasses();
                getAllAttributes();
                getAllMethods();
            }
        });

        selectedClassComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassIndex = selectedClassComboBox.getSelectedIndex();

                if (selectedClassIndex >= 0 && selectedClassIndex < ClassesManager.getClasses().size()) {
                    ClassInformation selectedClass = ClassesManager.getClass(selectedClassIndex);
                    listModel.clear();

                    for (AttributeInformation attribute : selectedClass.getAttributes()) {
                        listModel.addElement(attribute.toString());
                    }
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }
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

    
    }
}
