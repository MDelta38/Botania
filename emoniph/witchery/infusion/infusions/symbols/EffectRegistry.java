/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDoor
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityGolem
 *  net.minecraft.entity.monster.EntityWitch
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDoor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSourceIndirect
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.storage.WorldInfo
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 */
package com.emoniph.witchery.infusion.infusions.symbols;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBrazier;
import com.emoniph.witchery.blocks.BlockWickerBundle;
import com.emoniph.witchery.blocks.BlockWitchDoor;
import com.emoniph.witchery.brewing.EntityBrew;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.brewing.potions.PotionEnslaved;
import com.emoniph.witchery.brewing.potions.PotionIllFitting;
import com.emoniph.witchery.dimension.WorldProviderTorment;
import com.emoniph.witchery.entity.EntityDarkMark;
import com.emoniph.witchery.entity.EntityEnt;
import com.emoniph.witchery.entity.EntitySpellEffect;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.InfusionLight;
import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
import com.emoniph.witchery.infusion.infusions.symbols.StrokeSet;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffectProjectile;
import com.emoniph.witchery.item.ItemChalk;
import com.emoniph.witchery.item.ItemLeonardsUrn;
import com.emoniph.witchery.network.PacketPushTarget;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.DemonicDamageSource;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.InvUtil;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class EffectRegistry {
    private static final EffectRegistry INSTANCE = new EffectRegistry();
    private Hashtable<ByteBuffer, SymbolEffect> effects = new Hashtable();
    private Hashtable<ByteBuffer, Integer> enhanced = new Hashtable();
    private Hashtable<Integer, SymbolEffect> effectID = new Hashtable();
    private ArrayList<SymbolEffect> allEffects = new ArrayList();
    public static final SymbolEffect Accio = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(1, "witchery.pott.accio"){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
            if (caster != null && mop != null) {
                double R = spell.getEffectLevel() == 1 ? 0.8 : (spell.getEffectLevel() == 2 ? 3.0 : 9.0);
                double R_SQ = R * R;
                AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(spell.field_70165_t - R), (double)(spell.field_70163_u - R), (double)(spell.field_70161_v - R), (double)(spell.field_70165_t + R), (double)(spell.field_70163_u + R), (double)(spell.field_70161_v + R));
                List entities = world.func_72872_a(EntityItem.class, bb);
                for (Object obj : entities) {
                    EntityItem item = (EntityItem)obj;
                    if (!(item.func_70068_e((Entity)spell) <= R_SQ)) continue;
                    item.func_70107_b(caster.field_70165_t, caster.field_70163_u + 1.0, caster.field_70161_v);
                }
            }
        }
    }.setColor(5322534).setSize(1.0f), new StrokeSet(1, 3, 0, 2, 2, 1), new StrokeSet(1, 3, 0, 2, 2, 2, 1), new StrokeSet(2, 3, 0, 0, 2, 2, 1, 1), new StrokeSet(2, 3, 0, 0, 2, 2, 2, 1, 1), new StrokeSet(3, 3, 0, 0, 0, 2, 2, 2, 1, 1, 1), new StrokeSet(3, 3, 0, 0, 0, 2, 2, 2, 2, 1, 1, 1));
    public static final SymbolEffect Aguamenti = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(2, "witchery.pott.aguamenti"){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
            if (spell.getEffectLevel() == 1 && !world.field_73011_w.field_76575_d || world.field_73011_w.field_76575_d && spell.getEffectLevel() == 3) {
                if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
                    this.setBlock(caster, world, MathHelper.func_76128_c((double)mop.field_72308_g.field_70165_t), MathHelper.func_76128_c((double)mop.field_72308_g.field_70163_u), MathHelper.func_76128_c((double)mop.field_72308_g.field_70161_v), (Block)Blocks.field_150358_i);
                } else if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                    Block hitBlock = world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                    if (hitBlock == Witchery.Blocks.CAULDRON) {
                        if (Witchery.Blocks.CAULDRON.tryFillWith(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, new FluidStack(FluidRegistry.WATER, 3000))) {
                            // empty if block
                        }
                    } else if (hitBlock == Witchery.Blocks.KETTLE) {
                        if (Witchery.Blocks.KETTLE.tryFillWith(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, new FluidStack(FluidRegistry.WATER, 1000))) {
                            // empty if block
                        }
                    } else {
                        int dy;
                        int dx;
                        int n = mop.field_72310_e == 5 ? 1 : (dx = mop.field_72310_e == 4 ? -1 : 0);
                        int n2 = mop.field_72310_e == 0 ? -1 : (dy = mop.field_72310_e == 1 ? 1 : 0);
                        int dz = mop.field_72310_e == 3 ? 1 : (mop.field_72310_e == 2 ? -1 : 0);
                        this.setBlock(caster, world, mop.field_72311_b + dx, mop.field_72312_c + dy + (!world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d).func_149688_o().func_76220_a() && mop.field_72310_e == 1 ? -1 : 0), mop.field_72309_d + dz, (Block)Blocks.field_150358_i);
                    }
                }
            } else if (!world.field_73011_w.field_76575_d) {
                if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
                    int x = MathHelper.func_76128_c((double)mop.field_72308_g.field_70165_t);
                    int y = MathHelper.func_76128_c((double)mop.field_72308_g.field_70163_u);
                    int z = MathHelper.func_76128_c((double)mop.field_72308_g.field_70161_v);
                    this.setBlock(caster, world, x, y, z, (Block)Blocks.field_150358_i);
                    this.setIfAir(caster, world, x, y + 1, z, (Block)Blocks.field_150358_i);
                    this.setIfAir(caster, world, x + 1, y, z, (Block)Blocks.field_150358_i);
                    this.setIfAir(caster, world, x - 1, y, z, (Block)Blocks.field_150358_i);
                    this.setIfAir(caster, world, x, y, z + 1, (Block)Blocks.field_150358_i);
                    this.setIfAir(caster, world, x, y, z - 1, (Block)Blocks.field_150358_i);
                    this.setIfAir(caster, world, x, y - 1, z, (Block)Blocks.field_150358_i);
                } else {
                    int dy;
                    int dx;
                    int n = mop.field_72310_e == 5 ? 1 : (dx = mop.field_72310_e == 4 ? -1 : 0);
                    int n3 = mop.field_72310_e == 0 ? -1 : (dy = mop.field_72310_e == 1 ? 1 : 0);
                    int dz = mop.field_72310_e == 3 ? 1 : (mop.field_72310_e == 2 ? -1 : 0);
                    int x = mop.field_72311_b + dx;
                    int y = mop.field_72312_c + dy + (!world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d).func_149688_o().func_76220_a() && mop.field_72310_e == 1 ? -1 : 0);
                    int z = mop.field_72309_d + dz;
                    this.setBlock(caster, world, x, y, z, (Block)Blocks.field_150358_i);
                    this.setIfAir(caster, world, x, y + 1, z, (Block)Blocks.field_150358_i);
                    this.setIfAir(caster, world, x + 1, y, z, (Block)Blocks.field_150358_i);
                    this.setIfAir(caster, world, x - 1, y, z, (Block)Blocks.field_150358_i);
                    this.setIfAir(caster, world, x, y, z + 1, (Block)Blocks.field_150358_i);
                    this.setIfAir(caster, world, x, y, z - 1, (Block)Blocks.field_150358_i);
                    this.setIfAir(caster, world, x, y - 1, z, (Block)Blocks.field_150358_i);
                }
            }
        }

        private void setBlock(EntityLivingBase caster, World world, int x, int y, int z, Block block) {
            if (BlockProtect.checkModsForBreakOK(world, x, y, z, caster)) {
                world.func_147449_b(x, y, z, block);
            }
        }

        private void setIfAir(EntityLivingBase caster, World world, int x, int y, int z, Block block) {
            if (world.func_147437_c(x, y, z)) {
                this.setBlock(caster, world, x, y, z, block);
            }
        }
    }.setColor(0x11F3FF).setSize(2.0f), new StrokeSet(1, 0, 0, 2, 2, 1), new StrokeSet(1, 0, 0, 2, 2, 2, 1), new StrokeSet(2, 0, 0, 0, 2, 2, 1, 1), new StrokeSet(2, 0, 0, 0, 2, 2, 2, 1, 1), new StrokeSet(3, 0, 0, 0, 0, 2, 2, 1, 1, 1), new StrokeSet(3, 0, 0, 0, 0, 2, 2, 2, 1, 1, 1));
    public static final SymbolEffect Alohomora = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(3, "witchery.pott.alohomora"){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
            if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                Block blockID = world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                if (blockID == Witchery.Blocks.DOOR_ALDER || blockID == Witchery.Blocks.DOOR_ROWAN) {
                    ((BlockWitchDoor)blockID).onBlockActivatedNormally(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, null, 1, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                } else if (blockID instanceof BlockDoor) {
                    ((BlockDoor)blockID).func_150014_a(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, !((BlockDoor)blockID).func_150015_f((IBlockAccess)world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d));
                }
            }
        }
    }.setColor(5322534).setSize(0.5f), new StrokeSet(2, 0, 2, 2, 1), new StrokeSet(2, 0, 2, 2, 2, 1), new StrokeSet(2, 0, 0, 2, 2, 1, 1), new StrokeSet(2, 0, 0, 2, 2, 2, 1, 1));
    public static final SymbolEffect AvadaKedavra = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(4, "witchery.pott.avadakedavra", 101, true, false, null, 0, false){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
            if (mop != null && caster != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY && mop.field_72308_g instanceof EntityLivingBase) {
                if (mop.field_72308_g instanceof EntityPlayer) {
                    if (world.field_72995_K || !(caster instanceof EntityPlayer) || MinecraftServer.func_71276_C().func_71219_W()) {
                        EntityPlayer hitPlayer = (EntityPlayer)mop.field_72308_g;
                        EntityUtil.instantDeath((EntityLivingBase)hitPlayer, caster);
                    }
                } else if (mop.field_72308_g instanceof EntityLiving) {
                    EntityLiving hitCreature = (EntityLiving)mop.field_72308_g;
                    if (caster instanceof EntityPlayer && ((EntityPlayer)caster).field_71075_bZ.field_75098_d) {
                        EntityUtil.instantDeath((EntityLivingBase)hitCreature, caster);
                    } else if ((PotionEnslaved.canCreatureBeEnslaved((EntityLivingBase)hitCreature) || hitCreature instanceof EntityWitch || hitCreature instanceof EntityEnt || hitCreature instanceof EntityGolem) && hitCreature.func_110138_aP() <= 200.0f) {
                        hitCreature.func_70097_a(DamageSource.func_76354_b((Entity)effectEntity, (Entity)caster), 200.0f);
                    } else {
                        hitCreature.func_70097_a(DamageSource.func_76354_b((Entity)effectEntity, (Entity)caster), 25.0f);
                    }
                }
            }
        }
    }.setColor(65280).setSize(2.0f), new StrokeSet(1, 1, 2, 2, 0, 0, 3, 3, 3, 3, 1, 1, 2));
    public static final SymbolEffect CaveInimicum = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(5, "witchery.pott.caveinimicum"){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
            if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                EffectRegistry.applyBlockEffect(world, caster, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, effectEntity.getEffectLevel(), new IBlockEffect(){

                    @Override
                    public void doAction(World world, EntityLivingBase actor, int x, int y, int z, Block block, int meta) {
                        Block newBlockID = Blocks.field_150350_a;
                        if (block == Blocks.field_150346_d) {
                            newBlockID = Blocks.field_150348_b;
                        } else if (block == Blocks.field_150349_c) {
                            newBlockID = Blocks.field_150348_b;
                        } else if (block == Blocks.field_150391_bh) {
                            newBlockID = Blocks.field_150348_b;
                        } else if (block == Blocks.field_150347_e) {
                            newBlockID = Blocks.field_150348_b;
                        } else if (block == Blocks.field_150344_f) {
                            newBlockID = Blocks.field_150348_b;
                        } else if (block == Witchery.Blocks.PLANKS) {
                            newBlockID = Blocks.field_150348_b;
                        } else if (block == Blocks.field_150417_aV) {
                            newBlockID = Blocks.field_150336_V;
                        } else if (block == Blocks.field_150354_m) {
                            newBlockID = Blocks.field_150322_A;
                        } else if (block == Blocks.field_150435_aG) {
                            newBlockID = Blocks.field_150405_ch;
                        } else if (block == Blocks.field_150466_ao) {
                            int i1 = ((BlockDoor)block).func_150012_g((IBlockAccess)world, x, y, z);
                            if ((i1 & 8) != 0) {
                                --y;
                            }
                            world.func_147468_f(x, y, z);
                            world.func_147468_f(x, y + 1, z);
                            int pp1 = MathHelper.func_76128_c((double)((double)((actor.field_70177_z + 180.0f) * 4.0f / 360.0f) - 0.5)) & 3;
                            ItemDoor.func_150924_a((World)world, (int)x, (int)y, (int)z, (int)pp1, (Block)Blocks.field_150454_av);
                        }
                        if (newBlockID != Blocks.field_150350_a) {
                            world.func_147449_b(x, y, z, newBlockID);
                        }
                    }
                });
            }
        }
    }.setColor(0x303030).setSize(3.0f), new StrokeSet(1, 0, 3, 0, 0, 2), new StrokeSet(1, 0, 3, 0, 0, 0, 2), new StrokeSet(1, 0, 3, 3, 0, 0, 2, 2), new StrokeSet(2, 0, 3, 3, 0, 0, 0, 2, 2), new StrokeSet(3, 0, 3, 3, 3, 0, 0, 0, 0, 2, 2, 2));
    public static final SymbolEffect Colloportus = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(6, "witchery.pott.colloportus"){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
            int y;
            Block blockID;
            if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && caster != null && (blockID = world.func_147439_a(mop.field_72311_b, y = mop.field_72312_c, mop.field_72309_d)) instanceof BlockDoor) {
                int i1 = ((BlockDoor)blockID).func_150012_g((IBlockAccess)world, mop.field_72311_b, y, mop.field_72309_d);
                if ((i1 & 8) != 0) {
                    --y;
                }
                world.func_147468_f(mop.field_72311_b, y, mop.field_72309_d);
                world.func_147468_f(mop.field_72311_b, y + 1, mop.field_72309_d);
                int pp1 = MathHelper.func_76128_c((double)((double)((caster.field_70177_z + 180.0f) * 4.0f / 360.0f) - 0.5)) & 3;
                ItemDoor.func_150924_a((World)world, (int)mop.field_72311_b, (int)y, (int)mop.field_72309_d, (int)pp1, (Block)Witchery.Blocks.DOOR_ROWAN);
            }
        }
    }.setColor(5322534).setSize(1.0f), new StrokeSet(3, 3, 1, 1, 2), new StrokeSet(3, 3, 1, 1, 1, 2), new StrokeSet(3, 3, 3, 1, 1, 2, 2), new StrokeSet(3, 3, 3, 1, 1, 2, 1, 2));
    public static final SymbolEffect Confundus = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(8, "witchery.pott.confundus"){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
            double radius = spell.getEffectLevel() == 1 ? 0.0 : (spell.getEffectLevel() == 2 ? 2.0 : 4.0);
            EffectRegistry.applyEntityEffect(world, caster, mop, spell.field_70165_t, spell.field_70163_u, spell.field_70161_v, radius, EntityLivingBase.class, new IEntityEffect<EntityLivingBase>(){

                @Override
                public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
                    if (target instanceof EntityLivingBase && !target.func_70644_a(Potion.field_76431_k)) {
                        target.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 600));
                    }
                }
            });
        }
    }.setColor(16771328).setSize(1.5f), new StrokeSet(1, 3, 3, 0, 0, 2), new StrokeSet(1, 3, 3, 3, 0, 0, 2, 2), new StrokeSet(2, 3, 3, 3, 0, 0, 0, 2, 2), new StrokeSet(3, 3, 3, 3, 3, 0, 0, 0, 0, 2, 2, 2));
    public static final SymbolEffect Crucio = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(9, "witchery.pott.crucio", 5, true, false, null, 0){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
            if (mop != null && caster != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY && mop.field_72308_g instanceof EntityLivingBase) {
                if (mop.field_72308_g instanceof EntityPlayer) {
                    if (world.field_72995_K || !(caster instanceof EntityPlayer) || MinecraftServer.func_71276_C().func_71219_W()) {
                        EntityPlayer hitPlayer = (EntityPlayer)mop.field_72308_g;
                        hitPlayer.func_70097_a(DamageSource.func_76354_b((Entity)spell, (Entity)caster), (float)(4 + 4 * (spell.getEffectLevel() - 1)));
                    }
                } else if (mop.field_72308_g instanceof EntityLiving) {
                    EntityLiving hitCreature = (EntityLiving)mop.field_72308_g;
                    hitCreature.func_70097_a(DamageSource.func_76354_b((Entity)spell, (Entity)caster), 4.0f);
                }
            }
        }
    }.setColor(0x6600FF).setSize(2.0f), new StrokeSet(1, 1, 3, 1, 1, 2), new StrokeSet(1, 1, 3, 3, 1, 1, 2, 2), new StrokeSet(2, 1, 3, 1, 1, 1, 2), new StrokeSet(2, 1, 3, 3, 1, 1, 1, 2, 2), new StrokeSet(3, 1, 3, 3, 3, 1, 1, 1, 1, 2, 2, 2));
    public static final SymbolEffect Defodio = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(10, "witchery.pott.defodio", 3, false, false, null, 0){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
            if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                EffectRegistry.applyBlockEffect(world, caster, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, effectEntity.getEffectLevel(), new IBlockEffect(){

                    @Override
                    public void doAction(World world, EntityLivingBase actor, int x, int y, int z, Block block, int meta) {
                        Material material = block.func_149688_o();
                        if (material == Material.field_151571_B || material == Material.field_151596_z || material == Material.field_151578_c || material == Material.field_151577_b || material == Material.field_151588_w || material == Material.field_151576_e || material == Material.field_151595_p) {
                            world.func_147468_f(x, y, z);
                            Item itemBlock = null;
                            int itemDamageValue = -1;
                            try {
                                itemBlock = block.func_149650_a(meta, world.field_73012_v, 0);
                                itemDamageValue = block.func_149692_a(meta);
                                int quantity = block.quantityDropped(meta, 0, world.field_73012_v);
                                if (itemBlock != null && itemDamageValue >= 0 && quantity > 0) {
                                    world.func_72838_d((Entity)new EntityItem(world, 0.5 + (double)x, 0.5 + (double)y, 0.5 + (double)z, new ItemStack(itemBlock, quantity, itemDamageValue)));
                                }
                            }
                            catch (Throwable ex) {
                                Log.instance().warning(ex, "Exception occured while spawning block as part of Defodio effect: new (" + itemBlock + ", " + itemDamageValue + ") old (" + block + ", " + meta + ")");
                            }
                        }
                    }
                });
            }
        }
    }.setColor(4008220).setSize(2.5f), new StrokeSet(1, 0, 0, 3, 1), new StrokeSet(1, 0, 0, 0, 3, 1, 1), new StrokeSet(1, 0, 0, 3, 3, 1, 2), new StrokeSet(2, 0, 0, 0, 3, 3, 1, 1, 2), new StrokeSet(2, 0, 0, 0, 0, 3, 3, 1, 1, 1, 2), new StrokeSet(2, 0, 0, 0, 3, 3, 3, 1, 1, 2, 2), new StrokeSet(3, 0, 0, 0, 0, 3, 3, 3, 1, 1, 1, 2, 2));
    public static final SymbolEffect Ennervate = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(12, "witchery.pott.ennervate", 1, false, true, null, 0){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
            double radius = spell.getEffectLevel() == 1 ? 0.0 : (spell.getEffectLevel() == 2 ? 2.0 : 4.0);
            EffectRegistry.applyEntityEffect(world, caster, mop, spell.field_70165_t, spell.field_70163_u, spell.field_70161_v, radius, EntityLivingBase.class, new IEntityEffect<EntityLivingBase>(){

                @Override
                public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
                    if (target.func_70644_a(Potion.field_76421_d)) {
                        target.func_82170_o(Potion.field_76421_d.field_76415_H);
                    }
                    if (target.func_70644_a(Potion.field_76419_f)) {
                        target.func_82170_o(Potion.field_76419_f.field_76415_H);
                    }
                    if (target.func_70644_a(Potion.field_76431_k)) {
                        target.func_82170_o(Potion.field_76431_k.field_76415_H);
                    }
                }
            });
        }
    }.setColor(16713595).setSize(1.5f), new StrokeSet(1, 0, 3, 0, 2, 3, 0, 2), new StrokeSet(2, 0, 3, 3, 0, 2, 2, 3, 3, 0, 2, 2), new StrokeSet(3, 0, 3, 3, 3, 0, 2, 2, 2, 3, 3, 3, 0, 2, 2, 2));
    public static final SymbolEffect Episkey = EffectRegistry.instance().addEffect(new SymbolEffect(13, "witchery.pott.episkey", 1, false, false, null, 0){

        @Override
        public void perform(World world, EntityPlayer player, int effectLevel) {
            double radius = effectLevel == 1 ? 0.0 : (effectLevel == 2 ? 2.0 : 4.0);
            MovingObjectPosition mop = new MovingObjectPosition((Entity)player);
            EffectRegistry.applyEntityEffect(world, (EntityLivingBase)player, mop, player.field_70165_t, player.field_70163_u, player.field_70161_v, radius, EntityLivingBase.class, new IEntityEffect<EntityLivingBase>(){

                @Override
                public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
                    int currentFood;
                    boolean hasFood = target instanceof EntityPlayer;
                    int n = currentFood = hasFood ? ((EntityPlayer)target).func_71024_bL().func_75116_a() : 5;
                    if (currentFood > 1 && target.func_110143_aJ() < target.func_110138_aP()) {
                        target.func_70691_i((float)Math.min(5, currentFood));
                        if (hasFood) {
                            ((EntityPlayer)target).func_71024_bL().func_75122_a(-Math.min(5, currentFood), 0.0f);
                        }
                        if (!target.func_70644_a(Potion.field_76431_k)) {
                            target.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, TimeUtil.secsToTicks(4)));
                        }
                        ParticleEffect.SPLASH.send(SoundEffect.MOB_SLIME_SMALL, (Entity)target, 1.0, 1.0, 16);
                    }
                }
            });
        }
    }, new StrokeSet(1, 2, 0, 3, 1, 1, 2), new StrokeSet(2, 2, 0, 0, 3, 1, 1, 1, 1, 2), new StrokeSet(2, 2, 2, 0, 3, 3, 1, 1, 2, 2), new StrokeSet(3, 2, 2, 0, 0, 3, 3, 1, 1, 1, 1, 2, 2));
    public static final SymbolEffect Expelliarmus = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(15, "witchery.pott.expelliarmus"){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
            double radius = spell.getEffectLevel() == 1 ? 0.0 : (spell.getEffectLevel() == 2 ? 3.0 : 5.0);
            EffectRegistry.applyEntityEffect(world, caster, mop, spell.field_70165_t, spell.field_70163_u, spell.field_70161_v, radius, EntityLivingBase.class, new IEntityEffect<EntityLivingBase>(){

                @Override
                public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
                    if (actor != target) {
                        this.disarm(target);
                    }
                }
            });
        }

        private void disarm(EntityLivingBase target) {
            ItemStack heldItem;
            if (target instanceof EntityPlayer) {
                int heldItemIndex;
                EntityPlayer playerVictim = (EntityPlayer)target;
                if ((playerVictim.field_71070_bA == null || playerVictim.field_71070_bA.field_75152_c == 0) && playerVictim.field_71071_by.field_70462_a[heldItemIndex = playerVictim.field_71071_by.field_70461_c] != null) {
                    playerVictim.func_71019_a(playerVictim.field_71071_by.field_70462_a[heldItemIndex], true);
                    playerVictim.field_71071_by.field_70462_a[heldItemIndex] = null;
                }
            } else if (!PotionIllFitting.isTargetBanned(target) && (heldItem = target.func_70694_bm()) != null) {
                if (target instanceof EntityPlayer) {
                    Infusion.dropEntityItemWithRandomChoice(target, heldItem, true);
                } else {
                    target.func_70099_a(heldItem, 0.5f);
                }
                target.func_70062_b(0, null);
            }
        }
    }.setColor(16747778).setSize(3.0f), new StrokeSet(1, 0, 0, 1), new StrokeSet(1, 0, 0, 0, 1, 1), new StrokeSet(2, 0, 0, 0, 0, 1, 1, 1), new StrokeSet(3, 0, 0, 0, 0, 0, 1, 1, 1, 1));
    public static final SymbolEffect Flagrate = EffectRegistry.instance().addEffect(new SymbolEffect(16, "witchery.pott.flagrate", 1, false, false, null, 0, false){

        @Override
        public void perform(World world, EntityPlayer player, int effectLevel) {
            MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 4.0);
            if (mop != null) {
                if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                    ItemChalk.drawGlyph(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, Witchery.Blocks.GLYPH_INFERNAL, player);
                } else {
                    SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
                }
            } else {
                SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
            }
        }
    }, new StrokeSet(2, 0, 2, 3, 0, 2));
    public static final SymbolEffect Flipendo = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(17, "witchery.pott.flipendo"){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
            final double radius = spell.getEffectLevel() == 1 ? 0.0 : (spell.getEffectLevel() == 2 ? 3.0 : 6.0);
            final double spellX = spell.field_70159_w;
            final double spellZ = spell.field_70179_y;
            EffectRegistry.applyEntityEffect(world, caster, mop, spell.field_70165_t, spell.field_70163_u, spell.field_70161_v, radius, EntityLivingBase.class, new IEntityEffect<EntityLivingBase>(){

                @Override
                public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
                    if (radius == 3.0 || target != actor) {
                        double ACCELERATION = 2.0;
                        if (target.func_70644_a(Potion.field_76421_d)) {
                            ACCELERATION += 0.5;
                        }
                        double motionX = spellX * ACCELERATION;
                        double motionY = 0.3;
                        double motionZ = spellZ * ACCELERATION;
                        if (target instanceof EntityPlayer) {
                            EntityPlayer targetPlayer = (EntityPlayer)target;
                            Witchery.packetPipeline.sendTo((IMessage)new PacketPushTarget(motionX, 0.3, motionZ), targetPlayer);
                        } else {
                            target.field_70159_w = motionX;
                            target.field_70181_x = 0.3;
                            target.field_70179_y = motionZ;
                        }
                    }
                }
            });
        }
    }.setColor(0xFFF999).setSize(3.0f), new StrokeSet(1, 2, 2, 3), new StrokeSet(1, 2, 2, 2, 3, 3), new StrokeSet(2, 2, 2, 2, 2, 3, 3, 3), new StrokeSet(3, 2, 2, 2, 2, 2, 3, 3, 3, 3));
    public static final SymbolEffect Impedimenta = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(19, "witchery.pott.impedimenta"){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
            double radius = spell.getEffectLevel() == 1 ? 0.0 : (spell.getEffectLevel() == 2 ? 3.0 : 6.0);
            double spellX = spell.field_70159_w;
            double spellZ = spell.field_70179_y;
            EffectRegistry.applyEntityEffect(world, caster, mop, spell.field_70165_t, spell.field_70163_u, spell.field_70161_v, radius, EntityLivingBase.class, new IEntityEffect<EntityLivingBase>(){

                @Override
                public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
                    if (target != actor && !target.func_70644_a(Potion.field_76421_d)) {
                        target.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 600, 1));
                    }
                }
            });
        }
    }.setColor(6191615).setSize(1.5f), new StrokeSet(1, 3, 3, 2), new StrokeSet(1, 3, 3, 3, 2, 2), new StrokeSet(2, 3, 3, 3, 3, 2, 2, 2), new StrokeSet(3, 3, 3, 3, 3, 3, 2, 2, 2, 2));
    public static final SymbolEffect Imperio = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(20, "witchery.pott.imperio", 10, true, false, null, 0){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
            EntityLivingBase entityLiving;
            if (mop != null && caster != null && caster instanceof EntityPlayer && mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY && mop.field_72308_g instanceof EntityLivingBase && PotionEnslaved.canCreatureBeEnslaved(entityLiving = (EntityLivingBase)mop.field_72308_g)) {
                EntityPlayer player = (EntityPlayer)caster;
                EntityLiving creature = (EntityLiving)entityLiving;
                NBTTagCompound nbt = entityLiving.getEntityData();
                if (PotionEnslaved.setEnslaverForMob(creature, player)) {
                    ParticleEffect.SPELL.send(SoundEffect.MOB_ZOMBIE_INFECT, (Entity)creature, 1.0, 2.0, 8);
                }
            }
        }
    }.setColor(10686463).setSize(1.5f), new StrokeSet(2, 1, 1, 1, 1));
    public static final SymbolEffect Incendio = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(21, "witchery.pott.incendio"){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
            double radius = spell.getEffectLevel() == 1 ? 0.0 : (spell.getEffectLevel() == 2 ? 3.0 : 6.0);
            final int level = spell.getEffectLevel();
            if (radius == 0.0) {
                if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
                    mop.field_72308_g.func_70015_d(1);
                    mop.field_72308_g.func_70097_a(new EntityDamageSourceIndirect("onFire", (Entity)spell, (Entity)caster).func_76361_j(), 0.1f);
                } else if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                    int dy;
                    int dx;
                    Block hitBlock = BlockUtil.getBlock(world, mop);
                    if (hitBlock == Witchery.Blocks.WICKER_BUNDLE && BlockWickerBundle.limitToValidMetadata(world.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) == 1) {
                        if (BlockWickerBundle.tryIgniteMan(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, caster != null ? caster.field_70177_z : 0.0f)) {
                            return;
                        }
                    } else if (hitBlock == Witchery.Blocks.BRAZIER) {
                        BlockBrazier.tryIgnite(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                        return;
                    }
                    int n = mop.field_72310_e == 5 ? 1 : (dx = mop.field_72310_e == 4 ? -1 : 0);
                    int n2 = mop.field_72310_e == 0 ? -1 : (dy = mop.field_72310_e == 1 ? 1 : 0);
                    int dz = mop.field_72310_e == 3 ? 1 : (mop.field_72310_e == 2 ? -1 : 0);
                    world.func_147449_b(mop.field_72311_b + dx, mop.field_72312_c + dy + (!world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d).func_149688_o().func_76220_a() && mop.field_72310_e == 1 ? -1 : 0), mop.field_72309_d + dz, (Block)Blocks.field_150480_ab);
                }
            } else {
                EffectRegistry.applyEntityEffect(world, caster, mop, spell.field_70165_t, spell.field_70163_u, spell.field_70161_v, radius, EntityLivingBase.class, new IEntityEffect<EntityLivingBase>(){

                    @Override
                    public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
                        if (target != actor) {
                            target.func_70015_d(level);
                        }
                    }
                });
                if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                    final int side = mop.field_72310_e;
                    EffectRegistry.applyBlockEffect(world, caster, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, level, new IBlockEffect(){

                        @Override
                        public void doAction(World world, EntityLivingBase actor, int x, int y, int z, Block block, int meta) {
                            if (side == 1) {
                                int dy;
                                int dx;
                                int n = side == 5 ? 1 : (dx = side == 4 ? -1 : 0);
                                int n2 = side == 0 ? -1 : (dy = side == 1 ? 1 : 0);
                                int nX = x + dx;
                                int nY = y + dy;
                                int dz = side == 3 ? 1 : (side == 2 ? -1 : 0);
                                int nZ = z + dz;
                                if (world.func_147437_c(nX, nY, nZ)) {
                                    world.func_147449_b(nX, nY, nZ, (Block)Blocks.field_150480_ab);
                                }
                            }
                        }
                    });
                }
            }
        }
    }.setColor(16724023).setSize(2.0f), new StrokeSet(1, 3, 0, 0, 1, 1), new StrokeSet(2, 3, 0, 0, 0, 1, 1, 1), new StrokeSet(3, 3, 0, 0, 0, 0, 1, 1, 1, 1));
    public static final SymbolEffect Lumos = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(22, "witchery.pott.lumos"){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
            if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                int dy;
                int dx;
                int n = mop.field_72310_e == 5 ? 1 : (dx = mop.field_72310_e == 4 ? -1 : 0);
                int n2 = mop.field_72310_e == 0 ? -1 : (dy = mop.field_72310_e == 1 ? 1 : 0);
                int x = mop.field_72311_b + 1 * dx;
                int y = mop.field_72312_c + 1 * dy;
                int dz = mop.field_72310_e == 3 ? 1 : (mop.field_72310_e == 2 ? -1 : 0);
                int z = mop.field_72309_d + 1 * dz;
                Material material = world.func_147439_a(x, y, z).func_149688_o();
                if (material == Material.field_151579_a || material == Material.field_151597_y) {
                    world.func_147449_b(x, y, z, Witchery.Blocks.GLOW_GLOBE);
                }
            }
        }
    }.setColor(0xFFFF3A).setSize(0.5f), new StrokeSet(1, 1, 1, 2));
    public static final SymbolEffect MeteolojinxRecanto = EffectRegistry.instance().addEffect(new SymbolEffect(23, "witchery.pott.meteolojinxrecanto", 100, false, false, null, 0){

        @Override
        public void perform(World world, EntityPlayer player, int effectLevel) {
            MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 4.0);
            if (world.func_72896_J()) {
                WorldServer worldserver = MinecraftServer.func_71276_C().field_71305_c[0];
                if (worldserver != null) {
                    WorldInfo worldinfo = worldserver.func_72912_H();
                    worldinfo.func_76084_b(false);
                    worldinfo.func_76069_a(false);
                }
            } else {
                SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
            }
        }
    }, new StrokeSet(0, 0, 0, 2, 2, 1, 0, 2, 2, 1, 1));
    public static final SymbolEffect Nox = EffectRegistry.instance().addEffect(new SymbolEffect(26, "witchery.pott.nox", 50, false, false, null, 0){

        @Override
        public void perform(World world, EntityPlayer player, int effectLevel) {
            int x0 = MathHelper.func_76128_c((double)player.field_70165_t);
            int y0 = MathHelper.func_76128_c((double)player.field_70163_u);
            int z0 = MathHelper.func_76128_c((double)player.field_70161_v);
            int radius = 10;
            for (int y = y0 - radius; y <= y0 + radius; ++y) {
                for (int x = x0 - radius; x <= x0 + radius; ++x) {
                    for (int z = z0 - radius; z <= z0 + radius; ++z) {
                        int blockMeta;
                        Block blockID = world.func_147439_a(x, y, z);
                        if (!((double)blockID.getLightValue((IBlockAccess)world, x, y, z) > 0.8) || !BlockProtect.canBreak(blockID, world) || !BlockProtect.checkModsForBreakOK(world, x, y, z, blockID, blockMeta = world.func_72805_g(x, y, z), (EntityLivingBase)player)) continue;
                        world.func_147468_f(x, y, z);
                        if (blockID.func_149745_a(world.field_73012_v) <= 0) continue;
                        blockID.func_149697_b(world, x, y, z, blockMeta, 0);
                    }
                }
            }
        }
    }, new StrokeSet(0, 0, 2, 1, 2, 0));
    public static final SymbolEffect Protego = EffectRegistry.instance().addEffect(new SymbolEffect(31, "witchery.pott.protego"){

        @Override
        public void perform(World world, EntityPlayer player, int effectLevel) {
            MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 4.0);
            if (mop != null) {
                if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                    InfusionLight.placeBarrierShield(world, player, mop);
                } else {
                    SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
                }
            } else {
                SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
            }
        }
    }, new StrokeSet(1, 1, 0), new StrokeSet(1, 1, 1, 0, 0), new StrokeSet(1, 1, 1, 1, 0, 0, 0));
    public static final SymbolEffect Stupefy = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(36, "witchery.pott.stupefy", 5, false, true, null, 0){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
            EntityLivingBase entityLiving;
            if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY && mop.field_72308_g instanceof EntityLivingBase && !(entityLiving = (EntityLivingBase)mop.field_72308_g).func_70644_a(Potion.field_76421_d)) {
                entityLiving.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 6000, 9));
            }
        }
    }.setColor(1279).setSize(1.5f), new StrokeSet(1, 2, 2, 0, 3, 0, 2), new StrokeSet(1, 2, 2, 2, 0, 3, 3, 0, 2, 2), new StrokeSet(2, 2, 2, 0, 0, 3, 0, 0, 2), new StrokeSet(2, 2, 2, 2, 0, 0, 3, 3, 0, 0, 2, 2));
    public static final SymbolEffect Ignianima = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(39, "witchery.pott.ignianima", 2, true, false, "ignianima", 0){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect e) {
            double R = 1.5;
            double R_SQ = 2.25;
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(e.field_70165_t - 1.5), (double)(e.field_70163_u - 1.5), (double)(e.field_70161_v - 1.5), (double)(e.field_70165_t + 1.5), (double)(e.field_70163_u + 1.5), (double)(e.field_70161_v + 1.5));
            List entities = world.func_72872_a(EntityLivingBase.class, bounds);
            for (Object hit : entities) {
                float scale;
                EntityLivingBase hitEntity = (EntityLivingBase)hit;
                if (!(e.func_70068_e((Entity)hitEntity) <= 2.25)) continue;
                float damage = 4.0f;
                float f = scale = hitEntity instanceof EntityPlayer ? hitEntity.func_110138_aP() / 20.0f : 1.0f;
                if (caster != null) {
                    float health = 20.0f * (caster.func_110143_aJ() / caster.func_110138_aP());
                    damage = health > 19.0f ? 2.0f : (health > 15.0f ? 3.0f : (health > 10.0f ? 5.0f : 6.0f + (12.0f - health) / 2.0f));
                }
                float scaledDamage = damage * scale;
                hitEntity.func_70097_a((DamageSource)new DemonicDamageSource((Entity)caster), scaledDamage);
                ParticleEffect.FLAME.send(SoundEffect.FIRE_IGNITE, (Entity)hitEntity, 1.0, 2.0, 16);
            }
        }
    }.setColor(16770912).setSize(3.0f), new StrokeSet(3, 3, 0, 1, 1), new StrokeSet(3, 3, 0, 0, 1, 1, 1, 1), new StrokeSet(3, 3, 3, 0, 1, 1), new StrokeSet(3, 3, 3, 0, 0, 1, 1, 1, 1), new StrokeSet(3, 3, 3, 3, 0, 1, 1), new StrokeSet(3, 3, 3, 3, 0, 0, 1, 1, 1, 1));
    public static final SymbolEffect CarnosaDiem = EffectRegistry.instance().addEffect(new SymbolEffect(40, "witchery.pott.carnosadiem", 1, true, false, "carnosadiem", 0){

        @Override
        public void perform(World world, EntityPlayer player, int effectLevel) {
            float damage = player.func_110138_aP() * 0.1f;
            player.func_70097_a((DamageSource)new DemonicDamageSource((Entity)player), damage);
            ParticleEffect.REDDUST.send(SoundEffect.MOB_ENDERDRAGON_GROWL, (Entity)player, 1.0, 2.0, 16);
            int currentPower = Infusion.getCurrentEnergy(player);
            int maxPower = Infusion.getMaxEnergy(player);
            Infusion.setCurrentEnergy(player, Math.min(currentPower + 10, maxPower));
            Witchery.modHooks.boostBloodPowers(player, damage);
        }
    }, new StrokeSet(2, 2, 0, 1, 1), new StrokeSet(2, 2, 0, 0, 1, 1, 1, 1), new StrokeSet(2, 2, 2, 0, 1, 1), new StrokeSet(2, 2, 2, 0, 0, 1, 1, 1, 1), new StrokeSet(2, 2, 2, 2, 0, 1, 1), new StrokeSet(2, 2, 2, 2, 0, 0, 1, 1, 1, 1));
    public static final SymbolEffect MORSMORDRE = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(41, "witchery.pott.morsmordre", 20, true, false, "morsmordre", 0){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
            if (!world.field_72995_K) {
                EntityDarkMark entity = new EntityDarkMark(world);
                entity.func_70012_b(effectEntity.field_70165_t, effectEntity.field_70163_u, effectEntity.field_70161_v, 0.0f, 0.0f);
                entity.func_110163_bv();
                world.func_72838_d((Entity)entity);
            }
        }
    }.setColor(0).setSize(3.0f).setTimeToLive(8), new StrokeSet(0, 0, 3, 2, 2), new StrokeSet(0, 0, 3, 3, 2, 2, 2, 2), new StrokeSet(0, 0, 0, 3, 2, 2), new StrokeSet(0, 0, 0, 3, 3, 2, 2, 2, 2), new StrokeSet(0, 0, 0, 0, 3, 2, 2), new StrokeSet(0, 0, 0, 0, 3, 3, 2, 2, 2, 2));
    public static final SymbolEffect Tormentum = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(42, "witchery.pott.tormentum", 25, true, true, "tormentum", TimeUtil.minsToTicks(30)){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect e) {
            if (!world.field_72995_K && e.field_71093_bK != Config.instance().dimensionTormentID) {
                double R = 2.0;
                AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(e.field_70165_t - 2.0), (double)(e.field_70163_u - 2.0), (double)(e.field_70161_v - 2.0), (double)(e.field_70165_t + 2.0), (double)(e.field_70163_u + 2.0), (double)(e.field_70161_v + 2.0));
                List entities = world.func_72872_a(EntityLivingBase.class, bounds);
                boolean setCooldown = false;
                for (Object hitEntity : entities) {
                    if (hitEntity instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer)hitEntity;
                        WorldProviderTorment.setPlayerMustTorment(player, 1, -1);
                        setCooldown = true;
                        continue;
                    }
                    if (!(hitEntity instanceof EntityLiving) || hitEntity instanceof IBossDisplayData) continue;
                    EntityLiving hitLiving = (EntityLiving)hitEntity;
                    hitLiving.func_70106_y();
                    setCooldown = true;
                }
                if (setCooldown && caster != null && caster instanceof EntityPlayer) {
                    this.setOnCooldown((EntityPlayer)caster);
                }
            }
        }
    }.setColor(0x222222).setSize(4.0f), new StrokeSet(1, 1, 3, 2, 2), new StrokeSet(1, 1, 3, 3, 2, 2, 2, 2), new StrokeSet(1, 1, 1, 3, 2, 2), new StrokeSet(1, 1, 1, 3, 3, 2, 2, 2, 2), new StrokeSet(1, 1, 1, 1, 3, 2, 2), new StrokeSet(1, 1, 1, 1, 3, 3, 2, 2, 2, 2));
    public static final SymbolEffect LEONARD_1 = EffectRegistry.instance().addEffect(new SymbolEffect(43, "witchery.pott.leonard1", 5, false, false, null, 0){

        @Override
        public void perform(World world, EntityPlayer player, int level) {
            EffectRegistry.castLeonardSpell(world, player, 0);
        }

        @Override
        public int getChargeCost(World world, EntityPlayer player, int level) {
            return EffectRegistry.costOfLeonardSpell(world, player, 0);
        }
    }, new StrokeSet(2, 0, 3, 3, 1));
    public static final SymbolEffect LEONARD_2 = EffectRegistry.instance().addEffect(new SymbolEffect(44, "witchery.pott.leonard2", 5, false, false, null, 0){

        @Override
        public void perform(World world, EntityPlayer player, int level) {
            EffectRegistry.castLeonardSpell(world, player, 1);
        }

        @Override
        public int getChargeCost(World world, EntityPlayer player, int level) {
            return EffectRegistry.costOfLeonardSpell(world, player, 1);
        }
    }, new StrokeSet(3, 1, 2, 2, 0));
    public static final SymbolEffect LEONARD_3 = EffectRegistry.instance().addEffect(new SymbolEffect(45, "witchery.pott.leonard3", 5, false, false, null, 0){

        @Override
        public void perform(World world, EntityPlayer player, int level) {
            EffectRegistry.castLeonardSpell(world, player, 2);
        }

        @Override
        public int getChargeCost(World world, EntityPlayer player, int level) {
            return EffectRegistry.costOfLeonardSpell(world, player, 2);
        }
    }, new StrokeSet(1, 2, 0, 0, 3));
    public static final SymbolEffect LEONARD_4 = EffectRegistry.instance().addEffect(new SymbolEffect(46, "witchery.pott.leonard4", 5, false, false, null, 0){

        @Override
        public void perform(World world, EntityPlayer player, int level) {
            EffectRegistry.castLeonardSpell(world, player, 3);
        }

        @Override
        public int getChargeCost(World world, EntityPlayer player, int level) {
            return EffectRegistry.costOfLeonardSpell(world, player, 3);
        }
    }, new StrokeSet(0, 3, 1, 1, 2));
    public static final SymbolEffect Attraho = EffectRegistry.instance().addEffect(new SymbolEffectProjectile(47, "witchery.pott.attraho"){

        @Override
        public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
            if (caster != null && mop != null) {
                double R = spell.getEffectLevel() == 1 ? 2.0 : (spell.getEffectLevel() == 2 ? 3.0 : 9.0);
                double R_SQ = R * R;
                AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(spell.field_70165_t - R), (double)(spell.field_70163_u - R), (double)(spell.field_70161_v - R), (double)(spell.field_70165_t + R), (double)(spell.field_70163_u + R), (double)(spell.field_70161_v + R));
                List entities = world.func_72872_a(EntityLivingBase.class, bb);
                for (EntityLivingBase entity : entities) {
                    if (!(entity.func_70068_e((Entity)spell) <= R_SQ)) continue;
                    EntityUtil.pullTowards(world, (Entity)entity, new EntityPosition((Entity)caster), 0.04, 0.1);
                }
            }
        }
    }.setColor(5322534).setSize(1.0f), new StrokeSet(1, 0, 0, 0, 2, 2, 1, 3));

    public static final EffectRegistry instance() {
        return INSTANCE;
    }

    private EffectRegistry() {
    }

    public SymbolEffect addEffect(SymbolEffect effect, StrokeSet ... strokeSets) {
        for (StrokeSet strokes : strokeSets) {
            strokes.addTo(this.effects, this.enhanced, effect);
        }
        this.effectID.put(effect.getEffectID(), effect);
        strokeSets[0].setDefaultFor(effect);
        this.allEffects.add(effect);
        return effect;
    }

    public boolean contains(byte[] strokes) {
        return this.getEffect(strokes) != null;
    }

    public SymbolEffect getEffect(byte[] strokes) {
        return this.effects.get(ByteBuffer.wrap(strokes));
    }

    public SymbolEffect getEffect(int effectID) {
        return this.effectID.get(effectID);
    }

    public int getLevel(byte[] strokes) {
        return this.enhanced.get(ByteBuffer.wrap(strokes));
    }

    public ArrayList<SymbolEffect> getEffects() {
        return this.allEffects;
    }

    private static <T extends Entity> void applyEntityEffect(World world, EntityLivingBase actor, MovingObjectPosition mop, double xMid, double yMid, double zMid, double radius, Class<T> clazz, IEntityEffect<T> effect) {
        if (radius == 0.0) {
            if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY && mop.field_72308_g != null && clazz.isAssignableFrom(mop.field_72308_g.getClass())) {
                effect.doAction(world, actor, xMid, yMid, zMid, mop.field_72308_g);
            }
        } else {
            double R = radius;
            double R_SQ = R * R;
            AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(xMid - R), (double)(yMid - R), (double)(zMid - R), (double)(xMid + R), (double)(yMid + R), (double)(zMid + R));
            List entities = world.func_72872_a(clazz, bb);
            for (Object obj : entities) {
                Entity entity = (Entity)obj;
                if (!(entity.func_70092_e(xMid, yMid, zMid) <= R_SQ)) continue;
                effect.doAction(world, actor, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity);
            }
        }
    }

    private static void applyBlockEffect(World world, EntityLivingBase actor, int midX, int midY, int midZ, int side, int radius, IBlockEffect effect) {
        if (radius == 1) {
            Block block = world.func_147439_a(midX, midY, midZ);
            int meta = world.func_72805_g(midX, midY, midZ);
            if (block != Blocks.field_150350_a && BlockProtect.canBreak(block, world) && BlockProtect.checkModsForBreakOK(world, midX, midY, midZ, block, meta, actor)) {
                effect.doAction(world, actor, midX, midY, midZ, block, meta);
            }
        } else {
            int r = Math.min(radius - 1, 3);
            int x = midX;
            int y = midY;
            int z = midZ;
            for (int k = -r; k <= r; ++k) {
                for (int j = -r; j <= r; ++j) {
                    switch (side) {
                        case 0: 
                        case 1: {
                            x = midX + k;
                            z = midZ + j;
                            break;
                        }
                        case 2: 
                        case 3: {
                            x = midX + k;
                            y = midY + j;
                            break;
                        }
                        case 4: 
                        case 5: {
                            y = midY + k;
                            z = midZ + j;
                        }
                    }
                    Block block = world.func_147439_a(x, y, z);
                    int meta = world.func_72805_g(x, y, z);
                    if (block == Blocks.field_150350_a || !BlockProtect.canBreak(block, world) || !BlockProtect.checkModsForBreakOK(world, x, y, z, block, meta, actor)) continue;
                    effect.doAction(world, actor, x, y, z, block, meta);
                }
            }
        }
    }

    private static int costOfLeonardSpell(World world, EntityPlayer player, int spellSlot) {
        ItemStack urnStack;
        int slot = InvUtil.getSlotContainingItem(player.field_71071_by, Witchery.Items.LEONARDS_URN);
        if (slot >= 0 && slot < player.field_71071_by.func_70302_i_() && (urnStack = player.field_71071_by.func_70301_a(slot)) != null) {
            ItemStack potion;
            ItemLeonardsUrn.InventoryLeonardsUrn inv = new ItemLeonardsUrn.InventoryLeonardsUrn(player, urnStack);
            if (urnStack.func_77960_j() >= spellSlot && (potion = inv.func_70301_a(spellSlot)) != null) {
                int baseLevel = WitcheryBrewRegistry.INSTANCE.getUsedCapacity(potion.func_77978_p());
                if (player.func_70644_a(Witchery.Potions.WORSHIP)) {
                    PotionEffect effect = player.func_70660_b(Witchery.Potions.WORSHIP);
                    if (effect.func_76458_c() < 1) {
                        baseLevel += (int)Math.ceil((double)baseLevel * 0.5);
                    }
                } else {
                    baseLevel *= 2;
                }
                return Math.max(baseLevel, 4);
            }
        }
        return 5;
    }

    private static void castLeonardSpell(World world, EntityPlayer player, int spellSlot) {
        ItemStack urnStack;
        int slot = InvUtil.getSlotContainingItem(player.field_71071_by, Witchery.Items.LEONARDS_URN);
        if (slot >= 0 && slot < player.field_71071_by.func_70302_i_() && (urnStack = player.field_71071_by.func_70301_a(slot)) != null) {
            ItemStack potion;
            ItemLeonardsUrn.InventoryLeonardsUrn inv = new ItemLeonardsUrn.InventoryLeonardsUrn(player, urnStack);
            if (urnStack.func_77960_j() >= spellSlot && (potion = inv.func_70301_a(spellSlot)) != null) {
                world.func_72889_a((EntityPlayer)null, 1008, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 0);
                EntityBrew entity = new EntityBrew(world, (EntityLivingBase)player, potion, true);
                world.func_72838_d((Entity)entity);
                return;
            }
        }
        SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
    }

    private static interface IBlockEffect {
        public void doAction(World var1, EntityLivingBase var2, int var3, int var4, int var5, Block var6, int var7);
    }

    private static interface IEntityEffect<T extends Entity> {
        public void doAction(World var1, EntityLivingBase var2, double var3, double var5, double var7, T var9);
    }
}

