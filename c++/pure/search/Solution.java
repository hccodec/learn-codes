import java.util.Hashtable;

/*
 * @Description: 
 * @Version: 1.0
 * @Author: hccodec
 * @Date: 2021-10-28 09:14:43
 * @LastEditors: hccodec
 * @LastEditTime: 2021-11-02 15:56:25
 */

/**
 * Solution
 */
public class Solution {
    public static void main(String[] args) {
        Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
        Put(table, 15);
        Print(table.get(4).toString());
        
    }

    public static void Put(Hashtable<Integer, Integer> table, int value) {
        table.put(hash(value), value);
    }

    public static int hash(Integer msg) {
        return msg % 11;
    }    

    public static void Print(String msg) {
        System.out.println(msg);;
    }
}