import java.util.ArrayList;
import java.util.Collections;

public class Node {
    int nodeIndex;
    ArrayList<String> words = new ArrayList<String>(3);
    ArrayList<Node> children = new ArrayList<Node>(3);
    Node parent;
    boolean isLeaf;

    public Node(Node parent, String word, int nodeIndex){
        this.words.add(word);
        this.parent = parent;
        this.nodeIndex = nodeIndex;
        this.isLeaf = true;
    }

    //https://www.reddit.com/r/computerscience/comments/1sukkgh/made_a_diagram_to_finally_understand_btree/
    public boolean search(String value){
        if (this.words.contains(value)){
            //System.out.println(this.children);
            return true;
        }
        if (this.isLeaf){
            return false;
        }
        int i = 0;
        while (i < this.words.size() && this.words.get(i).compareTo(value)<0){
            i++;
        }
        return this.children.get(i).search(value);
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public void addWord(String word){
        this.words.add(word);
        Collections.sort(this.words);
    }

    public void addChild(Node newChild){
        int i=0;
        while(i < this.children.size() && this.children.get(i).words.get(0).compareTo(newChild.words.get(0)) < 0){
            i++;
        }
        this.children.add(i, newChild);
    }

    public void addWordInOrder(String word){
        int i=0;
        while(i < this.words.size() && this.words.get(i).compareTo(word) < 0){
            i++;
        }
        this.words.add(i, word);
    }

    public void propogate(){
        if (this.words.size() > 2){
            String leftWord = this.words.get(0);
            String midWord = this.words.get(1);
            String rightWord = this.words.get(2);
            if (!this.isLeaf){
                if(this.parent == null){
                    //System.out.println("Not leaf, no parents");
                    Node leftChild = new Node(this, leftWord, 0);
                    Node rightChild = new Node(this, rightWord, 0);
                    Node leftGrandChild = this.children.get(0);
                    Node rightGrandChild = this.children.get(1);
                    this.words.clear();
                    this.words.add(midWord);
                    this.children.clear();
                    leftGrandChild.setParent(leftChild);
                    rightGrandChild.setParent(rightChild);
                    leftChild.addChild(leftGrandChild);
                    rightChild.addChild(rightGrandChild);
                    this.children.add(leftChild);
                    this.children.add(rightChild);
                }else{
                    //System.out.println("Not leaf, has parents");
                    this.parent.addChild(new Node(this.parent, rightWord, 0));
                    this.words.clear();
                    this.words.add(leftWord);
                    this.parent.addWordInOrder(midWord);
                    this.parent.propogate();
                }
            }else{
                //System.out.println("Leaf");
                Node leftChild = new Node(this, leftWord, 0);
                Node rightChild = new Node(this, rightWord, 0);
                this.words.clear();
                this.words.add(midWord);
                this.children.clear();
                this.children.add(leftChild);
                this.children.add(rightChild);
                this.isLeaf = false;
            }
        }
    }

    public boolean insert(String word){
        if (this.isLeaf){
            if (this.words.size() == 1){
                this.addWordInOrder(word);
                return true;
            }else{//Node has 2 words
                this.addWordInOrder(word);
                this.propogate();
                return true;
            }
        }else{//Not a leaf
            int i=0;
            while(i < this.words.size() && this.words.get(i).compareTo(word) < 0){
                i++;
            }
            this.children.get(i).insert(word);
            }
        return false;
    }




    public String toString(){
        String retStr = "";
        for (int i=0;i<3;i++){
            if (!this.isLeaf){
                if (i==this.words.size()){
                    retStr += "> ";
                }
                if (i < this.children.size()){
                    retStr += "[ " + this.children.get(i).toString() + " ]";
                }
                if (i==0){
                    retStr += "< ";
                }
            }
            if (this.words.size() > i){
                retStr += this.words.get(i) + " ";
            }
        }
        return retStr;
    }
}