package org.betonquest.betonquest.compatibility.npc.npcplugin;

import de.eisi05.npc.api.objects.NPC;
import org.betonquest.betonquest.compatibility.npc.GenericReverseIdentifier;

/**
 * Allows to get NpcIds for a NpcPlugin Npc.
 */
public class NpcPluginIdentifier extends GenericReverseIdentifier<NPC> {

    /**
     * Create a new NpcPlugin Identifier.
     *
     * @param prefix the prefix of relevant Ids
     */
    public NpcPluginIdentifier(final String prefix) {
        super(prefix, NPC.class, npc -> npc.getUUID().toString());
    }
}
