package com.example.AssignmentSpecificationPortal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Section2C_Methods extends JPanel {
    //
    /* This class creates the Layout for Method Input TabbedPane of the GUI
     */
    private CardLayout cardLayout;
    private JLabel prompt;
    private JPanel selectedClassPanel;
    private JLabel selectedClassLabel;
    private JButton loadClassesButton;
    private JComboBox<String> selectedClassComboBox;
    // private ArrayList<ClassInformation> classes;
    private int selectedClassIndex;




    private JPanel methodPanel;
    private DefaultListModel<String> methodListModel;
    private JList<String> methodList;

    private JComboBox<String> accessTypeCB;
    private JComboBox<String> isAbstract;
    private JComboBox<String> methodType;
    private JTextField objMethodType;
    private JLabel objMethodTypeLabel;
    private JLabel methodNameLabel;
    private JTextField methodName;



    private JComboBox<String> parameterType;
    private JTextField objParameterType;
    private JLabel parameterInputL;
    private JTextField parameterInput;
    private JButton addMethodParameter;

    private JComboBox<String> parameterTypeObj;
    private JLabel parameterInputLObj;

    private JTextField parameterInputObj;
    private JButton addMethodParameterObj;
    private JLabel objParameterTypeLabel;
    private JLabel objExpectedInputLabel;

    private JButton addMethod;
    private JButton removeMethod;

    private ArrayList<String> methodParameters;
    // private ArrayList<MethodInformation> allMethods;
    private ArrayList<String> ObjParameters;


    private JButton test;//to be deleted!!!
    private JPanel promptPanel;
    

    public Section2C_Methods(CardLayout layout) {
        this.cardLayout = layout;
        // allMethods = new ArrayList<>();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        prompt = new JLabel("  Add methods (for inputted classes) to be tested here.");
        promptPanel = new JPanel();
        promptPanel.setLayout(new BoxLayout(promptPanel, BoxLayout.X_AXIS));
        promptPanel.add(prompt);
        promptPanel.add(Box.createHorizontalGlue());

        selectedClassPanel = new JPanel();
        selectedClassPanel.setLayout(new FlowLayout());
        selectedClassLabel = new JLabel("Class:");
        loadClassesButton = new JButton("Load classes");
        selectedClassComboBox = new JComboBox<String>();

        updateSelectedClassComboBox();

        selectedClassPanel.add(selectedClassLabel);
        selectedClassPanel.add(selectedClassComboBox);
        selectedClassPanel.add(loadClassesButton);

        if (selectedClassComboBox.getItemCount() == 0) {
            selectedClassComboBox.setVisible(false);
        }

        add(promptPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(selectedClassPanel);
        add(createMethodPanel());

        loadClassesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedClassComboBox();
                selectedClassComboBox.setVisible(true);
                getAllClasses();
                getAllMethods();
            }
        });

        selectedClassComboBox.addActionListener(new ActionListener() {
            //private int selectedClassIndex;

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassIndex = selectedClassComboBox.getSelectedIndex();

                if (selectedClassIndex >= 0 && selectedClassIndex < ClassesManager.getClasses().size()) {
                    ClassInformation selectedClass = ClassesManager.getClass(selectedClassIndex);
                    methodListModel.clear();

                    for (MethodInformation method : selectedClass.getMethods()) {
                        methodListModel.addElement(method.toString());
                    }
                    
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }
            }
        });
    }

    private JPanel createMethodPanel(){
        methodParameters = new ArrayList<>();
        ObjParameters = new ArrayList<>();

        JPanel fullPanel = new JPanel();
        // fullPanel.setLayout(new GridLayout(5,1));
        fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.Y_AXIS));

        methodPanel = new JPanel();//layer1
        methodPanel.setLayout(new FlowLayout());

        JPanel parameter_panel = new JPanel();//layer2
        JPanel add_remove_panel = new JPanel();//layer 3
        JPanel show_hide_objPar_Panel = new JPanel(); //layer 4

        methodListModel = new DefaultListModel<>();
        methodList = new JList<>(methodListModel);//layer 5

        //METHOD PANEL
        accessTypeCB = new JComboBox<>(new String[]{"public", "private", "protected"});
        isAbstract = new JComboBox<>(new String[]{"concrete", "abstract"});
        methodType = new JComboBox<>(new String[]{"String", "int", "double","float", "boolean", "char", "void", "object"});
        objMethodType = new JTextField(10);
        objMethodType.setText("");
        objMethodType.setVisible(false);
        objMethodTypeLabel = new JLabel("Object Name:");
        objMethodTypeLabel.setVisible(false);
        methodNameLabel = new JLabel("Method Name:");
        methodName = new JTextField(15);
        methodName.setText("");

        test = new JButton("view all classes");

        methodPanel.add(test);
        methodPanel.add(accessTypeCB);
        methodPanel.add(isAbstract);
        methodPanel.add(methodType);
        methodPanel.add(objMethodTypeLabel);
        methodPanel.add(objMethodType);
        methodPanel.add(methodNameLabel);
        methodPanel.add(methodName);

        //PARAMTER PANEL
        parameterType = new JComboBox<>(new String[]{"String", "int", "double","float", "boolean", "char", "object"});
        objParameterType = new JTextField(10);
        objParameterType.setText("");
        objParameterType.setVisible(false);
        objParameterTypeLabel = new JLabel("Object Name:");
        objParameterTypeLabel.setVisible(false);
        parameterInputL = new JLabel("Expected Input:");
        parameterInput = new JTextField(5);
        addMethodParameter = new JButton("Add parameter to method");
        

        parameter_panel.add(parameterType);
        parameter_panel.add(objParameterTypeLabel);
        parameter_panel.add(objParameterType);
        parameter_panel.add(parameterInputL);
        parameter_panel.add(parameterInput);
        parameter_panel.add(addMethodParameter);


        //OBJECT PARAMETER PANEL
        parameterInputLObj = new JLabel("input parameter for object:");
        parameterTypeObj = new JComboBox<>(new String[]{"String", "int", "double","float", "boolean", "char"});
        objExpectedInputLabel = new JLabel("Expected Input:");
        parameterInputObj = new JTextField(5);
        addMethodParameterObj = new JButton("add parameter to object");
        
        show_hide_objPar_Panel.setVisible(false);
        show_hide_objPar_Panel.add(parameterInputLObj);
        show_hide_objPar_Panel.add(parameterTypeObj);
        show_hide_objPar_Panel.add(objExpectedInputLabel);
        show_hide_objPar_Panel.add(parameterInputObj);
        show_hide_objPar_Panel.add(addMethodParameterObj);
        

        // ADD/REMOVE PANEL
        addMethod = new JButton("Add method to class");
        removeMethod = new JButton("Remove method from class");

        add_remove_panel.add(addMethod);
        add_remove_panel.add(removeMethod);



        //ADD 5 PANEL SECTIONS TO MAIN PANEL
        fullPanel.add(methodPanel);
        fullPanel.add(parameter_panel);
        fullPanel.add(show_hide_objPar_Panel);
        fullPanel.add(add_remove_panel);
        fullPanel.add(new JScrollPane(methodList));

        //LISTENERS
        //ADD METHOD TO CLASS
        addMethod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String methodInfo = "";
                String accessType = (String) accessTypeCB.getSelectedItem();
                String isAbstractS = (String) isAbstract.getSelectedItem();
                if(isAbstractS.equals("concrete")){
                    isAbstractS = "";
                }
                String returnType = (String) methodType.getSelectedItem();
                
                if(returnType.equals("object")){
                    returnType = (String) objMethodType.getText();
                }
                String methodNameS = (String) methodName.getText();

                String methodPars = "";
                for(String element: methodParameters){
                    System.out.println(element);
                    methodPars += element + ", ";
                }

                if(methodPars.length() > 0){
                    methodPars = methodPars.substring(0, methodPars.length() - 2);
                }
                if(methodNameS.equals("")){
                    return;
                }
                if(returnType.equals("")){
                    return;
                }

                if(isAbstractS.equals("abstract")){
                    methodInfo = accessType + " " + isAbstractS + " " + returnType + " " + methodNameS + "(" + methodPars + ")";
                    
                } else {
                    methodInfo = accessType + " " + returnType + " " + methodNameS + "(" + methodPars + ")";
                }
            
                methodListModel.addElement(methodInfo);

                MethodInformation method = new MethodInformation(accessType, isAbstractS, returnType, methodNameS, methodPars, "markPH");
                ClassesManager.getClass(selectedClassIndex).addMethod(method);

                methodPars = "";
                methodParameters.clear();
                //idk

                methodName.setText("");
                objMethodType.setText("");
                    }
        });

        //REMOVE METHOD FROM CLASS
        removeMethod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int index = methodList.getSelectedIndex();
                if(selectedClassIndex >= 0 && selectedClassIndex < ClassesManager.getClasses().size()){
                    ClassInformation selectedClass = ClassesManager.getClass(selectedClassIndex);
                    if(index != -1){
                        methodListModel.remove(index);
                        selectedClass.getMethods().remove(index);
                    }
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }
            }
        });
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                getAllMethods();
            }
        });

        // ALLOWS OBJECT TYPE TO BE INPUTTED
        methodType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if("object".equals(methodType.getSelectedItem())){
                    objMethodType.setVisible(true);
                    objMethodTypeLabel.setVisible(true);
                } else {
                    objMethodType.setText("");
                    objMethodType.setVisible(false);
                    objMethodTypeLabel.setVisible(false);
                }
            }
        });

        //ADDS PARAMETER OF METHOD TO ARRAYLIST
        addMethodParameter.addActionListener(new ActionListener() {//this is the btn
            @Override
            public void actionPerformed(ActionEvent e){
                String parType = (String) parameterType.getSelectedItem();
                if(parType.equals("object")){
                    String parInput = (String) parameterInput.getText();
                    parType = (String) objParameterType.getText();

                    String allTogether = combineObjPars(parType, ObjParameters);
                    methodParameters.add(allTogether);
                    ObjParameters.clear();
                    objParameterType.setText("");
                    
                } else {
                    String parInput = (String) parameterInput.getText();
                    String type_input = parType + " " + parInput;
                    methodParameters.add(type_input); 
                    System.out.println(methodParameters);
                    parameterInput.setText("");
                }
                 
            }
        });

        addMethodParameterObj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // ObjParameters
                String parType = (String) parameterTypeObj.getSelectedItem();
                String parInput = (String) parameterInputObj.getText();
                String type_input = parType + " " + parInput;
                ObjParameters.add(type_input);
                System.out.println(ObjParameters);
                parameterInputObj.setText("");
                
            }
        });
        

        parameterType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if("object".equals(parameterType.getSelectedItem())){
                    objParameterType.setVisible(true);
                    objParameterTypeLabel.setVisible(true);
                    parameterInputL.setVisible(false);
                    parameterInput.setVisible(false);
                    show_hide_objPar_Panel.setVisible(true);

                } else {
                    objParameterType.setText("");
                    objParameterTypeLabel.setVisible(false);
                    objParameterType.setVisible(false);
                    parameterInputL.setVisible(true);
                    parameterInput.setVisible(true);
                    show_hide_objPar_Panel.setVisible(false);
                }
            }
        });


        return fullPanel;
    }

    private String combineObjPars(String parType, ArrayList<String> test){
        System.out.println("testing method");
        System.out.println(test);
        String objPars = "";
        for(String element : test){
            objPars += element + ",";
        }
        if(objPars.length() > 0){
            objPars = objPars.substring(0, objPars.length() - 1);
        }

        String allTogether = parType + " {" + objPars + "}";
        System.out.println(allTogether);
        return allTogether;
    }

    private void updateSelectedClassComboBox() {
        selectedClassComboBox.removeAllItems();
        for (ClassInformation c : ClassesManager.getClasses()) {
            String className = c.getClassName();
            selectedClassComboBox.addItem(className);
        }
    }

    private void getAllMethods(){
        for(ClassInformation classInfo: ClassesManager.getClasses()){
            System.out.println(classInfo.toString());
            System.out.println("Methods:");

            for(MethodInformation methodInfo : classInfo.getMethods()){
                System.out.println(methodInfo.toString());
                System.out.println("-----------------------------------------");
            }
        }
    }

    private void getAllClasses() {
        System.out.println(ClassesManager.getClasses());
    }
}

