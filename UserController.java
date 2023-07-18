package org.jsp.springuserprodapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.springuserprodapp.UserConfig;
import org.jsp.springuserprodapp.dao.ProductDao;
import org.jsp.springuserprodapp.dao.UserDao;
import org.jsp.springuserprodapp.dto.Product;
import org.jsp.springuserprodapp.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserController {
	
	static Scanner s = new Scanner(System.in);
	static UserDao uDao;
	static ProductDao pDao;
	static {
		ApplicationContext c = new AnnotationConfigApplicationContext(UserConfig.class);
		uDao = c.getBean(UserDao.class);
		pDao = c.getBean(ProductDao.class);
	}
	
	public static void main(String[] args) {
		System.out.println("1. Save the User");
		System.out.println("2. Update the User");
		System.out.println("3. Find User By Id");
		System.out.println("4. DeleteUserById");
		System.out.println("5. Save the Product");
		System.out.println("6. Fetch the Product by UserName");
		System.out.println("7. Find Product By Id");
		System.out.println("8. Delete Product By Id");
		System.out.println("9. fetch Product by UserId");
		System.out.println("10.view Product by Brand");
		System.out.println("11.view Product by Category");
		System.out.println("12.view Product by Price");
		System.out.println("13. Update Product by Id");
		System.out.println("14. fetch Product by Category and Brand");
		System.out.println("15. Verify User By Phone and Password");
		System.out.println("16. Fetch Product by Phone and Password");
		int choice = s.nextInt();
		switch(choice) {
		case 1:{
			save();
			break;
		}
		case 2:{
			update();
			break;
		}
		case 3:{
			findById();
			break;
		}
		case 4:{
			deleteUser();
			break;
		}
		case 5:{
			saveProduct();
			break;
		}
		case 6:{
			fetchUserByProductName();
			break;
		}
		case 7:{
			fetchProductById();
			break;
		}
		case 8:{
			deleteProductById();
			break;
		}
		case 9:{
			fetchProductByUserId();
			break;
		}
		case 10:{
			fetchProductByBrand();
			break;
		}
		case 11:{
			fetchProductByCategory();
			break;
		}
		case 12:{
			fetchProductByPrice();
			break;
		}
		case 13:{
			updateProductById();
			break;
		}
		case 14:{
			fetchProductByCategoryAndBrand();
			break;
		}
		case 15:{
			verifyUserByPhoneAndPassword();
			break;
		}
		case 16:{
			fetchProductByPhoneAndPassword();
			break;
		}
		}
	}



	private static void fetchUserByProductName() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the Product name");
		String name = s.next();
		List<User>users = uDao.FindUserByProductName(name);
		for(User u: users) {
			System.out.println(u);
		}
		
	}



	private static void fetchProductByPhoneAndPassword() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the Phone and Password");
		long phone = s.nextLong();
		String password = s.next();
		List<Product>products = pDao.fetchProductByPhoneAndPassword(phone, password);
		for(Product p: products) {
			System.out.println(p);
		}
		
	}

	public static void verifyUserByPhoneAndPassword() {
		// TODO Auto-generated method stub
		System.out.println("Enter the Phone and Password");
		long phone = s.nextLong();
		String password =s.next();
		User u = uDao.VerifyUserByPhoneAndPassword(phone, password);
		if(u!=null) {
			System.out.println(u);
		}
		else {
			System.err.println("Something went Wrong");
		}
		
	}

	private static void fetchProductByCategoryAndBrand() {
		// TODO Auto-generated method stub
		System.out.println("Enter the Category and Brand");
		String category = s.next();
		String brand = s.next();
		Product p = pDao.fetchProductByCategoryAndBrand(category, brand);
		if(p!=null) {
			System.out.println(p);
		}
		
	}

	private static void updateProductById() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the id , name and brand");
		int id = s.nextInt();
		String name = s.next();
		String brand = s.next();
		Product p = pDao.findById(id);
		if(p!=null) {
			p.setBrand(brand);
			p.setName(name);
			p = pDao.updateProductById(id);
			System.out.println("Product Updated");
		}
		else {
			System.out.println("Not Updated");
		}
		
	}

	public static void fetchProductByPrice() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the price");
		double price = s.nextDouble();
		List<Product>products = pDao.fetchProductByPrice(price);
		for(Product p: products) {
			System.out.println(p);
		}
		
	}

	public static void fetchProductByBrand() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the brand");
		String brand = s.next();
		List<Product>products = pDao.fetchProductByBrand(brand);
		for(Product p: products) {
			System.out.println(p);
		}
		
	}

	public static void fetchProductByCategory() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the category");
		String category = s.next();
		List<Product>products = pDao.fetchProductByCategory(category);
		for(Product p: products) {
			System.out.println(p);
		}
		
	}

	public static void fetchProductByUserId() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the userId");
		int uid = s.nextInt();
		List<Product>products = pDao.fetchProductByUserId(uid);
		for(Product p : products) {
			System.out.println(p);
		}
		
		
	}

	public static void deleteProductById() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the id");
		int id = s.nextInt();
		boolean deleted = pDao.deleteproduct(id);
		if(deleted) {
			System.out.println("product deleted");
		}
		else {
			System.out.println("Something went Wrong Product not deleted");
		}
		
	}

	public static void fetchProductById() {
		// TODO Auto-generated method stub
		System.out.println("Enter the id");
		int id = s.nextInt();
		Product p = pDao.findById(id);
		System.out.println(p.getName()+ " "+p.getId());	
	}



	public static void saveProduct() {
		// TODO Auto-generated method stub
		System.out.println("Enter the userId");
		int uid = s.nextInt();
		System.out.println("Enter the following details name, brand, category, and price");
		String name = s.next();
		String brand = s.next();
		String category = s.next();
		double price = s.nextDouble();
		Product p = new Product();
		p.setBrand(brand);
		p.setCategory(category);
		p.setName(name);
		p.setPrice(price);
		p = pDao.SaveProduct(p, uid);
		if(p!=null) {
			System.out.println("Product added by id - "+p.getId());
		}
		else {
			System.out.println("product not added");
		}
		
		
	}

	public static void deleteUser() {
		// TODO Auto-generated method stub
		System.out.println("Enter the value of id");
		int id = s.nextInt();
		boolean deleted = uDao.deleteUser(id);
		if(deleted) {
			System.out.println("User Deleted");
		}
		else {
			System.out.println("Something went Wrong");
		}
		
	}

	public static void findById() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the value of Id");
		int id = s.nextInt();
		User u = uDao.findUserById(id);
		System.out.println(u.getName()+"  "+u.getPhone());
		
	}

	public static void update() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the id to update phone and email");
		int id = s.nextInt();
		long phone = s.nextLong();
		String email = s.next();
		User u = uDao.findUserById(id);
		if(u!=null) {
			u.setEmail(email);
			u.setPhone(phone);
			u = uDao.updateUser(u);

		}
		else {
			System.out.println("Invalid Id ");
		}
		
	}

	public static void save() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the name, phone, email & password");
		String name = s.next();
		long phone = s.nextLong();
		String email = s.next();
		String password = s.next();
		
		User u = new User();
		u.setEmail(email);
		u.setName(name);
		u.setPassword(password);
		u.setPhone(phone);
		u = uDao.saveUser(u);
		System.out.println("User Saved with the Id :"+u.getId());
		
	}

}
