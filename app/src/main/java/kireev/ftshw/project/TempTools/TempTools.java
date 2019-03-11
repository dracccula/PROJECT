package kireev.ftshw.project.TempTools;

public class TempTools {

    public static int SetRandom(){
        int rand;
        int min = 0;
        int max = 100;
        rand = min + (int) (Math.random() * max);
        return rand;
    };
}
