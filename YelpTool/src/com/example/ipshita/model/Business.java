package com.example.ipshita.model;

import com.google.gson.JsonElement;

public class Business {
	
	
	
	public String business_id;
	public String full_address;
	public Hours hours;
	public boolean open;
	public String[] categories;
	public String city;
	public int review_count;
	public String name;
	public String[] neighborhoods;
	public double longitude;
	public String state;
	public float stars;
	public double latitude;
	public JsonElement attributes;
	public String type;

	public static class Timings {
		
		public String open = "";
		public String close = "";

	}

	public static class Hours {
		
		public Timings Sunday;
		public Timings Monday;
		public Timings Tuesday;
		public Timings Wednesday;
		public Timings Thursday;
		public Timings Friday;
		public Timings Saturday;

	}
}


