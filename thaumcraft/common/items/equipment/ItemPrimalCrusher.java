/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Sets
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemTool
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.common.util.EnumHelper
 */
package thaumcraft.common.items.equipment;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.EnumHelper;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IWarpingGear;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.utils.BlockUtils;

public class ItemPrimalCrusher
extends ItemTool
implements IRepairable,
IWarpingGear {
    public static Item.ToolMaterial material = EnumHelper.addToolMaterial((String)"PRIMALVOID", (int)5, (int)500, (float)8.0f, (float)4.0f, (int)20);
    private static final Set isEffective = Sets.newHashSet((Object[])new Block[]{Blocks.field_150347_e, Blocks.field_150334_T, Blocks.field_150333_U, Blocks.field_150348_b, Blocks.field_150322_A, Blocks.field_150341_Y, Blocks.field_150366_p, Blocks.field_150339_S, Blocks.field_150365_q, Blocks.field_150340_R, Blocks.field_150352_o, Blocks.field_150482_ag, Blocks.field_150484_ah, Blocks.field_150432_aD, Blocks.field_150424_aL, Blocks.field_150369_x, Blocks.field_150368_y, Blocks.field_150450_ax, Blocks.field_150439_ay, Blocks.field_150448_aq, Blocks.field_150319_E, Blocks.field_150318_D, Blocks.field_150408_cc, Blocks.field_150349_c, Blocks.field_150346_d, Blocks.field_150354_m, Blocks.field_150351_n, Blocks.field_150431_aC, Blocks.field_150433_aE, Blocks.field_150435_aG, Blocks.field_150458_ak, Blocks.field_150425_aM, Blocks.field_150391_bh, ConfigBlocks.blockTaint, ConfigBlocks.blockTaintFibres, Blocks.field_150343_Z});
    public IIcon icon;
    int side = 0;

    public ItemPrimalCrusher(Item.ToolMaterial enumtoolmaterial) {
        super(3.5f, enumtoolmaterial, isEffective);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    public boolean func_150897_b(Block p_150897_1_) {
        return p_150897_1_.func_149688_o() != Material.field_151575_d && p_150897_1_.func_149688_o() != Material.field_151584_j && p_150897_1_.func_149688_o() != Material.field_151585_k;
    }

    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_) {
        return p_150893_2_.func_149688_o() != Material.field_151573_f && p_150893_2_.func_149688_o() != Material.field_151574_g && p_150893_2_.func_149688_o() != Material.field_151576_e ? super.func_150893_a(p_150893_1_, p_150893_2_) : this.field_77864_a;
    }

    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of((Object)"shovel", (Object)"pickaxe");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:primal_crusher");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.epic;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 15)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    private boolean isEffectiveAgainst(Block block) {
        for (Object b : isEffective) {
            if (b != block) continue;
            return true;
        }
        return false;
    }

    public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player) {
        MovingObjectPosition movingobjectposition = BlockUtils.getTargetBlock(player.field_70170_p, (Entity)player, true);
        if (movingobjectposition != null && movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            this.side = movingobjectposition.field_72310_e;
        }
        return super.onBlockStartBreak(itemstack, X, Y, Z, player);
    }

    public boolean func_150894_a(ItemStack stack, World world, Block bi, int x, int y, int z, EntityLivingBase ent) {
        int md;
        if (ent.func_70093_af()) {
            return super.func_150894_a(stack, world, bi, x, y, z, ent);
        }
        if (!ent.field_70170_p.field_72995_K && (ForgeHooks.isToolEffective((ItemStack)stack, (Block)bi, (int)(md = world.func_72805_g(x, y, z))) || this.isEffectiveAgainst(bi))) {
            for (int aa = -1; aa <= 1; ++aa) {
                for (int bb = -1; bb <= 1; ++bb) {
                    int xx = 0;
                    int yy = 0;
                    int zz = 0;
                    if (this.side <= 1) {
                        xx = aa;
                        zz = bb;
                    } else if (this.side <= 3) {
                        xx = aa;
                        yy = bb;
                    } else {
                        zz = aa;
                        yy = bb;
                    }
                    if (ent instanceof EntityPlayer && !world.func_72962_a((EntityPlayer)ent, x + xx, y + yy, z + zz)) continue;
                    Block bl = world.func_147439_a(x + xx, y + yy, z + zz);
                    md = world.func_72805_g(x + xx, y + yy, z + zz);
                    if (!(bl.func_149712_f(world, x + xx, y + yy, z + zz) >= 0.0f) || !ForgeHooks.isToolEffective((ItemStack)stack, (Block)bl, (int)md) && !this.isEffectiveAgainst(bl)) continue;
                    stack.func_77972_a(1, ent);
                    BlockUtils.harvestBlock(world, (EntityPlayer)ent, x + xx, y + yy, z + zz, true, 2);
                }
            }
        }
        return true;
    }

    public int func_77619_b() {
        return 20;
    }

    @Override
    public int getWarp(ItemStack itemstack, EntityPlayer player) {
        return 2;
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
        if (stack.func_77951_h() && entity != null && entity.field_70173_aa % 20 == 0 && entity instanceof EntityLivingBase) {
            stack.func_77972_a(-1, (EntityLivingBase)entity);
        }
    }
}

