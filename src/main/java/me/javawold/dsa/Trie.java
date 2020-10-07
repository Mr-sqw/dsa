package me.javawold.dsa;

/**
 * 208. 实现 Trie (前缀树)
实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

示例:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");   
trie.search("app");     // 返回 true
说明:

你可以假设所有的输入都是由小写字母 a-z 构成的。
保证所有输入均为非空字符串。<br>
 * <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 *
 * @author suqianwen 2020年10月7日
 */
public class Trie {

	public static class TrieNode {
		
		boolean hit = false;//精确命中/完全匹配

		final TrieNode[] children;

		public TrieNode(int count) {
			children = new TrieNode[count];
		}

	}

	private TrieNode[] rootNodes;

	/** Initialize your data structure here. */
	public Trie() {
		rootNodes = new TrieNode[26];
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		if (word == null || word.length() == 0) {
			return;
		}

		/**/
		char[] chars = word.toCharArray();
		TrieNode parent = null;
		TrieNode[] children = rootNodes;
		for (int i = 0; i < chars.length; i++) {
			int index = chars[i] - 'a';
			if (children[index] == null) {
				children[index] = new TrieNode(26);
			}
			//
			parent = children[index];
			children = children[index].children;
		}
		parent.hit = true;
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		if (word == null || word.length() == 0) {
			return false;
		}

		/**/
		char[] chars = word.toCharArray();
		TrieNode parent = null;
		TrieNode[] children = rootNodes;
		for (int i = 0; i < chars.length; i++) {
			int index = chars[i] - 'a';
			if (children[index] == null) {
				return false;
			}
			//
			parent = children[index];
			children = children[index].children;
		}
		return parent.hit;
	}

	/**
	 * Returns if there is any word in the trie that starts with the given prefix.
	 */
	public boolean startsWith(String prefix) {
		if (prefix == null || prefix.length() == 0) {
			return false;
		}

		/**/
		char[] chars = prefix.toCharArray();
		TrieNode[] children = rootNodes;
		for (int i = 0; i < chars.length; i++) {
			int index = chars[i] - 'a';
			if (children[index] == null) {
				return false;
			}
			//
			children = children[index].children;
		}
		return true;
	}

}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */