

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Bog: Avanceret Java
 * Kapitel # - Netværk - Multitråds Server - Server.java
 * @author Sonny Sandberg
 */
public class Server
{
    public static void main(String[] args)
    {
        ArrayList array = new ArrayList();





        // ServerSocket oprettes og port 8001 angives som den der skal lyttes på
        ServerSocket ss;
        try
        {
            ss = new ServerSocket(8001);

            System.out.println("Server kører...");

            array.add(" test name 1 ");
            array.add(" test name 2 ");
            array.add(" test name 3 ");
            while(true)
            {
                // Så længe der ikke er oprettet en forbindelse, venter serveren her
                // Så snart der anmodes om en forbindelse accepteres den med accept()

                Socket incoming = ss.accept();
                System.out.println("Client connected");

                InputStream input = incoming.getInputStream();
                OutputStream output = incoming.getOutputStream();

                Scanner in = new Scanner(input);

                PrintWriter out = new PrintWriter(output,true);

                out.println(" velkommen");

                String besked = in.nextLine();

                System.out.println("modtog besked " + besked);
              String name = besked.substring(4);


                if (besked.startsWith("PUT:")) {
                    array.add(name + " ");
                    System.out.println(name + " er Tilføjet til arrayet ");
                }

                else if(besked.startsWith("NAME:")){
                name = new String(besked.getBytes()).replace("NAME:", "kurt");

            }

                else{
                    name = "Guest";
                }
                out.println(name);
                out.print(array);

                Runnable r = new ClientConnection(incoming);
                Thread t = new Thread(r);
                t.start();
            }

                /*
                Den nye forbindelse sendes til vores ClientConnection object,
                hvor al logik for serveren bliver håndteret.
                Der oprettes en ny tråd, som modtager ClientConnection objektet
                og tråden startes.
                */



            } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        {

        }
    }

