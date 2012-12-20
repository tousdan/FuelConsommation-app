package com.tousdan.db;

import android.provider.BaseColumns;

public class FuelConsumptionContract {
	private FuelConsumptionContract() {}
	
	public static abstract class GasEntry implements BaseColumns {	
		public static final String TABLE_NAME = "gasentry";
		
		public static final String COLUMN_NAME_ENTRY_ID = "_id";
		public static final String COLUMN_NAME_KILOMETERS_ODOMETER = "kiloOdometer";
		public static final String COLUMN_NAME_LITERS = "liters";
		public static final String COLUMN_NAME_PRICE_PER_LITER = "pricePerLiter";
		public static final String COLUMN_NAME_DATE = "date";
	}
	
	
}
