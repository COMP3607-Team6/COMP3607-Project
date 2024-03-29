package com.example.AssignmentSpecificationPortal.Sections;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;

/**
* This class creates the Panel that holds the TabbedPane for Classes/Attribute/Methods of the GUI
*/
public abstract class Section2_Input extends JPanel {
    private JLabel prompt;
    private JPanel promptPanel;
    protected JComboBox<String> selectedClassComboBox;
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JButton saveButton;
    private JButton removeButton;
    private JPanel outputPanel;

    public Section2_Input() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      
    }

    /**
    * This method creates the prompt panel to tell user what to do for section
    */
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
        mainPanel.add(outputPanel);

        return mainPanel;
    }

    /**
    * This method creates the panel to catch user input
    */
    public JPanel createInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        setupInputPanel(inputPanel);

        return inputPanel;
    }

    /**
    * This method creates the panel to display input received
    */
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

    /**
    * This method updates the class dropdown so the user can select a class
    */
    public void updateSelectedClassComboBox() {
        selectedClassComboBox.removeAllItems();
        for (ClassInformation c : ClassesManager.getClasses()) {
            String className = c.getClassName();
            selectedClassComboBox.addItem(className);
        }
    }

}
