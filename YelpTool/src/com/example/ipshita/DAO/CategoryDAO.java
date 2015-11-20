package com.example.ipshita.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.example.ipshita.bean.CategoryBean;

public class CategoryDAO {
	
	public void add(CategoryBean categoryBean, Connection connection) {
		PreparedStatement ptmt = null;
		try {
			String queryString = "INSERT INTO BusinessCategories (BusinessId, MainCategory, SubCategory) VALUES(?,?,?)";
		
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, categoryBean.getBusinessId());
			ptmt.setString(2, categoryBean.getMainCategory());
			ptmt.setString(3, categoryBean.getSubCategory());
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
