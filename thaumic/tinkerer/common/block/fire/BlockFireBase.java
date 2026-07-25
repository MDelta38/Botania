/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.material.MapColor
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumic.tinkerer.common.block.fire;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.fire.BlockFireAir;
import thaumic.tinkerer.common.block.fire.BlockFireChaos;
import thaumic.tinkerer.common.block.fire.BlockFireEarth;
import thaumic.tinkerer.common.block.fire.BlockFireIgnis;
import thaumic.tinkerer.common.block.fire.BlockFireOrder;
import thaumic.tinkerer.common.block.fire.BlockFireWater;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.core.helper.BlockTuple;
import thaumic.tinkerer.common.item.ItemBlockFire;
import thaumic.tinkerer.common.registry.ITTinkererBlock;

public abstract class BlockFireBase
extends BlockFire
implements ITTinkererBlock {
    private IIcon[] icons;
    private IIcon itemIcon;

    public abstract HashMap<BlockTuple, BlockTuple> getBlockTransformation();

    public boolean isTransmutationTarget(Block block, World w, int x, int y, int z) {
        return this.isTransmutationTarget(new BlockTuple(block), w, x, y, z);
    }

    public boolean isTransmutationResult(Block block, World w, int x, int y, int z) {
        return this.isTransmutationResult(new BlockTuple(block), w, x, y, z);
    }

    public boolean isTransmutationTarget(BlockTuple block, World w, int x, int y, int z) {
        return this.getBlockTransformation(w, x, y, z).keySet().contains(block);
    }

    public boolean isTransmutationResult(BlockTuple block, World w, int x, int y, int z) {
        return this.getBlockTransformation(w, x, y, z).values().contains(block);
    }

    public abstract HashMap<BlockTuple, BlockTuple> getBlockTransformation(World var1, int var2, int var3, int var4);

    public boolean isNeighborTarget(World w, int x, int y, int z) {
        for (ForgeDirection f : ForgeDirection.VALID_DIRECTIONS) {
            if (!w.func_72899_e(x + f.offsetX, y + f.offsetY, z + f.offsetZ) || !this.isTransmutationTarget(w.func_147439_a(x + f.offsetX, y + f.offsetY, z + f.offsetZ), w, x + f.offsetX, y + f.offsetY, z + f.offsetZ)) continue;
            return true;
        }
        return false;
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    public int getDecayChance(World w, int x, int y, int z) {
        return 1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return 3;
    }

    public int func_149745_a(Random p_149745_1_) {
        return 0;
    }

    public int func_149738_a(World p_149738_1_) {
        return 200;
    }

    public void setBlockWithTransmutationTarget(World world, int x, int y, int z, int meta, Block block) {
        Random random = new Random();
        if (this.isTransmutationTarget(world.func_147439_a(x, z, y), world, x, y, z) && random.nextInt(this.getDecayChance(world, x, y, z)) == 0) {
            world.func_147465_d(x, z, y, this.getBlockTransformation((World)world, (int)x, (int)y, (int)z).get((Object)new BlockTuple((Block)world.func_147439_a((int)x, (int)z, (int)y))).block, this.getBlockTransformation((World)world, (int)x, (int)y, (int)z).get((Object)new BlockTuple((Block)world.func_147439_a((int)x, (int)z, (int)y))).meta, 3);
        } else {
            world.func_147465_d(x, z, y, block, meta, 3);
        }
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        if (world.func_82736_K().func_82766_b("doFireTick") && ConfigHandler.enableFire) {
            if (world.func_72896_J() && (world.func_72951_B(x, y, z) || world.func_72951_B(x - 1, y, z) || world.func_72951_B(x + 1, y, z) || world.func_72951_B(x, y, z - 1) || world.func_72951_B(x, y, z + 1))) {
                world.func_147468_f(x, y, z);
            } else {
                if (!this.isNeighborTarget(world, x, y, z)) {
                    world.func_147468_f(x, y, z);
                }
                if (rand.nextInt(20) == 0 && this.isNeighborTarget(world, x, y, z)) {
                    for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                        if (!this.isTransmutationTarget(world.func_147439_a(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ), world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)) continue;
                        Random random = new Random();
                        if (random.nextInt(this.getDecayChance(world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)) == 0) {
                            world.func_147465_d(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, this.getBlockTransformation((World)world, (int)(x + dir.offsetX), (int)(y + dir.offsetY), (int)(z + dir.offsetZ)).get((Object)new BlockTuple((Block)world.func_147439_a((int)(x + dir.offsetX), (int)(y + dir.offsetY), (int)(z + dir.offsetZ)))).block, this.getBlockTransformation((World)world, (int)(x + dir.offsetX), (int)(y + dir.offsetY), (int)(z + dir.offsetZ)).get((Object)new BlockTuple((Block)world.func_147439_a((int)(x + dir.offsetX), (int)(y + dir.offsetY), (int)(z + dir.offsetZ)))).meta, 3);
                            continue;
                        }
                        world.func_147468_f(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
                    }
                }
            }
            int l = world.func_72805_g(x, y, z);
            if (l < 15) {
                world.func_72921_c(x, y, z, l + rand.nextInt(3) / 2, 4);
            }
            world.func_147464_a(x, y, z, (Block)this, this.func_149738_a(world) + rand.nextInt(3));
            boolean flag1 = world.func_72958_C(x, y, z);
            int b0 = 0;
            if (flag1) {
                b0 = -50;
            }
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                this.tryCatchFire(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, 300 + b0, rand, l, direction);
            }
            for (int i1 = x - 1; i1 <= x + 1; ++i1) {
                for (int j1 = z - 1; j1 <= z + 1; ++j1) {
                    for (int k1 = y - 1; k1 <= y + 2; ++k1) {
                        if (i1 == x && k1 == y && j1 == z) continue;
                        int l1 = 100;
                        int i2 = this.getChanceOfNeighborsEncouragingFire(world, i1, k1, j1);
                        if (i2 <= 0) continue;
                        int j2 = (i2 + 70) / (l + 30);
                        j2 += 70;
                        if (flag1) {
                            j2 /= 2;
                        }
                        if (j2 <= 0 || rand.nextInt(l1) > j2 || world.func_72896_J() && world.func_72951_B(i1, k1, j1) || world.func_72951_B(i1 - 1, k1, z) || world.func_72951_B(i1 + 1, k1, j1) || world.func_72951_B(i1, k1, j1 - 1) || world.func_72951_B(i1, k1, j1 + 1)) continue;
                        int k2 = l + rand.nextInt(5) / 4;
                        if (k2 > 15) {
                            k2 = 15;
                        }
                        this.setBlockWithTransmutationTarget(world, i1, j1, k1, k2, (Block)this);
                    }
                }
            }
        }
    }

    public boolean func_149698_L() {
        return false;
    }

    public int getBlockFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        if (this.isTransmutationTarget(world.func_147439_a(x, y, z), (World)world, x, y, z)) {
            return 100;
        }
        if (this.isTransmutationResult(world.func_147439_a(x, y, z), (World)world, x, y, z)) {
            return 0;
        }
        return world.func_147439_a(x, y, z).getFlammability(world, x, y, z, face);
    }

    private void tryCatchFire(World world, int x, int y, int z, int strength, Random rand, int meta, ForgeDirection face) {
        int j1 = this.getBlockFlammability((IBlockAccess)world, x, y, z, face);
        if (rand.nextInt(strength) < j1) {
            boolean flag;
            boolean bl = flag = world.func_147439_a(x, y, z) == Blocks.field_150335_W;
            if (rand.nextInt(meta + 10) < 5 && !world.func_72951_B(x, y, z)) {
                int k1 = meta + rand.nextInt(5) / 4;
                if (k1 > 15) {
                    k1 = 15;
                }
                this.setBlockWithTransmutationTarget(world, x, y, z, k1, (Block)this);
            } else {
                this.setBlockWithTransmutationTarget(world, x, y, z, 0, Blocks.field_150350_a);
            }
            if (flag) {
                Blocks.field_150335_W.func_149664_b(world, x, y, z, 1);
            }
        }
    }

    private boolean canNeighborBurn(World world, int x, int y, int z) {
        return this.isNeighborTarget(world, x, y, z);
    }

    private int getChanceOfNeighborsEncouragingFire(World world, int x, int y, int z) {
        if (!world.func_147437_c(x, y, z)) {
            return 0;
        }
        if (this.isNeighborTarget(world, x, y, z)) {
            return 100;
        }
        return 0;
    }

    public boolean func_149703_v() {
        return false;
    }

    @Deprecated
    public boolean func_149844_e(IBlockAccess p_149844_1_, int p_149844_2_, int p_149844_3_, int p_149844_4_) {
        return this.canCatchFire(p_149844_1_, p_149844_2_, p_149844_3_, p_149844_4_, ForgeDirection.UP);
    }

    @Deprecated
    public int func_149846_a(World p_149846_1_, int p_149846_2_, int p_149846_3_, int p_149846_4_, int p_149846_5_) {
        return this.getChanceToEncourageFire((IBlockAccess)p_149846_1_, p_149846_2_, p_149846_3_, p_149846_4_, p_149846_5_, ForgeDirection.UP);
    }

    public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        if (p_149689_5_ instanceof EntityPlayer) {
            ThaumicTinkerer.log.info("Player: " + ((EntityPlayer)p_149689_5_).getDisplayName() + " placed TT Fire");
        }
    }

    public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
        return true;
    }

    public boolean canCatchFire(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return this.getBlockFlammability(world, x, y, z, face) > 0;
    }

    public int getChanceToEncourageFire(IBlockAccess world, int x, int y, int z, int oldChance, ForgeDirection face) {
        int newChance = world.func_147439_a(x, y, z).getFireSpreadSpeed(world, x, y, z, face);
        return newChance > oldChance ? newChance : oldChance;
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        if (!World.func_147466_a((IBlockAccess)world, (int)x, (int)(y - 1), (int)z) && !this.canNeighborBurn(world, x, y, z)) {
            world.func_147468_f(x, y, z);
        }
    }

    public void func_149726_b(World world, int x, int y, int z) {
        if (world.field_73011_w.field_76574_g > 0 || !Blocks.field_150427_aO.func_150000_e(world, x, y, z)) {
            world.func_147464_a(x, y, z, (Block)this, this.func_149738_a(world) + world.field_73012_v.nextInt(10));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        block12: {
            block11: {
                float f2;
                float f1;
                float f;
                int l;
                if (rand.nextInt(24) == 0) {
                    world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "fire.fire", 1.0f + rand.nextFloat(), rand.nextFloat() * 0.7f + 0.3f, false);
                }
                if (World.func_147466_a((IBlockAccess)world, (int)x, (int)(y - 1), (int)z) || Blocks.field_150480_ab.canCatchFire((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP)) break block11;
                if (Blocks.field_150480_ab.canCatchFire((IBlockAccess)world, x - 1, y, z, ForgeDirection.EAST)) {
                    for (l = 0; l < 2; ++l) {
                        f = (float)x + rand.nextFloat() * 0.1f;
                        f1 = (float)y + rand.nextFloat();
                        f2 = (float)z + rand.nextFloat();
                        world.func_72869_a("largesmoke", (double)f, (double)f1, (double)f2, 0.0, 0.0, 0.0);
                    }
                }
                if (Blocks.field_150480_ab.canCatchFire((IBlockAccess)world, x + 1, y, z, ForgeDirection.WEST)) {
                    for (l = 0; l < 2; ++l) {
                        f = (float)(x + 1) - rand.nextFloat() * 0.1f;
                        f1 = (float)y + rand.nextFloat();
                        f2 = (float)z + rand.nextFloat();
                        world.func_72869_a("largesmoke", (double)f, (double)f1, (double)f2, 0.0, 0.0, 0.0);
                    }
                }
                if (Blocks.field_150480_ab.canCatchFire((IBlockAccess)world, x, y, z - 1, ForgeDirection.SOUTH)) {
                    for (l = 0; l < 2; ++l) {
                        f = (float)x + rand.nextFloat();
                        f1 = (float)y + rand.nextFloat();
                        f2 = (float)z + rand.nextFloat() * 0.1f;
                        world.func_72869_a("largesmoke", (double)f, (double)f1, (double)f2, 0.0, 0.0, 0.0);
                    }
                }
                if (Blocks.field_150480_ab.canCatchFire((IBlockAccess)world, x, y, z + 1, ForgeDirection.NORTH)) {
                    for (l = 0; l < 2; ++l) {
                        f = (float)x + rand.nextFloat();
                        f1 = (float)y + rand.nextFloat();
                        f2 = (float)(z + 1) - rand.nextFloat() * 0.1f;
                        world.func_72869_a("largesmoke", (double)f, (double)f1, (double)f2, 0.0, 0.0, 0.0);
                    }
                }
                if (!Blocks.field_150480_ab.canCatchFire((IBlockAccess)world, x, y + 1, z, ForgeDirection.DOWN)) break block12;
                for (l = 0; l < 2; ++l) {
                    f = (float)x + rand.nextFloat();
                    f1 = (float)(y + 1) - rand.nextFloat() * 0.1f;
                    f2 = (float)z + rand.nextFloat();
                    world.func_72869_a("largesmoke", (double)f, (double)f1, (double)f2, 0.0, 0.0, 0.0);
                }
                break block12;
            }
            for (int l = 0; l < 3; ++l) {
                float f = (float)x + rand.nextFloat();
                float f1 = (float)y + rand.nextFloat() * 0.5f + 0.5f;
                float f2 = (float)z + rand.nextFloat();
                world.func_72869_a("largesmoke", (double)f, (double)f1, (double)f2, 0.0, 0.0, 0.0);
            }
        }
    }

    public MapColor func_149728_f(int p_149728_1_) {
        return MapColor.field_151656_f;
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public boolean shouldRegister() {
        return true;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return null;
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return ItemBlockFire.class;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.icons = new IIcon[]{IconHelper.forName(iconRegister, this.getBlockName() + "_layer_0"), IconHelper.forName(iconRegister, this.getBlockName() + "_layer_1")};
        String s = "";
        if (this instanceof BlockFireAir) {
            s = "aer";
        }
        if (this instanceof BlockFireEarth) {
            s = "terra";
        }
        if (this instanceof BlockFireWater) {
            s = "aqua";
        }
        if (this instanceof BlockFireIgnis) {
            s = "ignis";
        }
        if (this instanceof BlockFireOrder) {
            s = "ordo";
        }
        if (this instanceof BlockFireChaos) {
            s = "perditio";
        }
        s = s + "Fire";
        this.itemIcon = IconHelper.forName(iconRegister, s);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149840_c(int p_149840_1_) {
        return this.icons[p_149840_1_];
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return this.itemIcon;
    }

    private static class FireInfo {
        public int encouragement = 0;
        public int flammibility = 0;

        public FireInfo(int flammibility, int encouragement) {
            this.flammibility = flammibility;
            this.encouragement = encouragement;
        }
    }
}

