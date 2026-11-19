package org.betonquest.betonquest.compatibility.npc.npcplugin;

import de.eisi05.npc.api.manager.NpcManager;
import org.betonquest.betonquest.api.QuestException;
import org.betonquest.betonquest.api.instruction.Argument;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
class NpcPluginWrapperTest {

    @Test
    void invalid_unique_id_throws_exception() throws QuestException {
        final Argument<String> npcId = mock(Argument.class);
        when(npcId.getValue(null)).thenReturn("not-a-unique-id");
        final NpcPluginWrapper wrapper = new NpcPluginWrapper(npcId);

        final QuestException exception = assertThrows(QuestException.class, () -> wrapper.getNpc(null));

        assertEquals("NpcPlugin Npc ID 'not-a-unique-id' is not a valid unique id", exception.getMessage(),
                "Invalid unique ids should be rejected with a QuestException");
    }

    @Test
    void unknown_unique_id_throws_exception() throws QuestException {
        final UUID uniqueId = UUID.randomUUID();
        final Argument<String> npcId = mock(Argument.class);
        when(npcId.getValue(null)).thenReturn(uniqueId.toString());
        final NpcPluginWrapper wrapper = new NpcPluginWrapper(npcId);

        try (MockedStatic<NpcManager> npcManager = mockStatic(NpcManager.class)) {
            npcManager.when(() -> NpcManager.fromUUID(uniqueId)).thenReturn(Optional.empty());

            final QuestException exception = assertThrows(QuestException.class, () -> wrapper.getNpc(null));

            assertEquals("NpcPlugin Npc with ID " + uniqueId + " not found", exception.getMessage(),
                    "Unknown unique ids should be rejected with a QuestException");
        }
    }
}
