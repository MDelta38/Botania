/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package vazkii.botania.api.subtile.signature;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public abstract class SubTileSignature {
    public static final String SPECIAL_FLOWER_PREFIX = "flower.";

    public abstract void registerIcons(IIconRegister var1);

    public abstract IIcon getIconForStack(ItemStack var1);

    public abstract String getUnlocalizedNameForStack(ItemStack var1);

    public abstract String getUnlocalizedLoreTextForStack(ItemStack var1);

    public void addTooltip(ItemStack stack, EntityPlayer player, List<String> tooltip) {
    }
}

