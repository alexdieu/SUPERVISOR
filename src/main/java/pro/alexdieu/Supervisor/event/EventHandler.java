package pro.alexdieu.supervisor.event;

import java.awt.Color;

import pro.alexdieu.supervisor.Bot;
import pro.alexdieu.supervisor.exception.CommandNotFoundException;
import pro.alexdieu.supervisor.exception.MissingPermissionException;
import pro.alexdieu.supervisor.util.EB;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventHandler extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String msg = e.getMessage().getContentRaw();
		if (msg.startsWith("!")) {
			try {
				Object rep = Bot.instance.cManager.performCommand(msg.substring(1), e.getChannel(), e.getMember());
				if (rep != null) {
					if (rep instanceof String)
						e.getMessage().getChannel().sendMessage((String) rep).queue();
					else if(rep instanceof MessageEmbed)
						e.getMessage().getChannel().sendMessage((MessageEmbed) rep).queue();
				}
			} catch (CommandNotFoundException ex) {
				e.getMessage().getChannel()
						.sendMessage(new EB("Erreur :'(", "La commande n'existe pas !", Color.ORANGE).build()).queue();
			} catch (MissingPermissionException e1) {
				e.getMessage().getChannel()
				.sendMessage(new EB("Erreur :'(", "Vous n'avez pas les permissions requises pour effectuer cette commande !", Color.RED).build()).queue();			}
		}
	}

}
