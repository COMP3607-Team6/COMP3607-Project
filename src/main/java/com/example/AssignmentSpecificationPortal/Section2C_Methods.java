package com.example.AssignmentSpecificationPortal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
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
    private CardLayout cardLayout;
    private JLabel prompt;
    private JPanel selectedClassPanel;
    private JLabel selectedClassLabel;
    private JButton loadClassesButton;
    private JComboBox<String> selectedClassComboBox;
    private ArrayList<ClassInformation> classes;
    private int selectedClassIndex;




    private JPanel methodPanel;
    private DefaultListModel<String> methodListModel;
    private JList<String> methodList;

    private JComboBox<String> accessTypeCB;
    private JComboBox<String> isAbstract;
    private JComboBox<String> methodType;
    private JTextField objMethodType;
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

    private JButton addMethod;
    private JButton removeMethod;

    private ArrayList<String> methodParameters;
    private ArrayList<MethodInformation> allMethods;
    private ArrayList<String> ObjParameters;
    

    public Section2C_Methods(CardLayout layout, ArrayList<ClassInformation> classes) {
        this.cardLayout = layout;
        this.classes = classes;
        allMethods = new ArrayList<>();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        prompt = new JLabel("Methods");
        prompt.setFont(new Font("Arial", Font.ITALIC, 22));
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        selectedClassPanel = new JPanel();
        selectedClassPanel.setLayout(new FlowLayout());
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

        add(prompt);
        add(selectedClassPanel);
        add(createMethodPanel());

        loadClassesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedClassComboBox();
                selectedClassComboBox.setVisible(true);
            }
        });

        selectedClassComboBox.addActionListener(new ActionListener() {
            //private int selectedClassIndex;

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

    private JPanel createMethodPanel(){
        methodParameters = new ArrayList<>();
        ObjParameters = new ArrayList<>();


        JPanel fullPanel = new JPanel();
        fullPanel.setLayout(new GridLayout(5,1));

        methodPanel = new JPanel();//layer1
        methodPanel.setLayout(new FlowLayout());

        JPanel parameter_panel = new JPanel();//layer2
        JPanel add_remove_panel = new JPanel();//layer 3
        JPanel show_hide_objPar_Panel = new JPanel(); //layer 4

        methodListModel = new DefaultListModel<>();
        methodList = new JList<>(methodListModel);//layer 5

        //METHOD PANEL
        accessTypeCB = new JComboBox<>(new String[]{"public", "private", "protected"});
        isAbstract = new JComboBox<>(new String[]{"abstract", "concrete"});
        methodType = new JComboBox<>(new String[]{"String", "int", "double","float", "boolean", "char", "void", "object"});
        objMethodType = new JTextField(10);
        objMethodType.setText("");
        objMethodType.setVisible(false);
        methodName = new JTextField(15);
        methodName.setText("");

        methodPanel.add(accessTypeCB);
        methodPanel.add(isAbstract);
        methodPanel.add(methodType);
        methodPanel.add(objMethodType);
        methodPanel.add(methodName);

        //PARAMTER PANEL
        parameterType = new JComboBox<>(new String[]{"String", "int", "double","float", "boolean", "char", "object"});
        objParameterType = new JTextField(10);
        objParameterType.setText("");
        objParameterType.setVisible(false);
        parameterInputL = new JLabel("Expected Input:");
        parameterInput = new JTextField(5);
        addMethodParameter = new JButton("Add parameter to method");

        parameter_panel.add(parameterType);
        parameter_panel.add(objParameterType);
        parameter_panel.add(parameterInputL);
        parameter_panel.add(parameterInput);
        parameter_panel.add(addMethodParameter);


        //OBJECT PARAMETER PANEL
        parameterInputLObj = new JLabel("input parameter for object:");
        parameterTypeObj = new JComboBox<>(new String[]{"String", "int", "double","float", "boolean", "char"});
        parameterInputObj = new JTextField(5);
        addMethodParameterObj = new JButton("add parameter to object");
        
        show_hide_objPar_Panel.setVisible(false);
        show_hide_objPar_Panel.add(parameterInputLObj);
        show_hide_objPar_Panel.add(parameterTypeObj);
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
                //addMethodToList();
                String methodInfo = "";
                String accessType = (String) accessTypeCB.getSelectedItem();
                String isAbstractS = (String) isAbstract.getSelectedItem();
                if(isAbstractS.equals("concrete")){
                    isAbstractS = "";
                }
                String returnType = (String) methodType.getSelectedItem();
                //HERE i have to somehow access arraylist with other par
                if(returnType.equals("object")){
                    returnType = (String) objMethodType.getText();
                }
                String methodNameS = (String) methodName.getText();

                String methodPars = "";
                for(String element: methodParameters){
                    System.out.println(element);
                    methodPars += element + ",";
                }

                if(methodPars.length() > 0){
                    methodPars = methodPars.substring(0, methodPars.length() - 1);
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
                allMethods.add(method);
                classes.get(selectedClassIndex).addMethod(method);

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
                if(selectedClassIndex >= 0 && selectedClassIndex < classes.size()){
                    ClassInformation selectedClass = classes.get(selectedClassIndex);
                    if(index != -1){
                        methodListModel.remove(index);
                        allMethods.remove(index);
                        selectedClass.getMethods().remove(index);
                    }
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }
            }
        });

        // ALLOWS OBJECT TYPE TO BE INPUTTED
        methodType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if("object".equals(methodType.getSelectedItem())){
                    objMethodType.setVisible(true);
                } else {
                    objMethodType.setText("");
                    objMethodType.setVisible(false);
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
                    parameterInputL.setVisible(false);
                    parameterInput.setVisible(false);

                    show_hide_objPar_Panel.setVisible(true);

                } else {
                    objParameterType.setText("");
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
        for (ClassInformation c : classes) {
            String className = c.getClassName();
            selectedClassComboBox.addItem(className);
        }
    }

    private ArrayList<MethodInformation> getAllMethods(){
        for(ClassInformation classInfo: classes){
            System.out.println(classInfo.toString());
            System.out.println("Methods:");

            for(MethodInformation methodInfo : classInfo.getMethods()){
                System.out.println(methodInfo.toString());
                System.out.println("-----------------------------------------");
            }
        }

        return allMethods;
    }

    private ArrayList<ClassInformation> getAllClasses() {
        for (ClassInformation classInfo : classes) {
            System.out.println(classInfo.toString());
            System.out.println("-----------------------------------------");
        }

        return classes;
    }

    private void addMethodToList(){
        

        // String accessType = (String) accessTypeCB.getSelectedItem();
        // String isAbstractS = (String) isAbstract.getSelectedItem();
        // String returnType = (String) methodType.getSelectedItem();
        // if(returnType.equals("object")){
        //     returnType = (String) objMethodType.getText();
        // }
        // String methodNameS = (String) methodName.getText();
        // // String marks = (String) markFieldM.getText();
        // String methodInfo = "";

        // String tested = "";
        // for(String element: methodParameters){
        //     tested += element + ",";
        // }

        // if(tested.length() > 0){
        //     tested = tested.substring(0, tested.length() - 1);
        // }

        // if(methodName.equals("")){
        //     return;
        // }
        // if(returnType.equals("")){
        //     return;
        // }

        // if(isAbstractS.equals("Abstract")){
        //     methodInfo = accessType + " " + isAbstractS + " " + returnType + " " + methodNameS + "(" + tested + ");" + " [ " + " marks]";
        //     methodListModel.addElement(methodInfo);
        //     tested = "";
        //     methodParameters.clear();
        // }
        // if(isAbstractS.equals("Concrete")){
        //     methodInfo = accessType + " " + returnType + " " + methodNameS + "(" + tested + ")" + " [ " + " marks]";
        //     methodListModel.addElement(methodInfo);
        //     System.out.println(methodInfo);
        //     tested = "";
        //     methodParameters.clear();
        // }
        // methodName.setText("");
        // objMethodType.setText("");
        // // markFieldM.setText("");
    }
}

