package com.kaede.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author kaede
 * @create 2022-09-13 10:19
 */

public class AtomicReferenceDemo {

    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User user1 = new User("zhangsan", 22);
        User user2 = new User("lisi", 24);

        atomicReference.set(user1);
        boolean isSuccess = atomicReference.compareAndSet(user1, user2);
        System.out.println(isSuccess + "\t" + atomicReference.get());
        isSuccess = atomicReference.compareAndSet(user1, user2);
        System.out.println(isSuccess + "\t" + atomicReference.get());
    }

}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
