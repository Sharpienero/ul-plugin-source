package net.sharp.plugins.chopandburn.tasks;

import lombok.Getter;
import lombok.Setter;
import net.unethicalite.api.plugins.Task;

@Getter
@Setter
public class ChopAndBurnTask implements Task {
    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        return 0;
    }
}
