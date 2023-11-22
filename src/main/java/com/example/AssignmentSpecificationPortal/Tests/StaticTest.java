package com.example.AssignmentSpecificationPortal.Tests;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import com.example.TestCase;
import com.example.TestCaseManager;
import com.example.AssignmentSpecificationPortal.AttributeInformation;
import com.example.AssignmentSpecificationPortal.MethodInformation;
import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;
import com.example.BasicTest.ClassBasicTest;
import com.example.BasicTest.AttributeBasicTest;
import com.example.BasicTest.MethodBasicTest; 

public class StaticTest extends BaseTest {
    /* This class gives the layout and behaviour specific to Static Test
     */

    public StaticTest(String description) {
        super(); 
        this.testDescription.setText(description);
        this.testType = "static";
    }

    @Override
    public void printTest() {

        clearTestCases();

        String nameCon = (String) selectedClassComboBox.getSelectedItem();
        String cName = (String) selectedClassComboBox.getSelectedItem();

        String textFieldValue = marksTextField1.getText(); 
        int marks = Integer.parseInt(textFieldValue); 
       // String testType = "name";

        if (classCheckB.isSelected() == true) {
            classTests.add(new ClassBasicTest(marks, cName, testType));
            nameCon = nameCon + "\n" + "-Class [" + marks + " marks]\n";
        }
        if (attCheckB.isSelected() == true) {
            nameCon = nameCon + "-Attributes: ";
            nameCon = checkCheckboxes(attributePanel, nameCon, cName,0,testType);
        }
        if (methCheckB.isSelected() == true) {
            nameCon = nameCon + "-Methods: ";
            nameCon = checkCheckboxes(methodPanel, nameCon, cName,1,testType);
        }
        nameCon = nameCon + "---------------------------\n";
        nameTests.append(nameCon);

        addTestCases();
    }

    @Override
    protected void updateMethodList(String ans) {
        for(ClassInformation c : ClassesManager.getClasses()){
            if(c.getClassName().equals(ans)){
                methodPanel.removeAll();
                ArrayList<MethodInformation> methods = c.getMethods();
                for(MethodInformation m:methods){
                    JPanel panel = new JPanel();
                    String text = "";
                    if (m.getIsStatic().equals("static")) 
                        text = "static" + " " + m.getMethodName();
                    else
                        text = m.getMethodName();
                    JCheckBox mCheckBox = new JCheckBox(text);

                    mCheckBox.setPreferredSize(new Dimension(100, 20));
                    mCheckBox.setLayout(new FlowLayout(FlowLayout.LEADING));

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

    @Override
    protected void updateAttributeList(String ans) {
        for(ClassInformation c : ClassesManager.getClasses()){
            if(c.getClassName().equals(ans)){
                attributePanel.removeAll();
                ArrayList<AttributeInformation> attributes = c.getAttributes();
                for(AttributeInformation a:attributes){
                    
                    JPanel panel = new JPanel();
                    String text = "";
                    if (a.getIsStatic().equals("static")) 
                        text = "static" + " " + a.getAttributeName();
                    else
                        text = a.getAttributeName();
                        
                    JCheckBox aCheckBox = new JCheckBox(text);
                    aCheckBox.setPreferredSize(new Dimension(100, 20));
                    aCheckBox.setLayout(new FlowLayout(FlowLayout.LEADING));
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

      
}
