package com.shbd.model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.type.descriptor.sql.SmallIntTypeDescriptor;
import org.junit.Test;

import com.shbd.util.HibernateUtil;

public class UserTest {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void test() {
		// 创建Session Factory
		Configuration  cfg = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(cfg.getProperties()).buildServiceRegistry();
		
		SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
		Session session = null;
		try {
			//创建session
			session = factory.openSession();
			// 开启事务
			session.beginTransaction();
			User user = new User();
			user.setUsername("zhang shan");
			user.setPassword("123");
			user.setNickname("张三");
			user.setBorn(new Date());
			session.save(user);
			//提交事务
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if(session != null) session.getTransaction().rollback();
		}finally {
			if(session != null) session.close();
		}		
	}
	
	@Test
	public void testAdd() {		
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User user = new User();
			user.setUsername("lisi");
			user.setPassword("123");
			user.setNickname("李四");
			user.setBorn(sdf.parse("1978-04-22"));
			session.save(user);			
			session.getTransaction().commit();
		}catch (HibernateException | ParseException e) {
			e.printStackTrace();
			if(session != null) session.getTransaction().rollback();
		}finally {
			HibernateUtil.closeSession(session);
		}		
	}
	
	@Test
	public void testLoad() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			User user = (User)session.load(User.class, 2);
			System.out.println(user);
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession(session);
		}		
	}
	
	
	@Test
	public void testUpdate() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User user = (User)session.load(User.class, 1);
			user.setNickname("李小小四");
			session.update(user);		
			session.getTransaction().commit();
		}catch (HibernateException e) {
			e.printStackTrace();
			if(session != null) session.getTransaction().rollback();
		}finally {
			HibernateUtil.closeSession(session);
		}	
		
	}
	
	@Test
	public void testDelete() {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			User user = new User();
			user.setId(2);
			session.delete(user);		
			session.getTransaction().commit();
		}catch (HibernateException e) {
			e.printStackTrace();
			if(session != null) session.getTransaction().rollback();
		}finally {
			HibernateUtil.closeSession(session);
		}		
	}
	
	@Test
	public void list(){		
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			List<User> users = session.createQuery("from User").list();
			for(User user : users) {
				System.out.println(user);
			}
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	@Test
	public void page(){		
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			List<User> users = session.createQuery("from User")
					.setFirstResult(0)
					.setMaxResults(2).list();
			for(User user : users) {
				System.out.println(user);
			}
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	

}
