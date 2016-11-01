package com.example.marek.paintactivity.smart_home;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;

import com.example.marek.paintactivity.Bluetooth;
import com.example.marek.paintactivity.R;
import com.example.marek.paintactivity.SendDataFrame;
import com.example.marek.paintactivity.ProcessingFrame;

public class MainActivity extends ActionBarActivity {

    static boolean fan_switch_flag          = false;
    static boolean roler_switch_sypialnia   = false;
    static boolean sypialnia_bulb1          = false;
    static boolean sypialnia_bulb2          = false;
    static boolean rolety_salon             = false;
    static boolean salon_bulb1              = false;
    static boolean salon_fan                = false;
    static boolean kuchnia_LED              = false;
    static boolean kuchnia_oswietlenieGlowne= false;
    private final  byte    MAX_DATA                = (byte) 100;
    private final  byte    MIN_DATA                = (byte) 0;

    private final byte      SYPIALNIA_MAIN_LIGHT    = 1;
    private final byte      SYPIALNIA_NIGHT_LIGHT   = 2;
    private final byte      SYPIALNIA_BLINDS        = 3;
    private final byte      SYPIALNIA_DEVICE_1      = 4;
    private final byte      SYPIALNIA_DEVICE_2      = 5;
    private final byte      SYPIALNIA_DEVICE_3      = 6;

    private final byte      SALON_MAIN_LIGHT        = 7;
    private final byte      SALON_FAN               = 8;
    private final byte      SALON_BLINDS            = 9;
    private final byte      SALON_DEVICE_1          = 10;
    private final byte      SALON_DEVICE_2          = 11;

    private final byte      KUCHNIA_MAIN_LIGHT      = 12;
    private final byte      KUCHNIA_LED             = 13;
    private final byte      KUCHNIA_FAN             = 14;
    private final byte      KUCHNIA_DEVICE_1        = 15;
    private final byte      KUCHNIA_DEVICE_2        = 16;

    private byte    SYPIALNIA_MAIN_LIGHT_DATA;
    private byte    SYPIALNIA_NIGHT_LIGHT_DATA;
    private byte    SALON_MAIN_LIGHT_DATA;
    tab1 sypialnia                          = new tab1();
    tab2 salon                              = new tab2();
    tab3 kuchnia                            = new tab3();
    SendDataFrame sendDataFrame             = new SendDataFrame();
    ProcessingFrame abc                     = new ProcessingFrame();
    public Integer[] fan={
            R.drawable.power_off,
            R.drawable.power_on,

    };
    public Integer[] bulb={
            R.drawable.off,
            R.drawable.on1,
    };
    public Integer[] rolety = {
            R.drawable.rolety_off,
            R.drawable.rolety_on,
    };



