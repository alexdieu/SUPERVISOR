package pro.alexdieu.supervisor.command;

import java.util.ArrayList;
import java.util.List;

import pro.alexdieu.supervisor.command.commands.CommandHelp;
import pro.alexdieu.supervisor.command.impl.ICommand;
import pro.alexdieu.supervisor.exception.CommandNotFoundException;
import pro.alexdieu.supervisor.exception.MissingPermissionException;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class CommandManager {

	public List<ICommand> commands = new ArrayList<ICommand>();
	
	public CommandManager() {
		this.registerCommand(new CommandHelp());
	}
	
	public void registerCommand(ICommand command) {
		commands.add(command);
	}
	
	public Object performCommand(String command, TextChannel textChannel, Member member) throws CommandNotFoundException, MissingPermissionException {
		
		for(ICommand cmd : commands) {
			if(cmd.getName().contentEquals(command)) {
				if(cmd.getRequiredPermissions().length > 0) {
					if(!member.hasPermission(cmd.getRequiredPermissions())) {
						throw new MissingPermissionException("Maquant : " + cmd.getRequiredPermissions());
					}
				}
				return cmd.perform(command);
			}
		}
		
		throw new CommandNotFoundException(command + " inconnue ");
	}
}
