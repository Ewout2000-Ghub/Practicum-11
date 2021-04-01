package Pract11;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public static void main(String[] args) throws IOException {
        ArrayList<String> usdLines = new ArrayList<>();
        ArrayList<String> eurLines = new ArrayList<>();
        System.out.println("LET OP! bestandsnamen altijd met .txt er achteraan schrijven!");

        // Scanners voor user input
        Scanner bronScanner = new Scanner(System.in);
        System.out.println("Geef de naam van het bronbestand: ");
        String bronBestand = bronScanner.nextLine();

        Scanner bestemmingScanner = new Scanner(System.in);
        System.out.println("Geef de naam van het bestemmingsbestand: ");
        String bestemmingsBestand = bestemmingScanner.nextLine();

        Scanner waardeScanner = new Scanner(System.in);
        System.out.println("Geef de waarde van 1 USD in eurocenten: ");
        double waarde = Double.parseDouble(waardeScanner.nextLine());
        double wisselkoers = waarde / 100;

        // Try-catch voor het inlezen van USDArtikelen.txt
        try {
            File USDArtikelen = new File("C:\\Users\\ewout\\IdeaProjects\\Studie\\Practica\\Practicum 11\\src\\Pract11\\" + bronBestand);
            Scanner reader = new Scanner(USDArtikelen);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                usdLines.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Kon het bestand niet vinden");
        }

        for (String line : usdLines) {
            String[] e = line.split(": ");
            String artikelNaam = e[0];
            String stringBedrag = e[1];
            double bedrag = Double.parseDouble(stringBedrag) * wisselkoers;

            String a = String.format("%.2f", bedrag);
            String euroBedrag = a.replace(",", ".");
            String newLine = artikelNaam + ": " + euroBedrag;
            eurLines.add(newLine);
        }

        // Try-catch voor het aanmaken van het nieuwe tekstbestand
        try {
            File EURArtikelen = new File("C:\\Users\\ewout\\IdeaProjects\\Studie\\Practica\\Practicum 11\\" + bestemmingsBestand);
            if (EURArtikelen.createNewFile()) {
                System.out.println("Bestand aangemaakt: " + EURArtikelen);
            }
        } catch (FileAlreadyExistsException e) {
            System.out.println("Dit bestand bestaat al");
        }

        // Try-catch voor het schrijven naar het nieuwe tekstbestand
        try {
            FileWriter writer = new FileWriter(bestemmingsBestand);

            for (String i : eurLines) {
                writer.write(i + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Kon niet naar het bestand schrijven!");
        }
    }
}