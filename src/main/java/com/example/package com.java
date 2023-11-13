package com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

class Section2 extends JPanel {

    private AssignmentSpecPortal parent;
    private int classCount = 1;

    //Variables for  Class Input Panel
    private JPanel classPanel;
    private JPanel subPanel;
    private JPanel inputPanel;
    

    private JComboBox<String> accessComboBox;
    private JComboBox<String> isAbstractComboBox;
    private JComboBox<String> isInterfaceComboBox;
    private JComboBox<String> EIComboBox;

    private JTextField classNameField;
    private JTextField NameField;
    private JTextField markField;

    private JCheckBox extendsCheckBox;

    private JButton addClassButton;
    private JButton backButton;
    private JButton removeButton;
    private JButton addAttButton;

    private DefaultListModel<String> classListModel;

    private JList<String> classList;

    private JLabel prompt;
    private JLabel markslabel;

    //Variables for  Attribute Input Panel
    private DefaultListModel<String> attributeListModel;

    private JList<String> attributeList;

    private JPanel testPanel;
    private JPanel attributePanel;

    private JCheckBox instanceCheckBox;

    private JTextField attributeNameField;
    private JTextField objNameField;

    private JComboBox<String> attributeTypeComboBox;
    private JComboBox<String> attAccessComboBox;

    private JButton saveButton;
    private JButton closeButton;


