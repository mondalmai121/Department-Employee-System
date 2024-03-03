package org.jsp.DE.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.DE.DeEmConfig;
import org.jsp.DE.dao.DepartmentDao;
import org.jsp.DE.dao.EmployeeDao;
import org.jsp.DE.dto.Department;
import org.jsp.DE.dto.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Department_Employee {

	static DepartmentDao ddao;
	static EmployeeDao edao;
	static {
		ApplicationContext con=new AnnotationConfigApplicationContext(DeEmConfig.class);
		ddao=con.getBean(DepartmentDao.class);
		edao=con.getBean(EmployeeDao.class);
	}
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		boolean f=true;
		
		while(f) {
			System.out.println("<====== 1.Save Department ======>");
			System.out.println("<====== 2.Update Department ======>");
			System.out.println("<====== 3.Find Department by id ======>");
			System.out.println("<====== 4.Add Employee ======>");
			System.out.println("<====== 5.Update Employee ======>");
			System.out.println("<====== 6.Find Employee by Department id ======>");
			
			switch(sc.nextInt()) {
			
			case 1: {
				System.out.println("Enter name, loc to save: ");
				Department d=new Department();
				d.setName(sc.next());
				d.setLoc(sc.next());
				d=ddao.saveDepartment(d);
				System.out.println("Department saved successfully......");
				break;
			}
			
			case 2: {
				System.out.println("Enter id to update: ");
				int id=sc.nextInt();
				System.out.println("Enter name, loc to save: ");
				Department d=new Department();
				d.setId(id);
				d.setName(sc.next());
				d.setLoc(sc.next());
				d=ddao.updateDepartment(d);
				
				if(d!=null) {
					System.out.println("Department updated successfully......");
				}else {
					System.err.println("Cannot update bcz you have entered wrong id.......!!!");
				}
				break;
			}
				
			case 3: {
				System.out.println("Enter id to find: ");
				int id=sc.nextInt();
				Department d=ddao.findByEmployeeID(id);
				
				if(d!=null) {
					System.out.println("Department found successfully......");
					System.out.println("Department id: "+d.getId());
					System.out.println("Department name: "+d.getName());
					System.out.println("Department loc: "+d.getLoc());
					System.out.println("===================");
				}else {
					System.err.println("You have entered wrong id.......!!!");
				}
				break;
			}
				
			case 4: {
				System.out.println("Enter department_id to add employee: ");
				int department_id=sc.nextInt();
				System.out.println("Enter name, phone, email, password, address to add: ");
				Employee e=new Employee();
				e.setName(sc.next());
				e.setPhone(sc.nextLong());
				e.setEmail(sc.next());
				e.setPassword(sc.next());
				e.setAddress(sc.next());
				e=edao.addEmployee(e, department_id);
				
				if(e!=null) {
					System.out.println("Employee add successfully.......");
				}else {
					System.err.println("Cannot Add Employee bcz you have entered invalid dept id......!!!");
				}
				break;
			}
				
			case 5: {
				System.out.println("Enter id to update: ");
				int id=sc.nextInt();
				System.out.println("Enter name, phone, email, password, address to add: ");
				Employee e=new Employee();
				e.setId(id);
				e.setName(sc.next());
				e.setPhone(sc.nextLong());
				e.setEmail(sc.next());
				e.setPassword(sc.next());
				e.setAddress(sc.next());
				e=edao.updateEmployee(e);
				
				if(e!=null) {
					System.out.println("Employee updated successfully.......");
				}else {
					System.err.println("Cannot update Employee bcz you have entered invalid id......!!!");
				}
				break;
			}
				
			case 6: {
				System.out.println("Enter department id to find employee: ");
				int department_id=sc.nextInt();
				List<Employee> emp=edao.findByDepartmentId(department_id);
				
				if(emp.size()>0) {
					for(Employee e:emp) {
						System.out.println("Employee found successfully........");
						System.out.println("Employee id: "+e.getId());
						System.out.println("Employee name: "+e.getName());
						System.out.println("Employee phone: "+e.getPhone());
						System.out.println("Employee email: "+e.getEmail());
						System.out.println("Employee Address: "+e.getAddress());
						System.out.println("==========================");
					}
				}else {
					System.err.println("You have entered wrong id........!!!");
				}
				break;
			}
				
			default: System.out.println("< ***** INVALID CHOICE ***** >");
			System.exit(0);
			}
		}
	}
}
