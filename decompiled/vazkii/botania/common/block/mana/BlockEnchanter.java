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
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.mana;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileEnchanter;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockEnchanter
extends BlockModContainer
implements IWandable,
ILexiconable,
IWandHUD {
    Random random;
    public static IIcon overlay;

    public BlockEnchanter() {
        super(Material.field_151576_e);
        this.func_149711_c(3.0f);
        this.func_149752_b(5.0f);
        this.func_149715_a(1.0f);
        this.func_149672_a(field_149769_e);
        this.func_149663_c("enchanter");
        this.random = new Random();
    }

    @Override
    public boolean registerInCreative() {
        return false;
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        super.func_149651_a(par1IconRegister);
        overlay = IconHelper.forBlock(par1IconRegister, (Block)this, "Overlay");
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileEnchanter();
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.func_150898_a((Block)Blocks.field_150368_y);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        boolean stackEnchantable;
        TileEnchanter enchanter = (TileEnchanter)par1World.func_147438_o(par2, par3, par4);
        ItemStack stack = par5EntityPlayer.func_71045_bC();
        if (stack != null && stack.func_77973_b() == ModItems.twigWand) {
            return false;
        }
        boolean bl = stackEnchantable = stack != null && stack.func_77973_b() != Items.field_151122_aG && stack.func_77956_u() && stack.field_77994_a == 1 && stack.func_77973_b().getItemEnchantability(stack) > 0;
        if (enchanter.itemToEnchant == null) {
            if (stackEnchantable) {
                enchanter.itemToEnchant = stack.func_77946_l();
                par5EntityPlayer.field_71071_by.func_70299_a(par5EntityPlayer.field_71071_by.field_70461_c, null);
                enchanter.sync();
            }
        } else if (enchanter.stage == 0) {
            if (par5EntityPlayer.field_71071_by.func_70441_a(enchanter.itemToEnchant.func_77946_l())) {
                enchanter.itemToEnchant = null;
                enchanter.sync();
            } else {
                par5EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("botaniamisc.invFull", new Object[0]));
            }
        }
        return true;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileEnchanter enchanter = (TileEnchanter)par1World.func_147438_o(par2, par3, par4);
        ItemStack itemstack = enchanter.itemToEnchant;
        if (itemstack != null) {
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
                entityitem.field_70159_w = (double)((float)this.random.nextGaussian() * f3) * 0.5;
                entityitem.field_70181_x = (float)this.random.nextGaussian() * f3 + 0.2f;
                entityitem.field_70179_y = (double)((float)this.random.nextGaussian() * f3) * 0.5;
                if (itemstack.func_77942_o()) {
                    entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                }
                par1World.func_72838_d((Entity)entityitem);
            }
        }
        par1World.func_147453_f(par2, par3, par4, par5);
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    @Override
    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        ((TileEnchanter)world.func_147438_o(x, y, z)).onWanded(player, stack);
        return true;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.manaEnchanting;
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
        ((TileEnchanter)world.func_147438_o(x, y, z)).renderHUD(mc, res);
    }
}

