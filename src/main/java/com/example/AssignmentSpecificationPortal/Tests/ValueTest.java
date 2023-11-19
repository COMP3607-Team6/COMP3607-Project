package com.example.AssignmentSpecificationPortal.Tests;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.management.AttributeValueExp;
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
import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;
import com.example.AssignmentSpecificationPortal.MethodInformation;
import com.example.BasicTest.AttributeBasicTest;
import com.example.BasicTest.MethodBasicTest;
import com.example.BehaviourTests.AttributeValueTest;
import com.example.BehaviourTests.MethodValueTest;

public class ValueTest extends JPanel{
    private JLabel testDescription;
    private JPanel selectedClassPanel;
    private JLabel selectedClassLabel;
    private JButton loadClassesButton;
    private JComboBox<String> selectedClassComboBox;
    private int selectedClassIndex;

    private JPanel grid;
    private JPanel displaySection;
    private JPanel saveBtnPanel;
    private JPanel inputSection;
    private JPanel attributesSection;
    private JPanel methodsSection;

    private JButton saveBtn;
    private JButton deleteBtn;
    private JTextArea allTests;

    private JLabel attributesLabel;
    private JLabel attributesMarkLabel;
    private JTextField attributesMarkField;

    private JLabel methodsLabel;
    private JLabel methodsMarkLabel;
    private JTextField methodsMarkField;

    private JPanel attributeList;
    private JPanel methodList;

    private JCheckBox aCheckBox;

    private ArrayList<AttributeValueTest> attributeTests;
    private ArrayList<MethodValueTest> methodTests;
    private ArrayList<TestCase> testCases;


