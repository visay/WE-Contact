package com.wecontact;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class StaffListAdapter extends ArrayAdapter<Staff> {
	
	private static final String tag = "StaffArrayAdapter";
	public static final String ASSETS_DIR = "images/";
	public static final String FILE_EXT = ".jpg";
	
	private List<Staff> staffs = new ArrayList<Staff>();
	private Context context;
	private ImageView staffImage;
	private TextView staffName;
	
	public StaffListAdapter(Context context, int textViewResourceId, List<Staff> objects)
	{
		super(context, textViewResourceId, objects);
		this.context = context;
		this.staffs = objects;
	}
	
	@Override
	public int getCount() {
		return this.staffs.size();
	}
	
	@Override
	public Staff getItem(int index) {
		return this.staffs.get(index);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(tag, "Starting XML Row Inflation ... ");
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.staff_list_item, parent, false);
			Log.d(tag, "Successfully completed XML Row Inflation!");
		}
		
		Staff staff = getItem(position);
		
		staffImage = (ImageView) row.findViewById(R.id.staff_img);
		String imgFilePath = StaffListAdapter.ASSETS_DIR + staff.id + StaffListAdapter.FILE_EXT;
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(this.context.getResources().getAssets().open(imgFilePath));
			staffImage.setImageBitmap(bitmap);
		} catch (Exception e) {
		}
		
		staffName = (TextView) row.findViewById(R.id.staff_name);
		staffName.setText(staff.name);
		
		return row;
	}
}