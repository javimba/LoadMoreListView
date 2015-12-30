package com.osblog.loadmoreview.factory;

import java.util.ArrayList;
import java.util.List;

public class DataFactory {

    private static List<String> userList;

    public static void init() {
        userList = new ArrayList<>();
        for (int i = 0; i < 75; i++) {
            userList.add("张三" + i);
        }
    }

    public static List<String> getData(int startid, int limit) {
        if(startid + limit >userList.size()){
            throw new IllegalArgumentException("data size is biger");
        }
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            dataList.add(userList.get(startid + i));
        }
        return dataList;
    }
}
