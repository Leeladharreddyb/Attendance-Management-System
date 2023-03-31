package Test;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDateTime;

public class Marking_Attendance extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Marking_Attendance(){
		setTitle("Attendance Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        String[] columnNames = {"Name", "Registration No.", "Section", "Roll No.", "Attendance"};
        Object[][] data = getStudentData();
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        String[] attendanceOptions = {"present", "absent"};
        JComboBox<String> attendanceComboBox = new JComboBox<>(attendanceOptions);
        table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(attendanceComboBox));
        JButton markAttendanceButton = new JButton("Mark attendance");
        markAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from JTable
                Object[][] tableData = new Object[table.getRowCount()][2];
                for (int i = 0; i < table.getRowCount(); i++) {
                    int registrationNo = (int) table.getValueAt(i, 1);
                    String attendance = (String) table.getValueAt(i, 4);
                    Object[] row = {registrationNo, attendance};
                    tableData[i] = row;
                }
                saveAttendance(tableData);
            }
        });
        add(markAttendanceButton, BorderLayout.SOUTH);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	boolean saveAttendance(Object[][] a) {
		try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
	    	PreparedStatement pstmt = con.prepareStatement("Insert into student_attendance_details values(?, ?, ?, ?, ?);");
	    	for (Object[] row : a) {
	    		 int reg = (int) row[0];
	    		 String d = (String) row[1];
                pstmt.setInt(1, reg);
                if(d.equals("present")) {
                	pstmt.setInt(2, 1);
                }
                else {
                	pstmt.setInt(2, 0);
                }
                pstmt.setInt(3, 1);
                pstmt.setObject(4, LocalDateTime.now());
                pstmt.setString(5, d);
                pstmt.executeUpdate();
            }
	    	 con.close();
	    	 return true;
	    } 
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		return false;
	}
	Object[][] getStudentData() {
	    try {
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams","root","@LEELadhar2719");
	        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM students;");
	        int rowCount = 0;
	        while (rs.next()) {
	            rowCount++;
	        }
	        rs = con.createStatement().executeQuery("SELECT * FROM students;");
	        Object[][] data = new Object[rowCount][5];
	        int i = 0;
	        while (rs.next()) {
	            String name = rs.getString(1);
	            int id = rs.getInt(2);
	            String section = rs.getString(3);
	            int roll = rs.getInt(4);
	            data[i][0] = name;
	            data[i][1] = id;
	            data[i][2] = section;
	            data[i][3] = roll;
	            data[i][4] = "present";
	            i++;
	        }
	        con.close();
	        return data;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return new Object[0][0];
	    }
	}

}
