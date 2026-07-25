/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.api;

import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaTransport;

public class ThaumcraftApiHelper {
    private static HashMap<Integer, AspectList> allAspects = new HashMap();
    private static HashMap<Integer, AspectList> allCompoundAspects = new HashMap();

    public static AspectList cullTags(AspectList temp) {
        AspectList temp2 = new AspectList();
        for (Aspect tag : temp.getAspects()) {
            if (tag == null) continue;
            temp2.add(tag, temp.getAmount(tag));
        }
        while (temp2 != null && temp2.size() > 6) {
            Aspect lowest = null;
            float low = 32767.0f;
            for (Aspect tag : temp2.getAspects()) {
                if (tag == null) continue;
                float ta = temp2.getAmount(tag);
                if (tag.isPrimal()) {
                    ta *= 0.9f;
                } else {
                    if (!tag.getComponents()[0].isPrimal()) {
                        ta *= 1.1f;
                        if (!tag.getComponents()[0].getComponents()[0].isPrimal()) {
                            ta *= 1.05f;
                        }
                        if (!tag.getComponents()[0].getComponents()[1].isPrimal()) {
                            ta *= 1.05f;
                        }
                    }
                    if (!tag.getComponents()[1].isPrimal()) {
                        ta *= 1.1f;
                        if (!tag.getComponents()[1].getComponents()[0].isPrimal()) {
                            ta *= 1.05f;
                        }
                        if (!tag.getComponents()[1].getComponents()[1].isPrimal()) {
                            ta *= 1.05f;
                        }
                    }
                }
                if (!(ta < low)) continue;
                low = ta;
                lowest = tag;
            }
            temp2.aspects.remove(lowest);
        }
        return temp2;
    }

    public static boolean areItemsEqual(ItemStack s1, ItemStack s2) {
        if (s1.func_77984_f() && s2.func_77984_f()) {
            return s1.func_77973_b() == s2.func_77973_b();
        }
        return s1.func_77973_b() == s2.func_77973_b() && s1.func_77960_j() == s2.func_77960_j();
    }

    public static boolean isResearchComplete(String username, String researchkey) {
        return ThaumcraftApi.internalMethods.isResearchComplete(username, researchkey);
    }

    public static boolean hasDiscoveredAspect(String username, Aspect aspect) {
        return ThaumcraftApi.internalMethods.hasDiscoveredAspect(username, aspect);
    }

    public static AspectList getDiscoveredAspects(String username) {
        return ThaumcraftApi.internalMethods.getDiscoveredAspects(username);
    }

    public static ItemStack getStackInRowAndColumn(Object instance, int row, int column) {
        return ThaumcraftApi.internalMethods.getStackInRowAndColumn(instance, row, column);
    }

    public static AspectList getObjectAspects(ItemStack is) {
        return ThaumcraftApi.internalMethods.getObjectAspects(is);
    }

    public static AspectList getBonusObjectTags(ItemStack is, AspectList ot) {
        return ThaumcraftApi.internalMethods.getBonusObjectTags(is, ot);
    }

    public static AspectList generateTags(Item item, int meta) {
        return ThaumcraftApi.internalMethods.generateTags(item, meta);
    }

