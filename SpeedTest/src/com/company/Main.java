package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args){
        List<Integer> list=new ArrayList<Integer>();
        List<Integer> link=new LinkedList<Integer>();
        Date before =new Date();
        for(int j = 0;j<1000000; ++j){
            list.add(1);
        }
        Date after=new Date();
        int cost= (int)(after.getTime()-before.getTime());
        System.out.println("Adding 1000000 elements into ArrayList cost  "+cost+" ms");
        before =new Date();
        for(int j = 0;j<1000000; ++j){
            link.add(1);
        }
        after=new Date();
        cost= (int)(after.getTime()-before.getTime());
        System.out.println("Adding 1000000 elements into LinkedList cost  "+cost+" ms");
    }
}
