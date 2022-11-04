package com.kaede.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author kaede
 * @create 2022-09-10 13:56
 */

public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建MyTask对象
        MyTask myTask = new MyTask(1, 100);
        //创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        //获取最终合并之后的结果
        Integer result = submit.get();
        System.out.println("result = " + result);
        //关闭池对象
        forkJoinPool.shutdown();
    }

}

class MyTask extends RecursiveTask<Integer> {
    //拆分差值不能超过10，计算10以内的运算
    private static final Integer VALUE = 10;
    //拆分开始值
    private int begin;
    //拆分结束值
    private int end;
    //返回结果
    private int result;

    //创建有参构造
    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    //拆分和合并过程
    @Override
    protected Integer compute() {
        //判断相加两个数的差值
        if((this.end - this.begin) <= VALUE ) {
            //相加操作
            for (int i = begin; i <= end ; i++) {
                this.result = this.result + i;
            }
        } else {
            //进一步拆分
            //获取中间值
            int middle = (this.begin + this.end) / 2;
            MyTask myTaskLeft = new MyTask(this.begin, middle);
            //调用方法拆分
            myTaskLeft.fork();
            MyTask myTaskRight = new MyTask(middle+1, this.end);
            myTaskRight.fork();
            //合并结果
            this.result = myTaskLeft.join() + myTaskRight.join();
        }
        return this.result;
    }
}
