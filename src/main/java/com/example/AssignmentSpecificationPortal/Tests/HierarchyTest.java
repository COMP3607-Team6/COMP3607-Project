package com.example.AssignmentSpecificationPortal.Tests;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.example.TestCase;
import com.example.TestCaseManager;
// import com.example.AssignmentSpecificationPortal.AttributeInformation;
import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;
import com.example.HierarchyTests.SubClassTest;
import com.example.HierarchyTests.SubTypeTest;

public class HierarchyTest extends JPanel {
    /* This class gives the layout and behaviour specific to Hierarchy Test
     */

    private JLabel testDescription;

    private JPanel grid;
    private JPanel extendsSection;
    private JPanel implementsSection;
    private JPanel testsSection;
    private JPanel displaySection;
    private JTextArea allTests;

    private JPanel saveBtnPanel;
    private JButton saveBtn;
    private JButton deleteBtn;

    private JLabel extendsSectionTitle;
    private JPanel extendsList;

    private JLabel implementsSectionTitle;
    private JPanel implementsList;

    private JLabel extendsMarksLabel;
    private JTextField extendsMarksField;

    private JLabel implementsMarksLabel;
    private JTextField implementsMarksField;

    private ArrayList<SubClassTest> subClassTests;
    private ArrayList<SubTypeTest> subTypeTests;
    private ArrayList<TestCase> testCases;

    private JCheckBox extendsCheckBox;
    private JCheckBox implementsCheckBox;

