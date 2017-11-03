package com.eg.pack;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Employee")
public class EmployeeControllerClass {
	
	StudentRecord sr = new StudentRecord();
	LeaveApplication la = new LeaveApplication();
	EmployeeRegInfo info = new EmployeeRegInfo();
	
	@GET
	@Path("/AllApplication")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LeaveApplication> getAllApplication()
	{ 
		List<LeaveApplication> listOfApplication = StudentRecord.getStudentRecord();
		return listOfApplication;
	}
	
	@GET
	@Path("/AllIdApplication")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LeaveApplication> getApplicationById(@QueryParam("id") String id)
	{
		return  (List<LeaveApplication>)StudentRecord.getStudentRecordById(id);
	}
	
	@POST
	@Path("/updateApplication")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LeaveApplication> updateApplication(@QueryParam("id") String id,@QueryParam("status") String status) throws NumberFormatException, Exception
	{
		FireBasePushNotiUsers PushU = new FireBasePushNotiUsers();
		System.out.println("id "+id+" status "+status);
		la.setId(id);
		la.setStatus(status);
		StudentRecord.updateLeaveApplication(la);
		if(!status.equals("Unattended"))
		{
			PushU.pushFCMNotification(StudentRecord.getRegID(Integer.parseInt(id)));
		}
		
		return (List<LeaveApplication>)StudentRecord.getStudentRecord();
	}
	
	
	
	@DELETE
	@Path("/DeleteEmployee")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteEmployeeRecord(@QueryParam("eid") int eid,@QueryParam("email") String email,@QueryParam("ename") String ename,@QueryParam("delete") String delete)
	{    System.out.println(eid+" "+ename+" "+email);
		System.out.println("delete "+delete);
		StudentRecord.EmployeeLogout(eid, email, ename);
		System.out.println(StudentRecord.checkRecord(eid, email, ename));
		return String.valueOf(StudentRecord.checkRecord(eid, email, ename));
	}
	
	@PUT
	@Path("/InsertEmployee")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertEmployee(@QueryParam("eid") int eid,@QueryParam("email") String email,@QueryParam("ename") String ename,@QueryParam("pno") long pno,@QueryParam("macid") String macid,@QueryParam("token") String token) throws SQLException
	{
		System.out.println(eid+" "+ename+" "+email+" "+pno);
		info.setEid(eid);
		info.setEname(ename);
		info.setEmail(email);
		info.setPno(pno);
		info.setMacID(macid);
		info.setDeviceID(token);
		StudentRecord.fetchEmpRec(info);
		return String.valueOf(StudentRecord.checkRecord(info.getEid(), info.getEmail(), info.getEname()));
	}
	
	
	@GET
	@Path("/getEmployee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getEmployee(@QueryParam("eid") int eid,@QueryParam("ename") String ename,@QueryParam("email") String email)
	{
	boolean b;	
		System.out.println(eid+" "+ename+" "+email);
		EmployeeRegInfo info = StudentRecord.getEmployeeRecord(eid);
		System.out.println(info.getEid()+" "+info.getEmail());
		if(info.getEid()==eid && info.getEmail().equals(email))
		{
			System.out.println("Employee Exists........");
		b = true;
		}
		else{
			System.out.println("Employee doesn't exists......");
	    b = false;
		}
		
		String str = String.valueOf(b);
		return str;
	}
	
	@GET
	@Path("/getMac")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMac(@QueryParam("eid") int eid)
	{
		System.out.println(StudentRecord.getMacId(eid));
	 return StudentRecord.getMacId(eid);
	}
	
	@GET
	@Path("/getOtp")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOtp(@QueryParam("invoke") String invoke,@QueryParam("name") String name,@QueryParam("email") String email)
	{
		if(invoke.equals("invoke"))
		{
			md d = new md();
			String otp = d.createOtp();
			System.out.println(otp);
			new NotifyUser(email,name,otp);
			return otp;
		}
		else{
			return null;
		}
	}
	
	@PUT
	@Path("/LeaveSubmit")
	@Produces(MediaType.APPLICATION_JSON)
	public String submitLeave(@QueryParam("eid") String id,@QueryParam("ename") String ename,@QueryParam("TOL") String TOL,@QueryParam("fromDate") String fromDate,@QueryParam("toDate") String toDate,@QueryParam("status") String status ) throws Exception
	{
		FireBasePushNotiAdmin notiA = new FireBasePushNotiAdmin();
		System.out.println("leavesubmit");
		int PCount = StudentRecord.getLeaveCount();
		la.setS_id(id);
		la.setS_name(ename);
		la.setLeave_type(TOL);
		la.setFrom_date(fromDate);
		la.setTo_date(toDate);
		la.setStatus(status);
		String str = String.valueOf(StudentRecord.insert(la));
		
		int NCount = StudentRecord.getLeaveCount();
		if(PCount!=NCount)
		{
			List<AdminInfo> Ainfo = StudentRecord.getAdminCount();
			for(int i=0;i<Ainfo.size();i++)
			{
				notiA.pushFCMNotification(Ainfo.get(i).toString());
			}
		}
		
		return str;
	}
	
	@PUT
	@Path("/InsertAdmin")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertAdmin(@QueryParam("adminD") String adminD,@QueryParam("aMacID") String aMacID,@QueryParam("aDeviceID") String aDeviceID)
	{
		System.out.println(adminD+" "+aMacID+" "+aDeviceID);
		int status = StudentRecord.insertAdmin(adminD, aMacID, aDeviceID);
		return String.valueOf(status);
	}
	
	

}
