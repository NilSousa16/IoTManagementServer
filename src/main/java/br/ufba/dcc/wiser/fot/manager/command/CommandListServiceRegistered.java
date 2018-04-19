package br.ufba.dcc.wiser.fot.manager.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.Service;
import br.ufba.dcc.wiser.fot.manager.service.ServiceDBService;

/**
 * Class responsible for listing the services of a gateway retrieving its
 * information via karaf.
 *
 * @author Nilson Rodrigues Sousa
 */
@Command(scope = "wiser", name = "list-service-registered")
public class CommandListServiceRegistered implements Action {
	//LISTAR TODOS OS SERVIÇOS REGISTRADOS NO BD

	private ServiceDBService serviceDBService = null;

	public void setServiceDBService(ServiceDBService serviceDBService) {
		this.serviceDBService = serviceDBService;
	}

	/**
	 * Returns the services that have record in the database.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param session
	 *            CommandSession
	 * @return Object - Reply required by karaf
	 */
	public Object execute(CommandSession session) throws Exception {

		try {
			List<Service> service = new ArrayList<Service>();
			
			service.addAll(serviceDBService.getListService());

			System.out.println("\n---------------------------------------------------");
			System.out.println("REPORT SERVICE REGISTERED");
			System.out.println("---------------------------------------------------");

			if (service != null && !(service.isEmpty())) {
				for (Service svc : service) {
					System.out.println("Id: "+ svc.getId() +" NameService: " + svc.getNameService());
				}
			} else {
				System.out.println("\nNo Service stored.\n");
			}

			return null;
		} catch (Exception e) {
			System.out.println("Error in searching for stored services."); 
			e.printStackTrace();
		}
		
		System.out.println("---------------------------------------------------\n");
		return null;
	}

}
