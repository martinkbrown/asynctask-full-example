package mbrown.example.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AsyncTaskFullExampleActivity extends Activity {

	EditText tv;
	Button button;
	EditText start;
	EditText end;
	Button toaster;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		tv = (EditText) findViewById(R.id.editText1);
		button = (Button) findViewById(R.id.startButton);
		start = (EditText) findViewById(R.id.editText2);
		end = (EditText) findViewById(R.id.editText3);
		toaster = (Button) findViewById(R.id.toaster);
		
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				int startNo = Integer.parseInt(start.getText().toString());
				int endNo = Integer.parseInt(end.getText().toString());
				
				new UpdateTask().execute(startNo, endNo);
			}
		});
		
		toaster.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Toast.makeText(getApplicationContext(), "Toaster!", Toast.LENGTH_SHORT).show();
				
			}
		});

	}

	class UpdateTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {

			int i;
			for(i = params[0]; i < params[1]; i++) {
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(i%2 == 0) {
					publishProgress(i);
				}
			}

			return i;
		}

		protected void onProgressUpdate(Integer... progress) {
			
			final int prog = progress[0];
			
			runOnUiThread(new Runnable() {
				public void run() {
					tv.setText("" + prog);
				}
			});
			
		}

		protected void onPostExecute(Integer result) {
			
			final int res = result;
			
			runOnUiThread(new Runnable() {
				public void run() {
					tv.setText("" + res);
					Toast.makeText(getApplicationContext(), "Work complete", Toast.LENGTH_SHORT).show();
				}
			});
			
		}

	}
}