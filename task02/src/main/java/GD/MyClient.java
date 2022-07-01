package main.java.GD;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class MyClient {
    private static final String address = "task02.chuklee.com";
    private static final String name = "Foo Guo Dong";
    private static final String email = "gdfoo94@gmail.com";
    public static OutputStream os;
    public static InputStream is;
    public static ObjectInputStream ois;
    public static ObjectOutputStream oos;


    public static void main(String[] args) {
        //Objective 1 - Connect to server "task02.chuklee.com" at port 80
        try {

            Socket socket = new Socket("68.183.239.26", 80);

            os = socket.getOutputStream();
            is = socket.getInputStream();
            oos = new ObjectOutputStream(os);
            ois = new ObjectInputStream(is);

            //Objective 2 - Use readUTF() to read request from server
            String[] serverRequest = ois.readUTF().split(" ");
            String requestID = serverRequest[0];
            System.out.println(requestID);
            String[] arrayOfNumbers = serverRequest[1].split(",");
            System.out.println(Arrays.toString(arrayOfNumbers));
            int[] numbers = new int[arrayOfNumbers.length];
            for (int i = 0; i < arrayOfNumbers.length; i++){
                numbers[i] = Integer.parseInt(arrayOfNumbers[i]);
            }

            //Objective 3 - Calculate average of int[]
            int sum = 0;
            for (int number : numbers){
                sum += number;
            }
            float average = sum / ((float)(numbers.length));
            System.out.println(average);
            //Objective 4 - Write answer to server using methods from ObjectOutputStream
            oos.writeUTF(requestID);
            oos.writeUTF(name);
            oos.writeUTF(email);
            oos.writeFloat(average);
            oos.flush();

            //Objective 5 - readBoolean() response from Server
                //if true, print "SUCCESS" then close socket
                //if false, read error message from Server, print "FAILED" and error message, then close socket.
            
            if (readBoolean() == true){
                System.out.println("SUCESS");
                socket.close();
            }
            else{
                String errorMsg = ois.readUTF();
                System.out.println("FAILED");
                System.out.println(errorMsg);
                socket.close();
        }
            
        
    
        is.close();
        os.close();
        socket.close();
            
        } catch (UnknownHostException e) {
            System.out.println("unknown host exception");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        public static boolean readBoolean() throws IOException {
            boolean serverResponse = Boolean.parseBoolean(ois.readUTF());
            return serverResponse;
        }
    }





