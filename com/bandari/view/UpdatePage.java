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
import com.bandari.beans.Employee;
import com.bandari.db.DBConnection;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
 
 public class UpdatePage
 {
   JPanel detailPanel;
   JTextField empName;
   JLabel empID;
   JTextField empDesignation;
   JTextField empPhone;
   JTextField empEmail;
   JTextField empSalary;
   JTextField empAddress;
   JComboBox<String> sexCombo;
   JComboBox<String> designationCombo;
   JDatePickerImpl dojdatePicker;
   JDatePickerImpl dobDatePicker;
   
   public UpdatePage(final JPanel mainPanel, final String empId)
   {
     Employee employee = DBConnection.getInstance().readEmployee(empId);
     JButton update = new JButton("Update");
     JButton back = new JButton("Back");
     
     empName = new JTextField(employee.getEmpName());
     empID = new JLabel(employee.getEmpId());
     empPhone = new JTextField(employee.getEmpPhone());
     empEmail = new JTextField(employee.getEmpEmail());
     empSalary = new JTextField(employee.getEmpSalary());
     empAddress = new JTextField(employee.getEmpAddress());
     
     mainPanel.removeAll();
     detailPanel = new JPanel();
     detailPanel.setLayout(new GridLayout(0, 2));
     detailPanel.add(new JLabel("Id"));
     detailPanel.add(empID);
     detailPanel.add(new JLabel("Name"));
     detailPanel.add(empName);
     detailPanel.add(new JLabel("Email"));
     detailPanel.add(empEmail);
     detailPanel.add(new JLabel("Phone"));
     detailPanel.add(empPhone);
     detailPanel.add(new JLabel("Salary"));
     detailPanel.add(empSalary);
     detailPanel.add(new JLabel("Designation"));
     Vector<String> designation = new Vector<String>();
     designation.add("Electrician");
     designation.add("Seniour Electrician");
     designation.add("Labour");
     designationCombo = new JComboBox<String>(designation);
     detailPanel.add(designationCombo);
     
     detailPanel.add(new JLabel("DOB"));
     UtilDateModel model = new UtilDateModel();
     
     JDatePanelImpl datePanel = new JDatePanelImpl(model);
     final JDatePickerImpl dobDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
     detailPanel.add(dobDatePicker);
     
     detailPanel.add(new JLabel("DOJ"));
     UtilDateModel model1 = new UtilDateModel();
     JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
     final JDatePickerImpl dojdatePicker = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
     detailPanel.add(dojdatePicker);
     
     detailPanel.add(new JLabel("Address"));
     detailPanel.add(empAddress);
     detailPanel.add(new JLabel("Sex"));
     Vector<String> sex = new Vector<String>();
     sex.add("Male");
     sex.add("Female");
     sexCombo = new JComboBox<String>(sex);
     detailPanel.add(sexCombo);
     
     detailPanel.add(back);
     detailPanel.add(update);
     mainPanel.add(detailPanel);
     mainPanel.updateUI();
     
     back.addActionListener(new ActionListener()
     {
       public void actionPerformed(ActionEvent e) {
         new AdminPage(mainPanel);
       }
       
     });
     update.addActionListener(new ActionListener()
     {
       public void actionPerformed(ActionEvent e) {
         try {
           StringBuffer expression = new StringBuffer("update employee set ");
           expression.append("name = '" + empName.getText() + "',");
           expression.append("phone='" + empPhone.getText() + "',");
           expression.append("email='" + empEmail.getText() + "',");
           expression.append("salary='" + empSalary.getText() + "',");
           expression.append("address='" + empAddress.getText() + "',");
           expression.append("doj='" + dojdatePicker.getJFormattedTextField().getText() + "',");
           expression.append("dob='" + dobDatePicker.getJFormattedTextField().getText() + "',");
           expression.append("sex='" + (String)sexCombo.getItemAt(sexCombo.getSelectedIndex()) + "',");
           expression.append("designation='" + (String)designationCombo.getItemAt(designationCombo.getSelectedIndex()) + "'");
           expression.append("where id = '" + empId + "'");
           expression.append(";");
           System.out.println(expression);
           
           DBConnection connection = DBConnection.getInstance();
           connection.query(expression.toString());
         } catch (Exception e1) {
           e1.printStackTrace();
         }
       }
     });
   }
 }