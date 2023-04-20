package scheduleTask.hutoolCron;

import cn.hutool.cron.task.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-10-18 09:59
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyTask implements Task {

    private String id;

    @Override
    public void execute() {
        log.info("do task...{}", id);
    }

}
