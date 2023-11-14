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
import java.util.concurrent.Flow;
import java.util.regex.Pattern;
import java.util.ArrayList;


class Section2 extends JPanel {

    private AssignmentSpecPortal parent;
    private int classCount = 1;

    //Variables for  Class Input Panel
    private JPanel classPanel;
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


    // Attributes for method section
    private JPanel methodPanel;
    private JPanel inputPanelM;
    private JPanel vicPanel;
    private JPanel parameterPanel;

    private JLabel promptM;
    private JComboBox<String> accessComboBoxM;
    private JComboBox<String> isAbstractComboBoxM;

    private JLabel returnTypeLabel;
    private JComboBox<String> returnTypeComboBox;
    private JLabel returnTypeObjNameLabel;
    private JTextField returnTypeObj;

    private JLabel methodNameLabel;
    private JTextField methodNameField;
    private JTextField nameFieldM;
    private JTextField markFieldM;
    private JLabel markslabelM;

    private JLabel parameterTypeLabel;
    private JComboBox<String> parameterTypeComboBox;
    private JLabel parameterTypeObjLabel;
    private JTextField parameterTypeObjField;

    private JLabel parameterNameLabel;
    private JTextField parameterNameField;


    private JButton addMethodButton;
    private JButton removeButtonM;
    private JButton addParButtonM;

    private DefaultListModel<String> methodListModel;
    private JList<String> methodList;
    private ArrayList<String> testingStuff;


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

        


        // method section code
        testingStuff = new ArrayList<>();
        methodPanel = new JPanel();
        methodPanel.setLayout(new GridLayout(4,1));
        parameterPanel = new JPanel();
        parameterPanel.setLayout(new FlowLayout());
        vicPanel = new JPanel();
        vicPanel.setLayout(new FlowLayout());
        inputPanelM = new JPanel();
        inputPanelM.setLayout(new FlowLayout());

        promptM = new JLabel("Enter Assignment Methods Here:                                                         ");
        promptM.setFont(new Font("Arial", Font.ITALIC, 22));

        accessComboBoxM = new JComboBox<>(new String[]{"Public", "Private", "Protected"});
        isAbstractComboBoxM = new JComboBox<>(new String[]{"Concrete","Abstract"});

        returnTypeLabel = new JLabel("Return Type:");
        returnTypeComboBox = new JComboBox<>(new String[]{"String", "int", "double", "boolean","void", "object"});
        returnTypeObjNameLabel = new JLabel("Object name:");
        returnTypeObjNameLabel.setVisible(false);
        returnTypeObj = new JTextField(8);
        returnTypeObj.setText("");
        returnTypeObj.setVisible(false);

        methodNameLabel = new JLabel("Method Name:");
        methodNameField = new JTextField(10);
        methodNameField.setText("");


        markslabelM = new JLabel("Marks:");
        markFieldM = new JTextField(4);
        markFieldM.setText("");

        addMethodButton = new JButton("Add Method");
        removeButtonM = new JButton("Remove Method");
        addParButtonM = new JButton("Add Parameter");


        methodListModel = new DefaultListModel<>();
        methodList = new JList<>(methodListModel);


        parameterTypeLabel = new JLabel("Parameter Type:");
        parameterTypeComboBox = new JComboBox<>( new String[]{"String", "int", "double", "boolean", "object"});

        parameterTypeObjLabel = new JLabel("Object name:");
        parameterTypeObjLabel.setVisible(false);
        parameterTypeObjField = new JTextField(8);
        parameterTypeObjField.setText("");
        parameterTypeObjField.setVisible(false);

        parameterNameLabel = new JLabel("Parameter Name:");
        parameterNameField = new JTextField(8);


        inputPanelM.add(promptM);
        inputPanelM.add(accessComboBoxM);
        inputPanelM.add(isAbstractComboBoxM);
        inputPanelM.add(returnTypeLabel);
        inputPanelM.add(returnTypeComboBox);
        inputPanelM.add(returnTypeObjNameLabel);
        inputPanelM.add(returnTypeObj);
        inputPanelM.add(methodNameLabel);
        inputPanelM.add(methodNameField);
        inputPanelM.add(markslabelM);
        inputPanelM.add(markFieldM);


        parameterPanel.add(parameterTypeLabel);
        parameterPanel.add(parameterTypeComboBox);
        parameterPanel.add(parameterTypeObjLabel);
        parameterPanel.add(parameterTypeObjField);
        parameterPanel.add(parameterNameLabel);
        parameterPanel.add(parameterNameField);
        parameterPanel.add(addParButtonM); 


        vicPanel.add(addMethodButton);
        vicPanel.add(removeButtonM);



