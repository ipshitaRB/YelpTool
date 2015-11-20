package com.example.ipshita.model;

import com.google.gson.JsonObject;

public class User {
	
/*	{
		‘yelping_since’: (the date when user account was created)
		'votes': {
		'useful': (count of useful votes across all reviews),
		'funny': (count of funny votes across all reviews),
		'cool': (count of cool votes across all reviews)a
		4}
		'review_count': (review count),
		'name': (first name, last initial, like 'Matt J.'),
		'user_id': (unique user identifier),
		‘friends’: (friends of the user),
		‘fans’: (number fans of the user),
		'average_stars': (floating point average, like 4.31),
		'type': 'user',
		‘compliments’: (comments from other users),
		‘elite’: ()
		}*/
	
	public String yelping_since;
	public Votes votes;
	public int review_count;
	public String name;
	public String user_id;
	public String[] friends;
	public int fans;
	public float average_stars;
	public String type;
	public JsonObject compliments;
	public int[] elite;
	

}
