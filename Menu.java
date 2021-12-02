package com.srs.assignment05;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu {

    ArrayList<ArrayList<String>> fileData;

    public void createMenu() {
        JFrame jFrame = new JFrame("Attendance Management");

        JMenuBar menuBar = new JMenuBar();

        //adding File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem loadRoster = new JMenuItem("Load Roster");
        JMenuItem addAttendance = new JMenuItem("Add Attendance");
        JMenuItem saveData = new JMenuItem("Save Data");
        JMenuItem plotData = new JMenuItem("Plot Data");

        fileMenu.add(loadRoster);
        fileMenu.add(addAttendance);
        fileMenu.add(saveData);
        fileMenu.add(plotData);

        // adding About to the menu bar
        JMenuItem about = new JMenuItem("About");

        menuBar.add(fileMenu);
        menuBar.add(about);
        jFrame.setJMenuBar(menuBar);
        jFrame.setSize(500, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        // listener for loading a roaster file
        loadRoster.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                LoadRoaster loadRoaster = new LoadRoaster();
                fileData= loadRoaster.getRoasterFileData();
                if(null != fileData){
                    JScrollPane jScrollPane = new JScrollPane(Utils.showTable(fileData,Constants.LOAD_ROASTER_HEADER), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    jFrame.add(jScrollPane);
                    jFrame.setVisible(true);
                }
            }
        });
    }

    }
