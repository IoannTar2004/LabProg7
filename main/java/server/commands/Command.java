package server.commands;

import server.modules.ServerSender;

import java.io.Serializable;

/**
 * interfaces for classes which are responsible to command
 */
public interface Command extends Serializable {
    /**
     * @param mode    have two modes: user and script. "User" is switched on when you write down the command to terminal, "script" -
     *                when commands are executed from script files.
     * @param command entered or read command (with arguments if they are required)
     * @param args    arguments are supplied to execute(). Contains some special objects.
     */
    ServerSender execute(String mode, String[] command, Object... args);
}
