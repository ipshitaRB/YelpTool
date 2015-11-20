package com.example.ipshita.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.ipshita.bean.BusinessBean;

public class BusinessDAO {
	
	



	public void add(BusinessBean businessBean, Connection connection) {
		PreparedStatement ptmt = null;
		try {
			String queryString = "INSERT INTO Business(BusinessID, City, State, Name, Stars) VALUES(?,?,?,?,?)";
			
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, businessBean.getBusiness_id());
			ptmt.setString(2, businessBean.getCity());
			ptmt.setString(3, businessBean.getState());
			ptmt.setString(4, businessBean.getName());
			ptmt.setFloat(5, businessBean.getStars());
			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
