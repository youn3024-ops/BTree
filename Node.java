public class Node {
    int nodeIndex;
    String[] words = new String[3];
    Node[] children = new Node[3];
    boolean isLeaf;

    public Node(String word, int nodeIndex) {
        this.words[0] = word;
        this.nodeIndex = nodeIndex;
        this.isLeaf = true;
    }

    public Node(String[] words, int nodeIndex) {
        this.words = words;
        this.nodeIndex = nodeIndex;
        this.isLeaf = true;
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

    public boolean insert(String value) {
        int i=0;
        while (this.words[i] != null && this.words[i].compareTo(value)<0){
            i++;
        }
        System.out.println("In Insert: " + i);
        if (this.children[i] == null){
            this.children[i] = new Node(value, 0);
            this.isLeaf = false;
            System.out.println("Created new node");
        }else {
            this.children[i].insert(value);
            this.isLeaf = false;
        }
        return true;
    }

    public String toString(){
        String retStr = this.words[0] + ", " + this.words[1] + ", " + this.words[2] + " - ";
        //String retStr = "";
        System.out.println(isLeaf);
        if (!this.isLeaf){
            for(int i=0;i<3;i++){
                if (this.children[i] != null){
                    retStr += "[ " + this.children[i].toString() + " ]";
                }else {
                    retStr += "[ XXX ]";
                }
                //retStr += this.words[i];
            }
        }
        return retStr;
    }
}