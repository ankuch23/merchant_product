package org.jsp.merchantproductapp.controller;

import java.util.Scanner;

import org.jsp.merchantproductapp.dto.Merchant;
import org.sp.merchantproductapp.dao.MerchantDao;

public class MerchantController {

	public static void main(String[] args) {
		MerchantDao dao=new MerchantDao();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter option");

		System.out.println("1.Save Merchant");
		System.out.println("2.Update Merchant");
		System.out.println("3.Find Merchant By Id");
		System.out.println("4.verify Merchant By Email and Password");
		System.out.println("5.verify Merchant By Phone and Password");

		switch (sc.nextInt()) {
		case 1:{
			System.out.println("Enter name,phone,email,gst_no,password");
			Merchant m=new Merchant();
			m.setName(sc.next());
			m.setPhone(sc.nextLong());
			m.setEmail(sc.next());
			m.setGst_number(sc.next());
			m.setPassword(sc.next());
			m=dao.saveMerchant(m);
			System.out.println("Merchant saved with id:"+m.getId());
		}
		case 2:{
			System.out.println("Enter name,phone,email,gst_no,password");
			Merchant m=new Merchant();
			m.setName(sc.next());
			m.setPhone(sc.nextLong());
			m.setEmail(sc.next());
			m.setGst_number(sc.next());
			m.setPassword(sc.next());
			m=dao.UpdateMerchant(m);
			if(m !=null) {
				System.out.println("Merchant updated successfully");
			}
			else {
				System.err.println("cannot update merchant as Id is Invalid");
				break;
			}
		}
		case 3:{
			System.out.println("Enter the Merchant Id to display details");
			Merchant merchant=dao.FindById(sc.nextInt());
			if(merchant !=null) {
				System.out.println(merchant);
			}
			else {
				System.err.println("Invalid Merchant Id");
				break;
			}
		}
		case 4:
		{
			System.out.println("Enter the email and passsword");
			String email=sc.next();
			String password=sc.next();
			Merchant merchant=dao.VerifyByEmailAndPassword(email, password);
			if(merchant !=null) {
				System.out.println(merchant);
			}
			else {
				System.err.println("Invalid email and password");
				break;
			}
		}
		case 5:
		{
			System.out.println("Enter the phone and passsword");
			long phone=sc.nextLong();
			String password=sc.next();
			Merchant merchant=dao.VerifyByPhoneAndPassword(phone, password);
			if(merchant !=null) {
				System.out.println(merchant);
			}
			else {
				System.err.println("Invalid phone and password");
				break;
			}
		}


		}

	}

}
