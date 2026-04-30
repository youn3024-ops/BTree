import java.util.ArrayList;

public class Node {
    int nodeIndex;
    ArrayList<String> words = new ArrayList<String>(3);
    Node[] children = new Node[3];
    Node parent;
    boolean isLeaf;

    public Node(String word, int nodeIndex) {
        this.words.add(word);
        this.nodeIndex = nodeIndex;
        this.isLeaf = true;
        this.parent = null;
    }

    public Node(ArrayList<String> words, int nodeIndex) {
        this.words = words;
        this.nodeIndex = nodeIndex;
        this.isLeaf = true;
    }

    public ArrayList<String> getVal(){
        return this.words;
    }

    public Node[] getChildren(){
        return this.children;
    }


    //https://www.reddit.com/r/computerscience/comments/1sukkgh/made_a_diagram_to_finally_understand_btree/
    public boolean search(String value){
        int i = 0;
        while (this.words.get(i).compareTo(value)<0){
            i++;
        }
        if (this.words.get(i).equals(value)){
            return true;
        }
        if (this.isLeaf){
            return false;
        }
        return this.children[i].search(value);
    }

    public Node getParent(){
        return this.parent;
    }

    public void setChild(Node newChild, int idx){
        this.children[idx] = newChild;
    }

    public void setLeafStatus(boolean newStatus){
        this.isLeaf = newStatus;
    }

    public Node insert(Node parent, String value) {
        int i=0;
        while (i<this.words.size() && this.words.get(i).compareTo(value)<0){
            i++;
        }
        System.out.println("In Insert: " + i);
        
        if (this.words.size() < 2){
            this.words.add(i, value);
            System.out.println("Added to existing node");
            this.parent = parent;
            return this;
        }
        //if (this.words.size() < 3){//Size is now 3: middle one has got to get booted up
            this.words.add(i, value);
            String midVal = this.words.get(1);
            this.words.remove(1);
            if (parent == null){
                this.parent = new Node(midVal, 0);
                this.parent.setChild((new Node(this.words.get(0), 0)), 0);
                this.parent.setChild((new Node(this.words.get(1), 1)), 1);
                this.parent.setLeafStatus(false);

                return this.parent;
            }
            else{
                parent = parent.insert(parent.getParent(), midVal);
                this.parent.setLeafStatus(false);
                return this;
            }
            
        //}




/*
        if (i==0){//New item to be added is at the leftmost side of the node

        }
        if (this.children[i] == null){
            this.children[i] = new Node(value, 0);
            this.isLeaf = false;
            System.out.println("Created new node");
        }else {
            this.children[i].insert(value);
            this.isLeaf = false;
        }
        return true;*/
    }

    public String toString(){
        String retStr = "";
        for (int i=0;i<3;i++){
            if (!this.isLeaf){
                if (this.children[i] != null){
                    retStr += "[ " + this.children[i].toString() + " ]";
                } else {
                    retStr += "[EMPTY CHILD] ";
                }
            }
            if (this.words.size() > i){
                retStr += this.words.get(i) + ", ";
            }
        }

        /*for(int i=0;i<this.words.size();i++){
            retStr += this.words.get(i) + ", ";
        }
        //String retStr = this.words.get(0) + ", " + this.words.get(1) + ", " + this.words.get(2) + " - ";
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
        }*/
        return retStr;
    }
}