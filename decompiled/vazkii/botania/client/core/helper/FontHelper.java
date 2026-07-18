/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.client.core.helper;

public final class FontHelper {
    public static boolean isFormatColor(char par0) {
        return par0 >= '0' && par0 <= '9' || par0 >= 'a' && par0 <= 'f' || par0 >= 'A' && par0 <= 'F';
    }

    public static boolean isFormatSpecial(char par0) {
        return par0 >= 'k' && par0 <= 'o' || par0 >= 'K' && par0 <= 'O' || par0 == 'r' || par0 == 'R';
    }

    public static String getFormatFromString(String par0Str) {
        String s1 = "";
        int i = -1;
        int j = par0Str.length();
        while ((i = par0Str.indexOf(167, i + 1)) != -1) {
            if (i >= j - 1) continue;
            char c0 = par0Str.charAt(i + 1);
            if (FontHelper.isFormatColor(c0)) {
                s1 = "\u00a7" + c0;
                continue;
            }
            if (!FontHelper.isFormatSpecial(c0)) continue;
            s1 = s1 + "\u00a7" + c0;
        }
        return s1;
    }
}

