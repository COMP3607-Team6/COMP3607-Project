package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Section1 extends JPanel {
    private AssignmentSpecPortal parent;
    // private JPanel section1;
    // private JPanel section2;
	// private Container contentPane;
	private JLabel title;
	private JLabel introLabel;
	private JLabel courseCode;
	private JTextField courseCodeTextField;
	private JLabel aTitle;
	private JTextField aTitleTextField;
	private JLabel aDescription;
	private JTextArea aDescriptionTextArea;
	private JLabel deadline;
	private JComboBox date;
	private JComboBox month;
	private JComboBox year;
    private JLabel filePathLabel;
    private JLabel actualFilePathLabel;
    private JButton browseButton;
	private JButton nextButton;

    private String dates[]
		= { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private String months[]
		= { "Jan", "Feb", "Mar", "Apr", "May", "Jun", 
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	private String years[]
		= { "2020", "2021", "2022", "2023", "2024", "2025" };

    public Section1(AssignmentSpecPortal parent) {
        // section1 = new JPanel();
        this.parent = parent;
        setLayout(null);

        title = new JLabel("Welcome");
		title.setFont(new Font("Arial", Font.PLAIN, 30));
		title.setSize(300, 30);
		title.setLocation(380, 30);
		add(title);

        introLabel = new JLabel("This system is designed to perform automated evaluation of assignments based on your criteria.");
		introLabel.setFont(new Font("Arial", Font.ITALIC, 15));
		introLabel.setSize(800, 30);
		introLabel.setLocation(105, 70);
		add(introLabel);

		courseCode = new JLabel("Course Code:");
		courseCode.setFont(new Font("Arial", Font.PLAIN, 14));
		courseCode.setSize(100, 20);
		courseCode.setLocation(100, 130);
		add(courseCode);

		courseCodeTextField = new JTextField();
		courseCodeTextField.setFont(new Font("Arial", Font.PLAIN, 15));
		courseCodeTextField.setSize(100, 20);
		courseCodeTextField.setLocation(200, 130);
		add(courseCodeTextField);

		aTitle = new JLabel("Title:");
		aTitle.setFont(new Font("Arial", Font.PLAIN, 14));
		aTitle.setSize(100, 20);
		aTitle.setLocation(100, 170);
		add(aTitle);

		aTitleTextField = new JTextField();
		aTitleTextField.setFont(new Font("Arial", Font.PLAIN, 15));
		aTitleTextField.setSize(200, 20);
		aTitleTextField.setLocation(200, 170);
		add(aTitleTextField);

        aDescription = new JLabel("Description:");
		aDescription.setFont(new Font("Arial", Font.PLAIN, 14));
		aDescription.setSize(100, 20);
		aDescription.setLocation(100, 210);
		add(aDescription);

		aDescriptionTextArea = new JTextArea();
		aDescriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
		aDescriptionTextArea.setSize(300, 60);
		aDescriptionTextArea.setLocation(200, 210);
        aDescriptionTextArea.setLineWrap(true);
        aDescriptionTextArea.setWrapStyleWord(true);
        // JScrollPane aDescriptionScrollPane = new JScrollPane(aDescriptionTextArea,
        // JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(aDescriptionTextArea);

		deadline = new JLabel("Deadline:");
		deadline.setFont(new Font("Arial", Font.PLAIN, 14));
		deadline.setSize(100, 20);
		deadline.setLocation(100, 300);
		add(deadline);

		date = new JComboBox(dates);
		date.setFont(new Font("Arial", Font.PLAIN, 15));
		date.setSize(50, 20);
		date.setLocation(200, 300);
		add(date);

		month = new JComboBox(months);
		month.setFont(new Font("Arial", Font.PLAIN, 15));
		month.setSize(60, 20);
		month.setLocation(260, 300);
		add(month);

		year = new JComboBox(years);
		year.setFont(new Font("Arial", Font.PLAIN, 15));
		year.setSize(70, 20);
		year.setLocation(330, 300);
		add(year);

        filePathLabel = new JLabel("Folder:");
		filePathLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		filePathLabel.setSize(60, 20);
		filePathLabel.setLocation(100, 340);
		add(filePathLabel);
       
        browseButton = new JButton("Browse");
        browseButton.setSize(100, 20);
        browseButton.setLocation(200, 340);
        add(browseButton);

        actualFilePathLabel = new JLabel("no file added");
		actualFilePathLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		actualFilePathLabel.setSize(200, 20);
		actualFilePathLabel.setLocation(310, 340);
		add(actualFilePathLabel);

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

		nextButton = new JButton("Next");
		nextButton.setFont(new Font("Arial", Font.PLAIN, 15));
		nextButton.setSize(100, 20);
		nextButton.setLocation(150, 450);
		add(nextButton);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.switchToSection2();
            }
        });
    }
}
