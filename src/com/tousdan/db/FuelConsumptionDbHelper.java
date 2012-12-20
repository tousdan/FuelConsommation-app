package com.tousdan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FuelConsumptionDbHelper extends SQLiteOpenHelper {
	public static final int DATABASE_VERSION = 2;
	public static final String DATABASE_NAME = "FuelConsumption.db";
	
	private static final String COMMA_SEP = ",";
	
	//sqlite has no date type see : http://www.sqlite.org/datatype3.html
	private static final String DATE_TYPE = " TEXT"; 
	private static final String INTEGER_TYPE = " INTEGER";
	private static final String DOUBLE_TYPE  = " REAL";
	
	private static final String SQL_CREATE_GAS_ENTRIES =
	    "CREATE TABLE " + FuelConsumptionContract.GasEntry.TABLE_NAME + " (" +
	    		FuelConsumptionContract.GasEntry._ID + " INTEGER PRIMARY KEY," +
	    		FuelConsumptionContract.GasEntry.COLUMN_NAME_DATE + DATE_TYPE + COMMA_SEP +
	    		FuelConsumptionContract.GasEntry.COLUMN_NAME_KILOMETERS_ODOMETER + INTEGER_TYPE + COMMA_SEP +
	    		FuelConsumptionContract.GasEntry.COLUMN_NAME_LITERS + INTEGER_TYPE + COMMA_SEP +
	    		FuelConsumptionContract.GasEntry.COLUMN_NAME_PRICE_PER_LITER + DOUBLE_TYPE +
	    " )";

	private static final String SQL_DELETE_ENTRIES =
	    "DROP TABLE IF EXISTS " + FuelConsumptionContract.GasEntry.TABLE_NAME;
	
	
	public FuelConsumptionDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL(SQL_CREATE_GAS_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
		
		onCreate(db);
	}
	
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}

}
