 package com.example.web.admin;




import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.admin.AtelierRepository;
import com.example.dao.admin.ConferenceRepository;
import com.example.dao.admin.EntrepriseRepository;
import com.example.dao.admin.ProfilRepository;
import com.example.dao.admin.RepresentantRepository;
import com.example.dao.admin.SponsorRepository;
import com.example.dao.admin.SuperviseurRepository;
import com.example.dao.admin.UserRepository;
import com.example.dao.etudiant.EtudiantRepository;
import com.example.dao.etudiant.InscriptionRepository;
import com.example.dao.etudiant.OffreRepository;
import com.example.dao.etudiant.PostulationRepository;
import com.example.dao.etudiant.ReservationRepository;
import com.example.entites.Atelier;
import com.example.entites.Conference;
import com.example.entites.Entreprise;
import com.example.entites.Inscription;
import com.example.entites.Postulation;
import com.example.entites.Profil;
import com.example.entites.Representant;
import com.example.entites.Reservation;
import com.example.entites.Sponsor;
import com.example.entites.Superviseur;
import com.example.entites.User;
import com.example.service.admin.ConfMetierImpl;
import com.example.service.admin.EntrepriseMetierImpl;
import com.example.service.admin.IAtelierMetier;
import com.example.service.admin.IConfMetier;
import com.example.service.admin.IEntrepriseMetier;
import com.example.service.admin.ISponsorMetier;
import com.example.service.admin.ISuperviseurMetier;

