package sdf.assessment.task02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class ClientMain {


    public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException {
        System.out.println("Client: Andrew, connecting...");
        String[] arr = args[0].split(":");
        String host = arr[0];
        int port = Integer.parseInt(arr[1]);
        //Connect to server
        Socket sock = new Socket(host, port);
        System.out.println("Connected"); //connected to the server

        //Get OutputStream
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        ObjectOutputStream objOs = new ObjectOutputStream(dos); 
        
        //Get input stream
        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        ObjectInputStream objIs = new ObjectInputStream(dis);

        //Read the full request
        String request = objIs.readUTF();
        System.out.println(request);

        //Split request into requestID and Integers
        String[] requestSplit = request.split(" ");
        String requestID = requestSplit[0];
        //print request ID
        System.out.println("request ID: " + requestID);
        String numberList = requestSplit[1];
        //print list of numbers
        System.out.println("Number List: " + numberList);
        String[] strNumArray = numberList.split(",");
        int[] numbers = new int[strNumArray.length];
        for (int i = 0; i < strNumArray.length; i++) {
            numbers[i] = Integer.parseInt(strNumArray[i]);
        }
        //print number array
        System.out.println(Arrays.toString(numbers));

        //Task 3: Calculate average of the list
        float numLength = numbers.length;

        int sum = 0;

        for (int i = 0; i < numLength; i++) {
            sum += numbers[i];
        }

        float average = sum/numLength;
        System.out.println("Average of array: " + average);

        //Task 4: write answer back
        System.out.println("Writing back to server");
        String name = "Kwan Yi-Hao, Andrew";
        String email = "andrewtimkwan@gmail.com";
        objOs.writeUTF(requestID);
        objOs.writeUTF(name);
        objOs.writeUTF(email);
        objOs.writeFloat(average);
        System.out.println("Waiting for response");
        objOs.flush();

        //Task 5: read response from server
        boolean request2 = objIs.readBoolean();
        //System.out.println(request2);

        if (request2 == false) {
            String errorMessage = objIs.readUTF();
            System.out.println("FAILED " + errorMessage);

            is.close();
            os.close();
            dis.close();
            dos.close();
            objIs.close();
            objOs.close();

            sock.close();
        }

        else {
            System.out.println("SUCCESS");
            is.close();
            os.close();
            dis.close();
            dos.close();
            objIs.close();
            objOs.close();

            sock.close();
        }
    



        //is.close();
        //os.close();

    }
  
    
}

//Task 2 success printed