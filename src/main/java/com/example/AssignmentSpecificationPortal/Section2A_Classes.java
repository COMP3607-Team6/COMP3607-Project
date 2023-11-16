package com.example.AssignmentSpecificationPortal;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Pattern;

public class Section2A_Classes extends JPanel {
    private DefaultListModel<String> classListModel;
    private JList<String> classList;
    private JTextField classNameField;
    private JComboBox<String> accessComboBox;
    private JComboBox<String> isAbstractComboBox;
    private JComboBox<String> isInterfaceComboBox;
    private JComboBox<String> EIComboBox;
    private JTextField NameField;
    private JTextField markField;
    private JCheckBox extendsCheckBox;
    private JButton addClassButton;
    private JButton removeButton;
    // private JButton addAttButton;
    private CardLayout cardLayout;
    // private JButton backButton;
    private JPanel classPanel;
    private JPanel inputPanel;
    private JPanel testPanel;
    private JLabel prompt;
    private JLabel markslabel;
    private int classCount;
    // private JButton nextButton;
    // ArrayList<ClassInformation> allClasses;
    private ArrayList<ClassInformation> classes;

    public Section2A_Classes(CardLayout cardLayout, ArrayList<ClassInformation> classes) {
        this.cardLayout = cardLayout;
        this.classes = classes;
        // backButton = new JButton("Back");
        // nextButton = new JButton("Next");
        // allClasses = new ArrayList<ClassInformation>();

        // backButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         layout.previous(getParent());
        //     }
        // });

        // nextButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         cardLayout.next(getParent());
        //         // getAllClasses(); // just for testing
        //     }
        // });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Setting Up Class/Input Panel
        classPanel = new JPanel();
        classPanel.setLayout(new GridLayout(2, 1));
        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        testPanel = new JPanel();
        testPanel.setLayout(new GridLayout(2, 1));

        accessComboBox = new JComboBox<>(new String[] { "public", "private", "protected" });
        isAbstractComboBox = new JComboBox<>(new String[] { "abstract", "concrete" });
        isInterfaceComboBox = new JComboBox<>(new String[] { "class", "interface" });
        EIComboBox = new JComboBox<>(new String[] { "extends", "implements" });
        EIComboBox.setVisible(false);

        classNameField = new JTextField(10);
        classNameField.setText("");
        markField = new JTextField(6);
        markField.setDocument(new IntegerDocument());
        markField.setText("0");
        NameField = new JTextField(10);
        NameField.setVisible(false);

        prompt = new JLabel("Classes");
        prompt.setFont(new Font("Arial", Font.ITALIC, 22));
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        markslabel = new JLabel("Marks: \n");

        extendsCheckBox = new JCheckBox("extends/implements");

        removeButton = new JButton("Remove Selected Class");
        // addAttButton = new JButton("Add Attributes");
        addClassButton = new JButton("+ Class");

        classListModel = new DefaultListModel<>();
        classList = new JList<>(classListModel);

        // inputPanel.add(prompt);
        inputPanel.add(accessComboBox);
        inputPanel.add(isAbstractComboBox);
        inputPanel.add(isInterfaceComboBox);
        inputPanel.add(classNameField);
        inputPanel.add(extendsCheckBox);
        inputPanel.add(EIComboBox);
        inputPanel.add(NameField);
        inputPanel.add(markslabel);
        inputPanel.add(markField);
        inputPanel.add(addClassButton);
        inputPanel.add(removeButton);
        // inputPanel.add(addAttButton);

        // add(prompt);
        classPanel.add(inputPanel);
        classPanel.add(new JScrollPane(classList));

        add(prompt);
        add(classPanel);
        add(testPanel);
        // add(backButton);
        // add(nextButton);

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
                    NameField.setVisible(true);
                    extendsCheckBox.setText("");

                } else {
                    EIComboBox.setVisible(false);
                    NameField.setVisible(false);
                    extendsCheckBox.setText("extends/implements");

                }
            }
        });

        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classCount++;
                addClassToList();
                // getAllClasses();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = classList.getSelectedIndex();
                if (index != -1) {
                    // String selectedClass = classListModel.getElementAt(index);
                    classListModel.remove(index);
                    // allClasses.remove(index);
                    classes.remove(index);
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
        String extendedOrImplementedClass = (String) NameField.getText();
        String marks = (String) markField.getText();
        boolean extendsOrImplementedIsChecked = extendsCheckBox.isSelected();

        if (className.equals("")) {
            return;
        } 
        if ((extendedOrImplementedClass.equals("")) && (extendsOrImplementedIsChecked == true)) {
            return;
        } 

        ClassInformation classInfo = new ClassInformation(accessType, isAbstract, isInterface, className,
                                                      extendsOrImplements, extendedOrImplementedClass, marks);

        classListModel.addElement(classInfo.toString());

        // allClasses.add(classInfo);
        classes.add(classInfo);


        classNameField.setText("");
        NameField.setText("");
        markField.setText("0");
    }

    private ArrayList<ClassInformation> getAllClasses() {
        for (ClassInformation classInfo : classes) {
            System.out.println(classInfo.toString());
            System.out.println("-----------------------------------------");
        }
    
        return classes;
    }

    // Restricts input to integers only
    // use like: 
    //     JTextField marksTextField = new JTextField();
    //     marksTextField.setDocument(new IntegerDocument());
    static class IntegerDocument extends PlainDocument {
        private final Pattern pattern = Pattern.compile("-?\\d*");

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            String text = getText(0, getLength()) + str;
            if (pattern.matcher(text).matches()) {
                super.insertString(offs, str, a);
            }
        }
    }

}