   public Section2(AssignmentSpecPortal parent) {
        setLayout(new BorderLayout());

        this.parent = parent; 
        
        //Setting Up Class Input Panel
        classPanel = new JPanel();
        classPanel.setLayout(new GridLayout(2,1));
        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
            
        accessComboBox = new JComboBox<>(new String[]{"Public", "Private", "Protected"});
        isAbstractComboBox = new JComboBox<>(new String[]{"Abstract","Concrete"});
        isInterfaceComboBox = new JComboBox<>(new String[]{"Class","Interface"});
        EIComboBox = new JComboBox<>(new String[]{"Extends","Implements"});
        EIComboBox.setVisible(false);

        classNameField = new JTextField(10);
        classNameField.setText("");
        markField = new JTextField(6);
        markField.setText("0");
        NameField = new JTextField(10);
        NameField.setVisible(false);

        prompt = new JLabel("Enter Assignment Classes Here:                                                                  ");
        prompt.setFont(new Font("Arial", Font.ITALIC, 22));
        markslabel = new JLabel("Marks: \n");

        extendsCheckBox = new JCheckBox("Extends/Implements");

        removeButton = new JButton("Remove Selected Class");
        addAttButton = new JButton("Add Attributes");
        addClassButton = new JButton("+ Class");
        backButton = new JButton("Back");

        classListModel = new DefaultListModel<>();
        classList = new JList<>(classListModel);

        inputPanel.add(prompt);
        inputPanel.add(accessComboBox);
        inputPanel.add(isAbstractComboBox);
        inputPanel.add(isInterfaceComboBox);    
        inputPanel.add(classNameField);
        inputPanel.add(extendsCheckBox);
        inputPanel.add(EIComboBox);
        inputPanel.add(NameField);
        inputPanel.add(markslabel);
        inputPanel.add(markField);
        inputPanel.add(addClassButton);
        inputPanel.add(removeButton);
        inputPanel.add(addAttButton); 

        classPanel.add(inputPanel);
        classPanel.add(new JScrollPane(classList));

        setLayout(null);
        //These go on main page
        classPanel.setSize(900, 200);
        classPanel.setLocation(0, 10);
        add(classPanel);

        backButton.setFont(new Font("Arial", Font.PLAIN, 15));
        backButton.setSize(100, 20);
        backButton.setLocation(50, 500);
        add(backButton);

        //Listeners
        isInterfaceComboBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if("Interface".equals(isInterfaceComboBox.getSelectedItem())) {
                    isAbstractComboBox.setVisible(false);
                    EIComboBox.removeAllItems();
                    EIComboBox.addItem("Extends");
            
		            isInterfaceComboBox.setLocation(240, 90);
                }
                else{
                    isAbstractComboBox.setVisible(true);   
                    EIComboBox.addItem("Implements");        
	            	isInterfaceComboBox.setLocation(350, 90);
                } 
            }
        }); 

        extendsCheckBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(true==extendsCheckBox.isSelected()) {
                    EIComboBox.setVisible(true);
                    NameField.setVisible(true);
                    extendsCheckBox.setText("");
		            
                }
                else{
                    EIComboBox.setVisible(false);   
                    NameField.setVisible(false);         
                    extendsCheckBox.setText("Extends/Implements"); 
	        
                } 
            }
        }); 

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.switchToSection1();
            }
        });

        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                classCount++;
                addClassToList();
                
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int index = classList.getSelectedIndex();
                if(index != -1){
                    classListModel.remove(index);
                }
            }
        });

        addAttButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int index = classList.getSelectedIndex();
                if(index != -1){
                    displayAttributes(index);
                    
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String attributeName = attributeNameField.getText();
                String attributeType = (String) attributeTypeComboBox.getSelectedItem();
                String attAccessType = (String) attAccessComboBox.getSelectedItem();
                String attributeInfo = "";

                if(attributeName.equals("")){
                    return;
                }
                else {
                    attributeInfo = attAccessType+" "+attributeType+" "+attributeName;
                    attributeListModel.addElement(attributeInfo);
                }
                attributeNameField.setText("");
               // addAttToList();
                
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                testPanel.setVisible(false);  
            }
        });

        attributeTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("object".equals(attributeTypeComboBox.getSelectedItem())) {
                    objNameField.setVisible(true);
                }
                else{
                    objNameField.setVisible(false);     
                } 
            }
        });
   }

   private void displayAttributes(int index) {
    
        attributeListModel = new DefaultListModel<>();
        attributeList = new JList<>(attributeListModel);

        testPanel = new JPanel();
        testPanel.setLayout(new GridLayout(2,1));

        attributePanel = new JPanel();
        attributePanel.setLayout(new FlowLayout());

        instanceCheckBox = new JCheckBox("Instance Variable");

        attributeNameField = new JTextField(20);
        objNameField = new JTextField(10);

        attributeTypeComboBox = new JComboBox<>(new String[]{"String","int","double","object"});
        attAccessComboBox = new JComboBox<>(new String[]{"Public", "Private", "Protected"});
        
        saveButton = new JButton("Save");
        closeButton = new JButton("X");

        attributePanel.add(attAccessComboBox);
        attributePanel.add(attributeTypeComboBox);
        attributePanel.add(objNameField);
        objNameField.setVisible(false);   
        attributePanel.add(instanceCheckBox);
        attributePanel.add(attributeNameField);
        attributePanel.add(saveButton);
        attributePanel.add(closeButton);

        testPanel.add(attributePanel);
        testPanel.add(new JScrollPane(attributeList));
            
        setLayout(null);
        testPanel.setSize(900, 200);
		testPanel.setLocation(0, 220);
        testPanel.setVisible(true);

        add(testPanel); 
        revalidate();
        repaint();
   }

   private void addClassToList(){
    String accessType = (String) accessComboBox.getSelectedItem();
    String isAbstract = (String) isAbstractComboBox.getSelectedItem();
    String EIbox = (String) EIComboBox.getSelectedItem();
    String isInterface = (String) isInterfaceComboBox.getSelectedItem();
    String className = (String) classNameField.getText();
    String extraName = (String) NameField.getText();
    String marks = (String) markField.getText();
    String classInfo = "";
    boolean isSelected = extendsCheckBox.isSelected();
    

    if(className.equals("")){
        return;
    }
    else if((extraName.equals(""))&&(isSelected == true)){
        return;
    }
    else if((isInterface=="Interface")&&( isSelected == true)){
       classInfo = accessType+" "+isInterface+" "+ className + " Extends "+ extraName + " [ " + marks + " marks]" ;
       classListModel.addElement(classInfo);
       
    }
    else if(isInterface=="Interface"){
       classInfo = accessType+" "+isInterface+" "+ className + " [ " + marks + " marks]" ;
       classListModel.addElement(classInfo);
    
    }
   else if(isSelected == true){
       classInfo = accessType+" "+isAbstract+" "+ className +" "+ EIbox+" " + extraName+ " [ " + marks + " marks]";
       classListModel.addElement(classInfo);
      
    }
    else {
        classInfo = accessType+" "+isAbstract+" "+isInterface+" "+ className+ " [ " + marks + " marks]";
        classListModel.addElement(classInfo);
        
    }
    classNameField.setText("");
    NameField.setText("");
    markField.setText("0");
    }  

    public static void main( String[] args ) {
        System.out.println( "Hello World!!!!!! :)))))" );
        // ZipFileInput z = new ZipFileInput("Enter zip file path here");
        // z.readFiles();

        AssignmentSpecPortal assignmentSpecPortal = new AssignmentSpecPortal();
        assignmentSpecPortal.setVisible(true);
    } 

}
     
    