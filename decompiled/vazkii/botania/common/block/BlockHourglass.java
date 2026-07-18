/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IManaTrigger;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileHourglass;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockHourglass
extends BlockModContainer
implements IManaTrigger,
IWandable,
IWandHUD,
ILexiconable {
    Random random;

    protected BlockHourglass() {
        super(Material.field_151573_f);
        this.func_149663_c("hourglass");
        this.func_149711_c(2.0f);
        this.func_149672_a(field_149777_j);
        float f = 0.0625f;
        float w = 8.0f * f;
        float d = (1.0f - w) / 2.0f;
        this.func_149676_a(d, 0.0f, d, 1.0f - d, 1.15f, 1.0f - d);
        this.random = new Random();
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float xs, float ys, float zs) {
        TileHourglass hourglass = (TileHourglass)world.func_147438_o(x, y, z);
        ItemStack hgStack = hourglass.func_70301_a(0);
        ItemStack stack = player.func_71045_bC();
        if (stack != null && stack.func_77973_b() == ModItems.twigWand) {
            return false;
        }
        if (hourglass.lock) {
            if (!player.field_70170_p.field_72995_K) {
                player.func_145747_a((IChatComponent)new ChatComponentTranslation("botaniamisc.hourglassLock", new Object[0]));
            }
            return true;
        }
        if (hgStack == null && TileHourglass.getStackItemTime(stack) > 0) {
            hourglass.func_70299_a(0, stack.func_77946_l());
            hourglass.func_70296_d();
            stack.field_77994_a = 0;
            return true;
        }
        if (hgStack != null) {
            ItemStack copy = hgStack.func_77946_l();
            if (!player.field_71071_by.func_70441_a(copy)) {
                player.func_71019_a(copy, false);
            }
            hourglass.func_70299_a(0, null);
            hourglass.func_70296_d();
            return true;
        }
        return false;
    }

    public boolean func_149744_f() {
        return true;
    }

    public int func_149709_b(IBlockAccess world, int x, int y, int z, int side) {
        return world.func_72805_g(x, y, z) == 0 ? 0 : 15;
    }

    public int func_149738_a(World world) {
        return 4;
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        world.func_72921_c(x, y, z, 0, 3);
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileSimpleInventory inv = (TileSimpleInventory)par1World.func_147438_o(par2, par3, par4);
        if (inv != null) {
            for (int j1 = 0; j1 < inv.func_70302_i_(); ++j1) {
                ItemStack itemstack = inv.func_70301_a(j1);
                if (itemstack == null) continue;
                float f = this.random.nextFloat() * 0.8f + 0.1f;
                float f1 = this.random.nextFloat() * 0.8f + 0.1f;
                float f2 = this.random.nextFloat() * 0.8f + 0.1f;
                while (itemstack.field_77994_a > 0) {
                    int k1 = this.random.nextInt(21) + 10;
                    if (k1 > itemstack.field_77994_a) {
                        k1 = itemstack.field_77994_a;
                    }
                    itemstack.field_77994_a -= k1;
                    EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
                    float f3 = 0.05f;
                    entityitem.field_70159_w = (float)this.random.nextGaussian() * f3;
                    entityitem.field_70181_x = (float)this.random.nextGaussian() * f3 + 0.2f;
                    entityitem.field_70179_y = (float)this.random.nextGaussian() * f3;
                    if (itemstack.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                    }
                    par1World.func_72838_d((Entity)entityitem);
                }
            }
            par1World.func_147453_f(par2, par3, par4, par5);
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return ModBlocks.manaGlass.func_149691_a(0, 0);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return LibRenderIDs.idHourglass;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileHourglass();
    }

    @Override
    public void onBurstCollision(IManaBurst burst, World world, int x, int y, int z) {
        if (!world.field_72995_K && !burst.isFake()) {
            TileHourglass tile = (TileHourglass)world.func_147438_o(x, y, z);
            tile.move = !tile.move;
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(tile);
        }
    }

    @Override
    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        TileHourglass tile = (TileHourglass)world.func_147438_o(x, y, z);
        tile.lock = !tile.lock;
        return false;
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
        TileHourglass tile = (TileHourglass)world.func_147438_o(x, y, z);
        tile.renderHUD(res);
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.hourglass;
    }
}

