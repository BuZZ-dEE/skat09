package main.net;

/**
 * Server runnable
 * 
 * @since 16.06.2015 00:23:08
 * 
 * @author Sebastian Schlatow <ssc@openmailbox.org>
 *
 */
public class ServerRunnable implements Runnable {

	@Override
	public void run() {
		Server server = new Server(8098);
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
