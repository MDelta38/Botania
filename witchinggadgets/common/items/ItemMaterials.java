/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.input.Keyboard
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.crafting.IInfusionStabiliser
 *  thaumcraft.api.crafting.InfusionEnchantmentRecipe
 *  thaumcraft.api.crafting.InfusionRecipe
 *  thaumcraft.api.nodes.INode
 *  thaumcraft.api.research.ScanResult
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.lib.crafting.InfusionRunicAugmentRecipe
 *  thaumcraft.common.lib.crafting.ThaumcraftCraftingManager
 *  thaumcraft.common.lib.events.EventHandlerRunic
 *  thaumcraft.common.lib.network.PacketHandler
 *  thaumcraft.common.lib.network.playerdata.PacketScannedToServer
 *  thaumcraft.common.lib.research.ResearchManager
 *  thaumcraft.common.lib.research.ScanManager
 *  thaumcraft.common.tiles.TileInfusionMatrix
 *  thaumcraft.common.tiles.TilePedestal
 *  thaumcraft.common.tiles.TileTable
 */
package witchinggadgets.common.items;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IInfusionStabiliser;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.research.ScanResult;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.crafting.InfusionRunicAugmentRecipe;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.events.EventHandlerRunic;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketScannedToServer;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thaumcraft.common.tiles.TilePedestal;
import thaumcraft.common.tiles.TileTable;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.blocks.tiles.TileEntityCuttingTable;
import witchinggadgets.common.util.Utilities;

