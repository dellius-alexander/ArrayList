import java.util.logging.Logger;


//////////////////////////////////////////////////////////
//driver to test the dynamic array implementation
public class Main {

    private static Logger log = Logger.getLogger("ArrayItr");   // For logging execution purposes

    /**
     * Get the line number for tracking executions calls
     * @return  The current tread line number of calling object
     */
    private static int getLineNumber(){ return Thread.currentThread().getStackTrace()[2].getLineNumber();}


    /**
     * Main method
     * @param args  commandline arguments if provided
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        log.info("\tBeginning of Main...");
        //create a dynamic array
        DArray<Integer> a = new DArray<Integer>(5);   // Modified for any data type
        DArray<String> s = new DArray<String>(2);   // Added String datatype
        s.set(4, "Hello");
        s.set(5, "World");
        System.out.println("Contents of s: " + s);
        System.out.println(a.length());
        System.out.println(a.maxLength());
        //give it some values
        a.set(0,5);
        a.set(1,4);
        a.set(2,3);
        a.set(3,2);
        a.set(4,1);
        log.info("Line: [" + getLineNumber() + "]\tOriginal content of a: " + a);
        System.out.println("Original content of a: " + a);

        //Illustration of iterator
        Iterator itr = a.iterator();
        log.info("Line: [" + getLineNumber() + "]\tIterator usage:" + itr.toString());
        System.out.println("Iterator usage:");
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

        //create a new array from a
        DArray<Integer> b = new DArray(a);
        log.info("Line: [" + getLineNumber() + "]\tContent of b: " + b);
        System.out.println("Content of b: " + b);


        //testing equals method

        //check to see if the two arrays are equal
        if(a.equals(b)){
            System.out.println("Array a is same as array b");
        }else{
            System.out.println("Array a is same as array b");
        }

        a.set(1,9);  //change the content of array a

        //print the content of both arrays
        System.out.println("Content of a: " + a);

        System.out.println("Content of b: " + b);

        //check to see if the two arrays are equal
        if(a.equals(b)){
            System.out.println("Array a is same as array b");
        }else{
            System.out.println("Array a is NOT same as array b");
        }

        // testing resize method
        //testing resize functionality - must complete the code
        a.set(0, 10); //set value 10 into index 0
        a.set(100, 50);//set value 50 to index 100
        System.out.println("Contents of Array a is: " + a);
        a.resize(3);
        System.out.println("Size of Array a is: " + a.length());
        System.out.println("Contents of Array a is: " + a);


    }//end of main


}
