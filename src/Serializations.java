import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Serializations implements Serializable {
    public static void main(String[] args) {
        List<Customer> list =Arrays.asList(
                 new Customer("Jack", "Daniels", 22),
                new Customer("Capitan","Black", 23),
                new Customer("Fernand","Henessy",34),
                new Customer("James","Cook",35)
                );
        // sort ages
        var oldestCustomer = list.stream().filter(customer -> customer.getAge() > 30).toList();
        var eldestCustomer = list.stream().filter(customer -> customer.getAge() < 30).toList();

        // file for 30+ ages
        File file = new File("+30ages.txt");
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(oldestCustomer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object readObject = ois.readObject();
            if (readObject instanceof List readStudent) {
                System.out.println(readStudent);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // file for 30- ages
        File file2 = new File("-30ages.txt");
        try (FileOutputStream fos1 = new FileOutputStream(file2);
             ObjectOutputStream oos1 = new ObjectOutputStream(fos1)){
            oos1.writeObject(eldestCustomer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileInputStream fis1 = new FileInputStream(file2);
             ObjectInputStream ois1 = new ObjectInputStream(fis1)) {
            Object readObject = ois1.readObject();
            if (readObject instanceof List readStudent) {
                System.out.println(readStudent);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
