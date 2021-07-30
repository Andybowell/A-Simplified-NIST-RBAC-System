import java.util.*;
import java.io.*;

class Main {

    RoleHierarchy myHierarchy = new RoleHierarchy();
    AccessMatrix matrix;
    String otherResources[];
    ArrayList<ArrayList<String>> roleSSDSets = new ArrayList<ArrayList<String>>();

    Main() {
        myHierarchy = new RoleHierarchy();
    }


    public static void main(String[] args) throws Exception  {
    
        Main main = new Main();
        main.run();

    }

    public void run()  throws Exception {

        // Q2 . Create Hierarchies 
        createRoleHierarchy();

        System.out.println(myHierarchy);

        // Q3. Create Matrix
        createAccessMatrix();
        matrix.printMatrix(myHierarchy);

        // Q4. 
        // 3 Update the role-object matrix to grant “control” by EACH role to itself. 
        matrix.populateSelfPermissions();
        matrix.populateDescendantPermissions();

        matrix.displayPermissions();
        System.out.println();
    
        // 5 This program also reads permissions to roles from file 
        // “permissionsToRoles.txt”.

        File file = new File("permissionsToRoles.txt");
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()) {
            String p2roles_array[] = scan.nextLine().split("\\s+");
            for (int i = 0 ; i < p2roles_array.length ; i++) {
                matrix.setPermission(p2roles_array[0], p2roles_array[2], p2roles_array[1]);
            }
        }
        matrix.displayPermissions();
    

        // Q5. Read from file “roleSetsSSD.txt”.
        loadroleSetsSSD();

        // print SSD sets 
        printSSDSets();


    }

    public void createRoleHierarchy() throws Exception  {

        File file = new File("roleHierarchy.txt");
        Scanner scan = new Scanner(file);

        int lineNumber = 0;
        // Read line by line
        while(scan.hasNextLine()) {
            // Add a new hierarchy
            String line = scan.nextLine();
            lineNumber++;

            String[] arr = line.split("\\s+");

            boolean addResult = myHierarchy.addHierarchy(arr[0], arr[1]) ;
            if(!addResult)
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
                myHierarchy = new RoleHierarchy();
            
                // Open File again
                file = new File("roleHierarchy.txt");
                scan = new Scanner(file);
            }
        }

        scan.close();
    }

    public boolean isUnique(String[] array) throws Exception  {

        for (int i = 0 ; i < otherResources.length ; i++)
        {
            int count = 0;
            for (int j = i + 1 ;  j < otherResources.length ; j++)
                if (otherResources[i].equals(otherResources[j]))
                    count++;
            if (count > 0) {
                System.out.println("Duplicate item found: "+otherResources[i]);
                return false;
            }
        }
        return true;
    }

    public void createAccessMatrix() throws Exception  {

        while(true) {
            File file = new File("resourceObjects.txt");
            Scanner scan = new Scanner(file);

            otherResources = scan.nextLine().split("\\s+");
            if (!isUnique(otherResources)) {
                // close the file
                scan.close();

                // once reading any user input, go back to 2.2. Continue if it’s valid.
                System.out.print("Fix it and press Enter" );
                new Scanner(System.in).nextLine();
            }
            else {
                matrix = new AccessMatrix(myHierarchy, otherResources);
                break;
            }
        }
         
    }

    public void loadroleSetsSSD() throws Exception {

        File file = new File("roleSetsSSD.txt");
        Scanner scan = new Scanner(file);
        
        roleSSDSets = new ArrayList<ArrayList<String>>();

        int lineNumber = 0;
        // Read line by line
        while(scan.hasNextLine()) {
            // Add a new hierarchy
            String line = scan.nextLine();
            lineNumber++;

            String[] arr = line.split("\\s+");

            ArrayList<String> ssdSet = new ArrayList<String>();
            for (int i = 0 ; i < arr.length ; i++)
                ssdSet.add(arr[i]);

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
                roleSSDSets = new ArrayList<ArrayList<String>>();
            
                // Open File again
                file = new File("“roleSetsSSD.txt");
                scan = new Scanner(file);
            }
            else {
                roleSSDSets.add(ssdSet);
            }
        }

    }

    public void printSSDSets() {

        System.out.println();

        for (int constraint = 0 ; constraint < roleSSDSets.size() ; constraint++) {
            ArrayList<String> ali = roleSSDSets.get(constraint);
            System.out.print("Constraint " + (constraint+1) + "," + " n = " + ali.get(0) + ", set of roles = {");
            int i = 1;
            for ( ; i <  ali.size()-1; i++) {
                System.out.print(ali.get(i) + ", ");
            }
            System.out.println(ali.get(i) + "}");
        }
    }


}
