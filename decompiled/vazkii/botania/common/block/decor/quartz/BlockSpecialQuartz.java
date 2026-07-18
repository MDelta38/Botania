/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor.quartz;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.item.block.ItemBlockSpecialQuartz;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockSpecialQuartz
extends BlockMod
implements ILexiconable {
    private final String[] iconNames;
    public final String type;
    private IIcon[] specialQuartzIcons;
    private IIcon chiseledSpecialQuartzIcon;
    private IIcon pillarSpecialQuartzIcon;
    private IIcon specialQuartzTopIcon;

    public BlockSpecialQuartz(String type) {
        super(Material.field_151576_e);
        this.type = type;
        this.iconNames = new String[]{"block" + type + "Quartz0", "chiseled" + type + "Quartz0", "pillar" + type + "Quartz0", null, null};
        this.func_149711_c(0.8f);
        this.func_149752_b(10.0f);
        this.func_149663_c("quartzType" + type);
    }

    @Override
    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockSpecialQuartz.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    public String[] getNames() {
        return new String[]{"tile.botania:block" + this.type + "Quartz", "tile.botania:chiseled" + this.type + "Quartz", "tile.botania:pillar" + this.type + "Quartz"};
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        if (par2 != 2 && par2 != 3 && par2 != 4) {
            if (par1 != 1 && (par1 != 0 || par2 != 1)) {
                if (par1 == 0) {
                    return this.specialQuartzTopIcon;
                }
                if (par2 < 0 || par2 >= this.specialQuartzIcons.length) {
                    par2 = 0;
                }
                return this.specialQuartzIcons[par2];
            }
            return par2 == 1 ? this.chiseledSpecialQuartzIcon : this.specialQuartzTopIcon;
        }
        return par2 == 2 && (par1 == 1 || par1 == 0) ? this.pillarSpecialQuartzIcon : (par2 == 3 && (par1 == 5 || par1 == 4) ? this.pillarSpecialQuartzIcon : (par2 == 4 && (par1 == 2 || par1 == 3) ? this.pillarSpecialQuartzIcon : this.specialQuartzIcons[par2]));
    }

    public int func_149660_a(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
        if (par9 == 2) {
            switch (par5) {
                case 0: 
                case 1: {
                    par9 = 2;
                    break;
                }
                case 2: 
                case 3: {
                    par9 = 4;
                    break;
                }
                case 4: 
                case 5: {
                    par9 = 3;
                }
            }
        }
        return par9;
    }

    public int func_149692_a(int par1) {
        return par1 != 3 && par1 != 4 ? par1 : 2;
    }

    public ItemStack func_149644_j(int par1) {
        return par1 != 3 && par1 != 4 ? super.func_149644_j(par1) : new ItemStack((Block)this, 1, 2);
    }

    public int func_149645_b() {
        return 39;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item item, CreativeTabs tab, List par3List) {
        par3List.add(new ItemStack((Block)this, 1, 0));
        par3List.add(new ItemStack((Block)this, 1, 1));
        par3List.add(new ItemStack((Block)this, 1, 2));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.specialQuartzIcons = new IIcon[this.iconNames.length];
        for (int i = 0; i < this.specialQuartzIcons.length; ++i) {
            this.specialQuartzIcons[i] = this.iconNames[i] == null ? this.specialQuartzIcons[i - 1] : IconHelper.forName(par1IconRegister, this.iconNames[i]);
        }
        this.specialQuartzTopIcon = IconHelper.forName(par1IconRegister, "block" + this.type + "Quartz1");
        this.chiseledSpecialQuartzIcon = IconHelper.forName(par1IconRegister, "chiseled" + this.type + "Quartz1");
        this.pillarSpecialQuartzIcon = IconHelper.forName(par1IconRegister, "pillar" + this.type + "Quartz1");
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return this == ModFluffBlocks.elfQuartz ? LexiconData.elvenResources : LexiconData.decorativeBlocks;
    }
}

