package com.example.AssignmentSpecificationPortal.Tests;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.TestCaseManager;
import com.example.AssignmentSpecificationPortal.AttributeInformation;
import com.example.AssignmentSpecificationPortal.MethodInformation;
import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;
import com.example.BehaviourTests.AttributeTypeTest;
import com.example.BehaviourTests.ClassTypeTest;
import com.example.BehaviourTests.MethodTypeTest;

/**
 * This class gives the layout and behaviour specific to Type Test
 */
public class TypeTest extends BaseTest {
   

    private String classType;
    protected ArrayList<ClassTypeTest> classTypeTests;
    protected ArrayList<MethodTypeTest> methodTypeTests;
    protected ArrayList<AttributeTypeTest> attributeTypeTests;

    public TypeTest(String description) {
        super(); 
        this.testDescription.setText(description);
        classType="";
        String ans = (String) selectedClassComboBox.getSelectedItem();
        for(ClassInformation c : ClassesManager.getClasses()){
            if(c.getClassName().equals(ans)){
                classType = c.getType();  
            }
        }  
        classTypeTests = new ArrayList<ClassTypeTest>();
        methodTypeTests = new ArrayList<MethodTypeTest>();
        attributeTypeTests = new ArrayList<AttributeTypeTest>(); 
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
                        classType = c.getType();
                    
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
            classTypeTests.add(new ClassTypeTest(cName, marks, classType));
            nameCon = nameCon + "\n" + "-Class [" + marks + " marks] - "+ classType;
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
                                

                                currentComponent = iterator.next();
                                if (currentComponent instanceof JLabel) {
                            
                                    JLabel Comptype =(JLabel) currentComponent;
                                    testType = Comptype.getText();
                                    name = name + checkBoxText + "[ "+marks+" ], ";
                                }
                                
                            }
                            if(check == 0){
        
                                attributeTypeTests.add(new AttributeTypeTest(marks,className, checkBoxText, testType));
                                
                            }
                            else if(check == 1){
                                methodTypeTests.add(new MethodTypeTest(marks,className, checkBoxText, testType));
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

                            JLabel accessType = new JLabel(a.getAttributeType());
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

                            JLabel accessType = new JLabel(m.getMethodType());
                            panel.add(accessType);
                            
                            methodPanel.add(panel);
                        }    
                    }
        }  
    }
  
    @Override
    protected void clearTestCases() {

        TestCaseManager.getTestCases().removeAll(classTypeTests);
        TestCaseManager.getTestCases().removeAll(attributeTypeTests);
        TestCaseManager.getTestCases().removeAll(methodTypeTests);
        

        classTypeTests.clear();
        methodTypeTests.clear();
        attributeTypeTests.clear();
    }

    @Override
    protected void addTestCases() {
        testCases.addAll(classTypeTests);
        testCases.addAll(attributeTypeTests);
        testCases.addAll(methodTypeTests);

        TestCaseManager.getTestCases().addAll(classTypeTests);
        TestCaseManager.getTestCases().addAll(attributeTypeTests);
        TestCaseManager.getTestCases().addAll(methodTypeTests);
    }
       
}

  

