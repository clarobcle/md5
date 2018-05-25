package com.dodogco.hashing;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerSide {
	
	public static void main(String[] args) {
		
		final int port = 2025;
		
		try
		{
			VirtualFile file = new VirtualFileImp("res/test.txt");
			Registry registry = LocateRegistry.createRegistry(port);
			registry.rebind("VirtualFile", file);
			System.out.printf
			("File {%s} registered with success!", file.toString());
			
		} catch (RemoteException e) {
			System.err.println("Failure on registering file:");
			e.printStackTrace();
		}
		
	}

}
