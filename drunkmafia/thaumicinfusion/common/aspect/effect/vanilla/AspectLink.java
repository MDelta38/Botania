/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.ThaumicInfusion;
import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.item.TIItems;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.common.world.data.BlockData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.api.WorldCoordinates;

public abstract class AspectLink
extends AspectEffect {
    public WorldCoordinates destination;

    @OverrideBlock(overrideBlockFunc=false)
    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack paper = player.func_71045_bC();
        if (world.field_72995_K || paper == null || paper.func_77973_b() != TIItems.coordinatePaper && paper.func_77973_b() != Items.field_151121_aF) {
            return false;
        }
        NBTTagCompound paperTag = paper.func_77978_p();
        WorldCoordinates pos = new WorldCoordinates(x, y, z, player.field_71093_bK);
        if (paper.func_77973_b() == TIItems.coordinatePaper && paperTag != null && paperTag.func_74764_b("CoordinateX")) {
            WorldCoordinates storedDest = new WorldCoordinates(paperTag.func_74762_e("CoordinateX"), paperTag.func_74762_e("CoordinateY"), paperTag.func_74762_e("CoordinateZ"), paperTag.func_74762_e("CoordinateDim"));
            WorldServer worldDest = DimensionManager.getWorld((int)storedDest.dim);
            BlockData data = TIWorldData.getWorldData((World)worldDest).getBlock(BlockData.class, storedDest);
            if (data == null || data.getEffect(this.getClass()) == null || data.getEffect(this.getClass()) == this) {
                player.func_145747_a((IChatComponent)new ChatComponentText(ThaumicInfusion.translate("ti.linking.fail", new Object[0])));
                return false;
            }
            ((AspectLink)data.getEffect(this.getClass())).destination = pos;
            this.destination = storedDest;
            player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = new ItemStack(Items.field_151121_aF);
            player.func_145747_a((IChatComponent)new ChatComponentText(ThaumicInfusion.translate("ti.linking.end", new Object[0])));
            world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:zap", 0.25f, 1.0f);
            return false;
        }
        player.func_145747_a((IChatComponent)new ChatComponentText(ThaumicInfusion.translate("ti.linking.begin", new Object[0])));
        if (paper.field_77994_a > 1) {
            --paper.field_77994_a;
            world.func_72838_d((Entity)new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, paper));
        }
        paperTag = (paper = new ItemStack(TIItems.coordinatePaper)).func_77978_p() != null ? paper.func_77978_p() : new NBTTagCompound();
        paperTag.func_74768_a("CoordinateX", x);
        paperTag.func_74768_a("CoordinateY", y);
        paperTag.func_74768_a("CoordinateZ", z);
        paperTag.func_74768_a("CoordinateDim", player.field_71093_bK);
        paper.func_77982_d(paperTag);
        player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = paper;
        world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:zap", 0.25f, 1.0f);
        return false;
    }

    public WorldCoordinates getDestination() {
        WorldServer world;
        if (this.destination == null || (world = DimensionManager.getWorld((int)this.destination.dim)) == null) {
            this.destination = null;
            return null;
        }
        BlockData blockData = TIWorldData.getWorldData((World)world).getBlock(BlockData.class, this.destination);
        return blockData != null && blockData.hasEffect(this.getClass()) ? this.destination : (this.destination = null);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt) {
        super.writeNBT(nbt);
        if (this.destination == null) {
            return;
        }
        this.destination.writeNBT(nbt);
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {
        super.readNBT(nbt);
        if (!nbt.func_74764_b("dest_x")) {
            this.destination = null;
        }
        this.destination = new WorldCoordinates();
        this.destination.readNBT(nbt);
    }
}

