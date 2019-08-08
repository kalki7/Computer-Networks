import java.io.*;
import java.net.*;
import java.lang.*;
class serverr
{
	public static DatagramSocket serversocket;
	public static DatagramPacket dp;
	public static BufferedReader br;
	public static InetAddress ia;
	public static byte buf[] = new byte[1024];
	public static int cport = 222,sport=555;
	public static void main(String[] args) throws IOException
       {
         serversocket = new DatagramSocket(sport);
         dp = new DatagramPacket(buf,buf.length);
         br = new BufferedReader (new InputStreamReader(System.in));
         ia = InetAddress.getLocalHost();

         System.out.println("Server is Running...");
         while(true){

          serversocket.receive(dp);
          String str2 = new String(dp.getData(), 0, dp.getLength());
          String str3 = new String();
          if(Character.isDigit(str2.charAt(0))){
            System.out.println("Client requests with IP Address : "+str2);
            if(str2.equals("209.85.231.104"))
              str3 = new String("http://www.google.com");
            else if(str2.equals("207.46.170.123"))
              str3 = new String("http://www.microsoft.com");
            else if(str2.equals("72.30.2.43"))
                str3 = new String("http://www.yahoo.com");
            else if(str2.equals("66.220.149.25"))
                str3 = new String("http://www.facebook.com");
            else if(str2.equals("208.80.152.2"))
                str3 = new String("http://www.wikipedia.com");
            else{
              str3 = new String("Invalid IP Address");
            }
            System.out.println("Server responds with Domain Name : "+str3);

          }
          else{
            System.out.println("Client requests with Domain Name : "+str2);
            if(str2.equals("www.google.com"))
              str3 = new String("209.85.231.104");
            else if(str2.equals("www.microsoft.com"))
              str3 = new String("207.46.170.123");
            else if(str2.equals("www.yahoo.com"))
              str3 = new String("72.30.2.43");
            else if(str2.equals("www.facebook.com"))
              str3 = new String("66.220.149.25");
            else if(str2.equals("www.wikipedia.com"))
              str3 = new String("208.80.152.2");
            else{
              str3 = new String("Invalid Domain Name");
            }
            System.out.println("Server Responds with IP Address : "+str3);
          }
          buf = str3.getBytes();
          serversocket.send(new DatagramPacket(buf,str3.length(), ia, cport));
        }
       }
    }