@Controller
public class AdminController {
		@Autowired
		public EntrepriseRepository entrepriseRepository;
		@Autowired
		public IEntrepriseMetier entrepriseMetier;	
		@Autowired
		public ISuperviseurMetier superviseurMetrier;
		@Autowired
		public SuperviseurRepository superviseurRep;
		@Autowired
		public ConferenceRepository confRep;
		@Autowired
		public IConfMetier confMetier;	
		@Autowired
		public AtelierRepository atelierRep;
		@Autowired
		public IAtelierMetier atelierMetier;	
		@Autowired
		public SponsorRepository sponsorRep;	
		@Autowired
		public ISponsorMetier sponsorMetier;
		@Autowired
		public RepresentantRepository representantrep;
		@Autowired
		public UserRepository userRep;
		@Autowired
		public ProfilRepository profilRep;
		@Autowired
		public ReservationRepository reservationRep;
		@Autowired
		public InscriptionRepository inscRep;
		@Autowired
		public PostulationRepository postulationRep;
	    @Autowired
	    public OffreRepository repo;
	    @Autowired
	    public EtudiantRepository etudiantRep;
	
	    
@RequestMapping("/")
public String index() {
	return "index";
	
}

@Secured(value={"ROLE_ADMIN"})
@RequestMapping("/adminIndex")
public String admin(HttpServletRequest httpServletRequest,Model model)
{
	HttpSession httpSession=httpServletRequest.getSession();
	SecurityContext securityContext=(SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
	String username=securityContext.getAuthentication().getName();
	User user=userRep.findUserByLogin(username);
	String name=user.getPrenom();
	model.addAttribute("nom",name);
	
	return "AdminInterface";
	
}

@Secured(value={"ROLE_ADMIN"})
@RequestMapping("/dashboard")
public String dashboard(Model model) {
		List<User> listUsers=userRep.findAll();
		model.addAttribute("listUsers", listUsers);
		int nbr=atelierRep.nbrAteliers();
		model.addAttribute("nbr", nbr);
		int nbrC=confRep.nbrConferences();
		model.addAttribute("nbrC", nbrC);
		int nbrO=repo.countOffre();
		model.addAttribute("nbrO", nbrO);
		int nbrU=userRep.nbrUsers();
		model.addAttribute("nbrU", nbrU);
		List<Atelier> listAteliers=atelierRep.findAll();
		model.addAttribute("listAteliers", listAteliers);
		List<Inscription> listInsc=inscRep.findAll();	
		model.addAttribute("listInsc", listInsc);
		List<Reservation> listReserv=reservationRep.findAll();
		model.addAttribute("listReserv", listReserv);
		List<Postulation> listPost=postulationRep.findAll();
		model.addAttribute("listPost", listPost);
		Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("Postulations", postulationRep.countPostulation());
        graphData.put("Inscriptions", inscRep.countInsc());
        graphData.put("Réservation", reservationRep.countReservation());
        model.addAttribute("chartData", graphData);
        Map<String, Integer> graphData2 = new TreeMap<>();
        graphData2.put("Etudiants", etudiantRep.countEtudiants());
        graphData2.put("Superviseurs", superviseurRep.countSuperviseur());
        graphData2.put("Sponsors", sponsorRep.countSponsor());
        graphData2.put("Enreprises", entrepriseRepository.nbrEtr());
        graphData2.put("Représentants", representantrep.countRep());	
        model.addAttribute("chartData2", graphData2);
	return "dashboard";
	
}
		
/////////////////////Entreprise//////////////////////////
////////////////////////////////////////////////////////

@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value = "/listEntreprises",method=RequestMethod.GET)
	  public String listeEntreprises(Model model,
	  @RequestParam(name ="page",defaultValue ="0") int p,
	  @RequestParam(name ="size",defaultValue ="4") int s,
	  @RequestParam(name="searchBy",defaultValue ="nomE") String searchBy,
	  @RequestParam(name="mc",defaultValue ="") String mc,HttpServletRequest httpServletRequest)
	{ 
	HttpSession httpSession=httpServletRequest.getSession();
	SecurityContext securityContext=(SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
	String username=securityContext.getAuthentication().getName();
	User user=userRep.findUserByLogin(username);
	String name=user.getPrenom();
	model.addAttribute("nom",name);
		if(searchBy.equals("nomE")) {
			 Page <Entreprise> pageEntreprise=entrepriseRepository.chercherByNom("%"+mc+"%", PageRequest.of(p, s));
			  model.addAttribute("pageEntreprise", pageEntreprise.getContent());
			  int[] totalPages=new int[pageEntreprise.getTotalPages()]; 
			  model.addAttribute("totalPages",totalPages); 
			  model.addAttribute("pageCourante", p);
			  model.addAttribute("size", s); 
			  model.addAttribute("motCle", mc);
			  model.addAttribute("searchBy", searchBy);
					  return "listeEntreprises";
		}
		else if(searchBy.equals("activiteE")) {
			Page <Entreprise> pageEntreprise=entrepriseRepository.chercherByActivite("%"+mc+"%", PageRequest.of(p, s));
			  model.addAttribute("pageEntreprise", pageEntreprise.getContent()); int[] totalPages=new
					  int[pageEntreprise.getTotalPages()]; model.addAttribute("totalPages",
					  totalPages); model.addAttribute("pageCourante", p);
					  model.addAttribute("size", s); model.addAttribute("motCle", mc); 
					  model.addAttribute("searchBy", searchBy);
					  return "listeEntreprises";
		}
		else if(searchBy.equals("adresseE")) {
			Page <Entreprise> pageEntreprise=entrepriseRepository.chercherByVille("%"+mc+"%", PageRequest.of(p, s));
			  model.addAttribute("pageEntreprise", pageEntreprise.getContent()); int[] totalPages=new
					  int[pageEntreprise.getTotalPages()]; model.addAttribute("totalPages",
					  totalPages); model.addAttribute("pageCourante", p);
					  model.addAttribute("size", s); model.addAttribute("motCle", mc);
					  model.addAttribute("searchBy", searchBy);
					  return "listeEntreprises";
		}
		 return "listeEntreprises";
	}
	 
	
@Secured(value={"ROLE_ADMIN"})	//apres submit dans le formulaire d'ajout
	@RequestMapping(value = "/saveEntreprise", method=RequestMethod.POST)
	public String saveEntreprise(Model model,
			@RequestParam(name="nomE") String nom,
			@RequestParam(name="activiteE") String activite,
			@RequestParam(name="adresseE") String adresse,
			@RequestParam(name="emailE") String email,
			@RequestParam(name="logoE") MultipartFile logo) throws IOException {
		Entreprise ent =entrepriseMetier.ajouterEntreprise(nom,activite,adresse,email,logo);
		entrepriseMetier.saveEntreprise(ent);
		return "redirect:/listEntreprises";
	}	
	
	@Secured(value={"ROLE_ADMIN"}) //apres submit et modification
	@RequestMapping(value = "/saveEditEntreprise", method=RequestMethod.POST)
	public String saveEditEntreprise(Model model,
			@RequestParam(name="idEntreprise") Long id,
			@RequestParam(name="nomE") String nom,
			@RequestParam(name="activiteE") String activite,
			@RequestParam(name="adresseE") String adresse,
			@RequestParam(name="emailE") String email,
			@RequestParam(name="logoE") MultipartFile logo) throws IOException {
		Entreprise ent =entrepriseMetier.ajouterEntreprise(nom,activite,adresse,email,logo);
		ent.setIdEntreprise(id);
		entrepriseMetier.saveEntreprise(ent);
		return "redirect:/listEntreprises";
	}	
	
@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/deleteEntreprise")
	public String deleteEntrprise(Long id,
			@RequestParam(name ="page",defaultValue ="0") int p,
			@RequestParam(name ="size",defaultValue ="6") int s,
			@RequestParam(name="mc",defaultValue ="") String mc) {
		entrepriseMetier.deleteEntreprise(id);
		return "redirect:/listEntreprises?page="+p+"&size="+s+"&motCle="+mc;
		
	}
	
	
@Secured(value={"ROLE_ADMIN"}) //afficher le formulaire plein.
	@RequestMapping(value = "/editEntreprise",method=RequestMethod.GET)
	public String editEntreprise(Model model,@RequestParam Long id) {
		Entreprise p=entrepriseMetier.editEntreprise(id);
		model.addAttribute("entreprise",p);
		return "EditEntrepriseForm";
	}
	
@Secured(value={"ROLE_ADMIN"})	//on click sur le bouton "ajouter"
	@RequestMapping(value = "/ajouter",method=RequestMethod.GET)
	public String  ajouterEform(Model model) {
		model.addAttribute("entreprise", new Entreprise());
		return "AjouterEntrepriseForm";
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Superviseur/////////////////////////////////////////////////////
@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value = "/listSuperviseurs",method=RequestMethod.GET)
	  public String listSuperviseurs(Model model,
	  @RequestParam(name ="page",defaultValue ="0") int p,
	  @RequestParam(name ="size",defaultValue ="9") int s,
	  @RequestParam(name="mc",defaultValue ="") String mc,HttpServletRequest httpServletRequest)
	{ 

	HttpSession httpSession=httpServletRequest.getSession();
	SecurityContext securityContext=(SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
	String username=securityContext.getAuthentication().getName();
	User user=userRep.findUserByLogin(username);
	String name=user.getPrenom();
	model.addAttribute("nom",name);
	Page <Superviseur> pageSuperviseur=superviseurMetrier.chercher("%"+mc+"%", p, s);
	  model.addAttribute("pageSuperviseur", pageSuperviseur.getContent()); int[] totalPages=new
	  int[pageSuperviseur.getTotalPages()]; model.addAttribute("totalPages",
	  totalPages); model.addAttribute("pageCourante", p);
	  model.addAttribute("size", s);
	  model.addAttribute("motCle", mc); 	
	  return "listeSuperviseur";
	  
	}
	 
	
@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value = "/saveSuperviseur", method=RequestMethod.POST)
	public String saveSuperviseur(Model model,@Valid Superviseur superviseur, BindingResult bindingResult,
			@RequestParam(name ="page",defaultValue ="0") int p,
			@RequestParam(name ="size",defaultValue ="9") int s,
			@RequestParam(name="mc",defaultValue ="") String mc) {
		if(bindingResult.hasErrors()) {
			return "EditSuperviseur";
		}
		superviseurMetrier.saveSuperviseur(superviseur);
		return "redirect:/listSuperviseurs?page="+p+"&size="+s+"&motCle="+mc;
	}	
@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/deleteSuperviseur")
	public String deleteSuperviseur(Long id,
			@RequestParam(name ="page",defaultValue ="0") int p,
			@RequestParam(name ="size",defaultValue ="9") int s,
			@RequestParam(name="mc",defaultValue ="") String mc) {
		superviseurMetrier.deleteSuperviseur(id);
		return "redirect:/listSuperviseurs?page="+p+"&size="+s+"&motCle="+mc;
		
	}
@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value = "/editSuperviseur",method=RequestMethod.GET)
	public String editSuperviseur(Model model,@RequestParam Long id) {
		Superviseur s=superviseurMetrier.editSuperviseur(id);
		model.addAttribute("superviseur",s);
		return "EditSuperviseur";
	}
@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value = "/ajouterSuperviseur",method=RequestMethod.GET)
	public String  ajouterSform(Model model) {
		model.addAttribute("s", new Superviseur());
		return "AjouterSuperviseur";
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Conferences/////////////////////////////////////////////////////

@Secured(value={"ROLE_ADMIN"})
			@RequestMapping(value = "/listConference",method=RequestMethod.GET)
			public String listConference(Model model,
			@RequestParam(name ="page",defaultValue ="0") int p,
			@RequestParam(name ="size",defaultValue ="4") int s,
			@RequestParam(name="mc",defaultValue ="") String mc,HttpServletRequest httpServletRequest)
			{ 
			
				HttpSession httpSession=httpServletRequest.getSession();//prend la session cree
				SecurityContext securityContext=(SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");//prend les attributs de session
				String username=securityContext.getAuthentication().getName();
				User user=userRep.findUserByLogin(username);
				String name=user.getPrenom();
				model.addAttribute("nom",name);
			Page <Conference> pageConference=confMetier.chercher("%"+mc+"%", p, s);
			model.addAttribute("pageConference", pageConference.getContent()); 
			
			int[] totalPages=new int[pageConference.getTotalPages()]; 
			model.addAttribute("totalPages",totalPages);
			model.addAttribute("pageCourante", p);
			model.addAttribute("size", s); model.addAttribute("motCle", mc);
			
			return "listeConference";
			
			}
			
			
@Secured(value={"ROLE_ADMIN"})
			@RequestMapping(value = "/saveConference", method=RequestMethod.POST)
			public String saveConferencer(Model model,@Valid Conference conference, BindingResult bindingResult,
					@RequestParam(name ="page",defaultValue ="0") int p,
					@RequestParam(name ="size",defaultValue ="4") int s,
					@RequestParam(name="superviseur",defaultValue ="") Long superviseur,
					@RequestParam(name="mc",defaultValue ="") String mc) 
			{
				Superviseur sp =superviseurRep.getOne(superviseur);
				conference.setSuperviseurConf(sp);
				if(bindingResult.hasErrors()) {
					List<Superviseur> ls=superviseurRep.findAll();
					model.addAttribute("lss", ls);
					return "AjouterConference";
				}
				
				confMetier.saveConference(conference);
				return "redirect:/listConference?page="+p+"&size="+s+"&motCle="+mc;
			}	
			
			
			@RequestMapping(value = "/saveEditConference", method=RequestMethod.POST)
			public String saveEditConferencer(Model model,@Valid Conference conference, BindingResult bindingResult,
					@RequestParam(name ="page",defaultValue ="0") int p,
					@RequestParam(name ="size",defaultValue ="4") int s,
					@RequestParam(name="superviseur",defaultValue ="") Long superviseur,
					@RequestParam(name="mc",defaultValue ="") String mc) 
			{
				Superviseur spr =superviseurRep.getOne(superviseur);
				conference.setSuperviseurConf(spr);
				if(bindingResult.hasErrors()) {
					List<Superviseur> listSuper=superviseurRep.findAll();
					model.addAttribute("listSuper", listSuper);
					return "EditConference";
				}
				
				confMetier.saveConference(conference);
				return "redirect:/listConference?page="+p+"&size="+s+"&motCle="+mc;
			}	
			@Secured(value={"ROLE_ADMIN"})
			@RequestMapping(value="/deleteConference")
			public String deleteConference(Long id,
					@RequestParam(name ="page",defaultValue ="0") int p,
					@RequestParam(name ="size",defaultValue ="4") int s,
					@RequestParam(name="mc",defaultValue ="") String mc) {
				confMetier.deleteConference(id);
				return "redirect:/listConference?page="+p+"&size="+s+"&motCle="+mc;
				
			}
			@Secured(value={"ROLE_ADMIN"})
			@RequestMapping(value = "/editConference",method=RequestMethod.GET)
			public String editConference(Model model,@RequestParam Long id) {
				Conference confedit=confMetier.editConference(id);
				List<Superviseur> listSuper=superviseurRep.findAll();
				model.addAttribute("listSuper", listSuper);
				model.addAttribute("conference",confedit);
				return "EditConference";
			}
			
			@Secured(value={"ROLE_ADMIN"})
			@RequestMapping(value = "/ajouterConference",method=RequestMethod.GET)
			public String  ajouterCform(Model model) {
				List<Superviseur> ls=superviseurRep.findAll();
				model.addAttribute("lss", ls);
				model.addAttribute("conference", new Conference());
				return "AjouterConference";
			}
			

///////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Sponsor/////////////////////////////////////////////////////

			@Secured(value={"ROLE_ADMIN"})
			@RequestMapping(value = "/listSponsor",method=RequestMethod.GET)
			  public String listSponsor(Model model,
			  @RequestParam(name ="page",defaultValue ="0") int p,
			  @RequestParam(name ="size",defaultValue ="4") int s,
			  @RequestParam(name="searchBy",defaultValue ="nomE") String searchBy,
			  @RequestParam(name="mc",defaultValue ="") String mc,HttpServletRequest httpServletRequest)
			{ 

				HttpSession httpSession=httpServletRequest.getSession();
				SecurityContext securityContext=(SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
				String username=securityContext.getAuthentication().getName();
				User user=userRep.findUserByLogin(username);
				String name=user.getPrenom();
				model.addAttribute("nom",name);
			Page <Sponsor> pageSponsor=sponsorMetier.chercher("%"+mc+"%", p, s);
			model.addAttribute("pageSponsor", pageSponsor.getContent());
			int[] totalPages=new int[pageSponsor.getTotalPages()]; 
			  model.addAttribute("totalPages",totalPages); 
			  model.addAttribute("pageCourante", p);
			  model.addAttribute("size", s); 
			  model.addAttribute("motCle", mc); 	
			  return "listeSponsor";
			  
			}
			 
			
			@Secured(value={"ROLE_ADMIN"})
			@RequestMapping(value = "/saveSponsor", method=RequestMethod.POST)
			public String saveSuperviseur(Model model,
					@RequestParam(name ="nomSponsor") String nomSponsor,
					@RequestParam(name ="aproposSponsor") String aproposSponsor,
					@RequestParam(name ="page",defaultValue ="0") int p,
					@RequestParam(name ="size",defaultValue ="9") int s,
					@RequestParam(name="mc",defaultValue ="") String mc,
					@RequestParam(name="logoSponsor") MultipartFile logo) {
				Sponsor sponsor=sponsorMetier.ajouterSponsor(nomSponsor, aproposSponsor, logo);
				sponsorMetier.saveSponsor(sponsor);
				return "redirect:/listSponsor?page="+p+"&size="+s+"&motCle="+mc;
			}	
			
			@Secured(value={"ROLE_ADMIN"})
			@RequestMapping(value = "/saveEditSponsor", method=RequestMethod.POST)
			public String saveEditSuperviseur(Model model,
					@RequestParam(name ="idSponsor") Long id ,
					@RequestParam(name ="nomSponsor") String nomSponsor,
					@RequestParam(name ="aproposSponsor") String aproposSponsor,
					@RequestParam(name ="page",defaultValue ="0") int p,
					@RequestParam(name ="size",defaultValue ="9") int s,
					@RequestParam(name="mc",defaultValue ="") String mc,
					@RequestParam(name="logoSponsor") MultipartFile logo) {
				
				Sponsor sponsor=sponsorMetier.ajouterSponsor(nomSponsor, aproposSponsor, logo);
				sponsor.setIdSponsor(id);
				sponsorMetier.saveSponsor(sponsor);
				return "redirect:/listSponsor?page="+p+"&size="+s+"&motCle="+mc;
			}	
			
			@Secured(value={"ROLE_ADMIN"})
			@RequestMapping(value="/deleteSponsor")
			public String deleteSponsor(Long id,
					@RequestParam(name ="page",defaultValue ="0") int p,
					@RequestParam(name ="size",defaultValue ="9") int s,
					@RequestParam(name="mc",defaultValue ="") String mc) {
				sponsorMetier.deleteSponsor(id);
				return "redirect:/listSponsor?page="+p+"&size="+s+"&motCle="+mc;
				
			}
			@Secured(value={"ROLE_ADMIN"})
			@RequestMapping(value = "/editSponsor",method=RequestMethod.GET)
			public String editSponsor(Model model,@RequestParam Long id) {
				Sponsor s=sponsorMetier.editSponsor(id);
				model.addAttribute("sponsor",s);
				return "EditSponsor";
			}
			
			@Secured(value={"ROLE_ADMIN"})
			@RequestMapping(value = "/ajouterSponsor",method=RequestMethod.GET)
			public String  ajouterSpform(Model model) {
				model.addAttribute("s", new Sponsor());
				return "AjouterSponsor";
			}
			
		///////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////Ateliers/////////////////////////////////////////////////////

			@Secured(value={"ROLE_ADMIN"})
		@RequestMapping(value = "/listAtelier",method=RequestMethod.GET)
		public String listAtelier(Model model,
		@RequestParam(name ="page",defaultValue ="0") int p,
		@RequestParam(name ="size",defaultValue ="4") int s,
		@RequestParam(name="mc",defaultValue ="") String mc,HttpServletRequest httpServletRequest)
		{ 
		
				HttpSession httpSession=httpServletRequest.getSession();
				SecurityContext securityContext=(SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
				String username=securityContext.getAuthentication().getName();
				User user=userRep.findUserByLogin(username);
				String name=user.getPrenom();
				model.addAttribute("nom",name);
			Page <Atelier> pageAtelier=atelierMetier.chercher("%"+mc+"%", p, s);
			model.addAttribute("pageAtelier", pageAtelier.getContent()); 
			
			int[] totalPages=new int[pageAtelier.getTotalPages()]; 
			model.addAttribute("totalPages",totalPages);
			model.addAttribute("pageCourante", p);
			model.addAttribute("size", s); model.addAttribute("motCle", mc);
		
		return "listeAtelier";
		
		}
		
		
			@Secured(value={"ROLE_ADMIN"})
		@RequestMapping(value = "/saveAtelier", method=RequestMethod.POST)
		public String saveAtelierr(Model model,@Valid Atelier atelier, BindingResult bindingResult,
		@RequestParam(name ="page",defaultValue ="0") int p,
		@RequestParam(name ="size",defaultValue ="4") int s,
		@RequestParam(name="superviseur",defaultValue ="") Long superviseur,
		@RequestParam(name="mc",defaultValue ="") String mc) 
		{
			Superviseur sp =superviseurRep.getOne(superviseur);
			atelier.setSuperviseurAtelier(sp);
			if(bindingResult.hasErrors()) {
				List<Superviseur> ls=superviseurRep.findAll();
				model.addAttribute("lists", ls);
				return "AjouterAtelier";
			}
			atelierMetier.saveAtelier(atelier);
			return "redirect:/listAtelier?page="+p+"&size="+s+"&motCle="+mc;
		}	
			
		
			@Secured(value={"ROLE_ADMIN"})
		@RequestMapping(value = "/saveEditAtelier", method=RequestMethod.POST)
		public String saveEditAtelierr(Model model,@Valid Atelier atelier, BindingResult bindingResult,
		@RequestParam(name ="page",defaultValue ="0") int p,
		@RequestParam(name ="size",defaultValue ="4") int s,
		@RequestParam(name="superviseur",defaultValue ="") Long superviseur,
		@RequestParam(name="mc",defaultValue ="") String mc) 
		{
			Superviseur sp =superviseurRep.getOne(superviseur);
			atelier.setSuperviseurAtelier(sp);
			if(bindingResult.hasErrors()) {
				List<Superviseur> ls=superviseurRep.findAll();
				model.addAttribute("ls", ls);
				return "EditAtelier";
			}
			atelierMetier.saveAtelier(atelier);
			return "redirect:/listAtelier?page="+p+"&size="+s+"&motCle="+mc;
		}	
			
			@Secured(value={"ROLE_ADMIN"})
		@RequestMapping(value="/deleteAtelier")
		public String deleteAtelier(Long id,
		@RequestParam(name ="page",defaultValue ="0") int p,
		@RequestParam(name ="size",defaultValue ="4") int s,
		@RequestParam(name="mc",defaultValue ="") String mc) {
		atelierMetier.deleteAtelier(id);
		return "redirect:/listAtelier?page="+p+"&size="+s+"&motCle="+mc;
		
		}
			@Secured(value={"ROLE_ADMIN"})
		@RequestMapping(value = "/editAtelier",method=RequestMethod.GET)
		public String editAtelier(Model model,@RequestParam Long id) {
		Atelier atelier=atelierMetier.editAtelier(id);
		List<Superviseur> ls=superviseurRep.findAll();
		model.addAttribute("ls", ls);
		model.addAttribute("atelier",atelier);
		return "EditAtelier";
		}
		
			@Secured(value={"ROLE_ADMIN"})
		@RequestMapping(value = "/ajouterAtelier",method=RequestMethod.GET)
		public String  ajouterAform(Model model) {
		List<Superviseur> ls=superviseurRep.findAll();
		model.addAttribute("lists", ls);
		model.addAttribute("atelier", new Atelier());
		return "AjouterAtelier";

		}
		
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////Representant//////////////////////////////////////////////////////////////////
			@Secured(value={"ROLE_ADMIN"})
		@RequestMapping(value = "/saveRep", method=RequestMethod.POST)
		public String saveRepresentant(Model model,@Valid User user, BindingResult bindingResult,
				@RequestParam(name="entreprise",defaultValue ="") Long entreprise)
		{
			Entreprise etr=entrepriseRepository.getOne(entreprise);
			Profil p=profilRep.getOne(3);
			Representant rep=new Representant(user.getLogin(), user.getPassword(), user.getNom(), user.getPrenom(), p, etr);
			user.setProfil(p);
			representantrep.save(rep);
			//userRep.save(user);
			if(bindingResult.hasErrors()) {
				
				List<Entreprise> le=entrepriseRepository.findAll();
				model.addAttribute("listE", le);
				return "AjouterRepresentant";
			}
			
	
			return "redirect:/ajouterRep";
		}	
		
			@Secured(value={"ROLE_ADMIN"})
		@RequestMapping(value = "/ajouterRep",method=RequestMethod.GET)
		public String  ajouterRep(Model model) {
		List<Entreprise> le=entrepriseRepository.findAll();
		model.addAttribute("listE", le);
		model.addAttribute("user", new User());
		return "AjouterRepresentant";

		}
		

}































