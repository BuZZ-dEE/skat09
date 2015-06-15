package skat09.net;

public class ServerRunnable implements Runnable {

	@Override
	public void run() {
		Server server = new Server(8098);
		try {
			server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
