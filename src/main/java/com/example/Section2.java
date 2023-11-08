package com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

class Section2 extends JPanel {
    private AssignmentSpecPortal parent;
    private int classCount = 1;
    private JPanel classPanel;
    private JComboBox<String> accessComboBox;
    private JComboBox<String> isAbstractComboBox;
    private JComboBox<String> isInterfaceComboBox;
    private JComboBox<String> EIComboBox;
    private JTextField classNameField;
    private JTextField NameField;
    private JTextField markField;
    private JCheckBox extendsCheckBox;
    private JButton addClassButton;
    private DefaultListModel<String> classListModel;
    private JList<String> classList;
    private JLabel prompt;
    private JLabel markslabel;
    
   
    // private JPanel section2;

    public Section2(AssignmentSpecPortal parent) {
        // section2 = new JPanel();
        setLayout(new BorderLayout());
       // setLayout(null);
        System.out.println("hola");
        System.out.println("hola2");
        System.out.println("hola3");

        this.parent = parent; 
        accessComboBox = new JComboBox<>(new String[]{"Public", "Private", "Protected"});
        isAbstractComboBox = new JComboBox<>(new String[]{"Abstract","Concrete"});
        isInterfaceComboBox = new JComboBox<>(new String[]{"Class","Interface"});
        EIComboBox = new JComboBox<>(new String[]{"Extends","Implements"});
        EIComboBox.setVisible(false);
        classNameField = new JTextField(10);
        prompt = new JLabel("Enter Assignment Classes Here:                                                                  ");
        prompt.setFont(new Font("Arial", Font.ITALIC, 22));
        markslabel = new JLabel("Marks: \n");
                
        markField = new JTextField(6);
        markField.setText("0");
        NameField = new JTextField(10);
        NameField.setVisible(false);
        extendsCheckBox = new JCheckBox("Extends/Implements");
        addClassButton = new JButton("+ Class");
    
        classListModel = new DefaultListModel<>();
        classList = new JList<>(classListModel);
        JButton removeButton = new JButton("Remove Selected Class");
        
        JPanel classPanel = new JPanel();
        classPanel.setLayout(new GridLayout(2,1));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
      
        inputPanel.add(prompt);
        inputPanel.add(accessComboBox);
        inputPanel.add(isAbstractComboBox);
        inputPanel.add(isInterfaceComboBox);
        classNameField.setText("");
        inputPanel.add(classNameField);
        inputPanel.add(extendsCheckBox);
        inputPanel.add(EIComboBox);
        inputPanel.add(NameField);
        inputPanel.add(markslabel);
        inputPanel.add(markField);
        inputPanel.add(addClassButton);
        inputPanel.add(removeButton);


        classPanel.add(inputPanel);
        classPanel.add(new JScrollPane(classList));
        //add(classPanel);

        isInterfaceComboBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if("Interface".equals(isInterfaceComboBox.getSelectedItem())) {
                    isAbstractComboBox.setVisible(false);
                    EIComboBox.removeAllItems();
                    EIComboBox.addItem("Extends");
            
		            isInterfaceComboBox.setLocation(240, 90);
                }
                else{
                    isAbstractComboBox.setVisible(true);   
                    EIComboBox.addItem("Implements");        
	            	isInterfaceComboBox.setLocation(350, 90);
                } 
            }
        }); 

         extendsCheckBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(true==extendsCheckBox.isSelected()) {
                    EIComboBox.setVisible(true);
                    NameField.setVisible(true);
                    extendsCheckBox.setText("");
		            
                }
                else{
                    EIComboBox.setVisible(false);   
                    NameField.setVisible(false);         
                    extendsCheckBox.setText("Extends/Implements"); 
	        
                } 
            }
        }); 

      
        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                classCount++;
                addClassToList();
                
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int index = classList.getSelectedIndex();
                if(index != -1){
                    classListModel.remove(index);
                }
            }
        });
       

     
      

       

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

        add(new JScrollPane(table), BorderLayout.CENTER);  
        setLayout(null);
        table.setSize(900,90);
		table.setLocation(0, 300);
        add(table);
        
        classPanel.setSize(900, 200);
		classPanel.setLocation(0, 10);
        add(classPanel);
      
       
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

    private void addClassToList(){
        String accessType = (String) accessComboBox.getSelectedItem();
        String isAbstract = (String) isAbstractComboBox.getSelectedItem();
        String EIbox = (String) EIComboBox.getSelectedItem();
        String isInterface = (String) isInterfaceComboBox.getSelectedItem();
        String className = (String) classNameField.getText();
        String extraName = (String) NameField.getText();
        String marks = (String) markField.getText();
        String classInfo = "";
        boolean isSelected = extendsCheckBox.isSelected();
        

        if(className.equals("")){
            return;
        }
        else if((extraName.equals(""))&&(isSelected == true)){
            return;
        }
        else if((isInterface=="Interface")&&( isSelected == true)){
           classInfo = accessType+" "+isInterface+" "+ className + " Extends "+ extraName + " [ " + marks + " marks]" ;
           classListModel.addElement(classInfo);
           
        }
        else if(isInterface=="Interface"){
           classInfo = accessType+" "+isInterface+" "+ className + " [ " + marks + " marks]" ;
           classListModel.addElement(classInfo);
        
        }
       else if(isSelected == true){
           classInfo = accessType+" "+isAbstract+" "+ className +" "+ EIbox+" " + extraName+ " [ " + marks + " marks]";
           classListModel.addElement(classInfo);
          
        }
        else {
            classInfo = accessType+" "+isAbstract+" "+isInterface+" "+ className+ " [ " + marks + " marks]";
            classListModel.addElement(classInfo);
            
        }
        classNameField.setText("");
        NameField.setText("");
        markField.setText("0");
    }  
   
    

     public static void main( String[] args )
    {
        System.out.println( "Hello World!!!!!! :)))))" );
        // ZipFileInput z = new ZipFileInput("Enter zip file path here");
        // z.readFiles();

        AssignmentSpecPortal assignmentSpecPortal = new AssignmentSpecPortal();
        assignmentSpecPortal.setVisible(true);
    } 

}
