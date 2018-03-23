package com.redhat.api.DAOLayer;

import java.util.List;

import org.hibernate.SessionFactory;
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

		sessionFactory.getCurrentSession().saveOrUpdate(entity);

	}

	@Override
	@Transactional
	public List<EntityClass> listUser() {
		@SuppressWarnings("unchecked")
		List<EntityClass> list = sessionFactory.getCurrentSession().createQuery("from EntityClass").getResultList();
		return list;
	}

	@Override
	@Transactional
	public void deleteUser(Integer id) {

		EntityClass entity = sessionFactory.getCurrentSession().load(EntityClass.class, id);

		if (null != entity) {

			this.sessionFactory.getCurrentSession().delete(entity);
		}
	}

}
