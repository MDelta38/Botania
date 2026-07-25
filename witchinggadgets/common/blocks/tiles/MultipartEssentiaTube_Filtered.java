/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.lib.vec.Vector3
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IEssentiaContainerItem
 *  thaumcraft.common.blocks.BlockTube
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.tiles.TileTubeFilter
 */
package witchinggadgets.common.blocks.tiles;

import codechicken.lib.vec.Vector3;
import java.util.ArrayList;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.blocks.BlockTube;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.tiles.TileTubeFilter;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.MultipartEssentiaTube;

public class MultipartEssentiaTube_Filtered
extends MultipartEssentiaTube {
    public Aspect aspectFilter = null;

    public MultipartEssentiaTube_Filtered(int meta) {
        super(meta);
    }

    @Override
    public void invalidateConvertedTile() {
        super.invalidateConvertedTile();
        TileEntity te = this.world().func_147438_o(this.x(), this.y(), this.z());
        if (te instanceof TileTubeFilter) {
            this.aspectFilter = ((TileTubeFilter)te).aspectFilter;
            ((TileTubeFilter)te).aspectFilter = null;
        }
    }

    @Override
    public boolean renderStatic(Vector3 pos, int pass) {
        super.renderStatic(pos, pass);
        Tessellator tes = Tessellator.field_78398_a;
        BlockTube b = (BlockTube)ConfigBlocks.blockTube;
        if (this.aspectFilter != null) {
            tes.func_78378_d(this.aspectFilter.getColor());
        }
        ClientUtilities.addBoxToBlockrender(0.34375, 0.34375, 0.34375, 0.65625, 0.65625, 0.65625, b.icon[4], this.x(), this.y(), this.z());
        tes.func_78378_d(0xFFFFFF);
        ClientUtilities.addBoxToBlockrender(0.34375, 0.34375, 0.34375, 0.65625, 0.65625, 0.65625, b.icon[3], this.x(), this.y(), this.z());
        return true;
    }

    @Override
    void calculateSuction(Aspect filter, boolean restrict, boolean dir) {
        super.calculateSuction(this.aspectFilter, restrict, dir);
    }

    public AspectList getAspects() {
        if (this.aspectFilter != null) {
            return new AspectList().add(this.aspectFilter, -1);
        }
        return null;
    }

    public void setAspects(AspectList aspects) {
    }

    public boolean doesContainerAccept(Aspect tag) {
        return false;
    }

    public int addToContainer(Aspect tag, int amount) {
        return 0;
    }

    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
    }

    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return false;
    }

    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    public int containerContains(Aspect tag) {
        return 0;
    }

    @Override
    public void save(NBTTagCompound tag) {
        super.save(tag);
        if (this.aspectFilter != null) {
            tag.func_74778_a("AspectFilter", this.aspectFilter.getTag());
        }
    }

    @Override
    public void load(NBTTagCompound tag) {
        super.load(tag);
        this.aspectFilter = Aspect.getAspect((String)tag.func_74779_i("AspectFilter"));
    }

    @Override
    public String getType() {
        return "witchingGadgets:essentia_tube_filtered";
    }

    @Override
    public Iterable<ItemStack> getDrops() {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        for (ItemStack is : super.getDrops()) {
            drops.add(is);
        }
        if (this.aspectFilter != null) {
            drops.add(new ItemStack(ConfigItems.itemResource, 1, 13));
        }
        return drops;
    }

    @Override
    public boolean activate(EntityPlayer player, MovingObjectPosition hit, ItemStack stack) {
        if (this.aspectFilter != null) {
            if (player.func_70093_af()) {
                this.aspectFilter = null;
                if (this.world().field_72995_K) {
                    this.world().func_72980_b((double)this.x() + 0.5, (double)this.y() + 0.5, (double)this.z() + 0.5, "thaumcraft:page", 1.0f, 1.0f, false);
                } else {
                    ForgeDirection fd = ForgeDirection.getOrientation((int)hit.field_72310_e);
                    this.world().func_72838_d((Entity)new EntityItem(this.world(), (double)this.x() + 0.5 + (double)((float)fd.offsetX / 3.0f), (double)this.y() + 0.5, (double)this.z() + 0.5 + (double)((float)fd.offsetZ / 3.0f), new ItemStack(ConfigItems.itemResource, 1, 13)));
                    this.sendDescUpdate();
                }
                return true;
            }
        } else if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b().equals(ConfigItems.itemResource) && player.func_70694_bm().func_77960_j() == 13 && ((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) != null) {
            this.aspectFilter = ((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()).getAspects()[0];
            --player.func_70694_bm().field_77994_a;
            if (this.world().field_72995_K) {
                this.world().func_72980_b((double)this.x() + 0.5, (double)this.y() + 0.5, (double)this.z() + 0.5, "thaumcraft:page", 1.0f, 1.0f, false);
            } else {
                this.sendDescUpdate();
            }
        }
        return false;
    }
}

