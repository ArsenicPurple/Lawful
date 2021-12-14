package co.basin.lawfulmod.core.packets;

import co.basin.lawfulmod.common.entities.MeowlzebubEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MeowlzebubShieldPKT {
    private final int meowlzebubId;
    private final boolean hasShield;

    public MeowlzebubShieldPKT(PacketBuffer buf) {
        this.meowlzebubId = buf.readInt();
        this.hasShield = buf.readBoolean();
    }

    public MeowlzebubShieldPKT(int meowlzebubId, boolean hasShield) {
        this.meowlzebubId = meowlzebubId;
        this.hasShield = hasShield;
    }

    public void encode(PacketBuffer buf) {
        buf.writeInt(meowlzebubId);
        buf.writeBoolean(hasShield);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        assert Minecraft.getInstance().level != null;
        Entity entity = Minecraft.getInstance().level.getEntity(meowlzebubId);
        if (entity instanceof MeowlzebubEntity) {
            ((MeowlzebubEntity) entity).setHasShield(hasShield);
        }

        context.get().setPacketHandled(true);
    }
}
