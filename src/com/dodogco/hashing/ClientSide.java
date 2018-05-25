package com.dodogco.hashing;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientSide {
	
	public static void main(String[] args) {
		
		final String ip = "localhost", port = "2025";
		final String request = "VirtualFile";
		StringBuilder query = new StringBuilder()
				.append("rmi://")
				.append(ip).append(":")
				.append(port).append("/")
				.append(request);
		try {
			VirtualFile file = (VirtualFile) Naming.lookup(query.toString());
			System.out.printf
			("File {%s} retrieved with sucess!\n", file.toString());
			System.out.printf("Hash MD5: %s", file.getMD5());
		} catch (MalformedURLException | RemoteException | NotBoundException e)
		{
			System.err.println("Failure on retrieving file");
			e.printStackTrace();
		}
		
	}

}
