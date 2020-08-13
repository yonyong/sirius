package top.yonyong.sirius.serial;

import java.io.ObjectStreamClass;

/**
 * @author yonyong
 **/
public class GetSerialVersionUID {
    public static long get(Class clazz){
        return ObjectStreamClass.lookup(clazz.getClass()).getSerialVersionUID();
    }

    public static void main(String[] args) {
        System.out.println(GetSerialVersionUID.get(GetSerialVersionUID.class));
    }
}
