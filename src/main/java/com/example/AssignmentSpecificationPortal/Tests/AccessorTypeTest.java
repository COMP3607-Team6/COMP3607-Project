package com.example.AssignmentSpecificationPortal.Tests;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.AssignmentSpecificationPortal.AttributeInformation;
import com.example.AssignmentSpecificationPortal.MethodInformation;
import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;
import com.example.BasicTest.ClassBasicTest;
import com.example.BasicTest.AttributeBasicTest;
import com.example.BasicTest.MethodBasicTest;
import java.awt.Font;

/**
* This class gives the layout and behaviour specific to AccessorType Test
*/
public class AccessorTypeTest extends BaseTest {
    private String classAccessType;

    public AccessorTypeTest(String description) {
        super();
        this.testDescription.setText(description);
        classAccessType = "";
        String ans = (String) selectedClassComboBox.getSelectedItem();
        for (ClassInformation c : ClassesManager.getClasses()) {
            if (c.getClassName().equals(ans)) {
                classAccessType = c.getAccessType();
            }
        }
    }

    @Override
    protected void attachListeners() {
        loadClassesButton.addActionListener(e -> updateSelectedClassComboBox());

        saveTestsButton.addActionListener(e -> printTest());

        clearTestsButton.addActionListener(e -> nameTests.setText(""));

        selectedClassComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ans = (String) selectedClassComboBox.getSelectedItem();
                for (ClassInformation c : ClassesManager.getClasses()) {
                    if (c.getClassName().equals(ans)) {
                        classAccessType = c.getAccessType();

                    }
                }

                classCheckB.setText("Class - " + ans);
                updateAttributeList(ans);
                attributePanel.revalidate();
                attributePanel.repaint();

                updateMethodList(ans);
                methodPanel.revalidate();
                methodPanel.repaint();
            }
        });
    }

    @Override
    public void printTest() {

        clearTestCases();
        String nameCon = (String) selectedClassComboBox.getSelectedItem();
        String cName = (String) selectedClassComboBox.getSelectedItem();

        if (classCheckB.isSelected() == true) {
            String textFieldValue = marksTextField1.getText();
            int marks = Integer.parseInt(textFieldValue);
            classTests.add(new ClassBasicTest(marks, cName, classAccessType));
            nameCon = nameCon + "\n" + "-Class [" + marks + " marks] - " + classAccessType;
        }
        if (attCheckB.isSelected() == true) {
            nameCon = nameCon + "\n-Attributes: ";
            nameCon = checkCheckboxes(attributePanel, nameCon, cName, 0, testType);
        }
        if (methCheckB.isSelected() == true) {
            nameCon = nameCon + "\n-Methods: ";
            nameCon = checkCheckboxes(methodPanel, nameCon, cName, 1, testType);
        }
        nameCon = nameCon + "\n---------------------------\n";
        nameTests.append(nameCon);

        addTestCases();
    }

    @Override
    public String checkCheckboxes(JPanel Panel, String name, String className, int check, String testType) {
        for (Component component : Panel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel innerPanel = (JPanel) component;
                Component[] Comps = innerPanel.getComponents();
                Iterator<Component> iterator = Arrays.asList(Comps).iterator();

                int marks = 0;

                while (iterator.hasNext()) {
                    Component currentComponent = iterator.next();
                    if (currentComponent instanceof JCheckBox) {

                        JCheckBox checkBox = (JCheckBox) currentComponent;
                        String checkBoxText = checkBox.getText();
                        String[] parts = checkBoxText.split(" ");
                        String text = "";
                        String classCompName ="";
                        
                        if (parts.length == 1) {
                            text = parts[0]; // Only one word
                            classCompName = parts[0];
                            testType = parts[0];
                        } else if (parts.length >= 2) {
                            text = parts[0] + " " + parts[1]; // Concatenate the first and second parts
                            testType = parts[0];
                            classCompName = parts[1];
                        }

                        if (checkBox.isSelected()) {
                            currentComponent = iterator.next();
                            currentComponent = iterator.next(); // skip label

                            if (currentComponent instanceof JTextField) {
                                JTextField textfield = (JTextField) currentComponent;
                                String textFieldValue = textfield.getText();
                                marks = Integer.parseInt(textFieldValue);
                                name = name + text + "[ " + marks + " ], ";

                            }
                            if (check == 0) {
                                attributeTests.add(new AttributeBasicTest(marks, className, classCompName, testType));
                            } else if (check == 1) {
                                methodTests.add(new MethodBasicTest(marks, className, classCompName, testType));
                            }

                            marks = 0;
                        }
                    }
                }
            }
        }

        return name;
    }

    @Override
    protected void updateAttributeList(String ans) {
        for (ClassInformation c : ClassesManager.getClasses()) {
            if (c.getClassName().equals(ans)) {
                attributePanel.removeAll();
                ArrayList<AttributeInformation> attributes = c.getAttributes();
                for (AttributeInformation a : attributes) {

                    JPanel panel = new JPanel();

                    JCheckBox aCheckBox = new JCheckBox(a.getAccessType() + " " + a.getAttributeName());
                    aCheckBox.setPreferredSize(new Dimension(100, 20));
                    panel.add(aCheckBox);

                    JLabel marksLabel = new JLabel("Marks:");
                    marksLabel.setFont(new Font("Arial", Font.BOLD, 12));
                    panel.add(marksLabel);

                    JTextField textField = new JTextField(3);
                    textField.setDocument(new IntegerDocument());
                    panel.add(textField);

                    attributePanel.add(panel);
                }
            }
        }
    }

    @Override
    protected void updateMethodList(String ans) {
        for (ClassInformation c : ClassesManager.getClasses()) {
            if (c.getClassName().equals(ans)) {
                methodPanel.removeAll();
                ArrayList<MethodInformation> methods = c.getMethods();
                for (MethodInformation m : methods) {
                    JPanel panel = new JPanel();
                    JCheckBox mCheckBox = new JCheckBox(m.getAccessType() + " " + m.getMethodName());
                    mCheckBox.setPreferredSize(new Dimension(100, 20));
                    panel.add(mCheckBox);

                    JLabel marksLabel = new JLabel("Marks:");
                    marksLabel.setFont(new Font("Arial", Font.BOLD, 12));
                    panel.add(marksLabel);

                    JTextField textField = new JTextField(3);
                    textField.setDocument(new IntegerDocument());
                    panel.add(textField);

                    methodPanel.add(panel);
                }
            }
        }
    }

}
