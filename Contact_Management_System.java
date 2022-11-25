import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.nio.file.*;

class ContactsBook {
    // class variables
    // patterns for differnt fields
    static String firstNamePattern = "^[a-zA-Z][a-zA-Z ]*$";
    static String lastNamePattern = "^[a-zA-Z][a-zA-Z ]*$";
    static String EmailAddressPattern = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    static String PhoneNumberPattern = "^\\d{10}$";
    static String CompanyPattern = "^[a-zA-Z][a-zA-Z ]*$";

    static boolean isValidFirstName(String FName) {
        if (Pattern.matches(firstNamePattern, FName))
            return true;
        else
            return false;
    }

    static boolean isValidLastName(String LName) {
        if (Pattern.matches(lastNamePattern, LName))
            return true;
        else
            return false;
    }

    static boolean isValidEmailAddress(String EMAIL) {
        if (Pattern.matches(EmailAddressPattern, EMAIL))
            return true;
        else
            return false;
    }

    static boolean isValidPhoneNumber(String PhoneNumber) {
        if (Pattern.matches(PhoneNumberPattern, PhoneNumber))
            return true;
        else
            return false;
    }

    static boolean isValidCompany(String Company_name) {
        if (Pattern.matches(CompanyPattern, Company_name))
            return true;
        else
            return false;
    }

    // scanner variable is declared as static to use throughout the program
    static final Scanner scan = new Scanner(System.in);
    // contains set of contacts which are empty(contact created and no details
    // added)
    static HashSet<String> emptyContacts = new HashSet<>();
    // contains set of contacts which are non empty(contact created and details
    // added)
    static HashSet<String> nonEmptyContacts = new HashSet<>();

    static void InsertNewContact() throws IOException {
        int lines = 0;

        try {
            File contactFile = new File("Contact_file.txt");
            contactFile.createNewFile();
            Scanner sc_file = new Scanner(contactFile);
            while (sc_file.hasNextLine()) {
                sc_file.nextLine();
                lines++;
            }
            // System.out.println("Total Number of Lines: " + lines);

            // close scanner
            sc_file.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");

        }

        BufferedWriter BuffWriter = null;

        System.out.print("Enter First Name: ");
        String FirstName = scan.nextLine();

        while (!isValidFirstName(FirstName)) {
            System.out.println("Enter valid First Name!!\n");
            System.out.print("Enter First Name: ");
            FirstName = scan.nextLine();
        }

        System.out.print("Enter Last Name: ");
        String LastName = scan.nextLine();

        while (!isValidLastName(LastName)) {
            System.out.println("Enter valid Last Name!!\n");
            System.out.print("Enter Last Name: ");
            LastName = scan.nextLine();
        }

        System.out.print("Enter Contact Number: ");
        String PhoneNumber = scan.nextLine();

        while (!isValidPhoneNumber(PhoneNumber)) {
            System.out.println("Enter valid Contact Number!!\n");
            System.out.print("Enter Contact Number: ");
            PhoneNumber = scan.nextLine();
        }

        System.out.print("Enter Alternate Contact Number: ");
        String AlternatePhoneNumber = scan.nextLine();

        while (!isValidPhoneNumber(AlternatePhoneNumber)) {
            System.out.println("Enter valid Alternate Contact Number!!\n");
            System.out.print("Enter Alternate Contact Number: ");
            AlternatePhoneNumber = scan.nextLine();
        }

        System.out.print("Enter Email id: ");
        String Email = scan.nextLine();

        while (!isValidEmailAddress(Email)) {
            System.out.println("Enter valid Email id!!\n");
            System.out.print("Enter Email id: ");
            Email = scan.nextLine();
        }

        System.out.print("Enter Company: ");
        String company = scan.nextLine();

        while (!isValidCompany(company)) {
            System.out.println("Enter valid Company!!\n");
            System.out.print("Enter Company: ");
            company = scan.nextLine();
        }
        FirstName = FirstName.trim();
        LastName = LastName.trim();
        PhoneNumber = PhoneNumber.trim();
        AlternatePhoneNumber = AlternatePhoneNumber.trim();
        Email = Email.trim();
        company = company.trim();
        String Contact_Data = "";
        Contact_Data += (++lines) + " " + FirstName + " " + LastName + " " + PhoneNumber + " " + AlternatePhoneNumber
                + " "
                + Email + " "
                + company + "\n";

        try {

            BuffWriter = new BufferedWriter(new FileWriter(
                    "Contact_file.txt", true));
            BuffWriter.write(Contact_Data);
            BuffWriter.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        } catch (IOException e) {
            System.out.println("An error occurred.");

        }
    }

