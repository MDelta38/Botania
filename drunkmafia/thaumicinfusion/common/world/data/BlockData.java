/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.Facing
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.ItemApi
 *  thaumcraft.api.WorldCoordinates
 *  thaumcraft.api.aspects.Aspect
 */
package drunkmafia.thaumicinfusion.common.world.data;

import drunkmafia.thaumicinfusion.client.event.ClientEventContainer;
import drunkmafia.thaumicinfusion.common.ThaumicInfusion;
import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.aspect.AspectHandler;
import drunkmafia.thaumicinfusion.common.item.ItemFocusInfusing;
import drunkmafia.thaumicinfusion.common.util.IBlockHook;
import drunkmafia.thaumicinfusion.common.util.IClientTickable;
import drunkmafia.thaumicinfusion.common.util.RGB;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import drunkmafia.thaumicinfusion.common.world.SavableHelper;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.common.world.data.BlockSavable;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import drunkmafia.thaumicinfusion.net.packet.server.BlockSyncPacketC;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ItemApi;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;

public class BlockData
extends BlockSavable
implements IBlockHook {
    private static final int[] connectedTextureRefByID = new int[]{0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 16, 16, 20, 20, 16, 16, 28, 28, 21, 21, 46, 42, 21, 21, 43, 38, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 16, 16, 20, 20, 16, 16, 28, 28, 25, 25, 45, 37, 25, 25, 40, 32, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 7, 7, 24, 24, 7, 7, 10, 10, 29, 29, 44, 41, 29, 29, 39, 33, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 7, 7, 24, 24, 7, 7, 10, 10, 8, 8, 36, 35, 8, 8, 34, 11};
    private static final HashMap<WorldCoordinates, IIcon> iconCache = new HashMap();
    private static IIcon[] wardedGlassIcon;
    public World world;
    public int ticksExisted;
    private int[] methods = new int[0];
    private Map<Integer, OverrideBlock> methodsOverrides = new HashMap<Integer, OverrideBlock>();
    private Map<Integer, Integer> methodsToBlock = new HashMap<Integer, Integer>();
    private ArrayList<AspectEffect> dataEffects = new ArrayList();

    public BlockData() {
    }

    public BlockData(WorldCoordinates coords, Class[] list) {
        super(coords);
        for (AspectEffect effect : this.classesToEffects(list)) {
            if (effect == null) continue;
            effect.data = this;
            this.dataEffects.add(effect);
        }
    }

    private static int[] toPrimitive(Integer[] IntegerArray) {
        int[] result = new int[IntegerArray.length];
        for (int i = 0; i < IntegerArray.length; ++i) {
            result[i] = IntegerArray[i];
        }
        return result;
    }

    @Override
    public void dataLoad(World world) {
        super.dataLoad(world);
        if (world == null) {
            return;
        }
        this.world = world;
        this.methodsOverrides = new HashMap<Integer, OverrideBlock>();
        this.methodsToBlock = new HashMap<Integer, Integer>();
        for (int a = 0; a < this.dataEffects.size(); ++a) {
            AspectEffect effect = this.dataEffects.get(a);
            if (effect == null) {
                ThaumicInfusion.getLogger().error("NULL EFFECT! An effect has been removed or failed to load, the data at: " + this.getCoords() + " has been removed!");
                TIWorldData.getWorldData(world).removeData(BlockData.class, this.getCoords(), true);
                return;
            }
            effect.aspectInit(world, this.getCoords());
            effect.data = this;
            List<AspectEffect.MethodInfo> effectMethods = AspectEffect.getMethods(effect.getClass());
            for (AspectEffect.MethodInfo method : effectMethods) {
                this.methodsOverrides.put(method.methodID, method.override);
                this.methodsToBlock.put(method.methodID, this.dataEffects.indexOf(effect));
            }
        }
        this.methods = BlockData.toPrimitive(this.methodsToBlock.keySet().toArray(new Integer[this.methodsToBlock.keySet().size()]));
    }

    public void renderData(EntityPlayer player, float partialTicks) {
        for (AspectEffect effect : this.getEffects()) {
            effect.renderEffect(player, partialTicks);
        }
        if (player.func_71045_bC() != null && player.func_71045_bC().func_77973_b().getClass().isAssignableFrom(ItemApi.getItem((String)"itemWandCasting", (int)0).func_77973_b().getClass()) && ClientEventContainer.getFocus(player.func_71045_bC()) != null && ClientEventContainer.getFocus(player.func_71045_bC()) instanceof ItemFocusInfusing) {
            int x = this.coordinates.x;
            int y = this.coordinates.y;
            int z = this.coordinates.z;
            TIWorldData worldData = TIWorldData.getWorldData(player.func_130014_f_());
            double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
            double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
            double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
            for (AspectEffect effect : this.getEffects()) {
                if (!(effect instanceof IClientTickable)) continue;
                ((IClientTickable)((Object)effect)).clientTick(this.world, (int)(-iPX) + x, (int)(-iPY) + y, (int)(-iPZ) + z, partialTicks);
            }
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)1);
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glTranslated((double)(-iPX + (double)x + 0.5), (double)(-iPY + (double)y), (double)(-iPZ + (double)z + 0.5));
            RenderBlocks renderBlocks = new RenderBlocks();
            GL11.glDisable((int)2896);
            Tessellator t = Tessellator.field_78398_a;
            renderBlocks.func_147782_a((double)-0.001f, (double)-0.001f, (double)-0.001f, (double)1.001f, (double)1.001f, (double)1.001f);
            Aspect[] aspects = this.getAspects();
            if (aspects == null || aspects.length == 0) {
                return;
            }
            new RGB(aspects[0].getColor()).glColor3f();
            t.func_78382_b();
            t.func_78380_c(200);
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
            GL11.glTexEnvi((int)8960, (int)8704, (int)260);
            Block blockJar = Block.func_149634_a((Item)ItemApi.getBlock((String)"blockJar", (int)0).func_77973_b());
            if (!this.isConnectedBlock(worldData, x - Facing.field_71586_b[1], y - Facing.field_71587_c[1], z - Facing.field_71585_d[1])) {
                renderBlocks.func_147768_a(blockJar, -0.5001, 0.0, -0.5001, this.getIconOnSide(worldData, x, y, z, 0, player.field_70173_aa));
            }
            if (!this.isConnectedBlock(worldData, x - Facing.field_71586_b[0], y - Facing.field_71587_c[0], z - Facing.field_71585_d[0])) {
                renderBlocks.func_147806_b(blockJar, -0.5001, 0.0, -0.5001, this.getIconOnSide(worldData, x, y, z, 1, player.field_70173_aa));
            }
            if (!this.isConnectedBlock(worldData, x - Facing.field_71586_b[3], y - Facing.field_71587_c[3], z - Facing.field_71585_d[3])) {
                renderBlocks.func_147761_c(blockJar, -0.5001, 0.0, -0.5001, this.getIconOnSide(worldData, x, y, z, 2, player.field_70173_aa));
            }
            if (!this.isConnectedBlock(worldData, x - Facing.field_71586_b[2], y - Facing.field_71587_c[2], z - Facing.field_71585_d[2])) {
                renderBlocks.func_147734_d(blockJar, -0.5001, 0.0, -0.5001, this.getIconOnSide(worldData, x, y, z, 3, player.field_70173_aa));
            }
            if (!this.isConnectedBlock(worldData, x - Facing.field_71586_b[5], y - Facing.field_71587_c[5], z - Facing.field_71585_d[5])) {
                renderBlocks.func_147798_e(blockJar, -0.5001, 0.0, -0.5001, this.getIconOnSide(worldData, x, y, z, 4, player.field_70173_aa));
            }
            if (!this.isConnectedBlock(worldData, x - Facing.field_71586_b[4], y - Facing.field_71587_c[4], z - Facing.field_71585_d[4])) {
                renderBlocks.func_147764_f(blockJar, -0.5001, 0.0, -0.5001, this.getIconOnSide(worldData, x, y, z, 5, player.field_70173_aa));
            }
            t.func_78381_a();
            GL11.glTexEnvi((int)8960, (int)8704, (int)8448);
            GL11.glEnable((int)2896);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glDisable((int)3042);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
    }

    private boolean isConnectedBlock(TIWorldData world, int x, int y, int z) {
        BlockData data = world.getBlock(BlockData.class, new WorldCoordinates(x, y, z, 0));
        if (data == null) {
            return false;
        }
        int same = 0;
        block0: for (Aspect aspect : data.getAspects()) {
            for (Aspect aspect2 : this.getAspects()) {
                if (aspect != aspect2) continue;
                ++same;
                continue block0;
            }
        }
        return same == data.getAspects().length;
    }

    private IIcon getIconOnSide(TIWorldData world, int x, int y, int z, int side, int ticks) {
        WorldCoordinates wc = new WorldCoordinates(x, y, z, side);
        IIcon out = iconCache.get(wc);
        if ((ticks + side) % 10 == 0 || out == null) {
            boolean[] bitMatrix = new boolean[8];
            if (side == 0 || side == 1) {
                bitMatrix[0] = this.isConnectedBlock(world, x - 1, y, z - 1);
                bitMatrix[1] = this.isConnectedBlock(world, x, y, z - 1);
                bitMatrix[2] = this.isConnectedBlock(world, x + 1, y, z - 1);
                bitMatrix[3] = this.isConnectedBlock(world, x - 1, y, z);
                bitMatrix[4] = this.isConnectedBlock(world, x + 1, y, z);
                bitMatrix[5] = this.isConnectedBlock(world, x - 1, y, z + 1);
                bitMatrix[6] = this.isConnectedBlock(world, x, y, z + 1);
                bitMatrix[7] = this.isConnectedBlock(world, x + 1, y, z + 1);
            }
            if (side == 2 || side == 3) {
                bitMatrix[0] = this.isConnectedBlock(world, x + (side == 2 ? 1 : -1), y + 1, z);
                bitMatrix[1] = this.isConnectedBlock(world, x, y + 1, z);
                bitMatrix[2] = this.isConnectedBlock(world, x + (side == 3 ? 1 : -1), y + 1, z);
                bitMatrix[3] = this.isConnectedBlock(world, x + (side == 2 ? 1 : -1), y, z);
                bitMatrix[4] = this.isConnectedBlock(world, x + (side == 3 ? 1 : -1), y, z);
                bitMatrix[5] = this.isConnectedBlock(world, x + (side == 2 ? 1 : -1), y - 1, z);
                bitMatrix[6] = this.isConnectedBlock(world, x, y - 1, z);
                bitMatrix[7] = this.isConnectedBlock(world, x + (side == 3 ? 1 : -1), y - 1, z);
            }
            if (side == 4 || side == 5) {
                bitMatrix[0] = this.isConnectedBlock(world, x, y + 1, z + (side == 5 ? 1 : -1));
                bitMatrix[1] = this.isConnectedBlock(world, x, y + 1, z);
                bitMatrix[2] = this.isConnectedBlock(world, x, y + 1, z + (side == 4 ? 1 : -1));
                bitMatrix[3] = this.isConnectedBlock(world, x, y, z + (side == 5 ? 1 : -1));
                bitMatrix[4] = this.isConnectedBlock(world, x, y, z + (side == 4 ? 1 : -1));
                bitMatrix[5] = this.isConnectedBlock(world, x, y - 1, z + (side == 5 ? 1 : -1));
                bitMatrix[6] = this.isConnectedBlock(world, x, y - 1, z);
                bitMatrix[7] = this.isConnectedBlock(world, x, y - 1, z + (side == 4 ? 1 : -1));
            }
            int idBuilder = 0;
            for (int i = 0; i <= 7; ++i) {
                idBuilder += bitMatrix[i] ? (i == 0 ? 1 : (i == 1 ? 2 : (i == 2 ? 4 : (i == 3 ? 8 : (i == 4 ? 16 : (i == 5 ? 32 : (i == 6 ? 64 : 128))))))) : 0;
            }
            if (wardedGlassIcon == null) {
                try {
                    wardedGlassIcon = (IIcon[])Class.forName("thaumcraft.common.blocks.BlockCosmeticOpaque").getDeclaredField("wardedGlassIcon").get(null);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            out = idBuilder <= 255 && idBuilder >= 0 ? wardedGlassIcon[connectedTextureRefByID[idBuilder]] : wardedGlassIcon[0];
            iconCache.put(wc, out);
        }
        return out;
    }

    @Override
    public void setCoords(WorldCoordinates newPos) {
        super.setCoords(newPos);
        for (AspectEffect effect : this.dataEffects) {
            effect.setCoords(newPos);
        }
    }

    public <T extends AspectEffect> T getEffect(Class<T> effect) {
        for (AspectEffect obj : this.dataEffects) {
            if (obj.getClass() != effect) continue;
            return (T)((AspectEffect)effect.cast(obj));
        }
        return null;
    }

    public void removeEffect(Class<? extends AspectEffect> effect) {
        for (AspectEffect aspectEffect : this.dataEffects) {
            if (aspectEffect.getClass() != effect) continue;
            for (AspectEffect.MethodInfo method : AspectEffect.getMethods(aspectEffect.getClass())) {
                this.methodsToBlock.remove(method.methodID);
                this.methodsOverrides.remove(method.methodID);
            }
            this.dataEffects.remove(aspectEffect);
            if (!this.world.field_72995_K) {
                ChannelHandler.instance().sendToDimension(new BlockSyncPacketC(this), this.world.field_73011_w.field_76574_g);
            }
            return;
        }
    }

    public void addEffect(Class<? extends AspectEffect>[] classes) {
        for (AspectEffect effect : this.classesToEffects(classes)) {
            if (effect == null) continue;
            effect.data = this;
            this.dataEffects.add(effect);
        }
        if (!this.world.field_72995_K) {
            ChannelHandler.instance().sendToDimension(new BlockSyncPacketC(this), this.world.field_73011_w.field_76574_g);
        }
        this.dataLoad(this.world);
    }

    public boolean hasEffect(Class<? extends AspectEffect> effect) {
        return this.getEffect(effect) != null;
    }

    private AspectEffect[] classesToEffects(Class[] list) {
        AspectEffect[] effects = new AspectEffect[list.length];
        for (int i = 0; i < effects.length; ++i) {
            try {
                AspectEffect eff = (AspectEffect)list[i].newInstance();
                eff.data = this;
                effects[i] = eff;
                continue;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return effects;
    }

    public AspectEffect[] getEffects() {
        AspectEffect[] classes = new AspectEffect[this.dataEffects.size()];
        return this.dataEffects.toArray(classes);
    }

    public Aspect[] getAspects() {
        AspectEffect[] effects = this.getEffects();
        Aspect[] aspects = new Aspect[effects.length];
        for (int i = 0; i < effects.length; ++i) {
            if (effects[i] == null) continue;
            aspects[i] = AspectHandler.getAspectsFromEffect(effects[i].getClass());
        }
        return aspects;
    }

    @Override
    public void writeNBT(NBTTagCompound tagCompound) {
        super.writeNBT(tagCompound);
        tagCompound.func_74768_a("length", this.dataEffects.size());
        for (int i = 0; i < this.dataEffects.size(); ++i) {
            tagCompound.func_74782_a("effect: " + i, (NBTBase)SavableHelper.saveDataToNBT(this.dataEffects.get(i)));
        }
    }

    @Override
    public void readNBT(NBTTagCompound tagCompound) {
        super.readNBT(tagCompound);
        this.dataEffects = new ArrayList();
        for (int i = 0; i < tagCompound.func_74762_e("length"); ++i) {
            this.dataEffects.add((AspectEffect)SavableHelper.loadDataFromNBT(tagCompound.func_74775_l("effect: " + i)));
        }
    }

    @Override
    public int[] hookMethods(Block block) {
        return this.methods;
    }

    @Override
    public Block getBlock(int method) {
        Integer index = this.methodsToBlock.get(method);
        return index != null ? this.dataEffects.get(index) : null;
    }

    @Override
    public boolean shouldOverride(int method) {
        return this.methodsOverrides.get(method) != null && this.methodsOverrides.get(method).overrideBlockFunc();
    }
}

