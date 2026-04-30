public class Main {
    public static void main(String[] args) {
        Node myRoot = new Node("Hello", 0);
        //System.out.println(myNode);

        myRoot = myRoot.insert(null, "World");
        myRoot = myRoot.insert(null, "Animal");
        System.out.println(myRoot);
        myRoot = myRoot.insert(null, "Bumblebee");
        System.out.println(myRoot);
        myRoot = myRoot.insert(null, "Protuberance");
        System.out.println(myRoot);
        myRoot = myRoot.insert(null, "Isolated");
        System.out.println(myRoot);

    }
}