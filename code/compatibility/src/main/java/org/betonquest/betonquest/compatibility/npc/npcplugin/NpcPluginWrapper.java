package org.betonquest.betonquest.compatibility.npc.npcplugin;

import de.eisi05.npc.api.manager.NpcManager;
import de.eisi05.npc.api.objects.NPC;
import org.betonquest.betonquest.api.QuestException;
import org.betonquest.betonquest.api.instruction.Argument;
import org.betonquest.betonquest.api.profile.Profile;
import org.betonquest.betonquest.api.quest.npc.Npc;
import org.betonquest.betonquest.api.quest.npc.NpcWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * NpcPlugin wrapper to get a Npc.
 */
public class NpcPluginWrapper implements NpcWrapper<NPC> {

    /**
     * Npc identifier.
     */
    private final Argument<String> npcId;

    /**
     * Create a new NpcPlugin Npc Wrapper.
     *
     * @param npcId the npc identifier
     */
    public NpcPluginWrapper(final Argument<String> npcId) {
        this.npcId = npcId;
    }

    @Override
    public Npc<NPC> getNpc(@Nullable final Profile profile) throws QuestException {
        final String npcId = this.npcId.getValue(profile);
        final Optional<NPC> npc = NpcManager.fromUUID(parseUniqueId(npcId));
        if (npc.isEmpty()) {
            throw new QuestException("NpcPlugin Npc with ID " + npcId + " not found");
        }
        return new NpcPluginAdapter(npc.get());
    }

    @Override
    public Set<Npc<NPC>> getNpcs(@Nullable final Profile profile) throws QuestException {
        return Set.of(getNpc(profile));
    }

    private UUID parseUniqueId(final String npcId) throws QuestException {
        try {
            return UUID.fromString(npcId);
        } catch (final IllegalArgumentException exception) {
            throw new QuestException("NpcPlugin Npc ID '" + npcId + "' is not a valid unique id", exception);
        }
    }
}
