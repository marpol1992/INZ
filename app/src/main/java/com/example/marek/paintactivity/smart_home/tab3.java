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
import android.widget.ToggleButton;

import com.example.marek.paintactivity.R;

public class tab3 extends Fragment {
    ImageButton fan_switch;
    ImageButton kuchnia_LEDbutton;
    ImageButton kuchnia_oswietlenieGlownebutton;
    ImageView fan_animation;
    ImageView kuchnia_oswietlenieGlowne;
    ImageView kuchnia_LED;
    AnimationDrawable animacja;
    public ToggleButton kuchnia_device1;
    public ToggleButton kuchnia_device2;
    public Integer[] fan = {
            R.drawable.power_off,
            R.drawable.power_on,

    };
    public Integer[] bulb={
            R.drawable.off,
            R.drawable.on1,
    };
    MainActivity.Copy copy = new MainActivity.Copy();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3, container, false);
        kuchnia_device1 = (ToggleButton) view.findViewById(R.id.kuchnia_device1);
        kuchnia_device2 = (ToggleButton) view.findViewById(R.id.kuchnia_device2);
        kuchnia_LED = (ImageView) view.findViewById(R.id.imageView7);
        kuchnia_oswietlenieGlowne = (ImageView) view.findViewById(R.id.imageView5);
        kuchnia_LEDbutton = (ImageButton) view.findViewById(R.id.imageButton5);
        kuchnia_oswietlenieGlownebutton = (ImageButton) view.findViewById(R.id.imageButton4);
        fan_switch = (ImageButton) view.findViewById(R.id.imageButton6);
        fan_animation = (ImageView) view.findViewById(R.id.fan);
        fan_animation.setBackgroundResource(R.drawable.fan_animacja);
        animacja = (AnimationDrawable) fan_animation.getBackground();
        copy.copy_metod();
        if (copy.fan_switch_flagCopy) {
            animacja.start();
            fan_switch.setImageResource(fan[1]);
            //Log.d("copy.fan_switch_flaga", Boolean.toString(copy.fan_switch_flaga));
        }
        if (copy.kuchnia_LEDCopy) {
            kuchnia_LED.setImageResource(bulb[1]);
            kuchnia_LEDbutton.setImageResource(fan[1]);

        }
        if (copy.kuchnia_oswietlenieGlowneCopy) {
            kuchnia_oswietlenieGlowne.setImageResource(bulb[1]);
            kuchnia_oswietlenieGlownebutton.setImageResource(fan[1]);

        }

        return view;
    }


}
