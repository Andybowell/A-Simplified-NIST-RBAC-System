import java.util.*;

public class AccessMatrix {

    String array[][];
    String otherResources[];
    ArrayList<String> columnNames;
    RoleHierarchy myHierarchy;
    AccessMatrix(RoleHierarchy myHierarchy, String otherResources[]) {
        this.otherResources = otherResources;
        this.myHierarchy = myHierarchy;
        this.columnNames = new ArrayList<String>();
        int rows = myHierarchy.roleList.size();
        int cols = rows + otherResources.length;
        array = new String[rows][cols];

        for (int i = 0 ; i < array.length ; i++) 
            for (int j = 0 ; j < array[0].length ; j++)
                array[i][j] = "";
        
        populate_index();
    }

    public void populate_index() {
        int i = 0;
        for (i = 0 ; i < array.length ; i++)
            columnNames.add(myHierarchy.roleList.get(i).name) ;
    
        for (int j = 0 ; j < otherResources.length ; j++)
            columnNames.add(otherResources[j]);  
    }

    public int get_index(String name) {
        for (int i = 0 ; i < columnNames.size() ; i++)
        {
            if (columnNames.get(i).equals(name))
                return i;
        }
        return 0;
    }

    public void setPermission(String start, String end, String permission) {
        int i = get_index(start);
        int j = get_index(end);

        if(!array[i][j].contains(permission))
            array[i][j] += (permission + ",");  
    //    System.out.println("permission = " + permission);
        while(myHierarchy.roleList.get(i).descendants.size() > 0) {
            Role role = myHierarchy.roleList.get(i).descendants.get(0);
            i = get_index(role.name);
            if(!array[i][j].contains(permission))
                array[i][j] += (permission + ",");  
        }
    }

    public void populateSelfPermissions() {
        for (int i = 0 ; i < array.length ; i++) 
            array[i][i] += ("control" + ",");        
    }

    public void dfs(int root, int parent) {
        // Get all Roles Below parents 
        for (int i = 0 ; i < myHierarchy.roleList.get(parent).ascendants.size() ; i++) {
            String child_name = myHierarchy.roleList.get(parent).ascendants.get(i).name;
            int child_idx = Integer.parseInt(child_name.substring(1)) - 1;
            array[root][child_idx] += ("own," + array[child_idx][child_idx]);

            // Dfs again
            dfs(root, child_idx); 
        }
    }

    public void populateDescendantPermissions() {
        for (int i = 0 ; i < array.length ; i++) {
            // DFS to all Node below it 
            dfs(i, i);
        }
    }

    public void displayPermissions() {
        for (int i = 0 ; i < array.length ; i++) {
            String Parentname = myHierarchy.roleList.get(i).name;
            for (int j = 0 ; j < array[0].length ; j++) {
                if(array[i][j].length() > 0) {
                    String childName;
                    if (j < array.length)
                        childName = myHierarchy.roleList.get(j).name;
                    else
                        childName = otherResources[j-array.length];
                    System.out.println(Parentname + " --> " + childName + ": " + array[i][j]);
                }
            }
        }
    }

    public void printMatrix(RoleHierarchy myHierarchy) {

        // Print the header
        System.out.print("\t");
        for (int i = 0 ; i < array.length ; i++)
            System.out.print(myHierarchy.roleList.get(i).name + "\t");
    
        for (int i = 0 ; i < otherResources.length ; i++)
            System.out.print(otherResources[i] + "\t");  

        System.out.println();

        for (int i = 0 ; i < array.length ; i++) {
            System.out.print(myHierarchy.roleList.get(i).name + "\t");
            for (int j = 0 ; j < array[0].length ; j++) {
                System.out.print(array[i][j] + "\t");   
            }    
            System.out.println("\n");
        }
    }

}
