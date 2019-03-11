package kireev.ftshw.project.TempTools;

public class TempTools {

    public static int SetRandom(){
        int rand;
        int min = 100;
        int max = 200;
        rand = min + (int) (Math.random() * max);
        return rand;
    };
}
