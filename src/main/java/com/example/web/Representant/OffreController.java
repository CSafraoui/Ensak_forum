package com.example.web.Representant;


import com.example.dao.admin.UserRepository;
import com.example.dao.etudiant.EtudiantRepository;
import com.example.dao.etudiant.OffreRepository;
import com.example.dao.etudiant.PostulationRepository;
import com.example.dao.admin.RepresentantRepository;
import com.example.entites.*;
import com.example.service.etudiant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class OffreController {
    public static int numPlace = 0;
    @Autowired
    public OffreRepository repo;
    @Autowired
    private EtudiantMetiersImp etudiantMetier;
    @Autowired
    public PostulationRepository postulationRepository;
    @Autowired
    public OffreMetier offreMetier;
    @Autowired
    public PostulationMitier postulationMitier;
    @Autowired
    public RepresentantRepository representantRepo;
    @Autowired
    public EtudiantRepository etudiantRepository;
    @Autowired
    public UserRepository userRep;
    @Autowired
    private ReservationMetier reservationMetier;
    @Autowired
    private InscriptionMetier inscriptionMetier;
    @Autowired
    private PostulationMitier PostulationMetier;






    ////////////////////////Offre/////////////////////////////
    /////////////////////////////////////////////////////////


    //affichage d'index
    @Secured(value={"ROLE_REPRESENTANT"})
    @RequestMapping("/representantIndex")
    public String representant(HttpServletRequest httpServletRequest,Model model)
    {
        HttpSession httpSession=httpServletRequest.getSession();
        SecurityContext securityContext=(SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username=securityContext.getAuthentication().getName();
        User user=userRep.findUserByLogin(username);
        String name=user.getPrenom();
        Long id = user.getId_user();
        model.addAttribute("nom",name);
        Representant representant = representantRepo.findRepresentantById(id);
        Collection<Offre> L = representant.getOffres();
        model.addAttribute("L", L);
        return "representantIndex";

    }
    //liste des offres d'un representant
    @Secured(value = {"ROLE_REPRESENTANT"})
    @GetMapping(value = {"/listeOffres"})
    public String listeoffres(Model model,HttpServletRequest httpServletRequest) throws Exception {
            HttpSession httpSession = httpServletRequest.getSession();
            SecurityContext securityContext = (SecurityContext)
                    httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
            String username = securityContext.getAuthentication().getName();
            User user = repo.findRepresantantByName(username);
            Long id = user.getId_user();
            Representant representant = representantRepo.findRepresentantById(id);
            Collection<Offre> L = representant.getOffres();
            model.addAttribute("L", L);
            /*StringBuilder message = new StringBuilder();
            for (Offre s : L) {
                System.out.println("Titre : "+s.getTitre_offre());
                System.out.println("Description "+s.getDescription());
                System.out.println("Date "+s.getDate_pub());
                System.out.println("Entreprise "+s.getRep().getEntreprise().getNomE());
            }*/
            return "listeOffres";
        /*} catch (Exception e) {
            return "Il ya pas d'offres pour le moment";
        }*/
    }


    //modifier un offre
    @Secured(value = {"ROLE_REPRESENTANT"})
    @GetMapping(value = {"/ModifierOffre"})
    public String ModifierOffre(Model model,@RequestParam Long id){
        Offre offre=offreMetier.editOffre(id);
        model.addAttribute("offre",offre);
        return "ModifierOffre";
    }
    //liste des postulations
    @Secured(value = {"ROLE_REPRESENTANT"})
    @GetMapping(value = {"/AfficherPostulation"})
    public String AfficherPostulation(Model model, @RequestParam Long id){
        Collection<Postulation> postulation = postulationRepository.findPostulationByIdOffre(id);
        model.addAttribute("postulation",postulation);
        System.out.println(postulation);
        Collection<Etudiant> etudiants= new ArrayList<Etudiant>();
        for (Postulation p : postulation) {
            Etudiant e = etudiantRepository.getOne(p.getId_postulation().getId_etudiant());
            etudiants.add(e);
        }
        //students applied to this offer
        model.addAttribute("etudiants",etudiants);

        return "Postulation";
    }


    //charcher offre

    @Secured(value = {"ROLE_REPRESENTANT"})
    @GetMapping(value = {"/ChercherOffre"})
    public String ChercherOffre(String TitreOffre,HttpServletRequest httpServletRequest,Model model){
        HttpSession httpSession = httpServletRequest.getSession();
        SecurityContext securityContext = (SecurityContext)
                httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContext.getAuthentication().getName();
        User user = repo.findRepresantantByName(username);
        Long id = user.getId_user();
        Representant representant = representantRepo.findRepresentantById(id);
        Collection<Offre> L = repo.findOffreByTitre(TitreOffre,representant);
        model.addAttribute("L", L);
        return "ChercherOffre";
    }

    //ajouter offre
    @Secured(value = {"ROLE_REPRESENTANT"})
    @PostMapping(value = {"/saveoffre"})
    public String saveoffre(Model model,@Valid Offre offre, HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        HttpSession httpSession = httpServletRequest.getSession();
        SecurityContext securityContext = (SecurityContext)
                httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContext.getAuthentication().getName();
        User user = repo.findRepresantantByName(username);
        Long id_user = user.getId_user();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        offre.setDate_pub(date);
        Representant rep = representantRepo.findRepresentantById(id_user);
        offre.setRep(rep);
        offreMetier.saveOffre(offre);
        System.out.println(offre);
        Collection<Offre> L = rep.getOffres();
        if(bindingResult.hasErrors()) {
            return "AjouterAtelier";
        }
        return "redirect:/representantIndex";
    }

    @Secured(value = {"ROLE_REPRESENTANT"})
    @GetMapping(value = {"/deleteoffre"})
    public String deleteoffre(Long id, HttpServletRequest httpServletRequest) throws Exception {
        HttpSession httpSession = httpServletRequest.getSession();
        SecurityContext securityContext = (SecurityContext)
                httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        Collection<Postulation> postulation = postulationRepository.findPostulationByIdOffre(id);
        for (Postulation p : postulation){
            postulationRepository.delete(p);
        }
        String username = securityContext.getAuthentication().getName();
        User user = repo.findRepresantantByName(username);
        Long id_user = user.getId_user();
        Representant rep = representantRepo.findRepresentantById(id_user);
        Collection<Offre> collection = rep.getOffres();
        Iterator<Offre> iterator = collection.iterator();
        String message = "";
        boolean test = false;
        while (iterator.hasNext()) {
            if (id == iterator.next().getId_offre()) {
                repo.deleteById(id);
            }
        }
        Collection<Offre> L = rep.getOffres();
        return "redirect:/representantIndex";
    }


    @Secured(value = {"ROLE_REPRESENTANT"})
    @PostMapping(value = {"/modifieroffre"})
    public String modifieroffre(Model model,@Valid Offre offre, BindingResult bindingResult,HttpServletRequest httpServletRequest)
    {
        HttpSession httpSession = httpServletRequest.getSession();
        SecurityContext securityContext = (SecurityContext)
                httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContext.getAuthentication().getName();
        User user = repo.findRepresantantByName(username);
        Long id_user = user.getId_user();
        Representant representant = representantRepo.findRepresentantById(id_user);
        if(bindingResult.hasErrors()) {
            return "ModifierOffre";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        offre.setDate_pub(date);
        offre.setRep(representant);
        offreMetier.saveOffre(offre);
        Collection<Offre> L = representant.getOffres();
        return "redirect:/representantIndex#liste";
    }


    @Secured(value = {"ROLE_REPRESENTANT"})
    @GetMapping(value = {"/Profile"})
    //id of students who applied to an offer
    public String consulterProfil(Model model,@RequestParam Long id,HttpServletRequest httpServletRequest)
    {
        Etudiant etudiant = etudiantMetier.GetEtudiantById(id);
        //reservations effectuer par etudiant
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
        return "consulter_profil2";
    }


}
