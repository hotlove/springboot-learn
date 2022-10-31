package com.guo.springboot.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Auther: hotlove_linx
 * @Date: 2022/10/31 22:48
 * @Description:
 */
public class TrieNodeService {

    public void insertCn(CNTrieNode root, String word) {

        for (int i = 0; i < word.length(); i++) {
            String c = word.substring(i, i+1);
            CNTrieNode cnTrieNode = root.slot.get(c);
            if (cnTrieNode == null) {
                root.slot.put(c, new CNTrieNode());
            }

            root = root.slot.get(c);
            root.count ++;
            root.word = c;
        }
        root.isEnd = true;

    }

    public List<String> searchCh(CNTrieNode root, String prefix) {
        for (int i = 0; i < prefix.length(); i++) {
            String c = prefix.substring(i, i + 1);
            CNTrieNode cnTrieNode = root.slot.get(c);
            if (cnTrieNode == null) {
                return Collections.emptyList();
            }

            root = cnTrieNode;
        }

        List<String> list = new ArrayList<>();
        for (Map.Entry<String, CNTrieNode> entry : root.slot.entrySet()) {
            CNTrieNode node = entry.getValue();
            String pre = prefix + node.word;
            collectCh(node, pre, list);
        }

        return list;
    }

    private void collectCh(CNTrieNode trieNode, String pre, List<String> list) {

        if (trieNode.isEnd) {
            list.add(pre);
        }
        if (trieNode.slot.size() > 0) {
            for (Map.Entry<String, CNTrieNode> entry : trieNode.slot.entrySet()) {
                CNTrieNode node = entry.getValue();
                pre = pre + node.word;
                collectCh(node, pre, list);
            }
        }

    }


    public void insert(TrieNode root, String words, String explain) {
        char[] chars = words.toCharArray();
        for (char c : chars) {
            int idx = c - 'a';
            if (root.slot[idx] == null) {
                root.slot[idx] = new TrieNode();
            }
            root = root.slot[idx];
            root.c = c;
            root.prefix++;
        }
        // 此时指针已经指向叶子节点，在叶子节点设置完整的单词和说明
        root.isWord = true;
        root.word = words;
        root.explain = explain;
    }


    public List<String> searchPrefix(TrieNode root, String prefix) {

        char[] chars = prefix.toCharArray();

        for (char c : chars){
            int idx = c - 'a';

            if (idx > root.slot.length || idx < 0 || root.slot[idx] == null) {
                return Collections.emptyList();
            }

            root = root.slot[idx];
        }

        List<String> list = new ArrayList<>();

        if (root.prefix != 0) {
            // 说明不是根节点

            for (int i = 0; i < root.slot.length; i++) {

                if (root.slot[i] != null) {

                    TrieNode trieNode = root.slot[i];
                    collect(trieNode, list);
                }
            }
        }
        return list;
    }

    private void collect(TrieNode trieNode, List<String> list) {
        if (trieNode.isWord) {
            list.add(trieNode.word + "-->" + trieNode.explain);
            if (list.size() > 15) {
                return;
            }
        }

        for (int i = 0; i < trieNode.slot.length; i++) {
            if (trieNode.slot[i] != null) {
                TrieNode node = trieNode.slot[i];
                collect(node, list);
            }
        }
    }
}
