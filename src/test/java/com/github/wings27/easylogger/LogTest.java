package com.github.wings27.easylogger;

import org.junit.Test;

/**
 * Project easy-logger
 * Created by wenqiushi on 2016-06-21 11:22.
 */
public class LogTest {

    @Test
    public void testLog() {
        Person alice = new Person("alice", 18);
        Person bob = new Person("bob", 19);

        Log.debug("debug with args : {}, {}", alice, bob);
        Log.info("info with args : {}, {}", alice, bob);
        Log.warn("warn with args : {}, {}", alice, bob);
        Log.error("error with args : {}, {}", alice, bob);

        Log.debug("debug with lambda : alice.age={}, bob.age={}", alice::getAge, bob::getAge);
        Log.debug("debug with lambda : age.sum={}", () -> alice.getAge() + bob.getAge());
    }

    private static class Person {
        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }

}