/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 *  thaumcraft.api.aspects.Aspect
 */
package drunkmafia.thaumicinfusion.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import drunkmafia.thaumicinfusion.common.ThaumicInfusion;
import drunkmafia.thaumicinfusion.common.block.TIBlocks;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.common.world.data.EssentiaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;

public class EssentiaBlock
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon brick;
    @SideOnly(value=Side.CLIENT)
    private IIcon squarebrick;

    public EssentiaBlock() {
        super(Material.field_151576_e);
        this.func_149647_a(ThaumicInfusion.instance.tab);
        this.func_149663_c("local_essentia");
        this.func_149711_c(1.5f);
        this.func_149715_a(1.0f);
        this.func_149752_b(10.0f);
    }

    public static ItemStack getEssentiaBlock(Aspect aspect, int meta) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74778_a("aspectTag", aspect.getTag());
        ItemStack stack = new ItemStack(TIBlocks.essentiaBlock);
        stack.func_77964_b(meta);
        stack.func_77982_d(tag);
        stack.func_151001_c(aspect.getName() + (meta != 0 ? (meta == 1 ? ThaumicInfusion.translate("key.essentiaBlock.brick", new Object[0]) : ThaumicInfusion.translate("key.essentiaBlock.chiseled", new Object[0])) : ""));
        return stack;
    }

    public void func_149666_a(Item item, CreativeTabs tab, List list) {
        Object[] objs;
        for (Object obj : objs = Aspect.aspects.entrySet().toArray()) {
            for (int i = 0; i <= 2; ++i) {
                NBTTagCompound tag = new NBTTagCompound();
                Aspect aspect = (Aspect)((Map.Entry)obj).getValue();
                tag.func_74778_a("aspectTag", aspect.getTag());
                ItemStack stack = new ItemStack((Block)this);
                stack.func_77964_b(i);
                stack.func_77982_d(tag);
                stack.func_151001_c(aspect.getName() + (i != 0 ? (i == 1 ? " Brick" : " chiseled") : ""));
                list.add(stack);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister icon) {
        this.field_149761_L = icon.func_94245_a("thaumicinfusion:essentiablock");
        this.brick = icon.func_94245_a("thaumicinfusion:bricks_essentiablock");
        this.squarebrick = icon.func_94245_a("thaumicinfusion:squarebrick_essentiablock");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        switch (meta) {
            case 1: {
                return this.brick;
            }
            case 2: {
                return this.squarebrick;
            }
        }
        return this.field_149761_L;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        EssentiaData data;
        TIWorldData worldData = TIWorldData.getWorldData(world);
        if (worldData != null && (data = worldData.getBlock(EssentiaData.class, new WorldCoordinates(x, y, z, player.field_71093_bK))) != null) {
            int meta = world.func_72805_g(x, y, z);
            ItemStack stack = new ItemStack((Block)this, 1, meta);
            NBTTagCompound tagCompound = new NBTTagCompound();
            Aspect aspect = data.getAspect();
            tagCompound.func_74778_a("aspectTag", aspect.getTag());
            stack.func_77982_d(tagCompound);
            stack.func_151001_c(aspect.getName() + (meta != 0 ? (meta == 1 ? " Brick" : " chiseled") : ""));
            return stack;
        }
        return null;
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        TIWorldData worldData = TIWorldData.getWorldData(world);
        WorldCoordinates coord = new WorldCoordinates(x, y, z, entity.field_71093_bK);
        world.func_72921_c(coord.x, coord.y, coord.z, stack.func_77960_j(), 3);
        NBTTagCompound tagCompound = stack.func_77978_p();
        if (tagCompound != null && worldData != null) {
            worldData.addBlock(new EssentiaData(coord, Aspect.getAspect((String)tagCompound.func_74779_i("aspectTag"))));
        }
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        TIWorldData worldData = TIWorldData.getWorldData(world);
        if (worldData != null) {
            EssentiaData data = worldData.getBlock(EssentiaData.class, new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g));
            int meta = world.func_72805_g(x, y, z);
            ItemStack stack = new ItemStack(TIBlocks.essentiaBlock, 1, meta);
            NBTTagCompound tagCompound = new NBTTagCompound();
            Aspect aspect = data.getAspect();
            tagCompound.func_74778_a("aspectTag", aspect.getTag());
            stack.func_77982_d(tagCompound);
            stack.func_151001_c(aspect.getName() + (meta != 0 ? (meta == 1 ? " Brick" : " chiseled") : ""));
            ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
            stacks.add(stack);
            return stacks;
        }
        return new ArrayList<ItemStack>();
    }

    public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int id) {
    }

    public void func_149725_f(World world, int x, int y, int z, int meta) {
        TIWorldData worldData;
        if (!world.field_72995_K && (worldData = TIWorldData.getWorldData(world)) != null) {
            worldData.removeData(EssentiaData.class, new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g), true);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149720_d(IBlockAccess access, int x, int y, int z) {
        TIWorldData worldData = TIWorldData.getWorldData(TIWorldData.getWorld(access));
        if (worldData == null) {
            return this.func_149635_D();
        }
        EssentiaData data = worldData.getBlock(EssentiaData.class, new WorldCoordinates(x, y, z, Minecraft.func_71410_x().field_71439_g.field_71093_bK));
        if (data == null || data.getAspect() == null) {
            return this.func_149635_D();
        }
        return data.getAspect().getColor();
    }
}

