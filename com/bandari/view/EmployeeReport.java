package com.bandari.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EmployeeReport
{
  JPanel empReportPanel;
  
  public EmployeeReport()
  {
    empReportPanel = new JPanel();
    empReportPanel.setLayout(new java.awt.GridLayout(0, 2, 30, 10));
    empReportPanel.add(new JLabel("Employee Id :"));
    empReportPanel.add(new JTextField(10));
    empReportPanel.add(new JLabel("Employee Name :"));
    empReportPanel.add(new JTextField(10));
    empReportPanel.add(new JLabel("Total Working Days :"));
    empReportPanel.add(new JTextField(10));
    empReportPanel.add(new JLabel("Total attendence :"));
    empReportPanel.add(new JTextField(10));
    empReportPanel.add(new JLabel("Leves :"));
    empReportPanel.add(new JTextField(10));
    empReportPanel.add(new JLabel("Leaves Taken :"));
    empReportPanel.add(new JTextField(10));
    empReportPanel.add(new JLabel("Address :"));
    empReportPanel.add(new JTextField(100));
    empReportPanel.add(new javax.swing.JButton("Register"));
    empReportPanel.add(new javax.swing.JButton("Clear"));
  }
}