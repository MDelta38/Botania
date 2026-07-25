/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.lib.raytracer.IndexedCuboid6
 *  codechicken.lib.vec.Cuboid6
 *  codechicken.lib.vec.Vector3
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.ThaumcraftApiHelper
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.IEssentiaTransport
 *  thaumcraft.client.renderers.models.ModelTubeValve
 *  thaumcraft.common.blocks.BlockTube
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.items.relics.ItemResonator
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.tiles.TileTubeValve
 */
package witchinggadgets.common.blocks.tiles;

import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.client.renderers.models.ModelTubeValve;
import thaumcraft.common.blocks.BlockTube;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.relics.ItemResonator;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileTubeValve;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.MultipartEssentiaTube;

public class MultipartEssentiaTube_Valve
extends MultipartEssentiaTube {
    public boolean allowFlow = true;
    boolean wasPoweredLastTick = false;
    public float rotation = 0.0f;
    static ModelTubeValve valveModel = new ModelTubeValve();

    public MultipartEssentiaTube_Valve(int meta) {
        super(meta);
    }

    @Override
    public void invalidateConvertedTile() {
        super.invalidateConvertedTile();
        TileEntity te = this.world().func_147438_o(this.x(), this.y(), this.z());
        if (te instanceof TileTubeValve) {
            this.allowFlow = ((TileTubeValve)te).allowFlow;
            this.rotation = ((TileTubeValve)te).rotation;
        }
    }

    @Override
    public void update() {
        super.update();
        if (!this.world().field_72995_K && this.count % 5 == 0) {
            boolean gettingPower = this.world().func_72864_z(this.x(), this.y(), this.z());
            if (this.wasPoweredLastTick && !gettingPower && !this.allowFlow) {
                this.allowFlow = true;
                this.world().func_72908_a((double)this.x() + 0.5, (double)this.y() + 0.5, (double)this.z() + 0.5, "thaumcraft:squeek", 0.7f, 0.9f + this.world().field_73012_v.nextFloat() * 0.2f);
                this.world().func_147471_g(this.x(), this.y(), this.z());
                this.markDirty();
                this.sendDescUpdate();
            }
            if (!this.wasPoweredLastTick && gettingPower && this.allowFlow) {
                this.allowFlow = false;
                this.world().func_72908_a((double)this.x() + 0.5, (double)this.y() + 0.5, (double)this.z() + 0.5, "thaumcraft:squeek", 0.7f, 0.9f + this.world().field_73012_v.nextFloat() * 0.2f);
                this.world().func_147471_g(this.x(), this.y(), this.z());
                this.markDirty();
                this.sendDescUpdate();
            }
            this.wasPoweredLastTick = gettingPower;
        }
        if (this.world().field_72995_K) {
            if (!this.allowFlow && this.rotation < 360.0f) {
                this.rotation += 20.0f;
            } else if (this.allowFlow && this.rotation > 0.0f) {
                this.rotation -= 20.0f;
            }
        }
    }

    @Override
    public boolean renderStatic(Vector3 pos, int pass) {
        super.renderStatic(pos, pass);
        ClientUtilities.addBoxToBlockrender(0.375, 0.375, 0.375, 0.625, 0.625, 0.625, ((BlockTube)ConfigBlocks.blockTube).icon[1], this.x(), this.y(), this.z());
        return true;
    }

    @Override
    public void renderDynamic(Vector3 pos, float partialTickTime, int pass) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)(pos.x + 0.5), (double)(pos.y + 0.5), (double)(pos.z + 0.5));
        if (this.facing.offsetY == 0) {
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else {
            GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)90.0f, (float)this.facing.offsetY, (float)0.0f, (float)0.0f);
        }
        GL11.glRotatef((float)90.0f, (float)this.facing.offsetX, (float)this.facing.offsetY, (float)this.facing.offsetZ);
        GL11.glRotated((double)((double)(-this.rotation) * 1.5), (double)0.0, (double)1.0, (double)0.0);
        GL11.glTranslated((double)0.0, (double)(-(this.rotation / 360.0f) * 0.12f), (double)0.0);
        ClientUtilities.bindTexture("thaumcraft:textures/models/valve.png");
        valveModel.render();
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.25f, (float)-0.25f, (float)-0.25f);
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        Tessellator tessellator = Tessellator.field_78398_a;
        IIcon icon = ((BlockTube)ConfigBlocks.blockTube).iconValve;
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94209_e();
        float f4 = icon.func_94210_h();
        ClientUtilities.bindTexture("textures/atlas/blocks.png");
        ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f1, (float)f2, (float)f3, (float)f4, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.1f);
        GL11.glPopMatrix();
    }

    @Override
    public Cuboid6 getBounds() {
        float minx = 0.375f;
        float maxx = 0.625f;
        float miny = 0.375f;
        float maxy = 0.625f;
        float minz = 0.375f;
        float maxz = 0.625f;
        block8: for (ForgeDirection fd : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity te;
            if (fd == this.facing || (te = ThaumcraftApiHelper.getConnectableTile((World)this.world(), (int)this.x(), (int)this.y(), (int)this.z(), (ForgeDirection)fd)) == null) continue;
            switch (fd) {
                case DOWN: {
                    miny = 0.0f;
                    continue block8;
                }
                case UP: {
                    maxy = 1.0f;
                    continue block8;
                }
                case NORTH: {
                    minz = 0.0f;
                    continue block8;
                }
                case SOUTH: {
                    maxz = 1.0f;
                    continue block8;
                }
                case WEST: {
                    minx = 0.0f;
                    continue block8;
                }
                case EAST: {
                    maxx = 1.0f;
                }
            }
        }
        return new Cuboid6((double)minx, (double)miny, (double)minz, (double)maxx, (double)maxy, (double)maxz);
    }

    @Override
    public Iterable<IndexedCuboid6> getSubParts() {
        ArrayList<IndexedCuboid6> t = new ArrayList<IndexedCuboid6>();
        if (this.world().field_72995_K && (Minecraft.func_71410_x().field_71439_g.func_71045_bC() == null || !(Minecraft.func_71410_x().field_71439_g.func_71045_bC().func_77973_b() instanceof ItemWandCasting) && !(Minecraft.func_71410_x().field_71439_g.func_71045_bC().func_77973_b() instanceof ItemResonator))) {
            t.add(new IndexedCuboid6(null, this.getBounds()));
            t.add(new IndexedCuboid6(null, new Cuboid6(0.375 + (double)this.facing.offsetX * 0.375, 0.375 + (double)this.facing.offsetY * 0.375, 0.375 + (double)this.facing.offsetZ * 0.375, 0.625 + (double)this.facing.offsetX * 0.375, 0.625 + (double)this.facing.offsetY * 0.375, 0.625 + (double)this.facing.offsetZ * 0.375)));
        } else {
            for (int i = 0; i < 6; ++i) {
                ForgeDirection fd = ForgeDirection.getOrientation((int)i);
                if (!(this.world().func_147438_o(this.x() + fd.offsetX, this.y() + fd.offsetY, this.z() + fd.offsetZ) instanceof IEssentiaTransport)) continue;
                t.add(this.getConnectionPipe(ForgeDirection.getOrientation((int)i)));
            }
            t.add(new IndexedCuboid6(null, new Cuboid6(0.375, 0.375, 0.375, 0.625, 0.625, 0.625)));
        }
        return t;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face != this.facing && super.isConnectable(face);
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
        if (this.allowFlow) {
            super.setSuction(aspect, amount);
        }
    }

    @Override
    public void save(NBTTagCompound tag) {
        super.save(tag);
        tag.func_74757_a("allowFlow", this.allowFlow);
    }

    @Override
    public void load(NBTTagCompound tag) {
        super.load(tag);
        this.allowFlow = tag.func_74767_n("allowFlow");
    }

    @Override
    public boolean activate(EntityPlayer player, MovingObjectPosition hit, ItemStack stack) {
        boolean bl = this.allowFlow = !this.allowFlow;
        if (!this.world().field_72995_K) {
            this.world().func_72908_a((double)this.x() + 0.5, (double)this.y() + 0.5, (double)this.z() + 0.5, "thaumcraft:squeek", 0.7f, 0.9f + this.world().field_73012_v.nextFloat() * 0.2f);
            this.sendDescUpdate();
        }
        return true;
    }

    @Override
    public String getType() {
        return "witchingGadgets:essentia_tube_valve";
    }
}

