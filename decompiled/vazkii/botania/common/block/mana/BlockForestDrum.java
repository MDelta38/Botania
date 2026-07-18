/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package vazkii.botania.common.block.mana;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IManaTrigger;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.item.ItemGrassHorn;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockForestDrum
extends BlockMod
implements IManaTrigger,
ILexiconable {
    IIcon iconBases;
    IIcon iconFaces;
    IIcon iconBasesA;
    IIcon iconFacesA;
    IIcon iconBasesB;
    IIcon iconFacesB;

    public BlockForestDrum() {
        super(Material.field_151575_d);
        float f = 0.0625f;
        this.func_149676_a(f * 3.0f, 0.0f, f * 3.0f, 1.0f - f * 3.0f, 1.0f - f * 2.0f, 1.0f - f * 3.0f);
        this.func_149711_c(2.0f);
        this.func_149672_a(field_149766_f);
        this.func_149663_c("forestDrum");
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

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149692_a(int meta) {
        return meta;
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.iconBases = IconHelper.forBlock(par1IconRegister, (Block)this, 0);
        this.iconFaces = IconHelper.forBlock(par1IconRegister, (Block)this, 1);
        this.iconBasesA = IconHelper.forBlock(par1IconRegister, (Block)this, 2);
        this.iconFacesA = IconHelper.forBlock(par1IconRegister, (Block)this, 3);
        this.iconBasesB = IconHelper.forBlock(par1IconRegister, (Block)this, 4);
        this.iconFacesB = IconHelper.forBlock(par1IconRegister, (Block)this, 5);
    }

    public void func_149666_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 3; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public IIcon func_149691_a(int side, int meta) {
        boolean tree;
        boolean animal = meta == 1;
        boolean bl = tree = meta == 2;
        return side < 2 ? (animal ? this.iconBasesA : (tree ? this.iconBasesB : this.iconBases)) : (animal ? this.iconFacesA : (tree ? this.iconFacesB : this.iconFaces));
    }

    @Override
    public void onBurstCollision(IManaBurst burst, World world, int x, int y, int z) {
        if (burst.isFake()) {
            return;
        }
        if (world.func_72805_g(x, y, z) == 0) {
            ItemGrassHorn.breakGrass(world, null, 0, x, y, z);
        } else if (world.func_72805_g(x, y, z) == 2) {
            ItemGrassHorn.breakGrass(world, null, 1, x, y, z);
        } else if (!world.field_72995_K) {
            EntityItem ent;
            int range = 10;
            List entities = world.func_72872_a(EntityLiving.class, AxisAlignedBB.func_72330_a((double)(x - range), (double)(y - range), (double)(z - range), (double)(x + range + 1), (double)(y + range + 1), (double)(z + range + 1)));
            ArrayList<EntityLiving> shearables = new ArrayList<EntityLiving>();
            ItemStack stack = new ItemStack((Block)this, 1, 1);
            for (EntityLiving entity : entities) {
                if (entity instanceof IShearable && ((IShearable)entity).isShearable(stack, (IBlockAccess)world, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v)) {
                    shearables.add(entity);
                    continue;
                }
                if (!(entity instanceof EntityCow)) continue;
                List items = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)entity.field_70165_t, (double)entity.field_70163_u, (double)entity.field_70161_v, (double)(entity.field_70165_t + (double)entity.field_70130_N), (double)(entity.field_70163_u + (double)entity.field_70131_O), (double)(entity.field_70161_v + (double)entity.field_70130_N)));
                for (EntityItem item : items) {
                    ItemStack itemstack = item.func_92059_d();
                    if (itemstack == null || itemstack.func_77973_b() != Items.field_151133_ar || world.field_72995_K) continue;
                    while (itemstack.field_77994_a > 0) {
                        ent = entity.func_70099_a(new ItemStack(Items.field_151117_aB), 1.0f);
                        ent.field_70181_x += (double)(world.field_73012_v.nextFloat() * 0.05f);
                        ent.field_70159_w += (double)((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1f);
                        ent.field_70179_y += (double)((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1f);
                        --itemstack.field_77994_a;
                    }
                    item.func_70106_y();
                }
            }
            Collections.shuffle(shearables);
            int sheared = 0;
            for (EntityLiving entity : shearables) {
                if (sheared > 4) break;
                ArrayList stacks = ((IShearable)entity).onSheared(stack, (IBlockAccess)world, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v, 0);
                if (stacks != null) {
                    for (ItemStack wool : stacks) {
                        ent = entity.func_70099_a(wool, 1.0f);
                        ent.field_70181_x += (double)(world.field_73012_v.nextFloat() * 0.05f);
                        ent.field_70159_w += (double)((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1f);
                        ent.field_70179_y += (double)((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1f);
                    }
                }
                ++sheared;
            }
        }
        if (!world.field_72995_K) {
            for (int i = 0; i < 10; ++i) {
                world.func_72908_a((double)x, (double)y, (double)z, "note.bd", 1.0f, 1.0f);
            }
        } else {
            world.func_72869_a("note", (double)x + 0.5, (double)y + 1.2, (double)z + 0.5, 0.041666666666666664, 0.0, 0.0);
        }
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        int meta = world.func_72805_g(x, y, z);
        switch (meta) {
            case 1: {
                return LexiconData.gatherDrum;
            }
            case 2: {
                return LexiconData.canopyDrum;
            }
        }
        return LexiconData.forestDrum;
    }
}

