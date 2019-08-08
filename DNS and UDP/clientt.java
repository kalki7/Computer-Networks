import java.io.*;
import java.net.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
class clientt
{
 public static DatagramSocket clientsocket;
 public static DatagramPacket dp;
 public static BufferedReader br;
 public static InetAddress ia;
 public static byte buf[] = new byte[1024];
 public static int cport = 222, sport = 555;
 public static void main(String[] args) throws IOException
 {
  clientsocket = new DatagramSocket(cport);
  dp = new DatagramPacket(buf, buf.length);
  br = new BufferedReader(new InputStreamReader(System.in));
  ia = InetAddress.getLocalHost();

  System.out.println("Client is Running...");
while(true){
   System.out.print("Enter Domain Name/IP Address : ");
   String str1 = new String(br.readLine());
   buf = str1.getBytes();

   clientsocket.send(new DatagramPacket(buf,str1.length(), ia, sport));

   clientsocket.receive(dp);
   String str4 = new String(dp.getData(), 0, dp.getLength());
   System.out.println(" Server Responds IP Adress/Domain Name : " + str4);

   try {

    if(!Character.isDigit(str4.charAt(0))){
      URI uri= new URI(str4);

      java.awt.Desktop.getDesktop().browse(uri);
        System.out.println("Web page opened in browser");

      }
    else{
      URI uri= new URI(str1);

      java.awt.Desktop.getDesktop().browse(uri);
        System.out.println("Web page opened in browser");

    }
      } catch (Exception e) {

    //    e.printStackTrace();
      }
    }
 }
}
