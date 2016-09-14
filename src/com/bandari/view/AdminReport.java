package com.bandari.view;

import com.bandari.beans.Employee;
import com.bandari.db.DBConnection;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminReport {
	JPanel adminReportPanel;
	JPanel feidlPanel;
	JPanel buttonPanel;

	public AdminReport(final JPanel mainPanel, final boolean isAdmin) {
		ArrayList<Employee> employees = DBConnection.getInstance().getAllEmployeeReport();
		
		feidlPanel = new JPanel();
		buttonPanel = new JPanel();
		feidlPanel.setLayout(new GridLayout(0, 8));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton clear = new JButton("Setteled");
		JButton back = new JButton("Back");
		mainPanel.removeAll();
		
		adminReportPanel = new JPanel();
		adminReportPanel.add(feidlPanel);
		adminReportPanel.add(buttonPanel);
		adminReportPanel.setLayout(new GridLayout(0, 1));
		feidlPanel.add(new JLabel("Id"));
		feidlPanel.add(new JLabel("Name"));
		feidlPanel.add(new JLabel("Present"));
		feidlPanel.add(new JLabel("T Leaves"));
		feidlPanel.add(new JLabel("R Leaves"));
		feidlPanel.add(new JLabel("TW Hours"));
		feidlPanel.add(new JLabel("Full Salary"));
		feidlPanel.add(new JLabel("Salary"));
		
		for (int i = 0; i < employees.size(); i++) {
			feidlPanel.add(new JLabel(employees.get(0).getEmpId()));
			feidlPanel.add(new JLabel(employees.get(0).getEmpName()));
			feidlPanel.add(new JLabel(employees.get(0).getPresent()));
			feidlPanel.add(new JLabel(employees.get(0).getAllotedleave()));
			feidlPanel.add(new JLabel(String.valueOf(employees.get(0).getRemainingLeave())));
			feidlPanel.add(new JLabel(String.valueOf(employees.get(0).getTotalWorkedHours())));
			if (isAdmin) {
				feidlPanel.add(new JLabel(String.valueOf(employees.get(0).getMonthSalary())));
				feidlPanel.add(new JLabel(((Employee) employees.get(0)).getEmpSalary()));
			} else {
				feidlPanel.add(new JLabel("xxxxx"));
				feidlPanel.add(new JLabel("xxxxx"));
			}
		}
		buttonPanel.add(back);
		buttonPanel.add(clear);

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isAdmin) {
					new AdminPage(mainPanel);
				} else {
					new Attendence(mainPanel);
				}
			}
		});
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DBConnection.getInstance().query("truncate table timetable;");
					new AdminReport(mainPanel, true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		mainPanel.add(adminReportPanel);
		mainPanel.updateUI();
	}
}