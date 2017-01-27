package school.management;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class About extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_about);

		ImageView facebook = (ImageView) findViewById(R.id.facebook);

		facebook.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// startActivity(new Intent(getApplicationContext(),
				Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri
						.parse("https://www.facebook.com/ErrStudio"));

				startActivity(openBrowser);

			}
		});
		ImageView tw = (ImageView) findViewById(R.id.tw);

		tw.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri
						.parse("https://twitter.com/ErrStudio"));

				startActivity(openBrowser);

			}
		});
		ImageView yout = (ImageView) findViewById(R.id.you);

		yout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openBrowser = new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("https://www.youtube.com/channel/UC2thdtvoZvmihLdQ1T4rgxQ"));

				startActivity(openBrowser);

			}
		});
	}

}
