package org.betonquest.betonquest.compatibility.npc.npcplugin;

import de.eisi05.npc.api.events.NpcPreShowEvent;
import org.betonquest.betonquest.api.service.npc.NpcManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Prevents showing of hidden Npcs.
 */
public class NpcPluginHider implements Listener {

    /**
     * Manager to check current visibility.
     */
    private final NpcManager npcManager;

    /**
     * Create a new NpcPlugin Hider to force Npc hiding.
     *
     * @param npcManager the npc manager to check if Npc is hidden
     */
    public NpcPluginHider(final NpcManager npcManager) {
        this.npcManager = npcManager;
    }

    /**
     * Cancels npc sending to player if the hide conditions are met.
     *
     * @param event the pre show event to listen
     */
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPreShow(final NpcPreShowEvent event) {
        if (npcManager.isHidden(new NpcPluginAdapter(event.getNpc()), event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
