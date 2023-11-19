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
import com.example.BasicTest.ClassBasicTest;
import com.example.BasicTest.AttributeBasicTest;
import com.example.BasicTest.MethodBasicTest;
public class NamingConventionTest extends JPanel {

    private ArrayList<ClassInformation> classes;
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
    private TestCaseManager TestCaseManager;

    public NamingConventionTest(ArrayList<ClassInformation> classes) {
        this.classes = classes;

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
        // selectedClassComboBox = new JComboBox<String>(new String[]{"choose class"});
        selectedClassComboBox = new JComboBox<String>();

        for (ClassInformation c : classes) {
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
        //selectedClassPanel3.setPreferredSize(new Dimension(100, 10));

        
        attCheckB = new JCheckBox("Attributes");
        attCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel markslab2 = new JLabel("Marks:");
        markslab1.setFont(new Font("Arial", Font.PLAIN, 10));
		JTextField marksTextField2 = new JTextField(3);
		marksTextField2.setFont(new Font("Arial", Font.PLAIN, 15));
			
		selectedClassPanel3.add(attCheckB);
        selectedClassPanel3.add(markslab2);
		selectedClassPanel3.add(marksTextField2);	

        attributePanel = new JPanel();
        attributePanel.setLayout(new GridLayout(3, 3));
       // attributePanel.setPreferredSize(new Dimension(10, 10));
        updateAttributeList(ans);

        methodPanel = new JPanel();
        methodPanel.setLayout(new GridLayout(3, 3));
        updateMethodList(ans);
      
        selectedClassPanel4 = new JPanel();
        selectedClassPanel4.setLayout(new FlowLayout());
       // selectedClassPanel4.setPreferredSize(new Dimension(100, 100));

        
        methCheckB = new JCheckBox("Methods");
        methCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel markslab3 = new JLabel("Marks:");
        markslab3.setFont(new Font("Arial", Font.PLAIN, 10));
		JTextField marksTextField3 = new JTextField(3);
		marksTextField3.setFont(new Font("Arial", Font.PLAIN, 15));
			
		selectedClassPanel4.add(methCheckB);
        selectedClassPanel4.add(markslab3);
		selectedClassPanel4.add(marksTextField3);	

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

                if (selectedClassIndex >= 0 && selectedClassIndex < classes.size()) {
                    ClassInformation selectedClass = classes.get(selectedClassIndex);
                    // attributeListModel.clear();

                    // for (AttributeInformation attribute : selectedClass.getAttributes()) {
                    //     attributeListModel.addElement(attribute.toString());
                    // }
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }
            }
        });
    }

    private void updateSelectedClassComboBox() {
        selectedClassComboBox.removeAllItems();
        for (ClassInformation c : classes) {
            String className = c.getClassName();
            selectedClassComboBox.addItem(className);
        }
    }

    private void updateAttributeList(String ans) {
        for(ClassInformation c:classes){
                    if(c.getClassName().equals(ans)){
                        attributePanel.removeAll();
                        ArrayList<AttributeInformation> attributes = c.getAttributes();
                        for(AttributeInformation a:attributes){
                           // JCheckBox aCheckBox = new JCheckBox(a.getAttributeType()+ " "+ a.getAttributeName());
                           JCheckBox aCheckBox = new JCheckBox(a.getAttributeName());
                            attributePanel.add(aCheckBox);
                        }    
                    }
        }  
    }

    private void updateMethodList(String ans) {
        for(ClassInformation c:classes){
                    if(c.getClassName().equals(ans)){
                        methodPanel.removeAll();
                        ArrayList<MethodInformation> methods = c.getMethods();
                
                         for(MethodInformation m:methods){
                          //  JCheckBox mCheckBox = new JCheckBox(m.getMethodType()+ " "+ m.getMethodName());
                            JCheckBox mCheckBox = new JCheckBox(m.getMethodName());
                            methodPanel.add(mCheckBox);
                        }    
                    } 
        }  
    }

    public void printTest() {
        
        TestCaseManager.getTestCases().removeAll(classTests);
        TestCaseManager.getTestCases().removeAll(attributeTests);
        TestCaseManager.getTestCases().removeAll(methodTests);

        // classTests.clear();
        // methodTests.clear();
        // attributeTests.clear();

        String nameCon=(String) selectedClassComboBox.getSelectedItem();
        String cName=(String) selectedClassComboBox.getSelectedItem();
        String marks = (String) marksTextField1.getText();
        String testType ="name";


        if(classCheckB.isSelected()==true){      
            classTests.add(new ClassBasicTest(Integer.parseInt(marks),cName, testType));
            nameCon = nameCon + "\n" +"-Class [" + marks +" mark]\n";
            System.out.println("kwsejfbweiubfwefwefwefewfwfdsvsasdvawrebwarbaerbaerbaerbaerbraeb");
        }
        if(attCheckB.isSelected()==true){
            nameCon = nameCon+"-Attributes ";
            nameCon= checkAttributeCheckboxes(attributePanel,nameCon,cName);
           // updateAttributeTests();

        }
        if(methCheckB.isSelected()==true){
            nameCon = nameCon+"-Methods ";
            nameCon= checkMethodCheckboxes(methodPanel,nameCon,cName);
            
        }
        nameCon = nameCon +"---------------------------\n";
        nameTests.append(nameCon);

        // System.out.println("class: ");
        // System.out.println(classTests);
        // System.out.println("atts: ");
        // System.out.println(attributeTests);
        // System.out.println("meths: ");
        // System.out.println(methodTests);

        testCases.addAll(classTests);
        testCases.addAll(attributeTests);
        testCases.addAll(methodTests);

        TestCaseManager.getTestCases().addAll(classTests);
        TestCaseManager.getTestCases().addAll(attributeTests);
        TestCaseManager.getTestCases().addAll(methodTests);

        
        // System.out.println("tcr:");
        // System.out.println(TestCaseManager.getTestCases());
    }

    public String checkAttributeCheckboxes(JPanel Panel,String name,String className) {
        Component[] components = Panel.getComponents();
        for (Component component : components) {
            System.out.println("component: " + component.getName());
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                String checkBoxText = checkBox.getText();

                if (checkBox.isSelected()) {
                    name = name + checkBoxText + ", ";
                    // System.out.println("att added");
                    attributeTests.add(new AttributeBasicTest(0,className, checkBoxText, "name"));
                } 
            }
        }
        name = name + "\n";
        return name;
    }

    public String checkMethodCheckboxes(JPanel Panel,String name,String className) {
        Component[] components = Panel.getComponents();
        for (Component component : components) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                String checkBoxText = checkBox.getText();

                if (checkBox.isSelected()) {
                    name = name + checkBoxText + ", ";
                    methodTests.add(new MethodBasicTest(0,className, checkBoxText, "name"));
                } 
            }
        }
        name = name + "\n";
        return name;
    }

    
}

  
