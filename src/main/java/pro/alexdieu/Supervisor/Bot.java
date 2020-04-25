package pro.alexdieu.supervisor;

import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import pro.alexdieu.supervisor.command.CommandManager;
import pro.alexdieu.supervisor.event.EventHandler;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Bot {

	private String token;
	public CommandManager cManager;
	public static Bot instance;
	
	public Bot(String token) {
		instance = this;
		this.token = token;
		System.out.println("Phase d'Initialisation ... ");
		
		JDABuilder builder = new JDABuilder(token);
	    builder.setDisabledCacheFlags(EnumSet.of(CacheFlag.ACTIVITY, CacheFlag.VOICE_STATE));
	    builder.setBulkDeleteSplittingEnabled(false);
	    builder.setCompression(Compression.NONE);
	    builder.setActivity(Activity.watching("Vos données : "));
	    builder.addEventListeners(new EventHandler());
	    try {
			builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	    
	    System.out.println("En cours de post-Inistialisation");
	    System.out.println("Démarrage du manager de commandes ...");
	    cManager = new CommandManager();
	}
	
	public static void main(String[] args) {
		System.out.println("Démarrage de la phase de pré-initialization ...");
		
		if(args.length != 1) {
			System.err.println("ERREUR MAUVAISE SYNTAXE !");
			System.err.println(String.format("java -jar %s TOKEN", Bot.class.getProtectionDomain().getCodeSource().getLocation().getFile()));
			return;
		}
		
		String token = args[0];
		System.out.println("Token trouvé : " + token);
		new Bot(token);
	}
}
