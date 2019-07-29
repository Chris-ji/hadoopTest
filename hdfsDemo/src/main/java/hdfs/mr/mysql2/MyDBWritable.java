package hdfs.mr.mysql2;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库输入格式
 * Created by Chris on 2019/1/6.
 */
public class MyDBWritable implements DBWritable,Writable {

    private int id =0;
    private  String name = "";
    private  String txt="";


    private  String word = "";
    private  int wordCount = 0;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(id);
        dataOutput.writeUTF(name);
        dataOutput.writeUTF(txt);
        dataOutput.writeUTF(word);
        dataOutput.writeInt(wordCount);
    }

    public void readFields(DataInput dataInput) throws IOException {
        id = dataInput.readInt();
        name = dataInput.readUTF();
        txt = dataInput.readUTF();
        word = dataInput.readUTF();
        wordCount = dataInput.readInt();
    }

    /**
     * 写入db
     * @param preparedStatement
     * @throws SQLException
     */
    public void write(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1,word);
        preparedStatement.setInt(2,wordCount);
       /* preparedStatement.setInt(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setString(3,txt);*/
    }

    /**
     * 从db里读出
     * @param resultSet
     * @throws SQLException
     */
    public void readFields(ResultSet resultSet) throws SQLException {
         id = resultSet.getInt(1);
         name= resultSet.getString(2);
         txt = resultSet.getString(3);
    }


}
