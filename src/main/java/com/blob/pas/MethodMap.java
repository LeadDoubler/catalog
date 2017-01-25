/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.blob.pas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morten
 */
public class MethodMap implements Map{
    
    Method[] methods;
    Object object;
    
    public MethodMap(Method[] m, Object obj){
        this.methods = m;
        this.object = obj;
    }

    public int size() {
        return methods.length;
    }

    public boolean isEmpty() {
        return methods.length == 0;
    }

    public boolean containsKey(Object key) {
        for (Method method : methods){
            if (method.getName().equals(key)){
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object get(Object key) {
        Method m = null;
        String var = key.toString();
        for (Method method : methods){
            String s = method.getName();
            if (method.getName().equals(key.toString())){
                m = method;
                break;
            }
        }
        try {
            if (m == null){
                System.out.println("Method = "+m);
            }
            if (object == null){
                System.out.println("Object = "+ object);
            }
            try{
                Object obj = m.invoke(object);
                System.out.println(obj);
                return obj;
            }catch(NullPointerException ne){
                
            }
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MethodMap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MethodMap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MethodMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Object put(Object key, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void putAll(Map m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection values() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
