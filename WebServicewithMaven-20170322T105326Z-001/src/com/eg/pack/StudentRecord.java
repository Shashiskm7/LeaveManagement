package com.eg.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRecord {

	public static Connection getConnection()
	{
		Connection conn = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@10.0.0.5:1521:xe","Omfys","omfys");
			}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return conn;
		}
	
	//INSERTING DATA INTO LEAVETABLE
	public static int insert(LeaveApplication la)
	{
		int status = 0;
		try{  
            Connection conn=StudentRecord.getConnection();  
            PreparedStatement ps=conn.prepareStatement(  
         "INSERT INTO LEAVETABLE(S_ID,S_NAME,TYPE_OF_LEAVE,FROM_DATE,TO_DATE,STATUS) VALUES (?,?,?,?,?,?)");  
            ps.setString(1, la.getS_id());  
            ps.setString(2,la.getS_name());  
            ps.setString(3,la.getLeave_type());  
            ps.setString(4,la.getFrom_date());  
            ps.setString(5,la.getTo_date());
            ps.setString(6,la.getStatus());
            
            status=ps.executeUpdate();  
              
            conn.close();  
        }catch(Exception ex){ex.printStackTrace();} 
		return status;
	}
	
	//FETCHING PARTICULAR EMPLOYEE RECORD FROM LEAVETABLE
	public static List<LeaveApplication> getStudentRecordById(String S_id)
	{
		List<LeaveApplication> list = new ArrayList<LeaveApplication>();
		try{
			Connection conn = StudentRecord.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from(select * from LEAVETABLE order by ID DESC) where S_ID=?");
			ps.setString(1,S_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				LeaveApplication la = new LeaveApplication();
				la.setS_id(rs.getString(2));
				la.setS_name(rs.getString(3));
				la.setLeave_type(rs.getString(4));
				la.setFrom_date(rs.getString(5));
				la.setTo_date(rs.getString(6));
				la.setStatus(rs.getString(7));
				list.add(la);
			}
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	//FETCHING ALL RECORD FROM LEAVETABLE
	public static List<LeaveApplication> getStudentRecord()
	{
		List<LeaveApplication> list = new ArrayList<LeaveApplication>();
		try{
			Connection conn = StudentRecord.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM LEAVETABLE ORDER BY ID DESC");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				LeaveApplication s = new LeaveApplication();
				s.setId(rs.getString(1));
				s.setS_id(rs.getString(2));
				s.setS_name(rs.getString(3));
				s.setLeave_type(rs.getString(4));
				s.setFrom_date(rs.getString(5));
				s.setTo_date(rs.getString(6));
				s.setStatus(rs.getString(7));
			list.add(s);
		
			}
			rs.close();
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
		
	}
	//UPDATING LEAVE TABLE
	public static int updateLeaveApplication(LeaveApplication la)
	{
		int status =0;
		try{  
            Connection conn=StudentRecord.getConnection();  
            PreparedStatement ps=conn.prepareStatement("update LEAVETABLE set STATUS='"+la.getStatus()+"' where ID='"+la.getId()+"'");  
            System.out.println(la.getStatus());
            status=ps.executeUpdate();
            conn.close();
            
        }catch(Exception ex){ex.printStackTrace();} 
		return status;
			
	}
	
	
	//FETCHING ALL EMPLOYEE RECORD
	public static int fetchEmpRec(EmployeeRegInfo emp) throws SQLException
	{
		int status = 0;
		try{
		Connection conn = StudentRecord.getConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO EMPLOYEEAPK(E_ID,E_NAME,E_MAIL,E_PHONE,E_MACID,E_REGT) VALUES(?,?,?,?,?,?)");
		ps.setInt(1,emp.getEid());
		ps.setString(2,emp.getEname());
		ps.setString(3,emp.getEmail());
		ps.setLong(4,emp.getPno());
		ps.setString(5,emp.getMacID());
		ps.setString(6,emp.getDeviceID());
		status = ps.executeUpdate();
		}catch(Exception e){e.printStackTrace();}
		return status;
	}
	
	//FETCHING EMPLOYEE RECORD
	public static EmployeeRegInfo getEmployeeRecord(int eid)
	{EmployeeRegInfo emp = new EmployeeRegInfo();
	try{
		Connection conn = StudentRecord.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT E_ID,E_MAIL,E_NAME FROM EMPLOYEEAPK WHERE E_ID=?");
		ps.setInt(1,eid);
		ResultSet rs = ps.executeQuery();
//		emp = new EmployeeRegInfo();
		while(rs.next())
		{
		emp.setEid(rs.getInt(1));
		emp.setEmail(rs.getString(2));
		emp.setEname(rs.getString(3));
		}
	
	}catch(SQLException e)
	{
		e.printStackTrace();
	}
	return emp;
	}
	
	//DELTEING EMPLOYEE FROM EMPLOYEE TABLE
	public static void EmployeeLogout(int eid,String email,String ename)
	{
		try{
			Connection conn = StudentRecord.getConnection();
			PreparedStatement ps = conn.prepareStatement("DELETE FROM EMPLOYEEAPK WHERE E_ID=? AND E_MAIL=? AND E_NAME=?");
			ps.setInt(1,eid);
			ps.setString(2,email);
			ps.setString(3,ename);
			ps.executeUpdate();
			conn.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//CHECKING IF USER EXIST OR NOT 
	public static int checkRecord(int eid,String email,String ename)
	{ int count=1;
		try{
			Connection conn = StudentRecord.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(E_ID) FROM EMPLOYEEAPK WHERE E_ID=? AND E_MAIL=? AND E_NAME=?");
			ps.setInt(1,eid);
			ps.setString(2,email);
			ps.setString(3,ename);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				count = rs.getInt(1);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	//RETERIVING MACID FROM EMPLOYEE TABLE
	public static String getMacId(int id)
	{String macid = null;
		try{
			System.out.println(id);
			Connection conn = StudentRecord.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT E_MACID FROM EMPLOYEEAPK WHERE E_ID="+id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				macid = rs.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return macid;
	}
	
	//INSERT DATA INTO ADMIN TABLE
	public static int insertAdmin(String adminD,String aMacID,String aDeviceID)
	{
	int status = 0;
	
try{
	Connection conn = StudentRecord.getConnection();
	PreparedStatement ps = conn.prepareStatement("INSERT INTO ADMINTABLE(ADMIN_D,A_MACID,A_DEVICEID) VALUES (?,?,?)");
	ps.setString(1,adminD);
	ps.setString(2, aMacID);
	ps.setString(3, aDeviceID);
	status = ps.executeUpdate();
	conn.close();
	}
catch(Exception e)
    {
	e.printStackTrace();
    }
	return status;
	}
	
	
	public static List<AdminInfo> getAdminCount()
	{
		List<AdminInfo> list = new ArrayList<AdminInfo>();
		try{
			Connection conn = StudentRecord.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT ADEVICEID FROM ADMINTABLE");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				AdminInfo info = new AdminInfo();
				info.setDeviceid(rs.getString(1));
				list.add(info);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public static int getLeaveCount()
	{
		int count = 0;
		try{
			Connection conn = StudentRecord.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM LEAVETABLE");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				 count = rs.getInt(1);
			}
			rs.close();
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return count;
	}
	
	public static String getRegID(int id)
	{
		String ID = null;
		try{
			Connection conn = StudentRecord.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT E_REGT from EMPLOYEEAPK where E_ID="+id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
			ID = rs.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ID;
	}
	
	
	
}
