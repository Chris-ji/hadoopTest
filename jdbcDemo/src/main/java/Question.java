/**
 * Created by Chris on 2019/4/15.
 */
public class Question
{
    public static void main(String[] args)
    {
        int i = 7;
        int j = 8;
        int n = (i|j)%(i&j);
        System.out.println(n);
    }
}
