package com.tousdan.app;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tousdan.db.FuelConsumptionContract;
import com.tousdan.db.FuelConsumptionDbHelper;
import com.tousdan.tasks.NewEntryTask;

public class AddNewGasEntryActivity extends Activity {
	
	private static final String TAG = "AddNewGasEntryActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_form);
	}
	
	public void onAddNewEntry(View view) {
		//parse form data and validate.
		String raw_kilometers = ((EditText) findViewById(R.id.kilometers_odo)).getText().toString();
		int kilometers = tryParseInt(raw_kilometers, getString(R.string.error_kilometer_not_a_valid_value));
		
		if(kilometers == -1) return;
		
		String raw_liters = ((EditText) findViewById(R.id.liters_filled)).getText().toString();
		double liters = tryParseDouble(raw_liters, getString(R.string.error_liters_not_a_valid_value));
		
		if(liters == -1) return;
		
		String raw_ppl = ((EditText) findViewById(R.id.price_per_liter)).getText().toString();
		double ppl = tryParseDouble(raw_ppl, getString(R.string.error_price_per_liter_not_a_valid_value));
		
		if(ppl == -1) return;
		
		
		//if any error - function should have returned already. insert in db and return to main screen.
    	ContentValues values = new ContentValues();
    	
    	values.put(FuelConsumptionContract.GasEntry.COLUMN_NAME_KILOMETERS_ODOMETER, kilometers);
    	values.put(FuelConsumptionContract.GasEntry.COLUMN_NAME_LITERS, liters);
    	values.put(FuelConsumptionContract.GasEntry.COLUMN_NAME_PRICE_PER_LITER, ppl);
    	
    	Log.i(TAG, "About to start new entry task...");
    	
    	Long entryId;
    	try {
    		//this task \\cant\\ fail (yet).
    		entryId = new NewEntryTask(new FuelConsumptionDbHelper(this).getReadableDatabase()).execute(values).get().getValue();
		} catch (InterruptedException e) {
			Log.e(TAG, "Exception", e);
			showValidationError("Error!");
			return;
		} catch (ExecutionException e) {
			Log.e(TAG, "Exception", e);
			showValidationError("Error!");
			return;
		}
    	
    	Intent intent = new Intent(this, MainActivity.class);
    	
    	intent.putExtra("id", entryId);
    	
    	setResult(RESULT_OK, intent);
    	
    	finish();
	}
	
	private int tryParseInt(String raw_value, String error) {
		try {
			int value = Integer.parseInt(raw_value);
			
			if(value <= 0) {
				showValidationError(error);
			} else {
				return value;
			}
		} catch(NumberFormatException ex) {
			showValidationError(error);
		}
		
		return -1;
	}
	
	private double tryParseDouble(String raw_value, String error) {
		try {
			double value = Double.parseDouble(raw_value);
			
			if(value <= 0) {
				showValidationError(error);
			} else {
				return value;
			}
		} catch(NumberFormatException ex) {
			showValidationError(error);
		}
		
		return -1;
	}
	
	private void showValidationError(String error) {
		Toast.makeText(this, error, Toast.LENGTH_LONG).show();
	}
}
