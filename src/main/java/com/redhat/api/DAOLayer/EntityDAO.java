package com.redhat.api.DAOLayer;

import java.util.List;

import com.redhat.api.entity.EntityClass;

public interface EntityDAO {

	public void addUser(EntityClass entity);
	
	public List<EntityClass> listUser();
	
	public void deleteUser(Integer id);
	
	
}
