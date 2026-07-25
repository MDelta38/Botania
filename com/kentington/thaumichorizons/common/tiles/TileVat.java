/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Multimap
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.ModContainer
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.IMerchant
 *  net.minecraft.entity.INpc
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityGolem
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.entities.golems.EntityGolemBase
 *  thaumcraft.common.lib.network.PacketHandler
 *  thaumcraft.common.lib.network.fx.PacketFXBlockZap
 *  thaumcraft.common.lib.utils.InventoryUtils
 *  thaumcraft.common.tiles.TilePedestal
 */
package com.kentington.thaumichorizons.common.tiles;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.IEntityInfusedStats;
import com.kentington.thaumichorizons.common.lib.CreatureInfusionRecipe;
import com.kentington.thaumichorizons.common.lib.EntityInfusionProperties;
import com.kentington.thaumichorizons.common.lib.PacketFXEssentiaBubble;
import com.kentington.thaumichorizons.common.lib.PacketFXInfusionDone;
import com.kentington.thaumichorizons.common.lib.PacketHandler;
import com.kentington.thaumichorizons.common.lib.PacketInfusionFX;
import com.kentington.thaumichorizons.common.lib.SelfInfusionRecipe;
import com.kentington.thaumichorizons.common.tiles.TileVatConnector;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.EntityRegistry;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.crafting.IInfusionStabiliser;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TilePedestal;

