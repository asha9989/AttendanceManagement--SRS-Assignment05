package com.srs.assignment05;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AddAttendance extends JFrame {

    String selectedPath;

    public AddAttendance() {
        selectedPath = Utils.getSelectedFilePath("Choose a CSV file", "Please Select the path of the Attendance file:", Constants.CSV);
        System.out.println("The File chosen for Attendance:" + selectedPath);
    }

    public void appendDateToData(ArrayList<ArrayList<String>> fileData, String selectedDate) {
        HashMap<String, Integer> totalAttendance = new HashMap<String, Integer>();
        try {
            if ("" != selectedPath) {
                File file = new File(selectedPath);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                String[] lineArr;
                while ((line = bufferedReader.readLine()) != null) {
                    lineArr = line.split(Constants.COMMA_DELIMITER);
                    //append each line read from the file
                    addAttendance(totalAttendance, lineArr);
                }
                bufferedReader.close();
                updateFileData(fileData, totalAttendance);
            }
        } catch (IOException e) {
            System.out.println("IOException in the class LoadRoaster of method getRoasterFileData" + e.getMessage());
        }
    }

    public void updateFileData(ArrayList<ArrayList<String>> fileData, HashMap<String, Integer> totalAttendance) {

        for (ArrayList<String> strArr : fileData) {
            if (totalAttendance.containsKey(strArr.get(5))) {
                strArr.add(6, "" + totalAttendance.get(strArr.get(5)));
            }
        }

    }

    public void addAttendance(HashMap<String, Integer> totalAttendance, String[] lineArr) {
        int time = 0;
        if (lineArr.length >= 2) {
            if (totalAttendance.containsKey(lineArr[0])) {
                time = totalAttendance.get(lineArr[0]);
            }
            totalAttendance.put(lineArr[0], time + Integer.parseInt(lineArr[1]));
        }
    }
}
