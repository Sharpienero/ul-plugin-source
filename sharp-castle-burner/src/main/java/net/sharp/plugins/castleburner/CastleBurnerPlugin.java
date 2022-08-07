package net.sharp.plugins.castleburner;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.PluginDescriptor;
import net.sharp.plugins.castleburner.tasks.BurnTask;
import net.sharp.plugins.castleburner.tasks.HopTask;
import net.unethicalite.api.plugins.Task;
import net.unethicalite.api.plugins.TaskPlugin;
import org.pf4j.Extension;



@Extension
@PluginDescriptor(name = "32bh LumbyCastleBurnHop", enabledByDefault = false)
@Slf4j

public class CastleBurnerPlugin extends TaskPlugin {

    private final Task[] tasks = new Task[]
            {
                    new HopTask(),
                    new BurnTask()
            };

    @Override
    public Task[] getTasks()
    {
        return tasks;
    }

    @Provides
    Config provideConfig(ConfigManager configManager) {
        return configManager.getConfig(CastleBurnerConfig.class);
    }
}