    static void SearchContact() throws IOException {

        System.out.print("Enter First Name of the Contact you want to search: ");
        String FirstName = scan.nextLine();
        System.out.print("Enter Last Name of the Contact you want to search: ");
        String LastName = scan.nextLine();
        FirstName = FirstName.trim();
        LastName = LastName.trim();
        String SearchName = FirstName + " " + LastName;

        try {
            FileReader contactFile = new FileReader(
                    "Contact_file.txt");

            BufferedReader BuffReader = new BufferedReader(contactFile);

            String line;

            while ((line = BuffReader.readLine()) != null) {
                if (line.contains(SearchName)) {
                    System.out.println("Contact Found!!");
                    String[] ContactSearched = line.split(" ");

                    System.out.println("First Name: " + ContactSearched[1]);
                    System.out.println("Last Name: " + ContactSearched[2]);
                    System.out.println("Phone Number: " + ContactSearched[3]);
                    System.out.println("Alternate Phone Number: " + ContactSearched[4]);
                    System.out.println("Email ID: " + ContactSearched[5]);
                    System.out.println("Company: " + ContactSearched[6]);
                    BuffReader.close();
                    return;

                }

            }
            System.out.println("Contact Not Present");
            BuffReader.close();

        } catch (Exception e) {
            System.out.println("An error occurred.");

        }

    }

    static void DisplayAllContacts() {
        try {
            FileReader contactFile = new FileReader(
                    "Contact_file.txt");

            BufferedReader BuffReader = new BufferedReader(contactFile);

            String line;

            while ((line = BuffReader.readLine()) != null) {
                String[] ContactSearched = line.split(" ");

                System.out.println("Contact " + ContactSearched[0]);
                System.out.println("First Name: " + ContactSearched[1]);
                System.out.println("Last Name: " + ContactSearched[2]);
                System.out.println("Phone Number: " + ContactSearched[3]);
                System.out.println("Alternate Phone Number: " + ContactSearched[4]);
                System.out.println("Email ID: " + ContactSearched[5]);
                System.out.println("Company: " + ContactSearched[6]);
                System.out.println("\n*************************************************\n");

            }
            BuffReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");

        }
    }