    private enum SYPIALNIA_DEVICES_ADRESS{
        MAIN_LIGHT,
        NIGHT_LIGHT
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Hide Status bar

        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new tab1()).commit();
        }
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new tab2()).commit();
        }
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new tab3()).commit();
        }


        ActionBar actionbar = getSupportActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionbar.setTitle("Smart Home");

        ActionBar.Tab Frag1Tab = actionbar.newTab().setText("Sypialnia").setIcon(R.drawable.ic_hotel_black_24dp);
        ActionBar.Tab Frag2Tab = actionbar.newTab().setText("Salon").setIcon(R.drawable.ic_live_tv_black_24dp);
        ActionBar.Tab Frag3Tab = actionbar.newTab().setText("Kuchnia").setIcon(R.drawable.ic_local_dining_black_24dp);


        Fragment sypialnia = new tab1();
        Fragment salon = new tab2();
        Fragment kuchnia = new tab3();


        Frag1Tab.setTabListener(new MyTabsListener(sypialnia));
        Frag2Tab.setTabListener(new MyTabsListener(salon));
        Frag3Tab.setTabListener(new MyTabsListener(kuchnia));


        actionbar.addTab(Frag1Tab);
        actionbar.addTab(Frag2Tab);
        actionbar.addTab(Frag3Tab);



       /* sypialnia = (tab1)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        kuchnia =  (tab3)getSupportFragmentManager().findFragmentById(R.id.fragment_container);*/
    }
    public void test(View view) {
        sypialnia = (tab1)getSupportFragmentManager().findFragmentById(R.id.fragment_container);

    }
    public void test_kuchnia(View view) {
        kuchnia =  (tab3)getSupportFragmentManager().findFragmentById(R.id.fragment_container);

    }

    public void Fan_switch(View view) {
        kuchnia = (tab3)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        kuchnia.animacja = (AnimationDrawable)kuchnia.fan_animation.getBackground();
        fan_switch_flag = !fan_switch_flag;
        if (fan_switch_flag ==false) {
            kuchnia.fan_switch.setImageResource(fan[0]);
            kuchnia.animacja.stop();
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(KUCHNIA_FAN, MIN_DATA);
            }
        }
        if (fan_switch_flag== true) {
            kuchnia.fan_switch.setImageResource(fan[1]);
            kuchnia.animacja.start();
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(KUCHNIA_FAN, MAX_DATA);
            }
        }

    }

    public void Sypialnia_rolSwitch(View view) {
        sypialnia = (tab1)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        roler_switch_sypialnia = !roler_switch_sypialnia;
        if(roler_switch_sypialnia ==false){
            sypialnia.rolety_image.setImageResource(rolety[0]);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(SYPIALNIA_BLINDS, MIN_DATA);
            }
        }
        if(roler_switch_sypialnia ==true){
            sypialnia.rolety_image.setImageResource(rolety[1]);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(SYPIALNIA_BLINDS, MAX_DATA);
            }
        }

    }

    public void sypialnia_bulb1(View view) {
        sypialnia = (tab1)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        sypialnia_bulb1 = !sypialnia_bulb1;
        if (sypialnia_bulb1 == true) {
            sypialnia.sypialnia_bulb1.setImageResource(bulb[1]);
            sypialnia.radioButton1.setChecked(true);
            sypialnia.radioButton2.setChecked(true);
            sypialnia.radioButton3.setChecked(true);
            sypialnia.radioButton4.setChecked(true);
            sypialnia.radioButton5.setChecked(true);
            sypialnia.radioButton6.setChecked(true);
            sypialnia.radioButton7.setChecked(true);
            sypialnia.radioButton8.setChecked(true);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(SYPIALNIA_MAIN_LIGHT, MAX_DATA);
            }
        }

        if (sypialnia_bulb1 == false) {
            sypialnia.sypialnia_bulb1.setImageResource(bulb[0]);
            sypialnia.radioButton1.setChecked(false);
            sypialnia.radioButton2.setChecked(false);
            sypialnia.radioButton3.setChecked(false);
            sypialnia.radioButton4.setChecked(false);
            sypialnia.radioButton5.setChecked(false);
            sypialnia.radioButton6.setChecked(false);
            sypialnia.radioButton7.setChecked(false);
            sypialnia.radioButton8.setChecked(false);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(SYPIALNIA_MAIN_LIGHT, MIN_DATA);
            }

        }

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        sypialnia = (tab1)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        sypialnia.sypialnia_bulb1.setImageResource(bulb[1]);
        sypialnia_bulb1 = true;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.level_125:
                if (checked) {
                    sypialnia.radioButton2.setChecked(false);
                    sypialnia.radioButton3.setChecked(false);
                    sypialnia.radioButton4.setChecked(false);
                    sypialnia.radioButton5.setChecked(false);
                    sypialnia.radioButton6.setChecked(false);
                    sypialnia.radioButton7.setChecked(false);
                    sypialnia.radioButton8.setChecked(false);
                    SYPIALNIA_MAIN_LIGHT_DATA = 16;

                }
                break;
            case R.id.level_250:
                if (checked)

                    sypialnia.radioButton1.setChecked(true);
                sypialnia.radioButton3.setChecked(false);
                sypialnia.radioButton4.setChecked(false);
                sypialnia.radioButton5.setChecked(false);
                sypialnia.radioButton6.setChecked(false);
                sypialnia.radioButton7.setChecked(false);
                sypialnia.radioButton8.setChecked(false);
                SYPIALNIA_MAIN_LIGHT_DATA = 28;
                break;
            case R.id.level_375:
                if (checked)
                    sypialnia.radioButton1.setChecked(true);
                sypialnia.radioButton2.setChecked(true);
                sypialnia.radioButton4.setChecked(false);
                sypialnia.radioButton5.setChecked(false);
                sypialnia.radioButton6.setChecked(false);
                sypialnia.radioButton7.setChecked(false);
                sypialnia.radioButton8.setChecked(false);
                SYPIALNIA_MAIN_LIGHT_DATA = 40;
                break;
            case R.id.level_500:
                if (checked)
                    sypialnia.radioButton1.setChecked(true);
                sypialnia.radioButton2.setChecked(true);
                sypialnia.radioButton3.setChecked(true);
                sypialnia.radioButton5.setChecked(false);
                sypialnia.radioButton6.setChecked(false);
                sypialnia.radioButton7.setChecked(false);
                sypialnia.radioButton8.setChecked(false);
                SYPIALNIA_MAIN_LIGHT_DATA = 52;
                break;
            case R.id.level_625:
                if (checked)
                    sypialnia.radioButton1.setChecked(true);
                sypialnia.radioButton2.setChecked(true);
                sypialnia.radioButton3.setChecked(true);
                sypialnia.radioButton4.setChecked(true);
                sypialnia.radioButton6.setChecked(false);
                sypialnia.radioButton7.setChecked(false);
                sypialnia.radioButton8.setChecked(false);
                SYPIALNIA_MAIN_LIGHT_DATA = 64;
                break;
            case R.id.level_750:
                if (checked)
                    sypialnia.radioButton1.setChecked(true);
                sypialnia.radioButton2.setChecked(true);
                sypialnia.radioButton3.setChecked(true);
                sypialnia.radioButton4.setChecked(true);
                sypialnia.radioButton5.setChecked(true);
                sypialnia.radioButton7.setChecked(false);
                sypialnia.radioButton8.setChecked(false);
                SYPIALNIA_MAIN_LIGHT_DATA = 76;
                break;
            case R.id.level_875:
                if (checked)
                    sypialnia.radioButton1.setChecked(true);
                sypialnia.radioButton2.setChecked(true);
                sypialnia.radioButton3.setChecked(true);
                sypialnia.radioButton4.setChecked(true);
                sypialnia.radioButton5.setChecked(true);
                sypialnia.radioButton6.setChecked(true);
                sypialnia.radioButton8.setChecked(false);
                SYPIALNIA_MAIN_LIGHT_DATA = 88;
                break;
            case R.id.level_1000:
                if (checked)
                    sypialnia.radioButton1.setChecked(true);
                sypialnia.radioButton2.setChecked(true);
                sypialnia.radioButton3.setChecked(true);
                sypialnia.radioButton4.setChecked(true);
                sypialnia.radioButton5.setChecked(true);
                sypialnia.radioButton6.setChecked(true);
                sypialnia.radioButton7.setChecked(true);
                sypialnia.radioButton8.setChecked(true);
                SYPIALNIA_MAIN_LIGHT_DATA = 100;
                break;

        }
        if (Bluetooth.connectedThread != null) {
            sendDataFrame.Request_to_Devices(SYPIALNIA_MAIN_LIGHT, SYPIALNIA_MAIN_LIGHT_DATA);
        }
    }

    public void sypialnia_bulb2(View view) {
        sypialnia = (tab1)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        sypialnia_bulb2 = !sypialnia_bulb2;
        if (sypialnia_bulb2 == true) {
            sypialnia.sypialnia_bulb2.setImageResource(bulb[1]);
            sypialnia.wskaznik_1.setChecked(true);
            sypialnia.wskaznik_2.setChecked(true);
            sypialnia.wskaznik_3.setChecked(true);
            sypialnia.wskaznik_4.setChecked(true);
            sypialnia.wskaznik_5.setChecked(true);
            sypialnia.wskaznik_6.setChecked(true);
            sypialnia.wskaznik_7.setChecked(true);
            sypialnia.wskaznik_8.setChecked(true);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(SYPIALNIA_NIGHT_LIGHT, MAX_DATA);
            }

        }

        if (sypialnia_bulb2 == false) {
            sypialnia.sypialnia_bulb2.setImageResource(bulb[0]);
            sypialnia.wskaznik_1.setChecked(false);
            sypialnia.wskaznik_2.setChecked(false);
            sypialnia.wskaznik_3.setChecked(false);
            sypialnia.wskaznik_4.setChecked(false);
            sypialnia.wskaznik_5.setChecked(false);
            sypialnia.wskaznik_6.setChecked(false);
            sypialnia.wskaznik_7.setChecked(false);
            sypialnia.wskaznik_8.setChecked(false);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(SYPIALNIA_NIGHT_LIGHT, MIN_DATA);
            }
        }

    }

    public void bulb2_radioGroup(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        sypialnia = (tab1)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        sypialnia.sypialnia_bulb2.setImageResource(bulb[1]);
        sypialnia_bulb2 = true;
        switch(view.getId()) {
            case R.id.wskaznik_1:
                if (checked)
                    sypialnia.wskaznik_2.setChecked(false);
                    sypialnia.wskaznik_3.setChecked(false);
                    sypialnia.wskaznik_4.setChecked(false);
                    sypialnia.wskaznik_5.setChecked(false);
                    sypialnia.wskaznik_6.setChecked(false);
                    sypialnia.wskaznik_7.setChecked(false);
                    sypialnia.wskaznik_8.setChecked(false);
                    SYPIALNIA_NIGHT_LIGHT_DATA = 16;
                    break;


            case R.id.wskaznik_2:
                if (checked)
                    sypialnia.wskaznik_1.setChecked(true);
                    sypialnia.wskaznik_3.setChecked(false);
                    sypialnia.wskaznik_4.setChecked(false);
                    sypialnia.wskaznik_5.setChecked(false);
                    sypialnia.wskaznik_6.setChecked(false);
                    sypialnia.wskaznik_7.setChecked(false);
                    sypialnia.wskaznik_8.setChecked(false);
                    SYPIALNIA_NIGHT_LIGHT_DATA = 28;
                    break;
            case R.id.wskaznik_3:
                if (checked)
                    sypialnia.wskaznik_1.setChecked(true);
                    sypialnia.wskaznik_2.setChecked(true);
                    sypialnia.wskaznik_4.setChecked(false);
                    sypialnia.wskaznik_5.setChecked(false);
                    sypialnia.wskaznik_6.setChecked(false);
                    sypialnia.wskaznik_7.setChecked(false);
                    sypialnia.wskaznik_8.setChecked(false);
                    SYPIALNIA_NIGHT_LIGHT_DATA = 40;
                    break;
            case R.id.wskaznik_4:
                if (checked)
                    sypialnia.wskaznik_1.setChecked(true);
                    sypialnia.wskaznik_2.setChecked(true);
                    sypialnia.wskaznik_3.setChecked(true);
                    sypialnia.wskaznik_5.setChecked(false);
                    sypialnia.wskaznik_6.setChecked(false);
                    sypialnia.wskaznik_7.setChecked(false);
                    sypialnia.wskaznik_8.setChecked(false);
                    SYPIALNIA_NIGHT_LIGHT_DATA = 52;
                    break;
            case R.id.wskaznik_5:
                if (checked)
                    sypialnia.wskaznik_1.setChecked(true);
                    sypialnia.wskaznik_2.setChecked(true);
                    sypialnia.wskaznik_3.setChecked(true);
                    sypialnia.wskaznik_4.setChecked(true);
                    sypialnia.wskaznik_6.setChecked(false);
                    sypialnia.wskaznik_7.setChecked(false);
                    sypialnia.wskaznik_8.setChecked(false);
                    SYPIALNIA_NIGHT_LIGHT_DATA = 64;
                    break;
            case R.id.wskaznik_6:
                if (checked)
                    sypialnia.wskaznik_1.setChecked(true);
                    sypialnia.wskaznik_2.setChecked(true);
                    sypialnia.wskaznik_3.setChecked(true);
                    sypialnia.wskaznik_4.setChecked(true);
                    sypialnia.wskaznik_5.setChecked(true);
                    sypialnia.wskaznik_7.setChecked(false);
                    sypialnia.wskaznik_8.setChecked(false);
                    SYPIALNIA_NIGHT_LIGHT_DATA = 76;

                    break;
            case R.id.wskaznik_7:
                if (checked)
                    sypialnia.wskaznik_1.setChecked(true);
                    sypialnia.wskaznik_2.setChecked(true);
                    sypialnia.wskaznik_3.setChecked(true);
                    sypialnia.wskaznik_4.setChecked(true);
                    sypialnia.wskaznik_5.setChecked(true);
                    sypialnia.wskaznik_6.setChecked(true);
                    sypialnia.wskaznik_8.setChecked(false);
                    SYPIALNIA_NIGHT_LIGHT_DATA = 88;
                    break;
            case R.id.wskaznik_8:
                if (checked)
                    sypialnia.wskaznik_1.setChecked(true);
                    sypialnia.wskaznik_2.setChecked(true);
                    sypialnia.wskaznik_3.setChecked(true);
                    sypialnia.wskaznik_4.setChecked(true);
                    sypialnia.wskaznik_5.setChecked(true);
                    sypialnia.wskaznik_6.setChecked(true);
                    sypialnia.wskaznik_7.setChecked(true);
                    sypialnia.wskaznik_8.setChecked(true);
                    SYPIALNIA_NIGHT_LIGHT_DATA = 100;
                    break;
        }
        if (Bluetooth.connectedThread != null) {
            sendDataFrame.Request_to_Devices(SYPIALNIA_NIGHT_LIGHT, SYPIALNIA_NIGHT_LIGHT_DATA);
        }
    }

    public void urzadzenie1_sypialnia(View view) {
        sypialnia = (tab1) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (Bluetooth.connectedThread != null) {
            if (sypialnia.sypialnia_device1.isChecked()) {
                    sendDataFrame.Request_to_Devices(SYPIALNIA_DEVICE_1, MAX_DATA);
            }
            if (sypialnia.sypialnia_device1.isChecked() == false) {
                    sendDataFrame.Request_to_Devices(SYPIALNIA_DEVICE_1, MIN_DATA);
            }
        }
    }

    public void urzadzenie2_sypialnia(View view) {
        sypialnia = (tab1)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (Bluetooth.connectedThread != null) {
            if (sypialnia.sypialnia_device2.isChecked()) {
                    sendDataFrame.Request_to_Devices(SYPIALNIA_DEVICE_2, MAX_DATA);
            }else {
                    sendDataFrame.Request_to_Devices(SYPIALNIA_DEVICE_2, MIN_DATA);
            }
        }
    }


    public void urzadzenie3_sypialnia(View view) {
        sypialnia = (tab1)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (Bluetooth.connectedThread != null) {
            if (sypialnia.sypialnia_device3.isChecked()) {
                sendDataFrame.Request_to_Devices(SYPIALNIA_DEVICE_3, MAX_DATA);
            }else {
                sendDataFrame.Request_to_Devices(SYPIALNIA_DEVICE_3, MIN_DATA);
            }
        }
    }

    public void urzadzenie1_salon(View view) {
        salon = (tab2) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (Bluetooth.connectedThread != null) {
            if (salon.salon_device1.isChecked()) {
                sendDataFrame.Request_to_Devices(SALON_DEVICE_1, MAX_DATA);
            }
            if (salon.salon_device1.isChecked() == false) {
                sendDataFrame.Request_to_Devices(SALON_DEVICE_1, MIN_DATA);
            }
        }
    }

    public void urzadzenie2_salon(View view) {
        salon = (tab2) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (Bluetooth.connectedThread != null) {
            if (salon.salon_device2.isChecked()) {
                sendDataFrame.Request_to_Devices(SALON_DEVICE_2, MAX_DATA);
            }
            if (salon.salon_device2.isChecked() == false) {
                sendDataFrame.Request_to_Devices(SALON_DEVICE_2, MIN_DATA);
            }
        }
    }

    public void kuchnia_urzadzenie1(View view) {
        kuchnia = (tab3) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (Bluetooth.connectedThread != null) {
            if (kuchnia.kuchnia_device1.isChecked()) {
                sendDataFrame.Request_to_Devices(KUCHNIA_DEVICE_1, MAX_DATA);
            }
            if (kuchnia.kuchnia_device1.isChecked() == false) {
                sendDataFrame.Request_to_Devices(KUCHNIA_DEVICE_1, MIN_DATA);
            }
        }
    }

    public void kuchnia_urzadzenie2(View view) {
        kuchnia = (tab3) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (Bluetooth.connectedThread != null) {
            if (kuchnia.kuchnia_device2.isChecked()) {
                sendDataFrame.Request_to_Devices(KUCHNIA_DEVICE_2, MAX_DATA);
            }
            if (kuchnia.kuchnia_device2.isChecked() == false) {
                sendDataFrame.Request_to_Devices(KUCHNIA_DEVICE_2, MIN_DATA);
            }
        }
    }


    public void switch_salon(View view) {
        salon = (tab2)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        rolety_salon = !rolety_salon;
        if(rolety_salon ==false){
            salon.rolety_salon.setImageResource(rolety[0]);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(SALON_BLINDS, MIN_DATA);
            }
        }
        if(rolety_salon ==true){
            salon.rolety_salon.setImageResource(rolety[1]);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(SALON_BLINDS, MAX_DATA);
            }
        }


    }

    public void salon_bulb1(View view) {
        salon = (tab2)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        salon_bulb1 = !salon_bulb1;
        if (salon_bulb1 == true) {
            salon.salon_bulb1.setImageResource(bulb[1]);
            salon.wskaznik1.setChecked(true);
            salon.wskaznik2.setChecked(true);
            salon.wskaznik3.setChecked(true);
            salon.wskaznik4.setChecked(true);
            salon.wskaznik5.setChecked(true);
            salon.wskaznik6.setChecked(true);
            salon.wskaznik7.setChecked(true);
            salon.wskaznik8.setChecked(true);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(SALON_MAIN_LIGHT, MAX_DATA);
            }

        }

        if (salon_bulb1 == false) {
            salon.salon_bulb1.setImageResource(bulb[0]);
            salon.wskaznik1.setChecked(false);
            salon.wskaznik2.setChecked(false);
            salon.wskaznik3.setChecked(false);
            salon.wskaznik4.setChecked(false);
            salon.wskaznik5.setChecked(false);
            salon.wskaznik6.setChecked(false);
            salon.wskaznik7.setChecked(false);
            salon.wskaznik8.setChecked(false);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(SALON_MAIN_LIGHT, MIN_DATA);
            }

        }

    }


    public void fan_button_salon(View view) {
        salon = (tab2)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        salon.animacja = (AnimationDrawable)salon.fan_animation.getBackground();
        salon_fan = !salon_fan;
        if (salon_fan ==false) {
            salon.fan_button.setImageResource(fan[0]);
            salon.animacja.stop();
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(SALON_FAN, MIN_DATA);
            }

        }

        if (salon_fan== true) {
            salon.fan_button.setImageResource(fan[1]);
            salon.animacja.start();
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(SALON_FAN, MAX_DATA);
            }
        }
    }

    public void salon_radioBulb1(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        salon = (tab2)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        salon.salon_bulb1.setImageResource(bulb[1]);
        salon_bulb1 = true;
        switch(view.getId()) {
            case R.id.wskaznik1:
                if (checked)
                    salon.wskaznik2.setChecked(false);
                    salon.wskaznik3.setChecked(false);
                    salon.wskaznik4.setChecked(false);
                    salon.wskaznik5.setChecked(false);
                    salon.wskaznik6.setChecked(false);
                    salon.wskaznik7.setChecked(false);
                    salon.wskaznik8.setChecked(false);
                    SALON_MAIN_LIGHT_DATA = 16;
                    break;


            case R.id.wskaznik2:
                if (checked)
                    salon.wskaznik1.setChecked(true);
                    salon.wskaznik3.setChecked(false);
                    salon.wskaznik4.setChecked(false);
                    salon.wskaznik5.setChecked(false);
                    salon.wskaznik6.setChecked(false);
                    salon.wskaznik7.setChecked(false);
                    salon.wskaznik8.setChecked(false);
                    SALON_MAIN_LIGHT_DATA = 28;
                    break;
            case R.id.wskaznik3:
                if (checked)
                    salon.wskaznik1.setChecked(true);
                    salon.wskaznik2.setChecked(true);
                    salon.wskaznik4.setChecked(false);
                    salon.wskaznik5.setChecked(false);
                    salon.wskaznik6.setChecked(false);
                    salon.wskaznik7.setChecked(false);
                    salon.wskaznik8.setChecked(false);
                    SALON_MAIN_LIGHT_DATA = 40;
                    break;
            case R.id.wskaznik4:
                if (checked)
                    salon.wskaznik1.setChecked(true);
                    salon.wskaznik2.setChecked(true);
                    salon.wskaznik3.setChecked(true);
                    salon.wskaznik5.setChecked(false);
                    salon.wskaznik6.setChecked(false);
                    salon.wskaznik7.setChecked(false);
                    salon.wskaznik8.setChecked(false);
                    SALON_MAIN_LIGHT_DATA = 52;
                    break;
            case R.id.wskaznik5:
                if (checked)
                    salon.wskaznik1.setChecked(true);
                    salon.wskaznik2.setChecked(true);
                    salon.wskaznik3.setChecked(true);
                    salon.wskaznik4.setChecked(true);
                    salon.wskaznik6.setChecked(false);
                    salon.wskaznik7.setChecked(false);
                    salon.wskaznik8.setChecked(false);
                    SALON_MAIN_LIGHT_DATA = 64;
                    break;
            case R.id.wskaznik6:
                if (checked)
                    salon.wskaznik1.setChecked(true);
                    salon.wskaznik2.setChecked(true);
                    salon.wskaznik3.setChecked(true);
                    salon.wskaznik4.setChecked(true);
                    salon.wskaznik5.setChecked(true);
                    salon.wskaznik7.setChecked(false);
                    salon.wskaznik8.setChecked(false);
                    SALON_MAIN_LIGHT_DATA = 76;
                    break;
            case R.id.wskaznik7:
                if (checked)
                    salon.wskaznik1.setChecked(true);
                    salon.wskaznik2.setChecked(true);
                    salon.wskaznik3.setChecked(true);
                    salon.wskaznik4.setChecked(true);
                    salon.wskaznik5.setChecked(true);
                    salon.wskaznik6.setChecked(true);
                    salon.wskaznik8.setChecked(false);
                    SALON_MAIN_LIGHT_DATA = 88;
                    break;
            case R.id.wskaznik8:
                if (checked)
                    salon.wskaznik1.setChecked(true);
                    salon.wskaznik2.setChecked(true);
                    salon.wskaznik3.setChecked(true);
                    salon.wskaznik4.setChecked(true);
                    salon.wskaznik5.setChecked(true);
                    salon.wskaznik6.setChecked(true);
                    salon.wskaznik7.setChecked(true);
                    salon.wskaznik8.setChecked(true);
                    SALON_MAIN_LIGHT_DATA = 100;
                    break;

        }
        if (Bluetooth.connectedThread != null) {
            sendDataFrame.Request_to_Devices(SALON_MAIN_LIGHT, SALON_MAIN_LIGHT_DATA);
        }
    }

    public void kuchnia_LED(View view) {
        kuchnia = (tab3)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        kuchnia_LED = !kuchnia_LED;
        if (kuchnia_LED ==false) {
            kuchnia.kuchnia_LEDbutton.setImageResource(fan[0]);
            kuchnia.kuchnia_LED.setImageResource(bulb[0]);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(KUCHNIA_LED, MIN_DATA);
            }
        }

        if (kuchnia_LED== true) {
            kuchnia.kuchnia_LEDbutton.setImageResource(fan[1]);
            kuchnia.kuchnia_LED.setImageResource(bulb[1]);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(KUCHNIA_LED, MAX_DATA);
            }

        }
    }


    public void kuchnia_oswietlenieGlowne(View view) {
        kuchnia = (tab3)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        kuchnia_oswietlenieGlowne = !kuchnia_oswietlenieGlowne;
        if (kuchnia_oswietlenieGlowne ==false) {
            kuchnia.kuchnia_oswietlenieGlownebutton.setImageResource(fan[0]);
            kuchnia.kuchnia_oswietlenieGlowne.setImageResource(bulb[0]);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(KUCHNIA_MAIN_LIGHT, MIN_DATA);
            }
        }

        if (kuchnia_oswietlenieGlowne== true) {
            kuchnia.kuchnia_oswietlenieGlownebutton.setImageResource(fan[1]);
            kuchnia.kuchnia_oswietlenieGlowne.setImageResource(bulb[1]);
            if (Bluetooth.connectedThread != null) {
                sendDataFrame.Request_to_Devices(KUCHNIA_MAIN_LIGHT, MAX_DATA);
            }

        }
    }



    class MyTabsListener implements ActionBar.TabListener {
        public Fragment fragment;
        public MyTabsListener(Fragment fragment){
            this.fragment = fragment;
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            // TODO Auto-generated method stub
            ft.replace(R.id.fragment_container, fragment);

        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            // TODO Auto-generated method stub

        }
    }
    public static class Copy{
        public boolean fan_switch_flagCopy;

        public boolean sypialnia_bulb1Copy;

        public boolean roler_switch_sypialniaCopy;

        public boolean sypialnia_bulb2Copy;

        public boolean rolety_salonCopy;

        public boolean salon_bulb1Copy;

        public boolean salon_fanCopy;

        public boolean  kuchnia_LEDCopy;

        public boolean kuchnia_oswietlenieGlowneCopy;

        public  void  copy_metod(){
            fan_switch_flagCopy         =   fan_switch_flag;
            sypialnia_bulb1Copy         =   sypialnia_bulb1;
            roler_switch_sypialniaCopy  =   roler_switch_sypialnia;
            sypialnia_bulb2Copy         =   sypialnia_bulb2;
            rolety_salonCopy            =   rolety_salon;
            salon_bulb1Copy             =   salon_bulb1;
            salon_fanCopy               =   salon_fan;
            kuchnia_LEDCopy             =   kuchnia_LED;
            kuchnia_oswietlenieGlowneCopy=  kuchnia_oswietlenieGlowne;

        }
    }

}








