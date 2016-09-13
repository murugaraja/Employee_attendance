package com.bandari.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import com.bandari.beans.Employee;

public class DBConnection {
	private static Connection conn;
	private static DBConnection dBConnection;
	
	private DBConnection() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			conn = java.sql.DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "sa", "");
			try {
				Statement statement = conn.createStatement();
				statement.execute("select * from authentication;");
				statement.close();
			} catch (Exception exc) {
				Statement statement = conn.createStatement();
				statement.executeQuery("create table if not exists authentication (empname varchar(20), password varchar(20), isadmin boolean);");
				statement.close();

				statement = conn.createStatement();
				statement.executeQuery("create table if not exists employee (name varchar(20),id integer,phone varchar(15),email varchar(20),salary varchar(10),address varchar(20),doj date, dob date,sex varchar(10),designation varchar(20),allotedleave integer);");
				statement.close();

				statement = conn.createStatement();
				statement.executeQuery("create table if not exists timetable (empid integer, attendance integer,hours integer );");
				statement.close();

				statement = conn.createStatement();
				statement.execute("insert into authentication ( empname,password,isadmin) values ('admin','admin123',true);");
				statement.close();

				statement = conn.createStatement();
				statement.execute("insert into authentication ( empname,password,isadmin) values ('user','user123',false);");
				statement.close();
			}

			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DBConnection getInstance() {
		if (dBConnection == null) {
			dBConnection = new DBConnection();
		}
		return dBConnection;
	}

	public void shutdown() throws SQLException {
		conn.close();
	}

	public void query(String expression) throws SQLException {
		Statement st = null;
		st = conn.createStatement();
		st.execute(expression);
		st.close();
	}

	public void update(String expression) throws SQLException {
		Statement st = null;
		st = conn.createStatement();
		int i = st.executeUpdate(expression);
		if (i == -1) {
			System.out.println("db error : " + expression);
		}
		st.close();
	}

	public void dump(ResultSet rs) throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		int colmax = meta.getColumnCount();

		Object o = null;
		while (rs.next()) {
			for (int i = 0; i < colmax; i++) {
				o = rs.getObject(i + 1);
				System.out.print(o.toString() + " ");
			}
			System.out.println(" ");
		}
	}

	public void main(String[] args) {
		DBConnection db = null;
		try {
			db = new DBConnection();
		} catch (Exception ex1) {
			ex1.printStackTrace();
			return;
		}
		try {
			db.update("CREATE TABLE sample_table ( id INTEGER IDENTITY, str_col VARCHAR(256), num_col INTEGER)");
		} catch (Exception localException1) {
		}
		try {
			db.update("INSERT INTO sample_table(str_col,num_col) VALUES('Ford', 100)");
			db.update("INSERT INTO sample_table(str_col,num_col) VALUES('Toyota', 200)");
			db.update("INSERT INTO sample_table(str_col,num_col) VALUES('Honda', 300)");
			db.update("INSERT INTO sample_table(str_col,num_col) VALUES('GM', 400)");
			db.query("SELECT * FROM sample_table WHERE num_col < 250");
			db.query("select * from persons");
			db.shutdown();
		} catch (SQLException ex3) {
			ex3.printStackTrace();
		}
	}

	public ArrayList<Employee> readAllEmployeeDetails() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			Statement st = null;
			ResultSet rs = null;
			st = conn.createStatement();
			rs = st.executeQuery("select * from employee");
			ResultSetMetaData meta = rs.getMetaData();
			int colmax = meta.getColumnCount();
			
			Object o = null;
			while (rs.next()) {
				Employee employee = new Employee();
				for (int i = 0; i < colmax; i++) {
					o = rs.getObject(i + 1);

					System.out.print(meta.getColumnName(i + 1) + " : " + o + " ");

					switch (meta.getColumnName(i + 1)) {
					case "ID":
						employee.setEmpId(o.toString());
						break;
					case "NAME":
						employee.setEmpName(o.toString());
						break;
					case "PHONE":
						employee.setEmpPhone(o.toString());
						break;
					case "EMAIL":
						employee.setEmpEmail(o.toString());
						break;
					case "SALARY":
						employee.setEmpSalary(o.toString());
						break;
					case "ADDRESS":
						employee.setEmpAddress(o.toString());
						break;
					case "DOJ":
						employee.setDoj(o.toString());
						break;
					case "DOB":
						employee.setDob(o.toString());
						break;
					case "SEX":
						employee.setSex(o.toString());
						break;
					case "DESIGNATION":
						employee.setDesignation(o.toString());
						break;
					case "ALLOTEDLEAVE":
						employee.setAllotedleave(o.toString());
						break;

					default:
						break;
					}
					System.out.println(" ");
				}
				employees.add(employee);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	public int isAuthenticate(String username, String password) {
		int authentication = -1;
		try {
			Statement st = null;
			ResultSet rs = null;
			st = conn.createStatement();
			rs = st.executeQuery("select isadmin from authentication where empname= '" + username + "' and password ='"
					+ password + "';");
			rs.next();
			boolean auth = Boolean.parseBoolean(rs.getObject("ISADMIN").toString());
			if (auth) {
				authentication = 1;
			} else {
				authentication = 0;
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authentication;
	}

	public Employee readEmployee(String empId) {
		Employee employee = new Employee();
		try {
			Statement st = null;
			ResultSet rs = null;
			st = conn.createStatement();
			rs = st.executeQuery("select * from employee where id = " + empId + ";");
			ResultSetMetaData meta = rs.getMetaData();
			int colmax = meta.getColumnCount();

			Object o = null;
			while (rs.next()) {
				for (int i = 0; i < colmax; i++) {
					o = rs.getObject(i + 1);

					System.out.print(meta.getColumnName(i + 1) + " : " + o + " ");
					switch (meta.getColumnName(i + 1)) {
					case "ID":
						employee.setEmpId(o.toString());
						break;
					case "NAME":
						employee.setEmpName(o.toString());
						break;
					case "PHONE":
						employee.setEmpPhone(o.toString());
						break;
					case "EMAIL":
						employee.setEmpEmail(o.toString());
						break;
					case "SALARY":
						employee.setEmpSalary(o.toString());
						break;
					case "ADDRESS":
						employee.setEmpAddress(o.toString());
						break;
					case "DOJ":
						employee.setDoj(o.toString());
						break;
					case "DOB":
						employee.setDob(o.toString());
						break;
					case "SEX":
						employee.setSex(o.toString());
						break;
					case "DESIGNATION":
						employee.setDesignation(o.toString());
						break;
					case "ALLOTEDLEAVE":
						employee.setAllotedleave(o.toString());
						break;

					default:
						break;
					}
				}
				System.out.println(" ");
			}
			st.close();
		} catch (Exception localException) {
		}

		return employee;
	}

	public void deleteEmployees(String string) {
		try {
			Statement st = null;
			st = conn.createStatement();
			st.execute(string);
			st.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void insertAttendance(String empid, int todayDate, int totalHours) {
		try {
			Statement st = null;
			String empidString = null;
			try {
				String selectQury = new String(
						"select empid from timetable where empid='" + empid + "' and attendance='" + todayDate + "';");
				st = conn.createStatement();
				ResultSet resultSet = st.executeQuery(selectQury);
				resultSet.next();
				empidString = resultSet.getString("EMPID");
				System.out.println("Data already inserted");
				st.close();
			} catch (Exception localException1) {
			}

			if (empidString == null) {
				String insertQuery = new String("insert into timetable (empid,attendance,hours) values  (" + empid + ","
						+ todayDate + "," + totalHours + ");");
				st = conn.createStatement();
				st.execute(insertQuery);
				st.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Employee> getAllEmployeeReport() {
		ArrayList<Employee> employees = readAllEmployeeDetails();

		for (int i = 0; i < employees.size(); i++) {
			getPresentDetail((Employee) employees.get(i));
		}
		return employees;
	}

	private void getPresentDetail(Employee emp) {
		Statement st = null;
		try {
			String selectQury = new String("select * from timetable where empid='" + emp.getEmpId() + "';");
			st = conn.createStatement();
			ResultSet resultSet = st.executeQuery(selectQury);
			int totalHourse = 0;
			ArrayList<String> presentDates = new ArrayList<String>();

			while (resultSet.next()) {
				totalHourse += Integer.parseInt(resultSet.getString("hours"));
				presentDates.add(resultSet.getString("Attendance"));
			}

			emp.setPresent(presentDates.toString());
			emp.setRemainingLeave(calculateRemainingLeave(emp.getAllotedleave(), presentDates));
			emp.setTotalWorkedHours(totalHourse);
			emp.setMonthSalary(calculateSalary(emp.getEmpSalary(), emp.getTotalWorkedHours()));
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int calculateSalary(String salary, int totalHours) {
		Calendar calendar = Calendar.getInstance();
		int numDays = calendar.getActualMaximum(5);
		int salaryAmt = Integer.parseInt(salary);
		int perHoursalary = salaryAmt / ((numDays - 5) * 8);
		int totalSalary = perHoursalary * totalHours;
		return totalSalary;
	}

	private int calculateRemainingLeave(String allotedLeave, ArrayList<String> presentDates) {
		@SuppressWarnings("deprecation")
		int todayDate = new java.util.Date().getDate();
		int totalLeave = todayDate - presentDates.size();
		int allotedLeaveInt = Integer.parseInt(allotedLeave);
		return allotedLeaveInt - totalLeave;
	}
}