    static void EditContact() throws IOException {

        System.out.print("Enter First Name of the Contact you want to Edit: ");
        String FirstName = scan.nextLine();
        System.out.print("Enter Last Name of the Contact you want to Edit: ");
        String LastName = scan.nextLine();
        FirstName = FirstName.trim();
        LastName = LastName.trim();
        String SearchName = FirstName + " " + LastName;
        boolean present = false;
        try {
            File ContactFileCreate = new File("Contact_file.txt");
            ContactFileCreate.createNewFile();
            FileReader contactFile = new FileReader(
                    "Contact_file.txt");

            BufferedReader BuffReader = new BufferedReader(contactFile);

            String line;
            ArrayList<String> ContactsList = new ArrayList<>();

            while ((line = BuffReader.readLine()) != null) {
                if (line.contains(SearchName)) {
                    present = true;
                    String[] ContactSearched = line.split(" ");

                    System.out.println("First Name: " + ContactSearched[1]);
                    System.out.println("Last Name: " + ContactSearched[2]);
                    System.out.println("Phone Number: " + ContactSearched[3]);
                    System.out.println("Alternate Phone Number: " + ContactSearched[4]);
                    System.out.println("Email ID: " + ContactSearched[5]);
                    System.out.println("Company: " + ContactSearched[6]);

                    char choice;

                    System.out.println("\nDo you want to Change First Name?(y/n): ");
                    choice = scan.nextLine().charAt(0);
                    if (choice == 'Y' | choice == 'y') {
                        System.out.print("Enter New First Name: ");
                        ContactSearched[1] = scan.nextLine();

                        while (!isValidFirstName(ContactSearched[1])) {
                            System.out.println("Enter valid First Name!!\n");
                            System.out.print("Enter New First Name: ");
                            ContactSearched[1] = scan.nextLine();
                        }

                    }

                    System.out.println("\nDo you want to Change Last Name?(y/n): ");
                    choice = scan.nextLine().charAt(0);
                    if (choice == 'Y' | choice == 'y') {
                        System.out.print("Enter New Last Name: ");
                        ContactSearched[2] = scan.nextLine();

                        while (!isValidLastName(ContactSearched[2])) {
                            System.out.println("Enter valid Last Name!!\n");
                            System.out.print("Enter New Last Name: ");
                            ContactSearched[2] = scan.nextLine();
                        }

                    }

                    System.out.println("\nDo you want to Change Phone Number?(y/n): ");
                    choice = scan.nextLine().charAt(0);
                    if (choice == 'Y' | choice == 'y') {
                        System.out.print("Enter New Phone Number: ");
                        ContactSearched[3] = scan.nextLine();

                        while (!isValidPhoneNumber(ContactSearched[3])) {
                            System.out.println("Enter valid Phone Number!!\n");
                            System.out.print("Enter New Phone Number: ");
                            ContactSearched[3] = scan.nextLine();
                        }

                    }

                    System.out.println("\nDo you want to Change Alternate Phone Number?(y/n): ");
                    choice = scan.nextLine().charAt(0);
                    if (choice == 'Y' | choice == 'y') {
                        System.out.print("Enter New Alternate Phone Number: ");
                        ContactSearched[4] = scan.nextLine();

                        while (!isValidPhoneNumber(ContactSearched[4])) {
                            System.out.println("Enter valid Alternate Phone Number!!\n");
                            System.out.print("Enter New Alternate Phone Number: ");
                            ContactSearched[4] = scan.nextLine();
                        }

                    }

                    System.out.println("\nDo you want to Change Email ID?(y/n): ");
                    choice = scan.nextLine().charAt(0);
                    if (choice == 'Y' | choice == 'y') {
                        System.out.print("Enter New Email ID: ");
                        ContactSearched[5] = scan.nextLine();

                        while (!isValidEmailAddress(ContactSearched[5])) {
                            System.out.println("Enter valid Email ID!!\n");
                            System.out.print("Enter New Email ID: ");
                            ContactSearched[5] = scan.nextLine();
                        }

                    }

                    System.out.println("\nDo you want to Change Company?(y/n): ");
                    choice = scan.nextLine().charAt(0);
                    if (choice == 'Y' | choice == 'y') {
                        System.out.print("Enter New Company: ");
                        ContactSearched[6] = scan.nextLine();

                        while (!isValidCompany(ContactSearched[6])) {
                            System.out.println("Enter valid Company!!\n");
                            System.out.print("Enter New Company: ");
                            ContactSearched[6] = scan.nextLine();
                        }

                    }
                    line = "";
                    line = ContactSearched[0] + " " + ContactSearched[1] + " " + ContactSearched[2] + " "
                            + ContactSearched[3] + " " + ContactSearched[4]
                            + " "
                            + ContactSearched[5] + " "
                            + ContactSearched[6] + "\n";
                    ContactsList.add(line);

                    break;

                } else {

                    line += "\n";
                    ContactsList.add(line);

                }

            }

            BuffReader.close();
            if (present) {
                Files.deleteIfExists(Paths.get("Contact_file.txt"));
                // File fileCon = new File("Contact_file.txt");
                // fileCon.createNewFile();
                ContactFileCreate.createNewFile();
                BufferedWriter BuffWriter = null;
                BuffWriter = new BufferedWriter(new FileWriter(
                        "Contact_file.txt", true));

                for (String i : ContactsList) {
                    BuffWriter.write(i);
                }
                BuffWriter.close();
            } else {
                System.out.println("Contact Not Present");
            }

        } catch (Exception e) {
            System.out.println("An error occurred.");

        }

    }

