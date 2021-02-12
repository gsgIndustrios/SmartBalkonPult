package com.example.smartbalkon;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;
import java.util.Objects;


public class Balkon_Activity extends AppCompatActivity {
    final String TAG = "LifeCycle";
    /*############################ЗАПУСК####################################*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Log.d(TAG,"BalkonActivity.onCreate");
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_balkon);
    }

    /*############################РАБОТА####################################*/
    @Override
    public void onResume() {
        super.onResume();
        IntStart();
        Log.d(TAG,"BalkonActivity.onResume");
    }

    /*############################СТОПЭ####################################*/
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"BalkonActivity.onStop");
    }

    /*############################УБЕЙСЯ###################################*/
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"BalkonActivity.onDestroy");
    }


    /*############################ИНТЕРФЕЙС###################################*/



    /*################Старт интерфейса######################*/
    private void IntStart(){


        ButBackToMain = findViewById(R.id.butBackB);
        ButSound = findViewById(R.id.butSound);
        ButBackToMain.setOnClickListener(OclBtn);
        ButSound.setOnClickListener(OclBtn);
        Seek1 = findViewById(R.id.seekDim1);
        Seek2 = findViewById(R.id.seekDim2);
        Seek3 = findViewById(R.id.seekDim3);
        Seek4 = findViewById(R.id.seekDim4);
        Seek5 = findViewById(R.id.seekDim5);
        Seek1.setOnSeekBarChangeListener(Seek1List);
        Seek2.setOnSeekBarChangeListener(Seek2List);
        Seek3.setOnSeekBarChangeListener(Seek3List);
        Seek4.setOnSeekBarChangeListener(Seek4List);
        Seek5.setOnSeekBarChangeListener(Seek5List);

        ButLightDim1 = findViewById(R.id.butDimSw1);
        ButLightDim2 = findViewById(R.id.butDimSw2);
        ButLightDim3 = findViewById(R.id.butDimSw3);
        ButLightDim4 = findViewById(R.id.butDimSw4);
        ButLightDim5 = findViewById(R.id.butDimSw5);
        ButLightDim1.setOnCheckedChangeListener(SWDim1List);
        ButLightDim2.setOnCheckedChangeListener(SWDim2List);
        ButLightDim3.setOnCheckedChangeListener(SWDim3List);
        ButLightDim4.setOnCheckedChangeListener(SWDim4List);
        ButLightDim5.setOnCheckedChangeListener(SWDim5List);

        ButLightSw1 = findViewById(R.id.butSw1);
        ButLightSw2 = findViewById(R.id.butSw2);
        ButLightSw3 = findViewById(R.id.butSw3);
        ButLightSw4 = findViewById(R.id.butSw4);
        ButLightSw5 = findViewById(R.id.butSw5);
        ButLightSw6 = findViewById(R.id.butSw6);
        ButLightSw7 = findViewById(R.id.butSw7);
        ButLightSw8 = findViewById(R.id.butSw8);
        ButLightSw1.setOnCheckedChangeListener(SWR1List);
        ButLightSw2.setOnCheckedChangeListener(SWR2List);
        ButLightSw3.setOnCheckedChangeListener(SWR3List);
        ButLightSw4.setOnCheckedChangeListener(SWR4List);
        ButLightSw5.setOnCheckedChangeListener(SWR5List);
        ButLightSw6.setOnCheckedChangeListener(SWR6List);
        ButLightSw7.setOnCheckedChangeListener(SWR7List);
        ButLightSw8.setOnCheckedChangeListener(SWR8List);

    }

    /*################Обработка кнопок######################*/
    Button ButBackToMain,ButSound;
    Button.OnClickListener OclBtn = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.butBackB) {
                Intent gRun = new Intent(Balkon_Activity.this, MainActivity.class);
                startActivity(gRun);
                finish();
            }
            else if (v.getId() == R.id.butSound) {
                Intent SoundRun = new Intent(Balkon_Activity.this, Sound_Activity.class);
                startActivity(SoundRun);
                finish();
            }

        }
    };

    /*################Обработка переключателей######################*/
    //Выключатели диммеров
    ToggleButton ButLightDim1, ButLightDim2, ButLightDim3, ButLightDim4, ButLightDim5;
    //Выключатели реле
    ToggleButton ButLightSw1, ButLightSw2, ButLightSw3, ButLightSw4, ButLightSw5, ButLightSw6, ButLightSw7, ButLightSw8;


