package com.bandari.view;

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

		registrationPanel = new JPanel();
		registrationPanel.setLayout(new GridLayout(0, 2));
		registrationPanel.add(new JLabel("Employee Name :"));
		registrationPanel.add(empName);
		registrationPanel.add(new JLabel("Employee Id :"));
		registrationPanel.add(empId);
		registrationPanel.add(new JLabel("Sex :"));
		Vector<String> sex = new Vector<String>();
		sex.add("Male");
		sex.add("Female");
		sexCombo = new JComboBox<String>(sex);
		registrationPanel.add(sexCombo);

		registrationPanel.add(new JLabel("Designation :"));
		Vector<String> designation = new Vector<String>();
		designation.add("Electrician");
		designation.add("Seniour Electrician");
		designation.add("Labour");
		designationCombo = new JComboBox<String>(designation);
		registrationPanel.add(designationCombo);

		registrationPanel.add(new JLabel("DOB :"));
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		dobDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		registrationPanel.add(dobDatePicker);

		registrationPanel.add(new JLabel("DOJ :"));
		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
		dojdatePicker = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		registrationPanel.add(dojdatePicker);
		registrationPanel.add(new JLabel("Phone Number :"));
		registrationPanel.add(empPhone);
		registrationPanel.add(new JLabel("Email Id :"));
		registrationPanel.add(empEmail);
		registrationPanel.add(new JLabel("Salary :"));
		registrationPanel.add(empSalary);
		registrationPanel.add(new JLabel("Alloted Leave :"));
		registrationPanel.add(empAllotedLeave);
		registrationPanel.add(new JLabel("Address :"));
		registrationPanel.add(empAddress);

		registrationPanel.add(save);
		registrationPanel.add(clear);
		registrationPanel.add(back);

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