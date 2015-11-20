package com.example.ipshita.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.ipshita.bean.AttributeBean;

public class AttributeDAO {

	public void add(AttributeBean attributeBean, Connection connection) {
		PreparedStatement ptmt = null;
		try {
			String queryString = "INSERT INTO Attributes (businessID, attribute) VALUES(?,?)";
			if (null != attributeBean && (null != attributeBean.getAttribute())
					&& !attributeBean.getAttribute().isEmpty()) {

				ptmt = connection.prepareStatement(queryString);
				ptmt.setString(1, attributeBean.getBusiness_id());

				ptmt.setString(2, attributeBean.getAttribute());
				ptmt.executeUpdate();

			}
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