public class ItemMaterials
extends Item {
    public IIcon iconPlateOverlay;
    public IIcon iconPhotoOverlay;
    private static final String[] subNames = new String[]{"threadSimple", "threadGold", "threadThaumium", "clothSpace", "clothGolden", "clothBewitched", "wolfPelt", "calculator", "cuttingTools", "photoPlate", "developedPhoto", "guidingString", "powerlessPearl", "gemstoneDust"};
    public IIcon[] icon = new IIcon[subNames.length];
    HashMap<String, Object> guidingLights = new HashMap();

    public ItemMaterials() {
        this.field_77777_bU = 64;
        this.func_77637_a(WitchingGadgets.tabWG);
        this.func_77627_a(true);
    }

    public int getItemStackLimit(ItemStack stack) {
        if (stack.func_77960_j() == 7 || stack.func_77960_j() == 8 || stack.func_77960_j() == 10 || stack.func_77960_j() == 11) {
            return 1;
        }
        return super.getItemStackLimit(stack);
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int useTicks, boolean equipped) {
        if (stack.func_77960_j() == 11 && stack.func_77942_o()) {
            NBTTagCompound last;
            NBTTagList positionList = stack.func_77978_p().func_150295_c("pos", 10);
            if (world.field_72995_K && equipped && positionList.func_74745_c() > 1) {
                for (int i = 1; i < positionList.func_74745_c(); ++i) {
                    last = positionList.func_150305_b(i - 1);
                    NBTTagCompound pp = positionList.func_150305_b(i);
                    double[] pos = new double[]{pp.func_74769_h("x"), pp.func_74769_h("y"), pp.func_74769_h("z")};
                    String ident = Math.floor(pos[0]) + "," + Math.floor(pos[1]) + "," + Math.floor(pos[2]);
                    this.guidingLights.put(ident, Thaumcraft.proxy.beamPower(world, pos[0], pos[1] + 1.0, pos[2], last.func_74769_h("x"), last.func_74769_h("y") + 1.0, last.func_74769_h("z"), 1.0f, 1.0f, 0.5f, true, this.guidingLights.get(ident)));
                }
                if (stack.func_77978_p().func_74767_n("active") && entity instanceof EntityPlayer) {
                    NBTTagCompound last2 = positionList.func_150305_b(positionList.func_74745_c() - 1);
                    double[] hand = ClientUtilities.getPlayerHandPos((EntityPlayer)entity, true);
                    this.guidingLights.put("player", Thaumcraft.proxy.beamPower(world, hand[0], hand[1], hand[2], last2.func_74769_h("x"), last2.func_74769_h("y") + 1.0, last2.func_74769_h("z"), 1.0f, 1.0f, 0.5f, true, this.guidingLights.get("player")));
                }
            }
            int playerViewEigth = MathHelper.func_76128_c((double)((double)(entity.field_70177_z * 8.0f / 360.0f) + 0.5)) & 7;
            if (positionList.func_74745_c() > 0 && stack.func_77978_p().func_74767_n("active")) {
                last = positionList.func_150305_b(positionList.func_74745_c() - 1);
                double dist = Math.abs(last.func_74769_h("x") - entity.field_70165_t) + Math.abs(last.func_74769_h("z") - entity.field_70161_v) + Math.abs(last.func_74769_h("z") - entity.field_70161_v);
                if (last.func_74762_e("facing") != playerViewEigth && dist > 1.0) {
                    NBTTagCompound newPos = new NBTTagCompound();
                    newPos.func_74780_a("x", entity.field_70165_t);
                    newPos.func_74780_a("y", entity.field_70163_u);
                    newPos.func_74780_a("z", entity.field_70161_v);
                    newPos.func_74768_a("facing", playerViewEigth);
                    positionList.func_74742_a((NBTBase)newPos);
                    stack.func_77978_p().func_74782_a("pos", (NBTBase)positionList);
                }
            }
        }
    }

    public int func_82790_a(ItemStack stack, int par2) {
        if (stack.func_77960_j() == 3) {
            return Aspect.VOID.getColor();
        }
        if (stack.func_77960_j() == 4) {
            return Aspect.GREED.getColor();
        }
        if (stack.func_77960_j() == 5) {
            return Aspect.MAGIC.getColor();
        }
        if (stack.func_77960_j() == 99) {
            return Color.HSBtoRGB(0.75f, 0.5f, 1.0f);
        }
        return super.func_82790_a(stack, par2);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass) {
        if (stack.func_77960_j() == 11 && stack.func_77942_o()) {
            return stack.func_77978_p().func_74767_n("active");
        }
        return super.hasEffect(stack, pass);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        try {
            if (stack.func_77960_j() == 11 && stack.func_77942_o() && stack.func_77978_p().func_74767_n("active")) {
                list.add("wg.desc.active");
            }
            if (stack.func_77960_j() == 9 || stack.func_77960_j() == 10) {
                if (!stack.func_77942_o()) {
                    return;
                }
                ScanResult scan = Utilities.readScanResultFromNBT(stack.func_77978_p().func_74775_l("scanResult"), player.field_70170_p);
                if (scan != null) {
                    String name = "";
                    AspectList aspects = new AspectList();
                    switch (scan.type) {
                        case 1: {
                            ItemStack scanStack = new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta);
                            name = "\u00a7" + scanStack.func_77953_t().field_77937_e.func_96298_a() + scanStack.func_82833_r();
                            aspects = ThaumcraftCraftingManager.getObjectTags((ItemStack)new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta));
                            aspects = ThaumcraftCraftingManager.getBonusTags((ItemStack)new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta), (AspectList)aspects);
                            break;
                        }
                        case 2: {
                            if (scan.entity instanceof EntityItem) {
                                EntityItem item = (EntityItem)scan.entity;
                                name = "\u00a7" + item.func_92059_d().func_77953_t().field_77937_e.func_96298_a() + item.func_92059_d().func_82833_r();
                                aspects = ThaumcraftCraftingManager.getObjectTags((ItemStack)new ItemStack(item.func_92059_d().func_77973_b(), 1, item.func_92059_d().func_77960_j()));
                                aspects = ThaumcraftCraftingManager.getBonusTags((ItemStack)new ItemStack(item.func_92059_d().func_77973_b(), 1, item.func_92059_d().func_77960_j()), (AspectList)aspects);
                                break;
                            }
                            name = scan.entity.func_70005_c_();
                            aspects = ScanManager.generateEntityAspects((Entity)scan.entity);
                            break;
                        }
                        case 3: {
                            if (!scan.phenomena.startsWith("NODE")) break;
                            name = StatCollector.func_74838_a((String)"tile.blockAiry.0.name");
                            aspects = Utilities.generateNodeAspects(player.field_70170_p, scan.phenomena.replace("NODE", ""));
                        }
                    }
                    list.add(" " + name);
                    if (Keyboard.isKeyDown((int)29) || Keyboard.isKeyDown((int)157)) {
                        for (Aspect a : aspects.getAspectsSorted()) {
                            if (a == null || aspects.getAmount(a) <= 0) continue;
                            list.add("  \u00a77" + a.getName() + ": " + aspects.getAmount(a));
                        }
                    } else {
                        list.add("  " + StatCollector.func_74838_a((String)"wg.desc.ctrlForAspectList"));
                    }
                    list.add(" " + StatCollector.func_74838_a((String)"wg.desc.scanCompleted") + ": " + ScanManager.hasBeenScanned((EntityPlayer)player, (ScanResult)scan));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.icon[0] = iconRegister.func_94245_a("witchinggadgets:mat_" + subNames[0]);
        this.icon[1] = iconRegister.func_94245_a("witchinggadgets:mat_" + subNames[1]);
        this.icon[2] = iconRegister.func_94245_a("witchinggadgets:mat_" + subNames[2]);
        this.icon[3] = iconRegister.func_94245_a("witchinggadgets:mat_cloth");
        this.icon[4] = iconRegister.func_94245_a("witchinggadgets:mat_cloth");
        this.icon[5] = iconRegister.func_94245_a("witchinggadgets:mat_cloth");
        for (int i = 6; i < subNames.length; ++i) {
            this.icon[i] = iconRegister.func_94245_a("witchinggadgets:mat_" + subNames[i]);
        }
        this.iconPlateOverlay = iconRegister.func_94245_a("witchinggadgets:mat_" + subNames[9] + "_overlay");
        this.iconPhotoOverlay = iconRegister.func_94245_a("witchinggadgets:mat_" + subNames[10] + "_overlay");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int metadata) {
        if (metadata < this.icon.length) {
            return this.icon[metadata];
        }
        return this.icon[0];
    }

    public IIcon func_77618_c(int meta, int pass) {
        if (pass == 99) {
            return meta == 9 ? this.iconPlateOverlay : this.iconPlateOverlay;
        }
        return this.func_77617_a(meta);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.func_77618_c(stack.func_77960_j(), pass);
    }

    public int func_77647_b(int damageValue) {
        return damageValue;
    }

    public String func_77667_c(ItemStack itemstack) {
        return this.func_77658_a() + "." + subNames[Math.min(itemstack.func_77960_j(), subNames.length - 1)];
    }

    public void func_150895_a(Item item, CreativeTabs tab, List itemList) {
        for (int i = 0; i < subNames.length; ++i) {
            itemList.add(new ItemStack(item, 1, i));
        }
        ItemStack luckyCoin = new ItemStack(ConfigItems.itemResource, 1, 18);
        luckyCoin.func_77966_a(Enchantment.field_77346_s, 1);
        luckyCoin.func_77966_a(Enchantment.field_77335_o, 1);
        itemList.add(luckyCoin);
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int targetX, int targetY, int targetZ, int side, float hitX, float hitY, float hitZ) {
        if (subNames[stack.func_77960_j()] == "calculator" && !world.field_72995_K) {
            if (world.func_147438_o(targetX, targetY, targetZ) instanceof INode) {
                AspectList al = ResearchManager.reduceToPrimals((AspectList)((INode)world.func_147438_o(targetX, targetY, targetZ)).getAspects());
                player.func_145747_a((IChatComponent)new ChatComponentTranslation("wg.chat.nodeContents.primal", new Object[]{al.getAmount(Aspect.AIR), al.getAmount(Aspect.EARTH), al.getAmount(Aspect.FIRE), al.getAmount(Aspect.WATER), al.getAmount(Aspect.ORDER), al.getAmount(Aspect.ENTROPY)}));
                return true;
            }
            if (world.func_147438_o(targetX, targetY, targetZ) instanceof TileInfusionMatrix) {
                int dz;
                int dx;
                ArrayList<ChunkCoordinates> stabilizers = new ArrayList<ChunkCoordinates>();
                ArrayList<ChunkCoordinates> pedestals = new ArrayList<ChunkCoordinates>();
                ArrayList<Object[]> warnings = new ArrayList<Object[]>();
                ArrayList<ItemStack> components = new ArrayList<ItemStack>();
                for (int xx = -12; xx <= 12; ++xx) {
                    for (int zz = -12; zz <= 12; ++zz) {
                        boolean skip = false;
                        for (int yy = -10; yy <= 5; ++yy) {
                            if (xx == 0 && zz == 0) continue;
                            int x = targetX + xx;
                            int y = targetY + yy;
                            int z = targetZ + zz;
                            TileEntity te = world.func_147438_o(x, y, z);
                            if (!skip && te != null && te instanceof TilePedestal) {
                                if (yy >= 0) {
                                    warnings.add(new Object[]{"pedestalHeight", x, y, z});
                                    continue;
                                }
                                if (Math.abs(xx) > 8 || Math.abs(zz) > 8) {
                                    warnings.add(new Object[]{"pedestalPos", x, y, z});
                                    continue;
                                }
                                pedestals.add(new ChunkCoordinates(x, y, z));
                                skip = true;
                                continue;
                            }
                            Block bi = world.func_147439_a(x, y, z);
                            if (bi != Blocks.field_150465_bP && (!(bi instanceof IInfusionStabiliser) || !((IInfusionStabiliser)bi).canStabaliseInfusion(world, x, y, z))) continue;
                            stabilizers.add(new ChunkCoordinates(x, y, z));
                        }
                    }
                }
                int symmetry = 0;
                for (ChunkCoordinates cc : pedestals) {
                    int zz;
                    int xx;
                    boolean items = false;
                    dx = targetX - cc.field_71574_a;
                    dz = targetZ - cc.field_71573_c;
                    TileEntity te = world.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
                    if (te != null && te instanceof TilePedestal) {
                        symmetry += 2;
                        if (((TilePedestal)te).func_70301_a(0) != null) {
                            ++symmetry;
                            items = true;
                            components.add(((TilePedestal)te).func_70301_a(0));
                        }
                    }
                    if ((te = world.func_147438_o(xx = targetX + dx, cc.field_71572_b, zz = targetZ + dz)) != null && te instanceof TilePedestal) {
                        symmetry -= 2;
                        if (((TilePedestal)te).func_70301_a(0) == null) continue;
                        if (items) {
                            --symmetry;
                            continue;
                        }
                        warnings.add(new Object[]{"noPartnerItem", xx, cc.field_71572_b, zz});
                        continue;
                    }
                    warnings.add(new Object[]{"noPartnerPedestal", cc.field_71574_a, cc.field_71572_b, cc.field_71573_c});
                }
                float sym = 0.0f;
                for (ChunkCoordinates cc : stabilizers) {
                    int zz;
                    int xx;
                    dx = targetX - cc.field_71574_a;
                    dz = targetZ - cc.field_71573_c;
                    Block bi = world.func_147439_a(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
                    if (bi == Blocks.field_150465_bP || bi instanceof IInfusionStabiliser && ((IInfusionStabiliser)bi).canStabaliseInfusion(world, cc.field_71574_a, cc.field_71572_b, cc.field_71573_c)) {
                        sym += 0.1f;
                    }
                    if ((bi = world.func_147439_a(xx = targetX + dx, cc.field_71572_b, zz = targetZ + dz)) == Blocks.field_150465_bP || bi instanceof IInfusionStabiliser && ((IInfusionStabiliser)bi).canStabaliseInfusion(world, xx, cc.field_71572_b, zz)) {
                        sym -= 0.2f;
                        continue;
                    }
                    warnings.add(new Object[]{"noPartnerStabilizer", cc.field_71574_a, cc.field_71572_b, cc.field_71573_c});
                }
                symmetry = (int)((float)symmetry + sym);
                player.func_145747_a(new ChatComponentTranslation("wg.chat.infusionInfo.stabilityTotal", new Object[]{symmetry * -1}).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.DARK_PURPLE)));
                ItemStack central = null;
                TileEntity te = world.func_147438_o(targetX, targetY - 2, targetZ);
                if (te instanceof TilePedestal && ((TilePedestal)te).func_70301_a(0) != null) {
                    central = ((TilePedestal)te).func_70301_a(0).func_77946_l();
                }
                if (central != null) {
                    InfusionRecipe infRecipe = ThaumcraftCraftingManager.findMatchingInfusionRecipe(components, central, (EntityPlayer)player);
                    InfusionEnchantmentRecipe enchRecipe = ThaumcraftCraftingManager.findMatchingInfusionEnchantmentRecipe(components, (ItemStack)central, (EntityPlayer)player);
                    if (enchRecipe != null) {
                        player.func_145747_a(new ChatComponentTranslation("wg.chat.infusionInfo.instability", new Object[]{enchRecipe.calcInstability(central)}).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.DARK_PURPLE)));
                        float essmod = 1.0f + enchRecipe.getEssentiaMod(central);
                        if (essmod > 1.0f) {
                            String plaintext = "";
                            Iterator it = enchRecipe.aspects.aspects.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry e = it.next();
                                plaintext = plaintext + (int)((float)((Integer)e.getValue()).intValue() * essmod) + " " + ((Aspect)e.getKey()).getName() + (it.hasNext() ? ", " : "");
                            }
                            player.func_145747_a(new ChatComponentTranslation("wg.chat.infusionInfo.essentiaMod", new Object[]{Float.valueOf(essmod)}).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.GREEN)));
                            player.func_145747_a(new ChatComponentText(plaintext).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.GRAY)));
                        }
                    }
                    if (infRecipe != null) {
                        player.func_145747_a(new ChatComponentTranslation("wg.chat.infusionInfo.instability", new Object[]{infRecipe.getInstability(central)}).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.DARK_PURPLE)));
                        if (infRecipe instanceof InfusionRunicAugmentRecipe) {
                            int vis = (int)(32.0 * Math.pow(2.0, EventHandlerRunic.getFinalCharge((ItemStack)central)));
                            String plaintext = "";
                            if (vis > 0) {
                                plaintext = plaintext + vis + " " + Aspect.ENERGY.getName() + ", " + vis / 2 + " " + Aspect.MAGIC.getName() + ", " + vis / 2 + " " + Aspect.ARMOR.getName();
                            }
                            player.func_145747_a(new ChatComponentTranslation("wg.chat.infusionInfo.essentiaRunicMod", new Object[]{EventHandlerRunic.getFinalCharge((ItemStack)central)}).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.GREEN)));
                            player.func_145747_a(new ChatComponentText(plaintext).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.GRAY)));
                        }
                    }
                }
                for (Object[] warning : warnings) {
                    String w = "wg.chat.infusionWarning." + warning[0];
                    player.func_145747_a(new ChatComponentTranslation(w, new Object[]{warning[1], warning[2], warning[3]}).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.DARK_GRAY)));
                }
                return true;
            }
        }
        return false;
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (subNames[stack.func_77960_j()] == "cuttingTools" && world.func_147438_o(x, y, z) instanceof TileTable) {
            world.func_147465_d(x, y, z, WGContent.BlockWoodenDevice, 3, 3);
            if (world.func_147438_o(x, y, z) instanceof TileEntityCuttingTable) {
                int f;
                int playerViewQuarter = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
                ((TileEntityCuttingTable)world.func_147438_o((int)x, (int)y, (int)z)).facing = f = playerViewQuarter == 0 ? 2 : (playerViewQuarter == 1 ? 5 : (playerViewQuarter == 2 ? 3 : 4));
            }
            return true;
        }
        return false;
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        ScanResult scan;
        if (stack.func_77960_j() == 10 && stack.func_77942_o() && (scan = Utilities.readScanResultFromNBT(stack.func_77978_p().func_74775_l("scanResult"), world)) != null && !ScanManager.hasBeenScanned((EntityPlayer)player, (ScanResult)scan)) {
            if (world.field_72995_K && ScanManager.completeScan((EntityPlayer)player, (ScanResult)scan, (String)"@")) {
                PacketHandler.INSTANCE.sendToServer((IMessage)new PacketScannedToServer(scan, player, "@"));
            }
            player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
        }
        if (stack.func_77960_j() == 11) {
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            NBTTagList positionList = stack.func_77978_p().func_150295_c("pos", 10);
            int playerViewEigth = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 8.0f / 360.0f) + 0.5)) & 7;
            if (player.func_70093_af()) {
                stack.func_77978_p().func_82580_o("pos");
                stack.func_77978_p().func_74757_a("active", false);
            } else {
                stack.func_77978_p().func_74757_a("active", !stack.func_77978_p().func_74767_n("active"));
                NBTTagCompound newPos = new NBTTagCompound();
                newPos.func_74780_a("x", player.field_70165_t);
                newPos.func_74780_a("y", player.field_70163_u);
                newPos.func_74780_a("z", player.field_70161_v);
                newPos.func_74768_a("facing", playerViewEigth);
                positionList.func_74742_a((NBTBase)newPos);
                stack.func_77978_p().func_74782_a("pos", (NBTBase)positionList);
            }
        }
        return stack;
    }
}

