import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Iterator;

public class Functions {

    public static boolean isDigits(String inString){
        if (inString == null || inString.isEmpty()) 
            return false;
        for (char c : inString.toCharArray()) {
            if (!Character.isDigit(c)) 
               return false;
        }
        return true;
    }   
 
    public static void example1(){
        System.out.println("\nEXAMPLE 1: TESTING ADD & ADDBEFORE & DELETEBEFORE"); 
 
        MyDblLinkedList<String> names = new MyDblLinkedList<>();

        System.out.print("   TESTOMG MYDBLLINKEDLIST ADD:"); 
        names.add("Tom");
        names.add("John");
        names.add("Harry");
        names.add("Sal");
         names.add("Sam");
        System.out.println(names);

        //TASK 1: REMOVEFIRST
        names.removeFirst();
        System.out.println("    After removing first:        " + names);   

        //TASK 2: ADDBEFORE
        System.out.println("   TESTING MYDBLLINKEDLIST ADDBEFORE:"); 
        names.addBefore("Tom", "John");
        System.out.println("    After adding Tom before John: " + names);          
        names.addBefore("Sam", "Bob");
        System.out.println("    After adding Bob before Sam: " + names);   
        names.addBefore("Tom", "Al");
        System.out.println("    After adding Al before Tom:  " + names);   

        //TASK 3: DELETEBEFORE
        System.out.println("   \nTESTING MYDBLLINKEDLIST DELETEBEFORE:");       
        names.deleteBefore("Bob");
        System.out.println("    After deleting before Bob:   " + names);  
        names.deleteBefore("Carl");
        System.out.println("    After deleting before Carl:  " +names);    
        names.deleteBefore("Al");
        System.out.println("    After deleting before Bob:   " + names);  
        System.out.println();      
    }


    public static void example2(){
        System.out.println("\nTESTING MYLINKEDLIST FUNCTIONALITY"); 

        // Create a list for strings
        //MyLinkedList<String> list = new MyLinkedList<String>();
        MyDblLinkedList<String> list = new MyDblLinkedList<String>(); 

        //Checking functionality add
        list.add("America"); // Add it to the list
        System.out.println("(1) " + list);

        list.add(0, "Canada"); // Add it to the beginning of the list
        System.out.println("(2) " + list);

        list.add("Russia"); // Add it to the end of the list
        System.out.println("(3) " + list);

        list.addLast("France"); // Add it to the end of the list
        System.out.println("(4) " + list);

        list.add(2, "Germany"); // Add it to the list at index 2
        System.out.println("(5) " + list);

        list.add(5, "Norway"); // Add it to the list at index 5
        System.out.println("(6) " + list);

        list.add(0, "Poland"); // Same as list.addFirst("Poland")
        System.out.println("(7) " + list);

        //Checking functionality remove
        // Remove elements from the list
        list.remove(0); // Same as list.remove("Poland") in this case
        System.out.println("(8) " + list);

        list.remove(2); // Remove the element at index 2 ("Germany")
        System.out.println("(9) " + list);

        list.remove(list.size() - 1); // Remove the last element ("Norway")
        System.out.println("(10) " + list);
        
        //Checking functionality using iterator 
        System.out.print("\n(11) Print using iterator:  {");
        Iterator<String> iterator1 = list.iterator();
        while (iterator1.hasNext())
            System.out.print(iterator1.next().toUpperCase() + " ");
        System.out.println("}");
        
        // Print list in reverse order using iterator 
        System.out.print("\n(12) Print using reverse iterator:  {");
        Iterator<String> iterator2 = list.reverseIterator();
        while (iterator2.hasNext())
            System.out.print(iterator2.next().toUpperCase() + " ");
         System.out.println("}");       

        //Checking functionality of contains, indexOf, get, set, lastIndexOf 
        System.out.println("\n(12a) contains Japan:  " + list.contains("Japan"));
        System.out.println("(12b) contains Canada: " + list.contains("Canada"));

        System.out.println("(13a) Index of Japan:  " + list.indexOf("Japan"));   
        System.out.println("(13b) Index of France: " + list.indexOf("France"));

        System.out.println("\n(14a) Value at 0:   " + list.get(0));   
        System.out.println("(14b) Value at 3:   " + list.get(3));   
        System.out.println("(14c) Value at 18:  " + list.get(18));   
        System.out.println("(14d) Value at size:" + list.get(list.size() - 1));

        list.set(3, "Canada");
        list.set(0, "Finland");
        System.out.println("\n(15a) Set 0 to Finland & 3 to Canada: " + list); 
        System.out.println("(15b) last index of Canada: " + list.lastIndexOf("Canada"));       
        System.out.println("(15c) last index of Norway: " + list.lastIndexOf("Norway"));    
        System.out.println("(15d) last index of France: " + list.lastIndexOf("France"));     

       //Checking functionality removeFirst
        list.removeFirst();
        System.out.println("\n(16a) removeFirst:                  " + list);
        list.removeFirst();
        System.out.println("(16b) removeFirst:                  " + list);
        
        //Checking functionality addBefore
        list.addBefore("Finland", "America");
        System.out.println("\n(17a) add America before Finland:   " + list);

        list.addBefore("Russia", "Japan");
        System.out.println("(17b) add Japan before Russia:      " + list);

        list.addBefore("Canada", "Iceland");
        System.out.println("(17c) add Iceland before Canada:    " + list);

        System.out.println("\n(18) is List Circluar:              "  +  list.isCircular());        
 
        //Checking functionality deleteBefore 
        list.deleteBefore("Finland");
        System.out.println("\n(19a) delete before Finland:        "  +  list);
        list.deleteBefore("Canada");
        System.out.println("(19b) delete before Canada:         "  +  list);


        list.clear();
        System.out.println("\nAfter clearing the list, the list size is " + list.size());

    }


    public static MyDblLinkedList<Person> loadData(MyDblLinkedList<Person> people){
        try{
            Scanner scanner = new Scanner(new File("people.txt"));

            String inputLine;
            //while (scanner.hasNextLine() && peopleCounter < arrSize){
            while (scanner.hasNextLine()){
                inputLine = scanner.nextLine();
                String[] tokens = inputLine.split(",");

                try{
                    int tempAge = Integer.parseInt(tokens[3]);
                    char tempType = tokens[0].toUpperCase().charAt(0);
                     if (tempType != 'P' && tempType != 'T' && tempType != 'E' && tempType != 'S')
                        throw new TypeException(tempType);
                    
                    //add test & throw exception for age (age > 0 and < 110)



                    Person tempP = new Person(tokens[0].charAt(0), tokens[1], tokens[2], tempAge);
                    people.addOrdered(tempP);
                }
                //add catch for TypeException
                catch(TypeException e){
                    System.out.println("Type exception: " + e.getMessage());
                }
                catch(NumberFormatException e){
                    System.out.println("Number format exception: " + e.getMessage());
                }
                catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Not enough tokens in input file: " + e.getMessage());
                }
                catch(Exception e){
                    System.out.println("Oops...: " + e.getMessage());
                }
            }
            System.out.println("closing scanner");
            scanner.close();
        }
        catch(IOException e){
            System.out.println("Error opening the file: " + e.getMessage());
        }
        return people;
 
    }


}
