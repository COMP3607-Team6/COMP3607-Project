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
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.example.TestCase;
import com.example.TestCaseManager;
import com.example.AssignmentSpecificationPortal.AttributeInformation;
import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;
import com.example.AssignmentSpecificationPortal.MethodInformation;
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
    private JCheckBox mCheckBox;

    private ArrayList<AttributeValueTest> attributeTests;
    private ArrayList<MethodValueTest> methodTests;
    private ArrayList<TestCase> testCases;


    public ValueTest(String description){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        methodTests = new ArrayList<MethodValueTest>();
        attributeTests = new ArrayList<AttributeValueTest>();
        testCases = new ArrayList<TestCase>();

        testDescription = new JLabel(description);
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

        //whole panel for work
        grid = new JPanel();
        grid.setLayout(new GridLayout(1, 2));

        //right side of panel
        displaySection = new JPanel();
        displaySection.setLayout(new BoxLayout(displaySection, BoxLayout.Y_AXIS));

        //left side of panel
        inputSection = new JPanel();
        inputSection.setLayout(new BoxLayout(inputSection, BoxLayout.Y_AXIS));


        //top part of input section
        attributesSection = new JPanel();
        attributesSection.setLayout(new BoxLayout(attributesSection, BoxLayout.Y_AXIS));

        //bottom part of input section
        methodsSection = new JPanel();
        methodsSection.setLayout(new BoxLayout(methodsSection, BoxLayout.Y_AXIS));

        //adds title and marks input to attributes section
        JPanel placeholder = new JPanel();
        placeholder.setLayout(new FlowLayout());

        attributesLabel = new JLabel("Attributes");
        attributesMarkLabel = new JLabel("Marks: ");
        attributesMarkField = new JTextField(3);
        attributesMarkField.setText("1");
        attributesMarkField.setDocument(new IntegerDocument());

        placeholder.add(attributesLabel);
        placeholder.add(attributesMarkLabel);
        placeholder.add(attributesMarkField);

        attributesSection.add(placeholder);

        //list that displays all the attributes from the class
        attributeList = new JPanel();
        attributeList.setLayout(new BoxLayout(attributeList, BoxLayout.Y_AXIS));


        //instanciates the attribute and method checkboxes
        aCheckBox = new JCheckBox();
        mCheckBox = new JCheckBox();
        
        //populate the attributeList panel
        populateAttributeList(currentClassName);

        //creates the scrollable panel for attribute list
        JScrollPane attributesListScrollPane = new JScrollPane(attributeList);
        attributesListScrollPane.setPreferredSize(new Dimension(200, 100));
        attributesSection.add(attributesListScrollPane);


        //creates the label titles for method section
        JPanel placeholder1 = new JPanel();
        placeholder1.setLayout(new FlowLayout());
        methodsLabel = new JLabel("Methods ");
        methodsMarkLabel = new JLabel("Marks: ");
        methodsMarkField = new JTextField(3);
        methodsMarkField.setText("1");
        methodsMarkField.setDocument(new IntegerDocument());
    
        placeholder1.add(methodsLabel);
        placeholder1.add(methodsMarkLabel);
        placeholder1.add(methodsMarkField);

        methodsSection.add(placeholder1);

        //creates panel to display all methods of class
        methodList = new JPanel();
        methodList.setLayout(new BoxLayout(methodList, BoxLayout.Y_AXIS));

        //populate method list panel
        populateMethodsList(currentClassName);

        //makes method list panel scrollable
        JScrollPane methodsListScrollPane = new JScrollPane(methodList);
        methodsListScrollPane.setPreferredSize(new Dimension(200, 100));
        methodsSection.add(methodsListScrollPane);

        // add top and bottom sections to the left section
        inputSection.add(attributesSection);
        inputSection.add(methodsSection);


        //textarea to display the information on the attr and methods of the class
        allTests = new JTextArea(20, 10);
        allTests.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(allTests);

        // panel for add and clear buttons
        saveBtnPanel = new JPanel();
        saveBtnPanel.setLayout(new FlowLayout());

        saveBtn = new JButton("Save Tests");
        deleteBtn = new JButton("Clear Tests");

        saveBtnPanel.add(saveBtn);
        saveBtnPanel.add(deleteBtn);

        //adds sections to display section
        displaySection.add(scrollPane);
        displaySection.add(saveBtnPanel);

        //add grid panel to panel
        add(grid);
        grid.add(inputSection);
        grid.add(displaySection);



        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                allTests.setText("");
                clearTestCases();//BIG CHANGE
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
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentClassName = (String) selectedClassComboBox.getSelectedItem();
                populateAttributeList(currentClassName);
                attributeList.revalidate();
                attributeList.repaint();//BIG CHANGE

                populateMethodsList(currentClassName);
                methodList.revalidate();
                methodList.repaint();//BIG CHANGE
                
            }
        });

        selectedClassComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassIndex = selectedClassComboBox.getSelectedIndex();

                if (selectedClassIndex >= 0 && selectedClassIndex < ClassesManager.getClasses().size()) {
                    ClassInformation selectedClass = ClassesManager.getClasses().get(selectedClassIndex);
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
                    JTextField attrInput = new JTextField(5);

                    aCheckBox = new JCheckBox(a.getAttributeType() + " " + a.getAttributeName());

                    panel.add(aCheckBox);
                    panel.add(attrInput);
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
                    if(!m.getMethodType().equals("void")){
                        JPanel panel = new JPanel();
                        
                        if(m.getIsAbstract().equals("abstract")){
                            String methodSig = m.getAccessType() + "  " + m.getIsAbstract() + "  " + m.getMethodType() + "  " + m.getMethodName() + "  " + "(" + m.getMethodParameters() + ")";
                            mCheckBox = new JCheckBox(methodSig);
                        } else {
                            String methodSig = m.getAccessType() + "  " + m.getMethodType() + "  " + m.getMethodName() + "  " + "(" + m.getMethodParameters() + ")";
                            mCheckBox = new JCheckBox(methodSig);
                        }
                        JTextField methodOutput = new JTextField(5);
                        
                        panel.add(mCheckBox);
                        panel.add(methodOutput);

                        methodList.add(panel);
                    }
                    
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
        clearTestCases();

        String nameCon = (String) selectedClassComboBox.getSelectedItem();
        String cName = (String) selectedClassComboBox.getSelectedItem();

        
       

       
       

        if(aCheckBox.isSelected()){
            String mark = (String) attributesMarkField.getText();
            int marks = Integer.parseInt(mark);
            nameCon = "Class: " + nameCon + "\n-Attributes:\n";
            nameCon = checkCheckboxesA(attributeList, nameCon, cName, 0, marks);
        }
         if(mCheckBox.isSelected()){
             String markM = (String) methodsMarkField.getText();
             int marksM = Integer.parseInt(markM);
             nameCon = nameCon + "\n-Methods:\n";
             nameCon = checkCheckboxesM(methodList, nameCon, cName, 1, marksM);
        }
        
        nameCon = nameCon + "---------------------------\n";
        allTests.append(nameCon);

        addTestCases();
    }

    public String checkCheckboxesA(JPanel Panel, String name, String className, int check, int mark){
        System.out.println("inside checkA method");
        for(Component component : Panel.getComponents()){
            String input = "";

            if(component instanceof JPanel){
                JPanel innerPanel = (JPanel) component;
                Component[] Comps = innerPanel.getComponents();
                Iterator<Component> iterator = Arrays.asList(Comps).iterator();

                while(iterator.hasNext()){
                    Component currentComponent = iterator.next();

                    if(currentComponent instanceof JCheckBox){
                        JCheckBox checkBox = (JCheckBox) currentComponent;
                        String checkBoxText = checkBox.getText();

                        String[] tempArray = checkBoxText.split(" ");
                        String attrName = tempArray[1];
                        String attrType = tempArray[0];

                        if(checkBox.isSelected()){
                            currentComponent = iterator.next();

                            if (currentComponent instanceof JTextField){
                                JTextField textfield =(JTextField) currentComponent;
                                input = (String) textfield.getText();  
                            }

                            Object attrTypeObject = setAttributeType(attrType, input);
                            if(check == 0){
                                name = name + checkBoxText + " - Expected input: " + input + " [" + mark + "]\n";
                                attributeTests.add(new AttributeValueTest(attrName, className, mark, attrTypeObject));
                            }
                        }
                    }
                }
            }
        }

        System.out.println(attributeTests.size());
        name = name + "\n";
        return name;
    }

    public String checkCheckboxesM(JPanel Panel, String name, String className, int check, int mark){

        System.out.println("inside checkM method");

        String expectedOutput = "";

        String methodAccessType = "";
        String methodIsAbstract = "";
        String methodReturnType = "";
        String methodName = "";
        String methodParameters = "";
        
        for(Component component : Panel.getComponents()){
            if(component instanceof JPanel){

                JPanel innerPanel = (JPanel) component;
                Component[] Comps = innerPanel.getComponents();
                Iterator<Component> iterator = Arrays.asList(Comps).iterator(); 

                while (iterator.hasNext()){
                    Component currentComponent = iterator.next();
                    if(currentComponent instanceof JCheckBox){
                        JCheckBox checkBox = (JCheckBox) currentComponent;
                        String checkBoxText = checkBox.getText();
                        
                        //split up method signature by "  "
                        String[] parts = checkBoxText.split("  ");

                    
                        if(checkBox.isSelected()){

                            currentComponent = iterator.next();

                            if(currentComponent instanceof JTextField){
                                JTextField textfield =(JTextField) currentComponent;
                                expectedOutput = (String) textfield.getText();
                            }

                            if(parts[1].equals("abstract")){
                                methodAccessType = parts[0];
                                methodIsAbstract = parts[1];
                                methodReturnType = parts[2];
                                methodName = parts[3];
                                methodParameters = parts[4];
                            } else{
                                methodAccessType = parts[0];
                                methodReturnType = parts[1];
                                methodName = parts[2];
                                methodParameters = parts[3];
                            }
                            name = name + checkBoxText + " -Expected Output: " + expectedOutput + " [" + mark + "]\n";

                            Object expectedOutputValue = getExpectedValue(methodReturnType, expectedOutput);
                            ArrayList<Object> methodParametersObjectTypes = new ArrayList<>();

                            if(methodParameters.length() == 2){
                                methodTests.add(new MethodValueTest(methodName, className, mark, methodParametersObjectTypes, expectedOutputValue));
                            } else {
                                methodParametersObjectTypes = setMethodParameters(methodParameters);
                                methodTests.add(new MethodValueTest(methodName, className, mark, methodParametersObjectTypes, expectedOutputValue));
                            }

                            
                            // methodParametersObjectTypes.clear();
                        }

                    }
                }
            }
        }
        name = name + "\n";
        System.out.println("size of ting: " + methodTests.size());
        
        return name;
    }

    public ArrayList<Object> setMethodParameters(String methodParameters){

        ArrayList<Object> methodParametersObject = new ArrayList<>();
        String [] parts = methodParameters.split(", ");
        
        int trial = parts.length - 1;// get last element in array
        parts[0] = parts[0].substring(1);
        parts[trial] = parts[trial].substring(0, parts[trial].length() - 1);
        for(String parting : parts){
            System.out.println(parting);
        }
        
        for(int i = 0; i < parts.length;i++){
            String placeholder = parts[i];
            String[] parts2 = placeholder.split(" ");

            Object tempObj = setParamType(parts2[0], parts2[1]);
            methodParametersObject.add(tempObj);
        }

        return methodParametersObject;
    }

    public Object getExpectedValue(String methodReturnType, String expectedOutput){

        
        Object placeholder = "";

        String[] returnTypes = new String[]{"String", "int", "double", "float", "boolean", "char"};
        

        if(methodReturnType.equals(returnTypes[0])){
            return expectedOutput;
        } else if(methodReturnType.equals(returnTypes[1])){
            Object requiredObject = Integer.valueOf(expectedOutput);
            return requiredObject;
        } else if(methodReturnType.equals(returnTypes[2])){
            Object requiredObject = Double.valueOf(expectedOutput);
            return requiredObject;
        } else if(methodReturnType.equals(returnTypes[3])){
            Object requiredObject = Float.valueOf(expectedOutput);
            return requiredObject;
        } else if(methodReturnType.equals(returnTypes[4])){
            Object requiredObject = Boolean.valueOf(expectedOutput);
            return requiredObject;
        } else if(methodReturnType.equals(returnTypes[5])){
            Object requiredObject = expectedOutput.charAt(0);
            return requiredObject;
        }
        return placeholder;
    }



    public Object setAttributeType(String type, String input){
        Object placeholder = "";

        String[] returnTypes = new String[]{"String", "int", "double", "float", "boolean", "char"};

        
        if(type.equals(returnTypes[0])){
            return input;
        } else if(type.equals(returnTypes[1])){
            int requiredAttributeType = Integer.parseInt(input);
            return requiredAttributeType;
        } else if(type.equals(returnTypes[2])){
            double requiredAttributeType = Double.parseDouble(input);
            return requiredAttributeType;
        } else if(type.equals(returnTypes[3])){
            float requiredAttributeType = Float.parseFloat(input);
            return requiredAttributeType;
        } else if(type.equals(returnTypes[4])){
            boolean requiredAttributeType = Boolean.parseBoolean(input);
            return requiredAttributeType;
        } else if(type.equals(returnTypes[5])){
            char requiredAttributeType = input.charAt(0);
            return requiredAttributeType;
        }

        return placeholder;
    }

    public Object setParamType(String type, String input){

        String[] returnTypes = new String[]{"String", "int", "double", "float", "boolean", "char"};

        
        if(type.equals(returnTypes[0])){
            return input;
        } else if(type.equals(returnTypes[1])){
            int parameterTypes = Integer.parseInt(input);
            return parameterTypes;
        } else if(type.equals(returnTypes[2])){
            double parameterTypes = Double.parseDouble(input);
            return parameterTypes;
        } else if(type.equals(returnTypes[3])){
            float parameterTypes = Float.parseFloat(input);
            return parameterTypes;
        } else if(type.equals(returnTypes[4])){
            boolean parameterTypes = Boolean.parseBoolean(input);
            return parameterTypes;
        } else if(type.equals(returnTypes[5])){
            char parameterTypes = input.charAt(0);
            return parameterTypes;
        } else {
            String parameterTypes = input;
            return parameterTypes;
        }
    }



    private void updateSelectedClassComboBox() {
        selectedClassComboBox.removeAllItems();
        for (ClassInformation c : ClassesManager.getClasses()) {
            String className = c.getClassName();
            selectedClassComboBox.addItem(className);
        }
    }

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
