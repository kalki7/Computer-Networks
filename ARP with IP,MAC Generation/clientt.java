import java.net.*;
import java.io.*;
import java.util.*;
public class clientt
{
    Scanner inp = new Scanner(System.in);
    //String s = in.nextLine();
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
    private DataInputStream in       =  null;
    String ip,mac;
    public clientt(String address, int port)
    {
      try{
        File file1 = new File("ip.txt");
        BufferedReader bri1 = new BufferedReader(new FileReader(file1));
        PrintWriter pwi = new PrintWriter("ip_updated.txt");
        String sti;
        //reading IP for client
        sti = bri1.readLine();
        bri1.close();
        BufferedReader bri2 = new BufferedReader(new FileReader(file1));
        String line1 = bri2.readLine();
        //updating text file with flags
       while(line1 != null)
       {
           boolean flag = false;
               if(line1.equals(sti))
               {
                   flag = true;
               }
           if(!flag){
             pwi.println(line1);
             flag=false;
           }
           line1 = bri2.readLine();
       }
       pwi.flush();
       bri2.close();
       pwi.close();
       file1.delete();
       File f1 = new File ("ip_updated.txt");
       File filex=new File("ip.txt");
       f1.renameTo(filex);
       BufferedWriter writer1 = new BufferedWriter(new FileWriter("ip_used.txt", true));
       writer1.newLine();
       writer1.write(sti);
       writer1.close();
       System.out.println("IP : "+sti);
       ip=new String(sti);
    }
    catch(IOException i)
    {
        System.out.println(i);
    }
    try{
      File file2 = new File("mac.txt");
      BufferedReader brm1 = new BufferedReader(new FileReader(file2));
      PrintWriter pwm = new PrintWriter("mac_updated.txt");
      String stm;
      //reading MAC address for client
      stm = brm1.readLine();
      brm1.close();
      BufferedReader brm2 = new BufferedReader(new FileReader(file2));
      String line2 = brm2.readLine();
      //updating text files with flags
     while(line2 != null)
     {
         boolean flag = false;
             if(line2.equals(stm))
             {
                 flag = true;
             }
         if(!flag){
           pwm.println(line2);
           flag=false;
         }
         line2 = brm2.readLine();
     }
     pwm.flush();
     brm2.close();
     pwm.close();
     file2.delete();
     File f2 = new File ("mac_updated.txt");
     File filey=new File("mac.txt");
     f2.renameTo(filey);
     BufferedWriter writer2 = new BufferedWriter(new FileWriter("mac_used.txt", true));
     writer2.newLine();
     writer2.write(stm);
     writer2.close();
     System.out.println("MAC : "+stm);
     mac=new String(stm);
  }
  catch(IOException i)
  {
      System.out.println(i);
  }
}
    public static void main(String args[])
    {
        clientt client1 = new clientt("127.0.0.1",1);
        try{
        Socket sock = new Socket("127.0.0.1", 3000);
                               // reading from keyboard (keyRead object)
     BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
                              // sending to client (pwrite object)
     OutputStream ostream = sock.getOutputStream();
     PrintWriter pwrite = new PrintWriter(ostream, true);

                              // receiving from server ( receiveRead  object)
     InputStream istream = sock.getInputStream();
     BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

     String receiveMessage, sendMessage;
     String t=new String();
     System.out.print("Enter Server I.P : ");
     t=keyRead.readLine();
     pwrite.println(t);
     pwrite.flush();
     pwrite.println(client1.ip);
     pwrite.flush();
     pwrite.println(client1.mac);
     pwrite.flush();
     System.out.println("ARP REQUEST PACKET : ");
     System.out.println("   Client IP : "+ client1.ip + "  ");
     System.out.println("   Client MAC : "+ client1.mac + "  ");
     System.out.println("   Server IP : "+ t + "  ");
     System.out.println("   Server MAC : 00:00:00:00:00 ");
     System.out.println("ARP REPLY PACKET : ");
     receiveMessage = receiveRead.readLine();
     System.out.println("   Client IP : "+ client1.ip + "  ");
     System.out.println("   Client MAC : "+ client1.mac + "  ");
     System.out.println("   Server IP : "+ t + "  ");
     System.out.println("   Server MAC : "+ receiveMessage + "  ");
     System.out.println("CONNECTION ESTABLISHED  ");
     System.out.println("");
     receiveMessage = receiveRead.readLine();
     while(true)
     {
       if((receiveMessage = receiveRead.readLine()) != null) //receive from server
       {
           System.out.println("Server : "+receiveMessage); // displaying at DOS prompt
       }
        System.out.print("Client : ");
        sendMessage = keyRead.readLine();  // keyboard reading
        pwrite.println(sendMessage);       // sending to server
        pwrite.flush();                    // flush the data
      }
    }
      catch(IOException i)
      {
          System.out.println(i);
      }
    }
}
