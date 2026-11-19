package org.betonquest.betonquest.compatibility.npc.npcplugin;

import de.eisi05.npc.api.enums.ClickActionType;
import de.eisi05.npc.api.events.NpcInteractEvent;
import de.eisi05.npc.api.objects.NPC;
import org.betonquest.betonquest.api.profile.ProfileProvider;
import org.betonquest.betonquest.api.quest.npc.NpcInteractCatcher;
import org.betonquest.betonquest.api.service.npc.NpcRegistry;
import org.betonquest.betonquest.quest.objective.interact.Interaction;
import org.bukkit.event.EventHandler;

/**
 * Catches interaction with NpcPlugin Npcs.
 */
public class NpcPluginCatcher extends NpcInteractCatcher<NPC> {

    /**
     * Initializes the NpcPlugin catcher.
     *
     * @param profileProvider the profile provider instance
     * @param npcRegistry     the registry to identify the clicked Npc
     */
    public NpcPluginCatcher(final ProfileProvider profileProvider, final NpcRegistry npcRegistry) {
        super(profileProvider, npcRegistry);
    }

    /**
     * Catches Npc interactions.
     *
     * @param event the Npc interact event
     */
    @EventHandler(ignoreCancelled = true)
    public void onClick(final NpcInteractEvent event) {
        final Interaction interaction;
        if (event.getAction() == ClickActionType.LEFT) {
            interaction = Interaction.LEFT;
        } else if (event.getAction() == ClickActionType.RIGHT) {
            interaction = Interaction.RIGHT;
        } else {
            return;
        }

        if (interactLogic(event.getPlayer(), new NpcPluginAdapter(event.getNpc()), interaction,
                false, event.isAsynchronous())) {
            event.setCancelled(true);
        }
    }
}
