package code.String;

/**
 * @author Jevis Hoo
 * @since 2021/3/24 11:24
 * @description
 */
public class Trie {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("abc");
        trie.insert("abcd");
        trie.insert("abd");
        trie.insert("b");
        trie.insert("bcd");
        trie.insert("efg");
        trie.insert("hijk");

        System.out.println(trie.search("ab"));
        System.out.println(trie.search("abc"));
    }

    public static class TrieNode {
        public int path;
        public int end;
        public TrieNode[] map;

        public TrieNode() {
            path = 0;
            end = 0;
            map = new TrieNode[26];
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    void insert(String word) {
        if (word == null) {
            return;
        }
        char[] chs = word.toCharArray();
        TrieNode node = root;
        node.path++;
        int index;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.map[index] == null) {
                node.map[index] = new TrieNode();
            }
            node = node.map[index];
            node.path++;
        }
        node.end++;
    }

    void delete(String word) {
        if (search(word)) {
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.path++;
            int index;
            for (char ch : chs) {
                index = ch - 'a';
                if (node.map[index].path-- == 1) {
                    node.map[index] = null;
                    return;
                }
                node = node.map[index];
            }
            node.end--;
        }
    }

    boolean search(String word) {
        if (word == null) {
            return false;
        }
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.map[index] == null) {
                return false;
            }
            node = node.map[index];
        }
        return node.end != 0;
    }

    int prefixNumber(String pre) {
        if (pre == null) {
            return 0;
        }
        char[] chs = pre.toCharArray();
        TrieNode node = root;
        int index;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.map[index] == null) {
                return 0;
            }
            node = node.map[index];
        }
        return node.path;
    }
}
