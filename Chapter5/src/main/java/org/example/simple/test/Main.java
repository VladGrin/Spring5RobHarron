package org.example.simple.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Runner r = new Runner();
        r.run();
    }
}
class Runner {
    public void run() {
        String s = "[asd]{3}";
        String str = "jlhasdvliylhblasdjb;kjbas;jn'jdmo;l,asdm';asdl,";

        Pattern p = Pattern.compile("asd");
        Matcher m = p.matcher(str);
        while (m.find()) {
            System.out.println("ura");
        }
    }
}