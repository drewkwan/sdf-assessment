package sdf.assessment.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class Main 
{   

    public static void main( String[] args ) throws IOException 
    {   
            
        String csvPath = "thankyou.csv";
        String tmpPath = "thankyou.txt";
        File csvFile = new File(csvPath);
        File tmpFile = new File(tmpPath);

        if (0 < args.length) {
            csvPath = args[0];
            tmpPath = args[1];
        }


        System.out.println( "---SDF Assessment---" );
        List<String> template = new ArrayList<String>();
        Scanner sc = new Scanner(tmpFile);
        while (sc.hasNextLine()) {
            template.add(sc.nextLine());
        }
        System.out.println(template);
        
        MailMerger processor = new MailMerger(csvPath, tmpPath);
        Reader r = new FileReader(csvPath);
        BufferedReader br = new BufferedReader(r);

        String data =br.readLine();
        

        while (null!= data) {

            data=br.readLine();
            processor.read(data);
        }
    }
}