    static void DeleteContact() throws IOException {

        System.out.print("Enter First Name of the Contact you want to Delete: ");
        String FirstName = scan.nextLine();
        System.out.print("Enter Last Name of the Contact you want to Delete: ");
        String LastName = scan.nextLine();
        FirstName = FirstName.trim();
        LastName = LastName.trim();
        String SearchName = FirstName + " " + LastName;

        // int lines = 0;

        // try {
        //     File contactFile = new File("Contact_file.txt");
        //     contactFile.createNewFile();
        //     Scanner sc_file = new Scanner(contactFile);
        //     while (sc_file.hasNextLine()) {
        //         sc_file.nextLine();
        //         lines++;
        //     }
        //     // System.out.println("Total Number of Lines: " + lines);

        //     // close scanner
        //     sc_file.close();
        // } catch (Exception e) {
        //     System.out.println("An error occurred.");

        // }

        int i = 1, j = 1;
        File ContactFileCreate = new File("Contact_file.txt");
        ContactFileCreate.createNewFile();
        FileReader contactFile = new FileReader(
                "Contact_file.txt");

        BufferedReader BuffReader = new BufferedReader(contactFile);

        String line;
        while ((line = BuffReader.readLine()) != null) {
            if (line.contains(SearchName)) {
                // System.out.println("Contact Found!!");
                
                BuffReader.close();
                break;
                
            }
            i++;

        }
        try {
            ContactFileCreate = new File("Contact_file.txt");
            ContactFileCreate.createNewFile();
            contactFile = new FileReader(
                    "Contact_file.txt");

            BuffReader = new BufferedReader(contactFile);

            line = "";
            ArrayList<String> ContactsList = new ArrayList<>();
            boolean present = false;
            while ((line = BuffReader.readLine()) != null)
            {

                if (line.contains(SearchName))
                {
                    present = true;
                    // System.out.println("First Name: " + ContactSearched[1]);
                    // System.out.println("Last Name: " + ContactSearched[2]);
                    // System.out.println("Phone Number: " + ContactSearched[3]);
                    // System.out.println("Alternate Phone Number: " + ContactSearched[4]);
                    // System.out.println("Email ID: " + ContactSearched[5]);
                    // System.out.println("Company: " + ContactSearched[6]);
                    System.out.println("Contact Successfully Deleted!!");

                } 
                else 
                {

                    if (j < i) {
                        line += "\n";
                        ContactsList.add(line);

                    } else if (j > i) {

                        String[] ContactSearched = line.split(" ");
                        line = "";
                        line = (j - 1) + " " + ContactSearched[1] + " " + ContactSearched[2] + " "
                                + ContactSearched[3] + " " + ContactSearched[4]
                                + " "
                                + ContactSearched[5] + " "
                                + ContactSearched[6] + "\n";
                        ContactsList.add(line);
                    }

                }
                j++;

            }
            BuffReader.close();
            if (present) {
                Files.deleteIfExists(Paths.get("Contact_file.txt"));
                ContactFileCreate.createNewFile();
                BufferedWriter BuffWriter = null;
                BuffWriter = new BufferedWriter(new FileWriter(
                        "Contact_file.txt", true));

                for (String con : ContactsList) {
                    BuffWriter.write(con);
                }
                BuffWriter.close();
            } else {
                System.out.println("Contact Not Present");
            }


        } catch (Exception e) {
            System.out.println("An error occurred.");

        }

    }

}

public class Contact_Management_System {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("            *********************MAIN MENU**********************");
            System.out.println("            * 1. Insert New contact                            *");
            System.out.println("            * 2. Search Contact                                *");
            System.out.println("            * 3. Display all Contacts                          *");
            System.out.println("            * 4. Edit a Contact                                *");
            System.out.println("            * 5. Delete a contact                              *");
            System.out.println("            * 6. Exit                                          *");
            System.out.println("            ****************************************************");
            System.out.print("Enter your Choice:");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println();
                    ContactsBook.InsertNewContact();
                    break;
                case 2:
                    System.out.println();
                    ContactsBook.SearchContact();
                    break;
                case 3:
                    System.out.println();
                    ContactsBook.DisplayAllContacts();

                    break;
                case 4:
                    ContactsBook.EditContact();
                    // addressBook.viewContactInfo();
                    break;
                case 5:
                    ContactsBook.DeleteContact();
                    break;
                case 6:
                    System.out.println("Exiting....!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("\nInvalid Choice\n");
                    break;
            }

        }

    }
}
