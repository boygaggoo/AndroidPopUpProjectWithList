package com.zia.androidpopupprojectwithlist;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button popUpButton;
	PopupWindow pw;
	// List view
	private ListView lv;

	// Listview Adapter
	ArrayAdapter<String> adapter;

	// Search EditText
	EditText inputSearch;

	// ArrayList for Listview
	ArrayList<HashMap<String, String>> productList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		popUpButton = (Button) findViewById(R.id.buttonPopUp);
		popUpButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				initiatePopupWindow();

			}
		});

	}

	private void initiatePopupWindow() {
		try {
			// Listview Data
			
			String products[] = getResources().getStringArray(R.array.Spinner_Country);
			// We need to get the instance of the LayoutInflater, use the
			// context of this activity
			LayoutInflater inflater = (LayoutInflater) MainActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// Inflate the view from a predefined XML layout
			View layout = inflater.inflate(R.layout.poplistwithsearch,
					(ViewGroup) findViewById(R.id.popup_element));

			// create a 300px width and 470px height PopupWindow
			pw = new PopupWindow(layout, 300, 470, true);

			// display the popup in the center
			pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

			Button buttoncancel = (Button) layout.findViewById(R.id.button1);
			buttoncancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					pw.dismiss();
				}

			});
			// ////////
			lv = (ListView) layout.findViewById(R.id.list_view);
			inputSearch = (EditText) layout.findViewById(R.id.inputSearch);

			// Adding items to listview
			adapter = new ArrayAdapter<String>(this, R.layout.list_item,
					R.id.product_name, products);
			lv.setAdapter(adapter);

			/**
			 * Enabling Search Filter
			 * */
			inputSearch.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2,
						int arg3) {
					// When user changed the Text
					MainActivity.this.adapter.getFilter().filter(cs);
				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {
					// 

				}

				@Override
				public void afterTextChanged(Editable arg0) {
					//
				}
			});
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					TextView v = (TextView) arg1.findViewById(R.id.product_name);
					arg1.setBackgroundColor(Color.GREEN);
					
					String text = ((TextView) arg1.findViewById(R.id.product_name)).getText().toString() ;
					System.out.println(arg2 + "=postion="+v.getText()+"String="+text);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_search) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }
}
