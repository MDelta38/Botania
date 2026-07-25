/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.wands.IWandRodOnUpdate
 *  thaumcraft.common.entities.EntityAspectOrb
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.utils.InventoryUtils
 */
package flaxbeard.thaumicexploration.wand;

import flaxbeard.thaumicexploration.ThaumicExploration;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.InventoryUtils;

public class WandRodNecromancerOnUpdate
implements IWandRodOnUpdate {
    private static final int intialClock = 6000;

    public void onUpdate(ItemStack itemstack, EntityPlayer par1EntityPlayer) {
        if (!itemstack.func_77942_o()) {
            System.out.println("starting compound tag");
            itemstack.func_77982_d(new NBTTagCompound());
            NBTTagCompound tag = new NBTTagCompound();
            ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
            for (Aspect aspect : Aspect.getPrimalAspects()) {
                tag.func_74768_a(aspect.getName(), wand.getVis(itemstack, aspect));
            }
            itemstack.field_77990_d.func_74782_a("lastAspects", (NBTBase)tag);
        }
        ItemWandCasting thisWand = (ItemWandCasting)itemstack.func_77973_b();
        NBTTagCompound lastAspectTag = itemstack.field_77990_d.func_74775_l("lastAspects");
        AspectList lastAspects = new AspectList();
        for (Object aspect : Aspect.getPrimalAspects()) {
            lastAspects.add((Aspect)aspect, lastAspectTag.func_74762_e(aspect.getName()));
        }
        AspectList currentAspects = thisWand.getAllVis(itemstack);
        for (Aspect aspect : Aspect.getPrimalAspects()) {
            int diff = currentAspects.getAmount(aspect) - lastAspects.getAmount(aspect);
            if (diff > 0) {
                diff = (int)((float)diff * 0.75f);
            }
            lastAspects.add(aspect, diff);
        }
        AxisAlignedBB boundingBox = AxisAlignedBB.func_72330_a((double)(par1EntityPlayer.field_70165_t - 3.0), (double)(par1EntityPlayer.field_70163_u - 3.0), (double)(par1EntityPlayer.field_70161_v - 3.0), (double)(par1EntityPlayer.field_70165_t + 3.0), (double)(par1EntityPlayer.field_70163_u + 3.0), (double)(par1EntityPlayer.field_70161_v + 3.0));
        List orbs = par1EntityPlayer.field_70170_p.func_72872_a(EntityAspectOrb.class, boundingBox);
        thisWand.storeAllVis(itemstack, lastAspects);
        NBTTagCompound tag = new NBTTagCompound();
        for (Aspect aspect : Aspect.getPrimalAspects()) {
            tag.func_74768_a(aspect.getName(), lastAspects.getAmount(aspect));
        }
        itemstack.field_77990_d.func_74782_a("lastAspects", (NBTBase)tag);
        for (EntityAspectOrb orb : orbs) {
            if (orb.field_70128_L) continue;
            int slot = InventoryUtils.isWandInHotbarWithRoom((Aspect)orb.getAspect(), (int)orb.getAspectValue(), (EntityPlayer)par1EntityPlayer);
            if (orb.orbCooldown != 0 || par1EntityPlayer.field_71090_bL != 0 || !orb.getAspect().isPrimal() || slot < 0) continue;
            ItemWandCasting wand = (ItemWandCasting)par1EntityPlayer.field_71071_by.field_70462_a[slot].func_77973_b();
            if (wand.getRod(par1EntityPlayer.field_71071_by.field_70462_a[slot]) == ThaumicExploration.WAND_ROD_NECRO) {
                wand.addVis(par1EntityPlayer.field_71071_by.field_70462_a[slot], orb.getAspect(), (int)((float)orb.getAspectValue() * 4.3333335f), true);
            }
            wand.addVis(par1EntityPlayer.field_71071_by.field_70462_a[slot], orb.getAspect(), orb.getAspectValue(), true);
            par1EntityPlayer.field_71090_bL = 2;
            orb.func_85030_a("random.orb", 0.1f, 0.5f * ((par1EntityPlayer.field_70170_p.field_73012_v.nextFloat() - par1EntityPlayer.field_70170_p.field_73012_v.nextFloat()) * 0.7f + 1.8f));
            orb.func_70106_y();
        }
    }
}

