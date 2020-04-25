package pro.alexdieu.supervisor.util;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class EB {

	private EmbedBuilder eb = new EmbedBuilder();

	public EB(String title, String desc, Color color) {
		eb.setTitle(title);
		eb.setColor(color);
		eb.setDescription(desc);
	}

	public MessageEmbed build() {
		return eb.build();
	}
	
	public EB f(String title, String desc, boolean inline) {
		eb.addField(title, desc, inline);
		return this;
	}
	
	public EB bf(boolean inline) {
		eb.addBlankField(inline);
		return this;
	}
}
