package com.xiw.pattern.command;

import org.junit.Test;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 14:58
 */
public class CommandTest {
    @Test
    public void test() {
        Computer computer = new Computer();
        Door door = new Door();
        Light light = new Light();
        QuickCommand quickCommand = new QuickCommand(new Command[]{new DoorCloseCommand(door), new LightOffCommand(light), new ComputerOnCommand(computer)});
        ControlPanel controlPanel = new ControlPanel();
        controlPanel.setCommands(0, new ComputerOnCommand(computer));
        controlPanel.setCommands(1, new ComputerOffCommand(computer));
        controlPanel.setCommands(2, new DoorOpenCommand(door));
        controlPanel.setCommands(3, new DoorCloseCommand(door));
        controlPanel.setCommands(5, new LightOnCommand(light));
        controlPanel.setCommands(6, new LightOffCommand(light));
        controlPanel.setCommands(4, quickCommand);
        for (int i = 0; i < 9; i++) {
            controlPanel.keyPressed(i);
        }
    }
}
