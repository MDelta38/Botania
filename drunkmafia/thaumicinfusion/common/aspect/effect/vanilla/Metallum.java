/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryCraftResult
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.inventory.Slot
 *  net.minecraft.inventory.SlotCrafting
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.WorldCoordinates
 *  thaumcraft.common.Thaumcraft
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.common.world.data.BlockData;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import drunkmafia.thaumicinfusion.net.packet.server.EffectSyncPacketC;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.common.Thaumcraft;

@Effect(aspect="metallum")
public class Metallum
extends AspectEffect {
    public Metallum[][] grid;
    public boolean isMiddle;
    public Metallum middle;
    public ItemStack item;
    public ItemStack finalItem;

    @Override
    public void aspectInit(World world, WorldCoordinates pos) {
        super.aspectInit(world, pos);
        this.func_149695_a(world, pos.x, pos.y, pos.z, null);
    }

    @Override
    public int getCost() {
        return 4;
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
        if (world.field_72995_K || this.finalItem == null) {
            return;
        }
        Metallum[][] metallumArray = this.grid;
        int n = metallumArray.length;
        for (int i = 0; i < n; ++i) {
            Metallum[] row;
            for (Metallum metallum : row = metallumArray[i]) {
                metallum.item = null;
                ChannelHandler.instance().sendToAll(new EffectSyncPacketC(metallum, true));
            }
        }
        this.item = this.finalItem;
        ChannelHandler.instance().sendToAll(new EffectSyncPacketC(this, true));
    }

    @OverrideBlock
    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (this.middle == null || world.field_72995_K) {
            return true;
        }
        ItemStack equipped = player.func_71045_bC();
        if (this.item != null) {
            if (equipped != null && this.item.func_77973_b() == equipped.func_77973_b() && this.item.func_77960_j() == equipped.func_77960_j()) {
                if (this.item.field_77994_a < 64) {
                    this.item.field_77994_a += equipped.field_77994_a;
                    equipped.field_77994_a = this.item.field_77994_a > 64 ? this.item.field_77994_a - 64 : 0;
                }
                player.func_70062_b(0, equipped);
                if (this.middle != null) {
                    this.middle.itemUpdated(player);
                }
                player.field_71071_by.func_70296_d();
                ChannelHandler.instance().sendToAll(new EffectSyncPacketC(this, true));
                world.func_72908_a((double)x, (double)y, (double)z, "random.pop", 0.2f, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 1.5f);
                return true;
            }
            world.func_72838_d((Entity)new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, this.item));
            this.item = null;
            ChannelHandler.instance().sendToAll(new EffectSyncPacketC(this, true));
            if (this.middle != null) {
                this.middle.itemUpdated(player);
            }
            world.func_72908_a((double)x, (double)y, (double)z, "random.pop", 0.2f, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 1.5f);
            return true;
        }
        if (equipped != null) {
            this.item = equipped;
            player.func_70062_b(0, null);
            if (this.middle != null) {
                this.middle.itemUpdated(player);
            }
            player.field_71071_by.func_70296_d();
            ChannelHandler.instance().sendToAll(new EffectSyncPacketC(this, true));
            world.func_72908_a((double)x, (double)y, (double)z, "random.pop", 0.2f, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 1.6f);
            return true;
        }
        return false;
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149695_a(World world, int x, int y, int z, Block block) {
        if (world.field_72995_K) {
            return;
        }
        if (this.middle != null) {
            this.middle.checkGrid(world);
            return;
        }
        TIWorldData worldData = TIWorldData.getWorldData(world);
        for (int xPos = x - 2; xPos < x + 3; ++xPos) {
            for (int zPos = z - 2; zPos < z + 3; ++zPos) {
                BlockData checkBlock = worldData.getBlock(BlockData.class, new WorldCoordinates(xPos, y, zPos, world.field_73011_w.field_76574_g));
                if (checkBlock == null || !checkBlock.hasEffect(this.getClass())) continue;
                Metallum middle = null;
                Metallum[][] tempGrid = new Metallum[3][3];
                boolean fail = false;
                for (int gX = xPos; gX < xPos + 3; ++gX) {
                    for (int gZ = zPos; gZ < zPos + 3; ++gZ) {
                        checkBlock = worldData.getBlock(BlockData.class, new WorldCoordinates(gX, y, gZ, world.field_73011_w.field_76574_g));
                        if (checkBlock == null) {
                            fail = true;
                            break;
                        }
                        Metallum checkMetallum = (Metallum)checkBlock.getEffect(this.getClass());
                        if (checkMetallum == null) continue;
                        if (checkMetallum.grid != null) {
                            fail = true;
                            break;
                        }
                        tempGrid[gX - xPos][gZ - zPos] = checkMetallum;
                        if (gX - xPos != 1 || gZ - zPos != 1) continue;
                        middle = checkMetallum;
                    }
                    if (fail) break;
                }
                if (middle == null || fail) continue;
                super.setMiddle(tempGrid);
                return;
            }
        }
    }

    @Override
    public void renderEffect(EntityPlayer player, float partialTicks) {
        double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
        double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
        double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
        if (this.isMiddle) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(new ResourceLocation("thaumicinfusion", "/textures/grid.png"));
            Tessellator tessellator = Tessellator.field_78398_a;
            GL11.glPushMatrix();
            GL11.glTranslated((double)(-iPX + (double)this.getPos().x - 1.0), (double)(-iPY + 1.05 + (double)this.getPos().y), (double)(-iPZ + (double)this.getPos().z - 1.0));
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)3.0f, (float)3.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            tessellator.func_78382_b();
            tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
            tessellator.func_78374_a(0.0, 1.0, 0.0, 0.0, 1.0);
            tessellator.func_78374_a(1.0, 1.0, 0.0, 1.0, 1.0);
            tessellator.func_78374_a(1.0, 0.0, 0.0, 1.0, 0.0);
            tessellator.func_78374_a(0.0, 0.0, 0.0, 0.0, 0.0);
            tessellator.func_78381_a();
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
        if (this.item == null) {
            return;
        }
        float ticks = (float)Minecraft.func_71410_x().field_71451_h.field_70173_aa + partialTicks;
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-iPX + (double)this.getPos().x + 0.5), (double)(-iPY + (double)1.15f + (double)this.getPos().y), (double)(-iPZ + (double)this.getPos().z + 0.5));
        GL11.glRotatef((float)(ticks % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        if (this.item.func_77973_b() instanceof ItemBlock) {
            GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        } else {
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        }
        ItemStack is = this.item.func_77946_l();
        is.field_77994_a = 1;
        EntityItem entityitem = new EntityItem(player.field_70170_p, 0.0, 0.0, 0.0, is);
        entityitem.field_70290_d = 0.0f;
        RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
        if (!Minecraft.func_71375_t()) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
        }
        GL11.glPopMatrix();
        if (this.item != null) {
            Thaumcraft.instance.renderEventHandler.drawTextInAir((double)this.getPos().x, (double)(1.5f + (float)this.getPos().y), (double)this.getPos().z, partialTicks, this.item.field_77994_a + "");
        }
    }

    private void itemUpdated(EntityPlayer player) {
        MetallumContainer container = new MetallumContainer(player);
        if (this.grid == null) {
            return;
        }
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                container.craftMatrix.func_70299_a(x + y * 3, this.grid[x][y].item);
            }
        }
        this.finalItem = CraftingManager.func_77594_a().func_82787_a(container.craftMatrix, player.field_70170_p);
    }

    private void setMiddle(Metallum[][] grid) {
        this.isMiddle = true;
        this.grid = grid;
        Metallum[][] metallumArray = grid;
        int n = metallumArray.length;
        for (int i = 0; i < n; ++i) {
            Metallum[] x;
            for (Metallum z : x = metallumArray[i]) {
                z.middle = this;
            }
        }
        ChannelHandler.instance().sendToAll(new EffectSyncPacketC(this, true));
    }

    private void checkGrid(World world) {
        TIWorldData worldData = TIWorldData.getWorldData(world);
        Metallum[][] metallumArray = this.grid;
        int n = metallumArray.length;
        for (int i = 0; i < n; ++i) {
            Metallum[] x;
            for (Metallum z : x = metallumArray[i]) {
                BlockData blockData = worldData.getBlock(BlockData.class, z.pos);
                if (blockData != null && blockData.hasEffect(this.getClass())) continue;
                this.removeGrid();
                return;
            }
        }
    }

    private void removeGrid() {
        Metallum[][] metallumArray = this.grid;
        int n = metallumArray.length;
        for (int i = 0; i < n; ++i) {
            Metallum[] x;
            for (Metallum z : x = metallumArray[i]) {
                z.middle = null;
            }
        }
        this.isMiddle = false;
        this.grid = null;
        ChannelHandler.instance().sendToAll(new EffectSyncPacketC(this, true));
    }

    @Override
    public void writeNBT(NBTTagCompound tagCompound) {
        super.writeNBT(tagCompound);
        if (this.item != null) {
            NBTTagCompound stackTag = new NBTTagCompound();
            this.item.func_77955_b(stackTag);
            tagCompound.func_74782_a("stackNBT", (NBTBase)stackTag);
        }
        tagCompound.func_74757_a("isMiddle", this.isMiddle);
    }

    @Override
    public void readNBT(NBTTagCompound tagCompound) {
        super.readNBT(tagCompound);
        this.isMiddle = tagCompound.func_74767_n("isMiddle");
        this.item = tagCompound.func_74764_b("stackNBT") ? ItemStack.func_77949_a((NBTTagCompound)tagCompound.func_74775_l("stackNBT")) : null;
    }

    class MetallumContainer
    extends Container {
        public InventoryCrafting craftMatrix = new InventoryCrafting((Container)this, 3, 3);
        public IInventory craftResult = new InventoryCraftResult();

        public MetallumContainer(EntityPlayer player) {
            this.func_75146_a((Slot)new SlotCrafting(player, (IInventory)this.craftMatrix, this.craftResult, 0, 124, 35));
            for (int x = 0; x < 3; ++x) {
                for (int y = 0; y < 3; ++y) {
                    this.func_75146_a(new Slot((IInventory)this.craftMatrix, y + x * 3, 30 + y * 18, 17 + x * 18));
                }
            }
        }

        public boolean func_75145_c(EntityPlayer player) {
            return true;
        }
    }
}

