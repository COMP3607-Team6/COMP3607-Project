package com.example.AssignmentSpecificationPortal.Tests;



import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
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


import com.example.TestCase;
import com.example.TestCaseManager;
import com.example.AssignmentSpecificationPortal.AttributeInformation;
import com.example.AssignmentSpecificationPortal.MethodInformation;
import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;
import com.example.BasicTest.ClassBasicTest;
import com.example.BasicTest.AttributeBasicTest;
import com.example.BasicTest.MethodBasicTest;

//
/* This class generalizes the some tests UI and gives the Base Layout and functionality of Most Tests
*/
public class BaseTest extends JPanel {
    protected ArrayList<TestCase> testCases;
    protected ArrayList<ClassBasicTest> classTests;
    protected ArrayList<MethodBasicTest> methodTests;
    protected ArrayList<AttributeBasicTest> attributeTests;

    protected JLabel testDescription;
    protected JLabel selectedClassLabel;
    protected JLabel markslab1;

    protected JPanel selectedClassPanel; 
    protected JPanel attributePanel;
    protected JPanel methodPanel;
    protected JPanel selectedClassPanel4;
    protected JPanel selectedClassPanel2;
    protected JPanel testPanel;
    protected JPanel savePanel;
    protected JPanel selectedClassPanel3;
    
    protected JButton loadClassesButton; 
    protected JButton saveTestsButton;
    protected JButton clearTestsButton;

    protected JComboBox<String> selectedClassComboBox;
   
    protected JCheckBox classCheckB;
    protected JCheckBox attCheckB;
    protected JCheckBox methCheckB;
    
    protected JTextArea nameTests;
    protected JTextField marksTextField1;
   
    protected JScrollPane scrollPane;
    

    protected String testType;

    public BaseTest() {
        classTests = new ArrayList<ClassBasicTest>();
        methodTests = new ArrayList<MethodBasicTest>();
        attributeTests = new ArrayList<AttributeBasicTest>();
        testCases = new ArrayList<TestCase>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        createDescriptionPanel(); // descriptionPanel

        createSelectedClassPanel(); // selectedClassPanel


        // CLASS CHECKBOX & MARKS
        selectedClassPanel2 = new JPanel();
        selectedClassPanel2.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        String ans = (String) selectedClassComboBox.getSelectedItem();
        classCheckB = new JCheckBox("Class: " + ans);
        classCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
        classCheckB.setPreferredSize(new Dimension(200, 20));
        
        markslab1 = new JLabel("Marks:");
        markslab1.setFont(new Font("Arial", Font.BOLD, 12));
        marksTextField1 = new JTextField(3);
        marksTextField1.setDocument(new IntegerDocument());

        selectedClassPanel2.add(classCheckB);
        selectedClassPanel2.add(markslab1);
        selectedClassPanel2.add(marksTextField1);


        // ATTRIBUTES MAIN CHECKBOX
        selectedClassPanel3 = new JPanel();
        selectedClassPanel3.setLayout(new FlowLayout(FlowLayout.LEADING));

        attCheckB = new JCheckBox("Attributes");
        attCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
       	
		selectedClassPanel3.add(attCheckB);
       
        // ATTRIBUTES INDIVIDUAL CHECKBOXES
        attributePanel = new JPanel();
        attributePanel.setLayout(new GridLayout(0, 3));

        JScrollPane aScrollPane = new JScrollPane(attributePanel);
        aScrollPane.setPreferredSize(new Dimension(600, 150));
        aScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        updateAttributeList(ans);


        // METHODS MAIN CHECKBOX
        selectedClassPanel4 = new JPanel();
        selectedClassPanel4.setLayout(new FlowLayout(FlowLayout.LEADING));
     
        methCheckB = new JCheckBox("Methods");
        methCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
      
		selectedClassPanel4.add(methCheckB);

        // METHODS INDIVIDUAL CHECKBOXES
        methodPanel = new JPanel();
        methodPanel.setLayout(new GridLayout(0, 3));

        JScrollPane mScrollPane = new JScrollPane(methodPanel);
        mScrollPane.setPreferredSize(new Dimension(600, 150));
        mScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        updateMethodList(ans);

     	
        // PANEL TO DISPLAY TESTS
        testPanel = new JPanel();
        testPanel.setLayout(new FlowLayout());

        nameTests = new JTextArea(10, 20);
        nameTests.setEditable(false);
        scrollPane = new JScrollPane(nameTests);

        testPanel.add(scrollPane);

        createSaveTestAndClearTestsButtonPanel();

        add(testDescription);
        add(selectedClassPanel);
        add(selectedClassPanel2); // class name and marks
        add(selectedClassPanel3); // atts main checkbox
        add(aScrollPane); // att checkboxes
        add(selectedClassPanel4); // methods main checkbox
        add(mScrollPane); // met checkboxes
        add(testPanel); // white box to print tests
        add(savePanel);

        attachListeners();
    }

