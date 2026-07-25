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
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagByte
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockArcaneDoor;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.tiles.TileOwned;

public class ItemKey
extends Item {
    public IIcon iconIron;
    public IIcon iconGold;

    public ItemKey() {
        this.func_77625_d(64);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.iconIron = ir.func_94245_a("thaumcraft:keyiron");
        this.iconGold = ir.func_94245_a("thaumcraft:keygold");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return par1 == 0 ? this.iconIron : this.iconGold;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 0));
        par3List.add(new ItemStack((Item)this, 1, 1));
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }

    public boolean func_77636_d(ItemStack par1ItemStack) {
        return par1ItemStack.func_77942_o();
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }

    public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        Block bi = world.func_147439_a(x, y, z);
        int md = world.func_72805_g(x, y, z);
        if (bi == ConfigBlocks.blockArcaneDoor || bi == ConfigBlocks.blockWoodenDevice && (md == 2 || md == 3)) {
            int mod = 0;
            int mod2 = 1;
            byte type = 0;
            if (bi == ConfigBlocks.blockArcaneDoor) {
                int var10 = ((BlockArcaneDoor)bi).getFullMetadata((IBlockAccess)world, x, y, z);
                if ((var10 & 8) != 0) {
                    mod = -1;
                    mod2 = 0;
                }
            } else {
                type = 1;
            }
            String loc = x + "," + (y + mod) + "," + z;
            TileEntity tile = world.func_147438_o(x, y + mod, z);
            if (tile != null && tile instanceof TileOwned) {
                if (!itemstack.func_77942_o()) {
                    if (player.func_70005_c_().equals(((TileOwned)tile).owner) || ((TileOwned)tile).accessList.contains("1" + player.func_70005_c_()) && itemstack.func_77960_j() == 0) {
                        ItemStack st = new ItemStack(ConfigItems.itemKey, 1, itemstack.func_77960_j());
                        st.func_77983_a("location", (NBTBase)new NBTTagString(loc));
                        st.func_77983_a("type", (NBTBase)new NBTTagByte(type));
                        if (!player.field_71071_by.func_70441_a(st) && !world.field_72995_K) {
                            world.func_72838_d((Entity)new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, st));
                        }
                        if (!player.field_71075_bZ.field_75098_d) {
                            --itemstack.field_77994_a;
                        }
                        if (!world.field_72995_K) {
                            switch (type) {
                                case 0: {
                                    player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"tc.key1")));
                                    break;
                                }
                                case 1: {
                                    player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"tc.key2")));
                                }
                            }
                            world.func_72908_a((double)x, (double)y, (double)z, "thaumcraft:key", 1.0f, 0.9f);
                        }
                        player.func_71038_i();
                    }
                } else if (!player.func_70005_c_().equals(((TileOwned)tile).owner) && !((TileOwned)tile).accessList.contains(itemstack.func_77960_j() + player.func_70005_c_()) && !((TileOwned)tile).accessList.contains("1" + player.func_70005_c_()) && loc.equals(itemstack.field_77990_d.func_74779_i("location"))) {
                    ((TileOwned)tile).accessList.add(itemstack.func_77960_j() + player.func_70005_c_());
                    if (type == 0) {
                        TileEntity tile2 = world.func_147438_o(x, y + mod2, z);
                        if (tile2 != null && tile2 instanceof TileOwned) {
                            ((TileOwned)tile2).accessList.add(itemstack.func_77960_j() + player.func_70005_c_());
                        }
                        world.func_147471_g(x, y + mod2, z);
                    }
                    world.func_147471_g(x, y + mod, z);
                    if (!world.field_72995_K) {
                        switch (type) {
                            case 0: {
                                player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"tc.key3") + (itemstack.func_77960_j() == 0 ? "" : StatCollector.func_74838_a((String)"tc.key4"))));
                                break;
                            }
                            case 1: {
                                player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"tc.key5") + (itemstack.func_77960_j() == 0 ? "" : StatCollector.func_74838_a((String)"tc.key6"))));
                            }
                        }
                        world.func_72908_a((double)x, (double)y, (double)z, "thaumcraft:key", 1.0f, 1.1f);
                    }
                    if (!player.field_71075_bZ.field_75098_d) {
                        --itemstack.field_77994_a;
                    }
                    player.func_71038_i();
                } else if (!world.field_72995_K) {
                    if (!loc.equals(itemstack.field_77990_d.func_74779_i("location"))) {
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"tc.key7")));
                    } else {
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"tc.key8")));
                    }
                }
            }
            return !world.field_72995_K;
        }
        return true;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("location")) {
            String location = stack.field_77990_d.func_74779_i("location");
            try {
                String[] ss = location.split(",");
                location = "x " + ss[0] + ", z " + ss[2] + ", y " + ss[1];
            }
            catch (Exception e) {
                // empty catch block
            }
            byte type = stack.field_77990_d.func_74771_c("type");
            list.add("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"tc.key9"));
            list.add("\u00a75\u00a7o" + (type == 0 ? StatCollector.func_74838_a((String)"tc.key10") : StatCollector.func_74838_a((String)"tc.key11")));
            list.add("\u00a75\u00a7o" + location);
        }
    }
}

