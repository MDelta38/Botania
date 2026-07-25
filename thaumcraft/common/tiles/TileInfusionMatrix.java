/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.crafting.IInfusionStabiliser;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.container.InventoryFake;
import thaumcraft.common.lib.crafting.InfusionRunicAugmentRecipe;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.events.EssentiaHandler;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
import thaumcraft.common.lib.network.fx.PacketFXInfusionSource;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileInfusionPillar;
import thaumcraft.common.tiles.TilePedestal;

public class TileInfusionMatrix
extends TileThaumcraft
implements IWandable,
IAspectContainer {
    private ArrayList<ChunkCoordinates> pedestals = new ArrayList();
    private int dangerCount = 0;
    public boolean active = false;
    public boolean crafting = false;
    public boolean checkSurroundings = true;
    public int symmetry = 0;
    public int instability = 0;
    private AspectList recipeEssentia = new AspectList();
    private ArrayList<ItemStack> recipeIngredients = null;
    private Object recipeOutput = null;
    private String recipePlayer = null;
    private String recipeOutputLabel = null;
    private ItemStack recipeInput = null;
    private int recipeInstability = 0;
    private int recipeXP = 0;
    private int recipeType = 0;
    public HashMap<String, SourceFX> sourceFX = new HashMap();
    public int count = 0;
    public int craftCount = 0;
    public float startUp;
    private int countDelay = 10;
    ArrayList<ItemStack> ingredients = new ArrayList();
    int itemCount = 0;

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)(this.field_145848_d - 1), (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbtCompound) {
        this.active = nbtCompound.func_74767_n("active");
        this.crafting = nbtCompound.func_74767_n("crafting");
        this.instability = nbtCompound.func_74765_d("instability");
        this.recipeEssentia.readFromNBT(nbtCompound);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbtCompound) {
        nbtCompound.func_74757_a("active", this.active);
        nbtCompound.func_74757_a("crafting", this.crafting);
        nbtCompound.func_74777_a("instability", (short)this.instability);
        this.recipeEssentia.writeToNBT(nbtCompound);
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
            this.recipeOutput = ItemStack.func_77949_a((NBTTagCompound)nbtCompound.func_74775_l("recipeout"));
        } else if (rot != null) {
            this.recipeOutputLabel = rot;
            this.recipeOutput = nbtCompound.func_74781_a("recipeout");
        }
        this.recipeInput = ItemStack.func_77949_a((NBTTagCompound)nbtCompound.func_74775_l("recipeinput"));
        this.recipeInstability = nbtCompound.func_74762_e("recipeinst");
        this.recipeType = nbtCompound.func_74762_e("recipetype");
        this.recipeXP = nbtCompound.func_74762_e("recipexp");
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
        if (this.recipeOutput != null && this.recipeOutput instanceof ItemStack) {
            nbtCompound.func_74778_a("rotype", "@");
        }
        if (this.recipeOutput != null && this.recipeOutput instanceof NBTBase) {
            nbtCompound.func_74778_a("rotype", this.recipeOutputLabel);
        }
        if (this.recipeOutput != null && this.recipeOutput instanceof ItemStack) {
            nbtCompound.func_74782_a("recipeout", (NBTBase)((ItemStack)this.recipeOutput).func_77955_b(new NBTTagCompound()));
        }
        if (this.recipeOutput != null && this.recipeOutput instanceof NBTBase) {
            nbtCompound.func_74782_a("recipeout", (NBTBase)this.recipeOutput);
        }
        if (this.recipeInput != null) {
            nbtCompound.func_74782_a("recipeinput", (NBTBase)this.recipeInput.func_77955_b(new NBTTagCompound()));
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

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
        ++this.count;
        if (this.checkSurroundings) {
            this.checkSurroundings = false;
            this.getSurroundings();
        }
        if (this.field_145850_b.field_72995_K) {
            this.doEffects();
        } else {
            if (this.count % (this.crafting ? 20 : 100) == 0 && !this.validLocation()) {
                this.active = false;
                this.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                return;
            }
            if (this.active && this.crafting && this.count % this.countDelay == 0) {
                this.craftCycle();
                this.func_70296_d();
            }
        }
    }

    public boolean validLocation() {
        TileEntity te = null;
        te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
        if (te == null || !(te instanceof TilePedestal)) {
            return false;
        }
        te = this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d - 2, this.field_145849_e + 1);
        if (te == null || !(te instanceof TileInfusionPillar)) {
            return false;
        }
        te = this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d - 2, this.field_145849_e - 1);
        if (te == null || !(te instanceof TileInfusionPillar)) {
            return false;
        }
        te = this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d - 2, this.field_145849_e - 1);
        if (te == null || !(te instanceof TileInfusionPillar)) {
            return false;
        }
        te = this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d - 2, this.field_145849_e + 1);
        return te != null && te instanceof TileInfusionPillar;
    }

    public void craftingStart(EntityPlayer player) {
        TilePedestal ped;
        if (!this.validLocation()) {
            this.active = false;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return;
        }
        this.getSurroundings();
        TileEntity te = null;
        this.recipeInput = null;
        te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
        if (te != null && te instanceof TilePedestal && (ped = (TilePedestal)te).func_70301_a(0) != null) {
            this.recipeInput = ped.func_70301_a(0).func_77946_l();
        }
        if (this.recipeInput == null) {
            return;
        }
        ArrayList<ItemStack> components = new ArrayList<ItemStack>();
        for (ChunkCoordinates cc : this.pedestals) {
            TilePedestal ped2;
            te = this.field_145850_b.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
            if (te == null || !(te instanceof TilePedestal) || (ped2 = (TilePedestal)te).func_70301_a(0) == null) continue;
            components.add(ped2.func_70301_a(0).func_77946_l());
        }
        if (components.size() == 0) {
            return;
        }
        InfusionRecipe recipe = ThaumcraftCraftingManager.findMatchingInfusionRecipe(components, this.recipeInput, player);
        if (recipe != null) {
            this.recipeType = 0;
            this.recipeIngredients = new ArrayList();
            if (recipe instanceof InfusionRunicAugmentRecipe) {
                for (ItemStack ing : ((InfusionRunicAugmentRecipe)recipe).getComponents(this.recipeInput)) {
                    this.recipeIngredients.add(ing.func_77946_l());
                }
            } else {
                for (ItemStack ing : recipe.getComponents()) {
                    this.recipeIngredients.add(ing.func_77946_l());
                }
            }
            if (recipe.getRecipeOutput(this.recipeInput) instanceof Object[]) {
                Object[] obj = (Object[])recipe.getRecipeOutput(this.recipeInput);
                this.recipeOutputLabel = (String)obj[0];
                this.recipeOutput = (NBTBase)obj[1];
            } else {
                this.recipeOutput = recipe.getRecipeOutput(this.recipeInput);
            }
            this.recipeInstability = recipe.getInstability(this.recipeInput);
            this.recipeEssentia = recipe.getAspects(this.recipeInput).copy();
            this.recipePlayer = player.func_70005_c_();
            this.instability = this.symmetry + this.recipeInstability;
            this.crafting = true;
            this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:craftstart", 0.5f, 1.0f);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
            return;
        }
        InfusionEnchantmentRecipe recipe2 = ThaumcraftCraftingManager.findMatchingInfusionEnchantmentRecipe(components, this.recipeInput, player);
        if (recipe2 != null) {
            this.recipeType = 1;
            this.recipeIngredients = new ArrayList();
            for (ItemStack ing : recipe2.components) {
                this.recipeIngredients.add(ing.func_77946_l());
            }
            this.recipeOutput = recipe2.getEnchantment();
            this.recipeInstability = recipe2.calcInstability(this.recipeInput);
            AspectList esscost = recipe2.aspects.copy();
            float essmod = recipe2.getEssentiaMod(this.recipeInput);
            for (Aspect as : esscost.getAspects()) {
                esscost.add(as, (int)((float)esscost.getAmount(as) * essmod));
            }
            this.recipeEssentia = esscost;
            this.recipeXP = recipe2.calcXP(this.recipeInput);
            this.instability = this.symmetry + this.recipeInstability;
            this.crafting = true;
            this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:craftstart", 0.5f, 1.0f);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
            return;
        }
    }

    public void craftCycle() {
        Aspect[] ingEss;
        TilePedestal ped;
        boolean valid = false;
        TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
        if (te != null && te instanceof TilePedestal && (ped = (TilePedestal)te).func_70301_a(0) != null) {
            ItemStack i2 = ped.func_70301_a(0).func_77946_l();
            if (this.recipeInput.func_77960_j() == Short.MAX_VALUE) {
                i2.func_77964_b(Short.MAX_VALUE);
            }
            if (InventoryUtils.areItemStacksEqualForCrafting(i2, this.recipeInput, true, true, false)) {
                valid = true;
            }
        }
        if (!valid || this.instability > 0 && this.field_145850_b.field_73012_v.nextInt(500) <= this.instability) {
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
            if (valid) {
                return;
            }
        }
        if (!valid) {
            this.instability = 0;
            this.crafting = false;
            this.recipeEssentia = new AspectList();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:craftfail", 1.0f, 0.6f);
            this.func_70296_d();
            return;
        }
        if (this.recipeType == 1 && this.recipeXP > 0) {
            List targets = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(10.0, 10.0, 10.0));
            if (targets != null && targets.size() > 0) {
                for (EntityPlayer target : targets) {
                    if (target.field_71068_ca <= 0) continue;
                    target.func_82242_a(-1);
                    --this.recipeXP;
                    target.func_70097_a(DamageSource.field_76376_m, (float)this.field_145850_b.field_73012_v.nextInt(2));
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXInfusionSource(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, 0, 0, target.func_145782_y()), new NetworkRegistry.TargetPoint(this.func_145831_w().field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
                    this.field_145850_b.func_72956_a((Entity)target, "random.fizz", 1.0f, 2.0f + this.field_145850_b.field_73012_v.nextFloat() * 0.4f);
                    this.countDelay = 20;
                    return;
                }
                ingEss = this.recipeEssentia.getAspects();
                if (ingEss != null && ingEss.length > 0 && this.field_145850_b.field_73012_v.nextInt(3) == 0) {
                    Aspect as = ingEss[this.field_145850_b.field_73012_v.nextInt(ingEss.length)];
                    this.recipeEssentia.add(as, 1);
                    if (this.field_145850_b.field_73012_v.nextInt(50 - this.recipeInstability * 2) == 0) {
                        ++this.instability;
                    }
                    if (this.instability > 25) {
                        this.instability = 25;
                    }
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    this.func_70296_d();
                }
            }
            return;
        }
        if (this.recipeType == 1 && this.recipeXP == 0) {
            this.countDelay = 10;
        }
        if (this.recipeEssentia.visSize() > 0) {
            for (Aspect aspect : this.recipeEssentia.getAspects()) {
                if (this.recipeEssentia.getAmount(aspect) <= 0) continue;
                if (EssentiaHandler.drainEssentia(this, aspect, ForgeDirection.UNKNOWN, 12)) {
                    this.recipeEssentia.reduce(aspect, 1);
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
            }
            this.checkSurroundings = true;
            return;
        }
        if (this.recipeIngredients.size() > 0) {
            for (int a = 0; a < this.recipeIngredients.size(); ++a) {
                for (ChunkCoordinates cc : this.pedestals) {
                    te = this.field_145850_b.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
                    if (te == null || !(te instanceof TilePedestal) || ((TilePedestal)te).func_70301_a(0) == null || !InfusionRecipe.areItemStacksEqual(((TilePedestal)te).func_70301_a(0), this.recipeIngredients.get(a), true)) continue;
                    if (this.itemCount == 0) {
                        this.itemCount = 5;
                        PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXInfusionSource(this.field_145851_c, this.field_145848_d, this.field_145849_e, (byte)(this.field_145851_c - cc.field_71574_a), (byte)(this.field_145848_d - cc.field_71572_b), (byte)(this.field_145849_e - cc.field_71573_c), 0), new NetworkRegistry.TargetPoint(this.func_145831_w().field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
                    } else if (this.itemCount-- <= 1) {
                        ItemStack is = ((TilePedestal)te).func_70301_a(0).func_77973_b().getContainerItem(((TilePedestal)te).func_70301_a(0));
                        ((TilePedestal)te).func_70299_a(0, is == null ? null : is.func_77946_l());
                        this.recipeIngredients.remove(a);
                    }
                    return;
                }
                ingEss = this.recipeEssentia.getAspects();
                if (ingEss == null || ingEss.length <= 0 || this.field_145850_b.field_73012_v.nextInt(1 + a) != 0) continue;
                Aspect as = ingEss[this.field_145850_b.field_73012_v.nextInt(ingEss.length)];
                this.recipeEssentia.add(as, 1);
                if (this.field_145850_b.field_73012_v.nextInt(50 - this.recipeInstability * 2) == 0) {
                    ++this.instability;
                }
                if (this.instability > 25) {
                    this.instability = 25;
                }
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
            }
            return;
        }
        this.instability = 0;
        this.crafting = false;
        this.craftingFinish(this.recipeOutput, this.recipeOutputLabel);
        this.recipeOutput = null;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
    }

    private void inEvZap(boolean all) {
        List targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(10.0, 10.0, 10.0));
        if (targets != null && targets.size() > 0) {
            for (Entity target : targets) {
                PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockZap((float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f, (float)target.field_70165_t, (float)target.field_70163_u + target.field_70131_O / 2.0f, (float)target.field_70161_v), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
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
                Thaumcraft.addStickyWarpToPlayer(target, 1);
            } else {
                Thaumcraft.addWarpToPlayer(target, 1 + this.field_145850_b.field_73012_v.nextInt(5), true);
            }
        }
    }

    private void inEvEjectItem(int type) {
        for (int q = 0; q < 50 && this.pedestals.size() > 0; ++q) {
            ChunkCoordinates cc = this.pedestals.get(this.field_145850_b.field_73012_v.nextInt(this.pedestals.size()));
            TileEntity te = this.field_145850_b.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
            if (te == null || !(te instanceof TilePedestal) || ((TilePedestal)te).func_70301_a(0) == null) continue;
            if (type < 3 || type == 5) {
                InventoryUtils.dropItems(this.field_145850_b, cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
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
            PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockZap((float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f, (float)cc.field_71574_a + 0.5f, (float)cc.field_71572_b + 1.5f, (float)cc.field_71573_c + 0.5f), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
            return;
        }
    }

    public void craftingFinish(Object out, String label) {
        TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
        if (te != null && te instanceof TilePedestal) {
            EntityPlayer p;
            ItemStack temp;
            if (out instanceof ItemStack) {
                ((TilePedestal)te).setInventorySlotContentsFromInfusion(0, ((ItemStack)out).func_77946_l());
            } else if (out instanceof NBTBase) {
                temp = ((TilePedestal)te).func_70301_a(0);
                NBTBase tag = (NBTBase)out;
                temp.func_77983_a(label, tag);
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
                te.func_70296_d();
            } else if (out instanceof Enchantment) {
                temp = ((TilePedestal)te).func_70301_a(0);
                Map enchantments = EnchantmentHelper.func_82781_a((ItemStack)temp);
                enchantments.put(((Enchantment)out).field_77352_x, EnchantmentHelper.func_77506_a((int)((Enchantment)out).field_77352_x, (ItemStack)temp) + 1);
                EnchantmentHelper.func_82782_a((Map)enchantments, (ItemStack)temp);
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
                te.func_70296_d();
            }
            if (this.recipePlayer != null && (p = this.field_145850_b.func_72924_a(this.recipePlayer)) != null) {
                FMLCommonHandler.instance().firePlayerCraftingEvent(p, ((TilePedestal)te).func_70301_a(0), (IInventory)new InventoryFake(this.recipeIngredients));
            }
            this.recipeEssentia = new AspectList();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e, ConfigBlocks.blockStoneDevice, 12, 0);
        }
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

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        if (!world.field_72995_K && this.active && !this.crafting) {
            this.craftingStart(player);
            return 0;
        }
        if (!world.field_72995_K && !this.active && this.validLocation()) {
            this.active = true;
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
            return 0;
        }
        return -1;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        return wandstack;
    }

    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }

    private void doEffects() {
        if (this.crafting) {
            if (this.craftCount == 0) {
                this.field_145850_b.func_72980_b((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:infuserstart", 0.5f, 1.0f, false);
            } else if (this.craftCount % 65 == 0) {
                this.field_145850_b.func_72980_b((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:infuser", 0.5f, 1.0f, false);
            }
            ++this.craftCount;
            Thaumcraft.proxy.blockRunes(this.field_145850_b, this.field_145851_c, this.field_145848_d - 2, this.field_145849_e, 0.5f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f, 0.1f, 0.7f + this.field_145850_b.field_73012_v.nextFloat() * 0.3f, 25, -0.03f);
        } else if (this.craftCount > 0) {
            this.craftCount -= 2;
            if (this.craftCount < 0) {
                this.craftCount = 0;
            }
            if (this.craftCount > 50) {
                this.craftCount = 50;
            }
        }
        if (this.active && this.startUp != 1.0f) {
            if (this.startUp < 1.0f) {
                this.startUp += Math.max(this.startUp / 10.0f, 0.001f);
            }
            if ((double)this.startUp > 0.999) {
                this.startUp = 1.0f;
            }
        }
        if (!this.active && this.startUp > 0.0f) {
            if (this.startUp > 0.0f) {
                this.startUp -= this.startUp / 10.0f;
            }
            if ((double)this.startUp < 0.001) {
                this.startUp = 0.0f;
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
                            Thaumcraft.proxy.drawInfusionParticles3(this.field_145850_b, (float)fx.loc.field_71574_a + this.field_145850_b.field_73012_v.nextFloat(), (float)fx.loc.field_71572_b + this.field_145850_b.field_73012_v.nextFloat() + 1.0f, (float)fx.loc.field_71573_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145851_c, this.field_145848_d, this.field_145849_e);
                        } else {
                            int a;
                            Item bi = is.func_77973_b();
                            int md = is.func_77960_j();
                            if (is.func_94608_d() == 0 && bi instanceof ItemBlock) {
                                for (a = 0; a < Thaumcraft.proxy.particleCount(2); ++a) {
                                    Thaumcraft.proxy.drawInfusionParticles2(this.field_145850_b, (float)fx.loc.field_71574_a + this.field_145850_b.field_73012_v.nextFloat(), (float)fx.loc.field_71572_b + this.field_145850_b.field_73012_v.nextFloat() + 1.0f, (float)fx.loc.field_71573_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145851_c, this.field_145848_d, this.field_145849_e, Block.func_149634_a((Item)bi), md);
                                }
                            } else {
                                for (a = 0; a < Thaumcraft.proxy.particleCount(2); ++a) {
                                    Thaumcraft.proxy.drawInfusionParticles1(this.field_145850_b, (float)fx.loc.field_71574_a + 0.4f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f, (float)fx.loc.field_71572_b + 1.23f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f, (float)fx.loc.field_71573_c + 0.4f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f, this.field_145851_c, this.field_145848_d, this.field_145849_e, bi, md);
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
        if (this.crafting && this.instability > 0 && this.field_145850_b.field_73012_v.nextInt(200) <= this.instability) {
            Thaumcraft.proxy.nodeBolt(this.field_145850_b, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f, (float)this.field_145851_c + 0.5f + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 2.0f, (float)this.field_145848_d + 0.5f + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 2.0f, (float)this.field_145849_e + 0.5f + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 2.0f);
        }
    }

    @Override
    public AspectList getAspects() {
        return this.recipeEssentia;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public int addToContainer(Aspect tag, int amount) {
        return 0;
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
        return false;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return 0;
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
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

