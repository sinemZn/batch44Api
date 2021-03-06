package utilities;


import day15.Customer;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriteToText {

    public static void saveSSNData(String fileName, Customer[] customers) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            for (int i = 0; i < customers.length; i++) {
                writer.append(customers[i].getSsn() + ",\n");
            }
            writer.close();
        } catch (Exception e) {
        }
    }

    public static void saveEmailData(String fileName, Customer[] customers) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            for (int i = 0; i < customers.length; i++) {
                writer.append(customers[i].getEmail() + ",\n");
            }
            writer.close();
        } catch (Exception e) {
        }
    }
}
