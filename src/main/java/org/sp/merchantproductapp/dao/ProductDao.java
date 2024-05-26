package org.sp.merchantproductapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.merchantproductapp.dto.Merchant;
import org.jsp.merchantproductapp.dto.Product;

public class ProductDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("development");
	EntityManager manager = factory.createEntityManager();

	public Product saveProduct(Product product,int merchant_id) {
		Merchant merchant=manager.find(Merchant.class, merchant_id);
		if(merchant !=null) {
		EntityTransaction transaction = manager.getTransaction();
		merchant.getProducts().add(product);
		product.setMerchant(merchant);
		transaction.begin();
		manager.persist(product);
		transaction.commit();
		return product;
	}
		return null;
	}
	
	public Product findById(int id) {
		return manager.find(Product.class,id);
		
	}
	public Product updateProduct(Product product) {
		EntityTransaction transaction=manager.getTransaction();
		Product dbProduct=manager.find(Product.class, product.getId());
		if(dbProduct !=null) {
			dbProduct.setBrand(product.getBrand());
			dbProduct.setCategory(product.getCategory());
			dbProduct.setCost(product.getCost());
			dbProduct.setDescription(product.getDescription());
			dbProduct.setImage_url(product.getImage_url());
			transaction.begin();
			transaction.commit();
			return dbProduct;
		}
		return  null;
		
	}
	
	public List<Product> findByBrand(String brand){
		Query q=manager.createQuery("select p from Product p where p.brand=?1");
		q.setParameter(1, brand);
		return q.getResultList();
	}
	
	public List<Product> findByCategory(String category){
		Query q=manager.createQuery("select p from Product p where p.category=?1");
		q.setParameter(1, category);
		return q.getResultList();
	}
	
	public List<Product> findByName(String name){
		Query q=manager.createQuery("select p from Product p where p.name=?1");
		q.setParameter(1, name);
		return q.getResultList();
	}
	
	public List<Product> findProductByMerchant_id(int merchant_id){
		Query q=manager.createQuery("select p from Product p where p.merchant.sid=?1");
		q.setParameter(1, merchant_id);
		return q.getResultList();
	}
	
	public List<Product> findAllProduct(){
		Query q=manager.createQuery("select p from Product p");
		return q.getResultList();
	}

}
