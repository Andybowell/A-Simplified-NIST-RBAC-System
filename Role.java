import java.util.*;

class Role {

    String name;
    ArrayList<Role> ascendants;
    ArrayList<Role> descendants;

    Role(String name)
    {
        this.name = name;
        this.ascendants = new ArrayList<Role>();
        this.descendants = new ArrayList<Role>();
    }

    void addAscendant(Role role)
    {
        ascendants.add(role);
    }

    void addDescendant(Role role) {
        descendants.add(role);
    }

    int numAscendants(){
        return ascendants.size();
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

    public String toString() {

        Collections.sort(ascendants, new SortbyName()); 
        String ret = name + " ---> ";

        for (int i = 0 ; i < ascendants.size() ; i++) {
            ret += ascendants.get(i).name;
            if (i != ascendants.size() - 1)
                ret +=  ", ";
        }
        return ret;
    }

}
