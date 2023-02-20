import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFromFile {
    private ArrayList<Integer> listOfJugs = new ArrayList<>();
    private int targetJugValue;
    private ArrayList<Object> listOfAllJugs = new ArrayList<>();
    private static int infiniteJug = Integer.MAX_VALUE;
    public ArrayList<Object> readFile() {
        try {
            File myObj = new File("jugInfo.txt");
            Scanner myReader = new Scanner(myObj);
            String givenJugs = myReader.nextLine();
            String[] arrOfJugs = givenJugs.split(",");
            listOfJugs.add(infiniteJug);
            for (String arrOfJug : arrOfJugs) {
                listOfJugs.add(Integer.parseInt(arrOfJug));
            }
            targetJugValue = Integer.parseInt(myReader.nextLine());
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found. Please verify that the file is present.");
            e.printStackTrace();
        }
        listOfAllJugs.add(listOfJugs);
        listOfAllJugs.add(targetJugValue);
        return listOfAllJugs;
//        System.out.println(listOfJugs);
//        System.out.println(targetJugValue);
    }
}