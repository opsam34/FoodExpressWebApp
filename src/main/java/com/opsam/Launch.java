package com.opsam;

import java.util.List;
import java.util.Scanner;

import com.opsam.DaoImpl.MenuDaoImpl;
import com.opsam.DaoImpl.OrdersDaoImpl;
import com.opsam.DaoImpl.OrdersItemDaoImpl;
import com.opsam.DaoImpl.RestaurantDaoImpl;
import com.opsam.DaoImpl.UserDaoImpl;
import com.opsam.models.Menu;
import com.opsam.models.Orders;
import com.opsam.models.OrdersItem;
import com.opsam.models.Restaurant;
import com.opsam.models.User;

public class Launch {
	static Scanner sc ;
	public static void main(String[] args) {

		sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("Choooose the Following:");
			System.out.println("1.User Operations");
			System.out.println("2.Restaurant Operations");
			System.out.println("3.Orders Operations");
			System.out.println("4.Menu Operations");
			System.out.println("5.OrdersItem Operations");
			System.out.println("6.Exit");
			System.out.print("Your choice is:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: userOperations();break;
			case 2: restaurantOperations();break;
			case 3: ordersOperations();break;
			case 4: menuOperations();break;
			case 5: ordersItemOperations();break;
			default:
				System.exit(0);
				break;
			}
		}
		//		userOperations();
		//		restaurantOperations();
		//		ordersOperations();
		//		menuOperations();
		//		ordersItemOperations();

	}

	
