package com.training.project;
import com.opencsv.CSVWriter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CsvFileHandler implements MyFileHandler
{
        public Employee readData(int readCounter) {

            Path pathOfFile = Paths.get("/Users/rishabhbohra/IdeaProjects/Project/src/com/training/project/employee.csv");



            try
            {

                BufferedReader bufferedReader = Files.newBufferedReader(pathOfFile, StandardCharsets.US_ASCII);
                String oneRow = bufferedReader.readLine();

                for(int itr = 0; itr<readCounter;itr++ )
                {
                    oneRow = bufferedReader.readLine();
                }

                    String[] elementsOfOneRow = oneRow.split(",");

                    Employee employee = new Employee();
                    employee.setFirstName(elementsOfOneRow[0]);
                    employee.setLastName(elementsOfOneRow[1]);
                    employee.setDateOfBirth(new SimpleDateFormat("MM/DD/YY").parse(elementsOfOneRow[2]));
                    employee.setExperience(Double.parseDouble(elementsOfOneRow[3]));

                    return employee;

            } catch (Exception e) {
                System.out.println(e);
            }

            return null;

        }

        @Override
        public void writeData(Employee employee) {
            try{
                FileWriter fileWriter = new FileWriter("/Users/rishabhbohra/IdeaProjects/Project/src/com/training/project/duplicate.csv",true);   //file path
                CSVWriter csvWriter = new CSVWriter(fileWriter);

                String s1 = Double.toString(employee.experience);

                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
                String s2 = dateFormat.format(employee.dateOfBirth);

                String[] stringToPutInFile = {employee.firstName, employee.lastName, s2, s1};

                csvWriter.writeNext(stringToPutInFile);

                csvWriter.close();


            }catch(Exception e1){
                System.out.println(e1);
                System.out.println("Error in write csv");
            }


        }
}
