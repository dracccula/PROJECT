package kireev.ftshw.project.TempTools;

import android.graphics.Color;

import java.util.Random;

public class SetRandom {

    public static int SetRandomInt(){
        int rand;
        int min = 100;
        int max = 200;
        rand = min + (int) (Math.random() * max);
        return rand;
    }

    public static int SetRandomColor(){
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r, g, b);
    }
}
