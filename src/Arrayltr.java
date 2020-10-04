import java.util.logging.*;

/**
 * @author: Dellius Alexander
 * @date: 10/03/2020
 */
//////////////////////////////////////////////////////////////////////////
//////////////////////  abstract class Iterator  /////////////////////////
 /**
 Illustration of implementing iterator
 Note: Iterator class exists in Java ADT already
  abstract based-class for Iterator
 */
abstract class Iterator<T>
{
    public abstract boolean hasNext();
    public abstract T next();
}
//////////////////////////////////////////////////////////////////////////
/////////////////////////  class DArrayItr  //////////////////////////////
/**
 A specific iterator for dynamic array class 
 */
class DArrayItr<T> extends Iterator
{
    private DArray<T> storage; //points at the data structure
    private int curLoc; //points at the current location
    private  static final Logger log = Logger.getLogger("DArrayItr<T>");  // For logging execution purposes

    /**
     * Copy constructor
     * @param c from - a DArray object to be copied from
     */
    public DArrayItr(DArrayItr<T> c) throws  NullPointerException, ArrayIndexOutOfBoundsException
    {
        log.info("Line: ["+ getLineNumber()+"]\t- Copy constructor called...");
        //deal with invalid object and range errors here
        if(c.storage == null) {
            throw new NullPointerException("Sorry the object cannot be null...");
            // ++++ error handling codes go here snf get out ++++====
        }
        if (c.curLoc >= c.storage.length())
        { throw new ArrayIndexOutOfBoundsException("Array out of bounds...");}
        this.storage = c.storage;
        this.curLoc = c.curLoc;
    }
    /**
     * Parameterized constructor
     * @param a Points to the data structure
     * @throws NullPointerException
     */
    public DArrayItr(DArray<T> a) throws NullPointerException, ArrayIndexOutOfBoundsException
    {
        log.info("Line: ["+ getLineNumber()+"]\t- Main constructor called...");
        //deal with invalid object and range errors here
        if(a == null) {
            throw new NullPointerException("Sorry the object cannot be null...");
            // ++++ error handling codes go here snf get out ++++====
        }
        this.storage = a; //point at the same source
        this.curLoc = 0; //point at the first index
    }
    /**
     * Get the line number for tracking execution calls
     * @return  The current tread line number of calling object
     */
    private int getLineNumber(){ return Thread.currentThread().getStackTrace()[2].getLineNumber();}
    /**
     *
     * @return true if and only if the current location is in
     * a valid indexing range
     */
    public boolean hasNext() throws  ArrayIndexOutOfBoundsException
    {
        log.info("Line: ["+ getLineNumber()+"]\t- hasNext() called...");
        return this.curLoc < this.storage.length();
    }
    /**
     @return the element at the current index location and
     advance the current location to the next index slot
     */
    public T next(){
        log.info("Line: ["+ getLineNumber()+"]\t- next() called...");
        return this.storage.elementAt(curLoc++);
    }
    /**
     * Equals method
     * @param obj   The instance of the object DArrayItr
     * @return  true or false if the object is equal to current instance
     */
    public boolean equals(DArrayItr<T> obj)
    {
        log.info("Line: ["+ getLineNumber()+"]\t- equals() called...");
        if (getClass() == obj.getClass()){return true;}
        else return false;
    }
}
//////////////////////////////////////////////////////////////////////////
/////////////////////////// Class DArray /////////////////////////////////
/**
 * Custom ArrayList class
 * @param <T> Generic Object Class parameter
 */
class DArray<T> {
    private final double GROW_FACTOR = 0.5;// array size growing rate

    //attributes
    private int size; //the array size that the user is aware of
    private T[] buffer; //the actual array
    private static final Logger log = Logger.getLogger("DArray<T>");  // For logging execution purposes

    //constructors
    /**
     * create an array with the capacity larger than the request size by
     * the defined grow factor constant
     * @param size - the initial user requested size of the array
     */
    public DArray(int size) throws Exception
    {

        if(size < 0){
            throw new Exception("Not a valid range");
        }
        this.size = size;//the user's requested array size
        //capactity is user's requested size plus equivalent grow portion
        int bufferSize = (int) Math.ceil(this.size + this.size * GROW_FACTOR);
        //create the actual array buffer
        this.buffer = (T[]) new Object[bufferSize];
    }
    /**
     * Get the line number for tracking executions calls
     * @return  The current tread line number of calling object
     */
    private int getLineNumber(){ return Thread.currentThread().getStackTrace()[2].getLineNumber();}


