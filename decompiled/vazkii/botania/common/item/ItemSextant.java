/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

public class ItemSextant
extends ItemMod {
    private static final String TAG_SOURCE_X = "sourceX";
    private static final String TAG_SOURCE_Y = "sourceY";
    private static final String TAG_SOURCE_Z = "sourceZ";

    public ItemSextant() {
        this.func_77655_b("sextant");
        this.func_77625_d(1);
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (this.func_77626_a(stack) - count < 10) {
            return;
        }
        int x = ItemNBTHelper.getInt(stack, TAG_SOURCE_X, 0);
        int y = ItemNBTHelper.getInt(stack, TAG_SOURCE_Y, -1);
        int z = ItemNBTHelper.getInt(stack, TAG_SOURCE_Z, 0);
        if (y != -1) {
            World world = player.field_70170_p;
            Vector3 source = new Vector3(x, y, z);
            double radius = ItemSextant.calculateRadius(stack, player);
            if (count % 10 == 0) {
                for (int i = 0; i < 360; ++i) {
                    float radian = (float)((double)i * Math.PI / 180.0);
                    double xp = (double)x + Math.cos(radian) * radius;
                    double zp = (double)z + Math.sin(radian) * radius;
                    Botania.proxy.wispFX(world, xp + 0.5, source.y + 1.0, zp + 0.5, 0.0f, 1.0f, 1.0f, 0.3f, -0.01f);
                }
            }
        }
    }

    public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int time) {
        double radius = ItemSextant.calculateRadius(stack, player);
        if (radius > 1.0) {
            int x = ItemNBTHelper.getInt(stack, TAG_SOURCE_X, 0);
            int y = ItemNBTHelper.getInt(stack, TAG_SOURCE_Y, -1);
            int z = ItemNBTHelper.getInt(stack, TAG_SOURCE_Z, 0);
            if (y != -1) {
                Botania.proxy.setMultiblock(world, x, y, z, radius, Blocks.field_150347_e);
            }
        }
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        Botania.proxy.removeSextantMultiblock();
        if (!par3EntityPlayer.func_70093_af()) {
            MovingObjectPosition pos = ToolCommons.raytraceFromEntity(par2World, (Entity)par3EntityPlayer, false, 128.0);
            if (pos != null && pos.field_72308_g == null) {
                if (!par2World.field_72995_K) {
                    ItemNBTHelper.setInt(par1ItemStack, TAG_SOURCE_X, pos.field_72311_b);
                    ItemNBTHelper.setInt(par1ItemStack, TAG_SOURCE_Y, pos.field_72312_c);
                    ItemNBTHelper.setInt(par1ItemStack, TAG_SOURCE_Z, pos.field_72309_d);
                }
            } else {
                ItemNBTHelper.setInt(par1ItemStack, TAG_SOURCE_Y, -1);
            }
            par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        }
        return par1ItemStack;
    }

    public static double calculateRadius(ItemStack stack, EntityPlayer player) {
        int x = ItemNBTHelper.getInt(stack, TAG_SOURCE_X, 0);
        int y = ItemNBTHelper.getInt(stack, TAG_SOURCE_Y, -1);
        int z = ItemNBTHelper.getInt(stack, TAG_SOURCE_Z, 0);
        World world = player.field_70170_p;
        Vector3 source = new Vector3(x, y, z);
        Botania.proxy.wispFX(world, source.x + 0.5, source.y + 1.0, source.z + 0.5, 1.0f, 0.0f, 0.0f, 0.2f, -0.1f);
        Vector3 centerVec = Vector3.fromEntityCenter((Entity)player);
        Vector3 diffVec = source.copy().subtract(centerVec);
        Vector3 lookVec = new Vector3(player.func_70040_Z());
        double mul = diffVec.y / lookVec.y;
        lookVec.multiply(mul).add(centerVec);
        lookVec.x = MathHelper.func_76128_c((double)lookVec.x);
        lookVec.z = MathHelper.func_76128_c((double)lookVec.z);
        return vazkii.botania.common.core.helper.MathHelper.pointDistancePlane(source.x, source.z, lookVec.x, lookVec.z);
    }

    @SideOnly(value=Side.CLIENT)
    public static void renderHUD(ScaledResolution resolution, EntityPlayer player, ItemStack stack) {
        ItemStack onUse = player.func_71011_bu();
        int time = player.func_71052_bv();
        if (onUse == stack && stack.func_77973_b().func_77626_a(stack) - time >= 10) {
            double radius = ItemSextant.calculateRadius(stack, player);
            FontRenderer font = Minecraft.func_71410_x().field_71466_p;
            int x = resolution.func_78326_a() / 2 + 30;
            int y = resolution.func_78328_b() / 2;
            String s = "" + (int)radius;
            font.func_78261_a(s, x - font.func_78256_a(s) / 2, y - 4, 0xFFFFFF);
            if (radius > 0.0) {
                radius += 4.0;
                GL11.glDisable((int)3553);
                GL11.glLineWidth((float)3.0f);
                GL11.glBegin((int)3);
                GL11.glColor4f((float)0.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                for (int i = 0; i < 361; ++i) {
                    float radian = (float)((double)i * Math.PI / 180.0);
                    double xp = (double)x + Math.cos(radian) * radius;
                    double yp = (double)y + Math.sin(radian) * radius;
                    GL11.glVertex2d((double)xp, (double)yp);
                }
                GL11.glEnd();
                GL11.glEnable((int)3553);
            }
        }
    }

    public static class MultiblockSextant
    extends Multiblock {
        @Override
        public Multiblock[] createRotations() {
            return new Multiblock[]{this};
        }
    }
}

