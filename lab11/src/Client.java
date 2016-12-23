import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by yelabd on 11/11/16.
 */
public class Client {

    static Socket socket;

    static PrintWriter outTo;
    static InputStreamReader isr;
    static BufferedReader in;
    static PrintWriter filePrinter;
    static File outFile = new File("/Users/youssefelabd/Desktop/cs180/lab12/Client/info.txt");
    static FileOutputStream outputStream;


    public static void main(String[] args) {
        Client client = new Client();

        try {
            socket = new Socket("localhost", 6500);
            outTo = new PrintWriter(socket.getOutputStream(), true);
            isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);
        }catch (IOException e){
            e.printStackTrace();
        }


        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Filename: ");
        String filename = scan.nextLine();
        System.out.println("Enter Username: ");
        String username = scan.nextLine();
        String response = "";
        String response2 = "";
        String response3 = "";

        try {
            outTo.println(filename);
            outTo.println(username);

            response = in.readLine();
            response2 = in.readLine();
            response3 = in.readLine();

            System.out.println(response);
        }catch (IOException e){
            e.printStackTrace();
        }
        if (response.equals("FileNotFoundException")){
            System.out.println("File does not exist");
        } else if (response.equals("InfoNotFoundException")){
            try {
                throw new InfoNotFoundException("Your information is not in our file");
            } catch (InfoNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("was here");
            try {
                outputStream = new FileOutputStream(outFile);
                filePrinter = new PrintWriter(outputStream);
                filePrinter.println(response);
                filePrinter.println(response2);
                filePrinter.println(response3);

                outTo.close();
                filePrinter.close();
                outputStream.close();
                in.close();
                isr.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