    public static boolean containsMatch(boolean strict, ItemStack[] inputs, ItemStack ... targets) {
        for (ItemStack input : inputs) {
            for (ItemStack target : targets) {
                if (!ThaumcraftApiHelper.itemMatches(target, input, strict)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean areItemStackTagsEqualForCrafting(ItemStack slotItem, ItemStack recipeItem) {
        if (recipeItem == null || slotItem == null) {
            return false;
        }
        if (recipeItem.field_77990_d != null && slotItem.field_77990_d == null) {
            return false;
        }
        if (recipeItem.field_77990_d == null) {
            return true;
        }
        for (String s : recipeItem.field_77990_d.func_150296_c()) {
            if (slotItem.field_77990_d.func_74764_b(s)) {
                if (slotItem.field_77990_d.func_74781_a(s).toString().equals(recipeItem.field_77990_d.func_74781_a(s).toString())) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    public static boolean itemMatches(ItemStack target, ItemStack input, boolean strict) {
        if (input == null && target != null || input != null && target == null) {
            return false;
        }
        return target.func_77973_b() == input.func_77973_b() && (target.func_77960_j() == Short.MAX_VALUE && !strict || target.func_77960_j() == input.func_77960_j());
    }

    public static TileEntity getConnectableTile(World world, int x, int y, int z, ForgeDirection face) {
        TileEntity te = world.func_147438_o(x + face.offsetX, y + face.offsetY, z + face.offsetZ);
        if (te instanceof IEssentiaTransport && ((IEssentiaTransport)te).isConnectable(face.getOpposite())) {
            return te;
        }
        return null;
    }

    public static TileEntity getConnectableTile(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        TileEntity te = world.func_147438_o(x + face.offsetX, y + face.offsetY, z + face.offsetZ);
        if (te instanceof IEssentiaTransport && ((IEssentiaTransport)te).isConnectable(face.getOpposite())) {
            return te;
        }
        return null;
    }

    public static AspectList getAllAspects(int amount) {
        if (allAspects.get(amount) == null) {
            AspectList al = new AspectList();
            for (Aspect aspect : Aspect.aspects.values()) {
                al.add(aspect, amount);
            }
            allAspects.put(amount, al);
        }
        return allAspects.get(amount);
    }

    public static AspectList getAllCompoundAspects(int amount) {
        if (allCompoundAspects.get(amount) == null) {
            AspectList al = new AspectList();
            for (Aspect aspect : Aspect.getCompoundAspects()) {
                al.add(aspect, amount);
            }
            allCompoundAspects.put(amount, al);
        }
        return allCompoundAspects.get(amount);
    }

    public static boolean consumeVisFromWand(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit, boolean crafting) {
        return ThaumcraftApi.internalMethods.consumeVisFromWand(wand, player, cost, doit, crafting);
    }

    public static boolean consumeVisFromWandCrafting(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit) {
        return ThaumcraftApi.internalMethods.consumeVisFromWandCrafting(wand, player, cost, doit);
    }

    public static boolean consumeVisFromInventory(EntityPlayer player, AspectList cost) {
        return ThaumcraftApi.internalMethods.consumeVisFromInventory(player, cost);
    }

    public static void addWarpToPlayer(EntityPlayer player, int amount, boolean temporary) {
        ThaumcraftApi.internalMethods.addWarpToPlayer(player, amount, temporary);
    }

    public static void addStickyWarpToPlayer(EntityPlayer player, int amount) {
        ThaumcraftApi.internalMethods.addStickyWarpToPlayer(player, amount);
    }

    public static MovingObjectPosition rayTraceIgnoringSource(World world, Vec3 v1, Vec3 v2, boolean bool1, boolean bool2, boolean bool3) {
        if (!(Double.isNaN(v1.field_72450_a) || Double.isNaN(v1.field_72448_b) || Double.isNaN(v1.field_72449_c))) {
            if (!(Double.isNaN(v2.field_72450_a) || Double.isNaN(v2.field_72448_b) || Double.isNaN(v2.field_72449_c))) {
                int i = MathHelper.func_76128_c((double)v2.field_72450_a);
                int j = MathHelper.func_76128_c((double)v2.field_72448_b);
                int k = MathHelper.func_76128_c((double)v2.field_72449_c);
                int l = MathHelper.func_76128_c((double)v1.field_72450_a);
                int i1 = MathHelper.func_76128_c((double)v1.field_72448_b);
                int j1 = MathHelper.func_76128_c((double)v1.field_72449_c);
                Block block = world.func_147439_a(l, i1, j1);
                int k1 = world.func_72805_g(l, i1, j1);
                MovingObjectPosition movingobjectposition2 = null;
                k1 = 200;
                while (k1-- >= 0) {
                    int b0;
                    if (Double.isNaN(v1.field_72450_a) || Double.isNaN(v1.field_72448_b) || Double.isNaN(v1.field_72449_c)) {
                        return null;
                    }
                    if (l == i && i1 == j && j1 == k) continue;
                    boolean flag6 = true;
                    boolean flag3 = true;
                    boolean flag4 = true;
                    double d0 = 999.0;
                    double d1 = 999.0;
                    double d2 = 999.0;
                    if (i > l) {
                        d0 = (double)l + 1.0;
                    } else if (i < l) {
                        d0 = (double)l + 0.0;
                    } else {
                        flag6 = false;
                    }
                    if (j > i1) {
                        d1 = (double)i1 + 1.0;
                    } else if (j < i1) {
                        d1 = (double)i1 + 0.0;
                    } else {
                        flag3 = false;
                    }
                    if (k > j1) {
                        d2 = (double)j1 + 1.0;
                    } else if (k < j1) {
                        d2 = (double)j1 + 0.0;
                    } else {
                        flag4 = false;
                    }
                    double d3 = 999.0;
                    double d4 = 999.0;
                    double d5 = 999.0;
                    double d6 = v2.field_72450_a - v1.field_72450_a;
                    double d7 = v2.field_72448_b - v1.field_72448_b;
                    double d8 = v2.field_72449_c - v1.field_72449_c;
                    if (flag6) {
                        d3 = (d0 - v1.field_72450_a) / d6;
                    }
                    if (flag3) {
                        d4 = (d1 - v1.field_72448_b) / d7;
                    }
                    if (flag4) {
                        d5 = (d2 - v1.field_72449_c) / d8;
                    }
                    boolean flag5 = false;
                    if (d3 < d4 && d3 < d5) {
                        b0 = i > l ? 4 : 5;
                        v1.field_72450_a = d0;
                        v1.field_72448_b += d7 * d3;
                        v1.field_72449_c += d8 * d3;
                    } else if (d4 < d5) {
                        b0 = j > i1 ? 0 : 1;
                        v1.field_72450_a += d6 * d4;
                        v1.field_72448_b = d1;
                        v1.field_72449_c += d8 * d4;
                    } else {
                        b0 = k > j1 ? 2 : 3;
                        v1.field_72450_a += d6 * d5;
                        v1.field_72448_b += d7 * d5;
                        v1.field_72449_c = d2;
                    }
                    Vec3 vec32 = Vec3.func_72443_a((double)v1.field_72450_a, (double)v1.field_72448_b, (double)v1.field_72449_c);
                    vec32.field_72450_a = MathHelper.func_76128_c((double)v1.field_72450_a);
                    l = (int)vec32.field_72450_a;
                    if (b0 == 5) {
                        --l;
                        vec32.field_72450_a += 1.0;
                    }
                    vec32.field_72448_b = MathHelper.func_76128_c((double)v1.field_72448_b);
                    i1 = (int)vec32.field_72448_b;
                    if (b0 == 1) {
                        --i1;
                        vec32.field_72448_b += 1.0;
                    }
                    vec32.field_72449_c = MathHelper.func_76128_c((double)v1.field_72449_c);
                    j1 = (int)vec32.field_72449_c;
                    if (b0 == 3) {
                        --j1;
                        vec32.field_72449_c += 1.0;
                    }
                    Block block1 = world.func_147439_a(l, i1, j1);
                    int l1 = world.func_72805_g(l, i1, j1);
                    if (bool2 && block1.func_149668_a(world, l, i1, j1) == null) continue;
                    if (block1.func_149678_a(l1, bool1)) {
                        MovingObjectPosition movingobjectposition1 = block1.func_149731_a(world, l, i1, j1, v1, v2);
                        if (movingobjectposition1 == null) continue;
                        return movingobjectposition1;
                    }
                    movingobjectposition2 = new MovingObjectPosition(l, i1, j1, b0, v1, false);
                }
                return bool3 ? movingobjectposition2 : null;
            }
            return null;
        }
        return null;
    }
}

