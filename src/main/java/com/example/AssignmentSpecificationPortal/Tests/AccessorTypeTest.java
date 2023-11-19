package com.example.AssignmentSpecificationPortal.Tests;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.example.TestCase;
import com.example.TestCaseManager;
import com.example.AssignmentSpecificationPortal.AttributeInformation;
import com.example.AssignmentSpecificationPortal.MethodInformation;
import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;
import com.example.BasicTest.ClassBasicTest;
import com.example.BasicTest.AttributeBasicTest;
import com.example.BasicTest.MethodBasicTest;

public class AccessorTypeTest extends BaseTest {
    private String classAccessType;

    public AccessorTypeTest(String description) {
        super(); 
        this.testDescription.setText(description);
        classAccessType="";
        String ans = (String) selectedClassComboBox.getSelectedItem();
        for(ClassInformation c : ClassesManager.getClasses()){
            if(c.getClassName().equals(ans)){
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
                for(ClassInformation c : ClassesManager.getClasses()){
                    if(c.getClassName().equals(ans)){
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

        
       // String testType = "name";
        

        if (classCheckB.isSelected() == true) {
            String textFieldValue = marksTextField1.getText(); 
            int marks = Integer.parseInt(textFieldValue); 
            classTests.add(new ClassBasicTest(marks, cName, classAccessType));
            nameCon = nameCon + "\n" + "-Class [" + marks + " marks] - "+classAccessType;
        } 
        if (attCheckB.isSelected() == true) {
            nameCon = nameCon + "\n-Attributes: ";
            nameCon = checkCheckboxes(attributePanel, nameCon, cName,0,testType);
        }
        if (methCheckB.isSelected() == true) {
            nameCon = nameCon + "\n-Methods: ";
            nameCon = checkCheckboxes(methodPanel, nameCon, cName,1,testType);
        }
        nameCon = nameCon + "\n---------------------------\n";
        nameTests.append(nameCon);

        addTestCases();
    }

    @Override
    public String checkCheckboxes(JPanel Panel, String name, String className,int check,String testType) {
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
                        if (checkBox.isSelected()) {
                            currentComponent = iterator.next();
                           
                            if (currentComponent instanceof JTextField) {
                                JTextField textfield =(JTextField) currentComponent;
                                String textFieldValue = textfield.getText(); 
                                marks = Integer.parseInt(textFieldValue); 
                                name = name + checkBoxText + "[ "+marks+" ], ";

                                currentComponent = iterator.next();
                                if (currentComponent instanceof JLabel) {
                            
                                    JLabel accessType =(JLabel) currentComponent;
                                    testType = accessType.getText();
                                }
                                
                            }
                            if(check == 0){
                                attributeTests.add(new AttributeBasicTest(marks,className, checkBoxText, testType));
                            }
                            else if(check == 1){
                                methodTests.add(new MethodBasicTest(marks,className, checkBoxText, testType));
                            }
                        
                            marks=0;                    
                        } 
                    } 
                }
            }
        } 
        
        return name; 
    }

    @Override
    protected void updateAttributeList(String ans) {
        for(ClassInformation c : ClassesManager.getClasses()){
                    if(c.getClassName().equals(ans)){
                        attributePanel.removeAll();
                        ArrayList<AttributeInformation> attributes = c.getAttributes();
                        for(AttributeInformation a:attributes){
                         
                            JPanel panel = new JPanel();

                            JCheckBox aCheckBox = new JCheckBox(a.getAttributeName());
                            panel.add(aCheckBox);
                            
                            JTextField textField = new JTextField(3);
                            textField.setDocument(new IntegerDocument());
                            panel.add(textField);

                            JLabel accessType = new JLabel(a.getAccessType());
                            panel.add(accessType);
                           
                            attributePanel.add(panel);
                        }    
                    }
        }  
    }

    @Override
    protected void updateMethodList(String ans) {
        for(ClassInformation c : ClassesManager.getClasses()){
                    if(c.getClassName().equals(ans)){
                        methodPanel.removeAll();
                        ArrayList<MethodInformation> methods = c.getMethods();
                        for(MethodInformation m:methods){
                            JPanel panel = new JPanel();
                            JCheckBox mCheckBox = new JCheckBox(m.getMethodName());
                            panel.add(mCheckBox);
                            
                            JTextField textField = new JTextField(3);
                            textField.setDocument(new IntegerDocument());
                            panel.add(textField);

                            JLabel accessType = new JLabel(m.getAccessType());
                            panel.add(accessType);
                            
                            methodPanel.add(panel);
                        }    
                    }
        }  
    }
       
}

  
