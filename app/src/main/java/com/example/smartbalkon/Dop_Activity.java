package com.example.smartbalkon;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class Dop_Activity extends AppCompatActivity {
    final static String TAG = "LifeCycle";

    public Button ButBackD, ButDiskoH, ButDiskoB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_dop);
        //СТАРТ ИНТЕРФЕЙС
        /*#######################ИНТЕРФЕЙС###########################*/

        ButBackD = findViewById(R.id.butBackD);
        ButDiskoH = findViewById(R.id.butDiskoH);
        ButDiskoB = findViewById(R.id.butDiskoB);
        ButBackD.setOnClickListener(OclBtn);
        ButDiskoH.setOnClickListener(OclBtn);
        ButDiskoB.setOnClickListener(OclBtn);

    }
    //РАБОТА
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"MainActivity.onResume");
    }
    //СТОПЭ
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"MainActivity.onStop");
    }

    //УБЕЙСЯ

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"MainActivity.onDestroy");
    }
    @Override
    public void onRestart(){
        super.onRestart();

        Log.d(TAG,"MainActivity.onRestart");
    }

    Button.OnClickListener OclBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.butBackD:
                    //Toast.makeText(getBaseContext(), "ButLight", Toast.LENGTH_LONG).show();
                    Intent MainBack = new Intent(Dop_Activity.this, MainActivity.class);
                    startActivity(MainBack);
                    break;
                case R.id.butDiskoH:
                    //Toast.makeText(getBaseContext(), "ButSound", Toast.LENGTH_LONG).show();
                    Intent DiskoHeadRun = new Intent(Dop_Activity.this, DiscoHead_Activity.class);
                    startActivity(DiskoHeadRun);
                    break;
                case R.id.butDiskoB:
                    //Toast.makeText(getBaseContext(), "ButRadio", Toast.LENGTH_LONG).show();
                    Intent DiskoBallRun = new Intent(Dop_Activity.this, DiscoBall_Activity.class);
                    startActivity(DiskoBallRun);
                    break;
                default:
                    break;
            }
        }
    };
}