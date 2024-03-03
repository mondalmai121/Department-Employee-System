package org.jsp.DE.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.DE.dto.Employee;
import org.jsp.DE.dto.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {

	@Autowired
	private EntityManager manager;
	
	public Employee addEmployee(Employee e, int department_id) {
		Department d=manager.find(Department.class, department_id);
		if(d!=null) {
			d.getEmployee().add(e);
			e.setDepartment(d);
			manager.persist(e);
			EntityTransaction t=manager.getTransaction();
			t.begin();
			t.commit();
			return e;
		}else {
			return null;
		}
	}
	
	public Employee updateEmployee(Employee e) {
		Employee emp=manager.find(Employee.class, e.getId());
		if(emp!=null) {
			emp.setName(e.getName());
			emp.setPhone(e.getPhone());
			emp.setEmail(e.getEmail());
			emp.setPassword(e.getPassword());
			emp.setAddress(e.getAddress());
			EntityTransaction t=manager.getTransaction();
			t.begin();
			t.commit();
			return emp;
		}else {
			return null;
		}
	}
	
	public List<Employee> findByDepartmentId(int department_id) {
		String jpql="select d.employee from Department d where d.id=:id";
		Query q=manager.createQuery(jpql);
		q.setParameter("id",department_id);
		return q.getResultList();
	}
}
