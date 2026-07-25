/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagByte
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.items.wands;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.BlockCoordinates;
import thaumcraft.api.IArchitect;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.IWandable;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.api.wands.WandTriggerRegistry;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.tiles.TileOwned;

public class ItemWandCasting
extends Item
implements IArchitect {
    private IIcon icon;
    DecimalFormat myFormatter = new DecimalFormat("#######.##");
    public ItemFocusBasic.WandFocusAnimation animation = null;

    public ItemWandCasting() {
        this.field_77777_bU = 1;
        this.func_77656_e(0);
        this.func_77627_a(true);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    public boolean func_77645_m() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icon = par1IconRegister.func_94245_a("thaumcraft:blank");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int pass) {
        return this.icon;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77662_d() {
        return true;
    }

    public int getMaxVis(ItemStack stack) {
        return this.getRod(stack).getCapacity() * (this.isSceptre(stack) ? 150 : 100);
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        ItemStack w1 = new ItemStack((Item)this, 1, 0);
        ItemStack w2 = new ItemStack((Item)this, 1, 9);
        ItemStack w3 = new ItemStack((Item)this, 1, 54);
        ((ItemWandCasting)w2.func_77973_b()).setCap(w2, ConfigItems.WAND_CAP_GOLD);
        ((ItemWandCasting)w3.func_77973_b()).setCap(w3, ConfigItems.WAND_CAP_THAUMIUM);
        ((ItemWandCasting)w2.func_77973_b()).setRod(w2, ConfigItems.WAND_ROD_GREATWOOD);
        ((ItemWandCasting)w3.func_77973_b()).setRod(w3, ConfigItems.WAND_ROD_SILVERWOOD);
        ItemStack sceptre = new ItemStack(ConfigItems.itemWandCasting, 1, 128);
        ((ItemWandCasting)sceptre.func_77973_b()).setCap(sceptre, ConfigItems.WAND_CAP_THAUMIUM);
        ((ItemWandCasting)sceptre.func_77973_b()).setRod(sceptre, ConfigItems.WAND_ROD_SILVERWOOD);
        sceptre.func_77983_a("sceptre", (NBTBase)new NBTTagByte(1));
        for (Aspect aspect : Aspect.getPrimalAspects()) {
            ((ItemWandCasting)w1.func_77973_b()).addVis(w1, aspect, ((ItemWandCasting)w1.func_77973_b()).getMaxVis(w1), true);
            ((ItemWandCasting)w2.func_77973_b()).addVis(w2, aspect, ((ItemWandCasting)w2.func_77973_b()).getMaxVis(w2), true);
            ((ItemWandCasting)w3.func_77973_b()).addVis(w3, aspect, ((ItemWandCasting)w3.func_77973_b()).getMaxVis(w3), true);
            ((ItemWandCasting)sceptre.func_77973_b()).addVis(sceptre, aspect, ((ItemWandCasting)sceptre.func_77973_b()).getMaxVis(sceptre), true);
        }
        par3List.add(w1);
        par3List.add(w2);
        par3List.add(w3);
        par3List.add(sceptre);
    }

    public String func_77653_i(ItemStack is) {
        String name = StatCollector.func_74838_a((String)"item.Wand.name");
        name = name.replace("%CAP", StatCollector.func_74838_a((String)("item.Wand." + this.getCap(is).getTag() + ".cap")));
        String rod = this.getRod(is).getTag();
        if (rod.indexOf("_staff") >= 0) {
            rod = rod.substring(0, this.getRod(is).getTag().indexOf("_staff"));
        }
        name = name.replace("%ROD", StatCollector.func_74838_a((String)("item.Wand." + rod + ".rod")));
        name = name.replace("%OBJ", this.isStaff(is) ? StatCollector.func_74838_a((String)"item.Wand.staff.obj") : (this.isSceptre(is) ? StatCollector.func_74838_a((String)"item.Wand.sceptre.obj") : StatCollector.func_74838_a((String)"item.Wand.wand.obj")));
        return name;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        int pos = list.size();
        String tt2 = "";
        if (stack.func_77942_o()) {
            String tt = "";
            int tot = 0;
            int num = 0;
            for (Aspect aspect : Aspect.getPrimalAspects()) {
                int amt;
                if (!stack.field_77990_d.func_74764_b(aspect.getTag())) continue;
                String amount = this.myFormatter.format((float)stack.field_77990_d.func_74762_e(aspect.getTag()) / 100.0f);
                float mod = this.getConsumptionModifier(stack, player, aspect, false);
                String consumption = this.myFormatter.format(mod * 100.0f);
                ++num;
                tot = (int)((float)tot + mod * 100.0f);
                String text = "";
                ItemStack focus = this.getFocusItem(stack);
                if (focus != null && (amt = ((ItemFocusBasic)focus.func_77973_b()).getVisCost(focus).getAmount(aspect)) > 0) {
                    text = "\u00a7r, " + this.myFormatter.format((float)amt * mod / 100.0f) + " " + StatCollector.func_74838_a((String)(((ItemFocusBasic)focus.func_77973_b()).isVisCostPerTick(focus) ? "item.Focus.cost2" : "item.Focus.cost1"));
                }
                if (Thaumcraft.proxy.isShiftKeyDown()) {
                    list.add(" \u00a7" + aspect.getChatcolor() + aspect.getName() + "\u00a7r x " + amount + ", \u00a7o(" + consumption + "% " + StatCollector.func_74838_a((String)"tc.vis.cost") + ")" + text);
                    continue;
                }
                if (tt.length() > 0) {
                    tt = tt + " | ";
                }
                tt = tt + "\u00a7" + aspect.getChatcolor() + amount + "\u00a7r";
            }
            if (!Thaumcraft.proxy.isShiftKeyDown() && num > 0) {
                list.add(tt);
                tt2 = " (" + (tot /= num) + "% " + StatCollector.func_74838_a((String)"tc.vis.costavg") + ")";
            }
        }
        list.add(pos, EnumChatFormatting.GOLD + StatCollector.func_74838_a((String)"item.capacity.text") + " " + this.getMaxVis(stack) / 100 + "\u00a7r" + tt2);
        if (this.getFocus(stack) != null) {
            list.add(EnumChatFormatting.BOLD + "" + EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GREEN + this.getFocus(stack).func_77653_i(this.getFocusItem(stack)));
            if (Thaumcraft.proxy.isShiftKeyDown()) {
                this.getFocus(stack).addFocusInformation(this.getFocusItem(stack), player, list, par4);
            }
        }
    }

    public AspectList getAllVis(ItemStack is) {
        AspectList out = new AspectList();
        for (Aspect aspect : Aspect.getPrimalAspects()) {
            if (is.func_77942_o() && is.field_77990_d.func_74764_b(aspect.getTag())) {
                out.merge(aspect, is.field_77990_d.func_74762_e(aspect.getTag()));
                continue;
            }
            out.merge(aspect, 0);
        }
        return out;
    }

    public AspectList getAspectsWithRoom(ItemStack wandstack) {
        AspectList out = new AspectList();
        AspectList cur = this.getAllVis(wandstack);
        for (Aspect aspect : cur.getAspects()) {
            if (cur.getAmount(aspect) >= this.getMaxVis(wandstack)) continue;
            out.add(aspect, 1);
        }
        return out;
    }

    public void storeAllVis(ItemStack is, AspectList in) {
        for (Aspect aspect : in.getAspects()) {
            is.func_77983_a(aspect.getTag(), (NBTBase)new NBTTagInt(in.getAmount(aspect)));
        }
    }

    public int getVis(ItemStack is, Aspect aspect) {
        int out = 0;
        if (is != null && aspect != null && is.func_77942_o() && is.field_77990_d.func_74764_b(aspect.getTag())) {
            out = is.field_77990_d.func_74762_e(aspect.getTag());
        }
        return out;
    }

    public void storeVis(ItemStack is, Aspect aspect, int amount) {
        is.func_77983_a(aspect.getTag(), (NBTBase)new NBTTagInt(amount));
    }

    public float getConsumptionModifier(ItemStack is, EntityPlayer player, Aspect aspect, boolean crafting) {
        float consumptionModifier = 1.0f;
        consumptionModifier = this.getCap(is).getSpecialCostModifierAspects() != null && this.getCap(is).getSpecialCostModifierAspects().contains(aspect) ? this.getCap(is).getSpecialCostModifier() : this.getCap(is).getBaseCostModifier();
        if (player != null) {
            consumptionModifier -= WandManager.getTotalVisDiscount(player, aspect);
        }
        if (this.getFocus(is) != null && !crafting) {
            consumptionModifier -= (float)this.getFocusFrugal(is) / 10.0f;
        }
        if (this.isSceptre(is)) {
            consumptionModifier -= 0.1f;
        }
        return Math.max(consumptionModifier, 0.1f);
    }

    public int getFocusPotency(ItemStack itemstack) {
        if (this.getFocus(itemstack) == null) {
            return 0;
        }
        return this.getFocus(itemstack).getUpgradeLevel(this.getFocusItem(itemstack), FocusUpgradeType.potency) + (this.hasRunes(itemstack) ? 1 : 0);
    }

    public int getFocusTreasure(ItemStack itemstack) {
        if (this.getFocus(itemstack) == null) {
            return 0;
        }
        return this.getFocus(itemstack).getUpgradeLevel(this.getFocusItem(itemstack), FocusUpgradeType.treasure);
    }

    public int getFocusFrugal(ItemStack itemstack) {
        if (this.getFocus(itemstack) == null) {
            return 0;
        }
        return this.getFocus(itemstack).getUpgradeLevel(this.getFocusItem(itemstack), FocusUpgradeType.frugal);
    }

    public int getFocusEnlarge(ItemStack itemstack) {
        if (this.getFocus(itemstack) == null) {
            return 0;
        }
        return this.getFocus(itemstack).getUpgradeLevel(this.getFocusItem(itemstack), FocusUpgradeType.enlarge);
    }

    public int getFocusExtend(ItemStack itemstack) {
        if (this.getFocus(itemstack) == null) {
            return 0;
        }
        return this.getFocus(itemstack).getUpgradeLevel(this.getFocusItem(itemstack), FocusUpgradeType.extend);
    }

    public boolean consumeVis(ItemStack is, EntityPlayer player, Aspect aspect, int amount, boolean crafting) {
        amount = (int)((float)amount * this.getConsumptionModifier(is, player, aspect, crafting));
        if (this.getVis(is, aspect) >= amount) {
            this.storeVis(is, aspect, this.getVis(is, aspect) - amount);
            return true;
        }
        return false;
    }

    public boolean consumeAllVisCrafting(ItemStack is, EntityPlayer player, AspectList aspects, boolean doit) {
        if (aspects == null || aspects.size() == 0) {
            return false;
        }
        AspectList nl = new AspectList();
        for (Aspect aspect : aspects.getAspects()) {
            int cost = aspects.getAmount(aspect) * 100;
            nl.add(aspect, cost);
        }
        return this.consumeAllVis(is, player, nl, doit, true);
    }

    public boolean consumeAllVis(ItemStack is, EntityPlayer player, AspectList aspects, boolean doit, boolean crafting) {
        if (aspects == null || aspects.size() == 0) {
            return false;
        }
        AspectList nl = new AspectList();
        for (Aspect aspect : aspects.getAspects()) {
            int cost = aspects.getAmount(aspect);
            cost = (int)((float)cost * this.getConsumptionModifier(is, player, aspect, crafting));
            nl.add(aspect, cost);
        }
        for (Aspect aspect : nl.getAspects()) {
            if (this.getVis(is, aspect) >= nl.getAmount(aspect)) continue;
            return false;
        }
        if (doit && !player.field_70170_p.field_72995_K) {
            for (Aspect aspect : nl.getAspects()) {
                this.storeVis(is, aspect, this.getVis(is, aspect) - nl.getAmount(aspect));
            }
        }
        return true;
    }

    public int addVis(ItemStack is, Aspect aspect, int amount, boolean doit) {
        if (!aspect.isPrimal()) {
            return 0;
        }
        int storeAmount = this.getVis(is, aspect) + amount * 100;
        int leftover = Math.max(storeAmount - this.getMaxVis(is), 0);
        if (doit) {
            this.storeVis(is, aspect, Math.min(storeAmount, this.getMaxVis(is)));
        }
        return leftover / 100;
    }

    public int addRealVis(ItemStack is, Aspect aspect, int amount, boolean doit) {
        if (!aspect.isPrimal()) {
            return 0;
        }
        int storeAmount = this.getVis(is, aspect) + amount;
        int leftover = Math.max(storeAmount - this.getMaxVis(is), 0);
        if (doit) {
            this.storeVis(is, aspect, Math.min(storeAmount, this.getMaxVis(is)));
        }
        return leftover;
    }

    public void func_77663_a(ItemStack is, World w, Entity e, int slot, boolean currentItem) {
        if (!w.field_72995_K) {
            EntityPlayer player = (EntityPlayer)e;
            if (this.getRod(is).getOnUpdate() != null) {
                this.getRod(is).getOnUpdate().onUpdate(is, player);
            }
        }
    }

    public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        int ret;
        int ret2;
        Block bi = world.func_147439_a(x, y, z);
        int md = world.func_72805_g(x, y, z);
        boolean result = false;
        ForgeDirection direction = ForgeDirection.getOrientation((int)side);
        if (bi instanceof IWandable && (ret2 = ((IWandable)bi).onWandRightClick(world, itemstack, player, x, y, z, side, md)) >= 0) {
            return ret2 == 1;
        }
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof IWandable && (ret = ((IWandable)tile).onWandRightClick(world, itemstack, player, x, y, z, side, md)) >= 0) {
            return ret == 1;
        }
        if (WandTriggerRegistry.hasTrigger(bi, md)) {
            return WandTriggerRegistry.performTrigger(world, itemstack, player, x, y, z, side, bi, md);
        }
        if ((bi == ConfigBlocks.blockWoodenDevice && md == 2 || bi == ConfigBlocks.blockCosmeticOpaque && md == 2) && (!Config.wardedStone || tile != null && tile instanceof TileOwned && player.func_70005_c_().equals(((TileOwned)tile).owner))) {
            if (!world.field_72995_K) {
                ((TileOwned)tile).safeToRemove = true;
                world.func_72838_d((Entity)new EntityItem(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, new ItemStack(bi, 1, md)));
                world.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)bi) + (md << 12));
                world.func_147468_f(x, y, z);
            } else {
                player.func_71038_i();
            }
        }
        if (bi == ConfigBlocks.blockArcaneDoor && (!Config.wardedStone || tile != null && tile instanceof TileOwned && player.func_70005_c_().equals(((TileOwned)tile).owner))) {
            if (!world.field_72995_K) {
                ((TileOwned)tile).safeToRemove = true;
                tile = (md & 8) == 0 ? world.func_147438_o(x, y + 1, z) : world.func_147438_o(x, y - 1, z);
                if (tile != null && tile instanceof TileOwned) {
                    ((TileOwned)tile).safeToRemove = true;
                }
                if (Config.wardedStone || !Config.wardedStone && (md & 8) == 0) {
                    world.func_72838_d((Entity)new EntityItem(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, new ItemStack(ConfigItems.itemArcaneDoor)));
                }
                world.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)bi) + (md << 12));
                world.func_147468_f(x, y, z);
            } else {
                player.func_71038_i();
            }
        }
        return result;
    }

    public ItemFocusBasic getFocus(ItemStack stack) {
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("focus")) {
            NBTTagCompound nbt = stack.field_77990_d.func_74775_l("focus");
            return (ItemFocusBasic)ItemStack.func_77949_a((NBTTagCompound)nbt).func_77973_b();
        }
        return null;
    }

    public ItemStack getFocusItem(ItemStack stack) {
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("focus")) {
            NBTTagCompound nbt = stack.field_77990_d.func_74775_l("focus");
            return ItemStack.func_77949_a((NBTTagCompound)nbt);
        }
        return null;
    }

    public void setFocus(ItemStack stack, ItemStack focus) {
        if (focus == null) {
            stack.field_77990_d.func_82580_o("focus");
        } else {
            stack.func_77983_a("focus", (NBTBase)focus.func_77955_b(new NBTTagCompound()));
        }
    }

    public WandRod getRod(ItemStack stack) {
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("rod")) {
            return WandRod.rods.get(stack.field_77990_d.func_74779_i("rod"));
        }
        return ConfigItems.WAND_ROD_WOOD;
    }

    public boolean isStaff(ItemStack stack) {
        WandRod rod = this.getRod(stack);
        return rod != null && rod instanceof StaffRod;
    }

    public boolean isSceptre(ItemStack stack) {
        return stack.func_77942_o() && stack.field_77990_d.func_74764_b("sceptre");
    }

    public boolean hasRunes(ItemStack stack) {
        WandRod rod = this.getRod(stack);
        return rod != null && rod instanceof StaffRod && ((StaffRod)rod).hasRunes();
    }

    public void setRod(ItemStack stack, WandRod rod) {
        stack.func_77983_a("rod", (NBTBase)new NBTTagString(rod.getTag()));
        if (rod instanceof StaffRod) {
            NBTTagList tags = new NBTTagList();
            NBTTagCompound tag = new NBTTagCompound();
            tag.func_74778_a("AttributeName", SharedMonsterAttributes.field_111264_e.func_111108_a());
            AttributeModifier am = new AttributeModifier(field_111210_e, "Weapon modifier", 6.0, 0);
            tag.func_74778_a("Name", am.func_111166_b());
            tag.func_74780_a("Amount", am.func_111164_d());
            tag.func_74768_a("Operation", am.func_111169_c());
            tag.func_74772_a("UUIDMost", am.func_111167_a().getMostSignificantBits());
            tag.func_74772_a("UUIDLeast", am.func_111167_a().getLeastSignificantBits());
            tags.func_74742_a((NBTBase)tag);
            stack.field_77990_d.func_74782_a("AttributeModifiers", (NBTBase)tags);
        }
    }

    public WandCap getCap(ItemStack stack) {
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("cap")) {
            return WandCap.caps.get(stack.field_77990_d.func_74779_i("cap"));
        }
        return ConfigItems.WAND_CAP_IRON;
    }

    public void setCap(ItemStack stack, WandCap cap) {
        stack.func_77983_a("cap", (NBTBase)new NBTTagString(cap.getTag()));
    }

    public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player) {
        ItemFocusBasic focus;
        MovingObjectPosition movingobjectposition = this.func_77621_a(world, player, true);
        if (movingobjectposition != null && movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            ItemStack is;
            ItemStack is2;
            int i = movingobjectposition.field_72311_b;
            int j = movingobjectposition.field_72312_c;
            int k = movingobjectposition.field_72309_d;
            Block bi = world.func_147439_a(i, j, k);
            int md = world.func_72805_g(i, j, k);
            if (bi instanceof IWandable && (is2 = ((IWandable)bi).onWandRightClick(world, itemstack, player)) != null) {
                return is2;
            }
            TileEntity tile = world.func_147438_o(i, j, k);
            if (tile != null && tile instanceof IWandable && (is = ((IWandable)tile).onWandRightClick(world, itemstack, player)) != null) {
                return is;
            }
        }
        if ((focus = this.getFocus(itemstack)) != null && !WandManager.isOnCooldown((EntityLivingBase)player)) {
            WandManager.setCooldown((EntityLivingBase)player, focus.getActivationCooldown(this.getFocusItem(itemstack)));
            ItemStack ret = focus.onFocusRightClick(itemstack, world, player, movingobjectposition);
            if (ret != null) {
                return ret;
            }
        }
        return super.func_77659_a(itemstack, world, player);
    }

    public void setObjectInUse(ItemStack stack, int x, int y, int z) {
        if (stack.field_77990_d == null) {
            stack.field_77990_d = new NBTTagCompound();
        }
        stack.field_77990_d.func_74768_a("IIUX", x);
        stack.field_77990_d.func_74768_a("IIUY", y);
        stack.field_77990_d.func_74768_a("IIUZ", z);
    }

    public void clearObjectInUse(ItemStack stack) {
        if (stack.field_77990_d == null) {
            stack.field_77990_d = new NBTTagCompound();
        }
        stack.field_77990_d.func_82580_o("IIUX");
        stack.field_77990_d.func_82580_o("IIUY");
        stack.field_77990_d.func_82580_o("IIUZ");
    }

    public IWandable getObjectInUse(ItemStack stack, World world) {
        TileEntity te;
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("IIUX") && (te = world.func_147438_o(stack.field_77990_d.func_74762_e("IIUX"), stack.field_77990_d.func_74762_e("IIUY"), stack.field_77990_d.func_74762_e("IIUZ"))) != null && te instanceof IWandable) {
            return (IWandable)te;
        }
        return null;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        IWandable tv = this.getObjectInUse(stack, player.field_70170_p);
        if (tv != null) {
            this.animation = ItemFocusBasic.WandFocusAnimation.WAVE;
            tv.onUsingWandTick(stack, player, count);
        } else {
            ItemFocusBasic focus = this.getFocus(stack);
            if (focus != null && !WandManager.isOnCooldown((EntityLivingBase)player)) {
                WandManager.setCooldown((EntityLivingBase)player, focus.getActivationCooldown(this.getFocusItem(stack)));
                focus.onUsingFocusTick(stack, player, count);
            }
        }
    }

    public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int count) {
        IWandable tv = this.getObjectInUse(stack, player.field_70170_p);
        if (tv != null) {
            tv.onWandStoppedUsing(stack, world, player, count);
            this.animation = null;
        } else {
            ItemFocusBasic focus = this.getFocus(stack);
            if (focus != null) {
                focus.onPlayerStoppedUsingFocus(stack, world, player, count);
            }
        }
        this.clearObjectInUse(stack);
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack itemstack) {
        return Integer.MAX_VALUE;
    }

    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        ItemStack focus = this.getFocusItem(stack);
        if (focus != null && !WandManager.isOnCooldown(entityLiving)) {
            WandManager.setCooldown(entityLiving, this.getFocus(stack).getActivationCooldown(focus));
            return focus.func_77973_b().onEntitySwing(entityLiving, stack);
        }
        return super.onEntitySwing(entityLiving, stack);
    }

    public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) {
        ItemFocusBasic focus = this.getFocus(itemstack);
        if (focus != null && !WandManager.isOnCooldown((EntityLivingBase)player)) {
            WandManager.setCooldown((EntityLivingBase)player, focus.getActivationCooldown(this.getFocusItem(itemstack)));
            return focus.onFocusBlockStartBreak(itemstack, x, y, z, player);
        }
        return false;
    }

    public boolean canHarvestBlock(Block par1Block, ItemStack itemstack) {
        ItemFocusBasic focus = this.getFocus(itemstack);
        if (focus != null) {
            return this.getFocusItem(itemstack).func_77973_b().canHarvestBlock(par1Block, itemstack);
        }
        return false;
    }

    public float func_150893_a(ItemStack itemstack, Block block) {
        ItemFocusBasic focus = this.getFocus(itemstack);
        if (focus != null) {
            return this.getFocusItem(itemstack).func_77973_b().func_150893_a(itemstack, null);
        }
        return super.func_150893_a(itemstack, block);
    }

    @Override
    public ArrayList<BlockCoordinates> getArchitectBlocks(ItemStack stack, World world, int x, int y, int z, int side, EntityPlayer player) {
        ItemFocusBasic focus = this.getFocus(stack);
        if (focus != null && focus instanceof IArchitect && focus.isUpgradedWith(this.getFocusItem(stack), FocusUpgradeType.architect)) {
            return ((IArchitect)((Object)focus)).getArchitectBlocks(stack, world, x, y, z, side, player);
        }
        return null;
    }

    @Override
    public boolean showAxis(ItemStack stack, World world, EntityPlayer player, int side, IArchitect.EnumAxis axis) {
        ItemFocusBasic focus = this.getFocus(stack);
        if (focus != null && focus instanceof IArchitect && focus.isUpgradedWith(this.getFocusItem(stack), FocusUpgradeType.architect)) {
            return ((IArchitect)((Object)focus)).showAxis(stack, world, player, side, axis);
        }
        return false;
    }
}

