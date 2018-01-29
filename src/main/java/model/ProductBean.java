package model;

import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;

import model.hibernate.HibernateUtil;

@Entity
@Table(name="PRODUCT")
public class ProductBean {
	@Id
	private int id;
	private String name;
	private double price;
	private java.util.Date make;
	private int expire;
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
//insert
			ProductBean insert = new ProductBean();
			insert.setId(1);
			insert.setName("hahaha");
			insert.setPrice(111);
			insert.setMake(new java.util.Date());
			insert.setExpire(222);
			session.save(insert);

//select
//			ProductBean select = session.get(ProductBean.class, 100);
//			System.out.println("select="+select);
			
//update
//			select.setName("hohoho");
//			select.setPrice(123);
//			select.setMake(new java.util.Date(0));
//			select.setExpire(456);
			
//delete			
//			ProductBean delete = new ProductBean();
//			delete.setId(100);
//			session.delete(delete);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	@Override
	public boolean equals(Object obj) {
		if(obj!=null && (obj instanceof ProductBean)) {
			ProductBean temp = (ProductBean) obj;
			if(this.id == temp.id) {
				return true;
			}
		}
		return false;
	}

	@Transient
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public String toString() {
		return "{"+id+":"+name+":"+price+":"+make+":"+expire+"}";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public java.util.Date getMake() {
		return make;
	}
	public void setMake(java.util.Date make) {
		this.make = make;
	}
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
}
