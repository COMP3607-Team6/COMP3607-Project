
package com.example;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.*;
import java.awt.event.*;

public class Section3 extends JPanel {
    private AssignmentSpecPortal parent;
    private JPanel testsPanel;
    private JPanel inputPanel;
    //private JPanel  section2;

    //private Section2 sec2;

    private JLabel prompt;
    private JLabel prompt2;
    private JLabel prompt3;

    private JComboBox<String> TestsComboBox;
    private JComboBox<String> TestType1ComboBox;
    /*private JComboBox<String> TestType2ComboBox;
   /*  private JComboBox<String> TestType3ComboBox;
    private JComboBox<String> TestType4ComboBox;
    private JComboBox<String> TestType5ComboBox;
    private JComboBox<String> TestType6ComboBox;
    private JComboBox<String> TestType7ComboBox; */

    private JButton AddtestButton;

   
    private JList<String> classList;
    private JList<String> attributeList;
    //private DefaultListModel<String> classListModel;

    public Section3(AssignmentSpecPortal parent, JList<String> classList) {
        this.parent = parent; 
        this.classList = classList;
        testsPanel = new JPanel();
        testsPanel.setLayout(new GridLayout(2,1));
        this.attributeList = new JList<String>();
       
        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JScrollPane scrollPane = new JScrollPane(classList);
        JScrollPane scrollPane2 = new JScrollPane(attributeList);

        TestsComboBox = new JComboBox<>(new String[]{"Naming Convention Test","Hierarchy Test","AccessorType Test","ValueTest","TypeTest","Final Test","Static Test"});
        TestType1ComboBox = new JComboBox<>(new String[]{"ClassBasicTest","Method Basic Test","AttributeBasicTest"});

        //classListModel = new DefaultListModel<>();
        //classList = new JList<>(classListModel); 
        setLayout(null);

        prompt = new JLabel("Add Test Classes Here:");
        prompt.setFont(new Font("Arial", Font.ITALIC, 20));
        prompt2 = new JLabel("Classes:");
        prompt2.setFont(new Font("Arial", Font.PLAIN, 15));
        prompt3 = new JLabel("Attributes/Methods:");
        prompt3.setFont(new Font("Arial", Font.PLAIN, 15));

        AddtestButton = new JButton("Add Test To Selected Class");

        prompt.setSize(400, 200);
        prompt.setLocation(5, -70);
        add(prompt);

        prompt2.setSize(100, 50);
        prompt2.setLocation(5, 30);
        add(prompt2);

        prompt3.setSize(150, 50);
        prompt3.setLocation(450, 30);
        add(prompt3);
        
        scrollPane.setSize(400, 100);
        scrollPane.setLocation(5, 60);
        add(scrollPane);

        scrollPane2.setSize(400, 100);
        scrollPane2.setLocation(450, 60);
        add(scrollPane2);

        AddtestButton.setSize(250, 20);
        AddtestButton.setLocation(5, 170);
        add(AddtestButton); 

        TestsComboBox.setSize(250, 20);
        TestsComboBox.setLocation(300, 170);
        add(TestsComboBox);
        TestsComboBox.setVisible(false);

        TestType1ComboBox.setSize(250, 20);
        TestType1ComboBox.setLocation(300, 180);
        add(TestsComboBox);
        TestsComboBox.setVisible(false);

         //Listeners
         AddtestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int index = classList.getSelectedIndex();
                    if(index != -1){
                        TestsComboBox.setVisible(true); 
                    }
                
            }
        });

       

      
    }

   public void fillComboBox (){
        String testName = (String) TestsComboBox.getSelectedItem();
        TestType1ComboBox.removeAllItems();
      
        if(testName.equals("Naming Convention Test")){
            TestType1ComboBox.addItem("ClassBasicTest");
            TestType1ComboBox.addItem("MethodBasicTest");
            TestType1ComboBox.addItem("AttributeBasicTest");
        }
        else if(testName.equals("Hierarchy Test")){
            TestType1ComboBox.addItem("");
            TestType1ComboBox.addItem("");
            TestType1ComboBox.addItem("");

        }
        else if(testName.equals("AccessorType Test")){
            TestType1ComboBox.addItem("");
            TestType1ComboBox.addItem("");
            TestType1ComboBox.addItem("");

        }
        else if(testName.equals("Value Test")){
            TestType1ComboBox.addItem("");
            TestType1ComboBox.addItem("");
            TestType1ComboBox.addItem("");

        }
        else if(testName.equals("TypeTest")){
            TestType1ComboBox.addItem("");
            TestType1ComboBox.addItem("");
            TestType1ComboBox.addItem("");

        }
        else if(testName.equals("Final Test")){
            TestType1ComboBox.addItem("");
            TestType1ComboBox.addItem("");
            TestType1ComboBox.addItem("");

        }
        else if(testName.equals("Static Test")){
            TestType1ComboBox.addItem("");
            TestType1ComboBox.addItem("");
            TestType1ComboBox.addItem("");

        }
        
   }
    
    
}
