package com.example.marek.paintactivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/*
  Created by marek on 2015-09-16.
 */
 public class ProcessingFrame extends Thread {
    final int TIME_OUT = 1;

    public byte[] readBuffer;
    public int bytes;
    public byte[] bufer = new byte[3000];
    public double[]  graph_point = new double[3000];
    public int stary_bajt;
    public int mlody_bajt;
    public int bytes_all;
    public double point;
    public int CRC;
    public int CRC_parse;
    private boolean SET_THREAD = true;
    public int  Error_count;
    protected static final int SUCCESS_CONNECT = 0;
    protected static final int DISCONECT = 2;
    public boolean flaga = false;
    private long currentTime_start = 0;
    private int counter_bytes;
    Calculculations calculations = new Calculculations();
    static Handler mHandler = new Handler();
    public static void get_handler(Handler handler){mHandler = handler;}
    Handler hHandler;{
        hHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //TODO Auto-generated method stub
                super.handleMessage(msg);
                switch (msg.what) {
                        case Bluetooth.MESSAGE_READ:
                             if (Bluetooth.connectedThread != null) {
                                 Konstruktor(msg.obj, msg.arg1);
                                 break;
                             }
                            case Bluetooth.DISCONECT:{
                                mHandler.obtainMessage(DISCONECT).sendToTarget();
                                break;
                            }
                            case Bluetooth.SUCCESS_CONNECT:{
                                mHandler.obtainMessage(SUCCESS_CONNECT).sendToTarget();
                                break;
                            }
                }
            }

        };
    }
    @Override
    public void run() {
        while(SET_THREAD){

            if ((bytes_all>=2506)) {
                Log.d("bytesall","ok");
                bytes_all = 0;
               // Error_count = calculations.convert_Byte_to_Int(bufer[0]);
                CRC = calculations.CalcCRC16(bufer, 2504);
                CRC_parse = parse_bytes(calculations.convert_Byte_to_Int(bufer[2505]), calculations.convert_Byte_to_Int(bufer[2504]));
                Log.d("CRC:", Integer.toString(CRC));
                Log.d("CRC_parse:", Integer.toString(CRC_parse));
                if(CRC==CRC_parse) {
                    int counter = 0;
                    Error_count++;
                for(int i=4;i<2504;i+=2){
                    /*if((graph_point[counter] = parse_bytes(calculations.convert_Byte_to_Int(bufer[i]),calculations.convert_Byte_to_Int(bufer[i+1]))/2048.0)>1){
                        graph_point[counter] = 0;
                    }*/
                    graph_point[counter] = parse_bytes(calculations.convert_Byte_to_Int(bufer[i]),calculations.convert_Byte_to_Int(bufer[i+1]))/2048.0;
                    Log.d("point:", Double.toString(graph_point[counter]));
                    counter++;
                    Log.d("counter",Integer.toString(i));
                }
                    flaga = true;
                }else{
                    //Error_count++;
                }


            }

        }

    }

    public ProcessingFrame(){
        Bluetooth.gethandler(hHandler);
    }
    public void Konstruktor (Object obj, int arg1){


        if(bytes_all == 0){
            currentTime_start = System.currentTimeMillis();
            Thread thdA = new Thread(r);
            thdA.start();
            Log.d("WSZEDŁ", "START");
        }
        Log.d("WSZEDŁ", "PROCESS");
        readBuffer = (byte[]) obj;
        bytes = arg1;
        System.arraycopy(readBuffer, 0, bufer, bytes_all, bytes);
        bytes_all+=bytes;

    }

    Runnable r = new Runnable()
    {
        @Override
        public void run()
        {

            while (true)
            {
                /*try
                {*/
                    //Thread.sleep(1);
                    if((System.currentTimeMillis() - currentTime_start) >= TIME_OUT){
                        Log.d("WSZEDŁ",Integer.toString(bytes_all));
                        Log.d("WSZEDŁ","DANE");
                        for(int i = 0; i < bytes_all;i++){
                            Log.d("WSZEDŁ",Byte.toString(bufer[i]));
                        }
                        counter_bytes = bytes_all;
                        bytes_all = 0;
                        Log.d("WSZEDŁ",Long.toString(System.currentTimeMillis() - currentTime_start));
                        Log.d("WSZEDŁ", "STOP");
                        break;
                    }

                //}
                /*catch (*//*InterruptedException ie*//*)
                {
                }*/
            }
        }
    };



    public int parse_bytes(int mlody, int stary) {
         return 0xFFFF&((stary << 8) | (mlody));
    }

    public double return_voltage(){

        return parse_bytes(mlody_bajt,stary_bajt)*(3.3 / 4095);
    }


    public void SET_THREAD(boolean b) {
        SET_THREAD = b;
    }
}