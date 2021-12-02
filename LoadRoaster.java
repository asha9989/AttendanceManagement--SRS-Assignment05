package com.srs.assignment05;


import java.io.*;
import java.util.ArrayList;

/**
 * The class is used to read the roaster file and manipulate the data
 */
public class LoadRoaster {

    private String selectedPath;

    public LoadRoaster() {
        selectedPath = Utils.getSelectedFilePath("Choose a CSV file", "Please Select the path of the Roaster file:", Constants.CSV);
        System.out.println("The File chosen in LoadRoaster:" + selectedPath);
    }

    public ArrayList<ArrayList<String>> getRoasterFileData() {
        try {
            if ("" != selectedPath) {
                return getChosenFileData(selectedPath);
            }
        } catch (IOException e) {
            System.out.println("IOException in the class LoadRoaster of method getRoasterFileData" + e.getMessage());
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getChosenFileData(String filePath) throws IOException {
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        ArrayList<ArrayList<String>> fileData = new ArrayList<ArrayList<String>>();
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        String[] lineArr;
        while ((line = bufferedReader.readLine()) != null) {
            lineArr = line.split(Constants.COMMA_DELIMITER);
            //append each line read from the file
            appendDataToList(lineArr, fileData);
        }
        bufferedReader.close();
        return fileData;
    }

    public void appendDataToList(String[] dataArr, ArrayList<ArrayList<String>> fileData) {
        ArrayList<String> rowData = new ArrayList<String>();
        for (String item : dataArr) {
            rowData.add(item);
        }
        fileData.add(rowData);
    }
}
