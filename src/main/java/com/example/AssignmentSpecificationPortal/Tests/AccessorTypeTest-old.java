// package com.example.AssignmentSpecificationPortal.Tests;

// import java.awt.Component;
// import java.awt.Dimension;
// import java.awt.FlowLayout;
// import java.awt.Font;
// import java.awt.GridLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

// import javax.swing.BoxLayout;
// import javax.swing.DefaultListModel;
// import javax.swing.JButton;
// import javax.swing.JCheckBox;
// import javax.swing.JComboBox;
// import javax.swing.JLabel;
// import javax.swing.JList;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTextArea;
// import javax.swing.JTextField;

// import com.example.AssignmentSpecificationPortal.AttributeInformation;
// import com.example.AssignmentSpecificationPortal.MethodInformation;
// import com.example.AssignmentSpecificationPortal.ClassInformation;

// public class AccessorTypeTest extends JPanel {

//     private ArrayList<ClassInformation> classes;
//     private JLabel testDescription;

//     private JPanel selectedClassPanel;
//     private JPanel selectedClassPanel2;
//     private JPanel selectedClassPanel3;
//     private JPanel selectedClassPanel4;
//     private JPanel attributePanel;
//     private JPanel methodPanel;
//     private JPanel testPanel;
//      private Map<String, String> classLink = new HashMap<>();

//     private JLabel selectedClassLabel;
//     private JButton loadClassesButton;
//     private JComboBox<String> selectedClassComboBox;
//     private JCheckBox classCheckB;
//     private JCheckBox attCheckB;
//     private JCheckBox methCheckB;

//     private JTextField marksTextField1;
//     private JTextArea nameTests;

//     public AccessorTypeTest(ArrayList<ClassInformation> classes) {
//         this.classes = classes;

//         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//         testDescription = new JLabel("Accessor Type test description. short one line description of test");
//         testDescription.setFont(new Font("Arial", Font.ITALIC, 15));
//         testDescription.setAlignmentX(Component.LEFT_ALIGNMENT);

//         selectedClassPanel = new JPanel();
//         selectedClassPanel.setLayout(new FlowLayout());
//        // selectedClassPanel.setPreferredSize(new Dimension(100, 60));
        
//         selectedClassLabel = new JLabel("Class:");
//         loadClassesButton = new JButton("Load classes");
//         // selectedClassComboBox = new JComboBox<String>(new String[]{"choose class"});
//         selectedClassComboBox = new JComboBox<String>();

//         for (ClassInformation c : classes) {
//             String className = c.getClassName();
//             String classType = c.getAccessType();
//             selectedClassComboBox.addItem(className);
//             classLink.put(className, classType);
//         }
//         selectedClassPanel.add(selectedClassLabel);
//         selectedClassPanel.add(selectedClassComboBox);
//         selectedClassPanel.add(loadClassesButton);

//          if (selectedClassComboBox.getItemCount() == 0) {
//             selectedClassComboBox.setVisible(false);
//         }
        
//         selectedClassPanel2 = new JPanel();
//         selectedClassPanel2.setLayout(new FlowLayout());
//         //selectedClassPanel2.setPreferredSize(new Dimension(100, 60));
//         String ans = (String) selectedClassComboBox.getSelectedItem();
//         String type = classLink.get(ans);
//         classCheckB = new JCheckBox("Class - "+type+" "+ans);
    
//         classCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
//         JLabel markslab1 = new JLabel("Marks:");
//         markslab1.setFont(new Font("Arial", Font.PLAIN, 10));
// 	    marksTextField1 = new JTextField(3);
// 		marksTextField1.setFont(new Font("Arial", Font.PLAIN, 15));
			
// 		selectedClassPanel2.add(classCheckB);
//         selectedClassPanel2.add(markslab1);
// 		selectedClassPanel2.add(marksTextField1);	

//         selectedClassPanel3 = new JPanel();
//         selectedClassPanel3.setLayout(new FlowLayout());
//         //selectedClassPanel3.setPreferredSize(new Dimension(100, 10));

        
//         attCheckB = new JCheckBox("Attributes");
//         attCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
//         JLabel markslab2 = new JLabel("Marks:");
//         markslab1.setFont(new Font("Arial", Font.PLAIN, 10));
// 		JTextField marksTextField2 = new JTextField(3);
// 		marksTextField2.setFont(new Font("Arial", Font.PLAIN, 15));
			
// 		selectedClassPanel3.add(attCheckB);
//         selectedClassPanel3.add(markslab2);
// 		selectedClassPanel3.add(marksTextField2);	

//         attributePanel = new JPanel();
//         attributePanel.setLayout(new GridLayout(3, 3));
//        // attributePanel.setPreferredSize(new Dimension(10, 10));
//         updateAttributeList(ans);

//         methodPanel = new JPanel();
//         methodPanel.setLayout(new GridLayout(3, 3));
//         updateMethodList(ans);
      
//         selectedClassPanel4 = new JPanel();
//         selectedClassPanel4.setLayout(new FlowLayout());
//        // selectedClassPanel4.setPreferredSize(new Dimension(100, 100));

        
//         methCheckB = new JCheckBox("Methods");
//         methCheckB.setFont(new Font("Arial", Font.PLAIN, 15));
//         JLabel markslab3 = new JLabel("Marks:");
//         markslab3.setFont(new Font("Arial", Font.PLAIN, 10));
// 		JTextField marksTextField3 = new JTextField(3);
// 		marksTextField3.setFont(new Font("Arial", Font.PLAIN, 15));
			
