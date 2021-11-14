package com.example.web.etudiant;



//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.dao.admin.UserRepository;
import com.example.dao.etudiant.*;
import com.example.entites.*;
import com.example.service.etudiant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AppController {
	private static int numPlace = 0;
	@Autowired
	private OffreRepository repo;
	@Autowired
	private ReservationRepository reservationRepo;
	@Autowired
	private InscriptionRepository inscriptionRepo;
	@Autowired
	private PostulationRepository postulationRepository;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OffreMetier offreMetier;
	@Autowired
	private EtudiantMetiersImp etudiantMetier;
	@Autowired
	private UserMetier userMetier;
	@Autowired
	private RepresentantMetier repMetier;
	@Autowired
	private AtelierMetier atelierMetier;
	@Autowired
	private ConferenceMetier conferenceMetier;
	@Autowired
	private ReservationMetier reservationMetier;
	@Autowired
	private InscriptionMetier inscriptionMetier;
	@Autowired
	private PostulationMitier PostulationMetier;

	@Autowired
	private PostMetier PostMetier;
	@Autowired
	private PostRepository postRepository;

	// private BCryptPasswordEncoder bCryptPasswordEncoder;
	String message = "";
	String messageAtelier = "";
	String messageOffre = "";
	private MultipartFile cv;

	//////////////////////////  OFFERS //////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/offres", method = RequestMethod.GET)
	public String showAlloffres(Model model, HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		System.out.println("u passed hear  and u r offre is ");
		List<Offre> offres = offreMetier.offres();
		List<User> users = new ArrayList<User>();
		for (Offre offre : offres) {

			System.out.println("representant de l'offre est : " + offre.getRep().getNom());
		}
		model.addAttribute("offres", offres);
		model.addAttribute("msg", messageOffre);
		messageOffre = "";
		return "offress";
	}

	@RequestMapping(value = "/consulter_offre", method = RequestMethod.GET)
	public String ShowOffre(Model model, Long id, HttpServletRequest httpServletRequest) {
		Offre offre = offreMetier.getOffreById(id);
		model.addAttribute("offre", offre);
		return "consulter_offre";
	}

////////////////////////////////////////// ATELIERS ////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/liste_ateliers", method = RequestMethod.GET)
	public String showAllateliers(Model model, HttpServletRequest httpServletRequest) {
		System.out.println("u passed hear  and u r offre is ");
		List<Atelier> ateliers = atelierMetier.ateliers();
		model.addAttribute("ateliers", ateliers);
		model.addAttribute("msg", messageAtelier);
		messageAtelier = "";
		for (Atelier atelier : ateliers) {
			System.out.println("atelier : " + atelier.getTitreAtelier());
			System.out.println("supervisor " + atelier.getSuperviseurAtelier().getNomSuper());
		}
		return "liste_ateliers";
	}

	@RequestMapping(value = "/consulter_atelier", method = RequestMethod.GET)
	public String ShowAtelier(Model model, Long id,  HttpServletRequest httpServletRequest) {
		Atelier atelier = atelierMetier.getAtelierById(id);
		System.out.println("supervisor " + atelier.getSuperviseurAtelier().getNomSuper());
		model.addAttribute("atelier", atelier);

		return "consulter_atelier";
	}

	////////////////////////////////////////////////////// CONFERENCES  //////////////////////////////////////////////////
	@RequestMapping(value = "/liste_conferences", method = RequestMethod.GET)
	public String showAllConferences(Model model,  HttpServletRequest httpServletRequest) {
		System.out.println("u passed hear  and u r offre is ");
		List<Conference> conferences = conferenceMetier.conferences();
		model.addAttribute("conferences", conferences);
		model.addAttribute("msg", message);
		message = "";
		for (Conference c : conferences) {
			System.out.println("conf title : " + c.getTitreConf());
			System.out.println("supervisor " + c.getSuperviseurConf().getNomSuper());
		}
		return "liste_conference";
	}

	@RequestMapping(value = "/consulter_conference", method = RequestMethod.GET)
	public String ShowConference(Model model, Long id,  HttpServletRequest httpServletRequest) {
		Conference conf = conferenceMetier.getConfById(id);
		System.out.println("supervisor " + conf.getSuperviseurConf().getNomSuper());
		model.addAttribute("c", conf);
		return "consulter_conference";
	}

//////////////////////////////////////////////// SIGN UP ////////////////////////////////////////////////////////

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("etudiant", new Etudiant());
		return "register";
	}

	@RequestMapping(value = "/process_register", method = RequestMethod.POST)
	public String processRegister(Model model, @ModelAttribute User user, @ModelAttribute Etudiant etudiant,
								  BindingResult bindingResult,  HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			return "redirect:/register";
		}
		Profil profil = new Profil(2);
		etudiant.setImg("sans_image.png");
		etudiant.setProfil(profil);
		String password = etudiant.getPassword();
		// String encodedPassword = bCryptPasswordEncoder.encode(password);
		// etudiant.setPassword(encodedPassword);
		System.out.println("ecole " + etudiant.getEcole() + " niveau " + etudiant.getNiveau() + " filiere"
				+ etudiant.getFiliere());
		Etudiant savedEtudiant = etudiantRepository.save(etudiant);
		return "redirect:/login";
	}


	/////////////////////////////////////////////////// PROFILE ///////////////////////////////////////////////////////////

	@RequestMapping(value = "/update_profil")
	public String update(Model model, @ModelAttribute Etudiant etud,  HttpServletRequest httpServletRequest) {

		Profil profil = new Profil(2);
		etud.setProfil(profil);
		etud.setCv(etud.getCv());
		etud.setImg(etud.getImg());
		System.out.println(etud);
		Etudiant savedEtudiant = etudiantRepository.save(etud);
		
		return "consulter_profil";
	}


