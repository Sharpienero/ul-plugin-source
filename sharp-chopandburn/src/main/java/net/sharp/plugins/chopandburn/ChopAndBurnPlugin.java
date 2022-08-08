package net.sharp.plugins.chopandburn;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.PluginDescriptor;
import net.sharp.plugins.chopandburn.tasks.*;
import net.unethicalite.api.movement.pathfinder.GlobalCollisionMap;
import net.unethicalite.api.plugins.Task;
import net.unethicalite.api.plugins.TaskPlugin;
import org.pf4j.Extension;

import javax.inject.Inject;

@Extension
@PluginDescriptor(
        name = "32bh ChopAndBurn",
        description = "Automatically chops trees and burns the logs",
        enabledByDefault = false
)
@Slf4j
public class ChopAndBurnPlugin extends TaskPlugin {

    @Inject
    private ChopAndBurnConfig config;
    @Provides
    ChopAndBurnConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(ChopAndBurnConfig.class);
    }

    @Inject
    private GlobalCollisionMap collisionMap;

    private final Task[] tasks = new Task[]
            {
                    new ChopTask(),
                    new BurnTask(),
                    new SetFindLineTask(),
                    new ResetFindLineTask(),
                    new FindBurnLine(),
            };

    @Override
    public Task[] getTasks()
    {
        return tasks;
    }
}
