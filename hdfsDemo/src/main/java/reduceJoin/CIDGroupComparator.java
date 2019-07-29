package reduceJoin;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * CID分组对比器
 * Created by Chris on 2019/1/13.
 */
public class CIDGroupComparator extends WritableComparator {
    protected  CIDGroupComparator(){
        super(ComboKey.class,true);
    }
    public  int compare (WritableComparable a, WritableComparable b){
        ComboKey k1 = (ComboKey) a;
        ComboKey k2 = (ComboKey) b;
        return  k1.getCid() - k2.getCid();

    }
}
