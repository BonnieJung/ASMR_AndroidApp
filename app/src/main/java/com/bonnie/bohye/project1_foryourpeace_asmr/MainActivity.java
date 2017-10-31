package com.bonnie.bohye.project1_foryourpeace_asmr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnCurrent;
    Button btnOcean;
    Button btnForest;
    Button btnThunder;
    Button btnFire;
    Button btnBamboo;
    Button btnRain;
    ArrayList<Button> btnList;
    MediaPlayer mpOcean;
    MediaPlayer mpForest;
    MediaPlayer mpThunder;
    MediaPlayer mpFire;
    MediaPlayer mpBamboo;
    MediaPlayer mpRain;
    ArrayList<MediaPlayer> mpList;
    boolean isMusicOn = false;
    TextView tv;
    String myASMR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCurrent = (Button)findViewById(R.id.btnReport);

        //asmrs
        mpOcean = MediaPlayer.create(this, R.raw.ocean);
        mpForest = MediaPlayer.create(this, R.raw.forestbird);
        mpThunder = MediaPlayer.create(this, R.raw.thunder);
        mpFire = MediaPlayer.create(this, R.raw.campfire);
        mpBamboo = MediaPlayer.create(this, R.raw.bambooflute);
        mpRain = MediaPlayer.create(this, R.raw.rain);
        mpList = new ArrayList<MediaPlayer>(Arrays.asList(mpOcean, mpForest, mpThunder, mpFire, mpBamboo, mpRain));

        //buttons
        btnOcean=(Button)findViewById(R.id.btnOcean);
        btnForest=(Button)findViewById(R.id.btnForest);
        btnThunder=(Button)findViewById(R.id.btnThunder);
        btnFire=(Button)findViewById(R.id.btnFire);
        btnBamboo=(Button)findViewById(R.id.btnBamboo);
        btnRain=(Button)findViewById(R.id.btnRain);
        btnList = new ArrayList<Button>(Arrays.asList(btnOcean, btnForest, btnThunder, btnFire, btnBamboo, btnRain));

        //restore preference
        SharedPreferences settings = getSharedPreferences("asmrPref", Context.MODE_PRIVATE);
        myASMR = settings.getString("song", "Ocean");
        btnCurrent.setText("You Were In The Zone Of " + myASMR);
    }

    public void onClickAbout(View view) {
        Intent i = new Intent(this, AboutActivity.class);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //we need an Editor object to make preference change
        //all objects are from android.context content
        SharedPreferences settings = getSharedPreferences("asmrPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("song", myASMR);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //restore preference
        SharedPreferences settings = getSharedPreferences("asmrPref", Context.MODE_PRIVATE);
        myASMR = settings.getString("song", "Ocean");
        //otherButtonsGrayedOut(findMatchingButton(myASMR));
    }

    Button findMatchingButton(String s){
        Button btn = (Button)findViewById(R.id.btnOcean);
        switch (s){
            case "Ocean":
                btn = (Button)findViewById(R.id.btnOcean);
                break;
            case "Forest":
                btn = (Button)findViewById(R.id.btnForest);
                break;
            case "Thunder":
                btn = (Button)findViewById(R.id.btnThunder);
                break;
            case "Fire":
                btn = (Button)findViewById(R.id.btnFire);
                break;
            case "Bamboo":
                btn = (Button)findViewById(R.id.btnBamboo);
                break;
            case "Rain":
                btn = (Button)findViewById(R.id.btnRain);
                break;
        }
        return btn;
    }

    public void onClickMusic(View view) {
        switch(view.getId())
        {
            case R.id.btnOcean:
                decideOnOff(mpOcean, btnOcean);
                myASMR = "Ocean";
                break;
            case R.id.btnForest:
                decideOnOff(mpForest, btnForest);
                myASMR = "Forest";
                break;
            case R.id.btnThunder:
                decideOnOff(mpThunder, btnThunder);
                myASMR = "Thunder";
                break;
            case R.id.btnFire:
                decideOnOff(mpFire, btnFire);
                myASMR = "Fire";
                break;
            case R.id.btnBamboo:
                decideOnOff(mpBamboo, btnBamboo);
                myASMR = "Bamboo";
                break;
            case R.id.btnRain:
                decideOnOff(mpRain, btnRain);
                myASMR = "Rain";
                break;
            default:
                decideOnOff(mpOcean, btnOcean);
                myASMR = "Ocean";
                break;
        }
        btnCurrent.setText("You are in the zone of " + myASMR);
    }

    public void onClickReport(View view) {
        //open song choice activity
        Intent i = new Intent(this, ReportActivity.class);
        i.putExtra("songBundle", myASMR);
        i.putExtra("currentMsg", btnCurrent.getText().toString());
        startActivity(i);
    }

    public void otherButtonsGrayedOut(Button curr){
        for(Button btn: btnList){
            btn.setEnabled(false);
        }
        curr.setEnabled(true);
    }

    public void everyButtonActive(){
        for(Button btn: btnList){
            btn.setEnabled(true);
        }
    }

    //helper
    void decideOnOff(MediaPlayer mp, Button btn){
        if(!isMusicOn) {
            stopOtherMusics();
            playMusic(mp);
            otherButtonsGrayedOut(btn);
            btnCurrent.setText("You are in the zone of " + myASMR);
        }
        else{
            pauseMusic(mp);
            everyButtonActive();
            btnCurrent.setText("You were in the zone of " + myASMR);
        }
    }

    void playMusic(MediaPlayer mp)
    {   // set the media player to the song, and start music
        if(!mp.isPlaying()) {
            mp.start();
            isMusicOn = true;
        }
    }

    void pauseMusic(MediaPlayer mp)
    {   // pause the music if playing
        if(mp.isPlaying()){
            mp.pause();
            isMusicOn = false;
        }
    }

    void stopOtherMusics(){
        for(MediaPlayer mpMember: mpList){
            if(mpMember.isPlaying()){
                mpMember.pause();
                isMusicOn = false;
            }
        }
    }
}
