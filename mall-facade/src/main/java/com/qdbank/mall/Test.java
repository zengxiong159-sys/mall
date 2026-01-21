package com.qdbank.mall;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws ParseException {

        /*Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);

        items.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));

        items.forEach((k,v)->{
            System.out.println("Item : " + k + " Count : " + v);
            if("E".equals(k)){
                System.out.println("Hello E");
            }
        });*/

        System.out.print("--------------------------------------------------");
        List<String> it = new ArrayList<>();
        List<String> ite = new ArrayList<>();
        ite.add("A");
        ite.add("B");
        ite.add("C");
        ite.add("D");
        ite.add("E");

        //lambda
        //Output : A,B,C,D,E
        //ite.forEach(item->System.out.println(item));

        //Output : C
        ite.forEach(item->{
            /*if("C".equals(item)){
                System.out.println(item);
            }*/
            it.add(item);
        });

        it.forEach(i->{
            System.out.print("---"+i);
        });

        //method reference
        //Output : A,B,C,D,E
        //ite.forEach(System.out::println);

        //Stream and filter
        //Output : B
        /*ite.stream()
                .filter(s->s.contains("B"))
                .forEach(System.out::println);*/
    }


}
