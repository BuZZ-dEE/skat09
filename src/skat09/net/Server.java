package skat09.net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements IServer {
	
	private static final long serialVersionUID = -8604752504494313592L;

	protected Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public String sayHello() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
