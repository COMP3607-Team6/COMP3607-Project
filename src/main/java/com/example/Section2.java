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
    // private JPanel section2;

    public Section2(AssignmentSpecPortal parent) {
        // section2 = new JPanel();
        this.parent = parent;

        setLayout(new BorderLayout());
    
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

}