    /**
     * Gets the iterator class for traversal
     * @return  return an iterator for traversal
     */
    public Iterator<T> iterator(){
        return new DArrayItr(this);
    }
     /**
     * The size of the array
     * @return the user's aware size of the array
     */
    public int length(){
        return this.size;
    }
    /**
     * The max size of the dynamic array
     * @return the max length/ the capacity of the dynamic array
     */
    public int maxLength(){
        return this.buffer.length;
    }

    /**
     * Gets the element at the given location/index
     * @param index - the location of the element in the array
     * @return the element at the given location/index
     */
    public T elementAt(int index) throws  ArrayIndexOutOfBoundsException{
        // handle invalid index
        // something like the followings
       if(index < 0 || index >= this.size)
		   throw new ArrayIndexOutOfBoundsException("Index out of bound");

        return this.buffer[index];
    }   // End of elementAt
    /**
     * The contents of the dynamic array object
     @return: the content of the dynamic array as a string
     */
    public String toString(){
        String output = "[ ";
        for(int i = 0; i < this.size; i++) {
            output += "" + this.buffer[i] + (i < this.size - 1 ? " , " : "");
        }
        return output + " ]";
    }   // End of toString
    /**
     * Copy constructor
     * @param c from - a DArray object to be copied from
     * @throws Exception
     */
    public DArray(DArray<T> c) throws  Exception
    {
        log.info("Line: ["+ getLineNumber()+"]\t- Copy constructor called...");
        //deal with invalid object and range errors here
        if(c.size < 0) {
            throw new Exception("Not a valid range");
            // ++++ error handling codes go here snf get out ++++====
        }
        this.size = c.size;
        this.buffer = c.buffer;
    }   // End of DArray
    /**
     * Checks if current instance is the same as this instance of the class object
     * @param obj   The instance of the object DArrayItr
     * @return  true or false if the object is equal to current instance
     */
    public boolean equals(DArray<T> obj)
    {
        log.info("Line: ["+ getLineNumber()+"]\t- equals() called...");
        if (getClass() == obj.getClass()){return true;}
        else return false;
    }   // End of equals
    /**
     put the value into the dynamic array at the given index
     the array should grow if the given index exceed the current size
     @param index - the index in the array the value should be placed
     @param value - the value to be stored at the given index
     */
    public void set(int index, T value) throws Exception{
        log.info("Line: ["+ getLineNumber()+"]\t- set() called. Index: " + index + "\tValue: " +  value);
        if (index < 0) {
            throw new Exception("Not a valid index.  Index out of bounds");
        }
        if (index > this.size)  //expand the dynamic array
        {   // resize array by adding 1 to index
            //handle the array expansion/grow here
            resize(index + 1);    // resize array by adding 1 to index
            this.buffer[index] = value;  //put the value into the given
            log.info("Line: [" + getLineNumber()+ "]\t" + "Index: " + index +  "\tvalue added: " +
                    this.buffer[index] + "\tSize: "+ this.buffer.length);
        }
        else
        {   //  Add value to array
            if (index == 0)
                this.buffer[index] = value; //put the value into the given index if index is 0
            else
                this.buffer[index] = value;   //put the value into the given index if index > 0
            log.info("Line: [" + getLineNumber()+ "]\t" + "Index: " + index +  "\tvalue added: " +
                    this.buffer[index] + "\tSize: "+ this.buffer.length);
        }
    }   // End of set
    /**
     * Resize buffer array
     * @param size  new size
     */
    public void resize(int size)
    {
        log.info("Line: [" + getLineNumber()+ "]\t- resize() called. New size: " + size);
        log.info("Line: [" + getLineNumber()+ "]\t" + "Current size: " + this.buffer.length +
                "\tNew size: " + size);
        T[] container;
        int resize = size;
        //capactity is user's requested size plus equivalent grow portion
        this.size = (int) Math.ceil(resize + resize * GROW_FACTOR);
        log.info("Line: [" + getLineNumber()+ "]\t" + "Updated size: " + this.size);
        container = (T[]) new Object[this.size];   // create container for objects
        log.info("Line: [" + getLineNumber()+ "]\t" + "Container size: " + container.length +
                "\tNew size: " + this.size);
        if (container.length > this.buffer.length) {
            for (int i = 0; i < this.buffer.length; i++)
                container[i] = this.buffer[i];      // copy old array object to new array
        }
        if (this.buffer.length > container.length)
            for (int i = 0; i < container.length; i++) {
                container[i] = this.buffer[i];      // copy old array object to new array
            }
        this.buffer =  container;   // pass address of new array to buffer so it can access the new object
        //this.buffer =  Arrays.copyOf(this.buffer,size);
        log.info("Line: [" + getLineNumber()+ "]\t" + "New array size: " + this.buffer.length);
    }   // End of resize
}//end of DArray class