    public HierarchyTest(String description) {

        subClassTests = new ArrayList<SubClassTest>();
        subTypeTests = new ArrayList<SubTypeTest>();
        testCases = new ArrayList<TestCase>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        testDescription = new JLabel(description);
        testDescription.setFont(new Font("Arial", Font.ITALIC, 22));
        testDescription.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(testDescription);


        grid = new JPanel();
        grid.setLayout(new GridLayout(1, 2));
        
        testsSection = new JPanel();
        testsSection.setLayout(new BoxLayout(testsSection, BoxLayout.Y_AXIS));



        extendsSection = new JPanel();
        extendsSection.setLayout(new BoxLayout(extendsSection, BoxLayout.Y_AXIS));



        JPanel test = new JPanel();
        test.setLayout(new FlowLayout());

        extendsSectionTitle = new JLabel("Inheritance Tests: ");
        extendsMarksLabel = new JLabel("Marks: ");
        extendsMarksField = new JTextField(3);
        extendsMarksField.setText("0");

        test.add(extendsSectionTitle);
        test.add(extendsMarksLabel);
        test.add(extendsMarksField);
        
        extendsList = new JPanel();
        extendsList.setLayout(new BoxLayout(extendsList, BoxLayout.Y_AXIS));

        populateExtendsList();  

        JScrollPane extendsListScrollPane = new JScrollPane(extendsList);
        extendsListScrollPane.setPreferredSize(new Dimension(200, 100));

        extendsSection.add(test);
        extendsSection.add(extendsListScrollPane);


        implementsSection = new JPanel();
        implementsSection.setLayout(new BoxLayout(implementsSection, BoxLayout.Y_AXIS));

        JPanel test1 = new JPanel();
        test1.setLayout(new FlowLayout());

        implementsMarksLabel = new JLabel("Marks");
        implementsMarksField = new JTextField(3);
        implementsMarksField.setText("0");
        

        implementsList = new JPanel();
        implementsList.setLayout(new BoxLayout(implementsList, BoxLayout.Y_AXIS));


        populateImplementsList();
        

        JScrollPane implementsListScrollPane = new JScrollPane(implementsList);
        implementsListScrollPane.setPreferredSize(new Dimension(200, 100));


        implementsSectionTitle = new JLabel("Implementation Tests: ");
        test1.add(implementsSectionTitle);
        test1.add(implementsMarksLabel);
        test1.add(implementsMarksField);

        implementsSection.add(test1);
        implementsSection.add(implementsListScrollPane);

        testsSection.add(extendsSection);
        testsSection.add(implementsSection);

        allTests = new JTextArea(20, 10);
        allTests.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(allTests);

        displaySection = new JPanel();
        displaySection.setLayout(new BoxLayout(displaySection, BoxLayout.Y_AXIS));

        saveBtnPanel = new JPanel();
        saveBtnPanel.setLayout(new FlowLayout());

        saveBtn = new JButton("Save Tests");
        deleteBtn = new JButton("Remove Tests");

        saveBtnPanel.add(saveBtn);
        saveBtnPanel.add(deleteBtn);

        displaySection.add(scrollPane);
        displaySection.add(saveBtnPanel);

        add(grid);
        grid.add(testsSection);
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
                printTests();
            }
        });
    }


    //method to add extends
    public void populateExtendsList(){
        extendsList.removeAll();
        for(ClassInformation c: ClassesManager.getClasses()){
            String extendedOrImplementedClass = c.getExtendedOrImplementedClass();
            String name = c.getClassName();
            if (!extendedOrImplementedClass.isEmpty() && extendedOrImplementedClass != null 
                && c.getExtendsOrImplements().equals("extends")){
                extendsCheckBox = new JCheckBox(name + " extends " + extendedOrImplementedClass);
                extendsList.add(extendsCheckBox);
            }
        }
    }
    


    //method to add implements
    public void populateImplementsList(){
        implementsList.removeAll();
        for(ClassInformation c: ClassesManager.getClasses()){
            String extendedOrImplementedClass = c.getExtendedOrImplementedClass();
            String name = c.getClassName();
            if (!extendedOrImplementedClass.isEmpty() && extendedOrImplementedClass != null 
                && c.getExtendsOrImplements().equals("implements")){
                implementsCheckBox = new JCheckBox(name + " implements " + extendedOrImplementedClass);
                implementsList.add(implementsCheckBox);
            }
        }
    }

    // method to get checked extends test checkboxes
    public void getCheckedExtends() {
        String cName = "";
        String superClassName = "";
        String inheritance = "";
        String marks = extendsMarksField.getText();
        int markNum = 0;

        if (!marks.isEmpty()) {
            markNum = Integer.parseInt(marks);
        }
        
        for (Component comp : extendsList.getComponents()) {
            if (comp instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) comp;
                if (checkBox.isSelected()) {
                    String text = checkBox.getText();
                    String[] parts = text.split(" extends ");
                    if (parts.length > 1) {
                        cName = parts[0];
                        superClassName = parts[1];
                        subClassTests.add(new SubClassTest(cName,superClassName,markNum));

                        inheritance = cName + " extends " + superClassName + " [" + markNum + " Marks]";
                        inheritance = inheritance + "\n";
                        allTests.append(inheritance);
                    }
                }
            }
        }
    }

    // method to get checked implements test checkboxes
    public void getCheckedImplements() {
        String cName = "";
        String superClassName = "";
        String inheritance = "";
        String marks = implementsMarksField.getText();
        int markNum = 0;

        if (!marks.isEmpty()) {
            markNum = Integer.parseInt(marks);
        }
        
        for (Component comp : implementsList.getComponents()) {
            if (comp instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) comp;
                if (checkBox.isSelected()) {
                    String text = checkBox.getText();
                    String[] parts = text.split(" implements ");
                    if (parts.length > 1) {
                        cName = parts[0];
                        superClassName = parts[1];
                        subTypeTests.add(new SubTypeTest(cName,superClassName,markNum));

                        inheritance = cName + " implements " + superClassName + " [" + markNum + " Marks]";
                        inheritance = inheritance + "\n";
                        allTests.append(inheritance);
                    }
                }
            }
        }
    }
    

    //adds methods to tests and prints to screen
    public void printTests(){
        TestCaseManager.getTestCases().removeAll(subClassTests);
        TestCaseManager.getTestCases().removeAll(subTypeTests);

        subClassTests.clear();
        subTypeTests.clear();

        allTests.setText("");

        getCheckedExtends();
        getCheckedImplements();

        testCases.addAll(subClassTests);
        testCases.addAll(subTypeTests);
        
        TestCaseManager.getTestCases().addAll(subClassTests);
        TestCaseManager.getTestCases().addAll(subTypeTests);
    }

}