package com.tousdan.tasks;

import java.util.Date;

import android.content.ContentValues;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.text.format.Time;

import com.tousdan.db.FuelConsumptionContract;

public class NewVehiculeTask extends AsyncTask<ContentValues, Void, AsyncTaskResult<Long, String>> {
	
	private SQLiteDatabase db;
	public NewVehiculeTask(SQLiteDatabase db) {
		this.db = db;
	}
	
	public static final String ERROR_NAME_REQUIRED = "NameMissing";
	public static final String ERROR_NOT_UNIQUE = "AlreadyUsed";
	
	@Override
	protected AsyncTaskResult<Long, String> doInBackground(ContentValues... values) {
		ContentValues entry = values[0];
		
		String vehiculeName = entry.getAsString(FuelConsumptionContract.VehicleEntry.COLUMN_NAME_NAME);
		
		//name is not empty?
		if(vehiculeName == null || TextUtils.getTrimmedLength(vehiculeName) == 0) {
			return new AsyncTaskError<Long, String>(ERROR_NAME_REQUIRED);
		}
		
		vehiculeName = vehiculeName.trim();
		
		long vehiculesWithSameName = DatabaseUtils.queryNumEntries(
				db, 
				FuelConsumptionContract.VehicleEntry.TABLE_NAME, 
				"LOWER("+FuelConsumptionContract.VehicleEntry.COLUMN_NAME_NAME + ") = ?", 
				new String[] {vehiculeName.toLowerCase()});
		
		//validate that the name is not already in use.
		if(vehiculesWithSameName > 0) {
			return new AsyncTaskError<Long, String>(ERROR_NOT_UNIQUE);
		}
		
		Time now = new Time(Time.getCurrentTimezone());
		
		now.setToNow();
		
		entry.put(FuelConsumptionContract.VehicleEntry.COLUMN_NAME_DATE_ADDED, now.format("%Y-%m-%d %H:%M"));
		
		long id = db.insert(FuelConsumptionContract.VehicleEntry.TABLE_NAME, null, entry);
		
		return new AsyncTaskSuccess<Long, String>(id);
	}

}
