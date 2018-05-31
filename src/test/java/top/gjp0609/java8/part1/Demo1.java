package top.gjp0609.java8.part1;

import java.util.Arrays;
import java.util.List;

public class Demo1 {

    public static void main(String[] args) {
        List<String> names = Arrays.asList(
                "Dory",
                "Gill",
                "Bruce",
                "Nemo",
                "Darla",
                "Marlin",
                "Jacques");
        findNemo(names);
        System.out.println("-----------------------");
        findNemo2(names);
    }

    private static void findNemo(List<String> names) {
        boolean found = false;
        for (String name : names) {
            if (name.equals("Nemo")) {
                found = true;
                break;
            }
        }
        if (found)
            System.out.println("Found Nemo");
        else
            System.out.println("Sorry, Nemo not found");
    }

    private static void findNemo2(List<String> names) {
        if (names.contains("Nemo"))
            System.out.println("Found Nemo");
        else
            System.out.println("Sorry, Nemo not found");
    }
}
