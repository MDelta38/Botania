/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 */
package thaumic.tinkerer.client.core.helper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class IconHelper {
    private static IIcon emptyTexture;

    public static IIcon forName(IIconRegister ir, String name) {
        return ir.func_94245_a("ttinkerer:" + name);
    }

    public static IIcon emptyTexture(IIconRegister ir) {
        return emptyTexture == null ? (emptyTexture = IconHelper.forName(ir, "emptyTexture")) : emptyTexture;
    }

    public static IIcon forBlock(IIconRegister ir, Block block) {
        return IconHelper.forName(ir, block.func_149739_a().replaceAll("tile.", ""));
    }

    public static IIcon forBlock(IIconRegister ir, Block block, int i) {
        return IconHelper.forBlock(ir, block, Integer.toString(i));
    }

    public static IIcon forBlock(IIconRegister ir, Block block, String s) {
        return IconHelper.forName(ir, block.func_149739_a().replaceAll("tile.", "") + s);
    }

    public static IIcon forItem(IIconRegister ir, Item item) {
        return IconHelper.forName(ir, item.func_77658_a().replaceAll("item.", ""));
    }

    public static IIcon forItem(IIconRegister ir, Item item, int i) {
        return IconHelper.forItem(ir, item, Integer.toString(i));
    }

    public static IIcon forItem(IIconRegister ir, Item item, String s) {
        return IconHelper.forName(ir, item.func_77658_a().replaceAll("item.", "") + s);
    }
}

