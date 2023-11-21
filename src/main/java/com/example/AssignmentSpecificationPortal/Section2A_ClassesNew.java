package com.example.AssignmentSpecificationPortal;

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

public class Section2A_ClassesNew extends Section2_Input {
     //
    /* This class creates the Layout for Class Input TabbedPane of the GUI
     */

    private JPanel promptPanel;
    private JPanel inputPanel;
    private JComboBox accessComboBox;
    private JComboBox isAbstractComboBox;
    private JComboBox isInterfaceComboBox;
    private JTextField classNameField;
    private JCheckBox extendsCheckBox;
    private JComboBox EIComboBox;
    private JTextField nameField;
    private JPanel buttonPanel;
    private JPanel mainPanel;
    private DefaultListModel listModel;
    private JList list;
    protected int classCount;

    public Section2A_ClassesNew(CardLayout layout) {
        super(layout);

        promptPanel = createPromptPanel("Add classes to be tested here.");
        mainPanel = createMainPanel();

        add(promptPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(mainPanel);

        attachListeners();
    }

    @Override
    public void setupInputPanel(JPanel inputPanel2) {
        inputPanel = inputPanel2;
        accessComboBox = new JComboBox<>(new String[] { "public", "private", "protected" });
        isAbstractComboBox = new JComboBox<>(new String[] { "abstract", "concrete","final" });
        isInterfaceComboBox = new JComboBox<>(new String[] { "class", "interface" });
        
        classNameField = new JTextField(10);
        classNameField.setText("");

        extendsCheckBox = new JCheckBox("extends/implements");

        EIComboBox = new JComboBox<>(new String[] { "extends", "implements" });
        EIComboBox.setVisible(false);

        nameField = new JTextField(10);
        nameField.setVisible(false);

        buttonPanel = createButtonPanel("Add Class", "Remove Selected Class");
       
        inputPanel.add(accessComboBox);
        inputPanel.add(isAbstractComboBox);
        inputPanel.add(isInterfaceComboBox);
        inputPanel.add(classNameField);
        inputPanel.add(extendsCheckBox);
        inputPanel.add(EIComboBox);
        inputPanel.add(nameField);
        inputPanel.add(buttonPanel);
    }

    @Override
    public void setupOutputPanel(JPanel outputPanel2) {
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setFixedCellWidth(600);
        list.setFixedCellHeight(40);
        outputPanel2.add(new JScrollPane(list));
    }

    @Override
    public void attachSaveAndRemoveListeners(JButton saveBtn, JButton removeBtn) {
         saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classCount++;
                addClassToList();
                getAllClasses();
            }
        });

        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (index != -1) {
                    // String selectedClass = classListModel.getElementAt(index);
                    listModel.remove(index);
                    // allClasses.remove(index);
                    // classes.remove(index);
                    ClassesManager.removeClass(index);
                    // removeAttributePanel(selectedClass);
                    classCount--;
                }
            }
        });
    }
    
    private void addClassToList() {
        String accessType = (String) accessComboBox.getSelectedItem();
        String isAbstract = (String) isAbstractComboBox.getSelectedItem();
        String isInterface = (String) isInterfaceComboBox.getSelectedItem();
        String className = (String) classNameField.getText();
        String extendsOrImplements = (String) EIComboBox.getSelectedItem();
        String extendedOrImplementedClass = (String) nameField.getText();
        boolean extendsOrImplementedIsChecked = extendsCheckBox.isSelected();

        if (className.equals("")) {
            return;
        } 
        if ((extendedOrImplementedClass.equals("")) && (extendsOrImplementedIsChecked == true)) {
            return;
        } 
        if(isInterface.equals("interface")){
            isAbstract="";
        }

        ClassInformation classInfo = new ClassInformation(accessType, isAbstract, isInterface, className,
                                                      extendsOrImplements, extendedOrImplementedClass);

        listModel.addElement(classInfo.toString());

        // allClasses.add(classInfo);
        // classes.add(classInfo);
        ClassesManager.addClass(classInfo);


        classNameField.setText("");
        nameField.setText("");
    }

    public void attachListeners() {
        isInterfaceComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("interface".equals(isInterfaceComboBox.getSelectedItem())) {
                    isAbstractComboBox.setVisible(false);
                    EIComboBox.removeAllItems();
                    EIComboBox.addItem("extends");

                    isInterfaceComboBox.setLocation(240, 90);
                } else {
                    isAbstractComboBox.setVisible(true);
                    EIComboBox.addItem("implements");
                    isInterfaceComboBox.setLocation(350, 90);
                }
            }
        });

        extendsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (true == extendsCheckBox.isSelected()) {
                    EIComboBox.setVisible(true);
                    nameField.setVisible(true);
                    extendsCheckBox.setText("");

                } else {
                    EIComboBox.setVisible(false);
                    nameField.setVisible(false);
                    extendsCheckBox.setText("extends/implements");

                }
            }
        });
    }
}
