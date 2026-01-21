package com.qdbank.mall.web.user;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Demo {
    private String name;
    private int age;

    public static List<Demo> getDemo1() {
        Demo demo = new Demo();
        demo.setAge(11);
        demo.setName("李");
        Demo demo2 = new Demo();
        demo2.setAge(13);
        demo2.setName("王");
        List<Demo> demoList = new ArrayList<>();
        demoList.add(demo);
        demoList.add(demo2);
        return demoList;
    }

    public static List<Demo> getDemo2() {
        Demo demo = new Demo();
        demo.setAge(11);
        demo.setName("张");
        Demo demo2 = new Demo();
        demo2.setAge(12);
        demo2.setName("李");
        List<Demo> demoList = new ArrayList<>();
        demoList.add(demo);
        demoList.add(demo2);
        return demoList;
    }

    public static void main(String[] args) {
        List<Demo> demoList = getDemo1();
        List<Demo> demos = getDemo2();

        List<Demo> intersection = demoList.stream()
                .filter(item -> demos.stream().map(e -> e.getName()).collect(Collectors.toList())
                        .contains(item.getName())).collect(Collectors.toList());
        intersection.forEach(System.out::println);
    }
}