import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PQueue <T extends Comparator<T>> {
   private int heap_size=0;
   private List<T> heap=null;
   
   public PQueue()
   {
	   heap=new ArrayList<T>();
   }
   public void add(T element)
   {
	   if(element==null) throw new IllegalArgumentException();
	   heap.add(heap_size,element);
	   heap_size++;
	   if(heap.size()>1)
	   swim(heap_size-1);
   }
   
   public void swim(int k)
   {
	   if(k-1<0)
		   return;
	   int parent=((k-1)/2);
	   if(less(parent,k))
	   {
		   swap(parent,k);
	   }
	   swim(parent);
   }
   public T poll()
   {
	   if(heap.size()<=0)
		   throw new RuntimeException("Empty queue");
	   T removedData=heap.get(0);
	   if(heap.size()>1)
	   {
		  swap(0,heap.size()-1);
		  heap.remove(heap.size()-1);
		  sink(0); 
	   }
	   
	   return removedData;
	   
   };
   public T remove(int k)
   {
	   T removedData=null;
	   if(heap.size()<=0)
		   throw new RuntimeException("Empty queue");
	   if(k<0 || k>=heap.size())
	   {
		   throw new IllegalArgumentException();
	   }
	   if(k==0)
		  removedData= poll();
	   else
	   {
		   removedData=heap.get(k);
		   swap(k,heap.size()-1);
		   heap.remove(heap.size()-1);
		   int parent=(k-1)/2;
		   if(less(parent,k))
			   swim(k);
		   else
		   {
			   sink(k);
		
		   }
	   }
	   return removedData;
   }
   private void sink(int k)
   {
	   int rightChild=2*k+2;
	   int leftChild=2*k+1;
	   if(leftChild<heap.size() && less(leftChild,rightChild) && !less(leftChild,k))
	   {
		   swap(k,rightChild);
		   sink(rightChild);
	   }
	   else if(rightChild<heap.size() &&less(rightChild,leftChild) &&! less(rightChild,k))
	   {
		   swap(k,leftChild);
		   sink(leftChild);
	   }
	   else if(leftChild<heap.size()&&heap.get(leftChild)==heap.get(rightChild))
	   {
		   swap(k,leftChild);
		   sink(leftChild);
	   }
	   else
	   {
		   return;
	   }
   }
   private boolean less(int i,int j)
   {
	   T node1=heap.get(i);
	   T node2=heap.get(j);
	   return node1.compare(node1,node2)<=0;
   }
   private void swap(int i,int j)
   {
	   T temp=heap.get(i);
	   heap.set(i,heap.get(j));
	   heap.set(j,temp);
   }
   
   public void print()
   {
	   for(int i=0;i<heap.size();i++)
	   {
		   Temp t=(Temp)heap.get(i);
		   System.out.println(t.i);
	   }
   }
}
