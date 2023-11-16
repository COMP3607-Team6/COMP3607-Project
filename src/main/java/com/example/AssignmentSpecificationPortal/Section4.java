package com.example.AssignmentSpecificationPortal;


// import java.awt.CardLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// import javax.swing.BoxLayout;
// import javax.swing.DefaultListModel;
// import javax.swing.JButton;
// import javax.swing.JList;
// import javax.swing.JPanel;
import java.awt.*;//for font feature
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Section4 extends JPanel {
    private CardLayout cardLayout;
    private JButton backButton;
    private JButton nextButton;

    private JPanel methodPanel;
    private DefaultListModel<String> methodListModel;
    private JList<String> methodList;
    private JLabel sectionName;


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
    private ArrayList<String> objTypeMethodParameters;


    public Section4(CardLayout layout) {
        cardLayout = layout;
        backButton = new JButton("Back");
        nextButton = new JButton("Next");


        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                layout.previous(getParent());
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(getParent());
            }
        });

        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createMethodPanel());
        
        add(backButton);
        add(nextButton);
    }

    private JPanel createMethodPanel(){
        methodParameters = new ArrayList<>();
        objTypeMethodParameters = new ArrayList<>();
        JPanel fullPanel = new JPanel();
        fullPanel.setLayout(new GridLayout(4,1));


        JPanel add_remove_panel = new JPanel();
        JPanel parameter_panel = new JPanel();



        methodPanel = new JPanel();
        methodPanel.setLayout(new FlowLayout());//can make this a gridlayout and add panels as i see fit

        methodListModel = new DefaultListModel<>();
        methodList = new JList<>(methodListModel);

        sectionName = new JLabel("Enter class methods                           ");
        sectionName.setFont(new Font("Arial", Font.ITALIC, 22));

        accessTypeCB = new JComboBox<>(new String[]{"public", "private", "protected"});
        isAbstract = new JComboBox<>(new String[]{"abstract", "concrete"});
        methodType = new JComboBox<>(new String[]{"String", "int", "double","float", "boolean", "char", "void", "object"});

        objMethodType = new JTextField(10);
        objMethodType.setText("");
        objMethodType.setVisible(false);

        methodName = new JTextField(15);
        methodName.setText("");

        addMethod = new JButton("Add method to class");
        removeMethod = new JButton("Remove method from class");


        //section to add to panel
        //methodPanel.add(sectionName);
        methodPanel.add(accessTypeCB);
        methodPanel.add(isAbstract);
        methodPanel.add(methodType);
        methodPanel.add(objMethodType);
        methodPanel.add(methodName);

        parameterType = new JComboBox<>(new String[]{"String", "int", "double","float", "boolean", "char", "object"});
        objParameterType = new JTextField(10);
        objParameterType.setText("");
        objParameterType.setVisible(false);

        parameterInputL = new JLabel("Expected Input:");
        parameterInput = new JTextField(5);

        addMethodParameter = new JButton("Add parameter to method");

        /// following snippet should prob be in its own panel for easy hiding and reappearing
        parameterInputLObj = new JLabel("input parameter for object:");
        parameterInputLObj.setVisible(false);

        parameterTypeObj = new JComboBox<>(new String[]{"String", "int", "double","float", "boolean", "char"});
        parameterTypeObj.setVisible(false);

        parameterInputObj = new JTextField(5);
        parameterInputObj.setVisible(false);

        addMethodParameterObj = new JButton("add parameter to object");
        addMethodParameterObj.setVisible(false);
        ///





        parameter_panel.add(parameterType);
        parameter_panel.add(objParameterType);
        parameter_panel.add(parameterInputL);
        parameter_panel.add(parameterInput);
        parameter_panel.add(addMethodParameter);

        parameter_panel.add(parameterInputLObj);
        parameter_panel.add(parameterTypeObj);
        parameter_panel.add(parameterInputObj);
        parameter_panel.add(addMethodParameterObj);

        add_remove_panel.add(addMethod);
        add_remove_panel.add(removeMethod);


        //adds methodpanel to fullpanel
        fullPanel.add(methodPanel);
        fullPanel.add(parameter_panel);
        fullPanel.add(add_remove_panel);
        fullPanel.add(new JScrollPane(methodList));



        //listeners
        //checks if method type is not of primitive type
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
        
        //checks if parameter type is not of primitive type
        parameterType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if("object".equals(parameterType.getSelectedItem())){
                    objParameterType.setVisible(true);
                    parameterInputL.setVisible(false);
                    parameterInput.setVisible(false);

                    parameterInputLObj.setVisible(true);
                    parameterTypeObj.setVisible(true);
                    parameterInputObj.setVisible(true);
                    addMethodParameterObj.setVisible(true);

                } else {
                    objParameterType.setText("");
                    objParameterType.setVisible(false);
                    parameterInputL.setVisible(true);
                    parameterInput.setVisible(true);

                    parameterInputLObj.setVisible(false);
                    parameterTypeObj.setVisible(false);
                    parameterInputObj.setVisible(false);
                    addMethodParameterObj.setVisible(false);
                }
            }
        });

        //adds method primitive types
        addMethodParameter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String parInput = (String) parameterInput.getText();
                String parType = (String) parameterType.getSelectedItem();
                if(parType.equals("object")){
                    parType = (String) objParameterType.getText();
                }
                String type_input = parType + " " + parInput;
                methodParameters.add(type_input); 
                System.out.println(methodParameters);
                parameterInput.setText("");    
            }
        });

        addMethodParameterObj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String parInput = (String) parameterInputObj.getText();
                String parType = (String) parameterTypeObj.getSelectedItem();
                String idk = parType + " " + parInput;
                objTypeMethodParameters.add(idk);
                System.out.println(idk);
                parameterInputObj.setText("");
            }
        });

        //add method to list
        addMethod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                addMethodToList();
                
            }
        });




        return fullPanel;
    }

    private void addMethodToList(){
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
            methodPars = "(" + methodPars.substring(0, methodPars.length() - 1) + ")";
        }
        if(methodPars.length() == 0){
            methodPars = "()";
        }



        if(methodNameS.equals("")){
            return;
        }
        if(returnType.equals("")){
            return;
        }

        if(isAbstractS.equals("abstract")){
            methodInfo = accessType + " " + isAbstractS + " " + returnType + " " + methodNameS + methodPars;
            methodListModel.addElement(methodInfo);
            methodPars = "";
            methodParameters.clear();
        } else {
            methodInfo = accessType + " " + returnType + " " + methodNameS + methodPars;
            methodListModel.addElement(methodInfo);
            methodPars = "";
            methodParameters.clear();
        }

        methodName.setText("");
        objMethodType.setText("");

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

