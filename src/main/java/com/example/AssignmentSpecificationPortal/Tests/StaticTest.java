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

public class StaticTest extends JPanel {

    // private ArrayList<ClassInformation> classes;
    private JLabel testDescription;

    private JPanel selectedClassPanel;
    private JPanel selectedClassPanel2;
    private JPanel selectedClassPanel3;
    private JPanel selectedClassPanel4;
    private JPanel attributePanel;
    private JPanel methodPanel;
    private JPanel testPanel;

    private JLabel selectedClassLabel;
    private JButton loadClassesButton;
    private JComboBox<String> selectedClassComboBox;
    private JCheckBox classCheckB;
    private JCheckBox attCheckB;
    private JCheckBox methCheckB;

    private JTextField marksTextField1;
    private JTextArea nameTests;

    private ArrayList<ClassBasicTest> classTests;
    private ArrayList<MethodBasicTest> methodTests;
    private ArrayList<AttributeBasicTest> attributeTests;
    private ArrayList<TestCase> testCases;

    public StaticTest() {
        // this.classes = classes;

        classTests = new ArrayList<ClassBasicTest>();
        methodTests = new ArrayList<MethodBasicTest>();
        attributeTests = new ArrayList<AttributeBasicTest>();
        testCases = new ArrayList<TestCase>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        testDescription = new JLabel("static test description. short one line description of test");
        testDescription.setFont(new Font("Arial", Font.ITALIC, 15));
        testDescription.setAlignmentX(Component.LEFT_ALIGNMENT);

        selectedClassPanel = new JPanel();
        selectedClassPanel.setLayout(new FlowLayout());
       // selectedClassPanel.setPreferredSize(new Dimension(100, 60));
        
        selectedClassLabel = new JLabel("Class:");
        loadClassesButton = new JButton("Load classes");
        // selectedClassComboBox = new JComboBox<String>(new String[]{"choose class"});
        selectedClassComboBox = new JComboBox<String>();

        for (ClassInformation c : ClassesManager.getClasses()) {
            String className = c.getClassName();
            selectedClassComboBox.addItem(className);
        }
        selectedClassPanel.add(selectedClassLabel);
        selectedClassPanel.add(selectedClassComboBox);
        selectedClassPanel.add(loadClassesButton);

         if (selectedClassComboBox.getItemCount() == 0) {
            selectedClassComboBox.setVisible(false);
        }
        
        selectedClassPanel2 = new JPanel();
        selectedClassPanel2.setLayout(new FlowLayout());
        //selectedClassPanel2.setPreferredSize(new Dimension(100, 60));
        String ans = (String) selectedClassComboBox.getSelectedItem();
        classCheckB = new JCheckBox("Class - "+ans);
    
        classCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel markslab1 = new JLabel("Marks:");
        markslab1.setFont(new Font("Arial", Font.PLAIN, 10));
	    marksTextField1 = new JTextField(3);
		marksTextField1.setFont(new Font("Arial", Font.PLAIN, 15));
			
		selectedClassPanel2.add(classCheckB);
        selectedClassPanel2.add(markslab1);
		selectedClassPanel2.add(marksTextField1);	

        selectedClassPanel3 = new JPanel();
        selectedClassPanel3.setLayout(new FlowLayout());
        

        
        attCheckB = new JCheckBox("Attributes");
        attCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
       
			
		selectedClassPanel3.add(attCheckB);
       

        attributePanel = new JPanel();
        attributePanel.setLayout(new GridLayout(3, 3));
        updateAttributeList(ans);

        methodPanel = new JPanel();
        methodPanel.setLayout(new GridLayout(3, 3));
        updateMethodList(ans);
      
        selectedClassPanel4 = new JPanel();
        selectedClassPanel4.setLayout(new FlowLayout());
        
        methCheckB = new JCheckBox("Methods");
        methCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
      
			
		selectedClassPanel4.add(methCheckB);
     

        testPanel = new JPanel();
        testPanel.setLayout(new FlowLayout());

        nameTests = new JTextArea(10, 20);
        nameTests.setEditable(false); 
        JScrollPane scrollPane = new JScrollPane(nameTests);

        testPanel.add(scrollPane);

        JPanel savePanel = new JPanel();
        savePanel.setLayout(new FlowLayout());
        JButton saveTestsButton=new JButton("Save Test");
        savePanel.add(saveTestsButton);
        JButton clearTestsButton=new JButton("Clear Tests");
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

        loadClassesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedClassComboBox();
                selectedClassComboBox.setVisible(true);
            }
        });

        saveTestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTest();
            }
        });

        clearTestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameTests.setText("");
            }
        });

        selectedClassComboBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               String ans =(String) selectedClassComboBox.getSelectedItem();
                classCheckB.setText("Class - "+ans);
                updateAttributeList(ans);
                attributePanel.revalidate();
                attributePanel.repaint();
                updateMethodList(ans);
                methodPanel.revalidate();
                methodPanel.repaint();
            }
        }); 

        selectedClassComboBox.addActionListener(new ActionListener() {
            private int selectedClassIndex;

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassIndex = selectedClassComboBox.getSelectedIndex();

                if (selectedClassIndex >= 0 && selectedClassIndex < ClassesManager.getClasses().size()) {
                    ClassInformation selectedClass = ClassesManager.getClass(selectedClassIndex);
                    
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }
            }
        });
    }

    private void updateSelectedClassComboBox() {
        selectedClassComboBox.removeAllItems();
        for (ClassInformation c : ClassesManager.getClasses()) {
            String className = c.getClassName();
            selectedClassComboBox.addItem(className);
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

     private void updateMethodList(String ans) {
        for(ClassInformation c : ClassesManager.getClasses()){
                    if(c.getClassName().equals(ans)){
                        methodPanel.removeAll();
                        ArrayList<MethodInformation> methods = c.getMethods();
                        for(MethodInformation m:methods){
                         
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

    public void printTest() {
        
        TestCaseManager.getTestCases().removeAll(classTests);
        TestCaseManager.getTestCases().removeAll(attributeTests);
        TestCaseManager.getTestCases().removeAll(methodTests);

        classTests.clear();
        methodTests.clear();
        attributeTests.clear();

        String nameCon=(String) selectedClassComboBox.getSelectedItem();
        String cName=(String) selectedClassComboBox.getSelectedItem();
        String marks = (String) marksTextField1.getText();
        String testType ="static";

        if(classCheckB.isSelected()==true){      
            classTests.add(new ClassBasicTest(0,cName, testType));
            nameCon = nameCon + "\n" +"-Class [" + marks +" mark]\n";
        }
        if(attCheckB.isSelected()==true){
            nameCon = nameCon+"-Attributes ";
            Component[] components = attributePanel.getComponents();
            for (Component component : components) {
                if (component instanceof JPanel) {
                    JPanel Panel1 = (JPanel) component;
                    nameCon= checkAttributeCheckboxes(Panel1,nameCon,cName);
                }
            }
        }
        if(methCheckB.isSelected()==true){
            nameCon = nameCon+"-Methods ";
           // nameCon= checkMethodCheckboxes(methodPanel,nameCon,cName);
            Component[] components = methodPanel.getComponents();
            for (Component component : components) {
                if (component instanceof JPanel) {
                    JPanel Panel1 = (JPanel) component;
                    nameCon= checkMethodCheckboxes(Panel1,nameCon,cName);
                }
            }
            
        }
        nameCon = nameCon +"---------------------------\n";
        nameTests.append(nameCon);

        testCases.addAll(classTests);
        testCases.addAll(attributeTests);
        testCases.addAll(methodTests);

        TestCaseManager.getTestCases().addAll(classTests);
        TestCaseManager.getTestCases().addAll(attributeTests);
        TestCaseManager.getTestCases().addAll(methodTests);

    }

    public String checkAttributeCheckboxes(JPanel Panel,String name,String className) {
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
                        attributeTests.add(new AttributeBasicTest(marks,className, checkBoxText, "static"));
                        marks=0;
                   } 
             }
        }
        name = name + "\n";
        return name; 
    }

   
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
                        
                        methodTests.add(new MethodBasicTest(marks,className, checkBoxText, "static"));
                        marks=0;
                   } 
             }
        }
        name = name + "\n";
        return name; 
    }

    
}
