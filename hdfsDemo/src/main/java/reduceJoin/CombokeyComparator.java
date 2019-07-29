package reduceJoin;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 组合key排序对比器
 * Created by Chris on 2019/1/13.
 */
public class CombokeyComparator extends WritableComparator {
    protected  CombokeyComparator(){
        super(ComboKey.class,true);
    }
    public  int compare (WritableComparable a, WritableComparable b){
        ComboKey k1 = (ComboKey) a;
        ComboKey k2 = (ComboKey) b;
        return  k1.compareTo(k2);

    }

}
