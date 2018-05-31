package top.gjp0609.java8.part1;

import java.util.HashMap;
import java.util.Map;

public class Demo2 {
    public static void main(String[] args) {
        Map<String, Integer> pageVisits = new HashMap<>();
        String page = "https://agiledeveloper.com";

        incrementPageVisit(pageVisits, page);
        incrementPageVisit(pageVisits, page);

        incrementPageVisit2(pageVisits, page);
        incrementPageVisit2(pageVisits, page);

        System.out.println(pageVisits.get(page));
    }

    private static void incrementPageVisit(Map<String, Integer> pageVisits, String page) {
        if(!pageVisits.containsKey(page)) {
            pageVisits.put(page, 0);
        }
        pageVisits.put(page, pageVisits.get(page) + 1);
    }

    private static void incrementPageVisit2(Map<String, Integer> pageVisits, String page) {
        pageVisits.merge(page, 1, (oldValue, value) -> oldValue + value);
    }
}
