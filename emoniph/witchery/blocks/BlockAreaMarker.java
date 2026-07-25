/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.blocks.TileEntityBase;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.rites.RiteCurseCreature;
import com.emoniph.witchery.ritual.rites.RiteTeleportEntity;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Log;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class BlockAreaMarker
extends BlockBaseContainer {
    public BlockAreaMarker(Class<? extends TileEntityAreaMarker> clazzTile) {
        super(Material.field_151576_e, clazzTile);
        this.func_149722_s();
        this.func_149752_b(9999.0f);
        this.func_149711_c(2.5f);
        this.func_149672_a(field_149769_e);
        this.func_149676_a(0.15f, 0.0f, 0.15f, 0.85f, 0.5f, 0.85f);
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        int l = MathHelper.func_76128_c((double)((double)(entity.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        if (l == 0) {
            world.func_72921_c(x, y, z, 2, 2);
        }
        if (l == 1) {
            world.func_72921_c(x, y, z, 5, 2);
        }
        if (l == 2) {
            world.func_72921_c(x, y, z, 3, 2);
        }
        if (l == 3) {
            world.func_72921_c(x, y, z, 4, 2);
        }
        if (!world.field_72995_K && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            TileEntity tile = world.func_147438_o(x, y, z);
            if (tile != null && tile instanceof TileEntityAreaMarker) {
                TileEntityAreaMarker marker = (TileEntityAreaMarker)tile;
                marker.setOwner(player.func_70005_c_());
            }
        }
    }

    public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
        TileEntity tile;
        if (!world.field_72995_K & player != null && (tile = world.func_147438_o(x, y, z)) != null && tile instanceof TileEntityAreaMarker) {
            TileEntityAreaMarker marker = (TileEntityAreaMarker)tile;
            if (player.field_71075_bZ.field_75098_d || player.func_70005_c_().equals(marker.getOwner()) && player.func_70093_af()) {
                int dy = y;
                while (world.func_147439_a(x, dy, z) == this) {
                    world.func_147468_f(x, dy, z);
                    world.func_72838_d((Entity)new EntityItem(world, 0.5 + (double)x, 0.5 + (double)dy, 0.5 + (double)z, new ItemStack((Block)this)));
                    ++dy;
                }
            }
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileEntityAreaMarker) {
            TileEntityAreaMarker marker = (TileEntityAreaMarker)tile;
            return marker.activateBlock(world, x, y, z, player, side);
        }
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return 0;
    }

    public int func_149745_a(Random rand) {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("stone");
    }

    public static class AreaMarkerEventHooks {
        @SubscribeEvent(priority=EventPriority.NORMAL)
        public void onLivingDeath(LivingDeathEvent event) {
            if (!event.isCanceled() && !event.entityLiving.field_70170_p.field_72995_K && event.entityLiving instanceof EntityPlayer && event.source.func_76364_f() != null && event.source.func_76364_f() instanceof EntityPlayer && event.source.func_76364_f() != event.entityLiving) {
                EntityPlayer attacker = (EntityPlayer)event.source.func_76364_f();
                for (TileEntityAreaMarker tile : AreaMarkerRegistry.instance().tiles) {
                    if (!tile.isNear((EntityLivingBase)attacker)) continue;
                    tile.setKiller(attacker);
                }
            }
        }
    }

    public static abstract class TileEntityAreaMarker
    extends TileEntityBase {
        private static final String OWNER_KEY = "WITCPlacer";
        private String owner;
        private ArrayList<String> killers = new ArrayList();

        @Override
        protected void initiate() {
            super.initiate();
            if (!this.field_145850_b.field_72995_K) {
                if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this.getExpectedBlockType()) {
                    AreaMarkerRegistry.instance().add(this);
                } else {
                    Log.instance().warning("Area Marker tile entity exists without a corresponding block at: " + this.field_145851_c + ", " + this.field_145848_d + ", " + this.field_145849_e);
                }
            }
        }

        public void func_145843_s() {
            super.func_145843_s();
            if (!this.field_145850_b.field_72995_K) {
                AreaMarkerRegistry.instance().remove(this);
            }
        }

        public void setOwner(String username) {
            this.owner = username;
        }

        public String getOwner() {
            return this.owner != null ? this.owner : "";
        }

        public void setKiller(EntityPlayer player) {
            String username = player.func_70005_c_();
            if (!this.killers.contains(username)) {
                this.killers.add(username);
            }
        }

        public boolean checkIsProtected(EntityLivingBase entity, Rite rite) {
            if (this.isNear(entity)) {
                boolean killer = entity instanceof EntityPlayer && this.killers.contains(entity.func_70005_c_());
                return this.isProtected(entity, killer, rite);
            }
            return false;
        }

        public void func_145841_b(NBTTagCompound nbtTag) {
            super.func_145841_b(nbtTag);
            nbtTag.func_74778_a(OWNER_KEY, this.getOwner());
            NBTTagList nbtKillers = new NBTTagList();
            for (String killer : this.killers) {
                nbtKillers.func_74742_a((NBTBase)new NBTTagString(killer));
            }
            nbtTag.func_74782_a("Killers", (NBTBase)nbtKillers);
        }

        public void func_145839_a(NBTTagCompound nbtTag) {
            super.func_145839_a(nbtTag);
            this.owner = nbtTag.func_74764_b(OWNER_KEY) ? nbtTag.func_74779_i(OWNER_KEY) : "";
            NBTTagList nbtKillers = nbtTag.func_150295_c("Killers", 8);
            int count = nbtKillers.func_74745_c();
            for (int i = 0; i < count; ++i) {
                this.killers.add(nbtKillers.func_150307_f(i));
            }
        }

        public abstract boolean activateBlock(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6);

        protected abstract boolean isNear(EntityLivingBase var1);

        protected abstract boolean isProtected(EntityLivingBase var1, boolean var2, Rite var3);

        protected abstract Block getExpectedBlockType();
    }

    public static class AreaMarkerRegistry {
        private static AreaMarkerRegistry INSTANCE_CLIENT;
        private static AreaMarkerRegistry INSTANCE_SERVER;
        private final ArrayList<TileEntityAreaMarker> tiles = new ArrayList();

        public static AreaMarkerRegistry instance() {
            if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
                return INSTANCE_SERVER;
            }
            return INSTANCE_CLIENT;
        }

        public static void serverStart() {
            INSTANCE_CLIENT = new AreaMarkerRegistry();
            INSTANCE_SERVER = new AreaMarkerRegistry();
        }

        private void add(TileEntityAreaMarker tile) {
            if (!this.tiles.contains((Object)tile)) {
                try {
                    Iterator<TileEntityAreaMarker> it = this.tiles.iterator();
                    while (it.hasNext()) {
                        TileEntityAreaMarker source = it.next();
                        if (source != null && !source.func_145837_r() && (source.field_145851_c != tile.field_145851_c || source.field_145848_d != tile.field_145848_d || source.field_145849_e != tile.field_145849_e)) continue;
                        it.remove();
                    }
                }
                catch (Throwable e) {
                    Log.instance().warning(e, "Exception occured validating existing power source entries");
                }
                this.tiles.add(tile);
            }
        }

        private void remove(TileEntityAreaMarker tile) {
            if (this.tiles.contains((Object)tile)) {
                this.tiles.remove((Object)tile);
            }
            try {
                Iterator<TileEntityAreaMarker> it = this.tiles.iterator();
                while (it.hasNext()) {
                    TileEntityAreaMarker source = it.next();
                    if (source == null || source.func_145837_r()) {
                        it.remove();
                        continue;
                    }
                    if (source.func_145831_w().func_147438_o(source.field_145851_c, source.field_145848_d, source.field_145849_e) == source) continue;
                    it.remove();
                }
            }
            catch (Throwable e) {
                Log.instance().warning(e, "Exception occured removing existing power source entries");
            }
        }

        public boolean isProtectionActive(EntityLivingBase entity, Rite rite) {
            for (TileEntityAreaMarker tile : this.tiles) {
                if (!tile.checkIsProtected(entity, rite)) continue;
                return true;
            }
            return false;
        }
    }

    public static class TileEntityAreaTeleportPullProtect
    extends TileEntityAreaMarker {
        @Override
        public boolean activateBlock(World world, int x, int y, int z, EntityPlayer player, int side) {
            return false;
        }

        @Override
        protected boolean isProtected(EntityLivingBase entity, boolean killer, Rite rite) {
            return !killer && Config.instance().allowDecurseTeleport && (rite == null || rite instanceof RiteTeleportEntity);
        }

        @Override
        protected boolean isNear(EntityLivingBase entity) {
            int RADIUS = Config.instance().decurseTeleportPullRadius;
            int RADIUS_SQ = RADIUS * RADIUS;
            boolean inRange = Coord.distanceSq(entity.field_70165_t, 1.0, entity.field_70161_v, this.field_145851_c, 1.0, this.field_145849_e) <= (double)RADIUS_SQ;
            return inRange && this.field_145850_b.field_73011_w.field_76574_g == entity.field_71093_bK;
        }

        @Override
        protected Block getExpectedBlockType() {
            return Witchery.Blocks.DECURSE_TELEPORT;
        }
    }

    public static class TileEntityAreaCurseProtect
    extends TileEntityAreaMarker {
        @Override
        public boolean activateBlock(World world, int x, int y, int z, EntityPlayer player, int side) {
            return false;
        }

        @Override
        protected boolean isProtected(EntityLivingBase entity, boolean killer, Rite rite) {
            return !killer && Config.instance().allowDecurseDirected && (rite == null || rite instanceof RiteCurseCreature);
        }

        @Override
        protected boolean isNear(EntityLivingBase entity) {
            int RADIUS = Config.instance().decurseDirectedRadius;
            int RADIUS_SQ = RADIUS * RADIUS;
            boolean inRange = Coord.distanceSq(entity.field_70165_t, 1.0, entity.field_70161_v, this.field_145851_c, 1.0, this.field_145849_e) <= (double)RADIUS_SQ;
            return inRange && this.field_145850_b.field_73011_w.field_76574_g == entity.field_71093_bK;
        }

        @Override
        protected Block getExpectedBlockType() {
            return Witchery.Blocks.DECURSE_DIRECTED;
        }
    }
}

