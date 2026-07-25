/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
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
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemSpade
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.items.equipment;

import com.google.common.collect.ImmutableSet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
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
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.BlockCoordinates;
import thaumcraft.api.IArchitect;
import thaumcraft.api.IRepairable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.InventoryUtils;

public class ItemElementalShovel
extends ItemSpade
implements IRepairable,
IArchitect {
    private static final Block[] isEffective = new Block[]{Blocks.field_150349_c, Blocks.field_150346_d, Blocks.field_150354_m, Blocks.field_150351_n, Blocks.field_150431_aC, Blocks.field_150433_aE, Blocks.field_150435_aG, Blocks.field_150458_ak, Blocks.field_150425_aM, Blocks.field_150391_bh};
    public IIcon icon;
    int side = 0;

    public ItemElementalShovel(Item.ToolMaterial enumtoolmaterial) {
        super(enumtoolmaterial);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of((Object)"shovel");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:elementalshovel");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.rare;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        int xm = ForgeDirection.getOrientation((int)side).offsetX;
        int ym = ForgeDirection.getOrientation((int)side).offsetY;
        int zm = ForgeDirection.getOrientation((int)side).offsetZ;
        Block bi = world.func_147439_a(x, y, z);
        int md = world.func_72805_g(x, y, z);
        TileEntity te = world.func_147438_o(x, y, z);
        if (te == null) {
            for (int aa = -1; aa <= 1; ++aa) {
                for (int bb = -1; bb <= 1; ++bb) {
                    int xx = 0;
                    int yy = 0;
                    int zz = 0;
                    byte o = ItemElementalShovel.getOrientation(itemstack);
                    if (o == 1) {
                        yy = bb;
                        if (side <= 1) {
                            int l = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
                            if (l == 0 || l == 2) {
                                xx = aa;
                            } else {
                                zz = aa;
                            }
                        } else if (side <= 3) {
                            zz = aa;
                        } else {
                            xx = aa;
                        }
                    } else if (o == 2) {
                        if (side <= 1) {
                            int l = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
                            yy = bb;
                            if (l == 0 || l == 2) {
                                xx = aa;
                            } else {
                                zz = aa;
                            }
                        } else {
                            zz = bb;
                            xx = aa;
                        }
                    } else if (side <= 1) {
                        xx = aa;
                        zz = bb;
                    } else if (side <= 3) {
                        xx = aa;
                        yy = bb;
                    } else {
                        zz = aa;
                        yy = bb;
                    }
                    Block b2 = world.func_147439_a(x + xx + xm, y + yy + ym, z + zz + zm);
                    if (!world.func_147437_c(x + xx + xm, y + yy + ym, z + zz + zm) && b2 != Blocks.field_150395_bd && b2 != Blocks.field_150329_H && b2.func_149688_o() != Material.field_151586_h && b2 != Blocks.field_150330_I && !b2.isReplaceable((IBlockAccess)world, x + xx + xm, y + yy + ym, z + zz + zm)) continue;
                    if (player.field_71075_bZ.field_75098_d || InventoryUtils.consumeInventoryItem(player, Item.func_150898_a((Block)bi), md)) {
                        world.func_72980_b((double)(x + xx + xm), (double)(y + yy + ym), (double)(z + zz + zm), bi.field_149762_H.func_150496_b(), 0.6f, 0.9f + world.field_73012_v.nextFloat() * 0.2f, false);
                        world.func_147465_d(x + xx + xm, y + yy + ym, z + zz + zm, bi, md, 3);
                        itemstack.func_77972_a(1, (EntityLivingBase)player);
                        Thaumcraft.proxy.blockSparkle(world, x + xx + xm, y + yy + ym, z + zz + zm, 8401408, 4);
                        player.func_71038_i();
                        continue;
                    }
                    if (bi != Blocks.field_150349_c || !player.field_71075_bZ.field_75098_d && !InventoryUtils.consumeInventoryItem(player, Item.func_150898_a((Block)Blocks.field_150346_d), 0)) continue;
                    world.func_72980_b((double)(x + xx + xm), (double)(y + yy + ym), (double)(z + zz + zm), bi.field_149762_H.func_150496_b(), 0.6f, 0.9f + world.field_73012_v.nextFloat() * 0.2f, false);
                    world.func_147465_d(x + xx + xm, y + yy + ym, z + zz + zm, Blocks.field_150346_d, 0, 3);
                    itemstack.func_77972_a(1, (EntityLivingBase)player);
                    Thaumcraft.proxy.blockSparkle(world, x + xx + xm, y + yy + ym, z + zz + zm, 3, 4);
                    player.func_71038_i();
                }
            }
        }
        return false;
    }

    private boolean isEffectiveAgainst(Block block) {
        int var3 = 0;
        while (true) {
            if (var3 >= isEffective.length) break;
            if (isEffective[var3] == block) {
                return true;
            }
            ++var3;
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
                    BlockUtils.harvestBlock(world, (EntityPlayer)ent, x + xx, y + yy, z + zz, true, 3);
                }
            }
        }
        return true;
    }

    @Override
    public ArrayList<BlockCoordinates> getArchitectBlocks(ItemStack focusstack, World world, int x, int y, int z, int side, EntityPlayer player) {
        ArrayList<BlockCoordinates> b = new ArrayList<BlockCoordinates>();
        if (!player.func_70093_af()) {
            return b;
        }
        int xm = ForgeDirection.getOrientation((int)side).offsetX;
        int ym = ForgeDirection.getOrientation((int)side).offsetY;
        int zm = ForgeDirection.getOrientation((int)side).offsetZ;
        for (int aa = -1; aa <= 1; ++aa) {
            for (int bb = -1; bb <= 1; ++bb) {
                int xx = 0;
                int yy = 0;
                int zz = 0;
                byte o = ItemElementalShovel.getOrientation(focusstack);
                if (o == 1) {
                    yy = bb;
                    if (side <= 1) {
                        int l = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
                        if (l == 0 || l == 2) {
                            xx = aa;
                        } else {
                            zz = aa;
                        }
                    } else if (side <= 3) {
                        zz = aa;
                    } else {
                        xx = aa;
                    }
                } else if (o == 2) {
                    if (side <= 1) {
                        int l = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
                        yy = bb;
                        if (l == 0 || l == 2) {
                            xx = aa;
                        } else {
                            zz = aa;
                        }
                    } else {
                        zz = bb;
                        xx = aa;
                    }
                } else if (side <= 1) {
                    xx = aa;
                    zz = bb;
                } else if (side <= 3) {
                    xx = aa;
                    yy = bb;
                } else {
                    zz = aa;
                    yy = bb;
                }
                Block b2 = world.func_147439_a(x + xx + xm, y + yy + ym, z + zz + zm);
                if (!world.func_147437_c(x + xx + xm, y + yy + ym, z + zz + zm) && b2 != Blocks.field_150395_bd && b2 != Blocks.field_150329_H && b2.func_149688_o() != Material.field_151586_h && b2 != Blocks.field_150330_I && !b2.isReplaceable((IBlockAccess)world, x + xx + xm, y + yy + ym, z + zz + zm)) continue;
                b.add(new BlockCoordinates(x + xx + xm, y + yy + ym, z + zz + zm));
            }
        }
        return b;
    }

    @Override
    public boolean showAxis(ItemStack stack, World world, EntityPlayer player, int side, IArchitect.EnumAxis axis) {
        return false;
    }

    public static byte getOrientation(ItemStack stack) {
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("or")) {
            return stack.field_77990_d.func_74771_c("or");
        }
        return 0;
    }

    public static void setOrientation(ItemStack stack, byte o) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        if (stack.func_77942_o()) {
            stack.field_77990_d.func_74774_a("or", (byte)(o % 3));
        }
    }
}

