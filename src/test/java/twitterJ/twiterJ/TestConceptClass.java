package twitterJ.twiterJ;

public class TestConceptClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
		String s = " 73     form        kalyanpur ";

		 String[] arr = s.split("\\s+");
	
		System.out.println("bus no ="+arr[0]);
		System.out.println("bus no ="+arr[1]);
		System.out.println("bus no ="+arr[2]);
		System.out.println("bus no ="+arr[3]);
		 for ( String ss : arr) {

		       System.out.println(ss);
		  }*/
		
		
		    String s= " "+"    Thisis different type   of file.";
		    String s1[]=s.split("[ ]+");
		    System.out.println("Zerothindex="+s1[0]);
		    System.out.println(s1[1]);
		     System.out.println(s1[2]);
		    System.out.println(s1[3]);
		    
		  /*  for(int i=0;i<s1.length;i++)
		    {
		        System.out.println(s1[i]);
		    }*/
				
		    
		    System.out.println("*******************************************");
		    String str = "University";
	        System.out.println(str.substring(0,4));
		    
		    
	}

}
