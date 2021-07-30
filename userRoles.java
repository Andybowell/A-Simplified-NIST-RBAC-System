import java.util.*;

public class UserRoles {

    ArrayList<ArrayList<String>> roleSSDSets;
    int[][] userRoleMatrix = new int[100][100];

    UserRoles(ArrayList<ArrayList<String>> roleSSDSets) {
        this.roleSSDSets = roleSSDSets;
    }

    populateMatrixFromFile() {


        File file = new File("usersRoles.txt");
        Scanner scan = new Scanner(file);
        
        roleSSDSets = new ArrayList<ArrayList<String>>();

        int lineNumber = 0;
        // Read line by line
        while(scan.hasNextLine()) {
            // Add a new hierarchy
            String line = scan.nextLine();
            lineNumber++;

            String[] arr = line.split("\\s+");

            if(false)
            {
                // close the file
                scan.close();

                // display “invalid line is found in roleHierarchy.txt
                System.out.println("Invalid input at line "+ lineNumber);

                // once reading any user input, go back to 2.2. Continue if it’s valid.
                System.out.print("Fix it and press Enter" );
                new Scanner(System.in).nextLine();

                // Reset Variables
                lineNumber = 0;
            
                // Open File again
                file = new File("usersRoles.txt");
                scan = new Scanner(file);
            }
            else {

                

            }
        }
      

    }   

}
