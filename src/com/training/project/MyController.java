package com.training.project;


public class MyController
{
    public static void main(String[] args)
    {

        Thread xmlReadThread = new Thread(new MyController().new XmlReadImplemntation());
        xmlReadThread.start();

        Thread CsvReadThread = new Thread(new MyController().new CsvReadImplemntation());
        CsvReadThread.start();

        Thread JsonReadThread = new Thread(new MyController().new JsonReadImplemntation());
        JsonReadThread.start();

        try
        {
            xmlReadThread.join();
            CsvReadThread.join();
            JsonReadThread.join();
        }catch(Exception e)
        {
            e.printStackTrace();

        }

        Thread xmlWriteThread = new Thread(new MyController().new XmlWriteImplementation());
        xmlWriteThread.start();

        Thread CsvWriteThread = new Thread(new MyController().new CsvWriteImplementation());
        CsvWriteThread.start();

        Thread JsonWriteThread = new Thread(new MyController().new JsonWriteImplementation());
        JsonWriteThread.start();

        try
        {
            xmlWriteThread.join();
            CsvWriteThread.join();
            JsonWriteThread.join();
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    private class XmlReadImplemntation implements Runnable
    {
        public void run()
        {
            for(int i=0;i<100;i++) {
                XMLFileHandler readVal = new XMLFileHandler();
                MyCollection collectXmlData = new MyCollection();
                collectXmlData.addData(readVal.readData(i));
            }

        }
    }

    private class CsvReadImplemntation implements Runnable
    {
        public void run()
        {
            for(int i=0;i<100;i++) {
                CsvFileHandler readVal = new CsvFileHandler();
                MyCollection collectXmlData = new MyCollection();
                collectXmlData.addData(readVal.readData(i));
            }

        }
    }

    private class JsonReadImplemntation implements Runnable
    {
        public void run()
        {
            for(int i=0;i<100;i++) {
                JsonFileHandler readVal = new JsonFileHandler();
                MyCollection collectXmlData = new MyCollection();
                collectXmlData.addData(readVal.readData(i));
            }

        }
    }

    private class XmlWriteImplementation implements Runnable
    {
        @Override
        public void run()
        {
            for(int i=0;i<100;i++)
            {
                XMLFileHandler writeVal = new XMLFileHandler();
                MyCollection reciveXmlData = new MyCollection();
                writeVal.writeData(reciveXmlData.getData());
            }

        }
    }

    private class CsvWriteImplementation implements Runnable
    {
        @Override
        public void run()
        {
            for(int i=0;i<100;i++)
            {
                CsvFileHandler writeVal = new CsvFileHandler();
                MyCollection reciveCsvData = new MyCollection();
                writeVal.writeData(reciveCsvData.getData());
            }

        }
    }

    private class JsonWriteImplementation implements Runnable
    {
        @Override
        public void run()
        {
            for(int i=0;i<100;i++)
            {
                JsonFileHandler writeVal = new JsonFileHandler();
                MyCollection reciveJsonData = new MyCollection();
                writeVal.writeData(reciveJsonData.getData());
            }

        }
    }
}