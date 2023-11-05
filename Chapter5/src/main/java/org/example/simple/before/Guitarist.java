package org.example.simple.before;

public class Guitarist implements Singer {

    private String lyric="You're gonna live forever in me ";

    @Override
    public void sing(int count) {
        System.out.println(lyric + count + " times");
    }
}
