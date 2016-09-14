package com.bandari.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.bandari.beans.Employee;
import com.bandari.db.DBConnection;

public class Attendence {
	JPanel attendencePanel;
	JButton save;
	JButton report;
	JButton exit;
	ArrayList<JRadioButton> shiftTimeArray = new ArrayList<JRadioButton>();
	ArrayList<String> empIdArray = new ArrayList<String>();
	ArrayList<JTextField> otHoursArray = new ArrayList<JTextField>();

	JPanel feidlPanel;
	JPanel buttonPanel;

	public Attendence(final JPanel mainPanel) {
		ArrayList<Employee> employees = DBConnection.getInstance().readAllEmployeeDetails();
		save = new JButton("Save");
		report = new JButton("Report");
		exit = new JButton("Exit");
		
		mainPanel.removeAll();
		attendencePanel = new JPanel();
		attendencePanel.setLayout(new GridLayout(0, 1));
		
		feidlPanel = new JPanel();
		buttonPanel = new JPanel();
		feidlPanel.setLayout(new GridLayout(0, 5));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		attendencePanel.add(feidlPanel);
		attendencePanel.add(buttonPanel);
		
		feidlPanel.add(new JLabel("Employee Id"));
		feidlPanel.add(new JLabel("Employee Name"));
		feidlPanel.add(new JLabel("Full Day"));
		feidlPanel.add(new JLabel("Half Day"));
		feidlPanel.add(new JLabel("Over Time"));

		for (int i = 0; i < employees.size(); i++) {
			feidlPanel.add(new JLabel(((Employee) employees.get(i)).getEmpId()));
			feidlPanel.add(new JLabel(((Employee) employees.get(i)).getEmpName()));
			ButtonGroup shiftGroup = new ButtonGroup();
			JRadioButton halfDay = new JRadioButton("Half");
			JRadioButton fullDay = new JRadioButton("Full");
			JTextField otHours = new JTextField("0");
			fullDay.setSelected(true);
			shiftGroup.add(halfDay);
			shiftGroup.add(fullDay);
			feidlPanel.add(halfDay);
			feidlPanel.add(fullDay);
			feidlPanel.add(otHours);

			shiftTimeArray.add(fullDay);
			empIdArray.add(((Employee) employees.get(i)).getEmpId());
			otHoursArray.add(otHours);
		}

		buttonPanel.add(save);
		buttonPanel.add(report);
		buttonPanel.add(exit);

		attendencePanel.add(feidlPanel);
		attendencePanel.add(buttonPanel);
		mainPanel.add(attendencePanel);
		mainPanel.updateUI();
		
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminReport(mainPanel, false);
			}

		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < empIdArray.size(); i++) {
					try {
						StringBuffer query = new StringBuffer();
						int totalHours = 0;
						JRadioButton fullDayRadioButton = shiftTimeArray.get(i);
						if (fullDayRadioButton.isSelected()) {
							totalHours = 8 + Integer.parseInt(otHoursArray.get(i).getText());
						} else {
							totalHours = 4 + Integer.parseInt(otHoursArray.get(i).getText());
						}

						@SuppressWarnings("deprecation")
						int todayDate = new Date().getDate();
						query.append("insert into timetable (empid,attendance,hours) values  (" + empIdArray.get(i)+ "," + todayDate + "," + totalHours + ");");
						System.out.println(query.toString());
						DBConnection.getInstance().insertAttendance(empIdArray.get(i), todayDate, totalHours);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DBConnection.getInstance().shutdown();
					System.exit(0);
					Main.mainFrame.dispose();
					Main.mainFrame.setVisible(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}