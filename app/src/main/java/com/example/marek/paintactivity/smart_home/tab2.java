package com.example.marek.paintactivity.smart_home;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ToggleButton;

import com.example.marek.paintactivity.R;

/**
 * Created by Dr.h3cker on 14/03/2015.
 */
public class tab2 extends Fragment {
    ImageView rolety_salon;
    ImageView fan_animation;
    ImageView salon_bulb1;
    ImageButton fan_button;
    AnimationDrawable animacja;
    RadioButton wskaznik1;
    RadioButton wskaznik2;
    RadioButton wskaznik3;
    RadioButton wskaznik4;
    RadioButton wskaznik5;
    RadioButton wskaznik6;
    RadioButton wskaznik7;
    RadioButton wskaznik8;

    public ToggleButton salon_device1;
    public ToggleButton salon_device2;

    MainActivity.Copy copy = new MainActivity.Copy();
    public Integer[] rolety = {
            R.drawable.rolety_off,
            R.drawable.rolety_on,
    };
    public Integer[] fan={
            R.drawable.power_off,
            R.drawable.power_on,

    };
    public Integer[] bulb={
            R.drawable.off,
            R.drawable.on1,
    };
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

View view =inflater.inflate(R.layout.tab2,container,false);
        salon_device1 = (ToggleButton) view.findViewById(R.id.salon_device1);
        salon_device2 = (ToggleButton) view.findViewById(R.id.salon_device2);
        wskaznik1 = (RadioButton) view.findViewById(R.id.wskaznik1);
        wskaznik2 = (RadioButton) view.findViewById(R.id.wskaznik2);
        wskaznik3 = (RadioButton) view.findViewById(R.id.wskaznik3);
        wskaznik4 = (RadioButton) view.findViewById(R.id.wskaznik4);
        wskaznik5 = (RadioButton) view.findViewById(R.id.wskaznik5);
        wskaznik6 = (RadioButton) view.findViewById(R.id.wskaznik6);
        wskaznik7 = (RadioButton) view.findViewById(R.id.wskaznik7);
        wskaznik8 = (RadioButton) view.findViewById(R.id.wskaznik8);
        fan_button = (ImageButton) view.findViewById(R.id.imageButton3);
        salon_bulb1 = (ImageView) view.findViewById(R.id.salon_bulb1);
        rolety_salon = (ImageView) view.findViewById(R.id.imageView6);
        fan_animation = (ImageView)view.findViewById(R.id.imageView9);
        fan_animation.setBackgroundResource(R.drawable.fan_animacja);
        animacja = (AnimationDrawable)fan_animation.getBackground();
        copy.copy_metod();
        if (copy.rolety_salonCopy){
            rolety_salon.setImageResource(rolety[1]);
        }
        if (copy.salon_fanCopy){
            animacja.start();
            fan_button.setImageResource(fan[1]);
        }
        if (copy.salon_bulb1Copy){

            salon_bulb1.setImageResource(bulb[1]);
        }
        return view;
    }
}
