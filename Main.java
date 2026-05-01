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
        myRoot = myRoot.insert(null, "Cartography");
        System.out.println(myRoot);

        System.out.println("------------------");

        Node otherRoot = new Node("Favorite", 0);
        otherRoot.insert("Meridian");
        System.out.println(otherRoot);
        otherRoot.insert("Lukewarm");
        System.out.println(otherRoot);
        otherRoot.insert("Anchovies");
        System.out.println(otherRoot);
        otherRoot.insert("Jackrabbit");
        System.out.println(otherRoot);
        otherRoot.insert("Krampus");
        System.out.println(otherRoot);
        otherRoot.insert("Heliotrope");
        System.out.println(otherRoot);
        otherRoot.insert("Novacaine");
        System.out.println(otherRoot);
        otherRoot.insert("Ovaltine");
        System.out.println(otherRoot);
        otherRoot.insert("Perspire");
        System.out.println(otherRoot);
        otherRoot.insert("Quixotic");
        System.out.println(otherRoot);
    }
}