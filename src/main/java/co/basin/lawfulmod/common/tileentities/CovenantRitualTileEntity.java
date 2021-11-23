package co.basin.lawfulmod.common.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class CovenantRitualTileEntity extends TileEntity {
    public CovenantRitualTileEntity(TileEntityType<?> type) {
        super(type);
    }

    private CompoundNBT nbt = new CompoundNBT();

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
    }

    @Override
    public CompoundNBT getTileData() {
        return super.getTileData();
    }
}
