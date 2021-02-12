//<uses-feature android:name="android.hardware.type.watch" />
//<uses-permission android:name="android.permission.WAKE_LOCK" />

package com.example.smartbalkon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    /*#########################################ОТЛАДКА###################################################*/
    final static String TAG = "LifeCycle";

    /*МЕТОДЫ*/
    //Старт интерфейса
    /*###############################ИНТЕРФЕЙС - ПЕРЕМЕННЫЕ ФУНКЦИИ И МЕТОДЫ#############################*/
    /*ПЕРЕМЕННЫЕ*/
    //Кнопки
    public Button ButDop, ButBalk, ButRoom, ButCt, ButCls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        Log.d(TAG,"MainActivity.onCreate");
        //СТАРТ ИНТЕРФЕЙС
        /*#######################ИНТЕРФЕЙС###########################*/

        ButCls = findViewById(R.id.ButClose);
        ButCt = findViewById(R.id.ButConnect);
        ButDop = findViewById(R.id.ButDop);
        ButBalk = findViewById(R.id.ButBalkon);
        ButRoom = findViewById(R.id.ButRoom);
        ButDop.setOnClickListener(OclBtn);
        ButBalk.setOnClickListener(OclBtn);
        ButRoom.setOnClickListener(OclBtn);
        ButCls.setOnClickListener(OclBtn);
        ButCt.setOnClickListener(OclBtn);


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
                case R.id.ButRoom:
                    //Toast.makeText(getBaseContext(), "ButLight", Toast.LENGTH_LONG).show();
                    Intent RoomRun = new Intent(MainActivity.this, Room_Activity.class);
                    startActivity(RoomRun);
                    break;
                case R.id.ButBalkon:
                    //Toast.makeText(getBaseContext(), "ButSound", Toast.LENGTH_LONG).show();
                    Intent BalkonRun = new Intent(MainActivity.this, Balkon_Activity.class);
                    startActivity(BalkonRun);
                    break;
                case R.id.ButDop:
                    //Toast.makeText(getBaseContext(), "ButRadio", Toast.LENGTH_LONG).show();
                    Intent RadioRun = new Intent(MainActivity.this, Dop_Activity.class);
                    startActivity(RadioRun);
                    break;
                case R.id.ButClose:
                    BtClose();
                    onDestroy();
                    finish();
                    break;
                case R.id.ButConnect:
                    //ЗАПУСК BLUETOOTH
                    if (BtOn){
                        BtClose();
                    }
                    else {
                        BtStart();
                        BtDrive(macAddress);
                        onResume();
                    }
                    break;
                default:
                    break;
            }
        }
    };
    /*###########################БЛЮПУП - ПЕРЕМЕННЫЕ ФУНКЦИИ И МЕТОДЫ#############################*/
    //МАСКА ПРИЕМА: (УС-ВО):(ИМЯ ПЕРЕМЕННОЙ).(ЗНАЧЕНИЕ)
    //МАСКА ОТПРАВКИ (УС-ВО):(ИМЯ ПЕРЕМЕННОЙ).(ЗНАЧЕНИЕ)

    /*ПЕРЕМЕННЫЕ*/
    public boolean BtOn=false;
    //public static SharedPreferences sPref;
    private static final int REQUEST_ENABLE_BT = 1;
    public BluetoothAdapter btAdapter;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static BluetoothThread BtThread = null;
    public String macAddress = "20:16:10:24:91:21";

    //Прием
    private static final int RECIVE_MESSAGE = 1;
    Handler BtHandler;
    String msg_IN;

    /*МЕТОДЫ*/
    //Сохранение прирятой переменной
    /*private void saveVar(String name, String Value){
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editing = sPref.edit();
        editing.putString(name,Value);
        editing.apply();
    }
*/
    /*############################СТАРТ####################################*/
    @SuppressLint("HandlerLeak")
    public void BtStart() {
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) {
            Log.d(TAG,"***Нет БЛЮПУПА***");
        }
        else {
            if (btAdapter.isEnabled()) {
                BtDrive(macAddress);
            } else {
                //Включить bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                BtDrive(macAddress);
            }
        }
    }
    /*############################РАБОТА####################################*/
    @SuppressLint("HandlerLeak")
    public void BtDrive(String MacAdress) {
        BluetoothDevice device = btAdapter.getRemoteDevice(MacAdress);
        BluetoothSocket btSocket;
        try {
            btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
            Log.d(TAG,"***Создан сокет: "+btSocket+"***");
        } catch (IOException e) {
            Log.d(TAG,"***Не могу создать сокет***");
            BtOn=false;
            return;
        }
        btAdapter.cancelDiscovery();
        try {
            btSocket.connect();
        } catch (IOException e) {
            Log.d(TAG,"***Не коннектится***");
            BtOn=false;
            return;
        }
        Log.d(TAG,"***Соединился***");
        BtThread = new BluetoothThread(btSocket);
        BtThread.start();
        Log.d(TAG,"***BtThread.start***");
        BtHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                //txtArduino.setText(msg_exemple);
                Log.d(TAG,"***"+msg_IN+"***");
                char[] InMsg = msg_IN.toCharArray();
                //Log.d(TAG,InMsg.toString());
                StringBuilder MSG = new StringBuilder();
                String Name = "";
                String Value;
                int Lenght = InMsg.length;
                for (int i = 0; Lenght>i; i++){
                    char b = InMsg[i];
                    if(b=='.'){
                        Name = MSG.toString();
                        MSG = new StringBuilder();
                        b = 0;
                    }
                    else if (b==';'){
                        Value = MSG.toString();
                        MSG = new StringBuilder();
                        //InputRef (Name, Value);
                        //saveVar(Name,Value);
                        Log.d(TAG,"***IN:"+Name+' '+Value+"***");
                    }
                    else {
                        MSG.append(b);
                    }
                }
            }
        };
        BtOn=true;
    }
    /*############################ПОСЫЛКА##################################*/
    public static void BtSend(String message) {
        Log.d(TAG,message);
        byte[] msgBuffer = message.getBytes();
        try {
            OutStream.write(msgBuffer);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e){
                Log.d(TAG,"***Ошибка приостановки потока***");
            }

        } catch (IOException e) {
            Log.d(TAG,"***Bt неисправен***");
        }

    }

    /*############################ВЫКЛЮЧИТЬ BLUETOOTH##################################*/
    public void BtClose(){
        if (BtThread.status_OutStream() != null) {
            BtSend("OutS.1;");
            try {
                MyBtSocket.close();
            } catch (IOException e) {
                Log.d(TAG,"***Не могу закрыть сокет***");
                return;
            }
        }

        Log.d(TAG,"***Cокет закрыт***");
    }

    /*ОТДЕЛЬНЫЙ ПОТОК ДЛЯ ПЕРЕДАЧИ/ПРИЕМА*/
    public BluetoothSocket MyBtSocket;
    public static OutputStream OutStream;
    public InputStream InStream;
    public class BluetoothThread extends Thread {

        BluetoothThread(BluetoothSocket socket) {
            MyBtSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException ignored) {
            }
            InStream = tmpIn;
            OutStream = tmpOut;
        }
        Object status_OutStream() {
            if (OutStream == null) {
                return null;
            } else {
                return OutStream;
            }
        }
        public void run() {
            while (true){
                try {
                    int bytes = InStream.available();
                    if (bytes == 0) {
                        try {
                            Thread.sleep(350);
                        } catch (InterruptedException e){
                            Log.d(TAG,"***Ошибка приостановки потока***");
                        }
                        continue;
                    }
                    byte[] buffer = new byte[bytes];
                    bytes = InStream.read(buffer);
                    msg_IN = new String(buffer);
                    BtHandler.obtainMessage(RECIVE_MESSAGE,bytes,-1,buffer).sendToTarget();
                }catch (IOException e){
                    Log.d(TAG,"***Ошибка буфера***");
                    break;
                }
            }
        }

    }

}