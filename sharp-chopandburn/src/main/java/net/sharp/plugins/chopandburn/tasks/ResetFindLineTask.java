package net.sharp.plugins.chopandburn.tasks;

import net.sharp.plugins.chopandburn.framework.ChopAndBurnTask;
import net.unethicalite.api.items.Inventory;

import java.util.Locale;

public class ResetFindLineTask extends ChopAndBurnTask {
    @Override
    public boolean validate() {
        var hasLogs = Inventory.getFirst(x -> x.getName().toLowerCase(Locale.ROOT).contains("logs"));
        return hasLogs != null && this.isShouldFindLine();
    }

    @Override
    public int execute() {
        this.setShouldFindLine(false);
        return 333;
    }
}
