package com.bandari.view;

import com.bandari.beans.Employee;
import com.bandari.db.DBConnection;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Details
{
  JPanel detailPanel;
  
  public Details(final JPanel mainPanel, String empId)
  {
    Employee employee = DBConnection.getInstance().readEmployee(empId);
    JButton okButton = new JButton("Ok");
    
    mainPanel.removeAll();
    detailPanel = new JPanel();
    detailPanel.setLayout(new GridLayout(0, 2));
    detailPanel.add(new JLabel("Id"));
    detailPanel.add(new JLabel(employee.getEmpId()));
    detailPanel.add(new JLabel("Name"));
    detailPanel.add(new JLabel(employee.getEmpName()));
    detailPanel.add(new JLabel("Email"));
    detailPanel.add(new JLabel(employee.getEmpEmail()));
    detailPanel.add(new JLabel("Phone"));
    detailPanel.add(new JLabel(employee.getEmpPhone()));
    detailPanel.add(new JLabel("Salary"));
    detailPanel.add(new JLabel(employee.getEmpSalary()));
    detailPanel.add(new JLabel("Designation"));
    detailPanel.add(new JLabel(employee.getDesignation()));
    detailPanel.add(new JLabel("Alloted Leave"));
    detailPanel.add(new JLabel(employee.getAllotedleave()));
    detailPanel.add(new JLabel("DOB"));
    detailPanel.add(new JLabel(employee.getDob()));
    detailPanel.add(new JLabel("DOJ"));
    detailPanel.add(new JLabel(employee.getDoj()));
    detailPanel.add(new JLabel("Address"));
    detailPanel.add(new JLabel(employee.getEmpAddress()));
    detailPanel.add(new JLabel("Sex"));
    detailPanel.add(new JLabel(employee.getSex()));
    
    detailPanel.add(okButton);
    mainPanel.add(detailPanel);
    mainPanel.updateUI();
    
    okButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        new AdminPage(mainPanel);
      }
    });
  }
}