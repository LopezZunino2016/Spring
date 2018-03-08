package es.altair.inventario.controller;

import java.io.Console;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.altair.inventario.bean.Articulo;
import es.altair.inventario.bean.Usuario;
import es.altair.inventario.dao.ArticuloDAO;
import es.altair.inventario.dao.UsuarioDAO;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDAO; 
	
	@Autowired
	private ArticuloDAO articuloDAO; 
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView inicio(@RequestParam(value="fallo",required=false,defaultValue="") String fallo,@RequestParam(value="mensaje",required=false,defaultValue="") String mensaje,Model model) {
		model.addAttribute("mensaje",mensaje);
		model.addAttribute("fallo",fallo);

		return new ModelAndView("home","usuario",new Usuario());
	}
	
	@RequestMapping(value="/Registrar", method=RequestMethod.POST)
	public String registrarUsuario(@ModelAttribute Usuario Usu) {
		
		int filas = 0; 
		String mensaje = ""; 
		
		if(!usuarioDAO.comprobarAlias(Usu)) {
			filas = usuarioDAO.registrar(Usu);
			if(filas == 1) {
				mensaje="Usuario Registrado"; 
				return "redirect:/?mensaje="+ mensaje; 
			}else {
				mensaje="No se ha registrado al usuario"; 
				return "redirect:/?mensaje="+ mensaje; 
			}
		}else {
			mensaje="Usuario ya se encuentra registrado"; 
			return "redirect:/?mensaje="+ mensaje; 
		}
	}
	
	@RequestMapping(value="/Principal", method=RequestMethod.GET)
	public ModelAndView principal(Model model,HttpSession sesion) {
		model.addAttribute("listaArticulo",articuloDAO.listar());	

		return new ModelAndView("Principal");

	}
	@RequestMapping(value="/inicioSesion",method=RequestMethod.POST)
	public String iniciarSesion(@ModelAttribute Usuario usu,HttpSession sesion) {
		usu = usuarioDAO.comprobarUsuario(usu.getAlias(), usu.getPassword());
		System.out.println(usu);
		if(usu != null) {
			sesion.setAttribute("usuarioLogeado", usu);
			return "redirect:/Principal"; 
		}else
		return "redirect:/?fallo=Usuario y/o Password Incorrecto";

	}
	
	@RequestMapping(value="/addArticulo", method=RequestMethod.GET)
	public ModelAndView addArticulo(@RequestParam(value="fallo",required=false,defaultValue="") String fallo,@RequestParam(value="mensaje",required=false,defaultValue="") String mensaje,Model model) {
		
		return new ModelAndView("anadirArticulo","articulo",new Articulo());

	}
	
	@RequestMapping(value="/AnadirA", method=RequestMethod.POST)
	public String anadirArticulo(@ModelAttribute Articulo art) {
		int filas = 0; 
		String mensaje = "";
		if(!articuloDAO.comprobarNombre(art.getNombre())) {
			filas = articuloDAO.anadirArticulo(art);
			if(filas == 1) {
				mensaje="Articulo Registrado"; 
				return "redirect:/Principal?mensaje="+ "Articulo Registrado";
			}else {
				mensaje="No se ha guardado el articulo"; 
				return "redirect:/addArticulo?mensaje="+ mensaje; 
			}
		}else {
			mensaje="el articulo ya se encuentra registradro"; 
			return "redirect:/addArticulo?mensaje="+ mensaje; 
		}
		
		
	}
	
	@RequestMapping(value ="/BorrarArticulo", method = RequestMethod.GET)
	public String eliminaArticulo(@RequestParam("idArticulo") String idArticulo,HttpServletRequest request) {
		System.out.println("Entra en el borrar");
		int idArt = Integer.parseInt(request.getParameter("idArticulo"));
		System.out.println("ID SELECCIONADO PARA BORRAR: " + idArt);
		articuloDAO.borrar(idArt);
		return "redirect:/Principal?mensaje="+ "Articulo borrado";
	}
	
	@RequestMapping(value="/EditarArticulo",method=RequestMethod.GET)
	public ModelAndView modelEditarVid(@RequestParam("idArticulo") String idArticulo,HttpServletRequest request) {
		int id=Integer.parseInt(request.getParameter("idArticulo"));		
		
		Articulo art = articuloDAO.obtenerArticuloPorId(id);
		System.out.println(art);
		return new ModelAndView("EditarArticulo","articulo", art);
	}
	
	@RequestMapping(value="/editarArticulo", method = RequestMethod.POST)
	public String editarArticulo (@ModelAttribute Articulo art){
		System.out.println("Antes del dao");
		articuloDAO.ActualizarArticulo(art);
		return "redirect:/Principal?mensaje="+ "Articulo editado";
	}
}
