package school.management;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class Update extends Activity implements OnClickListener {

	RadioButton chrdid, chrdname;

	EditText txteditname, txteditid  ;
	Button btn_Searchid, btn_Searchname, btn_Deleteid, btn_Deletename;
	TextView  txt_name, edttexdrivertxt_name, txt_id, txt_class, txt_type, txt_case, txt_prantname,
	txt_perntnumber, text_bus, txtshb ;

	boolean checkby;
	SQLiteDatabase db;

	LinearLayout linname, linbid;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_update);

		linname = (LinearLayout) findViewById(R.id.linStud);
		linbid = (LinearLayout) findViewById(R.id.linid);

		txtshb = (TextView) findViewById(R.id.shby);
		chrdid = (RadioButton) findViewById(R.id.rdid);
		chrdname = (RadioButton) findViewById(R.id.rdname);

		txteditname = (EditText) findViewById(R.id.editname);
		txteditid = (EditText) findViewById(R.id.editid);

		btn_Searchid = (Button) findViewById(R.id.btnSearchid);
		btn_Searchname = (Button) findViewById(R.id.btnSearchname);
		btn_Deleteid = (Button) findViewById(R.id.btnDeleteid);
		btn_Deletename = (Button) findViewById(R.id.btnDeletename);

		txt_name = (TextView) findViewById(R.id.txtname);
		txt_id = (TextView) findViewById(R.id.txtid);
		txt_class = (TextView) findViewById(R.id.txtclass);
		txt_type = (TextView) findViewById(R.id.txttype);
		txt_case = (TextView) findViewById(R.id.txtcase);
		txt_prantname = (TextView) findViewById(R.id.txtprantname);
		txt_perntnumber = (TextView) findViewById(R.id.perntnumber);
		text_bus = (TextView) findViewById(R.id.textbus);

		btn_Searchid.setOnClickListener(this);
		btn_Searchname.setOnClickListener(this);
		btn_Deleteid.setOnClickListener(this);
		btn_Deletename.setOnClickListener(this);

		chrdid.setOnClickListener(this);
		chrdname.setOnClickListener(this);

		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
	}

	public void onClick(View view) {
		if (view == chrdid) {
			// showname.setText("Parent Name  :" + " ");
			// shownumber.setText("Parent Phone number  :" + " ");
			btn_Searchid.setVisibility(View.VISIBLE);
			btn_Searchname.setVisibility(View.GONE);
			btn_Deleteid.setVisibility(View.VISIBLE);
			btn_Deletename.setVisibility(View.GONE);
			txtshb.setText("update by id  :" + " ");
			txteditname.setVisibility(View.GONE);
			txteditid.setVisibility(View.VISIBLE);
			linname.setVisibility(View.VISIBLE);
			txteditname.setText("");
			txteditid.setText("");
			linbid.setVisibility(View.GONE);

		}
		if (view == chrdname) {
			txtshb.setText("Update by Name  :" + " ");
			linname.setVisibility(View.GONE);
			linbid.setVisibility(View.VISIBLE);
			txteditname.setText("");
			txteditid.setText("");
			// showname.setText("Driver  Name  :" + " ");
			// shownumber.setText("Driver  Phone number  :" + " ");
			btn_Searchid.setVisibility(View.GONE);
			btn_Searchname.setVisibility(View.VISIBLE);
			btn_Deleteid.setVisibility(View.GONE);
			btn_Deletename.setVisibility(View.VISIBLE);
			txteditname.setVisibility(View.VISIBLE);
			txteditid.setVisibility(View.GONE);

		}
		if (view == btn_Searchid) {
			if (txteditid.getText().toString().trim().length() == 0) {
				showMessage("Error", "Please enter Student Id");
				return;
			}
			Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='"
					+ txteditid.getText() + "'", null);
			if (c.moveToFirst()) {
				txt_name.setText(c.getString(1));
				txt_class.setText(c.getString(2));
				txt_perntnumber.setText(c.getString(3));
				txt_case.setText(c.getString(4));
				txt_type.setText(c.getString(5));
				txt_prantname.setText(c.getString(6));
				text_bus.setText(c.getString(7));
				txt_id.setText(c.getString(0));
				// changename.setText(c.getString(8));

			} else {
				showMessage("Error", "Invalid Student Id");
			}

		}
		if (view == btn_Searchname) {
			if (txteditname.getText().toString().trim().length() == 0) {
				showMessage("Error", "Please enter Student Name");
				return;
			}
			Cursor c = db.rawQuery("SELECT * FROM student WHERE name='"
					+ txteditname.getText() + "'", null);
			if (c.moveToFirst()) {
				txt_name.setText(c.getString(1));
				txt_class.setText(c.getString(2));
				txt_perntnumber.setText(c.getString(3));
				txt_case.setText(c.getString(4));
				txt_type.setText(c.getString(5));
				txt_prantname.setText(c.getString(6));
				text_bus.setText(c.getString(7));
				txt_id.setText(c.getString(0));

				// changename.setText(c.getString(8));

			} else {
				showMessage("Error", "Invalid Student Name");
			}
		}

		if (view == btn_Deletename) {
			if (txteditname.getText().toString().trim().length() == 0) {
				showMessage("Error", "Please enter Student Name");
				return;
			}
			Cursor c = db.rawQuery("SELECT * FROM student WHERE name='"
					+ txteditname.getText() + "'", null);
			if (c.moveToFirst()) {
				db.execSQL("UPDATE student SET name='" + txt_name.getText()
						+ "',marks='" + txt_class.getText() + "',phone='"
						+ txt_perntnumber.getText() + "',spcasecc='"
						+ txt_case.getText() + "',typeofstudent='"
						+ txt_type.getText() + "',drivename='"
						+ txt_prantname.getText() + "',bus='"
						+ text_bus.getText() + "' WHERE name='"
						+ txteditname.getText() + "'");
				showMessage("Success", "Data Update");
			} else {
				showMessage("Error", "Invalid Student Name");
			}
			clearText();
		}
		// /String.valueOf(spinner2.

		if (view == btn_Deleteid) {
			if (txteditid.getText().toString().trim().length() == 0) {
				showMessage("Error", "Please enter Student Id");
				return;
			}
			Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='"
					+ txteditid.getText() + "'", null);
			if (c.moveToFirst()) {
				db.execSQL("UPDATE student SET name='" + txt_name.getText()
						+ "',marks='" + txt_class.getText() + "',phone='"
						+ txt_perntnumber.getText() + "',spcasecc='"
						+ txt_case.getText() + "',typeofstudent='"
						+ txt_type.getText() + "',drivename='"
						+ txt_prantname.getText() + "',bus='"
						+ text_bus.getText() + "' WHERE rollno='"
						+ txteditid.getText() + "'");
				showMessage("Success", "Data Update");
			} else {
				showMessage("Error", "Invalid Student id");
			}
			clearText();
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
		txt_name.setText("");
		txt_class.setText("");
		txt_perntnumber.setText("");
		txt_case.setText("");
		txt_type.setText("");
		txt_name.setText("");
		text_bus.setText("");
		txt_prantname.setText("");
		// editRollno.requestFocus();
	}
}