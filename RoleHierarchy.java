import java.util.*;

class RoleHierarchy {

    // List of Roles 
    ArrayList<Role> roleList = new ArrayList<Role>();
    ArrayList<String> descendants = new ArrayList<String>();

    RoleHierarchy() {

    }

    // Method to Create a New Role
    public void addRole(Role role){
        roleList.add(role);
    }

    // Method to Create a new Hierarchy
    public boolean addHierarchy(String R1, String R2)
    {
        // Get the Roles for R1
        Role role1 = getRole(R1);
        Role role2 = getRole(R2);

        // If R1 is already assignned to something, return false
        if (descendants.contains(R1))
            return false;

        // Add role1 to role2
        role2.addAscendant(role1);
        role1.addDescendant(role2);
        descendants.add(R1);
        return true;
    }


    // Method to get a role given name
    Role getRole(String name) 
    {
        // Iterate through the list 
        for (int i = 0 ;  i < roleList.size() ; i++)
        {
            if (roleList.get(i).name.equals(name))
                return roleList.get(i);
        }

        Role role = new Role(name);
        addRole(role);
        return role;
    }

    class SortbyName implements Comparator<Role> 
    { 
        // Used for sorting in ascending order of 
        // name
        public int compare(Role a, Role b) 
        { 
            if (a.name.length() > b.name.length())
                return 1;
            else if (a.name.length() < b.name.length())
                return -1;
            return a.name.compareTo(b.name); 
        } 
    } 

    // Method to print all heirarchies
    public String toString() {
        Collections.sort(roleList, new SortbyName());
        String ret = "";
        for (int i = 0 ; i < roleList.size() ; i++) {
            if (roleList.get(i).numAscendants() > 0)
                ret += roleList.get(i) + "\n";
        }
        return ret;
    }
}
    
