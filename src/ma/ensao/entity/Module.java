package ma.ensao.entity;

import ma.ensao.entity.Categories;

public class Module {

	private Integer id;
	private String module;
	private String description;
	private String image;
	private Categories categorie;
	private boolean archiver ;
	private Integer editeur;
	
	public boolean isArchiver() {
		return archiver;
	}
	public void setArchiver(boolean archiver) {
		this.archiver = archiver;
	}
	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Module(Integer id, String module, String description, String image,
			Categories categorie , boolean archiver,Integer editeur) {
		super();
		this.id = id;
		this.module = module;
		this.description = description;
		this.image = image;
		this.categorie = categorie;
		this.archiver=archiver;
		this.editeur=editeur;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Categories getCategorie() {
		return categorie;
	}
	public void setCategorie(Categories categorie) {
		this.categorie = categorie;
	}
	public Integer getEditeur() {
		return editeur;
	}
	public void setEditeur(Integer editeur) {
		this.editeur = editeur;
	}
	
	
}
