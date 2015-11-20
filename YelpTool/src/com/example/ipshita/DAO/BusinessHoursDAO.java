package com.example.ipshita.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.example.ipshita.bean.BusinessHoursBean;
import com.example.ipshita.util.ConnectionFactory;

public class BusinessHoursDAO {
	
	private static final DateFormat hourMinFormatter = new SimpleDateFormat(
			"HH:mm");
	public void add(BusinessHoursBean businessHoursBean, Connection connection) {
		PreparedStatement ptmt = null;
		
		try {
			String queryString = "INSERT INTO BusinessHours(BusinessID, DayOfTheWeek, Close, Open) VALUES(?,?,?,?)";
			
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, businessHoursBean.getBusiness_id());
			
			ptmt.setString(2, businessHoursBean.getDayOfTheWeek());
			
			if (!businessHoursBean.getClose().isEmpty()) {
				String[] hourmin = businessHoursBean.getClose().split(":");
				int hour = Integer.parseInt(hourmin[0]);
				int min = Integer.parseInt(hourmin[1]);
				
				Time time = new Time(hour, min, 0);
				
				ptmt.setTime(3,time );
			}
			if (!businessHoursBean.getOpen().isEmpty()) {
				String[] hourmin = businessHoursBean.getOpen().split(":");
				int hour = Integer.parseInt(hourmin[0]);
				int min = Integer.parseInt(hourmin[1]);
				
				Time time = new Time(hour, min, 0);
				
				ptmt.setTime(4,time );
			}
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
