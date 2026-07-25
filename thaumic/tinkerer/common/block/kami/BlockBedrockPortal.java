/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.Facing
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package thaumic.tinkerer.common.block.kami;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.block.BlockMod;
import thaumic.tinkerer.common.block.tile.kami.TileBedrockPortal;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.core.handler.ModCreativeTab;
import thaumic.tinkerer.common.dim.TeleporterBedrock;
import thaumic.tinkerer.common.dim.WorldProviderBedrock;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class BlockBedrockPortal
extends BlockMod {
    @SideOnly(value=Side.CLIENT)
    private IIcon icon;

    public BlockBedrockPortal() {
        super(Material.field_151567_E);
        this.func_149672_a(Block.field_149769_e);
        this.func_149752_b(6000000.0f);
        this.func_149649_H();
        this.func_149647_a(ModCreativeTab.INSTANCE);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        world.func_147449_b(x, y, z, Blocks.field_150357_h);
        return super.func_149727_a(world, x, y, z, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.icon = IconHelper.forName(iconRegister, "portal");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int meta) {
        return this.icon;
    }

    public float func_149712_f(World par1World, int par2, int par3, int par4) {
        return -1.0f;
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public boolean canHarvestBlock(EntityPlayer player, int meta) {
        return false;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileBedrockPortal();
    }

    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        boolean bl;
        boolean flag;
        if (par5 != 1 && par5 != 0 && !super.func_149646_a(par1IBlockAccess, par2, par3, par4, par5)) {
            return false;
        }
        int i1 = par2 + Facing.field_71586_b[Facing.field_71588_a[par5]];
        int j1 = par3 + Facing.field_71587_c[Facing.field_71588_a[par5]];
        int k1 = par4 + Facing.field_71585_d[Facing.field_71588_a[par5]];
        boolean bl2 = flag = (par1IBlockAccess.func_72805_g(i1, j1, k1) & 8) != 0;
        if (flag) {
            if (par5 == 0 || par5 != 1 || super.func_149646_a(par1IBlockAccess, par2, par3, par4, par5)) {
                // empty if block
            }
            bl = true;
        } else {
            if (par5 == 1 || par5 != 0 || super.func_149646_a(par1IBlockAccess, par2, par3, par4, par5)) {
                // empty if block
            }
            bl = true;
        }
        return bl;
    }

    public void func_149670_a(World par1World, int par2, int par3, int par4, Entity entity) {
        super.func_149670_a(par1World, par2, par3, par4, entity);
        if (entity.field_70170_p.field_73011_w.func_76569_d()) {
            if (entity instanceof EntityPlayer && !par1World.field_72995_K) {
                FMLCommonHandler.instance().getMinecraftServerInstance().func_71203_ab().transferPlayerToDimension((EntityPlayerMP)entity, ConfigHandler.bedrockDimensionID, (Teleporter)new TeleporterBedrock((WorldServer)par1World));
                if (entity.field_70170_p.func_147439_a(par2, 250, par4) == Blocks.field_150357_h) {
                    entity.field_70170_p.func_147449_b(par2, 250, par4, Blocks.field_150350_a);
                }
                if (entity.field_70170_p.func_147439_a(par2, 251, par4) == Blocks.field_150357_h) {
                    entity.field_70170_p.func_147449_b(par2, 251, par4, Blocks.field_150350_a);
                }
                if (entity.field_70170_p.func_147439_a(par2, 252, par4) == Blocks.field_150357_h) {
                    entity.field_70170_p.func_147449_b(par2, 252, par4, Blocks.field_150350_a);
                }
                if (entity.field_70170_p.func_147439_a(par2, 253, par4) == Blocks.field_150357_h) {
                    entity.field_70170_p.func_147449_b(par2, 253, par4, Blocks.field_150350_a);
                }
                if (entity.field_70170_p.func_147439_a(par2, 254, par4) == Blocks.field_150357_h) {
                    entity.field_70170_p.func_147449_b(par2, 254, par4, (Block)this);
                }
                ((EntityPlayerMP)entity).field_71135_a.func_147364_a((double)par2 + 0.5, 251.0, (double)par4 + 0.5, 0.0f, 0.0f);
            }
        } else if (entity.field_70170_p.field_73011_w instanceof WorldProviderBedrock && entity instanceof EntityPlayer && !par1World.field_72995_K) {
            FMLCommonHandler.instance().getMinecraftServerInstance().func_71203_ab().transferPlayerToDimension((EntityPlayerMP)entity, 0, (Teleporter)new TeleporterBedrock((WorldServer)par1World));
            Random rand = new Random();
            int x = (int)entity.field_70165_t + rand.nextInt(100);
            int z = (int)entity.field_70161_v + rand.nextInt(100);
            x -= 50;
            z -= 50;
            int y = 120;
            while (entity.field_70170_p.func_147439_a(x, y, z) == Blocks.field_150350_a || entity.field_70170_p.func_147439_a(x, y, z).isAir((IBlockAccess)par1World, x, y, z)) {
                --y;
            }
            ((EntityPlayerMP)entity).field_71135_a.func_147364_a((double)x + 0.5, (double)(y + 3), (double)z + 0.5, 0.0f, 0.0f);
        }
    }

    public void travelToDimension(int par1, Entity e) {
        if (!e.field_70170_p.field_72995_K && !e.field_70128_L) {
            e.field_70170_p.field_72984_F.func_76320_a("changeDimension");
            MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
            int j = e.field_71093_bK;
            WorldServer worldserver = minecraftserver.func_71218_a(j);
            WorldServer worldserver1 = minecraftserver.func_71218_a(par1);
            e.field_71093_bK = par1;
            if (j == 1 && par1 == 1) {
                worldserver1 = minecraftserver.func_71218_a(0);
                e.field_71093_bK = 0;
            }
            e.field_70170_p.func_72900_e(e);
            e.field_70128_L = false;
            e.field_70170_p.field_72984_F.func_76320_a("reposition");
            minecraftserver.func_71203_ab().transferEntityToWorld(e, j, worldserver, worldserver1, (Teleporter)new TeleporterBedrock(worldserver));
            e.field_70170_p.field_72984_F.func_76318_c("reloading");
            Entity entity = EntityList.func_75620_a((String)EntityList.func_75621_b((Entity)e), (World)worldserver1);
            if (entity != null) {
                entity.func_82141_a(e, true);
                if (j == 1 && par1 == 1) {
                    ChunkCoordinates chunkcoordinates = worldserver1.func_72861_E();
                    chunkcoordinates.field_71572_b = e.field_70170_p.func_72825_h(chunkcoordinates.field_71574_a, chunkcoordinates.field_71573_c);
                    entity.func_70012_b((double)chunkcoordinates.field_71574_a, (double)chunkcoordinates.field_71572_b, (double)chunkcoordinates.field_71573_c, entity.field_70177_z, entity.field_70125_A);
                }
                worldserver1.func_72838_d(entity);
            }
            e.field_70128_L = true;
            e.field_70170_p.field_72984_F.func_76319_b();
            worldserver.func_82742_i();
            worldserver1.func_82742_i();
            e.field_70170_p.field_72984_F.func_76319_b();
        }
    }

    @Override
    public String getBlockName() {
        return "bedrockPortal";
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return null;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return TileBedrockPortal.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return null;
    }
}

