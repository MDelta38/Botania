/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$InterfaceList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.IGoggles
 *  thaumcraft.api.nodes.IRevealer
 */
package vazkii.botania.common.item.interaction.thaumcraft;

import cpw.mods.fml.common.Optional;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import thaumcraft.api.IGoggles;
import thaumcraft.api.nodes.IRevealer;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.equipment.armor.elementium.ItemElementiumHelm;

@Optional.InterfaceList(value={@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.IGoggles", striprefs=true), @Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.nodes.IRevealer", striprefs=true)})
public class ItemElementiumHelmRevealing
extends ItemElementiumHelm
implements IGoggles,
IRevealer {
    public ItemElementiumHelmRevealing() {
        super("elementiumHelmReveal");
    }

    public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public String getArmorTextureAfterInk(ItemStack stack, int slot) {
        return ConfigHandler.enableArmorModels ? "botania:textures/model/elementiumNew.png" : "botania:textures/model/elementium2.png";
    }
}

