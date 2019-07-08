package com.guo.springboot.zookeeper;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainTest {

    public static void main(String[] args) {
        DaliyBaseInspect daliyBaseInspect1 = new DaliyBaseInspect("基地1", "作物1", "id1");
        DaliyBaseInspect daliyBaseInspect2 = new DaliyBaseInspect("基地1", "作物2", "id1");
        DaliyBaseInspect daliyBaseInspect3 = new DaliyBaseInspect("基地2", "作物1", "bid1");

        List<DaliyBaseInspect> list = new ArrayList<>(3);
        list.add(daliyBaseInspect1);
        list.add(daliyBaseInspect2);
        list.add(daliyBaseInspect3);

        Map<String, List<DaliyBaseInspect>> map = list.stream().collect(Collectors.groupingBy(e -> e.getBaseName()));

        Map<String, Map<String, DaliyBaseInspect>> resultMap = new HashMap<>();
        map.forEach((k, v) -> {
            Map<String, DaliyBaseInspect> tempMap = new HashMap<>();
            v.forEach(e -> {
                tempMap.put(e.getCropName(), e);
            });

            resultMap.put(k, tempMap);
        });

        System.out.println(JSON.toJSON(resultMap));

    }
}

class DaliyBaseInspect {
    private String baseName;

    private String cropName;

    private String cropId;

    public DaliyBaseInspect(String baseName, String cropName, String cropId) {
        this.baseName = baseName;
        this.cropName = cropName;
        this.cropId = cropId;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropId() {
        return cropId;
    }

    public void setCropId(String cropId) {
        this.cropId = cropId;
    }
}
