package com.bandari.view;

import com.bandari.db.DBConnection;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	JPanel loginPanel;
	JPanel mainPanel;

	public Login(final JPanel mainPanel) {
		this.mainPanel = mainPanel;
		JButton loginButton = new JButton("Login");
		final JTextField userNameField = new JTextField();
		final JPasswordField passwordField = new JPasswordField();

		loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(0, 2));
		loginPanel.add(new JLabel("UserName :"));
		loginPanel.add(userNameField);
		loginPanel.add(new JLabel("Password :"));
		loginPanel.add(passwordField);
		loginPanel.add(loginButton);

		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				@SuppressWarnings("deprecation")
				int auth = DBConnection.getInstance().isAuthenticate(userNameField.getText(), passwordField.getText());
				if (auth == -1) {
					userNameField.setText("");
					passwordField.setText("");
				} else if (auth == 1) {
					new AdminPage(mainPanel);
				} else {
					new Attendence(mainPanel);
				}
			}
		});
	}

	public void getLoginPanel() {
		mainPanel.add(loginPanel);
	}
}