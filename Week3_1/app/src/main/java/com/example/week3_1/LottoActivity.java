package com.example.week3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class LottoActivity extends AppCompatActivity {
    private TextView lottoTextView;
    private LottoBroadcastReceiver lottoBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto);
        lottoTextView = findViewById(R.id.lottoTextView);
        lottoBroadcastReceiver = new LottoBroadcastReceiver();
        registerReceiver(lottoBroadcastReceiver, new IntentFilter("com.tamk.lotto"));
    }

    public void arvo(View view) {
        Intent intent = new Intent( this, MyLottoService.class);

        intent.putExtra("LOTTO_NUMBERS", 7);
        startService(intent);
    }
    class LottoBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive (Context context, Intent intent){
            System.out.println("INSIDE LOTTOBROADCASTRECEIVER ONRECEIVE");
            if ( intent.getAction() == "com.tamk.lotto"){
                int[] numero = intent.getIntArrayExtra("LOTTONUMERO");
                System.out.println("NUMERO ARRAY");
                System.out.println(numero);
                String lottostring = "";
                for(int i = 0; i< numero.length; i++){
                    System.out.println("ARRAY " + i + " = " + numero[i]);
                    lottostring = lottostring + numero[i] + " ";
                }
                lottoTextView.setText("Numero " + lottostring);
            }
        }
    }
}