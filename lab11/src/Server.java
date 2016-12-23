/**
 * Created by yelabd on 11/11/16.
 */
import java.net.*;
import java.io.*;

public class Server {
    static ServerSocket serverSocket;
    static Socket client;
    static PrintWriter out;
    static BufferedReader in;
    static File f;
    static BufferedReader fBufferedReader;

    public static void main(String[] args) throws IOException {
        String filename = "";
        String username = "";
        try {
            serverSocket = new ServerSocket(6500);
            System.out.println("Server waitng for connection");
            client = serverSocket.accept();
            System.out.println("Connection is successful and waiting for commands");
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e){
            e.printStackTrace();

        }
        try {
            filename = in.readLine();
            username = in.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            f = new File("/Users/youssefelabd/Desktop/cs180/lab12/Server/"+filename);
            fBufferedReader = new BufferedReader(new FileReader(f));


        }catch(FileNotFoundException e){
            out.println("FileNotFoundException");
        }
        String fileLine = "";
        String formattedOutput = "";
        boolean exists = false;

        while((fileLine = fBufferedReader.readLine()) != null) {
            String[] array = fileLine.split(";");
            if (array[1].equals(username)){
                exists = true;
                formattedOutput = "LastName: "+array[2] + "\nFirstName: "+ array[3]+ "\nMarks: "+array[4];
                System.out.println(formattedOutput);
                break;
            }
        }
        if (!exists){
            out.println("InfoNotFoundException");
        }else {
            out.println(formattedOutput);

        }

        serverSocket.close();
        client.close();
        out.close();
        in.close();
        fBufferedReader.close();

    }
}
