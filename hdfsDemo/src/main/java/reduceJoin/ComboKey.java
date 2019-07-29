package reduceJoin;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


/**
 * Created by Chris on 2019/1/13.
 */
public class ComboKey implements  WritableComparable<ComboKey>{
    private  int type;
    private  int cid;
    private  int oid;
    private  String customerInfo = "";
    private  String orderInfo = "";

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public int compareTo(ComboKey o) {
        int type0 = o.type;
        int cid0 = o.cid;
        int oid0 = o.oid;
        String customerInfo0 =o.customerInfo;
        String orderInfo0 =o.orderInfo;
        //是否为同一个customer的数据
        if(cid==cid0) {
            //同一个客户的两个订单
            if (type == type0) {
                return  oid - oid0;
            }
            else{
                if (type==0)
                    return -1 ;
                else
                    return  1;
            }
        }
        else{
            return  cid -cid0;
        }

    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(type);
        dataOutput.writeInt(cid);
        dataOutput.writeInt(oid);
        dataOutput.writeUTF(customerInfo);
        dataOutput.writeUTF(orderInfo);


    }

    public void readFields(DataInput dataInput) throws IOException {
       this.type = dataInput.readInt();
       this.cid =dataInput.readInt();
       this.oid=dataInput.readInt();
       this.customerInfo=dataInput.readUTF();
       this.orderInfo=dataInput.readUTF();

    }
}