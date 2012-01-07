package com.wecontact;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.io.InputStream;
import java.util.List;
import android.widget.Button;

public class StaffActivity extends Activity
{
	private List<Staff> staffList;
	
    /**
	 * Called when the activity is first created. 
	 * 
	 * @return void
	 */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        renderStaffList();
    }
	
	/**
	 * Render Staff List View
	 * 
	 * @return void
	 */
	public void renderStaffList() {
		
			// render staff list layout
		setContentView(R.layout.staff_list);
		
			// initialize data parser class represent staff repository
		StaffParser staffParser = new StaffParser();
		
			// inputStream reads xml from resource folder
		InputStream inputStream = getResources().openRawResource(R.raw.staffs);
		
			// manipulate data from stream reader with staff repository
		staffParser.parse(inputStream);
		
			// array of staff object in List type
		staffList = staffParser.getList();
		
			// generate an adapter to be a datasource of ListView widget
		StaffListAdapter adapter = new StaffListAdapter(getApplicationContext(), R.layout.staff_list_item, staffList);
		
			// get listview and set datasource to it
		ListView lstView = (ListView) findViewById(R.id.lstView);
		lstView.setAdapter(adapter);
		lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				renderStaffDetail(position + 1);
			}
		});
	}
	
	/**
	 * Render Staff Detail View
	 * 
	 * @param int staffId
	 * @return void
	 */
	public void renderStaffDetail(int staffId) {
		setContentView(R.layout.staff_detail);
		
			// get selected staff
		Staff staff = staffList.get(staffId - 1);
				
		ImageView staffImage = (ImageView) findViewById(R.id.staff_img);
		String imgFilePath =  StaffListAdapter.ASSETS_DIR + staffId + StaffListAdapter.FILE_EXT;
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(this.getResources().getAssets().open(imgFilePath));
			staffImage.setImageBitmap(bitmap);
		} catch (Exception e) {
		}
		
			// name textview
		TextView staffName = (TextView) findViewById(R.id.staff_name);
		staffName.setText(staffName.getText() + ": " + staff.name);
		
			// email textview
		TextView staffEmail = (TextView) findViewById(R.id.staff_email);
		staffEmail.setText(staffEmail.getText() + ": " + staff.email);
		
			// position textview
		TextView staffPosition = (TextView) findViewById(R.id.staff_position);
		staffPosition.setText(staffPosition.getText() + ": " + staff.position);
		
			// mobile textview
		TextView staffMobile = (TextView) findViewById(R.id.staff_mobile);
		staffMobile.setText(staffMobile.getText() + ": " + staff.mobile);
		
			// skype textview
		TextView staffSkype = (TextView) findViewById(R.id.staff_skype);
		staffSkype.setText(staffSkype.getText() + ": " + staff.skype);
		
			// back to list button
		Button backToList = (Button) findViewById(R.id.btn_back_to_list);
		backToList.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				renderStaffList();
			}
		});
	}
}