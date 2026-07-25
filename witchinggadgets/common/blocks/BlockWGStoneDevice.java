/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.BlockDirectional
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package witchinggadgets.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.render.BlockRenderStoneDevice;
import witchinggadgets.common.blocks.tiles.TileEntityAgeingStone;
import witchinggadgets.common.blocks.tiles.TileEntityBlastfurnace;
import witchinggadgets.common.blocks.tiles.TileEntityEtherealWall;
import witchinggadgets.common.blocks.tiles.TileEntityMagicalTileLock;
import witchinggadgets.common.blocks.tiles.TileEntitySarcophagus;
import witchinggadgets.common.util.recipe.InfernalBlastfurnaceRecipe;

public class BlockWGStoneDevice
extends BlockContainer {
    public static String[] subNames = new String[]{"etherealWall", "magicTileLock", "obsidianPlate", "obsidianDecoration0", "obsidianDecoration1", "obsidianDecoration2", "sarcophagus", "timeStone", "blastFurnace"};
    IIcon[] icons = new IIcon[subNames.length];

    public BlockWGStoneDevice() {
        super(Material.field_151576_e);
        this.func_149711_c(4.0f);
        this.func_149752_b(10.0f);
        this.func_149647_a(WitchingGadgets.tabWG);
    }

    public int func_149692_a(int meta) {
        return meta == 3 || meta == 4 || meta == 5 ? 3 : meta;
    }

    public void func_149651_a(IIconRegister iconRegister) {
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = iconRegister.func_94245_a("witchinggadgets:" + subNames[i]);
        }
        TileEntityBlastfurnace.icon_bricks = iconRegister.func_94245_a("witchinggadgets:blastFurnace");
        TileEntityBlastfurnace.icon_cornerBottomL = new IIcon[]{iconRegister.func_94245_a("witchinggadgets:blastFurnace_cornerBottomL_off"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_cornerBottomL_on"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_cornerBottomL_special")};
        TileEntityBlastfurnace.icon_cornerBottomR = new IIcon[]{iconRegister.func_94245_a("witchinggadgets:blastFurnace_cornerBottomR_off"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_cornerBottomR_on"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_cornerBottomR_special")};
        TileEntityBlastfurnace.icon_cornerTopL = new IIcon[]{iconRegister.func_94245_a("witchinggadgets:blastFurnace_cornerTopL_off"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_cornerTopL_on"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_cornerTopL_special")};
        TileEntityBlastfurnace.icon_cornerTopR = new IIcon[]{iconRegister.func_94245_a("witchinggadgets:blastFurnace_cornerTopR_off"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_cornerTopR_on"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_cornerTopR_special")};
        TileEntityBlastfurnace.icon_sideBottom = new IIcon[]{iconRegister.func_94245_a("witchinggadgets:blastFurnace_sideBottom_off"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_sideBottom_on"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_sideBottom_special")};
        TileEntityBlastfurnace.icon_sideTop = new IIcon[]{iconRegister.func_94245_a("witchinggadgets:blastFurnace_sideTop_off"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_sideTop_on"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_sideTop_special")};
        TileEntityBlastfurnace.icon_bottom = iconRegister.func_94245_a("witchinggadgets:blastFurnace_bottom");
        TileEntityBlastfurnace.icon_bottomTBLR = new IIcon[]{iconRegister.func_94245_a("witchinggadgets:blastFurnace_bottomT"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_bottomB"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_bottomL"), iconRegister.func_94245_a("witchinggadgets:blastFurnace_bottomR")};
        TileEntityBlastfurnace.icon_internal = iconRegister.func_94245_a("witchinggadgets:blastFurnace_internal");
        TileEntityBlastfurnace.icon_lava = iconRegister.func_94245_a("witchinggadgets:blastFurnace_lava");
    }

    public IIcon func_149691_a(int side, int meta) {
        if (meta == 6 && (side == 0 || side == 1)) {
            return this.icons[2];
        }
        return this.icons[Math.min(meta, this.icons.length - 1)];
    }

    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        if (world.func_147438_o(x, y, z) instanceof TileEntityEtherealWall) {
            TileEntityEtherealWall tile = (TileEntityEtherealWall)world.func_147438_o(x, y, z);
            if (tile.camoID != null && tile.camoID != null && tile.isRenderTypeValid(tile.camoID.func_149645_b(), tile.camoMeta)) {
                return tile.camoID.func_149691_a(side, tile.camoMeta);
            }
        }
        if (world.func_147438_o(x, y, z) instanceof TileEntitySarcophagus && (side == 0 || side == 1)) {
            return this.icons[2];
        }
        if (world.func_147438_o(x, y, z) instanceof TileEntityBlastfurnace) {
            return ((TileEntityBlastfurnace)world.func_147438_o(x, y, z)).getTexture(side);
        }
        return this.icons[Math.min(world.func_72805_g(x, y, z), this.icons.length - 1)];
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (world.func_147438_o(x, y, z) instanceof TileEntityEtherealWall) {
            TileEntityEtherealWall tile = (TileEntityEtherealWall)world.func_147438_o(x, y, z);
            if (tile.camoID != null) {
                return tile.camoID.func_149750_m();
            }
        }
        if (world.func_147438_o(x, y, z) instanceof TileEntityBlastfurnace) {
            byte pos = ((TileEntityBlastfurnace)world.func_147438_o((int)x, (int)y, (int)z)).position;
            return pos == 10 || pos == 12 || pos == 14 || pos == 16 ? 13 : (pos == 22 ? 15 : 0);
        }
        return 0;
    }

    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        int xx = x - ForgeDirection.getOrientation((int)side).offsetX;
        int yy = y - ForgeDirection.getOrientation((int)side).offsetY;
        int zz = z - ForgeDirection.getOrientation((int)side).offsetZ;
        if (iBlockAccess.func_147438_o(xx, yy, zz) instanceof TileEntityEtherealWall) {
            boolean sameBlock;
            TileEntityEtherealWall tile = (TileEntityEtherealWall)iBlockAccess.func_147438_o(xx, yy, zz);
            boolean bl = sameBlock = iBlockAccess.func_147439_a(x, y, z).equals((Object)this) && iBlockAccess.func_72805_g(x, y, z) == iBlockAccess.func_72805_g(xx, yy, zz);
            boolean sameRenderBlock = tile.camoID != null ? iBlockAccess.func_147439_a(x, y, z).equals(tile.camoID) && iBlockAccess.func_72805_g(x, y, z) == tile.camoMeta : false;
            sameBlock |= sameRenderBlock;
            if (iBlockAccess.func_147438_o(x, y, z) instanceof TileEntityEtherealWall) {
                sameBlock &= ((TileEntityEtherealWall)iBlockAccess.func_147438_o((int)x, (int)y, (int)z)).camoID != null ? ((TileEntityEtherealWall)iBlockAccess.func_147438_o((int)x, (int)y, (int)z)).camoID.equals(tile.camoID) && ((TileEntityEtherealWall)iBlockAccess.func_147438_o((int)x, (int)y, (int)z)).camoMeta == tile.camoMeta : false;
            }
            return !sameBlock;
        }
        if (iBlockAccess.func_147438_o(xx, yy, zz) instanceof TileEntityBlastfurnace) {
            return true;
        }
        return super.func_149646_a(iBlockAccess, x, y, z, side);
    }

    public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity) {
        int pos;
        TileEntityEtherealWall tile;
        if (world.func_147438_o(x, y, z) instanceof TileEntityEtherealWall && (tile = (TileEntityEtherealWall)world.func_147438_o(x, y, z)) != null && tile.master != null && tile.master.isAnyTileInNetPowered()) {
            return;
        }
        if (world.func_147438_o(x, y, z) instanceof TileEntityBlastfurnace && (pos = ((TileEntityBlastfurnace)world.func_147438_o((int)x, (int)y, (int)z)).position) > 17 && pos != 22) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
            super.func_149743_a(world, x, y, z, aabb, list, entity);
            this.func_149676_a((pos -= 18) % 3 == 0 ? 0.5f : 0.0f, 0.5f, pos < 3 ? 0.5f : 0.0f, (pos + 1) % 3 == 0 ? 0.5f : 1.0f, 1.0f, pos > 5 ? 0.5f : 1.0f);
            super.func_149743_a(world, x, y, z, aabb, list, entity);
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            return;
        }
        super.func_149743_a(world, x, y, z, aabb, list, entity);
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        if (world.func_147438_o(x, y, z) instanceof TileEntityBlastfurnace && ((TileEntityBlastfurnace)world.func_147438_o((int)x, (int)y, (int)z)).position == 22) {
            return null;
        }
        return super.func_149668_a(world, x, y, z);
    }

    public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
        if (world.func_147438_o(x, y, z) instanceof TileEntitySarcophagus && !((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).dummyLeft && !((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).dummyRight) {
            switch (((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).facing) {
                case 2: 
                case 3: {
                    return AxisAlignedBB.func_72330_a((double)(x - 1), (double)(y + 0), (double)(z + 0), (double)(x + 2), (double)(y + 1), (double)(z + 1));
                }
                case 4: 
                case 5: {
                    return AxisAlignedBB.func_72330_a((double)(x + 0), (double)(y + 0), (double)(z - 1), (double)(x + 1), (double)(y + 1), (double)(z + 2));
                }
            }
        }
        if (world.func_147438_o(x, y, z) instanceof TileEntityBlastfurnace && ((TileEntityBlastfurnace)world.func_147438_o((int)x, (int)y, (int)z)).position == 22) {
            return AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)x, (double)y, (double)z);
        }
        return super.func_149633_g(world, x, y, z);
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return !(world.func_147438_o(x, y, z) instanceof TileEntityBlastfurnace) || ((TileEntityBlastfurnace)world.func_147438_o((int)x, (int)y, (int)z)).position != 22;
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        if (!world.field_72995_K && world.func_147438_o(x, y, z) instanceof TileEntityBlastfurnace && ((TileEntityBlastfurnace)world.func_147438_o((int)x, (int)y, (int)z)).position == 22) {
            if (entity instanceof EntityItem) {
                ItemStack input = ((EntityItem)entity).func_92059_d();
                if (InfernalBlastfurnaceRecipe.getRecipeForInput(input) == null) {
                    world.func_147452_c(x, y, z, (Block)this, 5, 0);
                    entity.func_70106_y();
                    return;
                }
                int[] mPos = ((TileEntityBlastfurnace)world.func_147438_o((int)x, (int)y, (int)z)).masterPos;
                TileEntityBlastfurnace master = (TileEntityBlastfurnace)world.func_147438_o(mPos[0], mPos[1], mPos[2]);
                master.addStackToInputs(input);
                entity.func_70106_y();
            } else if (entity instanceof EntityLivingBase && !entity.func_70045_F()) {
                entity.func_70097_a(DamageSource.field_76371_c, 3.0f);
                entity.func_70015_d(10);
            }
        }
    }

    public TileEntity func_149915_a(World world, int metadata) {
        switch (metadata) {
            case 0: {
                return new TileEntityEtherealWall();
            }
            case 1: {
                return new TileEntityMagicalTileLock();
            }
            case 6: {
                return new TileEntitySarcophagus();
            }
            case 7: {
                return new TileEntityAgeingStone();
            }
            case 8: {
                return new TileEntityBlastfurnace();
            }
        }
        return null;
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack stack) {
        int f;
        int playerViewQuarter = MathHelper.func_76128_c((double)((double)(entityLiving.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        int meta = world.func_72805_g(x, y, z);
        int n = playerViewQuarter == 0 ? 2 : (playerViewQuarter == 1 ? 5 : (f = playerViewQuarter == 2 ? 3 : 4));
        if (meta == 1) {
            ((TileEntityMagicalTileLock)world.func_147438_o((int)x, (int)y, (int)z)).lockPreset = entityLiving.func_70681_au().nextInt(16);
        } else if (meta == 3) {
            world.func_72921_c(x, y, z, meta + entityLiving.func_70681_au().nextInt(3), 3);
        } else if (meta == 6) {
            ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).facing = f;
            if (f == 2 || f == 3) {
                world.func_147465_d(x - 1, y, z, (Block)this, 6, 3);
                ((TileEntitySarcophagus)world.func_147438_o((int)(x - 1), (int)y, (int)z)).facing = f;
                ((TileEntitySarcophagus)world.func_147438_o((int)(x - 1), (int)y, (int)z)).dummyLeft = true;
                world.func_147465_d(x + 1, y, z, (Block)this, 6, 3);
                ((TileEntitySarcophagus)world.func_147438_o((int)(x + 1), (int)y, (int)z)).facing = f;
                ((TileEntitySarcophagus)world.func_147438_o((int)(x + 1), (int)y, (int)z)).dummyRight = true;
            } else {
                world.func_147465_d(x, y, z - 1, (Block)this, 6, 3);
                ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)(z - 1))).facing = f;
                ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)(z - 1))).dummyLeft = true;
                world.func_147465_d(x, y, z + 1, (Block)this, 6, 3);
                ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)(z + 1))).facing = f;
                ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)(z + 1))).dummyRight = true;
            }
        }
    }

    public void func_149749_a(World world, int x, int y, int z, Block b, int side) {
        int[] mPos;
        if (world.func_147438_o(x, y, z) instanceof TileEntitySarcophagus) {
            if (((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).facing == 2 || ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).facing == 3) {
                if (((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).dummyLeft) {
                    ++x;
                }
                if (((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).dummyRight) {
                    --x;
                }
            } else {
                if (((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).dummyLeft) {
                    ++z;
                }
                if (((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).dummyRight) {
                    --z;
                }
            }
            ItemStack[] inv = ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).inv;
            if (!((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).dummyLeft && !((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).dummyRight) {
                switch (((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).facing) {
                    case 2: 
                    case 3: {
                        world.func_147468_f(x - 1, y, z);
                        world.func_147468_f(x, y, z);
                        world.func_147468_f(x + 1, y, z);
                    }
                    case 4: 
                    case 5: {
                        world.func_147468_f(x, y, z - 1);
                        world.func_147468_f(x, y, z);
                        world.func_147468_f(x, y, z + 1);
                    }
                }
            }
            for (ItemStack s : inv) {
                if (s == null) continue;
                float f = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                float f1 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                float f2 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                while (s.field_77994_a > 0) {
                    int k1 = world.field_73012_v.nextInt(21) + 10;
                    if (k1 > s.field_77994_a) {
                        k1 = s.field_77994_a;
                    }
                    s.field_77994_a -= k1;
                    EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(s.func_77973_b(), k1, s.func_77960_j()));
                    float f3 = 0.05f;
                    entityitem.field_70159_w = (float)world.field_73012_v.nextGaussian() * f3;
                    entityitem.field_70181_x = (float)world.field_73012_v.nextGaussian() * f3 + 0.2f;
                    entityitem.field_70179_y = (float)world.field_73012_v.nextGaussian() * f3;
                    if (s.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)s.func_77978_p().func_74737_b());
                    }
                    world.func_72838_d((Entity)entityitem);
                }
            }
        }
        if (world.func_147438_o(x, y, z) instanceof TileEntityBlastfurnace && (mPos = ((TileEntityBlastfurnace)world.func_147438_o((int)x, (int)y, (int)z)).masterPos) != null && mPos.length > 2 && world.func_147439_a(mPos[0], mPos[1], mPos[2]).equals((Object)this)) {
            byte pos = ((TileEntityBlastfurnace)world.func_147438_o((int)x, (int)y, (int)z)).position;
            BlockWGStoneDevice.removeBlastfurnace(world, mPos[0], mPos[1], mPos[2], x, y, z);
            if (pos != 22) {
                EntityItem blockDrop = new EntityItem(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, pos < 18 ? new ItemStack(TileEntityBlastfurnace.brickBlock[pos], 1, 0) : new ItemStack(TileEntityBlastfurnace.stairBlock, 1, TileEntityBlastfurnace.stairBlock != Blocks.field_150390_bg ? 1 : 0));
                blockDrop.field_70159_w = (float)world.field_73012_v.nextGaussian() * 0.05f;
                blockDrop.field_70181_x = (float)world.field_73012_v.nextGaussian() * 0.05f + 0.2f;
                blockDrop.field_70179_y = (float)world.field_73012_v.nextGaussian() * 0.05f;
                world.func_72838_d((Entity)blockDrop);
            }
        }
        super.func_149749_a(world, x, y, z, b, side);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        if (metadata == 6 || metadata == 8) {
            return new ArrayList<ItemStack>();
        }
        return super.getDrops(world, x, y, z, metadata, fortune);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.func_147438_o(x, y, z) instanceof TileEntityEtherealWall) {
            TileEntityEtherealWall tile = (TileEntityEtherealWall)world.func_147438_o(x, y, z);
            ItemStack currentStack = player.func_71045_bC();
            Block camoID = null;
            int camoMeta = -1;
            boolean changeTexture = false;
            if (currentStack != null && currentStack.func_77973_b() instanceof ItemBlock) {
                Block block = Block.func_149634_a((Item)currentStack.func_77973_b());
                int blockMeta = currentStack.func_77960_j();
                if (player.func_70093_af() || block instanceof BlockWGStoneDevice && blockMeta == 0) {
                    return false;
                }
                if (block != null && tile.isRenderTypeValid(block.func_149645_b(), blockMeta) && block.func_149688_o() != Material.field_151579_a) {
                    if (block instanceof BlockDirectional) {
                        switch (side) {
                            case 0: 
                            case 1: {
                                break;
                            }
                            case 2: {
                                blockMeta = blockMeta & 0xC | 2;
                                break;
                            }
                            case 3: {
                                blockMeta = blockMeta & 0xC | 0;
                                break;
                            }
                            case 4: {
                                blockMeta = blockMeta & 0xC | 1;
                                break;
                            }
                            case 5: {
                                blockMeta = blockMeta & 0xC | 3;
                            }
                        }
                    }
                    if (block.func_149645_b() == 39 && blockMeta == 2) {
                        switch (side) {
                            case 0: 
                            case 1: {
                                blockMeta = 2;
                                break;
                            }
                            case 2: 
                            case 3: {
                                blockMeta = 4;
                                break;
                            }
                            case 4: 
                            case 5: {
                                blockMeta = 3;
                            }
                        }
                    }
                    if (block.func_149645_b() == 31) {
                        int j1 = blockMeta & 3;
                        int b0 = 0;
                        switch (side) {
                            case 0: 
                            case 1: {
                                b0 = 0;
                                break;
                            }
                            case 2: 
                            case 3: {
                                b0 = 8;
                                break;
                            }
                            case 4: 
                            case 5: {
                                b0 = 4;
                            }
                        }
                        blockMeta = j1 | b0;
                    }
                    camoID = block;
                    camoMeta = blockMeta;
                    changeTexture = true;
                }
            } else {
                changeTexture = player.func_70093_af();
            }
            if (changeTexture) {
                tile.camoID = camoID;
                tile.camoMeta = camoMeta;
                world.func_147458_c(x, y, z, x, y, z);
            }
            return changeTexture;
        }
        if (world.func_147438_o(x, y, z) instanceof TileEntityMagicalTileLock) {
            if (((TileEntityMagicalTileLock)world.func_147438_o((int)x, (int)y, (int)z)).lockPreset < 0) {
                ((TileEntityMagicalTileLock)world.func_147438_o((int)x, (int)y, (int)z)).lockPreset = world.field_73012_v.nextInt(16);
            }
            player.openGui((Object)WitchingGadgets.instance, 10, world, x, y, z);
            return true;
        }
        if (world.func_147438_o(x, y, z) instanceof TileEntitySarcophagus && !((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).open) {
            if (((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).facing == 2 || ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).facing == 3) {
                if (((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).dummyLeft) {
                    ++x;
                }
                if (((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).dummyRight) {
                    --x;
                }
            } else {
                if (((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).dummyLeft) {
                    ++z;
                }
                if (((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).dummyRight) {
                    --z;
                }
            }
            if (((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).facing == 2 || ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).facing == 3) {
                ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).open = true;
                ((TileEntitySarcophagus)world.func_147438_o((int)(x + 1), (int)y, (int)z)).open = true;
                ((TileEntitySarcophagus)world.func_147438_o((int)(x - 1), (int)y, (int)z)).open = true;
                world.func_147458_c(x - 1, y, z, x + 1, y, z);
            } else {
                ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).open = true;
                ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)(z + 1))).open = true;
                ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)(z - 1))).open = true;
                world.func_147458_c(x, y, z - 1, x, y, z + 1);
            }
            if (!world.field_72995_K) {
                for (ItemStack s : ((TileEntitySarcophagus)world.func_147438_o((int)x, (int)y, (int)z)).inv) {
                    if (s == null) continue;
                    float f = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                    float f1 = world.field_73012_v.nextFloat() * 0.8f + 0.75f;
                    float f2 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                    while (s.field_77994_a > 0) {
                        int k1 = world.field_73012_v.nextInt(21) + 10;
                        if (k1 > s.field_77994_a) {
                            k1 = s.field_77994_a;
                        }
                        s.field_77994_a -= k1;
                        EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(s.func_77973_b(), k1, s.func_77960_j()));
                        float f3 = 0.05f;
                        entityitem.field_70159_w = (float)world.field_73012_v.nextGaussian() * f3;
                        entityitem.field_70181_x = (float)world.field_73012_v.nextGaussian() * f3 + 0.2f;
                        entityitem.field_70179_y = (float)world.field_73012_v.nextGaussian() * f3;
                        if (s.func_77942_o()) {
                            entityitem.func_92059_d().func_77982_d((NBTTagCompound)s.func_77978_p().func_74737_b());
                        }
                        world.func_72838_d((Entity)entityitem);
                    }
                }
            }
        }
        return false;
    }

    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
        TileEntityEtherealWall tile;
        if (world.func_147438_o(x, y, z) instanceof TileEntityEtherealWall && (tile = (TileEntityEtherealWall)world.func_147438_o(x, y, z)) != null && tile.master != null) {
            tile.master.freeSlaves();
        }
    }

    static void removeBlastfurnace(World world, int x, int y, int z, int hitX, int hitY, int hitZ) {
        world.func_147465_d(x, y, z, TileEntityBlastfurnace.brickBlock[4], 0, 3);
        world.func_147471_g(x, y, z);
        for (int yy = 0; yy <= 2; ++yy) {
            for (int xx = -1; xx <= 1; ++xx) {
                for (int zz = -1; zz <= 1; ++zz) {
                    if (yy == 0 && xx == 0 && zz == 0 || x + xx == hitX && y + yy == hitY && z + zz == hitZ || !(world.func_147438_o(x + xx, y + yy, z + zz) instanceof TileEntityBlastfurnace)) continue;
                    if (yy != 2) {
                        world.func_147465_d(x + xx, y + yy, z + zz, TileEntityBlastfurnace.brickBlock[yy * 9 + (zz + 1) * 3 + (xx + 1)], 0, 3);
                    } else if (xx == 0 && zz == 0) {
                        world.func_147465_d(x + xx, y + yy, z + zz, Blocks.field_150353_l, 0, 3);
                    } else {
                        int md = xx == -1 ? 0 : (xx == 1 ? 1 : (zz == -1 ? 2 : 3));
                        world.func_147465_d(x + xx, y + yy, z + zz, TileEntityBlastfurnace.stairBlock, md, 3);
                        if (world.func_147438_o(x + xx, y + yy, z + zz) != null) {
                            TileEntity tile = world.func_147438_o(x + xx, y + yy, z + zz);
                            NBTTagCompound tag = new NBTTagCompound();
                            tile.func_145841_b(tag);
                            tag.func_74778_a("stair", "INFERNAL_BRICK");
                            tile.func_145839_a(tag);
                        }
                    }
                    world.func_147471_g(x + xx, y + yy, z + zz);
                }
            }
        }
    }

    public int func_149645_b() {
        return BlockRenderStoneDevice.renderID;
    }

    public boolean canRenderInPass(int pass) {
        BlockRenderStoneDevice.renderPass = pass;
        return true;
    }

    public int func_149720_d(IBlockAccess world, int x, int y, int z) {
        if (world.func_147438_o(x, y, z) instanceof TileEntityEtherealWall) {
            TileEntityEtherealWall tile = (TileEntityEtherealWall)world.func_147438_o(x, y, z);
            if (tile.camoID != null) {
                return tile.camoID instanceof BlockWGStoneDevice ? 0xFFFFFF : tile.camoID.func_149720_d(world, x, y, z);
            }
        }
        return 0xFFFFFF;
    }

    public float func_149712_f(World world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md == 1 || md == 2) {
            return -1.0f;
        }
        return super.func_149712_f(world, x, y, z);
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        int md = world.func_72805_g(x, y, z);
        if (md == 1 || md == 2) {
            return 999.0f;
        }
        return this.func_149638_a(par1Entity);
    }
}

