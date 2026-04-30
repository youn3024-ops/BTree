public class Node {
    int nodeIndex;
    String[] words = new String[3];
    Node[] children = new Node[3];
    boolean isLeaf;

    public Node(String word, int nodeIndex) {
        this.words[0] = word;
        this.nodeIndex = nodeIndex;
    }

    public Node(String[] words, int nodeIndex) {
        this.words = words;
        this.nodeIndex = nodeIndex;
    }

    public String[] getVal(){
        return this.words;
    }

    public Node[] getChildren(){
        return this.children;
    }
}