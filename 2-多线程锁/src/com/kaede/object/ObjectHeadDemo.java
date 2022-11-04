package com.kaede.object;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author kaede
 * @create 2022-09-14 17:27
 */

public class ObjectHeadDemo {

    public static void main(String[] args) {
        //对象头 + 实例数据 + 对齐填充
        //new一个对象，占内存多少
        Object o = new Object();

        //hashcode记录在对象的什么地方
        System.out.println(o.hashCode());

        //有没有被加锁，锁了多少次
        synchronized (o) {

        }

        //手动收集垃圾，15次可以从新生代到老年代，如何知道活了多少次
        System.gc();

        Customer customer = new Customer();

        System.out.println(VM.current().details());
        System.out.println("-------------------------\n");
        System.out.println(ClassLayout.parseInstance(customer).toPrintable());
    }

}

//对象头16字节（忽略压缩针的影响） + 4字节 + 1字节 + 3字节填充 = 24字节
class Customer {
    private int id;
    private boolean flag;
}
