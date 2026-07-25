/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.IScribeTools;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemResearchNotes;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ResearchNoteData;
import thaumcraft.common.lib.utils.HexUtils;
import thaumcraft.common.lib.utils.InventoryUtils;

public class TileResearchTable
extends TileThaumcraft
implements IInventory {
    public ItemStack[] contents = new ItemStack[2];
    public AspectList bonusAspects = new AspectList();
    int nextRecalc = 0;
    EntityPlayer researcher = null;
    public ResearchNoteData data = null;

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        NBTTagCompound var4;
        int var3;
        NBTTagList var2 = nbttagcompound.func_150295_c("Inventory", 10);
        this.contents = new ItemStack[this.func_70302_i_()];
        for (var3 = 0; var3 < Math.min(2, var2.func_74745_c()); ++var3) {
            var4 = var2.func_150305_b(var3);
            int var5 = var4.func_74771_c("Slot") & 0xFF;
            if (var5 < 0 || var5 >= this.contents.length) continue;
            this.contents[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
        }
        this.nextRecalc = nbttagcompound.func_74762_e("nextRecalc");
        this.bonusAspects = new AspectList();
        var2 = nbttagcompound.func_150295_c("bonusAspects", 10);
        for (var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            var4 = var2.func_150305_b(var3);
            String var5 = var4.func_74779_i("tag");
            if (Aspect.getAspect(var5) == null) continue;
            this.bonusAspects.merge(Aspect.getAspect(var5), 1);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.contents.length; ++var3) {
            if (this.contents[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a("Slot", (byte)var3);
            this.contents[var3].func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        nbttagcompound.func_74782_a("Inventory", (NBTBase)var2);
        nbttagcompound.func_74768_a("nextRecalc", this.nextRecalc);
        var2 = new NBTTagList();
        for (Aspect aspect : this.bonusAspects.getAspects()) {
            if (aspect == null || this.bonusAspects.getAmount(aspect) <= 0) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74778_a("tag", aspect.getTag());
            var2.func_74742_a((NBTBase)var4);
        }
        nbttagcompound.func_74782_a("bonusAspects", (NBTBase)var2);
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (!this.field_145850_b.field_72995_K && this.nextRecalc++ > 600) {
            this.nextRecalc = 0;
            this.recalculateBonus();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_70296_d() {
        super.func_70296_d();
        this.gatherResults();
    }

    public void gatherResults() {
        this.data = null;
        if (this.contents[1] != null && this.contents[1].func_77973_b() instanceof ItemResearchNotes) {
            this.data = ResearchManager.getData(this.contents[1]);
        }
    }

    public void placeAspect(int q, int r, Aspect aspect, EntityPlayer player) {
        if (this.data == null) {
            this.gatherResults();
        }
        if (!ResearchManager.consumeInkFromTable(this.contents[0], false)) {
            return;
        }
        if (this.contents[1] != null && this.contents[1].func_77973_b() instanceof ItemResearchNotes && this.data != null && this.contents[1].func_77960_j() < 64) {
            boolean r1 = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHER1");
            boolean r2 = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHER2");
            HexUtils.Hex hex = new HexUtils.Hex(q, r);
            ResearchManager.HexEntry he = null;
            if (aspect != null) {
                he = new ResearchManager.HexEntry(aspect, 2);
                if (r2 && this.field_145850_b.field_73012_v.nextFloat() < 0.1f) {
                    this.field_145850_b.func_72956_a((Entity)player, "random.orb", 0.2f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f);
                } else if (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), aspect) <= 0) {
                    this.bonusAspects.remove(aspect, 1);
                    player.field_70170_p.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    this.func_70296_d();
                } else {
                    Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), aspect, (short)-1);
                    ResearchManager.scheduleSave(player);
                    PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectPool(aspect.getTag(), (short)0, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), aspect)), (EntityPlayerMP)player);
                }
            } else {
                float f = this.field_145850_b.field_73012_v.nextFloat();
                if (this.data.hexEntries.get((Object)hex.toString()).aspect != null && (r1 && f < 0.25f || r2 && f < 0.5f)) {
                    this.field_145850_b.func_72956_a((Entity)player, "random.orb", 0.2f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f);
                    Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), this.data.hexEntries.get((Object)hex.toString()).aspect, (short)1);
                    ResearchManager.scheduleSave(player);
                    PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectPool(this.data.hexEntries.get((Object)hex.toString()).aspect.getTag(), (short)0, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), this.data.hexEntries.get((Object)hex.toString()).aspect)), (EntityPlayerMP)player);
                }
                he = new ResearchManager.HexEntry(null, 0);
            }
            this.data.hexEntries.put(hex.toString(), he);
            this.data.hexes.put(hex.toString(), hex);
            ResearchManager.updateData(this.contents[1], this.data);
            ResearchManager.consumeInkFromTable(this.contents[0], true);
            if (!this.field_145850_b.field_72995_K && ResearchManager.checkResearchCompletion(this.contents[1], this.data, player.func_70005_c_())) {
                this.contents[1].func_77964_b(64);
                this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockTable, 1, 1);
            }
        }
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
    }

    private void recalculateBonus() {
        if (!this.field_145850_b.func_72935_r() && this.field_145850_b.func_72957_l(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) < 4 && !this.field_145850_b.func_72937_j(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
            this.bonusAspects.merge(Aspect.ENTROPY, 1);
        }
        if ((float)this.field_145848_d > (float)this.field_145850_b.func_72940_L() * 0.5f && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
            this.bonusAspects.merge(Aspect.AIR, 1);
        }
        if ((float)this.field_145848_d > (float)this.field_145850_b.func_72940_L() * 0.66f && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
            this.bonusAspects.merge(Aspect.AIR, 1);
        }
        if ((float)this.field_145848_d > (float)this.field_145850_b.func_72940_L() * 0.75f && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
            this.bonusAspects.merge(Aspect.AIR, 1);
        }
        for (int x = -8; x <= 8; ++x) {
            for (int z = -8; z <= 8; ++z) {
                for (int y = -8; y <= 8; ++y) {
                    if (y + this.field_145848_d <= 0 || y + this.field_145848_d >= this.field_145850_b.func_72940_L()) continue;
                    Block bi = this.field_145850_b.func_147439_a(x + this.field_145851_c, y + this.field_145848_d, z + this.field_145849_e);
                    int md = this.field_145850_b.func_72805_g(x + this.field_145851_c, y + this.field_145848_d, z + this.field_145849_e);
                    Material bm = bi.func_149688_o();
                    if (bi == ConfigBlocks.blockCustomOre && md == 1) {
                        if (this.bonusAspects.getAmount(Aspect.AIR) < 1 && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
                            this.bonusAspects.merge(Aspect.AIR, 1);
                            return;
                        }
                    } else if (bi == ConfigBlocks.blockCrystal && md == 0) {
                        if (this.bonusAspects.getAmount(Aspect.AIR) < 1 && this.field_145850_b.field_73012_v.nextInt(10) == 0) {
                            this.bonusAspects.merge(Aspect.AIR, 1);
                            return;
                        }
                    } else if (bm == Material.field_151581_o || bm == Material.field_151587_i || bi == ConfigBlocks.blockCustomOre && md == 2) {
                        if (this.bonusAspects.getAmount(Aspect.FIRE) < 1 && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
                            this.bonusAspects.merge(Aspect.FIRE, 1);
                            return;
                        }
                    } else if (bi == ConfigBlocks.blockCrystal && md == 1) {
                        if (this.bonusAspects.getAmount(Aspect.FIRE) < 1 && this.field_145850_b.field_73012_v.nextInt(10) == 0) {
                            this.bonusAspects.merge(Aspect.FIRE, 1);
                            return;
                        }
                    } else if (bm == Material.field_151578_c) {
                        if (this.bonusAspects.getAmount(Aspect.EARTH) < 1 && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
                            this.bonusAspects.merge(Aspect.EARTH, 1);
                            return;
                        }
                    } else if (bi == ConfigBlocks.blockCustomOre && md == 4) {
                        if (this.bonusAspects.getAmount(Aspect.EARTH) < 1 && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
                            this.bonusAspects.merge(Aspect.EARTH, 1);
                            return;
                        }
                    } else if (bi == ConfigBlocks.blockCrystal && md == 3) {
                        if (this.bonusAspects.getAmount(Aspect.EARTH) < 1 && this.field_145850_b.field_73012_v.nextInt(10) == 0) {
                            this.bonusAspects.merge(Aspect.EARTH, 1);
                            return;
                        }
                    } else if (bm == Material.field_151586_h) {
                        if (this.bonusAspects.getAmount(Aspect.WATER) < 1 && this.field_145850_b.field_73012_v.nextInt(15) == 0) {
                            this.bonusAspects.merge(Aspect.WATER, 1);
                            return;
                        }
                    } else if (bi == ConfigBlocks.blockCustomOre && md == 3) {
                        if (this.bonusAspects.getAmount(Aspect.WATER) < 1 && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
                            this.bonusAspects.merge(Aspect.WATER, 1);
                            return;
                        }
                    } else if (bi == ConfigBlocks.blockCrystal && md == 2) {
                        if (this.bonusAspects.getAmount(Aspect.WATER) < 1 && this.field_145850_b.field_73012_v.nextInt(10) == 0) {
                            this.bonusAspects.merge(Aspect.WATER, 1);
                            return;
                        }
                    } else if (bm == Material.field_151594_q || bm == Material.field_76233_E) {
                        if (this.bonusAspects.getAmount(Aspect.ORDER) < 1 && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
                            this.bonusAspects.merge(Aspect.ORDER, 1);
                            return;
                        }
                    } else if (bi == ConfigBlocks.blockCustomOre && md == 5) {
                        if (this.bonusAspects.getAmount(Aspect.ORDER) < 1 && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
                            this.bonusAspects.merge(Aspect.ORDER, 1);
                            return;
                        }
                    } else if (bi == ConfigBlocks.blockCrystal && md == 4) {
                        if (this.bonusAspects.getAmount(Aspect.ORDER) < 1 && this.field_145850_b.field_73012_v.nextInt(10) == 0) {
                            this.bonusAspects.merge(Aspect.ORDER, 1);
                            return;
                        }
                    } else if (bi == ConfigBlocks.blockCustomOre && md == 6) {
                        if (this.bonusAspects.getAmount(Aspect.ENTROPY) < 1 && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
                            this.bonusAspects.merge(Aspect.ENTROPY, 1);
                            return;
                        }
                    } else if (bi == ConfigBlocks.blockCrystal && md == 5 && this.bonusAspects.getAmount(Aspect.ENTROPY) < 1 && this.field_145850_b.field_73012_v.nextInt(10) == 0) {
                        this.bonusAspects.merge(Aspect.ENTROPY, 1);
                        return;
                    }
                    if ((bi != Blocks.field_150342_X || this.field_145850_b.field_73012_v.nextInt(300) != 0) && (bi != ConfigBlocks.blockJar || md != 1 || this.field_145850_b.field_73012_v.nextInt(200) != 0)) continue;
                    Aspect[] aspects = new Aspect[]{};
                    aspects = Aspect.aspects.values().toArray(aspects);
                    this.bonusAspects.merge(aspects[this.field_145850_b.field_73012_v.nextInt(aspects.length)], 1);
                    return;
                }
            }
        }
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
        return "Research Table";
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
        if (itemstack == null) {
            return false;
        }
        switch (i) {
            case 0: {
                if (!(itemstack.func_77973_b() instanceof IScribeTools)) break;
                return true;
            }
            case 1: {
                if (itemstack.func_77973_b() != ConfigItems.itemResearchNotes || itemstack.func_77960_j() >= 64) break;
                return true;
            }
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)this.field_145848_d, (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 2), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 2));
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        if (this.field_145850_b != null && this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public boolean func_145842_c(int i, int j) {
        if (i == 1) {
            if (this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_72980_b((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:learn", 1.0f, 1.0f, false);
            }
            return true;
        }
        return super.func_145842_c(i, j);
    }

    public void duplicate(EntityPlayer player) {
        if (this.data == null) {
            this.gatherResults();
        }
        if (this.contents[1] != null && this.contents[1].func_77973_b() instanceof ItemResearchNotes && this.data != null && this.contents[1].func_77960_j() == 64 && InventoryUtils.isPlayerCarrying(player, new ItemStack(Items.field_151121_aF)) >= 0 && InventoryUtils.isPlayerCarrying(player, new ItemStack(Items.field_151100_aR, 1, 0)) >= 0) {
            ResearchItem rr = ResearchCategories.getResearch(this.data.key);
            for (Aspect aspect : rr.tags.getAspects()) {
                if (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), aspect) >= rr.tags.getAmount(aspect) + this.data.copies) continue;
                return;
            }
            for (Aspect aspect : rr.tags.getAspects()) {
                Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), aspect, (short)(-(rr.tags.getAmount(aspect) + this.data.copies)));
                ResearchManager.scheduleSave(player);
                PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectPool(aspect.getTag(), (short)0, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), aspect)), (EntityPlayerMP)player);
            }
            InventoryUtils.consumeInventoryItem(player, Items.field_151121_aF, 0);
            InventoryUtils.consumeInventoryItem(player, Items.field_151100_aR, 0);
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockTable, 1, 1);
            ++this.data.copies;
            ResearchManager.updateData(this.contents[1], this.data);
            ++this.contents[1].field_77994_a;
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
    }
}

