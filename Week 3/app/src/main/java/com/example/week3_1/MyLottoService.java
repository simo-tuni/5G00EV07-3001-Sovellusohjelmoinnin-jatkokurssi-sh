package com.example.week3_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.concurrent.ThreadLocalRandom;

public class MyLottoService extends Service {
    private  int aLottoNumberCount;
    public MyLottoService() {
    }

    @Override
    public int onStartCommand(Intent intent, int lags, int startId){
        aLottoNumberCount = intent.getIntExtra("LOTTO_NUMBERS", 7);
        System.out.println("INSIDE MYLOTTOSERVICE CLASS");
        System.out.println("LOTTO_NUMBERS: "+ aLottoNumberCount );
        /*

        */
        new Thread(()->{
            try{
                    Thread.sleep(1000);
                    int min = 1;
                    int max = 9;
                    int[] arr = new int[aLottoNumberCount];
                    for (int i = 0; i<aLottoNumberCount; i++) {
                        int randomNum = ThreadLocalRandom.current().nextInt(min, max);
                        arr[i] = randomNum;
                    }
                    System.out.println(arr);
                    Intent lottoBroadcast = new Intent("com.tamk.lotto");
                    lottoBroadcast.putExtra("LOTTONUMERO", arr);
                    sendBroadcast(lottoBroadcast);
                }
            catch(Exception e){
                System.out.println(e);
            }
        }).start();

        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}