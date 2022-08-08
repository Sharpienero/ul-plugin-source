package net.sharp.plugins.chopandburn.tasks;

import net.sharp.plugins.chopandburn.framework.ChopAndBurnTask;
import net.unethicalite.api.items.Inventory;

import java.util.Locale;

public class SetFindLineTask extends ChopAndBurnTask {
    @Override
    public boolean validate() {
        var hasLogs = Inventory.getFirst(x -> x.getName().toLowerCase(Locale.ROOT).contains("logs"));
        return Inventory.isFull() && hasLogs != null && !this.isShouldFindLine();
    }

    @Override
    public int execute() {
        this.setShouldFindLine(true);
        return 333;
    }
}
