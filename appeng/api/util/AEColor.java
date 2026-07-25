/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 */
package appeng.api.util;

import net.minecraft.util.StatCollector;

public enum AEColor {
    White("gui.appliedenergistics2.White", 0xBEBEBE, 0xDBDBDB, 0xFAFAFA),
    Orange("gui.appliedenergistics2.Orange", 16357177, 16428612, 16047811),
    Magenta("gui.appliedenergistics2.Magenta", 8527490, 12069560, 12949704),
    LightBlue("gui.appliedenergistics2.LightBlue", 6458827, 8563943, 14219007),
    Yellow("gui.appliedenergistics2.Yellow", 0xFFF7AA, 16318282, 0xFFFFE8),
    Lime("gui.appliedenergistics2.Lime", 8191818, 12320593, 15202263),
    Pink("gui.appliedenergistics2.Pink", 14454197, 16299479, 16244459),
    Gray("gui.appliedenergistics2.Gray", 0x7C7C7C, 0xA0A0A0, 0xC9C9C9),
    LightGray("gui.appliedenergistics2.LightGray", 0x9D9D9D, 0xCDCDCD, 0xEFEFEF),
    Cyan("gui.appliedenergistics2.Cyan", 3120037, 5352134, 11460084),
    Purple("gui.appliedenergistics2.Purple", 8532146, 10769358, 13083596),
    Blue("gui.appliedenergistics2.Blue", 2959776, 5327615, 14542591),
    Brown("gui.appliedenergistics2.Brown", 7491125, 12031615, 14734024),
    Green("gui.appliedenergistics2.Green", 4562977, 6349614, 14938851),
    Red("gui.appliedenergistics2.Red", 10813481, 16711740, 16770797),
    Black("gui.appliedenergistics2.Black", 0x2B2B2B, 0x565656, 0x848484),
    Transparent("gui.appliedenergistics2.Fluix", 1778500, 9002152, 14138348);

    public final String unlocalizedName;
    public final int blackVariant;
    public final int mediumVariant;
    public final int whiteVariant;

    private AEColor(String unlocalizedName, int blackHex, int medHex, int whiteHex) {
        this.unlocalizedName = unlocalizedName;
        this.blackVariant = blackHex;
        this.mediumVariant = medHex;
        this.whiteVariant = whiteHex;
    }

    public boolean matches(AEColor color) {
        return this.equals((Object)Transparent) || color.equals((Object)Transparent) || this.equals((Object)color);
    }

    public String toString() {
        return StatCollector.func_74838_a((String)this.unlocalizedName);
    }
}

