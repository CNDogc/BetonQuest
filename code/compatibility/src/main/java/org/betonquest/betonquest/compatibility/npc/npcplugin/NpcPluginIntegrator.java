package org.betonquest.betonquest.compatibility.npc.npcplugin;

import org.betonquest.betonquest.api.BetonQuestApi;
import org.betonquest.betonquest.api.integration.Integration;
import org.betonquest.betonquest.api.integration.policy.Policy;
import org.betonquest.betonquest.api.service.npc.NpcRegistry;
import org.betonquest.betonquest.lib.integration.policy.Policies;
import org.betonquest.betonquest.lib.version.DefaultVersionType;
import org.betonquest.betonquest.lib.version.VersionParser;

/**
 * Integrator implementation for the
 * <a href="https://www.spigotmc.org/resources/npc-plugin-1-17-26-1-2.87761/">NpcPlugin</a>.
 * <p>
 * Only the Paper flavour of the plugin is supported, as the Spigot flavour is a different plugin.
 */
public class NpcPluginIntegrator implements Integration {

    /**
     * The prefix used before any registered name for distinguishing.
     */
    public static final String PREFIX = "NpcPlugin";

    /**
     * The name of the integrated plugin as declared in its plugin metadata.
     */
    public static final String PLUGIN_NAME = "NpcPlugin-Paper";

    /**
     * The minimum required version of the NpcPlugin.
     */
    public static final String REQUIRED_VERSION = "3.6.0";

    /**
     * The exclusive upper bound of the supported NpcPlugin version range.
     */
    public static final String MAX_VERSION = "3.999";

    /**
     * The default Constructor.
     */
    public NpcPluginIntegrator() {
    }

    /**
     * Checks for the valid version range of the 'NpcPlugin' plugin.
     *
     * @return whether the correct version is installed or not
     */
    public static Policy[] getPolicies() {
        return Policies.pluginVersionRange(PLUGIN_NAME,
                VersionParser.parse(DefaultVersionType.SIMPLE_SEMANTIC_VERSION, REQUIRED_VERSION),
                VersionParser.parse(DefaultVersionType.SIMPLE_SEMANTIC_VERSION, MAX_VERSION));
    }

    @Override
    public void enable(final BetonQuestApi api) {
        final NpcRegistry npcRegistry = api.npcs().registry();
        api.bukkit().registerEvents(new NpcPluginCatcher(api.profiles(), npcRegistry));
        api.bukkit().registerEvents(new NpcPluginHider(api.npcs().manager()));
        npcRegistry.register(PREFIX, new NpcPluginFactory());
        npcRegistry.registerIdentifier(new NpcPluginIdentifier(PREFIX));
    }

    @Override
    public void postEnable(final BetonQuestApi api) {
        // Empty
    }

    @Override
    public void disable() {
        // Empty
    }
}
