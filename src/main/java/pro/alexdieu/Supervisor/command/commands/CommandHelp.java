package pro.alexdieu.supervisor.command.commands;

import java.awt.Color;

import pro.alexdieu.supervisor.Bot;
import pro.alexdieu.supervisor.command.impl.ICommand;
import pro.alexdieu.supervisor.util.EB;
import net.dv8tion.jda.api.Permission;

public class CommandHelp implements ICommand {

	@Override
	public String getName() {
		return "Aide";
	}

	@Override
	public Permission[] getRequiredPermissions() {
		return new Permission[] {};
	}
	
	@Override
	public String description() {
		return "Permet d'afficher la liste des commandes";
	}

	@Override
	public Object perform(String args) {
		
		EB eb = new EB("Commande Aide", "Voici la liste des commandes disponibles !", Color.blue);
		for(ICommand command : Bot.instance.cManager.commands) {
			eb.f(command.getName(), command.description(), false);
		}
		
		return eb.build();
	}

}
