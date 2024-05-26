package org.sp.merchantproductapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.merchantproductapp.dto.Merchant;

public class MerchantDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("development");
	EntityManager manager = factory.createEntityManager();

	public Merchant saveMerchant(Merchant merchant) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(merchant);
		transaction.commit();
		return merchant;
	}

	public Merchant FindById(int id) {
		return manager.find(Merchant.class, id);

	}

	public Merchant UpdateMerchant(Merchant merchant) {
		EntityTransaction transaction = manager.getTransaction();
		Merchant dbMerchant = manager.find(Merchant.class, merchant.getId());
		if (dbMerchant != null) {
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPassword(merchant.getPassword());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setPhone(merchant.getPhone());
			dbMerchant.setEmail(merchant.getEmail());
			transaction.begin();
			transaction.commit();
			return dbMerchant;
		}
		return null;

	}

	public Merchant VerifyByEmailAndPassword(String email, String password) {
		Query q = manager.createQuery("select m from Merchant m where m.email=?1 and m.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (Merchant) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public Merchant VerifyByPhoneAndPassword(Long phone, String password) {
		Query q = manager.createQuery("select m from Merchant m where m.phone=?1 and m.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Merchant) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
