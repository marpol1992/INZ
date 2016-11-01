package com.example.marek.paintactivity.smart_home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.marek.paintactivity.R;


public class tab1 extends Fragment {
    public RadioButton radioButton1;
    public RadioButton radioButton2;
    public RadioButton radioButton3;
    public RadioButton radioButton4;
    public RadioButton radioButton5;
    public RadioButton radioButton6;
    public RadioButton radioButton7;
    public RadioButton radioButton8;

    public RadioButton wskaznik_1;
    public RadioButton wskaznik_2;
    public RadioButton wskaznik_3;
    public RadioButton wskaznik_4;
    public RadioButton wskaznik_5;
    public RadioButton wskaznik_6;
    public RadioButton wskaznik_7;
    public RadioButton wskaznik_8;

    public ToggleButton sypialnia_device1;
    public ToggleButton sypialnia_device2;
    public ToggleButton sypialnia_device3;


    public TextView textView;
    public ImageView sypialnia_bulb1;
    public ImageView sypialnia_bulb2;
    public ImageView rolety_image;
    public Switch rolety_switch;
    MainActivity.Copy copy = new MainActivity.Copy();
    public Integer[] rolety = {
            R.drawable.rolety_off,
            R.drawable.rolety_on,
    };
    public Integer[] bulb={
            R.drawable.off,
            R.drawable.on1,
    };
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab1,container,false);
        radioButton1 = (RadioButton) view.findViewById(R.id.level_125);
        radioButton2 = (RadioButton) view.findViewById(R.id.level_250);
        radioButton3 = (RadioButton) view.findViewById(R.id.level_375);
        radioButton4 = (RadioButton) view.findViewById(R.id.level_500);
        radioButton5 = (RadioButton) view.findViewById(R.id.level_625);
        radioButton6 = (RadioButton) view.findViewById(R.id.level_750);
        radioButton7 = (RadioButton) view.findViewById(R.id.level_875);
        radioButton8 = (RadioButton) view.findViewById(R.id.level_1000);

        wskaznik_1 = (RadioButton) view.findViewById(R.id.wskaznik_1);
        wskaznik_2 = (RadioButton) view.findViewById(R.id.wskaznik_2);
        wskaznik_3 = (RadioButton) view.findViewById(R.id.wskaznik_3);
        wskaznik_4 = (RadioButton) view.findViewById(R.id.wskaznik_4);
        wskaznik_5 = (RadioButton) view.findViewById(R.id.wskaznik_5);
        wskaznik_6 = (RadioButton) view.findViewById(R.id.wskaznik_6);
        wskaznik_7 = (RadioButton) view.findViewById(R.id.wskaznik_7);
        wskaznik_8 = (RadioButton) view.findViewById(R.id.wskaznik_8);

        sypialnia_device1 = (ToggleButton) view.findViewById(R.id.toggleButton);
        sypialnia_device2 = (ToggleButton) view.findViewById(R.id.toggleButton2);
        sypialnia_device3 = (ToggleButton) view.findViewById(R.id.toggleButton3);

        textView = (TextView) view.findViewById(R.id.textView3);
        sypialnia_bulb1 = (ImageView) view.findViewById(R.id.sypialnia_bulb1);
        sypialnia_bulb2 = (ImageView) view.findViewById(R.id.imageView4);
        rolety_switch = (Switch) view.findViewById(R.id.switch3);
        rolety_image = (ImageView) view.findViewById(R.id.imageView8);
        copy.copy_metod();
        if (copy.roler_switch_sypialniaCopy){
            rolety_image.setImageResource(rolety[1]);
        }
        if (copy.sypialnia_bulb1Copy){
            sypialnia_bulb1.setImageResource(bulb[1]);
        }
        if (copy.sypialnia_bulb2Copy){
            sypialnia_bulb2.setImageResource(bulb[1]);
        }

        return  view;
    }

}
