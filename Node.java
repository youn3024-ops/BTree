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


    //https://www.reddit.com/r/computerscience/comments/1sukkgh/made_a_diagram_to_finally_understand_btree/
    public boolean search(String value){
        int i = 0;
        while (this.words[i].compareTo(value)<0){
            i++;
        }
        if (this.words[i].equals(value)){
            return true;
        }
        if (this.isLeaf){
            return false;
        }
        return this.children[i].search(value);
    }
}