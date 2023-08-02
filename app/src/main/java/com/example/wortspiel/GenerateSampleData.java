package com.example.wortspiel;

import com.example.wortspiel.Model.Wort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GenerateSampleData {
    private  ArrayList<Wort>  data = new ArrayList<>();
    private static GenerateSampleData singleTonObj ;

    private GenerateSampleData() {
        Map<String , String> d = new HashMap<>();
        d.put("entstellen","To disfigure");
        d.put("enstellen","To hire");
        d.put("aufstelen","To make");
        d.put("zustellen","To deliver");
        d.put("einstelen","To hire");
        d.put("bestelen","To order");
        //d.put("vorstellen","To introduction");
        //d.put("verstellen","To adjust");

        d.forEach((g,e)->{
            this.data.add(new Wort(g,e));
        });
    }

    public static GenerateSampleData getInstances(){
        if (singleTonObj==null){
            singleTonObj = new GenerateSampleData();
        }
        return singleTonObj;
    }

    public static ArrayList<Wort> getData(){
        return  getInstances().data;
    }

}
