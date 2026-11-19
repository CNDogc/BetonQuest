package org.betonquest.betonquest.compatibility.npc.npcplugin;

import de.eisi05.npc.api.objects.NPC;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.betonquest.betonquest.api.bukkit.event.npc.NpcVisibilityUpdateEvent;
import org.betonquest.betonquest.api.profile.OnlineProfile;
import org.betonquest.betonquest.api.quest.npc.Npc;
import org.bukkit.Location;

import java.util.Optional;

/**
 * NpcPlugin Compatibility Adapter for general BetonQuest NPC behaviour.
 */
public class NpcPluginAdapter implements Npc<NPC> {

    /**
     * The NpcPlugin NPC instance.
     */
    private final NPC npc;

    /**
     * Create a new NpcPlugin NPC Adapter.
     *
     * @param npc the NpcPlugin NPC instance
     */
    public NpcPluginAdapter(final NPC npc) {
        this.npc = npc;
    }

    @Override
    public NPC getOriginal() {
        return npc;
    }

    @Override
    public String getName() {
        return PlainTextComponentSerializer.plainText().serialize(npc.getName());
    }

    @Override
    public String getFormattedName() {
        return LegacyComponentSerializer.legacySection().serialize(npc.getName());
    }

    @Override
    public Optional<Location> getLocation() {
        return Optional.of(npc.getLocation().clone());
    }

    @Override
    public Optional<Location> getEyeLocation() {
        return getLocation();
    }

    @Override
    public void teleport(final Location location) {
        npc.setLocation(location);
        new NpcVisibilityUpdateEvent(this).callEvent();
    }

    @Override
    public boolean isSpawned() {
        return npc.isEnabled();
    }

    @Override
    public void spawn(final Location location) {
        npc.setEnabled(true);
        teleport(location);
    }

    @Override
    public void despawn() {
        npc.setEnabled(false);
        new NpcVisibilityUpdateEvent(this).callEvent();
    }

    @Override
    public void show(final OnlineProfile onlineProfile) {
        npc.showNPCToPlayer(onlineProfile.getPlayer());
    }

    @Override
    public void hide(final OnlineProfile onlineProfile) {
        npc.hideNpcFromPlayer(onlineProfile.getPlayer());
    }
}
