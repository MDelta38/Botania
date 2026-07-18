/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.api.subtile.signature;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.api.subtile.signature.PassiveFlower;
import vazkii.botania.api.subtile.signature.SubTileSignature;

public class BasicSignature
extends SubTileSignature {
    final String name;

    public BasicSignature(String name) {
        this.name = name;
    }

    @Override
    public void registerIcons(IIconRegister register) {
        BotaniaAPI.internalHandler.registerBasicSignatureIcons(this.name, register);
    }

    @Override
    public IIcon getIconForStack(ItemStack stack) {
        return BotaniaAPI.internalHandler.getSubTileIconForName(this.name);
    }

    @Override
    public String getUnlocalizedNameForStack(ItemStack stack) {
        return this.unlocalizedName("");
    }

    @Override
    public String getUnlocalizedLoreTextForStack(ItemStack stack) {
        return this.unlocalizedName(".reference");
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        Class<? extends SubTileEntity> clazz = BotaniaAPI.getSubTileMapping(this.name);
        if (clazz == null) {
            return "uwotm8";
        }
        if (clazz.getAnnotation(PassiveFlower.class) != null) {
            return "botania.flowerType.passiveGenerating";
        }
        if (SubTileGenerating.class.isAssignableFrom(clazz)) {
            return "botania.flowerType.generating";
        }
        if (SubTileFunctional.class.isAssignableFrom(clazz)) {
            return "botania.flowerType.functional";
        }
        return "botania.flowerType.misc";
    }

    @Override
    public void addTooltip(ItemStack stack, EntityPlayer player, List<String> tooltip) {
        tooltip.add(EnumChatFormatting.BLUE + StatCollector.func_74838_a((String)this.getType()));
    }

    private String unlocalizedName(String end) {
        return "tile.botania:flower." + this.name + end;
    }
}

