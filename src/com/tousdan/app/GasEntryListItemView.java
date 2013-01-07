package com.tousdan.app;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class GasEntryListItemView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_gas_entry_list_item);
		
		Log.i("BACON", "FUDGE");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.aview_gas_entry_list_item, menu);
		return true;
	}

}
