/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.DrawBlockHighlightEvent
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
import thaumcraft.codechicken.lib.raytracer.RayTracer;
import thaumcraft.codechicken.lib.vec.BlockCoord;
import thaumcraft.codechicken.lib.vec.Vector3;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.relics.ItemResonator;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileCentrifuge;
import thaumcraft.common.tiles.TileEssentiaCrystalizer;
import thaumcraft.common.tiles.TileTube;
import thaumcraft.common.tiles.TileTubeBuffer;
import thaumcraft.common.tiles.TileTubeFilter;
import thaumcraft.common.tiles.TileTubeOneway;
import thaumcraft.common.tiles.TileTubeRestrict;
import thaumcraft.common.tiles.TileTubeValve;

public class BlockTube
extends BlockContainer {
    public IIcon[] icon = new IIcon[8];
    public IIcon iconValve;
    private RayTracer rayTracer = new RayTracer();

    public BlockTube() {
        super(Material.field_151573_f);
        this.func_149711_c(0.5f);
        this.func_149752_b(5.0f);
        this.func_149672_a(Block.field_149777_j);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:pipe_1");
        this.icon[1] = ir.func_94245_a("thaumcraft:pipe_2");
        this.icon[2] = ir.func_94245_a("thaumcraft:pipe_3");
        this.icon[3] = ir.func_94245_a("thaumcraft:pipe_filter");
        this.icon[4] = ir.func_94245_a("thaumcraft:pipe_filter_core");
        this.icon[5] = ir.func_94245_a("thaumcraft:pipe_buffer");
        this.icon[6] = ir.func_94245_a("thaumcraft:pipe_restrict");
        this.icon[7] = ir.func_94245_a("thaumcraft:pipe_oneway");
        this.iconValve = ir.func_94245_a("thaumcraft:pipe_valve");
    }

    public IIcon func_149691_a(int i, int md) {
        return md == 4 ? this.icon[5] : (md == 5 ? this.icon[6] : this.icon[0]);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
    }

    public int func_149645_b() {
        return ConfigBlocks.blockTubeRI;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
        boolean noDoodads;
        int metadata = world.func_72805_g(x, y, z);
        boolean bl = noDoodads = Minecraft.func_71410_x().field_71439_g == null || Minecraft.func_71410_x().field_71439_g.func_71045_bC() == null || !(Minecraft.func_71410_x().field_71439_g.func_71045_bC().func_77973_b() instanceof ItemWandCasting) && !(Minecraft.func_71410_x().field_71439_g.func_71045_bC().func_77973_b() instanceof ItemResonator);
        if ((metadata == 0 || metadata == 1 || metadata == 3 || metadata == 5 || metadata == 6) && noDoodads) {
            float minx = BlockRenderer.W6;
            float maxx = BlockRenderer.W10;
            float miny = BlockRenderer.W5;
            float maxy = BlockRenderer.W11;
            float minz = BlockRenderer.W5;
            float maxz = BlockRenderer.W11;
            ForgeDirection fd = null;
            block8: for (int side = 0; side < 6; ++side) {
                fd = ForgeDirection.getOrientation((int)side);
                TileEntity te = ThaumcraftApiHelper.getConnectableTile(world, x, y, z, fd);
                if (te == null) continue;
                switch (side) {
                    case 0: {
                        miny = 0.0f;
                        continue block8;
                    }
                    case 1: {
                        maxy = 1.0f;
                        continue block8;
                    }
                    case 2: {
                        minz = 0.0f;
                        continue block8;
                    }
                    case 3: {
                        maxz = 1.0f;
                        continue block8;
                    }
                    case 4: {
                        minx = 0.0f;
                        continue block8;
                    }
                    case 5: {
                        maxx = 1.0f;
                    }
                }
            }
            this.func_149676_a(minx, miny, minz, maxx, maxy, maxz);
        }
        if (metadata == 4 && noDoodads) {
            this.func_149676_a(0.25f, 0.25f, 0.25f, 0.75f, 0.75f, 0.75f);
        }
        if (metadata == 7) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        }
        return super.func_149633_g(world, x, y, z);
    }

    public void func_149719_a(IBlockAccess world, int i, int j, int k) {
        int metadata = world.func_72805_g(i, j, k);
        if (metadata == 2) {
            this.func_149676_a(0.25f, 0.0f, 0.25f, 0.75f, 1.0f, 0.75f);
        } else if (metadata == 7) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {
        int metadata = world.func_72805_g(i, j, k);
        if (metadata == 0 || metadata == 1 || metadata == 3 || metadata == 4 || metadata == 5 || metadata == 6) {
            float minx = BlockRenderer.W6;
            float maxx = BlockRenderer.W10;
            float miny = BlockRenderer.W6;
            float maxy = BlockRenderer.W10;
            float minz = BlockRenderer.W6;
            float maxz = BlockRenderer.W10;
            ForgeDirection fd = null;
            block8: for (int side = 0; side < 6; ++side) {
                fd = ForgeDirection.getOrientation((int)side);
                TileEntity te = ThaumcraftApiHelper.getConnectableTile(world, i, j, k, fd);
                if (te == null) continue;
                switch (side) {
                    case 0: {
                        miny = 0.0f;
                        continue block8;
                    }
                    case 1: {
                        maxy = 1.0f;
                        continue block8;
                    }
                    case 2: {
                        minz = 0.0f;
                        continue block8;
                    }
                    case 3: {
                        maxz = 1.0f;
                        continue block8;
                    }
                    case 4: {
                        minx = 0.0f;
                        continue block8;
                    }
                    case 5: {
                        maxx = 1.0f;
                    }
                }
            }
            this.func_149676_a(minx, miny, minz, maxx, maxy, maxz);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
        } else {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
        }
    }

    public int func_149692_a(int metadata) {
        return metadata;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 0) {
            return new TileTube();
        }
        if (metadata == 1) {
            return new TileTubeValve();
        }
        if (metadata == 2) {
            return new TileCentrifuge();
        }
        if (metadata == 3) {
            return new TileTubeFilter();
        }
        if (metadata == 4) {
            return new TileTubeBuffer();
        }
        if (metadata == 5) {
            return new TileTubeRestrict();
        }
        if (metadata == 6) {
            return new TileTubeOneway();
        }
        if (metadata == 7) {
            return new TileEssentiaCrystalizer();
        }
        return super.createTileEntity(world, metadata);
    }

    public TileEntity func_149915_a(World var1, int md) {
        return null;
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int rs) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileTubeBuffer) {
            float f = ((TileTubeBuffer)te).aspects.visSize();
            ((TileTubeBuffer)te).getClass();
            float r = f / 8.0f;
            return MathHelper.func_76141_d((float)(r * 14.0f)) + (((TileTubeBuffer)te).aspects.visSize() > 0 ? 1 : 0);
        }
        return 0;
    }

    public void func_149749_a(World world, int x, int y, int z, Block par5, int par6) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileTubeFilter && ((TileTubeFilter)te).aspectFilter != null && !world.field_72995_K) {
            world.func_72838_d((Entity)new EntityItem(world, (double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), new ItemStack(ConfigItems.itemResource, 1, 13)));
        }
        super.func_149749_a(world, x, y, z, par5, par6);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9) {
        TileEntity te;
        int metadata = world.func_72805_g(x, y, z);
        if (metadata == 1) {
            if (player.func_70694_bm() != null && (player.func_70694_bm().func_77973_b() instanceof ItemWandCasting || player.func_70694_bm().func_77973_b() instanceof ItemResonator || player.func_70694_bm().func_77973_b() == Item.func_150898_a((Block)this))) {
                return false;
            }
            te = world.func_147438_o(x, y, z);
            if (te instanceof TileTubeValve) {
                ((TileTubeValve)te).allowFlow = !((TileTubeValve)te).allowFlow;
                world.func_147471_g(x, y, z);
                if (!world.field_72995_K) {
                    world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:squeek", 0.7f, 0.9f + world.field_73012_v.nextFloat() * 0.2f);
                }
                return true;
            }
        }
        if (metadata == 3) {
            te = world.func_147438_o(x, y, z);
            if (te != null && te instanceof TileTubeFilter && player.func_70093_af() && ((TileTubeFilter)te).aspectFilter != null) {
                ((TileTubeFilter)te).aspectFilter = null;
                world.func_147471_g(x, y, z);
                if (world.field_72995_K) {
                    world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "thaumcraft:page", 1.0f, 1.0f, false);
                } else {
                    ForgeDirection fd = ForgeDirection.getOrientation((int)side);
                    world.func_72838_d((Entity)new EntityItem(world, (double)((float)x + 0.5f + (float)fd.offsetX / 3.0f), (double)((float)y + 0.5f), (double)((float)z + 0.5f + (float)fd.offsetZ / 3.0f), new ItemStack(ConfigItems.itemResource, 1, 13)));
                }
                return true;
            }
            if (te != null && te instanceof TileTubeFilter && player.func_70694_bm() != null && ((TileTubeFilter)te).aspectFilter == null && player.func_70694_bm().func_77973_b() == ConfigItems.itemResource && player.func_70694_bm().func_77960_j() == 13) {
                if (((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) != null) {
                    ((TileTubeFilter)te).aspectFilter = ((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()).getAspects()[0];
                    --player.func_70694_bm().field_77994_a;
                    world.func_147471_g(x, y, z);
                    if (world.field_72995_K) {
                        world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "thaumcraft:page", 1.0f, 1.0f, false);
                    }
                }
                return true;
            }
        }
        return super.func_149727_a(world, x, y, z, player, side, par7, par8, par9);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onBlockHighlight(DrawBlockHighlightEvent event) {
        if (event.target.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && event.player.field_70170_p.func_147439_a(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d) == this && event.player.field_70170_p.func_72805_g(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d) != 7 && event.player.func_71045_bC() != null && (event.player.func_71045_bC().func_77973_b() instanceof ItemWandCasting || event.player.func_71045_bC().func_77973_b() instanceof ItemResonator)) {
            RayTracer.retraceBlock(event.player.field_70170_p, event.player, event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d);
        }
    }

    public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 start, Vec3 end) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile == null || !(tile instanceof TileTube) && !(tile instanceof TileTubeBuffer)) {
            return super.func_149731_a(world, x, y, z, start, end);
        }
        LinkedList<IndexedCuboid6> cuboids = new LinkedList<IndexedCuboid6>();
        if (tile instanceof TileTube) {
            ((TileTube)tile).addTraceableCuboids(cuboids);
        } else if (tile instanceof TileTubeBuffer) {
            ((TileTubeBuffer)tile).addTraceableCuboids(cuboids);
        }
        return this.rayTracer.rayTraceCuboids(new Vector3(start), new Vector3(end), cuboids, new BlockCoord(x, y, z), (Block)this);
    }
}

