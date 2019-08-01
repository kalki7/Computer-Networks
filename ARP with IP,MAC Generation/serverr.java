import java.net.*;
import java.io.*;
import java.util.*;
public class serverr
{
    Scanner inp = new Scanner(System.in);
    //String s = in.nextLine();
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
    private DataOutputStream out     = null;
    String ip;
    String mac;
    public serverr(int port)
    {

      try{
        File file1 = new File("ip.txt");
        BufferedReader bri1 = new BufferedReader(new FileReader(file1));
        PrintWriter pwi = new PrintWriter("ip_updated.txt");
        String sti;
        //reading IP for server
        sti = bri1.readLine();
        bri1.close();
        BufferedReader bri2 = new BufferedReader(new FileReader(file1));
        String line1 = bri2.readLine();
        //updating text files with flags
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
       File filex = new File("ip.txt");
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
      //reading MAC for server
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
        serverr server1 = new serverr(1);
        try{
        ServerSocket sersock = new ServerSocket(3000);

      Socket sock = sersock.accept( );
                              // reading from keyboard (keyRead object)
      BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
	                      // sending to client (pwrite object)

      OutputStream ostream = sock.getOutputStream();
      PrintWriter pwrite = new PrintWriter(ostream, true);

                              // receiving from server ( receiveRead  object)
      InputStream istream = sock.getInputStream();
      BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

      String receiveMessage, sendMessage;
      Scanner inp = new Scanner( System.in );
      int op=0;
      String cip,cmac,st;
      receiveMessage = receiveRead.readLine();
      System.out.println("ARP PACKET : ");
      st=new String(receiveMessage);
      System.out.println("   Server IP : "+ server1.ip + " ");
      System.out.println("   Server Mac : 00:00:00:00:00:00");
      receiveMessage = receiveRead.readLine();
      System.out.println("   Client IP : "+ receiveMessage + "  ");
      cip=new String(receiveMessage);
      receiveMessage = receiveRead.readLine();
      System.out.println("   Client MAC : "+ receiveMessage + "  ");
      cmac=new String(receiveMessage);
      if(st.equals(server1.ip)){
        System.out.print("Do you wish to connect to client (0/1) : ");
        op=inp.nextInt();
        pwrite.println(server1.ip);
        pwrite.flush();
        pwrite.println(server1.mac);
        pwrite.flush();
      System.out.println("RARP PACKET : ");
      System.out.println("   Client IP : "+ cip + "  ");
      System.out.println("   Client MAC : "+ cmac + "  ");
      System.out.println("   Server IP : "+ server1.ip + "  ");
      System.out.println("   Client IP : "+ server1.mac + "  ");
      System.out.println("CONNECTION ESTABLISHED  ");
      System.out.println("");
      }
      else{
        System.out.println("Client has provided Faulty IP");
      }
      while(op==1)
      {
        System.out.print("Server : ");
        sendMessage = keyRead.readLine();
        pwrite.println(sendMessage);
        pwrite.flush();
        if((receiveMessage = receiveRead.readLine()) != null)
        {
           System.out.println("Client : " + receiveMessage);
        }
    }
    System.out.println("OVER");
  }
  catch(IOException i)
  {
      System.out.println(i);
  }
  }
}
