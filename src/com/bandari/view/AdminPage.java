package com.bandari.view;

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
	JButton createNewEmp;
	JButton report;
	JButton deleteButton;
	ArrayList<JCheckBox> checkBoxs;

	public AdminPage(final JPanel mainPanel) {
		ArrayList<Employee> employees = DBConnection.getInstance().readAllEmployeeDetails();
		checkBoxs = new ArrayList<JCheckBox>();

		mainPanel.removeAll();
		adminPanel = new JPanel();
		adminPanel.setLayout(new GridLayout(0, 8));
		adminPanel.add(new JLabel("Employee Id"));
		adminPanel.add(new JLabel("Employee Name"));
		adminPanel.add(new JLabel("Alloted Leave"));
		adminPanel.add(new JLabel("Designation"));
		adminPanel.add(new JLabel("Salery"));
		adminPanel.add(new JLabel("Details"));
		adminPanel.add(new JLabel("Update"));
		adminPanel.add(new JLabel("Delete"));

		for (int i = 0; i < employees.size(); i++) {
			adminPanel.add(new JLabel(((Employee) employees.get(i)).getEmpId()));
			adminPanel.add(new JLabel(((Employee) employees.get(i)).getEmpName()));
			adminPanel.add(new JLabel(((Employee) employees.get(i)).getAllotedleave()));
			adminPanel.add(new JLabel(((Employee) employees.get(i)).getDesignation()));
			adminPanel.add(new JLabel(((Employee) employees.get(i)).getEmpSalary()));

			ImageIcon imageIcon = new ImageIcon("detail.png");
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance(25, 25, 4);
			ImageIcon imageIcon1 = new ImageIcon(newimg);
			final JLabel detailLable = new JLabel(imageIcon1, 2);
			detailLable.setName(((Employee) employees.get(i)).getEmpId());
			adminPanel.add(detailLable);

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
			adminPanel.add(editLable);

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
			adminPanel.add(box);
		}

		createNewEmp = new JButton("Create");
		adminPanel.add(createNewEmp);
		deleteButton = new JButton("Delete");
		adminPanel.add(deleteButton);

		report = new JButton("Report");
		adminPanel.add(report);
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminReport(mainPanel, true);
			}

		});
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