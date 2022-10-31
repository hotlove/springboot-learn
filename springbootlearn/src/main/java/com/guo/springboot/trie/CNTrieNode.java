package com.guo.springboot.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: hotlove_linx
 * @Date: 2022/10/31 23:47
 * @Description:
 */
public class CNTrieNode {

    public String word;

    public boolean isEnd;

    public int count;

    public Map<String, CNTrieNode> slot = new HashMap<>();


}
