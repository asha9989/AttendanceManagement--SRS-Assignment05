package com.srs.assignment05;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveDataToFile {

    private String saveFilePath;

    public SaveDataToFile() {
        JFrame jframe = new JFrame();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Provide the path of the CSV file to save data");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Choose a CSV file", Constants.CSV);
        jFileChooser.setFileFilter(filter);
        int selectedSaveDataFilePath = jFileChooser.showSaveDialog(jframe);
        if (selectedSaveDataFilePath == JFileChooser.APPROVE_OPTION) {
            File fileToSave = jFileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            saveFilePath = fileToSave.getAbsolutePath();
        }
    }

    public void saveDataToCSVFile(ArrayList<ArrayList<String>> fileData) {
        PrintWriter writer = null;
        JFrame jFrame = new JFrame();
        try {
            if (null != saveFilePath && "" != saveFilePath.trim()) {
                writer = new PrintWriter(saveFilePath);
                StringBuilder sb = new StringBuilder();
                for (ArrayList<String> strArr : fileData) {
                    for (String str : strArr) {
                        sb.append(str.trim()).append(Constants.COMMA_DELIMITER);
                    }
                    sb.append("\n");
                }
                writer.write(sb.toString());
                JOptionPane.showMessageDialog(jFrame,
                        "Data saved successfully to the file in location :" + saveFilePath,
                        "Info",
                        JOptionPane.PLAIN_MESSAGE);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException in the method saveDataToCSVFile in SaveDataToFile class");
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }
}
