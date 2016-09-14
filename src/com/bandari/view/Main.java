package com.bandari.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	public static JFrame mainFrame;
	JPanel mainPanel;
	JPanel contentPanel;

	public Main() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				System.out.println("CHOSEN THIS");
				break;
			}
		}

		mainFrame = new JFrame("Bandari Electricals...");
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
		mainFrame.setResizable(false);
	}

	JPanel getLogoPanel() {
		JPanel logopanel = new JPanel();
		//JLabel company = new JLabel("Bandari Electricals");
		//company.setFont(new Font(company.getFont().getName(), 0, 32));
		
		ImageIcon editIcon1 = new ImageIcon("title.png");
		Image editimage1 = editIcon1.getImage();
		Image newEditImg1 = editimage1.getScaledInstance(300, 70, 4);
		ImageIcon editIconNew1 = new ImageIcon(newEditImg1);
		JLabel editLable1 = new JLabel(editIconNew1, 2);
		logopanel.add(editLable1);
		//logopanel.add(company);

		ImageIcon editIcon = new ImageIcon("logout.png");
		Image editimage = editIcon.getImage();
		Image newEditImg = editimage.getScaledInstance(70, 30, 4);
		ImageIcon editIconNew = new ImageIcon(newEditImg);
		JLabel editLable = new JLabel(editIconNew, 2);
		logopanel.add(editLable);

		editLable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				contentPanel.removeAll();
				new Login(contentPanel).getLoginPanel();
				contentPanel.updateUI();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		return logopanel;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,IllegalAccessException, UnsupportedLookAndFeelException {
		new Main();
	}
}