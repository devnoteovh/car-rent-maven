package ovh.devnote.rent.car;

import java.io.*;

public class FileTest {
    public static void main(String[] args) {
        String path = "./plik.txt";

        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(path));
            String lineFromFile;
            while((lineFromFile = reader.readLine()) != null) {
                System.out.println(lineFromFile);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Pliku nie ma, zepsulo sie !!");
        } catch (IOException e) {
            System.out.println("Nie da sie pliku odczytać !!");
        }

        String tekst = "To bedzie nasz tekst w pliku";
        String file = "./plik2.txt";

        try {
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(file));
            writer.write(tekst);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Zepsulo sie zapisywanie do pliku !!");
        }
    }
}
