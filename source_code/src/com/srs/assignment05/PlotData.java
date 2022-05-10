package com.srs.assignment05;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlotData extends JFrame {
    private int maxStudents = 0;

    public PlotData(ArrayList<ArrayList<String>> fileData) {
        XYDataset dataset = createDataset(fileData);
        // draw scatter plot
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Attendance data per student chart",
                "% of Attendance", "Number of Students", dataset);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(255, 228, 196));

        //set the range of x-axis : Percentage of attendance : 0% - 100%
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(10, 100);
        domain.setTickUnit(new NumberTickUnit(10));
        domain.setVerticalTickLabels(true);
        //set the range of y-axis : No of students read from the data structure
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(0, maxStudents);
        range.setTickUnit(new NumberTickUnit(1));
        range.setVerticalTickLabels(true);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset(ArrayList<ArrayList<String>> fileData) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        HashMap<Integer, Integer> attendanceOfOneDay;
        for (int i = 6; i < fileData.get(0).size(); i++) {
            XYSeries dayAttendance = new XYSeries(fileData.get(0).get(i));
            attendanceOfOneDay = calculateAttendance(getAttendanceOnSelDate(fileData, i));
            for (Map.Entry<Integer, Integer> entry : attendanceOfOneDay.entrySet()) {
                dayAttendance.add(entry.getKey(), entry.getValue());
            }
            dataset.addSeries(dayAttendance);
        }

        return dataset;
    }

    private HashMap<Integer, Integer> calculateAttendance(ArrayList<Integer> dayAttendance) {
        HashMap<Integer, Integer> attendanceSheet = new HashMap<Integer, Integer>();
        for (int time : dayAttendance) {
            int count = 1;
            int scaledTime = scaleAttendance(time);
            if (attendanceSheet.containsKey(scaledTime)) {
                count += attendanceSheet.get(scaledTime);
            }
            attendanceSheet.put(scaledTime, count);
            maxStudents = count > maxStudents ? count : maxStudents;
        }
        return attendanceSheet;
    }

    /**
     * This methos is used to scale attendance as 75 or more minutes is equal to 100%
     * @param num
     * @return
     */
    private int scaleAttendance(int num) {
        if (num >= 75) {
            return 100;
        } else {
            return (num * 100) / 75;
        }
    }

    private ArrayList<Integer> getAttendanceOnSelDate(ArrayList<ArrayList<String>> fileData, int columnIndex) {
        ArrayList<Integer> attendanceSelDate = new ArrayList<Integer>();

        for (int i = 1; i < fileData.size(); i++) {
            if (null != fileData.get(i) && null != fileData.get(i).get(columnIndex)) {
                attendanceSelDate.add(Integer.parseInt(fileData.get(i).get(columnIndex)));
            }
        }
        return attendanceSelDate;
    }
}
