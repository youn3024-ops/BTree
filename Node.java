import java.util.ArrayList;
import java.util.Collections;

public class Node {
    int nodeIndex;
    ArrayList<String> words = new ArrayList<String>(3);
    ArrayList<Node> children = new ArrayList<Node>(3);
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

    public Node(Node parent, ArrayList<String> words, int nodeIndex){
        this.words = words;
        this.parent = parent;
        this.nodeIndex = nodeIndex;
        this.isLeaf = true;
    }

    public Node(Node parent, String word, int nodeIndex){
        this.words.add(word);
        this.parent = parent;
        this.nodeIndex = nodeIndex;
        this.isLeaf = true;
    }

    public ArrayList<String> getVal(){
        return this.words;
    }

    public ArrayList<Node> getChildren(){
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
        return this.children.get(i).search(value);
    }

    public Node getParent(){
        return this.parent;
    }

    public void setChild(Node newChild, int idx){
        System.out.println("idx: " + idx);
        System.out.println(this.children);
        if (idx <= this.children.size()){
            this.children.add(idx, newChild);
        }
        else{
            this.children.set(idx, newChild);
        }
        newChild.setIdx(idx);
    }

    public void setLeafStatus(boolean newStatus){
        this.isLeaf = newStatus;
    }

    public void setIdx(int idx){
        this.nodeIndex = idx;
    }

    public int getIdx(){
        return this.nodeIndex;
    }

    public void addWord(String word){
        this.words.add(word);
        Collections.sort(this.words);
    }

    public void addChild(Node newChild, int newIdx){
        this.children.add(newIdx, newChild);
        //this.children.add(newRight, newLeftIdx+1);
    }

    public Node insert(Node parent, String value) {
        int i=0;
        while (i<this.words.size() && this.words.get(i).compareTo(value)<0){
            i++;
        }
        System.out.println("In Insert: " + i);
        System.out.println(this.children);
        if(i < this.children.size()) {
        //if (this.children.get(i) != null){
            this.children.set(i, this.children.get(i).insert(this, value));
            return this;
        }
        
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
                //WRONG
                //Add midVal to Parent Node
                //Delete this node from Parent
                System.out.println("Words: ");
                System.out.println(this.words);
                parent.setChild(new Node(this.words.get(1), this.nodeIndex+1), this.nodeIndex+1);
                this.parent.addWord(midVal);
                //parent.children.add(this.nodeIndex+1, new Node(this.words.get(i), this.nodeIndex+1));
                return new Node(this.words.get(0), this.nodeIndex);
                //Add this.words.get(0) as node to parent
                //Add this.words.get(1) as node to parent
                
                //parent = parent.insert(parent.getParent(), midVal);
                //this.parent.setLeafStatus(false);
                //return this;
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

    public boolean insert(String word){
        if (this.words.size() == 1){
            if (this.words.get(0).compareTo(word)<0){
                this.words.add(1, word);
            }
            else{
                this.words.add(0, word);
            }
            return true;
        }else{
            System.out.println(this.words.size());
            int i = 0;
            while(i < this.words.size() && this.words.get(i).compareTo(word) < 0){
                i++;
            }
            if (!this.isLeaf){
                this.children.get(0).insert(word);
                return true;
            }else{
                this.words.add(i, word);
                System.out.println(this.words.size());
                //Node newRight = new Node(this.parent, this.words.get(1), this.nodeIndex+1);
                String leftWord = this.words.get(0);
                String midWord = this.words.get(1);
                String rightWord = this.words.get(2);
                //this = new Node(this.parent, this.words.get(0), this.nodeIndex);
                this.words.clear();

                if (this.parent == null){//That means this is a root node
                    Node leftChild = new Node(this, leftWord, 0);
                    Node rightChild = new Node(this, rightWord, 1);
                    System.out.println("I am a root");
                    this.words.add(midWord);
                    this.addChild(leftChild, 0);
                    this.addChild(rightChild, 1);
                    this.isLeaf = false;
                    System.out.println(this.children.size());
                }else{
                    Node newChild = new Node(this.parent, rightWord, this.nodeIndex+1);
                    this.words.add(leftWord);
                    this.parent.addChild(newChild, this.nodeIndex+1);
                    this.parent.insert(midWord);
                }
                return true;
            }
        }
    }




    public String toString(){
        String retStr = "";
        for (int i=0;i<3;i++){
            if (!this.isLeaf){
                if (i < this.children.size()){
                    retStr += "[ " + this.children.get(i).getIdx() + ": " + this.children.get(i).toString() + " ]";
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