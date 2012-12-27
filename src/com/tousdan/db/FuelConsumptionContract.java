package com.tousdan.db;

import android.provider.BaseColumns;

public class FuelConsumptionContract {
	private FuelConsumptionContract() {}
	
	public static abstract class GasEntry implements BaseColumns {	
		public static final String TABLE_NAME = "gasentry";
		
		public static final String COLUMN_NAME_ENTRY_ID = "_id";
		public static final String COLUMN_NAME_KILOMETERS_ODOMETER = "kilo_odometer";
		public static final String COLUMN_NAME_LITERS = "liters";
		public static final String COLUMN_NAME_PRICE_PER_LITER = "price_per_liter";
		public static final String COLUMN_NAME_DATE_ADDED = "date_added";
		public static final String COLUMN_NAME_DATE_LAST_MODIFIED = "date_modified";
		
		public static final String COLUMN_NAME_VEHICULE_ID = "vehicule_id";
	}
	
	public static abstract class VehicleEntry implements BaseColumns {
		public static final String TABLE_NAME = "vehicule";
		
		public static final String COLUMN_NAME_ID = "_id";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_DATE_ADDED = "date_added";
		public static final String COLUMN_NAME_DATE_LAST_MODIFIED = "date_modified";
	}
	
	
}
