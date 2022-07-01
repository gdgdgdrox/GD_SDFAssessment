package GD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
        System.out.println(Arrays.deepToString(csv2DArray));
        

        //Converting .txt into a String[]
        List<String> listOfStrings = new ArrayList<String>();
        Scanner scan = new Scanner(template);
        //BufferedReader br2 = new BufferedReader(new FileReader(template));
        scan.useDelimiter(" ");
        
        while (scan.hasNextLine()){
            listOfStrings.add(scan.next());
        }
        scan.close();
        String[] templateArray = listOfStrings.toArray(new String[0]);
        System.out.println(Arrays.toString(templateArray));
        System.out.println(templateArray.length);

        //Compare the variable name of CSV file against the template


        /*for (int j=0; j<csv2DArray[0].length; j++){
            for (int k=0; k<templateArray.length k++){
                if (csv2DArray[0][j].equals("_" + templateArray[k] + "_")){
                    templateArray[k] = csv2DArray[j+1][j];
                }
            }
        }*/

        
    }

}

//public static void printMail (String[][] data, String[])