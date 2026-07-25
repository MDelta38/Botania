/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.ThaumcraftApiHelper
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IAspectContainer
 *  thaumcraft.api.aspects.IEssentiaTransport
 *  thaumcraft.api.visnet.VisNetHandler
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.lib.utils.Utils
 */
package witchinggadgets.common.blocks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.utils.Utils;
import witchinggadgets.api.ITerraformFocus;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;

public class TileEntityTerraformer
extends TileEntityWGBase
implements IAspectContainer,
IEssentiaTransport {
    private Aspect currentSuction;
    private AspectList essentia = new AspectList();
    int tick = 0;
    int drawDelay = 0;

    public void func_145845_h() {
        ++this.tick;
        if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof ITerraformFocus || this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof ITerraformFocus) {
            ITerraformFocus focus = null;
            focus = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof ITerraformFocus ? (ITerraformFocus)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) : (ITerraformFocus)this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
            if (this.getSuctionType(null) != focus.requiredAspect(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) {
                this.setSuction(focus.requiredAspect(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e), 0);
            }
            if (!this.field_145850_b.field_72995_K && this.currentSuction != null) {
                if (this.drawEssentia()) {
                    this.addToContainer(this.currentSuction, 1);
                }
                if (this.tick >= 20 && this.essentia.getAmount(this.currentSuction) >= 4) {
                    BiomeGenBase transformBiome = focus.getCreatedBiome(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
                    int x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
                    int z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
                    for (int to = 0; to < 40 && this.field_145850_b.func_72807_a((int)x, (int)z).field_76756_M == transformBiome.field_76756_M; ++to) {
                        x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
                        z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
                    }
                    if (this.field_145850_b.func_72807_a((int)x, (int)z).field_76756_M != transformBiome.field_76756_M && VisNetHandler.drainVis((World)this.field_145850_b, (int)this.field_145851_c, (int)this.field_145848_d, (int)this.field_145849_e, (Aspect)Aspect.EARTH, (int)4) == 4) {
                        Utils.setBiomeAt((World)this.field_145850_b, (int)x, (int)z, (BiomeGenBase)transformBiome);
                        this.takeFromContainer(this.currentSuction, 4);
                        for (int j = 0; j < 4; ++j) {
                            for (int i = 0; i < 4; ++i) {
                                ForgeDirection fd = ForgeDirection.getOrientation((int)(2 + i));
                                double x1 = (double)this.field_145851_c + 0.5 + 0.6875 * (double)fd.offsetX;
                                double y1 = (double)this.field_145848_d + 0.875;
                                double z1 = (double)this.field_145849_e + 0.5 + 0.6875 * (double)fd.offsetZ;
                                double x2 = 0.1875;
                                double y2 = 0.25;
                                double z2 = 0.1875;
                                Thaumcraft.proxy.drawVentParticles(this.field_145850_b, x1, y1, z1, x2, y2, z2, this.currentSuction.getColor(), 2.0f);
                            }
                        }
                    }
                }
            }
        } else if (this.getSuctionType(null) != null) {
            this.setSuction(null, 0);
        }
        if (this.tick >= 20) {
            this.tick = 0;
        }
    }

    boolean drawEssentia() {
        if (++this.drawDelay % 5 != 0) {
            return false;
        }
        TileEntity te = ThaumcraftApiHelper.getConnectableTile((World)this.field_145850_b, (int)this.field_145851_c, (int)this.field_145848_d, (int)this.field_145849_e, (ForgeDirection)ForgeDirection.DOWN);
        if (te != null) {
            IEssentiaTransport ic = (IEssentiaTransport)te;
            if (!ic.canOutputTo(ForgeDirection.UP)) {
                return false;
            }
            if (ic.getSuctionAmount(ForgeDirection.UP) < this.getSuctionAmount(ForgeDirection.DOWN) && ic.takeEssentia(this.currentSuction, 1, ForgeDirection.UP) == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void readCustomNBT(NBTTagCompound tags) {
        this.essentia.readFromNBT(tags);
        this.tick = tags.func_74762_e("tick");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tags) {
        this.essentia.writeToNBT(tags);
        tags.func_74768_a("tick", this.tick);
    }

    public int addEssentia(Aspect a, int amount, ForgeDirection fd) {
        return this.canInputFrom(fd) ? amount - this.addToContainer(a, amount) : 0;
    }

    public boolean canInputFrom(ForgeDirection fd) {
        return fd == ForgeDirection.DOWN;
    }

    public boolean canOutputTo(ForgeDirection fd) {
        return false;
    }

    public int getEssentiaAmount(ForgeDirection fd) {
        return 0;
    }

    public Aspect getEssentiaType(ForgeDirection fd) {
        return null;
    }

    public int getMinimumSuction() {
        return 0;
    }

    public int getSuctionAmount(ForgeDirection fd) {
        return this.currentSuction != null ? 128 : 0;
    }

    public Aspect getSuctionType(ForgeDirection fd) {
        return this.currentSuction;
    }

    public boolean isConnectable(ForgeDirection fd) {
        return fd == ForgeDirection.DOWN;
    }

    public boolean renderExtendedTube() {
        return false;
    }

    public void setSuction(Aspect a, int amount) {
        this.currentSuction = a;
    }

    public int takeEssentia(Aspect a, int amount, ForgeDirection fd) {
        return this.canOutputTo(fd) && this.takeFromContainer(a, amount) ? amount : 0;
    }

    public int addToContainer(Aspect a, int amount) {
        this.essentia.add(a, amount);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
        return 0;
    }

    public int containerContains(Aspect a) {
        return this.essentia.getAmount(a);
    }

    public boolean doesContainerAccept(Aspect a) {
        return a.equals(this.currentSuction);
    }

    public boolean doesContainerContain(AspectList al) {
        return false;
    }

    public boolean doesContainerContainAmount(Aspect a, int amount) {
        return this.essentia.getAmount(a) >= amount;
    }

    public AspectList getAspects() {
        return this.essentia;
    }

    public void setAspects(AspectList al) {
        this.essentia = al;
    }

    public boolean takeFromContainer(AspectList al) {
        return false;
    }

    public boolean takeFromContainer(Aspect a, int amount) {
        if (this.essentia.getAmount(a) >= amount) {
            this.essentia.remove(a, amount);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
            return true;
        }
        return false;
    }
}

