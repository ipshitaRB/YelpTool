package com.example.ipshita.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.example.ipshita.model.Business;
import com.example.ipshita.model.Review;
import com.example.ipshita.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JSONParser {

	private static String[] mainCategories = { "Active Life", "Arts & Entertainment", "Automotive", "Car Rental",
			"Cafes", "Beauty & Spas", "Convenience Stores", "Dentists", "Doctors", "Drugstores", "Department Stores",
			"Education", "Event Planning & Services", "Flowers & Gifts", "Food", "Health & Medical", "Home Services",
			"Home & Garden", "Hospitals", "Hotels & Travel", "Hardware Stores", "Grocery", "Medical Centers",
			"Nurseries & Gardening", "Nightlife", "Restaurants", "Shopping", "Transportation" };

	private static void insertInBusinessHours(String business_id, Business.Hours hours, Connection connection) {
		if (null != hours) {
			if (null != hours.Monday) {

				DatabaseUtil.insertInBusinessHours(business_id, "Monday", hours.Monday.close, hours.Monday.open, connection);
			}
			if (null != hours.Tuesday) {

				DatabaseUtil.insertInBusinessHours(business_id, "Tuesday", hours.Tuesday.close, hours.Tuesday.open, connection);
			}
			if (null != hours.Wednesday) {

				DatabaseUtil.insertInBusinessHours(business_id, "Wednesday", hours.Wednesday.close,
						hours.Wednesday.open, connection);
			}
			if (null != hours.Thursday) {
				DatabaseUtil.insertInBusinessHours(business_id, "Thursday", hours.Thursday.close, hours.Thursday.open, connection);
			}
			if (null != hours.Friday) {
				DatabaseUtil.insertInBusinessHours(business_id, "Friday", hours.Friday.close, hours.Friday.open, connection);
			}
			if (null != hours.Saturday) {
				DatabaseUtil.insertInBusinessHours(business_id, "Saturday", hours.Saturday.close, hours.Saturday.open, connection);
			}
			if (null != hours.Sunday) {
				DatabaseUtil.insertInBusinessHours(business_id, "Sunday", hours.Sunday.close, hours.Sunday.open, connection);
			}

		}

	}

	private static void splitCategories(String[] categories, String businessId,Connection connection) {
		ArrayList<String> mainCategoriesList = new ArrayList<>();
		ArrayList<String> subCategoryList = new ArrayList<>();
		for (int i = 0; i < categories.length; i++) {
			if (isMainCategory(categories[i])) {
				mainCategoriesList.add(categories[i]);
			} else {
				subCategoryList.add(categories[i]);
			}
		}
		for (Iterator<String> iterator = mainCategoriesList.iterator(); iterator.hasNext();) {
			String mainCategory = (String) iterator.next();
			for (Iterator<String> iterator2 = subCategoryList.iterator(); iterator2.hasNext();) {
				String subCategory = (String) iterator2.next();
				DatabaseUtil.insertInCategories(businessId, mainCategory, subCategory, connection);

			}
		}
	}

	private static boolean isMainCategory(String category) {
		for (int i = 0; i < mainCategories.length; i++) {
			if (category.equals(mainCategories[i])) {
				return true;
			}
		}
		return false;
	}

	private static void insertInAttributes(String[] split, String business_id, Connection connection) {
		for (int i = 0; i < split.length; i++) {
			DatabaseUtil.insertInAttributes(business_id, split[i], connection);
		}

	}

	public static String processAttribute(JsonElement jsonElement, String key) {

		String attribute = "";

		// Check whether jsonElement is JsonObject or not
		if (jsonElement.isJsonObject()) {
			Set<Entry<String, JsonElement>> ens = ((JsonObject) jsonElement).entrySet();
			if (ens != null) {
				// Iterate JSON Elements with Key values
				for (Entry<String, JsonElement> en : ens) {

					if (en.getValue().isJsonPrimitive()) {
						if (!en.getValue().getAsString().equals("true")) {
							// append key + val + nextline
							if (key.equals("")) {
								attribute += en.getKey() + " " + en.getValue().getAsString() + "\n";
							} else
								attribute += key + " " + en.getKey() + " " + en.getValue().getAsString() + "\n";
						} else {
							// append key + nextline
							if (key.equals("")) {
								attribute += en.getKey() + "\n";
							} else
								attribute += key + " " + en.getKey() + "\n";
						}
					} else {
						// append print json
						attribute += processAttribute(en.getValue(), en.getKey());
					}
				}
			}
		}

		return attribute;
	}

	public static void parseInsertBusinessData(String businessFileName , Connection connection) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		BufferedReader br;
		String line;
		try {
			br = new BufferedReader(new FileReader(businessFileName));

			while ((line = br.readLine()) != null) {
				Business business = gson.fromJson(line, Business.class);
				// insert in business
				DatabaseUtil.insertInBusiness(business.business_id, business.city, business.state, business.name,
						business.stars, connection);
				// insert for each business id the attributes
				insertInAttributes(processAttribute(business.attributes, "").split("\n"), business.business_id, connection);

				// after splitting categories the categories are inserted
				splitCategories(business.categories, business.business_id, connection);

				// insert businessHours
				insertInBusinessHours(business.business_id, business.hours, connection);

			}
			br.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Business Json file " + businessFileName + " not found ");

		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading Business Json file " + businessFileName);
		}

	}

	public static void parseInsertUserData(String userFileName, Connection connection) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		BufferedReader br;
		String line;

		try {
			br = new BufferedReader(new FileReader(userFileName));

			while ((line = br.readLine()) != null) {

				User user = gson.fromJson(line, User.class);
				DatabaseUtil.insertInUser(user.user_id, user.name, connection);
			}

			br.close();

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("User Json file, " + userFileName + ", not found ");
		} catch (IOException e) {

			throw new IllegalArgumentException("Error reading User Json file " + userFileName);
		}

	}

	public static void parseInsertReviewData(String reviewFileName, Connection connection) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		BufferedReader br;
		String line;
		try {
			br = new BufferedReader(new FileReader(reviewFileName));
			while ((line = br.readLine()) != null) {
				Review review = gson.fromJson(line, Review.class);
				DatabaseUtil.insertInReviews(review.review_id, review.user_id, review.business_id, review.votes.useful,
						review.stars, review.date, review.text, connection);
			}
			br.close();

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Review Json file, " + reviewFileName + ", not found ");
		} catch (IOException e) {

			throw new IllegalArgumentException("Error reading Review Json file " + reviewFileName);
		}

	}
}