// 		selectedClassPanel4.add(methCheckB);
//         selectedClassPanel4.add(markslab3);
// 		selectedClassPanel4.add(marksTextField3);	

//         testPanel = new JPanel();
//         testPanel.setLayout(new FlowLayout());

//         nameTests = new JTextArea(10, 20);
//         nameTests.setEditable(false); 
//         JScrollPane scrollPane = new JScrollPane(nameTests);

//         testPanel.add(scrollPane);

//         JPanel savePanel = new JPanel();
//         savePanel.setLayout(new FlowLayout());
//         JButton saveTestsButton=new JButton("Save Test");
//         savePanel.add(saveTestsButton);
//         JButton clearTestsButton=new JButton("Clear Tests");
//         savePanel.add(clearTestsButton);
       

//         add(testDescription);
//         add(selectedClassPanel);
//         add(selectedClassPanel2);
//         add(selectedClassPanel3);
//         add(attributePanel);
//         add(selectedClassPanel4);
//         add(methodPanel);
//         add(testPanel);
//         add(savePanel);

//         loadClassesButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 updateSelectedClassComboBox();
//                 selectedClassComboBox.setVisible(true);
//             }
//         });

//         saveTestsButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 printTest();
//             }
//         });

//         clearTestsButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 nameTests.setText("");
//             }
//         });

//         selectedClassComboBox.addActionListener(new ActionListener() {
            
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                String ans =(String) selectedClassComboBox.getSelectedItem();
//                String type = classLink.get(ans);

//                 classCheckB.setText("Class - "+type+" "+ans);// fix
//                 updateAttributeList(ans);
//                 attributePanel.revalidate();
//                 attributePanel.repaint();
//                 updateMethodList(ans);
//                 methodPanel.revalidate();
//                 methodPanel.repaint();
//             }
//         }); 

//         selectedClassComboBox.addActionListener(new ActionListener() {
//             private int selectedClassIndex;

//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 selectedClassIndex = selectedClassComboBox.getSelectedIndex();

//                 if (selectedClassIndex >= 0 && selectedClassIndex < classes.size()) {
//                     ClassInformation selectedClass = classes.get(selectedClassIndex);
//                     // attributeListModel.clear();

//                     // for (AttributeInformation attribute : selectedClass.getAttributes()) {
//                     //     attributeListModel.addElement(attribute.toString());
//                     // }
//                 } else {
//                     System.out.println("Invalid selected index or class not found.");
//                 }
//             }
//         });
//     }

//     private void updateSelectedClassComboBox() {
//         selectedClassComboBox.removeAllItems();
//         for (ClassInformation c : classes) {
//             String className = c.getClassName();
//             selectedClassComboBox.addItem(className);
//         }
//     }

//     private void updateAttributeList(String ans) {
//         for(ClassInformation c:classes){
//                     if(c.getClassName().equals(ans)){
//                         attributePanel.removeAll();
//                         ArrayList<AttributeInformation> attributes = c.getAttributes();
//                         for(AttributeInformation a:attributes){
//                             JCheckBox aCheckBox = new JCheckBox(a.getAttributeType()+ " "+ a.getAttributeName());
//                             attributePanel.add(aCheckBox);
//                         }    
//                     }
//         }  
//     }

//     private void updateMethodList(String ans) {
//         for(ClassInformation c:classes){
//                     if(c.getClassName().equals(ans)){
//                         methodPanel.removeAll();
//                         ArrayList<MethodInformation> methods = c.getMethods();
                
//                          for(MethodInformation m:methods){
//                             JCheckBox mCheckBox = new JCheckBox(m.getMethodType()+ " "+ m.getMethodName());
//                             methodPanel.add(mCheckBox);
//                         }    
//                     } 
//         }  
//     }

//     public void printTest() {
//         String nameCon=(String) selectedClassComboBox.getSelectedItem();
//         String marks = (String) marksTextField1.getText();
//         String type = classLink.get(nameCon);

//         if(classCheckB.isSelected()==true){
        
//             nameCon = nameCon + "\n" +"-Access Type: "+ type +" [" + marks +" mark]\n";
            
//         }
//         if(attCheckB.isSelected()==true){
//             nameCon = nameCon+"-Attributes ";
//             nameCon= checkSelectedCheckboxes(attributePanel,nameCon);

//         }
//         if(methCheckB.isSelected()==true){
//             nameCon = nameCon+"-Methods ";
//             nameCon= checkSelectedCheckboxes(methodPanel,nameCon);
//         }
//         nameCon = nameCon +"---------------------------\n";
//         nameTests.append(nameCon);
//     }

//     public String checkSelectedCheckboxes(JPanel Panel,String name) {
//         Component[] components = Panel.getComponents();
//         for (Component component : components) {
//             if (component instanceof JCheckBox) {
//                 JCheckBox checkBox = (JCheckBox) component;
//                 String checkBoxText = checkBox.getText();

//                 if (checkBox.isSelected()) {
//                     name = name + checkBoxText + ", ";
//                 } 
//             }
//         }
//         name = name + "\n";
//         return name;
//     }
// }
