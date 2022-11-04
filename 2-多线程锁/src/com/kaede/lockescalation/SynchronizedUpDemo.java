package com.kaede.lockescalation;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author kaede
 * @create 2022-09-14 20:28
 */

public class SynchronizedUpDemo {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        //若不调用hashCode则结果字节中的hashCode的31位均为0，调用后则为hashCode
        System.out.println("10进制  o.hashCode() = " + o.hashCode() + "\n");
        System.out.println("16进制  o.hashCode() = " + Integer.toHexString(o.hashCode()) + "\n");
        System.out.println("2进制  o.hashCode() = " + Integer.toBinaryString(o.hashCode()) + "\n");

        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        //结果字节应倒着看，字节内应该正着看
    }

}
