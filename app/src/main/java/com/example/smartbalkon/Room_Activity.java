package com.example.smartbalkon;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import java.util.Objects;

public class Room_Activity extends AppCompatActivity {

    final String TAG = "LifeCycle";

        /*############################ЗАПУСК####################################*/
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            Objects.requireNonNull(getSupportActionBar()).hide();
            Log.d(TAG,"LightActivity.onCreate");
            Objects.requireNonNull(getSupportActionBar()).hide();
            setContentView(R.layout.activity_room);
        }

        /*############################РАБОТА####################################*/
        @Override
        public void onResume() {
            super.onResume();
            IntStart();
            Log.d(TAG,"LightActivity.onResume");
        }

        /*############################СТОПЭ####################################*/
        @Override
        public void onStop() {
            super.onStop();
            Log.d(TAG,"LightActivity.onStop");
        }

        /*############################УБЕЙСЯ###################################*/
        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG,"LightActivity.onDestroy");
        }


        /*############################ИНТЕРФЕЙС###################################*/



        /*################Старт интерфейса######################*/
        private void IntStart(){
            ButBackToMain = findViewById(R.id.butBackRoom);
            ButLightRoom = findViewById(R.id.butLightR);
            SeekRoom = findViewById(R.id.seekR);
            ButBackToMain.setOnClickListener(OclBtnL);
            ButLightRoom.setOnCheckedChangeListener(SWRList);
            SeekRoom.setOnSeekBarChangeListener(SeekRList);
        }

        /*################Обработка кнопок######################*/
        Button ButBackToMain;
        Button.OnClickListener OclBtnL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.butBackRoom) {
                    Intent MainRun = new Intent(Room_Activity.this, MainActivity.class);
                    startActivity(MainRun);
                    finish();
                }

            }
        };

        /*################Обработка переключателей######################*/
        ToggleButton  ButLightRoom;

        ToggleButton.OnCheckedChangeListener SWRList = new ToggleButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.BtSend("3:1=1;");
                    Log.d(TAG, "3:1=1;");
                }
                else
                {
                    MainActivity.BtSend("3:1=0;");
                    Log.d(TAG,"3:1=0;");
                }
            }
        };

        /*################Обработка ползунков######################*/
        SeekBar SeekRoom;
        public String SeekVa1R;
        //Комната
        SeekBar.OnSeekBarChangeListener SeekRList = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SeekVa1R = String.valueOf(SeekRoom.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SeekVa1R = ("3:2="+SeekVa1R+";");
                MainActivity.BtSend(SeekVa1R);
            }
        };


}