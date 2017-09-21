import java.io.*;
import java.net.Socket;
import java.util.Scanner;



public class Client {

    public static void main(String[]args){

        try{
            Socket s = new Socket("127.0.0.1",8001);

            while (true){
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();


                Scanner in = new Scanner(input);

                PrintWriter out = new PrintWriter(output,true);

                String welcome = in.nextLine();
                System.out.println(welcome);

                out.println("Første besked Dude");
                System.out.println(in.nextLine());

                out.println("Anden besked er kommet");
                System.out.println(in.nextLine());

                out.println("Tredje Linje det virker min ven");
                System.out.println(in.nextLine());


                s.close();
                System.out.println("Forbindelsen er lukket Peace out");
            }
        }
        catch (IOException ex)
        {

        }

    }
}
