package com.redhat.api.DAOLayer;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.redhat.api.entity.EntityClass;

@Repository
@Transactional
public class EntityDAOImpl implements EntityDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void addUser(EntityClass entity) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.saveOrUpdate(entity);
		
		session.flush();
		session.clear();
		transaction.commit();

	}

	@Override
	@Transactional
	public List<EntityClass> listUser() {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<EntityClass> list = session.createQuery("from EntityClass").getResultList();
		
		session.flush();
		session.clear();
		transaction.commit();

		return list;

	}

	@Override
	@Transactional
	public void deleteUser(Integer id) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		EntityClass entity = session.load(EntityClass.class, id);

		if (null != entity) {

			session.delete(entity);
			
			session.flush();
			session.clear();
			transaction.commit();

		}
	}
	
}
