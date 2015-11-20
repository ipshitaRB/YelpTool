package com.example.ipshita.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.ipshita.bean.ReviewBean;
import com.example.ipshita.util.ConnectionFactory;

import oracle.sql.CLOB;

public class ReviewDAO {



	public PreparedStatement add(ReviewBean reviewBean, Connection connection) {
		PreparedStatement ptmt = null;
		
			String queryString = "INSERT INTO BusinessReview(ReviewID, userID, BusinessID, VotesUseful, stars,reviewDate,description) VALUES(?,?,?,?,?,?,?)";
			
			try {
				ptmt = connection.prepareStatement(queryString);
				ptmt.setString(1, reviewBean.getReview_id());
				ptmt.setString(2, reviewBean.getUser_id());
				ptmt.setString(3, reviewBean.getBusiness_id());
				ptmt.setInt(4, reviewBean.getUseful());
				ptmt.setFloat(5, reviewBean.getStars());
				ptmt.setDate(6, reviewBean.getDate());
				ptmt.setString(7, reviewBean.getDescription());
				ptmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
