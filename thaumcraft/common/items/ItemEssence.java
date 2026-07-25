/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileAlembic;
import thaumcraft.common.tiles.TileJarFillable;

public class ItemEssence
extends Item
implements IEssentiaContainerItem {
    public IIcon icon;
    public IIcon iconOverlay;

    public ItemEssence() {
        this.func_77625_d(64);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:phial");
        this.iconOverlay = ir.func_94245_a("thaumcraft:essence");
    }

    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderPasses(int metadata) {
        return metadata == 0 ? 1 : 2;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77618_c(int par1, int par2) {
        return par1 == 0 || par2 == 0 ? this.icon : this.iconOverlay;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack stack, int par2) {
        if (stack.func_77960_j() == 0 || par2 == 0) {
            return 0xFFFFFF;
        }
        if (stack.func_77960_j() == 1 && this.getAspects(stack) != null) {
            return this.getAspects(stack).getAspects()[0].getColor();
        }
        return 0xFFFFFF;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 0));
        for (Aspect tag : Aspect.aspects.values()) {
            ItemStack i = new ItemStack((Item)this, 1, 1);
            this.setAspects(i, new AspectList().add(tag, 8));
            par3List.add(i);
        }
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        AspectList aspects = this.getAspects(stack);
        if (aspects != null && aspects.size() > 0) {
            for (Aspect tag : aspects.getAspectsSorted()) {
                if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
                    list.add(tag.getName() + " x " + aspects.getAmount(tag));
                    continue;
                }
                list.add(StatCollector.func_74838_a((String)"tc.aspect.unknown"));
            }
        }
        super.func_77624_a(stack, player, list, par4);
    }

    public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float f1, float f2, float f3) {
        AspectList al;
        TileThaumcraft tile;
        Block bi = world.func_147439_a(x, y, z);
        int md = world.func_72805_g(x, y, z);
        if (itemstack.func_77960_j() == 0 && bi == ConfigBlocks.blockMetalDevice && md == 1) {
            tile = (TileAlembic)world.func_147438_o(x, y, z);
            if (((TileAlembic)tile).amount >= 8) {
                if (world.field_72995_K) {
                    player.func_71038_i();
                    return false;
                }
                ItemStack phial = new ItemStack((Item)this, 1, 1);
                this.setAspects(phial, new AspectList().add(((TileAlembic)tile).aspect, 8));
                if (((TileAlembic)tile).takeFromContainer(((TileAlembic)tile).aspect, 8)) {
                    --itemstack.field_77994_a;
                    if (!player.field_71071_by.func_70441_a(phial)) {
                        world.func_72838_d((Entity)new EntityItem(world, (double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), phial));
                    }
                    world.func_72956_a((Entity)player, "game.neutral.swim", 0.25f, 1.0f);
                    player.field_71069_bz.func_75142_b();
                    return true;
                }
            }
        }
        if (itemstack.func_77960_j() == 0 && bi == ConfigBlocks.blockJar && (md == 0 || md == 3)) {
            tile = (TileJarFillable)world.func_147438_o(x, y, z);
            if (((TileJarFillable)tile).amount >= 8) {
                if (world.field_72995_K) {
                    player.func_71038_i();
                    return false;
                }
                Aspect asp = Aspect.getAspect(((TileJarFillable)tile).aspect.getTag());
                if (((TileJarFillable)tile).takeFromContainer(asp, 8)) {
                    --itemstack.field_77994_a;
                    ItemStack phial = new ItemStack((Item)this, 1, 1);
                    this.setAspects(phial, new AspectList().add(asp, 8));
                    if (!player.field_71071_by.func_70441_a(phial)) {
                        world.func_72838_d((Entity)new EntityItem(world, (double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), phial));
                    }
                    world.func_72956_a((Entity)player, "game.neutral.swim", 0.25f, 1.0f);
                    player.field_71069_bz.func_75142_b();
                    return true;
                }
            }
        }
        if ((al = this.getAspects(itemstack)) != null && al.size() == 1) {
            Aspect aspect = al.getAspects()[0];
            if (itemstack.func_77960_j() != 0 && bi == ConfigBlocks.blockJar && (md == 0 || md == 3)) {
                TileJarFillable tile2 = (TileJarFillable)world.func_147438_o(x, y, z);
                if (tile2.amount <= tile2.maxAmount - 8 && tile2.doesContainerAccept(aspect)) {
                    if (world.field_72995_K) {
                        player.func_71038_i();
                        return false;
                    }
                    if (tile2.addToContainer(aspect, 8) == 0) {
                        world.func_147471_g(x, y, z);
                        tile2.func_70296_d();
                        --itemstack.field_77994_a;
                        if (!player.field_71071_by.func_70441_a(new ItemStack((Item)this, 1, 0))) {
                            world.func_72838_d((Entity)new EntityItem(world, (double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), new ItemStack((Item)this, 1, 0)));
                        }
                        world.func_72956_a((Entity)player, "game.neutral.swim", 0.25f, 1.0f);
                        player.field_71069_bz.func_75142_b();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public AspectList getAspects(ItemStack itemstack) {
        if (itemstack.func_77942_o()) {
            AspectList aspects = new AspectList();
            aspects.readFromNBT(itemstack.func_77978_p());
            return aspects.size() > 0 ? aspects : null;
        }
        return null;
    }

    @Override
    public void setAspects(ItemStack itemstack, AspectList aspects) {
        if (!itemstack.func_77942_o()) {
            itemstack.func_77982_d(new NBTTagCompound());
        }
        aspects.writeToNBT(itemstack.func_77978_p());
    }
}

