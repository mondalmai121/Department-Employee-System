package org.jsp.DE.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.DE.dto.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao {

	@Autowired
	private EntityManager manager;
	
	public Department saveDepartment(Department d) {
		manager.persist(d);
		EntityTransaction t=manager.getTransaction();
		t.begin();
		t.commit();
		return d;
	}
	
	public Department updateDepartment(Department d) {
		Department dep=manager.find(Department.class, d.getId());
		if(dep!=null) {
			dep.setName(d.getName());
			dep.setLoc(d.getLoc());
			EntityTransaction t=manager.getTransaction();
			t.begin();
			t.commit();
			return dep;
		}else {
			return null;
		}
	}
	
	public Department findByEmployeeID(int id) {
		return manager.find(Department.class, id);
	}
}
