package com.srs.assignment05;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * This is a utility , having common functions.
 */
public class Utils {

    public Utils() {
    }

    public static String getSelectedFilePath(String filterTypeText, String dialogTitle, String filterType) {
        String selectedPath = "";
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(filterTypeText, filterType);
        jFileChooser.setFileFilter(filter);
        jFileChooser.setVisible(true);
        jFileChooser.setDialogTitle(dialogTitle);
        int returnVal = jFileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedPath = jFileChooser.getSelectedFile().getPath();
        }
        return selectedPath;
    }

    public static JTable showTable(ArrayList<ArrayList<String>> fileData, ArrayList<String> headerArray) {
        DefaultTableModel tableModel = new DefaultTableModel();
        //add column to the table
        for (String header : headerArray) {
            tableModel.addColumn(header);
        }
        //add dynamic data to table
        for (int i=1;i< fileData.size();i++) {
            tableModel.addRow(fileData.get(i).toArray());
        }
        JTable jTable = new JTable(tableModel);
        jTable.setBounds(30, 40, 200, 300);
        //To get the horizontal scrollbar in the JTable set auto resize off
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        return jTable;
    }

}
