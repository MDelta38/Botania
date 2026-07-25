/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$InterfaceList
 *  cpw.mods.fml.common.Optional$Method
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  dan200.computercraft.api.lua.ILuaContext
 *  dan200.computercraft.api.lua.LuaException
 *  dan200.computercraft.api.peripheral.IComputerAccess
 *  dan200.computercraft.api.peripheral.IPeripheral
 *  li.cil.oc.api.machine.Arguments
 *  li.cil.oc.api.machine.Callback
 *  li.cil.oc.api.machine.Context
 *  li.cil.oc.api.network.SimpleComponent
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 *  thaumcraft.common.lib.FakeThaumcraftPlayer
 */
package thaumic.tinkerer.common.block.tile.tablet;

import appeng.api.movable.IMovableTile;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.eventhandler.Event;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import java.util.ArrayList;
import java.util.List;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import thaumcraft.common.lib.FakeThaumcraftPlayer;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockAnimationTablet;
import thaumic.tinkerer.common.block.tile.tablet.TabletFakePlayer;

@Optional.InterfaceList(value={@Optional.Interface(iface="li.cil.oc.api.network.SimpleComponent", modid="OpenComputers"), @Optional.Interface(iface="dan200.computercraft.api.peripheral.IPeripheral", modid="ComputerCraft")})
public class TileAnimationTablet
extends TileEntity
implements IInventory,
IMovableTile,
IPeripheral,
SimpleComponent {
    private static final String TAG_LEFT_CLICK = "leftClick";
    private static final String TAG_REDSTONE = "redstone";
    private static final String TAG_PROGRESS = "progress";
    private static final String TAG_MOD = "mod";
    private static final String TAG_OWNER = "owner";
    private static final int[][] LOC_INCREASES = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static final ForgeDirection[] SIDES = new ForgeDirection[]{ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.WEST, ForgeDirection.EAST};
    private static final int SWING_SPEED = 3;
    private static final int MAX_DEGREE = 45;
    public double ticksExisted = 0.0;
    public boolean leftClick = true;
    public boolean redstone = false;
    public int swingProgress = 0;
    List<Entity> detectedEntities = new ArrayList<Entity>();
    ItemStack[] inventorySlots = new ItemStack[1];
    FakeThaumcraftPlayer player;
    private int swingMod = 0;
    private boolean isBreaking = false;
    private int initialDamage = 0;
    private int curblockDamage = 0;
    private int durabilityRemainingOnBlock;

    public void func_145845_h() {
        boolean detect;
        this.player.func_70071_h_();
        this.player.field_71071_by.func_146027_a(null, -1);
        this.ticksExisted += 1.0;
        ItemStack stack = this.func_70301_a(0);
        if (stack != null) {
            if (this.swingProgress >= 45) {
                this.swingHit();
            }
            this.swingMod = this.swingProgress <= 0 ? 0 : (this.swingProgress >= 45 ? -3 : this.swingMod);
            this.swingProgress += this.swingMod;
            if (this.swingProgress < 0) {
                this.swingProgress = 0;
            }
        } else {
            this.swingMod = 0;
            this.swingProgress = 0;
            if (this.isBreaking) {
                this.stopBreaking();
            }
        }
        if (!(detect = this.detect())) {
            this.stopBreaking();
        }
        if (detect && this.isBreaking) {
            this.continueBreaking();
        }
        if ((!this.redstone || this.isBreaking) && detect && this.swingProgress == 0) {
            this.initiateSwing();
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ThaumicTinkerer.registry.getFirstBlockFromClass(BlockAnimationTablet.class), 0, 0);
        }
    }

    public void initiateSwing() {
        this.swingMod = 3;
        this.swingProgress = 1;
    }

    public void swingHit() {
        boolean done;
        ItemStack stack;
        block11: {
            Block block;
            Item item;
            ChunkCoordinates coords;
            block12: {
                block13: {
                    Entity entity;
                    coords = this.getTargetLoc();
                    stack = this.func_70301_a(0);
                    item = stack.func_77973_b();
                    block = this.field_145850_b.func_147439_a(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
                    this.player.func_70062_b(0, stack);
                    done = false;
                    if (!this.leftClick) break block12;
                    Entity entity2 = entity = this.detectedEntities.isEmpty() ? null : this.detectedEntities.get(this.field_145850_b.field_73012_v.nextInt(this.detectedEntities.size()));
                    if (entity == null) break block13;
                    this.player.func_110140_aT().func_111147_b(stack.func_111283_C());
                    this.player.func_71059_n(entity);
                    done = true;
                    break block11;
                }
                if (this.isBreaking || block == Blocks.field_150350_a || block.isAir((IBlockAccess)this.field_145850_b, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c) || !(block.func_149712_f(this.field_145850_b, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c) >= 0.0f)) break block11;
                this.isBreaking = true;
                this.startBreaking(block, this.field_145850_b.func_72805_g(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c));
                done = true;
                break block11;
            }
            int side = SIDES[(this.func_145832_p() & 7) - 2].getOpposite().ordinal();
            if (block == Blocks.field_150350_a || block.isAir((IBlockAccess)this.field_145850_b, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c)) {
                --coords.field_71572_b;
                side = ForgeDirection.UP.ordinal();
                block = this.field_145850_b.func_147439_a(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
            }
            try {
                ForgeEventFactory.onPlayerInteract((EntityPlayer)this.player, (PlayerInteractEvent.Action)PlayerInteractEvent.Action.RIGHT_CLICK_AIR, (int)coords.field_71574_a, (int)coords.field_71572_b, (int)coords.field_71573_c, (int)side, (World)this.field_145850_b);
                Entity entity = this.detectedEntities.isEmpty() ? null : this.detectedEntities.get(this.field_145850_b.field_73012_v.nextInt(this.detectedEntities.size()));
                boolean bl = done = entity != null && entity instanceof EntityLiving && (item.func_111207_a(stack, (EntityPlayer)this.player, (EntityLivingBase)entity) || !(entity instanceof EntityAnimal) || ((EntityAnimal)entity).func_70085_c((EntityPlayer)this.player));
                if (!done) {
                    item.onItemUseFirst(stack, (EntityPlayer)this.player, this.field_145850_b, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, side, 0.0f, 0.0f, 0.0f);
                }
                if (!done) {
                    boolean bl2 = done = block != null && block.func_149727_a(this.field_145850_b, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, (EntityPlayer)this.player, side, 0.0f, 0.0f, 0.0f);
                }
                if (!done) {
                    done = item.func_77648_a(stack, (EntityPlayer)this.player, this.field_145850_b, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, side, 0.0f, 0.0f, 0.0f);
                }
                if (done) break block11;
                item.func_77659_a(stack, this.field_145850_b, (EntityPlayer)this.player);
                done = true;
            }
            catch (Throwable e) {
                e.printStackTrace();
                List list = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 8), (double)(this.field_145848_d - 8), (double)(this.field_145849_e - 8), (double)(this.field_145851_c + 8), (double)(this.field_145848_d + 8), (double)(this.field_145849_e + 8)));
                for (Object player : list) {
                    ((EntityPlayer)player).func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Something went wrong with a Tool Dynamism Tablet! Check your FML log."));
                    ((EntityPlayer)player).func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.ITALIC + e.getMessage()));
                }
            }
        }
        if (done) {
            stack = this.player.func_71045_bC();
            if (stack == null || stack.field_77994_a <= 0) {
                this.func_70299_a(0, null);
            } else if (stack != this.func_70301_a(0)) {
                this.func_70299_a(0, stack);
            }
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        this.func_70296_d();
    }

    private void stopBreaking() {
        this.isBreaking = false;
        ChunkCoordinates coords = this.getTargetLoc();
        this.field_145850_b.func_147443_d(this.player.func_145782_y(), coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, -1);
    }

    private void startBreaking(Block block, int meta) {
        int side = SIDES[(this.func_145832_p() & 7) - 2].getOpposite().ordinal();
        ChunkCoordinates coords = this.getTargetLoc();
        PlayerInteractEvent event = ForgeEventFactory.onPlayerInteract((EntityPlayer)this.player, (PlayerInteractEvent.Action)PlayerInteractEvent.Action.LEFT_CLICK_BLOCK, (int)coords.field_71574_a, (int)coords.field_71572_b, (int)coords.field_71573_c, (int)side, (World)this.field_145850_b);
        if (event.isCanceled()) {
            this.stopBreaking();
            return;
        }
        this.initialDamage = this.curblockDamage;
        float var5 = 1.0f;
        if (block != null) {
            if (event.useBlock != Event.Result.DENY) {
                block.func_149699_a(this.field_145850_b, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, (EntityPlayer)this.player);
            }
            var5 = block.func_149737_a((EntityPlayer)this.player, this.field_145850_b, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
        }
        if (event.useItem == Event.Result.DENY) {
            this.stopBreaking();
            return;
        }
        if (var5 >= 1.0f) {
            this.tryHarvestBlock(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
            this.stopBreaking();
        } else {
            int var7 = (int)(var5 * 10.0f);
            this.field_145850_b.func_147443_d(this.player.func_145782_y(), coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, var7);
            this.durabilityRemainingOnBlock = var7;
        }
    }

    private void continueBreaking() {
        ++this.curblockDamage;
        ChunkCoordinates coords = this.getTargetLoc();
        int var1 = this.curblockDamage - this.initialDamage;
        Block block = this.field_145850_b.func_147439_a(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
        if (block == Blocks.field_150350_a) {
            this.stopBreaking();
        } else {
            float var4 = block.func_149737_a((EntityPlayer)this.player, this.field_145850_b, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c) * (float)var1;
            int var5 = (int)(var4 * 10.0f);
            if (var5 != this.durabilityRemainingOnBlock) {
                this.field_145850_b.func_147443_d(this.player.func_145782_y(), coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, var5);
                this.durabilityRemainingOnBlock = var5;
            }
            if (var4 >= 1.0f) {
                this.tryHarvestBlock(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
                this.stopBreaking();
            }
        }
    }

    public boolean tryHarvestBlock(int par1, int par2, int par3) {
        boolean var6;
        ItemStack stack = this.func_70301_a(0);
        if (stack != null && stack.func_77973_b().onBlockStartBreak(stack, par1, par2, par3, (EntityPlayer)this.player)) {
            return false;
        }
        Block block = this.field_145850_b.func_147439_a(par1, par2, par3);
        int var5 = this.field_145850_b.func_72805_g(par1, par2, par3);
        boolean var8 = false;
        if (block != null) {
            var8 = block.canHarvestBlock((EntityPlayer)this.player, var5);
        }
        this.field_145850_b.field_72996_f.size();
        if (stack != null) {
            stack.func_77973_b().func_150894_a(stack, this.field_145850_b, block, par1, par2, par3, (EntityLivingBase)this.player);
        }
        if ((var6 = this.removeBlock(par1, par2, par3)) && var8) {
            block.func_149636_a(this.field_145850_b, (EntityPlayer)this.player, par1, par2, par3, var5);
        }
        return var6;
    }

    private boolean removeBlock(int par1, int par2, int par3) {
        boolean var6;
        Block var4 = this.field_145850_b.func_147439_a(par1, par2, par3);
        int var5 = this.field_145850_b.func_72805_g(par1, par2, par3);
        if (var4 != null) {
            var4.func_149681_a(this.field_145850_b, par1, par2, par3, var5, (EntityPlayer)this.player);
        }
        boolean bl = var6 = var4 != null && var4.removedByPlayer(this.field_145850_b, (EntityPlayer)this.player, par1, par2, par3);
        if (var4 != null && var6) {
            var4.func_149664_b(this.field_145850_b, par1, par2, par3, var5);
        }
        return var6;
    }

    public boolean detect() {
        ChunkCoordinates coords = this.getTargetLoc();
        this.findEntities(coords);
        return !this.field_145850_b.func_147437_c(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c) || !this.detectedEntities.isEmpty();
    }

    public void findEntities(ChunkCoordinates coords) {
        AxisAlignedBB boundingBox = AxisAlignedBB.func_72330_a((double)coords.field_71574_a, (double)coords.field_71572_b, (double)coords.field_71573_c, (double)(coords.field_71574_a + 1), (double)(coords.field_71572_b + 1), (double)(coords.field_71573_c + 1));
        this.detectedEntities = this.field_145850_b.func_72872_a(Entity.class, boundingBox);
    }

    public void func_145829_t() {
        super.func_145829_t();
        this.player = new TabletFakePlayer(this);
    }

    public ChunkCoordinates getTargetLoc() {
        ChunkCoordinates coords = new ChunkCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        int meta = this.func_145832_p();
        if (meta == 0) {
            ThaumicTinkerer.log.error("Metadata of a Tool Dynamism tablet is in an invalid state. This is a critical error.");
            return coords;
        }
        int[] increase = LOC_INCREASES[(meta & 7) - 2];
        coords.field_71574_a += increase[0];
        coords.field_71573_c += increase[1];
        return coords;
    }

    public boolean getIsBreaking() {
        return this.isBreaking;
    }

    public boolean func_145842_c(int par1, int par2) {
        if (par1 == 0) {
            this.initiateSwing();
            return true;
        }
        return this.field_145846_f;
    }

    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        this.swingProgress = par1NBTTagCompound.func_74762_e(TAG_PROGRESS);
        this.readCustomNBT(par1NBTTagCompound);
    }

    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a(TAG_PROGRESS, this.swingProgress);
        par1NBTTagCompound.func_74768_a(TAG_MOD, this.swingMod);
        this.writeCustomNBT(par1NBTTagCompound);
    }

    public void readCustomNBT(NBTTagCompound par1NBTTagCompound) {
        this.leftClick = par1NBTTagCompound.func_74767_n(TAG_LEFT_CLICK);
        this.redstone = par1NBTTagCompound.func_74767_n(TAG_REDSTONE);
        NBTTagList var2 = par1NBTTagCompound.func_150295_c("Items", 10);
        this.inventorySlots = new ItemStack[this.func_70302_i_()];
        for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            NBTTagCompound var4 = var2.func_150305_b(var3);
            byte var5 = var4.func_74771_c("Slot");
            if (var5 < 0 || var5 >= this.inventorySlots.length) continue;
            this.inventorySlots[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
        }
    }

    public void writeCustomNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.func_74757_a(TAG_LEFT_CLICK, this.leftClick);
        par1NBTTagCompound.func_74757_a(TAG_REDSTONE, this.redstone);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.inventorySlots.length; ++var3) {
            if (this.inventorySlots[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a("Slot", (byte)var3);
            this.inventorySlots[var3].func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        par1NBTTagCompound.func_74782_a("Items", (NBTBase)var2);
    }

    public int func_70302_i_() {
        return this.inventorySlots.length;
    }

    public ItemStack func_70301_a(int i) {
        return this.inventorySlots[i];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.inventorySlots[par1] != null) {
            if (this.inventorySlots[par1].field_77994_a <= par2) {
                ItemStack stackAt = this.inventorySlots[par1];
                this.inventorySlots[par1] = null;
                if (!this.field_145850_b.field_72995_K) {
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                }
                return stackAt;
            }
            ItemStack stackAt = this.inventorySlots[par1].func_77979_a(par2);
            if (this.inventorySlots[par1].field_77994_a == 0) {
                this.inventorySlots[par1] = null;
            }
            if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            return stackAt;
        }
        return null;
    }

    public ItemStack func_70304_b(int i) {
        return this.func_70301_a(i);
    }

    public void func_70299_a(int i, ItemStack itemstack) {
        this.inventorySlots[i] = itemstack;
        if (!this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public String func_145825_b() {
        return "animationTablet";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && entityplayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public S35PacketUpdateTileEntity getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeCustomNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, nbttagcompound);
    }

    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(manager, packet);
        this.readCustomNBT(packet.func_148857_g());
    }

    public String getType() {
        return "tt_animationTablet";
    }

    public String[] getMethodNames() {
        return new String[]{"getRedstone", "setRedstone", "getLeftClick", "setLeftClick", "getRotation", "setRotation", "hasItem", "trigger"};
    }

    @Optional.Method(modid="ComputerCraft")
    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException {
        switch (method) {
            case 0: {
                return new Object[]{this.redstone};
            }
            case 1: {
                return this.setRedstoneImplementation((Boolean)arguments[0]);
            }
            case 2: {
                return new Object[]{this.leftClick};
            }
            case 3: {
                return this.setLeftClickImplementation((Boolean)arguments[0]);
            }
            case 4: {
                return new Object[]{this.func_145832_p() - 2};
            }
            case 5: {
                return this.setRotationImplementation((Double)arguments[0]);
            }
            case 6: {
                return new Object[]{this.func_70301_a(0) != null};
            }
            case 7: {
                return this.triggerImplementation();
            }
        }
        return null;
    }

    private Object[] triggerImplementation() {
        if (this.swingProgress != 0) {
            return new Object[]{false};
        }
        this.findEntities(this.getTargetLoc());
        this.initiateSwing();
        this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ThaumicTinkerer.registry.getFirstBlockFromClass(BlockAnimationTablet.class), 0, 0);
        return new Object[]{true};
    }

    @Optional.Method(modid="ComputerCraft")
    private Object[] setRotationImplementation(Double argument) throws LuaException {
        int rotation = (int)argument.doubleValue();
        if (rotation > 3) {
            throw new LuaException("Invalid value: " + rotation + ".");
        }
        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, rotation + 2, 3);
        return null;
    }

    private Object[] setLeftClickImplementation(Boolean argument) {
        this.leftClick = argument;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return null;
    }

    private Object[] setRedstoneImplementation(Boolean argument) {
        this.redstone = argument;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return null;
    }

    @Optional.Method(modid="ComputerCraft")
    public void attach(IComputerAccess computer) {
    }

    @Optional.Method(modid="ComputerCraft")
    public void detach(IComputerAccess computer) {
    }

    @Optional.Method(modid="ComputerCraft")
    public boolean equals(IPeripheral other) {
        return this.equals((Object)other);
    }

    @Override
    public boolean prepareToMove() {
        this.stopBreaking();
        return true;
    }

    @Override
    public void doneMoving() {
    }

    public String getComponentName() {
        return this.getType();
    }

    @Callback(doc="function():boolean -- Returns Whether tablet is redstone activated")
    @Optional.Method(modid="OpenComputers")
    public Object[] getRedstone(Context context, Arguments args) throws Exception {
        return new Object[]{this.redstone};
    }

    @Callback(doc="function(boolean):Nil -- Sets Whether tablet is redstone activated")
    @Optional.Method(modid="OpenComputers")
    public Object[] setRedstone(Context context, Arguments args) throws Exception {
        this.setRedstoneImplementation(args.checkBoolean(0));
        return new Object[]{this.redstone};
    }

    @Callback(doc="function():boolean -- Returns Whether tablet Left clicks")
    @Optional.Method(modid="OpenComputers")
    public Object[] getLeftClick(Context context, Arguments args) throws Exception {
        return new Object[]{this.leftClick};
    }

    @Callback(doc="function(boolean):Nil -- Sets Whether tablet Left Clicks")
    @Optional.Method(modid="OpenComputers")
    public Object[] setLeftClick(Context context, Arguments args) throws Exception {
        this.setLeftClickImplementation(args.checkBoolean(0));
        return new Object[]{this.leftClick};
    }

    @Callback(doc="function():number -- Returns tablet Rotation")
    @Optional.Method(modid="OpenComputers")
    public Object[] getRotation(Context context, Arguments args) throws Exception {
        return new Object[]{this.func_145832_p() - 2};
    }

    @Callback(doc="function(number):Nil -- Sets tablet rotation")
    @Optional.Method(modid="OpenComputers")
    public Object[] setRotation(Context context, Arguments args) throws Exception {
        this.setRotationImplementation(Double.valueOf(args.checkInteger(0)));
        return new Object[]{this.func_145832_p() - 2};
    }

    @Callback(doc="function():boolean -- Returns wether tablet has an item or not")
    @Optional.Method(modid="OpenComputers")
    public Object[] hasItem(Context context, Arguments args) throws Exception {
        return new Object[]{this.func_70301_a(0) != null};
    }

    @Callback(doc="function():Nil -- Triggers tablets swing")
    @Optional.Method(modid="OpenComputers")
    public Object[] trigger(Context context, Arguments args) throws Exception {
        return this.triggerImplementation();
    }
}

