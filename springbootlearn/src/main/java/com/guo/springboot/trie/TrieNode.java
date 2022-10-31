package com.guo.springboot.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: hotlove_linx
 * @Date: 2022/10/31 22:45
 * @Description:
 */
public class TrieNode {

    // 形成链
    public TrieNode[] slot = new TrieNode[26];

    // 字母
    public char c;

    // 是否为单词 数量 > 0表示单词
    public boolean isWord;

    // 前缀
    public int prefix;

    // 具体的一个单词字符串
    public String word;

    // 说明
    public String explain;

}
