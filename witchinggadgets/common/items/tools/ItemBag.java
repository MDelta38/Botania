/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package witchinggadgets.common.items.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import witchinggadgets.WitchingGadgets;

public class ItemBag
extends Item {
    String[] subNames = new String[]{"normal", "void", "ender", "hungry"};
    IIcon[] overlayIcons = new IIcon[this.subNames.length];

    public ItemBag() {
        this.func_77627_a(true);
        this.func_77625_d(1);
        this.func_77637_a(WitchingGadgets.tabWG);
    }

    public static int getDefaultBagColour(int meta) {
        return meta == 1 ? 0x484848 : (meta == 2 ? 2967361 : (meta == 3 ? 8737138 : 9073853));
    }

    public int func_82790_a(ItemStack stack, int pass) {
        return this.getBagColorFromItemStack(stack, pass);
    }

    public int getBagColorFromItemStack(ItemStack stack, int pass) {
        if (pass > 0) {
            return 0xFFFFFF;
        }
        NBTTagCompound tag = stack.func_77978_p();
        if (tag == null) {
            return ItemBag.getDefaultBagColour(stack.func_77960_j());
        }
        NBTTagCompound tagDisplay = tag.func_74775_l("display");
        return tagDisplay == null ? ItemBag.getDefaultBagColour(stack.func_77960_j()) : (tagDisplay.func_74764_b("color") ? tagDisplay.func_74762_e("color") : ItemBag.getDefaultBagColour(stack.func_77960_j()));
    }

    public void removeColorFromItemStack(ItemStack stack) {
        NBTTagCompound tag1;
        NBTTagCompound tag = stack.func_77978_p();
        if (tag != null && (tag1 = tag.func_74775_l("display")).func_74764_b("color")) {
            tag1.func_82580_o("color");
        }
    }

    public void modifyColorOnItemStack(ItemStack stack, int par2) {
        NBTTagCompound tag = stack.func_77978_p();
        if (tag == null) {
            tag = new NBTTagCompound();
            stack.func_77982_d(tag);
        }
        NBTTagCompound tag1 = tag.func_74775_l("display");
        if (!tag.func_74764_b("display")) {
            tag.func_74782_a("display", (NBTBase)tag1);
        }
        tag1.func_74768_a("color", par2);
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("witchinggadgets:bag");
        for (int i = 0; i < this.overlayIcons.length; ++i) {
            if (i == 0) continue;
            this.overlayIcons[i] = iconRegister.func_94245_a("witchinggadgets:bagOverlay_" + this.subNames[i]);
        }
    }

    public boolean func_77623_v() {
        return true;
    }

    public int getRenderPasses(int meta) {
        return meta > 0 ? 2 : 1;
    }

    public IIcon func_77618_c(int meta, int pass) {
        if (pass == 0) {
            return this.field_77791_bV;
        }
        return this.overlayIcons[meta];
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        super.func_77663_a(stack, world, entity, par4, par5);
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        return stack.func_77960_j() == 1 ? EnumRarity.epic : EnumRarity.rare;
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K) {
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            if (!stack.func_77978_p().func_74764_b("Owner")) {
                stack.func_77978_p().func_74778_a("Owner", player.func_70005_c_());
            }
            if ((stack.func_77960_j() == 0 || stack.func_77960_j() == 3) && stack.func_77942_o()) {
                if (!(player.field_71075_bZ.field_75098_d || stack.func_77978_p().func_74767_n("unlocked") || stack.func_77978_p().func_74779_i("Owner").equalsIgnoreCase(player.func_70005_c_()))) {
                    player.func_70097_a(DamageSource.field_76376_m, 4.0f);
                    player.func_71040_bB(true);
                    player.func_146105_b((IChatComponent)new ChatComponentTranslation("wg.chat.notyourbag", new Object[]{stack.func_77978_p().func_74779_i("Owner")}));
                    return stack;
                }
                if (stack.func_77978_p().func_74779_i("Owner").equalsIgnoreCase(player.func_70005_c_()) && player.func_70093_af()) {
                    stack.func_77978_p().func_74757_a("unlocked", !stack.func_77978_p().func_74767_n("unlocked"));
                    player.func_146105_b((IChatComponent)new ChatComponentTranslation("wg.chat.bag" + (stack.func_77978_p().func_74767_n("unlocked") ? "un" : "") + "locked", new Object[0]));
                    return stack;
                }
            }
            if (stack.func_77960_j() == 0 || stack.func_77960_j() == 3) {
                player.openGui((Object)WitchingGadgets.instance, 3, world, MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v));
            } else if (stack.func_77960_j() == 1) {
                player.openGui((Object)WitchingGadgets.instance, 11, world, MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v));
            } else if (stack.func_77960_j() == 2) {
                player.func_71007_a((IInventory)player.func_71005_bN());
            }
        }
        return super.func_77659_a(stack, world, player);
    }

    public String func_77667_c(ItemStack itemstack) {
        return this.func_77658_a() + "." + this.subNames[itemstack.func_77960_j()];
    }

    public void func_150895_a(Item item, CreativeTabs tab, List itemList) {
        for (int i = 0; i < this.subNames.length; ++i) {
            itemList.add(new ItemStack((Item)this, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack item, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        if (item.func_77960_j() == 1) {
            list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"wg.desc.filteredItems"));
            for (ItemStack stack : this.getStoredItems(item)) {
                if (stack == null) continue;
                list.add(EnumChatFormatting.DARK_GRAY + " " + stack.func_82833_r());
            }
        }
    }

    public ItemStack[] getStoredItems(ItemStack item) {
        ItemStack[] stackList = new ItemStack[18];
        if (item.func_77942_o()) {
            NBTTagList inv = item.func_77978_p().func_150295_c("Inventory", 10);
            for (int i = 0; i < inv.func_74745_c(); ++i) {
                NBTTagCompound tag = inv.func_150305_b(i);
                int slot = tag.func_74771_c("Slot") & 0xFF;
                if (slot < 0 || slot >= stackList.length) continue;
                stackList[slot] = ItemStack.func_77949_a((NBTTagCompound)tag);
            }
        }
        return stackList;
    }

    public void setStoredItems(ItemStack item, ItemStack[] stackList) {
        NBTTagList inv = new NBTTagList();
        for (int i = 0; i < stackList.length; ++i) {
            if (stackList[i] == null) continue;
            NBTTagCompound tag = new NBTTagCompound();
            tag.func_74774_a("Slot", (byte)i);
            stackList[i].func_77955_b(tag);
            inv.func_74742_a((NBTBase)tag);
        }
        if (!item.func_77942_o()) {
            item.func_77982_d(new NBTTagCompound());
        }
        item.func_77978_p().func_74782_a("Inventory", (NBTBase)inv);
    }
}

