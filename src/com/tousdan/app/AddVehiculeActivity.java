package com.tousdan.app;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tousdan.db.FuelConsumptionContract;
import com.tousdan.db.FuelConsumptionDbHelper;
import com.tousdan.tasks.AsyncTaskResult;
import com.tousdan.tasks.NewVehiculeTask;

public class AddVehiculeActivity extends Activity {
	private static final String TAG = "AddVehiculeActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_vehicule);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_vehicule, menu);
		return true;
	}
	
	public void onSubmitNewVehiculeClicked(View view) {
		EditText vehiculeNameComponent = (EditText) findViewById(R.id.txtVehiculeName);
		
		String vehiculeName = vehiculeNameComponent.getText().toString();	
		
		ContentValues values = new ContentValues();
		values.put(FuelConsumptionContract.VehicleEntry.COLUMN_NAME_NAME, vehiculeName);
		
		AsyncTaskResult<Long, String> result;
		try {
			result = new NewVehiculeTask(new FuelConsumptionDbHelper(this).getWritableDatabase()).execute(values).get();
		} catch (InterruptedException e) {
			Log.e(TAG, "Exception", e);
			showValidationError("Error!");
			return;
		} catch (ExecutionException e) {
			Log.e(TAG, "Exception", e);
			showValidationError("Error!");
			return;
		}
			
		if(result.isError()) {
			showValidationError(result.getError());
		} else {
			Intent intent = new Intent(this, MainActivity.class);
	    	
	    	intent.putExtra("id", result.getValue());
	    	
	    	setResult(RESULT_OK, intent);
	    	
	    	finish();
		}
	}
	
	private void showValidationError(String error) {
		Toast.makeText(this, error, Toast.LENGTH_LONG).show();
	}
}
