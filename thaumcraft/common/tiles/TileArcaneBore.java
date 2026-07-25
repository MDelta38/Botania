/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.util.FakePlayer
 *  net.minecraftforge.common.util.FakePlayerFactory
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRepairableExtended;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.IWandable;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.equipment.ItemElementalPickaxe;
import thaumcraft.common.items.wands.foci.ItemFocusExcavation;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.misc.PacketBoreDig;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.lib.utils.TCVec3;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.tiles.TileArcaneBoreBase;
import thaumcraft.common.tiles.TileArcaneLamp;

public class TileArcaneBore
extends TileThaumcraft
implements IInventory,
IWandable {
    public int spiral = 0;
    public float currentRadius = 0.0f;
    public int maxRadius = 2;
    public float vRadX = 0.0f;
    public float vRadZ = 0.0f;
    public float tRadX = 0.0f;
    public float tRadZ = 0.0f;
    public float mRadX = 0.0f;
    public float mRadZ = 0.0f;
    private int count = 0;
    public int topRotation = 0;
    long soundDelay = 0L;
    Object beam1 = null;
    Object beam2 = null;
    int beamlength = 0;
    TileArcaneBoreBase base = null;
    public ItemStack[] contents = new ItemStack[2];
    public int rotX = 0;
    public int rotZ = 0;
    public int tarX = 0;
    public int tarZ = 0;
    public int speedX = 0;
    public int speedZ = 0;
    public boolean hasFocus = false;
    public boolean hasPickaxe = false;
    int lastX = 0;
    int lastZ = 0;
    int lastY = 0;
    boolean toDig = false;
    int digX = 0;
    int digZ = 0;
    int digY = 0;
    Block digBlock = Blocks.field_150350_a;
    int digMd = 0;
    float radInc = 0.0f;
    int paused = 100;
    int maxPause = 100;
    long repairCounter = 0L;
    boolean first = true;
    public ForgeDirection orientation = ForgeDirection.getOrientation((int)1);
    public ForgeDirection baseOrientation = ForgeDirection.getOrientation((int)1);
    FakePlayer fakePlayer = null;
    private AspectList repairCost = new AspectList();
    private AspectList currentRepairVis = new AspectList();
    public int fortune = 0;
    public int speed = 0;
    public int area = 0;
    int blockCount = 0;
    private float speedyTime;
    private final int itemsPerVis = 20;

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (!this.field_145850_b.field_72995_K && this.speedyTime < 20.0f) {
            this.speedyTime += (float)VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.ENTROPY, 100) / 5.0f;
            if (this.speedyTime < 20.0f && this.base != null && this.base.drawEssentia()) {
                this.getClass();
                this.speedyTime += 20.0f;
            }
        }
        if (!this.field_145850_b.field_72995_K && this.fakePlayer == null) {
            this.fakePlayer = FakePlayerFactory.get((WorldServer)((WorldServer)this.field_145850_b), (GameProfile)new GameProfile((UUID)null, "FakeThaumcraftBore"));
        }
        if (this.field_145850_b.field_72995_K && this.first) {
            this.setOrientation(this.orientation, true);
            this.first = false;
        }
        if (this.rotX < this.tarX) {
            this.rotX += this.speedX;
            this.speedX = this.rotX < this.tarX ? ++this.speedX : (int)((float)this.speedX / 3.0f);
        } else if (this.rotX > this.tarX) {
            this.rotX += this.speedX;
            this.speedX = this.rotX > this.tarX ? --this.speedX : (int)((float)this.speedX / 3.0f);
        } else {
            this.speedX = 0;
        }
        if (this.rotZ < this.tarZ) {
            this.rotZ += this.speedZ;
            this.speedZ = this.rotZ < this.tarZ ? ++this.speedZ : (int)((float)this.speedZ / 3.0f);
        } else if (this.rotZ > this.tarZ) {
            this.rotZ += this.speedZ;
            this.speedZ = this.rotZ > this.tarZ ? --this.speedZ : (int)((float)this.speedZ / 3.0f);
        } else {
            this.speedZ = 0;
        }
        if (this.gettingPower() && this.areItemsValid()) {
            this.dig();
        } else if (this.field_145850_b.field_72995_K) {
            if (this.topRotation % 90 != 0) {
                this.topRotation += Math.min(10, 90 - this.topRotation % 90);
            }
            this.vRadX *= 0.9f;
            this.vRadZ *= 0.9f;
        }
        if (!this.field_145850_b.field_72995_K && this.hasPickaxe && this.func_70301_a(1) != null) {
            if (this.repairCounter++ % 40L == 0L && this.func_70301_a(1).func_77951_h()) {
                this.doRepair(this.func_70301_a(1), (EntityPlayer)this.fakePlayer);
            }
            if (this.repairCost != null && this.repairCost.size() > 0 && this.repairCounter % 5L == 0L) {
                for (Aspect a : this.repairCost.getAspects()) {
                    if (this.currentRepairVis.getAmount(a) >= this.repairCost.getAmount(a)) continue;
                    this.currentRepairVis.add(a, VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, a, this.repairCost.getAmount(a)));
                }
            }
            this.fakePlayer.field_70173_aa = (int)this.repairCounter;
            try {
                this.func_70301_a(1).func_77945_a(this.field_145850_b, (Entity)this.fakePlayer, 0, true);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private void doRepair(ItemStack is, EntityPlayer player) {
        int level = EnchantmentHelper.func_77506_a((int)Config.enchRepair.field_77352_x, (ItemStack)is);
        if (level <= 0) {
            return;
        }
        if (level > 2) {
            level = 2;
        }
        if (is.func_77973_b() instanceof IRepairable) {
            AspectList cost = ThaumcraftCraftingManager.getObjectTags(is);
            if (cost == null || cost.size() == 0) {
                return;
            }
            cost = ResearchManager.reduceToPrimals(cost);
            for (Aspect a : cost.getAspects()) {
                if (a == null) continue;
                this.repairCost.merge(a, (int)Math.sqrt(cost.getAmount(a) * 2) * level);
            }
            boolean doIt = true;
            if (is.func_77973_b() instanceof IRepairableExtended) {
                doIt = ((IRepairableExtended)is.func_77973_b()).doRepair(is, player, level);
            }
            if (doIt) {
                for (Aspect a : this.repairCost.getAspects()) {
                    if (this.currentRepairVis.getAmount(a) >= this.repairCost.getAmount(a)) continue;
                    doIt = false;
                    break;
                }
            }
            if (doIt) {
                for (Aspect a : this.repairCost.getAspects()) {
                    this.currentRepairVis.reduce(a, this.repairCost.getAmount(a));
                }
                is.func_77972_a(-level, (EntityLivingBase)player);
                this.func_70296_d();
            }
        } else {
            this.repairCost = new AspectList();
        }
    }

    private boolean areItemsValid() {
        boolean notNearBroken = true;
        if (this.hasPickaxe && this.func_70301_a(1).func_77960_j() + 1 >= this.func_70301_a(1).func_77958_k()) {
            notNearBroken = false;
        }
        return this.hasFocus && this.hasPickaxe && this.func_70301_a(1).func_77984_f() && notNearBroken;
    }

    public void func_70296_d() {
        super.func_70296_d();
        this.fortune = 0;
        this.area = 0;
        this.speed = 0;
        if (this.func_70301_a(0) != null && this.func_70301_a(0).func_77973_b() instanceof ItemFocusExcavation) {
            this.fortune = ((ItemFocusExcavation)this.func_70301_a(0).func_77973_b()).getUpgradeLevel(this.func_70301_a(0), FocusUpgradeType.treasure);
            this.area = ((ItemFocusExcavation)this.func_70301_a(0).func_77973_b()).getUpgradeLevel(this.func_70301_a(0), FocusUpgradeType.enlarge);
            this.speed += ((ItemFocusExcavation)this.func_70301_a(0).func_77973_b()).getUpgradeLevel(this.func_70301_a(0), FocusUpgradeType.potency);
            this.hasFocus = true;
        } else {
            this.hasFocus = false;
        }
        if (this.func_70301_a(1) != null && this.func_70301_a(1).func_77973_b() instanceof ItemPickaxe) {
            this.hasPickaxe = true;
            int f = EnchantmentHelper.func_77506_a((int)Enchantment.field_77346_s.field_77352_x, (ItemStack)this.func_70301_a(1));
            if (f > this.fortune) {
                this.fortune = f;
            }
            this.speed += EnchantmentHelper.func_77506_a((int)Enchantment.field_77349_p.field_77352_x, (ItemStack)this.func_70301_a(1));
        } else {
            this.hasPickaxe = false;
        }
    }

    private void dig() {
        if (this.rotX != this.tarX || this.rotZ != this.tarZ) {
            if (this.field_145850_b.field_72995_K) {
                if (this.topRotation % 90 != 0) {
                    this.topRotation += Math.min(10, 90 - this.topRotation % 90);
                }
                this.vRadX *= 0.9f;
                this.vRadZ *= 0.9f;
            }
            return;
        }
        if (!this.field_145850_b.field_72995_K) {
            boolean dug = false;
            if (this.base == null) {
                this.base = (TileArcaneBoreBase)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + this.baseOrientation.getOpposite().offsetY, this.field_145849_e);
            }
            if (--this.count > 0) {
                return;
            }
            if (this.toDig) {
                this.toDig = false;
                Block bi = this.field_145850_b.func_147439_a(this.digX, this.digY, this.digZ);
                int md = this.field_145850_b.func_72805_g(this.digX, this.digY, this.digZ);
                if (!bi.isAir((IBlockAccess)this.field_145850_b, this.digX, this.digY, this.digZ)) {
                    int tfortune = this.fortune;
                    boolean silktouch = false;
                    if (this.func_70301_a(1) != null && EnchantmentHelper.func_77506_a((int)Enchantment.field_77348_q.field_77352_x, (ItemStack)this.func_70301_a(1)) > 0 && bi.canSilkHarvest(this.field_145850_b, null, this.digX, this.digY, this.digZ, md)) {
                        silktouch = true;
                        tfortune = 0;
                    }
                    if (!silktouch && this.func_70301_a(0) != null && ((ItemFocusExcavation)this.func_70301_a(0).func_77973_b()).isUpgradedWith(this.func_70301_a(0), FocusUpgradeType.silktouch) && bi.canSilkHarvest(this.field_145850_b, null, this.digX, this.digY, this.digZ, md)) {
                        silktouch = true;
                        tfortune = 0;
                    }
                    this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockWoodenDevice, 99, Block.func_149682_b((Block)bi) + (md << 12));
                    ArrayList items = new ArrayList();
                    if (silktouch) {
                        ItemStack dropped = BlockUtils.createStackedBlock(bi, md);
                        if (dropped != null) {
                            items.add(dropped);
                        }
                    } else {
                        items = bi.getDrops(this.field_145850_b, this.digX, this.digY, this.digZ, md, tfortune);
                    }
                    List targets = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)this.digX, (double)this.digY, (double)this.digZ, (double)(this.digX + 1), (double)(this.digY + 1), (double)(this.digZ + 1)).func_72314_b(1.0, 1.0, 1.0));
                    if (targets.size() > 0) {
                        for (EntityItem e : targets) {
                            items.add(e.func_92059_d().func_77946_l());
                            e.func_70106_y();
                        }
                    }
                    if (items.size() > 0) {
                        for (ItemStack is : items) {
                            ItemStack dropped = is.func_77946_l();
                            if (!silktouch && (this.func_70301_a(1) != null && this.func_70301_a(1).func_77973_b() instanceof ItemElementalPickaxe || this.func_70301_a(0) != null && this.func_70301_a(0).func_77973_b() instanceof ItemFocusBasic && ((ItemFocusBasic)this.func_70301_a(0).func_77973_b()).isUpgradedWith(this.func_70301_a(0), ItemFocusExcavation.dowsing))) {
                                dropped = Utils.findSpecialMiningResult(is, 0.2f + (float)tfortune * 0.075f, this.field_145850_b.field_73012_v);
                            }
                            if (this.base == null || !(this.base instanceof TileArcaneBoreBase)) continue;
                            TileEntity inventory = this.field_145850_b.func_147438_o(this.base.field_145851_c + this.base.orientation.offsetX, this.base.field_145848_d, this.base.field_145849_e + this.base.orientation.offsetZ);
                            if (inventory != null && inventory instanceof IInventory) {
                                dropped = InventoryUtils.placeItemStackIntoInventory(dropped, (IInventory)inventory, this.base.orientation.getOpposite().ordinal(), true);
                            }
                            if (dropped == null) continue;
                            EntityItem ei = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5 + (double)this.base.orientation.offsetX * 0.66, (double)this.field_145848_d + 0.4 + (double)this.baseOrientation.getOpposite().offsetY, (double)this.field_145849_e + 0.5 + (double)this.base.orientation.offsetZ * 0.66, dropped.func_77946_l());
                            ei.field_70159_w = 0.075f * (float)this.base.orientation.offsetX;
                            ei.field_70181_x = 0.025f;
                            ei.field_70179_y = 0.075f * (float)this.base.orientation.offsetZ;
                            this.field_145850_b.func_72838_d((Entity)ei);
                        }
                    }
                }
                this.func_70299_a(1, InventoryUtils.damageItem(1, this.func_70301_a(1), this.field_145850_b));
                if (this.func_70301_a((int)1).field_77994_a <= 0) {
                    this.func_70299_a(1, null);
                }
                this.field_145850_b.func_147468_f(this.digX, this.digY, this.digZ);
                if (this.base != null) {
                    for (int lb = 2; lb < 6; ++lb) {
                        ForgeDirection lbd = ForgeDirection.getOrientation((int)lb);
                        TileEntity lbte = this.field_145850_b.func_147438_o(this.base.field_145851_c + lbd.offsetX, this.base.field_145848_d, this.base.field_145849_e + lbd.offsetZ);
                        if (lbte == null || !(lbte instanceof TileArcaneLamp)) continue;
                        int d = this.field_145850_b.field_73012_v.nextInt(32) * 2;
                        int xx = this.field_145851_c + this.orientation.offsetX + this.orientation.offsetX * d;
                        int yy = this.field_145848_d + this.orientation.offsetY + this.orientation.offsetY * d;
                        int zz = this.field_145849_e + this.orientation.offsetZ + this.orientation.offsetZ * d;
                        int p = d / 2 % 4;
                        if (this.orientation.offsetX != 0) {
                            zz += p == 0 ? 3 : (p == 1 || p == 3 ? 0 : -3);
                        } else {
                            xx += p == 0 ? 3 : (p == 1 || p == 3 ? 0 : -3);
                        }
                        if (p == 3 && this.orientation.offsetY == 0) {
                            yy -= 2;
                        }
                        if (!this.field_145850_b.func_147437_c(xx, yy, zz) || this.field_145850_b.func_147439_a(xx, yy, zz) == ConfigBlocks.blockAiry || this.field_145850_b.func_72957_l(xx, yy, zz) >= 15) break;
                        this.field_145850_b.func_147465_d(xx, yy, zz, ConfigBlocks.blockAiry, 3, 3);
                        break;
                    }
                }
                dug = true;
            }
            this.findNextBlockToDig();
            if (dug && this.speedyTime > 0.0f) {
                this.speedyTime -= 1.0f;
            }
        } else {
            ++this.paused;
            if (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
                this.func_145843_s();
            }
            if (this.paused < this.maxPause && this.soundDelay < System.currentTimeMillis()) {
                this.soundDelay = System.currentTimeMillis() + 1200L + (long)this.field_145850_b.field_73012_v.nextInt(100);
                this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:rumble", 0.25f, 0.9f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f, false);
            }
            if (this.beamlength > 0 && this.paused > this.maxPause) {
                --this.beamlength;
            }
            if (this.toDig) {
                this.paused = 0;
                this.beamlength = 64;
                Block block = this.field_145850_b.func_147439_a(this.digX, this.digY, this.digZ);
                int md = this.field_145850_b.func_72805_g(this.digX, this.digY, this.digZ);
                this.maxPause = block != null ? 10 + Math.max(10 - this.speed, (int)(block.func_149712_f(this.field_145850_b, this.digX, this.digY, this.digZ) * 2.0f) - this.speed * 2) : 20;
                if (this.speedyTime <= 0.0f) {
                    this.maxPause *= 4;
                }
                this.toDig = false;
                double xd = (double)this.field_145851_c + 0.5 - ((double)this.digX + 0.5);
                double yd = (double)this.field_145848_d + 0.5 - ((double)this.digY + 0.5);
                double zd = (double)this.field_145849_e + 0.5 - ((double)this.digZ + 0.5);
                double var12 = MathHelper.func_76133_a((double)(xd * xd + zd * zd));
                float rx = (float)(Math.atan2(zd, xd) * 180.0 / Math.PI);
                float rz = (float)(-(Math.atan2(yd, var12) * 180.0 / Math.PI)) + 90.0f;
                this.tRadX = MathHelper.func_76142_g((float)this.rotX) + rx;
                if (this.orientation.ordinal() == 5) {
                    if (this.tRadX > 180.0f) {
                        this.tRadX -= 360.0f;
                    }
                    if (this.tRadX < -180.0f) {
                        this.tRadX += 360.0f;
                    }
                }
                this.tRadZ = rz - (float)this.rotZ;
                if (this.orientation.ordinal() <= 1) {
                    this.tRadZ += 180.0f;
                    if (this.vRadX - this.tRadX >= 180.0f) {
                        this.vRadX -= 360.0f;
                    }
                    if (this.vRadX - this.tRadX <= -180.0f) {
                        this.vRadX += 360.0f;
                    }
                }
                this.mRadX = Math.abs((this.vRadX - this.tRadX) / 6.0f);
                this.mRadZ = Math.abs((this.vRadZ - this.tRadZ) / 6.0f);
                if (this.speedyTime > 0.0f) {
                    this.speedyTime -= 1.0f;
                }
            }
            if (this.paused < this.maxPause) {
                if (this.vRadX < this.tRadX) {
                    this.vRadX += this.mRadX;
                } else if (this.vRadX > this.tRadX) {
                    this.vRadX -= this.mRadX;
                }
                if (this.vRadZ < this.tRadZ) {
                    this.vRadZ += this.mRadZ;
                } else if (this.vRadZ > this.tRadZ) {
                    this.vRadZ -= this.mRadZ;
                }
            } else {
                this.vRadX *= 0.9f;
                this.vRadZ *= 0.9f;
            }
            this.mRadX *= 0.9f;
            this.mRadZ *= 0.9f;
            float vx = (float)(this.rotX + 90) - this.vRadX;
            float vz = (float)(this.rotZ + 90) - this.vRadZ;
            float var3 = 1.0f;
            float dX = MathHelper.func_76126_a((float)(vx / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(vz / 180.0f * (float)Math.PI)) * var3;
            float dZ = MathHelper.func_76134_b((float)(vx / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(vz / 180.0f * (float)Math.PI)) * var3;
            float dY = MathHelper.func_76126_a((float)(vz / 180.0f * (float)Math.PI)) * var3;
            Vec3 var13 = Vec3.func_72443_a((double)((double)this.field_145851_c + 0.5 + (double)dX), (double)((double)this.field_145848_d + 0.5 + (double)dY), (double)((double)this.field_145849_e + 0.5 + (double)dZ));
            Vec3 var14 = Vec3.func_72443_a((double)((double)this.field_145851_c + 0.5 + (double)(dX * (float)this.beamlength)), (double)((double)this.field_145848_d + 0.5 + (double)(dY * (float)this.beamlength)), (double)((double)this.field_145849_e + 0.5 + (double)(dZ * (float)this.beamlength)));
            MovingObjectPosition mop = this.field_145850_b.func_147447_a(var13, var14, false, true, false);
            int impact = 0;
            float length = 64.0f;
            double bx = var14.field_72450_a;
            double by = var14.field_72448_b;
            double bz = var14.field_72449_c;
            if (mop != null) {
                int z;
                int y;
                double xd = (double)this.field_145851_c + 0.5 + (double)dX - mop.field_72307_f.field_72450_a;
                double yd = (double)this.field_145848_d + 0.5 + (double)dY - mop.field_72307_f.field_72448_b;
                double zd = (double)this.field_145849_e + 0.5 + (double)dZ - mop.field_72307_f.field_72449_c;
                bx = mop.field_72307_f.field_72450_a;
                by = mop.field_72307_f.field_72448_b;
                bz = mop.field_72307_f.field_72449_c;
                length = MathHelper.func_76133_a((double)(xd * xd + yd * yd + zd * zd)) + 0.5f;
                impact = 5;
                int x = MathHelper.func_76128_c((double)bx);
                if (!this.field_145850_b.func_147437_c(x, y = MathHelper.func_76128_c((double)by), z = MathHelper.func_76128_c((double)bz))) {
                    Thaumcraft.proxy.boreDigFx(this.field_145850_b, x, y, z, this.field_145851_c + this.orientation.offsetX, this.field_145848_d + this.orientation.offsetY, this.field_145849_e + this.orientation.offsetZ, this.field_145850_b.func_147439_a(x, y, z), this.field_145850_b.func_72805_g(x, y, z) >> 12 & 0xFF);
                }
            }
            this.topRotation += this.beamlength / 6;
            this.beam1 = Thaumcraft.proxy.beamBore(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, bx, by, bz, 1, 65382, true, impact > 0 ? 2.0f : 0.0f, this.beam1, impact);
            this.beam2 = Thaumcraft.proxy.beamBore(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, bx, by, bz, 2, 0xFF8855, false, impact > 0 ? 2.0f : 0.0f, this.beam2, impact);
            if (this.field_145850_b.func_147437_c(this.digX, this.digY, this.digZ) && this.digBlock != Blocks.field_150350_a) {
                this.field_145850_b.func_72980_b((double)((float)this.digX + 0.5f), (double)((float)this.digY + 0.5f), (double)((float)this.digZ + 0.5f), this.digBlock.field_149762_H.func_150495_a(), (this.digBlock.field_149762_H.func_150497_c() + 1.0f) / 2.0f, this.digBlock.field_149762_H.func_150494_d() * 0.8f, false);
                for (int a = 0; a < Thaumcraft.proxy.particleCount(10); ++a) {
                    Thaumcraft.proxy.boreDigFx(this.field_145850_b, this.digX, this.digY, this.digZ, this.field_145851_c + this.orientation.offsetX, this.field_145848_d + this.orientation.offsetY, this.field_145849_e + this.orientation.offsetZ, this.digBlock, this.digMd >> 12 & 0xFF);
                }
                this.digBlock = Blocks.field_150350_a;
            }
        }
    }

    private void findNextBlockToDig() {
        if (this.radInc == 0.0f) {
            this.radInc = (float)(this.maxRadius + this.area) / 360.0f;
        }
        int x = this.lastX;
        int z = this.lastZ;
        int y = this.lastY;
        while (x == this.lastX && z == this.lastZ && y == this.lastY) {
            this.spiral += 2;
            if (this.spiral >= 360) {
                this.spiral -= 360;
            }
            this.currentRadius += this.radInc;
            if (this.currentRadius > (float)(this.maxRadius + this.area) || this.currentRadius < (float)(-(this.maxRadius + this.area))) {
                this.radInc *= -1.0f;
            }
            TCVec3 vsource = TCVec3.createVectorHelper((double)(this.field_145851_c + this.orientation.offsetX) + 0.5, (double)(this.field_145848_d + this.orientation.offsetY) + 0.5, (double)(this.field_145849_e + this.orientation.offsetZ) + 0.5);
            TCVec3 vtar = TCVec3.createVectorHelper(0.0, this.currentRadius, 0.0);
            vtar.rotateAroundZ((float)this.spiral / 180.0f * (float)Math.PI);
            vtar.rotateAroundY(1.5707964f * (float)this.orientation.offsetX);
            vtar.rotateAroundX(1.5707964f * (float)this.orientation.offsetY);
            TCVec3 vres = vsource.addVector(vtar.xCoord, vtar.yCoord, vtar.zCoord);
            x = MathHelper.func_76128_c((double)vres.xCoord);
            y = MathHelper.func_76128_c((double)vres.yCoord);
            z = MathHelper.func_76128_c((double)vres.zCoord);
        }
        this.lastX = x;
        this.lastZ = z;
        this.lastY = y;
        x += this.orientation.offsetX;
        y += this.orientation.offsetY;
        z += this.orientation.offsetZ;
        for (int depth = 0; depth < 64; ++depth) {
            Vec3 var14;
            Block block = this.field_145850_b.func_147439_a(x += this.orientation.offsetX, y += this.orientation.offsetY, z += this.orientation.offsetZ);
            int md = this.field_145850_b.func_72805_g(x, y, z);
            if (block != null && block.func_149712_f(this.field_145850_b, x, y, z) < 0.0f) break;
            if (this.field_145850_b.func_147437_c(x, y, z) || block == null || !block.func_149678_a(md, false) || block.func_149668_a(this.field_145850_b, x, y, z) == null) continue;
            this.digX = x;
            this.digY = y;
            this.digZ = z;
            if (++this.blockCount > 2) {
                this.blockCount = 0;
            }
            this.count = Math.max(10 - this.speed, (int)(block.func_149712_f(this.field_145850_b, x, y, z) * 2.0f) - this.speed * 2);
            if (this.speedyTime < 1.0f) {
                this.count *= 4;
            }
            this.toDig = true;
            Vec3 var13 = Vec3.func_72443_a((double)((double)this.field_145851_c + 0.5 + (double)this.orientation.offsetX), (double)((double)this.field_145848_d + 0.5 + (double)this.orientation.offsetY), (double)((double)this.field_145849_e + 0.5 + (double)this.orientation.offsetZ));
            MovingObjectPosition mop = this.field_145850_b.func_147447_a(var13, var14 = Vec3.func_72443_a((double)((double)this.digX + 0.5), (double)((double)this.digY + 0.5), (double)((double)this.digZ + 0.5)), false, true, false);
            if (mop != null) {
                block = this.field_145850_b.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                md = this.field_145850_b.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                if (block.func_149712_f(this.field_145850_b, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) > -1.0f && block.func_149668_a(this.field_145850_b, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) != null) {
                    this.count = Math.max(10 - this.speed, (int)(block.func_149712_f(this.field_145850_b, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) * 2.0f) - this.speed * 2);
                    if (this.speedyTime < 1.0f) {
                        this.count *= 4;
                    }
                    this.digX = mop.field_72311_b;
                    this.digY = mop.field_72312_c;
                    this.digZ = mop.field_72309_d;
                }
            }
            this.sendDigEvent();
            break;
        }
    }

    public boolean gettingPower() {
        return this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e) || this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d + this.baseOrientation.getOpposite().offsetY, this.field_145849_e);
    }

    public void setOrientation(ForgeDirection or, boolean initial) {
        this.orientation = or;
        this.lastX = 0;
        this.lastZ = 0;
        switch (or.ordinal()) {
            case 0: {
                this.tarZ = 180;
                this.tarX = 0;
                break;
            }
            case 1: {
                this.tarZ = 0;
                this.tarX = 0;
                break;
            }
            case 2: {
                this.tarZ = 90;
                this.tarX = 270;
                break;
            }
            case 3: {
                this.tarZ = 90;
                this.tarX = 90;
                break;
            }
            case 4: {
                this.tarZ = 90;
                this.tarX = 0;
                break;
            }
            case 5: {
                this.tarZ = 90;
                this.tarX = 180;
            }
        }
        if (initial) {
            this.rotX = this.tarX;
            this.rotZ = this.tarZ;
        }
        this.toDig = false;
        this.radInc = 0.0f;
        this.paused = 100;
        this.tRadX = 0.0f;
        this.tRadZ = 0.0f;
        this.mRadX = 0.0f;
        this.mRadZ = 0.0f;
        this.digX = 0;
        this.digY = 0;
        this.digZ = 0;
        if (this.field_145850_b != null) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    @Override
    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.speedyTime = nbttagcompound.func_74765_d("SpeedyTime");
        this.setOrientation(this.orientation, true);
    }

    @Override
    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74777_a("SpeedyTime", (short)this.speedyTime);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.orientation = ForgeDirection.getOrientation((int)nbttagcompound.func_74762_e("orientation"));
        this.baseOrientation = ForgeDirection.getOrientation((int)nbttagcompound.func_74762_e("baseOrientation"));
        NBTTagList var2 = nbttagcompound.func_150295_c("Inventory", 10);
        this.contents = new ItemStack[this.func_70302_i_()];
        for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            NBTTagCompound var4 = var2.func_150305_b(var3);
            int var5 = var4.func_74771_c("Slot") & 0xFF;
            if (var5 < 0 || var5 >= this.contents.length) continue;
            this.contents[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
        }
        this.func_70296_d();
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("orientation", this.orientation.ordinal());
        nbttagcompound.func_74768_a("baseOrientation", this.baseOrientation.ordinal());
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.contents.length; ++var3) {
            if (this.contents[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a("Slot", (byte)var3);
            this.contents[var3].func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        nbttagcompound.func_74782_a("Inventory", (NBTBase)var2);
    }

    public boolean func_145842_c(int i, int j) {
        if (i == 99) {
            try {
                Block var40;
                if (this.field_145850_b.field_72995_K && (j & 0xFFF) > 0 && (var40 = Block.func_149729_e((int)(j & 0xFFF))) != null) {
                    this.field_145850_b.func_72980_b((double)((float)this.digX + 0.5f), (double)((float)this.digY + 0.5f), (double)((float)this.digZ + 0.5f), var40.field_149762_H.func_150495_a(), (var40.field_149762_H.func_150497_c() + 1.0f) / 2.0f, var40.field_149762_H.func_150494_d() * 0.8f, false);
                    for (int a = 0; a < Thaumcraft.proxy.particleCount(10); ++a) {
                        Thaumcraft.proxy.boreDigFx(this.field_145850_b, this.digX, this.digY, this.digZ, this.field_145851_c + this.orientation.offsetX, this.field_145848_d + this.orientation.offsetY, this.field_145849_e + this.orientation.offsetZ, var40, j >> 12 & 0xFF);
                    }
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            return true;
        }
        return super.func_145842_c(i, j);
    }

    public void getDigEvent(int j) {
        int x = (j >> 16 & 0xFF) - 64;
        int y = (j >> 8 & 0xFF) - 64;
        int z = (j & 0xFF) - 64;
        this.digX = this.field_145851_c + x;
        this.digY = this.field_145848_d + y;
        this.digZ = this.field_145849_e + z;
        this.toDig = true;
        this.digBlock = this.field_145850_b.func_147439_a(this.digX, this.digY, this.digZ);
        this.digMd = this.field_145850_b.func_72805_g(this.digX, this.digY, this.digZ);
    }

    public void sendDigEvent() {
        int x = this.digX - this.field_145851_c + 64;
        int y = this.digY - this.field_145848_d + 64;
        int z = this.digZ - this.field_145849_e + 64;
        int c = (x & 0xFF) << 16 | (y & 0xFF) << 8 | z & 0xFF;
        PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketBoreDig(this.field_145851_c, this.field_145848_d, this.field_145849_e, c), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 64.0));
    }

    public int func_70302_i_() {
        return 2;
    }

    public ItemStack func_70301_a(int var1) {
        return this.contents[var1];
    }

    public ItemStack func_70298_a(int var1, int var2) {
        if (this.contents[var1] != null) {
            if (this.contents[var1].field_77994_a <= var2) {
                ItemStack var3 = this.contents[var1];
                this.contents[var1] = null;
                this.func_70296_d();
                return var3;
            }
            ItemStack var3 = this.contents[var1].func_77979_a(var2);
            if (this.contents[var1].field_77994_a == 0) {
                this.contents[var1] = null;
            }
            this.func_70296_d();
            return var3;
        }
        return null;
    }

    public ItemStack func_70304_b(int var1) {
        if (this.contents[var1] != null) {
            ItemStack var2 = this.contents[var1];
            this.contents[var1] = null;
            return var2;
        }
        return null;
    }

    public void func_70299_a(int var1, ItemStack var2) {
        this.contents[var1] = var2;
        if (var2 != null && var2.field_77994_a > this.func_70297_j_()) {
            var2.field_77994_a = this.func_70297_j_();
        }
        this.func_70296_d();
    }

    public String func_145825_b() {
        return "Arcane Bore";
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer var1) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : var1.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_145818_k_() {
        return false;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        this.setOrientation(ForgeDirection.getOrientation((int)side), false);
        player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:tool", 0.5f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
        player.func_71038_i();
        this.func_70296_d();
        return 0;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        return null;
    }

    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }
}

