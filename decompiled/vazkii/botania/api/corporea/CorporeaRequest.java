/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.api.corporea;

public class CorporeaRequest {
    public Object matcher;
    public boolean checkNBT;
    public int count;
    public int foundItems = 0;
    public int extractedItems = 0;

    public CorporeaRequest(Object matcher, boolean checkNBT, int count) {
        this.matcher = matcher;
        this.checkNBT = checkNBT;
        this.count = count;
    }
}

