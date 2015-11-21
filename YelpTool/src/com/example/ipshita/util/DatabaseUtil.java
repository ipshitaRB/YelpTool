package com.example.ipshita.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.example.ipshita.DAO.AttributeDAO;
import com.example.ipshita.DAO.BusinessDAO;
import com.example.ipshita.DAO.BusinessHoursDAO;
import com.example.ipshita.DAO.CategoryDAO;
import com.example.ipshita.DAO.ReviewDAO;
import com.example.ipshita.DAO.UserDAO;
import com.example.ipshita.bean.AttributeBean;
import com.example.ipshita.bean.BusinessBean;
import com.example.ipshita.bean.BusinessHoursBean;
import com.example.ipshita.bean.CategoryBean;
import com.example.ipshita.bean.ReviewBean;
import com.example.ipshita.bean.UserBean;

public class DatabaseUtil {

	public static void insertInCategories(String businessId, String mainCategory, String subCategory, Connection connection) {
		CategoryBean categoryBean = new CategoryBean(businessId, mainCategory, subCategory);
		CategoryDAO dao = new CategoryDAO();
		dao.add(categoryBean, connection);
		
	}

	public static void insertInAttributes(String business_id, String attribute, Connection connection) {
		AttributeBean attributeBean = new AttributeBean(business_id, attribute);
		AttributeDAO dao = new AttributeDAO();
		dao.add(attributeBean, connection);
		
	}

	public static void insertInBusinessHours(String business_id, String dayOfTheWeek, String close, String open, Connection connection) {
		BusinessHoursBean businessHoursBean = new BusinessHoursBean(business_id, dayOfTheWeek, close, open);
		BusinessHoursDAO dao = new BusinessHoursDAO();
		dao.add(businessHoursBean, connection);
		
	}

	public static void insertInBusiness(String business_id, String city, String state, String name, float stars, Connection connection) {
		BusinessBean businessBean = new BusinessBean(business_id, city, state, name, stars);
		BusinessDAO dao = new BusinessDAO();
		dao.add(businessBean, connection);
	}

	public static void insertInReviews(String review_id, String user_id, String business_id, int useful, float stars,
			String date, String text, Connection connection) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date simpledDate;
		try {
			simpledDate = sdf.parse(date);
			java.sql.Date sqlStartDate = new java.sql.Date(simpledDate.getTime()); 
			ReviewBean reviewBean = new ReviewBean(review_id, user_id, business_id, useful, stars, sqlStartDate, text);
			ReviewDAO dao = new ReviewDAO();
			dao.add(reviewBean,connection);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	public static void insertInUser(String user_id, String name, Connection connection) {
		UserBean userBean = new UserBean(user_id, name);
		UserDAO dao = new UserDAO();
		dao.add(userBean, connection);
		
	}

	public static String[] getMainCategories(Connection connection) {
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT DISTINCT MainCategory FROM BusinessCategories";
		ArrayList<String> mainCategoryList = new ArrayList<>();
		
		try {
			
			preparedStatement = connection.prepareStatement(selectSQL);
			

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {

				mainCategoryList.add(rs.getString("mainCategory"));

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


		}
		
		String[] mainCategories = new String[mainCategoryList.size()];
		mainCategories = mainCategoryList.toArray(mainCategories);
		return mainCategories;
	}

	public static String[] getSubCategories(Connection connection) {
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT DISTINCT SubCategory FROM BusinessCategories";
		ArrayList<String> subCategoryList = new ArrayList<>();
		
		try {
			
			preparedStatement = connection.prepareStatement(selectSQL);
			

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {

				subCategoryList.add(rs.getString("subCategory"));

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


		}
		
		String[] subCategories = new String[subCategoryList.size()];
		subCategories = subCategoryList.toArray(subCategories);
		return subCategories;
		
	}

	public static String[] getAllAttribues(Connection connection) {
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT DISTINCT attribute FROM Attributes";
		ArrayList<String> attributeList = new ArrayList<>();
		
		try {
			
			preparedStatement = connection.prepareStatement(selectSQL);
			

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {

				attributeList.add(rs.getString("attribute"));

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


		}
		
		String[] attributes = new String[attributeList.size()];
		attributes = attributeList.toArray(attributes);
		return attributes;
	}

}
