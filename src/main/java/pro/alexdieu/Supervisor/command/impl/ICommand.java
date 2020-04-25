package pro.alexdieu.supervisor.command.impl;

import net.dv8tion.jda.api.Permission;

public interface ICommand {
	
	String getName();
	String description();

	Permission[] getRequiredPermissions();
	Object perform(String args);
	
}
