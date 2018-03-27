package com.corporation.task05.controller.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.corporation.task05.controller.command.impl.GoToCommand;
import com.corporation.task05.controller.command.impl.XmlParsersCommand;

public class CommandFactory {

    private static final CommandFactory instance = new CommandFactory();

    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("parseXml", new XmlParsersCommand());
        commands.put("unknownCommand", new GoToCommand("error-page-404.jsp"));
    }

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command getCommand(HttpServletRequest request) {
        String commandParam = request.getParameter("command");
        Command command = commands.get(commandParam);
        if (command == null) {
            command = commands.get("unknownCommand");
        }
        return command;
    }

}
