package main.net;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {

	public String sayHello() throws RemoteException;
}
