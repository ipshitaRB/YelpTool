package com.example.ipshita.model;


public class Review {
	
	/*{
			'votes': {
			'useful': (count of useful votes),
			'funny': (count of funny votes),
			'cool': (count of cool votes)
			}
			'user_id': (the identifier of the authoring user),
			'review_id': (the identifier of the reviewed business),
			'stars': (star rating, integer 1-5),
			'date': (date, formatted like '2011-04-19'),
			'text': (review text),
			'type': 'review',
			'business_id': (the identifier of the reviewed business)
			}
		*/

	

	public Votes votes;
	public String user_id;
	public String review_id;
	public float stars;
	public String date;
	public String text;
	public String type;
	public String business_id;
	
	@Override
	public String toString() {
		return business_id.toString();
	}

}

