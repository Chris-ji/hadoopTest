package secondarysort;


import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by Chris on 2019/1/5.
 */
public class CombokeyComparator extends  WritableComparator{
    protected  CombokeyComparator (){
        super(ComboKey.class, true);
    }


    public int compare(WritableComparable a, WritableComparable b) {
        ComboKey k1 = (ComboKey)a;
        ComboKey k2 = (ComboKey)b;
        return k1.compareTo(k2);
    }
}
