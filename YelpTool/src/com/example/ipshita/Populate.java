package com.example.ipshita;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.ipshita.util.ConnectionFactory;
import com.example.ipshita.util.JSONParser;

public class Populate {

	public static Connection connection = null;
	
	public static void main(String[] args) {
		if (args.length != 4 ) {
			throw new IllegalArgumentException("Dataset file names missing.\n Execute as : > java Populate yelp_business.json yelp_review.json yelp_checkin.json yelp_user.json " + args.length);
		}else {
			//CALL DETELE FUNCTION deleteContent();
			try {
				connection = getConnection();
				deleteAllRecords(connection);
				populateBusiness(args[0], connection);
				populateUser(args[3], connection);
				populateReview(args[1], connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}

	private static void deleteAllRecords(Connection connection) {
		String[] tableNames = {"BusinessReview", "yelpUser", "BusinessHours", "Attributes", "BusinessCategories", "BusinessHours", "Business"};
		String del;
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			for (int i = 0; i < tableNames.length; i++) {
				
				del = "DELETE FROM " + tableNames[i] ;
				stmt.executeUpdate(del);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (null != stmt) {
				try {
					stmt.close();
					stmt = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	private static void populateReview(String reviewFileName, Connection connection) {
		JSONParser.parseInsertReviewData(reviewFileName, connection);
		
	}

	private static void populateUser(String userFileName, Connection connection) {
		JSONParser.parseInsertUserData(userFileName, connection);
		
	}

	private static void populateBusiness(String businessFileName, Connection connection) {
		JSONParser.parseInsertBusinessData(businessFileName, connection);
		
	}

	private static Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	
}
