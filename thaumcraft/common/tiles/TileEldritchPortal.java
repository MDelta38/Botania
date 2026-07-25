/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.Teleporter
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Teleporter;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.world.dim.TeleporterThaumcraft;

public class TileEldritchPortal
extends TileEntity {
    public int opencount = -1;
    private int count = 0;

    public boolean canUpdate() {
        return true;
    }

    public double func_145833_n() {
        return 9216.0;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)(this.field_145848_d - 1), (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 2), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 2));
    }

    public void func_145845_h() {
        List ents;
        ++this.count;
        if (this.field_145850_b.field_72995_K && (this.count % 250 == 0 || this.count == 0)) {
            this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:evilportal", 1.0f, 1.0f, false);
        }
        if (this.field_145850_b.field_72995_K && this.opencount < 30) {
            ++this.opencount;
        }
        if (!this.field_145850_b.field_72995_K && this.count % 5 == 0 && (ents = this.field_145850_b.func_72872_a(EntityPlayerMP.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(0.5, 1.0, 0.5))).size() > 0) {
            for (Object e : ents) {
                EntityPlayerMP player = (EntityPlayerMP)e;
                if (player.field_70154_o != null || player.field_70153_n != null) continue;
                MinecraftServer mServer = FMLCommonHandler.instance().getMinecraftServerInstance();
                if (player.field_71088_bW > 0) {
                    player.field_71088_bW = 100;
                    continue;
                }
                if (player.field_71093_bK != Config.dimensionOuterId) {
                    player.field_71088_bW = 100;
                    player.field_71133_b.func_71203_ab().transferPlayerToDimension(player, Config.dimensionOuterId, (Teleporter)new TeleporterThaumcraft(mServer.func_71218_a(Config.dimensionOuterId)));
                    if (ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "ENTEROUTER")) continue;
                    PacketHandler.INSTANCE.sendTo((IMessage)new PacketResearchComplete("ENTEROUTER"), player);
                    Thaumcraft.proxy.getResearchManager().completeResearch((EntityPlayer)player, "ENTEROUTER");
                    continue;
                }
                player.field_71088_bW = 100;
                player.field_71133_b.func_71203_ab().transferPlayerToDimension(player, 0, (Teleporter)new TeleporterThaumcraft(mServer.func_71218_a(0)));
            }
        }
    }
}

