package com.example.ipshita.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
			ptmt.setTimestamp(3, null);
			ptmt.setTimestamp(4, null);
			if (!businessHoursBean.getClose().isEmpty()) {
				/*String[] hourmin = businessHoursBean.getClose().split(":");
				int hour = Integer.parseInt(hourmin[0]);
				int min = Integer.parseInt(hourmin[1]);
				
				Time time = new Time(hour, min, 0);
				*/
				long time;
				try {
			      Date date =  hourMinFormatter.parse(businessHoursBean.getClose());
			      java.sql.Timestamp ts = new Timestamp(date.getTime());
				     
					ptmt.setTimestamp(3, ts);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (!businessHoursBean.getOpen().isEmpty()) {
				long time;
				try {
				      Date date = (Date) hourMinFormatter.parse(businessHoursBean.getOpen());
				      java.sql.Timestamp ts = new Timestamp(date.getTime());
					ptmt.setTimestamp(4, ts);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
