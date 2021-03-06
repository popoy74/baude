package main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import graphicalInterface.MainView;
import stubs.PrivateStubImpl;
import stubs.PublicStubImpl;

public class User2Test {


	public static void main(String[] args) {
		try {

			PrivateStubImpl privateStub = new PrivateStubImpl("Pierre", "Bouillet", "Sophia-Antipolis", "Trap�ziste");
			PublicStubImpl publicStub = new PublicStubImpl(privateStub);
			privateStub.setPublicStub(publicStub);

			Registry registry;
			try{
				registry = LocateRegistry.getRegistry(2004);
			}catch(Exception e)
			{
				registry = LocateRegistry.createRegistry(2004);
			}
            registry.rebind(publicStub.getPersonInfo(), publicStub); //

            MainView view = new MainView(privateStub);
            view.setVisible(true);

        } catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}


