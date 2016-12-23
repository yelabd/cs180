import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by youssefelabd on 11/13/16.
 */
public class Parser {

    static File file;
    static BufferedReader fileBufferedReader;
    static String name = "";
    static int numQueries = 0;

    public void parse(String filePath) throws IOException, WrongFileFormatException, WrongNumberOfQueriesException,
            InvalidInputException, MalformedQueryException {


            file = new File(filePath);

            if (file.exists() && !file.isDirectory()){
                fileBufferedReader = new BufferedReader(new FileReader(file));
            }else{
                throw new IOException();
            }


        String fileLine = "";
        fileLine = fileBufferedReader.readLine();

        if (fileLine.equals("C")) {
            fileLine = fileBufferedReader.readLine();
            name = fileLine;
            fileLine = fileBufferedReader.readLine();
            if (fileLine.equals("c")) {
                fileLine = fileBufferedReader.readLine();
                if (fileLine.equals("N")) {
                    fileLine = fileBufferedReader.readLine();
                    int x = Integer.parseInt(fileLine);
                    numQueries = x;
                    if (x >= 1) {
                        fileLine = fileBufferedReader.readLine();
                        if (fileLine.equals("n")) {
                            fileLine = fileBufferedReader.readLine();
                            if (fileLine.equals("Q")) {
                                for (int i = 1; i <= x; i++) {
                                    fileLine = fileBufferedReader.readLine();
                                    if (!(fileLine.substring(0,1).equals("S") || fileLine.substring(0,1).equals("U") || fileLine.substring(0,1).equals("D") || fileLine.substring(0,1).equals("I"))) {
                                        if (fileLine.equals("q")) {
                                            throw new WrongNumberOfQueriesException();
                                        } else {
                                            throw new MalformedQueryException();
                                        }
                                    }
                                }
                                fileLine = fileBufferedReader.readLine();
                                if (!fileLine.equals("q") && fileLine.substring(0,1).equals("S") || fileLine.substring(0,1).equals("U") || fileLine.substring(0,1).equals("D") || fileLine.substring(0,1).equals("I")) {
                                    throw new WrongNumberOfQueriesException();
                                } else if (!fileLine.equals("q")) {
                                    throw new WrongFileFormatException();
                                }
                            } else {
                                throw new WrongFileFormatException();
                            }
                        } else {
                            throw new WrongFileFormatException();
                        }
                    } else {
                        throw new InvalidInputException();
                    }
                } else {
                    throw new WrongFileFormatException();
                }
            } else {
                throw new WrongFileFormatException();
            }
        } else {
            throw new WrongFileFormatException();
        }
    }
    public String getUserName(){
        return name;
    }
    public int getNumQueries(){
        return numQueries;
    }

    public static void main(String[] args) {
        Parser test = new Parser();
        //for(int i = 1; i < 12;i++){
            try {
                //System.out.println(i);
                test.parse("/Users/youssefelabd/Desktop/cs180/Homework9/SampleFiles/file"+11+".sql");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongFileFormatException e) {
                e.printStackTrace();
            } catch (WrongNumberOfQueriesException e) {
                e.printStackTrace();
            } catch (InvalidInputException e) {
                e.printStackTrace();
            } catch (MalformedQueryException e) {
                e.printStackTrace();
            }
        //}
    }


}