        methodPanel.add(inputPanelM);
        methodPanel.add(parameterPanel);
        methodPanel.add(vicPanel);
        methodPanel.add(methodList);
        






        setLayout(null);
        //These go on main page
        classPanel.setSize(900, 200);
        classPanel.setLocation(0, 10);
        add(classPanel);

        methodPanel.setSize(900, 250);
        methodPanel.setLocation(0, 250);
        add(methodPanel);

        backButton.setFont(new Font("Arial", Font.PLAIN, 15));
        backButton.setSize(100, 20);
        backButton.setLocation(50, 550);
        add(backButton);




        // method section listeners
        returnTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if("object".equals(returnTypeComboBox.getSelectedItem())){
                    returnTypeObj.setVisible(true);
                    returnTypeObjNameLabel.setVisible(true);
                } else {
                    returnTypeObj.setVisible(false);
                    returnTypeObjNameLabel.setVisible(false);
                }
            }
        });
        parameterTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if("object".equals(parameterTypeComboBox.getSelectedItem())){
                    parameterTypeObjField.setVisible(true);
                    parameterTypeObjLabel.setVisible(true);
                } else {
                    parameterTypeObjField.setVisible(false);
                    parameterTypeObjLabel.setVisible(false);
                }
            }
        });
        addMethodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                addMethodToList();
                
            }
        });
        removeButtonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int index = methodList.getSelectedIndex();
                if(index != -1){
                    methodListModel.remove(index);
                }
            }
        });

        addParButtonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String test = (String) parameterNameField.getText();
                String test1 = (String) parameterTypeComboBox.getSelectedItem();
                if(test1.equals("object")){
                    test1 = (String) parameterTypeObjField.getText();
                }
                String test2 = test1 + " " + test;
                testingStuff.add(test2);
                parameterNameField.setText("");
                System.out.println(test2);
                System.out.println(testingStuff);
                test2 = "";

            }
        });
//once you add method, rmeove all the content from the arrraylist














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

   }
   ///////////////////
   private void addMethodToList(){
    String accessType = (String) accessComboBoxM.getSelectedItem();
    String isAbstract = (String) isAbstractComboBoxM.getSelectedItem();
    String returnType = (String) returnTypeComboBox.getSelectedItem();
    if(returnType.equals("object")){
        returnType = (String) returnTypeObj.getText();
    }
    String methodName = (String) methodNameField.getText();
    String marks = (String) markFieldM.getText();
    String methodInfo = "";

    String tested = "";
    for(String element: testingStuff){
        tested += element + ",";
    }

    if(tested.length() > 0){
        tested = tested.substring(0, tested.length() - 1);
    }

    if(methodName.equals("")){
        return;
    }
    if(returnType.equals("")){
        return;
    }

    if(isAbstract.equals("Abstract")){
        methodInfo = accessType + " " + isAbstract + " " + returnType + " " + methodName + "(" + tested + ");" + " [ " + marks + " marks]";
        methodListModel.addElement(methodInfo);
        tested = "";
        testingStuff.clear();
    }
    if(isAbstract.equals("Concrete")){
        methodInfo = accessType + " " + returnType + " " + methodName + "(" + tested + ")" + " [ " + marks + " marks]";
        methodListModel.addElement(methodInfo);
        tested = "";
        testingStuff.clear();
    }
    methodNameField.setText("");
    returnTypeObj.setText("");
    markFieldM.setText("");
    
   }







   /////////////////

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
        attributeNameField.setText("");
        objNameField.setText("");

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

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAttToList();
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

    private void addAttToList(){
        String attributeName = attributeNameField.getText();
        String objName = objNameField.getText();
        String attributeType = (String) attributeTypeComboBox.getSelectedItem();
        String attAccessType = (String) attAccessComboBox.getSelectedItem();
        String attributeInfo = "";

                if(attributeName.equals("")){
                    return;
                }
                else if(("object".equals(attributeTypeComboBox.getSelectedItem()))&&(objName.equals(""))){
                    return;
                }
                else if("object".equals(attributeTypeComboBox.getSelectedItem())){
                    attributeInfo = attAccessType+" "+objName+" "+attributeName;
                    attributeListModel.addElement(attributeInfo);
                }
                else {
                    attributeInfo = attAccessType+" "+attributeType+" "+attributeName;
                    attributeListModel.addElement(attributeInfo);
                }
                attributeNameField.setText("");
                objNameField.setText("");

    }

    public static void main( String[] args ) {
        System.out.println( "Hello World!!!!!! :)))))" );
        // ZipFileInput z = new ZipFileInput("Enter zip file path here");
        // z.readFiles();

        AssignmentSpecPortal assignmentSpecPortal = new AssignmentSpecPortal();
        assignmentSpecPortal.setVisible(true);
    } 
 
}  