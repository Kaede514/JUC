package com.kaede.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author kaede
 * @create 2022-09-10 20:49
 */

public class CompletableFutureChain {

    public static void main(String[] args) {
        Student student = new Student();
        //传统方式
        student.setId(1);
        student.setName("zhangsan");
        student.setAge(18);
        //链式编程，IDEA中使用set生成器时可以指定模板为Builder支持链式编程
        student.setId(2).setName("lisi").setAge(19);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "hello 1234";
        });
        //System.out.println(completableFuture.get());
        System.out.println(completableFuture.join());
    }

}

//引入Lombok依赖后可以使用@Accessors(chain = true)支持链式编程
class Student {
    private Integer id;
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public Student setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Student setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Student() {
    }

    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