//////////////////////////////////////////////////// STUDENTS ACTIONS /////////////////////////////////////////////////

	// the id have to be in the parameter don't forget!!!!
	@RequestMapping(value = "/Edit_profil")
	public String testUpdate(Model model, HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		Etudiant etudiant = etudiantMetier.GetEtudiantById(id);
		model.addAttribute("etudiant", etudiant);
		return "update_profil";
	}

	// the id have to be in the parameter don't forget!!!!
	@Secured(value = {"ROLE_ETUDIANT"})
	@RequestMapping(value = "/consulter_profil", method = RequestMethod.GET)
	public String ShowProfil(Model model,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		Etudiant etudiant = etudiantMetier.GetEtudiantById(id);
		List<Reservation> reservations = reservationMetier.reservationsById(id);
		for (Reservation res : reservations) {
			System.out.println("res place : " + res.getNum_place());

		}
		List<Inscription> inscriptions = inscriptionMetier.inscriptionById(id);

		List<Postulation> postulations = PostulationMetier.postulationsById(id);
		for (Postulation pos : postulations) {
			System.out.println("titre  : " + pos.getOffre().getTitre_offre());
		}
		model.addAttribute("etudiant", etudiant);
		model.addAttribute("reservations", reservations);
		model.addAttribute("inscriptions", inscriptions);
		model.addAttribute("postulations", postulations);
		return "consulter_profil";
	}

	//the id have to be in the parameter don't forget!!!!
	//tested!!!!!!!
	@RequestMapping(value = "/postuler_offre", method = RequestMethod.GET)
	public String PostulerOffre(Model model, Long id_offre, HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		System.out.println(date);
		/////////// update///////////////
		boolean exist = false;
		List<Postulation> postulations = PostulationMetier.postulationsById(id);
		for (Postulation postulation : postulations) {
			if (postulation.getId_postulation().getId_etudiant() == id
					&& postulation.getId_postulation().getId_offre() == id_offre) {
				exist = true;
			}
		}
		if (!exist) {
			Offre offre = offreMetier.getOffreById(id_offre);
			Etudiant etudiant = etudiantMetier.GetEtudiantById(id);
			Postulation postulation = new Postulation(id, id_offre, etudiant, offre, date);
			System.out.println(postulation);
			try {
				postulationRepository.save(postulation);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			messageOffre = "reussie";
			return "redirect:/offres";
		} else {
			messageOffre = "echoue";
			return "redirect:/offres";
		}
	}

	@RequestMapping(value = "/inscrire_atelier", method = RequestMethod.GET)
	public String inscrireAtelier(Model model, Long id_atelier, HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		Date d = new Date();
		String date = (d.getYear() + 1900) + "-" + (d.getMonth() + 1) + "-" + d.getDate() + " " + d.getHours() + ":"
				+ d.getMinutes() + ":" + d.getSeconds();
		System.out.println(date);
		/////////////////// update ////////////
		boolean exist = false;
		List<Inscription> inscriptions = inscriptionMetier.inscriptionById(id);
		for (Inscription inscription : inscriptions) {
			if (inscription.getId_insc().getId_etudiant() == id
					&& inscription.getId_insc().getId_atelier() == id_atelier) {
				exist = true;
				break;
			}
		}
		if (!exist) {
			Atelier atelier = atelierMetier.getAtelierById(id_atelier);
			Etudiant etudiant = etudiantMetier.GetEtudiantById(id);
			Inscription inscription = new Inscription(id, id_atelier, etudiant, atelier, date);
			System.out.println(inscription);
			try {
				inscriptionRepo.save(inscription);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			messageAtelier = "reussie";
			return "redirect:/liste_ateliers";
		} else {
			messageAtelier = "echoue";
			return "redirect:/liste_ateliers";

		}
	}


	@RequestMapping(value = "/reserver_conference")
	public String inscrireConference(Model model, Long id_conf, HttpServletRequest httpServletRequest) {
		// for test we need student id
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		boolean exist = false;
		System.out.println("reservation place for conference id conf " + id_conf);

		int place;
		List<Reservation> reservations = reservationMetier.reservations();

		///// list reservation conf de idconf /////////////////////////////////////////////////
		List<Reservation> reservationsConf = reservationMetier.reservationsConfById(id_conf);

		if (reservationsConf.isEmpty()) {
			place = 1;
			Conference conf = conferenceMetier.getConfById(id_conf);
			Etudiant etudiant = etudiantMetier.GetEtudiantById(id);
			Reservation reservation = new Reservation(id, id_conf, etudiant, conf, place);

			try {
				reservationRepo.save(reservation);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			message = "reussie";
			return "redirect:/liste_conferences";
		} else {
			/////////////////////////////////////////////////////////////////////

			for (Reservation reservation : reservationsConf) {
				if (reservation.getId_reservation().getId_etudiant() == id) {
					exist = true;
				}

			}

			if (!exist) {
				///////////////// and this /////////////////
				place = reservationsConf.get(reservationsConf.size() - 1).getNum_place() + 1;
				System.out.println("@@@@ new place number = " + place);
				///////////////////////////////////////////
				Conference conf = conferenceMetier.getConfById(id_conf);
				Etudiant etudiant = etudiantMetier.GetEtudiantById(id);
				Reservation reservation = new Reservation(id, id_conf, etudiant, conf, place);

				try {
					reservationRepo.save(reservation);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				message = "reussie";
				System.out.println("new reservation");
				return "redirect:/liste_conferences";

			} else {
				message = "echoue";
				System.out.println("u already have a place in conference !!!!!!!!");

				return "redirect:/liste_conferences";
			}
		}
	}

	@RequestMapping(value="/liste_ateliers_test",method=RequestMethod.GET)
	public String showAllateliersTest(Model model) {
		System.out.println("u passed hear  and u r offre is ");
		List<Atelier> ateliers = atelierMetier.ateliers();
		model.addAttribute("ateliers", ateliers);
		for(Atelier atelier : ateliers) {
			System.out.println("atelier : "+atelier.getTitreAtelier());
			System.out.println("supervisor "+atelier.getSuperviseurAtelier().getNomSuper());
		}
		return "liste_ateliers_style";
	}

	@RequestMapping(value="/importCv", method=RequestMethod.POST)
	public String importCv(Model model,@RequestParam(name ="cv") MultipartFile cv, HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();

		Etudiant etudiant=etudiantMetier.GetEtudiantById(id);

		java.nio.file.Path path= Paths.get("src\\main\\resources\\static\\cvs");

		try {
			String fileName=cv.getOriginalFilename();
			InputStream inputStream =cv.getInputStream();
			Files.copy(inputStream, path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
			etudiant.setCv(fileName.toLowerCase());
			Etudiant savedEtudiant = etudiantRepository.save(etudiant);
		} catch (Exception e2) {
			System.out.println("upload exeption");
		}
		return "redirect:/consulter_profil";

	}

	@RequestMapping(value="/consulterCv", method=RequestMethod.GET)
	public ResponseEntity<byte[]> getPDF1(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		Etudiant etudiant =etudiantMetier.GetEtudiantById(id);

		ResponseEntity<byte[]> response = null;

		try {
			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			String filename = etudiant.getCv();

			headers.add("content-disposition", "inline;filename=" + filename);

			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

			byte[] pdf1Bytes;

			pdf1Bytes = Files.readAllBytes(Paths.get(
					"src\\\\main\\\\resources\\\\static\\\\cvs\\"+filename));

			response = new ResponseEntity<byte[]>(pdf1Bytes, headers, HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value="/studentIndex",method=RequestMethod.GET)
	public String showIndex(Model model) {

		List<Offre> offres = offreMetier.offres();
		model.addAttribute("offres", offres);
		model.addAttribute("msg", messageOffre);
		messageOffre="";

		List<Atelier> ateliers = atelierMetier.ateliers();
		model.addAttribute("ateliers", ateliers);
		model.addAttribute("msg", messageAtelier);
		messageAtelier="";

		List<Conference> conferences = conferenceMetier.conferences();
		model.addAttribute("conferences", conferences);
		model.addAttribute("msg", message);
		message="";
		return "studentIndex";
	}

	@RequestMapping(value="/modifierImg", method=RequestMethod.POST)
	public String modifierImg(Model model,@RequestParam(name ="img") MultipartFile img, HttpServletRequest httpServletRequest) {

		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();

		Etudiant etudiant=etudiantMetier.GetEtudiantById(id);

		java.nio.file.Path path=Paths.get("src\\main\\resources\\static\\images");

		try {
			String fileName=img.getOriginalFilename();
			InputStream inputStream =img.getInputStream();
			Files.copy(inputStream, path.resolve(fileName),StandardCopyOption.REPLACE_EXISTING);
			etudiant.setImg(fileName.toLowerCase());
			Etudiant savedEtudiant = etudiantRepository.save(etudiant);
		} catch (Exception e2) {
			System.out.println("upload exeption");
		}
		return "redirect:/consulter_profil";

	}
	//student's posts with id
	
	@Secured(value = {"ROLE_ETUDIANT"})
	@RequestMapping(value = "/MyPosts", method = RequestMethod.GET)
	public String MyPosts(Model model,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		Etudiant etudiant=etudiantMetier.GetEtudiantById(id);
		List<Post> posts = postRepository.findMyPosts(etudiant);
		model.addAttribute("posts",posts);
		return "Posts";
	}
	//creer post et redirect vers page post
	
	@Secured(value = {"ROLE_ETUDIANT"})
	@RequestMapping(value = "/SavePost", method = RequestMethod.POST)
	public String SavePost(Model model,HttpServletRequest httpServletRequest, String titre, String description, String subject) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		Etudiant etudiant=etudiantMetier.GetEtudiantById(id);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		Post post = new Post();
		post.setAnswer("0");
		post.setEtudiant(etudiant);
		post.setDate_pub(date);
		post.setDescription(description);
		post.setTitre_post(titre);
		post.setSujet(subject);
		post =PostMetier.savePost(post);
		String a="redirect:/post?id="+post.getId_post();
		return a;
	}

	@Secured(value = {"ROLE_ETUDIANT"})
	@RequestMapping(value = "/deletePost", method = RequestMethod.GET)
	public String deletePost(String id,HttpServletRequest httpServletRequest){
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id1 = user.getId_user();
		Etudiant etudiant=etudiantMetier.GetEtudiantById(id1);
		Post post=postRepository.findPostById(id);
		// si le post est creer par cet etudiant connecté
		if (etudiant.getId_user()==post.getEtudiant().getId_user()){
			postRepository.deleteById(id);
			List<Post> respones = postRepository.findResponseToPost(id);
			for (int i=0;i<respones.size();i++){
				postRepository.deleteById(respones.get(i).getId_post());
			}
			return "redirect:/listePosts";
		}
		else {
			return "redirect:/listePosts";
		}
	}
	//ajouter reponse
	@Secured(value = {"ROLE_ETUDIANT","ROLE_REPRESENTANT"})
	@RequestMapping(value = "/SaveResponse", method = RequestMethod.POST)
	public String SaveResponse(Model model,HttpServletRequest httpServletRequest, String description, String post) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		Etudiant etudiant=etudiantMetier.GetEtudiantById(id);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		//id string
		Post postt = postRepository.findPostById(post);
		Post response = new Post();
		response.setAnswer(postt.getId_post());
		response.setEtudiant(etudiant);
		response.setDate_pub(date);
		System.out.println(response.getDate_pub());
		response.setDescription(description);
		response.setSujet(postt.getSujet());
		System.out.println(response.getSujet());
		response = PostMetier.savePost(response);
		String a="redirect:/post?id="+postt.getId_post();
		return a;
	}


	@Secured(value = {"ROLE_ETUDIANT"})
	@RequestMapping(value = "/subject", method = RequestMethod.GET)
	public String SubjectListe(Model model) {
		List<String> subjects = postRepository.findAllSubjects();
		model.addAttribute("subjects",subjects);
		ArrayList<String> cnt0 =new ArrayList<String>(subjects.size());
		List<count> cnt1 = new ArrayList<count>(subjects.size());
		for (int i=0;i<subjects.size();i++){
			count cnt2 = new count();
			cnt2.setSjt(subjects.get(i));
			cnt0.add(subjects.get(i));
			int a= postRepository.findCountSubject(subjects.get(i));
			cnt0.add(Integer.toString(a));
			cnt2.setNombre(a);
			cnt1.add(cnt2);
		}
		model.addAttribute("cnt",cnt1);
		return "subject";
	}


	@Secured(value = {"ROLE_ETUDIANT"})
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String Post(Model model, String id) {
		Post post = postRepository.findPostById(id);
		model.addAttribute("post",post);
		List<Post> response = postRepository.findResponseToPost(id);
		model.addAttribute("response",response);
		model.addAttribute("number",response.size());
		return "Post";
	}


	//when he calls this
	@Secured(value = {"ROLE_ETUDIANT"})
	@RequestMapping(value = "/Search", method = RequestMethod.POST)
	public String Search(Model model,HttpServletRequest httpServletRequest, String desc,@RequestParam("page") Optional<Integer> page,
							 @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(7);
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		Etudiant etudiant=etudiantMetier.GetEtudiantById(id);
		List<Post>  books = postRepository.findPostBySearch(desc);
		System.out.println(books);
		Page<Post> bookPage = PostMetier.findPaginated(PageRequest.of(currentPage - 1, pageSize),books);
		model.addAttribute("bookPage", bookPage);
		model.addAttribute("books", books);
		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "Posts";
	}



	@Secured(value = {"ROLE_ETUDIANT"})
	@RequestMapping(value = "/SearchMine", method = RequestMethod.POST)
	public String SearchMine(Model model,HttpServletRequest httpServletRequest, String desc,@RequestParam("page") Optional<Integer> page,
							 @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(7);
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		Etudiant etudiant=etudiantMetier.GetEtudiantById(id);
		List<Post>  books = postRepository.findMinePostBySearch(desc,etudiant);
		Page<Post> bookPage = PostMetier.findPaginated(PageRequest.of(currentPage - 1, pageSize),books);
		model.addAttribute("bookPage", bookPage);
		model.addAttribute("books", books);
		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "listePosts";
	}





	@Secured(value = {"ROLE_ETUDIANT"})
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public String Posts(Model model, String sujet,HttpServletRequest httpServletRequest,@RequestParam("page") Optional<Integer> page,
						@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(7);
		List<Post>  books = postRepository.findPostsBySubject(sujet);
		Page<Post> bookPage = PostMetier.findPaginated(PageRequest.of(currentPage - 1, pageSize),books);
		model.addAttribute("bookPage", bookPage);
		model.addAttribute("books", books);
		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "Posts";
	}





	@Secured(value = {"ROLE_ETUDIANT"})
	@RequestMapping(value = "/lastPosts", method = RequestMethod.GET)
	public String lastPosts(Model model,HttpServletRequest httpServletRequest,@RequestParam("page") Optional<Integer> page,
							 @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(7);
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		Etudiant etudiant=etudiantMetier.GetEtudiantById(id);
		List<Post>  books = postRepository.findOtherPostBySearch(etudiant);
		System.out.println(books);
		Page<Post> bookPage = PostMetier.findPaginated(PageRequest.of(currentPage - 1, pageSize),books);
		model.addAttribute("bookPage", bookPage);
		model.addAttribute("books", books);
		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "lastPosts";
	}














	@RequestMapping(value = "/listePosts", method = RequestMethod.GET)
	public String listePosts(
			Model model,HttpServletRequest httpServletRequest,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(7);
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securityContext.getAuthentication().getName();
		User user = repo.findRepresantantByName(username);
		Long id = user.getId_user();
		Etudiant etudiant=etudiantMetier.GetEtudiantById(id);
		List<Post> books = postRepository.findMyPosts(etudiant);
		Page<Post> bookPage = PostMetier.findPaginated(PageRequest.of(currentPage - 1, pageSize),books);
		model.addAttribute("bookPage", bookPage);
		model.addAttribute("books", books);
		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "listePosts";
	}






//http://localhost:8080/listePosts?page=2&size=2
}
