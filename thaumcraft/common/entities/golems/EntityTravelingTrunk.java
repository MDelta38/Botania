/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IEntityOwnable
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package thaumcraft.common.entities.golems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.golems.InventoryTrunk;
import thaumcraft.common.lib.events.EventHandlerEntity;
import thaumcraft.common.lib.utils.InventoryUtils;

public class EntityTravelingTrunk
extends EntityLiving
implements IEntityOwnable {
    public int slotCount = 27;
    public InventoryTrunk inventory = new InventoryTrunk(this, this.slotCount);
    public float lidrot;
    public float field_768_a;
    public float field_767_b;
    private int jumpDelay = 0;
    private int eatDelay = 0;

    public EntityTravelingTrunk(World world) {
        super(world);
        this.field_70156_m = true;
        this.jumpDelay = this.field_70146_Z.nextInt(20) + 10;
        this.field_70178_ae = true;
        this.field_70174_ab = 10;
        this.lidrot = 0.0f;
        this.func_110163_bv();
        this.func_70105_a(0.8f, 0.8f);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(75.0);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(15, (Object)new Byte(0));
        this.field_70180_af.func_75682_a(16, (Object)new Byte(0));
        this.field_70180_af.func_75682_a(17, (Object)"");
        this.field_70180_af.func_75682_a(18, (Object)-1);
        this.field_70180_af.func_75682_a(19, (Object)new Byte(0));
        this.field_70180_af.func_75682_a(20, (Object)new Short(0));
    }

    public boolean func_70097_a(DamageSource ds, float par2) {
        if (ds == DamageSource.field_76367_g) {
            return false;
        }
        if (this.getUpgrade() == 3) {
            return false;
        }
        return super.func_70097_a(ds, par2);
    }

    public void func_70014_b(NBTTagCompound nbttagcompound) {
        super.func_70014_b(nbttagcompound);
        nbttagcompound.func_74757_a("Stay", this.getStay());
        nbttagcompound.func_74774_a("upgrade", (byte)this.getUpgrade());
        if (this.func_152113_b() == null) {
            nbttagcompound.func_74778_a("Owner", "");
        } else {
            nbttagcompound.func_74778_a("Owner", this.func_152113_b());
        }
        nbttagcompound.func_74782_a("Inventory", (NBTBase)this.inventory.writeToNBT(new NBTTagList()));
    }

    public void func_70037_a(NBTTagCompound nbttagcompound) {
        super.func_70037_a(nbttagcompound);
        this.setStay(nbttagcompound.func_74767_n("Stay"));
        this.setUpgrade(nbttagcompound.func_74771_c("upgrade"));
        String s = nbttagcompound.func_74779_i("Owner");
        if (s.length() > 0) {
            this.setOwner(s);
        }
        NBTTagList nbttaglist = nbttagcompound.func_150295_c("Inventory", 10);
        this.inventory.readFromNBT(nbttaglist);
        this.setInvSize();
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.getUpgrade() == 5) {
            this.pullItems();
        }
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70171_ac) {
            this.field_70181_x += (double)0.033f;
        }
        if (this.field_70170_p.field_72995_K) {
            if (!this.field_70122_E && this.field_70181_x < 0.0 && !this.field_70171_ac) {
                this.lidrot += 0.015f;
            }
            if ((this.field_70122_E || this.field_70171_ac) && !this.isOpen()) {
                this.lidrot -= 0.1f;
                if (this.lidrot < 0.0f) {
                    this.lidrot = 0.0f;
                }
            }
            if (this.isOpen()) {
                this.lidrot += 0.035f;
            }
            float f = this.isOpen() ? 0.5f : 0.2f;
            if (this.lidrot > f) {
                this.lidrot = this.isOpen() ? 0.5f : 0.2f;
            }
        } else if (this.func_110143_aJ() < this.func_110138_aP() && (this.getUpgrade() == 3 || this.field_70173_aa % 50 == 0)) {
            this.func_70691_i(1.0f);
        }
    }

    /*
     * WARNING - void declaration
     */
    protected void func_70626_be() {
        if (this.getAnger() > 0) {
            this.setAnger(this.getAnger() - 1);
        }
        if (this.eatDelay > 0) {
            --this.eatDelay;
        }
        this.field_70143_R = 0.0f;
        if (this.func_70902_q() != null) {
            if (!this.field_70170_p.field_72995_K) {
                ArrayList<WeakReference<Object>> ll = EventHandlerEntity.linkedEntities.get(this.func_70902_q().func_70005_c_());
                if (ll == null) {
                    ll = new ArrayList();
                }
                boolean add = true;
                for (WeakReference<Entity> weakReference : ll) {
                    if (weakReference.get() == null || ((Entity)weakReference.get()).func_145782_y() != this.func_145782_y()) continue;
                    add = false;
                    break;
                }
                if (add) {
                    ll.add(new WeakReference<EntityTravelingTrunk>(this));
                    EventHandlerEntity.linkedEntities.put(this.func_70902_q().func_70005_c_(), ll);
                }
            }
            if (!this.getStay() && this.func_70902_q() != null && this.func_70032_d(this.func_70902_q()) > 20.0f) {
                void var4_9;
                int i = MathHelper.func_76128_c((double)this.func_70902_q().field_70165_t) - 2;
                int j = MathHelper.func_76128_c((double)this.func_70902_q().field_70161_v) - 2;
                int k = MathHelper.func_76128_c((double)this.func_70902_q().field_70121_D.field_72338_b);
                boolean bl = false;
                while (var4_9 <= 4) {
                    for (int i1 = 0; i1 <= 4; ++i1) {
                        if (var4_9 >= true && i1 >= 1 && var4_9 <= 3 && i1 <= 3 || !this.field_70170_p.func_147445_c(i + var4_9, k - 1, j + i1, false) && this.field_70170_p.func_147439_a(i + var4_9, k - 1, j + i1).func_149688_o() != Material.field_151586_h || this.field_70170_p.func_147445_c(i + var4_9, k, j + i1, false) || this.field_70170_p.func_147445_c(i + var4_9, k + 1, j + i1, false)) continue;
                        this.field_70170_p.func_72908_a((double)((float)(i + var4_9) + 0.5f), (double)k, (double)((float)(j + i1) + 0.5f), "mob.endermen.portal", 0.5f, 1.0f);
                        this.func_70012_b((float)(i + var4_9) + 0.5f, k, (float)(j + i1) + 0.5f, this.field_70177_z, this.field_70125_A);
                        this.func_70624_b(null);
                        return;
                    }
                    ++var4_9;
                }
            }
            if (this.func_70638_az() != null && this.func_70638_az().field_70128_L) {
                this.func_70624_b(null);
                this.setAnger(5);
            }
            if (!this.getStay() && this.getUpgrade() == 2 && this.getAnger() == 0 && this.func_70638_az() == null && this.func_70902_q() != null && this.getOwnerEntity().func_70643_av() != null && !this.getOwnerEntity().func_70643_av().field_70128_L && this.getOwnerEntity().func_70643_av() instanceof EntityLivingBase && this.func_70685_l((Entity)this.getOwnerEntity().func_70643_av())) {
                this.setAnger(600);
                this.func_70624_b(this.getOwnerEntity().func_70643_av());
            }
            boolean move = false;
            if (this.getAnger() > 0 && this.func_70638_az() != null && !this.func_70638_az().field_70128_L && this.func_70638_az() != this.getOwnerEntity()) {
                this.func_70625_a((Entity)this.func_70638_az(), 10.0f, 20.0f);
                move = true;
                if (this.field_70724_aR <= 0 && (double)this.func_70032_d((Entity)this.func_70638_az()) < 1.5 && this.func_70638_az().field_70121_D.field_72337_e > this.field_70121_D.field_72338_b && this.func_70638_az().field_70121_D.field_72338_b < this.field_70121_D.field_72337_e) {
                    this.field_70724_aR = 10 + this.field_70170_p.field_73012_v.nextInt(5);
                    this.func_70638_az().func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 4.0f);
                    this.field_70170_p.func_72960_a((Entity)this, (byte)17);
                    this.field_70170_p.func_72956_a((Entity)this, "mob.blaze.hit", 0.5f, this.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
                }
            }
            if (this.func_70902_q() != null && this.func_70032_d(this.func_70902_q()) > 5.0f && this.getAnger() == 0 && !this.getStay()) {
                this.func_70625_a(this.func_70902_q(), 10.0f, 20.0f);
                move = true;
            }
            if ((this.field_70122_E || this.field_70171_ac) && this.jumpDelay-- <= 0 && move) {
                boolean fast = this.getUpgrade() == 0;
                this.jumpDelay = this.field_70146_Z.nextInt(10) + 5;
                this.jumpDelay /= 3;
                this.field_70703_bu = true;
                this.field_768_a = 1.0f;
                this.field_70702_br = 1.0f - this.field_70146_Z.nextFloat() * 2.0f;
                float f = this.field_70701_bs = fast ? 8.0f : 6.0f;
                if (this.field_70171_ac) {
                    this.field_70701_bs *= 0.75f;
                }
                this.field_70747_aH = fast ? 0.04f : 0.03f;
                this.field_70170_p.func_72956_a((Entity)this, "random.chestclosed", 0.1f, this.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
            } else {
                this.field_70703_bu = false;
                if (this.field_70122_E) {
                    this.field_70701_bs = 0.0f;
                    this.field_70702_br = 0.0f;
                }
            }
        }
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public void func_70100_b_(EntityPlayer entityplayer) {
    }

    protected String func_70621_aR() {
        return Blocks.field_150364_r.field_149762_H.func_150498_e();
    }

    protected String func_70673_aS() {
        return "random.break";
    }

    protected Item func_146068_u() {
        return Item.func_150899_d((int)0);
    }

    protected float func_70599_aP() {
        return 0.5f;
    }

    public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
        this.setInvSize();
        return super.func_110161_a(par1EntityLivingData);
    }

    public void setInvSize() {
        this.setRows(this.getUpgrade() == 1 ? 4 : 3);
        this.slotCount = this.getRows() * 9;
    }

    public boolean func_70085_c(EntityPlayer player) {
        if (player.func_70093_af()) {
            return false;
        }
        ItemStack itemstack = player.field_71071_by.func_70448_g();
        if (itemstack != null && itemstack.func_77973_b() == ConfigItems.itemGolemBell) {
            return this.getUpgrade() == 3 && !this.func_152113_b().equals(player.func_70005_c_());
        }
        if (this.getUpgrade() == -1 && itemstack != null && itemstack.func_77973_b() == ConfigItems.itemGolemUpgrade) {
            this.setUpgrade(itemstack.func_77960_j());
            this.setInvSize();
            --itemstack.field_77994_a;
            if (itemstack.field_77994_a <= 0) {
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
            }
            this.field_70170_p.func_72956_a((Entity)this, "thaumcraft:upgrade", 0.5f, 1.0f);
            player.func_71038_i();
            return true;
        }
        if (itemstack != null && itemstack.func_77973_b() instanceof ItemFood && this.func_110143_aJ() < this.func_110138_aP()) {
            ItemFood itemfood = (ItemFood)itemstack.func_77973_b();
            --itemstack.field_77994_a;
            this.func_70691_i(itemfood.func_150905_g(itemstack));
            if (this.func_110143_aJ() == this.func_110138_aP()) {
                this.field_70170_p.func_72956_a((Entity)this, "random.burp", 0.5f, this.field_70170_p.field_73012_v.nextFloat() * 0.5f + 0.5f);
            } else {
                this.field_70170_p.func_72956_a((Entity)this, "random.eat", 0.5f, this.field_70170_p.field_73012_v.nextFloat() * 0.5f + 0.5f);
            }
            this.field_70170_p.func_72960_a((Entity)this, (byte)18);
            this.lidrot = 0.15f;
            if (itemstack.field_77994_a <= 0) {
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
            }
            return true;
        }
        if (!this.field_70170_p.field_72995_K) {
            if (this.getUpgrade() == 3 && !this.func_152113_b().equals(player.func_70005_c_())) {
                return true;
            }
            player.openGui((Object)Thaumcraft.instance, 2, this.field_70170_p, this.func_145782_y(), 0, 0);
            return false;
        }
        return true;
    }

    void showHeartsOrSmokeFX(boolean flag) {
        String s = "heart";
        int amount = 1;
        if (!flag) {
            s = "explode";
            amount = 7;
        }
        for (int i = 0; i < amount; ++i) {
            double d = this.field_70146_Z.nextGaussian() * 0.02;
            double d1 = this.field_70146_Z.nextGaussian() * 0.02;
            double d2 = this.field_70146_Z.nextGaussian() * 0.02;
            this.field_70170_p.func_72869_a(s, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d, d1, d2);
        }
    }

    private void pullItems() {
        Entity entity;
        int a;
        if (this.field_70128_L || this.func_110143_aJ() <= 0.0f) {
            return;
        }
        List list = null;
        if (!this.field_70170_p.field_72995_K) {
            list = this.field_70170_p.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(this.field_70165_t - 0.5), (double)(this.field_70163_u - 0.5), (double)(this.field_70161_v - 0.5), (double)(this.field_70165_t + 0.5), (double)(this.field_70163_u + 0.5), (double)(this.field_70161_v + 0.5)));
            for (a = 0; a < list.size(); ++a) {
                ItemStack stack;
                ItemStack outstack;
                entity = (Entity)list.get(a);
                if (!(entity instanceof EntityItem) || (outstack = InventoryUtils.placeItemStackIntoInventory(stack = ((EntityItem)entity).func_92059_d().func_77946_l(), this.inventory, 0, true)) != null && outstack.field_77994_a == stack.field_77994_a) continue;
                this.field_70170_p.func_72956_a((Entity)this, "random.eat", 0.5f, this.field_70170_p.field_73012_v.nextFloat() * 0.5f + 0.5f);
                this.field_70170_p.func_72960_a((Entity)this, (byte)17);
                if (outstack != null && outstack.field_77994_a >= 0) {
                    ((EntityItem)entity).func_92058_a(outstack);
                    continue;
                }
                entity.func_70106_y();
            }
        }
        list = this.field_70170_p.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(this.field_70165_t - 3.0), (double)(this.field_70163_u - 3.0), (double)(this.field_70161_v - 3.0), (double)(this.field_70165_t + 3.0), (double)(this.field_70163_u + 3.0), (double)(this.field_70161_v + 3.0)));
        for (a = 0; a < list.size(); ++a) {
            entity = (Entity)list.get(a);
            if (!(entity instanceof EntityItem)) continue;
            double d6 = entity.field_70165_t - this.field_70165_t;
            double d8 = entity.field_70163_u - this.field_70163_u + (double)(this.field_70131_O * 0.8f);
            double d10 = entity.field_70161_v - this.field_70161_v;
            double d11 = MathHelper.func_76133_a((double)(d6 * d6 + d8 * d8 + d10 * d10));
            double d13 = 0.075;
            entity.field_70159_w -= (d6 /= d11) * d13;
            entity.field_70181_x -= (d8 /= d11) * d13;
            entity.field_70179_y -= (d10 /= d11) * d13;
        }
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        if (!this.field_70170_p.field_72995_K) {
            this.inventory.dropAllItems();
        }
        super.func_70645_a(par1DamageSource);
    }

    public boolean func_70648_aU() {
        return true;
    }

    public int getUpgrade() {
        return this.field_70180_af.func_75683_a(18);
    }

    public void setUpgrade(int upgrade) {
        this.field_70180_af.func_75692_b(18, (Object)((byte)upgrade));
    }

    public int getRows() {
        return this.field_70180_af.func_75683_a(19);
    }

    public void setRows(int rows) {
        this.field_70180_af.func_75692_b(19, (Object)((byte)rows));
    }

    public int getAnger() {
        return this.field_70180_af.func_75693_b(20);
    }

    public void setAnger(int anger) {
        this.field_70180_af.func_75692_b(20, (Object)((short)anger));
    }

    public boolean isOpen() {
        return this.field_70180_af.func_75683_a(15) == 1;
    }

    public void setOpen(boolean par1) {
        this.field_70180_af.func_75692_b(15, (Object)(par1 ? (byte)1 : 0));
    }

    public boolean getStay() {
        return this.field_70180_af.func_75683_a(16) == 1;
    }

    public void setStay(boolean par1) {
        this.field_70180_af.func_75692_b(16, (Object)(par1 ? (byte)1 : 0));
    }

    public String func_152113_b() {
        return this.field_70180_af.func_75681_e(17);
    }

    public void setOwner(String par1Str) {
        this.field_70180_af.func_75692_b(17, (Object)par1Str);
    }

    public Entity func_70902_q() {
        return this.getOwnerEntity();
    }

    public EntityLivingBase getOwnerEntity() {
        return this.field_70170_p.func_72924_a(this.func_152113_b());
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte par1) {
        if (par1 == 17) {
            this.lidrot = 0.15f;
        } else if (par1 == 18) {
            this.lidrot = 0.15f;
            this.showHeartsOrSmokeFX(true);
        } else {
            super.func_70103_a(par1);
        }
    }

    public void func_71027_c(int par1) {
        if (this.getStay() || this.field_70128_L || this.field_71093_bK == par1) {
            return;
        }
        if (this.func_70902_q() == null) {
            try {
                Entity entity;
                MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
                WorldServer worldserver1 = minecraftserver.func_71218_a(par1);
                if (worldserver1 == null) {
                    return;
                }
                EntityPlayer target = worldserver1.func_72924_a(this.func_152113_b());
                if (target == null) {
                    return;
                }
                this.field_70170_p.func_72900_e((Entity)this);
                this.field_70128_L = false;
                if (this.func_70089_S()) {
                    this.field_70170_p.func_72866_a((Entity)this, false);
                }
                if ((entity = EntityList.func_75620_a((String)EntityList.func_75621_b((Entity)this), (World)worldserver1)) != null) {
                    entity.func_82141_a((Entity)this, true);
                    entity.func_70012_b(target.field_70165_t, target.field_70163_u + 0.25, target.field_70161_v, entity.field_70177_z, entity.field_70125_A);
                    entity.field_71093_bK = par1;
                    worldserver1.func_72838_d(entity);
                }
                this.field_71093_bK = par1;
                this.field_70128_L = true;
            }
            catch (Exception e) {
                Thaumcraft.log.error("Error while teleporting traveling trunk to dimension " + par1);
                e.printStackTrace();
            }
        } else {
            super.func_71027_c(par1);
        }
    }
}

