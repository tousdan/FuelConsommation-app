package com.tousdan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FuelConsumptionDbHelper extends SQLiteOpenHelper {
	public final static String TAG = "FuelConsumptionDbHelper";
	
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "FuelConsumption.db";
	
	private static final String COMMA_SEP = ",";
	
	//sqlite has no date type see : http://www.sqlite.org/datatype3.html
	private static final String DATE_TYPE = " TEXT"; 
	private static final String INTEGER_TYPE = " INTEGER";
	private static final String DOUBLE_TYPE  = " REAL";
	private static final String STRING_TYPE = " STRING";
	
	private static final String SQL_CREATE_GAS_ENTRIES =
	    "CREATE TABLE " + FuelConsumptionContract.GasEntry.TABLE_NAME + " (" +
	    		FuelConsumptionContract.GasEntry._ID + " INTEGER PRIMARY KEY," +
	    		FuelConsumptionContract.GasEntry.COLUMN_NAME_DATE_ADDED + DATE_TYPE + COMMA_SEP +
	    		FuelConsumptionContract.GasEntry.COLUMN_NAME_DATE_LAST_MODIFIED + DATE_TYPE + COMMA_SEP +
	    		FuelConsumptionContract.GasEntry.COLUMN_NAME_KILOMETERS_ODOMETER + INTEGER_TYPE + COMMA_SEP +
	    		FuelConsumptionContract.GasEntry.COLUMN_NAME_LITERS + INTEGER_TYPE + COMMA_SEP +
	    		FuelConsumptionContract.GasEntry.COLUMN_NAME_PRICE_PER_LITER + DOUBLE_TYPE + COMMA_SEP +
	    		FuelConsumptionContract.GasEntry.COLUMN_NAME_VEHICULE_ID + INTEGER_TYPE + COMMA_SEP +
	    " FOREIGN KEY (" + FuelConsumptionContract.GasEntry.COLUMN_NAME_VEHICULE_ID + ") REFERENCES " + FuelConsumptionContract.VehicleEntry.TABLE_NAME + " (" + FuelConsumptionContract.VehicleEntry.COLUMN_NAME_ID + ")" +
	    " )";

	private static final String SQL_DELETE_GAS_ENTRIES =
	    "DROP TABLE IF EXISTS " + FuelConsumptionContract.GasEntry.TABLE_NAME;
	
	private static final String SQL_CREATE_VEHICULE_ENTRIES =
	    "CREATE TABLE " + FuelConsumptionContract.VehicleEntry.TABLE_NAME + " (" +
	    		FuelConsumptionContract.VehicleEntry._ID + " INTEGER PRIMARY KEY," +
	    		FuelConsumptionContract.VehicleEntry.COLUMN_NAME_DATE_ADDED + DATE_TYPE + COMMA_SEP +
	    		FuelConsumptionContract.VehicleEntry.COLUMN_NAME_DATE_LAST_MODIFIED + DATE_TYPE + COMMA_SEP +
	    		FuelConsumptionContract.VehicleEntry.COLUMN_NAME_NAME + STRING_TYPE + "UNIQUE" +
	    " )";
	
	private static final String SQL_DELETE_VEHICULE_ENTRIES =
		    "DROP TABLE IF EXISTS " + FuelConsumptionContract.VehicleEntry.TABLE_NAME;
	
	
	public FuelConsumptionDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "Creating databases");
		
		db.execSQL(SQL_CREATE_VEHICULE_ENTRIES);
		db.execSQL(SQL_CREATE_GAS_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "Upgrading databases, was at v" + oldVersion + " and now upgrading to " + newVersion);
		
		Log.d(TAG, "Deleting databases");
		db.execSQL(SQL_DELETE_GAS_ENTRIES);
		db.execSQL(SQL_DELETE_VEHICULE_ENTRIES);
		
		onCreate(db);
	}
	
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "Downgrating databases, was at v" + oldVersion + " and now downgrading to " + newVersion);
		onUpgrade(db, oldVersion, newVersion);
	}

}
