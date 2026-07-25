/*
 * Decompiled with CFR 0.152.
 */
package drunkmafia.thaumicinfusion.common.util.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.RUNTIME)
public @interface OverrideBlock {
    public boolean overrideBlockFunc() default true;
}

