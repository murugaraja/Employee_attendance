package com.bandari.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bandari.db.DBConnection;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Registration {
	JPanel registrationPanel;
	
	JPanel feidlPanel;
	JPanel buttonPanel;
	
	JTextField empName;
	JTextField empId;
	JTextField empAllotedLeave;
	JTextField empPhone;
	JTextField empEmail;
	JTextField empSalary;
	JTextField empAddress;
	JComboBox<String> sexCombo;
	JComboBox<String> designationCombo;
	JDatePickerImpl dojdatePicker;
	JDatePickerImpl dobDatePicker;
	JButton clear;
	JButton save;
	JButton back;

	public Registration(final JPanel mainPanel) {
		mainPanel.removeAll();
		empName = new JTextField();
		empId = new JTextField();
		empAllotedLeave = new JTextField();
		empPhone = new JTextField();
		empEmail = new JTextField();
		empSalary = new JTextField();
		empAddress = new JTextField();
		clear = new JButton("Clear");
		save = new JButton("Save");
		back = new JButton("Back");
		
		feidlPanel = new JPanel();
		buttonPanel = new JPanel();
		feidlPanel.setLayout(new GridLayout(0, 2));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		registrationPanel = new JPanel();
		registrationPanel.add(feidlPanel);
		registrationPanel.add(buttonPanel);
		registrationPanel.setLayout(new GridLayout(0, 1));
		feidlPanel.add(new JLabel("Employee Name :"));
		feidlPanel.add(empName);
		feidlPanel.add(new JLabel("Employee Id :"));
		feidlPanel.add(empId);
		feidlPanel.add(new JLabel("Sex :"));
		Vector<String> sex = new Vector<String>();
		sex.add("Male");
		sex.add("Female");
		sexCombo = new JComboBox<String>(sex);
		feidlPanel.add(sexCombo);

		feidlPanel.add(new JLabel("Designation :"));
		Vector<String> designation = new Vector<String>();
		designation.add("Electrician");
		designation.add("Seniour Electrician");
		designation.add("Labour");
		designationCombo = new JComboBox<String>(designation);
		feidlPanel.add(designationCombo);

		feidlPanel.add(new JLabel("DOB :"));
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		dobDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		feidlPanel.add(dobDatePicker);

		feidlPanel.add(new JLabel("DOJ :"));
		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
		dojdatePicker = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		feidlPanel.add(dojdatePicker);
		feidlPanel.add(new JLabel("Phone Number :"));
		feidlPanel.add(empPhone);
		feidlPanel.add(new JLabel("Email Id :"));
		feidlPanel.add(empEmail);
		feidlPanel.add(new JLabel("Salary :"));
		feidlPanel.add(empSalary);
		feidlPanel.add(new JLabel("Alloted Leave :"));
		feidlPanel.add(empAllotedLeave);
		feidlPanel.add(new JLabel("Address :"));
		feidlPanel.add(empAddress);

		buttonPanel.add(save);
		buttonPanel.add(clear);
		buttonPanel.add(back);

		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empName.setText("");
				empId.setText("");
				empPhone.setText("");
				empEmail.setText("");
				empSalary.setText("");
				empAllotedLeave.setText("");
				empAddress.setText("");
				dojdatePicker.getJFormattedTextField().setText("");
				dobDatePicker.getJFormattedTextField().setText("");
				sexCombo.setSelectedIndex(0);
				designationCombo.setSelectedIndex(0);
			}

		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StringBuffer expression = new StringBuffer("insert into employee (Name,Id,AllotedLeave,Phone,Email,Salary,Address,doj,dob,sex,designation) values ( ");
					expression.append("'" + empName.getText() + "',");
					expression.append("'" + empId.getText() + "',");
					expression.append("'" + empAllotedLeave.getText() + "',");
					expression.append("'" + empPhone.getText() + "',");
					expression.append("'" + empEmail.getText() + "',");
					expression.append("'" + empSalary.getText() + "',");
					expression.append("'" + empAddress.getText() + "',");
					expression.append("'" + dojdatePicker.getJFormattedTextField().getText() + "',");
					expression.append("'" + dobDatePicker.getJFormattedTextField().getText() + "',");
					expression.append("'" + (String) sexCombo.getItemAt(sexCombo.getSelectedIndex()) + "',");
					expression.append("'" + (String) designationCombo.getItemAt(designationCombo.getSelectedIndex()) + "'");
					expression.append(");");

					DBConnection connection = DBConnection.getInstance();
					connection.query(expression.toString());

					empName.setText("");
					empId.setText("");
					empPhone.setText("");
					empEmail.setText("");
					empSalary.setText("");
					empAddress.setText("");
					empAllotedLeave.setText("");
					dojdatePicker.getJFormattedTextField().setText("");
					dobDatePicker.getJFormattedTextField().setText("");
					sexCombo.setSelectedIndex(0);
					designationCombo.setSelectedIndex(0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminPage(mainPanel);
			}

		});
		mainPanel.add(registrationPanel);
		mainPanel.updateUI();
	}
}