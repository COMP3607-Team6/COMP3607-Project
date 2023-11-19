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

import javax.swing.AbstractButton;
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

public class NamingConventionTest extends JPanel {

    private ArrayList<ClassBasicTest> classTests;
    private ArrayList<MethodBasicTest> methodTests;
    private ArrayList<AttributeBasicTest> attributeTests;
    private ArrayList<TestCase> testCases;
    private JLabel testDescription;
    private JPanel selectedClassPanel;
    private JLabel selectedClassLabel;
    private JButton loadClassesButton;
    private JComboBox<String> selectedClassComboBox;
    private JPanel selectedClassPanel2;
    private JCheckBox classCheckB;
    private JTextField marksTextField1;
    private JPanel selectedClassPanel3;
    private JCheckBox attCheckB;
    private JPanel attributePanel;
    private JPanel methodPanel;
    private JPanel selectedClassPanel4;
    private JCheckBox methCheckB;
    private JTextField marksTextField3;
    private JPanel testPanel;
    private JTextArea nameTests;
    private JPanel savePanel;
    private JButton saveTestsButton;
    private JButton clearTestsButton;
    private JScrollPane scrollPane;
    private JLabel markslab1;
    private JLabel markslab2;
    private JTextField marksTextField2;
    private JLabel markslab3;

    public NamingConventionTest() {

        classTests = new ArrayList<ClassBasicTest>();
        methodTests = new ArrayList<MethodBasicTest>();
        attributeTests = new ArrayList<AttributeBasicTest>();
        testCases = new ArrayList<TestCase>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        testDescription = new JLabel("naming convention test description. short one line description of test");
        testDescription.setFont(new Font("Arial", Font.ITALIC, 15));
        testDescription.setAlignmentX(Component.LEFT_ALIGNMENT);

        selectedClassPanel = new JPanel();
        selectedClassPanel.setLayout(new FlowLayout());
        // selectedClassPanel.setPreferredSize(new Dimension(100, 60));

        selectedClassLabel = new JLabel("Class:");
        loadClassesButton = new JButton("Load classes");
        
        selectedClassComboBox = new JComboBox<String>();
        for (ClassInformation c : ClassesManager.getClasses()) {
            selectedClassComboBox.addItem(c.getClassName());
        }
        selectedClassPanel.add(selectedClassLabel);
        selectedClassPanel.add(selectedClassComboBox);
        selectedClassPanel.add(loadClassesButton);

        selectedClassPanel2 = new JPanel();
        selectedClassPanel2.setLayout(new FlowLayout());
        // selectedClassPanel2.setPreferredSize(new Dimension(100, 60));
        
        String ans = (String) selectedClassComboBox.getSelectedItem();
        classCheckB = new JCheckBox("Class - " + ans);
        classCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
        
        markslab1 = new JLabel("Marks:");
        markslab1.setFont(new Font("Arial", Font.PLAIN, 10));
        marksTextField1 = new JTextField(3);
        marksTextField1.setFont(new Font("Arial", Font.PLAIN, 15));

        selectedClassPanel2.add(classCheckB);
        selectedClassPanel2.add(markslab1);
        selectedClassPanel2.add(marksTextField1);

        selectedClassPanel3 = new JPanel();
        selectedClassPanel3.setLayout(new FlowLayout());
        // selectedClassPanel3.setPreferredSize(new Dimension(100, 10));

        attCheckB = new JCheckBox("Attributes");
        attCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
        //JLabel markslab2 = new JLabel("Marks:");
        //markslab1.setFont(new Font("Arial", Font.PLAIN, 10));
		//JTextField marksTextField2 = new JTextField(3);
		//marksTextField2.setFont(new Font("Arial", Font.PLAIN, 15));
			
		selectedClassPanel3.add(attCheckB);
        //selectedClassPanel3.add(markslab2);
		//selectedClassPanel3.add(marksTextField2);	

        attributePanel = new JPanel();
        attributePanel.setLayout(new GridLayout(3, 3));
        updateAttributeList(ans);

        methodPanel = new JPanel();
        methodPanel.setLayout(new GridLayout(3, 3));
        updateMethodList(ans);

        selectedClassPanel4 = new JPanel();
        selectedClassPanel4.setLayout(new FlowLayout());
        // selectedClassPanel4.setPreferredSize(new Dimension(100, 100));

        methCheckB = new JCheckBox("Methods");
        methCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
      //  JLabel markslab3 = new JLabel("Marks:");
        //markslab3.setFont(new Font("Arial", Font.PLAIN, 10));
		//JTextField marksTextField3 = new JTextField(3);
		//marksTextField3.setFont(new Font("Arial", Font.PLAIN, 15));
			
		selectedClassPanel4.add(methCheckB);
        //selectedClassPanel4.add(markslab3);
		//selectedClassPanel4.add(marksTextField3);	

        testPanel = new JPanel();
        testPanel.setLayout(new FlowLayout());

        nameTests = new JTextArea(10, 20);
        nameTests.setEditable(false);
        scrollPane = new JScrollPane(nameTests);

        testPanel.add(scrollPane);

        savePanel = new JPanel();
        savePanel.setLayout(new FlowLayout());
        saveTestsButton = new JButton("Save Test");
        savePanel.add(saveTestsButton);
        clearTestsButton = new JButton("Clear Tests");
        savePanel.add(clearTestsButton);

        add(testDescription);
        add(selectedClassPanel);
        add(selectedClassPanel2);
        add(selectedClassPanel3);
        add(attributePanel);
        add(selectedClassPanel4);
        add(methodPanel);
        add(testPanel);
        add(savePanel);

        attachListeners();
    }

