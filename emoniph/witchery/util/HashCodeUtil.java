/*
 * Decompiled with CFR 0.152.
 */
package com.emoniph.witchery.util;

import java.lang.reflect.Array;

public final class HashCodeUtil {
    public static final int SEED = 23;
    private static final int fODD_PRIME_NUMBER = 37;

    public static int hash(int aSeed, boolean aBoolean) {
        System.out.println("boolean...");
        return HashCodeUtil.firstTerm(aSeed) + (aBoolean ? 1 : 0);
    }

    public static int hash(int aSeed, char aChar) {
        System.out.println("char...");
        return HashCodeUtil.firstTerm(aSeed) + aChar;
    }

    public static int hash(int aSeed, int aInt) {
        System.out.println("int...");
        return HashCodeUtil.firstTerm(aSeed) + aInt;
    }

    public static int hash(int aSeed, long aLong) {
        System.out.println("long...");
        return HashCodeUtil.firstTerm(aSeed) + (int)(aLong ^ aLong >>> 32);
    }

    public static int hash(int aSeed, float aFloat) {
        return HashCodeUtil.hash(aSeed, Float.floatToIntBits(aFloat));
    }

    public static int hash(int aSeed, double aDouble) {
        return HashCodeUtil.hash(aSeed, Double.doubleToLongBits(aDouble));
    }

    public static int hash(int aSeed, Object aObject) {
        int result = aSeed;
        if (aObject == null) {
            result = HashCodeUtil.hash(result, 0);
        } else if (!HashCodeUtil.isArray(aObject)) {
            result = HashCodeUtil.hash(result, aObject.hashCode());
        } else {
            int length = Array.getLength(aObject);
            for (int idx = 0; idx < length; ++idx) {
                Object item = Array.get(aObject, idx);
                result = HashCodeUtil.hash(result, item);
            }
        }
        return result;
    }

    private static int firstTerm(int aSeed) {
        return 37 * aSeed;
    }

    private static boolean isArray(Object aObject) {
        return aObject.getClass().isArray();
    }
}

