package com.company;

public class DataCheck {

    //Constructor.
    public DataCheck() {
    }
    //Checks for correct array length
    public void checkPersonArrayLength(String[] a){
        if(a.length != 14) {
            throw new IllegalArgumentException("This line does not have the required amount of information for the Person file.");
        }
    }
    public void checkRelationshipArrayLength(String[] a) {
        if(a.length != 2) {
            if(a.length != 14) {
                throw new IllegalArgumentException("This line does not have the required amount of information for the Relationship file.");
            }
        }
    }

    public void checkTreatmentArrayLength(String[] a) {
        if (a.length != 5 ) {
            throw new IllegalArgumentException("This line does not have the required amount of information for the Treatment file.");
        }
    }

   //Checks for extraneous information in the array
   public void checkOnlyDoctor(String[] a) {
        boolean extraInfo = false;
        for (int j =4; j <= 13; j++) {
            if (a[j] != null) {
                extraInfo = true;
                break;
            }
       }
        if (extraInfo) {
            throw new IllegalArgumentException("There is extraneous information in this Doctor line.");
        }
   }

   public void checkOnlyPatient(String[] a) {
        if (a[3] != null) {
            throw new IllegalArgumentException("This patient line contains a value for privileges. Privileges should be null.");
        }
   }

   public void checkOnlyOutpatient(String[] a) {
        if (a[5] != null) {
            throw new IllegalArgumentException("This outpatient line contains a value for room number. Room number should be null");
        }
   }

   public void checkOnlyBasicWorker(String[] a) {
       boolean extraInfo = false;
       for (int i = 3; i <= 13; i++) {
           if (a[i] != null) {
               extraInfo = true;
               break;
           }
       }
       if (extraInfo) {
           throw new IllegalArgumentException("There is extraneous information in this Worker line.");
       }
   }


public void checkNameLength(String name) {
        if (name.length() > 50) {
            throw new IllegalArgumentException("First name or last name length cannot be more than 50 characters. Please abbreviate.");
        }
}

    //Checks if strings contain numeric values.
    private boolean hasNumbers(String word) {
        for (char c: word.toCharArray()){
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public void checkPersonNameAndType(String type, String firstName, String lastName) {
        //Check for incorrect data: type needs to be of V, D, A, N, T, I, or O
        if ((type != "V") || (type != "D") || (type != "A") || (type != "N") || (type != "T")
                || (type != "I") || (type != ")")) {
            throw new IllegalArgumentException("Please enter a valid type.");
        }
        //Check for first names over length limit.
        if(firstName.length() > 50) {
            throw new IllegalArgumentException("Please enter a first name less than or equal to 50 characters. You may abbreviate.");
        }
        //Check for last names over length limit.
        if (lastName.length() > 50) {
            throw new IllegalArgumentException("Please enter a first name less than or equal to 50 characters. You may abbreviate.");
        }
        //Check that both first names and last names do not contain any numeric values.
        if ((hasNumbers(firstName)) || (hasNumbers(lastName))) {
            throw new IllegalArgumentException("First name and last name cannot contain numeric values.");

        }
    }
//check that doctor's privileges either equal Admitting or Consulting
    public void checkDoctorPrivileges(String privileges) {
        if ((privileges != "A") || (privileges != "C")) {
            throw new IllegalArgumentException("Doctor's privileges are either A for Admitting or C for Consulting");
        }
    }
//Check that patientID is not over the length limit.
    public void checkPatientID(int patientID) {
        if (patientID >= 1000000) {
            throw new IllegalArgumentException("Our patient IDs are 6 characters or less in length");
        }
    }

    //Check that roomNumber is [1,20] inclusive.
    public void checkRoomNumber(int number) {
        if ((number <= 0) ||(number > 20)){
            throw new IllegalArgumentException("Room number must be greater than 0 and less than or equal to 20");
        }
    }
//Check that emergency contact last name is properly formatted, plus phone number is 10 digits.
    public void checkEmergencyContact(String name, String number) {
        if (name.length() > 100) {
            throw new IllegalArgumentException("Contact name must not have more than 100 characters.");
        }
        if (hasNumbers(name)){
            throw new IllegalArgumentException("Contact name cannot include numeric characters.");
        }
        String regex = "(0/91)?[7-9][0-9]{9}";

        if (!(number.matches(regex))) {
            throw new IllegalArgumentException("Phone number invalid.");
        }
    }

    public void checkInpatientDates(String admitted, String discharged) {
        checkDate(admitted);
        checkDate(discharged);
    }


    public void checkDate(String text) {
        String regex = "^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";
        if (!(text.matches(regex))) {
            throw new IllegalArgumentException("Date must be in the format 01/01/19");
        }
    }

    public void checkTreatment(String treatment) {
        if (!(treatment.equals("P")) || (treatment.equals("M"))) {
            throw new IllegalArgumentException("Treatment type must be either M for Medication or P for Procedure.");
        }
    }


    public void checkInsurance(int number, String company) {
        if (hasNumbers(company)) {
            throw new IllegalArgumentException("Insurance company name cannot have numeric values in it");
        }
        if (number >= 1000000) {
            throw new IllegalArgumentException("Insurance company number cannot be greater than 6 digits.");
        }
    }


        public void checkTreatmentType(String type) {
            if (!(type.equals("P")) || (type.equals("M"))) {
                throw new IllegalArgumentException("Treatment Type should equal M or P.");

            }
        }
}