    public ValueTest(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        methodTests = new ArrayList<MethodValueTest>();
        attributeTests = new ArrayList<AttributeValueTest>();
        testCases = new ArrayList<TestCase>();

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

        if (selectedClassComboBox.getItemCount() == 0) {
            selectedClassComboBox.setVisible(false);
        }

        String currentClassName = (String) selectedClassComboBox.getSelectedItem();
        add(testDescription);
        add(selectedClassPanel);

        grid = new JPanel();
        grid.setLayout(new GridLayout(1, 2));

        displaySection = new JPanel();
        displaySection.setLayout(new BoxLayout(displaySection, BoxLayout.Y_AXIS));




        inputSection = new JPanel();
        inputSection.setLayout(new BoxLayout(inputSection, BoxLayout.Y_AXIS));




        attributesSection = new JPanel();
        attributesSection.setLayout(new BoxLayout(attributesSection, BoxLayout.Y_AXIS));




        methodsSection = new JPanel();
        methodsSection.setLayout(new BoxLayout(methodsSection, BoxLayout.Y_AXIS));

        JPanel placeholder = new JPanel();
        placeholder.setLayout(new FlowLayout());
        attributesLabel = new JLabel("Attributes");
        attributesMarkLabel = new JLabel("Marks: ");
        attributesMarkField = new JTextField(3);

        placeholder.add(attributesLabel);
        placeholder.add(attributesMarkLabel);
        placeholder.add(attributesMarkField);

        attributesSection.add(placeholder);

        attributeList = new JPanel();
        attributeList.setLayout(new BoxLayout(attributeList, BoxLayout.Y_AXIS));

        
        // for(int i = 0; i < 20; i++){
        //     attributeList.add(new JCheckBox("somethign extends somethng"));
        // }
        populateAttributeList(currentClassName);
        JScrollPane attributesListScrollPane = new JScrollPane(attributeList);
        attributesListScrollPane.setPreferredSize(new Dimension(200, 100));
        attributesSection.add(attributesListScrollPane);

        JPanel placeholder1 = new JPanel();
        placeholder1.setLayout(new FlowLayout());
        methodsLabel = new JLabel("Methods ");
        methodsMarkLabel = new JLabel("Marks: ");
        methodsMarkField = new JTextField(3);

        placeholder1.add(methodsLabel);
        placeholder1.add(methodsMarkLabel);
        placeholder1.add(methodsMarkField);

        methodsSection.add(placeholder1);


        methodList = new JPanel();
        methodList.setLayout(new BoxLayout(methodList, BoxLayout.Y_AXIS));

        // for(int i = 0; i < 20; i++){
        //     methodList.add(new JCheckBox("somethign extends somethng"));
        // }
        populateMethodsList(currentClassName);

        JScrollPane methodsListScrollPane = new JScrollPane(methodList);
        methodsListScrollPane.setPreferredSize(new Dimension(200, 100));
        methodsSection.add(methodsListScrollPane);

        inputSection.add(attributesSection);
        inputSection.add(methodsSection);


        allTests = new JTextArea(20, 10);
        allTests.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(allTests);

        saveBtnPanel = new JPanel();
        saveBtnPanel.setLayout(new FlowLayout());

        saveBtn = new JButton("Save Tests");
        deleteBtn = new JButton("Remove Tests");

        saveBtnPanel.add(saveBtn);
        saveBtnPanel.add(deleteBtn);

        displaySection.add(scrollPane);
        displaySection.add(saveBtnPanel);

        add(grid);
        grid.add(inputSection);
        grid.add(displaySection);

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                allTests.setText("");
            }
        });
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                printTest();
            }
        });

        loadClassesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedClassComboBox();
                selectedClassComboBox.setVisible(true);
            }
        });

        selectedClassComboBox.addActionListener(new ActionListener() {
            // private int selectedClassIndex;

            @Override
            public void actionPerformed(ActionEvent e) {
                String currentClassName = (String) selectedClassComboBox.getSelectedItem();
                populateAttributeList(currentClassName);
                populateMethodsList(currentClassName);
            }
        });
        selectedClassComboBox.addActionListener(new ActionListener() {
            // private int selectedClassIndex;

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassIndex = selectedClassComboBox.getSelectedIndex();

                if (selectedClassIndex >= 0 && selectedClassIndex < ClassesManager.getClasses().size()) {
                    ClassInformation selectedClass = ClassesManager.getClasses().get(selectedClassIndex);

                    // for (AttributeInformation attribute : selectedClass.getAttributes()) {
                    //     attributeListModel.addElement(attribute.toString());
                    // }
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }
            }
        });



    }

    private void populateAttributeList(String currentClassName){
        for(ClassInformation c : ClassesManager.getClasses()){
            if(c.getClassName().equals(currentClassName)){
                attributeList.removeAll();
                ArrayList<AttributeInformation> attributes = c.getAttributes();
                for(AttributeInformation a: attributes){
                    JPanel panel = new JPanel();
                    aCheckBox = new JCheckBox(a.getAttributeType() + " " + a.getAttributeName());
                    panel.add(aCheckBox);


                    NumberFormat format = NumberFormat.getIntegerInstance();
                    NumberFormatter formatter = new NumberFormatter(format);
                    formatter.setValueClass(Integer.class);
                    formatter.setMinimum(0); 

                    JFormattedTextField integerTextField = new JFormattedTextField(formatter); 
                    integerTextField.setColumns(3);



                    JLabel label = new JLabel("Input: ");
                    JLabel labelMark = new JLabel("Mark: ");
                    JTextField attrInput = new JTextField(5);
                    panel.add(label);
                    panel.add(attrInput);
                    panel.add(labelMark);
                    panel.add(integerTextField);
                    attributeList.add(panel);


                }
            }
        }
    }


    private void populateMethodsList(String currentClassName){
        for(ClassInformation c : ClassesManager.getClasses()){
            if(c.getClassName().equals(currentClassName)){
                
                methodList.removeAll();
                ArrayList<MethodInformation> method = c.getMethods();
                for(MethodInformation m: method){
                    JPanel panel = new JPanel();
                    JCheckBox mCheckBox = new JCheckBox(m.getMethodName());
                    panel.add(mCheckBox);

                    NumberFormat format = NumberFormat.getIntegerInstance();
                    NumberFormatter formatter = new NumberFormatter(format);
                    formatter.setValueClass(Integer.class);
                    formatter.setMinimum(0); 

                    JFormattedTextField integerTextField = new JFormattedTextField(formatter); 
                    integerTextField.setColumns(3);
                    panel.add(integerTextField);
                    methodList.add(panel);


                }
            }
        }
    }


    private void clearTestCases() {
        TestCaseManager.getTestCases().removeAll(attributeTests);
        TestCaseManager.getTestCases().removeAll(methodTests);

        methodTests.clear();
        attributeTests.clear();
    }

    private void addTestCases() {

        testCases.addAll(attributeTests);
        testCases.addAll(methodTests);


        TestCaseManager.getTestCases().addAll(attributeTests);
        TestCaseManager.getTestCases().addAll(methodTests);
    }


    public void printTest(){
        System.out.println("in here");
        clearTestCases();

        String nameCon = (String) selectedClassComboBox.getSelectedItem();
        String cName = (String) selectedClassComboBox.getSelectedItem();

        if(aCheckBox.isSelected()){
            System.out.println("ayooo");
            nameCon = checkAttributeCheckboxes(attributeList, nameCon, cName);
        }
        allTests.append(nameCon);
        addTestCases();
    }



    public String checkAttributeCheckboxes(JPanel Panel, String name, String className){
        Component[] components = Panel.getComponents();
        Iterator<Component> iterator = Arrays.asList(components).iterator();
        String input = "";
        int marks = 0;

        while(iterator.hasNext()){
            System.out.println("UGHHHHH");
            Component currentComponent = iterator.next();

            if(currentComponent instanceof JCheckBox){
                JCheckBox checkBox = (JCheckBox) currentComponent;
                String checkBoxText = (String) checkBox.getText();
                System.out.println(checkBoxText);
                String[] tempArray = checkBoxText.split(" ");
                String attrName = tempArray[1];

                if(checkBox.isSelected()){
                    name = name + checkBoxText + ", ";
                    if(iterator.hasNext()){
                        if(iterator.next() instanceof JTextField){
                            JTextField textField = (JTextField) iterator.next();
                            input = (String)textField.getText();
                        }
                    }

                    Object test = setAttributeType(checkBoxText, input);
                    System.out.println(test);
                    attributeTests.add(new AttributeValueTest(attrName, className, marks, test));
                    System.out.println(attributeTests.isEmpty());
                    
                    marks = 0;
                }
            }
        }
        name = name + "\n";
        return name;
    }

    public Object setAttributeType(String checkBoxText, String input){
        Object placeholder = "";

        String[] words = checkBoxText.split(" ");
        String attrType = words[0];

        String[] returnTypes = new String[]{"String", "int", "double", "float", "boolean", "char"};

        
        if(attrType.equals(returnTypes[0])){
            return input;
        } else if(attrType.equals(returnTypes[1])){
            int test = Integer.parseInt(input);
            return test;
        } else if(attrType.equals(returnTypes[2])){
            double test = Double.parseDouble(input);
            return test;
        } else if(attrType.equals(returnTypes[3])){
            float test = Float.parseFloat(input);
            return test;
        } else if(attrType.equals(returnTypes[4])){
            boolean test = Boolean.parseBoolean(input);
            return test;
        } else if(attrType.equals(returnTypes[5])){
            char test = input.charAt(0);
            return test;
        }
        

        return placeholder;
    }



    private void updateSelectedClassComboBox() {
        selectedClassComboBox.removeAllItems();
        for (ClassInformation c : ClassesManager.getClasses()) {
            String className = c.getClassName();
            selectedClassComboBox.addItem(className);
        }
    }


}
