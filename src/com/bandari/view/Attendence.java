package com.bandari.view;

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

	public Attendence(final JPanel mainPanel) {
		ArrayList<Employee> employees = DBConnection.getInstance().readAllEmployeeDetails();
		save = new JButton("Save");
		report = new JButton("Report");
		exit = new JButton("Exit");
		JTextField otHours = new JTextField("0");

		mainPanel.removeAll();
		attendencePanel = new JPanel();
		attendencePanel.setLayout(new java.awt.GridLayout(0, 5));
		attendencePanel.add(new JLabel("Employee Id"));
		attendencePanel.add(new JLabel("Employee Name"));
		attendencePanel.add(new JLabel("Full Day"));
		attendencePanel.add(new JLabel("Half Day"));
		attendencePanel.add(new JLabel("Over Time"));

		for (int i = 0; i < employees.size(); i++) {
			attendencePanel.add(new JLabel(((Employee) employees.get(i)).getEmpId()));
			attendencePanel.add(new JLabel(((Employee) employees.get(i)).getEmpName()));
			ButtonGroup shiftGroup = new ButtonGroup();
			JRadioButton halfDay = new JRadioButton("Half");
			JRadioButton fullDay = new JRadioButton("Full");
			fullDay.setSelected(true);
			shiftGroup.add(halfDay);
			shiftGroup.add(fullDay);
			attendencePanel.add(halfDay);
			attendencePanel.add(fullDay);
			attendencePanel.add(otHours);

			shiftTimeArray.add(fullDay);
			empIdArray.add(((Employee) employees.get(i)).getEmpId());
			otHoursArray.add(otHours);
		}

		attendencePanel.add(save);
		attendencePanel.add(report);
		attendencePanel.add(exit);
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