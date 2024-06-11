package Classes;

public class Item{
	 private int id;
	    private String name;
	    
	    public Item( int id, String name) {
	    	  this.id = id; 
	    	  this.name = name;
	    	
	    }	  
	    @Override
	    public String toString() 
	    { 
	    	         return name; 	  
	    } 
	    
	    public int getId() {
	        return id;
	    }

	    public void setId(int cid) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
}
