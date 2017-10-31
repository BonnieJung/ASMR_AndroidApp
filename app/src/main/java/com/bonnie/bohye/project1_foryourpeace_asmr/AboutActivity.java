package com.bonnie.bohye.project1_foryourpeace_asmr;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AboutActivity extends AppCompatActivity {
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        et = (EditText)findViewById(R.id.etFeedback);
    }

    public void onClickGoBack(View view) {
        finish();
    }

    public void onClickEmail(View view) {
        String [] to = {"bohye9912@gmail.com"};
        String [] cc = {"b_jung@fanshaweonline.ca"};
        String body = et.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_TEXT,body);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Feedback from ASMR app");
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent,"Email"));
    }
}
