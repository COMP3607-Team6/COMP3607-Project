package com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

class AssignmentSpecPortal extends JFrame{
    private JPanel section1;
    private JPanel section2;
	private Container contentPane;
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

	
	public AssignmentSpecPortal()
	{
        initFrame();

        createSection1();
        // createSection2();
        
		

		// setVisible(true);
	}

	private void createSection1() {
        title = new JLabel("Welcome");
		title.setFont(new Font("Arial", Font.PLAIN, 30));
		title.setSize(300, 30);
		title.setLocation(380, 30);
		section1.add(title);

        introLabel = new JLabel("This system is designed to perform automated evaluation of assignments based on your criteria.");
		introLabel.setFont(new Font("Arial", Font.ITALIC, 15));
		introLabel.setSize(800, 30);
		introLabel.setLocation(105, 70);
		section1.add(introLabel);

		courseCode = new JLabel("Course Code:");
		courseCode.setFont(new Font("Arial", Font.PLAIN, 14));
		courseCode.setSize(100, 20);
		courseCode.setLocation(100, 130);
		section1.add(courseCode);

		courseCodeTextField = new JTextField();
		courseCodeTextField.setFont(new Font("Arial", Font.PLAIN, 15));
		courseCodeTextField.setSize(100, 20);
		courseCodeTextField.setLocation(200, 130);
		section1.add(courseCodeTextField);

		aTitle = new JLabel("Title:");
		aTitle.setFont(new Font("Arial", Font.PLAIN, 14));
		aTitle.setSize(100, 20);
		aTitle.setLocation(100, 170);
		section1.add(aTitle);

		aTitleTextField = new JTextField();
		aTitleTextField.setFont(new Font("Arial", Font.PLAIN, 15));
		aTitleTextField.setSize(200, 20);
		aTitleTextField.setLocation(200, 170);
		section1.add(aTitleTextField);

        aDescription = new JLabel("Description:");
		aDescription.setFont(new Font("Arial", Font.PLAIN, 14));
		aDescription.setSize(100, 20);
		aDescription.setLocation(100, 210);
		section1.add(aDescription);

		aDescriptionTextArea = new JTextArea();
		aDescriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
		aDescriptionTextArea.setSize(300, 60);
		aDescriptionTextArea.setLocation(200, 210);
        aDescriptionTextArea.setLineWrap(true);
        aDescriptionTextArea.setWrapStyleWord(true);
        // JScrollPane aDescriptionScrollPane = new JScrollPane(aDescriptionTextArea,
        // JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		section1.add(aDescriptionTextArea);

		deadline = new JLabel("Deadline:");
		deadline.setFont(new Font("Arial", Font.PLAIN, 14));
		deadline.setSize(100, 20);
		deadline.setLocation(100, 300);
		section1.add(deadline);

		date = new JComboBox(dates);
		date.setFont(new Font("Arial", Font.PLAIN, 15));
		date.setSize(50, 20);
		date.setLocation(200, 300);
		section1.add(date);

		month = new JComboBox(months);
		month.setFont(new Font("Arial", Font.PLAIN, 15));
		month.setSize(60, 20);
		month.setLocation(260, 300);
		section1.add(month);

		year = new JComboBox(years);
		year.setFont(new Font("Arial", Font.PLAIN, 15));
		year.setSize(70, 20);
		year.setLocation(330, 300);
		section1.add(year);

        filePathLabel = new JLabel("Folder:");
		filePathLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		filePathLabel.setSize(60, 20);
		filePathLabel.setLocation(100, 340);
		section1.add(filePathLabel);
       
        browseButton = new JButton("Browse");
        browseButton.setSize(100, 20);
        browseButton.setLocation(200, 340);
        section1.add(browseButton);

        actualFilePathLabel = new JLabel("no file added");
		actualFilePathLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		actualFilePathLabel.setSize(200, 20);
		actualFilePathLabel.setLocation(310, 340);
		section1.add(actualFilePathLabel);

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
		section1.add(nextButton);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                section1.setVisible(false);
                section2.setVisible(true);
                revalidate();
                repaint();
                createSection2();
            }
        });
        
        // contentPane.add(section1); // not working
        setContentPane(section1);
    }

    private void createSection2() {
        section2.setLayout(new BorderLayout());
    
        String[] columnNames = {"Access", "Instance Variable", "Type", "Attribute Name", "Marks"};
        Object[][] data = {
            {"private", Boolean.FALSE, "", "", 0},
            {"public", Boolean.FALSE, "", "", 0},
            {"protected", Boolean.FALSE, "", "", 0},
            {"private", Boolean.FALSE, "", "", 0},
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        customizeTable(table);
    
        section2.add(new JScrollPane(table), BorderLayout.CENTER);
    
        setContentPane(section2);
        revalidate();
        repaint();
    }
    

    private void customizeTable(JTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        customizeIsInstanceColumn(table.getColumnModel().getColumn(1));
        customizeAccessColumn(table.getColumnModel().getColumn(0));
        customizeMarksColumn(table.getColumnModel().getColumn(4));
    }

    private void customizeIsInstanceColumn(TableColumn column) {
        column.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof Boolean) {
                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setSelected((Boolean) value);
                    checkBox.setHorizontalAlignment(JLabel.CENTER);
                    return checkBox;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });

        column.setCellEditor(new DefaultCellEditor(new JCheckBox()));
    }

    private void customizeAccessColumn(TableColumn column) {
        JComboBox<String> accessComboBox = new JComboBox<>(new String[]{"private", "public", "protected"});
        column.setCellEditor(new DefaultCellEditor(accessComboBox));
    }

    private void customizeMarksColumn(TableColumn column) {
        JTextField marksTextField = new JTextField();
        marksTextField.setDocument(new IntegerDocument());
        column.setCellEditor(new DefaultCellEditor(marksTextField));
    }

    // Restricts input to integers only
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

    // sets up the frame
    private void initFrame() {
        setTitle("Assignment Specification Portal");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setResizable(false);

		contentPane = getContentPane();
		contentPane.setLayout(null);

        section1 = new JPanel();
        section2 = new JPanel();
        section1.setLayout(null);
    }

    public static void main(String[] args) throws Exception
	{
		AssignmentSpecPortal assignmentSpecPortal = new AssignmentSpecPortal();
        assignmentSpecPortal.setVisible(true);
	}
	
}
