package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DataImporter {
    Parser parseData;

    public DataImporter() {

        parseData = new Parser();
    }

    protected void parsePerson(String lineIn) {
        String[] a = lineIn.split(",");

        if (a[0].equals("D")) { //action if file contains a Doctor object.
            parseData.parseWorker(a);
            parseData.parseDoctor(a);
        }

        else if (a[0].equals("I")) { //action if file contains a Patient object.
            parseData.parsePatient(a);
        }

        else if (a[0].equals("O")) {
            parseData.parsePatient(a);
        }
        else {                      //action if file contains a Worker object.
            parseData.parseWorker(a);
        }
    }


    protected void parseDoctorPatientRelationship(String line) {
        String[] a = line.split(",");
        parseData.parseRelationship(a);
    }



    protected void parseTreatment(String line) {
        String[] a = line.split(",");
        parseData.parseTreatment(a);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String personFile = args[0];
        //String doctorPatientFile = args[1];
        //String treatmentFile = args[2];
        DataImporter importer = new DataImporter();
        File pFile= new File(personFile);
        //File dFile = new File(doctorPatientFile);
        //File tFile = new File(treatmentFile);
        Scanner sc = new Scanner(pFile);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            importer.parsePerson(line);

        }
     /**   Scanner sc1 = new Scanner(dFile);
        while (sc1.hasNextLine()) {
            String line = sc.nextLine();
            importer.parseDoctorPatientRelationship(line);
        }

        Scanner sc2 = new Scanner(tFile);
        while (sc2.hasNextLine()) {
            String line = sc.nextLine();
            importer.parseTreatment(line);
        } */
    }
}

