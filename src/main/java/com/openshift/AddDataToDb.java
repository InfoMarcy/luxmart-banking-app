//package com.openshift;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.openshift.model.DatosTelefonicos;
//import com.openshift.model.Domicilio;
//import com.openshift.model.User;
//import com.openshift.model.repository.UserRepository;
//
//@Component
//public class AddDataToDb implements CommandLineRunner{
//	
//	private UserRepository  userRepository;
//	
//	
//	//constructor to load the repository
//		public AddDataToDb(UserRepository userRepository) {
//			this.userRepository = userRepository;
//		}
//
//		
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		
//		User usuario1 = new User(
//				"mercy", "secret", "Marcy", "Esmelin", "Garcia", "Lorenzo", "Marcysmerlin@hotmail.com", "5510213679", new DatosTelefonicos("0", "AT&T"),  28, "M", "developer", "casado", "GALM90001", "Dominicano", "GALM90001HNE02RRR2","1990-02-01", "Extranjero", "url",
//				 Arrays.asList(
//						    new Domicilio("Moctezuma", "91", "102", "Soto y Lerdo", "Guerrero", "Cuauhtemoc", "Ciuidad de Mexico", "Mexico", "06300")
//						 )
//				 
//				 
//				 
//				);
//		
//		User usuario2 = new User(
//				"juana", "secret", "Juana", "", "Cortes", "Rojas", "jauana@hotmail.com", "5510213979", new DatosTelefonicos("1564", "AT&T"), 25, "F", "Asisente de Credito", "Soltera", "JAN90001", "Mexicana", "JAN90001HNE02RRR2","1990-02-01", "Mexico",  "url",
//				 Arrays.asList(
//						    new Domicilio("Luis Quintero", "102", "", "Luis y Soto", "Gustavo A. Madero", "Atzacualco", "Cuidad de Mexico", "Mexico", "07300")
//						 )
//				 
//				);
//		
//		
//		
//		
//		
//		
//		// drop all hotels
//				this.userRepository.deleteAll();
//				
//				// ad ours hotel to the database
//				//create a list to hold all the values
//				List<User> users = Arrays.asList(usuario1, usuario2); 
//				// save and pass the list of hotels to the repository
//				this.userRepository.saveAll(users);
//				
//				
//	}
//}
//
