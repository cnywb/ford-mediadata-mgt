package com.ford.mediadata.mgt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglijun on 16/12/3.
 */
public class ListTest {

    @Test
    public void list(){
        List<String> list=new ArrayList<> ();
        list.add ("1111");
        list.add ("22222");
        System.out.println(list.toString ());
    }
}
