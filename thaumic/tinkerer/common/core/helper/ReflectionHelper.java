/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.base.Throwables
 *  org.apache.commons.lang3.ArrayUtils
 */
package thaumic.tinkerer.common.core.helper;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class ReflectionHelper {
    public static <T> T call(Object instance, String methodName, Object ... args) {
        return ReflectionHelper.call(instance.getClass(), instance, (String[])ArrayUtils.toArray((Object[])new String[]{methodName}), args);
    }

    public static <T> T call(Object instance, String[] methodNames, Object ... args) {
        return ReflectionHelper.call(instance.getClass(), instance, methodNames, args);
    }

    public static <T> T call(Class<?> klazz, Object instance, String[] methodNames, Object ... args) {
        Method m = ReflectionHelper.getMethod(klazz, methodNames, args);
        Preconditions.checkNotNull((Object)m, (String)"Method %s not found", (Object[])new Object[]{Arrays.toString(methodNames)});
        m.setAccessible(true);
        try {
            return (T)m.invoke(instance, args);
        }
        catch (Exception e) {
            throw Throwables.propagate((Throwable)e);
        }
    }

    public static Method getMethod(Class<?> klazz, String[] methodNames, Object ... args) {
        if (klazz == null) {
            return null;
        }
        Class[] argTypes = new Class[args.length];
        for (int i = 0; i < args.length; ++i) {
            Object arg = args[i];
            argTypes[i] = arg.getClass();
        }
        for (String name : methodNames) {
            Method result = ReflectionHelper.getDeclaredMethod(klazz, name, argTypes);
            if (result == null) continue;
            return result;
        }
        return null;
    }

    public static Method getMethod(Class<?> klazz, String[] methodNames, Class<?> ... types) {
        if (klazz == null) {
            return null;
        }
        for (String name : methodNames) {
            Method result = ReflectionHelper.getDeclaredMethod(klazz, name, types);
            if (result == null) continue;
            return result;
        }
        return null;
    }

    public static Method getDeclaredMethod(Class<?> clazz, String name, Class<?>[] argsTypes) {
        while (clazz != null) {
            try {
                return clazz.getDeclaredMethod(name, argsTypes);
            }
            catch (NoSuchMethodException e) {
            }
            catch (Exception e) {
                throw Throwables.propagate((Throwable)e);
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }
}

