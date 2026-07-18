/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import vazkii.botania.api.item.ISortableTool;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

public class ItemSwapRing
extends ItemBauble {
    public ItemSwapRing() {
        super("swapRing");
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase entity) {
        Block block;
        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)entity;
        ItemStack currentStack = player.func_71045_bC();
        if (currentStack == null || !(currentStack.func_77973_b() instanceof ISortableTool)) {
            return;
        }
        ISortableTool tool = (ISortableTool)currentStack.func_77973_b();
        MovingObjectPosition pos = ToolCommons.raytraceFromEntity(entity.field_70170_p, (Entity)entity, true, 4.5);
        ISortableTool.ToolType typeToFind = null;
        if (player.field_82175_bq && pos != null && (block = entity.field_70170_p.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d)) != null) {
            Material mat = block.func_149688_o();
            if (ToolCommons.isRightMaterial(mat, ToolCommons.materialsPick)) {
                typeToFind = ISortableTool.ToolType.PICK;
            } else if (ToolCommons.isRightMaterial(mat, ToolCommons.materialsShovel)) {
                typeToFind = ISortableTool.ToolType.SHOVEL;
            } else if (ToolCommons.isRightMaterial(mat, ToolCommons.materialsAxe)) {
                typeToFind = ISortableTool.ToolType.AXE;
            }
        }
        if (typeToFind == null) {
            return;
        }
        ItemStack bestTool = currentStack;
        int bestToolPriority = tool.getSortingType(currentStack) == typeToFind ? tool.getSortingPriority(currentStack) : -1;
        int bestSlot = -1;
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); ++i) {
            int priority;
            ISortableTool toolInSlot;
            ItemStack stackInSlot = player.field_71071_by.func_70301_a(i);
            if (stackInSlot == null || !(stackInSlot.func_77973_b() instanceof ISortableTool) || stackInSlot == currentStack || !(toolInSlot = (ISortableTool)stackInSlot.func_77973_b()).getSortingType(stackInSlot).equals((Object)typeToFind) || (priority = toolInSlot.getSortingPriority(stackInSlot)) <= bestToolPriority) continue;
            bestTool = stackInSlot;
            bestToolPriority = priority;
            bestSlot = i;
        }
        if (bestSlot != -1) {
            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, bestTool);
            player.field_71071_by.func_70299_a(bestSlot, currentStack);
        }
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.RING;
    }
}

