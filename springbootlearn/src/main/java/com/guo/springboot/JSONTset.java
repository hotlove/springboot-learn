package com.guo.springboot;

import com.alibaba.fastjson.JSON;
import javafx.scene.input.InputMethodTextRun;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class JSONTset {

    public static void main(String[] args) {

        String content = "{\"nodes\":[{\"type\":\"node\",\"label\":\"堃堃的肾2\",\"shape\":\"NODE_ROOT\",\"size\":\"50*50\",\"x\":600,\"y\":150,\"form\":{\"itemId\":84,\"itemTitle\":\"堃堃的肾2\",\"itemType\":1,\"itemCode\":\"P12345679213\",\"imageUrl\":\"0ea22e6147ca414e895e13741e088397\",\"skuCode\":\"P12345679213001\",\"properties\":null,\"propertiesName\":\"\",\"itemCategoryName\":\"855555\",\"minUnitName\":\"个\"},\"color\":\"#c55a11\",\"index\":0,\"level\":0,\"leaf\":2,\"id\":\"7f41ad4c\"},{\"type\":\"node\",\"label\":\"测试物料\",\"shape\":\"NODE_CHILD\",\"size\":\"50*50\",\"x\":500,\"y\":300,\"form\":{\"nodeId\":\"\",\"itemTypeList\":\"3\",\"wasehouseId\":10,\"itemId\":90,\"itemTitle\":\"测试物料\",\"itemType\":\"\",\"itemCode\":\"P12345679238\",\"imageUrl\":\"\",\"skuCode\":\"P12345679238001\",\"skuId\":112,\"properties\":\"\",\"propertiesName\":\"\",\"itemCategoryName\":null,\"minUnitName\":\"个\",\"unitPercent\":1,\"attritionPercent\":1,\"remark\":\"\"},\"index\":1,\"level\":1,\"leaf\":2,\"color\":\"#1890FF\",\"id\":\"d1a9cf3b\"},{\"type\":\"node\",\"label\":\"测试得东西\",\"shape\":\"NODE_CHILD\",\"size\":\"50*50\",\"x\":700,\"y\":300,\"form\":{\"nodeId\":\"\",\"itemTypeList\":\"1,2\",\"wasehouseId\":10,\"itemId\":89,\"itemTitle\":\"测试得东西\",\"itemType\":\"\",\"itemCode\":\"P12345679233\",\"imageUrl\":\"\",\"skuCode\":\"P12345679233002\",\"skuId\":110,\"properties\":\"\",\"propertiesName\":\"sku1:2\",\"itemCategoryName\":null,\"minUnitName\":\"个\",\"unitPercent\":3,\"attritionPercent\":1,\"remark\":\"\",\"technologyId\":5},\"index\":3,\"level\":1,\"leaf\":1,\"color\":\"#1890FF\",\"id\":\"daccb00f\"},{\"type\":\"node\",\"label\":\"测试物流商品\",\"shape\":\"NODE_CHILD\",\"size\":\"50*50\",\"x\":400,\"y\":450,\"form\":{\"nodeId\":\"\",\"itemTypeList\":\"3\",\"wasehouseId\":10,\"itemId\":96,\"itemTitle\":\"测试物流商品\",\"itemType\":\"\",\"itemCode\":\"P12345679272\",\"imageUrl\":\"\",\"skuCode\":\"P12345679272001\",\"skuId\":119,\"properties\":\"\",\"propertiesName\":\"\",\"itemCategoryName\":null,\"minUnitName\":\"个\",\"unitPercent\":1,\"attritionPercent\":3,\"remark\":\"\",\"technologyId\":5},\"index\":5,\"level\":2,\"leaf\":0,\"color\":\"#1890FF\",\"id\":\"255deaa9\"},{\"type\":\"node\",\"label\":\"发啊啊\",\"shape\":\"NODE_CHILD\",\"size\":\"50*50\",\"x\":600,\"y\":450,\"form\":{\"nodeId\":\"\",\"itemTypeList\":\"3\",\"wasehouseId\":10,\"itemId\":87,\"itemTitle\":\"发啊啊\",\"itemType\":\"\",\"itemCode\":\"P12345679229\",\"imageUrl\":\"\",\"skuCode\":\"P12345679229001\",\"skuId\":107,\"properties\":\"\",\"propertiesName\":\"\",\"itemCategoryName\":null,\"minUnitName\":\"个\",\"unitPercent\":2,\"attritionPercent\":3,\"remark\":\"\",\"technologyId\":5},\"index\":7,\"level\":2,\"leaf\":0,\"color\":\"#1890FF\",\"id\":\"21501847\"}],\"edges\":[{\"source\":\"7f41ad4c\",\"sourceAnchor\":0,\"target\":\"d1a9cf3b\",\"targetAnchor\":0,\"style\":{\"stroke\":\"#45bf6c\"},\"type\":\"edge\",\"id\":\"8bc55952\",\"index\":2,\"form\":{\"label\":\"1:1\",\"unitPercent\":1,\"attritionPercent\":1},\"label\":\"1:1\"},{\"source\":\"7f41ad4c\",\"sourceAnchor\":0,\"target\":\"daccb00f\",\"targetAnchor\":0,\"style\":{\"stroke\":\"#45bf6c\"},\"type\":\"edge\",\"id\":\"c964b7d6\",\"index\":4,\"form\":{\"label\":\"3:1\",\"unitPercent\":3,\"attritionPercent\":1},\"label\":\"3:1\"},{\"source\":\"d1a9cf3b\",\"sourceAnchor\":1,\"target\":\"255deaa9\",\"targetAnchor\":0,\"style\":{\"stroke\":\"#45bf6c\"},\"type\":\"edge\",\"id\":\"00a4b209\",\"index\":6,\"form\":{\"label\":\"1:3\",\"unitPercent\":1,\"attritionPercent\":3},\"label\":\"1:3\"},{\"source\":\"d1a9cf3b\",\"sourceAnchor\":1,\"target\":\"21501847\",\"targetAnchor\":0,\"style\":{\"stroke\":\"#45bf6c\"},\"type\":\"edge\",\"id\":\"4919969f\",\"index\":8,\"form\":{\"label\":\"2:3\",\"unitPercent\":2,\"attritionPercent\":3},\"label\":\"2:3\"},{\"source\":\"daccb00f\",\"sourceAnchor\":1,\"target\":\"21501847\",\"targetAnchor\":0,\"style\":{\"stroke\":\"#45bf6c\"},\"type\":\"edge\",\"form\":{\"label\":\"2:3\",\"unitPercent\":2,\"attritionPercent\":3},\"id\":\"ca64d9d0\",\"index\":9,\"label\":\"2:3\"}]}";

        List<Item> itemList = new ArrayList<>();

        Item item = new Item();
        item.setBomNodeId("7f41ad4c");
        item.setProductTotal(10);

        itemList.add(item);

        BomService bomService = new BomService();


        Map<String, BigDecimal> bomMap = bomService.countEveryNodeNumber(content, itemList);

        System.out.println(JSON.toJSON(bomMap));
    }

    public static class BomService {
        public Map<String, BigDecimal> countEveryNodeNumber(String content, List<Item> itemList){

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
                        nodes.stream().filter(e -> "NODE_ROOT".equals(e.get("shape")) &&
                                e.get("id").equals(item.getBomNodeId())).collect(Collectors.toList());


                // 获取到根节点
                Map<String, Object> root = roots.get(0);

                countMap.put(MapUtils.getString(root, "id"), item.getProductTotal() == null ? BigDecimal.ONE : new BigDecimal(item.getProductTotal()));

                // 2.根据关系找到子节点 计算数量 这里需要使用递归
                this.recursionCount(edges, countMap, MapUtils.getString(root, "id"));

            }

            return countMap;
        }

        // 递归计算每个节点的数量
        private void recursionCount(List<Map<String, Object>> edges, Map<String, BigDecimal> countMap, String sourceId) {
            List<Map<String, Object>> childrenEdges = edges.stream()
                    .filter(e -> e.get("source").equals(sourceId)).collect(Collectors.toList());

            if (CollectionUtils.isNotEmpty(childrenEdges)) {
                for (Map<String, Object> edge : childrenEdges) {

                    String targetId = MapUtils.getString(edge, "target");

                    String rateStr = MapUtils.getString(edge, "label");
                    BigDecimal result = BigDecimal.ONE;
                    if (StringUtils.isNotBlank(rateStr)) {
                        // 如果存在比例那就是 按比例进行计算 否则就是0
                        String []rateLabels = MapUtils.getString(edge, "label").split(":");
                        BigDecimal sourceCount = countMap.get(sourceId);

                        BigDecimal a = sourceCount.multiply(new BigDecimal(rateLabels[1]));
                        result = a.divide(new BigDecimal(rateLabels[0]), 2, BigDecimal.ROUND_HALF_UP);

                    }

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



    public static class Item {

        private String bomNodeId;

        private String id;

        private Integer count;

        private Integer productTotal;

        public Integer getProductTotal() {
            return productTotal;
        }

        public void setProductTotal(Integer productTotal) {
            this.productTotal = productTotal;
        }

        public String getBomNodeId() {
            return bomNodeId;
        }

        public void setBomNodeId(String bomNodeId) {
            this.bomNodeId = bomNodeId;
        }

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
}
