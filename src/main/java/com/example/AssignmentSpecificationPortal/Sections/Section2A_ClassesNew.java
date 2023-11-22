package com.example.AssignmentSpecificationPortal.Sections;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;

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

    public Section2A_ClassesNew() {
        super();

        promptPanel = createPromptPanel("Add classes to be tested here. Enter class signature:");
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

        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        outputPanel2.add(listScrollPane);
    }

    @Override
    public void attachSaveAndRemoveListeners(JButton saveBtn, JButton removeBtn) {
         saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classCount++;
                addClassToList();
            }
        });

        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (index != -1) {
                    listModel.remove(index);
                    ClassesManager.removeClass(index);
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

        ClassesManager.addClass(classInfo);


        classNameField.setText("");
        nameField.setText("");
        extendsCheckBox.setSelected(false);
        EIComboBox.setVisible(false);
        nameField.setVisible(false);
        extendsCheckBox.setText("extends/implements");
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
