package org.betonquest.betonquest.compatibility.npc.npcplugin;

import de.eisi05.npc.api.objects.NPC;
import org.betonquest.betonquest.api.QuestException;
import org.betonquest.betonquest.api.instruction.Instruction;
import org.betonquest.betonquest.api.quest.npc.NpcFactory;
import org.betonquest.betonquest.api.quest.npc.NpcWrapper;

/**
 * Factory to get NpcPlugin Npcs.
 */
public class NpcPluginFactory implements NpcFactory {

    /**
     * The default Constructor.
     */
    public NpcPluginFactory() {
    }

    @Override
    public NpcWrapper<NPC> parseInstruction(final Instruction instruction) throws QuestException {
        return new NpcPluginWrapper(instruction.string().get());
    }
}
