
public class Main {


    public static void main(String[] args) {


        //TASK 2: ADDBEFORE (ADD, ADDFIRST, ADDLAST) TESTING
        //TASK 3: DELETEBEFOR TESTING
        Functions.example1();    
 
        //COMPLETE TEST OF MYDBLLINKEDLIST
        //   INCLUDES: CONTAINS, GET, INDEXOF
        //             LASTINDEXOF SET
        Functions.testMyList();
        

        System.out.println("\n\nCREATING ORDERED LIST WITH PERSON OBJECTS\n");

        MyDblLinkedList<Person> people = new MyDblLinkedList<Person>();

        people = Functions.loadData(people);
        for (Person p : people)
            System.out.println(p);
        
    }
}