//Переключатели Диммеров
    //Диммер 1
    ToggleButton.OnCheckedChangeListener SWDim1List = new ToggleButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                SeekVa11 = ("9:1="+SeekVa11+";");
                MainActivity.BtSend(SeekVa11);
                Log.d(TAG, "9:1=1;");
            }
            else
            {
                MainActivity.BtSend("9:1=0;");
                Log.d(TAG,"9:1=0;");
            }
        }
    };

    //Диммер 2
    ToggleButton.OnCheckedChangeListener SWDim2List = new ToggleButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                SeekVa12 = ("9:2="+SeekVa12+";");
                MainActivity.BtSend(SeekVa12);
                Log.d(TAG, "9:2=1;");
            }
            else
            {
                MainActivity.BtSend("9:2=0;");
                Log.d(TAG,"9:2=0;");
            }
        }
    };

    //Диммер 3
    ToggleButton.OnCheckedChangeListener SWDim3List = new ToggleButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                SeekVa13 = ("9:3="+SeekVa13+";");
                MainActivity.BtSend(SeekVa13);
                Log.d(TAG, "9:3=1;");
            }
            else
            {
                MainActivity.BtSend("9:3=0;");
                Log.d(TAG,"9:3=0;");
            }
        }
    };

    //Диммер 4
    ToggleButton.OnCheckedChangeListener SWDim4List = new ToggleButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                SeekVa14 = ("9:4="+SeekVa14+";");
                MainActivity.BtSend(SeekVa14);
                Log.d(TAG, "9:4=1;");
            }
            else
            {
                MainActivity.BtSend("9:4=0;");
                Log.d(TAG,"9:4=0;");
            }
        }
    };

    //Диммер 5
    ToggleButton.OnCheckedChangeListener SWDim5List = new ToggleButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                SeekVa15 = ("9:5="+SeekVa15+";");
                MainActivity.BtSend(SeekVa15);
                Log.d(TAG, "9:5=1;");
            }
            else
            {
                MainActivity.BtSend("9:5=0;");
                Log.d(TAG,"9:5=0;");
            }
        }
    };
    //Реле 1
    ToggleButton.OnCheckedChangeListener SWR1List = new ToggleButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                MainActivity.BtSend("8:1=1;");
                Log.d(TAG, "8:1=1;");
            }
            else
            {
                MainActivity.BtSend("8:1=0;");
                Log.d(TAG,"8:1=0;");
            }
        }
    };

    //Реле 2
    ToggleButton.OnCheckedChangeListener SWR2List = new ToggleButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                MainActivity.BtSend("8:2=1;");
                Log.d(TAG, "8:2=1;");
            }
            else
            {
                MainActivity.BtSend("8:2=0;");
                Log.d(TAG,"8:2=0;");
            }
        }
    };

    //Реле 3
    ToggleButton.OnCheckedChangeListener SWR3List = new ToggleButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                MainActivity.BtSend("8:3=1;");
                Log.d(TAG, "8:3=1;");
            }
            else
            {
                MainActivity.BtSend("8:3=0;");
                Log.d(TAG,"8:3=0;");
            }
        }
    };

    //Реле 4
    ToggleButton.OnCheckedChangeListener SWR4List = new ToggleButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                MainActivity.BtSend("8:4=1;");
                Log.d(TAG, "8:4=1;");
            }
            else
            {
                MainActivity.BtSend("8:4=0;");
                Log.d(TAG,"8:4=0;");
            }
        }
    };

    //Реле 5
    ToggleButton.OnCheckedChangeListener SWR5List = new ToggleButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                MainActivity.BtSend("8:5=1;");
                Log.d(TAG, "8:5=1;");
            }
            else
            {
                MainActivity.BtSend("8:5=0;");
                Log.d(TAG,"8:5=0;");
            }
        }
    };

    //Реле 6
    ToggleButton.OnCheckedChangeListener SWR6List = new ToggleButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                MainActivity.BtSend("8:6=1;");
                Log.d(TAG, "8:6=1;");
            }
            else
            {
                MainActivity.BtSend("8:6=0;");
                Log.d(TAG,"8:6=0;");
            }
        }
    };

    //Реле 7
    ToggleButton.OnCheckedChangeListener SWR7List = new ToggleButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                MainActivity.BtSend("8:7=1;");
                Log.d(TAG, "8:7=1;");
            }
            else
            {
                MainActivity.BtSend("8:7=0;");
                Log.d(TAG,"8:7=0;");
            }
        }
    };

    //Реле 8
    ToggleButton.OnCheckedChangeListener SWR8List = new ToggleButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                MainActivity.BtSend("8:8=1;");
                Log.d(TAG, "8:8=1;");
            }
            else
            {
                MainActivity.BtSend("8:8=0;");
                Log.d(TAG,"8:8=0;");
            }
        }
    };


    /*################Обработка ползунков######################*/
    SeekBar Seek1,Seek2,Seek3,Seek4,Seek5;
    public String SeekVa11,SeekVa12,SeekVa13,SeekVa14,SeekVa15;
    //Ползун1
    SeekBar.OnSeekBarChangeListener Seek1List = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            SeekVa11 = String.valueOf(Seek1.getProgress());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            SeekVa11 = ("9:1="+SeekVa11+";");
            MainActivity.BtSend(SeekVa11);
        }
    };
    //Ползун2
    SeekBar.OnSeekBarChangeListener Seek2List = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            SeekVa12 = String.valueOf(Seek2.getProgress());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            SeekVa12 = ("9:2="+SeekVa12+";");
            MainActivity.BtSend(SeekVa12);
        }
    };
    //Ползун3
    SeekBar.OnSeekBarChangeListener Seek3List = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            SeekVa13 = String.valueOf(Seek3.getProgress());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            SeekVa13 = ("9:3="+SeekVa13+";");
            MainActivity.BtSend(SeekVa13);
        }
    };
    //Ползун4
    SeekBar.OnSeekBarChangeListener Seek4List = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            SeekVa14 = String.valueOf(Seek4.getProgress());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            SeekVa14 = ("9:4="+SeekVa14+";");
            MainActivity.BtSend(SeekVa14);
        }
    };
    //Ползун5
    SeekBar.OnSeekBarChangeListener Seek5List = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            SeekVa15 = String.valueOf(Seek5.getProgress());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            SeekVa15 = ("9:5="+SeekVa15+";");
            MainActivity.BtSend(SeekVa15);
        }
    };


}