public class TileVat
extends TileThaumcraft
implements IAspectContainer,
IEssentiaTransport,
ISidedInventory {
    public int mode = 0;
    AspectList myEssentia = new AspectList();
    AspectList essentiaDemanded = new AspectList();
    Aspect currentlySucking = null;
    public ItemStack sample = null;
    public ItemStack nutrients = null;
    public int progress;
    private EntityLivingBase entityContained = null;
    public final int CLONE_TIME = 800;
    public int[] selfInfusions = new int[12];
    public float selfInfusionHealth = 20.0f;
    private ArrayList<ChunkCoordinates> pedestals = new ArrayList();
    private int dangerCount = 0;
    public boolean checkSurroundings = true;
    public int symmetry = 0;
    public int instability = 0;
    private ArrayList<ItemStack> recipeIngredients = null;
    private Object recipeOutput = null;
    private String recipePlayer = null;
    private String recipeOutputLabel = "";
    private int recipeInstability = 0;
    private int recipeXP = 0;
    public int recipeType = 0;
    int itemCount = 0;
    public int count = 0;
    public int craftCount = 0;
    public float startUp;
    private int countDelay = 10;
    ArrayList<ItemStack> ingredients = new ArrayList();
    public HashMap<String, SourceFX> sourceFX = new HashMap();

    public boolean activate(EntityPlayer player, boolean direct) {
        ItemStack possibleJar = player.func_70694_bm();
        if (possibleJar != null && Block.func_149634_a((Item)possibleJar.func_77973_b()) == ThaumicHorizons.blockJar && possibleJar.func_77942_o() && !possibleJar.field_77990_d.func_74767_n("isSoul")) {
            if (this.mode == 0 && this.getEntityContained() == null && player.field_71071_by.func_70441_a(new ItemStack(ConfigBlocks.blockJar))) {
                this.setEntityContained((EntityLivingBase)EntityList.func_75615_a((NBTTagCompound)possibleJar.func_77978_p(), (World)this.field_145850_b));
                --possibleJar.field_77994_a;
                this.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                return true;
            }
        } else if (possibleJar != null && Block.func_149634_a((Item)possibleJar.func_77973_b()) == ConfigBlocks.blockJar && possibleJar.func_77960_j() == 0 && this.getEntityContained() != null && !(this.getEntityContained() instanceof EntityPlayer)) {
            if (this.mode == 0) {
                return this.jarCritter(possibleJar, player);
            }
        } else if (this.mode == 4 && possibleJar != null && Block.func_149634_a((Item)possibleJar.func_77973_b()) == ThaumicHorizons.blockJar && possibleJar.func_77942_o() && possibleJar.field_77990_d.func_74767_n("isSoul")) {
            if (this.selfInfusions[1] == 0 && player.field_71071_by.func_70441_a(new ItemStack(ConfigBlocks.blockJar))) {
                this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:whispers", 1.0f, this.field_145850_b.field_73012_v.nextFloat());
                Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, 0xFFFFFF, 20);
                Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d - 2, this.field_145849_e, 0xFFFFFF, 20);
                EntityVillager villager = new EntityVillager(this.field_145850_b);
                villager.func_70938_b(possibleJar.func_77978_p().func_74762_e("villagerType"));
                this.setEntityContained((EntityLivingBase)villager);
                this.mode = 0;
                this.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.selfInfusions = new int[12];
                --possibleJar.field_77994_a;
            }
        } else {
            if (this.mode == 0 && direct && this.getEntityContained() == null) {
                player.func_70634_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5);
                this.setEntityContained((EntityLivingBase)player);
                this.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                return true;
            }
            if (this.mode == 0 && this.getEntityContained() == player) {
                if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == ThaumicHorizons.blockSoulBeacon) {
                    player.func_70634_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 2.0, (double)this.field_145849_e + 0.5);
                } else {
                    player.func_70634_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 1.0, (double)this.field_145849_e + 0.5);
                }
                this.setEntityContained(null);
                this.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                return true;
            }
            player.openGui((Object)ThaumicHorizons.instance, 7, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return true;
        }
        return false;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.getEntityContained() != null && this.getEntityContained().func_70027_ad()) {
            this.getEntityContained().func_70066_B();
        }
        if (this.field_145850_b.field_72995_K) {
            if (this.mode == 2) {
                this.doEffects();
            } else if (this.mode != 2 && this.startUp > 0.0f) {
                if (this.startUp > 0.0f) {
                    this.startUp -= this.startUp / 10.0f;
                }
                if ((double)this.startUp < 0.001) {
                    this.startUp = 0.0f;
                }
            }
            if (this.mode == 1) {
                Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, 14184241, 1);
                Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d - 2, this.field_145849_e, 14184241, 1);
            }
            return;
        }
        if (this.mode == 0) {
            this.essentiaDemanded = new AspectList();
            if (this.getEntityContained() != null) {
                if (this.getEntityContained().func_110143_aJ() < this.getEntityContained().func_110138_aP()) {
                    if (this.getEntityContained().func_70668_bt() != EnumCreatureAttribute.UNDEAD) {
                        if (this.myEssentia.getAmount(Aspect.HEAL) > 0 && this.progress <= 0) {
                            this.getEntityContained().func_70691_i(8.0f);
                            this.myEssentia.remove(Aspect.HEAL, 1);
                            this.func_70296_d();
                            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                            this.progress += 40;
                        }
                        if (this.getEntityContained().func_110143_aJ() < this.getEntityContained().func_110138_aP() && this.essentiaDemanded.getAmount(Aspect.HEAL) < 1) {
                            this.essentiaDemanded.add(Aspect.HEAL, 1);
                        }
                        if (this.myEssentia.getAmount(Aspect.LIFE) > 0 && this.progress <= 0) {
                            this.getEntityContained().func_70691_i(4.0f);
                            this.myEssentia.remove(Aspect.LIFE, 1);
                            this.func_70296_d();
                            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                            this.progress += 50;
                        }
                        if (this.getEntityContained().func_110143_aJ() < this.getEntityContained().func_110138_aP() && this.essentiaDemanded.getAmount(Aspect.LIFE) < 1) {
                            this.essentiaDemanded.add(Aspect.LIFE, 1);
                        }
                    } else {
                        if (this.myEssentia.getAmount(Aspect.UNDEAD) > 0 && this.progress <= 0) {
                            this.getEntityContained().func_70691_i(8.0f);
                            this.myEssentia.remove(Aspect.UNDEAD, 1);
                            this.func_70296_d();
                            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                            this.progress += 40;
                        }
                        if (this.getEntityContained().func_110143_aJ() < this.getEntityContained().func_110138_aP() && this.essentiaDemanded.getAmount(Aspect.UNDEAD) < 1) {
                            this.essentiaDemanded.add(Aspect.UNDEAD, 1);
                        }
                        if (this.myEssentia.getAmount(Aspect.DEATH) > 0 && this.progress <= 0) {
                            this.getEntityContained().func_70691_i(4.0f);
                            this.myEssentia.remove(Aspect.DEATH, 1);
                            this.func_70296_d();
                            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                            this.progress += 50;
                        }
                        if (this.getEntityContained().func_110143_aJ() < this.getEntityContained().func_110138_aP() && this.essentiaDemanded.getAmount(Aspect.DEATH) < 1) {
                            this.essentiaDemanded.add(Aspect.DEATH, 1);
                        }
                    }
                }
                if (this.hasNegativeEffect(this.getEntityContained())) {
                    if (this.getEntityContained().func_70668_bt() != EnumCreatureAttribute.UNDEAD) {
                        if (this.myEssentia.getAmount(Aspect.HEAL) > 0 && this.progress <= 0) {
                            this.removeNegativeEffects(this.getEntityContained());
                            this.myEssentia.remove(Aspect.HEAL, 1);
                            this.func_70296_d();
                            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                            this.progress += 50;
                        } else if (this.essentiaDemanded.getAmount(Aspect.HEAL) < 1) {
                            this.essentiaDemanded.add(Aspect.HEAL, 1);
                        }
                    } else if (this.myEssentia.getAmount(Aspect.UNDEAD) > 0 && this.progress <= 0) {
                        this.removeNegativeEffects(this.getEntityContained());
                        this.myEssentia.remove(Aspect.UNDEAD, 1);
                        this.func_70296_d();
                        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                        this.progress += 50;
                    } else if (this.essentiaDemanded.getAmount(Aspect.UNDEAD) < 1) {
                        this.essentiaDemanded.add(Aspect.UNDEAD, 1);
                    }
                }
                if (this.getEntityContained() instanceof EntityPlayer && ((EntityPlayer)this.getEntityContained()).func_71024_bL().func_75121_c()) {
                    if (this.myEssentia.getAmount(Aspect.HUNGER) > 0 && this.progress <= 0) {
                        ((EntityPlayer)this.getEntityContained()).func_71024_bL().func_75122_a(4, 2.0f);
                        this.func_70296_d();
                        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                        this.progress += 50;
                    }
                    if (((EntityPlayer)this.getEntityContained()).func_71024_bL().func_75121_c() && this.essentiaDemanded.getAmount(Aspect.HUNGER) < 1) {
                        this.essentiaDemanded.add(Aspect.HUNGER, 1);
                    }
                }
            } else if (this.sample != null && this.sample.func_77973_b() == ThaumicHorizons.itemCorpseEffigy) {
                this.mode = 3;
                this.essentiaDemanded = new AspectList().add(Aspect.LIFE, 8).add(Aspect.HEAL, 8);
                this.progress = 80;
                this.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            } else if (this.sample != null && this.nutrients != null) {
                NBTTagCompound tlist;
                this.mode = 1;
                this.essentiaDemanded = new AspectList().add(Aspect.LIFE, 4);
                if (this.sample.func_77973_b() == ThaumicHorizons.itemSyringeBloodSample && this.sample.func_77942_o() && this.sample.field_77990_d.func_74775_l("critter") != null && this.sample.field_77990_d.func_74775_l("critter").func_74775_l("ForgeData") != null && (tlist = this.sample.field_77990_d.func_74775_l("critter").func_74775_l("CreatureInfusion").func_74775_l("InfusionCosts")) != null && tlist.func_74764_b("Aspects")) {
                    NBTTagList aspex = tlist.func_150295_c("Aspects", 10);
                    for (int j = 0; j < aspex.func_74745_c(); ++j) {
                        NBTTagCompound rs = aspex.func_150305_b(j);
                        if (!rs.func_74764_b("key")) continue;
                        this.essentiaDemanded.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
                    }
                }
                this.progress = 40;
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
            }
        } else if (this.mode == 1) {
            if (this.sample == null && this.getEntityContained() == null) {
                this.progress = 0;
                this.mode = 0;
                this.essentiaDemanded = new AspectList();
                this.myEssentia = new AspectList();
                return;
            }
            if (this.getEntityContained() == null && this.myEssentia.getAmount(Aspect.LIFE) >= 4) {
                if (this.sample.func_77973_b() == ThaumicHorizons.itemSyringeBloodSample) {
                    this.setEntityContained((EntityLivingBase)EntityList.func_75615_a((NBTTagCompound)this.sample.func_77978_p().func_74775_l("critter"), (World)this.field_145850_b));
                    if (this.getEntityContained() instanceof EntityTameable) {
                        ((EntityTameable)this.getEntityContained()).func_70903_f(false);
                    }
                } else {
                    this.setEntityContained((EntityLivingBase)EntityList.func_75616_a((int)ThaumicHorizons.incarnationItems.get(this.sample.func_77973_b()), (World)this.field_145850_b));
                }
                --this.sample.field_77994_a;
                if (this.sample.field_77994_a <= 0) {
                    this.sample = null;
                }
                --this.nutrients.field_77994_a;
                if (this.nutrients.field_77994_a <= 0) {
                    this.nutrients = null;
                }
                this.progress = 800;
                this.essentiaDemanded = new AspectList();
                this.myEssentia = new AspectList();
                if (this.getEntityContained() == null) {
                    this.progress = 0;
                    this.mode = 0;
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    this.func_70296_d();
                    return;
                }
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
            } else if (this.progress <= 0) {
                this.mode = 0;
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
            }
        } else if (this.mode == 2) {
            ++this.count;
            if (this.checkSurroundings) {
                this.checkSurroundings = false;
                this.getSurroundings();
            } else if (this.count % this.countDelay == 0) {
                this.craftCycle();
                this.func_70296_d();
            }
        } else if (this.mode == 3) {
            if (this.sample == null || this.sample.func_77973_b() != ThaumicHorizons.itemCorpseEffigy) {
                this.progress = 0;
                this.mode = 0;
                this.essentiaDemanded = new AspectList();
                this.myEssentia = new AspectList();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
                return;
            }
            if (this.progress <= 0 && this.myEssentia.getAmount(Aspect.LIFE) >= 8 && this.myEssentia.getAmount(Aspect.HEAL) >= 8) {
                this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:wand", 1.0f, this.field_145850_b.field_73012_v.nextFloat());
                Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d - 2, this.field_145849_e, 0xFF2222, 20);
                Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, 0xFF2222, 20);
                this.mode = 4;
                this.selfInfusionHealth = 20.0f;
                this.sample = null;
                this.essentiaDemanded = new AspectList();
                this.myEssentia = new AspectList();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
            }
        }
        if (this.mode != 2 && this.needsEssentia()) {
            this.tryDrawAllEssentia();
        }
        if (this.progress > 0) {
            --this.progress;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    boolean needsEssentia() {
        this.currentlySucking = null;
        if (this.progress > 0) {
            return false;
        }
        for (Aspect asp : this.essentiaDemanded.getAspects()) {
            if (this.myEssentia.getAmount(asp) >= this.essentiaDemanded.getAmount(asp)) continue;
            this.currentlySucking = asp;
            break;
        }
        return this.currentlySucking != null;
    }

    boolean tryDrawAllEssentia() {
        boolean drew = false;
        TileEntity conn = this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d - 3, this.field_145849_e);
        if (conn != null && conn instanceof TileVatConnector) {
            drew |= this.tryDrawEssentia((TileVatConnector)conn);
        }
        if ((conn = this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d - 3, this.field_145849_e)) != null && conn instanceof TileVatConnector) {
            drew |= this.tryDrawEssentia((TileVatConnector)conn);
        }
        if ((conn = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 3, this.field_145849_e - 1)) != null && conn instanceof TileVatConnector) {
            drew |= this.tryDrawEssentia((TileVatConnector)conn);
        }
        if ((conn = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 3, this.field_145849_e + 1)) != null && conn instanceof TileVatConnector) {
            drew |= this.tryDrawEssentia((TileVatConnector)conn);
        }
        return drew;
    }

    boolean tryDrawEssentia(TileVatConnector conn) {
        TileEntity te = null;
        IEssentiaTransport ic = null;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, conn.field_145851_c, conn.field_145848_d, conn.field_145849_e, dir);
            if (te == null || (ic = (IEssentiaTransport)te).getEssentiaAmount(dir.getOpposite()) <= 0 || ic.getSuctionAmount(dir.getOpposite()) >= this.getSuctionAmount(null) || this.getSuctionAmount(null) < ic.getMinimumSuction()) continue;
            for (Aspect asp : this.essentiaDemanded.getAspects()) {
                int ess;
                if (this.mode != 2 && this.myEssentia.getAmount(asp) >= this.essentiaDemanded.getAmount(asp) || (ess = ic.takeEssentia(asp, 1, dir.getOpposite())) <= 0) continue;
                this.addToContainer(asp, ess);
                return true;
            }
        }
        return false;
    }

    boolean hasNegativeEffect(EntityLivingBase ent) {
        if (ent.func_70660_b(Potion.field_76436_u) != null) {
            return true;
        }
        if (ent.func_70660_b(Potion.field_76440_q) != null) {
            return true;
        }
        if (ent.func_70660_b(Potion.field_76438_s) != null) {
            return true;
        }
        if (ent.func_70660_b(Potion.field_76437_t) != null) {
            return true;
        }
        if (ent.func_70660_b(Potion.field_82731_v) != null) {
            return true;
        }
        if (ent.func_70660_b(Potion.field_76431_k) != null) {
            return true;
        }
        if (ent.func_70660_b(Potion.field_76419_f) != null) {
            return true;
        }
        if (ent.func_70660_b(Potion.field_76421_d) != null) {
            return true;
        }
        if (ent.func_70660_b(Potion.field_76425_a[Config.potionBlurredID]) != null) {
            return true;
        }
        if (ent.func_70660_b(Potion.field_76425_a[Config.potionInfVisExhaustID]) != null) {
            return true;
        }
        if (ent.func_70660_b(Potion.field_76425_a[Config.potionTaintPoisonID]) != null) {
            return true;
        }
        if (ent.func_70660_b(Potion.field_76425_a[Config.potionThaumarhiaID]) != null) {
            return true;
        }
        return ent.func_70660_b(Potion.field_76425_a[Config.potionVisExhaustID]) != null;
    }

    void removeNegativeEffects(EntityLivingBase ent) {
        ent.func_82170_o(Config.potionBlurredID);
        ent.func_82170_o(Config.potionInfVisExhaustID);
        ent.func_82170_o(Config.potionTaintPoisonID);
        ent.func_82170_o(Config.potionThaumarhiaID);
        ent.func_82170_o(Config.potionVisExhaustID);
        ent.func_82170_o(Potion.field_76440_q.field_76415_H);
        ent.func_82170_o(Potion.field_76431_k.field_76415_H);
        ent.func_82170_o(Potion.field_76419_f.field_76415_H);
        ent.func_82170_o(Potion.field_76438_s.field_76415_H);
        ent.func_82170_o(Potion.field_76421_d.field_76415_H);
        ent.func_82170_o(Potion.field_76438_s.field_76415_H);
        ent.func_82170_o(Potion.field_76436_u.field_76415_H);
        ent.func_82170_o(Potion.field_76437_t.field_76415_H);
        ent.func_82170_o(Potion.field_82731_v.field_76415_H);
    }

    public boolean jarCritter(ItemStack possibleJar, EntityPlayer player) {
        ItemStack jar = new ItemStack(ThaumicHorizons.blockJar);
        NBTTagCompound entityData = new NBTTagCompound();
        entityData.func_74778_a("id", EntityList.func_75621_b((Entity)this.getEntityContained()));
        this.getEntityContained().func_70109_d(entityData);
        jar.func_77982_d(entityData);
        jar.func_77978_p().func_74778_a("jarredCritterName", this.getEntityContained().func_70005_c_());
        jar.func_77978_p().func_74757_a("isSoul", false);
        if (player.field_71071_by.func_70441_a(jar)) {
            --possibleJar.field_77994_a;
            this.setEntityContained(null);
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return true;
        }
        return false;
    }

    public EntityLivingBase getEntity() {
        return this.getEntityContained();
    }

    /*
     * WARNING - void declaration
     */
    public void startInfusion(EntityPlayer player) {
        Object recipe;
        this.getSurroundings();
        ArrayList<ItemStack> components = new ArrayList<ItemStack>();
        for (ChunkCoordinates chunkCoordinates : this.pedestals) {
            TilePedestal ped;
            TileEntity te = this.field_145850_b.func_147438_o(chunkCoordinates.field_71574_a, chunkCoordinates.field_71572_b, chunkCoordinates.field_71573_c);
            if (te == null || !(te instanceof TilePedestal) || (ped = (TilePedestal)te).func_70301_a(0) == null) continue;
            components.add(ped.func_70301_a(0).func_77946_l());
        }
        if (components.size() == 0) {
            return;
        }
        if (this.mode != 4) {
            recipe = ThaumicHorizons.getCreatureInfusion(this.getEntityContained(), components, player);
            if (!(recipe == null || ((CreatureInfusionRecipe)recipe).getID(null) != 0 && ((EntityInfusionProperties)this.getEntityContained().getExtendedProperties("CreatureInfusion")).hasInfusion(((CreatureInfusionRecipe)recipe).getID(null)))) {
                if (((CreatureInfusionRecipe)recipe).getRecipeOutput() instanceof NBTTagCompound && ((NBTTagCompound)((CreatureInfusionRecipe)recipe).getRecipeOutput()).func_74762_e("instilledLoyalty") != 0 && ((EntityLiving)this.entityContained).field_70714_bg.field_75782_a.size() == 0) {
                    return;
                }
                this.recipeType = 0;
                this.recipeIngredients = new ArrayList();
                for (ItemStack ing : ((CreatureInfusionRecipe)recipe).getComponents()) {
                    this.recipeIngredients.add(ing.func_77946_l());
                }
                if (((CreatureInfusionRecipe)recipe).getRecipeOutput(this.getEntityContained().getClass()) instanceof Object[]) {
                    Object[] objectArray = (Object[])((CreatureInfusionRecipe)recipe).getRecipeOutput(this.getEntityContained().getClass());
                    this.recipeOutputLabel = (String)objectArray[0];
                    this.recipeOutput = (NBTBase)objectArray[1];
                } else {
                    this.recipeOutput = ((CreatureInfusionRecipe)recipe).getRecipeOutput(this.getEntityContained().getClass());
                }
                this.recipeInstability = ((CreatureInfusionRecipe)recipe).getInstability(this.getEntityContained().getClass());
                this.essentiaDemanded = ((CreatureInfusionRecipe)recipe).getAspects(this.getEntityContained().getClass()).copy();
                this.myEssentia = ((CreatureInfusionRecipe)recipe).getAspects(this.getEntityContained().getClass()).copy();
                this.recipePlayer = player.func_70005_c_();
                this.instability = this.symmetry + this.recipeInstability;
                this.mode = 2;
                this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:craftstart", 0.5f, 1.0f);
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
                return;
            }
        } else {
            void var5_9;
            recipe = ThaumicHorizons.getSelfInfusion(components, player);
            if (recipe == null) {
                return;
            }
            boolean bl = false;
            while (var5_9 < this.selfInfusions.length) {
                if (this.selfInfusions[var5_9] == ((SelfInfusionRecipe)recipe).getID()) {
                    return;
                }
                ++var5_9;
            }
            this.recipeType = 1;
            this.recipeIngredients = new ArrayList();
            for (ItemStack ing : ((SelfInfusionRecipe)recipe).getComponents()) {
                this.recipeIngredients.add(ing.func_77946_l());
            }
            this.recipeOutputLabel = "";
            this.recipeOutput = ((SelfInfusionRecipe)recipe).getID();
            this.recipeInstability = ((SelfInfusionRecipe)recipe).getInstability();
            this.myEssentia = ((SelfInfusionRecipe)recipe).getAspects().copy();
            this.essentiaDemanded = ((SelfInfusionRecipe)recipe).getAspects().copy();
            this.recipePlayer = player.func_70005_c_();
            this.instability = this.symmetry + this.recipeInstability;
            this.mode = 2;
            this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:craftstart", 0.5f, 1.0f);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
            return;
        }
    }

    public boolean validLocation() {
        return true;
    }

    private void getSurroundings() {
        ArrayList<ChunkCoordinates> stuff = new ArrayList<ChunkCoordinates>();
        this.pedestals.clear();
        try {
            int x;
            for (int xx = -12; xx <= 12; ++xx) {
                for (int zz = -12; zz <= 12; ++zz) {
                    boolean skip = false;
                    for (int yy = -5; yy <= 10; ++yy) {
                        if (xx == 0 && zz == 0) continue;
                        x = this.field_145851_c + xx;
                        int y = this.field_145848_d - yy;
                        int z = this.field_145849_e + zz;
                        TileEntity te = this.field_145850_b.func_147438_o(x, y, z);
                        if (!skip && yy > 0 && Math.abs(xx) <= 8 && Math.abs(zz) <= 8 && te != null && te instanceof TilePedestal) {
                            this.pedestals.add(new ChunkCoordinates(x, y, z));
                            skip = true;
                            continue;
                        }
                        Block bi = this.field_145850_b.func_147439_a(x, y, z);
                        if (bi != Blocks.field_150465_bP && (!(bi instanceof IInfusionStabiliser) || !((IInfusionStabiliser)bi).canStabaliseInfusion(this.func_145831_w(), x, y, z))) continue;
                        stuff.add(new ChunkCoordinates(x, y, z));
                    }
                }
            }
            this.symmetry = 0;
            for (ChunkCoordinates cc : this.pedestals) {
                int zz;
                int xx;
                boolean items = false;
                int x2 = this.field_145851_c - cc.field_71574_a;
                int z = this.field_145849_e - cc.field_71573_c;
                TileEntity te = this.field_145850_b.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
                if (te != null && te instanceof TilePedestal) {
                    this.symmetry += 2;
                    if (((TilePedestal)te).func_70301_a(0) != null) {
                        ++this.symmetry;
                        items = true;
                    }
                }
                if ((te = this.field_145850_b.func_147438_o(xx = this.field_145851_c + x2, cc.field_71572_b, zz = this.field_145849_e + z)) == null || !(te instanceof TilePedestal)) continue;
                this.symmetry -= 2;
                if (((TilePedestal)te).func_70301_a(0) == null || !items) continue;
                --this.symmetry;
            }
            float sym = 0.0f;
            for (ChunkCoordinates cc : stuff) {
                int zz;
                int xx;
                boolean items = false;
                x = this.field_145851_c - cc.field_71574_a;
                int z = this.field_145849_e - cc.field_71573_c;
                Block bi = this.field_145850_b.func_147439_a(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
                if (bi == Blocks.field_150465_bP || bi instanceof IInfusionStabiliser && ((IInfusionStabiliser)bi).canStabaliseInfusion(this.func_145831_w(), cc.field_71574_a, cc.field_71572_b, cc.field_71573_c)) {
                    sym += 0.1f;
                }
                if ((bi = this.field_145850_b.func_147439_a(xx = this.field_145851_c + x, cc.field_71572_b, zz = this.field_145849_e + z)) != Blocks.field_150465_bP && (!(bi instanceof IInfusionStabiliser) || !((IInfusionStabiliser)bi).canStabaliseInfusion(this.func_145831_w(), cc.field_71574_a, cc.field_71572_b, cc.field_71573_c))) continue;
                sym -= 0.2f;
            }
            this.symmetry = (int)((float)this.symmetry + sym);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void doEffects() {
        if (this.mode == 2) {
            if (this.craftCount == 0) {
                this.field_145850_b.func_72980_b((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:infuserstart", 0.5f, 1.0f, false);
            } else if (this.craftCount % 65 == 0) {
                this.field_145850_b.func_72980_b((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:infuser", 0.5f, 1.0f, false);
            }
            ++this.craftCount;
            Thaumcraft.proxy.blockRunes(this.field_145850_b, (double)this.field_145851_c, (double)(this.field_145848_d - 2), (double)this.field_145849_e, 0.5f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f, 0.1f, 0.7f + this.field_145850_b.field_73012_v.nextFloat() * 0.3f, 25, -0.03f);
        } else if (this.craftCount > 0) {
            this.craftCount -= 2;
            if (this.craftCount < 0) {
                this.craftCount = 0;
            }
            if (this.craftCount > 50) {
                this.craftCount = 50;
            }
        }
        if (this.mode == 2 && this.startUp != 1.0f) {
            if (this.startUp < 1.0f) {
                this.startUp += Math.max(this.startUp / 10.0f, 0.001f);
            }
            if ((double)this.startUp > 0.999) {
                this.startUp = 1.0f;
            }
        }
        for (String fxk : this.sourceFX.keySet().toArray(new String[0])) {
            SourceFX fx = this.sourceFX.get(fxk);
            if (fx.ticks <= 0) {
                this.sourceFX.remove(fxk);
                continue;
            }
            if (fx.loc.field_71574_a == this.field_145851_c && fx.loc.field_71572_b == this.field_145848_d && fx.loc.field_71573_c == this.field_145849_e) {
                Entity player = this.field_145850_b.func_73045_a(fx.color);
                if (player != null) {
                    for (int a = 0; a < Thaumcraft.proxy.particleCount(2); ++a) {
                        Thaumcraft.proxy.drawInfusionParticles4(this.field_145850_b, player.field_70165_t + (double)((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * player.field_70130_N), player.field_70121_D.field_72338_b + (double)(this.field_145850_b.field_73012_v.nextFloat() * player.field_70131_O), player.field_70161_v + (double)((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * player.field_70130_N), this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    }
                }
            } else {
                TileEntity tile = this.field_145850_b.func_147438_o(fx.loc.field_71574_a, fx.loc.field_71572_b, fx.loc.field_71573_c);
                if (tile instanceof TilePedestal) {
                    ItemStack is = ((TilePedestal)tile).func_70301_a(0);
                    if (is != null) {
                        if (this.field_145850_b.field_73012_v.nextInt(3) == 0) {
                            Thaumcraft.proxy.drawInfusionParticles3(this.field_145850_b, (double)((float)fx.loc.field_71574_a + this.field_145850_b.field_73012_v.nextFloat()), (double)((float)fx.loc.field_71572_b + this.field_145850_b.field_73012_v.nextFloat() + 1.0f), (double)((float)fx.loc.field_71573_c + this.field_145850_b.field_73012_v.nextFloat()), this.field_145851_c, this.field_145848_d, this.field_145849_e);
                        } else {
                            int a;
                            Item bi = is.func_77973_b();
                            int md = is.func_77960_j();
                            if (is.func_94608_d() == 0 && bi instanceof ItemBlock) {
                                for (a = 0; a < Thaumcraft.proxy.particleCount(2); ++a) {
                                    Thaumcraft.proxy.drawInfusionParticles2(this.field_145850_b, (double)((float)fx.loc.field_71574_a + this.field_145850_b.field_73012_v.nextFloat()), (double)((float)fx.loc.field_71572_b + this.field_145850_b.field_73012_v.nextFloat() + 1.0f), (double)((float)fx.loc.field_71573_c + this.field_145850_b.field_73012_v.nextFloat()), this.field_145851_c, this.field_145848_d, this.field_145849_e, Block.func_149634_a((Item)bi), md);
                                }
                            } else {
                                for (a = 0; a < Thaumcraft.proxy.particleCount(2); ++a) {
                                    Thaumcraft.proxy.drawInfusionParticles1(this.field_145850_b, (double)((float)fx.loc.field_71574_a + 0.4f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f), (double)((float)fx.loc.field_71572_b + 1.23f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f), (double)((float)fx.loc.field_71573_c + 0.4f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f), this.field_145851_c, this.field_145848_d, this.field_145849_e, bi, md);
                                }
                            }
                        }
                    }
                } else {
                    fx.ticks = 0;
                }
            }
            --fx.ticks;
            this.sourceFX.put(fxk, fx);
        }
        if (this.mode == 2 && this.instability > 0 && this.field_145850_b.field_73012_v.nextInt(200) <= this.instability) {
            Thaumcraft.proxy.nodeBolt(this.field_145850_b, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f, (float)this.field_145851_c + 0.5f + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 2.0f, (float)this.field_145848_d + 0.5f + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 2.0f, (float)this.field_145849_e + 0.5f + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 2.0f);
        }
    }

    public void craftCycle() {
        float temp;
        float visDrawn;
        if (this.instability > 0 && this.field_145850_b.field_73012_v.nextInt(500) <= this.instability) {
            switch (this.field_145850_b.field_73012_v.nextInt(21)) {
                case 0: 
                case 2: 
                case 10: 
                case 13: {
                    this.inEvEjectItem(0);
                    break;
                }
                case 6: 
                case 17: {
                    this.inEvEjectItem(1);
                    break;
                }
                case 1: 
                case 11: {
                    this.inEvEjectItem(2);
                    break;
                }
                case 3: 
                case 8: 
                case 14: {
                    this.inEvZap(false);
                    break;
                }
                case 5: 
                case 16: {
                    this.inEvHarm(false);
                    break;
                }
                case 12: {
                    this.inEvZap(true);
                    break;
                }
                case 19: {
                    this.inEvEjectItem(3);
                    break;
                }
                case 7: {
                    this.inEvEjectItem(4);
                    break;
                }
                case 4: 
                case 15: {
                    this.inEvEjectItem(5);
                    break;
                }
                case 18: {
                    this.inEvHarm(true);
                    break;
                }
                case 9: {
                    this.field_145850_b.func_72876_a(null, (double)((float)this.field_145851_c + 0.5f), (double)((float)this.field_145848_d + 0.5f), (double)((float)this.field_145849_e + 0.5f), 1.5f + this.field_145850_b.field_73012_v.nextFloat(), false);
                    break;
                }
                case 20: {
                    this.inEvWarp();
                }
            }
        }
        if (this.instability > 0 && this.entityContained != null) {
            visDrawn = 999.0f;
            if (!this.field_145850_b.field_72995_K) {
                temp = VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, Aspect.EARTH, 100);
                if (temp < visDrawn) {
                    visDrawn = temp;
                }
                if ((temp = (float)VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, Aspect.WATER, 100)) < visDrawn) {
                    visDrawn = temp;
                }
                if ((temp = (float)VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, Aspect.ORDER, 100)) < visDrawn) {
                    visDrawn = temp;
                }
            }
            this.getEntityContained().func_70606_j(this.getEntityContained().func_110143_aJ() - (float)this.instability / 10.0f / (5.0f + visDrawn));
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            if (this.getEntityContained().func_110143_aJ() <= 0.0f) {
                this.killSubject();
                return;
            }
        } else if (this.instability > 0) {
            visDrawn = 999.0f;
            if (!this.field_145850_b.field_72995_K) {
                temp = VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, Aspect.EARTH, 100);
                if (temp < visDrawn) {
                    visDrawn = temp;
                }
                if ((temp = (float)VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, Aspect.WATER, 100)) < visDrawn) {
                    visDrawn = temp;
                }
                if ((temp = (float)VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, Aspect.ORDER, 100)) < visDrawn) {
                    visDrawn = temp;
                }
            }
            this.selfInfusionHealth -= (float)this.instability / 10.0f / (5.0f + visDrawn);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            if (this.selfInfusionHealth <= 0.0f) {
                this.killSubject();
                return;
            }
        }
        if (this.essentiaDemanded.visSize() > 0) {
            for (Aspect aspect : this.essentiaDemanded.getAspects()) {
                if (this.essentiaDemanded.getAmount(aspect) <= 0) continue;
                this.currentlySucking = aspect;
                if (this.tryDrawAllEssentia()) {
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    this.func_70296_d();
                    return;
                }
                if (this.field_145850_b.field_73012_v.nextInt(100 - this.recipeInstability * 3) == 0) {
                    ++this.instability;
                }
                if (this.instability > 25) {
                    this.instability = 25;
                }
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
                break;
            }
            this.checkSurroundings = true;
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
            return;
        }
        if (this.recipeIngredients.size() > 0) {
            for (int a = 0; a < this.recipeIngredients.size(); ++a) {
                for (ChunkCoordinates cc : this.pedestals) {
                    TileEntity te = this.field_145850_b.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
                    if (te == null || !(te instanceof TilePedestal) || ((TilePedestal)te).func_70301_a(0) == null || !InfusionRecipe.areItemStacksEqual(((TilePedestal)te).func_70301_a(0), this.recipeIngredients.get(a), true)) continue;
                    if (this.itemCount == 0) {
                        this.itemCount = 5;
                        PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketInfusionFX(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e, (byte)(this.field_145851_c - cc.field_71574_a), (byte)(this.field_145848_d - cc.field_71572_b - 2), (byte)(this.field_145849_e - cc.field_71573_c), 0), new NetworkRegistry.TargetPoint(this.func_145831_w().field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
                    } else if (this.itemCount-- <= 1) {
                        ItemStack is = ((TilePedestal)te).func_70301_a(0).func_77973_b().getContainerItem(((TilePedestal)te).func_70301_a(0));
                        ((TilePedestal)te).func_70299_a(0, is == null ? null : is.func_77946_l());
                        this.recipeIngredients.remove(a);
                    }
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    this.func_70296_d();
                    return;
                }
            }
            return;
        }
        this.instability = 0;
        this.craftingFinish(this.recipeOutput, this.recipeOutputLabel);
        this.recipeOutput = null;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
    }

    private void inEvZap(boolean all) {
        List targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(10.0, 10.0, 10.0));
        if (targets != null && targets.size() > 0) {
            for (Entity target : targets) {
                thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockZap((float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f, (float)target.field_70165_t, (float)target.field_70163_u + target.field_70131_O / 2.0f, (float)target.field_70161_v), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
                target.func_70097_a(DamageSource.field_76376_m, (float)(4 + this.field_145850_b.field_73012_v.nextInt(4)));
                if (all) continue;
                break;
            }
        }
    }

    private void inEvHarm(boolean all) {
        List targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(10.0, 10.0, 10.0));
        if (targets != null && targets.size() > 0) {
            for (EntityLivingBase target : targets) {
                if (this.field_145850_b.field_73012_v.nextBoolean()) {
                    target.func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 120, 0, false));
                } else {
                    PotionEffect pe = new PotionEffect(Config.potionVisExhaustID, 2400, 0, true);
                    pe.getCurativeItems().clear();
                    target.func_70690_d(pe);
                }
                if (all) continue;
                break;
            }
        }
    }

    private void inEvWarp() {
        List targets = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(10.0, 10.0, 10.0));
        if (targets != null && targets.size() > 0) {
            EntityPlayer target = (EntityPlayer)targets.get(this.field_145850_b.field_73012_v.nextInt(targets.size()));
            if (this.field_145850_b.field_73012_v.nextFloat() < 0.25f) {
                Thaumcraft.addStickyWarpToPlayer((EntityPlayer)target, (int)1);
            } else {
                Thaumcraft.addWarpToPlayer((EntityPlayer)target, (int)(1 + this.field_145850_b.field_73012_v.nextInt(5)), (boolean)true);
            }
        }
    }

    private void inEvEjectItem(int type) {
        for (int q = 0; q < 50 && this.pedestals.size() > 0; ++q) {
            ChunkCoordinates cc = this.pedestals.get(this.field_145850_b.field_73012_v.nextInt(this.pedestals.size()));
            TileEntity te = this.field_145850_b.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
            if (te == null || !(te instanceof TilePedestal) || ((TilePedestal)te).func_70301_a(0) == null) continue;
            if (type < 3 || type == 5) {
                InventoryUtils.dropItems((World)this.field_145850_b, (int)cc.field_71574_a, (int)cc.field_71572_b, (int)cc.field_71573_c);
            } else {
                ((TilePedestal)te).func_70299_a(0, null);
            }
            if (type == 1 || type == 3) {
                this.field_145850_b.func_147465_d(cc.field_71574_a, cc.field_71572_b + 1, cc.field_71573_c, ConfigBlocks.blockFluxGoo, 7, 3);
                this.field_145850_b.func_72908_a((double)cc.field_71574_a, (double)cc.field_71572_b, (double)cc.field_71573_c, "game.neutral.swim", 0.3f, 1.0f);
            } else if (type == 2 || type == 4) {
                this.field_145850_b.func_147465_d(cc.field_71574_a, cc.field_71572_b + 1, cc.field_71573_c, ConfigBlocks.blockFluxGas, 7, 3);
                this.field_145850_b.func_72908_a((double)cc.field_71574_a, (double)cc.field_71572_b, (double)cc.field_71573_c, "random.fizz", 0.3f, 1.0f);
            } else if (type == 5) {
                this.field_145850_b.func_72876_a(null, (double)((float)cc.field_71574_a + 0.5f), (double)((float)cc.field_71572_b + 0.5f), (double)((float)cc.field_71573_c + 0.5f), 1.0f, false);
            }
            this.field_145850_b.func_147452_c(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, ConfigBlocks.blockStoneDevice, 11, 0);
            thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockZap((float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f, (float)cc.field_71574_a + 0.5f, (float)cc.field_71572_b + 1.5f, (float)cc.field_71573_c + 0.5f), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
            return;
        }
    }

    public void craftingFinish(Object out, String label) {
        if (this.recipeType == 0) {
            if (out instanceof Integer) {
                EntityLivingBase created = null;
                if ((Integer)out < 0) {
                    created = (EntityLivingBase)EntityList.func_75616_a((int)(-((Integer)out).intValue()), (World)this.field_145850_b);
                }
                ModContainer mc = (ModContainer)Loader.instance().getIndexedModList().get("ThaumicHorizons");
                try {
                    created = (EntityLivingBase)EntityRegistry.instance().lookupModSpawn(mc, ((Integer)out).intValue()).getEntityClass().getConstructor(World.class).newInstance(this.field_145850_b);
                }
                catch (InvocationTargetException e) {
                    e.getCause().printStackTrace();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                created.func_82149_j((Entity)this.getEntityContained());
                created.func_82141_a((Entity)this.getEntityContained(), true);
                if (created instanceof IEntityInfusedStats) {
                    ((IEntityInfusedStats)created).resetStats();
                }
                this.setEntityContained(created);
            } else if (out instanceof NBTBase) {
                NBTTagCompound tagMods = (NBTTagCompound)out;
                HashMultimap map = HashMultimap.create();
                if (tagMods.func_74769_h("generic.movementSpeed") > 0.0) {
                    map.put((Object)"generic.movementSpeed", (Object)new AttributeModifier("generic.movementSpeed", tagMods.func_74769_h("generic.movementSpeed") / 10.0, 1));
                }
                if (tagMods.func_74769_h("generic.maxHealth") > 0.0) {
                    map.put((Object)"generic.maxHealth", (Object)new AttributeModifier("generic.maxHealth", tagMods.func_74769_h("generic.maxHealth"), 1));
                }
                if (tagMods.func_74769_h("generic.attackDamage") > 0.0) {
                    map.put((Object)"generic.attackDamage", (Object)new AttributeModifier("generic.attackDamage", tagMods.func_74769_h("generic.attackDamage"), 1));
                }
                if (map.size() > 0) {
                    this.getEntityContained().func_110140_aT().func_111147_b((Multimap)map);
                }
                Set keys = tagMods.func_150296_c();
                for (String s : keys) {
                    if (s.substring(0, 8).equals("generic.")) continue;
                    ((EntityInfusionProperties)this.getEntityContained().getExtendedProperties("CreatureInfusion")).addInfusion(tagMods.func_74762_e(s));
                    if (tagMods.func_74762_e(s) != 7) continue;
                    ((EntityInfusionProperties)this.getEntityContained().getExtendedProperties("CreatureInfusion")).setOwner(this.recipePlayer);
                }
            }
            ((EntityInfusionProperties)this.getEntityContained().getExtendedProperties("CreatureInfusion")).addCost(this.myEssentia);
            if (this.entityContained instanceof EntityLiving) {
                ((EntityLiving)this.entityContained).func_110163_bv();
            }
            this.mode = 0;
        } else {
            for (int i = 0; i < this.selfInfusions.length; ++i) {
                if (this.selfInfusions[i] != 0) continue;
                this.selfInfusions[i] = (Integer)this.recipeOutput;
                break;
            }
            this.mode = 4;
        }
        PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXInfusionDone(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
        this.essentiaDemanded = new AspectList();
        this.myEssentia = new AspectList();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        NBTTagCompound f;
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74768_a("mode", this.mode);
        nbttagcompound.func_74768_a("progress", this.progress);
        nbttagcompound.func_74777_a("instability", (short)this.instability);
        if (this.currentlySucking != null) {
            nbttagcompound.func_74778_a("sucking", this.currentlySucking.getTag());
        } else {
            nbttagcompound.func_74778_a("sucking", "");
        }
        NBTTagList tlist = new NBTTagList();
        nbttagcompound.func_74782_a("myEssentia", (NBTBase)tlist);
        for (Aspect aspect : this.myEssentia.getAspects()) {
            if (aspect == null) continue;
            f = new NBTTagCompound();
            f.func_74778_a("key", aspect.getTag());
            f.func_74768_a("amount", this.myEssentia.getAmount(aspect));
            tlist.func_74742_a((NBTBase)f);
        }
        tlist = new NBTTagList();
        nbttagcompound.func_74782_a("essentiaDemanded", (NBTBase)tlist);
        for (Aspect aspect : this.essentiaDemanded.getAspects()) {
            if (aspect == null) continue;
            f = new NBTTagCompound();
            f.func_74778_a("key", aspect.getTag());
            f.func_74768_a("amount", this.essentiaDemanded.getAmount(aspect));
            tlist.func_74742_a((NBTBase)f);
        }
        NBTTagCompound entityData = new NBTTagCompound();
        if (this.getEntityContained() != null && !(this.getEntityContained() instanceof EntityPlayer)) {
            entityData.func_74778_a("id", EntityList.func_75621_b((Entity)this.getEntityContained()));
            this.getEntityContained().func_70109_d(entityData);
        } else if (this.getEntityContained() != null) {
            entityData.func_74778_a("id", "PLAYER");
            entityData.func_74778_a("playerName", this.getEntityContained().func_70005_c_());
        }
        nbttagcompound.func_74782_a("entity", (NBTBase)entityData);
        NBTTagCompound item = new NBTTagCompound();
        if (this.sample != null) {
            this.sample.func_77955_b(item);
        }
        nbttagcompound.func_74782_a("sample", (NBTBase)item);
        NBTTagCompound itemtoo = new NBTTagCompound();
        if (this.nutrients != null) {
            this.nutrients.func_77955_b(itemtoo);
        }
        nbttagcompound.func_74782_a("nutrients", (NBTBase)itemtoo);
        nbttagcompound.func_74783_a("selfInfusions", this.selfInfusions);
        nbttagcompound.func_74776_a("selfInfusionHealth", this.selfInfusionHealth);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        NBTTagCompound rs;
        int j;
        super.readCustomNBT(nbttagcompound);
        this.mode = nbttagcompound.func_74762_e("mode");
        this.progress = nbttagcompound.func_74762_e("progress");
        this.instability = nbttagcompound.func_74765_d("instability");
        this.currentlySucking = Aspect.getAspect(nbttagcompound.func_74779_i("sucking"));
        AspectList al = new AspectList();
        NBTTagList tlist = nbttagcompound.func_150295_c("myEssentia", 10);
        for (j = 0; j < tlist.func_74745_c(); ++j) {
            rs = tlist.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            al.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
        }
        this.myEssentia = al.copy();
        al = new AspectList();
        tlist = nbttagcompound.func_150295_c("essentiaDemanded", 10);
        for (j = 0; j < tlist.func_74745_c(); ++j) {
            rs = tlist.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            al.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
        }
        this.essentiaDemanded = al.copy();
        if (nbttagcompound.func_74775_l("entity").func_74779_i("id").equals("PLAYER")) {
            this.setEntityContained((EntityLivingBase)this.field_145850_b.func_72924_a(nbttagcompound.func_74775_l("entity").func_74779_i("playerName")));
        } else {
            this.setEntityContained((EntityLivingBase)EntityList.func_75615_a((NBTTagCompound)nbttagcompound.func_74775_l("entity"), (World)this.field_145850_b));
        }
        this.sample = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound.func_74775_l("sample"));
        this.nutrients = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound.func_74775_l("nutrients"));
        this.selfInfusions = nbttagcompound.func_74759_k("selfInfusions");
        if (this.selfInfusions.length == 0) {
            this.selfInfusions = new int[12];
        }
        this.selfInfusionHealth = nbttagcompound.func_74760_g("selfInfusionHealth");
    }

    @Override
    public void func_145839_a(NBTTagCompound nbtCompound) {
        super.func_145839_a(nbtCompound);
        NBTTagList nbttaglist = nbtCompound.func_150295_c("recipein", 10);
        this.recipeIngredients = new ArrayList();
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            byte b0 = nbttagcompound1.func_74771_c("item");
            this.recipeIngredients.add(ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1));
        }
        String rot = nbtCompound.func_74779_i("rotype");
        if (rot != null && rot.equals("@")) {
            this.recipeOutput = nbtCompound.func_74762_e("recipeout");
        } else if (rot != null) {
            this.recipeOutputLabel = rot;
            this.recipeOutput = nbtCompound.func_74781_a("recipeout");
        }
        this.recipeInstability = nbtCompound.func_74762_e("recipeinst");
        this.recipeType = nbtCompound.func_74762_e("recipetype");
        this.recipePlayer = nbtCompound.func_74779_i("recipeplayer");
        if (this.recipePlayer.isEmpty()) {
            this.recipePlayer = null;
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound nbtCompound) {
        super.func_145841_b(nbtCompound);
        if (this.recipeIngredients != null && this.recipeIngredients.size() > 0) {
            NBTTagList nbttaglist = new NBTTagList();
            int count = 0;
            for (ItemStack stack : this.recipeIngredients) {
                if (stack == null) continue;
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.func_74774_a("item", (byte)count);
                stack.func_77955_b(nbttagcompound1);
                nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
                ++count;
            }
            nbtCompound.func_74782_a("recipein", (NBTBase)nbttaglist);
        }
        if (this.recipeOutput != null && this.recipeOutput instanceof Integer) {
            nbtCompound.func_74778_a("rotype", "@");
        }
        if (this.recipeOutput != null && this.recipeOutput instanceof NBTBase) {
            nbtCompound.func_74778_a("rotype", this.recipeOutputLabel);
        }
        if (this.recipeOutput != null && this.recipeOutput instanceof Integer) {
            nbtCompound.func_74782_a("recipeout", (NBTBase)new NBTTagInt(((Integer)this.recipeOutput).intValue()));
        }
        if (this.recipeOutput != null && this.recipeOutput instanceof NBTBase) {
            nbtCompound.func_74782_a("recipeout", (NBTBase)this.recipeOutput);
        }
        nbtCompound.func_74768_a("recipeinst", this.recipeInstability);
        nbtCompound.func_74768_a("recipetype", this.recipeType);
        nbtCompound.func_74768_a("recipexp", this.recipeXP);
        if (this.recipePlayer == null) {
            nbtCompound.func_74778_a("recipeplayer", "");
        } else {
            nbtCompound.func_74778_a("recipeplayer", this.recipePlayer);
        }
    }

    public boolean isValidInfusionTarget() {
        if (!(this.getEntityContained() == null || this.getEntityContained().func_70668_bt() == EnumCreatureAttribute.UNDEAD || this.getEntityContained() instanceof EntityPlayer || this.getEntityContained() instanceof EntityGolem || this.getEntityContained() instanceof EntityGolemBase || this.getEntityContained() instanceof IMerchant || this.getEntityContained() instanceof INpc)) {
            for (Class clazz : ThaumicHorizons.classBanList) {
                if (!this.getEntityContained().getClass().isAssignableFrom(clazz)) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public void killMe() {
        EntityItem item;
        if (this.entityContained != null) {
            this.killSubject();
        }
        if (this.sample != null) {
            item = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 1.5, (double)this.field_145849_e - 0.5, this.sample);
            this.field_145850_b.func_72838_d((Entity)item);
            this.sample = null;
        }
        if (this.nutrients != null) {
            item = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 1.5, (double)this.field_145849_e - 0.5, this.nutrients);
            this.field_145850_b.func_72838_d((Entity)item);
            this.nutrients = null;
        }
        for (int y = 0; y < 4; ++y) {
            for (int x = -1; x < 2; ++x) {
                for (int z = -1; z < 2; ++z) {
                    if (x != 0 || z != 0) {
                        if (y == 0 || y == 3) {
                            this.field_145850_b.func_147465_d(this.field_145851_c + x, this.field_145848_d - y, this.field_145849_e + z, ConfigBlocks.blockWoodenDevice, 6, 3);
                            continue;
                        }
                        this.field_145850_b.func_147465_d(this.field_145851_c + x, this.field_145848_d - y, this.field_145849_e + z, Blocks.field_150359_w, 0, 3);
                        continue;
                    }
                    if (y == 0 || y == 3) {
                        this.field_145850_b.func_147465_d(this.field_145851_c + x, this.field_145848_d - y, this.field_145849_e + z, ConfigBlocks.blockMetalDevice, 9, 3);
                        continue;
                    }
                    this.field_145850_b.func_147465_d(this.field_145851_c + x, this.field_145848_d - y, this.field_145849_e + z, Blocks.field_150355_j, 0, 3);
                }
            }
        }
    }

    public void killSubject() {
        if (!this.field_145850_b.field_72995_K && (this.entityContained != null && !(this.entityContained instanceof EntityPlayer) || this.recipeType == 1)) {
            this.field_145850_b.func_72876_a(null, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, 0.5f, false);
            for (int a = 0; a < 25; ++a) {
                int zz;
                int yy;
                int xx = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8);
                if (!this.field_145850_b.func_147437_c(xx, yy = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8), zz = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8))) continue;
                if (yy < this.field_145848_d) {
                    this.field_145850_b.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGoo, 8, 3);
                    continue;
                }
                this.field_145850_b.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGas, 8, 3);
            }
        }
        this.selfInfusions = new int[12];
        this.setEntityContained(null);
        this.mode = 0;
        this.currentlySucking = null;
        this.myEssentia = new AspectList();
        this.essentiaDemanded = new AspectList();
        this.progress = 0;
        this.symmetry = 0;
        this.instability = 0;
        this.craftCount = 0;
        this.count = 0;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public int func_70302_i_() {
        return 2;
    }

    public ItemStack func_70301_a(int slot) {
        if (slot == 0) {
            return this.sample;
        }
        return this.nutrients;
    }

    public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
        ItemStack theStack = p_70298_1_ == 0 ? this.sample : this.nutrients;
        if (theStack != null) {
            if (theStack.field_77994_a <= p_70298_2_) {
                ItemStack outStack;
                if (p_70298_1_ == 0) {
                    outStack = this.sample.func_77946_l();
                    this.sample = null;
                } else {
                    outStack = this.nutrients.func_77946_l();
                    this.nutrients = null;
                }
                return outStack;
            }
            ItemStack outStack = theStack.func_77979_a(p_70298_2_);
            if (theStack.field_77994_a == 0) {
                if (p_70298_1_ == 0) {
                    this.sample = null;
                } else {
                    this.nutrients = null;
                }
            }
            return outStack;
        }
        return null;
    }

    public ItemStack func_70304_b(int p_70304_1_) {
        return null;
    }

    public void func_70299_a(int slot, ItemStack stack) {
        if (slot == 0) {
            this.sample = stack;
        } else {
            this.nutrients = stack;
        }
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public String func_145825_b() {
        return "container.vat";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer p_70300_1_) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : p_70300_1_.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int slot, ItemStack stack) {
        if (slot == 0) {
            return stack.func_77973_b() == ThaumicHorizons.itemSyringeBloodSample || stack.func_77973_b() == Items.field_151076_bf || stack.func_77973_b() == Items.field_151082_bd || stack.func_77973_b() == Items.field_151147_al;
        }
        return stack.func_77973_b() == ThaumicHorizons.itemNutrients;
    }

    public int[] func_94128_d(int side) {
        return new int[]{0, 1};
    }

    public boolean func_102007_a(int slot, ItemStack item, int side) {
        return this.func_94041_b(slot, item);
    }

    public boolean func_102008_b(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        if (this.mode == 1) {
            return face == ForgeDirection.UP;
        }
        return false;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        if (this.mode == 1) {
            return face == ForgeDirection.UP;
        }
        return false;
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return false;
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
    }

    @Override
    public Aspect getSuctionType(ForgeDirection face) {
        if (this.mode != 2) {
            return null;
        }
        return this.currentlySucking;
    }

    @Override
    public int getSuctionAmount(ForgeDirection face) {
        return this.essentiaDemanded.size() > 0 ? 128 : 0;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return this.canInputFrom(face) ? amount - this.addToContainer(aspect, amount) : 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection face) {
        return null;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection face) {
        return 0;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }

    @Override
    public AspectList getAspects() {
        if (this.mode != 2) {
            if (this.myEssentia.getAspects().length > 0 && this.myEssentia.getAspects()[0] != null) {
                return this.myEssentia;
            }
            return null;
        }
        if (this.essentiaDemanded.getAspects().length > 0 && this.essentiaDemanded.getAspects()[0] != null) {
            return this.essentiaDemanded;
        }
        return null;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return this.currentlySucking != null && tag.getTag().equals(this.currentlySucking.getTag());
    }

    @Override
    public int addToContainer(Aspect tag, int amount) {
        if (this.mode != 2) {
            this.myEssentia.add(tag, amount);
        } else {
            this.essentiaDemanded.reduce(tag, amount);
        }
        this.clientEssentiaFX(tag);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
        return 0;
    }

    public void clientEssentiaFX(Aspect tag) {
        PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXEssentiaBubble((double)this.field_145851_c + 0.5, this.field_145848_d - 2, (double)this.field_145849_e + 0.5, tag.getColor()), new NetworkRegistry.TargetPoint(this.func_145831_w().field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
    }

    @Override
    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return this.containerContains(tag) >= amount;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return this.myEssentia.getAmount(tag);
    }

    public EntityLivingBase getEntityContained() {
        return this.entityContained;
    }

    public void setEntityContained(EntityLivingBase newEntity) {
        this.entityContained = newEntity;
        if (this.entityContained != null) {
            this.entityContained.func_70012_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d - 1.75, (double)this.field_145849_e + 0.5, 0.0f, 0.0f);
        }
    }

    public class SourceFX {
        public ChunkCoordinates loc;
        public int ticks;
        public int color;
        public int entity;

        public SourceFX(ChunkCoordinates loc, int ticks, int color) {
            this.loc = loc;
            this.ticks = ticks;
            this.color = color;
        }
    }
}

