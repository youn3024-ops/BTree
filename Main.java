import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Node otherRoot = new Node(null, "Favorite", 0);
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

        otherRoot.insert("Jade");
        System.out.println(otherRoot);
        otherRoot.insert("Jake");
        System.out.println(otherRoot);
        otherRoot.insert("Jale");
        System.out.println(otherRoot);
        otherRoot.insert("Jams");
        System.out.println(otherRoot);

        System.out.println(otherRoot.search("Perspire"));

        
        System.out.println("------------------");
        try{
            ArrayList<String> text = readText();
            Node textRoot = new Node(null, text.get(0), 0);
            for(int i=1;i<text.size();i++){
                if (!textRoot.search(text.get(i))){
                    textRoot.insert(text.get(i));
                }

            }
            System.out.println(textRoot);
        } catch (IOException e){
            System.out.println("File not found");
        }
    }

    public static ArrayList<String> readText() throws IOException{
        //Code utilized from previous file-reading project
        Scanner input = new Scanner(System.in);
        System.out.println("File name to read: ");
        String fileName = input.nextLine();

        File fileIn = new File(fileName);
        Scanner s = new Scanner(fileIn);

        String stringData = "";
        System.out.print("Scanning text: \n0 lines scanned");
        int lineCount = 0;
        while(s.hasNextLine()){
            stringData += s.nextLine() + " ";
            lineCount++;
            System.out.print("\r" + lineCount + " lines scanned");
        }
        s.close();
        ArrayList<String> wordsWordsWords = new ArrayList<>(Arrays.asList(stringData.split(" ")));
        for(int i=0;i<wordsWordsWords.size();i++){
            if(wordsWordsWords.get(i).equals(" ") || wordsWordsWords.get(i).equals("")){
                wordsWordsWords.remove(i);
                i--;
            }else{
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace(".", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace(",", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace(";", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace(":", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace("?", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace("!", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace("'", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace("-", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace("&", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace(" ", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace("(", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace(")", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace("[", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).replace("]", ""));
                wordsWordsWords.set(i, wordsWordsWords.get(i).toUpperCase());
            }
        }
        //System.out.println(String.join(", ", wordsWordsWords));
        return wordsWordsWords;
    }
}