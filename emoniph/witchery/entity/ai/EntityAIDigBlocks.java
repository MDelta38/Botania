/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.FakePlayer
 *  net.minecraftforge.common.util.FakePlayerFactory
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 *  net.minecraftforge.oredict.OreDictionary
 */
package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.util.BlockUtil;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.eventhandler.Event;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.oredict.OreDictionary;

public class EntityAIDigBlocks
extends EntityAIBase {
    protected final EntityGoblin entity;
    protected final double range;
    protected final double kobolditeChance;
    public static final GameProfile NORMAL_MINER_PROFILE = new GameProfile(UUID.fromString("AB06ACB0-0CDB-11E4-9191-0800200C9A66"), "[Minecraft]");
    public static final GameProfile KOBOLDITE_MINER_PROFILE = new GameProfile(UUID.fromString("24818AE0-0CDE-11E4-9191-0800200C9A66"), "[Minecraft]");
    MovingObjectPosition mop = null;
    int failedChecks = 0;
    private int waitTimer = 60;

    public EntityAIDigBlocks(EntityGoblin entity, double range, double kobolditeChance) {
        this.entity = entity;
        this.range = range;
        this.kobolditeChance = kobolditeChance;
        this.func_75248_a(7);
    }

    public boolean func_75250_a() {
        if (this.entity != null && !this.entity.isWorshipping() && this.entity.func_70694_bm() != null && this.entity.func_70694_bm().func_77973_b() instanceof ItemPickaxe && this.entity.func_110167_bD() && this.entity.field_70170_p.field_73012_v.nextInt(2) == 0) {
            MovingObjectPosition mop = EntityAIDigBlocks.raytraceBlocks(this.entity.field_70170_p, (EntityLiving)this.entity, true, this.failedChecks == 15 ? 1.0 : 4.0, this.failedChecks == 15);
            if (mop == null || mop.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK) {
                ++this.failedChecks;
                mop = null;
                return false;
            }
            Block block = BlockUtil.getBlock(this.entity.field_70170_p, mop);
            if (this.isMineable(block, this.entity.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) {
                this.failedChecks = 0;
                this.mop = mop;
                return true;
            }
            this.mop = null;
            ++this.failedChecks;
            return false;
        }
        return false;
    }

    private boolean isMineable(Block block, World world, int x, int y, int z) {
        if (block.func_149688_o() != Material.field_151576_e && block.func_149688_o() != Material.field_151595_p && block.func_149688_o() != Material.field_151577_b && block.func_149688_o() != Material.field_151597_y && block.func_149688_o() != Material.field_151578_c) {
            return false;
        }
        return !(block.func_149712_f(world, x, y, z) < 0.0f);
    }

    private static MovingObjectPosition raytraceBlocks(World world, EntityLiving player, boolean collisionFlag, double reachDistance, boolean down) {
        float rotationYaw;
        Vec3 playerPosition = Vec3.func_72443_a((double)player.field_70165_t, (double)(player.field_70163_u + (double)player.func_70047_e()), (double)player.field_70161_v);
        player.field_70177_z = rotationYaw = (float)world.field_73012_v.nextInt(360);
        float rotationPitch = down ? 90.0f : 0.0f;
        float f1 = MathHelper.func_76134_b((float)(-rotationYaw * ((float)Math.PI / 180) - (float)Math.PI));
        float f2 = MathHelper.func_76126_a((float)(-rotationYaw * ((float)Math.PI / 180) - (float)Math.PI));
        float f3 = -MathHelper.func_76134_b((float)(-rotationPitch * ((float)Math.PI / 180)));
        float f4 = MathHelper.func_76126_a((float)(-rotationPitch * ((float)Math.PI / 180)));
        Vec3 playerLook = Vec3.func_72443_a((double)(f2 * f3), (double)f4, (double)(f1 * f3));
        Vec3 playerViewOffset = Vec3.func_72443_a((double)(playerPosition.field_72450_a + playerLook.field_72450_a * reachDistance), (double)(playerPosition.field_72448_b + playerLook.field_72448_b * reachDistance), (double)(playerPosition.field_72449_c + playerLook.field_72449_c * reachDistance));
        return world.func_147447_a(playerPosition, playerViewOffset, collisionFlag, !collisionFlag, false);
    }

    public void func_75249_e() {
        double SPEED = 0.6;
        this.entity.func_70661_as().func_75492_a((double)this.mop.field_72311_b, (double)this.mop.field_72312_c, (double)this.mop.field_72309_d, 0.6);
    }

    public boolean func_75253_b() {
        return this.entity != null && !this.entity.isWorshipping() && this.entity.func_70694_bm() != null && this.entity.func_70694_bm().func_77973_b() instanceof ItemPickaxe && this.entity.func_110167_bD() && this.mop != null;
    }

    public void func_75251_c() {
        if (this.entity.isWorking()) {
            this.entity.setWorking(false);
        }
    }

    public void func_75246_d() {
        Block block;
        MovingObjectPosition mop;
        double DROP_RANGE = 2.5;
        double DROP_RANGE_SQ = 6.25;
        double dist = this.entity.func_70092_e((double)this.mop.field_72311_b + 0.5, (double)this.mop.field_72312_c + 0.5, (double)this.mop.field_72309_d + 0.5);
        boolean retry = true;
        if (dist <= 6.25) {
            if (!this.entity.isWorking()) {
                this.entity.setWorking(true);
            }
            if (--this.waitTimer == 0) {
                if (!EntityAIDigBlocks.tryHarvestBlock(this.entity.field_70170_p, this.mop.field_72311_b, this.mop.field_72312_c, this.mop.field_72309_d, (EntityLivingBase)this.entity)) {
                    retry = false;
                }
                this.mop = null;
                this.waitTimer = this.getNextHarvestDelay();
            }
        } else if (this.entity.func_70661_as().func_75500_f()) {
            this.mop = null;
            this.waitTimer = this.getNextHarvestDelay();
            if (this.entity.isWorking()) {
                this.entity.setWorking(false);
            }
        } else if (!this.entity.isWorking()) {
            this.entity.setWorking(true);
        }
        if (this.mop == null && retry && this.entity.field_70170_p.field_73012_v.nextInt(20) != 0 && (mop = EntityAIDigBlocks.raytraceBlocks(this.entity.field_70170_p, (EntityLiving)this.entity, true, 4.0, false)) != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && this.isMineable(block = BlockUtil.getBlock(this.entity.field_70170_p, mop), this.entity.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) {
            this.mop = mop;
            this.waitTimer = this.getNextHarvestDelay();
        }
    }

    private int getNextHarvestDelay() {
        return EntityAIDigBlocks.isHoldingKobolditePick((EntityLivingBase)this.entity) ? 4 : 60;
    }

    private static boolean isHoldingKobolditePick(EntityLivingBase entity) {
        return entity.func_70694_bm() != null && entity.func_70694_bm().func_77973_b() == Witchery.Items.KOBOLDITE_PICKAXE;
    }

    public static boolean tryHarvestBlock(World world, int par1, int par2, int par3, EntityLivingBase harvester) {
        boolean kobolditePick = EntityAIDigBlocks.isHoldingKobolditePick(harvester);
        FakePlayer minerPlayer = FakePlayerFactory.get((WorldServer)((WorldServer)world), (GameProfile)(kobolditePick ? KOBOLDITE_MINER_PROFILE : NORMAL_MINER_PROFILE));
        return EntityAIDigBlocks.tryHarvestBlock(world, par1, par2, par3, harvester, (EntityPlayer)minerPlayer);
    }

    public static boolean tryHarvestBlock(World world, int par1, int par2, int par3, EntityLivingBase harvester, EntityPlayer minerPlayer) {
        Block block = world.func_147439_a(par1, par2, par3);
        int blockMeta = world.func_72805_g(par1, par2, par3);
        BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(par1, par2, par3, world, block, blockMeta, minerPlayer);
        event.setCanceled(false);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            return false;
        }
        ItemStack stack = harvester.func_70694_bm();
        if (stack != null && stack.func_77973_b().onBlockStartBreak(stack, par1, par2, par3, minerPlayer)) {
            return false;
        }
        world.func_72926_e(2001, par1, par2, par3, Block.func_149682_b((Block)block) + (blockMeta << 12));
        boolean canHarvest = false;
        if (block.func_149712_f(world, par1, par2, par3) >= 0.0f) {
            int toolLevel;
            if (block.func_149688_o().func_76229_l()) {
                canHarvest = true;
            }
            String tool = block.getHarvestTool(blockMeta);
            int n = toolLevel = stack != null ? stack.func_77973_b().getHarvestLevel(stack, tool) : 0;
            if (toolLevel < 0) {
                canHarvest = true;
            }
            if (toolLevel >= block.getHarvestLevel(blockMeta)) {
                canHarvest = true;
            }
        }
        if (canHarvest && (canHarvest = EntityAIDigBlocks.removeBlock(world, par1, par2, par3, minerPlayer))) {
            block.func_149636_a(world, minerPlayer, par1, par2, par3, blockMeta);
        }
        return canHarvest;
    }

    private static boolean removeBlock(World world, int x, int y, int z, EntityPlayer player) {
        Block block = world.func_147439_a(x, y, z);
        int metadata = world.func_72805_g(x, y, z);
        block.func_149681_a(world, x, y, z, metadata, player);
        boolean flag = block.removedByPlayer(world, player, x, y, z, true);
        if (flag) {
            block.func_149664_b(world, x, y, z, metadata);
        }
        return flag;
    }

    public static void onHarvestDrops(EntityPlayer harvester, BlockEvent.HarvestDropsEvent event) {
        if (harvester != null && !harvester.field_70170_p.field_72995_K && !event.isCanceled() && (EntityAIDigBlocks.isEqual(harvester.func_146103_bH(), KOBOLDITE_MINER_PROFILE) || EntityAIDigBlocks.isEqual(harvester.func_146103_bH(), NORMAL_MINER_PROFILE))) {
            boolean hasKobolditePick = EntityAIDigBlocks.isEqual(harvester.func_146103_bH(), KOBOLDITE_MINER_PROFILE);
            ArrayList<ItemStack> newDrops = new ArrayList<ItemStack>();
            double kobolditeChance = hasKobolditePick ? 0.02 : 0.01;
            for (ItemStack drop : event.drops) {
                String oreName;
                int[] oreIDs = OreDictionary.getOreIDs((ItemStack)drop);
                boolean addOriginal = true;
                if (oreIDs.length > 0 && (oreName = OreDictionary.getOreName((int)oreIDs[0])) != null && oreName.startsWith("ore")) {
                    ItemStack smeltedDrop = FurnaceRecipes.func_77602_a().func_151395_a(drop);
                    if (smeltedDrop != null && hasKobolditePick && harvester.field_70170_p.field_73012_v.nextDouble() < 0.5) {
                        addOriginal = false;
                        newDrops.add(smeltedDrop.func_77946_l());
                        newDrops.add(smeltedDrop.func_77946_l());
                        if (harvester.field_70170_p.field_73012_v.nextDouble() < 0.25) {
                            newDrops.add(smeltedDrop.func_77946_l());
                        }
                    }
                    double d = kobolditeChance = hasKobolditePick ? 0.08 : 0.05;
                }
                if (!addOriginal) continue;
                newDrops.add(drop);
            }
            event.drops.clear();
            for (ItemStack newDrop : newDrops) {
                event.drops.add(newDrop);
            }
            if (kobolditeChance > 0.0 && harvester.field_70170_p.field_73012_v.nextDouble() < kobolditeChance) {
                event.drops.add(Witchery.Items.GENERIC.itemKobolditeDust.createStack());
            }
        }
    }

    private static boolean isEqual(GameProfile a, GameProfile b) {
        if (a == null || b == null || a.getId() == null || b.getId() == null) {
            return false;
        }
        return a.getId().equals(b.getId());
    }
}

