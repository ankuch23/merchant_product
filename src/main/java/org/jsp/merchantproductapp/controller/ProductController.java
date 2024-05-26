package org.jsp.merchantproductapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.merchantproductapp.dto.Product;
import org.sp.merchantproductapp.dao.ProductDao;

public class ProductController {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("1.save product");
		System.out.println("2.Find product by id");
		System.out.println("3.Update the product");
		System.out.println("4.Find Product By brand");
		System.out.println("5.Find Product By name");
		System.out.println("6.Find Product By category");
		System.out.println("7.Find Product By merchant_id");
		System.out.println("8.Find All Product");
		ProductDao dao=new ProductDao();
		
		switch(sc.nextInt()) {
		case 1:{
			Product p=new Product();
			System.out.println("Enter the merchant_id");
			int merchant_id=sc.nextInt();
			System.out.println("Enter the brand,category,cost,description,imag_url and name");
			
			p.setBrand(sc.next());
			p.setCategory(sc.next());
			p.setCost(sc.nextDouble());
			p.setDescription(sc.next());
			p.setImage_url(sc.next());
			p.setName(sc.next());
			p=dao.saveProduct(p, merchant_id);
			System.out.println("Product saved with:"+merchant_id);
		}
		case 2:{
			System.out.println("Enter the Id");
			Product product=dao.findById(sc.nextInt());
			if(product !=null) {
				System.out.println(product);
			}
			else {
				System.err.println("No record");
				break;
			}
		}
		
		case 3:{
			System.out.println("Enter the brand,category,cost,description,image_url,name");
			Product product=new Product();
			product.setBrand(sc.next());
			product.setCategory(sc.next());
			product.setCost(sc.nextDouble());
			product.setImage_url(sc.next());
			product.setName(sc.next());
			product=dao.updateProduct(product);
			if(product !=null) {
				System.out.println("product updated successfully");
			}
			else {
				System.err.println("cannot update the record");
				break;
			}
			
		}
		case 4:{
			System.out.println("Enter the brand to find products");
			String brand = sc.next();
			List<Product> products = dao.findByBrand(brand);
			if (products.isEmpty())
				System.err.println("No Product found with entered brand");
			else
				for (Product p : products) {
					System.out.println(p);
				}
			break;
			
		}
		
		case 5:{
			System.out.println("Enter the Name to find products");
			String name=sc.next();
			List<Product> products = dao.findByName(name);
			if (products.isEmpty())
				System.err.println("No Product found with entered category");
			else
				for (Product p : products) {
					System.out.println(p);
				}
			break;
			
		}
		
		case 6:{
			System.out.println("Enter the Category to find products");
			String category=sc.next();
			List<Product> products = dao.findByCategory(category);
			if (products.isEmpty())
				System.err.println("No Product found with entered category");
			else
				for (Product p : products) {
					System.out.println(p);
				}
			break;
			
		}
		
		case 7:{
			System.out.println("Enter the merchant_id to find products");
			int merchant_id=sc.nextInt();
			List<Product> products = dao.findProductByMerchant_id(merchant_id);
			if (products.isEmpty())
				System.err.println("No Product found with entered category");
			else
				for (Product p : products) {
					System.out.println(p);
				}
			break;
			
		}
		
		case 8:{
			List<Product> products = dao.findAllProduct();
			for (Product p : products) {
				System.out.println(p);
				System.out.println("----------------------------------");
			}
			break;
		}
		default: {
			sc.close();
			System.err.println("Invalid Choice");
		}
			
		}
		
		
		
		
		
		
		

	}
}
