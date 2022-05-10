package com.srs.assignment05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu {

    ArrayList<ArrayList<String>> fileData;
    JFrame dateFrame, jFrame;
    JComboBox day, month;
    JButton setButton;

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
        loadRoster.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadRoaster loadRoaster = new LoadRoaster();
                fileData = loadRoaster.getRoasterFileData();
                if (null != fileData) {
                    JScrollPane jScrollPane = new JScrollPane(Utils.showTable(fileData, fileData.get(0)), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    jFrame.add(jScrollPane);
                    jFrame.setVisible(true);
                }
            }
        });

        // listener for loading a attendance file
        addAttendance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DatePicker(jFrame);
            }
        });
        // listener for saving to a  file
        saveData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SaveDataToFile saveDataToFile = new SaveDataToFile();
                saveDataToFile.saveDataToCSVFile(fileData);
            }
        });
        // listener for plotting data
        plotData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlotData scatterPlot = new PlotData(fileData);
                scatterPlot.pack();
                scatterPlot.setVisible(true);

            }
        });
        // listener for viewing Team info
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jFrame,
                        " Team Members: " + "\n" + "Asha Latha Nagathota (Backend Developer)" + "\n" + " Sree Valindha Maddineni (Product Manager)" + "\n" + " Rahul Muralidhara Koushik (Requirement Analyst)" + "\n" + " Pavan dai Varma Budde (Frontend Developer)",
                        "Team Information",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public void DatePicker(JFrame jFrame) {
        dateFrame = new JFrame("Select a date");

        day = new JComboBox(Constants.days);
        month = new JComboBox(Constants.months);

        setButton = new JButton("Set Selected Date");
        day.setSelectedIndex(0);
        day.setEditable(false);
        month.setSelectedIndex(0);
        month.setEditable(false);

        dateFrame.add(day, BorderLayout.WEST);
        dateFrame.add(month, BorderLayout.EAST);
        dateFrame.add(setButton, BorderLayout.SOUTH);
        dateFrame.setSize(255, 100);
        dateFrame.setLocationRelativeTo(null);
        dateFrame.setVisible(true);

        setButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddAttendance attendance = new AddAttendance();
                dateFrame.dispose();
                String selectedDate = month.getSelectedItem() + " " + day.getSelectedItem();
                System.out.println(selectedDate);
                fileData.get(0).add(selectedDate);
                attendance.appendDateToData(fileData, selectedDate);
                if (null != fileData) {
                    Container gContentPane = jFrame.getContentPane();
                    JScrollPane jScrollPane = new JScrollPane(Utils.showTable(fileData, fileData.get(0)));
                    jFrame.setContentPane(jScrollPane);
                    jFrame.revalidate();
                    jFrame.repaint();
                    jFrame.setVisible(true);
                }
            }
        });
    }

}
