package com.tousdan.tasks;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.tousdan.db.FuelConsumptionContract;

public class NewEntryTask extends AsyncTask<ContentValues, Void, AsyncTaskResult<Long, Void>> {
	public final static String TAG = "NewEntryTask";
	
	private SQLiteDatabase db;
	public NewEntryTask(SQLiteDatabase db) {
		this.db = db;
	}
	
	@Override
	protected AsyncTaskResult<Long, Void> doInBackground(ContentValues... params) {
		Log.i(TAG, "About to insert entry...");
		
		return new AsyncTaskSuccess<Long, Void>(db.insert(FuelConsumptionContract.GasEntry.TABLE_NAME, null, params[0]));
	}

}
