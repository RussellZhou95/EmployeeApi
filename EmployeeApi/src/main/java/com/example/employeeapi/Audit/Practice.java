package com.example.employeeapi.Audit;

import com.example.employeeapi.dto.Person;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Practice {



    public static void main(String[] args) {


        String subject="English";
        if(!subject.equals("Math")){
            System.out.println("Not Math");
        }

        List<String> fruits= Arrays.asList("Apple","Apricot","Banana","Peach","Watermelon");
//        Predicate<String> fruitStartWithA=a->a.startsWith("A");
//
//        List<String> fruitsStartWithA = fruits.stream().filter(fruitStartWithA).collect(Collectors.toList());
//        fruitsStartWithA.forEach(System.out::println);
//
//        Map<String,String> nameWithTitle=new HashMap<>();
//        nameWithTitle.put("Kailey","Teacher");
//        nameWithTitle.put("Russell","Developer");
//        nameWithTitle.put("Cris","Tester");
//
//        for(Map.Entry<String,String> entry:nameWithTitle.entrySet()){
//            System.out.println(entry.getKey()+" "+entry.getValue());
//        }
//        nameWithTitle.forEach((key,value)->{
//            System.out.println(key+" "+value);
//        });
//
//
//        String template="Hello, %s";
//        System.out.println(String.format(template,"Kobe"));
//        int b=0;
//        for(String a:fruits){
//            if(a.equals("Banana")){
//                System.out.println("I found the banana");
//                System.out.println(b);
//                break;
//            }
//            b++;
//        }

        Person personInstance = new Person("John",22);

        // Serialize the object to a file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
            outputStream.writeObject(personInstance);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the object from the file
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("person.ser"))) {
            Person loadedPerson = (Person) inputStream.readObject();

            // Print the loaded object
            System.out.println("Name: " + loadedPerson.getName());
            System.out.println("Age: " + loadedPerson.getAge());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
