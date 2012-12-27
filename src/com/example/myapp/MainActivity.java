package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tousdan.db.FuelConsumptionContract;
import com.tousdan.db.FuelConsumptionDbHelper;

public class MainActivity extends Activity {
	public final static String TAG = "MainActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
                       
        
        ListView list = (ListView) findViewById(R.id.list_view);
        
        TextView noEntries = new TextView(this);
        
        noEntries.setText(R.string.no_entries);
        
        list.setEmptyView(noEntries);
        
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, null, new String[] {
        		FuelConsumptionContract.GasEntry.COLUMN_NAME_KILOMETERS_ODOMETER,
        		FuelConsumptionContract.GasEntry.COLUMN_NAME_LITERS
        }, new int[] {
        		android.R.id.text1, 
        		android.R.id.text2
        }, SimpleCursorAdapter.NO_SELECTION);

        list.setAdapter(adapter);
        
        refreshListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater inflater = getMenuInflater();
    	
        inflater.inflate(R.menu.activity_main, menu);
        
        return true;
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	
    	Log.i(TAG, "Restarted");
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	Log.i(TAG, "Resumed");
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

    	MenuItem add_plein = menu.findItem(R.id.menu_add_plein);
    	FuelConsumptionDbHelper helper = new FuelConsumptionDbHelper(this);
    	
    	SQLiteDatabase db = helper.getReadableDatabase();
    	
    	//Pas de pleins si il y a pas de vehicules.
    	add_plein.setEnabled(DatabaseUtils.queryNumEntries(db, FuelConsumptionContract.VehicleEntry.TABLE_NAME) > 0);
    	
    	return true;
    }
    
    public void showAddForm() {
    	Intent intent = new Intent(this, AddNewGasEntryActivity.class);
    	
    	startActivityForResult(intent, 0);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	//added a new entry! refresh the list view.
    	Toast.makeText(this, getString(R.string.success_new_entry), Toast.LENGTH_LONG).show();
    	
    	Log.i(TAG, "onActivityResult");
    	
    	refreshListView();
    }
    
    public void clearEntries(View view) {
    	FuelConsumptionDbHelper helper = new FuelConsumptionDbHelper(this);
    	
    	SQLiteDatabase db = helper.getWritableDatabase();
    	
    	db.delete(FuelConsumptionContract.GasEntry.TABLE_NAME, null, null);
    	
    	refreshListView();
    }
    
    private void refreshListView() {
    	ListView list = (ListView) findViewById(R.id.list_view);
    	
    	SimpleCursorAdapter adapter = (SimpleCursorAdapter) list.getAdapter();
    	
    	FuelConsumptionDbHelper helper = new FuelConsumptionDbHelper(this);
        
        SQLiteDatabase db = helper.getReadableDatabase();
        
        Cursor cursor = db.query(FuelConsumptionContract.GasEntry.TABLE_NAME, new String[] {
        		FuelConsumptionContract.GasEntry._ID,
        		FuelConsumptionContract.GasEntry.COLUMN_NAME_KILOMETERS_ODOMETER,
        		FuelConsumptionContract.GasEntry.COLUMN_NAME_LITERS
        }, null, null, null, null, FuelConsumptionContract.GasEntry.COLUMN_NAME_KILOMETERS_ODOMETER + " DESC");
        
    	adapter.changeCursor(cursor);
    	
    }
    
    @Override 
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    		case R.id.menu_add_plein:
    			showAddForm();
    			return true;
    	}
    	
    	return super.onOptionsItemSelected(item);
    }
    
}
