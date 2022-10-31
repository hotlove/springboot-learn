package com.guo.springboot.trie;

import java.util.List;

/**
 * @Auther: hotlove_linx
 * @Date: 2022/10/31 23:11
 * @Description:
 */
public class TestMain {

    public static void main(String[] args) {
        TrieNodeService trieNodeService = new TrieNodeService();
        CNTrieNode root = new CNTrieNode();
//
//        TrieNode root = new TrieNode();
//        trieNodeService.insert(root, "battle", "战斗");
//        trieNodeService.insert(root, "bat", "大厂");
//        trieNodeService.insert(root, "batch", "批量");
//        trieNodeService.insert(root, "bitch", "彪子");
//
//        List<String> results = trieNodeService.searchPrefix(root, "ba");
//
////        TrieNode root = new TrieNode();
////        TrieNodeServiceV2 trieNodeServiceV2 = new TrieNodeServiceV2();
////        trieNodeServiceV2.insert(root,"bat","大厂");
////        trieNodeServiceV2.insert(root,"batch", "批量");
////        trieNodeServiceV2.insert(root,"bitch", "彪子");
////        trieNodeServiceV2.insert(root,"battle", "战斗");
////        List<String> results = trieNodeServiceV2.searchPrefix(root, "ba");
//        results.forEach(System.out::println);
        trieNodeService.insertCn(root, "我是中国人");
        trieNodeService.insertCn(root, "我爱中国");
        trieNodeService.insertCn(root, "我是印度人");

        List<String> results = trieNodeService.searchCh(root, "我");
        results.forEach(System.out::println);
    }
}