    private void attachListeners() {
        loadClassesButton.addActionListener(e -> updateSelectedClassComboBox());

        saveTestsButton.addActionListener(e -> printTest());

        clearTestsButton.addActionListener(e -> nameTests.setText(""));

        selectedClassComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ans = (String) selectedClassComboBox.getSelectedItem();
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

    private void updateSelectedClassComboBox() {
        selectedClassComboBox.removeAllItems();
        for (ClassInformation c : ClassesManager.getClasses()) {
            selectedClassComboBox.addItem(c.getClassName());
        }
    }

    private void updateAttributeList(String ans) {
        for(ClassInformation c : ClassesManager.getClasses()){
                    if(c.getClassName().equals(ans)){
                        attributePanel.removeAll();
                        ArrayList<AttributeInformation> attributes = c.getAttributes();
                        for(AttributeInformation a:attributes){
                           // JCheckBox aCheckBox = new JCheckBox(a.getAttributeType()+ " "+ a.getAttributeName());
                          /*  JCheckBox aCheckBox = new JCheckBox(a.getAttributeName());
                            attributePanel.add(aCheckBox); */
                            JPanel panel = new JPanel();
                            JCheckBox aCheckBox = new JCheckBox(a.getAttributeName());
                            panel.add(aCheckBox);
                            
                            NumberFormat format = NumberFormat.getIntegerInstance();
                            NumberFormatter formatter = new NumberFormatter(format);
                            formatter.setValueClass(Integer.class);
                            formatter.setMinimum(0); // Set minimum value as needed
                            JFormattedTextField integerTextField = new JFormattedTextField(formatter); 
                            integerTextField.setColumns(3);
                            //JTextField textField = new JTextField(10);
                            //panel.add(textField);
                            panel.add(integerTextField);
                            attributePanel.add(panel);
                        }    
                    }
        }  
    }

   /*  private void updateMethodList(String ans) {
        for(ClassInformation c : ClassesManager.getClasses()){
                    if(c.getClassName().equals(ans)){
                        methodPanel.removeAll();
                        ArrayList<MethodInformation> methods = c.getMethods();
                
                         for(MethodInformation m:methods){
                            JCheckBox mCheckBox = new JCheckBox(m.getMethodName());
                            methodPanel.add(mCheckBox);
                        }    
                    } 
        }  
    } */

    private void updateMethodList(String ans) {
        for(ClassInformation c : ClassesManager.getClasses()){
                    if(c.getClassName().equals(ans)){
                        methodPanel.removeAll();
                        ArrayList<MethodInformation> methods = c.getMethods();
                        for(MethodInformation m:methods){
                           // JCheckBox aCheckBox = new JCheckBox(a.getAttributeType()+ " "+ a.getAttributeName());
                          /*  JCheckBox aCheckBox = new JCheckBox(a.getAttributeName());
                            attributePanel.add(aCheckBox); */
                            JPanel panel = new JPanel();
                            JCheckBox mCheckBox = new JCheckBox(m.getMethodName());
                            panel.add(mCheckBox);
                            
                            NumberFormat format = NumberFormat.getIntegerInstance();
                            NumberFormatter formatter = new NumberFormatter(format);
                            formatter.setValueClass(Integer.class);
                            formatter.setMinimum(0); // Set minimum value as needed
                            JFormattedTextField integerTextField = new JFormattedTextField(formatter); 
                            integerTextField.setColumns(3);
                            //JTextField textField = new JTextField(10);
                            //panel.add(textField);
                            panel.add(integerTextField);
                            methodPanel.add(panel);
                        }    
                    }
        }  
    }

    private void clearTestCases() {
        TestCaseManager.getTestCases().removeAll(classTests);
        TestCaseManager.getTestCases().removeAll(attributeTests);
        TestCaseManager.getTestCases().removeAll(methodTests);

        classTests.clear();
        methodTests.clear();
        attributeTests.clear();
    }

    private void addTestCases() {
        testCases.addAll(classTests);
        testCases.addAll(attributeTests);
        testCases.addAll(methodTests);

        TestCaseManager.getTestCases().addAll(classTests);
        TestCaseManager.getTestCases().addAll(attributeTests);
        TestCaseManager.getTestCases().addAll(methodTests);
    }

    public void printTest() {

        clearTestCases();

        String nameCon = (String) selectedClassComboBox.getSelectedItem();
        String cName = (String) selectedClassComboBox.getSelectedItem();
        String marks = (String) marksTextField1.getText();
        String testType = "name";

        if (classCheckB.isSelected() == true) {
            classTests.add(new ClassBasicTest(0, cName, testType));
            nameCon = nameCon + "\n" + "-Class [" + marks + " mark]\n";
        }
        if (attCheckB.isSelected() == true) {
            nameCon = nameCon + "-Attributes ";
            nameCon = checkAttributeCheckboxes(attributePanel, nameCon, cName);
        }
        if (methCheckB.isSelected() == true) {
            nameCon = nameCon + "-Methods ";
            nameCon = checkMethodCheckboxes(methodPanel, nameCon, cName);
        }
        nameCon = nameCon + "---------------------------\n";
        nameTests.append(nameCon);

        addTestCases();
    }

    public String checkAttributeCheckboxes(JPanel Panel, String name, String className) {
        Component[] components = Panel.getComponents();
        Iterator<Component> iterator = Arrays.asList(components).iterator();
        int marks = 0;

        while (iterator.hasNext()) {
            Component currentComponent = iterator.next();

             if (currentComponent instanceof JCheckBox) {
                  JCheckBox checkBox = (JCheckBox) currentComponent;
                  String checkBoxText = checkBox.getText();
                   if (checkBox.isSelected()) {
                        name = name + checkBoxText + ", ";
                        if (iterator.hasNext()) {
                            if (iterator.next() instanceof JFormattedTextField) {
                                JFormattedTextField textfield =(JFormattedTextField) iterator.next();
                                marks = Integer.parseInt(textfield.getText());
                            }
                        }
                        attributeTests.add(new AttributeBasicTest(marks,className, checkBoxText, "name"));
                        System.out.println("att added here");
                        marks=0;
                   } 
             }
        }
        name = name + "\n";
        return name; 
    }

    /*public String checkMethodCheckboxes(JPanel Panel,String name,String className) {
        Component[] components = Panel.getComponents();
        for (Component component : components) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                String checkBoxText = checkBox.getText();

                if (checkBox.isSelected()) {
                    name = name + checkBoxText + ", ";
                    methodTests.add(new MethodBasicTest(0, className, checkBoxText, "name"));
                }
            }
        }
        name = name + "\n";
        return name;
    } */

    public String checkMethodCheckboxes(JPanel Panel,String name,String className) {
        Component[] components = Panel.getComponents();
        Iterator<Component> iterator = Arrays.asList(components).iterator();
        int marks = 0;

        while (iterator.hasNext()) {
            Component currentComponent = iterator.next();

             if (currentComponent instanceof JCheckBox) {
                  JCheckBox checkBox = (JCheckBox) currentComponent;
                  String checkBoxText = checkBox.getText();
                   if (checkBox.isSelected()) {
                        name = name + checkBoxText + ", ";
                        if (iterator.hasNext()) {
                            if (iterator.next() instanceof JFormattedTextField) {
                                JFormattedTextField textfield =(JFormattedTextField) iterator.next();
                                marks = Integer.parseInt(textfield.getText());
                            }
                        }
                        //attributeTests.add(new AttributeBasicTest(marks,className, checkBoxText, "name"));
                        methodTests.add(new MethodBasicTest(marks,className, checkBoxText, "name"));
                        marks=0;
                   } 
             }
        }
        name = name + "\n";
        return name; 
    }
}