package com.tousdan.tasks;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.SlidingDrawer;

import com.tousdan.db.FuelConsumptionContract;

public class NewEntryTask extends AsyncTask<ContentValues, Void, Long> {
	public final static String TAG = "NewEntryTask";
	
	private SQLiteDatabase db;
	public NewEntryTask(SQLiteDatabase db) {
		this.db = db;
	}
	
	@Override
	protected Long doInBackground(ContentValues... params) {
		Log.i(TAG, "About to insert entry...");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db.insert(FuelConsumptionContract.GasEntry.TABLE_NAME, null, params[0]);
	}

}
