/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import drunkmafia.thaumicinfusion.net.packet.server.EffectSyncPacketC;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="sensus")
public class Sensus
extends AspectEffect {
    private Block disguisedBlock;
    private int metadata;

    @Override
    public void aspectInit(World world, WorldCoordinates pos) {
        super.aspectInit(world, pos);
        if (!world.field_72995_K) {
            ChannelHandler.instance().sendToDimension(new EffectSyncPacketC(this, true), world.field_73011_w.field_76574_g);
        }
    }

    @Override
    public int getCost() {
        return 4;
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
        Block block;
        if (world.field_72995_K) {
            return;
        }
        ItemStack stackInHand = player.func_71045_bC();
        if (player.func_70093_af()) {
            this.disguisedBlock = null;
            ChannelHandler.instance().sendToDimension(new EffectSyncPacketC(this, true), world.field_73011_w.field_76574_g);
        } else if (stackInHand != null && stackInHand.func_77973_b() instanceof ItemBlock && (block = Block.func_149634_a((Item)stackInHand.func_77973_b())).func_149721_r() && block.func_149686_d()) {
            this.disguisedBlock = block;
            this.metadata = stackInHand.func_77960_j();
            ChannelHandler.instance().sendToDimension(new EffectSyncPacketC(this, true), world.field_73011_w.field_76574_g);
        }
    }

    @OverrideBlock
    public IIcon func_149673_e(IBlockAccess access, int x, int y, int z, int side) {
        IIcon icon = this.disguisedBlock != null ? this.disguisedBlock.func_149691_a(side, this.metadata) : null;
        return icon != null ? icon : access.func_147439_a(x, y, z).func_149691_a(side, access.func_72805_g(x, y, z));
    }

    @Override
    public void readNBT(NBTTagCompound tagCompound) {
        super.readNBT(tagCompound);
        if (tagCompound.func_74764_b("disguisedBlock")) {
            this.disguisedBlock = Block.func_149729_e((int)tagCompound.func_74762_e("disguisedBlock"));
            this.metadata = tagCompound.func_74762_e("metadata");
        } else {
            this.disguisedBlock = null;
        }
    }

    @Override
    public void writeNBT(NBTTagCompound tagCompound) {
        super.writeNBT(tagCompound);
        if (this.disguisedBlock != null) {
            tagCompound.func_74768_a("disguisedBlock", Block.func_149682_b((Block)this.disguisedBlock));
            tagCompound.func_74768_a("metadata", this.metadata);
        }
    }
}

