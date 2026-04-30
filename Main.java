public class Main {
    public static void main(String[] args) {
        Node myRoot = new Node("Hello", 0);
        //System.out.println(myNode);

        myRoot = myRoot.insert(null, "World");
        myRoot = myRoot.insert(null, "Animal");
        myRoot = myRoot.insert(null, "Bumblebee");
        System.out.println(myRoot);

    }
}