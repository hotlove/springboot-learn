package com.guo.springboot.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: hotlove_linx
 * @Date: 2022/10/31 23:23
 * @Description:
 */
public class TrieNodeServiceV2 {

    public void insert(TrieNode root, String words, String explain) {
        char[] chars = words.toCharArray();
        for (char c : chars) {
            int idx = c - 'a'; // - a 从 0 开始，参考 ASCII 码表
            if (root.slot[idx] == null) {
                root.slot[idx] = new TrieNode();
            }
            root = root.slot[idx];
            root.c = c;
            root.prefix++;
        }
        root.explain = explain; // 单词的注释说明信息
        root.isWord = true;     // 循环拆解单词后标记
    }

    public List<String> searchPrefix(TrieNode root, String prefix) {
        char[] chars = prefix.toCharArray();
        StringBuilder cache = new StringBuilder();
        // 精准匹配：根据前置精准查找
        for (char c : chars) {
            int idx = c - 'a';
            // 匹配为空
            if (idx > root.slot.length || idx < 0 || root.slot[idx] == null) {
                return Collections.emptyList();
            }
            cache.append(c);
            root = root.slot[idx];
        }
        // 模糊匹配：根据前缀的最后一个单词，递归遍历所有的单词
        ArrayList<String> list = new ArrayList<>();
        if (root.prefix != 0) {
            for (int i = 0; i < root.slot.length; i++) {
                if (root.slot[i] != null) {
                    char c = (char) (i + 'a');
                    collect(root.slot[i], String.valueOf(cache) + c, list, 15);
                    if (list.size() >= 15) {
                        return list;
                    }
                }
            }
        }
        return list;
    }

    protected void collect(TrieNode trieNode, String pre, List<String> queue, int resultLimit) {
        // 找到单词
        if (trieNode.isWord) {
            trieNode.word = pre;
            // 保存检索到的单词到 queue
            queue.add(trieNode.word + " -> " + trieNode.explain);
            if (queue.size() >= resultLimit) {
                return;
            }
        }
        // 递归调用，查找单词
        for (int i = 0; i < trieNode.slot.length; i++) {
            char c = (char) ('a' + i);
            if (trieNode.slot[i] != null) {
                collect(trieNode.slot[i], pre + c, queue, resultLimit);
            }
        }
    }

}
