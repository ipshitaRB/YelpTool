package com.example.ipshita.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.ipshita.bean.UserBean;
import com.example.ipshita.util.ConnectionFactory;

public class UserDAO {

	public PreparedStatement add(UserBean userBean, Connection connection) {
		
		PreparedStatement ptmt = null;
			String queryString = "INSERT INTO yelpUser(userID, userName) VALUES(?,?)";
			
			try {
				ptmt = connection.prepareStatement(queryString);
				ptmt.setString(1, userBean.getUser_id());
				ptmt.setString(2, userBean.getName());
				ptmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 finally {
					try {
						if (ptmt != null)
							ptmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			return ptmt;
	}
		

}
