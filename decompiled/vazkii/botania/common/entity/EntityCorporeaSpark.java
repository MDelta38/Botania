/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.botania.api.corporea.ICorporeaSpark;
import vazkii.botania.common.core.helper.InventoryHelper;
import vazkii.botania.common.entity.EntitySpark;
import vazkii.botania.common.item.ModItems;

public class EntityCorporeaSpark
extends Entity
implements ICorporeaSpark {
    private static final int SCAN_RANGE = 8;
    private static final String TAG_MASTER = "master";
    private static final String TAG_NETWORK = "network";
    private static final String TAG_INVIS = "invis";
    ICorporeaSpark master;
    List<ICorporeaSpark> connections = new ArrayList<ICorporeaSpark>();
    List<ICorporeaSpark> connectionsClient = new ArrayList<ICorporeaSpark>();
    List<ICorporeaSpark> relatives = new ArrayList<ICorporeaSpark>();
    boolean firstUpdateClient = true;
    boolean firstUpdateServer = true;

    public EntityCorporeaSpark(World world) {
        super(world);
        this.field_70178_ae = true;
    }

    protected void func_70088_a() {
        this.func_70105_a(0.1f, 0.5f);
        this.field_70180_af.func_75682_a(27, (Object)0);
        this.field_70180_af.func_75682_a(28, (Object)0);
        this.field_70180_af.func_75682_a(29, (Object)0);
        this.field_70180_af.func_75682_a(30, (Object)0);
        this.field_70180_af.func_75682_a(31, (Object)new ItemStack(Blocks.field_150348_b, 0, 0));
        this.field_70180_af.func_82708_h(27);
        this.field_70180_af.func_82708_h(28);
        this.field_70180_af.func_82708_h(29);
        this.field_70180_af.func_82708_h(30);
        this.field_70180_af.func_82708_h(31);
    }

    public boolean func_70067_L() {
        return true;
    }

    public void func_70071_h_() {
        int displayTicks;
        super.func_70071_h_();
        IInventory inv = this.getInventory();
        if (inv == null) {
            if (!this.field_70170_p.field_72995_K) {
                this.func_70106_y();
            }
            return;
        }
        if (this.isMaster()) {
            this.master = this;
        }
        if (this.field_70170_p.field_72995_K ? this.firstUpdateClient : this.firstUpdateServer) {
            if (this.isMaster()) {
                this.restartNetwork();
            } else {
                this.findNetwork();
            }
            if (this.field_70170_p.field_72995_K) {
                this.firstUpdateClient = false;
            } else {
                this.firstUpdateServer = false;
            }
        }
        if (this.master != null && (((Entity)this.master).field_70128_L || this.master.getNetwork() != this.getNetwork())) {
            this.master = null;
        }
        if ((displayTicks = this.getItemDisplayTicks()) > 0) {
            this.setItemDisplayTicks(displayTicks - 1);
        } else if (displayTicks < 0) {
            this.setItemDisplayTicks(displayTicks + 1);
        }
    }

    public void func_70106_y() {
        super.func_70106_y();
        if (!this.field_70170_p.field_72995_K) {
            this.func_70099_a(new ItemStack(ModItems.corporeaSpark, 1, this.isMaster() ? 1 : 0), 0.0f);
        }
        this.connections.remove(this);
        this.connectionsClient.remove(this);
        this.restartNetwork();
    }

    @Override
    public void registerConnections(ICorporeaSpark master, ICorporeaSpark referrer, List<ICorporeaSpark> connections) {
        List<ICorporeaSpark> sparks = this.getNearbySparks();
        this.relatives.clear();
        for (ICorporeaSpark spark : sparks) {
            if (spark == null || connections.contains(spark) || spark.getNetwork() != this.getNetwork() || spark.isMaster() || ((Entity)spark).field_70128_L) continue;
            connections.add(spark);
            this.relatives.add(spark);
            spark.registerConnections(master, this, connections);
        }
        this.master = master;
        if (this.field_70170_p.field_72995_K) {
            this.connectionsClient = connections;
        } else {
            this.connections = connections;
        }
    }

    List<ICorporeaSpark> getNearbySparks() {
        return this.field_70170_p.func_72872_a(ICorporeaSpark.class, AxisAlignedBB.func_72330_a((double)(this.field_70165_t - 8.0), (double)(this.field_70163_u - 8.0), (double)(this.field_70161_v - 8.0), (double)(this.field_70165_t + 8.0), (double)(this.field_70163_u + 8.0), (double)(this.field_70161_v + 8.0)));
    }

    void restartNetwork() {
        if (this.field_70170_p.field_72995_K) {
            this.connectionsClient = new ArrayList<ICorporeaSpark>();
        } else {
            this.connections = new ArrayList<ICorporeaSpark>();
        }
        this.relatives = new ArrayList<ICorporeaSpark>();
        if (this.master != null) {
            ICorporeaSpark oldMaster = this.master;
            this.master = null;
            oldMaster.registerConnections(oldMaster, this, new ArrayList<ICorporeaSpark>());
        }
    }

    void findNetwork() {
        List<ICorporeaSpark> sparks = this.getNearbySparks();
        if (sparks.size() > 0) {
            for (ICorporeaSpark spark : sparks) {
                ICorporeaSpark master;
                if (spark.getNetwork() != this.getNetwork() || ((Entity)spark).field_70128_L || (master = spark.getMaster()) == null) continue;
                this.master = master;
                this.restartNetwork();
                break;
            }
        }
    }

    void displayRelatives(ArrayList<ICorporeaSpark> checked, ICorporeaSpark spark) {
        if (spark == null) {
            return;
        }
        List<ICorporeaSpark> sparks = spark.getRelatives();
        if (sparks.isEmpty()) {
            EntitySpark.particleBeam((Entity)spark, (Entity)spark.getMaster());
        } else {
            for (ICorporeaSpark endSpark : sparks) {
                if (checked.contains(endSpark)) continue;
                EntitySpark.particleBeam((Entity)spark, (Entity)endSpark);
                checked.add(endSpark);
                this.displayRelatives(checked, endSpark);
            }
        }
    }

    @Override
    public IInventory getInventory() {
        int x = MathHelper.func_76128_c((double)this.field_70165_t);
        int y = MathHelper.func_76128_c((double)(this.field_70163_u - 1.0));
        int z = MathHelper.func_76128_c((double)this.field_70161_v);
        return InventoryHelper.getInventory(this.field_70170_p, x, y, z);
    }

    @Override
    public List<ICorporeaSpark> getConnections() {
        return this.field_70170_p.field_72995_K ? this.connectionsClient : this.connections;
    }

    @Override
    public List<ICorporeaSpark> getRelatives() {
        return this.relatives;
    }

    @Override
    public void onItemExtracted(ItemStack stack) {
        this.setItemDisplayTicks(10);
        this.setDisplayedItem(stack);
    }

    @Override
    public void onItemsRequested(List<ItemStack> stacks) {
        if (!stacks.isEmpty()) {
            this.setItemDisplayTicks(-10);
            this.setDisplayedItem(stacks.get(0));
        }
    }

    @Override
    public ICorporeaSpark getMaster() {
        return this.master;
    }

    public void setMaster(boolean master) {
        this.field_70180_af.func_75692_b(28, (Object)(master ? 1 : 0));
    }

    @Override
    public boolean isMaster() {
        return this.field_70180_af.func_75679_c(28) == 1;
    }

    public void setNetwork(int network) {
        this.field_70180_af.func_75692_b(29, (Object)network);
    }

    @Override
    public int getNetwork() {
        return this.field_70180_af.func_75679_c(29);
    }

    public int getItemDisplayTicks() {
        return this.field_70180_af.func_75679_c(30);
    }

    public void setItemDisplayTicks(int ticks) {
        this.field_70180_af.func_75692_b(30, (Object)ticks);
    }

    public ItemStack getDisplayedItem() {
        return this.field_70180_af.func_82710_f(31);
    }

    public void setDisplayedItem(ItemStack stack) {
        this.field_70180_af.func_75692_b(31, (Object)stack);
    }

    public boolean func_130002_c(EntityPlayer player) {
        ItemStack stack = player.func_71045_bC();
        if (stack != null) {
            int color;
            if (stack.func_77973_b() == ModItems.twigWand) {
                if (player.func_70093_af()) {
                    this.func_70106_y();
                    if (this.isMaster()) {
                        this.restartNetwork();
                    }
                    if (player.field_70170_p.field_72995_K) {
                        player.func_71038_i();
                    }
                    return true;
                }
                this.displayRelatives(new ArrayList<ICorporeaSpark>(), this.master);
                return true;
            }
            if (stack.func_77973_b() == ModItems.dye && (color = stack.func_77960_j()) != this.getNetwork()) {
                this.setNetwork(color);
                if (this.master != null) {
                    this.restartNetwork();
                } else {
                    this.findNetwork();
                }
                --stack.field_77994_a;
                if (player.field_70170_p.field_72995_K) {
                    player.func_71038_i();
                }
            }
        }
        return this.doPhantomInk(stack);
    }

    public boolean doPhantomInk(ItemStack stack) {
        if (stack != null && stack.func_77973_b() == ModItems.phantomInk && !this.field_70170_p.field_72995_K) {
            int invis = this.field_70180_af.func_75679_c(27);
            this.field_70180_af.func_75692_b(27, (Object)(~invis & 1));
            return true;
        }
        return false;
    }

    protected void func_70037_a(NBTTagCompound cmp) {
        this.setMaster(cmp.func_74767_n(TAG_MASTER));
        this.setNetwork(cmp.func_74762_e(TAG_NETWORK));
        this.field_70180_af.func_75692_b(27, (Object)cmp.func_74762_e(TAG_INVIS));
    }

    protected void func_70014_b(NBTTagCompound cmp) {
        cmp.func_74757_a(TAG_MASTER, this.isMaster());
        cmp.func_74768_a(TAG_NETWORK, this.getNetwork());
        cmp.func_74768_a(TAG_INVIS, this.field_70180_af.func_75679_c(27));
    }
}

