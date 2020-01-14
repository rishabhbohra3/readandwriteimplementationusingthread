package com.training.project;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;

public class JsonFileHandler implements MyFileHandler
{
    public Employee readData(int readCounter)
    {


        JSONParser jsonparser=new JSONParser();

        try
        {
            Object obj=jsonparser.parse(new FileReader("/Users/rishabhbohra/IdeaProjects/Project/src/com/training/project/employee.json"));
            JSONArray jsonArray= (JSONArray) obj;
            Iterator itr=jsonArray.iterator();

                for(int it=0;it<readCounter;it++) {
                    itr.next();
                }
                    JSONObject jsonObject = (JSONObject) itr.next();


                    String fName = (String) jsonObject.get("firstName");
                    String lName = (String) jsonObject.get("lastName");
                    String dob = (String) jsonObject.get("dateOfBirth");
                    long exp = (long) jsonObject.get("experience");
                    Date db = new SimpleDateFormat("dd/MM/yyyy").parse(dob);

                    Employee employee = new Employee();
                    employee.setFirstName(fName);
                    employee.setLastName(lName);
                    employee.setDateOfBirth(db);
                    employee.setExperience((double) exp);
                    return employee;

        }
        catch(FileNotFoundException fileNotFoundExp)
        {
            System.out.println(fileNotFoundExp.getMessage());
            System.out.println("JsonError");
        }
        catch(IOException iOExp)
        {
            System.out.println(iOExp.getMessage());
            System.out.println("JsonError");
        }
        catch(java.text.ParseException parseExp)
        {
            System.out.println(parseExp.getMessage());
            System.out.println("JsonError");
        }
        catch(org.json.simple.parser.ParseException e)
        {
            System.out.println(e.getMessage());
            System.out.println("JsonError");
        }
        System.out.println("jsaon Null");
        return null;

    }



    public void writeData(Employee employee) {

        JSONObject jsonObject=new JSONObject();
        try {
            FileWriter fwrite=new FileWriter("/Users/rishabhbohra/IdeaProjects/Project/src/com/training/project/duplicate.json",true);


            jsonObject.put("firstName", employee.getFirstName());
            jsonObject.put("lastName", employee.getLastName());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            jsonObject.put("dateOfBirth",formatter.format(employee.getDateOfBirth()));
            jsonObject.put("experience",employee.getExperience());


            fwrite.write(jsonObject.toJSONString());
            fwrite.flush();

        }
        catch(IOException ioExp) {
            System.out.println(ioExp.getMessage());
        }


    }

}
