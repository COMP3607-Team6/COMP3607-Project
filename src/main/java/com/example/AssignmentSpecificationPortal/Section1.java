package com.example.AssignmentSpecificationPortal;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.example.AssignmentSpecification;
import com.example.AutomatedJudgeSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class Section1 extends JPanel {
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

    private String dates[]
		= { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private String months[]
		= { "Jan", "Feb", "Mar", "Apr", "May", "Jun", 
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	private String years[]
		= { "2020", "2021", "2022", "2023", "2024", "2025" };
    

    public Section1(CardLayout layout,AssignmentSpecification asSpec,AutomatedJudgeSystem system) {
        cardLayout = layout;
        
        // nextButton = new JButton("Next");

        // nextButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         cardLayout.next(getParent());
        //     }
        // });

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

        welcomeMsgLabel = new JLabel("This system is designed to perform automated evaluation of assignments based on your criteria.");
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
        // JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel savePanel = new JPanel();
        savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.Y_AXIS));
        savePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        
		
        
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
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int returnValue = fileChooser.showDialog(null, "Select");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedFolderPath = fileChooser.getSelectedFile().getAbsolutePath();
                    actualFilePathLabel.setText(selectedFolderPath);
                }
            }
        });

        specButton = new JButton("Add Assignment Specification");
        
        // ActionListener for the spec button
        specButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String courseCode = (String) courseCodeField.getText();
               asSpec.setCourseCode(courseCode);

               String title = (String) titleField.getText();
               asSpec.setTitle(title);

               String weightingFieldValue = weightingField.getText(); 
               int weighting = Integer.parseInt(weightingFieldValue);
               asSpec.setAssignmentWeighting(weighting);

               String desc = (String) descriptionTextArea.getText();
               asSpec.setDescription(desc);

               String a = (String) date.getSelectedItem();
               String b = (String) month.getSelectedItem();
               String c = (String) year.getSelectedItem();
               String deadline = a + " " + b + " " + c;
               asSpec.setDeadlineDate(deadline);

               String filepath = (String) actualFilePathLabel.getText();
               asSpec.setFolderPath(filepath);
               System.out.println("Assignment Specification Saved");
            }
        });
        
        savePanel.add(specButton);
        
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
        mainPanel.add(titlePanel); 
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(weightingPanel); 
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(descriptionPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(deadlinePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(filePathPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        mainPanel.add(savePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		centerAlignPanel.add(mainPanel);
        add(welcomePanel);
        add(centerAlignPanel);
        // add(buttonPanel);
    }

    // Restricts input to integers only
    // use like: 
    //     JTextField marksTextField = new JTextField();
    //     marksTextField.setDocument(new IntegerDocument());
    static class IntegerDocument extends PlainDocument {
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
