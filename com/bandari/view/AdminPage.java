package com.bandari.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.bandari.beans.Employee;
import com.bandari.db.DBConnection;

public class AdminPage {
	JPanel adminPanel;
	

	JPanel feidlPanel;
	JPanel buttonPanel;
	
	JButton createNewEmp;
	JButton report;
	JButton deleteButton;
	ArrayList<JCheckBox> checkBoxs;

	public AdminPage(final JPanel mainPanel) {
		ArrayList<Employee> employees = DBConnection.getInstance().readAllEmployeeDetails();
		checkBoxs = new ArrayList<JCheckBox>();

		feidlPanel = new JPanel();
		buttonPanel = new JPanel();
		feidlPanel.setLayout(new GridLayout(0, 8));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		adminPanel = new JPanel();
		mainPanel.removeAll();
		adminPanel = new JPanel();
		adminPanel.setLayout(new GridLayout(0, 1));
		feidlPanel.add(new JLabel("Employee Id"));
		feidlPanel.add(new JLabel("Employee Name"));
		feidlPanel.add(new JLabel("Alloted Leave"));
		feidlPanel.add(new JLabel("Designation"));
		feidlPanel.add(new JLabel("Salery"));
		feidlPanel.add(new JLabel("Details"));
		feidlPanel.add(new JLabel("Update"));
		feidlPanel.add(new JLabel("Delete"));

		for (int i = 0; i < employees.size(); i++) {
			feidlPanel.add(new JLabel(((Employee) employees.get(i)).getEmpId()));
			feidlPanel.add(new JLabel(((Employee) employees.get(i)).getEmpName()));
			feidlPanel.add(new JLabel(((Employee) employees.get(i)).getAllotedleave()));
			feidlPanel.add(new JLabel(((Employee) employees.get(i)).getDesignation()));
			feidlPanel.add(new JLabel(((Employee) employees.get(i)).getEmpSalary()));

			ImageIcon imageIcon = new ImageIcon("detail.png");
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance(25, 25, 4);
			ImageIcon imageIcon1 = new ImageIcon(newimg);
			final JLabel detailLable = new JLabel(imageIcon1, 2);
			detailLable.setName(((Employee) employees.get(i)).getEmpId());
			feidlPanel.add(detailLable);

			detailLable.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseClicked(MouseEvent e) {
					System.out.println(detailLable.getName());
					new Details(mainPanel, detailLable.getName());
				}

			});
			ImageIcon editIcon = new ImageIcon("edit.png");
			Image editimage = editIcon.getImage();
			Image newEditImg = editimage.getScaledInstance(25, 25, 4);
			ImageIcon editIconNew = new ImageIcon(newEditImg);
			JLabel editLable = new JLabel(editIconNew, 2);
			feidlPanel.add(editLable);

			editLable.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseClicked(MouseEvent e) {
					System.out.println(detailLable.getName());
					new UpdatePage(mainPanel, detailLable.getName());
				}

			});
			JCheckBox box = new JCheckBox();
			box.setName(((Employee) employees.get(i)).getEmpId());
			checkBoxs.add(box);
			feidlPanel.add(box);
		}

		createNewEmp = new JButton("Create");
		buttonPanel.add(createNewEmp);
		deleteButton = new JButton("Delete");
		buttonPanel.add(deleteButton);

		report = new JButton("Report");
		buttonPanel.add(report);
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminReport(mainPanel, true);
			}

		});
		adminPanel.add(feidlPanel);
		adminPanel.add(buttonPanel);
		mainPanel.add(adminPanel);
		mainPanel.updateUI();

		createNewEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Registration(mainPanel);
			}

		});
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean atleastOneSelected = false;

					StringBuffer buffer = new StringBuffer();
					StringBuffer deleteAttadence = new StringBuffer("delete from timetable where empid in ( ");
					buffer.append("delete from employee where id in (");
					for (int i = 0; i < checkBoxs.size(); i++) {
						if (checkBoxs.get(i).isSelected()) {
							atleastOneSelected = true;
							buffer.append(checkBoxs.get(i).getName() + ",");
							deleteAttadence.append(checkBoxs.get(i).getName() + ",");
						}
					}

					buffer.append(");");
					buffer.deleteCharAt(buffer.length() - 3);

					deleteAttadence.append(");");
					deleteAttadence.deleteCharAt(deleteAttadence.length() - 3);

					System.out.println(buffer);
					if (atleastOneSelected) {
						DBConnection.getInstance().deleteEmployees(buffer.toString());
						DBConnection.getInstance().query(deleteAttadence.toString());
						new AdminPage(mainPanel);
					}
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});
	}
}