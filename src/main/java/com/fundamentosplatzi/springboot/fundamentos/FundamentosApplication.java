package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentTwoImplement;
import com.fundamentosplatzi.springboot.fundamentos.entity.Post;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.PostRepository;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.services.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class); //usa la libreria para la clase FundamentosAplication

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;
	private PostRepository postRepository;


	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentsDependency,
								  MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo, UserRepository userRepository, UserService userService, PostRepository postRepository){
		this.componentDependency = componentsDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
		this.postRepository = postRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		//ejemplosAnteriores();
		saveUserInDataBase();
		getInformatioJpqlFromUser();
		//saveWithErrorTransactional();


	}

	private void saveWithErrorTransactional(){
		User test1 = new User("test1Transactional1", "test1Transactional1@domain.com", LocalDate.now());
		User test2 = new User("test2Transactional2", "test2Transactional2@domain.com", LocalDate.now());
		User test3 = new User("test3Transactional3", "test1Transactional1@domain.com", LocalDate.now());
		User test4 = new User("test4Transactional4", "test4Transactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);

		try {
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("Esta es una exception dentro del metodo transactional "+e);
		}

		// se encierra en un try catch para poder continuar con el flujo del sistema y mostrar la lista de usuarios test en el transactional
		/*userService.saveTransactional(users);*/

		userService.getAllUsers().stream().forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transaccional: "+user));

	}

	private void getInformatioJpqlFromUser(){
		/*LOGGER.info("Usuario con el metodo findByUserEmail: "+
				userRepository.finByUserEmail("julie@domain.com")
						.orElseThrow(()->new RuntimeException("No se encontro el usuario")));

		//el .stream es para crear una coleccion del objeto y poder realizar la busqueda
		userRepository.findAndSort("user", Sort.by("id").descending()).stream()
				.forEach(user -> LOGGER.info("Usuario con el metodo sort: "+user));

		userRepository.findByName("John").stream().forEach(user -> LOGGER.info("Usuario con QueryMethod: "+user));

		LOGGER.info("Usuario con QueryMethod findByEmailAndName: "+userRepository.findByEmailAndName("daniela@domain.com","Daniela")
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%u%").stream()
				.forEach(user -> LOGGER.info("Usuario con FindByNameLike: "+user));

		userRepository.findByNameOrEmail(null, "user10@domain.com").stream()
				.forEach(user -> LOGGER.info("Usuario con FindByNameOrEmail: "+user));*/ //se comentaria para no imprimir tannto en consola

		userRepository.findByBirthDateBetween(LocalDate.of(2021, 3, 1), LocalDate.of(2021, 4, 2))
				.stream().forEach(user -> LOGGER.info("Usuario con intervalo de fechas: "+user));

		/*userRepository.findByNameLikeOrderByIdDesc("%user%").stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con Like y Ordenado Descendiente: "+user));*/

		userRepository.findByNameContainingOrderByIdDesc("user").stream()  //usando el containing no s necesario los % para alguna palabra en especifico
				.forEach(user -> LOGGER.info("Usuario encontrado con Containing y Ordenado Descendiente: "+user));

		LOGGER.info("El usuario a partir del named parameter es: "+ userRepository.
				getAllByBirthDateAndEmail(LocalDate.of(2021, 7, 21), "daniela@domain.com").
				orElseThrow(()-> new RuntimeException("No se encontro el usuario a partir del named parameter ")));
		;

		LOGGER.info("Los Post con descripcion igual son son: "+userRepository.findByPosts_Description("prueba1"));

	}

	private void saveUserInDataBase(){  //se registra la informacion del usuario
		User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 3, 20));
		User user2 = new User("Julie", "julie@domain.com", LocalDate.of(2021, 5, 21));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 7, 21));
		User user4 = new User("user4", "user4@domain.com", LocalDate.of(2021, 7, 7));
		User user5 = new User("user5", "user5@domain.com", LocalDate.of(2021, 11, 11));
		User user6 = new User("user6", "user6@domain.com", LocalDate.of(2021, 2, 25));
		User user7 = new User("user7", "user7@domain.com", LocalDate.of(2021, 3, 11));
		User user8 = new User("user8", "user8@domain.com", LocalDate.of(2021, 4, 12));
		User user9 = new User("user9", "user9@domain.com", LocalDate.of(2021, 5, 22));
		User user10 = new User("user10", "user10@domain.com", LocalDate.of(2021, 8, 3));
		User user11 = new User("user11", "user11@domain.com", LocalDate.of(2021, 1, 12));
		User user12 = new User("user12", "user12@domain.com", LocalDate.of(2021, 2, 2));

		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7,
				user8, user9, user10, user11, user12);
		list.stream().forEach(userRepository::save);

		Post post1 = new Post("prueba1", user1);
		Post post2 = new Post("prueba2", user2);
		Post post3 = new Post("prueba3", user1);
		Post post4 = new Post("prueba1", user4);
		Post post5 = new Post("prueba5", user5);

		List<Post> posts = Arrays.asList(post1, post2, post3, post4, post5);
		postRepository.saveAll(posts);





	}





	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+" - "+userPojo.getPassword());

		try{
			//error
			int value= 10/0; //se genera el error para ver el comportamiento y el registro de consola
			LOGGER.debug("El valor es: "+value); //en caso de pasar el error muestre el dato para saber
		}catch (Exception e){
			LOGGER.error("Esto es un error al dividir por cero "+e.getMessage()); //muestra en consola los diferentes tipo error que se presenten y con el getStackTrace se muestra informacion mas detallada
		}
	}

}
