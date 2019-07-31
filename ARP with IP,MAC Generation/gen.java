import java.util.*;
import java.io.*;
import java.lang.*;
public class gen
{
    public static void main (String[] args) throws Exception {
      String[] arr= new String[10];
      String[] arr1= new String[10];

      Random r1 = new Random();
      Random r2 = new Random();
      Random r3 = new Random();
      Random r4 = new Random();
      Random r5 = new Random();
      Random r6 = new Random();


    for (int i = 0; i <10; i++) {


    String ip1 = Integer.toString (r1.nextInt(255)) ;
    String ip2 = Integer.toString(r2.nextInt(255) );

    String ip3 = Integer.toString(r3.nextInt(255)) ;

    String ip4 = Integer.toString(r4.nextInt(255));

    String ip=ip1+"."+ip2+"."+ip3+"."+ip4;
    arr[i]=ip;
    String p1 = Integer.toHexString(r1.nextInt(255)) ;
    String p2 = Integer.toHexString(r2.nextInt(255) );
    String p3 = Integer.toHexString(r3.nextInt(255)) ;
    String p4 = Integer.toHexString(r4.nextInt(255));
    String p5 = Integer.toHexString(r5.nextInt(255));
    String p6 = Integer.toHexString(r6.nextInt(255));
    String p=p1+":"+p2+":"+p3+":"+p4+":"+p5+":"+p6;
    arr1[i]=p;

    }

    FileWriter fw1= new FileWriter("ip.txt");
    FileWriter fw2 = new FileWriter("mac.txt");
    for (int i = 0; i <10; i++){
      fw2.write(arr1[i] + "\n");
      fw1.write(arr[i] + "\n");
    }
    fw1.close();
    fw2.close();

  }

}
