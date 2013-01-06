package com.tousdan.tasks;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.tousdan.db.FuelConsumptionContract;

public class NewVehiculeTask extends AsyncTask<ContentValues, Void, AsyncTaskResult<Long, String>> {

	@Override
	protected AsyncTaskResult<Long, String> doInBackground(ContentValues... values) {
		ContentValues entry = values[0];
		
		String vehiculeName = entry.getAsString(FuelConsumptionContract.VehicleEntry.COLUMN_NAME_NAME);
		//name is not empty?
		if(vehiculeName == null || TextUtils.getTrimmedLength(vehiculeName) == 0) {
			return new AsyncTaskError<Long, String>("Nom du vehicule requis");
		}
		
		//validate that the name is not already in use.
		
		return new AsyncTaskError<Long, String>("Not Implemented Yet!");
	}

}
