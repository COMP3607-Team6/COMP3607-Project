package com.example.AssignmentSpecificationPortal.Sections;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.example.AssignmentSpecification;
import com.example.AutomatedJudgeSystem;
import com.example.AssignmentSpecificationPortal.AssignmentSpecPortal;

public class Section1 extends JPanel {
    //
    /* This class creates the Layout for Assignment Spec Input Panel (Section 1) of the GUI
     */
    
    // private JButton nextButton;
    private CardLayout cardLayout;

    private JLabel welcomeLabel;
    private JLabel welcomeMsgLabel;

    private JTextField courseCodeField;
    private JLabel courseCodeLabel;

    private JTextField titleField;
    private JLabel titleLabel;

    private JLabel weightingLabel;
    private JTextField weightingField;
    private JLabel weightingPercentageLabel;

    private JTextArea descriptionTextArea;
    private JLabel descriptionLabel;

    private JLabel deadlineLabel;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;

    private JLabel filePathLabel;
    private JLabel actualFilePathLabel;

    private JButton browseButton;
    private JButton specButton;

    private JLabel specNotCompleteLabel;

    private String dates[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
    private String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    private String years[] = { "2020", "2021", "2022", "2023", "2024", "2025" };

    private JPanel specSavePanel;

    private JLabel numberLabel;

    private JTextField numberField;

    public Section1(CardLayout layout, AssignmentSpecification asSpec, AutomatedJudgeSystem system, AssignmentSpecPortal portal) {
        cardLayout = layout;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel centerAlignPanel = new JPanel();
        centerAlignPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Vertical layout

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomeMsgLabel = new JLabel(
                "This system is designed to perform automated evaluation of assignments based on your criteria.");
        welcomeMsgLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        welcomeMsgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomePanel.add(welcomeLabel);
        welcomePanel.add(welcomeMsgLabel);

        courseCodeLabel = new JLabel("Course Code:");
        courseCodeField = new JTextField(15);
        JPanel courseCodePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        courseCodeLabel.setPreferredSize(new Dimension(120, 20)); // Set the width of the label
        courseCodePanel.add(courseCodeLabel);
        courseCodePanel.add(courseCodeField);

        
        numberLabel = new JLabel("Assignment #:");
        numberField = new JTextField(4);
        numberField.setDocument(new IntegerDocument());
        JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        numberLabel.setPreferredSize(new Dimension(120, 20));
        numberPanel.add(numberLabel);
        numberPanel.add(numberField);

        titleLabel = new JLabel("Title:");
        titleField = new JTextField(30);
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titleLabel.setPreferredSize(new Dimension(120, 20));
        titlePanel.add(titleLabel);
        titlePanel.add(titleField);

        weightingLabel = new JLabel("Weighting:");
        weightingField = new JTextField(4);
        weightingField.setDocument(new IntegerDocument());
        weightingPercentageLabel = new JLabel("%");
        JPanel weightingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        weightingLabel.setPreferredSize(new Dimension(120, 20));
        weightingPanel.add(weightingLabel);
        weightingPanel.add(weightingField);
        weightingPanel.add(weightingPercentageLabel);

        descriptionLabel = new JLabel("Description:");
        descriptionTextArea = new JTextArea();
        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        descriptionLabel.setPreferredSize(new Dimension(120, 20));
        descriptionTextArea.setPreferredSize(new Dimension(300, 60));

        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.gray));

        // JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea,
        // JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        // JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionTextArea);

        deadlineLabel = new JLabel("Deadline:");
        JPanel deadlinePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deadlineLabel.setPreferredSize(new Dimension(120, 20));

        date = new JComboBox(dates);
        month = new JComboBox(months);
        year = new JComboBox(years);

        date.setPreferredSize(new Dimension(50, 20));
        month.setPreferredSize(new Dimension(60, 20));
        year.setPreferredSize(new Dimension(60, 20));

        deadlinePanel.add(deadlineLabel);
        deadlinePanel.add(date);
        deadlinePanel.add(month);
        deadlinePanel.add(year);

