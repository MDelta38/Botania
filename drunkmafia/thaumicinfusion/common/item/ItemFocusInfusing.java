/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IAspectSource
 *  thaumcraft.api.wands.ItemFocusBasic
 */
package drunkmafia.thaumicinfusion.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import drunkmafia.thaumicinfusion.common.ThaumicInfusion;
import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.aspect.AspectHandler;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.common.world.data.BlockData;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectSource;
import thaumcraft.api.wands.ItemFocusBasic;

public class ItemFocusInfusing
extends ItemFocusBasic {
    private static final List<Block> blockblacklist;
    public IIcon iconOrnament;
    public IIcon depthIcon;

    public ItemFocusInfusing() {
        this.func_77637_a(ThaumicInfusion.instance.tab);
    }

    public String getSortingHelper(ItemStack itemstack) {
        return "BWI" + super.getSortingHelper(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.depthIcon = ir.func_94245_a("thaumicinfusion:focus_infusion_depth");
        this.icon = ir.func_94245_a("thaumicinfusion:focus_infusion");
        this.iconOrnament = ir.func_94245_a("thaumicinfusion:focus_infusion_orn");
    }

    public IIcon getFocusDepthLayerIcon(ItemStack itemstack) {
        return this.depthIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77618_c(int par1, int renderPass) {
        return renderPass == 1 ? this.icon : this.iconOrnament;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    public IIcon getOrnament(ItemStack itemstack) {
        return this.iconOrnament;
    }

    public AspectList getVisCost(ItemStack focusstack) {
        return new AspectList();
    }

    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
        player.func_71038_i();
        if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            NBTTagCompound wandNBT;
            if (blockblacklist.contains(world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d))) {
                return itemstack;
            }
            NBTTagCompound nBTTagCompound = wandNBT = itemstack.func_77978_p() != null ? itemstack.func_77978_p() : new NBTTagCompound();
            if (!world.field_72995_K) {
                Aspect aspect = wandNBT.func_74764_b("InfusionAspect") ? Aspect.getAspect((String)wandNBT.func_74779_i("InfusionAspect")) : null;
                this.placeAspect(player, new WorldCoordinates(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, player.field_71093_bK), aspect);
                world.func_72908_a((double)mop.field_72311_b + 0.5, (double)mop.field_72312_c + 0.5, (double)mop.field_72309_d + 0.5, "thaumcraft:wand", 0.7f, world.field_73012_v.nextFloat() * 0.1f + 0.9f);
            }
        } else {
            player.openGui((Object)ThaumicInfusion.instance, 0, world, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
        }
        return itemstack;
    }

    public void placeAspect(EntityPlayer player, WorldCoordinates pos, Aspect aspect) {
        World world = player.field_70170_p;
        TIWorldData worldData = TIWorldData.getWorldData(world);
        WorldCoordinates coords = new WorldCoordinates(pos.x, pos.y, pos.z, player.field_71093_bK);
        if (aspect == null) {
            BlockData data = worldData.getBlock(BlockData.class, pos);
            if (data != null) {
                AspectList list = new AspectList();
                for (Aspect currentAspect : data.getAspects()) {
                    list.add(currentAspect, AspectHandler.getCostOfEffect(currentAspect));
                }
                this.refillJars(player, list);
                worldData.removeData(BlockData.class, pos, true);
            }
        } else {
            Aspect[] c;
            BlockData data = worldData.getBlock(BlockData.class, coords);
            if (data == null) {
                c = AspectHandler.getEffectFromAspect(aspect);
                if (c == null) {
                    return;
                }
                if (this.drainAspects(player, aspect)) {
                    data = new BlockData(coords, new Class[]{c});
                }
            } else {
                int n;
                c = data.getAspects();
                int n2 = c.length;
                for (n = 0; n < n2; ++n) {
                    Aspect dataAspect = c[n];
                    if (dataAspect != aspect) continue;
                    ArrayList<Class<? extends AspectEffect>> newAspects = new ArrayList<Class<? extends AspectEffect>>();
                    for (Aspect aspect2 : data.getAspects()) {
                        if (aspect2 == aspect) continue;
                        newAspects.add(AspectHandler.getEffectFromAspect(aspect2));
                    }
                    if (newAspects.size() == 0) {
                        worldData.removeData(BlockData.class, pos, true);
                    } else if (this.drainAspects(player, aspect)) {
                        worldData.removeData(BlockData.class, pos, true);
                        data = new BlockData(coords, newAspects.toArray(new Class[newAspects.size()]));
                        for (AspectEffect aspectEffect : data.getEffects()) {
                            aspectEffect.onPlaceEffect(player);
                        }
                        worldData.addBlock(data, true, true);
                    }
                    return;
                }
                if (this.drainAspects(player, aspect)) {
                    ArrayList<Class<? extends AspectEffect>> newAspects = new ArrayList<Class<? extends AspectEffect>>();
                    newAspects.add(AspectHandler.getEffectFromAspect(aspect));
                    Aspect[] aspectArray = data.getAspects();
                    n = aspectArray.length;
                    for (int dataAspect = 0; dataAspect < n; ++dataAspect) {
                        Aspect dataAspect2 = aspectArray[dataAspect];
                        newAspects.add(AspectHandler.getEffectFromAspect(dataAspect2));
                    }
                    worldData.removeData(BlockData.class, pos, true);
                    data = new BlockData(coords, newAspects.toArray(new Class[newAspects.size()]));
                }
            }
            if (data != null) {
                for (AspectEffect effect : data.getEffects()) {
                    effect.onPlaceEffect(player);
                }
                worldData.addBlock(data, true, true);
            }
        }
    }

    public boolean drainAspects(EntityPlayer player, Aspect aspect) {
        if (player.field_71075_bZ.field_75098_d) {
            return true;
        }
        int cost = AspectHandler.getCostOfEffect(aspect);
        int x = (int)(player.field_70165_t - 10.0);
        while ((double)x < player.field_70165_t + 10.0) {
            int y = (int)(player.field_70163_u - 10.0);
            while ((double)y < player.field_70163_u + 10.0) {
                int z = (int)(player.field_70161_v - 10.0);
                while ((double)z < player.field_70161_v + 10.0) {
                    IAspectSource source;
                    TileEntity tileEntity = player.field_70170_p.func_147438_o(x, y, z);
                    if (tileEntity instanceof IAspectSource && (source = (IAspectSource)tileEntity).doesContainerContainAmount(aspect, cost)) {
                        source.takeFromContainer(aspect, cost);
                        player.field_70170_p.func_72980_b((double)((float)tileEntity.field_145851_c + 0.5f), (double)((float)tileEntity.field_145848_d + 0.5f), (double)((float)tileEntity.field_145849_e + 0.5f), "game.neutral.swim", 0.5f, 1.0f + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.3f, false);
                        return true;
                    }
                    ++z;
                }
                ++y;
            }
            ++x;
        }
        return false;
    }

    public boolean refillJars(EntityPlayer player, AspectList aspectList) {
        if (player.field_71075_bZ.field_75098_d) {
            return true;
        }
        int filled = 0;
        block0: for (int i = 0; i < aspectList.size(); ++i) {
            boolean foundJar = false;
            Aspect currentAspect = aspectList.getAspects()[i];
            int x = (int)(player.field_70165_t - 10.0);
            while ((double)x < player.field_70165_t + 10.0) {
                int y = (int)(player.field_70163_u - 10.0);
                while ((double)y < player.field_70163_u + 10.0) {
                    int z = (int)(player.field_70161_v - 10.0);
                    while ((double)z < player.field_70161_v + 10.0) {
                        IAspectSource source;
                        TileEntity tileEntity = player.field_70170_p.func_147438_o(x, y, z);
                        if (tileEntity instanceof IAspectSource && (source = (IAspectSource)tileEntity).doesContainerAccept(currentAspect)) {
                            source.addToContainer(currentAspect, AspectHandler.getCostOfEffect(currentAspect));
                            ++filled;
                            foundJar = true;
                            player.field_70170_p.func_72980_b((double)((float)tileEntity.field_145851_c + 0.5f), (double)((float)tileEntity.field_145848_d + 0.5f), (double)((float)tileEntity.field_145849_e + 0.5f), "game.neutral.swim", 0.5f, 1.0f + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.3f, false);
                            break;
                        }
                        ++z;
                    }
                    if (foundJar) break;
                    ++y;
                }
                if (foundJar) continue block0;
                ++x;
            }
        }
        return filled == aspectList.size();
    }

    static {
        String[] blocks;
        blockblacklist = new ArrayList<Block>();
        for (String block : blocks = ThaumicInfusion.instance.config.get("Block Blacklist", "Blocks that are banned from being infused", new String[]{"bedrock"}).getStringList()) {
            blockblacklist.add(Block.func_149684_b((String)block));
        }
    }
}

