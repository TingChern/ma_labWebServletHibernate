package model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.CustomerBean;
import model.CustomerDAO;
import model.hibernate.HibernateUtil;

public class CustomerDAOHibernate implements CustomerDAO {
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			
			CustomerDAO customerDao = new CustomerDAOHibernate(HibernateUtil.getSessionFactory());
			CustomerBean bean = customerDao.select("Alex");
			System.out.println("select="+bean);
			
			boolean update = customerDao.update(
					"ABC".getBytes(), "ellen@yahoo.com", new java.util.Date(0), "Ellen");
			System.out.println("update="+update);
		
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	private SessionFactory sessionFactory;
	public CustomerDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public CustomerBean select(String custid) {
		return this.getSession().get(CustomerBean.class, custid);
	}

	@Override
	public boolean update(byte[] password, String email, java.util.Date birth, String custid) {
		CustomerBean result = this.getSession().get(CustomerBean.class, custid);
		if(result!=null) {
			result.setPassword(password);
			result.setEmail(email);
			result.setBirth(birth);
			return true;
		}
		return false;
	}
}
