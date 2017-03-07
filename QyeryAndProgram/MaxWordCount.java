import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
class Test 
{
	public static void main(String[] args)
	{	
		ArrayList l=new ArrayList();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Sentence::");
		String sentence=sc.nextLine();
		sentence=sentence.toLowerCase();
	    sentence= sentence.replaceAll( "  ", " " );     
        String[] tokens = sentence.split("[.!?]");   
	    int count1=0;
	    for(String s:tokens)
		{
          
           count1= s.split(" ").length;   
		   l.add(count1);
        } 
		Integer max=0;
        Iterator itr=l.iterator();
		 while(itr.hasNext())
		{
           Integer I=(Integer)itr.next();
				if(max<I)
			{
			   max=I;
			}


		}		
		System.out.println(max);

		
}
}
