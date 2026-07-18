/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 */
package vazkii.botania.client.core.helper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public final class IconHelper {
    public static IIcon forName(IIconRegister ir, String name) {
        return ir.func_94245_a("botania:" + name);
    }

    public static IIcon forName(IIconRegister ir, String name, String dir) {
        return ir.func_94245_a("botania:" + dir + "/" + name);
    }

    public static IIcon forBlock(IIconRegister ir, Block block) {
        return IconHelper.forName(ir, block.func_149739_a().replaceAll("tile\\.", ""));
    }

    public static IIcon forBlock(IIconRegister ir, Block block, int i) {
        return IconHelper.forBlock(ir, block, Integer.toString(i));
    }

    public static IIcon forBlock(IIconRegister ir, Block block, int i, String dir) {
        return IconHelper.forBlock(ir, block, Integer.toString(i), dir);
    }

    public static IIcon forBlock(IIconRegister ir, Block block, String s) {
        return IconHelper.forName(ir, block.func_149739_a().replaceAll("tile\\.", "") + s);
    }

    public static IIcon forBlock(IIconRegister ir, Block block, String s, String dir) {
        return IconHelper.forName(ir, block.func_149739_a().replaceAll("tile\\.", "") + s, dir);
    }

    public static IIcon forItem(IIconRegister ir, Item item) {
        return IconHelper.forName(ir, item.func_77658_a().replaceAll("item\\.", ""));
    }

    public static IIcon forItem(IIconRegister ir, Item item, int i) {
        return IconHelper.forItem(ir, item, Integer.toString(i));
    }

    public static IIcon forItem(IIconRegister ir, Item item, String s) {
        return IconHelper.forName(ir, item.func_77658_a().replaceAll("item\\.", "") + s);
    }
}

