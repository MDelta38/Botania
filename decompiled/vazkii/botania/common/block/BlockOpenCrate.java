/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileCraftCrate;
import vazkii.botania.common.block.tile.TileOpenCrate;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockOpenCrate
extends BlockModContainer
implements ILexiconable,
IWandable,
IWandHUD {
    IIcon iconSide;
    IIcon iconBottom;
    IIcon iconSideCraft;
    IIcon iconBottomCraft;
    IIcon[] sidePatternIcons;
    Random random;
    private static final int SUBTYPES = 2;

    public BlockOpenCrate() {
        super(Material.field_151575_d);
        this.func_149711_c(2.0f);
        this.func_149672_a(field_149766_f);
        this.func_149663_c("openCrate");
        this.random = new Random();
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    @Override
    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 2; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    public int func_149692_a(int meta) {
        return meta;
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World par1World, int par2, int par3, int par4, int par5) {
        TileOpenCrate crate = (TileOpenCrate)par1World.func_147438_o(par2, par3, par4);
        return crate.getSignal();
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
        this.iconSide = IconHelper.forBlock(par1IconRegister, (Block)this, 0);
        this.iconBottom = IconHelper.forBlock(par1IconRegister, (Block)this, 1);
        this.iconSideCraft = IconHelper.forBlock(par1IconRegister, (Block)this, 2);
        this.iconBottomCraft = IconHelper.forBlock(par1IconRegister, (Block)this, 3);
        this.sidePatternIcons = new IIcon[TileCraftCrate.PATTERNS.length];
        for (int i = 0; i < this.sidePatternIcons.length; ++i) {
            this.sidePatternIcons[i] = IconHelper.forName(par1IconRegister, "ocPattern" + i);
        }
    }

    public IIcon func_149691_a(int side, int meta) {
        return meta == 0 ? (side == 0 ? this.iconBottom : this.iconSide) : (side == 0 ? this.iconBottomCraft : this.iconSideCraft);
    }

    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileCraftCrate && ((TileCraftCrate)tile).pattern != -1 && side != 0) {
            return this.sidePatternIcons[((TileCraftCrate)tile).pattern];
        }
        return super.func_149673_e(world, x, y, z, side);
    }

    public TileEntity func_149915_a(World world, int meta) {
        return meta == 0 ? new TileOpenCrate() : new TileCraftCrate();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return world.func_72805_g(x, y, z) == 0 ? LexiconData.openCrate : LexiconData.craftCrate;
    }

    @Override
    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        TileOpenCrate crate = (TileOpenCrate)world.func_147438_o(x, y, z);
        return crate.onWanded(player, stack);
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile instanceof TileCraftCrate) {
            TileCraftCrate craft = (TileCraftCrate)tile;
            int width = 52;
            int height = 52;
            int xc = res.func_78326_a() / 2 + 20;
            int yc = res.func_78328_b() / 2 - height / 2;
            Gui.func_73734_a((int)(xc - 6), (int)(yc - 6), (int)(xc + width + 6), (int)(yc + height + 6), (int)0x22000000);
            Gui.func_73734_a((int)(xc - 4), (int)(yc - 4), (int)(xc + width + 4), (int)(yc + height + 4), (int)0x22000000);
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    int index = i * 3 + j;
                    int xp = xc + j * 18;
                    int yp = yc + i * 18;
                    boolean enabled = true;
                    if (craft.pattern > -1) {
                        enabled = TileCraftCrate.PATTERNS[craft.pattern][index];
                    }
                    Gui.func_73734_a((int)xp, (int)yp, (int)(xp + 16), (int)(yp + 16), (int)(enabled ? 0x22FFFFFF : 0x22FF0000));
                    ItemStack item = craft.func_70301_a(index);
                    RenderHelper.func_74520_c();
                    GL11.glEnable((int)32826);
                    RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, item, xp, yp);
                    RenderHelper.func_74518_a();
                }
            }
        }
    }
}

