public class Main {
    public static void main(String[] args) {
        Node myNode = new Node("Hello", 0);
        //System.out.println(myNode);

        myNode.insert("World");
        myNode.insert("Animal");
        System.out.println(myNode);

    }
}