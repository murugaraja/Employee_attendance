package com.bandari.view;

import com.bandari.beans.Employee;
import com.bandari.db.DBConnection;
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

	public AdminReport(final JPanel mainPanel, final boolean isAdmin) {
		ArrayList<Employee> employees = DBConnection.getInstance().getAllEmployeeReport();
		JButton clear = new JButton("Setteled");
		JButton back = new JButton("Back");
		mainPanel.removeAll();
		adminReportPanel = new JPanel();
		adminReportPanel.setLayout(new GridLayout(0, 8));

		adminReportPanel.add(new JLabel("Id"));
		adminReportPanel.add(new JLabel("Name"));
		adminReportPanel.add(new JLabel("Present"));
		adminReportPanel.add(new JLabel("Leave"));
		adminReportPanel.add(new JLabel("Remaining"));
		adminReportPanel.add(new JLabel("Hours"));
		adminReportPanel.add(new JLabel("Salary"));
		adminReportPanel.add(new JLabel("Salary"));

		for (int i = 0; i < employees.size(); i++) {
			adminReportPanel.add(new JLabel(employees.get(0).getEmpId()));
			adminReportPanel.add(new JLabel(employees.get(0).getEmpName()));
			adminReportPanel.add(new JLabel(employees.get(0).getPresent()));
			adminReportPanel.add(new JLabel(employees.get(0).getAllotedleave()));
			adminReportPanel.add(new JLabel(String.valueOf(employees.get(0).getRemainingLeave())));
			adminReportPanel.add(new JLabel(String.valueOf(employees.get(0).getTotalWorkedHours())));

			if (isAdmin) {
				adminReportPanel.add(new JLabel(String.valueOf(employees.get(0).getMonthSalary())));
				adminReportPanel.add(new JLabel(((Employee) employees.get(0)).getEmpSalary()));
			} else {
				adminReportPanel.add(new JLabel("xxxxx"));
				adminReportPanel.add(new JLabel("xxxxx"));
			}
		}

		adminReportPanel.add(back);
		adminReportPanel.add(clear);

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