        JPanel filePathPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        filePathLabel = new JLabel("Folder:");
        filePathLabel.setPreferredSize(new Dimension(120, 20));

        browseButton = new JButton("Browse");
        browseButton.setPreferredSize(new Dimension(100, 20));

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a file filter for ZIP files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("ZIP files (*.zip)", "zip");

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(filter);

                int returnValue = fileChooser.showDialog(null, "Select");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                    actualFilePathLabel.setText(selectedFilePath);
                }
            }
        });

        specSavePanel = new JPanel();
        specSavePanel.setLayout(new BoxLayout(specSavePanel, BoxLayout.Y_AXIS));
        specSavePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        specButton = new JButton("Add Assignment Specification");
        specButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        specButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String courseCode = (String) courseCodeField.getText();
                String number = (String) numberField.getText();
                int num = Integer.parseInt(number);
                String title = (String) titleField.getText();

                String weightingFieldValue = weightingField.getText();
                int weighting = 0;
                if (!weightingFieldValue.isEmpty()) {
                    try {
                        weighting = Integer.parseInt(weightingFieldValue);
                    } catch (final Exception ex) {
                        weighting = 999;
                        // ex.printStackTrace();
                    }
                }

                String desc = (String) descriptionTextArea.getText();

                String a = (String) date.getSelectedItem();
                String b = (String) month.getSelectedItem();
                String c = (String) year.getSelectedItem();
                String deadline = a + " " + b + " " + c;

                String filepath = (String) actualFilePathLabel.getText();

                if (courseCode.isEmpty() 
                || title.isEmpty() || weightingFieldValue.isEmpty() || desc.isEmpty() ||
                        (filepath.isEmpty() || filepath == "no file added")
                        ) {
                    specNotCompleteLabel.setText("Please fill all fields.");
                } else {
                    File zipFile = new File(filepath);

                    if (weighting == 999) {
                        specNotCompleteLabel.setText("Invalid: Weighting received invalid");
                    } else if (weighting > 100) {
                        specNotCompleteLabel.setText("Invalid: Weighting received exceeds 100%");
                    } else if (weighting < 0) {
                        specNotCompleteLabel.setText("Invalid: Weighting received is less than 0%");
                    } else if (!zipFile.exists()) {
                        specNotCompleteLabel.setText("No zip file exists at this location.");
                    } else {
                        asSpec.setCourseCode(courseCode);
                        asSpec.setTitle(title);
                        asSpec.setAssignmentWeighting(weighting);
                        asSpec.setDescription(desc);
                        asSpec.setDeadlineDate(deadline);
                        asSpec.setFolderPath(filepath);
                        asSpec.setAssignmentNumber(num);
                        
                        specNotCompleteLabel.setText("Assignment Specification Saved");
                        System.out.println("Assignment Specification Saved");
                    }
                }
                
                portal.onGUISaveSpecButtonPressed(specNotCompleteLabel.getText());
            }
        });
        

        specNotCompleteLabel = new JLabel("");
        specNotCompleteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        specSavePanel.add(specButton);
        specSavePanel.add(specNotCompleteLabel);

        actualFilePathLabel = new JLabel("no file added");
        actualFilePathLabel.setPreferredSize(new Dimension(200, 20));

        filePathPanel.add(filePathLabel);
        filePathPanel.add(browseButton);
        filePathPanel.add(actualFilePathLabel);

        // JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // nextButton.setPreferredSize(new Dimension(100, 30));
        // buttonPanel.add(nextButton);

        // mainPanel.add(welcomePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        mainPanel.add(courseCodePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(numberPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(weightingPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(descriptionPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(deadlinePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(filePathPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(specSavePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        centerAlignPanel.add(mainPanel);
        add(welcomePanel);
        add(centerAlignPanel);
        // add(buttonPanel);
    }

    // Restricts input to integers only
    class IntegerDocument extends PlainDocument {
        private final Pattern pattern = Pattern.compile("-?\\d*");

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            String text = getText(0, getLength()) + str;
            if (pattern.matcher(text).matches()) {
                super.insertString(offs, str, a);
            }
        }
    }
}
