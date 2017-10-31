package com.bonnie.bohye.project1_foryourpeace_asmr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ReportActivity extends AppCompatActivity {
    TextView tvLabel;
    ImageView iv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        tvLabel = (TextView)findViewById(R.id.tvLabel);
        tvLabel.setText(getIntent().getExtras().getString("currentMsg"));
        iv = (ImageView)findViewById(R.id.imageView);
        tv = (TextView)findViewById(R.id.tvCurrentASMR);
        String s = getIntent().getExtras().getString("songBundle");
        matchPictureAndText(s);
    }

    void matchPictureAndText(String s){
        switch (s){
            case "Ocean":
                iv.setImageResource(R.drawable.ocean);
                break;
            case "Forest":
                iv.setImageResource(R.drawable.forest);
                break;
            case "Thunder":
                iv.setImageResource(R.drawable.thunderstorm);
                break;
            case "Fire":
                iv.setImageResource(R.drawable.campfire);
                break;
            case "Bamboo":
                iv.setImageResource(R.drawable.bambooforest);
                break;
            case "Rain":
                iv.setImageResource(R.drawable.rain);
                break;
        }
        tv.setText("Feel fully calm in the spirit of " + s);
    }


    public void onClickGoBack(View view) {
        finish();
    }
}
