public class Person implements Comparable<Person>{
    protected char type;
    protected String lname;
    protected String fname;
    protected int age;

    Person(){
        type = ' ';
        lname = "";
        fname = "";
        age = 0;
    }
    Person(char t, String l, String f, int a){
        type = t;
        lname = l;
        fname = f;
        age = a;
    }

    public char getType() { return type;}
    public String getFName() { return fname;}
    public String getLName() { return lname;}
    public int getAge() { return age;}
    public void setLName(String l) { lname = l;}
    public String toString(){
        return type + lname + fname;
    }

    @Override
    public int compareTo(Person other) {
        int value = this.lname.compareTo(other.lname);
        if (value == 0)
            value = this.fname.compareTo(other.fname);
        return value;
    }
}
