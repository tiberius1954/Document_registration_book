package Classes;

public class Category {
	 private int cid;
	    private String name;
	    
	    public Category( int cid, String name) {
	    	  this.cid = cid; 
	    	  this.name = name;
	    	
	    }	  
	    @Override
	    public String toString() 
	    { 
	    	         return name; 	  
	    } 
	    
	    public int getCid() {
	        return cid;
	    }

	    public void setCid(int cid) {
	        this.cid = cid;
	    }

	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
}