    //Creates the description for the Specific test
    protected void createDescriptionPanel() {
        testDescription = new JLabel();
        testDescription.setFont(new Font("Arial", Font.ITALIC, 15));
        testDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public JPanel createSelectedClassPanel() {
        selectedClassPanel = new JPanel();
        selectedClassPanel.setLayout(new FlowLayout());
        selectedClassLabel = new JLabel("Class:");
        loadClassesButton = new JButton("Load classes");
        selectedClassComboBox = new JComboBox<String>();

        selectedClassPanel.add(selectedClassLabel);
        selectedClassPanel.add(selectedClassComboBox);
        selectedClassPanel.add(loadClassesButton);

        updateSelectedClassComboBox();

        if (selectedClassComboBox.getItemCount() == 0) {
            selectedClassComboBox.setVisible(false);
        }

        return selectedClassPanel;
    }

    protected void createSaveTestAndClearTestsButtonPanel() {
        savePanel = new JPanel();
        savePanel.setLayout(new FlowLayout());
        saveTestsButton = new JButton("Save Test");
        savePanel.add(saveTestsButton);
        clearTestsButton = new JButton("Clear Tests");
        savePanel.add(clearTestsButton);
    }

    protected void attachListeners() {
        loadClassesButton.addActionListener(e -> updateSelectedClassComboBox());

        saveTestsButton.addActionListener(e -> printTest());

        clearTestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameTests.setText("");
                clearTestCases();
            }
        });

        
        selectedClassComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ans = (String) selectedClassComboBox.getSelectedItem();
                classCheckB.setText("Class: " + ans);
                
                updateAttributeList(ans);
                attributePanel.revalidate();
                attributePanel.repaint();

                updateMethodList(ans);
                methodPanel.revalidate();
                methodPanel.repaint();
            }
        });
    }

    //Adds Classes To Class Combo Box
    protected void updateSelectedClassComboBox() {
        selectedClassComboBox.removeAllItems();
        for (ClassInformation c : ClassesManager.getClasses()) {
            selectedClassComboBox.addItem(c.getClassName());
        }
    }

    //Updates the Attributes In the List
    protected void updateAttributeList(String ans) {
        for(ClassInformation c : ClassesManager.getClasses()){
            if(c.getClassName().equals(ans)){
                attributePanel.removeAll();
                ArrayList<AttributeInformation> attributes = c.getAttributes();
                for(AttributeInformation a:attributes){
                    
                    JPanel panel = new JPanel();
                    JCheckBox aCheckBox = new JCheckBox(a.getAttributeName());
                    
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

    //Updates the methods in the List
    protected void updateMethodList(String ans) {
        for(ClassInformation c : ClassesManager.getClasses()){
            if(c.getClassName().equals(ans)){
                methodPanel.removeAll();
                ArrayList<MethodInformation> methods = c.getMethods();
                for(MethodInformation m:methods){
                    JPanel panel = new JPanel();
                    JCheckBox mCheckBox = new JCheckBox(m.getMethodName());

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

    //Clears the test cases from the ArrayList
    protected void clearTestCases() {
        TestCaseManager.getTestCases().removeAll(classTests);
        TestCaseManager.getTestCases().removeAll(attributeTests);
        TestCaseManager.getTestCases().removeAll(methodTests);

        classTests.clear();
        methodTests.clear();
        attributeTests.clear();

        nameTests.setText("");
    }

    protected void addTestCases() {
        testCases.addAll(classTests);
        testCases.addAll(attributeTests);
        testCases.addAll(methodTests);

        TestCaseManager.getTestCases().addAll(classTests);
        TestCaseManager.getTestCases().addAll(attributeTests);
        TestCaseManager.getTestCases().addAll(methodTests);
    }

    //Prints Desired Tests To Screen
    public void printTest() {

        clearTestCases();

        String nameCon = (String) selectedClassComboBox.getSelectedItem();
        String cName = (String) selectedClassComboBox.getSelectedItem();

        String textFieldValue = marksTextField1.getText(); 
        int marks = Integer.parseInt(textFieldValue);

        if (classCheckB.isSelected() == true) {
            classTests.add(new ClassBasicTest(marks, cName, testType));
            nameCon = nameCon + "\n" + "-Class [" + marks + " marks]\n";
        }
        if (attCheckB.isSelected() == true) {
            nameCon = nameCon + "-Attributes: \n";
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

    //Detects Which Checkboxes In the Lists Have Been Selected and Creates Test Case
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
                        String[] parts = checkBoxText.split(" ");
                        String text = "";
                        String classCompName ="";
                        
                        if (parts.length == 1) {
                            text = parts[0]; // Only one word
                            classCompName = parts[0];
                        } else if (parts.length >= 2) {
                            text = parts[0] + " " + parts[1]; // Concatenate the first and second parts
                            classCompName = parts[1];
                        }

                        if (checkBox.isSelected()) {
                            currentComponent = iterator.next();
                            currentComponent = iterator.next(); // skip marks label
                           
                            if (currentComponent instanceof JTextField) {
                                JTextField textfield =(JTextField) currentComponent;
                                String textFieldValue = textfield.getText(); 
                                marks = Integer.parseInt(textFieldValue); 
                                name = name + text + " ["+marks+" mks], \n";
                            }
                            if(check == 0){
                                attributeTests.add(new AttributeBasicTest(marks,className, classCompName, testType));
                            }
                            else if(check == 1){
                                methodTests.add(new MethodBasicTest(marks,className, classCompName, testType));
                            }
                        
                            marks=0;                    
                        } 
                    } 
                }
            }
        } 
        name = name + "\n";
        return name; 
    }

    // Restricts input to integers only
    
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

