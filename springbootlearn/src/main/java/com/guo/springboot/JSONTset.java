package com.guo.springboot;

import com.alibaba.fastjson.JSON;
import javafx.scene.input.InputMethodTextRun;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JSONTset {

    public static void main(String[] args) {

        String json = "{\"nodes\":[{\"type\":\"node\",\"label\":\"分配节点-4\",\"shape\":\"NODE_FLOW\",\"size\":\"170*34\",\"x\":730.5,\"y\":227.5,\"id\":\"a8637169\",\"color\":\"#1890FF\",\"actType2\":\"or\",\"form\":[],\"rules\":[],\"timeOutPeriod\":\"\",\"timeOutPeriodHour\":\"\",\"index\":0},{\"type\":\"node\",\"label\":\"分配节点-2\",\"shape\":\"NODE_FLOW\",\"size\":\"170*34\",\"x\":769,\"y\":99,\"id\":\"bf365209\",\"color\":\"#1890FF\",\"actType2\":\"or\",\"form\":[],\"rules\":[],\"timeOutPeriod\":\"\",\"timeOutPeriodHour\":\"\",\"index\":2},{\"type\":\"node\",\"label\":\"发起人\",\"shape\":\"NODE_CREATE_USER\",\"size\":\"50*50\",\"x\":372.5,\"y\":233,\"form\":[],\"rules\":[],\"color\":\"#c55a11\",\"index\":4,\"id\":\"33c8b3c8\",\"actType2\":\"or\",\"timeOutPeriod\":\"\",\"timeOutPeriodHour\":\"\"},{\"type\":\"node\",\"label\":\"分配节点-8\",\"shape\":\"NODE_FLOW\",\"size\":\"170*34\",\"x\":977,\"y\":232.5,\"id\":\"65b42c01\",\"color\":\"#1890FF\",\"actType2\":\"or\",\"form\":[],\"rules\":[],\"timeOutPeriod\":\"\",\"timeOutPeriodHour\":\"\",\"index\":5}],\"edges\":[{\"source\":\"33c8b3c8\",\"sourceAnchor\":1,\"target\":\"a8637169\",\"id\":\"12d6f4c0\",\"style\":{\"stroke\":\"#45bf6c\"},\"label\":\"1:2\",\"type\":\"edge\",\"conditions\":{\"sort\":1,\"list\":[],\"list2\":[],\"operator\":[]},\"targetAnchor\":4,\"index\":1,\"isBack\":false},{\"source\":\"33c8b3c8\",\"sourceAnchor\":1,\"target\":\"bf365209\",\"targetAnchor\":4,\"id\":\"16fa362e\",\"style\":{\"stroke\":\"#45bf6c\"},\"index\":3,\"isBack\":false,\"label\":\"1:1\",\"type\":\"edge\",\"conditions\":{\"sort\":1,\"list\":[],\"list2\":[],\"operator\":[]}},{\"source\":\"a8637169\",\"sourceAnchor\":2,\"target\":\"65b42c01\",\"targetAnchor\":4,\"id\":\"7286c5d8\",\"style\":{\"stroke\":\"#45bf6c\"},\"label\":\"1:2\",\"type\":\"edge\",\"conditions\":{\"sort\":1,\"list\":[],\"list2\":[],\"operator\":[]},\"index\":6,\"isBack\":false}],\"notifiers\":[],\"roleCondition\":[]}";
        JSONTset jsonTset = new JSONTset();

        Item item = new Item();
        item.setId("33c8b3c8");
        item.setCount(2);

        Map<String, BigDecimal> result = jsonTset.countEveryNodeNumber(json, Arrays.asList(item));

        result.forEach((k, v) -> {
            System.out.println("id:"+k);
            System.out.println("value:" + v.toString());
            System.out.println("--------------------------");
        });
    }

    public static class Item {
        private String id;

        private Integer count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }

    public Map<String, BigDecimal> countEveryNodeNumber(String content, List<Item> itemList) {

        Map bomData = JSON.parseObject(content, Map.class);

        // 节点
        List<Map<String, Object>> nodes = (List<Map<String, Object>>) bomData.get("nodes");

        // 线条
        List<Map<String, Object>> edges = (List<Map<String, Object>>) bomData.get("edges");


        // 各个节点计算结果
        Map<String, BigDecimal> countMap = new HashMap<>();

        for (Item item : itemList) {
            // 1.找到根节点 这里用循环可能根节点有多个
            List<Map<String, Object>> roots =
                    nodes.stream().filter(e -> "NODE_CREATE_USER".equals(e.get("shape")) &&
                            e.get("id").equals(item.getId())).collect(Collectors.toList());

            // 获取到根节点
            Map<String, Object> root = roots.get(0);

            countMap.put(MapUtils.getString(root, "id"), new BigDecimal(item.getCount()));

            // 2.根据关系找到子节点 计算数量 这里需要使用递归
            this.recursionCount(edges, countMap, MapUtils.getString(root, "id"));

        }

        return countMap;
    }

    private void recursionCount(List<Map<String, Object>> edges, Map<String, BigDecimal> countMap, String sourceId) {
        List<Map<String, Object>> childrenEdges = edges.stream()
                .filter(e -> e.get("source").equals(sourceId)).collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(childrenEdges)) {
            for (Map<String, Object> edge : childrenEdges) {

                String targetId = MapUtils.getString(edge, "target");

                String []rateLabels = MapUtils.getString(edge, "label").split(":");

                BigDecimal sourceCount = countMap.get(sourceId);

                BigDecimal a = sourceCount.multiply(new BigDecimal(rateLabels[1]));
                BigDecimal result = a.divide(new BigDecimal(rateLabels[0]), 2, BigDecimal.ROUND_HALF_UP);

                BigDecimal countIntege = countMap.get(targetId);
                if (countIntege != null) {
                    countMap.put(targetId, countIntege.add(result));
                } else {
                    countMap.put(targetId, result);
                }

                this.recursionCount(edges, countMap, targetId);
            }
        }

    }
}
