package com.count;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by Chris on 2019/1/4.
 */
public class MyPartitioner extends Partitioner<Text,IntWritable> {
    public MyPartitioner(){
        System.out.println(" new MyPartitioner()");
    }
    public int getPartition (Text text, IntWritable intWritable, int numPartitions){
        System.out.println("MyPartitioner.getPartition()");
        return 0;
    }

}
