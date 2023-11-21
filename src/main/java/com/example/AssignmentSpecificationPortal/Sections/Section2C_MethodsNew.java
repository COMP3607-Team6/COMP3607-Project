package com.example.AssignmentSpecificationPortal.Sections;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;
import com.example.AssignmentSpecificationPortal.MethodInformation;

public class Section2C_MethodsNew extends Section2_Input {
     //
    /* This class creates the Layout for Method Input TabbedPane of the GUI
     */

    private JPanel promptPanel;
    private JPanel selectedClassPanel;
    private int selectedClassIndex;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JPanel mainPanel;
    private DefaultListModel listModel;
    private JList list;
    protected int classCount;
    private JLabel selectedClassLabel;
    private JButton loadClassesButton;
    private JPanel methodPanel;
    private JComboBox accessTypeCB;
    private JComboBox isAbstract;
    private JComboBox methodType;
    private JTextField objMethodType;
    private JLabel objMethodTypeLabel;
    private JLabel methodNameLabel;
    private JTextField methodName;
    private JButton test;
    private JPanel paramPanel;
    private JComboBox parameterType;
    private JTextField objParameterType;
    private JLabel objParameterTypeLabel;
    private JLabel parameterInputL;
    private JTextField parameterInput;
    private JButton addMethodParameter;
    private JPanel showHideObjParPanel;
    private JLabel parameterInputLObj;
    private JComboBox parameterTypeObj;
    private JLabel objExpectedInputLabel;
    private JTextField parameterInputObj;
    private JButton addMethodParameterObj;
    private ArrayList<String> methodParameters;
    private ArrayList<String> ObjParameters;
    // private JRadioButton staticRadioButton;
    // private JRadioButton finalRadioButton;
    private JCheckBox staticCheckButton;
    private JCheckBox finalCheckButton;

    
    public Section2C_MethodsNew(CardLayout layout) {
        super(layout);

        promptPanel = createPromptPanel("Add methods (for inputted classes) to be tested here. Enter method signature:");
        selectedClassPanel = createSelectedClassPanel();
        mainPanel = createMainPanel();

        add(promptPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(selectedClassPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(mainPanel);

        attachListeners();
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

    @Override
    public void setupInputPanel(JPanel inputPanel2) {
        inputPanel = inputPanel2;
        inputPanel.setPreferredSize(new Dimension(200, 150));

        methodParameters = new ArrayList<>();
        ObjParameters = new ArrayList<>();

        methodPanel = createMethodPanel();
        paramPanel = createParamPanel();
        showHideObjParPanel = createShowHideObjParPanel();
        
        buttonPanel = createButtonPanel("Add Method", "Remove Selected Method");

        inputPanel.add(methodPanel);
        inputPanel.add(paramPanel);
        inputPanel.add(showHideObjParPanel);
        inputPanel.add(buttonPanel);
    }

    private JPanel createShowHideObjParPanel() {
        JPanel show_hide_objPar_Panel = new JPanel(); //layer 4
        show_hide_objPar_Panel.setLayout(new FlowLayout());
        
        parameterInputLObj = new JLabel("input parameter for object:");
        parameterTypeObj = new JComboBox<>(new String[]{"String", "int", "double","float", "boolean", "char"});
        objExpectedInputLabel = new JLabel("Parameter Name:");
        parameterInputObj = new JTextField(5);
        addMethodParameterObj = new JButton("add parameter to object");
        
        show_hide_objPar_Panel.setVisible(false);
        show_hide_objPar_Panel.add(parameterInputLObj);
        show_hide_objPar_Panel.add(parameterTypeObj);
        show_hide_objPar_Panel.add(objExpectedInputLabel);
        show_hide_objPar_Panel.add(parameterInputObj);
        show_hide_objPar_Panel.add(addMethodParameterObj);

        return show_hide_objPar_Panel;
    }

    private JPanel createParamPanel() {
        JPanel parameter_panel = new JPanel();//layer2
        parameter_panel.setLayout(new FlowLayout());

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

        return parameter_panel;
    }

    private JPanel createMethodPanel() {
        JPanel mPanel = new JPanel();//layer1
        mPanel.setLayout(new FlowLayout());

        accessTypeCB = new JComboBox<>(new String[]{"public", "private", "protected"});
        staticCheckButton = new JCheckBox("static");
        finalCheckButton = new JCheckBox("final");
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

        // mPanel.add(test);
        mPanel.add(accessTypeCB);
        mPanel.add(staticCheckButton);
        mPanel.add(finalCheckButton);
        mPanel.add(isAbstract);
        mPanel.add(methodType);
        mPanel.add(objMethodTypeLabel);
        mPanel.add(objMethodType);
        mPanel.add(methodNameLabel);
        mPanel.add(methodName);
        
        return mPanel;
    }

    @Override
    public void setupOutputPanel(JPanel outputPanel2) {
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setFixedCellWidth(600);
        list.setFixedCellHeight(22);
        outputPanel2.add(new JScrollPane(list));
    }

    

    @Override
    public void attachSaveAndRemoveListeners(JButton saveBtn, JButton removeBtn) {
         saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String methodInfo = "";
                String accessType = (String) accessTypeCB.getSelectedItem();
                String isAbstractS = (String) isAbstract.getSelectedItem();
                String sendStatic = "";
                String sendFinal = "";

                
                if(staticCheckButton.isSelected()){
                    sendStatic = "static";
                    sendFinal = "";
                    //finalCheckButton.setVisible(false);
                }

                if(finalCheckButton.isSelected()){
                    sendFinal = "final";
                    sendStatic = "";
                }

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

                MethodInformation method = new MethodInformation(accessType, isAbstractS, returnType, methodNameS, methodPars, sendStatic ,sendFinal);
                ClassesManager.getClass(selectedClassIndex).addMethod(method);

                System.out.println(method.toString());

                listModel.addElement(method.toString());

                methodPars = "";
                methodParameters.clear();

                methodName.setText("");
                objMethodType.setText("");

                staticCheckButton.setVisible(true);
                finalCheckButton.setVisible(true);
                staticCheckButton.setSelected(false);
                finalCheckButton.setSelected(false);
            }
        });

        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int index = list.getSelectedIndex();
                if(selectedClassIndex >= 0 && selectedClassIndex < ClassesManager.getClasses().size()){
                    ClassInformation selectedClass = ClassesManager.getClass(selectedClassIndex);
                    if(index != -1){
                       listModel.remove(index);
                        selectedClass.getMethods().remove(index);
                    }
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }
            }
        });
    }
    
    public void attachListeners() {
        loadClassesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedClassComboBox();
                selectedClassComboBox.setVisible(true);
                getAllClasses();
                getAllAttributes();
                getAllMethods();
            }
        });

        selectedClassComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassIndex = selectedClassComboBox.getSelectedIndex();

                if (selectedClassIndex >= 0 && selectedClassIndex < ClassesManager.getClasses().size()) {
                    ClassInformation selectedClass = ClassesManager.getClass(selectedClassIndex);
                    listModel.clear();

                    for (MethodInformation method : selectedClass.getMethods()) {
                        listModel.addElement(method.toString());
                    }
                } else {
                    System.out.println("Invalid selected index or class not found.");
                }
            }
        });

        staticCheckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (true == staticCheckButton.isSelected()) {
                    finalCheckButton.setVisible(false);
                    finalCheckButton.setSelected(false);
                }
                else {
                    finalCheckButton.setVisible(true);
                }
            }
        });

        finalCheckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (true == finalCheckButton.isSelected()) {
                    staticCheckButton.setVisible(false);
                    staticCheckButton.setSelected(false);
                }
                else {
                    staticCheckButton.setVisible(true);
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
                    showHideObjParPanel.setVisible(true);

                } else {
                    objParameterType.setText("");
                    objParameterTypeLabel.setVisible(false);
                    objParameterType.setVisible(false);
                    parameterInputL.setVisible(true);
                    parameterInput.setVisible(true);
                    showHideObjParPanel.setVisible(false);
                }
            }
        });

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

}
