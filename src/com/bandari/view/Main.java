package com.bandari.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	public static JFrame mainFrame;
	JPanel mainPanel;
	JPanel contentPanel;

	public Main() {
		mainFrame = new JFrame("Login");
		mainPanel = new JPanel();
		contentPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(getLogoPanel(), BorderLayout.NORTH);
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		new Login(contentPanel).getLoginPanel();
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		mainFrame.setSize(new Dimension(800, 600));

		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		mainFrame.setLocation((int) width * 20 / 100, (int) height * 10 / 100);
		mainFrame.setDefaultCloseOperation(3);
	}

	JPanel getLogoPanel() {
		JPanel logopanel = new JPanel();
		JButton logout = new JButton("Logout");
		JLabel company = new JLabel("Bandari Electricals");
		company.setFont(new Font(company.getFont().getName(), 0, 32));
		logopanel.add(company);
		logopanel.add(logout);

		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.removeAll();
				new Login(contentPanel).getLoginPanel();
				contentPanel.updateUI();
			}
		});
		return logopanel;
	}

	public static void main(String[] args) {
		new Main();
	}
}