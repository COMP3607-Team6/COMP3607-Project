package com.example.AssignmentSpecificationPortal.Sections;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.AssignmentSpecificationPortal.AttributeInformation;
import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;
import com.example.AssignmentSpecificationPortal.MethodInformation;

public abstract class Section2_Input extends JPanel {
    //
    /* This class creates the Panel that holds the TabbedPane for Classes/Attribute/Methods of the GUI
     */

    private CardLayout cardLayout;
    private JLabel prompt;
    private JPanel promptPanel;
    protected JComboBox<String> selectedClassComboBox;
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JButton saveButton;
    private JButton removeButton;
    private JPanel outputPanel;

    public Section2_Input(CardLayout layout) {
        this.cardLayout = layout;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      
    }

    public JPanel createPromptPanel(String promptMsg) {
        prompt = new JLabel("  " + promptMsg);
        promptPanel = new JPanel();
        promptPanel.setLayout(new BoxLayout(promptPanel, BoxLayout.X_AXIS));
        promptPanel.add(prompt);
        promptPanel.add(Box.createHorizontalGlue());

        return promptPanel;
    }

    public JPanel createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        inputPanel = createInputPanel();
        outputPanel = createOutputPanel();

        mainPanel.add(inputPanel);
        // mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(outputPanel);
        // mainPanel.add(new JScrollPane(mainList));

        return mainPanel;
    }

    public JPanel createInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        setupInputPanel(inputPanel);

        return inputPanel;
    }

    public JPanel createOutputPanel() {
        outputPanel = new JPanel();
        outputPanel.setLayout(new FlowLayout());

        setupOutputPanel(outputPanel);

        return outputPanel;
    }

    public abstract void setupInputPanel(JPanel inputPanel2);
    public abstract void setupOutputPanel(JPanel outputPanel2);

    public JPanel createButtonPanel(String saveMsg, String removeMsg) {
        buttonPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        saveButton = new JButton(saveMsg);
        removeButton = new JButton(removeMsg);

        buttonPanel.add(saveButton);
        buttonPanel.add(removeButton);

        attachSaveAndRemoveListeners(saveButton, removeButton);

        return buttonPanel;
    }

    public abstract void attachSaveAndRemoveListeners(JButton saveBtn, JButton removeBtn);

    public void updateSelectedClassComboBox() {
        selectedClassComboBox.removeAllItems();
        for (ClassInformation c : ClassesManager.getClasses()) {
            String className = c.getClassName();
            selectedClassComboBox.addItem(className);
        }
    }

    public void getAllMethods() {
        for (ClassInformation classInfo : ClassesManager.getClasses()) {
            System.out.println(classInfo.toString());
            System.out.println("Methods:");

            for (MethodInformation methodInfo : classInfo.getMethods()) {
                System.out.println(methodInfo.toString());
                System.out.println("-----------------------------------------");
            }
        }
    }

    public void getAllAttributes() {
        for (ClassInformation classInfo : ClassesManager.getClasses()) {
            System.out.println(classInfo.toString());
            System.out.println("Attributes:");

            for (AttributeInformation attributeInfo : classInfo.getAttributes()) {
                System.out.println(attributeInfo.toString());
                System.out.println("-----------------------------------------");
            }
        }
    }

    public void getAllClasses() {
        System.out.println(ClassesManager.getClasses());
    }

}
