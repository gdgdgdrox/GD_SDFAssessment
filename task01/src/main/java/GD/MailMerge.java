package GD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MailMerge{
    public static File csvFile;
    public static File template;

    public static void main(String[] args) throws IOException {
        if (args.length == 2){
            csvFile = new File(args[0]);
            template = new File(args[1]);
        }

        //Converting .csv into a 2D array
            //column major order
        List<List<String>> list = new ArrayList<List<String>>();
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line = br.readLine();
        String[] headers = line.split(",");
        for(String header: headers) {
            List<String> subList = new ArrayList<String>();
            subList.add(header);
            list.add(subList);
        }
        while((line = br.readLine()) != null) {
            String[] elems = line.split(",");
            for(int i = 0; i < elems.length; i++) {
                list.get(i).add(elems[i]);
            }
        }
        br.close();
        int rows = list.size();
        int cols = list.get(0).size(); 
        String[][] csv2DArray = new String[rows][cols];
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                csv2DArray[row][col] = list.get(row).get(col);
            }
        }

        //Converting template into a String array
        Scanner scan = new Scanner(new FileReader(template));
        List<String> listOfStrings = new ArrayList<String>();
        String str;
        while (scan.hasNext()){
            str = scan.next();
            listOfStrings.add(str);
        }
        System.out.println(listOfStrings);

        String[] templateArray = listOfStrings.toArray(new String[0]);

        //Compare the variable name of CSV file against the template and substitution            
        for (int i = 0; i < templateArray.length; i++){
            for (int j = 0; j < csv2DArray.length; j++){
                if (templateArray[i].contains(csv2DArray[j][0])){
                    templateArray[i] = csv2DArray[j][j+1];
                }
            }
        }
        System.out.println(Arrays.toString(templateArray));
        
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < templateArray.length; i++) {
            sb.append(templateArray[i]);
        }
        String mailString = sb.toString();
        System.out.println(mailString);
        
        


        
    }
}



