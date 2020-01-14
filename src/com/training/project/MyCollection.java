package com.training.project;

import java.util.ArrayList;

public class MyCollection
{

    private static ArrayList<Employee> dataCollection = new ArrayList<Employee>();

    private static int counter=0;

    synchronized public static void addData(Employee empObject)
    {
        dataCollection.add(empObject);

    }

    synchronized public static Employee getData()
    {

        Employee employeeObject = dataCollection.get(counter);
        counter++;
        return employeeObject;
    }
}
