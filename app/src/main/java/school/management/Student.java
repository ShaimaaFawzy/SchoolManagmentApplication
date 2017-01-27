package school.management;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Student extends Activity implements OnClickListener {
	private static String result = "no";
	private static String resullt;

	boolean checkCase;
	private Spinner spinner2;
	LinearLayout lone, ltwo, LIN;
	EditText editRollno, editName, editMarks, editphone, editcase, editdrive,
			editbus, editdrivname;
	RadioButton Raid, Raname;
	TextView showname, shownumber;
	CheckBox chid;
	Button btnAdd;
 	SQLiteDatabase db;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_student);

		editbus = (EditText) findViewById(R.id.editbus);
		editdrivname = (EditText) findViewById(R.id.drivname);
		editdrive = (EditText) findViewById(R.id.editprname);
		lone = (LinearLayout) findViewById(R.id.lin1);
		ltwo = (LinearLayout) findViewById(R.id.lin2);
		Raid = (RadioButton) findViewById(R.id.rdid);
		Raname = (RadioButton) findViewById(R.id.rdname);
		Raid.setOnClickListener(this);
		Raname.setOnClickListener(this);
		editRollno = (EditText) findViewById(R.id.editRollno);
		editName = (EditText) findViewById(R.id.editName);
		editMarks = (EditText) findViewById(R.id.editMarks);
		editphone = (EditText) findViewById(R.id.editphone);
		editcase = (EditText) findViewById(R.id.editcase);
		showname = (TextView) findViewById(R.id.prname);
		shownumber = (TextView) findViewById(R.id.prphone);
		LIN = (LinearLayout) findViewById(R.id.lin3);
		addItemsOnSpinner2();
		chid = (CheckBox) findViewById(R.id.chid);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		// btnDelete = (Button) findViewById(R.id.btnDelete);
		// btnModify = (Button) findViewById(R.id.btnModify);
		findViewById(R.id.chid).setOnClickListener(this);
		// btnView=(Button)findViewById(R.id.btnView);
		// btnViewAll = (Button) findViewById(R.id.btnViewAll);
		// btnShowInfo=(Button)findViewById(R.id.btnShowInfo);
		btnAdd.setOnClickListener(this);
		// btnDelete.setOnClickListener(this);
		// btnModify.setOnClickListener(this);
		// btnView.setOnClickListener(this);
		// btnViewAll.setOnClickListener(this);
		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR,marks VARCHAR,phone VARCHAR,spcasecc VARCHAR,typeofstudent VARCHAR,drivename VARCHAR,bus VARCHAR,pname VARCHAR);");
	}

	public void addItemsOnSpinner2() {

		spinner2 = (Spinner) findViewById(R.id.spinner2);
		List<String> list = new ArrayList<String>();
		list.add("American");
		list.add("British");
		list.add("languages");
		list.add("Early Years");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(dataAdapter);
	}

	public void onClick(View view) {

		switch (view.getId()) {

		case R.id.chid:
			if (true == checkCase) {
				result = "yes";
				editcase.setText(" " + result + " ");

			} else {
				result = "no";
				editcase.setText(" " + result + " ");

			}
			break;
		}
		if (view == Raid) {
			showname.setText("Parent Name  :" + " ");
			shownumber.setText("Parent Phone number  :" + " ");
			ltwo.setVisibility(View.VISIBLE);
			lone.setVisibility(View.VISIBLE);
			LIN.setVisibility(View.GONE);
			resullt = "Parent Name";
			editdrivname.setText(" " + resullt + " ");

		}
		if (view == Raname) {

			showname.setText("Driver  Name  :" + " ");
			shownumber.setText("Driver  Phone number  :" + " ");
			lone.setVisibility(View.VISIBLE);
			ltwo.setVisibility(View.VISIBLE);
			LIN.setVisibility(View.VISIBLE);
			resullt = "Driver Name";
			editdrivname.setText(" " + resullt + " ");
		}
		// /String.valueOf(spinner2.
		if (view == btnAdd) {
			if (editRollno.getText().toString().trim().length() == 0
					|| editName.getText().toString().trim().length() == 0
					|| editMarks.getText().toString().trim().length() == 0
					|| editphone.getText().toString().trim().length() == 0
					|| editcase.getText().toString().trim().length() == 0
					|| spinner2.getSelectedItem().toString().trim().length() == 0) {
				showMessage("Error", "Please enter all values");
				return;
			}
			db.execSQL("INSERT INTO student VALUES('" + editRollno.getText()
					+ "','" + editName.getText() + "','" + editMarks.getText()
					+ "','" + editphone.getText() + "','" + editcase.getText()
					+ "','" + spinner2.getSelectedItem() + "','"
					+ editdrive.getText() + "','" + editbus.getText() + "','"
					+ editdrivname.getText() + "');");
			showMessage("Success", "Record added");
			clearText();
			//
		}
	}

	public void showMessage(String title, String message) {
		Builder builder = new Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();
	}

	public void clearText() {
		editRollno.setText("");
		editName.setText("");
		editMarks.setText("");
		editphone.setText("");
		editcase.setText("");
		editbus.setText("");
		editdrive.setText("");
		// edit_Driverid.setText("");
		editRollno.requestFocus();
	}
}