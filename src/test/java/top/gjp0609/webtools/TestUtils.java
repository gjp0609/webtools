package top.gjp0609.webtools;

import top.gjp0609.webtools.utils.DebugUtil;

import javax.persistence.Entity;
import java.util.*;

public class TestUtils {


    public static void main(String[] args) {
        TestClass aClass = new TestClass();
        aClass.setName("cName");
        aClass.setNum(1000L);
        aClass.setTime(new Date());
        ArrayList<TestClass> list = new ArrayList<>();
        list.add(new TestClass("aaa", 123L, new Date()));
        list.add(new TestClass("bbb", 234L, new Date()));
        list.add(new TestClass("ccc", 345L, new Date()));
        aClass.setList(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("time", new Date());
        map.put("list", list);
        map.put("obj", aClass);
        aClass.setMap(map);
        System.out.println(DebugUtil.reflectionToString(aClass));
        System.out.println(DebugUtil.getFmtRefStr(aClass));
    }


//    @Entity
    public static class TestClass {

        private String name;
        private Long num;
        private Date time;
        private Map<String, Object> map;
        private List<TestClass> list;

        public TestClass() {
        }

        public TestClass(String name, Long num, Date time) {
            this.name = name;
            this.num = num;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public Long getNum() {
            return num;
        }

        public void setNum(Long num) {
            this.num = num;
        }

        public Map<String, Object> getMap() {
            return map;
        }

        public void setMap(Map<String, Object> map) {
            this.map = map;
        }

        public List<TestClass> getList() {
            return list;
        }

        public void setList(List<TestClass> list) {
            this.list = list;
        }
    }
}
