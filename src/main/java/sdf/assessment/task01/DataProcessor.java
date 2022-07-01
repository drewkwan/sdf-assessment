package sdf.assessment.task01;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProcessor {

    public Map<String, List<List<String>>> dataSet = new HashMap<> ();

    public void read (String data) {

        if ((null == data) || (data.trim().length()<=0))
            return;

        
        String[] fields = data.split(",");
        String firstName = removeCommas(fields[0]);
        String lastName = removeCommas(fields[1]);
        String address = removeCommas(fields[2]);
        String years = removeCommas(fields[3]);

        System.out.println(address + "\n\nDear " + firstName +",\n" + "Thank you for staying with us over these " + years + " years.\n\n");


    }

    public String removeCommas (String s) {
        return s.replaceAll(",", "");
    }

    
}