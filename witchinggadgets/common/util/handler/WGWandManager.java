/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.ThaumcraftApiHelper
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.wands.IWandTriggerManager
 *  thaumcraft.common.Thaumcraft
 */
package witchinggadgets.common.util.handler;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandTriggerManager;
import thaumcraft.common.Thaumcraft;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.blocks.tiles.TileEntityBlastfurnace;
import witchinggadgets.common.util.Utilities;

public class WGWandManager
implements IWandTriggerManager {
    public boolean performTrigger(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side, int event) {
        switch (event) {
            case 1: {
                return this.createBlastFurnace(wand, player, world, x, y, z);
            }
        }
        return false;
    }

    private boolean createBlastFurnace(ItemStack itemstack, EntityPlayer player, World world, int clickedX, int clickedY, int clickedZ) {
        int xx;
        int z;
        int y;
        int x;
        if (world.field_72995_K) {
            return false;
        }
        if (!ThaumcraftApiHelper.isResearchComplete((String)player.func_70005_c_(), (String)"INFERNALBLASTFURNACE")) {
            return false;
        }
        if (!Utilities.consumeVisFromWand(itemstack, player, new AspectList().add(Aspect.FIRE, 50).add(Aspect.EARTH, 50).add(Aspect.ENTROPY, 50), false)) {
            return false;
        }
        int playerViewQuarter = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        int f = playerViewQuarter == 0 ? 2 : (playerViewQuarter == 1 ? 5 : (playerViewQuarter == 2 ? 3 : 4));
        List<int[]> lavas = this.getNearbyLava(world, clickedX, clickedY, clickedZ);
        if (lavas.isEmpty()) {
            return false;
        }
        int lava = -1;
        for (lava = 0; lava < lavas.size(); ++lava) {
            x = lavas.get(lava)[0];
            y = lavas.get(lava)[1] - 2;
            z = lavas.get(lava)[2];
            boolean structure = true;
            structure &= this.isValidBFStair(world, x - 1, y + 2, z - 1, 18, 0, 2);
            structure &= this.isValidBFStair(world, x - 1, y + 2, z + 0, 21, 0);
            structure &= this.isValidBFStair(world, x - 1, y + 2, z + 1, 24, 0, 3);
            structure &= this.isValidBFStair(world, x + 0, y + 2, z - 1, 19, 2);
            structure &= this.isValidBFStair(world, x + 0, y + 2, z + 1, 25, 3);
            structure &= this.isValidBFStair(world, x + 1, y + 2, z - 1, 20, 1, 2);
            structure &= this.isValidBFStair(world, x + 1, y + 2, z + 0, 23, 1);
            structure &= this.isValidBFStair(world, x + 1, y + 2, z + 1, 26, 1, 3);
            for (int yy = 0; yy <= 1; ++yy) {
                for (xx = -1; xx <= 1; ++xx) {
                    for (int zz = -1; zz <= 1; ++zz) {
                        structure &= this.isValidBFBrick(world, x + xx, y + yy, z + zz, yy * 9 + (zz + 1) * 3 + (xx + 1));
                    }
                }
            }
            if (structure) break;
        }
        if (lava >= 0 && lava < lavas.size()) {
            int zz;
            int yy;
            x = lavas.get(lava)[0];
            y = lavas.get(lava)[1] - 2;
            z = lavas.get(lava)[2];
            for (yy = 0; yy <= 2; ++yy) {
                for (zz = -1; zz <= 1; ++zz) {
                    for (xx = -1; xx <= 1; ++xx) {
                        byte pos;
                        world.func_147465_d(x + xx, y + yy, z + zz, WGContent.BlockStoneDevice, 8, 3);
                        TileEntityBlastfurnace tile = (TileEntityBlastfurnace)world.func_147438_o(x + xx, y + yy, z + zz);
                        tile.masterPos = new int[]{x, y, z};
                        tile.position = pos = (byte)(yy * 9 + (zz + 1) * 3 + (xx + 1));
                        tile.facing = ForgeDirection.getOrientation((int)f);
                        tile.func_70296_d();
                    }
                }
            }
            for (yy = -2; yy <= 0; ++yy) {
                for (zz = -1; zz <= 1; ++zz) {
                    for (xx = -1; xx <= 1; ++xx) {
                        world.func_147471_g(x + xx, y + yy, z + zz);
                    }
                }
            }
            Utilities.consumeVisFromWand(itemstack, player, new AspectList().add(Aspect.FIRE, 50).add(Aspect.EARTH, 50).add(Aspect.ENTROPY, 50), true);
            Thaumcraft.addStickyWarpToPlayer((EntityPlayer)player, (int)3);
            return true;
        }
        return false;
    }

    List<int[]> getNearbyLava(World world, int x, int y, int z) {
        ArrayList<int[]> result = new ArrayList<int[]>();
        for (int yy = 0; yy <= 2; ++yy) {
            for (int xx = -1; xx <= 1; ++xx) {
                for (int zz = -1; zz <= 1; ++zz) {
                    if (world.func_147439_a(x + xx, y + yy, z + zz) != Blocks.field_150353_l) continue;
                    result.add(new int[]{x + xx, y + yy, z + zz});
                }
            }
        }
        return result;
    }

    boolean isValidBFBrick(World world, int x, int y, int z, int pos) {
        return world.func_147439_a(x, y, z).equals(TileEntityBlastfurnace.brickBlock[pos]) && world.func_72805_g(x, y, z) == 0;
    }

    boolean isValidBFStair(World world, int x, int y, int z, int pos, int ... requestedMeta) {
        boolean b = world.func_147439_a(x, y, z).equals(TileEntityBlastfurnace.stairBlock);
        if (TileEntityBlastfurnace.stairBlock != Blocks.field_150417_aV && world.func_147438_o(x, y, z) != null) {
            NBTTagCompound tag = new NBTTagCompound();
            world.func_147438_o(x, y, z).func_145841_b(tag);
            b &= tag.func_74779_i("stair").equalsIgnoreCase("INFERNAL_BRICK");
        }
        boolean meta = false;
        for (int rm : requestedMeta) {
            if (rm != world.func_72805_g(x, y, z)) continue;
            meta = true;
        }
        return b &= meta;
    }
}

