package com.spencero.example.generalHttpRequest;

import com.spencero.example.generalHttpRequest.GeneralHttpTask.OnResponseListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GeneralHTTPRequestExampleActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button button = (Button) findViewById(R.id.submit);
		button.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v)
			{
				sendRequest();
			}
		});
		
		sendRequest();
	}
	
	private void sendRequest()
	{
		GeneralHttpTask recoveryTask = new GeneralHttpTask(
				GeneralHTTPRequestExampleActivity.this, 
				"Sending...",
				onResponseListener );
		String url = "http://www.256design.com/";
		recoveryTask.execute(url, "200");
	}
	
    protected OnResponseListener onResponseListener = new OnResponseListener() {
		
    	public void onSuccess() {
			AlertDialog.Builder builder = new AlertDialog.Builder(GeneralHTTPRequestExampleActivity.this);
			builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			builder.setMessage("Success");
			builder.show();
		}
		
		public void onFailure(String message) {
			AlertDialog.Builder builder = new AlertDialog.Builder(GeneralHTTPRequestExampleActivity.this);
			builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			builder.setMessage("Failure. Message: " + message);
			builder.show();
		}
	};
}