//	############################ OrdersItem

	private static void ordersItemOperations() {
		while(true) {
			System.out.println("Choooose the Following:");
			System.out.println("1.Add ordersItem");
			System.out.println("2.Update ordersItem");
			System.out.println("3.Delete ordersItem");
			System.out.println("4.Get ordersItem");
			System.out.println("5.GetAll ordersItem");
			System.out.println("6.Exit");
			System.out.print("Your choice is:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: addOrdersItem();break;
			case 2: updateOrdersItem();break;
			case 3: deleteOrdersItem();break;
			case 4: getOrdersItem();break;
			case 5: getAllOrdersItem();break;
			default:
				System.exit(0);
				break;
			}
		}

		//addOrdersItem();
		//updateOrdersItem();
		//deleteOrdersItem();
		//getOrdersItem();
		//getAllOrdersItem();
	}


	private static void getAllOrdersItem() {
		System.out.println("GET ALL OrdersItem");
		OrdersItemDaoImpl odi = new OrdersItemDaoImpl();
		List<OrdersItem> list = odi.getAllOrdersItem();
		for (OrdersItem ordersItem : list) {
			System.out.println(ordersItem);
		}
	}


	private static void getOrdersItem() {
		System.out.println("Get OrdersItem..");
		System.out.println("Enter the id to get OrdersItem");
		int id = sc.nextInt();

		OrdersItemDaoImpl odi = new OrdersItemDaoImpl();
		OrdersItem ordersItem = odi.getOrdersItem(id);
		System.out.println(ordersItem);
	}


	private static void updateOrdersItem() {
		System.out.println("Enter the id to Update");
		int id = sc.nextInt();

		OrdersItemDaoImpl odi = new OrdersItemDaoImpl();
		OrdersItem ordersItem = odi.getOrdersItem(id);
		System.out.println("Enter the totalprice to be update ");
		int value = sc.nextInt();
		ordersItem.setTotalPrice(value);
		int res = odi.updateOrdersItem(ordersItem);

		if(res==1) {
			System.out.println("UPDATE SUCCESFUL");
		}else {
			System.out.println("UPDATE Failed");
		}
	}


	private static void deleteOrdersItem() {
		System.out.println("Enter the id to delete");
		int id = sc.nextInt();
		OrdersItemDaoImpl odi = new OrdersItemDaoImpl();
		int res = odi.deleteOrdersItem(id);
		if(res==1) {
			System.out.println("DELETE SUCCESSFUL");
		}
		else {
			System.out.println("DELETE Failed");	
		}
	}


	private static void addOrdersItem() {
		System.out.println("Adding OrderItems");
//		System.out.println("Enter the OrderItemId");
//		int orderItemId = sc.nextInt();
		System.out.println("Enter the OrderId");
		int orderId = sc.nextInt();
		System.out.println("Enter the menuId");
		int menuId = sc.nextInt();
		System.out.println("Enter the quantity");
		int quantity = sc.nextInt();
		System.out.println("Enter the totalPrice");
		int totalPrice = sc.nextInt();

		OrdersItem ordersItem = new OrdersItem(orderId, menuId, quantity, totalPrice);
		OrdersItemDaoImpl odi = new OrdersItemDaoImpl();
		odi.addOrdersItem(ordersItem);
		System.out.println(ordersItem);
	}


	//######################### Restaurant Operations
	private static void restaurantOperations() {
		while(true) {
			System.out.println("Choooose the Following:");
			System.out.println("1.Add Restaurant");
			System.out.println("2.Update Restaurant");
			System.out.println("3.Delete Restaurant");
			System.out.println("4.Get Restaurant");
			System.out.println("5.GetAll Restaurant");
			System.out.println("6.Exit");
			System.out.print("Your choice is:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: addRestaurant();break;
			case 2: updateRestaurant();break;
			case 3: deleteRestaurant();break;
			case 4: getRestaurant();break;
			case 5: getAllRestaurant();break;
			default:
				System.exit(0);
				break;
			}
		}
		//addRestaurant();
		//updateRestaurant();	
		//deleteRestaurant();
		//getRestaurant();
		//getAllRestaurant();
	}

	private static void deleteRestaurant() {
		System.out.println("Enter the id to delete");
		int id = sc.nextInt();
		RestaurantDaoImpl rdi = new RestaurantDaoImpl();
		int res = rdi.deleteRestaurant(id);
		if(res==1) {
			System.out.println("DELETE SUCCESSFUL");
		}
		else {
			System.out.println("DELETE Failed");	
		}
	}

	private static void getAllRestaurant() {
		System.out.println("GET ALL RESTAURANT");
		RestaurantDaoImpl rdi = new RestaurantDaoImpl();
		List<Restaurant> list = rdi.getAllRestaurants();

		for (Restaurant restaurant : list) {
			System.out.println(restaurant);
		}
	}

	private static void getRestaurant() {
		System.out.println("Get Restaurant..");
		System.out.println("Enter the id to get Restaurant");
		int id = sc.nextInt();

		RestaurantDaoImpl rdi = new RestaurantDaoImpl();
		Restaurant restaurant = rdi.getRestaurant(id);
		System.out.println(restaurant);
	}

	private static void updateRestaurant() {
		System.out.println("Enter the id to Update");
		int id = sc.nextInt();

		RestaurantDaoImpl rdi = new RestaurantDaoImpl();
		Restaurant restaurant = rdi.getRestaurant(id);
		System.out.println("Enter the name to be update ");
		String value = sc.next();
		restaurant.setName(value);
		int res = rdi.updateRestaurant(restaurant);


		if(res==1) {
			System.out.println("UPDATE SUCCESFUL");
		}else {
			System.out.println("UPDATE Failed");
		}
	}

	private static void addRestaurant() {
		System.out.println("Adding Restaurant");
//		System.out.println("Enter the restaurantId");
//		int restaurantId = sc.nextInt();
		System.out.println("Enter the name");
		String name = sc.next();
		System.out.println("Enter the address");
		String address = sc.next();
		System.out.println("Enter the phone");
		String phone = sc.next();
		System.out.println("Enter the rating");
		int rating = sc.nextInt();
		System.out.println("Enter the CusineType");
		String cusineType = sc.next();
		System.out.println("Enter the isActive");
		int isActive = sc.nextInt();
		System.out.println("Enter the eta");
		String eta = sc.next();
		System.out.println("Enter the adminUserId");
		int adminUserId = sc.nextInt();
		System.out.println("Enter the image path");
		String imagePath = sc.next();

		Restaurant restaurant = new Restaurant(name, address, phone, rating, cusineType, isActive, eta, adminUserId, imagePath);
		RestaurantDaoImpl rdi = new RestaurantDaoImpl();
		rdi.addRestaurant(restaurant);
		System.out.println(restaurant);
	}



	//############################# ORDERS Operations
	private static void ordersOperations() {
		while(true) {
			System.out.println("Choooose the Following:");
			System.out.println("1.Add orders");
			System.out.println("2.Update orders");
			System.out.println("3.Delete orders");
			System.out.println("4.Get orders");
			System.out.println("5.GetAll orders");
			System.out.println("6.Exit");
			System.out.print("Your choice is:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: addOrders();break;
			case 2: updateOrders();break;
			case 3: deleteOrders();break;
			case 4: getOrders();break;
			case 5: getAllOrders();break;
			default:
				System.exit(0);
				break;
			}
		}

		//addOrders();
		//updateOrders();
		//deleteOrders();
		//getOrders();
		//getAllOrders();
	}

	private static void getAllOrders() {
		System.out.println("GET ALL ORDERS");
		OrdersDaoImpl odi = new OrdersDaoImpl();
		List<Orders> list = odi.getAllOrders();

		for (Orders orders : list) {
			System.out.println(orders);
		}
	}
	private static void getOrders() {
		System.out.println("Get User>>");
		System.out.println("ENter the id to get Orders");
		int id = sc.nextInt();

		OrdersDaoImpl odi = new OrdersDaoImpl();
		Orders orders = odi.getOrders(id);
		System.out.println(orders);
	}

	private static void deleteOrders() {
		System.out.println("Enter the id to delete");
		int id = sc.nextInt();
		OrdersDaoImpl odi = new OrdersDaoImpl();
		int res = odi.deleteOrders(id);
		if(res==1) {
			System.out.println("DELETE SUCCESSFUL");
		}
		else {
			System.out.println("DELETE Failed");	
		}
	}

	private static void updateOrders() {
		System.out.println("Enter the id to Update");
		int id = sc.nextInt();

		OrdersDaoImpl odi = new OrdersDaoImpl();
		Orders orders = odi.getOrders(id);
		System.out.println("Enter the Total Amount ");
		int value = sc.nextInt();
		orders.setTotalAmount(value);

		int res = odi.updateOrders(orders);

		if(res==1) {
			System.out.println("UPDATE SUCCESFUL");
		}else {
			System.out.println("UPDATE Failed");
		}
	}

	private static void addOrders() {
		System.out.println("Adding Orders");
//		System.out.println("ENter the orderId");
//		int orderId = sc.nextInt();
		System.out.println("Enter the UserId");
		int userId = sc.nextInt();
		System.out.println("Enter the restaurantId");
		int restaurantId = sc.nextInt();
		System.out.println("Enter the totalAmount");
		int totalAmount = sc.nextInt();
		System.out.println("Enter the status");
		String status = sc.next();
		System.out.println("Enter the paymentMode");
		String paymentMode = sc.next();
		System.out.println("Enter the address");
		String address = sc.next();

		Orders orders = new Orders(userId, restaurantId, totalAmount, status, paymentMode,address);
		OrdersDaoImpl odi = new OrdersDaoImpl();
		odi.addOrders(orders);
		System.out.println(orders);
	}


	//	#################  MENU Operations

	private static void menuOperations() {
		while(true) {
			System.out.println("Choooose the Following:");
			System.out.println("1.Add menu");
			System.out.println("2.Update menu");
			System.out.println("3.Delete meu");
			System.out.println("4.Get menu");
			System.out.println("5.GetAll menu");
			System.out.println("6.Exit");
			System.out.print("Your choice is:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: addMenu();break;
			case 2: updateMenu();break;
			case 3: deleteMenu();break;
			case 4: getMenu();break;
			case 5: getAllMenu();break;
			default:
				System.exit(0);
				break;
			}
		}


		//addMenu();
		//updateMenu();
		//deleteMenu();
		//getMenu();
		//getAllMenu();
	}

	private static void getAllMenu() {
		System.out.println("We are getting all menu");
		MenuDaoImpl mdi = new MenuDaoImpl();
		List<Menu> list = mdi.getAllMenus();
		for (Menu menu : list) {
			System.out.println(menu);
		}
	}

	private static void getMenu() {
		System.out.println("Enter the id to get menu details");
		int id = sc.nextInt();
		MenuDaoImpl mdi = new MenuDaoImpl();
		Menu menu = mdi.getMenu(id);
		System.out.println(menu);
	}

	private static void deleteMenu() {
		System.out.println("Enetr the id to u want to delete");
		int id = sc.nextInt();

		MenuDaoImpl mdi = new MenuDaoImpl();
		int res = mdi.deleteMenu(id);
		if(res==1) {
			System.out.println("DELETE SUCCESSFUL");
		}
		else {
			System.out.println("DELETE Failed");
		}
	}

	private static void updateMenu() {
		System.out.println("Enter the id to Update");
		int id = sc.nextInt();

		MenuDaoImpl mdi = new MenuDaoImpl();
		Menu menu=mdi.getMenu(id);

		System.out.println("Enter the item name to change");
		String value = sc.next();
		menu.setItemName(value);

		int res = mdi.UpdateMenu(menu);

		if(res==1) {
			System.out.println("Updated Successful");
		}
		else {
			System.out.println("Updated Failed");
		}
	}

	private static void addMenu() {
		System.out.println("Adding menu");
//		System.out.println("Enter the menu id");
//		int menuId = sc.nextInt();
		System.out.println("enter the resrt id");
		int restaurantId = sc.nextInt();
		System.out.println("Enetr the item name	");
		String itemName = sc.next();
		System.out.println("Enter the description");
		String description = sc.next();
		System.out.println("Enter the price");
		int price = sc.nextInt();
		System.out.println("Enter the rating");
		int rating = sc.nextInt();
		System.out.println("Enter the isAvailable");
		int isAvailable= sc.nextInt();

		System.out.println("Enter the imagePath");
		String iamgePath = sc.next();

		Menu menu = new Menu(restaurantId, itemName, description, price, rating, isAvailable, iamgePath);
		MenuDaoImpl mdi=new MenuDaoImpl();

		mdi.addMenu(menu);
		System.out.println(menu);
	}



	//###############   USER Operations
	private static void userOperations() {
		while(true) {
			System.out.println("Choooose the Following:");
			System.out.println("1.Add User");
			System.out.println("2.Update User");
			System.out.println("3.Delete User");
			System.out.println("4.Get User");
			System.out.println("5.GetAll User");
			System.out.println("6.Exit");
			System.out.print("Your choice is:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: addUser();break;
			case 2: updateUser();break;
			case 3: deleteUser();break;
			case 4: getUser();break;
			case 5: getAllUser();break;
			default:
				System.exit(0);
				break;
			}
		}
	}

	private static void updateUser() {
		System.out.println("Enter the id to Update details:");
		int id = sc.nextInt();

		UserDaoImpl udi=new UserDaoImpl();
		User user = udi.getUser(id);
		System.out.println("We are Updating: \n Enter the Name:");
		String value = sc.next();
		user.setName(value);

		int res = udi.updateUser(user);
		if(res==1) {
			System.out.println("Succesful");
		}
		else {
			System.out.println("Failed");
		}
	}

	private static void getAllUser() {
		UserDaoImpl udi = new UserDaoImpl();

		List<User> list = udi.getAllUsers();
		//		System.out.println(list);
		for (User user : list) {
			System.out.println(user);
		}
	}

	private static void deleteUser() {
		System.out.println("Enter the id which user data want to delete:");
		int id = sc.nextInt();

		UserDaoImpl udi = new UserDaoImpl();
		int res = udi.deleteUser(id);
		if(res==1) {
			System.out.println("User data deleted Successfully");
		}
		else {
			System.out.println("User data is not deleted");
		}
	}

	private static void getUser() {
		System.out.println("Enter the id to get User details:");
		int id = sc.nextInt();

		UserDaoImpl u=new UserDaoImpl();
		User user = u.getUser(id);

		System.out.println(user);
	}

	private static void addUser() {
		System.out.println("Adding User");
//		System.out.println("Enter the User Id:");
//		int userId = sc.nextInt();
		System.out.println("Enter the Name:");
		String name = sc.next();
		System.out.println("Enter the User-Name:");
		String username = sc.next();
		System.out.println("Enter the Password:");
		String password = sc.next();
		System.out.println("Enter the Email:");
		String email = sc.next();
		System.out.println("Enter the Phone No:");
		String phone = sc.next();
		System.out.println("Enter the Address:");
		String address = sc.next();
		System.out.println("Enter the role:");
		String role = sc.next();
		//		String name = sc.next();


		User user=new User(name, username, password, email, phone, address, role);

		UserDaoImpl u=new UserDaoImpl();
		u.addUser(user);
		System.out.println(user);
	}
}
