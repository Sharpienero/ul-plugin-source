package net.sharp.plugins.chopandburn;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.plugins.PluginDescriptor;
import net.sharp.plugins.chopandburn.tasks.*;
import net.unethicalite.api.plugins.Task;
import net.unethicalite.api.plugins.TaskPlugin;
import org.pf4j.Extension;

@Extension
@PluginDescriptor(
        name = "32bh ChopAndBurn",
        description = "Automatically chops trees and burns the logs",
        enabledByDefault = false
)
@Slf4j
public class ChopAndBurnPlugin extends TaskPlugin {
    private final Task[] tasks = new Task[]
            {
                    new ChopTask(),
                    new BurnTask(),
//                    new SetFindLineTask(),
//                    new ResetFindLineTask(),
//                    new FindBurnLine(),
            };

    @Override
    public Task[] getTasks()
    {
        return tasks;
    }
}
